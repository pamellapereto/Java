/**
 * POO - Tarefa
 */

package carros;


/**
 * @author Pamella Pereto
 * Classe Modelo com Herança (extends)
 */

public class Aeroporto extends Carros {
	
		public Aeroporto() {
			super();
			System.out.println("******AEROPORTO******");
		}

		// Atributos
		double envergadura;
		boolean aviaoAterrizado;
		
		// Métodos
		/**
		 * Método usado para aterrizar um avião
		 */
		void aterrizar() {
			System.out.println("Pista preparada!");
			aviaoAterrizado = true;
		}
		
		/**
		 * Método usado para desligar um avião
		 */
		void desligar() {
			System.out.println("Avião desligado");
		}

}