package cnhd;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;
import java.awt.Color;

public class Program {

	private JFrame mainFrame;
	private JTextField txtId;
	private JTextField txtName;
	private JTextField txtPrice;
	JLabel lblId;
	JLabel lblName;
	JLabel lblPrice;
	JButton btnAdd;
	JButton btnSearch;
	JButton btnDelete;
	JButton btnEdit;
	JTextArea textAreaProdList;
	
	List<Product> prodList;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Program window = new Program();
					window.mainFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Program() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		mainFrame = new JFrame();
		mainFrame.setBounds(100, 100, 698, 450);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.getContentPane().setLayout(null);
		
		lblId = new JLabel("Product ID");
		lblId.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblId.setBounds(35, 38, 102, 25);
		mainFrame.getContentPane().add(lblId);
		
		txtId = new JTextField();
		txtId.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtId.setBounds(159, 38, 281, 25);
		mainFrame.getContentPane().add(txtId);
		txtId.setColumns(10);
		
		txtName = new JTextField();
		txtName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtName.setColumns(10);
		txtName.setBounds(159, 85, 281, 25);
		mainFrame.getContentPane().add(txtName);
		
		lblName = new JLabel("Product Name");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblName.setBounds(35, 85, 102, 25);
		mainFrame.getContentPane().add(lblName);
		
		txtPrice = new JTextField();
		txtPrice.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtPrice.setColumns(10);
		txtPrice.setBounds(159, 132, 281, 25);
		mainFrame.getContentPane().add(txtPrice);
		
		lblPrice = new JLabel("Product Price");
		lblPrice.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPrice.setBounds(35, 132, 102, 25);
		mainFrame.getContentPane().add(lblPrice);
		
		prodList = new ArrayList<Product>();
		
		btnAdd = new JButton("Add Product");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addToList(prodList);
			}
		});
		btnAdd.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnAdd.setBounds(497, 38, 120, 25);
		mainFrame.getContentPane().add(btnAdd);
		
		btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchProduct(prodList);
			}
		});
		btnSearch.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnSearch.setBounds(497, 85, 120, 25);
		mainFrame.getContentPane().add(btnSearch);
		
		btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteProduct(prodList);
			}
		});
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnDelete.setBounds(497, 132, 120, 25);
		mainFrame.getContentPane().add(btnDelete);
		
		btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editProduct(prodList);
			}
		});
		btnEdit.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnEdit.setBounds(497, 178, 120, 25);
		mainFrame.getContentPane().add(btnEdit);
		
		textAreaProdList = new JTextArea();
		textAreaProdList.setBorder(new LineBorder(new Color(0, 0, 0)));
		textAreaProdList.setFont(new Font("Monospaced", Font.PLAIN, 14));
		textAreaProdList.setBounds(35, 178, 405, 208);
		textAreaProdList.setText("ID --- Name --- Price\n");
		
		mainFrame.getContentPane().add(textAreaProdList);
	}

	protected void addToList(List<Product> arr) {
		String id = txtId.getText();
		String name = txtName.getText();
		String price_text = txtPrice.getText();
		
		int price;
		
		try {
			price = Integer.parseInt(price_text);
		} catch (Exception ex) {
			price = 0;
			txtPrice.setText("");
		}
		
		if(!(checkId(id)) || (id.equals("")) || (id.contains(" ")))
			showDialog("Invalid product ID!", "Warning!", JOptionPane.ERROR_MESSAGE);
		else
			arr.add(new Product(id, name, price));
		
		displayList(arr);
		
		txtId.setText("");
		txtName.setText("");
		txtPrice.setText("");
	}

	protected void searchProduct(List<Product> arr) {
		String toSearch = txtId.getText();

		for (Product element : arr) {
			if (element.getId().contains(toSearch)) {
				showDialog("Product ID: " + element.getId() + " - Product Name: " + element.getName() + " - Product Price: " + element.getPrice(), "Product found!", JOptionPane.INFORMATION_MESSAGE);
				break;
			}
		}
	}

	protected void deleteProduct(List<Product> arr) {
		String toDelete = txtId.getText();

		for (Product element : arr) {
			if (element.getId().equals(toDelete)) {
				arr.remove(arr.indexOf(element));
				break;
			}
		}
		
		displayList(arr);
	}
	
	protected void editProduct(List<Product> arr) {
		String toEdit = txtId.getText();

		for (Product element : arr) {
			if (element.getId().equals(toEdit)) {
				arr.remove(arr.indexOf(element));
				addToList(arr);
				break;
			}
		}
		
		displayList(arr);
	}

	private void displayList(List<Product> arr) {
		textAreaProdList.setText("ID --- Name --- Price\n");

		for (Product element : arr) {
			textAreaProdList.append(element.getId() + " --- " + element.getName() + " --- " + element.getPrice() + "\n");
		}
	}
	
	private boolean checkId(String id) {
		for (Product element : prodList) {
			if(element.getId().equals(id))
				return false;
		}		
		return true;
	}
	
	private void showDialog(String msg, String title, int msgType) {
		JFrame frame = new JFrame("JDialog");
		JOptionPane.showMessageDialog(frame, msg, title, msgType);
	}
}
