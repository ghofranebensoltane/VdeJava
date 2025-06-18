import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class Main {
   public static void main(String[] args) {
	   GestionnaireContact gestionnaire = new GestionnaireContact();
	   GestionnaireEmploye gestionnaireEmploye = new GestionnaireEmploye(); 
	   Employe e1=  new Employe("Alice", "Développeur", 3200);
	   Employe e2 = new Employe("Bob", "Manager", 4500);
	   Employe e3 = new Employe("Charlie", "Développeur", 3100);
	   Employe e4 = new Employe("Diana", "RH", 2900);
	   Employe e5 =  new Employe("Eve", "Développeur", 3300);

	   
	   
	   
	   
	   
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
   
    gestionnaireEmploye.ajouterEmploye(e1);
    gestionnaireEmploye.ajouterEmploye(e2);
    gestionnaireEmploye.ajouterEmploye(e3);
    gestionnaireEmploye.ajouterEmploye(e4);
    gestionnaireEmploye.ajouterEmploye(e5);
   
     
    // gestionnaireEmploye.afficheEmploye();
    // gestionnaireEmploye.filterdeveloper();
    // gestionnaireEmploye.trierParSalaire();
    gestionnaireEmploye.moyenne();
    gestionnaireEmploye.findEmployeByName("ghofrane"); 
    
   gestionnaireEmploye.findEmployeByNameOrDefault("ghofrane");

   }
 }
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
