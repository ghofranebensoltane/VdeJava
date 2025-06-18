
public class Produit {
	 private int id;
	 private String nom;
	 private double prix;
	 private int quantite;
	 
	 
	 public Produit(int id, String nom, double prix, int quantite) {
		 this.id=id;
		 this.nom=nom;
		 this.prix=prix;
		 this.quantite=quantite;
	 }
	 
	 public Produit(String nom, double prix, int quantite) {
		 this.nom=nom;
		 this.prix=prix;
		 this.quantite=quantite;
	 }
	 
	 public int getId(){
		 return id; 
	 }
	 public String getNom(){
		 return nom; 
	 }
	 public double getPrix() {
		 return prix; 
	 }
	 public int getQuantite() {
		 return quantite; 
	 }
	 
	 public void setId(int id) {
		 this.id=id; 
	 }
	 public void setNom(String nom) {
		 this.nom=nom; 
	 }
	 public void setPrix(double prix) {
		 this.prix=prix; 
	 }
	 public void setQuantite(int quantite) {
		 this.quantite=quantite; 
	 }
	 public String toString() {
	        return nom + " -> Prix: " + prix + "euro, Quantite: " + quantite;
	    }
}
