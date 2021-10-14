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
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.border.LineBorder;

public class Program {

	private JFrame mainFrame;
	private JTextField txtName;
	JLabel lblName;
	JButton btnAddName;
	JTextArea textAreaNameList;
	JButton btnDisplay;
	JLabel lblStatus;
	JButton btnClear;

	ArrayList<String> nameList;
	
	private JLabel lblToSearch;
	private JTextField txtToSearch;
	private JButton btnSearch;
	private JTextArea textAreaNameList_alt;

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
		mainFrame.setTitle("ArrayList Example");
		mainFrame.setBounds(100, 100, 674, 475);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.getContentPane().setLayout(null);

		lblName = new JLabel("Enter name");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblName.setBounds(69, 26, 109, 25);
		mainFrame.getContentPane().add(lblName);

		txtName = new JTextField();
		txtName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				lblStatus.setText("Entering name...");
				lblStatus.setVisible(true);
			}
		});
		txtName.setBounds(204, 29, 356, 24);
		mainFrame.getContentPane().add(txtName);
		txtName.setColumns(10);

		nameList = new ArrayList<String>();

		btnAddName = new JButton("Add name");
		btnAddName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnAddName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addToList(nameList);
			}
		});
		btnAddName.setBounds(268, 154, 109, 35);
		mainFrame.getContentPane().add(btnAddName);

		textAreaNameList = new JTextArea();
		textAreaNameList.setBorder(new LineBorder(new Color(0, 0, 0)));
		textAreaNameList.setBounds(69, 273, 241, 143);
		mainFrame.getContentPane().add(textAreaNameList);

		btnDisplay = new JButton("Display List");
		btnDisplay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				displayList(nameList);
			}
		});
		btnDisplay.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnDisplay.setBounds(268, 210, 109, 41);
		mainFrame.getContentPane().add(btnDisplay);

		lblStatus = new JLabel("Display status");
		lblStatus.setVisible(false);
		lblStatus.setHorizontalAlignment(SwingConstants.CENTER);
		lblStatus.setForeground(Color.RED);
		lblStatus.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblStatus.setBounds(268, 63, 126, 19);
		mainFrame.getContentPane().add(lblStatus);

		btnClear = new JButton("Clear List");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearList(nameList);
			}
		});
		btnClear.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnClear.setBounds(434, 151, 126, 41);
		mainFrame.getContentPane().add(btnClear);

		lblToSearch = new JLabel("Search for name");
		lblToSearch.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblToSearch.setBounds(69, 99, 109, 25);
		mainFrame.getContentPane().add(lblToSearch);

		txtToSearch = new JTextField();
		txtToSearch.setColumns(10);
		txtToSearch.setBounds(204, 102, 356, 24);
		mainFrame.getContentPane().add(txtToSearch);

		btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchName(nameList);
			}
		});
		btnSearch.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnSearch.setBounds(99, 154, 109, 35);
		mainFrame.getContentPane().add(btnSearch);

		textAreaNameList_alt = new JTextArea();
		textAreaNameList_alt.setBorder(new LineBorder(new Color(0, 0, 0)));
		textAreaNameList_alt.setBounds(352, 273, 241, 143);
		mainFrame.getContentPane().add(textAreaNameList_alt);
	}

	void addToList(ArrayList<String> arr) {
		String name = txtName.getText();

		arr.add(name);
		txtName.setText("");

		lblStatus.setText("Added successfully!");
		lblStatus.setVisible(true);

		textAreaNameList_alt.setText("");

		for (int i = 0; i < arr.size(); i++) {
			String element = arr.get(i);

			textAreaNameList_alt.append(element + "\n");
		}
	}

	void displayList(ArrayList<String> arr) {
		textAreaNameList.setText("");

		// For loop iteration
		for (int i = 0; i < arr.size(); i++) {
			String element = arr.get(i);

			textAreaNameList.append(element + "\n");
		}

		// Foreach
//		for (String element : arr) {
//			textAreaNameList.append(element + "\n");
//		}
	}

	void clearList(ArrayList<String> arr) {
		textAreaNameList.setText("");
		textAreaNameList_alt.setText("");
		arr.clear();
	}

	void searchName(ArrayList<String> arr) {
		String toSearch = txtToSearch.getText();
		String nameFound = "";
		int count = 0;

		for (String element : arr) {
			if (element.contains(toSearch)) {
				nameFound += element + "\n";
				count++;
			}
		}

		if (count == 0)
			showDialog("Name not found!", "Not found!", JOptionPane.ERROR_MESSAGE);
		else
			showDialog(nameFound, count + " name(s) found!", JOptionPane.INFORMATION_MESSAGE);

	}
	
	void showDialog(String msg, String title, int msgType) {
		JFrame frame = new JFrame("JDialog");
		JOptionPane.showMessageDialog(frame, msg, title, msgType);
	}
}
