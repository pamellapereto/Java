package view;

import java.awt.EventQueue;

import javax.swing.JDialog;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextArea;

import javax.swing.border.TitledBorder;

import model.DAO;

import java.awt.Cursor;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;

public class Fornecedores extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JTextField txtBuscarFor;
	private JTextField txtForID;
	private JTextField txtForCNPJ;
	private JTextField txtForIE;
	private JTextField txtForIM;
	private JTextField txtForRazao;
	private JTextField txtForFantasia;
	private JTextField txtForSite;
	private JTextField txtForFone;
	private JTextField txtForContato;
	private JTextField txtForEmail;
	private JTextField txtForCEP;
	private JTextField txtForEndereco;
	private JTextField txtForNumero;
	private JTextField txtForComplemento;
	private JTextField txtForBairro;
	private JTextField txtForCidade;
	private JTable tblFornecedores;
	private JComboBox cboForUF;

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

		txtBuscarFor = new JTextField();
		txtBuscarFor.setBounds(97, 15, 181, 20);
		getContentPane().add(txtBuscarFor);
		txtBuscarFor.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(Fornecedores.class.getResource("/img/search.png")));
		lblNewLabel_1.setBounds(288, 12, 24, 24);
		getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("ID");
		lblNewLabel_2.setBounds(24, 139, 46, 14);
		getContentPane().add(lblNewLabel_2);

		txtForID = new JTextField();
		txtForID.setBounds(46, 136, 39, 20);
		getContentPane().add(txtForID);
		txtForID.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("CNPJ");
		lblNewLabel_3.setBounds(191, 139, 46, 14);
		getContentPane().add(lblNewLabel_3);

		txtForCNPJ = new JTextField();
		txtForCNPJ.setBounds(234, 136, 115, 20);
		getContentPane().add(txtForCNPJ);
		txtForCNPJ.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("IE");
		lblNewLabel_4.setBounds(365, 139, 15, 14);
		getContentPane().add(lblNewLabel_4);

		txtForIE = new JTextField();
		txtForIE.setBounds(390, 136, 107, 20);
		getContentPane().add(txtForIE);
		txtForIE.setColumns(10);

		JLabel lblNewLabel_4_1 = new JLabel("IM");
		lblNewLabel_4_1.setBounds(521, 139, 15, 14);
		getContentPane().add(lblNewLabel_4_1);

		txtForIM = new JTextField();
		txtForIM.setColumns(10);
		txtForIM.setBounds(543, 136, 141, 20);
		getContentPane().add(txtForIM);

		JLabel lblNewLabel_5 = new JLabel("Raz\u00E3o Social");
		lblNewLabel_5.setBounds(24, 182, 81, 14);
		getContentPane().add(lblNewLabel_5);

		txtForRazao = new JTextField();
		txtForRazao.setBounds(108, 179, 245, 20);
		getContentPane().add(txtForRazao);
		txtForRazao.setColumns(10);

		JLabel lblNewLabel_6 = new JLabel("Nome de fantasia");
		lblNewLabel_6.setBounds(371, 182, 107, 14);
		getContentPane().add(lblNewLabel_6);

		txtForFantasia = new JTextField();
		txtForFantasia.setBounds(480, 179, 204, 20);
		getContentPane().add(txtForFantasia);
		txtForFantasia.setColumns(10);

		JLabel lblNewLabel_7 = new JLabel("Site");
		lblNewLabel_7.setBounds(24, 223, 33, 14);
		getContentPane().add(lblNewLabel_7);

		txtForSite = new JTextField();
		txtForSite.setBounds(63, 220, 187, 20);
		getContentPane().add(txtForSite);
		txtForSite.setColumns(10);

		JLabel lblNewLabel_8 = new JLabel("Fone");
		lblNewLabel_8.setBounds(266, 223, 46, 14);
		getContentPane().add(lblNewLabel_8);

		txtForFone = new JTextField();
		txtForFone.setBounds(311, 220, 107, 20);
		getContentPane().add(txtForFone);
		txtForFone.setColumns(10);

		JLabel lblNewLabel_9 = new JLabel("Contato");
		lblNewLabel_9.setBounds(441, 223, 46, 14);
		getContentPane().add(lblNewLabel_9);

		txtForContato = new JTextField();
		txtForContato.setBounds(505, 220, 179, 20);
		getContentPane().add(txtForContato);
		txtForContato.setColumns(10);

		JLabel lblNewLabel_10 = new JLabel("E-mail");
		lblNewLabel_10.setBounds(24, 266, 46, 14);
		getContentPane().add(lblNewLabel_10);

		txtForEmail = new JTextField();
		txtForEmail.setBounds(71, 263, 179, 20);
		getContentPane().add(txtForEmail);
		txtForEmail.setColumns(10);

		JLabel lblNewLabel_11 = new JLabel("CEP");
		lblNewLabel_11.setBounds(281, 266, 46, 14);
		getContentPane().add(lblNewLabel_11);

		txtForCEP = new JTextField();
		txtForCEP.setBounds(316, 263, 81, 20);
		getContentPane().add(txtForCEP);
		txtForCEP.setColumns(10);

		JButton btnBuscarCEP = new JButton("Buscar CEP");
		btnBuscarCEP.setBounds(414, 260, 107, 23);
		getContentPane().add(btnBuscarCEP);

		JLabel lblNewLabel_12 = new JLabel("Endere\u00E7o");
		lblNewLabel_12.setBounds(24, 306, 59, 14);
		getContentPane().add(lblNewLabel_12);

		txtForEndereco = new JTextField();
		txtForEndereco.setBounds(94, 303, 218, 20);
		getContentPane().add(txtForEndereco);
		txtForEndereco.setColumns(10);

		JLabel lblNewLabel_13 = new JLabel("N\u00FAmero");
		lblNewLabel_13.setBounds(338, 306, 46, 14);
		getContentPane().add(lblNewLabel_13);

		txtForNumero = new JTextField();
		txtForNumero.setBounds(394, 303, 72, 20);
		getContentPane().add(txtForNumero);
		txtForNumero.setColumns(10);

		JLabel lblNewLabel_14 = new JLabel("Complemento");
		lblNewLabel_14.setBounds(495, 306, 87, 14);
		getContentPane().add(lblNewLabel_14);

		txtForComplemento = new JTextField();
		txtForComplemento.setBounds(585, 303, 99, 20);
		getContentPane().add(txtForComplemento);
		txtForComplemento.setColumns(10);

		JLabel lblNewLabel_15 = new JLabel("Bairro");
		lblNewLabel_15.setBounds(24, 350, 46, 14);
		getContentPane().add(lblNewLabel_15);

		txtForBairro = new JTextField();
		txtForBairro.setBounds(71, 347, 200, 20);
		getContentPane().add(txtForBairro);
		txtForBairro.setColumns(10);

		JLabel lblNewLabel_16 = new JLabel("Cidade");
		lblNewLabel_16.setBounds(294, 350, 46, 14);
		getContentPane().add(lblNewLabel_16);

		txtForCidade = new JTextField();
		txtForCidade.setBounds(349, 347, 200, 20);
		getContentPane().add(txtForCidade);
		txtForCidade.setColumns(10);

		JLabel lblNewLabel_17 = new JLabel("UF");
		lblNewLabel_17.setBounds(572, 350, 24, 14);
		getContentPane().add(lblNewLabel_17);

		cboForUF = new JComboBox();
		cboForUF.setModel(new DefaultComboBoxModel(
				new String[] { "", "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA",
						"PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO" }));
		cboForUF.setBounds(603, 346, 81, 22);
		getContentPane().add(cboForUF);

		JLabel lblNewLabel_18 = new JLabel("Observa\u00E7\u00E3o");
		lblNewLabel_18.setBounds(31, 407, 74, 14);
		getContentPane().add(lblNewLabel_18);

		txtForObs = new JTextArea();
		txtForObs.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		txtForObs.setBounds(128, 394, 313, 41);
		getContentPane().add(txtForObs);

		JButton btnAdicionar = new JButton("");
		btnAdicionar.setEnabled(false);
		btnAdicionar.setIcon(new ImageIcon(Fornecedores.class.getResource("/img/create.png")));
		btnAdicionar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAdicionar.setContentAreaFilled(false);
		btnAdicionar.setBorderPainted(false);
		btnAdicionar.setToolTipText("Adicionar");
		btnAdicionar.setBounds(495, 391, 64, 64);
		getContentPane().add(btnAdicionar);

		JButton btnAlterar = new JButton("");
		btnAlterar.setEnabled(false);
		btnAlterar.setIcon(new ImageIcon(Fornecedores.class.getResource("/img/update.png")));
		btnAlterar.setToolTipText("Alterar");
		btnAlterar.setContentAreaFilled(false);
		btnAlterar.setBorderPainted(false);
		btnAlterar.setBounds(560, 391, 64, 64);
		getContentPane().add(btnAlterar);

		JButton btnExcluir = new JButton("");
		btnExcluir.setEnabled(false);
		btnExcluir.setIcon(new ImageIcon(Fornecedores.class.getResource("/img/delete.png")));
		btnExcluir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnExcluir.setToolTipText("Excluir");
		btnExcluir.setContentAreaFilled(false);
		btnExcluir.setBorderPainted(false);
		btnExcluir.setBounds(623, 391, 64, 64);
		getContentPane().add(btnExcluir);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(24, 41, 530, 72);
		getContentPane().add(scrollPane);

		tblFornecedores = new JTable();
		scrollPane.setViewportView(tblFornecedores);

		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pesquisarFornecedor();
			}
		});
		btnBuscar.setBounds(97, 135, 68, 22);
		btnBuscar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		getContentPane().add(btnBuscar);

	} // Fim do construtor

	DAO dao = new DAO();
	private JButton btnAdicionar;
	private JButton btnAlterar;
	private JButton btnExcluir;
	private JTextArea txtForObs;
	

	/**
	 * Método responsável pela pesquisa de fornecedor
	 */

	private void pesquisarFornecedor() {

		// Validação
		if (txtForID.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite o ID do fornecedor");
			txtForID.requestFocus();
		}

		else {
			// Lógica principal
			// Query (instrução SQL)

			String read = "select * from fornecedores where idfor = ?;";

			try {
				// Estabelecer a conexão
				Connection con = dao.conectar();

				// Preparar a execução da query
				PreparedStatement pst = con.prepareStatement(read);

				// Setar o argumento (ID)
				// Substituir o ? pelo conteúdo da caixa de texto
				pst.setString(1, txtForID.getText());

				// Executar a query e exibir o resultado no formulário
				ResultSet rs = pst.executeQuery();

				// Validação (existência do fornecedor)
				// rs.next() -> existência de fornecedor
				if (rs.next()) {
					// Preencher (setar) os campos do formulário
					txtBuscarFor.setText(rs.getString(5));
					txtForCNPJ.setText(rs.getString(2));
					txtForIE.setText(rs.getString(3));
					txtForIM.setText(rs.getString(4));
					txtForRazao.setText(rs.getString(5));
					txtForFantasia.setText(rs.getString(6));
					txtForSite.setText(rs.getString(7));
					txtForFone.setText(rs.getString(8));
					txtForContato.setText(rs.getString(9));
					txtForEmail.setText(rs.getString(10));
					txtForCEP.setText(rs.getString(11));
					txtForEndereco.setText(rs.getString(12));
					txtForNumero.setText(rs.getString(13));
					txtForComplemento.setText(rs.getString(14));
					txtForBairro.setText(rs.getString(15));
					txtForCidade.setText(rs.getString(16));
					cboForUF.setSelectedItem(rs.getString(17));
					txtForObs.setText(rs.getString(18));
					txtBuscarFor.requestFocus();
					btnAlterar.setEnabled(true);
					btnExcluir.setEnabled(true);
				}

				else {
					// Validação

					JOptionPane.showMessageDialog(null, "Fornecedor inexistente!");
					btnAdicionar.setEnabled(true);

				}

				// NUNCA esquecer de encerrar a conexão
				con.close();

			}

			// Tratar exceções sempre que lidar com o banco
			catch (Exception e) {
				System.out.println(e);
			}

		}

	}

} // Fim do código
