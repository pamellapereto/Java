package view;

import java.awt.EventQueue;

import javax.swing.JDialog;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

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
	private JCheckBox chkIE;
	private JButton btnAdicionar;
	private JButton btnAlterar;
	private JButton btnExcluir;
	private JButton btnBuscar;
	private JButton btnBuscarCEP;
	private JCheckBox chkIM;
	private JTextField txtForObs;

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
		setBounds(100, 100, 718, 656);
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
				// Evento digitacao
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

		JLabel lblNewLabel_3 = new JLabel("CNPJ*");
		lblNewLabel_3.setBounds(24, 197, 46, 14);
		getContentPane().add(lblNewLabel_3);

		txtForCNPJ = new JTextField();
		txtForCNPJ.setBounds(67, 194, 125, 20);
		getContentPane().add(txtForCNPJ);

		JLabel lblNewLabel_4 = new JLabel("IE");
		lblNewLabel_4.setBounds(208, 197, 21, 14);
		getContentPane().add(lblNewLabel_4);

		txtForIE = new JTextField();
		txtForIE.setEditable(false);
		txtForIE.setBounds(228, 194, 171, 20);
		getContentPane().add(txtForIE);

		JLabel lblNewLabel_4_1 = new JLabel("IM");
		lblNewLabel_4_1.setBounds(441, 197, 21, 14);
		getContentPane().add(lblNewLabel_4_1);

		txtForIM = new JTextField();
		txtForIM.setEditable(false);
		txtForIM.setBounds(461, 194, 171, 20);
		getContentPane().add(txtForIM);

		JLabel lblNewLabel_5 = new JLabel("Razão Social*");
		lblNewLabel_5.setBounds(24, 243, 81, 14);
		getContentPane().add(lblNewLabel_5);

		txtForRazao = new JTextField();
		txtForRazao.setBounds(108, 240, 245, 20);
		getContentPane().add(txtForRazao);

		JLabel lblNewLabel_6 = new JLabel("Nome fantasia*");
		lblNewLabel_6.setBounds(371, 243, 107, 14);
		getContentPane().add(lblNewLabel_6);

		txtForFantasia = new JTextField();
		txtForFantasia.setBounds(480, 240, 204, 20);
		getContentPane().add(txtForFantasia);

		JLabel lblNewLabel_7 = new JLabel("Site");
		lblNewLabel_7.setBounds(24, 284, 33, 14);
		getContentPane().add(lblNewLabel_7);

		txtForSite = new JTextField();
		txtForSite.setBounds(63, 281, 187, 20);
		getContentPane().add(txtForSite);

		JLabel lblNewLabel_8 = new JLabel("Fone*");
		lblNewLabel_8.setBounds(266, 284, 46, 14);
		getContentPane().add(lblNewLabel_8);

		txtForFone = new JTextField();
		txtForFone.setBounds(311, 281, 107, 20);
		getContentPane().add(txtForFone);

		JLabel lblNewLabel_9 = new JLabel("Contato");
		lblNewLabel_9.setBounds(441, 284, 46, 14);
		getContentPane().add(lblNewLabel_9);

		txtForContato = new JTextField();
		txtForContato.setBounds(505, 281, 179, 20);
		getContentPane().add(txtForContato);

		JLabel lblNewLabel_10 = new JLabel("E-mail");
		lblNewLabel_10.setBounds(24, 327, 46, 14);
		getContentPane().add(lblNewLabel_10);

		txtForEmail = new JTextField();
		txtForEmail.setBounds(71, 324, 179, 20);
		getContentPane().add(txtForEmail);

		JLabel lblNewLabel_11 = new JLabel("CEP*");
		lblNewLabel_11.setBounds(24, 388, 46, 14);
		getContentPane().add(lblNewLabel_11);

		txtForCEP = new JTextField();
		txtForCEP.setBounds(59, 385, 81, 20);
		getContentPane().add(txtForCEP);

		btnBuscarCEP = new JButton("Buscar CEP");
		btnBuscarCEP.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnBuscarCEP.setBounds(157, 382, 107, 23);
		getContentPane().add(btnBuscarCEP);

		JLabel lblNewLabel_12 = new JLabel("Endereço*");
		lblNewLabel_12.setBounds(24, 431, 59, 14);
		getContentPane().add(lblNewLabel_12);

		txtForEndereco = new JTextField();
		txtForEndereco.setEditable(false);
		txtForEndereco.setBounds(94, 428, 218, 20);
		getContentPane().add(txtForEndereco);

		JLabel lblNewLabel_13 = new JLabel("Número*");
		lblNewLabel_13.setBounds(338, 431, 46, 14);
		getContentPane().add(lblNewLabel_13);

		txtForNumero = new JTextField();
		txtForNumero.setEditable(false);
		txtForNumero.setBounds(394, 428, 72, 20);
		getContentPane().add(txtForNumero);

		JLabel lblNewLabel_14 = new JLabel("Complemento");
		lblNewLabel_14.setBounds(495, 431, 87, 14);
		getContentPane().add(lblNewLabel_14);

		txtForComplemento = new JTextField();
		txtForComplemento.setEditable(false);
		txtForComplemento.setBounds(585, 428, 99, 20);
		getContentPane().add(txtForComplemento);

		JLabel lblNewLabel_15 = new JLabel("Bairro*");
		lblNewLabel_15.setBounds(24, 475, 46, 14);
		getContentPane().add(lblNewLabel_15);

		txtForBairro = new JTextField();
		txtForBairro.setEditable(false);
		txtForBairro.setBounds(71, 472, 200, 20);
		getContentPane().add(txtForBairro);

		JLabel lblNewLabel_16 = new JLabel("Cidade*");
		lblNewLabel_16.setBounds(294, 475, 46, 14);
		getContentPane().add(lblNewLabel_16);

		txtForCidade = new JTextField();
		txtForCidade.setEditable(false);
		txtForCidade.setBounds(349, 472, 200, 20);
		getContentPane().add(txtForCidade);

		JLabel lblNewLabel_17 = new JLabel("UF*");
		lblNewLabel_17.setBounds(572, 475, 24, 14);
		getContentPane().add(lblNewLabel_17);

		cboForUF = new JComboBox();
		cboForUF.setModel(new DefaultComboBoxModel(
				new String[] { "", "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA",
						"PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO" }));
		cboForUF.setBounds(603, 471, 81, 22);
		getContentPane().add(cboForUF);

		JLabel lblNewLabel_18 = new JLabel("Observação");
		lblNewLabel_18.setBounds(266, 327, 74, 14);
		getContentPane().add(lblNewLabel_18);

		txtForObs = new JTextField();
		txtForObs.setBounds(338, 324, 313, 20);
		getContentPane().add(txtForObs);

		chkIE = new JCheckBox("Editar Inscrição Estadual (IE)");
		chkIE.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (chkIE.isSelected()) {
					txtForIE.requestFocus();
					txtForIE.setEditable(true);
				}

				else {
					txtForIE.setText(null);
					txtForIE.setEditable(false);
				}
			}
		});

		chkIE.setBounds(223, 164, 211, 23);
		getContentPane().add(chkIE);

		chkIM = new JCheckBox("Editar Inscrição Municipal (IM)");
		chkIM.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chkIM.isSelected()) {
					txtForIM.requestFocus();
					txtForIM.setEditable(true);
				}

				else {
					txtForIM.setText(null);
					txtForIM.setEditable(false);

				}
			}
		});

		chkIM.setBounds(456, 164, 218, 23);
		getContentPane().add(chkIM);

		btnAdicionar = new JButton("");
		btnAdicionar.setEnabled(true);
		btnAdicionar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				if (chkIE.isSelected()) {
					adicionarFornecedorComIE();
				}

				else if (chkIM.isSelected()) {
					adicionarFornecedorComIM();
				}

				else if (chkIE.isSelected() && chkIM.isSelected()) {
					adicionarFornecedorComIEIM();
				}

				else {
					adicionarFornecedor();
				}

			}

		});

		btnAdicionar.setIcon(new ImageIcon(Fornecedores.class.getResource("/img/create.png")));
		btnAdicionar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAdicionar.setContentAreaFilled(false);
		btnAdicionar.setBorderPainted(false);
		btnAdicionar.setToolTipText("Adicionar");
		btnAdicionar.setBounds(492, 520, 64, 64);
		getContentPane().add(btnAdicionar);

		btnAlterar = new JButton("");
		btnAlterar.setEnabled(false);
		btnAlterar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				if (chkIE.isSelected()) {
					alterarFornecedorComIE();
				}

				else if (chkIM.isSelected()) {
					alterarFornecedorComIM();
				}

				else if (chkIE.isSelected() && chkIM.isSelected()) {
					alterarFornecedorComIEIM();
				}

				else {
					alterarFornecedor();
				}

			}

		});
		btnAlterar.setIcon(new ImageIcon(Fornecedores.class.getResource("/img/update.png")));
		btnAlterar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAlterar.setToolTipText("Alterar");
		btnAlterar.setContentAreaFilled(false);
		btnAlterar.setBorderPainted(false);
		btnAlterar.setBounds(557, 520, 64, 64);
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
		btnExcluir.setBounds(620, 520, 64, 64);
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
		btnBuscar.setBounds(97, 132, 84, 28);
		btnBuscar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		getContentPane().add(btnBuscar);

		JLabel lblNewLabel_13_1 = new JLabel("*Campos obrigatórios");
		lblNewLabel_13_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_13_1.setForeground(Color.BLACK);
		lblNewLabel_13_1.setBounds(24, 547, 157, 14);
		getContentPane().add(lblNewLabel_13_1);

		// Validacao com o uso da biblioteca Atxy2k

		// txtBuscarFor
		RestrictedTextField validartxtBuscarFor = new RestrictedTextField(txtBuscarFor);

		// Limitar a somente 100 caracteres no campo txtBuscarFor
		validartxtBuscarFor.setLimit(100);

		// txtForCNPJ
		RestrictedTextField validarCNPJ = new RestrictedTextField(txtForCNPJ);

		// Restringir a somente numeros no campo CNPJ
		validarCNPJ.setOnlyNums(true);
		// Limitar a somente 14 numeros no campo CNPJ
		validarCNPJ.setLimit(14);

		// txtForIE
		RestrictedTextField validarIE = new RestrictedTextField(txtForIE);

		// Restringir a somente numeros no campo IE
		validarIE.setOnlyNums(true);
		// Limitar a somente 14 numeros no campo IE
		validarIE.setLimit(14);

		// txtForIM
		RestrictedTextField validarIM = new RestrictedTextField(txtForIM);

		// Restringir a somente numeros no campo IM
		validarIM.setOnlyNums(true);
		// Limitar a somente 14 numeros no campo IM
		validarIM.setLimit(14);

		// txtForRazao
		RestrictedTextField validarRazao = new RestrictedTextField(txtForRazao);

		// Restringir a somente letras no campo razao
		validarRazao.setOnlyText(true);
		// Aceitar espaco no campo razao
		validarRazao.setAcceptSpace(true);
		// Limitar a somente 100 caracteres no campo razao
		validarRazao.setLimit(100);

		// txtForFantasia
		RestrictedTextField validarFantasia = new RestrictedTextField(txtForFantasia);

		// Limitar a somente 100 caracteres no campo fantasia
		validarFantasia.setLimit(100);

		// txtForSite
		RestrictedTextField validarSite = new RestrictedTextField(txtForSite);

		// Limitar a somente 60 caracteres no campo site
		validarSite.setLimit(60);

		// txtForFone
		RestrictedTextField validarFone = new RestrictedTextField(txtForFone);

		// Restringir a somente numeros no campo Fone
		validarFone.setOnlyNums(true);
		// Limitar a somente 14 numeros no campo Fone
		validarFone.setLimit(14);

		// txtForContato
		RestrictedTextField validarContato = new RestrictedTextField(txtForContato);

		// Restringir a somente letras no campo contato
		validarContato.setOnlyText(true);
		// Aceitar espaco no campo contato
		validarContato.setAcceptSpace(true);
		// Limitar a somente 20 caracteres no campo contato
		validarContato.setLimit(20);

		// txtForEmail
		RestrictedTextField validarEmail = new RestrictedTextField(txtForEmail);

		// Limitar a somente 50 caracteres no campo email
		validarEmail.setLimit(50);

		// txtForCEP
		RestrictedTextField validarCEP = new RestrictedTextField(txtForCEP);

		// Restringir a somente numeros no campo CEP
		validarCEP.setOnlyNums(true);
		// Limitar a somente 9 numeros no campo CEP
		validarCEP.setLimit(9);

		// txtForEndereco
		RestrictedTextField validarEndereco = new RestrictedTextField(txtForEndereco);

		// Restringir a somente letras no campo endereco
		validarEndereco.setOnlyText(true);
		// Aceitar espaco no campo endereco
		validarEndereco.setAcceptSpace(true);
		// Limitar a somente 60 caracteres no campo endereco
		validarEndereco.setLimit(60);

		// txtForNumero
		RestrictedTextField validarNumero = new RestrictedTextField(txtForNumero);

		// Restringir a somente digitos no campo numero
		validarNumero.setOnlyNums(true);
		// Limitar a somente 10 digitos no campo numero
		validarNumero.setLimit(10);

		// txtForComplemento
		RestrictedTextField validarComplemento = new RestrictedTextField(txtForComplemento);

		// Limitar a somente 50 caracteres no campo complemento
		validarComplemento.setLimit(50);

		// txtForBairro
		RestrictedTextField validarBairro = new RestrictedTextField(txtForBairro);

		// Limitar a somente 50 caracteres no campo bairro
		validarBairro.setLimit(50);

		// txtForCidade
		RestrictedTextField validarCidade = new RestrictedTextField(txtForCidade);

		// Restringir a somente letras no campo cidade
		validarCidade.setOnlyText(true);
		// Aceitar espaco no campo cidade
		validarCidade.setAcceptSpace(true);
		// Limitar a somente 50 caracteres no campo cidade
		validarCidade.setLimit(50);

		// txtForObs
		RestrictedTextField validarObs = new RestrictedTextField(txtForObs);

		// Limitar a 255 caracteres no campo obs
		validarObs.setLimit(255);

	} // Fim do construtor

	// Criar objeto para acessar o banco
	DAO dao = new DAO();

	/**
	 * Metodo responsavel pela pesquisa avançada do fornecedor usando o nome
	 * fantasia e a biblioteca rs2xml
	 */

	private void buscarFornecedorTabela() {
		String readT = "select idfor as ID, fantasia as Fornecedor, fone as Telefone, contato as Contato from fornecedores where fantasia like ?;";

		try {
			// Estabelecer a conexao
			Connection con = dao.conectar();

			// Preparar a execucao da query
			PreparedStatement pst = con.prepareStatement(readT);

			// Setar o argumento (fantasia)
			// Substituir o ? pelo conteudo da caixa de texto
			pst.setString(1, txtBuscarFor.getText() + "%");

			// Executar a query e exibir o resultado no formulario
			ResultSet rs = pst.executeQuery();

			// Uso da bilblioteca rs2xml para "popular" a tabela
			tblFornecedores.setModel(DbUtils.resultSetToTableModel(rs));

			if (txtBuscarFor.getText().isEmpty()) {
				limparCampos();
				txtForID.setText(null);
				btnBuscar.setEnabled(false);
				btnAdicionar.setEnabled(true);
				btnBuscarCEP.setEnabled(true);
			}

			// NUNCA esquecer de encerrar a conexao
			con.close();

		}

		// Tratar excecoes sempre que lidar com o banco
		catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * Metodo responsavel pela pesquisa do ID do fornecedor (setar as caixas de
	 * texto de acordo com os campos da tabela)
	 */

	private void setarCaixasTexto() {

		// Criar uma variavel para receber a linha da tabela
		int setar = tblFornecedores.getSelectedRow();

		txtForID.setText(tblFornecedores.getModel().getValueAt(setar, 0).toString());
		txtBuscarFor.setText(tblFornecedores.getModel().getValueAt(setar, 1).toString());

		limparCampos();

		btnBuscar.setEnabled(true);
		btnAdicionar.setEnabled(false);
		btnBuscarCEP.setEnabled(false);

	}

	private void pesquisarFornecedor() {

		// Logica principal
		// Query (instrucao SQL)

		String read = "select * from fornecedores where idfor = ?;";

		try {
			// Estabelecer a conexao
			Connection con = dao.conectar();

			// Preparar a execucao da query
			PreparedStatement pst = con.prepareStatement(read);

			// Setar o argumento (ID)
			// Substituir o ? pelo conteudo da caixa de texto
			pst.setString(1, txtForID.getText());

			// Executar a query e exibir o resultado no formulario
			ResultSet rs = pst.executeQuery();

			// Validacao (existencia do fornecedor)
			// rs.next() -> existencia de fornecedor
			if (rs.next()) {
				// Preencher (setar) os campos do formulario
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

			// NUNCA esquecer de encerrar a conexao
			con.close();

		}

		// Tratar excecoes sempre que lidar com o banco
		catch (Exception e) {
			System.out.println(e);
		}

	}

	private void adicionarFornecedorComIE() {

		// Validacao do CNPJ fornecedor
		if (txtForCNPJ.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o CNPJ do fornecedor");
			txtForCNPJ.requestFocus();
		}

		// Validacao do IE fornecedor
		if (txtForIE.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null,
					"Preencha corretamente o IE do fornecedor ou, caso não o tenha, desabilite-o.");
			txtForIE.requestFocus();
		}

		// Validacao da razao social do fornecedor
		else if (txtForRazao.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha a razão social do fornecedor");
			txtForRazao.requestFocus();
		}

		// Validacao do nome fantasia do fornecedor
		else if (txtForFantasia.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o nome fantasia do fornecedor");
			txtForFantasia.requestFocus();
		}

		// Validacao do telefone do fornecedor
		else if (txtForFone.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o telefone do fornecedor");
			txtForFone.requestFocus();
		}

		// Validacao do CEP do fornecedor
		else if (txtForCEP.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o CEP do fornecedor");
			txtForCEP.requestFocus();
		}

		// Validacao do endereco do fornecedor
		else if (txtForEndereco.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Clique em Buscar CEP para conferir o endereço do fornecedor");
			txtForEndereco.requestFocus();
		}

		// Validacao do numero do fornecedor
		else if (txtForNumero.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o número do fornecedor");
			txtForNumero.requestFocus();
		}

		// Validacao do bairro do fornecedor
		else if (txtForBairro.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o bairro do fornecedor");
			txtForBairro.requestFocus();
		}

		// Validacao da cidade do fornecedor
		else if (txtForCidade.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha a cidade do fornecedor");
			txtForCidade.requestFocus();
		}

		// Validacao da UF do fornecedor
		else if (cboForUF.getSelectedItem().equals("")) {
			JOptionPane.showMessageDialog(null, "Selecione a União Federativa (UF) do fornecedor");
			cboForUF.requestFocus();
		}

		else {
			// Logica principal
			String create = "insert into fornecedores (cnpj, ie, razao, fantasia, site, fone, contato, email, cep, endereco, numero, complemento, bairro, cidade, uf, obs) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

			try {
				// Estabelecer a conexao
				Connection con = dao.conectar();

				// Preparar a execucao da query
				PreparedStatement pst = con.prepareStatement(create);

				// Substituir as interrogacoes pelo conteudo das caixas de texto
				pst.setString(1, txtForCNPJ.getText());
				pst.setString(2, txtForIE.getText());
				pst.setString(3, txtForRazao.getText());
				pst.setString(4, txtForFantasia.getText());
				pst.setString(5, txtForSite.getText());
				pst.setString(6, txtForFone.getText());
				pst.setString(7, txtForContato.getText());
				pst.setString(8, txtForEmail.getText());
				pst.setString(9, txtForCEP.getText());
				pst.setString(10, txtForEndereco.getText());
				pst.setString(11, txtForNumero.getText());
				pst.setString(12, txtForComplemento.getText());
				pst.setString(13, txtForBairro.getText());
				pst.setString(14, txtForCidade.getText());
				pst.setString(15, cboForUF.getSelectedItem().toString());
				pst.setString(16, txtForObs.getText());

				// Executar a query e inserir o fornecedor no banco
				pst.executeUpdate();

				// Exibir mensagem ao cadastrar fornecedor com sucesso no banco
				JOptionPane.showMessageDialog(null, "Fornecedor cadastrado com sucesso!");

				limparCampos();
				((DefaultTableModel) tblFornecedores.getModel()).setRowCount(0);
				txtBuscarFor.setText(null);
				// NUNCA esquecer de encerrar a conexao
				con.close();

			}

			catch (SQLIntegrityConstraintViolationException ex) {

				JOptionPane.showMessageDialog(null,
						"Ocorreu um erro. \nVerifique novamente o CNPJ e/ou a Inscrição Estadual (IE).");
				txtForCNPJ.requestFocus();

			} catch (Exception e) {
				System.out.println(e);
			}

		}

	}

	private void adicionarFornecedorComIM() {

		// Validacao do CNPJ fornecedor
		if (txtForCNPJ.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o CNPJ do fornecedor");
			txtForCNPJ.requestFocus();
		}

		// Validacao do IM fornecedor
		if (txtForIM.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null,
					"Preencha corretamente o IM do fornecedor ou, caso não o tenha, desabilite-o.");
			txtForIM.requestFocus();
		}

		// Validacao da razao social do fornecedor
		else if (txtForRazao.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha a razão social do fornecedor");
			txtForRazao.requestFocus();
		}

		// Validacao do nome fantasia do fornecedor
		else if (txtForFantasia.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o nome fantasia do fornecedor");
			txtForFantasia.requestFocus();
		}

		// Validacao do telefone do fornecedor
		else if (txtForFone.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o telefone do fornecedor");
			txtForFone.requestFocus();
		}

		// Validacao do CEP do fornecedor
		else if (txtForCEP.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o CEP do fornecedor");
			txtForCEP.requestFocus();
		}

		// Validacao do endereco do fornecedor
		else if (txtForEndereco.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Clique em Buscar CEP para conferir o endereço do fornecedor");
			txtForEndereco.requestFocus();
		}

		// Validacao do numero do fornecedor
		else if (txtForNumero.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o número do fornecedor");
			txtForNumero.requestFocus();
		}

		// Validacao do bairro do fornecedor
		else if (txtForBairro.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o bairro do fornecedor");
			txtForBairro.requestFocus();
		}

		// Validacao da cidade do fornecedor
		else if (txtForCidade.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha a cidade do fornecedor");
			txtForCidade.requestFocus();
		}

		// Validacao da UF do fornecedor
		else if (cboForUF.getSelectedItem().equals("")) {
			JOptionPane.showMessageDialog(null, "Selecione a União Federativa (UF) do fornecedor");
			cboForUF.requestFocus();
		}

		else {
			// Logica principal
			String create = "insert into fornecedores (cnpj, im, razao, fantasia, site, fone, contato, email, cep, endereco, numero, complemento, bairro, cidade, uf, obs) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

			try {
				// Estabelecer a conexao
				Connection con = dao.conectar();

				// Preparar a execucao da query
				PreparedStatement pst = con.prepareStatement(create);

				// Substituir as interrogacoes pelo conteudo das caixas de texto
				pst.setString(1, txtForCNPJ.getText());
				pst.setString(2, txtForIM.getText());
				pst.setString(3, txtForRazao.getText());
				pst.setString(4, txtForFantasia.getText());
				pst.setString(5, txtForSite.getText());
				pst.setString(6, txtForFone.getText());
				pst.setString(7, txtForContato.getText());
				pst.setString(8, txtForEmail.getText());
				pst.setString(9, txtForCEP.getText());
				pst.setString(10, txtForEndereco.getText());
				pst.setString(11, txtForNumero.getText());
				pst.setString(12, txtForComplemento.getText());
				pst.setString(13, txtForBairro.getText());
				pst.setString(14, txtForCidade.getText());
				pst.setString(15, cboForUF.getSelectedItem().toString());
				pst.setString(16, txtForObs.getText());

				// Executar a query e inserir o fornecedor no banco
				pst.executeUpdate();

				// Exibir mensagem ao cadastrar fornecedor com sucesso no banco
				JOptionPane.showMessageDialog(null, "Fornecedor cadastrado com sucesso!");

				limparCampos();
				((DefaultTableModel) tblFornecedores.getModel()).setRowCount(0);
				txtBuscarFor.setText(null);
				// NUNCA esquecer de encerrar a conexao
				con.close();

			}

			catch (SQLIntegrityConstraintViolationException ex) {

				JOptionPane.showMessageDialog(null,
						"Ocorreu um erro. \nVerifique novamente o CNPJ e/ou a Inscrição Municipal (IM).");
				txtForCNPJ.requestFocus();

			} catch (Exception e) {
				System.out.println(e);
			}

		}

	}

	private void adicionarFornecedorComIEIM() {

		// Validacao do CNPJ fornecedor
		if (txtForCNPJ.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o CNPJ do fornecedor");
			txtForCNPJ.requestFocus();
		}

		// Validacao do IE fornecedor
		if (txtForIE.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null,
					"Preencha corretamente o IE do fornecedor ou, caso não o tenha, desabilite-o.");
			txtForIE.requestFocus();
		}

		// Validacao do IM fornecedor
		if (txtForIM.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null,
					"Preencha corretamente o IM do fornecedor ou, caso não o tenha, desabilite-o.");
			txtForIM.requestFocus();
		}

		// Validacao da razao social do fornecedor
		else if (txtForRazao.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha a razão social do fornecedor");
			txtForRazao.requestFocus();
		}

		// Validacao do nome fantasia do fornecedor
		else if (txtForFantasia.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o nome fantasia do fornecedor");
			txtForFantasia.requestFocus();
		}

		// Validacao do telefone do fornecedor
		else if (txtForFone.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o telefone do fornecedor");
			txtForFone.requestFocus();
		}

		// Validacao do CEP do fornecedor
		else if (txtForCEP.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o CEP do fornecedor");
			txtForCEP.requestFocus();
		}

		// Validacao do endereco do fornecedor
		else if (txtForEndereco.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Clique em Buscar CEP para conferir o endereço do fornecedor");
			txtForEndereco.requestFocus();
		}

		// Validacao do numero do fornecedor
		else if (txtForNumero.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o número do fornecedor");
			txtForNumero.requestFocus();
		}

		// Validacao do bairro do fornecedor
		else if (txtForBairro.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o bairro do fornecedor");
			txtForBairro.requestFocus();
		}

		// Validacao da cidade do fornecedor
		else if (txtForCidade.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha a cidade do fornecedor");
			txtForCidade.requestFocus();
		}

		// Validacao da UF do fornecedor
		else if (cboForUF.getSelectedItem().equals("")) {
			JOptionPane.showMessageDialog(null, "Selecione a União Federativa (UF) do fornecedor");
			cboForUF.requestFocus();
		}

		else {
			// Logica principal
			String create = "insert into fornecedores (cnpj, ie, im, razao, fantasia, site, fone, contato, email, cep, endereco, numero, complemento, bairro, cidade, uf, obs) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

			try {
				// Estabelecer a conexao
				Connection con = dao.conectar();

				// Preparar a execucao da query
				PreparedStatement pst = con.prepareStatement(create);

				// Substituir as interrogacoes pelo conteudo das caixas de texto
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

				// Executar a query e inserir o fornecedor no banco
				pst.executeUpdate();

				// Exibir mensagem ao cadastrar fornecedor com sucesso no banco
				JOptionPane.showMessageDialog(null, "Fornecedor cadastrado com sucesso!");

				limparCampos();
				((DefaultTableModel) tblFornecedores.getModel()).setRowCount(0);
				txtBuscarFor.setText(null);
				// NUNCA esquecer de encerrar a conexao
				con.close();

			}

			catch (SQLIntegrityConstraintViolationException ex) {

				JOptionPane.showMessageDialog(null,
						"Ocorreu um erro. \nVerifique novamente o CNPJ, a Inscrição Estadual (IE) e/ou a Inscrição Municipal (IM).");
				txtForCNPJ.requestFocus();

			} catch (Exception e) {
				System.out.println(e);
			}

		}

	}

	private void adicionarFornecedor() {

		// Validacao do CNPJ fornecedor
		if (txtForCNPJ.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o CNPJ do fornecedor");
			txtForCNPJ.requestFocus();
		}

		// Validacao da razao social do fornecedor
		else if (txtForRazao.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha a razão social do fornecedor");
			txtForRazao.requestFocus();
		}

		// Validacao do nome fantasia do fornecedor
		else if (txtForFantasia.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o nome fantasia do fornecedor");
			txtForFantasia.requestFocus();
		}

		// Validacao do telefone do fornecedor
		else if (txtForFone.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o telefone do fornecedor");
			txtForFone.requestFocus();
		}

		// Validacao do CEP do fornecedor
		else if (txtForCEP.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o CEP do fornecedor");
			txtForCEP.requestFocus();
		}

		// Validacao do endereco do fornecedor
		else if (txtForEndereco.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Clique em Buscar CEP para conferir o endereço do fornecedor");
			txtForEndereco.requestFocus();
		}

		// Validacao do numero do fornecedor
		else if (txtForNumero.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o número do fornecedor");
			txtForNumero.requestFocus();
		}

		// Validacao do bairro do fornecedor
		else if (txtForBairro.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o bairro do fornecedor");
			txtForBairro.requestFocus();
		}

		// Validacao da cidade do fornecedor
		else if (txtForCidade.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha a cidade do fornecedor");
			txtForCidade.requestFocus();
		}

		// Validacao da UF do fornecedor
		else if (cboForUF.getSelectedItem().equals("")) {
			JOptionPane.showMessageDialog(null, "Selecione a União Federativa (UF) do fornecedor");
			cboForUF.requestFocus();
		}

		else {
			// Logica principal
			String create = "insert into fornecedores (cnpj, razao, fantasia, site, fone, contato, email, cep, endereco, numero, complemento, bairro, cidade, uf, obs) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

			try {
				// Estabelecer a conexao
				Connection con = dao.conectar();

				// Preparar a execucao da query
				PreparedStatement pst = con.prepareStatement(create);

				// Substituir as interrogacoes pelo conteudo das caixas de texto
				pst.setString(1, txtForCNPJ.getText());
				pst.setString(2, txtForRazao.getText());
				pst.setString(3, txtForFantasia.getText());
				pst.setString(4, txtForSite.getText());
				pst.setString(5, txtForFone.getText());
				pst.setString(6, txtForContato.getText());
				pst.setString(7, txtForEmail.getText());
				pst.setString(8, txtForCEP.getText());
				pst.setString(9, txtForEndereco.getText());
				pst.setString(10, txtForNumero.getText());
				pst.setString(11, txtForComplemento.getText());
				pst.setString(12, txtForBairro.getText());
				pst.setString(13, txtForCidade.getText());
				pst.setString(14, cboForUF.getSelectedItem().toString());
				pst.setString(15, txtForObs.getText());

				// Executar a query e inserir o fornecedor no banco
				pst.executeUpdate();

				// Exibir mensagem ao cadastrar fornecedor com sucesso no banco
				JOptionPane.showMessageDialog(null, "Fornecedor cadastrado com sucesso!");

				limparCampos();
				((DefaultTableModel) tblFornecedores.getModel()).setRowCount(0);
				txtBuscarFor.setText(null);
				// NUNCA esquecer de encerrar a conexao
				con.close();

			}

			catch (SQLIntegrityConstraintViolationException ex) {

				JOptionPane.showMessageDialog(null, "Ocorreu um erro. \nVerifique novamente o CNPJ.");
				txtForCNPJ.requestFocus();

			} catch (Exception e) {
				System.out.println(e);
			}

		}

	}

	private void alterarFornecedorComIE() {

		// Validacao do CNPJ fornecedor
		if (txtForCNPJ.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o CNPJ do fornecedor");
			txtForCNPJ.requestFocus();
		}

		// Validacao do IE fornecedor
		if (txtForIE.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null,
					"Preencha corretamente o IE do fornecedor ou, caso não o tenha, desabilite-o.");
			txtForIE.requestFocus();
		}

		// Validacao da razao social do fornecedor
		else if (txtForRazao.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha a razão social do fornecedor");
			txtForRazao.requestFocus();
		}

		// Validacao do nome fantasia do fornecedor
		else if (txtForFantasia.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o nome fantasia do fornecedor");
			txtForFantasia.requestFocus();
		}

		// Validacao do telefone do fornecedor
		else if (txtForFone.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o telefone do fornecedor");
			txtForFone.requestFocus();
		}

		// Validacao do CEP do fornecedor
		else if (txtForCEP.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o CEP do fornecedor");
			txtForCEP.requestFocus();
		}

		// Validacao do endereco do fornecedor
		else if (txtForEndereco.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o endereço do fornecedor");
			txtForEndereco.requestFocus();
		}

		// Validacao do numero do fornecedor
		else if (txtForNumero.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o número do fornecedor");
			txtForNumero.requestFocus();
		}

		// Validacao do bairro do fornecedor
		else if (txtForBairro.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o bairro do fornecedor");
			txtForBairro.requestFocus();
		}

		// Validacao da cidade do fornecedor
		else if (txtForCidade.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha a cidade do fornecedor");
			txtForCidade.requestFocus();
		}

		// Validacao da UF do fornecedor
		else if (cboForUF.getSelectedItem().equals("")) {
			JOptionPane.showMessageDialog(null, "Selecione a União Federativa (UF) do fornecedor");
			cboForUF.requestFocus();
		}

		else {
			// Logica principal
			String update = "update fornecedores set cnpj = ?, ie = ?, razao = ?, fantasia = ?, site = ?, fone = ?, contato = ?, email = ?, cep = ?, endereco = ?, numero = ?, complemento = ?, bairro = ?, cidade = ?, uf = ?, obs = ? where idfor = ?;";

			try {
				// Estabelecer a conexao
				Connection con = dao.conectar();

				// Preparar a execucao da query
				PreparedStatement pst = con.prepareStatement(update);

				// Substituir as interrogacoes pelo conteudo das caixas de texto
				pst.setString(1, txtForCNPJ.getText());
				pst.setString(2, txtForIE.getText());
				pst.setString(3, txtForRazao.getText());
				pst.setString(4, txtForFantasia.getText());
				pst.setString(5, txtForSite.getText());
				pst.setString(6, txtForFone.getText());
				pst.setString(7, txtForContato.getText());
				pst.setString(8, txtForEmail.getText());
				pst.setString(9, txtForCEP.getText());
				pst.setString(10, txtForEndereco.getText());
				pst.setString(11, txtForNumero.getText());
				pst.setString(12, txtForComplemento.getText());
				pst.setString(13, txtForBairro.getText());
				pst.setString(14, txtForCidade.getText());
				pst.setString(15, cboForUF.getSelectedItem().toString());
				pst.setString(16, txtForObs.getText());
				pst.setString(17, txtForID.getText());

				// Executar a query e alterar o fornecedor no banco
				pst.executeUpdate();

				// Exibir mensagem ao alterar fornecedor cadastrado com sucesso no banco
				JOptionPane.showMessageDialog(null, "Dados do fornecedor atualizados com sucesso!");

				limparCampos();

				((DefaultTableModel) tblFornecedores.getModel()).setRowCount(0);

				txtBuscarFor.setText(null);

				txtForID.setText(null);

				btnBuscar.setEnabled(false);

				// NUNCA esquecer de encerrar a conexao
				con.close();

			}

			catch (SQLIntegrityConstraintViolationException ex) {

				JOptionPane.showMessageDialog(null,
						"Ocorreu um erro. \nVerifique o CNPJ novamente e/ou a Inscrição Estadual (IM).");
				txtForCNPJ.requestFocus();

			}

			catch (Exception e) {
				System.out.println(e);
			}

		}

	}

	private void alterarFornecedorComIM() {

		// Validacao do CNPJ fornecedor
		if (txtForCNPJ.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o CNPJ do fornecedor");
			txtForCNPJ.requestFocus();
		}

		// Validacao do IM fornecedor
		if (txtForIM.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null,
					"Preencha corretamente o IM do fornecedor ou, caso não o tenha, desabilite-o.");
			txtForIM.requestFocus();
		}

		// Validacao da razao social do fornecedor
		else if (txtForRazao.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha a razão social do fornecedor");
			txtForRazao.requestFocus();
		}

		// Validacao do nome fantasia do fornecedor
		else if (txtForFantasia.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o nome fantasia do fornecedor");
			txtForFantasia.requestFocus();
		}

		// Validacao do telefone do fornecedor
		else if (txtForFone.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o telefone do fornecedor");
			txtForFone.requestFocus();
		}

		// Validacao do CEP do fornecedor
		else if (txtForCEP.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o CEP do fornecedor");
			txtForCEP.requestFocus();
		}

		// Validacao do endereco do fornecedor
		else if (txtForEndereco.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o endereço do fornecedor");
			txtForEndereco.requestFocus();
		}

		// Validacao do numero do fornecedor
		else if (txtForNumero.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o número do fornecedor");
			txtForNumero.requestFocus();
		}

		// Validacao do bairro do fornecedor
		else if (txtForBairro.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o bairro do fornecedor");
			txtForBairro.requestFocus();
		}

		// Validacao da cidade do fornecedor
		else if (txtForCidade.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha a cidade do fornecedor");
			txtForCidade.requestFocus();
		}

		// Validacao da UF do fornecedor
		else if (cboForUF.getSelectedItem().equals("")) {
			JOptionPane.showMessageDialog(null, "Selecione a União Federativa (UF) do fornecedor");
			cboForUF.requestFocus();
		}

		else {
			// Logica principal
			String update = "update fornecedores set cnpj = ?, im = ?, razao = ?, fantasia = ?, site = ?, fone = ?, contato = ?, email = ?, cep = ?, endereco = ?, numero = ?, complemento = ?, bairro = ?, cidade = ?, uf = ?, obs = ? where idfor = ?;";

			try {
				// Estabelecer a conexao
				Connection con = dao.conectar();

				// Preparar a execucao da query
				PreparedStatement pst = con.prepareStatement(update);

				// Substituir as interrogacoes pelo conteudo das caixas de texto
				pst.setString(1, txtForCNPJ.getText());
				pst.setString(2, txtForIM.getText());
				pst.setString(3, txtForRazao.getText());
				pst.setString(4, txtForFantasia.getText());
				pst.setString(5, txtForSite.getText());
				pst.setString(6, txtForFone.getText());
				pst.setString(7, txtForContato.getText());
				pst.setString(8, txtForEmail.getText());
				pst.setString(9, txtForCEP.getText());
				pst.setString(10, txtForEndereco.getText());
				pst.setString(11, txtForNumero.getText());
				pst.setString(12, txtForComplemento.getText());
				pst.setString(13, txtForBairro.getText());
				pst.setString(14, txtForCidade.getText());
				pst.setString(15, cboForUF.getSelectedItem().toString());
				pst.setString(16, txtForObs.getText());
				pst.setString(17, txtForID.getText());

				// Executar a query e alterar o fornecedor no banco
				pst.executeUpdate();

				// Exibir mensagem ao alterar fornecedor cadastrado com sucesso no banco
				JOptionPane.showMessageDialog(null, "Dados do fornecedor atualizados com sucesso!");

				limparCampos();

				((DefaultTableModel) tblFornecedores.getModel()).setRowCount(0);

				txtBuscarFor.setText(null);

				txtForID.setText(null);

				btnBuscar.setEnabled(false);

				// NUNCA esquecer de encerrar a conexao
				con.close();

			}

			catch (SQLIntegrityConstraintViolationException ex) {

				JOptionPane.showMessageDialog(null,
						"Ocorreu um erro. \nVerifique o CNPJ novamente e/ou a Inscrição Municipal (IM).");
				txtForCNPJ.requestFocus();

			}

			catch (Exception e) {
				System.out.println(e);
			}

		}

	}

	private void alterarFornecedorComIEIM() {

		// Validacao do CNPJ fornecedor
		if (txtForCNPJ.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o CNPJ do fornecedor");
			txtForCNPJ.requestFocus();
		}

		// Validacao do IE fornecedor
		if (txtForIE.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null,
					"Preencha corretamente o IE do fornecedor ou, caso não o tenha, desabilite-o.");
			txtForIE.requestFocus();
		}

		// Validacao do IM fornecedor
		if (txtForIM.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null,
					"Preencha corretamente o IM do fornecedor ou, caso não o tenha, desabilite-o.");
			txtForIM.requestFocus();
		}

		// Validacao da razao social do fornecedor
		else if (txtForRazao.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha a razão social do fornecedor");
			txtForRazao.requestFocus();
		}

		// Validacao do nome fantasia do fornecedor
		else if (txtForFantasia.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o nome fantasia do fornecedor");
			txtForFantasia.requestFocus();
		}

		// Validacao do telefone do fornecedor
		else if (txtForFone.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o telefone do fornecedor");
			txtForFone.requestFocus();
		}

		// Validacao do CEP do fornecedor
		else if (txtForCEP.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o CEP do fornecedor");
			txtForCEP.requestFocus();
		}

		// Validacao do endereco do fornecedor
		else if (txtForEndereco.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o endereço do fornecedor");
			txtForEndereco.requestFocus();
		}

		// Validacao do numero do fornecedor
		else if (txtForNumero.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o número do fornecedor");
			txtForNumero.requestFocus();
		}

		// Validacao do bairro do fornecedor
		else if (txtForBairro.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o bairro do fornecedor");
			txtForBairro.requestFocus();
		}

		// Validacao da cidade do fornecedor
		else if (txtForCidade.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha a cidade do fornecedor");
			txtForCidade.requestFocus();
		}

		// Validacao da UF do fornecedor
		else if (cboForUF.getSelectedItem().equals("")) {
			JOptionPane.showMessageDialog(null, "Selecione a União Federativa (UF) do fornecedor");
			cboForUF.requestFocus();
		}

		else {
			// Logica principal
			String update = "update fornecedores set cnpj = ?, ie = ?, im = ?, razao = ?, fantasia = ?, site = ?, fone = ?, contato = ?, email = ?, cep = ?, endereco = ?, numero = ?, complemento = ?, bairro = ?, cidade = ?, uf = ?, obs = ? where idfor = ?;";

			try {
				// Estabelecer a conexao
				Connection con = dao.conectar();

				// Preparar a execucao da query
				PreparedStatement pst = con.prepareStatement(update);

				// Substituir as interrogacoes pelo conteudo das caixas de texto
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

				((DefaultTableModel) tblFornecedores.getModel()).setRowCount(0);

				txtBuscarFor.setText(null);

				txtForID.setText(null);

				btnBuscar.setEnabled(false);

				// NUNCA esquecer de encerrar a conexao
				con.close();

			}

			catch (SQLIntegrityConstraintViolationException ex) {

				JOptionPane.showMessageDialog(null,
						"Ocorreu um erro. \nVerifique o CNPJ novamente, a Inscrição Estadual (IE) e/ou a Inscrição Municipal (IM).");
				txtForCNPJ.requestFocus();

			}

			catch (Exception e) {
				System.out.println(e);
			}

		}

	}

	private void alterarFornecedor() {

		// Validacao do CNPJ fornecedor
		if (txtForCNPJ.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o CNPJ do fornecedor");
			txtForCNPJ.requestFocus();
		}

		// Validacao da razao social do fornecedor
		else if (txtForRazao.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha a razão social do fornecedor");
			txtForRazao.requestFocus();
		}

		// Validacao do nome fantasia do fornecedor
		else if (txtForFantasia.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o nome fantasia do fornecedor");
			txtForFantasia.requestFocus();
		}

		// Validacao do telefone do fornecedor
		else if (txtForFone.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o telefone do fornecedor");
			txtForFone.requestFocus();
		}

		// Validacao do CEP do fornecedor
		else if (txtForCEP.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o CEP do fornecedor");
			txtForCEP.requestFocus();
		}

		// Validacao do endereco do fornecedor
		else if (txtForEndereco.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o endereço do fornecedor");
			txtForEndereco.requestFocus();
		}

		// Validacao do numero do fornecedor
		else if (txtForNumero.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o número do fornecedor");
			txtForNumero.requestFocus();
		}

		// Validacao do bairro do fornecedor
		else if (txtForBairro.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o bairro do fornecedor");
			txtForBairro.requestFocus();
		}

		// Validacao da cidade do fornecedor
		else if (txtForCidade.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha a cidade do fornecedor");
			txtForCidade.requestFocus();
		}

		// Validacao da UF do fornecedor
		else if (cboForUF.getSelectedItem().equals("")) {
			JOptionPane.showMessageDialog(null, "Selecione a União Federativa (UF) do fornecedor");
			cboForUF.requestFocus();
		}

		else {
			// Logica principal
			String update = "update fornecedores set cnpj = ?, razao = ?, fantasia = ?, site = ?, fone = ?, contato = ?, email = ?, cep = ?, endereco = ?, numero = ?, complemento = ?, bairro = ?, cidade = ?, uf = ?, obs = ? where idfor = ?;";

			try {
				// Estabelecer a conexao
				Connection con = dao.conectar();

				// Preparar a execucao da query
				PreparedStatement pst = con.prepareStatement(update);

				// Substituir as interrogacoes pelo conteudo das caixas de texto
				pst.setString(1, txtForCNPJ.getText());
				pst.setString(2, txtForRazao.getText());
				pst.setString(3, txtForFantasia.getText());
				pst.setString(4, txtForSite.getText());
				pst.setString(5, txtForFone.getText());
				pst.setString(6, txtForContato.getText());
				pst.setString(7, txtForEmail.getText());
				pst.setString(8, txtForCEP.getText());
				pst.setString(9, txtForEndereco.getText());
				pst.setString(10, txtForNumero.getText());
				pst.setString(11, txtForComplemento.getText());
				pst.setString(12, txtForBairro.getText());
				pst.setString(13, txtForCidade.getText());
				pst.setString(14, cboForUF.getSelectedItem().toString());
				pst.setString(15, txtForObs.getText());
				pst.setString(16, txtForID.getText());

				// Executar a query e alterar o fornecedor no banco
				pst.executeUpdate();

				// Exibir mensagem ao alterar fornecedor cadastrado com sucesso no banco
				JOptionPane.showMessageDialog(null, "Dados do fornecedor atualizados com sucesso!");

				limparCampos();

				((DefaultTableModel) tblFornecedores.getModel()).setRowCount(0);

				txtBuscarFor.setText(null);

				txtForID.setText(null);

				btnBuscar.setEnabled(false);

				// NUNCA esquecer de encerrar a conexao
				con.close();

			}

			catch (SQLIntegrityConstraintViolationException ex) {

				JOptionPane.showMessageDialog(null, "Ocorreu um erro. \nVerifique o CNPJ novamente.");
				txtForCNPJ.requestFocus();

			}

			catch (Exception e) {
				System.out.println(e);
			}

		}

	}

	private void excluirFornecedor() {

		// Validacao
		int confirma = JOptionPane.showConfirmDialog(null, "Confirma a exclusão do fornecedor?", "Atenção!",
				JOptionPane.YES_NO_OPTION);

		// Logica principal
		if (confirma == JOptionPane.YES_OPTION) {
			String delete = "delete from fornecedores where idfor = ?;";

			try {
				// Estabelecer a conexao
				Connection con = dao.conectar();

				// Preparar a execucao da query
				PreparedStatement pst = con.prepareStatement(delete);

				// Substituir o ? pelo conteudo da caixa de texto
				pst.setString(1, txtForID.getText());

				// Executar a query e deletar o fornecedor no banco
				pst.executeUpdate();

				limparCampos();

				((DefaultTableModel) tblFornecedores.getModel()).setRowCount(0);

				txtBuscarFor.setText(null);
				txtForID.setText(null);
				btnBuscar.setEnabled(false);

				// Exibir mensagem ao deletar fornecedor
				JOptionPane.showMessageDialog(null, "Fornecedor excluído com sucesso!");

				// NUNCA esquecer de encerrar a conexao
				con.close();

			}

			catch (Exception e) {
				System.out.println(e);
			}

		}
	}

	private void limparCampos() {
		// Limpar a tabela
		// ((DefaultTableModel) tblFornecedores.getModel()).setRowCount(0);

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
		btnAdicionar.setEnabled(true);
		txtForIE.setEditable(false);
		txtForIM.setEditable(false);
		chkIE.setSelected(false);
		chkIM.setSelected(false);

	}
} // Fim do codigo
