import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
   public static void main(String[] args) {
	   GestionnaireContact gestionnaire = new GestionnaireContact();
	   
	   String url = "jdbc:sqlserver://localhost:1433;databaseName=VDE;encrypt=false;";
       String user = "sa"; 
       String password = "123456"; 

       try {
           Connection conn = DriverManager.getConnection(url, user, password);
           System.out.println("✅ Connexion réussie à la base de données !");
           conn.close();
       } catch (SQLException e) {
           System.out.println("❌ Erreur de connexion : " + e.getMessage());
       }
       
    /*   Contact nouveauContact1 = new Contact("ghof", "bensoltane", "ghof.bensoltane@gmail.com", 56702402);
       Contact nouveauContact2 = new Contact ("kenet","kenet","kenet.kenet@gamil.com",52406852);	
       Contact nouveauContact3 = new Contact ("klein","jean","klein.jean@gmail.com",25841596);
       gestionnaire.ajouterContact(nouveauContact1);
       gestionnaire.ajouterContact(nouveauContact2);
       gestionnaire.ajouterContact(nouveauContact3);
       */
      // gestionnaire.afficherContactsFromDB();
       
      /* Contact modificationNouveauContact2 = new Contact("kenet","ngala","kenet.ngala@gmail.com",52406000);
       gestionnaire.modifierContact("kenet", modificationNouveauContact2);*/
       
       gestionnaire.supprimerContact("kenet");
       
   }
 }
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
