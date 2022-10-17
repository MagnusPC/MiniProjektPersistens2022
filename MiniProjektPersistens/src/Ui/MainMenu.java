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
//import gui.CreateOrder;
import model.Customer;
import model.Invoice;
import model.Order;
import model.OrderLine;
import model.Product;

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
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.Box;

public class MainMenu extends JFrame {

	private JPanel contentPane;
	private JTextField txtCustomerPhone;
	private JTextField txtQuantity;
	private JTextField txtProductid;
	
	private OrderCtrl oCtrl;
	private DefaultTableModel modelCustomer;
	private DefaultTableModel modelProduct;

	/**non ui
	 * Fields
	 */
	
	//private OrderController oCtrl;
	//TODO
	
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
		
		txtCustomerPhone = new JTextField();
		txtCustomerPhone.setAlignmentX(Component.LEFT_ALIGNMENT);
		txtCustomerPhone.setText("+45 11111111");
		txtCustomerPhone.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtCustomerPhone.setColumns(10);
		horizontalBox.add(txtCustomerPhone);
		
		JButton btnSearchCustomer = new JButton("S\u00F8g");
		btnSearchCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addCustomerByPhoneNo();
			}
		});
		btnSearchCustomer.setFont(new Font("Tahoma", Font.PLAIN, 16));
		horizontalBox.add(btnSearchCustomer);
		
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
		
		txtProductid = new JTextField();
		txtProductid.setText("ProductId");
		txtProductid.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtProductid.setColumns(10);
		horizontalBox_1.add(txtProductid);
		
		txtQuantity = new JTextField();
		txtQuantity.setText("Quantity");
		txtQuantity.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtQuantity.setColumns(10);
		horizontalBox_1.add(txtQuantity);
		
		JButton btnSearchProduct = new JButton("Tilføj");
		btnSearchProduct.setFont(new Font("Tahoma", Font.PLAIN, 16));
		horizontalBox_1.add(btnSearchProduct);
		btnSearchProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addProductByProductId();
			}
		});
		
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
		
		JButton btnAfslut = new JButton("Godkend");
		btnAfslut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				    addInvoice();
				    finishOrder();
				    System.out.println("Order has been made");
				    close();  //Temporary, a better solution is to whipe the table models in the ui
                } catch (NullPointerException n1) {
                    n1.printStackTrace();
                    System.out.println("You must add atleast 1 product");
                }
			}
		});
		btnAfslut.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel_4.add(btnAfslut);
		
		JButton btnCancel = new JButton("Annuller");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				close();
			}
		});
		btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel_4.add(btnCancel);
		
		
		
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
		
		modelCustomer = new DefaultTableModel();
		panel_9_listview.setLayout(new BorderLayout(0, 0));
		JTable table = new JTable(modelCustomer);
		panel_9_listview.add(new JScrollPane(table));
		
		modelCustomer.addColumn("Id");
		modelCustomer.addColumn("Navn");
		modelCustomer.addColumn("Adresse");
		modelCustomer.addColumn("Postnummer");
		modelCustomer.addColumn("By");
		modelCustomer.addColumn("Tlf");
		
		//Test
		modelCustomer.addRow(new Object[] {"Uuh", "Uuuh", "Uuuuh", "Uuuuuuuh", "Uuh det kan jeg ikke huske"});
		//Remove later
		
		modelProduct = new DefaultTableModel();
		panel_6.setLayout(new BorderLayout(0, 0));
		JTable table2 = new JTable(modelProduct);
		JScrollPane scrollPane = new JScrollPane(table2);
		panel_6.add(scrollPane);
		modelProduct.addColumn("Product Name");
		modelProduct.addColumn("Quantity");
//		modelProduct.addColumn("3");
//		modelProduct.addColumn("4");
//		modelProduct.addColumn("5");
		
		try {
			oCtrl = new OrderCtrl();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (DataAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		createOrder();
		
		
	}
	
	private void createOrder() {
		oCtrl.createNewOrder();
		
	}
	private void addCustomerByPhoneNo() {
		Customer c = null;
		try {
			c = oCtrl.addCustomerByPhoneNo(txtCustomerPhone.getText());
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		modelCustomer.addRow(new Object[] {c.getCustomerId(), c.getName(), c.getAdress(), c.getZipCode(), c.getCity(), c.getPhoneno()});
	}
	
	private void addProductByProductId() {
		OrderLine ol = null;
		ol = oCtrl.addProductByProductId(Integer.valueOf(txtProductid.getText()), Integer.valueOf(txtQuantity.getText()));
		
		modelProduct.addRow(new Object[] {ol.getProductName(), ol.getQuantity()});
	}
	
	private void addInvoice() {
		Invoice ai = null;
		ai = oCtrl.addInvoice();
		
		
	}
	
	private void finishOrder() {
		Order newOrder = null;
		try {
			newOrder = oCtrl.finishOrder();
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void close() {
	    this.setVisible(false);
        this.dispose();
	}
	
	
	

}
