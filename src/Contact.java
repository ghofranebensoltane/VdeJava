
public class Contact {
 
	private String nom; 
	private String prenom; 
	private String email;
	private int telephone; 

	public Contact (String nom, String prenom, String email, int telephone) {
		this.nom = nom;
		this.prenom = prenom;
		this.email = email; 
		this.telephone = telephone; 
	}
	
	public void setNom(String nom) {
		this.nom = nom; 
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom; 
	}
	public void setEmail(String email) {
		this.email = email; 
	}
	public void setTelephone(int telephone) {
		this.telephone = telephone; 
	}

	
	
	public String getNom() {
		return nom; 
	}
	public String getPrenom() {
		return prenom;
	}
	public String getEmail() {
		return email;
	}
	public int getTelephone() {
		return telephone;
	}
	
	public String toString() {
		return "nom: " +nom + ", " + "prenom: " +prenom + ", " + "email: " +email + "telephone: " +telephone; 
 
	}
}
