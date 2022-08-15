package view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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

import model.DAO;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
		cboForUF.setModel(new DefaultComboBoxModel(new String[] {"", "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO"}));
		cboForUF.setBounds(703, 315, 48, 22);
		contentPanel.add(cboForUF);
		
		JLabel lblNewLabel_18 = new JLabel("Observa\u00E7\u00E3o");
		lblNewLabel_18.setBounds(39, 378, 81, 14);
		contentPanel.add(lblNewLabel_18);
		
		JTextArea txtForObs = new JTextArea();
		txtForObs.setBorder(new LineBorder(Color.LIGHT_GRAY));
		txtForObs.setBounds(121, 354, 294, 66);
		contentPanel.add(txtForObs);
		
		JButton btnAdicionar = new JButton("");
		btnAdicionar.setIcon(new ImageIcon(Fornecedores.class.getResource("/img/create.png")));
		btnAdicionar.setContentAreaFilled(false);
		btnAdicionar.setBorderPainted(false);
		btnAdicionar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAdicionar.setBounds(474, 356, 64, 64);
		contentPanel.add(btnAdicionar);
		
		JButton btnAlterar = new JButton("");
		btnAlterar.setIcon(new ImageIcon(Fornecedores.class.getResource("/img/update.png")));
		btnAlterar.setContentAreaFilled(false);
		btnAlterar.setBorderPainted(false);
		btnAlterar.setBounds(570, 354, 64, 64);
		contentPanel.add(btnAlterar);
		
		JButton btnExcluir = new JButton("");
		btnExcluir.setIcon(new ImageIcon(Fornecedores.class.getResource("/img/cancelar.png")));
		btnExcluir.setContentAreaFilled(false);
		btnExcluir.setBorderPainted(false);
		btnExcluir.setBounds(670, 354, 64, 64);
		contentPanel.add(btnExcluir);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(39, 45, 712, 69);
		contentPanel.add(scrollPane);
		
		tblFornecedores = new JTable();
		scrollPane.setViewportView(tblFornecedores);
		
		JButton btnPesquisar = new JButton("Buscar");
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
	}// fim do construtor
	
	DAO dao = new DAO();
	
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
				// Validação (existência de usuário)
				// rs.next() -> existência de usuário
				if(rs.next()) {
					// preencher(setar) os campos do formulário
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
				}
				con.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}
	
}