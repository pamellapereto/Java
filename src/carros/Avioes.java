/**
 * POO - Tarefa (Exemplo: avi�es)
 */
package carros;

/**
 * @author Pamella Pereto
 * Classe respons�vel pela cria��o dos objetos
 */

public class Avioes {

	/**
	 * M�todo principal
	 * @param args
	 */
	
	public static void main(String[] args) {
		Aeroporto aviao = new Aeroporto();
		aviao.modelo = "Stratolaunch";
		aviao.cor = "branca";
		aviao.ano = 2014;
		aviao.envergadura = 117;
		aviao.aviaoAterrizado = false;
		
		System.out.println("Modelo da aeronave: " + aviao.modelo + ", na cor " + aviao.cor + ", fabricado em " + aviao.ano + ", com envergadura de " + aviao.envergadura + " metros.");
		
		aviao.aterrizar();
		
		if (aviao.aviaoAterrizado == true) {
			System.out.println("__________________________________");
			System.out.println("Avi�o aterrizado na pista!");
			System.out.println("__________________________________");
		}
		
		aviao.desligar();
		
	}

}
