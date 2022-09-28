package view;

import java.awt.Color;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;

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
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import com.toedter.calendar.JDateChooser;

import model.DAO;
import net.proteanit.sql.DbUtils;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Cursor;

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
	private JTextField txtDescricao;
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
		setBounds(100, 100, 1000, 538);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 994, 499);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Produtos.class.getResource("/img/barcodeProd.png")));
		lblNewLabel.setBounds(10, 34, 64, 45);
		contentPanel.add(lblNewLabel);
		
		JLabel lblNewLabel_2 = new JLabel("ID");
		lblNewLabel_2.setBounds(20, 94, 46, 14);
		contentPanel.add(lblNewLabel_2);
		
		txtBarcode = new JTextField();
		txtBarcode.setBounds(84, 47, 250, 20);
		contentPanel.add(txtBarcode);
		txtBarcode.setColumns(10);
		
		txtId = new JTextField();
		txtId.setColumns(10);
		txtId.setBounds(84, 91, 136, 20);
		contentPanel.add(txtId);
		
		btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pesquisarProdutoCodigo();
			}
		});
		btnPesquisar.setBounds(230, 90, 104, 23);
		contentPanel.add(btnPesquisar);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Fornecedor", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBackground(SystemColor.menu);
		panel.setBounds(369, 11, 587, 175);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		txtPesquisarFornecedor = new JTextField();
		txtPesquisarFornecedor.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				pesquisarProdutosTabela();
			}
		});
		txtPesquisarFornecedor.setBounds(10, 25, 157, 20);
		panel.add(txtPesquisarFornecedor);
		txtPesquisarFornecedor.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("\r\n");
		lblNewLabel_3.setIcon(new ImageIcon(Produtos.class.getResource("/img/procurar.png")));
		lblNewLabel_3.setBounds(175, 19, 32, 32);
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("ID");
		lblNewLabel_4.setBounds(410, 28, 46, 14);
		panel.add(lblNewLabel_4);
		
		txtIdFor = new JTextField();
		txtIdFor.setBounds(451, 25, 126, 20);
		panel.add(txtIdFor);
		txtIdFor.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 60, 567, 104);
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
		lblNewLabel_2_1.setBounds(20, 138, 46, 14);
		contentPanel.add(lblNewLabel_2_1);
		
		txtProduto = new JTextField();
		txtProduto.setColumns(10);
		txtProduto.setBounds(84, 135, 250, 20);
		contentPanel.add(txtProduto);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("Descri\u00E7\u00E3o");
		lblNewLabel_2_1_1.setBounds(20, 224, 74, 14);
		contentPanel.add(lblNewLabel_2_1_1);
		
		txtDescricao = new JTextField();
		txtDescricao.setColumns(10);
		txtDescricao.setBounds(84, 176, 250, 111);
		contentPanel.add(txtDescricao);
		
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
		btnAdicionarProd.setBounds(622, 411, 64, 64);
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
		btnAlterarProd.setBounds(701, 411, 64, 64);
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
		btnExcluirProd.setBounds(783, 411, 64, 64);
		contentPanel.add(btnExcluirProd);
		
		JLabel lblNewLabel_2_1_1_1 = new JLabel("Fabricante");
		lblNewLabel_2_1_1_1.setBounds(20, 316, 74, 14);
		contentPanel.add(lblNewLabel_2_1_1_1);
		
		txtFabricante = new JTextField();
		txtFabricante.setColumns(10);
		txtFabricante.setBounds(84, 313, 250, 20);
		contentPanel.add(txtFabricante);
		
		JLabel lblNewLabel_2_1_1_1_1 = new JLabel("Estoque");
		lblNewLabel_2_1_1_1_1.setBounds(20, 401, 74, 14);
		contentPanel.add(lblNewLabel_2_1_1_1_1);
		
		txtEstoque = new JTextField();
		txtEstoque.setColumns(10);
		txtEstoque.setBounds(84, 398, 64, 20);
		contentPanel.add(txtEstoque);
		
		JLabel lblNewLabel_2_1_1_1_1_1 = new JLabel("Estoque m\u00EDnimo");
		lblNewLabel_2_1_1_1_1_1.setBounds(158, 401, 112, 14);
		contentPanel.add(lblNewLabel_2_1_1_1_1_1);
		
		txtEstoqueMin = new JTextField();
		txtEstoqueMin.setColumns(10);
		txtEstoqueMin.setBounds(260, 398, 74, 20);
		contentPanel.add(txtEstoqueMin);
		
		JLabel lblNewLabel_2_1_1_1_1_1_1 = new JLabel("Local");
		lblNewLabel_2_1_1_1_1_1_1.setBounds(20, 447, 119, 14);
		contentPanel.add(lblNewLabel_2_1_1_1_1_1_1);
		
		txtLocal = new JTextField();
		txtLocal.setColumns(10);
		txtLocal.setBounds(84, 444, 250, 20);
		contentPanel.add(txtLocal);
		
		JLabel lblNewLabel_2_1_1_1_2 = new JLabel("Entrada");
		lblNewLabel_2_1_1_1_2.setBounds(369, 215, 74, 14);
		contentPanel.add(lblNewLabel_2_1_1_1_2);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(419, 215, 156, 23);
		contentPanel.add(dateChooser);
		
		JDateChooser dateChooser_1 = new JDateChooser();
		dateChooser_1.setBounds(691, 215, 156, 23);
		contentPanel.add(dateChooser_1);
		
		JLabel lblNewLabel_2_1_1_1_2_1 = new JLabel("Validade");
		lblNewLabel_2_1_1_1_2_1.setBounds(633, 215, 74, 14);
		contentPanel.add(lblNewLabel_2_1_1_1_2_1);
		
		JLabel lblNewLabel_2_1_1_1_2_2 = new JLabel("Custo");
		lblNewLabel_2_1_1_1_2_2.setBounds(369, 273, 74, 14);
		contentPanel.add(lblNewLabel_2_1_1_1_2_2);
		
		txtCusto = new JTextField();
		txtCusto.setColumns(10);
		txtCusto.setBounds(419, 270, 156, 20);
		contentPanel.add(txtCusto);
		
		JLabel lblNewLabel_2_1_1_1_2_2_1 = new JLabel("Lucro(%)");
		lblNewLabel_2_1_1_1_2_2_1.setBounds(633, 273, 74, 14);
		contentPanel.add(lblNewLabel_2_1_1_1_2_2_1);
		
		txtLucro = new JTextField();
		txtLucro.setColumns(10);
		txtLucro.setBounds(691, 270, 156, 20);
		contentPanel.add(txtLucro);
		
		JLabel lblNewLabel_2_1_1_1_3 = new JLabel("Departamento");
		lblNewLabel_2_1_1_1_3.setBounds(20, 359, 98, 14);
		contentPanel.add(lblNewLabel_2_1_1_1_3);
		
		cboDepartamento = new JComboBox();
		cboDepartamento.setModel(new DefaultComboBoxModel(new String[] {"", "Hardwares", "Coolers", "Impressora", "Perif\u00E9ricos", "SmartPhones", "TV", "Cadeiras", "Computadores"}));
		cboDepartamento.setBounds(115, 355, 219, 22);
		contentPanel.add(cboDepartamento);
		
		JLabel lblNewLabel_2_1_1_1_1_1_1_1 = new JLabel("Venda");
		lblNewLabel_2_1_1_1_1_1_1_1.setBounds(517, 329, 119, 14);
		contentPanel.add(lblNewLabel_2_1_1_1_1_1_1_1);
		
		txtVenda = new JTextField();
		txtVenda.setColumns(10);
		txtVenda.setBounds(564, 326, 156, 20);
		contentPanel.add(txtVenda);
	} // Fim do construtor
	
	DAO dao = new DAO();
	private JButton btnAlterarProd;
	private JButton btnAdicionarProd;
	private JButton btnExcluirProd;
	private JComboBox cboDepartamento;
	private JButton btnPesquisar;
	private JTextField txtVenda;
	
	
	/**
	 * Método responsável pela pesquisa avançada do fornecedor usando o nome
	 * de fantasia e a biblioteca rs2xml
	 */
	private void pesquisarProdutosTabela() {
		String readT = "select idfor as ID, fantasia as Fornecedor, fone as Telefone, contato as Contato from fornecedores where fantasia like ?";
		try {
			// Estabelecer a conexão
			Connection con = dao.conectar();
			// Preparar a execução da Query
			PreparedStatement pst = con.prepareStatement(readT);
			// Setar o argumento (fantasia)
			// Substituir o ? pelo conteúdo da caixa de texto
			pst.setString(1, txtPesquisarFornecedor.getText() + "%");
			ResultSet rs = pst.executeQuery();
			rs = pst.executeQuery();
			// uso da biblioteca rs2xml para "popular" a tabela
			tblFornecedor.setModel(DbUtils.resultSetToTableModel(rs));
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	/**
	 * Método responsável por setar o ID da tabela de acordo com a tabela
	 */
	
	private void setarCaixasTexto() {
		int setar = tblFornecedor.getSelectedRow();
		txtIdFor.setText(tblFornecedor.getModel().getValueAt(setar, 0).toString());

	}
	
	/**
	 * Pesquisar produto por código
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
			} else if (txtIdFor.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Informe o ID do fornecedor do produto");
				txtIdFor.requestFocus();
			} else {
				// lógica principal
				String create = "insert into produtos(barcode,produto,descricao,fabricante,departamento,estoque,estoquemin,localizacao,custo,lucro,venda,idfor) values (?,?,?,?,?,?,?,?,?,?,?,?)";
				try {
					// Estabelecer a conexão
					Connection con = dao.conectar();
					// Preparar a execução da Query
					PreparedStatement pst = con.prepareStatement(create);
					// Substituir o ? pelo conteúdoo da caixa de texto
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
					// confirmação
					JOptionPane.showMessageDialog(null, "Produto adicionado com sucesso");
					limparCampos();
					// Encerrar a conexão
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
			// validação
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
			} else if (txtIdFor.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Informe o ID do fornecedor do produto");
				txtIdFor.requestFocus();
			} else {
				// lógica principal
				String update = "update produtos set barcode=?,produto=?,descricao=?,fabricante=?,departamento=?,estoque=?,estoquemin=?,localizacao=?,custo=?,lucro=?,venda=?,idfor=? where id=?";
				try {
					// Estabelecer a conexão
					Connection con = dao.conectar();
					// Preparar a execução da Query
					PreparedStatement pst = con.prepareStatement(update);
					// Substituir o ? pelo conteúdo da caixa de texto
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
					pst.setString(13, txtId.getText());
					pst.executeUpdate();
					// confirmação
					JOptionPane.showMessageDialog(null, "Produto alterado com sucesso");
					// Encerrar a conexão
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
			// validação (confirmação da exclusão)
			int confirma = JOptionPane.showConfirmDialog(null, "Confirma a exclusão do produto?", "Atenção!",
					JOptionPane.YES_NO_OPTION);
			if (confirma == JOptionPane.YES_OPTION) {
				String delete = "delete from produtos where id=?";
				try {
					// Estabelecer a conexão
					Connection con = dao.conectar();
					// Preparar a execução da Query
					PreparedStatement pst = con.prepareStatement(delete);
					// Substituir o ? pelo conteúdo da caixa de texto
					pst.setString(1, txtId.getText());
					// Executar a query e excluir o cliente do banco
					pst.executeUpdate();
					// confirmação
					JOptionPane.showMessageDialog(null, "Produto excluido com sucesso");
					// Encerrar a conexão
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
			txtId.setText(null);
			btnAdicionarProd.setEnabled(false);
			btnAlterarProd.setEnabled(false);
			btnExcluirProd.setEnabled(false);
		}
} // Fim do código
