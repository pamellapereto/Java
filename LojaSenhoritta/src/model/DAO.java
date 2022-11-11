package model;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Classe Modelo - Conexao com o banco
 * 
 * @author Joao Vitor, Jefferson Cruz, Pamella Pereto e Pedro Guedes
 *
 */

public class DAO {
	// Parametros de conexao
	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://192.168.0.120:3306/lojasenhoritta"; //"jdbc:mysql://10.26.49.161:3306/lojasenhoritta";   //"jdbc:mysql://10.26.49.149:3306/lojasenhoritta";
	private String user = "root";
	private String password = "123@senac";

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
