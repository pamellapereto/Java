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
		lblNewLabel_1.setBounds(30, 66, 55, 14);
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

		// Validacao com o uso da biblioteca Atxy2k

		// txtLogin
		RestrictedTextField validarLogin = new RestrictedTextField(txtLogin);

		// Restringir a caracteres alfanumericos no campo login
		validarLogin.setOnlyAlphaNumeric(true);
		// Limitar a somente 15 caracteres no campo login
		validarLogin.setLimit(15);

		// txtUsuSenha
		RestrictedTextField validarSenha = new RestrictedTextField(txtSenha);

		// Limitar a somente 10 caracteres no campo senha
		validarSenha.setLimit(10);

		// Usar o Enter ao inves de "clicar" no botao para logar
		getRootPane().setDefaultButton(btnLogar);

	} // Fim do construtor

	// Criacao de um objeto para acessar a camada model
	DAO dao = new DAO();
	private JLabel lblStatus;

	/**
	 * Metodo usado para verificar o status do servidor
	 */

	private void status() {

		try {
			// Abrir a conexao
			Connection con = dao.conectar();

			if (con == null) {
				// Escolher a imagem
				lblStatus.setIcon(new ImageIcon(Login.class.getResource("/img/databaseOff.png")));

			} else {
				lblStatus.setIcon(new ImageIcon(Login.class.getResource("/img/databaseOn.png")));
			}

			// Nao esquecer de fechar a conexao
			con.close();
		} catch (Exception e) {
			System.out.println(e);

		}

	}

	/**
	 * Metodo responsavel para autenticar o usuario
	 */

	private void logar() {

		// Validacao da senha (captura segura)
		String capturaSenha = new String(txtSenha.getPassword());

		// Validacao do login do usuario
		if (txtLogin.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite o login do usuario");
			txtLogin.requestFocus();
		}

		// Validacao da senha do usuario
		else if (txtSenha.getPassword().length == 0) {
			JOptionPane.showMessageDialog(null, "Digite a senha do usuario");
			txtSenha.requestFocus();
		}

		// Logica principal
		// Query (instrucao SQL)
		else {
			String read = "select * from usuarios where login=? and senha=md5(?);";

			try {
				// Estabelecer a conexao
				Connection con = dao.conectar();

				// Preparar a execucao da query
				PreparedStatement pst = con.prepareStatement(read);

				// Setar o argumento (login e senha)
				// Substituir o ? ? (argumentos) pelo conteudo da caixa de texto
				pst.setString(1, txtLogin.getText());

				pst.setString(2, capturaSenha);

				// Executar a query e de acordo com o resultado liberar os recursos na tela
				// principal
				ResultSet rs = pst.executeQuery();

				// Validacao (autenticacao do usuario)
				// rs.next() -> existencia de login e senha correspondente

				if (rs.next()) {

					// Verificar o perfil do usuario

					String perfil = rs.getString(5);
					// System.out.println(perfil);

					Principal principal = new Principal();
					// Abrir a tela principal
					principal.setVisible(true);

					// Setar o nome do usuario na tela principal
					principal.txtLoginPrincipal.setText("Usuário: " + rs.getString(2));

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

					JOptionPane.showMessageDialog(null, "Login e/ou senha invalido(s)!");

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

} // Fim do codigo
