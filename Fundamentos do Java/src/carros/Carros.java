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
		
		// Métodos
		// void -> método simples sem retorno
		
		/**
		 * Método usado para ligar um carro
		 */
		void ligar() {
			System.out.println("Carro ligado");
		}
		
		/**
		 * Método usado para desligar um carro
		 */
		void desligar() {
			System.out.println("Carro desligado");
		}
		
		/**
		 * Método usado para acelerar um carro
		 */
		void acelerar() {
			System.out.println("Carro acelerado");
		}

}
