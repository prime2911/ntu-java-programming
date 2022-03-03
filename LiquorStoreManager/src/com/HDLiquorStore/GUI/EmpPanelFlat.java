package com.HDLiquorStore.GUI;

import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Vector;
import java.awt.Color;
import javax.swing.SwingConstants;

import com.HDLiquorStore.BLL.EmployeeBLL;
import com.HDLiquorStore.DTO.EmployeeDTO;
import com.HDLiquorStore.Utils.*;

import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@SuppressWarnings("serial")
public class EmpPanelFlat extends BackgroundPane {
	private JTable tblEmp;
	DefaultTableModel empList = new DefaultTableModel();
	Vector<String> empDetails;
	private JTextField txtId;
	private JTextField txtName;
	private JTextField txtDob;
	private JTextField txtPhone;
	private JTextField txtCitId;
	private JTextField txtUsername;
	private JPasswordField txtPassword;
	JComboBox<String> cbRole;
	private final ButtonGroup empSearch = new ButtonGroup();
	private StringFormatter sf = new StringFormatter();
	private DialogHelper dh = new DialogHelper();

	/**
	 * Create the panel.
	 */
	public EmpPanelFlat() {
		try {
			setBackground(ImageIO.read(getClass().getResource("/pub_logo.png")));
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		setLayout(new MigLayout("", "[10px:10px:10px][grow][::320px,grow][10px:10px:10px]", "[10px:10px:10px][][grow][60px:60px:60px,grow][10px:10px:10px]"));
		setVisible(false);
		
		tblEmp = new JTable();
		tblEmp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int rowIndex = tblEmp.getSelectedRow();

				// Prevents selecting column names
				if (rowIndex > 0) {
					txtId.setText(String.valueOf(empList.getValueAt(rowIndex, 0)));
					txtName.setText(String.valueOf(empList.getValueAt(rowIndex, 1)));
					txtDob.setText(String.valueOf(empList.getValueAt(rowIndex, 2)));
					txtPhone.setText(String.valueOf(empList.getValueAt(rowIndex, 3)));
					txtCitId.setText(String.valueOf(empList.getValueAt(rowIndex, 4)));
					txtUsername.setText(String.valueOf(empList.getValueAt(rowIndex, 5)));
					txtPassword.setText("");
					cbRole.setSelectedItem(empList.getValueAt(rowIndex, 7));
				}
				else
					clearFields();
			}
		});
		tblEmp.setBorder(new LineBorder(new Color(0, 0, 0)));
		add(tblEmp, "cell 1 2 2 1,grow");
		
		JPanel panelTopBuffer = new JPanel();
		panelTopBuffer.setOpaque(false);
		add(panelTopBuffer, "cell 1 0,grow");
		
		JPanel panelLeftBuffer = new JPanel();
		panelLeftBuffer.setOpaque(false);
		add(panelLeftBuffer, "cell 0 1,grow");
		
		JPanel panelRightBuffer = new JPanel();
		panelRightBuffer.setOpaque(false);
		add(panelRightBuffer, "cell 3 1,grow");
		
		JPanel panelBottomBtns = new JPanel();
		panelBottomBtns.setOpaque(false);
		add(panelBottomBtns, "cell 1 3 2 1,grow");
		panelBottomBtns.setLayout(new MigLayout("", "[grow][grow]", "[grow]"));
		
		JPanel panelRefresh = new JPanel();
		panelRefresh.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				tblEmp.clearSelection();
				initEmpTable();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				panelRefresh.setBackground(Color.GREEN);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				panelRefresh.setBackground(new Color(218, 165, 32));
			}
		});
		panelRefresh.setBackground(new Color(218, 165, 32));
		panelBottomBtns.add(panelRefresh, "cell 0 0,alignx trailing,growy");
		panelRefresh.setLayout(new BorderLayout(0, 0));
		
		JLabel lblRefreshList = new JLabel(" Refresh List ");
		lblRefreshList.setHorizontalAlignment(SwingConstants.CENTER);
		lblRefreshList.setFont(new Font("Tahoma", Font.BOLD, 14));
		panelRefresh.add(lblRefreshList, BorderLayout.CENTER);
		
		JPanel panelClear = new JPanel();
		panelClear.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				tblEmp.clearSelection();
				clearFields();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				panelClear.setBackground(Color.GREEN);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				panelClear.setBackground(new Color(218, 165, 32));
			}
		});
		panelClear.setBackground(new Color(218, 165, 32));
		panelBottomBtns.add(panelClear, "cell 1 0,alignx leading,growy");
		panelClear.setLayout(new BorderLayout(0, 0));
		
		JLabel lblClearFields = new JLabel(" Clear Fields ");
		lblClearFields.setHorizontalAlignment(SwingConstants.CENTER);
		lblClearFields.setFont(new Font("Tahoma", Font.BOLD, 14));
		panelClear.add(lblClearFields, BorderLayout.CENTER);
		
		JPanel panelBottomBuffer = new JPanel();
		panelBottomBuffer.setOpaque(false);
		add(panelBottomBuffer, "cell 1 4,grow");
		
		JPanel panelFields = new JPanel();
		panelFields.setOpaque(false);
		add(panelFields, "cell 1 1,grow");
		panelFields.setLayout(new MigLayout("", "[trailing][grow]", "[][][][][][][][]"));
		
		JLabel lblId = new JLabel("Employee ID");
		lblId.setHorizontalAlignment(SwingConstants.TRAILING);
		lblId.setForeground(Color.WHITE);
		lblId.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panelFields.add(lblId, "cell 0 0,alignx trailing");
		
		txtId = new JTextField();
		txtId.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtId.setColumns(10);
		panelFields.add(txtId, "cell 1 0,growx");
		
		JLabel lblName = new JLabel("Name");
		lblName.setHorizontalAlignment(SwingConstants.TRAILING);
		lblName.setForeground(Color.WHITE);
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panelFields.add(lblName, "cell 0 1,alignx trailing");
		
		txtName = new JTextField();
		txtName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtName.setColumns(10);
		panelFields.add(txtName, "cell 1 1,growx");
		
		JLabel lblDob = new JLabel("Date of Birth");
		lblDob.setHorizontalAlignment(SwingConstants.TRAILING);
		lblDob.setForeground(Color.WHITE);
		lblDob.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panelFields.add(lblDob, "cell 0 2,alignx trailing");
		
		txtDob = new JTextField();
		txtDob.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtDob.setColumns(10);
		panelFields.add(txtDob, "cell 1 2,growx");
		
		JLabel lblPhone = new JLabel("Phone");
		lblPhone.setHorizontalAlignment(SwingConstants.TRAILING);
		lblPhone.setForeground(Color.WHITE);
		lblPhone.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panelFields.add(lblPhone, "cell 0 3,alignx trailing");
		
		txtPhone = new JTextField();
		txtPhone.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtPhone.setColumns(10);
		panelFields.add(txtPhone, "cell 1 3,growx");
		
		JLabel lblCitID = new JLabel("Citizen ID");
		lblCitID.setHorizontalAlignment(SwingConstants.TRAILING);
		lblCitID.setForeground(Color.WHITE);
		lblCitID.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panelFields.add(lblCitID, "cell 0 4,alignx trailing");
		
		txtCitId = new JTextField();
		txtCitId.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtCitId.setColumns(10);
		panelFields.add(txtCitId, "cell 1 4,growx");
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setHorizontalAlignment(SwingConstants.TRAILING);
		lblUsername.setForeground(Color.WHITE);
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panelFields.add(lblUsername, "cell 0 5,alignx trailing");
		
		txtUsername = new JTextField();
		txtUsername.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtUsername.setColumns(10);
		panelFields.add(txtUsername, "cell 1 5,growx");
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setHorizontalAlignment(SwingConstants.TRAILING);
		lblPassword.setForeground(Color.WHITE);
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panelFields.add(lblPassword, "cell 0 6,alignx trailing");
		
		txtPassword = new JPasswordField();
		panelFields.add(txtPassword, "cell 1 6,growx");
		
		JLabel lblRole = new JLabel("Role");
		lblRole.setHorizontalAlignment(SwingConstants.TRAILING);
		lblRole.setForeground(Color.WHITE);
		lblRole.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panelFields.add(lblRole, "cell 0 7,alignx trailing");
		
		cbRole = new JComboBox<String>();
		panelFields.add(cbRole, "cell 1 7,growx");
		DefaultComboBoxModel<String> cbData = new DefaultComboBoxModel<String>();
		cbData.addElement("Admin");
		cbData.addElement("Employee");
		cbRole.setModel(cbData);
		
		JPanel panelButtons = new JPanel();
		panelButtons.setOpaque(false);
		add(panelButtons, "cell 2 1,grow");
		panelButtons.setLayout(new MigLayout("", "[:150px:150px,grow][:150px:150px,grow]", "[:60px:60px,grow][:60px:60px,grow][:60px:60px,grow]"));
		
		JPanel panelEmpInfo = new JPanel();
		panelEmpInfo.setBackground(new Color(218, 165, 32));
		panelButtons.add(panelEmpInfo, "cell 0 0,grow");
		panelEmpInfo.setLayout(new BorderLayout(0, 0));
		panelEmpInfo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int rowIndex = tblEmp.getSelectedRow();

				if (rowIndex <= 0)
					dh.showDialog("Please select a valid record!", "Error!", JOptionPane.ERROR_MESSAGE);
				else {
					EmployeeDTO emp = getEmployeeInfo();
					dh.showDialog("Employee ID: " + emp.getId() + "\n"
								+ "Name: " + emp.getName() + "\n" 
								+ "Date of Birth: " + sf.reformatDateString(String.valueOf(emp.getDateOfBirth()), true) + "\n"
								+ "Phone number: " + emp.getPhone() + "\n"
								+ "Citizen ID: " + emp.getCitizenId() + "\n"
								+ "Username: " + emp.getUsername() + "\n"
								+ "Password: " + emp.getPassword() + "\n"
								+ "Role: " + emp.getRole() + "\n",
								"Employee Details", JOptionPane.PLAIN_MESSAGE);
				}
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				panelEmpInfo.setBackground(Color.GREEN);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				panelEmpInfo.setBackground(new Color(218, 165, 32));
			}
		});
		
		JLabel lblEmpInfo = new JLabel("Employee Info");
		
		lblEmpInfo.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmpInfo.setFont(new Font("Tahoma", Font.BOLD, 14));
		panelEmpInfo.add(lblEmpInfo);
		
		JPanel panelAdd = new JPanel();
		panelAdd.setBackground(new Color(218, 165, 32));
		panelButtons.add(panelAdd, "cell 1 0,grow");
		panelAdd.setLayout(new BorderLayout(0, 0));
		panelAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				EmployeeBLL empBLL = new EmployeeBLL();
				EmployeeDTO emp = getEmployeeInfo();

//				empBLL.add(emp);
				if (empBLL.add(emp))
					dh.showDialog("Record added!", "Success!", JOptionPane.PLAIN_MESSAGE);
				else
					dh.showDialog("There was an error!", "Error!", JOptionPane.ERROR_MESSAGE);
				
//				showDialog("Record added!", "Success!", JOptionPane.PLAIN_MESSAGE);
				clearFields();
				initEmpTable();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				panelAdd.setBackground(Color.GREEN);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				panelAdd.setBackground(new Color(218, 165, 32));
			}
		});
		
		JLabel lblAdd = new JLabel("Add Employee");
		lblAdd.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdd.setFont(new Font("Tahoma", Font.BOLD, 14));
		panelAdd.add(lblAdd);
		
		JPanel panelUpdate = new JPanel();
		panelUpdate.setBackground(new Color(218, 165, 32));
		panelButtons.add(panelUpdate, "cell 0 1,grow");
		panelUpdate.setLayout(new BorderLayout(0, 0));
		panelUpdate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				EmployeeBLL empBLL = new EmployeeBLL();
				int rowIndex = tblEmp.getSelectedRow();
//				boolean dialog = false;

				if (rowIndex == -1)
					dh.showDialog("Please select a row to modify!", "Error!", JOptionPane.ERROR_MESSAGE);
				else if (rowIndex == 0)
					dh.showDialog("Can't modify column names!", "Error!", JOptionPane.ERROR_MESSAGE);
				else {
					EmployeeDTO emp = getEmployeeInfo();
//					empBLL.update(emp);
//					dialog = true;
					if (empBLL.update(emp))
						dh.showDialog("Record updated!", "Success!", JOptionPane.PLAIN_MESSAGE);
					else
						dh.showDialog("There was an error!", "Error!", JOptionPane.ERROR_MESSAGE);
				}
				
//				if(dialog)
//					dh.showDialog("Record updated!", "Success!", JOptionPane.PLAIN_MESSAGE);
				clearFields();
				initEmpTable();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				panelUpdate.setBackground(Color.GREEN);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				panelUpdate.setBackground(new Color(218, 165, 32));
			}
		});
		
		JLabel lblUpdate = new JLabel("Update Employee");
		lblUpdate.setHorizontalAlignment(SwingConstants.CENTER);
		lblUpdate.setFont(new Font("Tahoma", Font.BOLD, 14));
		panelUpdate.add(lblUpdate);
		
		JPanel panelDelete = new JPanel();
		panelDelete.setBackground(new Color(218, 165, 32));
		panelButtons.add(panelDelete, "cell 1 1,grow");
		panelDelete.setLayout(new BorderLayout(0, 0));
		panelDelete.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				EmployeeBLL empBLL = new EmployeeBLL();
				int rowIndex = tblEmp.getSelectedRow();
//				boolean dialog = false;

				if (rowIndex == -1)
					dh.showDialog("Please select a row to modify!", "Error!", JOptionPane.ERROR_MESSAGE);
				else if (rowIndex == 0)
					dh.showDialog("Can't modify column names!", "Error!", JOptionPane.ERROR_MESSAGE);
				else {
					EmployeeDTO emp = getEmployeeInfo();
//					empBLL.remove(emp.getId());
//					dialog = true;
					if (empBLL.remove(emp.getId()))
						dh.showDialog("Record deleted!", "Success!", JOptionPane.PLAIN_MESSAGE);
					else
						dh.showDialog("There was an error!", "Error!", JOptionPane.ERROR_MESSAGE);
				}
//				if (dialog)
//					dh.showDialog("Record deleted!", "Success!", JOptionPane.PLAIN_MESSAGE);
				clearFields();
				initEmpTable();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				panelDelete.setBackground(Color.GREEN);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				panelDelete.setBackground(new Color(218, 165, 32));
			}
		});
		
		JLabel lblDelete = new JLabel("Delete Employee");
		lblDelete.setHorizontalAlignment(SwingConstants.CENTER);
		lblDelete.setFont(new Font("Tahoma", Font.BOLD, 14));
		panelDelete.add(lblDelete);
		
		JPanel panelRadio = new JPanel();
		panelRadio.setOpaque(false);
		panelButtons.add(panelRadio, "cell 1 2,growx,aligny center");
		panelRadio.setLayout(new MigLayout("", "[105px,center]", "[25px][]"));
		
		JRadioButton rdbtnById = new JRadioButton("By Emp. ID");
		rdbtnById.setSelected(true);
		empSearch.add(rdbtnById);
		rdbtnById.setFont(new Font("Tahoma", Font.BOLD, 14));
		rdbtnById.setForeground(new Color(255, 255, 255));
		rdbtnById.setOpaque(false);
		panelRadio.add(rdbtnById, "cell 0 0,growx,aligny center");
		
		JRadioButton rdbtnByName = new JRadioButton("By Name");
		empSearch.add(rdbtnByName);
		rdbtnByName.setOpaque(false);
		rdbtnByName.setForeground(Color.WHITE);
		rdbtnByName.setFont(new Font("Tahoma", Font.BOLD, 14));
		panelRadio.add(rdbtnByName, "cell 0 1,growx,aligny center");
		
		JPanel panelSearch = new JPanel();
		panelSearch.setBackground(new Color(218, 165, 32));
		panelButtons.add(panelSearch, "cell 0 2,grow");
		panelSearch.setLayout(new BorderLayout(0, 0));
		panelSearch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (rdbtnById.isSelected())
					searchEmployee(true);
				else
					searchEmployee(false);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				panelSearch.setBackground(Color.GREEN);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				panelSearch.setBackground(new Color(218, 165, 32));
			}
		});
		
		JLabel lblSearch = new JLabel("Search Employee");
		lblSearch.setHorizontalAlignment(SwingConstants.CENTER);
		lblSearch.setFont(new Font("Tahoma", Font.BOLD, 14));
		panelSearch.add(lblSearch);
		
		initEmpTable();

	}

	private void clearFields() {
		txtId.setText("");
		txtName.setText("");
		txtDob.setText("");
		txtPhone.setText("");
		txtCitId.setText("");
		txtUsername.setText("");
		txtPassword.setText("");
		cbRole.setSelectedIndex(0);
	}

	private void initEmpTable() {
		clearEmpTable();

		// Fetch data from table
		EmployeeBLL empBLL = new EmployeeBLL();
		ArrayList<EmployeeDTO> tblEmp = empBLL.selectAll();
		// Transfer to table
		for (EmployeeDTO employee : tblEmp) {
			empDetails = new Vector<String>();
			empDetails.add(String.valueOf(employee.getId()));
			empDetails.add(employee.getName());
			empDetails.add(sf.reformatDateString(String.valueOf(employee.getDateOfBirth()), true));
			empDetails.add(employee.getPhone());
			empDetails.add(employee.getCitizenId());
			empDetails.add(employee.getUsername());
			empDetails.add(employee.getPassword());
			empDetails.add(employee.getRole());
			empList.addRow(empDetails);
		}
	}

	private void clearEmpTable() {
		empList = new DefaultTableModel();
		empList.addColumn("MaNV");
		empList.addColumn("HoTen");
		empList.addColumn("NgaySinh");
		empList.addColumn("SoDT");
		empList.addColumn("CCCD");
		empList.addColumn("TenDN");
		empList.addColumn("MatKhau");
		empList.addColumn("Quyen");

		// Clear the table
		for (int i = empList.getRowCount() - 1; i >= 0; i--) {
			empList.removeRow(i);
		}

		// Generates a row of column names
		Vector<String> colName = new Vector<String>();
		colName.add("Employee ID");
		colName.add("Name");
		colName.add("Date of Birth");
		colName.add("Phone");
		colName.add("Citizen ID");
		colName.add("Username");
		colName.add("Password");
		colName.add("Role");
		empList.addRow(colName);

		tblEmp.setModel(empList);
	}

//	private void showDialog(String msg, String title, int msgType) {
//		JFrame frame = new JFrame("JDialog");
//		JOptionPane.showMessageDialog(frame, msg, title, msgType);
//	}

	private EmployeeDTO getEmployeeInfo() {
		PasswordEncryption pe = new PasswordEncryption();
		int id = 0;
		try {
			id = Integer.valueOf(txtId.getText());
		} catch (Exception e1) {
			txtId.setText("");
			dh.showDialog("Invalid ID!", "Error!", JOptionPane.ERROR_MESSAGE);
		}
		String name = txtName.getText();
		Date dob = Date.valueOf(sf.reformatDateString(txtDob.getText(), false));
		String phone = txtPhone.getText();
		String citId = txtCitId.getText();
		String username = txtUsername.getText();
		String password = "";
		try {
			password = pe.convertHashToString(String.valueOf(txtPassword.getPassword()));
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String role = cbRole.getSelectedItem().toString();

		EmployeeDTO employee = new EmployeeDTO(id, name, dob, phone, citId, username, password, role);
		return employee;
	}
	
	private void searchEmployee(boolean byId) {
		EmployeeBLL empBLL = new EmployeeBLL();	
		int count = 0;
		
		clearEmpTable();
		
		if (byId) {
			int id = 0;
			
			try {
				id = Integer.valueOf(txtId.getText());
			} catch (Exception e1) {
				clearFields();
				initEmpTable();
				dh.showDialog("Invalid ID!", "Error!", JOptionPane.ERROR_MESSAGE);
			}
			
			EmployeeDTO employee = empBLL.searchByID(id);
			// Transfer to table
			empDetails = new Vector<String>();
			empDetails.add(String.valueOf(employee.getId()));
			empDetails.add(employee.getName());
			empDetails.add(sf.reformatDateString(String.valueOf(employee.getDateOfBirth()), true));
			empDetails.add(employee.getPhone());
			empDetails.add(employee.getCitizenId());
			empDetails.add(employee.getUsername());
			empDetails.add(employee.getPassword());
			empDetails.add(employee.getRole());
			empList.addRow(empDetails);
			count++;
		}
		else {
			String name = txtName.getText();
			ArrayList<EmployeeDTO> tblEmp = empBLL.searchByName(name);
			// Transfer to table
			for (EmployeeDTO employee : tblEmp) {
				empDetails = new Vector<String>();
				empDetails.add(String.valueOf(employee.getId()));
				empDetails.add(employee.getName());
				empDetails.add(sf.reformatDateString(String.valueOf(employee.getDateOfBirth()), true));
				empDetails.add(employee.getPhone());
				empDetails.add(employee.getCitizenId());
				empDetails.add(employee.getUsername());
				empDetails.add(employee.getPassword());
				empDetails.add(employee.getRole());
				empList.addRow(empDetails);
				count++;
			}
		}	
		dh.showDialog(String.valueOf(count) + " result(s) found!", "Search completed!", JOptionPane.PLAIN_MESSAGE);
	}

}
