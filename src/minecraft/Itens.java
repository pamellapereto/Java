/**
 * Jogo Minecraft (Exemplo: POO)
 */

package minecraft;

/**
 * @author Pamella Pereto
 *  Classe respons�vel pela cria��o dos itens
 */
public class Itens {

	/**
	 * M�todo principal
	 * @param args
	 */
	public static void main(String[] args) {
		Enxada enxadaDiamante = new Enxada();
		enxadaDiamante.resistencia = 10;
		enxadaDiamante.textura = "Diamante";
		enxadaDiamante.conquista = false;
		System.out.println("Enxada de " + enxadaDiamante.textura + ", com resist�ncia de " + enxadaDiamante.resistencia);
		enxadaDiamante.minerar();
		enxadaDiamante.arar();
		
		if (enxadaDiamante.conquista == true) {
			System.out.println("__________________________________");
			System.out.println("Conquista obtida!");
			System.out.println("Dedica��o s�ria. Hora do plantio.");
			System.out.println("__________________________________");
		}

	}

}
