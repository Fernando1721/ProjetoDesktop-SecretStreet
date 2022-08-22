package view;

import java.awt.Cursor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Atxy2k.CustomTextField.RestrictedTextField;
import model.DAO;
import javax.swing.JCheckBox;

public class Usuarios extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtUsuId;
	private JTextField txtUsuNome;
	private JPasswordField txtUsuSenha;
	private JTextField txtUsuLogin;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Usuarios dialog = new Usuarios();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Usuarios() {
		setModal(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Usuarios.class.getResource("/img/user.png")));
		setTitle("Usu\u00E1rios");
		setResizable(false);
		setBounds(100, 100, 450, 327);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 434, 288);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);

		JLabel lblNewLabel = new JLabel("ID");
		lblNewLabel.setBounds(61, 43, 39, 14);
		contentPanel.add(lblNewLabel);

		txtUsuId = new JTextField();
		txtUsuId.setEditable(false);
		txtUsuId.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		txtUsuId.setBounds(110, 40, 96, 20);
		contentPanel.add(txtUsuId);
		txtUsuId.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Usu\u00E1rio");
		lblNewLabel_1.setBounds(61, 115, 46, 14);
		contentPanel.add(lblNewLabel_1);

		txtUsuNome = new JTextField();
		txtUsuNome.setBounds(110, 112, 247, 20);
		contentPanel.add(txtUsuNome);
		txtUsuNome.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Login");
		lblNewLabel_2.setBounds(61, 90, 46, 14);
		contentPanel.add(lblNewLabel_2);

		txtUsuSenha = new JPasswordField();
		txtUsuSenha.setBounds(110, 137, 145, 20);
		contentPanel.add(txtUsuSenha);

		JLabel lblNewLabel_3 = new JLabel("Senha");
		lblNewLabel_3.setBounds(61, 140, 46, 14);
		contentPanel.add(lblNewLabel_3);

		txtUsuLogin = new JTextField();
		txtUsuLogin.setBounds(110, 87, 96, 20);
		contentPanel.add(txtUsuLogin);
		txtUsuLogin.setColumns(10);

		cboUsuPerfil = new JComboBox();
		cboUsuPerfil.setEditable(true);
		cboUsuPerfil.setModel(new DefaultComboBoxModel(new String[] { "", "admin", "user" }));
		cboUsuPerfil.setBounds(110, 161, 92, 22);
		contentPanel.add(cboUsuPerfil);

		JLabel lblNewLabel_4 = new JLabel("perfil");
		lblNewLabel_4.setBounds(61, 165, 46, 14);
		contentPanel.add(lblNewLabel_4);

		btnAdicionar = new JButton("");
		btnAdicionar.setEnabled(false);
		btnAdicionar.setToolTipText("adicionar");
		btnAdicionar.setIcon(new ImageIcon(Usuarios.class.getResource("/img/create.png")));
		btnAdicionar.setContentAreaFilled(false);
		btnAdicionar.setBorderPainted(false);
		btnAdicionar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adicionarUsuario();
			}
		});
		btnAdicionar.setBounds(81, 197, 64, 64);
		contentPanel.add(btnAdicionar);

		btnAlterar = new JButton("");
		btnAlterar.setEnabled(false);
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// l�gica para verificar o checkbox
				if (chkSenha.isSelected()) {
					alterarUsuarioSenha();
				} else {
					alterarUsuario();
				}

			}
		});
		btnAlterar.setToolTipText("alterar");
		btnAlterar.setIcon(new ImageIcon(Usuarios.class.getResource("/img/update.png")));
		btnAlterar.setContentAreaFilled(false);
		btnAlterar.setBorderPainted(false);
		btnAlterar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAlterar.setBounds(183, 197, 64, 64);
		contentPanel.add(btnAlterar);

		btnExcluir = new JButton("");
		btnExcluir.setEnabled(false);
		btnExcluir.setIcon(new ImageIcon(Usuarios.class.getResource("/img/cancelar.png")));
		btnExcluir.setToolTipText("excluir");
		btnExcluir.setContentAreaFilled(false);
		btnExcluir.setBorderPainted(false);
		btnExcluir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				excluirUsuario();
			}
		});
		btnExcluir.setBounds(282, 197, 64, 64);
		contentPanel.add(btnExcluir);

		JButton btnPesquisar = new JButton("");
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pesquisarUsuario();

			}
		});
		btnPesquisar.setIcon(new ImageIcon(Usuarios.class.getResource("/img/procurar.png")));
		btnPesquisar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnPesquisar.setContentAreaFilled(false);
		btnPesquisar.setBorderPainted(false);
		btnPesquisar.setToolTipText("procurar");
		btnPesquisar.setBounds(216, 75, 32, 32);
		contentPanel.add(btnPesquisar);

		// Valida��o com o uso da biblioteca Atxy2k
		// txtUsuId
		RestrictedTextField validarId = new RestrictedTextField(txtUsuId);
		validarId.setOnlyNums(true);
		validarId.setLimit(4);
		// txtUsuNome
		RestrictedTextField validarNome = new RestrictedTextField(txtUsuNome);
		validarNome.setLimit(50);
		// txtUsuLogin
		RestrictedTextField validarLogin = new RestrictedTextField(txtUsuLogin);
		validarLogin.setLimit(15);
		// txtUsuSenhas
		RestrictedTextField validarSenha = new RestrictedTextField(txtUsuSenha);

		chkSenha = new JCheckBox("Alterar senha");
		chkSenha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtUsuSenha.setText(null);
				txtUsuSenha.requestFocus();
				txtUsuSenha.setEditable(true);
			}
		});
		chkSenha.setVisible(false);
		chkSenha.setBounds(261, 136, 121, 23);
		contentPanel.add(chkSenha);
		validarSenha.setLimit(255);

	} // Fim do construtor

	DAO dao = new DAO();
	private JComboBox cboUsuPerfil;
	private JButton btnAdicionar;
	private JButton btnAlterar;
	private JButton btnExcluir;
	private JCheckBox chkSenha;

	/**
	 * M�todo respons�vel pela pesquisa de usu�rios
	 */

	private void pesquisarUsuario() {
		// valida��o
		if (txtUsuLogin.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite o login do usu�rio");
			txtUsuLogin.requestFocus();
		} else {
			// l�gica principal
			// Query (instru��o SQL)
			String read = "select * from usuarios where login = ?";
			// tratar excess�es sempre que lidar com o banco
			try {
				// Estabelecer a conex�o
				Connection con = dao.conectar();
				// Preparar a execu��o da Query
				PreparedStatement pst = con.prepareStatement(read);
				// Setar o argumento (id)
				// Substituir o ? pelo conte�do da caixa de texto
				pst.setString(1, txtUsuLogin.getText());
				// Executar a query e exibir o resultado no formul�rio
				ResultSet rs = pst.executeQuery();
				// Valida��o (exist�ncia de usu�rio)
				// rs.next() -> exist�ncia de usu�rio
				limparCampos();
				if (rs.next()) {
					// preencher(setar) os campos do formul�rio
					txtUsuId.setText(rs.getString(1));
					txtUsuNome.setText(rs.getString(2));
					txtUsuLogin.setText(rs.getString(3));
					cboUsuPerfil.setSelectedItem(rs.getString(5));
					txtUsuSenha.setText(rs.getString(4));
					btnAlterar.setEnabled(true);
					btnExcluir.setEnabled(true);
					chkSenha.setVisible(true);
					txtUsuSenha.setEditable(false);

				} else {
					JOptionPane.showMessageDialog(null, "Usu�rio n�o cadastrado");
					txtUsuNome.setEditable(true);
					cboUsuPerfil.setEnabled(true);
					txtUsuSenha.setEditable(true);
					txtUsuNome.requestFocus();
					btnAdicionar.setEnabled(true);
				}
				// NUNCA esquecer de encerrar a conex�o
				con.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}

	/**
	 * M�todo respons�vel por adicionar um novo usu�rio no banco
	 */
	private void adicionarUsuario() {
		// valida��o da senha (captura segura)
		String capturaSenha = new String(txtUsuSenha.getPassword());
		// valida��o
		if (txtUsuNome.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Coloque as informa��es do usu�rio");
			txtUsuNome.requestFocus();
		} else if (txtUsuLogin.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Coloque o login do usu�rio");
			txtUsuLogin.requestFocus();
		} else if (cboUsuPerfil.getSelectedItem().equals("")) {
			JOptionPane.showMessageDialog(null, "Coloque o perfil do usu�rio");
			cboUsuPerfil.requestFocus();
		} else if (txtUsuSenha.getPassword().length == 0) {
			JOptionPane.showMessageDialog(null, "Coloque a senha do usu�rio");
			txtUsuSenha.requestFocus();
		} else {
			// l�gica principal
			String create = "insert into usuarios(usuario,login,senha,perfil) values (?, ?,md5(?),?)";
			try {
				// Estabelecer a conex�o
				Connection con = dao.conectar();
				// Preparar a execu��o da Query
				PreparedStatement pst = con.prepareStatement(create);
				// Substituir o ? pelo conte�do da caixa de texto
				pst.setString(1, txtUsuNome.getText());
				pst.setString(2, txtUsuLogin.getText());
				pst.setString(3, capturaSenha);
				pst.setString(4, cboUsuPerfil.getSelectedItem().toString());
				// Executar a query e inserir o usu�rio no banco
				pst.executeUpdate();
				// confirma��o
				limparCampos();
				JOptionPane.showMessageDialog(null, "Usu�rio adicionado com sucesso");
				// Encerrar a conex�o
				con.close();
			} catch (SQLIntegrityConstraintViolationException ex) {
				JOptionPane.showMessageDialog(null, "Login em uso.\nEscolha outro login");
				txtUsuLogin.setText(null);
				txtUsuLogin.requestFocus();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}

	/**
	 * M�todo responsavel por alterar os dados de um usu�rio do banco exceto a senha
	 */

	private void alterarUsuario() {
		// valida��o
		if (txtUsuNome.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Coloque as informa��es do usu�rio");
			txtUsuNome.requestFocus();
		} else if (txtUsuLogin.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Coloque o login do usu�rio");
			txtUsuLogin.requestFocus();
		} else if (cboUsuPerfil.getSelectedItem().equals("")) {
			JOptionPane.showMessageDialog(null, "Coloque o perfil do usu�rio");
			cboUsuPerfil.requestFocus();
		} else {
			// l�gica principal
			String update = "update usuarios set usuario=?, login=?, perfil=? where idusu=?;";
			try {
				// Estabelecer a conex�o
				Connection con = dao.conectar();
				// Preparar a execu��o da Query
				PreparedStatement pst = con.prepareStatement(update);
				// Substituir o ? pelo conte�do da caixa de texto
				pst.setString(1, txtUsuNome.getText());
				pst.setString(2, txtUsuLogin.getText());
				pst.setString(3, cboUsuPerfil.getSelectedItem().toString());
				pst.setString(4, txtUsuId.getText());
				// Executar a query e alterar o usu�rio no banco
				pst.executeUpdate();
				// confirma��o
				limparCampos();
				JOptionPane.showMessageDialog(null, "Usu�rio alterado com sucesso");
				// Encerrar a conex�o
				con.close();
			} catch (SQLIntegrityConstraintViolationException ex) {
				JOptionPane.showMessageDialog(null, "Login em uso.\nEscolha outro login");
				txtUsuLogin.setText(null);
				txtUsuLogin.requestFocus();
			} catch (Exception e) {
				System.out.println(e);
			}
		}

	}

	/**
	 * M�todo respons�vel por alterar os dados de um usu�rio e sua respectiva senha
	 * no banco de dados
	 */

	private void alterarUsuarioSenha() {
		// valida��o da senha (captura segura)
		String capturaSenha = new String(txtUsuSenha.getPassword());
		// valida��o
		if (txtUsuNome.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Coloque as informa��es do usu�rio");
			txtUsuNome.requestFocus();
		} else if (txtUsuLogin.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Coloque o login do usu�rio");
			txtUsuLogin.requestFocus();
		} else if (cboUsuPerfil.getSelectedItem().equals("")) {
			JOptionPane.showMessageDialog(null, "Coloque o perfil do usu�rio");
			cboUsuPerfil.requestFocus();
		} else if (txtUsuSenha.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha a senha");
			txtUsuSenha.requestFocus();
		} else {
			// l�gica principal
			String update2 = "update usuarios set usuario=?, login=?, senha=md5(?), perfil=? where idusu=?";
			try {
				// Estabelecer a conex�o
				Connection con = dao.conectar();
				// Preparar a execu��o da Query
				PreparedStatement pst = con.prepareStatement(update2);
				// Substituir o ? pelo conte�do da caixa de texto
				pst.setString(1, txtUsuNome.getText());
				pst.setString(2, txtUsuLogin.getText());
				pst.setString(3, capturaSenha);
				pst.setString(4, cboUsuPerfil.getSelectedItem().toString());
				pst.setString(5, txtUsuId.getText());
				// Executar a query e alterar o usu�rio no banco
				pst.executeUpdate();
				// confirma��o
				JOptionPane.showMessageDialog(null, "Usu�rio alterado com sucesso");
				limparCampos();
				// Encerrar a conex�o
				con.close();
			} catch (SQLIntegrityConstraintViolationException ex) {
				JOptionPane.showMessageDialog(null, "Login em uso.\nEscolha outro login");
				txtUsuLogin.setText(null);
				txtUsuLogin.requestFocus();
			} catch (Exception e) {
				System.out.println(e);
			}
		}

	}

	/**
	 * M�todo responsavel por excluir um usu�rio do banco
	 */

	private void excluirUsuario() {
		// valida��o (confirma��o da exclus�o)
		int confirma = JOptionPane.showConfirmDialog(null, "Confirma a exclus�o do usu�rio?", "Aten��o!",
				JOptionPane.YES_NO_OPTION);
		if (confirma == JOptionPane.YES_OPTION) {
			String delete = "delete from usuarios where idusu=?";
			try {
				// Estabelecer a conex�o
				Connection con = dao.conectar();
				// Preparar a execu��o da Query
				PreparedStatement pst = con.prepareStatement(delete);
				// Substituir o ? pelo conte�do da caixa de texto
				pst.setString(1, txtUsuId.getText());
				// Executar a query e excluir o usu�rio do banco
				pst.executeUpdate();
				// confirma��o
				limparCampos();
				JOptionPane.showMessageDialog(null, "Usu�rio excluido com sucesso");
				// Encerrar a conex�o
				con.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}

	}

	private void limparCampos() {
		txtUsuId.setText(null);
		txtUsuNome.setText(null);
		txtUsuLogin.setText(null);
		txtUsuSenha.setText(null);
		cboUsuPerfil.setSelectedItem("");
		btnAdicionar.setEnabled(false);
		btnAlterar.setEnabled(false);
		btnExcluir.setEnabled(false);
		txtUsuLogin.requestFocus();
		chkSenha.setVisible(false);
		chkSenha.setSelected(false);
		txtUsuSenha.setEditable(true);
	}
}// Fim do c�digo
