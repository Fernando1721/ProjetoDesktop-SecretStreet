package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import java.awt.Cursor;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;
import java.awt.Color;

public class Fornecedores extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;
	private JTextField textField_10;
	private JTextField textField_11;
	private JTextField textField_12;
	private JTextField textField_13;
	private JTextField textField_14;
	private JTextField textField_15;

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
		
		textField = new JTextField();
		textField.setBounds(107, 17, 252, 20);
		contentPanel.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblNewLabel_1.setIcon(new ImageIcon(Fornecedores.class.getResource("/img/procurar.png")));
		lblNewLabel_1.setBounds(369, 11, 32, 32);
		contentPanel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("ID");
		lblNewLabel_2.setBounds(39, 125, 46, 14);
		contentPanel.add(lblNewLabel_2);
		
		textField_1 = new JTextField();
		textField_1.setBounds(62, 122, 86, 20);
		contentPanel.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("CNPJ");
		lblNewLabel_3.setBounds(170, 125, 46, 14);
		contentPanel.add(lblNewLabel_3);
		
		textField_2 = new JTextField();
		textField_2.setBounds(207, 122, 171, 20);
		contentPanel.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("IE");
		lblNewLabel_4.setBounds(406, 125, 46, 14);
		contentPanel.add(lblNewLabel_4);
		
		textField_3 = new JTextField();
		textField_3.setBounds(419, 122, 147, 20);
		contentPanel.add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("IM");
		lblNewLabel_5.setBounds(588, 125, 46, 14);
		contentPanel.add(lblNewLabel_5);
		
		textField_4 = new JTextField();
		textField_4.setBounds(604, 122, 147, 20);
		contentPanel.add(textField_4);
		textField_4.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("Raz\u00E3o Social");
		lblNewLabel_6.setBounds(39, 163, 109, 14);
		contentPanel.add(lblNewLabel_6);
		
		textField_5 = new JTextField();
		textField_5.setBounds(116, 160, 274, 20);
		contentPanel.add(textField_5);
		textField_5.setColumns(10);
		
		JLabel lblNewLabel_7 = new JLabel("Nome de fantasia");
		lblNewLabel_7.setBounds(400, 163, 109, 14);
		contentPanel.add(lblNewLabel_7);
		
		textField_6 = new JTextField();
		textField_6.setBounds(501, 160, 252, 20);
		contentPanel.add(textField_6);
		textField_6.setColumns(10);
		
		JLabel lblNewLabel_8 = new JLabel("Site");
		lblNewLabel_8.setBounds(39, 202, 52, 14);
		contentPanel.add(lblNewLabel_8);
		
		textField_7 = new JTextField();
		textField_7.setBounds(62, 199, 218, 20);
		contentPanel.add(textField_7);
		textField_7.setColumns(10);
		
		JLabel lblNewLabel_9 = new JLabel("Fone");
		lblNewLabel_9.setBounds(308, 202, 46, 14);
		contentPanel.add(lblNewLabel_9);
		
		textField_8 = new JTextField();
		textField_8.setBounds(340, 199, 114, 20);
		contentPanel.add(textField_8);
		textField_8.setColumns(10);
		
		JLabel lblNewLabel_10 = new JLabel("Contato");
		lblNewLabel_10.setBounds(474, 202, 46, 14);
		contentPanel.add(lblNewLabel_10);
		
		textField_9 = new JTextField();
		textField_9.setBounds(524, 199, 229, 20);
		contentPanel.add(textField_9);
		textField_9.setColumns(10);
		
		JLabel lblNewLabel_11 = new JLabel("E-mail");
		lblNewLabel_11.setBounds(39, 240, 46, 14);
		contentPanel.add(lblNewLabel_11);
		
		textField_10 = new JTextField();
		textField_10.setBounds(82, 237, 274, 20);
		contentPanel.add(textField_10);
		textField_10.setColumns(10);
		
		JLabel lblNewLabel_12 = new JLabel("CEP");
		lblNewLabel_12.setBounds(369, 240, 46, 14);
		contentPanel.add(lblNewLabel_12);
		
		textField_11 = new JTextField();
		textField_11.setBounds(397, 237, 237, 20);
		contentPanel.add(textField_11);
		textField_11.setColumns(10);
		
		JButton btnNewButton = new JButton("Buscar CEP");
		btnNewButton.setBounds(644, 236, 107, 23);
		contentPanel.add(btnNewButton);
		
		JLabel lblNewLabel_13 = new JLabel("Endere\u00E7o");
		lblNewLabel_13.setBounds(39, 281, 81, 14);
		contentPanel.add(lblNewLabel_13);
		
		textField_12 = new JTextField();
		textField_12.setBounds(106, 278, 444, 20);
		contentPanel.add(textField_12);
		textField_12.setColumns(10);
		
		JLabel lblNewLabel_14 = new JLabel("N\u00FAmero");
		lblNewLabel_14.setBounds(560, 281, 46, 14);
		contentPanel.add(lblNewLabel_14);
		
		textField_13 = new JTextField();
		textField_13.setBounds(606, 278, 145, 20);
		contentPanel.add(textField_13);
		textField_13.setColumns(10);
		
		JLabel lblNewLabel_15 = new JLabel("Bairro");
		lblNewLabel_15.setBounds(39, 320, 89, 14);
		contentPanel.add(lblNewLabel_15);
		
		textField_14 = new JTextField();
		textField_14.setBounds(82, 317, 265, 20);
		contentPanel.add(textField_14);
		textField_14.setColumns(10);
		
		JLabel lblNewLabel_16 = new JLabel("Cidade");
		lblNewLabel_16.setBounds(355, 320, 46, 14);
		contentPanel.add(lblNewLabel_16);
		
		textField_15 = new JTextField();
		textField_15.setBounds(406, 317, 269, 20);
		contentPanel.add(textField_15);
		textField_15.setColumns(10);
		
		JLabel lblNewLabel_17 = new JLabel("UF");
		lblNewLabel_17.setBounds(678, 320, 46, 14);
		contentPanel.add(lblNewLabel_17);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"", "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO"}));
		comboBox.setBounds(703, 315, 48, 22);
		contentPanel.add(comboBox);
		
		JLabel lblNewLabel_18 = new JLabel("Observa\u00E7\u00E3o");
		lblNewLabel_18.setBounds(39, 378, 81, 14);
		contentPanel.add(lblNewLabel_18);
		
		JTextArea textArea = new JTextArea();
		textArea.setBorder(new LineBorder(Color.LIGHT_GRAY));
		textArea.setBounds(121, 354, 294, 66);
		contentPanel.add(textArea);
		
		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.setIcon(new ImageIcon(Fornecedores.class.getResource("/img/create.png")));
		btnNewButton_1.setContentAreaFilled(false);
		btnNewButton_1.setBorderPainted(false);
		btnNewButton_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton_1.setBounds(474, 356, 64, 64);
		contentPanel.add(btnNewButton_1);
		
		JButton btnNewButton_1_1 = new JButton("");
		btnNewButton_1_1.setIcon(new ImageIcon(Fornecedores.class.getResource("/img/update.png")));
		btnNewButton_1_1.setContentAreaFilled(false);
		btnNewButton_1_1.setBorderPainted(false);
		btnNewButton_1_1.setBounds(570, 354, 64, 64);
		contentPanel.add(btnNewButton_1_1);
		
		JButton btnNewButton_1_1_1 = new JButton("");
		btnNewButton_1_1_1.setIcon(new ImageIcon(Fornecedores.class.getResource("/img/cancelar.png")));
		btnNewButton_1_1_1.setContentAreaFilled(false);
		btnNewButton_1_1_1.setBorderPainted(false);
		btnNewButton_1_1_1.setBounds(670, 354, 64, 64);
		contentPanel.add(btnNewButton_1_1_1);
	}
}
