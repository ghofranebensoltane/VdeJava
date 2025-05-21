import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;

public class GestionnaireContact {
	
	private List<Contact> contacts; 
	
	String filePath = "C:\\Users\\Asus\\Documents\\contacts.txt";  
	private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=VDE;encrypt=false;";
    private static final String USER = "sa";
    private static final String PASSWORD = "123456";

	public GestionnaireContact () {
		contacts = new ArrayList<Contact>();
	}
	 
	
	
public void ajouterContact(Contact contact) {
	String sql = "INSERT INTO Contact (nom, prenom, email, telephone) VALUES (?, ?, ?, ?)";

	     try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
	            PreparedStatement ps = conn.prepareStatement(sql)) {

	            ps.setString(1, contact.getNom());
	            ps.setString(2, contact.getPrenom());
	            ps.setString(3, contact.getEmail());
	            ps.setInt(4, contact.getTelephone());

	            int rows = ps.executeUpdate();

	            if (rows > 0) {
	                System.out.println("Contact ajouté avec succès !");
	            }

	        } catch (SQLException e) {
	            System.out.println("Erreur lors de l'ajout du contact : " + e.getMessage());
	        }
	    }
	
	
	public void afficherContactsFromDB() {
	    try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
	         Statement stmt = conn.createStatement();
	         ResultSet rs = stmt.executeQuery("SELECT * FROM Contact")) {

	        while (rs.next()) {
	            String nom = rs.getString("nom");
	            String prenom = rs.getString("prenom");
	            String email = rs.getString("email");
	            int telephone = rs.getInt("telephone");

	            System.out.println("nom: " + nom + ", prenom: " + prenom + ", email: " + email + ", telephone: " + telephone);
	        }
	    } catch (SQLException e) {
	        System.out.println("Erreur d'affichage : " + e.getMessage());
	    }
	}
	
		
	
	public boolean modifierContact(String nomRecherche, Contact nouveauContact) {
	    String sql = "UPDATE Contact SET nom = ?, prenom = ?, email = ?, telephone = ? WHERE nom = ?";

	    try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
	         PreparedStatement stmt = conn.prepareStatement(sql)) {

	        stmt.setString(1, nouveauContact.getNom());
	        stmt.setString(2, nouveauContact.getPrenom());
	        stmt.setString(3, nouveauContact.getEmail());
	        stmt.setInt(4, nouveauContact.getTelephone());
	        stmt.setString(5, nomRecherche);

	        int lignesAffectees = stmt.executeUpdate();

	        return lignesAffectees > 0;

	    } catch (SQLException e) {
	        System.err.println("Erreur lors de la modification : " + e.getMessage());
	        return false;
	    }
	}
  
  
  
  public boolean supprimerContact(String nomRecherche) {
	    String sql = "DELETE FROM Contact WHERE nom = ?";

	    try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
	         PreparedStatement stmt = conn.prepareStatement(sql)) {

	        stmt.setString(1, nomRecherche); 
	        int lignesAffectees = stmt.executeUpdate(); 

	        return lignesAffectees > 0; 

	    } catch (SQLException e) {
	        System.err.println("Erreur lors de la suppression : " + e.getMessage());
	        return false;
	    }
	}
 
  
	
  public List<Contact> rechercherContact(String nomRecherche) {
	    List<Contact> resultats = new ArrayList<>();
	    String sql = "SELECT * FROM Contact WHERE nom = ?";

	    try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
	         PreparedStatement stmt = conn.prepareStatement(sql)) {

	        stmt.setString(1, nomRecherche);
	        ResultSet rs = stmt.executeQuery();

	        while (rs.next()) {
	            String nom = rs.getString("nom");
	            String prenom = rs.getString("prenom");
	            String email = rs.getString("email");
	            int telephone = rs.getInt("telephone");
	            Contact c = new Contact(nom, prenom, email, telephone);
	            resultats.add(c);
	        }

	    } catch (SQLException e) {
	        System.err.println("Erreur lors de la recherche : " + e.getMessage());
	    }
	    return resultats;
	}


}
