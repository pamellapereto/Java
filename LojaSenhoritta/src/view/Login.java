package view;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.DAO;

import Atxy2k.CustomTextField.RestrictedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtLogin;
	private JPasswordField txtSenha;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				status();
			}
		});
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/img/logo.png")));
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 349, 209);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Login");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setBounds(29, 29, 37, 14);
		contentPane.add(lblNewLabel);

		txtLogin = new JTextField();
		txtLogin.setBounds(83, 26, 203, 20);
		contentPane.add(txtLogin);
		txtLogin.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Senha");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1.setBounds(25, 67, 37, 14);
		contentPane.add(lblNewLabel_1);

		txtSenha = new JPasswordField();
		txtSenha.setBounds(83, 64, 203, 20);
		contentPane.add(txtSenha);

		JButton btnLogar = new JButton("Acessar");
		btnLogar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				logar();
			}
		});
		btnLogar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnLogar.setBounds(197, 117, 89, 23);
		contentPane.add(btnLogar);

		lblStatus = new JLabel("");
		lblStatus.setIcon(new ImageIcon(Login.class.getResource("/img/databaseOff.png")));
		lblStatus.setBounds(7, 106, 64, 64);
		contentPane.add(lblStatus);

		// Valida��o com o uso da biblioteca Atxy2k

		// txtLogin
		RestrictedTextField validarLogin = new RestrictedTextField(txtLogin);

		// Restringir a caracteres alfanum�ricos no campo login
		validarLogin.setOnlyAlphaNumeric(true);
		// Limitar a somente 15 caracteres no campo login
		validarLogin.setLimit(15);

		// txtUsuSenha
		RestrictedTextField validarSenha = new RestrictedTextField(txtSenha);

		// Limitar a somente 10 caracteres no campo senha
		validarSenha.setLimit(10);

		// Usar o Enter ao inv�s de "clicar" no bot�o para logar
		getRootPane().setDefaultButton(btnLogar);

	} // Fim do construtor

	// Cria��o de um objeto para acessar a camada model
	DAO dao = new DAO();
	private JLabel lblStatus;

	/**
	 * M�todo usado para verificar o status do servidor
	 */

	private void status() {

		try {
			// Abrir a conex�o
			Connection con = dao.conectar();

			if (con == null) {
				// Escolher a imagem
				lblStatus.setIcon(new ImageIcon(Login.class.getResource("/img/databaseOff.png")));

			} else {
				lblStatus.setIcon(new ImageIcon(Login.class.getResource("/img/databaseOn.png")));
			}

			// N�o esquecer de fechar a conex�o
			con.close();
		} catch (Exception e) {
			System.out.println(e);

		}

	}

	/**
	 * M�todo respons�vel para autenticar o usu�rio
	 */

	private void logar() {

		// Valida��o da senha (captura segura)
		String capturaSenha = new String(txtSenha.getPassword());

		// Valida��o do login do usu�rio
		if (txtLogin.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite o login do usu�rio");
			txtLogin.requestFocus();
		}

		// Valida��o da senha do usu�rio
		else if (txtSenha.getPassword().length == 0) {
			JOptionPane.showMessageDialog(null, "Digite a senha do usu�rio");
			txtSenha.requestFocus();
		}

		// L�gica principal
		// Query (instru��o SQL)
		else {
			String read = "select * from usuarios where login=? and senha=md5(?);";

			try {
				// Estabelecer a conex�o
				Connection con = dao.conectar();

				// Preparar a execu��o da query
				PreparedStatement pst = con.prepareStatement(read);

				// Setar o argumento (login e senha)
				// Substituir o ? ? (argumentos) pelo conte�do da caixa de texto
				pst.setString(1, txtLogin.getText());

				pst.setString(2, capturaSenha);

				// Executar a query e de acordo com o resultado liberar os recursos na tela
				// principal
				ResultSet rs = pst.executeQuery();

				// Valida��o (autentica��o do usu�rio)
				// rs.next() -> exist�ncia de login e senha correspondente

				if (rs.next()) {

					// Verificar o perfil do usu�rio

					String perfil = rs.getString(5);
					// System.out.println(perfil);

					Principal principal = new Principal();
					// Abrir a tela principal
					principal.setVisible(true);

					// Setar o nome do usu�rio na tela principal
					principal.txtLoginPrincipal.setText("Usu�rio: " + rs.getString(2));

					if (perfil.equals("admin")) {

						// Habilitar recursos
						principal.btnUsuarios.setEnabled(true);
						principal.btnRelatorios.setEnabled(true);

						// Personalizar recursos
						principal.panelUsuario.setBackground(SystemColor.textHighlight);

					}

					// Fechar a tela de login
					this.dispose();

				}

				else {

					JOptionPane.showMessageDialog(null, "Login e/ou senha inv�lido(s)!");

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

} // Fim do c�digo
