/**
 * Jogo Minecraft (Exemplo: POO)
 */

package minecraft;

/**
 * @author Pamella Pereto
 *  Classe responsável pela criação dos itens
 */
public class Itens {

	/**
	 * Método principal
	 * @param args
	 */
	public static void main(String[] args) {
		Enxada enxadaDiamante = new Enxada();
		enxadaDiamante.resistencia = 10;
		enxadaDiamante.textura = "Diamante";
		enxadaDiamante.conquista = false;
		System.out.println("Enxada de " + enxadaDiamante.textura + ", com resistência de " + enxadaDiamante.resistencia);
		enxadaDiamante.minerar();
		enxadaDiamante.arar();
		
		if (enxadaDiamante.conquista == true) {
			System.out.println("__________________________________");
			System.out.println("Conquista obtida!");
			System.out.println("Dedicação séria. Hora do plantio.");
			System.out.println("__________________________________");
		}

	}

}
