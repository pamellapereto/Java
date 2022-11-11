package view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Principal extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblData;

	// Atribuir o modificador publico para manipular esses objetos de outra classe
	public JButton btnUsuarios;
	public JButton btnRelatorios;
	public JLabel txtLoginPrincipal;
	public JPanel panelUsuario;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal frame = new Principal();
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
	public Principal() {
		addWindowListener(new WindowAdapter() {

			// Evento ativar janela
			@Override

			public void windowActivated(WindowEvent e) {
				Date data = new Date();
				DateFormat formatador = DateFormat.getDateInstance(DateFormat.FULL);
				lblData.setText(formatador.format(data));
			}
		});
		setIconImage(Toolkit.getDefaultToolkit().getImage(Principal.class.getResource("/img/logo.png")));
		setTitle("Loja Senhoritta");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 744, 481);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		btnUsuarios = new JButton("");
		btnUsuarios.setEnabled(false);

		btnUsuarios.addActionListener(new ActionListener() {
			// evento clicar no botao
			public void actionPerformed(ActionEvent e) {
				// Link para o jDialog
				Usuarios usuarios = new Usuarios();
				usuarios.setVisible(true);
			}
		});

		btnUsuarios.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnUsuarios.setToolTipText("Usuarios");
		btnUsuarios.setIcon(new ImageIcon(Principal.class.getResource("/img/users.png")));
		btnUsuarios.setBounds(47, 36, 128, 128);
		contentPane.add(btnUsuarios);

		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Fornecedores fornecedores = new Fornecedores();
				fornecedores.setVisible(true);
			}
		});
		btnNewButton_1.setToolTipText("Fornecedores");
		btnNewButton_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton_1.setIcon(new ImageIcon(Principal.class.getResource("/img/supplier.png")));
		btnNewButton_1.setBounds(302, 36, 128, 128);
		contentPane.add(btnNewButton_1);

		JButton btnNewButton_1_1 = new JButton("");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Clientes clientes = new Clientes();
				clientes.setVisible(true);
			}
		});
		btnNewButton_1_1.setToolTipText("Clientes");
		btnNewButton_1_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton_1_1.setIcon(new ImageIcon(Principal.class.getResource("/img/client.png")));
		btnNewButton_1_1.setBounds(552, 36, 128, 128);
		contentPane.add(btnNewButton_1_1);

		JButton btnNewButton_2 = new JButton("");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Produtos produtos = new Produtos();
				produtos.setVisible(true);
			}
		});
		btnNewButton_2.setToolTipText("Produtos");
		btnNewButton_2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton_2.setIcon(new ImageIcon(Principal.class.getResource("/img/product.png")));
		btnNewButton_2.setBounds(302, 227, 128, 128);
		contentPane.add(btnNewButton_2);

		/*JButton btnNewButton_3 = new JButton("");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PontoDeVenda pdv = new PontoDeVenda();
				pdv.setVisible(true);
			}
		});

		btnNewButton_3.setToolTipText("PDV");
		btnNewButton_3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton_3.setIcon(new ImageIcon(Principal.class.getResource("/img/store.png")));
		btnNewButton_3.setBounds(85, 198, 128, 128);
		contentPane.add(btnNewButton_3);*/

		btnRelatorios = new JButton("");
		btnRelatorios.setEnabled(false);
		btnRelatorios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Relatorios relatorios = new Relatorios();
				relatorios.setVisible(true);
			}
		});

		btnRelatorios.setToolTipText("Relatorios");
		btnRelatorios.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnRelatorios.setIcon(new ImageIcon(Principal.class.getResource("/img/report.png")));
		btnRelatorios.setBounds(47, 227, 128, 128);
		contentPane.add(btnRelatorios);

		/*JButton btnNewButton_2_1 = new JButton("");
		btnNewButton_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Ferramentas ferramentas = new Ferramentas();
				ferramentas.setVisible(true);
			}
		});

		btnNewButton_2_1.setToolTipText("Ferramentas");
		btnNewButton_2_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton_2_1.setIcon(new ImageIcon(Principal.class.getResource("/img/tools.png")));
		btnNewButton_2_1.setBounds(323, 177, 128, 128);
		contentPane.add(btnNewButton_2_1);*/

		JButton btnNewButton_1_1_1 = new JButton("");
		btnNewButton_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Sobre sobre = new Sobre();
				sobre.setVisible(true);
			}
		});

		btnNewButton_1_1_1.setToolTipText("Sobre");
		btnNewButton_1_1_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton_1_1_1.setIcon(new ImageIcon(Principal.class.getResource("/img/info.png")));
		btnNewButton_1_1_1.setBounds(552, 227, 128, 128);
		contentPane.add(btnNewButton_1_1_1);

		panelUsuario = new JPanel();
		panelUsuario.setBackground(Color.GRAY);
		panelUsuario.setBounds(0, 388, 728, 53);
		contentPane.add(panelUsuario);
		panelUsuario.setLayout(null);

		lblData = new JLabel("");
		lblData.setForeground(SystemColor.window);
		lblData.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblData.setBounds(360, 11, 368, 31);
		panelUsuario.add(lblData);

		txtLoginPrincipal = new JLabel("");
		txtLoginPrincipal.setForeground(Color.WHITE);
		txtLoginPrincipal.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtLoginPrincipal.setBounds(28, 11, 289, 31);
		panelUsuario.add(txtLoginPrincipal);
	}
}
