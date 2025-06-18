
public class Employe {
private String nom; 
private String post;
private int salaire;


	public Employe(String nom, String post, int salaire) {
	this.nom=nom;
	this.post=post;
	this.salaire=salaire;
}

	public String getNom() {
	return nom;
}
	public String getPost() {
	return post;
}
	public int getSalaire() {
	return salaire;
}
	public void setNom(String nom) {
		this.nom=nom;
	}
	
	public void setPost(String post) {
		this.post=post;
	}
	
	public void setSalaire(int salaire) {
		this.salaire=salaire;
	}
	

	public String toString() {
		return "nom: " +nom + ", " + "post: " +post +"," + "salaire: " +salaire; }
	
	

}
