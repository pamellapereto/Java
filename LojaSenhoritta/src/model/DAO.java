package model;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Classe Modelo - Conexão com o banco
 * @author Pamella Pereto
 *
 */

public class DAO {
	//Parâmetros de conexão
	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://10.26.49.149:3306/dbSenhoritta";
	private String user = "root";
	private String password = "123@senac";
	
	/**
	 * Método responsável pela conexão
	 * @return con / null
	 */
			

	public Connection conectar() {
		
		//Objeto usado para conexão
		Connection con = null;
		
		//Tratamento de exceções
		
		try {
		//Uso do Driver
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			return con;
		}
		
		catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
}
