package view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Iterator;

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
import javax.swing.table.DefaultTableModel;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import Atxy2k.CustomTextField.RestrictedTextField;
import model.DAO;
import net.proteanit.sql.DbUtils;

public class Fornecedores extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtPesquisarFornecedor;
	private JTextField txtForId;
	private JTextField txtForCNPJ;
	private JTextField txtForIE;
	private JTextField txtForIM;
	private JTextField txtForRazao;
	private JTextField txtForFantasia;
	private JTextField txtForSite;
	private JTextField txtForFone;
	private JTextField txtForContato;
	private JTextField txtForEmail;
	private JTextField txtForCEP;
	private JTextField txtForEndereco;
	private JTextField txtForNumero;
	private JTextField txtForBairro;
	private JTextField txtForCidade;
	private JTable tblFornecedores;
	private JComboBox cboForUF;
	private JTextField txtForComplemento;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Fornecedores dialog = new Fornecedores();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Fornecedores() {
		setTitle("Fornecedores");
		setResizable(false);
		setModal(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Fornecedores.class.getResource("/img/forklift.png")));
		setBounds(100, 100, 800, 476);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 784, 454);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Fornecedor");
		lblNewLabel.setBounds(39, 20, 81, 14);
		contentPanel.add(lblNewLabel);

		txtPesquisarFornecedor = new JTextField();
		txtPesquisarFornecedor.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				// evento digita��o
				pesquisarFornecedoresTabela();
			}
		});
		txtPesquisarFornecedor.setBounds(107, 17, 252, 20);
		contentPanel.add(txtPesquisarFornecedor);
		txtPesquisarFornecedor.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblNewLabel_1.setIcon(new ImageIcon(Fornecedores.class.getResource("/img/procurar.png")));
		lblNewLabel_1.setBounds(369, 11, 32, 32);
		contentPanel.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("ID");
		lblNewLabel_2.setBounds(39, 125, 46, 14);
		contentPanel.add(lblNewLabel_2);

		txtForId = new JTextField();
		txtForId.setBounds(62, 122, 66, 20);
		contentPanel.add(txtForId);
		txtForId.setColumns(10);

		JLabel textFor = new JLabel("CNPJ");
		textFor.setBounds(234, 125, 46, 14);
		contentPanel.add(textFor);

		txtForCNPJ = new JTextField();
		txtForCNPJ.setBounds(271, 122, 160, 20);
		contentPanel.add(txtForCNPJ);
		txtForCNPJ.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("IE");
		lblNewLabel_4.setBounds(448, 125, 46, 14);
		contentPanel.add(lblNewLabel_4);

		txtForIE = new JTextField();
		txtForIE.setBounds(474, 122, 122, 20);
		contentPanel.add(txtForIE);
		txtForIE.setColumns(10);

		JLabel lblNewLabel_5 = new JLabel("IM");
		lblNewLabel_5.setBounds(615, 125, 46, 14);
		contentPanel.add(lblNewLabel_5);

		txtForIM = new JTextField();
		txtForIM.setBounds(642, 122, 109, 20);
		contentPanel.add(txtForIM);
		txtForIM.setColumns(10);

		JLabel text = new JLabel("Raz\u00E3o Social");
		text.setBounds(39, 163, 109, 14);
		contentPanel.add(text);

		txtForRazao = new JTextField();
		txtForRazao.setBounds(116, 160, 274, 20);
		contentPanel.add(txtForRazao);
		txtForRazao.setColumns(10);

		JLabel lblNewLabel_7 = new JLabel("Nome de fantasia");
		lblNewLabel_7.setBounds(400, 163, 109, 14);
		contentPanel.add(lblNewLabel_7);

		txtForFantasia = new JTextField();
		txtForFantasia.setBounds(501, 160, 252, 20);
		contentPanel.add(txtForFantasia);
		txtForFantasia.setColumns(10);

		JLabel textSite = new JLabel("Site");
		textSite.setBounds(39, 202, 52, 14);
		contentPanel.add(textSite);

		txtForSite = new JTextField();
		txtForSite.setBounds(62, 199, 218, 20);
		contentPanel.add(txtForSite);
		txtForSite.setColumns(10);

		JLabel lblNewLabel_9 = new JLabel("Fone");
		lblNewLabel_9.setBounds(308, 202, 46, 14);
		contentPanel.add(lblNewLabel_9);

		txtForFone = new JTextField();
		txtForFone.setBounds(340, 199, 114, 20);
		contentPanel.add(txtForFone);
		txtForFone.setColumns(10);

		JLabel lblNewLabel_10 = new JLabel("Contato");
		lblNewLabel_10.setBounds(474, 202, 46, 14);
		contentPanel.add(lblNewLabel_10);

		txtForContato = new JTextField();
		txtForContato.setBounds(524, 199, 229, 20);
		contentPanel.add(txtForContato);
		txtForContato.setColumns(10);

		JLabel lblNewLabel_11 = new JLabel("E-mail");
		lblNewLabel_11.setBounds(39, 240, 46, 14);
		contentPanel.add(lblNewLabel_11);

		txtForEmail = new JTextField();
		txtForEmail.setBounds(82, 237, 274, 20);
		contentPanel.add(txtForEmail);
		txtForEmail.setColumns(10);

		JLabel lblNewLabel_12 = new JLabel("CEP");
		lblNewLabel_12.setBounds(369, 240, 46, 14);
		contentPanel.add(lblNewLabel_12);

		txtForCEP = new JTextField();
		txtForCEP.setBounds(397, 237, 237, 20);
		contentPanel.add(txtForCEP);
		txtForCEP.setColumns(10);

		JButton btnBuscarCEP = new JButton("Buscar CEP");
		btnBuscarCEP.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnBuscarCEP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// botão buscar CEP
	                if (txtForCEP.getText().equals("")) {
	                    JOptionPane.showMessageDialog(null, "Informe o CEP");
	                    txtForCEP.requestFocus();
	                } else {
	                    buscarCEP();
	                }
	            }
			});
		btnBuscarCEP.setBounds(644, 236, 107, 23);
		contentPanel.add(btnBuscarCEP);

		JLabel lblNewLabel_13 = new JLabel("Endere\u00E7o");
		lblNewLabel_13.setBounds(39, 281, 81, 14);
		contentPanel.add(lblNewLabel_13);

		txtForEndereco = new JTextField();
		txtForEndereco.setBounds(103, 278, 288, 20);
		contentPanel.add(txtForEndereco);
		txtForEndereco.setColumns(10);

		JLabel lblNewLabel_14 = new JLabel("N\u00FAmero");
		lblNewLabel_14.setBounds(400, 281, 46, 14);
		contentPanel.add(lblNewLabel_14);

		txtForNumero = new JTextField();
		txtForNumero.setBounds(454, 278, 66, 20);
		contentPanel.add(txtForNumero);
		txtForNumero.setColumns(10);

		JLabel lblNewLabel_15 = new JLabel("Bairro");
		lblNewLabel_15.setBounds(39, 320, 89, 14);
		contentPanel.add(lblNewLabel_15);

		txtForBairro = new JTextField();
		txtForBairro.setBounds(82, 317, 265, 20);
		contentPanel.add(txtForBairro);
		txtForBairro.setColumns(10);

		JLabel lblNewLabel_16 = new JLabel("Cidade");
		lblNewLabel_16.setBounds(355, 320, 46, 14);
		contentPanel.add(lblNewLabel_16);

		txtForCidade = new JTextField();
		txtForCidade.setBounds(406, 317, 269, 20);
		contentPanel.add(txtForCidade);
		txtForCidade.setColumns(10);

		JLabel lblNewLabel_17 = new JLabel("UF");
		lblNewLabel_17.setBounds(678, 320, 46, 14);
		contentPanel.add(lblNewLabel_17);

		cboForUF = new JComboBox();
		cboForUF.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cboForUF.setModel(new DefaultComboBoxModel(
				new String[] { "", "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA",
						"PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO" }));
		cboForUF.setBounds(703, 315, 48, 22);
		contentPanel.add(cboForUF);

		JLabel lblNewLabel_18 = new JLabel("Observa\u00E7\u00E3o");
		lblNewLabel_18.setBounds(39, 378, 81, 14);
		contentPanel.add(lblNewLabel_18);

		JTextArea txtForObs = new JTextArea();
		txtForObs.setBorder(new LineBorder(Color.LIGHT_GRAY));
		txtForObs.setBounds(121, 354, 294, 66);
		contentPanel.add(txtForObs);

		btnAdicionar = new JButton("");
		btnAdicionar.setEnabled(false);
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adicionarFornecedores();
			}
		});
		btnAdicionar.setIcon(new ImageIcon(Fornecedores.class.getResource("/img/create.png")));
		btnAdicionar.setContentAreaFilled(false);
		btnAdicionar.setBorderPainted(false);
		btnAdicionar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAdicionar.setBounds(474, 356, 64, 64);
		contentPanel.add(btnAdicionar);

		btnAlterar = new JButton("");
		btnAlterar.setEnabled(false);
		btnAlterar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alterarFornecedor();
			}
		});
		btnAlterar.setIcon(new ImageIcon(Fornecedores.class.getResource("/img/update.png")));
		btnAlterar.setContentAreaFilled(false);
		btnAlterar.setBorderPainted(false);
		btnAlterar.setBounds(570, 354, 64, 64);
		contentPanel.add(btnAlterar);

		btnExcluir = new JButton("");
		btnExcluir.setEnabled(false);
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				excluirFornecedor();
			}
		});
		btnExcluir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnExcluir.setIcon(new ImageIcon(Fornecedores.class.getResource("/img/cancelar.png")));
		btnExcluir.setContentAreaFilled(false);
		btnExcluir.setBorderPainted(false);
		btnExcluir.setBounds(670, 354, 64, 64);
		contentPanel.add(btnExcluir);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(39, 45, 712, 69);
		contentPanel.add(scrollPane);

		tblFornecedores = new JTable();
		tblFornecedores.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// evento clicar com o mouse na tabela
				setarCaixasTexto();
			}
		});
		scrollPane.setViewportView(tblFornecedores);

		JButton btnPesquisar = new JButton("Buscar");
		btnPesquisar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pesquisarFornecedores();
			}
		});
		btnPesquisar.setBounds(138, 121, 86, 23);
		contentPanel.add(btnPesquisar);

		JLabel lblNewLabel_14_1 = new JLabel("Complemento");
		lblNewLabel_14_1.setBounds(533, 281, 89, 14);
		contentPanel.add(lblNewLabel_14_1);

		txtForComplemento = new JTextField();
		txtForComplemento.setColumns(10);
		txtForComplemento.setBounds(618, 278, 133, 20);
		contentPanel.add(txtForComplemento);

		// Valida��o com o uso da biblioteca Atxy2k
		// txtForCNPJ
		RestrictedTextField validarCNPJ = new RestrictedTextField(txtForCNPJ);
		validarCNPJ.setOnlyNums(true);
		validarCNPJ.setLimit(20);
		// txtForIE
		RestrictedTextField validarIE = new RestrictedTextField(txtForIE);
		validarIE.setOnlyNums(true);
		validarIE.setLimit(15);
		// txtForIM
		RestrictedTextField validarIM = new RestrictedTextField(txtForIM);
		validarIM.setOnlyNums(true);
		validarIM.setLimit(15);
		// txtForRazao
		RestrictedTextField validarRazao = new RestrictedTextField(txtForRazao);
		validarRazao.setLimit(50);
		// txtForFantasia
		RestrictedTextField validarFantasia = new RestrictedTextField(txtForFantasia);
		validarFantasia.setLimit(50);
		// txtForSite
		RestrictedTextField validarSite = new RestrictedTextField(txtForSite);
		validarSite.setLimit(50);
		// txtForFone
		RestrictedTextField validarFone = new RestrictedTextField(txtForFone);
		validarFone.setOnlyNums(true);
		validarFone.setLimit(20);
		// txtForContato
		RestrictedTextField validarContato = new RestrictedTextField(txtForContato);
		validarContato.setLimit(50);
		// txtForEmail
		RestrictedTextField validarEmail = new RestrictedTextField(txtForEmail);
		validarEmail.setLimit(50);
		// txtForCEP
		RestrictedTextField validarCEP = new RestrictedTextField(txtForCEP);
		validarCEP.setOnlyNums(true);
		validarCEP.setLimit(10);
		// txtForEndereco
		RestrictedTextField validarEndereco = new RestrictedTextField(txtForEndereco);
		validarEndereco.setLimit(100);
		// txtForComplemento
		RestrictedTextField validarComplemento = new RestrictedTextField(txtForComplemento);
		validarComplemento.setLimit(100);
		// txtForBairro
		RestrictedTextField validarBairro = new RestrictedTextField(txtForBairro);
		validarBairro.setLimit(50);
		// txtForCidade
		RestrictedTextField validarCidade = new RestrictedTextField(txtForCidade);
		validarCidade.setLimit(50);

	}// fim do construtor

	// criar objeto para acessar o banco
	DAO dao = new DAO();
	private JButton btnAlterar;
	private JButton btnExcluir;
	private JButton btnAdicionar;

	/**
	 * M�todo respons�vel pela pesquisa avan�ada do fornecedor usando o nome de
	 * fantasia e a biblioteca rs2xml
	 */

	private void pesquisarFornecedoresTabela() {
		String readT = "select idfor as ID, fantasia as Fornecedor, fone as Telefone, contato as Contato from fornecedores where fantasia like ?";
		try {
			// Estabelecer a conex�o
			Connection con = dao.conectar();
			// Preparar a execu��o da Query
			PreparedStatement pst = con.prepareStatement(readT);
			// Setar o argumento (fantasia)
			// Substituir o ? pelo conte�do da caixa de texto
			pst.setString(1, txtPesquisarFornecedor.getText() + "%");
			ResultSet rs = pst.executeQuery();
			rs = pst.executeQuery();
			// uso da biblioteca rs2xml para "popular" a tabela
			tblFornecedores.setModel(DbUtils.resultSetToTableModel(rs));
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * M�todo respons�vel por setar os campos da tabela de acordo com a tabela
	 */

	private void setarCaixasTexto() {
		int setar = tblFornecedores.getSelectedRow();
		txtForId.setText(tblFornecedores.getModel().getValueAt(setar, 0).toString());
		txtForFantasia.setText(tblFornecedores.getModel().getValueAt(setar, 1).toString());
		txtForFone.setText(tblFornecedores.getModel().getValueAt(setar, 2).toString());
		txtForContato.setText(tblFornecedores.getModel().getValueAt(setar, 3).toString());

	}

	/**
	 * M�todo responsável por setar os campos da tabela de acordo com o id dos
	 * fornecedores
	 */

	private void pesquisarFornecedores() {
		if (txtForId.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite o ID do fornecedor");
			txtForId.requestFocus();
		} else {
			// lógica principal
			// query principal ( Instrução SQL)
			String read = "select * from fornecedores where idfor = ?";
			// tratar excessões sempre que lidar com o banco
			try {
				// estabelecer a conexão
				Connection con = dao.conectar();
				// Preparar a execução da Query
				PreparedStatement pst = con.prepareStatement(read);
				// Setar o argumento (id)
				// Substituir o ? pelo conteúdo da caixa de texto
				pst.setString(1, txtForId.getText());
				// Executar a query e exibir o resultado no formulário
				ResultSet rs = pst.executeQuery();
				// Validação (existência de fornecedor)
				// rs.next() -> existência de fornecedor
				limparCampos();
				if (rs.next()) {
					// preencher(setar) os campos do formulario
					txtForId.setText(rs.getString(1));
					txtForCNPJ.setText(rs.getString(2));
					txtForIE.setText(rs.getString(3));
					txtForIM.setText(rs.getString(4));
					txtForRazao.setText(rs.getString(5));
					txtForFantasia.setText(rs.getString(6));
					txtForSite.setText(rs.getString(7));
					txtForFone.setText(rs.getString(8));
					txtForContato.setText(rs.getString(9));
					txtForEmail.setText(rs.getString(10));
					txtForCEP.setText(rs.getString(11));
					txtForEndereco.setText(rs.getString(12));
					txtForNumero.setText(rs.getString(13));
					txtForComplemento.setText(rs.getString(14));
					txtForBairro.setText(rs.getString(15));
					txtForCidade.setText(rs.getString(16));
					cboForUF.setSelectedItem(rs.getString(17));
					
					btnAlterar.setEnabled(true);
					btnExcluir.setEnabled(true);
				} else {
					JOptionPane.showMessageDialog(null, "Fornecedor não cadastrado");
					limparCampos();
					btnAdicionar.setEnabled(true);
				}
				con.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}
	
	/**
	 * Método responsável por adicionar um fornecedor ao banco
	 */

	private void adicionarFornecedores() {

		if (txtForCNPJ.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Informe o CNPJ");
			txtForCNPJ.requestFocus();
		} else if (txtForRazao.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Informe a raz�o social do fornecedor");
			txtForRazao.requestFocus();
		} else if (txtForFantasia.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Informe o nome fantasia do fornecedor");
			txtForFantasia.requestFocus();
		} else if (txtForFone.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Informe o telefone do fornecedor");
			txtForFone.requestFocus();
		} else if (txtForCEP.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Informe o CEP do fornecedor");
			txtForCEP.requestFocus();
		} else if (txtForEndereco.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Informe o endere�o do fornecedor");
			txtForEndereco.requestFocus();
		} else if (txtForBairro.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Informe o bairro do fornecedor");
			txtForBairro.requestFocus();
		} else if (txtForCidade.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Informe a cidade do fornecedor");
			txtForCidade.requestFocus();
		} else {
			// l�gica principal
			String create = "insert into fornecedores (cnpj,ie,im,razao,fantasia,site,fone,contato,email,cep,endereco,numero,complemento,bairro,cidade,uf) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			try {
				// Estabelecer a conex�o
				Connection con = dao.conectar();
				// Preparar a execu��o da Query
				PreparedStatement pst = con.prepareStatement(create);
				// Substituir o ? pelo conte�do da caixa de texto
				pst.setString(1, txtForCNPJ.getText());
				pst.setString(2, txtForIE.getText());
				pst.setString(3, txtForIM.getText());
				pst.setString(4, txtForRazao.getText());
				pst.setString(5, txtForFantasia.getText());
				pst.setString(6, txtForSite.getText());
				pst.setString(7, txtForFone.getText());
				pst.setString(8, txtForContato.getText());
				pst.setString(9, txtForEmail.getText());
				pst.setString(10, txtForCEP.getText());
				pst.setString(11, txtForEndereco.getText());
				pst.setString(12, txtForNumero.getText());
				pst.setString(13, txtForComplemento.getText());
				pst.setString(14, txtForBairro.getText());
				pst.setString(15, txtForCidade.getText());
				pst.setString(16, cboForUF.getSelectedItem().toString());
				// Executar a query e inserir o fornecedor no banco
				pst.executeUpdate();
				// limpar campos
				limparCampos();
				// confirma��o
				JOptionPane.showMessageDialog(null, "Fornecedor adicionado com sucesso");
				// Encerrar a conex�o
				con.close();
			} catch (SQLIntegrityConstraintViolationException ex) {
					JOptionPane.showMessageDialog(null, "CNPJ em uso.\nDigite outro CNPJ");
					txtForCNPJ.setText(null);
					txtForCNPJ.requestFocus();
			} catch (Exception e) {
				System.out.println(e);				
			}
		}
	}
	
	/**
	 * M�todo responsavel por alterar os dados de um fornecedor do banco
	 */

	private void alterarFornecedor() {
		// valida��o
		if (txtForCNPJ.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Informe o CNPJ");
			txtForCNPJ.requestFocus();
		} else if (txtForRazao.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Informe a raz�o social do fornecedor");
			txtForRazao.requestFocus();
		} else if (txtForFantasia.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Informe o nome fantasia do fornecedor");
			txtForFantasia.requestFocus();
		} else if (txtForFone.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Informe o telefone do fornecedor");
			txtForFone.requestFocus();
		} else if (txtForCEP.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Informe o CEP do fornecedor");
			txtForCEP.requestFocus();
		} else if (txtForEndereco.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Informe o endere�o do fornecedor");
			txtForEndereco.requestFocus();
		} else if (txtForBairro.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Informe o bairro do fornecedor");
			txtForBairro.requestFocus();
		} else if (txtForCidade.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Informe a cidade do fornecedor");
			txtForCidade.requestFocus();
		} else {
			// l�gica principal
			String update = "update fornecedores set cnpj=?, ie=?,im=?,razao=?,fantasia=?,site=?,fone=?,contato=?,email=?,cep=?,endereco=?,numero=?,complemento=?,bairro=?,cidade=?,uf=? where idfor=?";
			try {
				// Estabelecer a conexão
				Connection con = dao.conectar();
				// Preparar a execução da Query
				PreparedStatement pst = con.prepareStatement(update);
				// Substituir o ? pelo conteúdo da caixa de texto
				pst.setString(1, txtForCNPJ.getText());
				pst.setString(2, txtForIE.getText());
				pst.setString(3, txtForIM.getText());
				pst.setString(4, txtForRazao.getText());
				pst.setString(5, txtForFantasia.getText());
				pst.setString(6, txtForSite.getText());
				pst.setString(7, txtForFone.getText());
				pst.setString(8, txtForContato.getText());
				pst.setString(9, txtForEmail.getText());
				pst.setString(10, txtForCEP.getText());
				pst.setString(11, txtForEndereco.getText());
				pst.setString(12, txtForNumero.getText());
				pst.setString(13, txtForComplemento.getText());
				pst.setString(14, txtForBairro.getText());
				pst.setString(15, txtForCidade.getText());
				pst.setString(16, cboForUF.getSelectedItem().toString());
				pst.setString(17, txtForId.getText());
				// Executar a query e alterar o fornecedor no banco
				pst.executeUpdate();
				// confirmação
				limparCampos();
				JOptionPane.showMessageDialog(null, "Fornecedor alterado com sucesso");
				// Encerrar a conexão
				con.close();
			} catch (SQLIntegrityConstraintViolationException ex) {
				JOptionPane.showMessageDialog(null, "CNPJ em uso.\nEscolha outro CNPJ");
				txtForCNPJ.setText(null);
				txtForCNPJ.requestFocus();
			} catch (Exception e) {
				System.out.println(e);
			}
		}

	}
	
	/**
	 * M�todo responsavel por excluir um fornecedor do banco
	 */

	private void excluirFornecedor() {
		// validação (confirmação da exclusão)
		int confirma = JOptionPane.showConfirmDialog(null, "Confirma a exclusão do fornecedor?", "Atenção!",
				JOptionPane.YES_NO_OPTION);
		if (confirma == JOptionPane.YES_OPTION) {
			String delete = "delete from fornecedores where idfor=?";
			try {
				// Estabelecer a conexão
				Connection con = dao.conectar();
				// Preparar a execução da Query
				PreparedStatement pst = con.prepareStatement(delete);
				// Substituir o ? pelo conte�do da caixa de texto
				pst.setString(1, txtForId.getText());
				// Executar a query e excluir o cliente do banco
				pst.executeUpdate();
				// confirmação
				limparCampos();
				JOptionPane.showMessageDialog(null, "Fornecedor excluido com sucesso");
				// Encerrar a conexão
				con.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}

	}
	
	/**
	 * Botão buscar CEP
	 */
	
	private void buscarCEP() {
        String logradouro = "";
        String tipoLogradouro = "";
        String resultado = null;
        String cep = txtForCEP.getText();
        try {
            URL url = new URL("http://cep.republicavirtual.com.br/web_cep.php?cep=" + cep + "&formato=xml");
            SAXReader xml = new SAXReader();
            Document documento = xml.read(url);
            Element root = documento.getRootElement();
            for (Iterator<Element> it = root.elementIterator(); it.hasNext();) {
                Element element = it.next();
                if (element.getQualifiedName().equals("cidade")) {
                    txtForCidade.setText(element.getText());
                }
                if (element.getQualifiedName().equals("bairro")) {
                    txtForBairro.setText(element.getText());
                }
                if (element.getQualifiedName().equals("uf")) {
                    cboForUF.setSelectedItem(element.getText()); 
                }
                if (element.getQualifiedName().equals("tipo_logradouro")) {
                    tipoLogradouro = element.getText();
                }
                if (element.getQualifiedName().equals("logradouro")) {
                    logradouro = element.getText();
                }
                if (element.getQualifiedName().equals("resultado")) {
                    resultado = element.getText();
                    if (resultado.equals("1")) {

                    } else {
                        JOptionPane.showMessageDialog(null, "CEP não encontrado");
                    }
                }

            }
            // Setar Campo Endereço
            txtForEndereco.setText(tipoLogradouro + " " + logradouro);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
	

	/**
	 * Limpar campos
	 */
	private void limparCamposFornecedor() {
		// limpar tabela
		((DefaultTableModel) tblFornecedores.getModel()).setRowCount(0);
	}
	
	private void limparCampos() {
		 txtForCNPJ.setText(null);
	     txtForIE.setText(null);
	     txtForIM.setText(null);
	     txtForRazao.setText(null);
	     txtForFantasia.setText(null);
	     txtForSite.setText(null);
	     txtForFone.setText(null);
	     txtForContato.setText(null);
	     txtForEmail.setText(null);
	     txtForCEP.setText(null);
	     txtForEndereco.setText(null);
	     txtForNumero.setText(null);
	     txtForComplemento.setText(null);
	     txtForBairro.setText(null);
	     txtForCidade.setText(null);
	     cboForUF.setSelectedItem("");
	     txtForId.setText(null);
	     btnAdicionar.setEnabled(false);
	     btnAlterar.setEnabled(false);
	     btnExcluir.setEnabled(false);
	}

}