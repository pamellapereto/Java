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
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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
		setResizable(false);
		setModal(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Usuarios.class.getResource("/img/logo.png")));
		setTitle("Usuarios");
		setBounds(100, 100, 486, 341);
		getContentPane().setLayout(null);

		JLabel lblNewLabel_2 = new JLabel("Login");
		lblNewLabel_2.setBounds(30, 35, 46, 14);
		getContentPane().add(lblNewLabel_2);

		txtUsuLogin = new JTextField();
		
		addWindowListener(new WindowAdapter() {
			public void windowOpened(WindowEvent e) {
				txtUsuLogin.requestFocus();
			}
		});

		txtUsuLogin.setBounds(95, 32, 125, 20);
		getContentPane().add(txtUsuLogin);

		JLabel lblNewLabel = new JLabel("ID");
		lblNewLabel.setBounds(305, 35, 32, 14);
		getContentPane().add(lblNewLabel);

		txtUsuId = new JTextField();
		txtUsuId.setEditable(false);
		txtUsuId.setBounds(340, 32, 46, 20);
		getContentPane().add(txtUsuId);

		JLabel lblNewLabel_1 = new JLabel("Usuario");
		lblNewLabel_1.setBounds(30, 91, 71, 14);
		getContentPane().add(lblNewLabel_1);

		txtUsuNome = new JTextField();
		txtUsuNome.setBounds(95, 88, 310, 20);
		getContentPane().add(txtUsuNome);

		JLabel lblNewLabel_3 = new JLabel("Senha");
		lblNewLabel_3.setBounds(30, 133, 46, 14);
		getContentPane().add(lblNewLabel_3);

		txtUsuSenha = new JPasswordField();
		txtUsuSenha.setBounds(95, 130, 206, 20);
		getContentPane().add(txtUsuSenha);

		JLabel lblNewLabel_4 = new JLabel("Perfil");
		lblNewLabel_4.setBounds(30, 180, 40, 14);
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
		btnPesquisar.setBounds(228, 25, 32, 32);
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
				// Logica para verificar o checkbox

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

		// Validacao com o uso da biblioteca Atxy2k

		// txtUsuNome
		RestrictedTextField validarNome = new RestrictedTextField(txtUsuNome);

		// Restringir a somente letras no campo nome
		validarNome.setOnlyText(true);
		// Aceitar espaco no campo nome
		validarNome.setAcceptSpace(true);
		// Limitar a somente 50 caracteres no campo nome
		validarNome.setLimit(50);

		// txtUsuLogin
		RestrictedTextField validarLogin = new RestrictedTextField(txtUsuLogin);

		// Restringir a caracteres alfanumericos no campo login
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

	}// Fim do construtor

	DAO dao = new DAO();
	private JComboBox cboUsuPerfil;
	private JButton btnAdicionar;
	private JButton btnAlterar;
	private JButton btnExcluir;
	private JCheckBox chkSenha;

	/**
	 * Metodo responsavel pela pesquisa de usuarios
	 */

	private void pesquisarUsuario() {

		// Validacao
		if (txtUsuLogin.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite o login do usuario");
			txtUsuLogin.requestFocus();
		}

		else {
			// Logica principal
			// Query (instrucao SQL)

			String read = "select * from usuarios where login = ?;";

			try {
				// Estabelecer a conexao
				Connection con = dao.conectar();

				// Preparar a execucao da query
				PreparedStatement pst = con.prepareStatement(read);

				// Setar o argumento (login)
				// Substituir o ? pelo conteudo da caixa de texto
				pst.setString(1, txtUsuLogin.getText());

				// Executar a query e exibir o resultado no formulario
				ResultSet rs = pst.executeQuery();

				// Validacao (existencia do usuario)
				// rs.next() -> existencia de usuario
				if (rs.next()) {
					// Preencher (setar) os campos do formulario
					txtUsuNome.setText(rs.getString(2));
					txtUsuId.setText(rs.getString(1));
					cboUsuPerfil.setSelectedItem(rs.getString(5));
					txtUsuSenha.setText(rs.getString(4));
					txtUsuSenha.setEditable(false);
					txtUsuNome.requestFocus();
					btnAlterar.setEnabled(true);
					btnExcluir.setEnabled(true);
					chkSenha.setVisible(true);
					btnAdicionar.setEnabled(false);

				}

				else {
					// Validacao

					JOptionPane.showMessageDialog(null, "Usuario inexistente!");

					txtUsuNome.requestFocus();
					btnAdicionar.setEnabled(true);
					limparCampos();

				}

				// NUNCA esquecer de encerrar a conexao
				con.close();

			}

			// Tratar excecoes sempre que lidar com o banco
			catch (Exception e) {
				System.out.println(e);
			}

		}

	}

	/**
	 * Metodo responsavel por adicionar usuarios
	 */

	private void adicionarUsuario() {

		// Validacao da senha (captura segura)
		String capturaSenha = new String(txtUsuSenha.getPassword());

		// Validacao do nome do usuario
		if (txtUsuNome.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o nome do usuario");
			txtUsuNome.requestFocus();
		}

		// Validacao do login do usuario
		else if (txtUsuLogin.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o login do usuario");
			txtUsuLogin.requestFocus();
		}

		// Validacao do perfil do usuario
		else if (cboUsuPerfil.getSelectedItem().equals("")) {
			JOptionPane.showMessageDialog(null, "Selecione o perfil do usuario");
			cboUsuPerfil.requestFocus();
		}

		// Validacao da senha do usuario
		else if (txtUsuSenha.getPassword().length == 0) {
			JOptionPane.showMessageDialog(null, "Preencha a senha do usuario");
			txtUsuSenha.requestFocus();
		}

		else {
			// Logica principal
			String create = "insert into usuarios (usuario, login, senha, perfil) values (?, ?, md5(?), ?);";

			try {
				// Estabelecer a conexao
				Connection con = dao.conectar();

				// Preparar a execucao da query
				PreparedStatement pst = con.prepareStatement(create);

				// Substituir os ? ? ? ? pelo conteudo das caixas de texto
				pst.setString(1, txtUsuNome.getText());
				pst.setString(2, txtUsuLogin.getText());
				pst.setString(3, capturaSenha);
				pst.setString(4, cboUsuPerfil.getSelectedItem().toString());

				// Executar a query e inserir o usuario no banco
				pst.executeUpdate();

				// Exibir mensagem ao cadastrar usuario com sucesso no banco
				JOptionPane.showMessageDialog(null, "Usuario cadastrado com sucesso!");

				limparCampos();
				txtUsuLogin.setText(null);
				txtUsuLogin.requestFocus();
				// NUNCA esquecer de encerrar a conexao
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
	 * Metodo responsavel por alterar os dados de um usuario no banco, exceto a
	 * senha
	 */
	private void alterarUsuario() {
		// Validacao do nome do usuario
		if (txtUsuNome.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o nome do usuario");
			txtUsuNome.requestFocus();
		}

		// Validacao do login do usuario
		else if (txtUsuLogin.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o login do usuario");
			txtUsuLogin.requestFocus();
		}

		// Validacao do perfil do usuario
		else if (cboUsuPerfil.getSelectedItem().equals("")) {
			JOptionPane.showMessageDialog(null, "Selecione o perfil do usuario");
			cboUsuPerfil.requestFocus();
		}

		// Validacao da senha do usuario
		else if (txtUsuSenha.getPassword().length == 0) {
			JOptionPane.showMessageDialog(null, "Preencha a senha do usuario");
			txtUsuSenha.requestFocus();
		}

		else {
			// Logica principal
			String update = "update usuarios set usuario = ?, login = ?, perfil = ? where idusu = ?;";

			try {
				// Estabelecer a conexao
				Connection con = dao.conectar();

				// Preparar a execucao da query
				PreparedStatement pst = con.prepareStatement(update);

				// Substituir os ? ? ? ? pelo conteudo das caixas de texto
				pst.setString(1, txtUsuNome.getText());
				pst.setString(2, txtUsuLogin.getText());
				pst.setString(3, cboUsuPerfil.getSelectedItem().toString());
				pst.setString(4, txtUsuId.getText());

				// Executar a query e atualizar dados do usuario no banco
				pst.executeUpdate();

				// Exibir mensagem ao atualizar dados do usuario com sucesso no banco
				JOptionPane.showMessageDialog(null, "Dados do usuario, exceto senha, atualizados com sucesso!");

				limparCampos();
				txtUsuLogin.setText(null);
				txtUsuLogin.requestFocus();

				// NUNCA esquecer de encerrar a conexao
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
	 * Metodo responsavel por os dados de um usuario e sua respectiva senha no banco
	 */

	private void alterarUsuarioSenha() {

		// Validacao da senha (captura segura)
		String capturaSenha = new String(txtUsuSenha.getPassword());

		// Validacao do nome do usuario
		if (txtUsuNome.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o nome do usuario");
			txtUsuNome.requestFocus();
		}

		// Validacao do login do usuario
		else if (txtUsuLogin.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o login do usuario");
			txtUsuLogin.requestFocus();
		}

		// Validacao do perfil do usuario
		else if (cboUsuPerfil.getSelectedItem().equals("")) {
			JOptionPane.showMessageDialog(null, "Selecione o perfil do usuario");
			cboUsuPerfil.requestFocus();
		}

		// Validacao da senha do usuario
		else if (txtUsuSenha.getPassword().length == 0) {
			JOptionPane.showMessageDialog(null, "Preencha a senha do usuario");
			txtUsuSenha.requestFocus();
		}

		else {
			// Logica principal
			String update2 = "update usuarios set usuario = ?, login = ?, senha = md5(?), perfil = ? where idusu = ?;";

			try {
				// Estabelecer a conexao
				Connection con = dao.conectar();

				// Preparar a execucao da query
				PreparedStatement pst = con.prepareStatement(update2);

				// Substituir os ? ? ? ? pelo conteudo das caixas de texto
				pst.setString(1, txtUsuNome.getText());
				pst.setString(2, txtUsuLogin.getText());
				pst.setString(3, capturaSenha);
				pst.setString(4, cboUsuPerfil.getSelectedItem().toString());
				pst.setString(5, txtUsuId.getText());

				// Executar a query e atualizar dados do usuario no banco
				pst.executeUpdate();

				// Exibir mensagem ao atualizar dados do usuario com sucesso no banco
				JOptionPane.showMessageDialog(null, "Dados do usuario atualizados com sucesso!");

				limparCampos();
				txtUsuLogin.setText(null);
				txtUsuLogin.requestFocus();

				// NUNCA esquecer de encerrar a conexao
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
	 * Metodo responsavel por deletar usuarios
	 */

	private void excluirUsuario() {

		// Validacao
		int confirma = JOptionPane.showConfirmDialog(null, "Confirma a exclusao do usuario?", "Atençao!",
				JOptionPane.YES_NO_OPTION);

		// Logica principal
		if (confirma == JOptionPane.YES_OPTION) {
			String delete = "delete from usuarios where idusu = ?;";

			try {
				// Estabelecer a conexao
				Connection con = dao.conectar();

				// Preparar a execucao da query
				PreparedStatement pst = con.prepareStatement(delete);

				// Substituir o ? pelo conteudo da caixa de texto
				pst.setString(1, txtUsuId.getText());

				// Executar a query e deletar usuario no banco
				pst.executeUpdate();

				limparCampos();
				txtUsuLogin.setText(null);
				txtUsuLogin.requestFocus();

				// Exibir mensagem ao deletar usuario
				JOptionPane.showMessageDialog(null, "Usuario excluido com sucesso!");

				// NUNCA esquecer de encerrar a conexao
				con.close();

			}

			catch (Exception e) {
				System.out.println(e);
			}

		}

	}

	private void limparCampos() {
		txtUsuNome.setText(null);
		txtUsuSenha.setText(null);
		txtUsuId.setText(null);
		cboUsuPerfil.setSelectedItem("");
		txtUsuSenha.setEditable(true);
		btnAlterar.setEnabled(false);
		btnExcluir.setEnabled(false);
		chkSenha.setVisible(false);
		chkSenha.setSelected(false);
		txtUsuSenha.setBackground(Color.WHITE);

	}

} // Fim do codigo
