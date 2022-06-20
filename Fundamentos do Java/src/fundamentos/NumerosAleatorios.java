/**
 * 
 */
package fundamentos;

import java.util.Random;

/**
 * @author Pamella Pereto
 *
 */
public class NumerosAleatorios {
	
	/**
	 * Método principal
	 * @param args
	 */
	
	public static void main(String[] args) {
		// Uso da classe Random para gerar números aleatórios
		Random gerador = new Random();
		
		//Exemplo 1
		//(100) Gerar números entre 0 e 99
		int numero = gerador.nextInt(100);
		System.out.println("Número aleatório: " + numero);
		
		//Exemplo 2
		int dado = gerador.nextInt(6) + 1;
		System.out.println("Face do dado: " + dado);
		
		//Exemplo 3
		//A linha abaixo cria um objeto que contém caracteres
		String chassi = new String("0123456789ABCDEFGHIJKLMNOPQRSTUVXZ");
		System.out.print("Chassi: ===== ");
		for (int i = 0; i < 16; i++) {
			
		// A linha abaixo cria uma variável do tipo char que usa o objeto
		// gerador para gerar um número aleatório entre 0 e o tamanho máximo
		// de caracteres do objeto chassi (.length() -> tamanho da String)
		// (char) converter o tipo int para o tipo char
		char numeracao = (char)gerador.nextInt(chassi.length());
		
		//A linha abaixo imprime o valor da variável numeração
		//(.charAt*()) -> imprime apenas um caracter
		System.out.print(chassi.charAt(numeracao));
		
		}
		
		System.out.println(" =====");
	}

}
