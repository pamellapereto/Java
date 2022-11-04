package view;

import java.awt.EventQueue;

import javax.swing.JDialog;
import java.awt.Toolkit;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.ImageIcon;

public class Sobre extends JDialog {

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
					Sobre dialog = new Sobre();
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
	public Sobre() {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Sobre.class.getResource("/img/logo.png")));
		setModal(true);
		setTitle("Sobre");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("Loja Senhoritta");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(170, 27, 110, 30);
		getContentPane().add(lblNewLabel);

		JLabel lblSobALicena = new JLabel("Sob a licença MIT");
		lblSobALicena.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblSobALicena.setBounds(184, 185, 96, 30);
		getContentPane().add(lblSobALicena);

		JLabel lblSobALicena_1 = new JLabel("");
		lblSobALicena_1.setIcon(new ImageIcon(Sobre.class.getResource("/img/mitLicense.png")));
		lblSobALicena_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblSobALicena_1.setBounds(126, 177, 48, 48);
		getContentPane().add(lblSobALicena_1);

	} // Fim do construtor
}
