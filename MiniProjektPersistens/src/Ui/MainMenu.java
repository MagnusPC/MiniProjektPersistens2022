package Ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JSplitPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.DropMode;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainMenu extends JFrame {

	private JPanel contentPane;
	private JTextField txtSerienummer;
	private JTextField txtQuantity;
	private JTextField textField;

	/**non ui
	 * Fields
	 */
	
	//private OrderController oCtrl;
	//Todo
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainMenu frame = new MainMenu();
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
	
	public MainMenu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 881, 705);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JSplitPane splitPane = new JSplitPane();
		contentPane.add(splitPane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		splitPane.setLeftComponent(panel);
		
		JSplitPane splitPane_4 = new JSplitPane();
		splitPane_4.setOrientation(JSplitPane.VERTICAL_SPLIT);
		panel.add(splitPane_4);
		
		JPanel panel_8 = new JPanel();
		splitPane_4.setLeftComponent(panel_8);
		
		JSplitPane splitPane_1 = new JSplitPane();
		splitPane_1.setOrientation(JSplitPane.VERTICAL_SPLIT);
		panel_8.add(splitPane_1);
		
		JLabel lblTastPerson = new JLabel("Tast Person");
		lblTastPerson.setFont(new Font("Tahoma", Font.PLAIN, 16));
		splitPane_1.setLeftComponent(lblTastPerson);
		
		JPanel panel_1 = new JPanel();
		splitPane_1.setRightComponent(panel_1);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField.setText("tlf...");
		textField.setColumns(10);
		panel_1.add(textField);
		
		JButton btnNewButton = new JButton("S\u00F8g");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel_1.add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setName("ScrollPerson");
		splitPane_4.setRightComponent(scrollPane);
		
		JSplitPane splitPane_2 = new JSplitPane();
		splitPane_2.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane.setRightComponent(splitPane_2);
		
		JPanel panel_6 = new JPanel();
		splitPane_2.setRightComponent(panel_6);
		
		JSplitPane splitPane_3 = new JSplitPane();
		splitPane_3.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane_2.setLeftComponent(splitPane_3);
		
		JLabel lblNewLabel_1 = new JLabel("Tast Product");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		splitPane_3.setLeftComponent(lblNewLabel_1);
		
		JPanel panel_7 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_7.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		splitPane_3.setRightComponent(panel_7);
		
		txtSerienummer = new JTextField();
		txtSerienummer.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtSerienummer.setText("Serienummer");
		panel_7.add(txtSerienummer);
		txtSerienummer.setColumns(10);
		
		txtQuantity = new JTextField();
		txtQuantity.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtQuantity.setText("Quantity");
		panel_7.add(txtQuantity);
		txtQuantity.setColumns(10);
		
		JButton btnNewButton_4 = new JButton("S\u00F8g");
		btnNewButton_4.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel_7.add(btnNewButton_4);
		
		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2, BorderLayout.SOUTH);
		panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.X_AXIS));
		
		JPanel panel_3 = new JPanel();
		panel_2.add(panel_3);
		panel_3.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_5 = new JPanel();
		panel_3.add(panel_5, BorderLayout.WEST);
		
		JLabel lblNewLabel = new JLabel("Western Style Ltd ");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panel_5.add(lblNewLabel);
		
		JPanel panel_4 = new JPanel();
		panel_2.add(panel_4);
		
		JButton btnNewButton_1 = new JButton("Afslut");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel_4.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Annuller");
		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel_4.add(btnNewButton_2);
	}
	
	private void createOrder() {
	//Todo	
	}
	private void addCustomerByPhoneNo() {
	//Todo
	}
	private void addProductBySerialNo() {
	//Todo
	}
	private void finishOrder() {
	//Todo
	}
	
	
	

}
