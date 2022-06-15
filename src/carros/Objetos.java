/**
 * POO - Tarefa (Exemplo: carros)
 */
package carros;

/**
 * @author Pamella Pereto
 * Classe responsável pela criação dos objetos
 */

import java.util.Random;

public class Objetos {

	/**
	 * Método principal
	 * @param args
	 */
	
	public static void main(String[] args) {
		
		//Criar objeto da classe Random usando o construtor padrão
        Random geradorAleatorio = new Random();

        
        int numero = geradorAleatorio.nextInt(10);
        System.out.println("Número inteiro aleatório de 0 até 10: " + numero);

        	
		// Objeto 1
		Carros corsa = new Carros();
		corsa.modelo = "Corsa";
		corsa.ano = 2017;
		corsa.cor = "Vermelho";
	
		System.out.println("Modelo do carro: " + corsa.modelo);
		System.out.println("Ano de fabricação: " + corsa.ano);
		System.out.println("Cor: " + corsa.cor);
		
		corsa.ligar();
		corsa.acelerar();
		corsa.desligar();
		
		// Objeto 2
		Carros fusca = new Carros();
		fusca.modelo = "Fusca";
		fusca.ano = 1938;
		fusca.cor = "Azul";
		
		System.out.println("Modelo do carro: " + fusca.modelo);
		System.out.println("Ano de fabricação: " + fusca.ano);
		System.out.println("Cor: " + fusca.cor);
				
		fusca.ligar();
		fusca.acelerar();
		fusca.desligar();
		
		// Objeto 3
		Carros uno = new Carros();
		uno.modelo = "Uno";
		uno.ano = 2008;
		uno.cor = "Preto";
				
		System.out.println("Modelo do carro: " + uno.modelo);
		System.out.println("Ano de fabricação: " + uno.ano);
		System.out.println("Cor: " + uno.cor);
						
		uno.ligar();
		uno.acelerar();
		uno.desligar();
				
				// Objeto 4
		Carros hb20 = new Carros();
		hb20.modelo = "HB20";
		hb20.ano = 2013;
		hb20.cor = "Grafite";
				
		System.out.println("Modelo do carro: " + hb20.modelo);
		System.out.println("Ano de fabricação: " + hb20.ano);
		System.out.println("Cor: " + hb20.cor);
						
		hb20.ligar();
		hb20.acelerar();
		hb20.desligar();

		
	}

}
