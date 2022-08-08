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
import javax.swing.ImageIcon;
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
		setBounds(100, 100, 486, 340);
		getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("ID");
		lblNewLabel.setBounds(39, 36, 34, 14);
		getContentPane().add(lblNewLabel);

		txtUsuId = new JTextField();
		txtUsuId.setBounds(72, 33, 73, 20);
		getContentPane().add(txtUsuId);
		txtUsuId.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Usu\u00E1rio");
		lblNewLabel_1.setBounds(39, 91, 46, 14);
		getContentPane().add(lblNewLabel_1);

		txtUsuNome = new JTextField();
		txtUsuNome.setBounds(95, 88, 290, 20);
		getContentPane().add(txtUsuNome);
		txtUsuNome.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Login");
		lblNewLabel_2.setBounds(39, 138, 46, 14);
		getContentPane().add(lblNewLabel_2);

		txtUsuLogin = new JTextField();
		txtUsuLogin.setBounds(95, 135, 118, 20);
		getContentPane().add(txtUsuLogin);
		txtUsuLogin.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("Senha");
		lblNewLabel_3.setBounds(39, 183, 46, 14);
		getContentPane().add(lblNewLabel_3);

		txtUsuSenha = new JPasswordField();
		txtUsuSenha.setBounds(95, 180, 290, 20);
		getContentPane().add(txtUsuSenha);

		JLabel lblNewLabel_4 = new JLabel("Perfil");
		lblNewLabel_4.setBounds(239, 138, 46, 14);
		getContentPane().add(lblNewLabel_4);

		cboUsuPerfil = new JComboBox();
		cboUsuPerfil.setModel(new DefaultComboBoxModel(new String[] { "", "admin", "user" }));
		cboUsuPerfil.setBounds(282, 134, 103, 22);
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
		btnPesquisar.setBounds(160, 25, 32, 32);
		getContentPane().add(btnPesquisar);

		JButton btnAdicionar = new JButton("");
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

		JButton btnAlterar = new JButton("");
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adicionarUsuario();
			}
		});

		btnAlterar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAlterar.setIcon(new ImageIcon(Usuarios.class.getResource("/img/update.png")));
		btnAlterar.setToolTipText("Alterar");
		btnAlterar.setContentAreaFilled(false);
		btnAlterar.setBorderPainted(false);
		btnAlterar.setBounds(322, 226, 64, 64);
		getContentPane().add(btnAlterar);

		JButton btnExcluir = new JButton("");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
		
		// Restringir a somente caracteres alfanum�ricos no campo login
		validarLogin.setOnlyAlphaNumeric(true);
		// Limitar a somente 15 caracteres no campo login
		validarLogin.setLimit(15);
		
		
		// txtUsuSenha
		RestrictedTextField validarSenha = new RestrictedTextField(txtUsuSenha);
		
		// Limitar a somente 50 caracteres no campo senha
		validarSenha.setLimit(50);
		
		
	}// Fim do construtor

	DAO dao = new DAO();
	private JComboBox cboUsuPerfil;

	/**
	 * M�todo respons�vel pela pesquisa de usu�rios
	 */

	private void pesquisarUsuario() {

		// Valida��o
		if (txtUsuId.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite o ID do usu�rio");
			txtUsuId.requestFocus();
		}

		else {
			// L�gica principal
			// Query (instru��o SQL)

			String read = "select * from usuarios where idusu = ?;";

			try {
				// Estabelecer a conex�o
				Connection con = dao.conectar();

				// Preparar a execu��o da query
				PreparedStatement pst = con.prepareStatement(read);

				// Setar o argumento (id)
				// Substituir o ? pelo conte�do da caixa de texto
				pst.setString(1, txtUsuId.getText());

				// Executar a query e exibir o resultado no formul�rio
				ResultSet rs = pst.executeQuery();

				// Valida��o (exist�ncia do usu�rio)
				// rs.next() -> exist�ncia de usu�rio
				if (rs.next()) {
					// Preencher (setar) os campos do formul�rio
					txtUsuNome.setText(rs.getString(2));
					txtUsuLogin.setText(rs.getString(3));
					cboUsuPerfil.setSelectedItem(rs.getString(5));
					txtUsuSenha.setText(rs.getString(4));
				}

				else {
					JOptionPane.showMessageDialog(null, "Usu�rio inexistente");
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

	private void limparCampos() {
		txtUsuNome.setText(null);
		txtUsuLogin.setText(null);
		txtUsuSenha.setText(null);
		cboUsuPerfil.setSelectedItem("");

	}

} // Fim do c�digo
