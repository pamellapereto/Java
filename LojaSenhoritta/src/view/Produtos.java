package view;

import java.awt.EventQueue;

import javax.swing.JDialog;
import java.awt.Toolkit;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.toedter.calendar.JDateChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Cursor;

import javax.swing.JComboBox;
import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDayChooser;
import com.toedter.calendar.JMonthChooser;

import Atxy2k.CustomTextField.RestrictedTextField;
import model.DAO;
import net.proteanit.sql.DbUtils;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.border.EtchedBorder;

public class Produtos extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtProdBarcode;
	private JTextField txtProdCodigo;
	private JTextField txtBuscarFor;
	private JTextField txtForID;
	private JTable tblFornecedores;
	private JTextField txtProdNome;
	private JTextField txtProdFornecedor;
	private JTextField txtProdEstoque;
	private JTextField txtProdEstoqueMin;
	private JTextField txtProdSetor;
	private JTextField txtProdCusto;
	private JButton btnPesquisarProdID;
	private JTextArea txtProdDescricao;
	private JComboBox cboProdUnidade;
	private JDateChooser txtProdCadastro;
	// private JDateChooser txtProdValidade;
	private JButton btnAlterar;
	private JButton btnExcluir;
	private JButton btnAdicionar;
	private JComboBox cboProdCategoria;
	private JComboBox cboProdTamanho;
	private JTextField prodImagem1;
	private JTextField prodImagem2;
	private JTextField prodImagem3;
	private JTextField prodImagem4;
	private JTextField txtValorVenda;
	private JDateChooser txtProdInicioVenda;
	private JTextField txtProdCor;
	// private JDateChooser txtProdVenda;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Produtos dialog = new Produtos();
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
	public Produtos() {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Produtos.class.getResource("/img/logo.png")));
		setTitle("Produtos");
		setModal(true);
		setBounds(100, 100, 868, 727);
		getContentPane().setLayout(null);

		JLabel imgBarCode = new JLabel("");
		imgBarCode.setIcon(new ImageIcon(Produtos.class.getResource("/img/barcode.png")));
		imgBarCode.setBounds(24, 37, 80, 45);
		getContentPane().add(imgBarCode);

		JLabel lblNewLabel_1 = new JLabel("Codigo");
		lblNewLabel_1.setBounds(38, 108, 89, 14);
		getContentPane().add(lblNewLabel_1);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Consultar fornecedor", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(397, 45, 451, 189);
		getContentPane().add(panel);
		panel.setLayout(null);

		txtBuscarFor = new JTextField();
		txtBuscarFor.setForeground(Color.DARK_GRAY);
		txtBuscarFor.setText("Digite para pesquisar...                        ⌕");
		txtBuscarFor.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent event) {
				if (txtBuscarFor.getText().equals("Digite para pesquisar...                        ⌕")) {
					txtBuscarFor.setText("");
				}
			}

			@Override
			public void focusLost(FocusEvent event) {
				if (txtBuscarFor.getText().equals("")) {
					txtBuscarFor.setText("Digite para pesquisar...                        ⌕");
				}
			}
		});

		txtBuscarFor.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				// Evento digitacao
				buscarFornecedorTabela();

			}
		});

		txtBuscarFor.setBounds(10, 27, 225, 20);
		panel.add(txtBuscarFor);

		txtProdBarcode = new JTextField();

		txtProdBarcode.addKeyListener(new KeyAdapter() {

			@Override
			// Evento usado no leitor de codigo de barras
			public void keyPressed(KeyEvent teclaEnter) {
				if (teclaEnter.getKeyCode() == KeyEvent.VK_ENTER) {
					pesquisarProdutoPorBarcode();
				}

			}
		});

		txtProdBarcode.setBounds(128, 51, 137, 20);
		getContentPane().add(txtProdBarcode);

		txtProdCodigo = new JTextField();
		txtProdCodigo.setBounds(128, 105, 137, 20);
		getContentPane().add(txtProdCodigo);


		btnPesquisarProdID = new JButton("Pesquisar");
		btnPesquisarProdID.setEnabled(false);
		btnPesquisarProdID.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pesquisarProdutoPorCodigo();
			}
		});
		btnPesquisarProdID.setBounds(275, 104, 112, 23);
		getContentPane().add(btnPesquisarProdID);
		btnPesquisarProdID.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		JLabel lblNewLabel_3 = new JLabel("ID");
		lblNewLabel_3.setBounds(274, 30, 11, 14);
		panel.add(lblNewLabel_3);

		txtForID = new JTextField();
		txtForID.setBounds(295, 27, 136, 20);
		txtForID.setEditable(false);
		panel.add(txtForID);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 58, 421, 120);
		panel.add(scrollPane);

		tblFornecedores = new JTable();
		tblFornecedores.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setarCaixasTexto();
			}
		});
		scrollPane.setViewportView(tblFornecedores);

		txtProdNome = new JTextField();
		txtProdNome.setBounds(128, 150, 137, 20);
		getContentPane().add(txtProdNome);

		JLabel lblNewLabel_1_1 = new JLabel("Produto*");
		lblNewLabel_1_1.setBounds(38, 153, 89, 14);
		getContentPane().add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_1_1 = new JLabel("Descriçao*");
		lblNewLabel_1_1_1.setBounds(38, 285, 89, 14);
		getContentPane().add(lblNewLabel_1_1_1);

		txtProdDescricao = new JTextArea();
		txtProdDescricao.setBorder(new LineBorder(Color.LIGHT_GRAY));
		txtProdDescricao.setBounds(128, 281, 259, 20);
		getContentPane().add(txtProdDescricao);

		btnAdicionar = new JButton("");
		btnAdicionar.setBorderPainted(false);
		btnAdicionar.setContentAreaFilled(false);
		btnAdicionar.setIcon(new ImageIcon(Produtos.class.getResource("/img/createProd.png")));
		btnAdicionar.setToolTipText("Adicionar");
		btnAdicionar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAdicionar.setEnabled(false);
		btnAdicionar.setBounds(586, 622, 64, 64);
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (!txtProdBarcode.getText().isEmpty()) {
					adicionarProdutoComBarcode();
				}

				else {
					adicionarProdutoSemBarcode();
				}
			}
		});
		getContentPane().add(btnAdicionar);

		btnAlterar = new JButton("");
		btnAlterar.setIcon(new ImageIcon(Produtos.class.getResource("/img/updateProd.png")));
		btnAlterar.setToolTipText("Alterar");
		btnAlterar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAlterar.setEnabled(false);
		btnAlterar.setContentAreaFilled(false);
		btnAlterar.setBorderPainted(false);
		btnAlterar.setBounds(678, 622, 64, 64);

		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (!txtProdBarcode.getText().isEmpty()) {
					alterarProdutoComBarcode();
				}

				else {
					alterarProdutoSemBarcode();
				}
			}
		});

		getContentPane().add(btnAlterar);

		btnExcluir = new JButton("");
		btnExcluir.setIcon(new ImageIcon(Produtos.class.getResource("/img/deleteProd.png")));
		btnExcluir.setToolTipText("Excluir");
		btnExcluir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnExcluir.setEnabled(false);
		btnExcluir.setContentAreaFilled(false);
		btnExcluir.setBorderPainted(false);
		btnExcluir.setBounds(768, 622, 64, 64);

		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				excluirProduto();
			}
		});

		getContentPane().add(btnExcluir);

		txtProdFornecedor = new JTextField();
		txtProdFornecedor.setEditable(false);
		txtProdFornecedor.setBounds(125, 516, 265, 20);
		getContentPane().add(txtProdFornecedor);

		JLabel lblNewLabel_1_2 = new JLabel("Fabricante");
		lblNewLabel_1_2.setBounds(38, 518, 89, 14);
		getContentPane().add(lblNewLabel_1_2);

		JLabel lblNewLabel_1_2_1 = new JLabel("Estoque*");
		lblNewLabel_1_2_1.setBounds(38, 561, 89, 14);
		getContentPane().add(lblNewLabel_1_2_1);

		txtProdEstoque = new JTextField();
		txtProdEstoque.setBounds(125, 558, 68, 20);
		getContentPane().add(txtProdEstoque);

		JLabel lblNewLabel_1_2_1_1 = new JLabel("Estoque minimo*");
		lblNewLabel_1_2_1_1.setBounds(203, 561, 127, 14);
		getContentPane().add(lblNewLabel_1_2_1_1);

		txtProdEstoqueMin = new JTextField();
		txtProdEstoqueMin.setBounds(326, 558, 64, 20);
		getContentPane().add(txtProdEstoqueMin);

		JLabel lblNewLabel_1_1_2 = new JLabel("Setor");
		lblNewLabel_1_1_2.setBounds(38, 646, 46, 14);
		getContentPane().add(lblNewLabel_1_1_2);

		txtProdSetor = new JTextField();
		txtProdSetor.setBounds(125, 643, 142, 20);
		getContentPane().add(txtProdSetor);

		JLabel lblNewLabel_1_2_1_2 = new JLabel("Unidade*");
		lblNewLabel_1_2_1_2.setBounds(38, 602, 89, 14);
		getContentPane().add(lblNewLabel_1_2_1_2);

		JLabel lblNewLabel_1_2_1_3 = new JLabel("Custo*");
		lblNewLabel_1_2_1_3.setBounds(454, 519, 60, 14);
		getContentPane().add(lblNewLabel_1_2_1_3);

		txtProdCusto = new JTextField();
		txtProdCusto.addKeyListener(new KeyAdapter() {

			@Override
			public void keyTyped(KeyEvent e) {
				// txtProdCusto
				String caracteres = "0987654321.";

				// Limitar a somente numeros e ponto no campo Custo
				if (!caracteres.contains(e.getKeyChar() + "")) {
					e.consume();
				}

			}

		});
		txtProdCusto.setBounds(520, 516, 89, 20);
		getContentPane().add(txtProdCusto);
		cboProdUnidade = new JComboBox();
		cboProdUnidade.setModel(new DefaultComboBoxModel(new String[] { "", "UN", "CX", "PÇ", "KG", "PCT", "M" }));
		cboProdUnidade.setBounds(125, 598, 60, 22);
		getContentPane().add(cboProdUnidade);

		JLabel lblNewLabel_1_2_2 = new JLabel("Data de cadastro*");
		lblNewLabel_1_2_2.setBounds(454, 252, 147, 14);
		getContentPane().add(lblNewLabel_1_2_2);

		txtProdCadastro = new JDateChooser();
		txtProdCadastro.setBounds(600, 245, 137, 20);
		getContentPane().add(txtProdCadastro);

		JLabel lblNewLabel_1_2_3 = new JLabel("*Campos obrigatorios");
		lblNewLabel_1_2_3.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1_2_3.setBounds(407, 659, 169, 14);
		getContentPane().add(lblNewLabel_1_2_3);

		// JLabel lblNewLabel_1_2_2_1 = new JLabel("Validade");
		// lblNewLabel_1_2_2_1.setBounds(454, 331, 60, 14);
		// getContentPane().add(lblNewLabel_1_2_2_1);

		// txtProdValidade = new JDateChooser();
		// txtProdValidade.setBounds(520, 331, 124, 20);
		// getContentPane().add(txtProdValidade);

		JLabel lblNewLabel_1_3 = new JLabel("Cor*");
		lblNewLabel_1_3.setBounds(38, 197, 89, 14);
		getContentPane().add(lblNewLabel_1_3);

		txtProdCor = new JTextField();
		txtProdCor.setBounds(128, 194, 137, 20);
		getContentPane().add(txtProdCor);

		JLabel lblNewLabel_1_4 = new JLabel("Tamanho*");
		lblNewLabel_1_4.setBounds(38, 239, 89, 14);
		getContentPane().add(lblNewLabel_1_4);

		cboProdTamanho = new JComboBox();
		cboProdTamanho
				.setModel(new DefaultComboBoxModel(new String[] { "", "P", "M", "G", "GG", "EXG", "G1", "G2", "G3" }));
		cboProdTamanho.setBounds(128, 235, 57, 22);
		getContentPane().add(cboProdTamanho);

		JLabel lblNewLabel_1_5 = new JLabel("Categoria*");
		lblNewLabel_1_5.setBounds(38, 326, 89, 14);
		getContentPane().add(lblNewLabel_1_5);

		cboProdCategoria = new JComboBox();
		cboProdCategoria.setModel(
				new DefaultComboBoxModel(new String[] { "", "Blusas", "Saias", "Calças", "Vestidos", "Macacões" }));
		cboProdCategoria.setBounds(128, 322, 57, 22);
		getContentPane().add(cboProdCategoria);

		// txtProdVenda = new JDateChooser();
		// txtProdVenda.setBounds(560, 340, 137, 20);
		// getContentPane().add(txtProdVenda);

		JLabel lblNewLabel_1_1_1_1 = new JLabel("URL - Imagem 1*");
		lblNewLabel_1_1_1_1.setBounds(38, 377, 201, 14);
		getContentPane().add(lblNewLabel_1_1_1_1);

		prodImagem1 = new JTextField();
		prodImagem1.setBorder(new LineBorder(Color.LIGHT_GRAY));
		prodImagem1.setBounds(38, 397, 283, 20);
		getContentPane().add(prodImagem1);

		prodImagem2 = new JTextField();
		prodImagem2.setBorder(new LineBorder(Color.LIGHT_GRAY));
		prodImagem2.setBounds(38, 461, 283, 20);
		getContentPane().add(prodImagem2);

		JLabel lblNewLabel_1_1_1_1_1 = new JLabel("URL - Imagem 2");
		lblNewLabel_1_1_1_1_1.setBounds(38, 441, 216, 14);
		getContentPane().add(lblNewLabel_1_1_1_1_1);

		prodImagem4 = new JTextField();
		prodImagem4.setBorder(new LineBorder(Color.LIGHT_GRAY));
		prodImagem4.setBounds(454, 397, 283, 20);
		getContentPane().add(prodImagem4);

		JLabel lblNewLabel_1_1_1_1_2 = new JLabel("URL - Imagem 3");
		lblNewLabel_1_1_1_1_2.setBounds(454, 377, 196, 14);
		getContentPane().add(lblNewLabel_1_1_1_1_2);

		JLabel lblNewLabel_1_1_1_1_2_1 = new JLabel("URL - Imagem 4");
		lblNewLabel_1_1_1_1_2_1.setBounds(454, 441, 229, 14);
		getContentPane().add(lblNewLabel_1_1_1_1_2_1);

		prodImagem3 = new JTextField();
		prodImagem3.setBorder(new LineBorder(Color.LIGHT_GRAY));
		prodImagem3.setBounds(454, 461, 283, 20);
		getContentPane().add(prodImagem3);

		txtValorVenda = new JTextField();

		txtValorVenda.addKeyListener(new KeyAdapter() {

			@Override
			public void keyTyped(KeyEvent e) {
				// txtValorVenda
				String caracteres = "0987654321. ";

				// Limitar a somente numeros e ponto no campo Venda
				if (!caracteres.contains(e.getKeyChar() + "")) {
					e.consume();
				}

			}

		});
		txtValorVenda.setBounds(584, 568, 127, 20);
		getContentPane().add(txtValorVenda);

		JLabel lblNewLabel_1_2_1_1_1_1 = new JLabel("Preço de venda*");
		lblNewLabel_1_2_1_1_1_1.setBounds(454, 571, 137, 14);
		getContentPane().add(lblNewLabel_1_2_1_1_1_1);

		JLabel lblNewLabel_1_2_2_2 = new JLabel("Em venda a partir de*");
		lblNewLabel_1_2_2_2.setBounds(454, 295, 147, 14);
		getContentPane().add(lblNewLabel_1_2_2_2);

		txtProdInicioVenda = new JDateChooser();
		txtProdInicioVenda.setBounds(600, 292, 137, 20);
		getContentPane().add(txtProdInicioVenda);

		// Validacao com o uso da biblioteca Atxy2k

		// txtBuscarFor
		RestrictedTextField validartxtBuscarFor = new RestrictedTextField(txtBuscarFor);

		// Limitar a somente 100 caracteres no campo txtBuscarFor
		validartxtBuscarFor.setLimit(100);

		// txtProdBarcode
		RestrictedTextField validarBarcode = new RestrictedTextField(txtProdBarcode);

		// Restringir a somente numeros no campo Barcode
		validarBarcode.setOnlyNums(true);
		// Limitar a somente 13 numeros no campo Barcode
		validarBarcode.setLimit(13);

		// txtProdCodigo
		RestrictedTextField validarCodigo = new RestrictedTextField(txtProdCodigo);

		// Restringir a somente numeros no campo Codigo
		validarCodigo.setOnlyNums(true);
		// Limitar a somente 9 numeros no campo Codigo
		validarCodigo.setLimit(9);

		// txtProdNome
		RestrictedTextField validarNome = new RestrictedTextField(txtProdNome);

		// Restringir a somente letras no campo Nome
		validarNome.setOnlyText(true);
		// Aceitar espaco no campo Nome
		validarNome.setAcceptSpace(true);
		// Limitar a somente 60 caracteres no campo Nome
		validarNome.setLimit(60);

		RestrictedTextField validarCor = new RestrictedTextField(txtProdCor);
		// Restringir a somente letras no campo Cor
		validarCor.setOnlyText(true);
		// Aceitar espaco no campo Cor
		validarCor.setAcceptSpace(true);
		// Limitar a somente 35 caracteres no campo Cor
		validarCor.setLimit(35);

		// txtProdFornecedor
		RestrictedTextField validarFornecedor = new RestrictedTextField(txtProdFornecedor);

		// Limitar a somente 100 caracteres no campo Fornecedor
		validarFornecedor.setLimit(100);

		// txtProdEstoque
		RestrictedTextField validarEstoque = new RestrictedTextField(txtProdEstoque);

		// Restringir a somente numeros no campo Estoque
		validarEstoque.setOnlyNums(true);
		// Limitar a somente 5 numeros no campo Estoque
		validarEstoque.setLimit(5);

		// txtProdEstoqueMin
		RestrictedTextField validarEstoqueMin = new RestrictedTextField(txtProdEstoqueMin);

		// Restringir a somente numeros no campo Estoque Mínimo
		validarEstoqueMin.setOnlyNums(true);
		// Limitar a somente 5 numeros no campo Estoque Mínimo
		validarEstoqueMin.setLimit(5);

		// txtProdLocal
		RestrictedTextField validarLocal = new RestrictedTextField(txtProdSetor);

		// Limitar a somente 100 caracteres no campo Local
		validarLocal.setLimit(100);

		// txtProdCusto
		RestrictedTextField validarCusto = new RestrictedTextField(txtProdCusto);

		// Limitar a somente 10 numeros no campo Custo
		validarCusto.setLimit(10);

	} // Fim do construtor

	// Criar objeto para acessar o banco

	DAO dao = new DAO();

	/**
	 * Metodo responsavel pela pesquisa avancada do fornecedor usando o nome
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
				txtForID.setText(null);
				txtBuscarFor.setText(null);
				txtProdFornecedor.setText(null);
				btnPesquisarProdID.setEnabled(false);
				limparCampos();
				btnAdicionar.setEnabled(false);
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
		txtProdFornecedor.setText(tblFornecedores.getModel().getValueAt(setar, 1).toString());

		limparCampos();

		btnPesquisarProdID.setEnabled(true);
		txtProdCodigo.setEditable(true);
		btnAdicionar.setEnabled(true);
	}

	// Pesquisar produto por barcode
	private void pesquisarProdutoPorBarcode() {

		// Logica principal
		// Query (instrucao SQL)

		String read = "select * from produtos where barcode = ?;";

		try {
			// Estabelecer a conexao
			Connection con = dao.conectar();

			// Preparar a execucao da query
			PreparedStatement pst = con.prepareStatement(read);

			// Setar o argumento (ID)
			// Substituir o ? pelo conteudo da caixa de texto
			pst.setString(1, txtProdBarcode.getText());

			// Executar a query e exibir o resultado no formulario
			ResultSet rs = pst.executeQuery();

			// Validacao (existencia do produto)
			// rs.next() -> existencia de produto
			if (rs.next()) {
				// Preencher (setar) os campos do formulario
				txtProdCodigo.setText(rs.getString(1));
				txtProdNome.setText(rs.getString(3));
				txtProdCor.setText(rs.getString(4));
				cboProdTamanho.setSelectedItem(rs.getString(5));
				txtProdDescricao.setText(rs.getString(7));
				cboProdCategoria.setSelectedItem(rs.getString(8));
				txtProdFornecedor.setText(rs.getString(9));

				txtProdEstoque.setText(rs.getString(13));
				txtProdEstoqueMin.setText(rs.getString(14));
				cboProdUnidade.setSelectedItem(rs.getString(15));
				txtProdSetor.setText(rs.getString(16));
				txtProdCusto.setText(rs.getString(17));
				txtValorVenda.setText(rs.getString(19));

				prodImagem1.setText(rs.getString(20));
				prodImagem2.setText(rs.getString(21));
				prodImagem3.setText(rs.getString(22));
				prodImagem4.setText(rs.getString(23));

				txtBuscarFor.setText(rs.getString(9));
				txtForID.setText(rs.getString(24));

				btnAdicionar.setEnabled(false);
				btnAlterar.setEnabled(true);
				btnExcluir.setEnabled(true);

				// Formatar o valor do JCalendar para inserção correta no JText
				// JCalendar - formatação para exibição
				String setarDataCadastro = rs.getString(10);
				Date dataCadastroFormatada = new SimpleDateFormat("yyyy-MM-dd").parse(setarDataCadastro);
				txtProdCadastro.setDate(dataCadastroFormatada);

				String setarInicioVenda = rs.getString(11);
				Date dataInicioFormatada = new SimpleDateFormat("yyyy-MM-dd").parse(setarInicioVenda);
				txtProdInicioVenda.setDate(dataInicioFormatada);

				// String setarDataVenda = rs.getString(12);
				// Date dataVendaFormatada = new
				// SimpleDateFormat("yyyy-MM-dd").parse(setarDataVenda);
				// txtProdVenda.setDate(dataVendaFormatada);

			}

			else {
				// Validacao

				JOptionPane.showMessageDialog(null, "Produto nao cadastrado!");

				txtProdNome.requestFocus();
				limparCampos();
				btnAdicionar.setEnabled(true);
				txtProdCodigo.setEditable(false);
				btnPesquisarProdID.setEnabled(false);

			}

			// NUNCA esquecer de encerrar a conexao
			con.close();

		}

		// Tratar excecoes sempre que lidar com o banco
		catch (Exception e) {
			System.out.println(e);
		}

	}

	// Pesquisar produto por código
	private void pesquisarProdutoPorCodigo() {

		// Logica principal
		// Query (instrucao SQL)

		String read = "select * from produtos where codigo = ?;";

		try {
			// Estabelecer a conexao
			Connection con = dao.conectar();

			// Preparar a execucao da query
			PreparedStatement pst = con.prepareStatement(read);

			// Setar o argumento (ID)
			// Substituir o ? pelo conteudo da caixa de texto
			pst.setString(1, txtProdCodigo.getText());

			// Executar a query e exibir o resultado no formulario
			ResultSet rs = pst.executeQuery();

			// Validacao (existencia do produto)
			// rs.next() -> existencia de produto
			if (rs.next()) {
				// Preencher (setar) os campos do formulario
				txtProdBarcode.setText(rs.getString(2));
				txtProdNome.setText(rs.getString(3));
				txtProdCor.setText(rs.getString(4));
				cboProdTamanho.setSelectedItem(rs.getString(5));
				txtProdDescricao.setText(rs.getString(7));
				cboProdCategoria.setSelectedItem(rs.getString(8));
				txtProdFornecedor.setText(rs.getString(9));

				txtProdEstoque.setText(rs.getString(13));
				txtProdEstoqueMin.setText(rs.getString(14));
				cboProdUnidade.setSelectedItem(rs.getString(15));
				txtProdSetor.setText(rs.getString(16));
				txtProdCusto.setText(rs.getString(17));
				txtValorVenda.setText(rs.getString(19));

				prodImagem1.setText(rs.getString(20));
				prodImagem2.setText(rs.getString(21));
				prodImagem3.setText(rs.getString(22));
				prodImagem4.setText(rs.getString(23));

				txtBuscarFor.setText(rs.getString(9));
				txtForID.setText(rs.getString(24));

				btnAdicionar.setEnabled(false);
				btnAlterar.setEnabled(true);
				btnExcluir.setEnabled(true);

				// Formatar o valor do JCalendar para inserção correta no JText
				// JCalendar - formatação para exibição
				String setarDataCadastro = rs.getString(10);
				Date dataCadastroFormatada = new SimpleDateFormat("yyyy-MM-dd").parse(setarDataCadastro);
				txtProdCadastro.setDate(dataCadastroFormatada);

				String setarInicioVenda = rs.getString(11);
				Date dataInicioFormatada = new SimpleDateFormat("yyyy-MM-dd").parse(setarInicioVenda);
				txtProdInicioVenda.setDate(dataInicioFormatada);

				// String setarDataVenda = rs.getString(12);
				// Date dataVendaFormatada = new
				// SimpleDateFormat("yyyy-MM-dd").parse(setarDataVenda);
				// txtProdVenda.setDate(dataVendaFormatada);

			}

			else {
				// Validacao

				JOptionPane.showMessageDialog(null, "Produto nao cadastrado!");

				txtProdNome.requestFocus();
				limparCampos();
				btnAdicionar.setEnabled(true);
				txtProdCodigo.setEditable(false);
				btnPesquisarProdID.setEnabled(false);

			}

			// NUNCA esquecer de encerrar a conexao
			con.close();

		}

		// Tratar excecoes sempre que lidar com o banco
		catch (Exception e) {
			System.out.println(e);
		}

	}

	private void adicionarProdutoComBarcode() {

		if (txtProdCor.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha a cor do produto");
			txtProdCor.requestFocus();
		}

		else if (cboProdTamanho.getSelectedItem().equals("")) {
			JOptionPane.showMessageDialog(null, "Selecione o tamanho do produto");
			cboProdCategoria.requestFocus();
		}

		else if (cboProdCategoria.getSelectedItem().equals("")) {
			JOptionPane.showMessageDialog(null, "Selecione a categoria do produto");
			cboProdCategoria.requestFocus();
		}

		else if (prodImagem1.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha a url da imagem do produto");
			prodImagem1.requestFocus();
		}

		// Validacao do fornecedor do produto
		else if (txtBuscarFor.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Pesquise pelo fornecedor do produto");
			txtBuscarFor.requestFocus();
		}

		// Validacao do nome do produto
		else if (txtProdNome.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o nome do produto");
			txtProdNome.requestFocus();
		}

		// Validacao do estoque do produto
		else if (txtProdEstoque.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o estoque do produto");
			txtProdEstoque.requestFocus();
		}

		// Validacao do estoque mínimo do produto
		else if (txtProdEstoqueMin.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o estoque minimo do produto");
			txtProdEstoqueMin.requestFocus();
		}

		// Validacao do custo do produto
		else if (txtProdCusto.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o custo do produto");
			txtProdCusto.requestFocus();
		}

		else if (txtValorVenda.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha preço de venda do produto");
			txtValorVenda.requestFocus();
		}

		// Validacao da descricao do produto
		else if (txtProdDescricao.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha a descriçao do produto");
			txtProdDescricao.requestFocus();
		}

		// Validacao da unidade do produto
		else if (cboProdUnidade.getSelectedItem().equals("")) {
			JOptionPane.showMessageDialog(null, "Selecione a unidade do produto");
			cboProdUnidade.requestFocus();
		}

		// Validação da entrada do produto
		// else if (txtProdEntrada.setDate == null) {
		// JOptionPane.showMessageDialog(null, "Preencha a data de entrada do produto");
		// txtProdEntrada.requestFocus();

		// }

		else {
			// Logica principal
			String create = "insert into produtos (barcode, produto, cor, tamanho, descricao, categoria, fabricante, datacad, iniciovenda, estoque, estoquemin, unidade, localizacao, custo, venda, foto1, foto2, foto3, foto4, idfor) values  (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

			try {
				// Estabelecer a conexao
				Connection con = dao.conectar();

				// Preparar a execucao da query
				PreparedStatement pst = con.prepareStatement(create);

				// Substituir as interrogacoes pelo conteudo das caixas de texto
				pst.setString(1, txtProdBarcode.getText());
				pst.setString(2, txtProdNome.getText());
				pst.setString(3, txtProdCor.getText());
				pst.setString(4, cboProdTamanho.getSelectedItem().toString());
				pst.setString(5, txtProdDescricao.getText());
				pst.setString(6, cboProdCategoria.getSelectedItem().toString());
				pst.setString(7, txtProdFornecedor.getText());

				pst.setString(10, txtProdEstoque.getText());
				pst.setString(11, txtProdEstoqueMin.getText());
				pst.setString(12, cboProdUnidade.getSelectedItem().toString());
				pst.setString(13, txtProdSetor.getText());
				pst.setString(14, txtProdCusto.getText());

				pst.setString(15, txtValorVenda.getText());

				pst.setString(16, prodImagem1.getText());

				pst.setString(17, prodImagem2.getText());

				pst.setString(18, prodImagem3.getText());

				pst.setString(19, prodImagem4.getText());

				pst.setString(20, txtForID.getText());

				// Formatar o valor do JCalendar para inserção correta no banco
				SimpleDateFormat formatadorCadastro = new SimpleDateFormat("yyyyMMdd");
				String dataCadastroFormatada = formatadorCadastro.format(txtProdCadastro.getDate());
				pst.setString(8, dataCadastroFormatada);

				SimpleDateFormat formatadorInicioVenda = new SimpleDateFormat("yyyyMMdd");
				String dataInicioVendaFormatada = formatadorInicioVenda.format(txtProdInicioVenda.getDate());
				pst.setString(9, dataInicioVendaFormatada);

				// SimpleDateFormat formatadorVenda = new SimpleDateFormat("yyyyMMdd");
				// String dataVendaFormatada = formatadorVenda.format(txtProdVenda.getDate());
				// pst.setString(10, dataVendaFormatada);

				// Executar a query e inserir o produto no banco
				pst.executeUpdate();

				// Exibir mensagem ao cadastrar fornecedor com sucesso no banco
				JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!");

				txtBuscarFor.requestFocus();
				limparCampos();
				btnAdicionar.setEnabled(false);
				txtForID.setText(null);
				txtBuscarFor.setText(null);
				txtProdFornecedor.setText(null);
				btnPesquisarProdID.setEnabled(false);

				// NUNCA esquecer de encerrar a conexao
				con.close();

			}

			catch (SQLIntegrityConstraintViolationException ex) {

				JOptionPane.showMessageDialog(null,
						"Ocorreu um erro. \nVerifique novamente o codigo de barras do produto.");
				txtProdBarcode.requestFocus();

			} catch (Exception e) {
				System.out.println(e);
			}

		}

	}

	private void adicionarProdutoSemBarcode() {
		if (txtProdCor.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha a cor do produto");
			txtProdCor.requestFocus();
		}

		else if (cboProdTamanho.getSelectedItem().equals("")) {
			JOptionPane.showMessageDialog(null, "Selecione o tamanho do produto");
			cboProdCategoria.requestFocus();
		}

		else if (cboProdCategoria.getSelectedItem().equals("")) {
			JOptionPane.showMessageDialog(null, "Selecione a categoria do produto");
			cboProdCategoria.requestFocus();
		}

		else if (prodImagem1.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha a url da imagem do produto");
			prodImagem1.requestFocus();
		}

		// Validacao do fornecedor do produto
		else if (txtBuscarFor.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Pesquise pelo fornecedor do produto");
			txtBuscarFor.requestFocus();
		}

		// Validacao do nome do produto
		else if (txtProdNome.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o nome do produto");
			txtProdNome.requestFocus();
		}

		// Validacao do estoque do produto
		else if (txtProdEstoque.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o estoque do produto");
			txtProdEstoque.requestFocus();
		}

		// Validacao do estoque mínimo do produto
		else if (txtProdEstoqueMin.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o estoque minimo do produto");
			txtProdEstoqueMin.requestFocus();
		}

		// Validacao do custo do produto
		else if (txtProdCusto.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o custo do produto");
			txtProdCusto.requestFocus();
		}

		else if (txtValorVenda.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha preço de venda do produto");
			txtValorVenda.requestFocus();
		}

		// Validacao da descricao do produto
		else if (txtProdDescricao.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha a descriçao do produto");
			txtProdDescricao.requestFocus();
		}

		// Validacao da unidade do produto
		else if (cboProdUnidade.getSelectedItem().equals("")) {
			JOptionPane.showMessageDialog(null, "Selecione a unidade do produto");
			cboProdUnidade.requestFocus();
		}

		// Validação da entrada do produto
		// else if (txtProdEntrada.setDate == null) {
		// JOptionPane.showMessageDialog(null, "Preencha a data de entrada do produto");
		// txtProdEntrada.requestFocus();

		// }

		else {
			// Logica principal
			String create = "insert into produtos (produto, cor, tamanho, descricao, categoria, fabricante, datacad, iniciovenda, estoque, estoquemin, unidade, localizacao, custo, venda, foto1, foto2, foto3, foto4, idfor) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

			try {
				// Estabelecer a conexao
				Connection con = dao.conectar();

				// Preparar a execucao da query
				PreparedStatement pst = con.prepareStatement(create);

				// Substituir as interrogacoes pelo conteudo das caixas de texto
				pst.setString(1, txtProdNome.getText());
				pst.setString(2, txtProdCor.getText());
				pst.setString(3, cboProdTamanho.getSelectedItem().toString());
				pst.setString(4, txtProdDescricao.getText());
				pst.setString(5, cboProdCategoria.getSelectedItem().toString());
				pst.setString(6, txtProdFornecedor.getText());

				pst.setString(9, txtProdEstoque.getText());
				pst.setString(10, txtProdEstoqueMin.getText());
				pst.setString(11, cboProdUnidade.getSelectedItem().toString());
				pst.setString(12, txtProdSetor.getText());
				pst.setString(13, txtProdCusto.getText());

				pst.setString(14, txtValorVenda.getText());

				pst.setString(15, prodImagem1.getText());

				pst.setString(16, prodImagem2.getText());

				pst.setString(17, prodImagem3.getText());

				pst.setString(18, prodImagem4.getText());

				pst.setString(19, txtForID.getText());

				// Formatar o valor do JCalendar para inserção correta no banco
				SimpleDateFormat formatadorCadastro = new SimpleDateFormat("yyyyMMdd");
				String dataCadastroFormatada = formatadorCadastro.format(txtProdCadastro.getDate());
				pst.setString(7, dataCadastroFormatada);

				SimpleDateFormat formatadorInicioVenda = new SimpleDateFormat("yyyyMMdd");
				String dataInicioVendaFormatada = formatadorInicioVenda.format(txtProdInicioVenda.getDate());
				pst.setString(8, dataInicioVendaFormatada);

				// SimpleDateFormat formatadorVenda = new SimpleDateFormat("yyyyMMdd");
				// String dataVendaFormatada = formatadorVenda.format(txtProdVenda.getDate());
				// pst.setString(9, dataVendaFormatada);

				// Executar a query e inserir o produto no banco
				pst.executeUpdate();

				// Exibir mensagem ao cadastrar fornecedor com sucesso no banco
				JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!");

				
				
				txtBuscarFor.requestFocus();
				limparCampos();
				btnAdicionar.setEnabled(false);
				txtForID.setText(null);
				txtBuscarFor.setText(null);
				txtProdFornecedor.setText(null);
				btnPesquisarProdID.setEnabled(false);
				
				// NUNCA esquecer de encerrar a conexao
				con.close();

			}

			catch (SQLIntegrityConstraintViolationException ex) {

				JOptionPane.showMessageDialog(null,
						"Ocorreu um erro. \nVerifique novamente as informações acerca do produto.");
				txtProdNome.requestFocus();

			} catch (Exception e) {
				System.out.println(e);
			}

		}

	}

	private void alterarProdutoComBarcode() {
		if (txtProdCor.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha a cor do produto");
			txtProdCor.requestFocus();
		}

		else if (cboProdTamanho.getSelectedItem().equals("")) {
			JOptionPane.showMessageDialog(null, "Selecione o tamanho do produto");
			cboProdCategoria.requestFocus();
		}

		else if (cboProdCategoria.getSelectedItem().equals("")) {
			JOptionPane.showMessageDialog(null, "Selecione a categoria do produto");
			cboProdCategoria.requestFocus();
		}

		else if (prodImagem1.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha a url da imagem do produto");
			prodImagem1.requestFocus();
		}

		// Validacao do fornecedor do produto
		else if (txtBuscarFor.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Pesquise pelo fornecedor do produto");
			txtBuscarFor.requestFocus();
		}

		// Validacao do nome do produto
		else if (txtProdNome.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o nome do produto");
			txtProdNome.requestFocus();
		}

		// Validacao do estoque do produto
		else if (txtProdEstoque.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o estoque do produto");
			txtProdEstoque.requestFocus();
		}

		// Validacao do estoque mínimo do produto
		else if (txtProdEstoqueMin.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o estoque minimo do produto");
			txtProdEstoqueMin.requestFocus();
		}

		// Validacao do custo do produto
		else if (txtProdCusto.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o custo do produto");
			txtProdCusto.requestFocus();
		}

		else if (txtValorVenda.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha preço de venda do produto");
			txtValorVenda.requestFocus();
		}

		// Validacao da descricao do produto
		else if (txtProdDescricao.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha a descriçao do produto");
			txtProdDescricao.requestFocus();
		}

		// Validacao da unidade do produto
		else if (cboProdUnidade.getSelectedItem().equals("")) {
			JOptionPane.showMessageDialog(null, "Selecione a unidade do produto");
			cboProdUnidade.requestFocus();
		}

		// Validação da entrada do produto
		// else if (txtProdEntrada.setDate == null) {
		// JOptionPane.showMessageDialog(null, "Preencha a data de entrada do produto");
		// txtProdEntrada.requestFocus();

		// }

		else {
			// Logica principal
			String update = "update produtos set barcode = ?, produto = ?, cor = ?, tamanho = ?, descricao = ?, categoria = ?, fabricante = ?, datacad = ?, iniciovenda = ?, estoque = ?, estoquemin = ?, unidade = ?, localizacao = ?, custo = ?, venda = ?, foto1 = ?, foto2 = ?, foto3 = ?, foto4 = ?, idfor = ? where codigo = ?;";

			try {
				// Estabelecer a conexao
				Connection con = dao.conectar();

				// Preparar a execucao da query
				PreparedStatement pst = con.prepareStatement(update);

				// Substituir as interrogacoes pelo conteudo das caixas de texto
				pst.setString(1, txtProdBarcode.getText());
				pst.setString(2, txtProdNome.getText());
				pst.setString(3, txtProdCor.getText());
				pst.setString(4, cboProdTamanho.getSelectedItem().toString());
				pst.setString(5, txtProdDescricao.getText());
				pst.setString(6, cboProdCategoria.getSelectedItem().toString());
				pst.setString(7, txtProdFornecedor.getText());

				pst.setString(10, txtProdEstoque.getText());
				pst.setString(11, txtProdEstoqueMin.getText());
				pst.setString(12, cboProdUnidade.getSelectedItem().toString());

				pst.setString(13, txtProdSetor.getText());
				pst.setString(14, txtProdCusto.getText());

				pst.setString(15, txtValorVenda.getText());

				pst.setString(16, prodImagem1.getText());
				pst.setString(17, prodImagem2.getText());
				pst.setString(18, prodImagem3.getText());
				pst.setString(19, prodImagem4.getText());

				pst.setString(20, txtForID.getText());

				pst.setString(21, txtProdCodigo.getText());

				// Formatar o valor do JCalendar para inserção correta no banco
				SimpleDateFormat formatadorCadastro = new SimpleDateFormat("yyyyMMdd");
				String dataCadastroFormatada = formatadorCadastro.format(txtProdCadastro.getDate());
				pst.setString(8, dataCadastroFormatada);

				SimpleDateFormat formatadorInicioVenda = new SimpleDateFormat("yyyyMMdd");
				String dataInicioVendaFormatada = formatadorInicioVenda.format(txtProdInicioVenda.getDate());
				pst.setString(9, dataInicioVendaFormatada);

				// Executar a query e alterar o produto no banco
				pst.executeUpdate();

				// Exibir mensagem ao alterar produto cadastrado com sucesso no banco
				JOptionPane.showMessageDialog(null, "Dados do produto atualizados com sucesso!");

				
				txtBuscarFor.requestFocus();
				limparCampos();
				btnAdicionar.setEnabled(false);
				txtForID.setText(null);
				txtBuscarFor.setText(null);
				txtProdFornecedor.setText(null);
				btnPesquisarProdID.setEnabled(false);
				
				// NUNCA esquecer de encerrar a conexao
				con.close();

			}

			catch (SQLIntegrityConstraintViolationException ex) {

				JOptionPane.showMessageDialog(null,
						"Ocorreu um erro. \nVerifique novamente o codigo de barras do produto.");
				txtProdBarcode.requestFocus();

			}

			catch (Exception e) {
				System.out.println(e);
			}

		}
	}

	private void alterarProdutoSemBarcode() {
		if (txtProdCor.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha a cor do produto");
			txtProdCor.requestFocus();
		}

		else if (cboProdTamanho.getSelectedItem().equals("")) {
			JOptionPane.showMessageDialog(null, "Selecione o tamanho do produto");
			cboProdCategoria.requestFocus();
		}

		else if (cboProdCategoria.getSelectedItem().equals("")) {
			JOptionPane.showMessageDialog(null, "Selecione a categoria do produto");
			cboProdCategoria.requestFocus();
		}

		else if (prodImagem1.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha a url da imagem do produto");
			prodImagem1.requestFocus();
		}

		// Validacao do fornecedor do produto
		else if (txtBuscarFor.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Pesquise pelo fornecedor do produto");
			txtBuscarFor.requestFocus();
		}

		// Validacao do nome do produto
		else if (txtProdNome.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o nome do produto");
			txtProdNome.requestFocus();
		}

		// Validacao do estoque do produto
		else if (txtProdEstoque.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o estoque do produto");
			txtProdEstoque.requestFocus();
		}

		// Validacao do estoque mínimo do produto
		else if (txtProdEstoqueMin.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o estoque minimo do produto");
			txtProdEstoqueMin.requestFocus();
		}

		// Validacao do custo do produto
		else if (txtProdCusto.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o custo do produto");
			txtProdCusto.requestFocus();
		}

		else if (txtValorVenda.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha preço de venda do produto");
			txtValorVenda.requestFocus();
		}

		// Validacao da descricao do produto
		else if (txtProdDescricao.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha a descriçao do produto");
			txtProdDescricao.requestFocus();
		}

		// Validacao da unidade do produto
		else if (cboProdUnidade.getSelectedItem().equals("")) {
			JOptionPane.showMessageDialog(null, "Selecione a unidade do produto");
			cboProdUnidade.requestFocus();
		}

		// Validação da entrada do produto
		// else if (txtProdEntrada.setDate == null) {
		// JOptionPane.showMessageDialog(null, "Preencha a data de entrada do produto");
		// txtProdEntrada.requestFocus();

		// }

		else {
			// Logica principal
			String update = "update produtos set produto = ?, cor = ?, tamanho = ?, descricao = ?, categoria = ?, fabricante = ?, datacad = ?, iniciovenda = ?, estoque = ?, estoquemin = ?, unidade = ?, localizacao = ?, custo = ?, venda = ?, foto1 = ?, foto2 = ?, foto3 = ?, foto4 = ?, idfor = ? where codigo = ?;";

			try {
				// Estabelecer a conexao
				Connection con = dao.conectar();

				// Preparar a execucao da query
				PreparedStatement pst = con.prepareStatement(update);

				// Substituir as interrogacoes pelo conteudo das caixas de texto
				pst.setString(1, txtProdNome.getText());
				pst.setString(2, txtProdCor.getText());
				pst.setString(3, cboProdTamanho.getSelectedItem().toString());
				pst.setString(4, txtProdDescricao.getText());
				pst.setString(5, cboProdCategoria.getSelectedItem().toString());
				pst.setString(6, txtProdFornecedor.getText());

				pst.setString(9, txtProdEstoque.getText());
				pst.setString(10, txtProdEstoqueMin.getText());
				pst.setString(11, cboProdUnidade.getSelectedItem().toString());

				pst.setString(12, txtProdSetor.getText());
				pst.setString(13, txtProdCusto.getText());

				pst.setString(14, txtValorVenda.getText());

				pst.setString(15, prodImagem1.getText());
				pst.setString(16, prodImagem2.getText());
				pst.setString(17, prodImagem3.getText());
				pst.setString(18, prodImagem4.getText());

				pst.setString(19, txtForID.getText());

				pst.setString(20, txtProdCodigo.getText());

				// Formatar o valor do JCalendar para inserção correta no banco
				SimpleDateFormat formatadorCadastro = new SimpleDateFormat("yyyyMMdd");
				String dataCadastroFormatada = formatadorCadastro.format(txtProdCadastro.getDate());
				pst.setString(7, dataCadastroFormatada);

				SimpleDateFormat formatadorInicioVenda = new SimpleDateFormat("yyyyMMdd");
				String dataInicioVendaFormatada = formatadorInicioVenda.format(txtProdInicioVenda.getDate());
				pst.setString(8, dataInicioVendaFormatada);

				// Executar a query e alterar o produto no banco
				pst.executeUpdate();

				// Exibir mensagem ao alterar produto cadastrado com sucesso no banco
				JOptionPane.showMessageDialog(null, "Dados do produto atualizados com sucesso!");

				txtBuscarFor.requestFocus();
				limparCampos();
				btnAdicionar.setEnabled(false);
				txtForID.setText(null);
				txtBuscarFor.setText(null);
				txtProdFornecedor.setText(null);
				btnPesquisarProdID.setEnabled(false);

				// NUNCA esquecer de encerrar a conexao
				con.close();

			}

			catch (SQLIntegrityConstraintViolationException ex) {

				JOptionPane.showMessageDialog(null,
						"Ocorreu um erro. \nVerifique novamente as informações acerca do produto.");
				txtProdNome.requestFocus();

			}

			catch (Exception e) {
				System.out.println(e);
			}

		}
	}

	private void excluirProduto() {

		// Validacao
		int confirma = JOptionPane.showConfirmDialog(null, "Confirma a exclusao do produto?", "Atençao!",
				JOptionPane.YES_NO_OPTION);

		// Logica principal
		if (confirma == JOptionPane.YES_OPTION) {
			String delete = "delete from produtos where codigo = ?;";

			try {
				// Estabelecer a conexao
				Connection con = dao.conectar();

				// Preparar a execucao da query
				PreparedStatement pst = con.prepareStatement(delete);

				// Substituir o ? pelo conteudo da caixa de texto
				pst.setString(1, txtProdCodigo.getText());

				// Executar a query e deletar o produto no banco
				pst.executeUpdate();

				txtBuscarFor.requestFocus();
				limparCampos();
				btnAdicionar.setEnabled(false);
				txtForID.setText(null);
				txtBuscarFor.setText(null);
				txtProdFornecedor.setText(null);
				btnPesquisarProdID.setEnabled(false);
				
				// Exibir mensagem ao deletar fornecedor
				JOptionPane.showMessageDialog(null, "Produto excluido com sucesso!");

				// NUNCA esquecer de encerrar a conexao
				con.close();

			}

			catch (Exception e) {
				System.out.println(e);
			}

		}

	}

	private void limparCampos() {
		
		txtProdBarcode.setText(null);
		txtProdNome.setText(null);
		txtProdCor.setText(null);
		cboProdTamanho.setSelectedItem("");
		cboProdCategoria.setSelectedItem("");
		txtProdDescricao.setText(null);
		//txtProdFornecedor.setText(null);
		txtProdEstoque.setText(null);
		txtProdEstoqueMin.setText(null);
		txtProdCusto.setText(null);
		txtValorVenda.setText(null);
		txtProdSetor.setText(null);
		txtProdCodigo.setText(null);		
		cboProdUnidade.setSelectedItem("");
		//txtForID.setText(null);
		//txtBuscarFor.setText(null);
		prodImagem1.setText(null);
		prodImagem2.setText(null);
		prodImagem3.setText(null);
		prodImagem4.setText(null);
		txtProdCadastro.setDate(null);
		txtProdInicioVenda.setDate(null);
		((DefaultTableModel) tblFornecedores.getModel()).setRowCount(0);
		btnAdicionar.setEnabled(true);
		btnAlterar.setEnabled(false);
		btnExcluir.setEnabled(false);

	}
} // Fim do código
