/**
 * POO - Fundamentos
 */

package minecraft;

/**
 * @author Pamella Pereto
 * Classe Modelo
 */
public class Bloco {
	
	
	/**
	 * Construtor
	 */
	public Bloco() {
		super();
		System.out.println("==================================");
	}
	
	

	// Atributos
	int resistencia;
	String textura;
	
	// Métodos
	// void -> método simples sem retorno
	
	/**
	 * Método usado para construir um bloco
	 */
	void construir() {
		System.out.println("Bloco colocado");
	}
	
	/**
	 * Método usado para obter recursos de mineração
	 */
	void minerar() {
		System.out.println("Recursos obtidos");
	}
	
	/**
	 * Método usado para criar itens
	 */
	void craftar() {
		System.out.println("Item criado");
	}
}

