/**
 * 
 */
package fundamentos;

import java.util.Scanner;

/**
 * @author Pamella Pereto
 *
 */
public class Tabuada {

	/**
	 * Método principal
	 * @param args
	 */
	public static void main(String[] args) {
		int valor;
		Scanner teclado = new Scanner (System.in);
		System.out.println("--------------- TABUADA ---------------");
		System.out.print("Digite o valor da tabuada: ");
		
		//Entrada
		valor = teclado.nextInt();
		System.out.println("Tabuada do " + valor);
		System.out.println("");
		
		//Processamento/Saída
		for (int i = 1; i < 11; i++) {
			System.out.println(valor + " x " + i + " = " + (valor * i));
		}
		
		// Encerrar a captura de dados
		teclado.close();

	}

}
