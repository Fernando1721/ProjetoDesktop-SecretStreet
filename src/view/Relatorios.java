package view;

import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.itextpdf.text.Document;
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
	private JButton btnRelClientes;
	private JButton btnRelFornecedores;
	private JButton btnRelEmail;
	private JButton btnRelInventario;
	private JButton btnRelReposicao;

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
		setResizable(false);
		setModal(true);
		setTitle("Relat\u00F3rios");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Relatorios.class.getResource("/img/relatorio.png")));
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 434, 261);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		
		btnRelClientes = new JButton("");
		btnRelClientes.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnRelClientes.setContentAreaFilled(false);
		btnRelClientes.setBorderPainted(false);
		btnRelClientes.setIcon(new ImageIcon(Relatorios.class.getResource("/img/employee_3_icon-icons.com_76973.png")));
		btnRelClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				relatorioClientes();
			}
		});
		btnRelClientes.setBounds(107, 25, 48, 48);
		contentPanel.add(btnRelClientes);
		
		btnRelFornecedores = new JButton("");
		btnRelFornecedores.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnRelFornecedores.setBorderPainted(false);
		btnRelFornecedores.setContentAreaFilled(false);
		btnRelFornecedores.setIcon(new ImageIcon(Relatorios.class.getResource("/img/CEO_icon-icons.com_76995.png")));
		btnRelFornecedores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				relatorioFornecedores();
			}
		});
		btnRelFornecedores.setBounds(256, 25, 48, 48);
		contentPanel.add(btnRelFornecedores);
		
		btnRelReposicao = new JButton("");
		btnRelReposicao.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnRelReposicao.setContentAreaFilled(false);
		btnRelReposicao.setBorderPainted(false);
		btnRelReposicao.setIcon(new ImageIcon(Relatorios.class.getResource("/img/business_inventory_maintenance_product_box_boxes_2326.png")));
		btnRelReposicao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				relatorioReposicaoEstoque();
			}
		});
		btnRelReposicao.setBounds(256, 103, 48, 48);
		contentPanel.add(btnRelReposicao);
		
		btnRelInventario = new JButton("");
		btnRelInventario.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnRelInventario.setContentAreaFilled(false);
		btnRelInventario.setBorderPainted(false);
		btnRelInventario.setIcon(new ImageIcon(Relatorios.class.getResource("/img/backpack_icon-icons.com_64572.png")));
		btnRelInventario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				relatorioInventario();
			}
		});
		btnRelInventario.setBounds(177, 180, 48, 48);
		contentPanel.add(btnRelInventario);
		
		btnRelEmail = new JButton("");
		btnRelEmail.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnRelEmail.setContentAreaFilled(false);
		btnRelEmail.setBorderPainted(false);
		btnRelEmail.setIcon(new ImageIcon(Relatorios.class.getResource("/img/email.png")));
		btnRelEmail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				relatorioEmailMkt();
			}
		});
		btnRelEmail.setBounds(107, 103, 48, 48);
		contentPanel.add(btnRelEmail);
		
		lblNewLabel = new JLabel("Clientes");
		lblNewLabel.setBounds(109, 78, 46, 14);
		contentPanel.add(lblNewLabel);
		
		JLabel lblFornecedores = new JLabel("Fornecedores");
		lblFornecedores.setBounds(250, 78, 87, 14);
		contentPanel.add(lblFornecedores);
		
		JLabel lblEmailMarketing = new JLabel("E-mail Marketing");
		lblEmailMarketing.setBounds(86, 162, 112, 14);
		contentPanel.add(lblEmailMarketing);
		
		JLabel lblReposioDeEstoque = new JLabel("Reposi\u00E7\u00E3o de estoque");
		lblReposioDeEstoque.setBounds(225, 162, 134, 14);
		contentPanel.add(lblReposioDeEstoque);
		
		JLabel lblInventrio = new JLabel("Invent\u00E1rio");
		lblInventrio.setBounds(177, 236, 112, 14);
		contentPanel.add(lblInventrio);
	} // Fim do Construtor
	
	DAO dao = new DAO();
	private JLabel lblNewLabel;

	/**
	 * Método responsável pela impressão do relatório de clientes
	 */
	private void relatorioClientes() {
		//criar objeto para construir a página pdf
		Document document = new Document();
		//gerar o documento pdf
		try {
			//cria um documento pdf em branco de nome clientes.pdf
			PdfWriter.getInstance(document, new FileOutputStream("clientes.pdf"));
			document.open();
			//gerar o conteúdo do documento
			Date data = new Date();			
	        	DateFormat formatador = DateFormat.getDateInstance(DateFormat.FULL);
			document.add(new Paragraph(new Paragraph(formatador.format(data))));
			document.add(new Paragraph(" "));
			document.add(new Paragraph("Clientes cadastrados"));
			document.add(new Paragraph(" "));
			//... Demais conteúdos (imagem, tabela, gráfico, etc)
			PdfPTable tabela = new PdfPTable(4);
			PdfPCell col1 = new PdfPCell(new Paragraph("Nome"));
			PdfPCell col2 = new PdfPCell(new Paragraph("Fone"));
			PdfPCell col3 = new PdfPCell(new Paragraph("CPF"));
			PdfPCell col4 = new PdfPCell(new Paragraph("E-mail"));
			tabela.addCell(col1);
			tabela.addCell(col2);
			tabela.addCell(col3);
			tabela.addCell(col4);
			// Acessar o banco de dados
			 
			document.add(tabela);
		} catch (Exception e) {
			System.out.println(e);
		} finally { //executa o código independente do resultado OK ou não
			document.close();
		}
		
		//abrir o documento que foi gerado no leitor padrão de pdf do sistema (PC)
		try {
			Desktop.getDesktop().open(new File("clientes.pdf"));
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	/**
	 * Método responsável pela impressão do relatório de fornecedores
	 */
	private void relatorioFornecedores() {
		//criar objeto para construir a página pdf
		Document document = new Document();
		//gerar o documento pdf
		try {
			//cria um documento pdf em branco de nome clientes.pdf
			PdfWriter.getInstance(document, new FileOutputStream("fornecedores.pdf"));
			document.open();
			//gerar o conteúdo do documento
			Date data = new Date();			
	        	DateFormat formatador = DateFormat.getDateInstance(DateFormat.FULL);
			document.add(new Paragraph(new Paragraph(formatador.format(data))));
			document.add(new Paragraph(" "));
			document.add(new Paragraph("Fornecedores cadastrados"));
			document.add(new Paragraph(" "));
			//... Demais conteúdos (imagem, tabela, gráfico, etc)
			PdfPTable tabela = new PdfPTable(4);
			PdfPCell col1 = new PdfPCell(new Paragraph("Empresa"));
			PdfPCell col2 = new PdfPCell(new Paragraph("Fone"));
			PdfPCell col3 = new PdfPCell(new Paragraph("CNPJ"));
			PdfPCell col4 = new PdfPCell(new Paragraph("E-mail"));
			tabela.addCell(col1);
			tabela.addCell(col2);
			tabela.addCell(col3);
			tabela.addCell(col4);
			// Acessar o banco de dados
			 
			document.add(tabela);
		} catch (Exception e) {
			System.out.println(e);
		} finally { //executa o código independente do resultado OK ou não
			document.close();
		}
		
		//abrir o documento que foi gerado no leitor padrão de pdf do sistema (PC)
		try {
			Desktop.getDesktop().open(new File("fornecedores.pdf"));
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	/**
	 * Método responsável pela impressão do relatório de emails de marketing
	 */
	private void relatorioEmailMkt() {
		//criar objeto para construir a página pdf
		Document document = new Document();
		//gerar o documento pdf
		try {
			//cria um documento pdf em branco de nome clientes.pdf
			PdfWriter.getInstance(document, new FileOutputStream("emailmkt.pdf"));
			document.open();
			//gerar o conteúdo do documento
			Date data = new Date();			
	        	DateFormat formatador = DateFormat.getDateInstance(DateFormat.FULL);
			document.add(new Paragraph(new Paragraph(formatador.format(data))));
			document.add(new Paragraph(" "));
			document.add(new Paragraph("Permissões para E-mail de marketing"));
			document.add(new Paragraph(" "));
			//... Demais conteúdos (imagem, tabela, gráfico, etc)
			PdfPTable tabela = new PdfPTable(4);
			PdfPCell col1 = new PdfPCell(new Paragraph("Sim"));
			PdfPCell col2 = new PdfPCell(new Paragraph("Não"));
			PdfPCell col3 = new PdfPCell(new Paragraph(" "));
			PdfPCell col4 = new PdfPCell(new Paragraph(" "));
			tabela.addCell(col1);
			tabela.addCell(col2);
			tabela.addCell(col3);
			tabela.addCell(col4);
			// Acessar o banco de dados
			 
			document.add(tabela);
		} catch (Exception e) {
			System.out.println(e);
		} finally { //executa o código independente do resultado OK ou não
			document.close();
		}
		
		//abrir o documento que foi gerado no leitor padrão de pdf do sistema (PC)
		try {
			Desktop.getDesktop().open(new File("emailmkt.pdf"));
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	/**
	 * Método responsável pela impressão do relatório de reposição de estoque
	 */
	private void relatorioReposicaoEstoque() {
		//criar objeto para construir a página pdf
		Document document = new Document();
		//gerar o documento pdf
		try {
			//cria um documento pdf em branco de nome clientes.pdf
			PdfWriter.getInstance(document, new FileOutputStream("repestoque.pdf"));
			document.open();
			//gerar o conteúdo do documento
			Date data = new Date();			
	        	DateFormat formatador = DateFormat.getDateInstance(DateFormat.FULL);
			document.add(new Paragraph(new Paragraph(formatador.format(data))));
			document.add(new Paragraph(" "));
			document.add(new Paragraph("Reposição de estoque"));
			document.add(new Paragraph(" "));
			//... Demais conteúdos (imagem, tabela, gráfico, etc)
			PdfPTable tabela = new PdfPTable(4);
			PdfPCell col1 = new PdfPCell(new Paragraph("Produto"));
			PdfPCell col2 = new PdfPCell(new Paragraph("Estoque"));
			PdfPCell col3 = new PdfPCell(new Paragraph("Estoque Minimo"));
			PdfPCell col4 = new PdfPCell(new Paragraph("Prateleira"));
			tabela.addCell(col1);
			tabela.addCell(col2);
			tabela.addCell(col3);
			tabela.addCell(col4);
			// Acessar o banco de dados
			 
			document.add(tabela);
		} catch (Exception e) {
			System.out.println(e);
		} finally { //executa o código independente do resultado OK ou não
			document.close();
		}
		
		//abrir o documento que foi gerado no leitor padrão de pdf do sistema (PC)
		try {
			Desktop.getDesktop().open(new File("repestoque.pdf"));
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	/**
	 * Método responsável pela impressão do relatório de inventário
	 */
	private void relatorioInventario() {
		//criar objeto para construir a página pdf
		Document document = new Document();
		//gerar o documento pdf
		try {
			//cria um documento pdf em branco de nome clientes.pdf
			PdfWriter.getInstance(document, new FileOutputStream("inventario.pdf"));
			document.open();
			//gerar o conteúdo do documento
			Date data = new Date();			
	        	DateFormat formatador = DateFormat.getDateInstance(DateFormat.FULL);
			document.add(new Paragraph(new Paragraph(formatador.format(data))));
			document.add(new Paragraph(" "));
			document.add(new Paragraph("Inventário"));
			document.add(new Paragraph(" "));
			//... Demais conteúdos (imagem, tabela, gráfico, etc)
			PdfPTable tabela = new PdfPTable(4);
			PdfPCell col1 = new PdfPCell(new Paragraph("Nome"));
			PdfPCell col2 = new PdfPCell(new Paragraph("Fone"));
			PdfPCell col3 = new PdfPCell(new Paragraph("CPF"));
			PdfPCell col4 = new PdfPCell(new Paragraph("E-mail"));
			tabela.addCell(col1);
			tabela.addCell(col2);
			tabela.addCell(col3);
			tabela.addCell(col4);
			// Acessar o banco de dados
			 
			document.add(tabela);
		} catch (Exception e) {
			System.out.println(e);
		} finally { //executa o código independente do resultado OK ou não
			document.close();
		}
		
		//abrir o documento que foi gerado no leitor padrão de pdf do sistema (PC)
		try {
			Desktop.getDesktop().open(new File("inventario.pdf"));
		} catch (Exception e) {
			System.out.println(e);
		}
	}
} // Fim do código
