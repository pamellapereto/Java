/**
 * Fundamentos do Java
 * Vari�veis, operadores e estruturas de controle
 * Exerc�cio 1 - APP Eleitor
 * Desenvolver um APP no modo console que em fun��o da idade do eleitor avise se o voto � obrigat�rio ou n�o
 * Abaixo de 16: VOTO PROIBIDO
 * Entre 16 e 17, incluindo estas idades: VOTO FACULTATIVO
 * Entre 18 e 69, incluindo estas idades: VOTO OBRIGAT�RIO
 * Acima ou igual a 70: VOTO FACULTATIVO
 */

package fundamentos;

import java.util.Scanner;

/**
 * @author Pamella Pereto
 *
 */

public class Eleitor {

	/**
	 * M�todo principal
	 * @param args
	 */
	
	public static void main(String[] args) {
		// Vari�veis
		int idade;
		
		// Objetos
		Scanner teclado = new Scanner (System.in);
		System.out.println("APP ELEITOR ===================");
		System.out.print("Digite sua idade: ");
		
		
		// Entrada
		idade = teclado.nextInt();
		
		// Apoio ao teste do fluxo (verificar se a idade foi capturada)
		// System.out.println(idade);
		
		// Processamento e sa�da
		if (idade < 16) {
			System.out.println("VOTO PROIBIDO");
		}
		
		else if (idade > 17 && idade < 71) {
			System.out.println("VOTO OBRIGAT�RIO");
		}
		
		else {
			System.out.println("VOTO FACULTATIVO");
		}
		
		// Encerrar a captura de dados
		teclado.close();
		

	}

}
