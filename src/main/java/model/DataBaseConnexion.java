package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnexion {
	private static Connection connection;

	public static Connection getConnection() throws SQLException {
		
	    if (connection == null || connection.isClosed()) {
	    	
	        try {
	        	Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	            String url = "jdbc:sqlserver://localhost:1433;databaseName=garage_db;encrypt=false";
	            String username = "sa";
	            String password = "123456";

	            connection = DriverManager.getConnection(url, username, password);
	            System.out.println("✅ Connexion réussie !");
	        } catch (Exception e) {
	            e.printStackTrace();
	            throw new SQLException("Erreur de connexion à la base de données",e);
	        }
	    }
	    return connection;
	}

	public static void main(String[] args) {
	    System.out.println("Début du main");
	    try {
	        Connection conn = getConnection();
	        if (conn != null && !conn.isClosed()) {
	            System.out.println("Connexion à la base OK !");
	        } else {
	            System.out.println("Connexion à la base NOK");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    System.out.println("Fin du main");
	}
}
