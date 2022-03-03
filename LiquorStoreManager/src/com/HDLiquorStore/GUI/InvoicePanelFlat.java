package com.HDLiquorStore.GUI;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Vector;

import javax.imageio.ImageIO;

import com.HDLiquorStore.BLL.EmployeeBLL;
import com.HDLiquorStore.BLL.InvoiceBLL;
import com.HDLiquorStore.BLL.InvoiceDetailsBLL;
import com.HDLiquorStore.BLL.ProductBLL;
import com.HDLiquorStore.DTO.EmployeeDTO;
import com.HDLiquorStore.DTO.InvoiceDTO;
import com.HDLiquorStore.DTO.InvoiceDetailsDTO;
import com.HDLiquorStore.DTO.ProductDTO;
import com.HDLiquorStore.Utils.*;

import net.miginfocom.swing.MigLayout;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.BorderLayout;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

@SuppressWarnings("serial")
public class InvoicePanelFlat extends BackgroundPane {
	DefaultTableModel invList = new DefaultTableModel();
	Vector<String> invDetails;
	DefaultTableModel deList = new DefaultTableModel();
	Vector<String> deDetails;
	JComboBox<String> cbEmp;
	private JTable tblInvoice;
	private JTable tblDetails;
	private JTextField txtInvId;
	private JTextField txtInvDate;
	private final ButtonGroup invSearch = new ButtonGroup();
	private JPanel panelInvoice;
	private JPanel panelDetails;
	private JPanel panelInvContent;
	private JPanel panelInvFields;
	private JPanel panelInvBtns;
	private JPanel panelInvRadio;
	private JPanel panelAddInv;
	private JPanel panelUpdateInv;
	private JPanel panelDeleteInv;
	private JPanel panelSearchInv;
	private JPanel panelDeContent;
	private JPanel panelDeFields;
	private JPanel panelDeBtns;
	private StringFormatter sf = new StringFormatter();
	private DialogHelper dh = new DialogHelper();
	private JLabel lblProduct;
	private JLabel lblQuantity;
	private JTextField txtQuantity;
	private JComboBox<String> cbProd;
	private JPanel panelInvTitle;
	private JLabel lblInvoiceList;
	private JPanel panelDeTitle;
	private JLabel lblInvoiceDetails;
	private JLabel lblDeInvId;
	private JTextField txtInvDeId;
	private JPanel panelAddDe;
	private JPanel panelUpdateDe;
	private JPanel panelDeleteDe;
	private JPanel panelTotalPrice;
	private JLabel lblAddDe;
	private JLabel lblUpdateDe;
	private JLabel lblDeleteDe;
	private JLabel lblTotalPrice;
	private JLabel lblPrice;
	private JTextField txtProdPrice;
	private JPanel panelInvBottomBtns;
	private JPanel panelRefresh;
	private JPanel panelClear;
	private JLabel lblRefreshList;
	private JLabel lblClearFields;

	/**
	 * Create the panel.
	 */
	public InvoicePanelFlat() {
		try {
			setBackground(ImageIO.read(getClass().getResource("/pub_logo.png")));
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		setLayout(new MigLayout("", "[grow][grow]", "[grow]"));
		setVisible(false);
		
		panelInvoice = new JPanel();
		panelInvoice.setOpaque(false);
		add(panelInvoice, "cell 0 0,grow");
		panelInvoice.setLayout(new MigLayout("", "[grow]", "[][][grow][:80px:80px]"));
		
		panelInvTitle = new JPanel();
		panelInvTitle.setOpaque(false);
		panelInvoice.add(panelInvTitle, "cell 0 0,grow");
		panelInvTitle.setLayout(new BorderLayout(0, 0));
		
		lblInvoiceList = new JLabel("Invoice List");
		lblInvoiceList.setHorizontalAlignment(SwingConstants.CENTER);
		lblInvoiceList.setForeground(Color.RED);
		lblInvoiceList.setFont(new Font("Tahoma", Font.BOLD, 20));
		panelInvTitle.add(lblInvoiceList, BorderLayout.CENTER);
		
		panelInvContent = new JPanel();
		panelInvContent.setOpaque(false);
		panelInvoice.add(panelInvContent, "cell 0 1,grow");
		panelInvContent.setLayout(new MigLayout("", "[grow]", "[grow][grow]"));
		
		panelInvFields = new JPanel();
		panelInvFields.setOpaque(false);
		panelInvContent.add(panelInvFields, "cell 0 0,grow");
		panelInvFields.setLayout(new MigLayout("", "[][grow]", "[][][]"));
		
		JLabel lblInvId = new JLabel("Invoice No.");
		lblInvId.setHorizontalAlignment(SwingConstants.TRAILING);
		lblInvId.setForeground(Color.WHITE);
		lblInvId.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panelInvFields.add(lblInvId, "cell 0 0,alignx trailing");
		
		txtInvId = new JTextField();
		txtInvId.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtInvId.setColumns(10);
		panelInvFields.add(txtInvId, "cell 1 0,growx");
		
		JLabel lblDate = new JLabel("Date");
		lblDate.setHorizontalAlignment(SwingConstants.TRAILING);
		lblDate.setForeground(Color.WHITE);
		lblDate.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panelInvFields.add(lblDate, "cell 0 1,alignx trailing");
		
		txtInvDate = new JTextField();
		txtInvDate.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtInvDate.setColumns(10);
		panelInvFields.add(txtInvDate, "cell 1 1,growx");
		
		JLabel lblEmp = new JLabel("Managing Employee");
		lblEmp.setHorizontalAlignment(SwingConstants.TRAILING);
		lblEmp.setForeground(Color.WHITE);
		lblEmp.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panelInvFields.add(lblEmp, "cell 0 2,alignx trailing");
		
		cbEmp = new JComboBox<String>();
		panelInvFields.add(cbEmp, "cell 1 2,growx");
		DefaultComboBoxModel<String> cbEmpData = new DefaultComboBoxModel<String>();
		EmployeeBLL empBLL = new EmployeeBLL();
		ArrayList<EmployeeDTO> empList = empBLL.selectAll();
		for (EmployeeDTO employee : empList)
			cbEmpData.addElement(employee.getId() + " - " + employee.getName());
		cbEmp.setModel(cbEmpData);
		
		panelInvBtns = new JPanel();
		panelInvBtns.setOpaque(false);
		panelInvContent.add(panelInvBtns, "cell 0 1,alignx center,aligny center");
		panelInvBtns.setLayout(new MigLayout("", "[:150px:150px,grow][:150px:150px,grow][:150px:150px,grow]", "[80px:80px:80px,grow][80px:80px:80px,grow]"));
		
		panelAddInv = new JPanel();
		panelAddInv.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				InvoiceBLL invBLL = new InvoiceBLL();
				InvoiceDTO inv = getInvoiceInfo();
				
				if (invBLL.add(inv))
					dh.showDialog("Record added!", "Success!", JOptionPane.PLAIN_MESSAGE);
				else
					dh.showDialog("There was an error!", "Error!", JOptionPane.ERROR_MESSAGE);
				
				clearInvFields();
				initInvTable();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				panelAddInv.setBackground(Color.GREEN);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				panelAddInv.setBackground(new Color(218, 165, 32));
			}
		});
		panelAddInv.setBackground(new Color(218, 165, 32));
		panelInvBtns.add(panelAddInv, "cell 0 0,grow");
		panelAddInv.setLayout(new BorderLayout(0, 0));
		
		JLabel lblAddInv = new JLabel("Add Invoice");
		lblAddInv.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddInv.setFont(new Font("Tahoma", Font.BOLD, 14));
		panelAddInv.add(lblAddInv, BorderLayout.CENTER);
		
		panelUpdateInv = new JPanel();
		panelUpdateInv.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				InvoiceBLL invBLL = new InvoiceBLL();
				int rowIndex = tblInvoice.getSelectedRow();
				
				if (rowIndex == -1)
					dh.showDialog("Please select a row to modify!", "Error!", JOptionPane.ERROR_MESSAGE);
				else if (rowIndex == 0)
					dh.showDialog("Can't modify column names!", "Error!", JOptionPane.ERROR_MESSAGE);
				else {
					InvoiceDTO inv = getInvoiceInfo();
					
					if (invBLL.update(inv))
						dh.showDialog("Record updated!", "Success!", JOptionPane.PLAIN_MESSAGE);
					else
						dh.showDialog("There was an error!", "Error!", JOptionPane.ERROR_MESSAGE);
				}
				
				clearInvFields();
				initInvTable();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				panelUpdateInv.setBackground(Color.GREEN);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				panelUpdateInv.setBackground(new Color(218, 165, 32));
			}
		});
		panelUpdateInv.setBackground(new Color(218, 165, 32));
		panelInvBtns.add(panelUpdateInv, "cell 1 0,grow");
		panelUpdateInv.setLayout(new BorderLayout(0, 0));
		
		JLabel lblUpdateInv = new JLabel("Update Invoice");
		lblUpdateInv.setHorizontalAlignment(SwingConstants.CENTER);
		lblUpdateInv.setFont(new Font("Tahoma", Font.BOLD, 14));
		panelUpdateInv.add(lblUpdateInv, BorderLayout.CENTER);
		
		panelDeleteInv = new JPanel();
		panelDeleteInv.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				InvoiceBLL invBLL = new InvoiceBLL();
				int rowIndex = tblInvoice.getSelectedRow();
				
				if (rowIndex == -1)
					dh.showDialog("Please select a row to modify!", "Error!", JOptionPane.ERROR_MESSAGE);
				else if (rowIndex == 0)
					dh.showDialog("Can't modify column names!", "Error!", JOptionPane.ERROR_MESSAGE);
				else {
					InvoiceDTO inv = getInvoiceInfo();
					
					if (invBLL.remove(inv.getId()))
						dh.showDialog("Record deleted!", "Success!", JOptionPane.PLAIN_MESSAGE);
					else
						dh.showDialog("There was an error!", "Error!", JOptionPane.ERROR_MESSAGE);
				}
				
				clearInvFields();
				initInvTable();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				panelDeleteInv.setBackground(Color.GREEN);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				panelDeleteInv.setBackground(new Color(218, 165, 32));
			}
		});
		panelDeleteInv.setBackground(new Color(218, 165, 32));
		panelInvBtns.add(panelDeleteInv, "cell 2 0,grow");
		panelDeleteInv.setLayout(new BorderLayout(0, 0));
		
		JLabel lblDeleteInv = new JLabel("Delete Invoice");
		lblDeleteInv.setHorizontalAlignment(SwingConstants.CENTER);
		lblDeleteInv.setFont(new Font("Tahoma", Font.BOLD, 14));
		panelDeleteInv.add(lblDeleteInv, BorderLayout.CENTER);
		
		panelInvRadio = new JPanel();
		panelInvRadio.setOpaque(false);
		panelInvBtns.add(panelInvRadio, "cell 1 1 2 1,alignx center,aligny center");
		panelInvRadio.setLayout(new MigLayout("", "[][][]", "[]"));
		
		JRadioButton rdbtnById = new JRadioButton("By Invoice No.");
		invSearch.add(rdbtnById);
		rdbtnById.setSelected(true);
		rdbtnById.setOpaque(false);
		rdbtnById.setForeground(Color.WHITE);
		rdbtnById.setFont(new Font("Tahoma", Font.BOLD, 14));
		panelInvRadio.add(rdbtnById, "cell 0 0");
		
		JRadioButton rdbtnByEmployee = new JRadioButton("By Employee");
		invSearch.add(rdbtnByEmployee);
		rdbtnByEmployee.setOpaque(false);
		rdbtnByEmployee.setForeground(Color.WHITE);
		rdbtnByEmployee.setFont(new Font("Tahoma", Font.BOLD, 14));
		panelInvRadio.add(rdbtnByEmployee, "cell 2 0");
		
		panelSearchInv = new JPanel();
		panelSearchInv.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (rdbtnById.isSelected())
					searchInvoice(true);
				else
					searchInvoice(false);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				panelSearchInv.setBackground(Color.GREEN);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				panelSearchInv.setBackground(new Color(218, 165, 32));
			}
		});
		panelSearchInv.setBackground(new Color(218, 165, 32));
		panelInvBtns.add(panelSearchInv, "cell 0 1,grow");
		panelSearchInv.setLayout(new BorderLayout(0, 0));
		
		JLabel lblSearchInv = new JLabel("Search Invoice");
		lblSearchInv.setHorizontalAlignment(SwingConstants.CENTER);
		lblSearchInv.setFont(new Font("Tahoma", Font.BOLD, 14));
		panelSearchInv.add(lblSearchInv, BorderLayout.CENTER);
		
		tblInvoice = new JTable();
		tblInvoice.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int rowIndex = tblInvoice.getSelectedRow();

				// Prevents selecting column names
				if (rowIndex > 0) {
					txtInvId.setText(String.valueOf(invList.getValueAt(rowIndex, 0)));
					txtInvDeId.setText(String.valueOf(invList.getValueAt(rowIndex, 0)));
					txtInvDate.setText(String.valueOf(invList.getValueAt(rowIndex, 1)));
					cbEmp.setSelectedItem(invList.getValueAt(rowIndex, 2));
					
//					InvoiceDTO invoice = getInvoiceInfo();
//					initDeTableByInvId(invoice.getId());
					
					clearDeFields();
					initDeTableByInvId(Integer.valueOf(txtInvDeId.getText()));					
//					initDeTableByInvId(Integer.valueOf(String.valueOf(invList.getValueAt(rowIndex, 0))));
					
					displayTotalPrice();
				}
				else
					clearInvFields();
			}
		});
		panelInvoice.add(tblInvoice, "cell 0 2,grow");
		
		panelInvBottomBtns = new JPanel();
		panelInvBottomBtns.setOpaque(false);
		panelInvoice.add(panelInvBottomBtns, "cell 0 3,alignx center,growy");
		panelInvBottomBtns.setLayout(new MigLayout("", "[grow][grow]", "[grow]"));
		
		panelRefresh = new JPanel();
		panelRefresh.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				tblInvoice.clearSelection();
				initInvTable();
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
		panelInvBottomBtns.add(panelRefresh, "cell 0 0,grow");
		panelRefresh.setLayout(new BorderLayout(0, 0));
		
		lblRefreshList = new JLabel(" Refresh List ");
		lblRefreshList.setHorizontalAlignment(SwingConstants.CENTER);
		lblRefreshList.setFont(new Font("Tahoma", Font.BOLD, 14));
		panelRefresh.add(lblRefreshList, BorderLayout.CENTER);
		
		panelClear = new JPanel();
		panelClear.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				tblInvoice.clearSelection();
				clearInvFields();
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
		panelInvBottomBtns.add(panelClear, "cell 1 0,grow");
		panelClear.setLayout(new BorderLayout(0, 0));
		
		lblClearFields = new JLabel(" Clear Fields ");
		lblClearFields.setHorizontalAlignment(SwingConstants.CENTER);
		lblClearFields.setFont(new Font("Tahoma", Font.BOLD, 14));
		panelClear.add(lblClearFields, BorderLayout.CENTER);
		
		panelDetails = new JPanel();
		panelDetails.setOpaque(false);
		add(panelDetails, "cell 1 0,grow");
		panelDetails.setLayout(new MigLayout("", "[grow]", "[][][grow]"));
		
		panelDeTitle = new JPanel();
		panelDeTitle.setOpaque(false);
		panelDetails.add(panelDeTitle, "cell 0 0,grow");
		panelDeTitle.setLayout(new BorderLayout(0, 0));
		
		lblInvoiceDetails = new JLabel("Invoice Details");
		lblInvoiceDetails.setHorizontalAlignment(SwingConstants.CENTER);
		lblInvoiceDetails.setForeground(Color.RED);
		lblInvoiceDetails.setFont(new Font("Tahoma", Font.BOLD, 20));
		panelDeTitle.add(lblInvoiceDetails, BorderLayout.CENTER);
		
		panelDeContent = new JPanel();
		panelDeContent.setOpaque(false);
		panelDetails.add(panelDeContent, "cell 0 1,grow");
		panelDeContent.setLayout(new MigLayout("", "[grow]", "[grow][grow][]"));
		
		panelDeFields = new JPanel();
		panelDeFields.setOpaque(false);
		panelDeContent.add(panelDeFields, "cell 0 0,grow");
		panelDeFields.setLayout(new MigLayout("", "[][grow]", "[][][][]"));
		
		lblDeInvId = new JLabel("Invoice No.");
		lblDeInvId.setHorizontalAlignment(SwingConstants.TRAILING);
		lblDeInvId.setForeground(Color.WHITE);
		lblDeInvId.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panelDeFields.add(lblDeInvId, "cell 0 0,alignx trailing");
		
		txtInvDeId = new JTextField();
		txtInvDeId.setEditable(false);
		txtInvDeId.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtInvDeId.setColumns(10);
		panelDeFields.add(txtInvDeId, "cell 1 0,growx");
		
		lblProduct = new JLabel("Product");
		lblProduct.setHorizontalAlignment(SwingConstants.TRAILING);
		lblProduct.setForeground(Color.WHITE);
		lblProduct.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panelDeFields.add(lblProduct, "cell 0 1,alignx trailing");
		
		cbProd = new JComboBox<String>();
		cbProd.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				ProductBLL prodBLL = new ProductBLL();
				String prodId = sf.reformatIdValueString(String.valueOf(cbProd.getSelectedItem()), 0);
				int prodPrice = prodBLL.selectPrice(prodId);
				txtProdPrice.setText(String.valueOf(prodPrice));
			}
		});
		panelDeFields.add(cbProd, "cell 1 1,growx");
		DefaultComboBoxModel<String> cbProdData = new DefaultComboBoxModel<String>();
		ProductBLL prodBLL = new ProductBLL();
		ArrayList<ProductDTO> prodList = prodBLL.selectAll();
		for (ProductDTO product : prodList)
			cbProdData.addElement(product.getId() + " - " + product.getName());
		cbProd.setModel(cbProdData);
		String prodId = sf.reformatIdValueString(String.valueOf(cbProd.getSelectedItem()), 0);
		int prodPrice = prodBLL.selectPrice(prodId);
		
		lblPrice = new JLabel("Price");
		lblPrice.setHorizontalAlignment(SwingConstants.TRAILING);
		lblPrice.setForeground(Color.WHITE);
		lblPrice.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panelDeFields.add(lblPrice, "cell 0 2,alignx trailing");
		
		txtProdPrice = new JTextField();
		txtProdPrice.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtProdPrice.setEditable(false);
		txtProdPrice.setColumns(10);
		panelDeFields.add(txtProdPrice, "cell 1 2,growx");
		txtProdPrice.setText(String.valueOf(prodPrice));
		
		lblQuantity = new JLabel("Quantity");
		lblQuantity.setHorizontalAlignment(SwingConstants.TRAILING);
		lblQuantity.setForeground(Color.WHITE);
		lblQuantity.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panelDeFields.add(lblQuantity, "cell 0 3,alignx trailing");
		
		txtQuantity = new JTextField();
		txtQuantity.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtQuantity.setColumns(10);
		panelDeFields.add(txtQuantity, "cell 1 3,growx");
		
		panelDeBtns = new JPanel();
		panelDeBtns.setOpaque(false);
		panelDeContent.add(panelDeBtns, "cell 0 1,alignx center,aligny center");
		panelDeBtns.setLayout(new MigLayout("", "[:150px:150px,grow][:150px:150px,grow][:150px:150px,grow]", "[80px:80px:80px,grow][]"));
		
		panelAddDe = new JPanel();
		panelAddDe.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				InvoiceDetailsBLL deBLL = new InvoiceDetailsBLL();
				InvoiceDetailsDTO de = getDetailsInfo();
				
				if (deBLL.add(de))
					dh.showDialog("Record added!", "Success!", JOptionPane.PLAIN_MESSAGE);
				else
					dh.showDialog("There was an error!", "Error!", JOptionPane.ERROR_MESSAGE);
				
				clearDeFields();
				initDeTableByInvId(de.getInvId());
				displayTotalPrice();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				panelAddDe.setBackground(Color.GREEN);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				panelAddDe.setBackground(new Color(218, 165, 32));
			}
		});
		panelAddDe.setBackground(new Color(218, 165, 32));
		panelDeBtns.add(panelAddDe, "cell 0 0,grow");
		panelAddDe.setLayout(new BorderLayout(0, 0));
		
		lblAddDe = new JLabel("Add Product");
		lblAddDe.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddDe.setFont(new Font("Tahoma", Font.BOLD, 14));
		panelAddDe.add(lblAddDe, BorderLayout.CENTER);
		
		panelUpdateDe = new JPanel();
		panelUpdateDe.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				InvoiceDetailsBLL deBLL = new InvoiceDetailsBLL();
				int rowIndex = tblInvoice.getSelectedRow();
				
				if (rowIndex == -1)
					dh.showDialog("Please select a row to modify!", "Error!", JOptionPane.ERROR_MESSAGE);
				else if (rowIndex == 0)
					dh.showDialog("Can't modify column names!", "Error!", JOptionPane.ERROR_MESSAGE);
				else {
					InvoiceDetailsDTO de = getDetailsInfo();
					
					if (deBLL.update(de))
						dh.showDialog("Record updated!", "Success!", JOptionPane.PLAIN_MESSAGE);
					else
						dh.showDialog("There was an error!", "Error!", JOptionPane.ERROR_MESSAGE);
				}
				
				clearDeFields();
				initDeTableByInvId(Integer.valueOf(txtInvDeId.getText()));
				displayTotalPrice();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				panelUpdateDe.setBackground(Color.GREEN);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				panelUpdateDe.setBackground(new Color(218, 165, 32));
			}
		});
		panelUpdateDe.setBackground(new Color(218, 165, 32));
		panelDeBtns.add(panelUpdateDe, "cell 1 0,grow");
		panelUpdateDe.setLayout(new BorderLayout(0, 0));
		
		lblUpdateDe = new JLabel("Update Product");
		lblUpdateDe.setHorizontalAlignment(SwingConstants.CENTER);
		lblUpdateDe.setFont(new Font("Tahoma", Font.BOLD, 14));
		panelUpdateDe.add(lblUpdateDe, BorderLayout.CENTER);
		
		panelDeleteDe = new JPanel();
		panelDeleteDe.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				InvoiceDetailsBLL deBLL = new InvoiceDetailsBLL();
				int rowIndex = tblInvoice.getSelectedRow();
				
				if (rowIndex == -1)
					dh.showDialog("Please select a row to modify!", "Error!", JOptionPane.ERROR_MESSAGE);
				else if (rowIndex == 0)
					dh.showDialog("Can't modify column names!", "Error!", JOptionPane.ERROR_MESSAGE);
				else {
					InvoiceDetailsDTO de = getDetailsInfo();
					
					if (deBLL.remove(de.getProdId()))
						dh.showDialog("Record deleted!", "Success!", JOptionPane.PLAIN_MESSAGE);
					else
						dh.showDialog("There was an error!", "Error!", JOptionPane.ERROR_MESSAGE);
				}
				
				clearDeFields();
				initDeTableByInvId(Integer.valueOf(txtInvDeId.getText()));
				displayTotalPrice();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				panelDeleteDe.setBackground(Color.GREEN);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				panelDeleteDe.setBackground(new Color(218, 165, 32));
			}
		});
		panelDeleteDe.setBackground(new Color(218, 165, 32));
		panelDeBtns.add(panelDeleteDe, "cell 2 0,grow");
		panelDeleteDe.setLayout(new BorderLayout(0, 0));
		
		lblDeleteDe = new JLabel("Delete Product");
		lblDeleteDe.setHorizontalAlignment(SwingConstants.CENTER);
		lblDeleteDe.setFont(new Font("Tahoma", Font.BOLD, 14));
		panelDeleteDe.add(lblDeleteDe, BorderLayout.CENTER);
		
		panelTotalPrice = new JPanel();
		panelDeContent.add(panelTotalPrice, "cell 0 2,alignx center,aligny center");
		panelTotalPrice.setOpaque(false);
		panelTotalPrice.setBackground(new Color(218, 165, 32));
		panelTotalPrice.setLayout(new BorderLayout(0, 0));
		
		lblTotalPrice = new JLabel("Total Price: 0");
		lblTotalPrice.setForeground(Color.RED);
		lblTotalPrice.setHorizontalAlignment(SwingConstants.CENTER);
		lblTotalPrice.setFont(new Font("Tahoma", Font.BOLD, 14));
		panelTotalPrice.add(lblTotalPrice, BorderLayout.CENTER);
		
		tblDetails = new JTable();
		tblDetails.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int rowIndex = tblDetails.getSelectedRow();

				// Prevents selecting column names
				if (rowIndex > 0) {
//					System.out.print(cbProd.getSelectedItem());
					cbProd.setSelectedItem(deList.getValueAt(rowIndex, 0) + " - " + deList.getValueAt(rowIndex, 1));
					txtQuantity.setText(String.valueOf(deList.getValueAt(rowIndex, 3)));
				}
				else
					clearDeFields();
			}
		});
		panelDetails.add(tblDetails, "cell 0 2,grow");
		
		initInvTable();
//		initDeTable();
		clearDeTable();
		
	}
	
	private void clearInvFields() {
		txtInvId.setText("");
		txtInvDate.setText("");
		cbEmp.setSelectedIndex(0);
	}
	
	private InvoiceDTO getInvoiceInfo() {
		int id = 0;
		try {
			id = Integer.valueOf(txtInvId.getText());
		} catch (Exception e1) {
			txtInvId.setText("");
			dh.showDialog("Invalid ID!", "Error!", JOptionPane.ERROR_MESSAGE);
		}
		Date invDate = Date.valueOf(sf.reformatDateString(txtInvDate.getText(), false));
		int empId = Integer.valueOf(sf.reformatIdValueString(cbEmp.getSelectedItem().toString(), 0));
		String empName = sf.reformatIdValueString(cbEmp.getSelectedItem().toString(), 1);
		
		InvoiceDTO invoice = new InvoiceDTO(id, invDate, empId, empName);
		return invoice;
	}
	
	private void clearInvTable() {
		invList = new DefaultTableModel();
		invList.addColumn("MaHD");
		invList.addColumn("NgayLap");
		invList.addColumn("TenNV");

		// Clear the table
		for (int i = invList.getRowCount() - 1; i >= 0; i--) {
			invList.removeRow(i);
		}

		// Generates a row of column names
		Vector<String> colName = new Vector<String>();
		colName.add("Invoice No.");
		colName.add("Date");
		colName.add("Managing Employee");
		invList.addRow(colName);
		
		tblInvoice.setModel(invList);
	}
	
	private void initInvTable() {
		clearInvTable();

		// Fetch data from table
		InvoiceBLL invBLL = new InvoiceBLL();
		ArrayList<InvoiceDTO> tblInv = invBLL.selectAll();
		// Transfer to table
		for (InvoiceDTO invoice : tblInv) {
			invDetails = new Vector<String>();
			invDetails.add(String.valueOf(invoice.getId()));
			invDetails.add(sf.reformatDateString(String.valueOf(invoice.getSaleDate()), true));
			invDetails.add(invoice.getEmpName());
			invList.addRow(invDetails);
		}
	}
	
	private void searchInvoice(boolean byId) {
		InvoiceBLL invBLL = new InvoiceBLL();	
		int count = 0;
		
		clearInvTable();
		
		if (byId) {
			int id = 0;
			
			try {
				id = Integer.valueOf(txtInvId.getText());
			} catch (Exception e1) {
				clearInvFields();
				initInvTable();
				dh.showDialog("Invalid ID!", "Error!", JOptionPane.ERROR_MESSAGE);
			}
			
			InvoiceDTO invoice = invBLL.searchById(id);
			// Transfer to table
			invDetails = new Vector<String>();
			invDetails.add(String.valueOf(invoice.getId()));
			invDetails.add(sf.reformatDateString(String.valueOf(invoice.getSaleDate()), true));
			invDetails.add(invoice.getEmpName());
			invList.addRow(invDetails);
			count++;
		}
		else {
			int empId = Integer.valueOf(sf.reformatIdValueString(cbEmp.getSelectedItem().toString(), 0));
			
			ArrayList<InvoiceDTO> tblInv = invBLL.searchByEmp(empId);
			// Transfer to table
			for (InvoiceDTO invoice : tblInv) {
				invDetails = new Vector<String>();
				invDetails.add(String.valueOf(invoice.getId()));
				invDetails.add(sf.reformatDateString(String.valueOf(invoice.getSaleDate()), true));
				invDetails.add(invoice.getEmpName());
				invList.addRow(invDetails);
				count++;
			}
		}
		dh.showDialog(String.valueOf(count) + " result(s) found!", "Search completed!", JOptionPane.PLAIN_MESSAGE);
	}
	
	private void clearDeFields() {
		cbProd.setSelectedIndex(0);
		txtQuantity.setText("");
	}
	
	private InvoiceDetailsDTO getDetailsInfo() {
//		int invId = 0;
//		try {
//			invId = Integer.valueOf(txtInvId.getText());
//		} catch (Exception e1) {
//			txtInvDeId.setText("");
//			dh.showDialog("Invalid ID!", "Error!", JOptionPane.ERROR_MESSAGE);
//		}
		int invId = Integer.valueOf(txtInvDeId.getText());
		
		String prodId = sf.reformatIdValueString(String.valueOf(cbProd.getSelectedItem()), 0);
		String prodName = sf.reformatIdValueString(String.valueOf(cbProd.getSelectedItem()), 1);
		int prodPrice = Integer.valueOf(txtProdPrice.getText());
		int quantity = Math.abs(Integer.valueOf(txtQuantity.getText()));
		int totalPrice = prodPrice * quantity;
//		
		InvoiceDetailsDTO details = new InvoiceDetailsDTO(invId, prodId, prodName, prodPrice, quantity, totalPrice);
		return details;
	}
	
	private void clearDeTable() {
		deList = new DefaultTableModel();
//		deList.addColumn("MaHD");
		deList.addColumn("MaMH");
		deList.addColumn("TenMH");
		deList.addColumn("DonGia");
		deList.addColumn("SoLuong");
		deList.addColumn("ThanhTien");

		// Clear the table
		for (int i = deList.getRowCount() - 1; i >= 0; i--) {
			deList.removeRow(i);
		}

		// Generates a row of column names
		Vector<String> colName = new Vector<String>();
//		colName.add("Invoice No.");
		colName.add("Product ID");
		colName.add("Product Name");
		colName.add("Product Price");
		colName.add("Quantity");
		colName.add("Product Total");
		deList.addRow(colName);
		
		tblDetails.setModel(deList);
	}
	
//	private void initDeTable() {		
//		clearDeTable();
//		
//		InvoiceDetailsBLL deBLL = new InvoiceDetailsBLL();
//		ArrayList<InvoiceDetailsDTO> tblList = deBLL.selectAll();
//		
//		for (InvoiceDetailsDTO details : tblList) {
//			deDetails = new Vector<String>();
//			deDetails.add(String.valueOf(details.getInvId()));
//			deDetails.add(details.getProdId());
//			deDetails.add(details.getProdName());
//			deDetails.add(String.valueOf(details.getProdPrice()));
//			deDetails.add(String.valueOf(details.getQuantity()));
//			deDetails.add(String.valueOf(details.getTotalPrice()));
//			deList.addRow(deDetails);
//		}
//	}
	
	private void initDeTableByInvId(int id) {
		clearDeTable();
		
		InvoiceDetailsBLL deBLL = new InvoiceDetailsBLL();
		ArrayList<InvoiceDetailsDTO> tblList = deBLL.selectByInvId(id);
		
		for (InvoiceDetailsDTO details : tblList) {
			deDetails = new Vector<String>();
//			deDetails.add(String.valueOf(details.getInvId()));
			deDetails.add(details.getProdId());
			deDetails.add(details.getProdName());
			deDetails.add(String.valueOf(details.getProdPrice()));
			deDetails.add(String.valueOf(details.getQuantity()));
			deDetails.add(String.valueOf(details.getTotalPrice()));
			deList.addRow(deDetails);
		}
	}

	private void displayTotalPrice() {
		if (tblDetails.getRowCount() < 2)
			lblTotalPrice.setText("Total Price: 0");
		else {
			int total = 0;
			for (int i = 1; i < tblDetails.getRowCount(); i++){
		        
		        int prodPrice = Integer.valueOf(String.valueOf(tblDetails.getValueAt(i, 4)));
				
		        total += prodPrice;
		        lblTotalPrice.setText("Total Price: " + String.valueOf(total));
		    }
		}
	}
}
