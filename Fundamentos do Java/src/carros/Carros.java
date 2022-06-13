/**
 * POO - Tarefa
 */
package carros;

/**
 * @author Pamella Pereto
 * Classe Modelo
 */

public class Carros {
	
		
	/**
	 * Construtor
	 */
	public Carros() {
		super();
		System.out.println("================================");
	}

		// Atributos
		String modelo;
		int ano;
		String cor;
		
		// M�todos
		// void -> m�todo simples sem retorno
		
		/**
		 * M�todo usado para ligar um carro
		 */
		void ligar() {
			System.out.println("Carro ligado");
		}
		
		/**
		 * M�todo usado para desligar um carro
		 */
		void desligar() {
			System.out.println("Carro desligado");
		}
		
		/**
		 * M�todo usado para acelerar um carro
		 */
		void acelerar() {
			System.out.println("Carro acelerado");
		}

}
