/**
 * POO - Fundamentos
 */

package minecraft;

/**
 * @author Pamella Pereto
 * Classe Modelo com Herança (extends)
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
	
	// Métodos
	
	/**
	 * Método usado para arar a terra
	 */
	void arar() {
		System.out.println("Terra preparada para o plantio");
		
		//ATENÇÃO!
		conquista = true;
	}
	
	
	// Polimorfismo (sobrescrita do método minerar)
	
	/**
	 * Método usado para minerar a terra
	 */
	void minerar() {
		System.out.println("Dano causado!");
	}
	
	
}