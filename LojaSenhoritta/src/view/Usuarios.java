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
		
		// Restringir a somente caracteres alfanuméricos no campo login
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
	 * Método responsável pela pesquisa de usuários
	 */

	private void pesquisarUsuario() {

		// Validação
		if (txtUsuId.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite o ID do usuário");
			txtUsuId.requestFocus();
		}

		else {
			// Lógica principal
			// Query (instrução SQL)

			String read = "select * from usuarios where idusu = ?;";

			try {
				// Estabelecer a conexão
				Connection con = dao.conectar();

				// Preparar a execução da query
				PreparedStatement pst = con.prepareStatement(read);

				// Setar o argumento (id)
				// Substituir o ? pelo conteúdo da caixa de texto
				pst.setString(1, txtUsuId.getText());

				// Executar a query e exibir o resultado no formulário
				ResultSet rs = pst.executeQuery();

				// Validação (existência do usuário)
				// rs.next() -> existência de usuário
				if (rs.next()) {
					// Preencher (setar) os campos do formulário
					txtUsuNome.setText(rs.getString(2));
					txtUsuLogin.setText(rs.getString(3));
					cboUsuPerfil.setSelectedItem(rs.getString(5));
					txtUsuSenha.setText(rs.getString(4));
				}

				else {
					JOptionPane.showMessageDialog(null, "Usuário inexistente");
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

	private void limparCampos() {
		txtUsuNome.setText(null);
		txtUsuLogin.setText(null);
		txtUsuSenha.setText(null);
		cboUsuPerfil.setSelectedItem("");

	}

} // Fim do código
