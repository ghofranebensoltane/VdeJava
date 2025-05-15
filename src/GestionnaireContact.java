import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GestionnaireContact {
	
	private List<Contact> contacts; 
	String filePath = "C:\\Users\\Asus\\Documents\\contacts.txt";  

	public GestionnaireContact () {
		contacts = new ArrayList<Contact>();
	}
	 
	public void ajouterContact(Contact contact) {
		contacts.add(contact);
	}
	
	public void afficherContact(){
		/*
		 * for (Contact contact : listContact) { System.out.println(contact);}
		 */
		
		contacts.stream().forEach(contact -> {
			System.out.println(contact);
		});
	}
	
	public List<Contact> rechercherContact(List<Contact> listContact, String nom) {
		return listContact.stream().filter(contact -> contact.getNom().equalsIgnoreCase(nom)).toList();
	}
	
/*	List<Contact> c = new ArrayList<Contact>();
        for (Contact contact : listContact) {
        	if (nom.equals(contact.getNom())) {
             c.add(contact);
        	}
				return c; 
				break;
				}
	}
*/
  public boolean  modifierContact(String nomRecherche, Contact nouveauContact) {
	  for (Contact contact : contacts) {
		if (contact.getNom().equalsIgnoreCase(nomRecherche)) {
			contact.setNom(nouveauContact.getNom());
			contact.setPrenom(nouveauContact.getPrenom());
			contact.setEmail(nouveauContact.getEmail());
			contact.setTelephone(nouveauContact.getTelephone());
			return true; 
		}
	  }
		return false;  
  }
  
  public void supprimerContact(String nomRecherche) {
	contacts.removeIf(contact ->contact.getNom().equalsIgnoreCase(nomRecherche));
  }
 
  public void sauvegarderContacts(){
	  try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
	  contacts.stream().map(contact-> contact.getNom() + "," + contact.getPrenom() + "," + contact.getEmail() + "," + contact.getTelephone())
	  .forEach(contact ->{ 
	  try  { writer.write(contact);
	         writer.newLine();
	        } catch (IOException e) {
	            System.err.println("Erreur d'écriture dans le fichier : " + e.getMessage());
	        }
	  });
	  System.out.println("Contacts sauvegardés dans contact.txt");
	    } catch (IOException e) {
	        System.err.println("Erreur lors de l'ouverture du fichier : " + e.getMessage());
	    } 
  }

  
  public void chargerContacts() { 
	  
      String line;

      try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
          while ((line = reader.readLine()) != null) {
              String[] infos = line.split(",");

              if (infos.length == 4) {
                  String nom = infos[0].trim();      
                  String prenom = infos[1].trim();
                  String email = infos[2].trim();
                  int telephone = Integer.parseInt(infos[3].trim());
                  Contact c = new Contact(nom, prenom, email, telephone);
                  contacts.add(c);
              }
          }
      } catch (IOException e) {
          System.out.println("Erreur de lecture du fichier : " + e.getMessage());
      } catch (NumberFormatException e) {
          System.out.println("Erreur de conversion du numéro de téléphone : " + e.getMessage());
      }
  }
}
