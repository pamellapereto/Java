/**
 * Seguros
 */
package seguros;

import contas.Conta;

/**
 * @author Pamella Pereto
 *
 */
public class SeguroPessoaFisica extends Conta {

	/**
	 * Método principal
	 * @param args
	 */
	public static void main(String[] args) {
		//Cliente 3
		//Usando o modificador protected deve-se utilizar a própria herança (SeguroPessoaFisica) para criar o objeto
		SeguroPessoaFisica cc3 = new SeguroPessoaFisica();
		cc3.setCliente("Maria Martins");
		cc3.setSaldo (530);
		System.out.println("Cliente: " + cc3.getCliente());
		cc3.exibirSaldo();
		cc3.depositar(50);
		cc3.exibirSaldo();

	}

}
