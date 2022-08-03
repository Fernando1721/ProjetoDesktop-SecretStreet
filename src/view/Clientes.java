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
import javax.swing.JTable;
import javax.swing.JFormattedTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Cursor;

public class Clientes extends JDialog {

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
		
		textField = new JTextField();
		textField.setBounds(64, 27, 86, 20);
		contentPanel.add(textField);
		textField.setColumns(10);
		
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
		lblNewLabel_6.setBounds(670, 168, 94, 14);
		contentPanel.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("Cep");
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
		
		JLabel lblNewLabel_14 = new JLabel("");
		lblNewLabel_14.setIcon(new ImageIcon(Clientes.class.getResource("/img/procurar.png")));
		lblNewLabel_14.setBounds(160, 11, 32, 48);
		contentPanel.add(lblNewLabel_14);
		
		textField_1 = new JTextField();
		textField_1.setBounds(75, 125, 275, 20);
		contentPanel.add(textField_1);
		textField_1.setColumns(10);
		
		JFormattedTextField formattedTextField = new JFormattedTextField();
		formattedTextField.setBounds(488, 125, 115, 20);
		contentPanel.add(formattedTextField);
		
		textField_2 = new JTextField();
		textField_2.setBounds(655, 125, 127, 20);
		contentPanel.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(64, 165, 165, 20);
		contentPanel.add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setBounds(290, 165, 358, 20);
		contentPanel.add(textField_4);
		textField_4.setColumns(10);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"", "Sim", "N\u00E3o"}));
		comboBox.setBounds(731, 164, 51, 22);
		contentPanel.add(comboBox);
		
		textField_5 = new JTextField();
		textField_5.setBounds(64, 205, 99, 20);
		contentPanel.add(textField_5);
		textField_5.setColumns(10);
		
		JButton btnNewButton = new JButton("Buscar CEP");
		btnNewButton.setBounds(173, 204, 123, 23);
		contentPanel.add(btnNewButton);
		
		textField_6 = new JTextField();
		textField_6.setBounds(374, 205, 408, 20);
		contentPanel.add(textField_6);
		textField_6.setColumns(10);
		
		textField_7 = new JTextField();
		textField_7.setBounds(88, 244, 75, 20);
		contentPanel.add(textField_7);
		textField_7.setColumns(10);
		
		textField_8 = new JTextField();
		textField_8.setBounds(270, 244, 188, 20);
		contentPanel.add(textField_8);
		textField_8.setColumns(10);
		
		textField_9 = new JTextField();
		textField_9.setBounds(513, 244, 269, 20);
		contentPanel.add(textField_9);
		textField_9.setColumns(10);
		
		textField_10 = new JTextField();
		textField_10.setBounds(82, 289, 358, 20);
		contentPanel.add(textField_10);
		textField_10.setColumns(10);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"", "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO"}));
		comboBox_1.setBounds(499, 288, 56, 22);
		contentPanel.add(comboBox_1);
		
		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton_1.setToolTipText("Adicionar");
		btnNewButton_1.setIcon(new ImageIcon(Clientes.class.getResource("/img/create.png")));
		btnNewButton_1.setContentAreaFilled(false);
		btnNewButton_1.setBorderPainted(false);
		btnNewButton_1.setBounds(290, 322, 64, 64);
		contentPanel.add(btnNewButton_1);
		
		JButton btnNewButton_1_1 = new JButton("");
		btnNewButton_1_1.setContentAreaFilled(false);
		btnNewButton_1_1.setBorderPainted(false);
		btnNewButton_1_1.setBounds(254, 322, 64, 64);
		contentPanel.add(btnNewButton_1_1);
		
		JButton btnNewButton_1_2 = new JButton("");
		btnNewButton_1_2.setIcon(new ImageIcon(Clientes.class.getResource("/img/update.png")));
		btnNewButton_1_2.setToolTipText("Adicionar");
		btnNewButton_1_2.setContentAreaFilled(false);
		btnNewButton_1_2.setBorderPainted(false);
		btnNewButton_1_2.setBounds(358, 322, 64, 64);
		contentPanel.add(btnNewButton_1_2);
		
		JButton btnNewButton_1_2_1 = new JButton("");
		btnNewButton_1_2_1.setIcon(new ImageIcon(Clientes.class.getResource("/img/cancelar.png")));
		btnNewButton_1_2_1.setToolTipText("Adicionar");
		btnNewButton_1_2_1.setContentAreaFilled(false);
		btnNewButton_1_2_1.setBorderPainted(false);
		btnNewButton_1_2_1.setBounds(432, 322, 64, 64);
		contentPanel.add(btnNewButton_1_2_1);
	}
}
