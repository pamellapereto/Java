/**
 * POO - Tarefa
 */

package carros;


/**
 * @author Pamella Pereto
 * Classe Modelo com Heran�a (extends)
 */

public class Aeroporto extends Carros {
	
		public Aeroporto() {
			super();
			System.out.println("******AEROPORTO******");
		}

		// Atributos
		double envergadura;
		boolean aviaoAterrizado;
		
		// M�todos
		/**
		 * M�todo usado para aterrizar um avi�o
		 */
		void aterrizar() {
			System.out.println("Pista preparada!");
			aviaoAterrizado = true;
		}
		
		/**
		 * M�todo usado para desligar um avi�o
		 */
		void desligar() {
			System.out.println("Avi�o desligado");
		}

}