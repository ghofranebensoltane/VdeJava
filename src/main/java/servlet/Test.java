package servlet;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DataBaseConnexion;

@WebServlet("/Test")
public class Test extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        resp.setContentType("text/html");

        try (Connection conn = DataBaseConnexion.getConnection()) {
            if (conn != null && !conn.isClosed()) {
                resp.getWriter().println("<h2 style='color:green;'>✅ Connexion réussie à la base de données !</h2>");
            } else {
                resp.getWriter().println("<h2 style='color:red;'>❌ Connexion échouée !</h2>");
            }
        } catch (Exception e) {
            resp.getWriter().println("<h2 style='color:red;'>❌ Erreur : " + e.getMessage() + "</h2>");
            e.printStackTrace(resp.getWriter());
        }
    }
}
