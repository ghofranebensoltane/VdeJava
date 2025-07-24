package com.example.FirstApplicationSpring.controllers;


import com.example.FirstApplicationSpring.config.security.JwtUtil;
import com.example.FirstApplicationSpring.dto.AuthRequest;
import com.example.FirstApplicationSpring.dto.AuthResponse;
import com.example.FirstApplicationSpring.model.User;
import com.example.FirstApplicationSpring.services.TokenBlacklistService;
import com.example.FirstApplicationSpring.services.UserDetailsServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserDetailsServiceImpl userDetailsService;
    private final TokenBlacklistService tokenBlacklistService;


    @PostMapping("/authenticate")
    public AuthResponse createAuthenticationToken(@RequestBody AuthRequest authRequest) {
        try {
            // Authentifie l'utilisateur
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
            );
        } catch (AuthenticationException e) {
            throw new RuntimeException("Identifiants invalides");
        }

        UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUsername());

        String role = ((User) userDetails).getRole();
        String jwt = jwtUtil.generateToken(userDetails.getUsername(), role);
        String refreshToken = jwtUtil.generateRefreshToken(userDetails.getUsername());

        return new AuthResponse(jwt, refreshToken);
    }




    @PostMapping("/logout")
    public AuthResponse logout(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            tokenBlacklistService.blacklistToken(token);
            return new AuthResponse("Déconnecté avec succès", null);
        }
        return new AuthResponse("Aucun token Bearer trouvé", null);
    }

    @PostMapping("/refresh-token")
    public AuthResponse refreshToken(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String refreshToken = authHeader.substring(7);
            try {
                String username = jwtUtil.extractUsername(refreshToken);

                if (jwtUtil.isTokenExpired(refreshToken)) {
                    throw new RuntimeException("Refresh token expiré");
                }
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                String role = ((User) userDetails).getRole();
                String newToken = jwtUtil.generateToken(username, role);
                return new AuthResponse(newToken, refreshToken);

            } catch (Exception e) {
                throw new RuntimeException("Refresh token invalide");
            }
        }

        throw new RuntimeException("Aucun token Bearer trouvé");
    }
}
