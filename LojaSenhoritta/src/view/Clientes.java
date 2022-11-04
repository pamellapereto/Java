// INSERIR DATA DE NASCIMENTO

package view;

import java.awt.EventQueue;

import javax.swing.JDialog;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

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
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Iterator;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.awt.Color;
import java.awt.Font;

public class Clientes extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JTextField txtBuscarCli;
	private JTextField txtCliID;
	private JTextField txtCliCPF;
	private JTextField txtCliNome;
	private JTextField txtCliFone;
	private JTextField txtCliEmail;
	private JTextField txtCliCEP;
	private JLabel lblStatusCEP;
	private JTextField txtCliEndereco;
	private JTextField txtCliNumero;
	private JTextField txtCliComplemento;
	private JTextField txtCliBairro;
	private JTextField txtCliCidade;
	private JTable tblClientes;
	private JComboBox cboCliUF;
	private JComboBox cboCliMarketing;
	private JButton btnAdicionar;
	private JButton btnAlterar;
	private JButton btnExcluir;
	private JButton btnBuscar;
	private JButton btnBuscarCEP;

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
		setModal(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Fornecedores.class.getResource("/img/logo.png")));
		setTitle("Clientes");
		setBounds(100, 100, 709, 574);
		getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("Cliente");
		lblNewLabel.setBounds(24, 16, 46, 14);
		getContentPane().add(lblNewLabel);

		txtBuscarCli = new JTextField();
		txtBuscarCli.setForeground(Color.DARK_GRAY);
		txtBuscarCli.setText("Digite para pesquisar...        ⌕");
		txtBuscarCli.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent event) {
				if (txtBuscarCli.getText().equals("Digite para pesquisar...        ⌕")) {
					txtBuscarCli.setText("");
				}
			}

			@Override
			public void focusLost(FocusEvent event) {
				if (txtBuscarCli.getText().equals("")) {
					txtBuscarCli.setText("Digite para pesquisar...        ⌕");
				}
			}
		});

		txtBuscarCli.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				// Evento digitacao
				buscarClienteTabela();

			}
		});
		txtBuscarCli.setBounds(67, 13, 168, 20);
		getContentPane().add(txtBuscarCli);

		JLabel lblNewLabel_2 = new JLabel("ID");
		lblNewLabel_2.setBounds(24, 139, 46, 14);
		getContentPane().add(lblNewLabel_2);

		txtCliID = new JTextField();
		txtCliID.setEditable(false);
		txtCliID.setBounds(46, 136, 39, 20);
		getContentPane().add(txtCliID);

		JLabel lblNewLabel_3 = new JLabel("CPF*");
		lblNewLabel_3.setBounds(24, 197, 46, 14);
		getContentPane().add(lblNewLabel_3);

		txtCliCPF = new JTextField();
		txtCliCPF.setBounds(67, 194, 125, 20);
		getContentPane().add(txtCliCPF);

		JLabel lblNewLabel_5 = new JLabel("Nome*");
		lblNewLabel_5.setBounds(215, 197, 81, 14);
		getContentPane().add(lblNewLabel_5);

		txtCliNome = new JTextField();
		txtCliNome.setBounds(260, 194, 227, 20);
		getContentPane().add(txtCliNome);

		JLabel lblNewLabel_8 = new JLabel("Fone*");
		lblNewLabel_8.setBounds(503, 197, 46, 14);
		getContentPane().add(lblNewLabel_8);

		txtCliFone = new JTextField();
		txtCliFone.setBounds(551, 194, 123, 20);
		getContentPane().add(txtCliFone);

		JLabel lblNewLabel_9 = new JLabel("Deseja receber comunicados via e-mail?*");
		lblNewLabel_9.setBounds(283, 250, 243, 14);
		getContentPane().add(lblNewLabel_9);

		cboCliMarketing = new JComboBox();
		cboCliMarketing.setModel(new DefaultComboBoxModel(new String[] { "", "Sim", "Não" }));
		cboCliMarketing.setBounds(526, 247, 81, 22);
		getContentPane().add(cboCliMarketing);

		JLabel lblNewLabel_10 = new JLabel("E-mail");
		lblNewLabel_10.setBounds(24, 250, 46, 14);
		getContentPane().add(lblNewLabel_10);

		txtCliEmail = new JTextField();
		txtCliEmail.setBounds(73, 247, 179, 20);
		getContentPane().add(txtCliEmail);

		JLabel lblNewLabel_11 = new JLabel("CEP");
		lblNewLabel_11.setBounds(24, 308, 46, 14);
		getContentPane().add(lblNewLabel_11);

		txtCliCEP = new JTextField();
		txtCliCEP.setBounds(59, 305, 81, 20);
		getContentPane().add(txtCliCEP);

		lblStatusCEP = new JLabel("");
		lblStatusCEP.setBounds(146, 308, 46, 14);
		getContentPane().add(lblStatusCEP);

		btnBuscarCEP = new JButton("Buscar CEP");
		btnBuscarCEP.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (txtCliCEP.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Informe o CEP para realizar a busca do endereço");
					txtCliCEP.requestFocus();
				} else {
					buscarCEP();
				}
			}
		});

		btnBuscarCEP.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnBuscarCEP.setBounds(210, 302, 107, 23);
		getContentPane().add(btnBuscarCEP);

		JLabel lblNewLabel_12 = new JLabel("Endereço");
		lblNewLabel_12.setBounds(24, 351, 59, 14);
		getContentPane().add(lblNewLabel_12);

		txtCliEndereco = new JTextField();
		txtCliEndereco.setBounds(94, 348, 218, 20);
		getContentPane().add(txtCliEndereco);

		JLabel lblNewLabel_13 = new JLabel("Número");
		lblNewLabel_13.setBounds(338, 351, 46, 14);
		getContentPane().add(lblNewLabel_13);

		txtCliNumero = new JTextField();
		txtCliNumero.setBounds(394, 348, 72, 20);
		getContentPane().add(txtCliNumero);

		JLabel lblNewLabel_14 = new JLabel("Complemento");
		lblNewLabel_14.setBounds(495, 351, 87, 14);
		getContentPane().add(lblNewLabel_14);

		txtCliComplemento = new JTextField();
		txtCliComplemento.setBounds(585, 348, 99, 20);
		getContentPane().add(txtCliComplemento);

		JLabel lblNewLabel_15 = new JLabel("Bairro");
		lblNewLabel_15.setBounds(24, 395, 46, 14);
		getContentPane().add(lblNewLabel_15);

		txtCliBairro = new JTextField();
		txtCliBairro.setBounds(71, 392, 200, 20);
		getContentPane().add(txtCliBairro);

		JLabel lblNewLabel_16 = new JLabel("Cidade");
		lblNewLabel_16.setBounds(294, 395, 46, 14);
		getContentPane().add(lblNewLabel_16);

		txtCliCidade = new JTextField();
		txtCliCidade.setBounds(349, 392, 200, 20);
		getContentPane().add(txtCliCidade);

		JLabel lblNewLabel_17 = new JLabel("UF");
		lblNewLabel_17.setBounds(572, 395, 24, 14);
		getContentPane().add(lblNewLabel_17);

		cboCliUF = new JComboBox();
		cboCliUF.setModel(new DefaultComboBoxModel(
				new String[] { "", "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA",
						"PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO" }));
		cboCliUF.setBounds(603, 391, 81, 22);
		getContentPane().add(cboCliUF);

		btnAdicionar = new JButton("");
		btnAdicionar.setEnabled(true);
		btnAdicionar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				adicionarCliente();
			}
		});

		btnAdicionar.setIcon(new ImageIcon(Fornecedores.class.getResource("/img/create.png")));
		btnAdicionar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAdicionar.setContentAreaFilled(false);
		btnAdicionar.setBorderPainted(false);
		btnAdicionar.setToolTipText("Adicionar");
		btnAdicionar.setBounds(492, 440, 64, 64);
		getContentPane().add(btnAdicionar);

		btnAlterar = new JButton("");
		btnAlterar.setEnabled(false);
		btnAlterar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				alterarCliente();
			}

		});

		btnAlterar.setIcon(new ImageIcon(Fornecedores.class.getResource("/img/update.png")));
		btnAlterar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAlterar.setToolTipText("Alterar");
		btnAlterar.setContentAreaFilled(false);
		btnAlterar.setBorderPainted(false);
		btnAlterar.setBounds(557, 440, 64, 64);
		getContentPane().add(btnAlterar);

		btnExcluir = new JButton("");
		btnExcluir.setEnabled(false);
		btnExcluir.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				excluirCliente();
			}
		});

		btnExcluir.setIcon(new ImageIcon(Fornecedores.class.getResource("/img/delete.png")));
		btnExcluir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnExcluir.setToolTipText("Excluir");
		btnExcluir.setContentAreaFilled(false);
		btnExcluir.setBorderPainted(false);
		btnExcluir.setBounds(620, 440, 64, 64);
		getContentPane().add(btnExcluir);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(24, 41, 552, 72);
		getContentPane().add(scrollPane);

		tblClientes = new JTable();

		tblClientes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setarCaixasTexto();
			}
		});

		scrollPane.setViewportView(tblClientes);

		btnBuscar = new JButton("Buscar");
		btnBuscar.setEnabled(false);
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pesquisarCliente();
			}
		});
		btnBuscar.setBounds(97, 132, 84, 28);
		btnBuscar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		getContentPane().add(btnBuscar);

		JLabel lblNewLabel_13_1 = new JLabel("*Campos obrigatórios");
		lblNewLabel_13_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_13_1.setForeground(Color.BLACK);
		lblNewLabel_13_1.setBounds(24, 467, 157, 14);
		getContentPane().add(lblNewLabel_13_1);

		// Validacao com o uso da biblioteca Atxy2k

		// txtBuscarCli
		RestrictedTextField validartxtBuscarCli = new RestrictedTextField(txtBuscarCli);

		// Limitar a somente 100 caracteres no campo txtBuscarCli
		validartxtBuscarCli.setLimit(100);

		// txtCliCPF
		RestrictedTextField validarCPF = new RestrictedTextField(txtCliCPF);

		// Restringir a somente numeros no campo CPF
		validarCPF.setOnlyNums(true);
		// Limitar a somente 14 numeros no campo CPF
		validarCPF.setLimit(14);

		// txtCliNome
		RestrictedTextField validarNome = new RestrictedTextField(txtCliNome);

		// Restringir a somente letras no campo nome
		validarNome.setOnlyText(true);
		// Aceitar espaco no campo nome
		validarNome.setAcceptSpace(true);
		// Limitar a somente 100 caracteres no campo nome
		validarNome.setLimit(100);

		// txtCliFone
		RestrictedTextField validarFone = new RestrictedTextField(txtCliFone);

		// Restringir a somente numeros no campo Fone
		validarFone.setOnlyNums(true);
		// Limitar a somente 14 numeros no campo Fone
		validarFone.setLimit(14);

		// txtCliEmail
		RestrictedTextField validarEmail = new RestrictedTextField(txtCliEmail);

		// Limitar a somente 50 caracteres no campo email
		validarEmail.setLimit(50);

		// txtCliCEP
		RestrictedTextField validarCEP = new RestrictedTextField(txtCliCEP);

		// Restringir a somente numeros no campo CEP
		validarCEP.setOnlyNums(true);
		// Limitar a somente 8 numeros no campo CEP
		validarCEP.setLimit(8);

		// txtCliEndereco
		RestrictedTextField validarEndereco = new RestrictedTextField(txtCliEndereco);

		// Restringir a somente letras no campo endereco
		validarEndereco.setOnlyText(true);
		// Aceitar espaco no campo endereco
		validarEndereco.setAcceptSpace(true);
		// Limitar a somente 60 caracteres no campo endereco
		validarEndereco.setLimit(60);

		// txtCliNumero
		RestrictedTextField validarNumero = new RestrictedTextField(txtCliNumero);

		// Restringir a somente digitos no campo numero
		validarNumero.setOnlyNums(true);
		// Limitar a somente 10 digitos no campo numero
		validarNumero.setLimit(10);

		// txtCliComplemento
		RestrictedTextField validarComplemento = new RestrictedTextField(txtCliComplemento);

		// Limitar a somente 50 caracteres no campo complemento
		validarComplemento.setLimit(50);

		// txtCliBairro
		RestrictedTextField validarBairro = new RestrictedTextField(txtCliBairro);

		// Limitar a somente 50 caracteres no campo bairro
		validarBairro.setLimit(50);

		// txtCliCidade
		RestrictedTextField validarCidade = new RestrictedTextField(txtCliCidade);

		// Restringir a somente letras no campo cidade
		validarCidade.setOnlyText(true);
		// Aceitar espaco no campo cidade
		validarCidade.setAcceptSpace(true);
		// Limitar a somente 50 caracteres no campo cidade
		validarCidade.setLimit(50);

	} // Fim do construtor

	// Criar objeto para acessar o banco
	DAO dao = new DAO();

	/**
	 * Metodo responsavel pela pesquisa avancada do cliente usando o seu nome e a
	 * biblioteca rs2xml
	 */

	private void buscarClienteTabela() {
		String readT = "select idcli as ID, nome as Nome, fone as Telefone, cpf as CPF from clientes where nome like ?;";

		try {
			// Estabelecer a conexao
			Connection con = dao.conectar();

			// Preparar a execucao da query
			PreparedStatement pst = con.prepareStatement(readT);

			// Setar o argumento (nome)
			// Substituir o ? pelo conteudo da caixa de texto
			pst.setString(1, txtBuscarCli.getText() + "%");

			// Executar a query e exibir o resultado no formulario
			ResultSet rs = pst.executeQuery();

			// Uso da bilblioteca rs2xml para "popular" a tabela
			tblClientes.setModel(DbUtils.resultSetToTableModel(rs));

			if (txtBuscarCli.getText().isEmpty()) {
				limparCampos();
				txtCliID.setText(null);
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
	 * Metodo responsavel pela pesquisa do ID do cliente (setar as caixas de texto
	 * de acordo com os campos da tabela)
	 */

	private void setarCaixasTexto() {

		// Criar uma variavel para receber a linha da tabela
		int setar = tblClientes.getSelectedRow();

		txtCliID.setText(tblClientes.getModel().getValueAt(setar, 0).toString());
		txtBuscarCli.setText(tblClientes.getModel().getValueAt(setar, 1).toString());

		limparCampos();

		btnBuscar.setEnabled(true);
		btnAdicionar.setEnabled(false);
		btnBuscarCEP.setEnabled(false);

	}

	private void pesquisarCliente() {

		// Logica principal
		// Query (instrucao SQL)

		String read = "select * from clientes where idcli = ?;";

		try {
			// Estabelecer a conexao
			Connection con = dao.conectar();

			// Preparar a execucao da query
			PreparedStatement pst = con.prepareStatement(read);

			// Setar o argumento (ID)
			// Substituir o ? pelo conteudo da caixa de texto
			pst.setString(1, txtCliID.getText());

			// Executar a query e exibir o resultado no formulario
			ResultSet rs = pst.executeQuery();

			// Validacao (existencia do cliente)
			// rs.next() -> existencia de cliente
			if (rs.next()) {
				// Preencher (setar) os campos do formulario
				txtBuscarCli.setText(rs.getString(4));
				txtCliCPF.setText(rs.getString(6));
				txtCliNome.setText(rs.getString(4));
				txtCliFone.setText(rs.getString(5));
				txtCliEmail.setText(rs.getString(7));
				cboCliMarketing.setSelectedItem(rs.getString(8));
				// INSERIR DATANASCIMENTO > CAMPO 9
				txtCliCEP.setText(rs.getString(11));
				txtCliEndereco.setText(rs.getString(12));
				txtCliNumero.setText(rs.getString(14));
				txtCliComplemento.setText(rs.getString(15));
				txtCliBairro.setText(rs.getString(13));
				txtCliCidade.setText(rs.getString(16));
				cboCliUF.setSelectedItem(rs.getString(17));
				txtBuscarCli.requestFocus();
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

	private void adicionarCliente() {

		// Validacao do CPF cliente
		if (txtCliCPF.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o CPF do cliente");
			txtCliCPF.requestFocus();
		}

		// Validacao do nome do cliente
		else if (txtCliNome.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o nome do cliente");
			txtCliNome.requestFocus();
		}

		// Validacao do telefone do cliente
		else if (txtCliFone.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o telefone do cliente");
			txtCliFone.requestFocus();
		}

		// Validacao da autorizacao do envio de comunicados via e-mail para o cliente
		else if (cboCliMarketing.getSelectedItem().equals("")) {
			JOptionPane.showMessageDialog(null, "Selecione se o cliente deseja receber comunicados via e-mail");
			cboCliMarketing.requestFocus();
		}

		else {
			// Logica principal
			String create = "insert into clientes (nome, fone, cpf, email, marketing, cep, endereco, bairro, numero, complemento, cidade, uf) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

			try {
				// Estabelecer a conexao
				Connection con = dao.conectar();

				// Preparar a execucao da query
				PreparedStatement pst = con.prepareStatement(create);

				// Substituir as interrogacoes pelo conteudo das caixas de texto
				pst.setString(1, txtCliNome.getText());
				pst.setString(2, txtCliFone.getText());
				pst.setString(3, txtCliCPF.getText());
				pst.setString(4, txtCliEmail.getText());
				pst.setString(5, cboCliMarketing.getSelectedItem().toString());
				pst.setString(6, txtCliCEP.getText());
				pst.setString(7, txtCliEndereco.getText());
				pst.setString(8, txtCliBairro.getText());
				pst.setString(9, txtCliNumero.getText());
				pst.setString(10, txtCliComplemento.getText());
				pst.setString(11, txtCliCidade.getText());
				pst.setString(12, cboCliUF.getSelectedItem().toString());

				// Executar a query e inserir o cliente no banco
				pst.executeUpdate();

				// Exibir mensagem ao cadastrar cliente com sucesso no banco
				JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso!");

				limparCampos();
				lblStatusCEP.setVisible(false);
				txtBuscarCli.setText(null);
				// NUNCA esquecer de encerrar a conexao
				con.close();

			}

			catch (SQLIntegrityConstraintViolationException ex) {

				JOptionPane.showMessageDialog(null, "Ocorreu um erro. \nVerifique novamente o CPF.");
				txtCliCPF.requestFocus();

			} catch (Exception e) {
				System.out.println(e);
			}

		}

	}

	private void alterarCliente() {

		// Validacao do CPF cliente
		if (txtCliCPF.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o CPF do cliente");
			txtCliCPF.requestFocus();
		}

		// Validacao do nome do cliente
		else if (txtCliNome.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o nome do cliente");
			txtCliNome.requestFocus();
		}

		// Validacao do telefone do cliente
		else if (txtCliFone.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o telefone do cliente");
			txtCliFone.requestFocus();
		}

		// Validacao da autorizacao do envio de comunicados via e-mail para o cliente
		else if (cboCliMarketing.getSelectedItem().equals("")) {
			JOptionPane.showMessageDialog(null, "Selecione se o cliente deseja receber comunicados via e-mail");
			cboCliMarketing.requestFocus();
		}

		else {
			// Logica principal
			String update = "update clientes set nome = ?, fone = ?, cpf = ?, email = ?, marketing = ?, cep = ?, endereco = ?, bairro = ?, numero = ?, complemento = ?, cidade = ?, uf = ? where idcli = ?;";

			try {
				// Estabelecer a conexao
				Connection con = dao.conectar();

				// Preparar a execucao da query
				PreparedStatement pst = con.prepareStatement(update);

				// Substituir as interrogacoes pelo conteudo das caixas de texto
				pst.setString(1, txtCliNome.getText());
				pst.setString(2, txtCliFone.getText());
				pst.setString(3, txtCliCPF.getText());
				pst.setString(4, txtCliEmail.getText());
				pst.setString(5, cboCliMarketing.getSelectedItem().toString());
				pst.setString(6, txtCliCEP.getText());
				pst.setString(7, txtCliEndereco.getText());
				pst.setString(8, txtCliBairro.getText());
				pst.setString(9, txtCliNumero.getText());
				pst.setString(10, txtCliComplemento.getText());
				pst.setString(11, txtCliCidade.getText());
				pst.setString(12, cboCliUF.getSelectedItem().toString());
				pst.setString(13, txtCliID.getText());

				// Executar a query e alterar o cliente no banco
				pst.executeUpdate();

				// Exibir mensagem ao alterar cliente cadastrado com sucesso no banco
				JOptionPane.showMessageDialog(null, "Dados do cliente atualizados com sucesso!");

				limparCampos();

				lblStatusCEP.setVisible(false);

				txtBuscarCli.setText(null);

				txtCliID.setText(null);

				btnBuscar.setEnabled(false);

				// NUNCA esquecer de encerrar a conexao
				con.close();

			}

			catch (SQLIntegrityConstraintViolationException ex) {

				JOptionPane.showMessageDialog(null, "Ocorreu um erro. \nVerifique o CPF novamente.");
				txtCliCPF.requestFocus();

			}

			catch (Exception e) {
				System.out.println(e);
			}

		}

	}

	private void excluirCliente() {

		// Validacao
		int confirma = JOptionPane.showConfirmDialog(null, "Confirma a exclusão do cliente?", "Atenção!",
				JOptionPane.YES_NO_OPTION);

		// Logica principal
		if (confirma == JOptionPane.YES_OPTION) {
			String delete = "delete from clientes where idcli = ?;";

			try {
				// Estabelecer a conexao
				Connection con = dao.conectar();

				// Preparar a execucao da query
				PreparedStatement pst = con.prepareStatement(delete);

				// Substituir o ? pelo conteudo da caixa de texto
				pst.setString(1, txtCliID.getText());

				// Executar a query e deletar o cliente no banco
				pst.executeUpdate();

				limparCampos();
				((DefaultTableModel) tblClientes.getModel()).setRowCount(0);
				txtBuscarCli.setText(null);
				txtCliID.setText(null);
				btnBuscar.setEnabled(false);

				// Exibir mensagem ao deletar cliente
				JOptionPane.showMessageDialog(null, "Cliente excluído com sucesso!");

				// NUNCA esquecer de encerrar a conexao
				con.close();

			}

			catch (Exception e) {
				System.out.println(e);
			}

		}
	}

	private void buscarCEP() {

		String logradouro = "";
		String tipoLogradouro = "";
		String resultado = null;
		String CEP = txtCliCEP.getText();

		try {

			URL url = new URL("http://cep.republicavirtual.com.br/web_cep.php?cep=" + CEP + "&formato=xml");

			SAXReader xml = new SAXReader();

			Document documento = xml.read(url);

			Element root = documento.getRootElement();

			for (Iterator<Element> it = root.elementIterator(); it.hasNext();) {

				Element element = it.next();

				if (element.getQualifiedName().equals("cidade")) {
					txtCliCidade.setText(element.getText());
				}

				if (element.getQualifiedName().equals("bairro")) {
					txtCliBairro.setText(element.getText());
				}

				if (element.getQualifiedName().equals("uf")) {
					cboCliUF.setSelectedItem(element.getText());
				}

				if (element.getQualifiedName().equals("tipo_logradouro")) {
					tipoLogradouro = element.getText();
				}

				if (element.getQualifiedName().equals("logradouro")) {
					logradouro = element.getText();
				}

				if (element.getQualifiedName().equals("resultado")) {
					resultado = element.getText();

					if (resultado.equals("1")) {
						lblStatusCEP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/check.png")));
						lblStatusCEP.setVisible(true);
					}

					else {
						JOptionPane.showMessageDialog(null, "CEP não encontrado");
					}
				}
			}

			// Setar o campo endereco
			txtCliEndereco.setText(tipoLogradouro + " " + logradouro);

		}

		catch (Exception e) {
			System.out.println(e);
		}

	}

	private void limparCampos() {
		// Limpar a tabela
		((DefaultTableModel) tblClientes.getModel()).setRowCount(0);

		txtBuscarCli.requestFocus();
		txtCliCPF.setText(null);
		txtCliNome.setText(null);
		txtCliFone.setText(null);
		txtCliEmail.setText(null);
		txtCliCEP.setText(null);
		txtCliEndereco.setText(null);
		txtCliNumero.setText(null);
		txtCliComplemento.setText(null);
		txtCliBairro.setText(null);
		txtCliCidade.setText(null);
		cboCliMarketing.setSelectedItem("");
		cboCliUF.setSelectedItem("");
		btnAlterar.setEnabled(false);
		btnExcluir.setEnabled(false);
		btnAdicionar.setEnabled(true);

	}
} // Fim do codigo
