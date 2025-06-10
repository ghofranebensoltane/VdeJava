package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.VoitureDao;
import model.Voiture;

@WebServlet("/VoitureServlet")
public class VoitureServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public VoitureServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         String action = request.getParameter("action"); 
         VoitureDao dao = new VoitureDao();
        if ("ajouter".equals(action)) {
            request.getRequestDispatcher("/ajouter.jsp").forward(request, response);
            return;
        }
        if ("modifier".equals(action)) {
            String immatriculation = request.getParameter("immatriculation");
            if (immatriculation == null || immatriculation.isEmpty()) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Immatriculation manquante");
                return;
            }
            try {
                Voiture voiture = dao.getVoitureParImmatriculation(immatriculation);
                if (voiture == null) {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND, "Voiture non trouvée");
                    return;
                }
                request.setAttribute("voiture", voiture);
                request.getRequestDispatcher("/modifier.jsp").forward(request, response);
            } catch (SQLException e) {
                e.printStackTrace();
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erreur base de données");
            }
            return;
        }
        if ("supprimer".equals(action)) {
            String immatriculation = request.getParameter("immatriculation");
            try {
                dao.supprimerVoiture(immatriculation);
                response.sendRedirect("VoitureServlet");
            } catch (SQLException e) {
                e.printStackTrace();
                response.getWriter().write("Erreur lors de la suppression");
            }
            return;
        }
        try {
            List<Voiture> voitures = dao.getToutesLesVoitures();
            request.setAttribute("voitures", voitures);
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write("Erreur lors de la récupération des voitures");
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8"); 
        
        String action = request.getParameter("action"); 
        String immatriculation = request.getParameter("immatriculation");
        String marque = request.getParameter("marque");
        String modele = request.getParameter("modele");
        String etat = request.getParameter("etat");

       
        if (etat != null) {
            etat = etat.trim();
        }

     
        System.out.println(" etat : '" + etat + "'");


        Voiture v = new Voiture(immatriculation, marque, modele, etat);

        VoitureDao dao = new VoitureDao();
        try {
            if ("modifier".equals(action)) {
                dao.modifierVoiture(v);
            } else {
                dao.ajouterVoiture(v);
            }
            response.sendRedirect("VoitureServlet");
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write("Erreur lors de la sauvegarde de la voiture");
        }
    }

  

}