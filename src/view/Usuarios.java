package view;


import java.awt.Cursor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import model.DAO;

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
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 434, 261);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ID");
		lblNewLabel.setBounds(84, 57, 46, 14);
		contentPanel.add(lblNewLabel);
		
		txtUsuId = new JTextField();
		txtUsuId.setBounds(128, 54, 86, 20);
		contentPanel.add(txtUsuId);
		txtUsuId.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Usu\u00E1rio");
		lblNewLabel_1.setBounds(84, 86, 46, 14);
		contentPanel.add(lblNewLabel_1);
		
		txtUsuNome = new JTextField();
		txtUsuNome.setBounds(128, 83, 229, 20);
		contentPanel.add(txtUsuNome);
		txtUsuNome.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Login");
		lblNewLabel_2.setBounds(84, 111, 46, 14);
		contentPanel.add(lblNewLabel_2);
		
		txtUsuSenha = new JPasswordField();
		txtUsuSenha.setBounds(128, 133, 229, 20);
		contentPanel.add(txtUsuSenha);
		
		JLabel lblNewLabel_3 = new JLabel("Senha");
		lblNewLabel_3.setBounds(84, 136, 46, 14);
		contentPanel.add(lblNewLabel_3);
		
		txtUsuLogin = new JTextField();
		txtUsuLogin.setBounds(128, 108, 86, 20);
		contentPanel.add(txtUsuLogin);
		txtUsuLogin.setColumns(10);
		
		cboUsuPerfil = new JComboBox();
		cboUsuPerfil.setModel(new DefaultComboBoxModel(new String[] {"", "admin", "user"}));
		cboUsuPerfil.setBounds(256, 107, 101, 22);
		contentPanel.add(cboUsuPerfil);
		
		JLabel lblNewLabel_4 = new JLabel("perfil");
		lblNewLabel_4.setBounds(222, 111, 46, 14);
		contentPanel.add(lblNewLabel_4);
		
		JButton btnAdicionar = new JButton("");
		btnAdicionar.setToolTipText("adicionar");
		btnAdicionar.setIcon(new ImageIcon(Usuarios.class.getResource("/img/create.png")));
		btnAdicionar.setContentAreaFilled(false);
		btnAdicionar.setBorderPainted(false);
		btnAdicionar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnAdicionar.setBounds(84, 185, 64, 64);
		contentPanel.add(btnAdicionar);
		
		JButton btnAlterar = new JButton("");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnAlterar.setToolTipText("alterar");
		btnAlterar.setIcon(new ImageIcon(Usuarios.class.getResource("/img/update.png")));
		btnAlterar.setContentAreaFilled(false);
		btnAlterar.setBorderPainted(false);
		btnAlterar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAlterar.setBounds(188, 185, 64, 64);
		contentPanel.add(btnAlterar);
		
		JButton btnExcluir = new JButton("");
		btnExcluir.setIcon(new ImageIcon(Usuarios.class.getResource("/img/cancelar.png")));
		btnExcluir.setToolTipText("excluir");
		btnExcluir.setContentAreaFilled(false);
		btnExcluir.setBorderPainted(false);
		btnExcluir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnExcluir.setBounds(293, 185, 64, 64);
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
		btnPesquisar.setBounds(224, 43, 32, 32);
		contentPanel.add(btnPesquisar);
	} // Fim do construtor
	
	DAO dao = new DAO();
	private JComboBox cboUsuPerfil;
	
	/**
	 * Método responsável pela pesquisa de usuários
	 */
	
	private void pesquisarUsuario() {
		// validação
		if (txtUsuId.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite o ID do usuário");
			txtUsuId.requestFocus();
		} else {
			//lógica principal
			//Query (instrução SQL)
			String read = "select * from usuarios where idUsu = ?";
			// tratar excessões sempre que lidar com o banco
			try {
				//Estabelecer a conexão 
				Connection con = dao.conectar();
				//Preparar a execução da Query
				PreparedStatement pst = con.prepareStatement(read);
				//Setar o argumento (id)
				//Substituir o ? pelo conteúdo da caixa de texto 
				pst.setString(1, txtUsuId.getText());
				//Executar a query e exibir o resultado no formulário
				ResultSet rs = pst.executeQuery();
				//Validação (existência de usuário)
				//rs.next() -> existência de usuário
				if (rs.next()) {
					//preencher(setar) os campos do formulário
					txtUsuNome.setText(rs.getString(2));
					txtUsuLogin.setText(rs.getString(3));
					cboUsuPerfil.setSelectedItem(rs.getString(5));
					txtUsuSenha.setText(rs.getString(4));
					
					
				} else {
					JOptionPane.showMessageDialog(null, "Usuário não cadastrado");
				}
				// NUNCA esquecer de encerrar a conexão
			} catch (Exception e) {
				System.out.println(e);
			}
		}
		}
	
	
}// Fim do código
