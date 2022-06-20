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
			
		//A linha abaixo cria um objeto que contém caracteres
		String chassi = new String("0123456789ABCDEFGHIJKLMNOPQRSTUVXZ");
		System.out.print("Chassi: ===== ");
		for (int i = 0; i < 16; i++) {
						
		//A linha abaixo cria uma variável do tipo char que usa o objeto
		//gerador para gerar um número aleatório entre 0 e o tamanho máximo
		//de caracteres do objeto chassi (.length() -> tamanho da String)
		//(char) converter o tipo int para o tipo char
		char numeracao = (char)gerador.nextInt(chassi.length());
					
		//A linha abaixo imprime o valor da variável numeração
		//(.charAt*()) -> imprime apenas um caracter
		System.out.print(chassi.charAt(numeracao));
					
		}
					
		System.out.println(" =====");

	}

		
		//Atributos
		String modelo;
		int ano;
		String cor;
		
		//Métodos
		//void -> método simples sem retorno
		
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
