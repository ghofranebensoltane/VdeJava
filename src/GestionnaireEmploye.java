import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


public class GestionnaireEmploye {
	private List<Employe> employees; 
	public GestionnaireEmploye() {
		employees = new ArrayList<Employe>();
	}
	
	private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=VDE;encrypt=false;";
    private static final String USER = "sa";
    private static final String PASSWORD = "123456";
	
	public void ajouterEmploye(Employe employe) {
		String sql = "INSERT INTO Employe (nom, poste, salaire) VALUES (?, ?, ?)";

		     try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
		            PreparedStatement ps = conn.prepareStatement(sql)) {

		            ps.setString(1, employe.getNom());
		            ps.setString(2, employe.getPost());
		            ps.setInt(3, employe.getSalaire());
		            

		            int rows = ps.executeUpdate();

		            if (rows > 0) {
		                System.out.println("Employe ajouté avec succès !");
		                employees.add(employe);
		            }

		        } catch (SQLException e) {
		            System.out.println("Erreur lors de l'ajout  : " + e.getMessage());
		        }
		    }
		
	public List<Employe> afficheEmploye(){
		employees.forEach(System.out::println);
		return employees;
	}

	public void filterdeveloper(){
		 employees.stream().
				filter(e-> e.getPost().equalsIgnoreCase("Développeur")).
				forEach(System.out::println);
	}
	
	public void trierParSalaire(){
	 employees.stream().sorted(Comparator.comparingInt(Employe::getSalaire))
				.forEach(System.out::println);;
	}
	//avrege retourne un optionalDouble (car la liste peut etre vide)
	public double moyenne(){
	double moyenne=  employees.stream().mapToInt(Employe::getSalaire)
				.average()
				.orElse(0.0);
		  System.out.println(" Moyenne des salaires : " + moyenne);

		    return moyenne;
	}

	public Optional<Employe> findEmployeByName(String nomRecherche){
		Optional<Employe> result = employees.stream().filter(e -> e.getNom().equalsIgnoreCase(nomRecherche))
				.findFirst();//il faut utilser ca pour convertir du stream vers optional 
		
		
		if (result.isPresent()) {
			System.out.println("Employe trouvé: " +result.get());
		}else {
			System.out.println(" Aucun employé trouvé avec ce nom : " + nomRecherche);
		}
		return result; 
	}
	
	public Employe findEmployeByNameOrDefault(String nomRecherche) {
	    Employe employeParDefaut = new Employe("Inconnu", "Aucun poste", 0);

	    Employe result = employees.stream()
	                    .filter(e -> e.getNom().equalsIgnoreCase(nomRecherche))
	                    .findFirst()
	                    .orElse(employeParDefaut);
	    
	    System.out.println("Résultat de la recherche pour '" + nomRecherche + "' : " + result);
	    
	    return result;
	}
 
}
