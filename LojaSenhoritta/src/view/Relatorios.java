package view;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import model.DAO;

public class Relatorios extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Relatorios dialog = new Relatorios();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Relatorios() {
		setModal(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Principal.class.getResource("/img/logo.png")));
		setTitle("Relatorios");
		setBounds(100, 100, 581, 184);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JButton btnRelClientes = new JButton("Clientes");
		btnRelClientes.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnRelClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				relatorioClientes();
			}
		});
		btnRelClientes.setBounds(69, 31, 172, 23);
		contentPanel.add(btnRelClientes);

		JButton btnrelFornecedores = new JButton("Fornecedores");
		btnrelFornecedores.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnrelFornecedores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				relatorioFornecedores();
			}
		});

		btnrelFornecedores.setBounds(69, 89, 172, 23);
		contentPanel.add(btnrelFornecedores);

		JButton btnRelReposicao = new JButton("Reposiçao de estoque");
		btnRelReposicao.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnRelReposicao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				relatorioReposicao();
			}
		});
		btnRelReposicao.setBounds(288, 31, 203, 23);
		contentPanel.add(btnRelReposicao);

		JButton btnRelInventario = new JButton("Inventario");
		btnRelInventario.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnRelInventario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				relatorioInventario();
			}
		});

		btnRelInventario.setBounds(288, 89, 203, 23);
		contentPanel.add(btnRelInventario);

	}// fim do construtor

	// acesso ao banco de dados
	DAO dao = new DAO();


	private void relatorioFornecedores() {

		Document document = new Document();

		try {
	
			PdfWriter.getInstance(document, new FileOutputStream("fornecedores.pdf"));
			document.open();
		
			Date data = new Date();
			DateFormat formatador = DateFormat.getDateInstance(DateFormat.FULL);
			document.add(new Paragraph(new Paragraph(formatador.format(data))));
			document.add(new Paragraph(" "));
			document.add(new Paragraph("Fornecedores cadastrados"));
			document.add(new Paragraph(" "));

			PdfPTable tabela = new PdfPTable(4);
			PdfPCell col1 = new PdfPCell(new Paragraph("CNPJ"));
			PdfPCell col2 = new PdfPCell(new Paragraph("Razao Social"));
			PdfPCell col3 = new PdfPCell(new Paragraph("Nome Fantasia"));
			PdfPCell col4 = new PdfPCell(new Paragraph("Telefone"));
			tabela.addCell(col1);
			tabela.addCell(col2);
			tabela.addCell(col3);
			tabela.addCell(col4);
			
	
			String relFornecedores = "select cnpj,razao,fantasia,fone from fornecedores";
			try {
				Connection con = dao.conectar();
				PreparedStatement pst = con.prepareStatement(relFornecedores);
				ResultSet rs = pst.executeQuery();
				while (rs.next()) {
					tabela.addCell(rs.getString(1));
					tabela.addCell(rs.getString(2));
					tabela.addCell(rs.getString(3));
					tabela.addCell(rs.getString(4));
				}

			} catch (Exception e) {
				System.out.println(e);
			}
			
			document.add(tabela);
		} catch (Exception e) {
			System.out.println(e);
		} finally { 
			document.close();
		}

	
		try {
			Desktop.getDesktop().open(new File("fornecedores.pdf"));
		} catch (Exception e) {
			System.out.println(e);
		}
	}


	private void relatorioInventario() {
		
		Document document = new Document();
	
		try {

			PdfWriter.getInstance(document, new FileOutputStream("inventario.pdf"));
			document.open();
			
			Date data = new Date();
			DateFormat formatador = DateFormat.getDateInstance(DateFormat.FULL);
			document.add(new Paragraph(new Paragraph(formatador.format(data))));
			document.add(new Paragraph(" "));
			document.add(new Paragraph("Inventario"));
			document.add(new Paragraph(" "));
			
			
			PdfPTable tabela = new PdfPTable(1);
			PdfPCell col1 = new PdfPCell(new Paragraph("Total (BRL)"));
			
			tabela.addCell(col1);
			
			String relInventario = "select sum(estoque * custo) from produtos;"; 
			try {
				Connection con = dao.conectar();
				PreparedStatement pst = con.prepareStatement(relInventario);
				ResultSet rs = pst.executeQuery();
				while (rs.next()) {
					tabela.addCell("R$ " + rs.getString(1));
				}

			} catch (Exception e) {
				System.out.println(e);
			}
			
			document.add(tabela);
		} catch (Exception e) {
			System.out.println(e);
		} finally { 
			document.close();
		}

		
		try {
			Desktop.getDesktop().open(new File("inventario.pdf"));
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	
	private void relatorioClientes() {
	
		Document document = new Document();
	
		try {
		
			PdfWriter.getInstance(document, new FileOutputStream("clientes.pdf"));
			document.open();
		
			Date data = new Date();
			DateFormat formatador = DateFormat.getDateInstance(DateFormat.FULL);
			document.add(new Paragraph(new Paragraph(formatador.format(data))));
			document.add(new Paragraph(" "));
			document.add(new Paragraph("Clientes cadastrados"));
			document.add(new Paragraph(" "));
	
			PdfPTable tabela = new PdfPTable(3);
			PdfPCell col1 = new PdfPCell(new Paragraph("Nome"));
			PdfPCell col2 = new PdfPCell(new Paragraph("CPF"));
			PdfPCell col3 = new PdfPCell(new Paragraph("Email"));
			tabela.addCell(col1);
			tabela.addCell(col2);
			tabela.addCell(col3);
			
			String relClientes = "select nome,cpf,email from clientes";
			try {
				Connection con = dao.conectar();
				PreparedStatement pst = con.prepareStatement(relClientes);
				ResultSet rs = pst.executeQuery();
				while (rs.next()) {
					tabela.addCell(rs.getString(1));
					tabela.addCell(rs.getString(2));
					tabela.addCell(rs.getString(3));
				}

			} catch (Exception e) {
				System.out.println(e);
			}
			
			document.add(tabela);
		} catch (Exception e) {
			System.out.println(e);
		} finally { 
			document.close();
		}

		
		try {
			Desktop.getDesktop().open(new File("clientes.pdf"));
		} catch (Exception e) {
			System.out.println(e);
		}
	}


	private void relatorioReposicao() {
		
		Document document = new Document(PageSize.A4.rotate(), 30f, 30f, 20f, 0f);
		
		try {
			
			PdfWriter.getInstance(document, new FileOutputStream("reposicao.pdf"));
			document.open();
	
			Date data = new Date();
			DateFormat formatador = DateFormat.getDateInstance(DateFormat.FULL);
			document.add(new Paragraph(new Paragraph(formatador.format(data))));
			document.add(new Paragraph(" "));
			document.add(new Paragraph("Reposiçao de estoque"));
			document.add(new Paragraph(" "));
			
			PdfPTable tabela = new PdfPTable(5);
			PdfPCell col1 = new PdfPCell(new Paragraph("Codigo"));
			PdfPCell col2 = new PdfPCell(new Paragraph("Produto"));
			PdfPCell col3 = new PdfPCell(new Paragraph("Em venda desde"));
			PdfPCell col4 = new PdfPCell(new Paragraph("Estoque"));
			PdfPCell col5 = new PdfPCell(new Paragraph("Estoque minimo"));
			tabela.addCell(col1);
			tabela.addCell(col2);
			tabela.addCell(col3);
			tabela.addCell(col4);
			tabela.addCell(col5);
			// Acessar o banco de dados
			String relReposicao = "select codigo,produto,date_format(iniciovenda,'%d/%m/%Y'), estoque, estoquemin from produtos where estoque < estoquemin";
			try {
				Connection con = dao.conectar();
				PreparedStatement pst = con.prepareStatement(relReposicao);
				ResultSet rs = pst.executeQuery();
				while (rs.next()) {
					tabela.addCell(rs.getString(1));
					tabela.addCell(rs.getString(2));
					tabela.addCell(rs.getString(3));
					tabela.addCell(rs.getString(4));
					tabela.addCell(rs.getString(5));
				}

			} catch (Exception e) {
				System.out.println(e);
			}
		
			document.add(tabela);
		} catch (Exception e) {
			System.out.println(e);
		} finally { 
			document.close();
		}

		
		try {
			Desktop.getDesktop().open(new File("reposicao.pdf"));
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}