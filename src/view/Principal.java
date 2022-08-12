package view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class Principal extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public final JPanel panelUsuario = new JPanel();
	private JLabel lblData;
	private JButton btnNewButton;
	// atribuir o modificador publico para manipular estes objetos de outra classe
	public JButton btnRelatorios;
	public JButton btnUsuarios;
	public JLabel lblUsuario;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal frame = new Principal();
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
	public Principal() {
		addWindowListener(new WindowAdapter() {
			// Evento ativar janela
			@Override
			public void windowActivated(WindowEvent e) {
				Date data = new Date();
				DateFormat formatador = DateFormat.getDateInstance(DateFormat.FULL);
				lblData.setText(formatador.format(data));
			}
		});
		setBackground(new Color(240, 240, 240));
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Principal.class.getResource("/img/computer.png")));
		setTitle("InfoRs");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(33, 452, 64, 64);
		contentPane.add(lblNewLabel);
		lblNewLabel.setIcon(new ImageIcon(Principal.class.getResource("/img/laptop.png")));
		
		btnNewButton = new JButton("");
		btnNewButton.setContentAreaFilled(false);
		btnNewButton.setBorderPainted(false);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Link para o jDialog
				Fornecedores fornecedores = new Fornecedores();
				fornecedores.setVisible(true);
				
			}
		});
		btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton.setToolTipText("Fornecedores");
		btnNewButton.setIcon(new ImageIcon(Principal.class.getResource("/img/forklift.png")));
		btnNewButton.setBounds(230, 59, 128, 128);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.setContentAreaFilled(false);
		btnNewButton_1.setBorderPainted(false);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Link para o jDialog
				Produtos produtos = new Produtos();
				produtos.setVisible(true);
			}
		});
		btnNewButton_1.setIcon(new ImageIcon(Principal.class.getResource("/img/caixas.png")));
		btnNewButton_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton_1.setToolTipText("Produtos");
		btnNewButton_1.setBounds(428, 59, 128, 128);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_1_1 = new JButton("");
		btnNewButton_1_1.setContentAreaFilled(false);
		btnNewButton_1_1.setBorderPainted(false);
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Link para o jDialog
				Clientes clientes = new Clientes();
				clientes.setVisible(true);
			}
		});
		btnNewButton_1_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton_1_1.setIcon(new ImageIcon(Principal.class.getResource("/img/client.png")));
		btnNewButton_1_1.setToolTipText("Clientes");
		btnNewButton_1_1.setBounds(631, 59, 128, 128);
		contentPane.add(btnNewButton_1_1);
		
		btnUsuarios = new JButton("");
		btnUsuarios.setEnabled(false);
		btnUsuarios.setContentAreaFilled(false);
		btnUsuarios.setBorderPainted(false);
		btnUsuarios.addActionListener(new ActionListener() {
			// Evento clicar no botão
			public void actionPerformed(ActionEvent e) {
				// Link para o jDialog
				Usuarios usuarios = new Usuarios();
				usuarios.setVisible(true);
				
			}
		});
		btnUsuarios.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnUsuarios.setIcon(new ImageIcon(Principal.class.getResource("/img/user.png")));
		btnUsuarios.setToolTipText("Usu\u00E1rios");
		btnUsuarios.setBounds(32, 59, 128, 128);
		contentPane.add(btnUsuarios);
		
		JButton btnNewButton_3 = new JButton("");
		btnNewButton_3.setContentAreaFilled(false);
		btnNewButton_3.setBorderPainted(false);
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Link para o jDialog
				Pvd pvd = new Pvd();
				pvd.setVisible(true);
			}
		});
		btnNewButton_3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton_3.setIcon(new ImageIcon(Principal.class.getResource("/img/pdv.png")));
		btnNewButton_3.setToolTipText("Caixa");
		btnNewButton_3.setBounds(32, 267, 128, 128);
		contentPane.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("");
		btnNewButton_4.setContentAreaFilled(false);
		btnNewButton_4.setBorderPainted(false);
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Link para o jDialog
				Ferramentas ferramentas = new Ferramentas();
				ferramentas.setVisible(true);
			}
		});
		btnNewButton_4.setToolTipText("Ferramentas");
		btnNewButton_4.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton_4.setIcon(new ImageIcon(Principal.class.getResource("/img/ferramenta.png")));
		btnNewButton_4.setBounds(428, 267, 128, 128);
		contentPane.add(btnNewButton_4);
		
		btnRelatorios = new JButton("");
		btnRelatorios.setEnabled(false);
		btnRelatorios.setContentAreaFilled(false);
		btnRelatorios.setBorderPainted(false);
		btnRelatorios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Link para o jDialog
				Relatorios relatorios = new Relatorios();
				relatorios.setVisible(true);
			}
		});
		btnRelatorios.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnRelatorios.setIcon(new ImageIcon(Principal.class.getResource("/img/relatorio.png")));
		btnRelatorios.setToolTipText("Relat\u00F3rios");
		btnRelatorios.setBounds(230, 267, 128, 128);
		contentPane.add(btnRelatorios);
		
		JButton btnNewButton_3_1_1 = new JButton("");
		btnNewButton_3_1_1.setContentAreaFilled(false);
		btnNewButton_3_1_1.setBorderPainted(false);
		btnNewButton_3_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Link para o jDialog
				Ajuda ajuda = new Ajuda();
				ajuda.setVisible(true);
			}
		});
		btnNewButton_3_1_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton_3_1_1.setIcon(new ImageIcon(Principal.class.getResource("/img/help.png")));
		btnNewButton_3_1_1.setToolTipText("Ajuda");
		btnNewButton_3_1_1.setBounds(631, 267, 128, 128);
		contentPane.add(btnNewButton_3_1_1);
		panelUsuario.setBackground(SystemColor.desktop);
		panelUsuario.setBounds(0, 497, 784, 64);
		contentPane.add(panelUsuario);
		panelUsuario.setLayout(null);
		
		lblData = new JLabel("New label");
		lblData.setBounds(478, 11, 306, 42);
		lblData.setFont(new Font("Arial", Font.BOLD, 14));
		lblData.setForeground(SystemColor.textHighlightText);
		panelUsuario.add(lblData);
		
		lblUsuario = new JLabel("New label");
		lblUsuario.setForeground(Color.WHITE);
		lblUsuario.setBounds(21, 20, 136, 27);
		panelUsuario.add(lblUsuario);
	}
}
