/**
 * POO - Tarefa (Exemplo: carros)
 */
package carros;

/**
 * @author Pamella Pereto
 * Classe responsável pela criação dos objetos
 */

public class Objetos {

	/**
	 * Método principal
	 * @param args
	 */
	
	public static void main(String[] args) {
		// Objeto 1
		Carros mercedes = new Carros();
		mercedes.modelo = "Mercedes";
		mercedes.ano = 2017;
		mercedes.cor = "Vermelha";
	
		System.out.println("Modelo: " + mercedes.modelo);
		System.out.println("Ano: " + mercedes.ano);
		System.out.println("Cor: " + mercedes.cor);
		
		mercedes.ligar();
		mercedes.acelerar();
		mercedes.desligar();
		
		// Objeto 2
		Carros fusca = new Carros();
		fusca.modelo = "Fusca";
		fusca.ano = 1938;
		fusca.cor = "Azul";
		
		System.out.println("Modelo: " + fusca.modelo);
		System.out.println("Ano: " + fusca.ano);
		System.out.println("Cor: " + fusca.cor);
				
		fusca.ligar();
		fusca.acelerar();
		fusca.desligar();

	}

}
