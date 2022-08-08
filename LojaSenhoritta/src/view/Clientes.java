package view;

import java.awt.EventQueue;

import javax.swing.JDialog;
import java.awt.Toolkit;

public class Clientes extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Clientes dialog = new Clientes();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the dialog.
	 */
	public Clientes() {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Clientes.class.getResource("/img/logo.png")));
		setTitle("Clientes");
		setModal(true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);

	} // Fim do construtor

}
