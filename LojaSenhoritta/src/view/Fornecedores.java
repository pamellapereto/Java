package view;

import java.awt.EventQueue;

import javax.swing.JDialog;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextArea;

import javax.swing.border.TitledBorder;
import java.awt.Cursor;

public class Fornecedores extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;
	private JTextField textField_10;
	private JTextField textField_11;
	private JTextField textField_12;
	private JTextField textField_13;
	private JTextField textField_14;
	private JTextField textField_15;
	private JTextField textField_16;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Fornecedores dialog = new Fornecedores();
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
	public Fornecedores() {
		setResizable(false);
		setModal(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Fornecedores.class.getResource("/img/logo.png")));
		setTitle("Fornecedores");
		setBounds(100, 100, 731, 505);
		getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("Fornecedor");
		lblNewLabel.setBounds(24, 16, 68, 14);
		getContentPane().add(lblNewLabel);

		textField = new JTextField();
		textField.setBounds(97, 15, 181, 20);
		getContentPane().add(textField);
		textField.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(Fornecedores.class.getResource("/img/search.png")));
		lblNewLabel_1.setBounds(288, 12, 24, 24);
		getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("ID");
		lblNewLabel_2.setBounds(24, 139, 46, 14);
		getContentPane().add(lblNewLabel_2);

		textField_1 = new JTextField();
		textField_1.setBounds(46, 136, 59, 20);
		getContentPane().add(textField_1);
		textField_1.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("CNPJ");
		lblNewLabel_3.setBounds(128, 139, 46, 14);
		getContentPane().add(lblNewLabel_3);

		textField_2 = new JTextField();
		textField_2.setBounds(171, 136, 141, 20);
		getContentPane().add(textField_2);
		textField_2.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("IE");
		lblNewLabel_4.setBounds(338, 139, 15, 14);
		getContentPane().add(lblNewLabel_4);

		textField_3 = new JTextField();
		textField_3.setBounds(356, 136, 141, 20);
		getContentPane().add(textField_3);
		textField_3.setColumns(10);

		JLabel lblNewLabel_4_1 = new JLabel("IM");
		lblNewLabel_4_1.setBounds(521, 139, 15, 14);
		getContentPane().add(lblNewLabel_4_1);

		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(543, 136, 141, 20);
		getContentPane().add(textField_4);

		JLabel lblNewLabel_5 = new JLabel("Raz\u00E3o Social");
		lblNewLabel_5.setBounds(24, 182, 81, 14);
		getContentPane().add(lblNewLabel_5);

		textField_5 = new JTextField();
		textField_5.setBounds(108, 179, 245, 20);
		getContentPane().add(textField_5);
		textField_5.setColumns(10);

		JLabel lblNewLabel_6 = new JLabel("Nome de fantasia");
		lblNewLabel_6.setBounds(371, 182, 107, 14);
		getContentPane().add(lblNewLabel_6);

		textField_6 = new JTextField();
		textField_6.setBounds(480, 179, 204, 20);
		getContentPane().add(textField_6);
		textField_6.setColumns(10);

		JLabel lblNewLabel_7 = new JLabel("Site");
		lblNewLabel_7.setBounds(24, 223, 33, 14);
		getContentPane().add(lblNewLabel_7);

		textField_7 = new JTextField();
		textField_7.setBounds(63, 220, 187, 20);
		getContentPane().add(textField_7);
		textField_7.setColumns(10);

		JLabel lblNewLabel_8 = new JLabel("Fone");
		lblNewLabel_8.setBounds(266, 223, 46, 14);
		getContentPane().add(lblNewLabel_8);

		textField_8 = new JTextField();
		textField_8.setBounds(311, 220, 107, 20);
		getContentPane().add(textField_8);
		textField_8.setColumns(10);

		JLabel lblNewLabel_9 = new JLabel("Contato");
		lblNewLabel_9.setBounds(441, 223, 46, 14);
		getContentPane().add(lblNewLabel_9);

		textField_9 = new JTextField();
		textField_9.setBounds(505, 220, 179, 20);
		getContentPane().add(textField_9);
		textField_9.setColumns(10);

		JLabel lblNewLabel_10 = new JLabel("E-mail");
		lblNewLabel_10.setBounds(24, 266, 46, 14);
		getContentPane().add(lblNewLabel_10);

		textField_10 = new JTextField();
		textField_10.setBounds(71, 263, 179, 20);
		getContentPane().add(textField_10);
		textField_10.setColumns(10);

		JLabel lblNewLabel_11 = new JLabel("CEP");
		lblNewLabel_11.setBounds(281, 266, 46, 14);
		getContentPane().add(lblNewLabel_11);

		textField_11 = new JTextField();
		textField_11.setBounds(316, 263, 81, 20);
		getContentPane().add(textField_11);
		textField_11.setColumns(10);

		JButton btnNewButton = new JButton("Buscar CEP");
		btnNewButton.setBounds(414, 260, 107, 23);
		getContentPane().add(btnNewButton);

		JLabel lblNewLabel_12 = new JLabel("Endere\u00E7o");
		lblNewLabel_12.setBounds(24, 306, 59, 14);
		getContentPane().add(lblNewLabel_12);

		textField_12 = new JTextField();
		textField_12.setBounds(94, 303, 218, 20);
		getContentPane().add(textField_12);
		textField_12.setColumns(10);

		JLabel lblNewLabel_13 = new JLabel("N\u00FAmero");
		lblNewLabel_13.setBounds(338, 306, 46, 14);
		getContentPane().add(lblNewLabel_13);

		textField_13 = new JTextField();
		textField_13.setBounds(394, 303, 72, 20);
		getContentPane().add(textField_13);
		textField_13.setColumns(10);

		JLabel lblNewLabel_14 = new JLabel("Complemento");
		lblNewLabel_14.setBounds(495, 306, 87, 14);
		getContentPane().add(lblNewLabel_14);

		textField_14 = new JTextField();
		textField_14.setBounds(585, 303, 99, 20);
		getContentPane().add(textField_14);
		textField_14.setColumns(10);

		JLabel lblNewLabel_15 = new JLabel("Bairro");
		lblNewLabel_15.setBounds(24, 350, 46, 14);
		getContentPane().add(lblNewLabel_15);

		textField_15 = new JTextField();
		textField_15.setBounds(71, 347, 200, 20);
		getContentPane().add(textField_15);
		textField_15.setColumns(10);

		JLabel lblNewLabel_16 = new JLabel("Cidade");
		lblNewLabel_16.setBounds(294, 350, 46, 14);
		getContentPane().add(lblNewLabel_16);

		textField_16 = new JTextField();
		textField_16.setBounds(349, 347, 200, 20);
		getContentPane().add(textField_16);
		textField_16.setColumns(10);

		JLabel lblNewLabel_17 = new JLabel("UF");
		lblNewLabel_17.setBounds(572, 350, 24, 14);
		getContentPane().add(lblNewLabel_17);

		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(
				new String[] { "", "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA",
						"PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO" }));
		comboBox.setBounds(603, 346, 81, 22);
		getContentPane().add(comboBox);

		JLabel lblNewLabel_18 = new JLabel("Observa\u00E7\u00E3o");
		lblNewLabel_18.setBounds(31, 407, 74, 14);
		getContentPane().add(lblNewLabel_18);

		JTextArea textArea = new JTextArea();
		textArea.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		textArea.setBounds(128, 394, 313, 41);
		getContentPane().add(textArea);

		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.setIcon(new ImageIcon(Fornecedores.class.getResource("/img/create.png")));
		btnNewButton_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton_1.setContentAreaFilled(false);
		btnNewButton_1.setBorderPainted(false);
		btnNewButton_1.setToolTipText("Adicionar");
		btnNewButton_1.setBounds(495, 391, 64, 64);
		getContentPane().add(btnNewButton_1);

		JButton btnNewButton_1_1 = new JButton("");
		btnNewButton_1_1.setIcon(new ImageIcon(Fornecedores.class.getResource("/img/update.png")));
		btnNewButton_1_1.setToolTipText("Alterar");
		btnNewButton_1_1.setContentAreaFilled(false);
		btnNewButton_1_1.setBorderPainted(false);
		btnNewButton_1_1.setBounds(560, 391, 64, 64);
		getContentPane().add(btnNewButton_1_1);

		JButton btnNewButton_1_2 = new JButton("");
		btnNewButton_1_2.setIcon(new ImageIcon(Fornecedores.class.getResource("/img/delete.png")));
		btnNewButton_1_2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton_1_2.setToolTipText("Excluir");
		btnNewButton_1_2.setContentAreaFilled(false);
		btnNewButton_1_2.setBorderPainted(false);
		btnNewButton_1_2.setBounds(623, 391, 64, 64);
		getContentPane().add(btnNewButton_1_2);

	} // Fim do construtor

}
