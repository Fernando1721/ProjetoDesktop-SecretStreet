package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.DAO;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;

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
		
		JLabel lblUsuario = new JLabel("Usu\u00E1rio");
		lblUsuario.setBounds(88, 78, 53, 14);
		contentPane.add(lblUsuario);
		
		JButton btnEntrar = new JButton("Entrar");
		btnEntrar.setBounds(181, 205, 89, 23);
		contentPane.add(btnEntrar);
		
		lblStatus = new JLabel("");
		lblStatus.setIcon(new ImageIcon(Login.class.getResource("/img/databaseoff.png")));
		lblStatus.setBounds(360, 211, 64, 64);
		contentPane.add(lblStatus);
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
	
} // Fim do código
