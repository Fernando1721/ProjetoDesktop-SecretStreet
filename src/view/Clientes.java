package view;

import java.awt.Cursor;
import java.awt.Toolkit;
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
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import model.DAO;
import java.awt.event.ActionListener;
import java.net.URL;
import java.awt.event.ActionEvent;

public class Clientes extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtCliId;
	private JTextField txtCliNome;
	private JTextField txtCliFone;
	private JTextField txtCliCPF;
	private JTextField txtCliEmail;
	private JTextField txtCliCep;
	private JTextField txtCliEndereco;
	private JTextField txtCliNumero;
	private JTextField txtCliComplemento;
	private JTextField txtCliBairro;
	private JTextField txtCliCidade;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Clientes dialog = new Clientes();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Clientes() {
		setTitle("Clientes");
		setResizable(false);
		setModal(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Clientes.class.getResource("/img/client.png")));
		setBounds(100, 100, 830, 448);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 814, 409);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);

		JLabel lblNewLabel = new JLabel("ID");
		lblNewLabel.setBounds(38, 30, 46, 14);
		contentPanel.add(lblNewLabel);

		txtCliId = new JTextField();
		txtCliId.setBounds(64, 27, 86, 20);
		contentPanel.add(txtCliId);
		txtCliId.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Nome");
		lblNewLabel_1.setBounds(38, 128, 46, 14);
		contentPanel.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Data de nascimento");
		lblNewLabel_2.setBounds(371, 128, 165, 14);
		contentPanel.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Fone");
		lblNewLabel_3.setBounds(623, 128, 46, 14);
		contentPanel.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("CPF");
		lblNewLabel_4.setBounds(38, 168, 46, 14);
		contentPanel.add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel("E-mail");
		lblNewLabel_5.setBounds(250, 168, 46, 14);
		contentPanel.add(lblNewLabel_5);

		JLabel lblNewLabel_6 = new JLabel("Marketing");
		lblNewLabel_6.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblNewLabel_6.setBounds(670, 168, 94, 14);
		contentPanel.add(lblNewLabel_6);

		JLabel lblNewLabel_7 = new JLabel("CEP");
		lblNewLabel_7.setBounds(38, 208, 46, 14);
		contentPanel.add(lblNewLabel_7);

		JLabel lblNewLabel_8 = new JLabel("Endere\u00E7o");
		lblNewLabel_8.setBounds(316, 208, 66, 14);
		contentPanel.add(lblNewLabel_8);

		JLabel lblNewLabel_9 = new JLabel("N\u00FAmero");
		lblNewLabel_9.setBounds(38, 247, 46, 14);
		contentPanel.add(lblNewLabel_9);

		JLabel lblNewLabel_10 = new JLabel("Complemento");
		lblNewLabel_10.setBounds(183, 247, 94, 14);
		contentPanel.add(lblNewLabel_10);

		JLabel lblNewLabel_11 = new JLabel("Bairro");
		lblNewLabel_11.setBounds(475, 247, 46, 14);
		contentPanel.add(lblNewLabel_11);

		JLabel lblNewLabel_12 = new JLabel("Cidade");
		lblNewLabel_12.setBounds(38, 292, 46, 14);
		contentPanel.add(lblNewLabel_12);

		JLabel lblNewLabel_13 = new JLabel("UF");
		lblNewLabel_13.setBounds(460, 292, 46, 14);
		contentPanel.add(lblNewLabel_13);

		txtCliNome = new JTextField();
		txtCliNome.setBounds(75, 125, 275, 20);
		contentPanel.add(txtCliNome);
		txtCliNome.setColumns(10);

		txtCliData = new JFormattedTextField();
		txtCliData.setBounds(488, 125, 115, 20);
		contentPanel.add(txtCliData);

		txtCliFone = new JTextField();
		txtCliFone.setBounds(655, 125, 127, 20);
		contentPanel.add(txtCliFone);
		txtCliFone.setColumns(10);

		txtCliCPF = new JTextField();
		txtCliCPF.setBounds(64, 165, 165, 20);
		contentPanel.add(txtCliCPF);
		txtCliCPF.setColumns(10);

		txtCliEmail = new JTextField();
		txtCliEmail.setBounds(290, 165, 358, 20);
		contentPanel.add(txtCliEmail);
		txtCliEmail.setColumns(10);

		cboCliMkt = new JComboBox();
		cboCliMkt.setModel(new DefaultComboBoxModel(new String[] { "", "Sim", "N\u00E3o" }));
		cboCliMkt.setBounds(731, 164, 51, 22);
		contentPanel.add(cboCliMkt);

		txtCliCep = new JTextField();
		txtCliCep.setBounds(64, 205, 99, 20);
		contentPanel.add(txtCliCep);
		txtCliCep.setColumns(10);

		JButton btnCliBuscarCep = new JButton("Buscar CEP");
		btnCliBuscarCep.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					// botao buscar cep
		        if (txtCliCep.getText().equals("")) {
		           JOptionPane.showMessageDialog(null, "Informe o CEP");
		           txtCliCep.requestFocus();
		        } else {
		           buscarCEP();
		        }
	        }
		});
		
		btnCliBuscarCep.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCliBuscarCep.setBounds(173, 204, 123, 23);
		contentPanel.add(btnCliBuscarCep);

		txtCliEndereco = new JTextField();
		txtCliEndereco.setBounds(374, 205, 408, 20);
		contentPanel.add(txtCliEndereco);
		txtCliEndereco.setColumns(10);

		txtCliNumero = new JTextField();
		txtCliNumero.setBounds(88, 244, 75, 20);
		contentPanel.add(txtCliNumero);
		txtCliNumero.setColumns(10);

		txtCliComplemento = new JTextField();
		txtCliComplemento.setBounds(270, 244, 188, 20);
		contentPanel.add(txtCliComplemento);
		txtCliComplemento.setColumns(10);

		txtCliBairro = new JTextField();
		txtCliBairro.setBounds(513, 244, 269, 20);
		contentPanel.add(txtCliBairro);
		txtCliBairro.setColumns(10);

		txtCliCidade = new JTextField();
		txtCliCidade.setBounds(82, 289, 358, 20);
		contentPanel.add(txtCliCidade);
		txtCliCidade.setColumns(10);

		cboCliUF = new JComboBox();
		cboCliUF.setModel(new DefaultComboBoxModel(
				new String[] { "", "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA",
						"PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO" }));
		cboCliUF.setBounds(499, 288, 56, 22);
		contentPanel.add(cboCliUF);

		btnCliAdicionar = new JButton("");
		btnCliAdicionar.setEnabled(false);
		btnCliAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adicionarClientes();
			}
		});
		btnCliAdicionar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCliAdicionar.setToolTipText("Adicionar");
		btnCliAdicionar.setIcon(new ImageIcon(Clientes.class.getResource("/img/create.png")));
		btnCliAdicionar.setContentAreaFilled(false);
		btnCliAdicionar.setBorderPainted(false);
		btnCliAdicionar.setBounds(290, 322, 64, 64);
		contentPanel.add(btnCliAdicionar);

		JButton btnNewButton_1_1 = new JButton("");
		btnNewButton_1_1.setContentAreaFilled(false);
		btnNewButton_1_1.setBorderPainted(false);
		btnNewButton_1_1.setBounds(254, 322, 64, 64);
		contentPanel.add(btnNewButton_1_1);

		btnCliAlterar = new JButton("");
		btnCliAlterar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCliAlterar.setEnabled(false);
		btnCliAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alterarCliente();
			}
		});
		btnCliAlterar.setIcon(new ImageIcon(Clientes.class.getResource("/img/update.png")));
		btnCliAlterar.setToolTipText("Adicionar");
		btnCliAlterar.setContentAreaFilled(false);
		btnCliAlterar.setBorderPainted(false);
		btnCliAlterar.setBounds(358, 322, 64, 64);
		contentPanel.add(btnCliAlterar);

		btnCliExcluir = new JButton("");
		btnCliExcluir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCliExcluir.setEnabled(false);
		btnCliExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				excluirCliente();
			}
		});
		btnCliExcluir.setIcon(new ImageIcon(Clientes.class.getResource("/img/cancelar.png")));
		btnCliExcluir.setToolTipText("Adicionar");
		btnCliExcluir.setContentAreaFilled(false);
		btnCliExcluir.setBorderPainted(false);
		btnCliExcluir.setBounds(432, 322, 64, 64);
		contentPanel.add(btnCliExcluir);

		btnBuscarId = new JButton("buscar ID");
		btnBuscarId.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnBuscarId.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pesquisarClientes();
			}
		});
		btnBuscarId.setBounds(160, 26, 89, 23);
		contentPanel.add(btnBuscarId);
	} // fim do costrutor

	DAO dao = new DAO();
	private JFormattedTextField txtCliData;
	private JComboBox cboCliMkt;
	private JComboBox cboCliUF;
	private JButton btnBuscarId;
	private JButton btnCliAdicionar;
	private JButton btnCliAlterar;
	private JButton btnCliExcluir;

	/**
	 * M�todo responsável por setar os campos da tabela de acordo com o id dos
	 * clientes
	 */

	private void pesquisarClientes() {
		if (txtCliId.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite o ID do cliente");
			txtCliId.requestFocus();
		} else {
			// lógica principal
			// query principal ( Instrução SQL)
			String read = "select * from clientes where idCli = ?";
			// tratar excessões sempre que lidar com o banco
			try {
				// estabelecer a conexão
				Connection con = dao.conectar();
				// Preparar a execução da Query
				PreparedStatement pst = con.prepareStatement(read);
				// Setar o argumento (id)
				// Substituir o ? pelo conteúdo da caixa de texto
				pst.setString(1, txtCliId.getText());
				// Executar a query e exibir o resultado no formulário
				ResultSet rs = pst.executeQuery();
				// Validação (existência de clientes)
				// rs.next() -> existência de clientes
				limparCampos();
				if (rs.next()) {
					// preencher(setar) os campos do formulario
					txtCliId.setText(rs.getString(1));
					txtCliNome.setText(rs.getString(2));
					txtCliData.setText(rs.getString(3));
					txtCliFone.setText(rs.getString(4));
					txtCliCPF.setText(rs.getString(5));
					txtCliEmail.setText(rs.getString(6));
					cboCliMkt.setSelectedItem(rs.getString(7));
					txtCliCep.setText(rs.getString(8));
					txtCliEndereco.setText(rs.getString(9));
					txtCliNumero.setText(rs.getString(10));
					txtCliComplemento.setText(rs.getString(11));
					txtCliBairro.setText(rs.getString(12));
					txtCliCidade.setText(rs.getString(13));
					cboCliUF.setSelectedItem(rs.getString(14));

					btnCliAlterar.setEnabled(true);
					btnCliExcluir.setEnabled(true);
				} else {
					JOptionPane.showMessageDialog(null, "Clientes não cadastrado");
					limparCampos();
					btnCliAdicionar.setEnabled(true);
				}
				con.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}

	/**
	 * Método responsável por adicionar um cliente ao banco
	 */

	private void adicionarClientes() {

		if (txtCliNome.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Informe o nome");
			txtCliNome.requestFocus();
		} else if (txtCliData.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Informe a data de nascimento do cliente");
			txtCliData.requestFocus();
		} else if (txtCliFone.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Informe o telefone do cliente");
			txtCliFone.requestFocus();
		} else if (txtCliCPF.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Informe o CPF do cliente");
			txtCliCPF.requestFocus();
		} else if (cboCliMkt.getSelectedItem().equals("")) {
			JOptionPane.showMessageDialog(null, "Informe se o cliente quer Marketing");
			cboCliMkt.requestFocus();
		} else {
			// l�gica principal
			String create = "insert into clientes(nome,nascimento,fone,cpf,email,marketing,cep,endereco,numero,complemento,bairro,cidade,uf) values (?,?,?,?,?,?,?,?,?,?,?,?,?)";
			try {
				// Estabelecer a conex�o
				Connection con = dao.conectar();
				// Preparar a execu��o da Query
				PreparedStatement pst = con.prepareStatement(create);
				// Substituir o ? pelo conte�do da caixa de texto
				pst.setString(1, txtCliNome.getText());
				pst.setString(2, txtCliData.getText());
				pst.setString(3, txtCliFone.getText());
				pst.setString(4, txtCliCPF.getText());
				pst.setString(5, txtCliEmail.getText());
				pst.setString(6, cboCliMkt.getSelectedItem().toString());
				pst.setString(7, txtCliCep.getText());
				pst.setString(8, txtCliEndereco.getText());
				pst.setString(9, txtCliNumero.getText());
				pst.setString(10, txtCliComplemento.getText());
				pst.setString(11, txtCliBairro.getText());
				pst.setString(12, txtCliCidade.getText());
				pst.setString(13, cboCliUF.getSelectedItem().toString());
				// Executar a query e inserir o cliente no banco
				pst.executeUpdate();
				// limpar campos
				// limparCampos();
				// confirma��o
				JOptionPane.showMessageDialog(null, "Cliente adicionado com sucesso");
				// Encerrar a conex�o
				con.close();
			} catch (SQLIntegrityConstraintViolationException ex) {
				JOptionPane.showMessageDialog(null, "CPF em uso.\nDigite outro CPF");
				txtCliCPF.setText(null);
				txtCliCPF.requestFocus();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}

	/**
	 * M�todo responsavel por alterar os dados de um fornecedor do banco
	 */

	private void alterarCliente() {
		// valida��o
		if (txtCliNome.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Informe o nome");
			txtCliNome.requestFocus();
		} else if (txtCliData.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Informe a data de nascimento do cliente");
			txtCliData.requestFocus();
		} else if (txtCliFone.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Informe o telefone do cliente");
			txtCliFone.requestFocus();
		} else if (txtCliCPF.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Informe o CPF do cliente");
			txtCliCPF.requestFocus();
		} else if (cboCliMkt.getSelectedItem().equals("")) {
			JOptionPane.showMessageDialog(null, "Informe se o cliente quer Marketing");
			cboCliMkt.requestFocus();
		} else {
			// l�gica principal
			String update = "update clientes set nome=?, nascimento=?, fone=?, cpf=?, email=?, marketing=?, cep=?, endereco=?, numero=?, complemento=?, bairro=?, cidade=?, uf=? where idCli=?";
			try {
				// Estabelecer a conexão
				Connection con = dao.conectar();
				// Preparar a execução da Query
				PreparedStatement pst = con.prepareStatement(update);
				// Substituir o ? pelo conteúdo da caixa de texto
				pst.setString(1, txtCliNome.getText());
				pst.setString(2, txtCliData.getText());
				pst.setString(3, txtCliFone.getText());
				pst.setString(4, txtCliCPF.getText());
				pst.setString(5, txtCliEmail.getText());
				pst.setString(6, cboCliMkt.getSelectedItem().toString());
				pst.setString(7, txtCliCep.getText());
				pst.setString(8, txtCliEndereco.getText());
				pst.setString(9, txtCliNumero.getText());
				pst.setString(10, txtCliComplemento.getText());
				pst.setString(11, txtCliBairro.getText());
				pst.setString(12, txtCliCidade.getText());
				pst.setString(13, cboCliUF.getSelectedItem().toString());
				pst.setString(14, txtCliId.getText());
				// Executar a query e alterar o cliente no banco
				pst.executeUpdate();
				// confirmação
				limparCampos();
				JOptionPane.showMessageDialog(null, "Cliente alterado com sucesso");
				// Encerrar a conexão
				con.close();
			} catch (SQLIntegrityConstraintViolationException ex) {
				JOptionPane.showMessageDialog(null, "CPF em uso.\nEscolha outro CPF");
				txtCliCPF.setText(null);
				txtCliCPF.requestFocus();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}

	/**
	 * M�todo responsavel por excluir um cliente do banco
	 */

	private void excluirCliente() {
		// validação (confirmação da exclusão)
		int confirma = JOptionPane.showConfirmDialog(null, "Confirma a exclusão do cliente?", "Atenção!",
				JOptionPane.YES_NO_OPTION);
		if (confirma == JOptionPane.YES_OPTION) {
			String delete = "delete from clientes where idCli=?";
			try {
				// Estabelecer a conexão
				Connection con = dao.conectar();
				// Preparar a execução da Query
				PreparedStatement pst = con.prepareStatement(delete);
				// Substituir o ? pelo conte�do da caixa de texto
				pst.setString(1, txtCliId.getText());
				// Executar a query e excluir o cliente do banco
				pst.executeUpdate();
				// confirmação
				limparCampos();
				JOptionPane.showMessageDialog(null, "Cliente excluido com sucesso");
				// Encerrar a conexão
				con.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}

	private void buscarCEP() {
		String logradouro = "";
		String tipoLogradouro = "";
		String resultado = null;
		String cep = txtCliCep.getText();
		try {
			URL url = new URL("http://cep.republicavirtual.com.br/web_cep.php?cep=" + cep + "&formato=xml");
			SAXReader xml = new SAXReader();
			Document documento = xml.read(url);
			Element root = documento.getRootElement();
			for (Iterator<Element> it = root.elementIterator(); it.hasNext();) {
				Element element = it.next();
				if (element.getQualifiedName().equals("cidade")) {
					txtCliCidade.setText(element.getText());
				}
				if (element.getQualifiedName().equals("bairro")) {
					txtCliBairro.setText(element.getText());
				}
				if (element.getQualifiedName().equals("uf")) {
					cboCliUF.setSelectedItem(element.getText());
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
			txtCliEndereco.setText(tipoLogradouro + " " + logradouro);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	private void limparCampos() {
		txtCliId.setText(null);
		txtCliNome.setText(null);
		txtCliData.setText(null);
		txtCliFone.setText(null);
		txtCliCPF.setText(null);
		txtCliEmail.setText(null);
		cboCliMkt.setSelectedItem("");
		txtCliCep.setText(null);
		txtCliEndereco.setText(null);
		txtCliNumero.setText(null);
		txtCliComplemento.setText(null);
		txtCliBairro.setText(null);
		txtCliCidade.setText(null);
		cboCliUF.setSelectedItem("");
		btnCliAdicionar.setEnabled(false);
		btnCliAlterar.setEnabled(false);
		btnCliExcluir.setEnabled(false);
	}
}
