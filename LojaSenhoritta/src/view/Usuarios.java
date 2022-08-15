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
				// L�gica para verificar o checkbox

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

		// Valida��o com o uso da biblioteca Atxy2k

		// txtUsuId
		RestrictedTextField validarId = new RestrictedTextField(txtUsuId);

		// Restringir a somente n�meros no campo ID
		validarId.setOnlyNums(true);
		// Limitar a somente 4 n�meros no campo ID
		validarId.setLimit(4);

		// txtUsuNome
		RestrictedTextField validarNome = new RestrictedTextField(txtUsuNome);

		// Restringir a somente letras no campo nome
		validarNome.setOnlyText(true);
		// Aceitar espa�o no campo nome
		validarNome.setAcceptSpace(true);
		// Limitar a somente 50 caracteres no campo nome
		validarNome.setLimit(50);

		// txtUsuLogin
		RestrictedTextField validarLogin = new RestrictedTextField(txtUsuLogin);

		// Restringir a caracteres alfanum�ricos no campo login
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
	 * M�todo respons�vel pela pesquisa de usu�rios
	 */

	private void pesquisarUsuario() {

		// Valida��o
		if (txtUsuLogin.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite o login do usu�rio");
			txtUsuLogin.requestFocus();
		}

		else {
			// L�gica principal
			// Query (instru��o SQL)

			String read = "select * from usuarios where login = ?;";

			try {
				// Estabelecer a conex�o
				Connection con = dao.conectar();

				// Preparar a execu��o da query
				PreparedStatement pst = con.prepareStatement(read);

				// Setar o argumento (login)
				// Substituir o ? pelo conte�do da caixa de texto
				pst.setString(1, txtUsuLogin.getText());

				// Executar a query e exibir o resultado no formul�rio
				ResultSet rs = pst.executeQuery();

				// Valida��o (exist�ncia do usu�rio)
				// rs.next() -> exist�ncia de usu�rio
				if (rs.next()) {
					// Preencher (setar) os campos do formul�rio
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
					// Valida��o

					JOptionPane.showMessageDialog(null, "Usu�rio inexistente!");
					// txtUsuLogin.setEditable(false);
					txtUsuNome.setEditable(true);
					cboUsuPerfil.setEnabled(true);
					txtUsuSenha.setEditable(true);
					txtUsuNome.requestFocus();
					btnAdicionar.setEnabled(true);

				}

				// NUNCA esquecer de encerrar a conex�o
				con.close();

			}

			// Tratar exce��es sempre que lidar com o banco
			catch (Exception e) {
				System.out.println(e);
			}

		}

	}

	/**
	 * M�todo respons�vel por adicionar usu�rios
	 */

	private void adicionarUsuario() {

		// Valida��o da senha (captura segura)
		String capturaSenha = new String(txtUsuSenha.getPassword());

		// Valida��o do nome do usu�rio
		if (txtUsuNome.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o nome do usu�rio");
			txtUsuNome.requestFocus();
		}

		// Valida��o do login do usu�rio
		else if (txtUsuLogin.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o login do usu�rio");
			txtUsuLogin.requestFocus();
		}

		// Valida��o do perfil do usu�rio
		else if (cboUsuPerfil.getSelectedItem().equals("")) {
			JOptionPane.showMessageDialog(null, "Selecione o perfil do usu�rio");
			cboUsuPerfil.requestFocus();
		}

		// Valida��o da senha do usu�rio
		else if (txtUsuSenha.getPassword().length == 0) {
			JOptionPane.showMessageDialog(null, "Preencha a senha do usu�rio");
			txtUsuSenha.requestFocus();
		}

		else {
			// L�gica principal
			String create = "insert into usuarios (usuario, login, senha, perfil) values (?, ?, md5(?), ?);";

			try {
				// Estabelecer a conex�o
				Connection con = dao.conectar();

				// Preparar a execu��o da query
				PreparedStatement pst = con.prepareStatement(create);

				// Substituir os ? ? ? ? pelo conte�do das caixas de texto
				pst.setString(1, txtUsuNome.getText());
				pst.setString(2, txtUsuLogin.getText());
				pst.setString(3, capturaSenha);
				pst.setString(4, cboUsuPerfil.getSelectedItem().toString());

				// Executar a query e inserir o usu�rio no banco
				pst.executeUpdate();

				// Exibir mensagem ao inserir usu�rio cadastrado com sucesso no banco
				JOptionPane.showMessageDialog(null, "Usu�rio cadastrado com sucesso!");

				limparCampos();
				// NUNCA esquecer de encerrar a conex�o
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
	 * M�todo respons�vel por alterar os dados de um usu�rio no banco, exceto a
	 * senha
	 */
	private void alterarUsuario() {
		// Valida��o do nome do usu�rio
		if (txtUsuNome.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o nome do usu�rio");
			txtUsuNome.requestFocus();
		}

		// Valida��o do login do usu�rio
		else if (txtUsuLogin.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o login do usu�rio");
			txtUsuLogin.requestFocus();
		}

		// Valida��o do perfil do usu�rio
		else if (cboUsuPerfil.getSelectedItem().equals("")) {
			JOptionPane.showMessageDialog(null, "Selecione o perfil do usu�rio");
			cboUsuPerfil.requestFocus();
		}

		// Valida��o da senha do usu�rio
		else if (txtUsuSenha.getPassword().length == 0) {
			JOptionPane.showMessageDialog(null, "Preencha a senha do usu�rio");
			txtUsuSenha.requestFocus();
		}

		else {
			// L�gica principal
			String update = "update usuarios set usuario = ?, login = ?, perfil = ? where idusu = ?;";

			try {
				// Estabelecer a conex�o
				Connection con = dao.conectar();

				// Preparar a execu��o da query
				PreparedStatement pst = con.prepareStatement(update);

				// Substituir os ? ? ? ? pelo conte�do das caixas de texto
				pst.setString(1, txtUsuNome.getText());
				pst.setString(2, txtUsuLogin.getText());
				pst.setString(3, cboUsuPerfil.getSelectedItem().toString());
				pst.setString(4, txtUsuId.getText());

				// Executar a query e inserir o usu�rio no banco
				pst.executeUpdate();

				// Exibir mensagem ao inserir usu�rio cadastrado com sucesso no banco
				JOptionPane.showMessageDialog(null, "Dados do usu�rio, exceto senha, atualizados com sucesso!");

				limparCampos();
				// NUNCA esquecer de encerrar a conex�o
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
	 * M�todo respons�vel por os dados de um usu�rio e sua respectiva senha no banco
	 */

	private void alterarUsuarioSenha() {

		// Valida��o da senha (captura segura)
		String capturaSenha = new String(txtUsuSenha.getPassword());

		// Valida��o do nome do usu�rio
		if (txtUsuNome.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o nome do usu�rio");
			txtUsuNome.requestFocus();
		}

		// Valida��o do login do usu�rio
		else if (txtUsuLogin.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o login do usu�rio");
			txtUsuLogin.requestFocus();
		}

		// Valida��o do perfil do usu�rio
		else if (cboUsuPerfil.getSelectedItem().equals("")) {
			JOptionPane.showMessageDialog(null, "Selecione o perfil do usu�rio");
			cboUsuPerfil.requestFocus();
		}

		// Valida��o da senha do usu�rio
		else if (txtUsuSenha.getPassword().length == 0) {
			JOptionPane.showMessageDialog(null, "Preencha a senha do usu�rio");
			txtUsuSenha.requestFocus();
		}

		else {
			// L�gica principal
			String update2 = "update usuarios set usuario = ?, login = ?, senha = md5(?), perfil = ? where idusu = ?;";

			try {
				// Estabelecer a conex�o
				Connection con = dao.conectar();

				// Preparar a execu��o da query
				PreparedStatement pst = con.prepareStatement(update2);

				// Substituir os ? ? ? ? pelo conte�do das caixas de texto
				pst.setString(1, txtUsuNome.getText());
				pst.setString(2, txtUsuLogin.getText());
				pst.setString(3, capturaSenha);
				pst.setString(4, cboUsuPerfil.getSelectedItem().toString());
				pst.setString(5, txtUsuId.getText());

				// Executar a query e inserir o usu�rio no banco
				pst.executeUpdate();

				// Exibir mensagem ao inserir usu�rio cadastrado com sucesso no banco
				JOptionPane.showMessageDialog(null, "Dados do usu�rio atualizados com sucesso!");

				limparCampos();
				// NUNCA esquecer de encerrar a conex�o
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
	 * M�todo respons�vel por deletar usu�rios
	 */

	private void excluirUsuario() {

		// Valida��o
		int confirma = JOptionPane.showConfirmDialog(null, "Confirma a exclus�o do usu�rio?", "Aten��o!",
				JOptionPane.YES_NO_OPTION);

		// L�gica principal
		if (confirma == JOptionPane.YES_OPTION) {
			String delete = "delete from usuarios where idusu = ?;";

			try {
				// Estabelecer a conex�o
				Connection con = dao.conectar();

				// Preparar a execu��o da query
				PreparedStatement pst = con.prepareStatement(delete);

				// Substituir o ? pelo conte�do da caixa de texto
				pst.setString(1, txtUsuId.getText());

				// Executar a query e inserir o usu�rio no banco
				pst.executeUpdate();

				limparCampos();

				// Exibir mensagem ao inserir usu�rio cadastrado com sucesso no banco
				JOptionPane.showMessageDialog(null, "Usu�rio excluido com sucesso!");

				// NUNCA esquecer de encerrar a conex�o
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

} // Fim do c�digo
