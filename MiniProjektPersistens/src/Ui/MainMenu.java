package Ui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import ctrl.OrderCtrl;
import db.DataAccessException;

import javax.swing.JSplitPane;
import javax.swing.JTable;
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
import javax.swing.Box;

public class MainMenu extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	
	private OrderCtrl oc;

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
		splitPane.setResizeWeight(0.5);
		contentPane.add(splitPane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		splitPane.setLeftComponent(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JSplitPane splitPane_4 = new JSplitPane();
		splitPane_4.setOrientation(JSplitPane.VERTICAL_SPLIT);
		panel.add(splitPane_4);
		
		JPanel panel_8 = new JPanel();
		splitPane_4.setLeftComponent(panel_8);
		panel_8.setLayout(new BorderLayout(0, 0));
		
		JSplitPane splitPane_1 = new JSplitPane();
		splitPane_1.setOrientation(JSplitPane.VERTICAL_SPLIT);
		panel_8.add(splitPane_1);
		
		JLabel lblTastPerson = new JLabel("Tast Person");
		lblTastPerson.setFont(new Font("Tahoma", Font.PLAIN, 16));
		splitPane_1.setLeftComponent(lblTastPerson);
		
		JPanel panel_1 = new JPanel();
		splitPane_1.setRightComponent(panel_1);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		Box horizontalBox = Box.createHorizontalBox();
		panel_1.add(horizontalBox);
		
		textField = new JTextField();
		textField.setAlignmentX(Component.LEFT_ALIGNMENT);
		textField.setText("tlf...");
		textField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField.setColumns(10);
		horizontalBox.add(textField);
		
		JButton btnNewButton = new JButton("S\u00F8g");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		horizontalBox.add(btnNewButton);
		
		JPanel panel_9_listview = new JPanel();
		splitPane_4.setRightComponent(panel_9_listview);
		
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
		
		Box horizontalBox_1 = Box.createHorizontalBox();
		panel_7.add(horizontalBox_1);
		
		textField_2 = new JTextField();
		textField_2.setText("Serienummer");
		textField_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField_2.setColumns(10);
		horizontalBox_1.add(textField_2);
		
		textField_1 = new JTextField();
		textField_1.setText("Quantity");
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField_1.setColumns(10);
		horizontalBox_1.add(textField_1);
		
		JButton btnNewButton_4 = new JButton("S\u00F8g");
		btnNewButton_4.setFont(new Font("Tahoma", Font.PLAIN, 16));
		horizontalBox_1.add(btnNewButton_4);
		
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
		
		
		
		TableCellRenderer tCR = new TableCellRenderer() {
			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
					int row, int column) {
				// TODO Auto-generated method stub
				return null;
			}
		};
		
		
		/*
		 * PersonData List/Table:
		 * /
		 */
		
		DefaultTableModel model = new DefaultTableModel();
		panel_9_listview.setLayout(new BorderLayout(0, 0));
		JTable table = new JTable(model);
		panel_9_listview.add(new JScrollPane(table));
		
		model.addColumn("Navn");
		model.addColumn("Adresse");
		model.addColumn("Postnummer");
		model.addColumn("By");
		model.addColumn("Tlf");
		
		//Test
		model.addRow(new Object[] {"Uuh", "Uuuh", "Uuuuh", "Uuuuuuuh", "Uuh det kan jeg ikke huske"});
		//Remove later
		
		/*
		 * Ordre oplysninger table
		 * 
		 */
		DefaultTableModel model2 = new DefaultTableModel();
		panel_6.setLayout(new BorderLayout(0, 0));
		JTable table2 = new JTable(model2);
		JScrollPane scrollPane = new JScrollPane(table2);
		panel_6.add(scrollPane);
		model2.addColumn("1");
		model2.addColumn("2");
		model2.addColumn("3");
		model2.addColumn("4");
		model2.addColumn("5");
		
		
	}
	
	private void createOrder() {
	//Todo	
	}
	private void addCustomerByPhoneNo() {
		
		
	}
	private void addProductBySerialNo() {
	//Todo
	}
	private void finishOrder() {
	//Todo
	}
	
	
	

}
