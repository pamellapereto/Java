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
	private JTextField txtProdLucro;
	private JButton btnPesquisarProdID;
	private JTextArea txtProdDescricao;
	private JComboBox cboProdUnidade;
	private JDateChooser txtProdEntrada;
	// private JDateChooser txtProdValidade;

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
		setBounds(100, 100, 895, 649);
		getContentPane().setLayout(null);

		JLabel imgBarCode = new JLabel("");
		imgBarCode.setIcon(new ImageIcon(Produtos.class.getResource("/img/barcode.png")));
		imgBarCode.setBounds(24, 37, 80, 45);
		getContentPane().add(imgBarCode);

		JLabel lblNewLabel_1 = new JLabel("C\u00F3digo");
		lblNewLabel_1.setBounds(38, 117, 46, 14);
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
		txtBuscarFor.setText("Digite para pesquisar...        ⌕");
		txtBuscarFor.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent event) {
				if (txtBuscarFor.getText().equals("Digite para pesquisar...        ⌕")) {
					txtBuscarFor.setText("");
				}
			}

			@Override
			public void focusLost(FocusEvent event) {
				if (txtBuscarFor.getText().equals("")) {
					txtBuscarFor.setText("Digite para pesquisar...        ⌕");
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

		txtBuscarFor.setBounds(10, 27, 166, 20);
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

		txtProdBarcode.setBounds(104, 51, 161, 20);
		getContentPane().add(txtProdBarcode);

		txtProdCodigo = new JTextField();
		txtProdCodigo.setBounds(104, 114, 161, 20);
		getContentPane().add(txtProdCodigo);

		btnPesquisarProdID = new JButton("Pesquisar");
		btnPesquisarProdID.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pesquisarProdutoPorCodigo();
			}
		});
		btnPesquisarProdID.setBounds(275, 113, 112, 23);
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
		txtProdNome.setBounds(104, 170, 161, 20);
		getContentPane().add(txtProdNome);

		JLabel lblNewLabel_1_1 = new JLabel("Produto");
		lblNewLabel_1_1.setBounds(38, 173, 46, 14);
		getContentPane().add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_1_1 = new JLabel("Descri\u00E7\u00E3o");
		lblNewLabel_1_1_1.setBounds(38, 227, 66, 14);
		getContentPane().add(lblNewLabel_1_1_1);

		txtProdDescricao = new JTextArea();
		txtProdDescricao.setBorder(new LineBorder(Color.LIGHT_GRAY));
		txtProdDescricao.setBounds(104, 222, 283, 103);
		getContentPane().add(txtProdDescricao);

		btnAdicionar = new JButton("");
		btnAdicionar.setBorderPainted(false);
		btnAdicionar.setContentAreaFilled(false);
		btnAdicionar.setIcon(new ImageIcon(Produtos.class.getResource("/img/createProd.png")));
		btnAdicionar.setToolTipText("Adicionar");
		btnAdicionar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAdicionar.setEnabled(false);
		btnAdicionar.setBounds(587, 472, 64, 64);
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
		btnAlterar.setBounds(679, 472, 64, 64);

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
		btnExcluir.setBounds(769, 472, 64, 64);

		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				excluirProduto();
			}
		});

		getContentPane().add(btnExcluir);

		txtProdFornecedor = new JTextField();
		txtProdFornecedor.setEditable(false);
		txtProdFornecedor.setBounds(104, 355, 286, 20);
		getContentPane().add(txtProdFornecedor);

		JLabel lblNewLabel_1_2 = new JLabel("Fabricante");
		lblNewLabel_1_2.setBounds(38, 358, 60, 14);
		getContentPane().add(lblNewLabel_1_2);

		JLabel lblNewLabel_1_2_1 = new JLabel("Estoque");
		lblNewLabel_1_2_1.setBounds(38, 405, 60, 14);
		getContentPane().add(lblNewLabel_1_2_1);

		txtProdEstoque = new JTextField();
		txtProdEstoque.setBounds(104, 402, 89, 20);
		getContentPane().add(txtProdEstoque);

		JLabel lblNewLabel_1_2_1_1 = new JLabel("Estoque m\u00EDnimo");
		lblNewLabel_1_2_1_1.setBounds(203, 405, 100, 14);
		getContentPane().add(lblNewLabel_1_2_1_1);

		txtProdEstoqueMin = new JTextField();
		txtProdEstoqueMin.setBounds(301, 402, 89, 20);
		getContentPane().add(txtProdEstoqueMin);

		JLabel lblNewLabel_1_1_2 = new JLabel("Setor");
		lblNewLabel_1_1_2.setBounds(38, 509, 46, 14);
		getContentPane().add(lblNewLabel_1_1_2);

		txtProdSetor = new JTextField();
		txtProdSetor.setBounds(104, 506, 163, 20);
		getContentPane().add(txtProdSetor);

		JLabel lblNewLabel_1_2_1_2 = new JLabel("Unidade");
		lblNewLabel_1_2_1_2.setBounds(38, 457, 60, 14);
		getContentPane().add(lblNewLabel_1_2_1_2);

		JLabel lblNewLabel_1_2_1_3 = new JLabel("Custo");
		lblNewLabel_1_2_1_3.setBounds(454, 402, 60, 14);
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
		txtProdCusto.setBounds(520, 399, 89, 20);
		getContentPane().add(txtProdCusto);

		JLabel lblNewLabel_1_2_1_1_1 = new JLabel("Lucro(%)");
		lblNewLabel_1_2_1_1_1.setBounds(641, 402, 80, 14);
		getContentPane().add(lblNewLabel_1_2_1_1_1);

		txtProdLucro = new JTextField();
		txtProdLucro.setBounds(717, 399, 89, 20);
		getContentPane().add(txtProdLucro);

		cboProdUnidade = new JComboBox();
		cboProdUnidade.setModel(new DefaultComboBoxModel(new String[] { "", "UN", "CX", "PÇ", "KG", "PCT", "M" }));
		cboProdUnidade.setBounds(104, 453, 81, 22);
		getContentPane().add(cboProdUnidade);

		JLabel lblNewLabel_1_2_2 = new JLabel("Entrada");
		lblNewLabel_1_2_2.setBounds(454, 281, 60, 14);
		getContentPane().add(lblNewLabel_1_2_2);

		txtProdEntrada = new JDateChooser();
		txtProdEntrada.setBounds(507, 281, 137, 20);
		getContentPane().add(txtProdEntrada);

		JLabel lblNewLabel_1_2_3 = new JLabel("Exceto o código de barras, todos os campos acima são obrigatórios.");
		lblNewLabel_1_2_3.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1_2_3.setBounds(38, 568, 422, 14);
		getContentPane().add(lblNewLabel_1_2_3);

		// JLabel lblNewLabel_1_2_2_1 = new JLabel("Validade");
		// lblNewLabel_1_2_2_1.setBounds(454, 331, 60, 14);
		// getContentPane().add(lblNewLabel_1_2_2_1);

		// txtProdValidade = new JDateChooser();
		// txtProdValidade.setBounds(520, 331, 124, 20);
		// getContentPane().add(txtProdValidade);

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
		
		// txtProdLucro
		RestrictedTextField validarLucro = new RestrictedTextField(txtProdLucro);

		// Limitar a somente 10 numeros no campo Lucro
		validarLucro.setLimit(10);


	} // Fim do construtor

	// Criar objeto para acessar o banco

	DAO dao = new DAO();
	private JButton btnAlterar;
	private JButton btnExcluir;
	private JButton btnAdicionar;

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
				limparCampos();
				btnAdicionar.setEnabled(true);
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
				txtProdDescricao.setText(rs.getString(4));
				txtProdFornecedor.setText(rs.getString(5));

				// Formatar o valor do JCalendar para inserção correta no JText
				// JCalendar - formatação para exibição
				String setarDataEntrada = rs.getString(6);
				Date dataEntradaFormatada = new SimpleDateFormat("yyyy-MM-dd").parse(setarDataEntrada);
				txtProdEntrada.setDate(dataEntradaFormatada);

				txtProdEstoque.setText(rs.getString(7));
				txtProdEstoqueMin.setText(rs.getString(8));
				cboProdUnidade.setSelectedItem(rs.getString(9));
				txtProdSetor.setText(rs.getString(10));
				txtProdCusto.setText(rs.getString(11));
				txtProdLucro.setText(rs.getString(12));
				txtBuscarFor.setText(rs.getString(5));
				txtForID.setText(rs.getString(13));
				btnAdicionar.setEnabled(false);
				btnAlterar.setEnabled(true);
				btnExcluir.setEnabled(true);

			}

			else {
				// Validacao

				JOptionPane.showMessageDialog(null, "Produto não cadastrado!");

				txtProdBarcode.requestFocus();
				limparCampos();
				btnAdicionar.setEnabled(true);

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
				txtProdDescricao.setText(rs.getString(4));
				txtProdFornecedor.setText(rs.getString(5));

				// Formatar o valor do JCalendar para inserção correta no JText
				// JCalendar - formatação para exibição
				String setarDataEntrada = rs.getString(6);
				Date dataEntradaFormatada = new SimpleDateFormat("yyyy-MM-dd").parse(setarDataEntrada);
				txtProdEntrada.setDate(dataEntradaFormatada);

				txtProdEstoque.setText(rs.getString(7));
				txtProdEstoqueMin.setText(rs.getString(8));
				cboProdUnidade.setSelectedItem(rs.getString(9));
				txtProdSetor.setText(rs.getString(10));
				txtProdCusto.setText(rs.getString(11));
				txtProdLucro.setText(rs.getString(12));
				txtBuscarFor.setText(rs.getString(5));
				txtForID.setText(rs.getString(13));
				btnAdicionar.setEnabled(false);
				btnAlterar.setEnabled(true);
				btnExcluir.setEnabled(true);

			}

			else {
				// Validacao

				JOptionPane.showMessageDialog(null, "Produto não cadastrado!");

				txtProdCodigo.requestFocus();
				limparCampos();
				btnAdicionar.setEnabled(true);

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

		// Validacao do fornecedor do produto
		if (txtBuscarFor.getText().isEmpty()) {
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
			JOptionPane.showMessageDialog(null, "Preencha o estoque mínimo do produto");
			txtProdEstoqueMin.requestFocus();
		}

		// Validacao do setor do produto
		else if (txtProdSetor.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o setor do produto");
			txtProdSetor.requestFocus();
		}

		// Validacao do custo do produto
		else if (txtProdCusto.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o custo do produto");
			txtProdCusto.requestFocus();
		}

		// Validacao do lucro do produto
		else if (txtProdLucro.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o lucro do produto");
			txtProdLucro.requestFocus();
		}

		// Validacao da descricao do produto
		else if (txtProdDescricao.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha a descrição do produto");
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
			String create = "insert into produtos (barcode, produto, descricao, fabricante, datacad, estoque, estoquemin, unidade, localizacao, custo, lucro, idfor) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

			try {
				// Estabelecer a conexao
				Connection con = dao.conectar();

				// Preparar a execucao da query
				PreparedStatement pst = con.prepareStatement(create);

				// Substituir as interrogacoes pelo conteudo das caixas de texto
				pst.setString(1, txtProdBarcode.getText());
				pst.setString(2, txtProdNome.getText());
				pst.setString(3, txtProdDescricao.getText());
				pst.setString(4, txtProdFornecedor.getText());

				// Formatar o valor do JCalendar para inserção correta no banco
				SimpleDateFormat formatador = new SimpleDateFormat("yyyyMMdd");
				String dataEntradaFormatada = formatador.format(txtProdEntrada.getDate());
				pst.setString(5, dataEntradaFormatada);

				pst.setString(6, txtProdEstoque.getText());
				pst.setString(7, txtProdEstoqueMin.getText());
				pst.setString(8, cboProdUnidade.getSelectedItem().toString());
				pst.setString(9, txtProdSetor.getText());
				pst.setString(10, txtProdCusto.getText());
				pst.setString(11, txtProdLucro.getText());
				pst.setString(12, txtForID.getText());

				// Executar a query e inserir o produto no banco
				pst.executeUpdate();

				// Exibir mensagem ao cadastrar fornecedor com sucesso no banco
				JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!");

				limparCampos();

				// NUNCA esquecer de encerrar a conexao
				con.close();

			}

			catch (SQLIntegrityConstraintViolationException ex) {

				JOptionPane.showMessageDialog(null,
						"Ocorreu um erro. \nVerifique novamente o código de barras do produto.");
				txtProdBarcode.requestFocus();

			} catch (Exception e) {
				System.out.println(e);
			}

		}

	}

	private void adicionarProdutoSemBarcode() {
		// Validacao do fornecedor do produto
		if (txtBuscarFor.getText().isEmpty()) {
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
			JOptionPane.showMessageDialog(null, "Preencha o estoque mínimo do produto");
			txtProdEstoqueMin.requestFocus();
		}

		// Validacao do setor do produto
		else if (txtProdSetor.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o setor do produto");
			txtProdSetor.requestFocus();
		}

		// Validacao do custo do produto
		else if (txtProdCusto.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o custo do produto");
			txtProdCusto.requestFocus();
		}

		// Validacao do lucro do produto
		else if (txtProdLucro.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o lucro do produto");
			txtProdLucro.requestFocus();
		}

		// Validacao da descricao do produto
		else if (txtProdDescricao.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha a descrição do produto");
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
			String create = "insert into produtos (produto, descricao, fabricante, datacad, estoque, estoquemin, unidade, localizacao, custo, lucro, idfor) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

			try {
				// Estabelecer a conexao
				Connection con = dao.conectar();

				// Preparar a execucao da query
				PreparedStatement pst = con.prepareStatement(create);

				// Substituir as interrogacoes pelo conteudo das caixas de texto
				pst.setString(1, txtProdNome.getText());
				pst.setString(2, txtProdDescricao.getText());
				pst.setString(3, txtProdFornecedor.getText());

				// Formatar o valor do JCalendar para inserção correta no banco
				SimpleDateFormat formatador = new SimpleDateFormat("yyyyMMdd");
				String dataEntradaFormatada = formatador.format(txtProdEntrada.getDate());
				pst.setString(4, dataEntradaFormatada);

				pst.setString(5, txtProdEstoque.getText());
				pst.setString(6, txtProdEstoqueMin.getText());
				pst.setString(7, cboProdUnidade.getSelectedItem().toString());
				pst.setString(8, txtProdSetor.getText());
				pst.setString(9, txtProdCusto.getText());
				pst.setString(10, txtProdLucro.getText());
				pst.setString(11, txtForID.getText());

				// Executar a query e inserir o produto no banco
				pst.executeUpdate();

				// Exibir mensagem ao cadastrar fornecedor com sucesso no banco
				JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!");

				limparCampos();

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
		// Validacao do fornecedor do produto
		if (txtBuscarFor.getText().isEmpty()) {
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
			JOptionPane.showMessageDialog(null, "Preencha o estoque mínimo do produto");
			txtProdEstoqueMin.requestFocus();
		}

		// Validacao do setor do produto
		else if (txtProdSetor.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o setor do produto");
			txtProdSetor.requestFocus();
		}

		// Validacao do custo do produto
		else if (txtProdCusto.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o custo do produto");
			txtProdCusto.requestFocus();
		}

		// Validacao do lucro do produto
		else if (txtProdLucro.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o lucro do produto");
			txtProdLucro.requestFocus();
		}

		// Validacao da descricao do produto
		else if (txtProdDescricao.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha a descrição do produto");
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
			String update = "update produtos set barcode = ?, produto = ?, descricao = ?, fabricante = ?, datacad = ?, estoque = ?, estoquemin = ?, unidade = ?, localizacao = ?, custo = ?, lucro = ?, idfor = ? where codigo = ?;";

			try {
				// Estabelecer a conexao
				Connection con = dao.conectar();

				// Preparar a execucao da query
				PreparedStatement pst = con.prepareStatement(update);

				// Substituir as interrogacoes pelo conteudo das caixas de texto
				pst.setString(1, txtProdBarcode.getText());
				pst.setString(2, txtProdNome.getText());
				pst.setString(3, txtProdDescricao.getText());
				pst.setString(4, txtProdFornecedor.getText());

				// Formatar o valor do JCalendar para inserção correta no banco
				SimpleDateFormat formatador = new SimpleDateFormat("yyyyMMdd");
				String dataEntradaFormatada = formatador.format(txtProdEntrada.getDate());
				pst.setString(5, dataEntradaFormatada);

				pst.setString(6, txtProdEstoque.getText());
				pst.setString(7, txtProdEstoqueMin.getText());
				pst.setString(8, cboProdUnidade.getSelectedItem().toString());
				pst.setString(9, txtProdSetor.getText());
				pst.setString(10, txtProdCusto.getText());
				pst.setString(11, txtProdLucro.getText());
				pst.setString(12, txtForID.getText());
				pst.setString(13, txtProdCodigo.getText());

				// Executar a query e alterar o produto no banco
				pst.executeUpdate();

				// Exibir mensagem ao alterar produto cadastrado com sucesso no banco
				JOptionPane.showMessageDialog(null, "Dados do produto atualizados com sucesso!");

				limparCampos();

				// NUNCA esquecer de encerrar a conexao
				con.close();

			}

			catch (SQLIntegrityConstraintViolationException ex) {

				JOptionPane.showMessageDialog(null,
						"Ocorreu um erro. \nVerifique novamente o código de barras do produto.");
				txtProdBarcode.requestFocus();

			}

			catch (Exception e) {
				System.out.println(e);
			}

		}
	}

	private void alterarProdutoSemBarcode() {
		// Validacao do fornecedor do produto
		if (txtBuscarFor.getText().isEmpty()) {
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
			JOptionPane.showMessageDialog(null, "Preencha o estoque mínimo do produto");
			txtProdEstoqueMin.requestFocus();
		}

		// Validacao do setor do produto
		else if (txtProdSetor.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o setor do produto");
			txtProdSetor.requestFocus();
		}

		// Validacao do custo do produto
		else if (txtProdCusto.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o custo do produto");
			txtProdCusto.requestFocus();
		}

		// Validacao do lucro do produto
		else if (txtProdLucro.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o lucro do produto");
			txtProdLucro.requestFocus();
		}

		// Validacao da descricao do produto
		else if (txtProdDescricao.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha a descrição do produto");
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
			String update = "update produtos set produto = ?, descricao = ?, fabricante = ?, datacad = ?, estoque = ?, estoquemin = ?, unidade = ?, localizacao = ?, custo = ?, lucro = ?, idfor = ? where codigo = ?;";

			try {
				// Estabelecer a conexao
				Connection con = dao.conectar();

				// Preparar a execucao da query
				PreparedStatement pst = con.prepareStatement(update);

				// Substituir as interrogacoes pelo conteudo das caixas de texto
				pst.setString(1, txtProdNome.getText());
				pst.setString(2, txtProdDescricao.getText());
				pst.setString(3, txtProdFornecedor.getText());

				// Formatar o valor do JCalendar para inserção correta no banco
				SimpleDateFormat formatador = new SimpleDateFormat("yyyyMMdd");
				String dataEntradaFormatada = formatador.format(txtProdEntrada.getDate());
				pst.setString(4, dataEntradaFormatada);

				pst.setString(5, txtProdEstoque.getText());
				pst.setString(6, txtProdEstoqueMin.getText());
				pst.setString(7, cboProdUnidade.getSelectedItem().toString());
				pst.setString(8, txtProdSetor.getText());
				pst.setString(9, txtProdCusto.getText());
				pst.setString(10, txtProdLucro.getText());
				pst.setString(11, txtForID.getText());
				pst.setString(12, txtProdCodigo.getText());

				// Executar a query e alterar o produto no banco
				pst.executeUpdate();

				// Exibir mensagem ao alterar produto cadastrado com sucesso no banco
				JOptionPane.showMessageDialog(null, "Dados do produto atualizados com sucesso!");

				limparCampos();

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
		int confirma = JOptionPane.showConfirmDialog(null, "Confirma a exclusão do produto?", "Atenção!",
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

				limparCampos();

				// Exibir mensagem ao deletar fornecedor
				JOptionPane.showMessageDialog(null, "Produto excluído com sucesso!");

				// NUNCA esquecer de encerrar a conexao
				con.close();

			}

			catch (Exception e) {
				System.out.println(e);
			}

		}

	}

	private void limparCampos() {
		// Limpar a tabela
		((DefaultTableModel) tblFornecedores.getModel()).setRowCount(0);

		txtProdBarcode.setText(null);
		// txtBuscarFor.setText(null);
		// txtForID.setText(null);

	}

} // Fim do código
