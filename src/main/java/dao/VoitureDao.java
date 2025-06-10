package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.DataBaseConnexion;
import model.Voiture;

public class VoitureDao {
	public void ajouterVoiture(Voiture v) throws SQLException {
        String sql = "INSERT INTO Voitures (immatriculation, marque, modele, etat) VALUES (?, ?, ?, ?)";
        try (Connection conn = DataBaseConnexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, v.getImmatriculation());
            stmt.setString(2, v.getMarque());
            stmt.setString(3, v.getModele());
            stmt.setString(4, v.getEtat());
            stmt.executeUpdate();
        }
    }
	public void modifierVoiture(Voiture v) throws SQLException {
        String sql = "UPDATE Voitures SET marque=?, modele=?, etat=? WHERE immatriculation=?";
        try (Connection conn = DataBaseConnexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, v.getMarque());
            stmt.setString(2, v.getModele());
            stmt.setString(3, v.getEtat());
            stmt.setString(4, v.getImmatriculation());
            stmt.executeUpdate();
        }
    }
	public Voiture getVoitureParImmatriculation(String immatriculation) throws SQLException {
	    String sql = "SELECT * FROM Voitures WHERE immatriculation = ?";
	    try (Connection conn = DataBaseConnexion.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {
	        stmt.setString(1, immatriculation);
	        ResultSet rs = stmt.executeQuery();
	        if (rs.next()) {
	            return new Voiture(
	                rs.getString("immatriculation"),
	                rs.getString("marque"),
	                rs.getString("modele"),
	                rs.getString("etat")
	            );
	        } else {
	            return null;
	        }
	    }
	}



    public void supprimerVoiture(String immatriculation) throws SQLException {
        String sql = "DELETE FROM Voitures WHERE immatriculation=?";
        try (Connection conn = DataBaseConnexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, immatriculation);
            stmt.executeUpdate();
        }
    }

  
    public List<Voiture> getToutesLesVoitures() throws SQLException {
        List<Voiture> liste = new ArrayList<>();
        String sql = "SELECT * FROM Voitures";
        try (Connection conn = DataBaseConnexion.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Voiture v = new Voiture(
                    rs.getString("immatriculation"),
                    rs.getString("marque"),
                    rs.getString("modele"),
                    rs.getString("etat")
                );
                liste.add(v);
            }
        }
        return liste;
    }

  
  
}
