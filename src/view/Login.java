package view;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Atxy2k.CustomTextField.RestrictedTextField;
import model.DAO;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtLogin;
	private JPasswordField txtSenha;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {	
				status();
			}
		});
		setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/img/computer.png")));
		setResizable(false);
		setTitle("InfoRs - Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 325);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblLogin = new JLabel("Login");
		lblLogin.setBounds(209, 35, 46, 14);
		contentPane.add(lblLogin);
		
		txtLogin = new JTextField();
		txtLogin.setBounds(151, 75, 141, 20);
		contentPane.add(txtLogin);
		txtLogin.setColumns(10);
		
		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setBounds(97, 148, 44, 14);
		contentPane.add(lblSenha);
		
		txtSenha = new JPasswordField();
		txtSenha.setBounds(151, 145, 141, 20);
		contentPane.add(txtSenha);
		
		JLabel lblUsuario = new JLabel("Login");
		lblUsuario.setBounds(97, 78, 53, 14);
		contentPane.add(lblUsuario);
		
		JButton btnEntrar = new JButton("Entrar");
		btnEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				logar();
			}
		});
		btnEntrar.setBounds(181, 205, 89, 23);
		contentPane.add(btnEntrar);
		
		lblStatus = new JLabel("");
		lblStatus.setIcon(new ImageIcon(Login.class.getResource("/img/databaseoff.png")));
		lblStatus.setBounds(360, 211, 64, 64);
		contentPane.add(lblStatus);
		
		RestrictedTextField validarLogin = new RestrictedTextField(txtLogin);
		validarLogin.setLimit(15);
		
		RestrictedTextField validarSenha = new RestrictedTextField(txtSenha);
		validarSenha.setLimit(255);
		
	}// Fim do construtor
	
		// Criação de um objeto para acessar a camada model
		DAO dao = new DAO();
		private JLabel lblStatus;
	
		/**
		 * Método usado para verificar o status do servidor
		 */
	
		private void status() {
			try {
				// abrir a conexão
				Connection con = dao.conectar();
				if (con == null) {
					// escolher a imagem databaseoff
					lblStatus.setIcon(new ImageIcon(Login.class.getResource("/img/databaseoff.png")));
				} else {
					// escolher a imagem databaseon
					lblStatus.setIcon(new ImageIcon(Login.class.getResource("/img/databaseon.png")));
				}
				// Não esquecer de fechar a conexão
				con.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	
		/**
		 * Método usado para autenticação de um usuário
		 */
		
		private void logar() {
			
			String capturaSenha = new String (txtSenha.getPassword());
			
			// validação
			if (txtLogin.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Insira o login");
				txtLogin.requestFocus();
			} else if (txtSenha.getPassword().length == 0) {
				JOptionPane.showMessageDialog(null, "Insira a senha");
				txtSenha.requestFocus();
			} else {
				// lógica principal
				String read = "select * from usuarios where login=? and senha=md5(?)";
			  try {
				//Estabelecer a conexão 
				Connection con = dao.conectar();
				//Preparar a execução da Query
				PreparedStatement pst = con.prepareStatement(read);
				//Setar o argumento (id)
				//Substituir o ? pelo conteúdo da caixa de texto 
				pst.setString(1, txtLogin.getText());
				pst.setString(2, capturaSenha);
				//Executar a query e exibir o resultado no formulário
				ResultSet rs = pst.executeQuery();
				//Validação (existência de usuário)
				//rs.next() -> existência de usuário
				if (rs.next()) {
					// Verificar o perfil do usuário
					String perfil = rs.getString(5);
					// System.out.println(perfil);
					Principal principal = new Principal();
					if (perfil.equals("admin")) {
						// abrir a tela principal
						principal.setVisible(true);
						// habilitar recursos
						principal.btnRelatorios.setEnabled(true);
						principal.btnUsuarios.setEnabled(true);
						// personalizar
						principal.panelUsuario.setBackground(Color.RED);
						// setar o nome do usuario na tela principal
						principal.lblUsuario.setText("Usuário: " + rs.getString(2));
						// fechar a tela de login 
						this.dispose();
					} else {
						// abrir a tela principal
						principal.setVisible(true);
						// setar o nome do usuario na tela principal
						principal.lblUsuario.setText("Usuário: " + rs.getString(2));
						// fechar a tela de login 
						this.dispose();
					}
										
					// encerrar a conexão
					con.close();
					
				} else {
					JOptionPane.showMessageDialog(null, "Login e/ou senha inválido(s)");
				} 
				
			  	} catch (Exception e) {
					System.out.println(e);
			    } 
			}
		}
		
} // Fim do código
