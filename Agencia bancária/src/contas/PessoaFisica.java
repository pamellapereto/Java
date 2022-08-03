/**
 * Conta corrente
 */
package contas;


/**
 * @author Pamella Pereto
 *
 */
public class PessoaFisica {

	/**
	 * Método principal
	 * @param args
	 */
	public static void main(String[] args) {
		
		//Cliente 1
		Conta cc1 = new Conta();
		cc1.setCliente("Pamella Pereto");
		cc1.setSaldo (10000);
		System.out.println("Cliente: " + cc1.getCliente());
		cc1.exibirSaldo();
		cc1.sacar(150);
		cc1.exibirSaldo();

		
		//Cliente 2
		Conta cc2 = new Conta();
		cc2.setCliente("João Batista");
		cc2.setSaldo(5300);
		System.out.println("Cliente: " + cc2.getCliente());
		cc2.exibirSaldo();
		cc2.sacar(330);
		cc2.exibirSaldo();
		
		//Transferência
		System.out.println("__________________________________");
		System.out.println("");
		System.out.println("Transferência");
		System.out.println("__________________________________");
		System.out.println("Cliente: " + cc1.getCliente());
		System.out.println("Favorecido: " + cc2.getCliente());
		cc1.transferir(cc2, 2400);
		System.out.println("");
		System.out.println("");
		System.out.println("Cliente: " + cc1.getCliente());
		cc1.exibirSaldo();
		System.out.println("Cliente: " + cc2.getCliente());
		cc2.exibirSaldo();
		
		//Relatório gerencial
		System.out.println("__________________________________");
		System.out.println("");
		System.out.println("Relatório gerencial");
		Conta gerente = new Conta();
		double relatorio = gerente.soma(cc1.getSaldo(), cc2.getSaldo());
		System.out.println("Saldo total nas contas: R$ " + relatorio);
	}

}
