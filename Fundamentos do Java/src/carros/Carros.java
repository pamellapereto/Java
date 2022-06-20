/**
 * POO - Tarefa
 */

package carros;

import java.util.Random;

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
		System.out.println("*********************");


		Random gerador = new Random();
			
		//A linha abaixo cria um objeto que cont�m caracteres
		String chassi = new String("0123456789ABCDEFGHIJKLMNOPQRSTUVXZ");
		System.out.print("Chassi: ===== ");
		for (int i = 0; i < 16; i++) {
						
		//A linha abaixo cria uma vari�vel do tipo char que usa o objeto
		//gerador para gerar um n�mero aleat�rio entre 0 e o tamanho m�ximo
		//de caracteres do objeto chassi (.length() -> tamanho da String)
		//(char) converter o tipo int para o tipo char
		char numeracao = (char)gerador.nextInt(chassi.length());
					
		//A linha abaixo imprime o valor da vari�vel numera��o
		//(.charAt*()) -> imprime apenas um caracter
		System.out.print(chassi.charAt(numeracao));
					
		}
					
		System.out.println(" =====");

	}

		
		//Atributos
		String modelo;
		int ano;
		String cor;
		
		//M�todos
		//void -> m�todo simples sem retorno
		
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
