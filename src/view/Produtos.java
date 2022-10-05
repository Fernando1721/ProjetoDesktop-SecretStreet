package view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import Atxy2k.CustomTextField.RestrictedTextField;
import model.DAO;
import net.proteanit.sql.DbUtils;

public class Produtos extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JLabel lblNewLabel;
	private JTextField txtBarcode;
	private JTextField txtId;
	private JTextField txtPesquisarFornecedor;
	private JTextField txtIdFor;
	private JTable tblFornecedor;
	private JTextField txtProduto;
	private JTextField txtFabricante;
	private JTextField txtEstoque;
	private JTextField txtEstoqueMin;
	private JTextField txtLocal;
	private JTextField txtCusto;
	private JTextField txtLucro;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Produtos dialog = new Produtos();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Produtos() {
		setModal(true);
		setResizable(false);
		setTitle("Produtos");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Produtos.class.getResource("/img/caixas.png")));
		setBounds(100, 100, 1000, 697);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 994, 687);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Produtos.class.getResource("/img/barcodeProd.png")));
		lblNewLabel.setBounds(23, 202, 64, 45);
		contentPanel.add(lblNewLabel);
		
		txtBarcode = new JTextField();
		txtBarcode.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
					if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					pesquisarProdutoCodigoBarras();
			}}		
		});
		txtBarcode.setBounds(97, 221, 250, 20);
		contentPanel.add(txtBarcode);
		txtBarcode.setColumns(10);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Fornecedor", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBackground(SystemColor.menu);
		panel.setBounds(430, 194, 541, 175);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		txtPesquisarFornecedor = new JTextField();
		txtPesquisarFornecedor.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				pesquisarForTabela();
			}
		});
		txtPesquisarFornecedor.setBounds(10, 25, 251, 20);
		panel.add(txtPesquisarFornecedor);
		txtPesquisarFornecedor.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("ID");
		lblNewLabel_4.setBounds(354, 28, 46, 14);
		panel.add(lblNewLabel_4);
		
		txtIdFor = new JTextField();
		txtIdFor.setBounds(410, 25, 121, 20);
		panel.add(txtIdFor);
		txtIdFor.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 60, 521, 104);
		panel.add(scrollPane);
		
		tblFornecedor = new JTable();
		tblFornecedor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setarCaixasTexto();
			}
		});
		scrollPane.setViewportView(tblFornecedor);
		
		JLabel lblNewLabel_2_1 = new JLabel("Produto");
		lblNewLabel_2_1.setBounds(33, 285, 46, 14);
		contentPanel.add(lblNewLabel_2_1);
		
		txtProduto = new JTextField();
		txtProduto.setColumns(10);
		txtProduto.setBounds(97, 282, 250, 20);
		contentPanel.add(txtProduto);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("Descri\u00E7\u00E3o");
		lblNewLabel_2_1_1.setBounds(33, 371, 74, 14);
		contentPanel.add(lblNewLabel_2_1_1);
		
		btnAdicionarProd = new JButton("");
		btnAdicionarProd.setEnabled(false);
		btnAdicionarProd.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAdicionarProd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adicionarProdutos();
			}
		});
		btnAdicionarProd.setContentAreaFilled(false);
		btnAdicionarProd.setBorderPainted(false);
		btnAdicionarProd.setIcon(new ImageIcon(Produtos.class.getResource("/img/createProd.png")));
		btnAdicionarProd.setBounds(548, 534, 64, 64);
		contentPanel.add(btnAdicionarProd);
		
		btnAlterarProd = new JButton("");
		btnAlterarProd.setEnabled(false);
		btnAlterarProd.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAlterarProd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alterarProduto();
			}
		});
		btnAlterarProd.setIcon(new ImageIcon(Produtos.class.getResource("/img/updateProd.png")));
		btnAlterarProd.setContentAreaFilled(false);
		btnAlterarProd.setBorderPainted(false);
		btnAlterarProd.setBounds(685, 534, 64, 64);
		contentPanel.add(btnAlterarProd);
		
		btnExcluirProd = new JButton("");
		btnExcluirProd.setEnabled(false);
		btnExcluirProd.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnExcluirProd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				excluirProduto();
			}
		});
		btnExcluirProd.setIcon(new ImageIcon(Produtos.class.getResource("/img/deleteProd.png")));
		btnExcluirProd.setContentAreaFilled(false);
		btnExcluirProd.setBorderPainted(false);
		btnExcluirProd.setBounds(812, 534, 64, 64);
		contentPanel.add(btnExcluirProd);
		
		JLabel lblNewLabel_2_1_1_1 = new JLabel("Fabricante");
		lblNewLabel_2_1_1_1.setBounds(33, 463, 74, 14);
		contentPanel.add(lblNewLabel_2_1_1_1);
		
		txtFabricante = new JTextField();
		txtFabricante.setColumns(10);
		txtFabricante.setBounds(97, 460, 250, 20);
		contentPanel.add(txtFabricante);
		
		JLabel lblNewLabel_2_1_1_1_1 = new JLabel("Estoque");
		lblNewLabel_2_1_1_1_1.setBounds(33, 548, 74, 14);
		contentPanel.add(lblNewLabel_2_1_1_1_1);
		
		txtEstoque = new JTextField();
		txtEstoque.setColumns(10);
		txtEstoque.setBounds(97, 545, 64, 20);
		contentPanel.add(txtEstoque);
		
		JLabel lblNewLabel_2_1_1_1_1_1 = new JLabel("Estoque m\u00EDnimo");
		lblNewLabel_2_1_1_1_1_1.setBounds(171, 548, 112, 14);
		contentPanel.add(lblNewLabel_2_1_1_1_1_1);
		
		txtEstoqueMin = new JTextField();
		txtEstoqueMin.setColumns(10);
		txtEstoqueMin.setBounds(273, 545, 74, 20);
		contentPanel.add(txtEstoqueMin);
		
		JLabel lblNewLabel_2_1_1_1_1_1_1 = new JLabel("Local");
		lblNewLabel_2_1_1_1_1_1_1.setBounds(33, 594, 119, 14);
		contentPanel.add(lblNewLabel_2_1_1_1_1_1_1);
		
		txtLocal = new JTextField();
		txtLocal.setColumns(10);
		txtLocal.setBounds(97, 591, 250, 20);
		contentPanel.add(txtLocal);
		
		JLabel lblNewLabel_2_1_1_1_2 = new JLabel("Entrada");
		lblNewLabel_2_1_1_1_2.setBounds(566, 355, 74, 14);
		contentPanel.add(lblNewLabel_2_1_1_1_2);
		
		dataEntrada = new JDateChooser();
		dataEntrada.setBounds(785, 405, 156, 23);
		contentPanel.add(dataEntrada);
		
		JLabel lblNewLabel_2_1_1_1_2_2 = new JLabel("Custo");
		lblNewLabel_2_1_1_1_2_2.setBounds(447, 405, 74, 14);
		contentPanel.add(lblNewLabel_2_1_1_1_2_2);
		
		txtCusto = new JTextField();
		txtCusto.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				
			}
			@Override
			public void keyTyped(KeyEvent e) {
				// validação (somente números ao digitar)
				String caracteres = "0987654321.";
				if (!caracteres.contains(e.getKeyChar() + "")) {
					e.consume();
				}
			}
		});
		txtCusto.setColumns(10);
		txtCusto.setBounds(520, 405, 156, 20);
		contentPanel.add(txtCusto);
		
		JLabel lblNewLabel_2_1_1_1_2_2_1 = new JLabel("Lucro(%)");
		lblNewLabel_2_1_1_1_2_2_1.setBounds(447, 463, 74, 14);
		contentPanel.add(lblNewLabel_2_1_1_1_2_2_1);
		
		txtLucro = new JTextField();
		txtLucro.setColumns(10);
		txtLucro.setBounds(520, 460, 156, 20);
		contentPanel.add(txtLucro);
		
		JLabel lblNewLabel_2_1_1_1_3 = new JLabel("Departamento");
		lblNewLabel_2_1_1_1_3.setBounds(33, 506, 98, 14);
		contentPanel.add(lblNewLabel_2_1_1_1_3);
		
		cboDepartamento = new JComboBox();
		cboDepartamento.setModel(new DefaultComboBoxModel(new String[] {"", "Hardwares", "Perif\u00E9ricos", "Games", "Computadores", "Smartphones", "TV", "Cadeiras"}));
		cboDepartamento.setBounds(128, 502, 219, 22);
		contentPanel.add(cboDepartamento);
		
		JLabel lblNewLabel_2_1_1_1_1_1_1_1 = new JLabel("Venda");
		lblNewLabel_2_1_1_1_1_1_1_1.setBounds(743, 463, 119, 14);
		contentPanel.add(lblNewLabel_2_1_1_1_1_1_1_1);
		
		txtVenda = new JTextField();
		txtVenda.setColumns(10);
		txtVenda.setBounds(785, 460, 156, 20);
		contentPanel.add(txtVenda);
		
		txtDescricao = new JTextArea();
		txtDescricao.setBounds(97, 327, 250, 107);
		contentPanel.add(txtDescricao);
		
		
		RestrictedTextField validarBarcode = new RestrictedTextField(txtBarcode);
		validarBarcode.setLimit(15);
		validarBarcode.setOnlyNums(true);

		RestrictedTextField validarProd = new RestrictedTextField(txtProduto);
		validarProd.setLimit(100);

		RestrictedTextField validarFabricante = new RestrictedTextField(txtFabricante);
		validarFabricante.setLimit(50);

		RestrictedTextField validarEstoque = new RestrictedTextField(txtEstoque);
		validarEstoque.setLimit(5);
		validarEstoque.setOnlyNums(true);
		
		RestrictedTextField validarEstoqueMin = new RestrictedTextField(txtEstoqueMin);
		validarEstoqueMin.setLimit(5);
		validarEstoqueMin.setOnlyNums(true);
		
		RestrictedTextField validarLocalizacao = new RestrictedTextField(txtLocal);
		validarLocalizacao.setLimit(50);
		
		RestrictedTextField validarIdFor = new RestrictedTextField(txtIdFor);
		
		JLabel lblNewLabel_3_2 = new JLabel("\r\n");
		lblNewLabel_3_2.setBounds(198, -20, 32, 32);
		panel.add(lblNewLabel_3_2);
		
		lblNewLabel_1 = new JLabel("\r\n");
		lblNewLabel_1.setIcon(new ImageIcon(Produtos.class.getResource("/img/procurar.png")));
		lblNewLabel_1.setBounds(271, 19, 32, 32);
		panel.add(lblNewLabel_1);
		validarIdFor.setLimit(4);
		validarIdFor.setOnlyNums(true);
		
		RestrictedTextField validarCusto = new RestrictedTextField(txtCusto);
		validarCusto.setLimit(9);
		
		RestrictedTextField validarLucro = new RestrictedTextField(txtLucro);
		validarLucro.setOnlyNums(true);
		validarLucro.setLimit(9);
		
		RestrictedTextField validarVenda = new RestrictedTextField(txtVenda);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Produtos", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBackground(SystemColor.menu);
		panel_1.setBounds(10, 11, 961, 175);
		contentPanel.add(panel_1);
		
		txtPesquisarProd = new JTextField();
		txtPesquisarProd.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				pesquisarProdTabela();
			}
		});
		txtPesquisarProd.setColumns(10);
		txtPesquisarProd.setBounds(10, 25, 714, 20);
		panel_1.add(txtPesquisarProd);
		
		JLabel lblNewLabel_3_1 = new JLabel("\r\n");
		lblNewLabel_3_1.setBounds(175, 19, 32, 32);
		panel_1.add(lblNewLabel_3_1);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 60, 941, 104);
		panel_1.add(scrollPane_1);
		
		tblProdutos = new JTable();
		tblProdutos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setarCaixasTextoProd();
			}
		});
		scrollPane_1.setViewportView(tblProdutos);
		
		JLabel lblNewLabel_4_1 = new JLabel("ID");
		lblNewLabel_4_1.setBounds(748, 28, 81, 14);
		panel_1.add(lblNewLabel_4_1);
		
		txtId = new JTextField();
		txtId.setBounds(777, 25, 72, 20);
		panel_1.add(txtId);
		txtId.setEditable(false);
		txtId.setColumns(10);
		
		RestrictedTextField validarId = new RestrictedTextField(txtId);
		validarId.setLimit(4);
		validarId.setOnlyNums(true);
		btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.setBounds(859, 24, 92, 23);
		panel_1.add(btnPesquisar);
		
		lblNewLabel_2_1_1_1_2_2_2 = new JLabel("Entrada");
		lblNewLabel_2_1_1_1_2_2_2.setBounds(728, 414, 74, 14);
		contentPanel.add(lblNewLabel_2_1_1_1_2_2_2);
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pesquisarProdutoCodigo();
			}
		});
		validarVenda.setOnlyNums(true);
		validarVenda.setLimit(9);
		
		
		
	} // Fim do construtor
	
	DAO dao = new DAO();
	private JButton btnAlterarProd;
	private JButton btnAdicionarProd;
	private JButton btnExcluirProd;
	private JComboBox cboDepartamento;
	private JButton btnPesquisar;
	private JTextField txtVenda;
	private JTextArea txtDescricao;
	private JDateChooser dataEntrada;
	private JTextField txtPesquisarProd;
	private JTable tblProdutos;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2_1_1_1_2_2_2;
	
	/**
	 * Método responsável pela pesquisa avançada do produto usando o nome
	 * do produto e a biblioteca rs2xml
	 */
	private void pesquisarProdTabela() {
		String readT = "select id as ID, produto as Produto, fabricante as Fabricante, venda as Venda from produtos where produto like ?";
		try {
			
			Connection con = dao.conectar();
			
			PreparedStatement pst = con.prepareStatement(readT);
			
			pst.setString(1, txtPesquisarProd.getText() + "%");
			ResultSet rs = pst.executeQuery();
			rs = pst.executeQuery();
			
			tblProdutos.setModel(DbUtils.resultSetToTableModel(rs));
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	
	/**
	 * Método responsável pela pesquisa avançada do fornecedor usando o nome
	 * de fantasia e a biblioteca rs2xml
	 */
	private void pesquisarForTabela() {
		String readT = "select idfor as ID, fantasia as Fornecedor, contato as Contato from fornecedores where fantasia like ?";
		try {
			
			Connection con = dao.conectar();
			
			PreparedStatement pst = con.prepareStatement(readT);
			
			pst.setString(1, txtPesquisarFornecedor.getText() + "%");
			ResultSet rs = pst.executeQuery();
			rs = pst.executeQuery();
			
			tblFornecedor.setModel(DbUtils.resultSetToTableModel(rs));
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	
	/**
	 * Método responsável por setar o ID da tabela de acordo com a tabela
	 */
	
	private void setarCaixasTextoProd() {
		int setar = tblProdutos.getSelectedRow();
		txtId.setText(tblProdutos.getModel().getValueAt(setar, 0).toString());

	}
	
	/**
	 * Método responsável por setar o ID da tabela de acordo com a tabela
	 */
	
	private void setarCaixasTexto() {
		int setar = tblFornecedor.getSelectedRow();
		txtIdFor.setText(tblFornecedor.getModel().getValueAt(setar, 0).toString());

	}
	
	/**
	 * Pesquisar produto por ID
	 */
	
	private void pesquisarProdutoCodigo() {
		String read = "select * from produtos where id = ?";
		try {
			Connection con = dao.conectar();
			PreparedStatement pst = con.prepareStatement(read);
			pst.setString(1, txtId.getText());
			ResultSet rs = pst.executeQuery();
			limparCampos();
			if (rs.next()) {
				txtBarcode.setText(rs.getString(2));
				txtProduto.setText(rs.getString(3));
				txtDescricao.setText(rs.getString(4));
				txtFabricante.setText(rs.getString(5));
					String setarDataCad = rs.getString(6);
					Date dataVal = new SimpleDateFormat("yyyy-MM-dd").parse(setarDataCad);
					dataEntrada.setDate(dataVal);
				cboDepartamento.setSelectedItem(rs.getString(7));
				txtEstoque.setText(rs.getString(8));
				txtEstoqueMin.setText(rs.getString(9));
				txtLocal.setText(rs.getString(10));
				txtCusto.setText(rs.getString(11));
				txtLucro.setText(rs.getString(12));
				txtVenda.setText(rs.getString(13));	
				txtIdFor.setText(rs.getString(14));
				
				btnAlterarProd.setEnabled(true);
				btnExcluirProd.setEnabled(true);
			} else {
				JOptionPane.showMessageDialog(null, "Produto não cadastrado");
				limparCampos();
				btnAdicionarProd.setEnabled(true);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	private void pesquisarProdutoCodigoBarras() {
		String read2 = "select * from produtos where barcode = ?";
		try {
			Connection con = dao.conectar();
			PreparedStatement pst = con.prepareStatement(read2);
			pst.setString(1, txtBarcode.getText());
			ResultSet rs = pst.executeQuery();
			limparCamposCodigo();
			if (rs.next()) {
				txtId.setText(rs.getString(1));
				txtBarcode.setText(rs.getString(2));
				txtProduto.setText(rs.getString(3));
				txtDescricao.setText(rs.getString(4));
				txtFabricante.setText(rs.getString(5));
					String setarDataCad = rs.getString(6);
					Date dataVal = new SimpleDateFormat("yyyy-MM-dd").parse(setarDataCad);
					dataEntrada.setDate(dataVal);
				cboDepartamento.setSelectedItem(rs.getString(7));
				txtEstoque.setText(rs.getString(8));
				txtEstoqueMin.setText(rs.getString(9));
				txtLocal.setText(rs.getString(10));
				txtCusto.setText(rs.getString(11));
				txtLucro.setText(rs.getString(12));
				txtVenda.setText(rs.getString(13));	
				txtIdFor.setText(rs.getString(14));
				
				btnAlterarProd.setEnabled(true);
				btnExcluirProd.setEnabled(true);
		   } else {
			   JOptionPane.showMessageDialog(null, "Produto não cadastrado");
			   limparCamposCodigo();
			   btnAdicionarProd.setEnabled(true);
		   }
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
		
		/**
		 * Método responsável por adicionar um produto ao banco
		 */

		private void adicionarProdutos() {

			if (txtProduto.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Informe o nome do produto");
				txtProduto.requestFocus();
			} else if (txtDescricao.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Informe a descrição do produto");
				txtDescricao.requestFocus();
			} else if (txtFabricante.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Informe o fabricante do produto");
				txtFabricante.requestFocus();
			} else if (cboDepartamento.getSelectedItem().equals("")) {
				JOptionPane.showMessageDialog(null, "Informe o departamento do produto");
				cboDepartamento.requestFocus();
			} else if (txtEstoque.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Informe a quantidade em estoque");
				txtEstoque.requestFocus();
			} else if (txtEstoqueMin.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Informe o estoque minimo");
				txtEstoqueMin.requestFocus();
			} else if (txtCusto.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Informe o custo do produto");
				txtCusto.requestFocus();
			} else if (txtLucro.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Informe o lucro do produto");
				txtLucro.requestFocus();
			} else if (txtVenda.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Informe o preço de venda do produto");
				txtVenda.requestFocus();
			} else if (txtIdFor.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Informe o ID do fornecedor do produto");
				txtIdFor.requestFocus();
			} else {
				String create = "insert into produtos(barcode,produto,descricao,fabricante,departamento,estoque,estoquemin,localizacao,custo,lucro,venda,idfor) values (?,?,?,?,?,?,?,?,?,?,?,?)";
				try {
					Connection con = dao.conectar();
					PreparedStatement pst = con.prepareStatement(create);
					
					pst.setString(1, txtBarcode.getText());
					pst.setString(2, txtProduto.getText());
					pst.setString(3, txtDescricao.getText());
					pst.setString(4, txtFabricante.getText());
					pst.setString(5, cboDepartamento.getSelectedItem().toString());
					pst.setString(6, txtEstoque.getText());
					pst.setString(7, txtEstoqueMin.getText());
					pst.setString(8, txtLocal.getText());
					pst.setString(9, txtCusto.getText());
					pst.setString(10, txtLucro.getText());
					pst.setString(11, txtVenda.getText());
					pst.setString(12, txtIdFor.getText());
					pst.executeUpdate();
					
					JOptionPane.showMessageDialog(null, "Produto adicionado com sucesso");
					limparCampos();
					limparCamposTbl();
					txtId.setText(null);
					con.close();
				} catch (SQLIntegrityConstraintViolationException ex) {
					JOptionPane.showMessageDialog(null, "Barcode existente.\n Digite outro");
					txtBarcode.setText(null);
					txtBarcode.requestFocus();
				} catch (Exception e) {
					System.out.println(e);
				}
			}
		}
		
		/**
		 * Método responsavel por alterar os dados de um produto do banco
		 */

		private void alterarProduto() {
			
			if (txtProduto.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Informe o nome do produto");
				txtProduto.requestFocus();
			} else if (txtDescricao.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Informe a descrição do produto");
				txtDescricao.requestFocus();
			} else if (txtFabricante.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Informe o fabricante do produto");
				txtFabricante.requestFocus();
			} else if (cboDepartamento.getSelectedItem().equals("")) {
				JOptionPane.showMessageDialog(null, "Informe o departamento do produto");
				cboDepartamento.requestFocus();
			} else if (txtEstoque.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Informe a quantidade em estoque");
				txtEstoque.requestFocus();
			} else if (txtEstoqueMin.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Informe o estoque minimo");
				txtEstoqueMin.requestFocus();
			} else if (txtCusto.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Informe o custo do produto");
				txtCusto.requestFocus();
			} else if (txtLucro.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Informe o lucro do produto");
				txtLucro.requestFocus();
			} else if (txtVenda.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Informe o preço de venda do produto");
				txtVenda.requestFocus();
			} else if (txtIdFor.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Informe o ID do fornecedor do produto");
				txtIdFor.requestFocus();
			} else {
				
				String update = "update produtos set barcode=?,produto=?,descricao=?,fabricante=?,datacad=?,departamento=?,estoque=?,estoquemin=?,localizacao=?,custo=?,lucro=?,venda=?,idfor=? where id=?";
				try {
					Connection con = dao.conectar();
					PreparedStatement pst = con.prepareStatement(update);
					
					pst.setString(1, txtBarcode.getText());
					pst.setString(2, txtProduto.getText());
					pst.setString(3, txtDescricao.getText());
					pst.setString(4, txtFabricante.getText());
						SimpleDateFormat formatador = new SimpleDateFormat("yyyyMMdd");
						String dataVal = formatador.format(dataEntrada.getDate());
						pst.setString(5, dataVal);			
					pst.setString(6, cboDepartamento.getSelectedItem().toString());
					pst.setString(7, txtEstoque.getText());
					pst.setString(8, txtEstoqueMin.getText());
					pst.setString(9, txtLocal.getText());
					pst.setString(10, txtCusto.getText());
					pst.setString(11, txtLucro.getText());
					pst.setString(12, txtVenda.getText());
					pst.setString(13, txtIdFor.getText());
					pst.setString(14, txtId.getText());
					pst.executeUpdate();
					
					JOptionPane.showMessageDialog(null, "Produto alterado com sucesso");
					limparCampos();
					limparCamposTbl();
					txtId.setText(null);
					con.close();
				} catch (SQLIntegrityConstraintViolationException ex) {
					JOptionPane.showMessageDialog(null, "Barcode existente.\nDigite outro");
					txtBarcode.setText(null);
					txtBarcode.requestFocus();
				} catch (Exception e) {
					System.out.println(e);
				}
			}

		}
		
		/**
		 * Método responsavel por excluir um produto do banco
		 */

		private void excluirProduto() {
			int confirma = JOptionPane.showConfirmDialog(null, "Confirma a exclusão do produto?", "Atenção!",
					JOptionPane.YES_NO_OPTION);
			if (confirma == JOptionPane.YES_OPTION) {
				String delete = "delete from produtos where id=?";
				try {
					
					Connection con = dao.conectar();
					
					PreparedStatement pst = con.prepareStatement(delete);
					
					pst.setString(1, txtId.getText());
					
					pst.executeUpdate();
					
					JOptionPane.showMessageDialog(null, "Produto excluido com sucesso");
					limparCampos();
					limparCamposTbl();
					txtId.setText(null);
					
					con.close();
				} catch (Exception e) {
					System.out.println(e);
				}
			}

		}
		
		/**
		 * Método responsavel por limpar campos
		 */
		
		private void limparCampos() {
			txtBarcode.setText(null);
			txtProduto.setText(null);
			txtDescricao.setText(null);
			txtFabricante.setText(null);
			cboDepartamento.setSelectedItem("");
			txtEstoque.setText(null);
			txtEstoqueMin.setText(null);
			txtLocal.setText(null);
			txtCusto.setText(null);
			txtVenda.setText(null);
			txtLucro.setText(null);
			txtIdFor.setText(null);
			dataEntrada.setDate(null);
			btnAdicionarProd.setEnabled(false);
			btnAlterarProd.setEnabled(false);
			btnExcluirProd.setEnabled(false);
		}
		
		private void limparCamposCodigo() {
			txtProduto.setText(null);
			txtDescricao.setText(null);
			txtFabricante.setText(null);
			cboDepartamento.setSelectedItem("");
			txtEstoque.setText(null);
			txtEstoqueMin.setText(null);
			txtLocal.setText(null);
			txtCusto.setText(null);
			txtVenda.setText(null);
			txtLucro.setText(null);
			txtIdFor.setText(null);
			txtId.setText(null);
			dataEntrada.setDate(null);
			btnAdicionarProd.setEnabled(false);
			btnAlterarProd.setEnabled(false);
			btnExcluirProd.setEnabled(false);
		}
		
		private void limparCamposTbl() {
			// limpar tabela
			((DefaultTableModel) tblFornecedor.getModel()).setRowCount(0);
			((DefaultTableModel) tblProdutos.getModel()).setRowCount(0);
		}
} // Fim do código
