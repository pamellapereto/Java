package model;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Classe Modelo - Conexao com o banco
 * 
 * @author Pamella Pereto
 *
 */

public class DAO {
	// Parametros de conexao
	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://127.0.0.1:3306/dbSenhoritta";
	private String user = "root";
	private String password = "";

	/**
	 * Metodo responsavel pela conexao
	 * 
	 * @return con / null
	 */

	public Connection conectar() {

		// Objeto usado para conexao
		Connection con = null;

		try {
			// Uso do Driver
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			return con;
		}
		
		// Tratamento de excecoes
		catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
}
