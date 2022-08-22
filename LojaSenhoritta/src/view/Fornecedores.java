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
import javax.swing.table.DefaultTableModel;

import Atxy2k.CustomTextField.RestrictedTextField;
import model.DAO;
import net.proteanit.sql.DbUtils;

import java.awt.Cursor;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Font;

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
	private JTextArea txtForObs;

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
		setBounds(100, 100, 731, 546);
		getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("Fornecedor");
		lblNewLabel.setBounds(24, 16, 68, 14);
		getContentPane().add(lblNewLabel);

		txtBuscarFor = new JTextField();
		txtBuscarFor.setForeground(Color.DARK_GRAY);
		txtBuscarFor.setToolTipText("");
		txtBuscarFor.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				// Evento digitação
				buscarFornecedorTabela();

			}
		});
		txtBuscarFor.setBounds(94, 13, 148, 20);
		getContentPane().add(txtBuscarFor);
		txtBuscarFor.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("ID");
		lblNewLabel_2.setBounds(24, 139, 46, 14);
		getContentPane().add(lblNewLabel_2);

		txtForID = new JTextField();
		txtForID.setEditable(false);
		txtForID.setBounds(46, 136, 39, 20);
		getContentPane().add(txtForID);
		txtForID.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("CNPJ*");
		lblNewLabel_3.setBounds(191, 139, 46, 14);
		getContentPane().add(lblNewLabel_3);

		txtForCNPJ = new JTextField();
		txtForCNPJ.setBounds(234, 136, 125, 20);
		getContentPane().add(txtForCNPJ);
		txtForCNPJ.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("IE");
		lblNewLabel_4.setBounds(369, 139, 21, 14);
		getContentPane().add(lblNewLabel_4);

		txtForIE = new JTextField();
		txtForIE.setBounds(390, 136, 107, 20);
		getContentPane().add(txtForIE);
		txtForIE.setColumns(10);

		JLabel lblNewLabel_4_1 = new JLabel("IM");
		lblNewLabel_4_1.setBounds(523, 139, 21, 14);
		getContentPane().add(lblNewLabel_4_1);

		txtForIM = new JTextField();
		txtForIM.setColumns(10);
		txtForIM.setBounds(543, 136, 141, 20);
		getContentPane().add(txtForIM);

		JLabel lblNewLabel_5 = new JLabel("Raz\u00E3o Social*");
		lblNewLabel_5.setBounds(24, 182, 81, 14);
		getContentPane().add(lblNewLabel_5);

		txtForRazao = new JTextField();
		txtForRazao.setBounds(108, 179, 245, 20);
		getContentPane().add(txtForRazao);
		txtForRazao.setColumns(10);

		JLabel lblNewLabel_6 = new JLabel("Nome de fantasia*");
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

		JLabel lblNewLabel_8 = new JLabel("Fone*");
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

		JLabel lblNewLabel_11 = new JLabel("CEP*");
		lblNewLabel_11.setBounds(281, 266, 46, 14);
		getContentPane().add(lblNewLabel_11);

		txtForCEP = new JTextField();
		txtForCEP.setBounds(316, 263, 81, 20);
		getContentPane().add(txtForCEP);
		txtForCEP.setColumns(10);

		btnBuscarCEP = new JButton("Buscar CEP");
		btnBuscarCEP.setEnabled(false);
		btnBuscarCEP.setBounds(414, 260, 107, 23);
		getContentPane().add(btnBuscarCEP);

		JLabel lblNewLabel_12 = new JLabel("Endere\u00E7o*");
		lblNewLabel_12.setBounds(24, 306, 59, 14);
		getContentPane().add(lblNewLabel_12);

		txtForEndereco = new JTextField();
		txtForEndereco.setBounds(94, 303, 218, 20);
		getContentPane().add(txtForEndereco);
		txtForEndereco.setColumns(10);

		JLabel lblNewLabel_13 = new JLabel("N\u00FAmero*");
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

		JLabel lblNewLabel_15 = new JLabel("Bairro*");
		lblNewLabel_15.setBounds(24, 350, 46, 14);
		getContentPane().add(lblNewLabel_15);

		txtForBairro = new JTextField();
		txtForBairro.setBounds(71, 347, 200, 20);
		getContentPane().add(txtForBairro);
		txtForBairro.setColumns(10);

		JLabel lblNewLabel_16 = new JLabel("Cidade*");
		lblNewLabel_16.setBounds(294, 350, 46, 14);
		getContentPane().add(lblNewLabel_16);

		txtForCidade = new JTextField();
		txtForCidade.setBounds(349, 347, 200, 20);
		getContentPane().add(txtForCidade);
		txtForCidade.setColumns(10);

		JLabel lblNewLabel_17 = new JLabel("UF*");
		lblNewLabel_17.setBounds(572, 350, 24, 14);
		getContentPane().add(lblNewLabel_17);

		cboForUF = new JComboBox();
		cboForUF.setModel(new DefaultComboBoxModel(
				new String[] { "", "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA",
						"PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO" }));
		cboForUF.setBounds(603, 346, 81, 22);
		getContentPane().add(cboForUF);

		JLabel lblNewLabel_18 = new JLabel("Observa\u00E7\u00E3o");
		lblNewLabel_18.setBounds(24, 399, 74, 14);
		getContentPane().add(lblNewLabel_18);

		txtForObs = new JTextArea();
		txtForObs.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		txtForObs.setBounds(108, 394, 313, 41);
		getContentPane().add(txtForObs);

		btnAdicionar = new JButton("");
		btnAdicionar.setEnabled(false);
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adicionarFornecedor();
			}
		});
		btnAdicionar.setIcon(new ImageIcon(Fornecedores.class.getResource("/img/create.png")));
		btnAdicionar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAdicionar.setContentAreaFilled(false);
		btnAdicionar.setBorderPainted(false);
		btnAdicionar.setToolTipText("Adicionar");
		btnAdicionar.setBounds(495, 391, 64, 64);
		getContentPane().add(btnAdicionar);

		btnAlterar = new JButton("");
		btnAlterar.setEnabled(false);
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alterarFornecedor();
			}
		});
		btnAlterar.setIcon(new ImageIcon(Fornecedores.class.getResource("/img/update.png")));
		btnAlterar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAlterar.setToolTipText("Alterar");
		btnAlterar.setContentAreaFilled(false);
		btnAlterar.setBorderPainted(false);
		btnAlterar.setBounds(560, 391, 64, 64);
		getContentPane().add(btnAlterar);

		btnExcluir = new JButton("");
		btnExcluir.setEnabled(false);
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				excluirFornecedor();
			}
		});

		btnExcluir.setIcon(new ImageIcon(Fornecedores.class.getResource("/img/delete.png")));
		btnExcluir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnExcluir.setToolTipText("Excluir");
		btnExcluir.setContentAreaFilled(false);
		btnExcluir.setBorderPainted(false);
		btnExcluir.setBounds(623, 391, 64, 64);
		getContentPane().add(btnExcluir);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(24, 41, 552, 72);
		getContentPane().add(scrollPane);

		tblFornecedores = new JTable();

		tblFornecedores.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setarCaixasTexto();
			}
		});

		scrollPane.setViewportView(tblFornecedores);

		btnBuscar = new JButton("Buscar");
		btnBuscar.setEnabled(false);
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pesquisarFornecedor();
			}
		});
		btnBuscar.setBounds(97, 135, 84, 22);
		btnBuscar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		getContentPane().add(btnBuscar);

		JLabel lblNewLabel_13_1 = new JLabel("*Campos obrigat\u00F3rios");
		lblNewLabel_13_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_13_1.setForeground(Color.BLACK);
		lblNewLabel_13_1.setBounds(24, 469, 157, 14);
		getContentPane().add(lblNewLabel_13_1);

		// Validação com o uso da biblioteca Atxy2k

		// txtBuscarFor
		RestrictedTextField validartxtBuscarFor = new RestrictedTextField(txtBuscarFor);

		// Limitar a somente 100 caracteres no campo txtBuscarFor
		validartxtBuscarFor.setLimit(100);

	} // Fim do construtor

	// Criar objeto para acessar o banco
	DAO dao = new DAO();
	private JButton btnAdicionar;
	private JButton btnAlterar;
	private JButton btnExcluir;
	private JButton btnBuscar;
	private JButton btnBuscarCEP;

	/**
	 * Método responsável pela pesquisa avançada do fornecedor usando o nome de
	 * fantasia e a biblioteca rs2xml
	 */

	private void buscarFornecedorTabela() {
		String readT = "select idfor as ID, fantasia as Fornecedor, fone as Telefone, contato as Contato from fornecedores where fantasia like ?;";

		try {
			// Estabelecer a conexão
			Connection con = dao.conectar();

			// Preparar a execução da query
			PreparedStatement pst = con.prepareStatement(readT);

			// Setar o argumento (fantasia)
			// Substituir o ? pelo conteúdo da caixa de texto
			pst.setString(1, txtBuscarFor.getText() + "%");

			// Executar a query e exibir o resultado no formulário
			ResultSet rs = pst.executeQuery();

			// Uso da bilblioteca rs2xml para "popular" a tabela
			tblFornecedores.setModel(DbUtils.resultSetToTableModel(rs));

			if (txtBuscarFor.getText().isEmpty()) {
				limparCampos();
				txtForID.setText(null);
				btnBuscar.setEnabled(false);
			}

			// NUNCA esquecer de encerrar a conexão
			con.close();

		}

		// Tratar exceções sempre que lidar com o banco
		catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * Método responsável pela pesquisa do ID do fornecedor (setar as caixas de
	 * texto de acordo com os campos da tabela)
	 */

	private void setarCaixasTexto() {

		// Criar uma variável para receber a linha da tabela
		int setar = tblFornecedores.getSelectedRow();

		txtForID.setText(tblFornecedores.getModel().getValueAt(setar, 0).toString());
		txtBuscarFor.setText(tblFornecedores.getModel().getValueAt(setar, 1).toString());

		limparCampos();

		btnBuscar.setEnabled(true);

	}

	private void pesquisarFornecedor() {

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
				txtBuscarFor.setText(rs.getString(6));
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
				btnBuscarCEP.setEnabled(true);
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

	private void adicionarFornecedor() {

	}

	private void alterarFornecedor() {

		// Validação do CNPJ do fornecedor
		if (txtForCNPJ.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o CNPJ do fornecedor");
			txtForCNPJ.requestFocus();
		}

		// Validação da razão social do fornecedor
		else if (txtForRazao.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha a razão social do fornecedor");
			txtForRazao.requestFocus();
		}

		// Validação do nome fantasia do fornecedor
		else if (txtForFantasia.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o nome fantasia do fornecedor");
			txtForFantasia.requestFocus();
		}

		// Validação do telefone do fornecedor
		else if (txtForFone.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o telefone do fornecedor");
			txtForFone.requestFocus();
		}

		// Validação do CEP do fornecedor
		else if (txtForCEP.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o CEP do fornecedor");
			txtForCEP.requestFocus();
		}

		// Validação do endereço do fornecedor
		else if (txtForEndereco.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o endereço do fornecedor");
			txtForEndereco.requestFocus();
		}

		// Validação do número do fornecedor
		else if (txtForNumero.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o número do fornecedor");
			txtForNumero.requestFocus();
		}

		// Validação do bairro do fornecedor
		else if (txtForBairro.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o bairro do fornecedor");
			txtForBairro.requestFocus();
		}

		// Validação da cidade do fornecedor
		else if (txtForCidade.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha a cidade do fornecedor");
			txtForCidade.requestFocus();
		}

		// Validação da UF do fornecedor
		else if (cboForUF.getSelectedItem().equals("")) {
			JOptionPane.showMessageDialog(null, "Selecione a União Federativa (UF) do fornecedor");
			cboForUF.requestFocus();
		}

		else {
			// Lógica principal
			String update = "update fornecedores set cnpj = ?, ie = ?, im = ?, razao = ?, fantasia = ?, site = ?, fone = ?, contato = ?, email = ?, cep = ?, endereco = ?, numero = ?, complemento = ?, bairro = ?, cidade = ?, uf = ?, obs = ? where idfor = ?;";

			try {
				// Estabelecer a conexão
				Connection con = dao.conectar();

				// Preparar a execução da query
				PreparedStatement pst = con.prepareStatement(update);

				// Substituir as interrogações pelo conteúdo das caixas de texto
				pst.setString(1, txtForCNPJ.getText());
				pst.setString(2, txtForIE.getText());
				pst.setString(3, txtForIM.getText());
				pst.setString(4, txtForRazao.getText());
				pst.setString(5, txtForFantasia.getText());
				pst.setString(6, txtForSite.getText());
				pst.setString(7, txtForFone.getText());
				pst.setString(8, txtForContato.getText());
				pst.setString(9, txtForEmail.getText());
				pst.setString(10, txtForCEP.getText());
				pst.setString(11, txtForEndereco.getText());
				pst.setString(12, txtForNumero.getText());
				pst.setString(13, txtForComplemento.getText());
				pst.setString(14, txtForBairro.getText());
				pst.setString(15, txtForCidade.getText());
				pst.setString(16, cboForUF.getSelectedItem().toString());
				pst.setString(17, txtForObs.getText());
				pst.setString(18, txtForID.getText());

				// Executar a query e alterar o fornecedor no banco
				pst.executeUpdate();

				// Exibir mensagem ao alterar fornecedor cadastrado com sucesso no banco
				JOptionPane.showMessageDialog(null, "Dados do fornecedor atualizados com sucesso!");

				limparCampos();

				txtBuscarFor.setText(null);

				txtForID.setText(null);

				btnBuscar.setEnabled(false);

				// NUNCA esquecer de encerrar a conexão
				con.close();

			}

			catch (SQLIntegrityConstraintViolationException ex) {
				
				
				JOptionPane.showMessageDialog(null, "CNPJ já cadastrado. \nVerifique novamente.");
				txtForCNPJ.setText(null);
				txtForCNPJ.requestFocus();
				
				
			}

			//catch (SQLIntegrityConstraintViolationException ex) {

				//JOptionPane.showMessageDialog(null, "Inscrição estadual (IE) já cadastrada. \nVerifique novamente.");
				//txtForIE.setText(null);
				//txtForIE.requestFocus();
			//}

			//catch (SQLIntegrityConstraintViolationException ex) {

				//JOptionPane.showMessageDialog(null, "Inscrição municipal (IM) já cadastrada. \nVerifique novamente.");
				//txtForIM.setText(null);
				//txtForIM.requestFocus();
			//}
			

			catch (Exception e) {
				System.out.println(e);
			}

		}

	}

	private void excluirFornecedor() {

	}

	private void limparCampos() {
		// Limpar a tabela
		((DefaultTableModel) tblFornecedores.getModel()).setRowCount(0);

		txtForCNPJ.setText(null);
		txtForIE.setText(null);
		txtForIM.setText(null);
		txtForRazao.setText(null);
		txtForFantasia.setText(null);
		txtForSite.setText(null);
		txtForFone.setText(null);
		txtForContato.setText(null);
		txtForEmail.setText(null);
		txtForCEP.setText(null);
		txtForEndereco.setText(null);
		txtForNumero.setText(null);
		txtForComplemento.setText(null);
		txtForBairro.setText(null);
		txtForCidade.setText(null);
		cboForUF.setSelectedItem("");
		txtForObs.setText(null);
		btnAlterar.setEnabled(false);
		btnExcluir.setEnabled(false);
		btnBuscarCEP.setEnabled(false);

	}

} // Fim do código
