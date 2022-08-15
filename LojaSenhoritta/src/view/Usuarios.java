package view;

import java.awt.EventQueue;

import javax.swing.JDialog;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Atxy2k.CustomTextField.RestrictedTextField;
import model.DAO;

import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.ImageIcon;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;
import java.awt.event.ActionEvent;

public class Usuarios extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtUsuId;
	private JTextField txtUsuNome;
	private JTextField txtUsuLogin;
	private JPasswordField txtUsuSenha;

	/**
	 * Launch the application.
	 */

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Usuarios dialog = new Usuarios();
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

	public Usuarios() {
		setModal(true);
		setTitle("Usu\u00E1rios");
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Usuarios.class.getResource("/img/logo.png")));
		setBounds(100, 100, 486, 341);
		getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("ID");
		lblNewLabel.setBounds(60, 36, 32, 14);
		getContentPane().add(lblNewLabel);

		txtUsuId = new JTextField();
		txtUsuId.setEditable(false);
		txtUsuId.setBounds(95, 33, 46, 20);
		getContentPane().add(txtUsuId);
		txtUsuId.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Usu\u00E1rio");
		lblNewLabel_1.setBounds(39, 91, 46, 14);
		getContentPane().add(lblNewLabel_1);

		txtUsuNome = new JTextField();
		txtUsuNome.setBounds(95, 88, 310, 20);
		getContentPane().add(txtUsuNome);
		txtUsuNome.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Login");
		lblNewLabel_2.setBounds(193, 36, 46, 14);
		getContentPane().add(lblNewLabel_2);

		txtUsuLogin = new JTextField();
		txtUsuLogin.setBounds(236, 33, 121, 20);
		getContentPane().add(txtUsuLogin);
		txtUsuLogin.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("Senha");
		lblNewLabel_3.setBounds(39, 133, 46, 14);
		getContentPane().add(lblNewLabel_3);

		txtUsuSenha = new JPasswordField();
		txtUsuSenha.setBounds(95, 130, 206, 20);
		getContentPane().add(txtUsuSenha);

		JLabel lblNewLabel_4 = new JLabel("Perfil");
		lblNewLabel_4.setBounds(45, 180, 40, 14);
		getContentPane().add(lblNewLabel_4);

		cboUsuPerfil = new JComboBox();
		cboUsuPerfil.setModel(new DefaultComboBoxModel(new String[] { "", "admin", "user" }));
		cboUsuPerfil.setBounds(95, 176, 113, 22);
		getContentPane().add(cboUsuPerfil);

		JButton btnPesquisar = new JButton("");
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pesquisarUsuario();
			}
		});

		btnPesquisar.setToolTipText("Pesquisar");
		btnPesquisar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnPesquisar.setContentAreaFilled(false);
		btnPesquisar.setBorderPainted(false);
		btnPesquisar.setIcon(new ImageIcon(Usuarios.class.getResource("/img/pesquisar.png")));
		btnPesquisar.setBounds(370, 26, 32, 32);
		getContentPane().add(btnPesquisar);

		btnAdicionar = new JButton("");
		btnAdicionar.setEnabled(false);
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adicionarUsuario();
			}
		});

		btnAdicionar.setToolTipText("Adicionar");
		btnAdicionar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAdicionar.setContentAreaFilled(false);
		btnAdicionar.setBorderPainted(false);
		btnAdicionar.setIcon(new ImageIcon(Usuarios.class.getResource("/img/create.png")));
		btnAdicionar.setBounds(257, 226, 64, 64);
		getContentPane().add(btnAdicionar);

		btnAlterar = new JButton("");
		btnAlterar.setEnabled(false);
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Lógica para verificar o checkbox

				if (chkSenha.isSelected()) {
					alterarUsuarioSenha();
				}

				else {
					alterarUsuario();
				}

			}
		});

		btnAlterar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAlterar.setIcon(new ImageIcon(Usuarios.class.getResource("/img/update.png")));
		btnAlterar.setToolTipText("Alterar");
		btnAlterar.setContentAreaFilled(false);
		btnAlterar.setBorderPainted(false);
		btnAlterar.setBounds(322, 226, 64, 64);
		getContentPane().add(btnAlterar);

		btnExcluir = new JButton("");
		btnExcluir.setEnabled(false);
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				excluirUsuario();
			}
		});

		btnExcluir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnExcluir.setIcon(new ImageIcon(Usuarios.class.getResource("/img/delete.png")));
		btnExcluir.setToolTipText("Excluir");
		btnExcluir.setContentAreaFilled(false);
		btnExcluir.setBorderPainted(false);
		btnExcluir.setBounds(385, 226, 64, 64);
		getContentPane().add(btnExcluir);

		// Validação com o uso da biblioteca Atxy2k

		// txtUsuId
		RestrictedTextField validarId = new RestrictedTextField(txtUsuId);

		// Restringir a somente números no campo ID
		validarId.setOnlyNums(true);
		// Limitar a somente 4 números no campo ID
		validarId.setLimit(4);

		// txtUsuNome
		RestrictedTextField validarNome = new RestrictedTextField(txtUsuNome);

		// Restringir a somente letras no campo nome
		validarNome.setOnlyText(true);
		// Aceitar espaço no campo nome
		validarNome.setAcceptSpace(true);
		// Limitar a somente 50 caracteres no campo nome
		validarNome.setLimit(50);

		// txtUsuLogin
		RestrictedTextField validarLogin = new RestrictedTextField(txtUsuLogin);

		// Restringir a caracteres alfanuméricos no campo login
		validarLogin.setOnlyAlphaNumeric(true);

		// Limitar a somente 15 caracteres no campo login
		validarLogin.setLimit(15);

		// txtUsuSenha
		RestrictedTextField validarSenha = new RestrictedTextField(txtUsuSenha);

		// Limitar a somente 10 caracteres no campo senha
		validarSenha.setLimit(10);

		chkSenha = new JCheckBox("Alterar senha");
		chkSenha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtUsuSenha.setBackground(Color.ORANGE);
				txtUsuSenha.setText(null);
				txtUsuSenha.requestFocus();
				txtUsuSenha.setEditable(true);
			}
		});
		
		chkSenha.setVisible(false);
		chkSenha.setBounds(320, 129, 113, 23);
		getContentPane().add(chkSenha);
		validarSenha.setLimit(255);

	}// Fim do construtor

	DAO dao = new DAO();
	private JComboBox cboUsuPerfil;
	private JButton btnAdicionar;
	private JButton btnAlterar;
	private JButton btnExcluir;
	private JCheckBox chkSenha;

	/**
	 * Método responsável pela pesquisa de usuários
	 */

	private void pesquisarUsuario() {

		// Validação
		if (txtUsuLogin.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite o login do usuário");
			txtUsuLogin.requestFocus();
		}

		else {
			// Lógica principal
			// Query (instrução SQL)

			String read = "select * from usuarios where login = ?;";

			try {
				// Estabelecer a conexão
				Connection con = dao.conectar();

				// Preparar a execução da query
				PreparedStatement pst = con.prepareStatement(read);

				// Setar o argumento (login)
				// Substituir o ? pelo conteúdo da caixa de texto
				pst.setString(1, txtUsuLogin.getText());

				// Executar a query e exibir o resultado no formulário
				ResultSet rs = pst.executeQuery();

				// Validação (existência do usuário)
				// rs.next() -> existência de usuário
				if (rs.next()) {
					// Preencher (setar) os campos do formulário
					txtUsuNome.setText(rs.getString(2));
					txtUsuId.setText(rs.getString(1));
					cboUsuPerfil.setSelectedItem(rs.getString(5));
					txtUsuSenha.setText(rs.getString(4));
					// txtUsuNome.setEditable(true);
					// cboUsuPerfil.setEnabled(true);
					txtUsuSenha.setEditable(false);
					txtUsuNome.requestFocus();
					btnAlterar.setEnabled(true);
					btnExcluir.setEnabled(true);
					chkSenha.setVisible(true);
				}

				else {
					// Validação

					JOptionPane.showMessageDialog(null, "Usuário inexistente!");
					// txtUsuLogin.setEditable(false);
					txtUsuNome.setEditable(true);
					cboUsuPerfil.setEnabled(true);
					txtUsuSenha.setEditable(true);
					txtUsuNome.requestFocus();
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

	/**
	 * Método responsável por adicionar usuários
	 */

	private void adicionarUsuario() {

		// Validação da senha (captura segura)
		String capturaSenha = new String(txtUsuSenha.getPassword());

		// Validação do nome do usuário
		if (txtUsuNome.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o nome do usuário");
			txtUsuNome.requestFocus();
		}

		// Validação do login do usuário
		else if (txtUsuLogin.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o login do usuário");
			txtUsuLogin.requestFocus();
		}

		// Validação do perfil do usuário
		else if (cboUsuPerfil.getSelectedItem().equals("")) {
			JOptionPane.showMessageDialog(null, "Selecione o perfil do usuário");
			cboUsuPerfil.requestFocus();
		}

		// Validação da senha do usuário
		else if (txtUsuSenha.getPassword().length == 0) {
			JOptionPane.showMessageDialog(null, "Preencha a senha do usuário");
			txtUsuSenha.requestFocus();
		}

		else {
			// Lógica principal
			String create = "insert into usuarios (usuario, login, senha, perfil) values (?, ?, md5(?), ?);";

			try {
				// Estabelecer a conexão
				Connection con = dao.conectar();

				// Preparar a execução da query
				PreparedStatement pst = con.prepareStatement(create);

				// Substituir os ? ? ? ? pelo conteúdo das caixas de texto
				pst.setString(1, txtUsuNome.getText());
				pst.setString(2, txtUsuLogin.getText());
				pst.setString(3, capturaSenha);
				pst.setString(4, cboUsuPerfil.getSelectedItem().toString());

				// Executar a query e inserir o usuário no banco
				pst.executeUpdate();

				// Exibir mensagem ao inserir usuário cadastrado com sucesso no banco
				JOptionPane.showMessageDialog(null, "Usuário cadastrado com sucesso!");

				limparCampos();
				// NUNCA esquecer de encerrar a conexão
				con.close();

			}

			catch (SQLIntegrityConstraintViolationException ex) {
				JOptionPane.showMessageDialog(null, "Login em uso. \nEscolha outro login.");
				txtUsuLogin.setText(null);
				txtUsuLogin.requestFocus();
			}

			catch (Exception e) {
				System.out.println(e);
			}

		}
	}

	/**
	 * Método responsável por alterar os dados de um usuário no banco, exceto a
	 * senha
	 */
	private void alterarUsuario() {
		// Validação do nome do usuário
		if (txtUsuNome.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o nome do usuário");
			txtUsuNome.requestFocus();
		}

		// Validação do login do usuário
		else if (txtUsuLogin.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o login do usuário");
			txtUsuLogin.requestFocus();
		}

		// Validação do perfil do usuário
		else if (cboUsuPerfil.getSelectedItem().equals("")) {
			JOptionPane.showMessageDialog(null, "Selecione o perfil do usuário");
			cboUsuPerfil.requestFocus();
		}

		// Validação da senha do usuário
		else if (txtUsuSenha.getPassword().length == 0) {
			JOptionPane.showMessageDialog(null, "Preencha a senha do usuário");
			txtUsuSenha.requestFocus();
		}

		else {
			// Lógica principal
			String update = "update usuarios set usuario = ?, login = ?, perfil = ? where idusu = ?;";

			try {
				// Estabelecer a conexão
				Connection con = dao.conectar();

				// Preparar a execução da query
				PreparedStatement pst = con.prepareStatement(update);

				// Substituir os ? ? ? ? pelo conteúdo das caixas de texto
				pst.setString(1, txtUsuNome.getText());
				pst.setString(2, txtUsuLogin.getText());
				pst.setString(3, cboUsuPerfil.getSelectedItem().toString());
				pst.setString(4, txtUsuId.getText());

				// Executar a query e inserir o usuário no banco
				pst.executeUpdate();

				// Exibir mensagem ao inserir usuário cadastrado com sucesso no banco
				JOptionPane.showMessageDialog(null, "Dados do usuário, exceto senha, atualizados com sucesso!");

				limparCampos();
				// NUNCA esquecer de encerrar a conexão
				con.close();

			}

			catch (SQLIntegrityConstraintViolationException ex) {
				JOptionPane.showMessageDialog(null, "Login em uso. \nEscolha outro login.");
				txtUsuLogin.setText(null);
				txtUsuLogin.requestFocus();
			}

			catch (Exception e) {
				System.out.println(e);
			}

		}
	}

	/**
	 * Método responsável por os dados de um usuário e sua respectiva senha no banco
	 */

	private void alterarUsuarioSenha() {

		// Validação da senha (captura segura)
		String capturaSenha = new String(txtUsuSenha.getPassword());

		// Validação do nome do usuário
		if (txtUsuNome.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o nome do usuário");
			txtUsuNome.requestFocus();
		}

		// Validação do login do usuário
		else if (txtUsuLogin.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o login do usuário");
			txtUsuLogin.requestFocus();
		}

		// Validação do perfil do usuário
		else if (cboUsuPerfil.getSelectedItem().equals("")) {
			JOptionPane.showMessageDialog(null, "Selecione o perfil do usuário");
			cboUsuPerfil.requestFocus();
		}

		// Validação da senha do usuário
		else if (txtUsuSenha.getPassword().length == 0) {
			JOptionPane.showMessageDialog(null, "Preencha a senha do usuário");
			txtUsuSenha.requestFocus();
		}

		else {
			// Lógica principal
			String update2 = "update usuarios set usuario = ?, login = ?, senha = md5(?), perfil = ? where idusu = ?;";

			try {
				// Estabelecer a conexão
				Connection con = dao.conectar();

				// Preparar a execução da query
				PreparedStatement pst = con.prepareStatement(update2);

				// Substituir os ? ? ? ? pelo conteúdo das caixas de texto
				pst.setString(1, txtUsuNome.getText());
				pst.setString(2, txtUsuLogin.getText());
				pst.setString(3, capturaSenha);
				pst.setString(4, cboUsuPerfil.getSelectedItem().toString());
				pst.setString(5, txtUsuId.getText());

				// Executar a query e inserir o usuário no banco
				pst.executeUpdate();

				// Exibir mensagem ao inserir usuário cadastrado com sucesso no banco
				JOptionPane.showMessageDialog(null, "Dados do usuário atualizados com sucesso!");

				limparCampos();
				// NUNCA esquecer de encerrar a conexão
				con.close();

			}

			catch (SQLIntegrityConstraintViolationException ex) {
				JOptionPane.showMessageDialog(null, "Login em uso. \nEscolha outro login.");
				txtUsuLogin.setText(null);
				txtUsuLogin.requestFocus();
			}

			catch (Exception e) {
				System.out.println(e);
			}

		}

	}

	/**
	 * Método responsável por deletar usuários
	 */

	private void excluirUsuario() {

		// Validação
		int confirma = JOptionPane.showConfirmDialog(null, "Confirma a exclusão do usuário?", "Atenção!",
				JOptionPane.YES_NO_OPTION);

		// Lógica principal
		if (confirma == JOptionPane.YES_OPTION) {
			String delete = "delete from usuarios where idusu = ?;";

			try {
				// Estabelecer a conexão
				Connection con = dao.conectar();

				// Preparar a execução da query
				PreparedStatement pst = con.prepareStatement(delete);

				// Substituir o ? pelo conteúdo da caixa de texto
				pst.setString(1, txtUsuId.getText());

				// Executar a query e inserir o usuário no banco
				pst.executeUpdate();

				limparCampos();

				// Exibir mensagem ao inserir usuário cadastrado com sucesso no banco
				JOptionPane.showMessageDialog(null, "Usuário excluido com sucesso!");

				// NUNCA esquecer de encerrar a conexão
				con.close();

			}

			catch (Exception e) {
				System.out.println(e);
			}

		}

	}

	private void limparCampos() {
		txtUsuNome.setText(null);
		txtUsuLogin.setText(null);
		txtUsuSenha.setText(null);
		txtUsuId.setText(null);
		cboUsuPerfil.setSelectedItem("");
		txtUsuSenha.setEditable(true);
		btnAdicionar.setEnabled(false);
		btnAlterar.setEnabled(false);
		btnExcluir.setEnabled(false);
		chkSenha.setVisible(false);
		chkSenha.setSelected(false);
		txtUsuSenha.setBackground(Color.WHITE);

	}

} // Fim do código
