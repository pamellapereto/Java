/**
 * POO - Fundamentos
 */

package minecraft;

/**
 * @author Pamella Pereto
 * Classe Modelo com Heran�a (extends)
 *
 */

public class Enxada extends Bloco {
	
	/**
	 * Construtor
	 */
	
	public Enxada() {
		System.out.println("******** CAMPO ********");
	}
	
	
	// Atributos
	boolean conquista;
	
	// M�todos
	
	/**
	 * M�todo usado para arar a terra
	 */
	void arar() {
		System.out.println("Terra preparada para o plantio");
		
		//ATEN��O!
		conquista = true;
	}
	
	
	// Polimorfismo (sobrescrita do m�todo minerar)
	
	/**
	 * M�todo usado para minerar a terra
	 */
	void minerar() {
		System.out.println("Dano causado!");
	}
	
	
}