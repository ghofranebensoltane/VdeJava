import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class GestionnaireStock {
	
	private List<Produit> produits; 
	 
	public GestionnaireStock() {
		produits = new ArrayList<Produit>();
	}
	
	private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=VDE;encrypt=false;";
    private static final String USER = "sa";
    private static final String PASSWORD = "123456";
	
	public void ajouterProduit(Produit produit) {
		String sql = "INSERT INTO Produit (nom, prix, quantite) VALUES (?, ?, ?)";

		     try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
		            PreparedStatement ps = conn.prepareStatement(sql)) {

		            ps.setString(1, produit.getNom());
		            ps.setDouble(2, produit.getPrix());
		            ps.setInt(3, produit.getQuantite());
		            

		            int rows = ps.executeUpdate();

		            if (rows > 0) {
		                System.out.println("Produit ajouté avec succès !");
		                produits.add(produit);
		            }

		        } catch (SQLException e) {
		            System.out.println("Erreur lors de l'ajout  : " + e.getMessage());
		        }
		    }
	
	public List<Produit> getAllProduits() {
		produits.clear();
        String sql = "SELECT id, nom, prix, quantite FROM Produit";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
        	System.out.println("\n Liste des produits dans la base :");
            while (rs.next()) {
            	Produit produit = new Produit(
                    rs.getInt("id"),
                    rs.getString("nom"),
                    rs.getDouble("prix"),
                    rs.getInt("quantite"));
            	    produits.add(produit);
                    System.out.println(produit);
               
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return produits;
    }

	public List<Produit> FindByPrice(){
	 List<Produit> stock=getAllProduits().stream().filter(s-> s.getQuantite()==0)
				.collect(Collectors.toList());
	 
	 System.out.println("\n Les produits en stcock: ");
	 stock.forEach(System.out::println);
	 
	 return stock; 
	}

	public List<Produit> sortedPrice(){
		List<Produit> stock= getAllProduits().stream()
				.sorted(Comparator.comparingDouble(Produit::getPrix).reversed()).collect(Collectors.toList());
		
		System.out.println("\n les prix triés par prix");
		stock.forEach(System.out::println);
		
		return stock; 
		
	}
	
	public Optional<Produit> findByName(String nomRecherche){
		Optional<Produit> stock = getAllProduits().stream()
				.filter(p->p.getNom().equalsIgnoreCase(nomRecherche)).findFirst();
		
	if(stock.isPresent()) {
		System.out.println("\n Produit trouvé: " +stock.get());
	}else {
		System.out.println("\n Aucun proDUIT trouvé avec ce nom : " +nomRecherche );
	}
	 return stock;
	
	}
	
	public List<Produit> findByLetter(String lettre){
		List<Produit> stockByLetter = getAllProduits().stream()
				.filter(p->p.getNom().toLowerCase().contains(lettre.toLowerCase()))
				.collect(Collectors.toList());
		
		  System.out.println("\n Produits contenant la lettre " + lettre + " :");
		  stockByLetter.forEach(System.out::println);
		  
		  return stockByLetter; 
	}
	public Produit chercherOuDefaut(String nomRecherche) {
		Produit produitParDefaut = new Produit("Inconnu", 0.0, 0); 
		 Produit produitTrouve = getAllProduits().stream()
			        .filter(p -> p.getNom().equalsIgnoreCase(nomRecherche))
			        .findFirst()
			        .orElse(produitParDefaut);
		  System.out.println("\n Résultat de la recherche : " + produitTrouve);
		    return produitTrouve;
	}
	public double moyenneDesPrix() {
	    double moyenne = getAllProduits().stream()
	        .mapToDouble(Produit::getPrix)
	        .average()
	        .orElse(0.0); 

	    System.out.println("\n Moyenne des prix : " + moyenne + "euro");
	    return moyenne;
	}

	public List<Produit> auDessusDeLaMoyenne() {
	    double moyenne = moyenneDesPrix(); 

	    List<Produit> result= getAllProduits().stream()
	        .filter(p -> p.getPrix() > moyenne)
	        .collect(Collectors.toList());

	    System.out.println("\n📊 Produits dont le prix est supérieur à la moyenne (" + moyenne + "euro) :");
	    result.forEach(System.out::println);

	    return result;
	}
	
	public void afficherAvecConsumer() {
	    Consumer<Produit> affichage = produit -> System.out.println(produit);

	    System.out.println("\nListe des produits (via Consumer) :");
	    getAllProduits().forEach(affichage);
	}
}