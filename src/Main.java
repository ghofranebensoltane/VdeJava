
public class Main {
   public static void main(String[] args) {
	   GestionnaireContact gestionnaire = new GestionnaireContact();
	   
	   gestionnaire.chargerContacts();
	   
	  // Contact c1 = new Contact("ghofrane", "bensoltane", "ghof.bensoltane@gmail.com", 56702502);
	  // Contact c2 = new Contact("kenet", "Durand", "kenet.durand@gmail.com", 2569686);
      // Contact c3 = new Contact("ngala", "ngala", "ngala.ngala@gmail.com", 669696969);
      

       
       // gestionnaire.ajouterContact(c1);
      //  gestionnaire.ajouterContact(c2);
      //  gestionnaire.ajouterContact(c3);
        
      //  gestionnaire.sauvegarderContacts();
       
      // gestionnaire.chargerContacts();
       
       System.out.println("Contacts chargés depuis le fichier :");
       gestionnaire.afficherContact();
      
     //  Contact nouveau2 = new Contact("Sarah", "Lemoine", "sarah.lemoine@gmail.com", 11122333);
      // gestionnaire.ajouterContact(nouveau2);
       
       //gestionnaire.sauvegarderContacts();
       
      // System.out.println("Liste des contacts :");
      // gestionnaire.afficherContact();
       
       
      // Contact nouveau = new Contact("ngala", "hello", "ngala.hello@gmail.com", 669696969);
      // gestionnaire.modifierContact("ngala", nouveau);
      
     //  System.out.println("\nAprès modification :");
      // gestionnaire.afficherContact();
       
      // gestionnaire.supprimerContact("ngala");
       
      // System.out.println("\nAprès suppression :");
      // gestionnaire.afficherContact();
       
     //  gestionnaire.sauvegarderContacts();
	   
   }
}
