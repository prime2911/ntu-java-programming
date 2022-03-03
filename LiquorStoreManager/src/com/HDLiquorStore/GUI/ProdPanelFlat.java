package com.HDLiquorStore.GUI;

import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.HDLiquorStore.BLL.ProductBLL;
import com.HDLiquorStore.BLL.ProductTypeBLL;
import com.HDLiquorStore.DTO.ProductDTO;
import com.HDLiquorStore.DTO.ProductTypeDTO;
import com.HDLiquorStore.Utils.*;

import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.BorderLayout;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

@SuppressWarnings("serial")
public class ProdPanelFlat extends BackgroundPane {
	DefaultTableModel prodList = new DefaultTableModel();
	Vector<String> prodDetails;
	private JPanel panelFields;
	private JTable tblProd;
	private JComboBox<String> cbType;
	private JPanel panelButtons;
	private JPanel panelTopBuffer;
	private JPanel panelLeftBuffer;
	private JPanel panelRightBuffer;
	private JPanel panelBottomBuffer;
	private JLabel lblType;
	private JLabel lblProductId;
	private JTextField txtId;
	private JLabel lblName;
	private JTextField txtName;
	private JLabel lblPrice;
	private JTextField txtPrice;
	private JLabel lblOrigins;
	private JTextField txtOrigins;
	private JPanel panelAdd;
	private JLabel lblAdd;
	private JPanel panelUpdate;
	private JLabel lblUpdate;
	private JPanel panelDelete;
	private JPanel panelSearch;
	private JPanel panelRadio;
	private JLabel lblDelete;
	private JLabel lblSearch;
	private JRadioButton rdbtnById;
	private JRadioButton rdbtnByName;
	private JRadioButton rdbtnByType;
	private final ButtonGroup prodSearch = new ButtonGroup();
	private JPanel panelBottomBtns;
	private JPanel panelRefresh;
	private JPanel panelClear;
	private JLabel lblRefreshList;
	private JLabel lblClearFields;
	private StringFormatter sf = new StringFormatter();
	private DialogHelper dh = new DialogHelper();

	/**
	 * Create the panel.
	 */
	public ProdPanelFlat() {
		try {
			setBackground(ImageIO.read(getClass().getResource("/pub_logo.png")));
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		setLayout(new MigLayout("", "[10px:10px:10px][grow][:320px:320px,grow][10px:10px:10px]", "[10px:10px:10px][][grow][:60px:60px,grow][10px:10px:10px]"));
		setVisible(false);
		
		tblProd = new JTable();
		tblProd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int rowIndex = tblProd.getSelectedRow();

				// Prevents selecting column names
				if (rowIndex > 0) {
					txtId.setText(String.valueOf(prodList.getValueAt(rowIndex, 0)));
					txtName.setText(String.valueOf(prodList.getValueAt(rowIndex, 1)));
					txtPrice.setText(String.valueOf(prodList.getValueAt(rowIndex, 2)));
					txtOrigins.setText(String.valueOf(prodList.getValueAt(rowIndex, 3)));
					cbType.setSelectedItem(prodList.getValueAt(rowIndex, 4));
				}
				else
					clearFields();
			}
		});
		add(tblProd, "cell 1 2 2 1,grow");
		
		panelTopBuffer = new JPanel();
		panelTopBuffer.setOpaque(false);
		add(panelTopBuffer, "cell 1 0,grow");
		
		panelLeftBuffer = new JPanel();
		panelLeftBuffer.setOpaque(false);
		add(panelLeftBuffer, "cell 0 1,grow");
		
		panelFields = new JPanel();
		panelFields.setOpaque(false);
		add(panelFields, "cell 1 1,grow");
		panelFields.setLayout(new MigLayout("", "[][grow]", "[][][][][]"));
		
		lblProductId = new JLabel("Product ID");
		lblProductId.setHorizontalAlignment(SwingConstants.TRAILING);
		lblProductId.setForeground(Color.WHITE);
		lblProductId.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panelFields.add(lblProductId, "cell 0 0,alignx trailing");
		
		txtId = new JTextField();
		txtId.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtId.setColumns(10);
		panelFields.add(txtId, "cell 1 0,growx");
		
		lblName = new JLabel("Name");
		lblName.setHorizontalAlignment(SwingConstants.TRAILING);
		lblName.setForeground(Color.WHITE);
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panelFields.add(lblName, "cell 0 1,alignx trailing");
		
		txtName = new JTextField();
		txtName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtName.setColumns(10);
		panelFields.add(txtName, "cell 1 1,growx");
		
		lblPrice = new JLabel("Price");
		lblPrice.setHorizontalAlignment(SwingConstants.TRAILING);
		lblPrice.setForeground(Color.WHITE);
		lblPrice.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panelFields.add(lblPrice, "cell 0 2,alignx trailing");
		
		txtPrice = new JTextField();
		txtPrice.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtPrice.setColumns(10);
		panelFields.add(txtPrice, "cell 1 2,growx");
		
		lblOrigins = new JLabel("Origins");
		lblOrigins.setHorizontalAlignment(SwingConstants.TRAILING);
		lblOrigins.setForeground(Color.WHITE);
		lblOrigins.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panelFields.add(lblOrigins, "cell 0 3,alignx trailing");
		
		txtOrigins = new JTextField();
		txtOrigins.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtOrigins.setColumns(10);
		panelFields.add(txtOrigins, "cell 1 3,growx");
		
		lblType = new JLabel("Type");
		lblType.setHorizontalAlignment(SwingConstants.TRAILING);
		lblType.setForeground(Color.WHITE);
		lblType.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panelFields.add(lblType, "cell 0 4,alignx trailing");
		
		cbType = new JComboBox<String>();
		panelFields.add(cbType, "cell 1 4,growx");
		DefaultComboBoxModel<String> cbData = new DefaultComboBoxModel<String>();
		ProductTypeBLL typeBLL = new ProductTypeBLL();
		ArrayList<ProductTypeDTO> typeList = typeBLL.selectAll();
		for (ProductTypeDTO type : typeList)
			cbData.addElement(type.getId() + " - " + type.getName());
		cbType.setModel(cbData);
		
		panelButtons = new JPanel();
		panelButtons.setOpaque(false);
		add(panelButtons, "cell 2 1,grow");
		panelButtons.setLayout(new MigLayout("", "[:150px:150px,grow][:150px:150px,grow]", "[:60px:60px,grow][:60px:60px,grow][:60px:60px,grow]"));
		
		panelAdd = new JPanel();
		panelAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ProductBLL prodBLL = new ProductBLL();
				ProductDTO prod = getProductInfo();
				
//				prodBLL.add(prod);
				if (prodBLL.add(prod))
					dh.showDialog("Record added!", "Success!", JOptionPane.PLAIN_MESSAGE);
				else
					dh.showDialog("There was an error!", "Error!", JOptionPane.ERROR_MESSAGE);
//				showDialog("Record added!", "Success!", JOptionPane.PLAIN_MESSAGE);
				clearFields();
				initProdTable();
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
		panelAdd.setBackground(new Color(218, 165, 32));
		panelButtons.add(panelAdd, "cell 0 0,grow");
		panelAdd.setLayout(new BorderLayout(0, 0));
		
		lblAdd = new JLabel("Add Product");
		lblAdd.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdd.setFont(new Font("Tahoma", Font.BOLD, 14));
		panelAdd.add(lblAdd, BorderLayout.CENTER);
		
		panelUpdate = new JPanel();
		panelUpdate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ProductBLL prodBLL = new ProductBLL();				
				int rowIndex = tblProd.getSelectedRow();
//				boolean dialog = false;
				
				if (rowIndex == -1)
					dh.showDialog("Please select a row to modify!", "Error!", JOptionPane.ERROR_MESSAGE);
				else if (rowIndex == 0)
					dh.showDialog("Can't modify column names!", "Error!", JOptionPane.ERROR_MESSAGE);
				else {
					ProductDTO prod = getProductInfo();					
//					prodBLL.update(prod);
//					dialog = true;
					if (prodBLL.update(prod))
						dh.showDialog("Record updated!", "Success!", JOptionPane.PLAIN_MESSAGE);
					else
						dh.showDialog("There was an error!", "Error!", JOptionPane.ERROR_MESSAGE);
				}
				
//				if (dialog)
//					dh.showDialog("Record updated!", "Success!", JOptionPane.PLAIN_MESSAGE);
				clearFields();
				initProdTable();
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
		panelUpdate.setBackground(new Color(218, 165, 32));
		panelButtons.add(panelUpdate, "cell 1 0,grow");
		panelUpdate.setLayout(new BorderLayout(0, 0));
		
		lblUpdate = new JLabel("Update Product");
		lblUpdate.setHorizontalAlignment(SwingConstants.CENTER);
		lblUpdate.setFont(new Font("Tahoma", Font.BOLD, 14));
		panelUpdate.add(lblUpdate, BorderLayout.CENTER);
		
		panelDelete = new JPanel();
		panelDelete.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ProductBLL prodBLL = new ProductBLL();				
				int rowIndex = tblProd.getSelectedRow();
//				boolean dialog = false;
				
				if (rowIndex == -1)
					dh.showDialog("Please select a row to modify!", "Error!", JOptionPane.ERROR_MESSAGE);
				else if (rowIndex == 0)
					dh.showDialog("Can't modify column names!", "Error!", JOptionPane.ERROR_MESSAGE);
				else {
					ProductDTO prod = getProductInfo();					
//					prodBLL.remove(prod.getId());
//					dialog = true;
					if (prodBLL.remove(prod.getId()))
						dh.showDialog("Record deleted!", "Success!", JOptionPane.PLAIN_MESSAGE);
					else
						dh.showDialog("There was an error!", "Error!", JOptionPane.ERROR_MESSAGE);
				}
				
//				if(dialog)
//					dh.showDialog("Record deleted!", "Success!", JOptionPane.PLAIN_MESSAGE);
				clearFields();
				initProdTable();
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
		panelDelete.setBackground(new Color(218, 165, 32));
		panelButtons.add(panelDelete, "cell 0 1,grow");
		panelDelete.setLayout(new BorderLayout(0, 0));
		
		lblDelete = new JLabel("Delete Product");
		lblDelete.setHorizontalAlignment(SwingConstants.CENTER);
		lblDelete.setFont(new Font("Tahoma", Font.BOLD, 14));
		panelDelete.add(lblDelete, BorderLayout.CENTER);
		
		panelRadio = new JPanel();
		panelRadio.setOpaque(false);
		panelButtons.add(panelRadio, "cell 0 2 1 2,growx,aligny center");
		panelRadio.setLayout(new MigLayout("", "[][][]", "[]"));
		
		rdbtnById = new JRadioButton("By Prod. ID");
		prodSearch.add(rdbtnById);
		rdbtnById.setSelected(true);
		rdbtnById.setOpaque(false);
		rdbtnById.setForeground(Color.WHITE);
		rdbtnById.setFont(new Font("Tahoma", Font.BOLD, 14));
		panelRadio.add(rdbtnById, "cell 0 0");
		
		rdbtnByName = new JRadioButton("By Name");
		prodSearch.add(rdbtnByName);
		rdbtnByName.setOpaque(false);
		rdbtnByName.setForeground(Color.WHITE);
		rdbtnByName.setFont(new Font("Tahoma", Font.BOLD, 14));
		panelRadio.add(rdbtnByName, "cell 1 0");
		
		rdbtnByType = new JRadioButton("By Type");
		prodSearch.add(rdbtnByType);
		rdbtnByType.setOpaque(false);
		rdbtnByType.setForeground(Color.WHITE);
		rdbtnByType.setFont(new Font("Tahoma", Font.BOLD, 14));
		panelRadio.add(rdbtnByType, "cell 2 0");
		
		panelSearch = new JPanel();
		panelSearch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (rdbtnById.isSelected())
					searchProduct(0);
				else if (rdbtnByName.isSelected())
					searchProduct(1);
				else
					searchProduct(2);
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
		panelSearch.setBackground(new Color(218, 165, 32));
		panelButtons.add(panelSearch, "cell 1 1,grow");
		panelSearch.setLayout(new BorderLayout(0, 0));
		
		lblSearch = new JLabel("Search Product");
		lblSearch.setHorizontalAlignment(SwingConstants.CENTER);
		lblSearch.setFont(new Font("Tahoma", Font.BOLD, 14));
		panelSearch.add(lblSearch, BorderLayout.CENTER);
		
		panelRightBuffer = new JPanel();
		panelRightBuffer.setOpaque(false);
		add(panelRightBuffer, "cell 3 1,grow");
		
		panelBottomBtns = new JPanel();
		panelBottomBtns.setOpaque(false);
		add(panelBottomBtns, "cell 1 3 2 1,grow");
		panelBottomBtns.setLayout(new MigLayout("", "[grow][grow]", "[grow]"));
		
		panelRefresh = new JPanel();
		panelRefresh.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				tblProd.clearSelection();
				initProdTable();
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
		
		lblRefreshList = new JLabel(" Refresh List ");
		lblRefreshList.setHorizontalAlignment(SwingConstants.CENTER);
		lblRefreshList.setFont(new Font("Tahoma", Font.BOLD, 14));
		panelRefresh.add(lblRefreshList, BorderLayout.CENTER);
		
		panelClear = new JPanel();
		panelClear.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				tblProd.clearSelection();
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
		
		lblClearFields = new JLabel(" Clear Fields ");
		lblClearFields.setHorizontalAlignment(SwingConstants.CENTER);
		lblClearFields.setFont(new Font("Tahoma", Font.BOLD, 14));
		panelClear.add(lblClearFields, BorderLayout.CENTER);
		
		panelBottomBuffer = new JPanel();
		panelBottomBuffer.setOpaque(false);
		add(panelBottomBuffer, "cell 1 4,grow");
		
		initProdTable();

	}
	
	private void clearProdTable() {
		prodList = new DefaultTableModel();
		prodList.addColumn("MaMH");
		prodList.addColumn("TenMH");
		prodList.addColumn("DonGia");
		prodList.addColumn("XuatXu");
		prodList.addColumn("Loai");

		// Clear the table
		for (int i = prodList.getRowCount() - 1; i >= 0; i--) {
			prodList.removeRow(i);
		}

		// Generates a row of column names
		Vector<String> colName = new Vector<String>();
		colName.add("Product ID");
		colName.add("Product Name");
		colName.add("Price");
		colName.add("Origins");
		colName.add("Type");
		prodList.addRow(colName);
		
		tblProd.setModel(prodList);
	}
	
	private void initProdTable() {
		clearProdTable();

		// Fetch data from table
		ProductBLL prodBLL = new ProductBLL();
		ArrayList<ProductDTO> tblEmp = prodBLL.selectAll();
		// Transfer to table
		for (ProductDTO product : tblEmp) {
			prodDetails = new Vector<String>();
			prodDetails.add(product.getId());
			prodDetails.add(product.getName());
			prodDetails.add(String.valueOf(product.getPrice()));
			prodDetails.add(product.getOrigins());
			prodDetails.add(product.getType());
			prodList.addRow(prodDetails);
		}
	}
	
	private void clearFields() {
		txtId.setText("");
		txtName.setText("");
		txtPrice.setText("");
		txtOrigins.setText("");
		cbType.setSelectedIndex(0);
	}
	
//	private void showDialog(String msg, String title, int msgType) {
//		JFrame frame = new JFrame("JDialog");
//		JOptionPane.showMessageDialog(frame, msg, title, msgType);
//	}
	
	private ProductDTO getProductInfo() {
		String id = txtId.getText();
		String name = txtName.getText();
		int price = 0;
		try {
			price = Math.abs(Integer.valueOf(txtPrice.getText()));
		} catch (Exception e1) {
			txtPrice.setText("");
			dh.showDialog("Invalid price value!", "Error!", JOptionPane.ERROR_MESSAGE);
		}
		String origins = txtOrigins.getText();
		String typeId = sf.reformatIdValueString(cbType.getSelectedItem().toString(), 0);
		String type = sf.reformatIdValueString(cbType.getSelectedItem().toString(), 1);
		
		ProductDTO product = new ProductDTO(id, name, price, origins, typeId, type);
		return product;
	}
	
	private void searchProduct(int searchMode) {
		ProductBLL prodBLL = new ProductBLL();	
		int count = 0;
		
		clearProdTable();
		
		if (searchMode == 0) {
			String id = txtId.getText();
			
			ArrayList<ProductDTO> tblProd = prodBLL.searchById(id);
			// Transfer to table
			for (ProductDTO product : tblProd) {
				prodDetails = new Vector<String>();
				prodDetails.add(product.getId());
				prodDetails.add(product.getName());
				prodDetails.add(String.valueOf(product.getPrice()));
				prodDetails.add(product.getOrigins());
				prodDetails.add(product.getType());
				prodList.addRow(prodDetails);
				count++;
			}
		}
		else if (searchMode == 1) {
			String name = txtName.getText();
			ArrayList<ProductDTO> tblProd = prodBLL.searchByName(name);
			// Transfer to table
			for (ProductDTO product : tblProd) {
				prodDetails = new Vector<String>();
				prodDetails.add(product.getId());
				prodDetails.add(product.getName());
				prodDetails.add(String.valueOf(product.getPrice()));
				prodDetails.add(product.getOrigins());
				prodDetails.add(product.getType());
				prodList.addRow(prodDetails);
				count++;
			}
		}	
		else {
			String typeId = sf.reformatIdValueString(cbType.getSelectedItem().toString(), 0);
			ArrayList<ProductDTO> tblProd = prodBLL.searchByType(typeId);
			// Transfer to table
			for (ProductDTO product : tblProd) {
				prodDetails = new Vector<String>();
				prodDetails.add(product.getId());
				prodDetails.add(product.getName());
				prodDetails.add(String.valueOf(product.getPrice()));
				prodDetails.add(product.getOrigins());
				prodDetails.add(product.getType());
				prodList.addRow(prodDetails);
				count++;
			}
		}
		dh.showDialog(String.valueOf(count) + " result(s) found!", "Search completed!", JOptionPane.PLAIN_MESSAGE);
	}

}
