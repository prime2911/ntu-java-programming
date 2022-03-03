package com.HDLiquorStore.GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import java.awt.FlowLayout;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import com.HDLiquorStore.Utils.BackgroundPane;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import net.miginfocom.swing.MigLayout;
import java.awt.Frame;

public class MainWindowFlat {

	JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindowFlat window = new MainWindowFlat();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWindowFlat() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setExtendedState(Frame.MAXIMIZED_BOTH);
		frame.setResizable(false);
		frame.setUndecorated(true);
		frame.setBounds(100, 100, 907, 550);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		EmpPanelFlat empPanel = new EmpPanelFlat();
		ProdPanelFlat prodPanel = new ProdPanelFlat();
		InvoicePanelFlat invoicePanel = new InvoicePanelFlat();
		
		JPanel panelHeader = new JPanel();
		panelHeader.setBackground(Color.BLUE);
		frame.getContentPane().add(panelHeader, BorderLayout.NORTH);
		
		JPanel panelAbout = new JPanel();
		panelAbout.setBackground(Color.LIGHT_GRAY);
		panelAbout.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JOptionPane.showMessageDialog(frame, "Welcome to My Liquor Store Manager!", "About", JOptionPane.INFORMATION_MESSAGE);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				panelAbout.setBackground(new Color(0, 0, 128));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				panelAbout.setBackground(Color.LIGHT_GRAY);
			}
		});
		panelHeader.setLayout(new MigLayout("", "[grow][52px][66px][37px]", "[27px,grow]"));
		
		JPanel panelTitle = new JPanel();
		panelTitle.setOpaque(false);
		panelHeader.add(panelTitle, "cell 0 0,grow");
		
		JLabel lblTitle = new JLabel("LIQUOR STORE MANAGER");
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		panelTitle.add(lblTitle);
		panelHeader.add(panelAbout, "cell 1 0,alignx left,aligny top");
		
		JLabel lblAbout = new JLabel("About");
		lblAbout.setForeground(Color.RED);
		lblAbout.setHorizontalAlignment(SwingConstants.CENTER);
		lblAbout.setFont(new Font("Tahoma", Font.BOLD, 14));
		panelAbout.add(lblAbout);
		
		JPanel panelLogout = new JPanel();
		panelLogout.setBackground(Color.LIGHT_GRAY);
		panelLogout.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				LoginFlat loginWindow = new LoginFlat();
				loginWindow.frmLogin.setVisible(true);
				frame.dispose();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				panelLogout.setBackground(new Color(0, 0, 128));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				panelLogout.setBackground(Color.LIGHT_GRAY);
			}
		});
		panelHeader.add(panelLogout, "cell 2 0,alignx left,aligny top");
		
		JLabel lblLogOut = new JLabel("Log Out");
		lblLogOut.setForeground(Color.RED);
		lblLogOut.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogOut.setFont(new Font("Tahoma", Font.BOLD, 14));
		panelLogout.add(lblLogOut);
		
		JPanel panelExit = new JPanel();
		panelExit.setBackground(Color.LIGHT_GRAY);
		panelExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				panelExit.setBackground(new Color(0, 0, 128));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				panelExit.setBackground(Color.LIGHT_GRAY);
			}
		});
		panelHeader.add(panelExit, "cell 3 0,alignx left,aligny top");
		
		JLabel lblExit = new JLabel("Exit");
		lblExit.setForeground(Color.RED);
		lblExit.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblExit.setHorizontalAlignment(SwingConstants.CENTER);
		panelExit.add(lblExit);
		
		BackgroundPane panelContent = new BackgroundPane();
		try {
			panelContent.setBackground(ImageIO.read(getClass().getResource("/pub_logo.png")));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		frame.getContentPane().add(panelContent, BorderLayout.CENTER);
		
		JPanel panelMenu = new JPanel();
		panelMenu.setBackground(Color.RED);
		frame.getContentPane().add(panelMenu, BorderLayout.WEST);
		panelMenu.setLayout(new MigLayout("", "[189px,grow]", "[30px][50px,grow][50px,grow][50px,grow]"));
		
		JPanel panelDashboard = new JPanel();
		panelDashboard.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				initContentPanel(panelContent, empPanel, prodPanel, invoicePanel);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				panelDashboard.setBackground(Color.GREEN);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				panelDashboard.setBackground(new Color(255, 255, 0));
			}
		});
		panelDashboard.setBackground(new Color(255, 255, 0));
		panelMenu.add(panelDashboard, "cell 0 0,alignx left,growy");
		panelDashboard.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblDashboard = new JLabel("Admin Dashboard");
		lblDashboard.setForeground(Color.BLUE);
		lblDashboard.setFont(new Font("Tahoma", Font.BOLD, 20));
		panelDashboard.add(lblDashboard);
		
		JPanel panelEmployee = new JPanel();
		panelEmployee.setBackground(new Color(218, 165, 32));
		panelEmployee.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {				
				initContentPanel(panelContent, empPanel, prodPanel, invoicePanel);
				frame.getContentPane().remove(panelContent);
				frame.getContentPane().add(empPanel, BorderLayout.CENTER);
				empPanel.setVisible(true);
				frame.getContentPane().revalidate();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				panelEmployee.setBackground(Color.GREEN);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				panelEmployee.setBackground(new Color(218, 165, 32));
			}
		});
		panelMenu.add(panelEmployee, "cell 0 1,grow");
		panelEmployee.setLayout(new BorderLayout(5, 5));
		
		JLabel lblEmployeeIcon = new JLabel("");
		lblEmployeeIcon.setIcon(new ImageIcon(MainWindowFlat.class.getResource("/employee_64.png")));
		lblEmployeeIcon.setHorizontalAlignment(SwingConstants.CENTER);
		panelEmployee.add(lblEmployeeIcon, BorderLayout.CENTER);
		
		JLabel lblEmployee = new JLabel("Employee Manager");
		lblEmployee.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblEmployee.setHorizontalAlignment(SwingConstants.CENTER);
		panelEmployee.add(lblEmployee, BorderLayout.SOUTH);
		
		JLabel lblEmployeeBuffer = new JLabel("");
		lblEmployeeBuffer.setHorizontalAlignment(SwingConstants.CENTER);
		panelEmployee.add(lblEmployeeBuffer, BorderLayout.NORTH);
		
		JPanel panelProduct = new JPanel();
		panelProduct.setBackground(new Color(218, 165, 32));
		panelProduct.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				initContentPanel(panelContent, empPanel, prodPanel, invoicePanel);
				frame.getContentPane().remove(panelContent);
				frame.getContentPane().add(prodPanel, BorderLayout.CENTER);
				prodPanel.setVisible(true);
				frame.getContentPane().revalidate();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				panelProduct.setBackground(Color.GREEN);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				panelProduct.setBackground(new Color(218, 165, 32));
			}
		});
		panelMenu.add(panelProduct, "cell 0 2,grow");
		panelProduct.setLayout(new BorderLayout(0, 5));
		
		JLabel lblProductIcon = new JLabel("");
		lblProductIcon.setIcon(new ImageIcon(MainWindowFlat.class.getResource("/drink_64.png")));
		lblProductIcon.setHorizontalAlignment(SwingConstants.CENTER);
		panelProduct.add(lblProductIcon, BorderLayout.CENTER);
		
		JLabel lblProductManager = new JLabel("Product Manager");
		lblProductManager.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblProductManager.setHorizontalAlignment(SwingConstants.CENTER);
		panelProduct.add(lblProductManager, BorderLayout.SOUTH);
		
		JLabel lblProductBuffer = new JLabel("");
		panelProduct.add(lblProductBuffer, BorderLayout.NORTH);
		
		JPanel panelInvoice = new JPanel();
		panelInvoice.setBackground(new Color(218, 165, 32));
		panelInvoice.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				initContentPanel(panelContent, empPanel, prodPanel, invoicePanel);
				frame.getContentPane().remove(panelContent);
				frame.getContentPane().add(invoicePanel, BorderLayout.CENTER);
				invoicePanel.setVisible(true);
				frame.getContentPane().revalidate();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				panelInvoice.setBackground(Color.GREEN);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				panelInvoice.setBackground(new Color(218, 165, 32));
			}
		});
		panelMenu.add(panelInvoice, "cell 0 3,grow");
		panelInvoice.setLayout(new BorderLayout(5, 5));
		
		JLabel lblInvoiceIcon = new JLabel("");
		lblInvoiceIcon.setIcon(new ImageIcon(MainWindowFlat.class.getResource("/invoice_64.png")));
		lblInvoiceIcon.setHorizontalAlignment(SwingConstants.CENTER);
		panelInvoice.add(lblInvoiceIcon, BorderLayout.CENTER);
		
		JLabel lblViewInvoice = new JLabel("View Invoice");
		lblViewInvoice.setHorizontalAlignment(SwingConstants.CENTER);
		lblViewInvoice.setFont(new Font("Tahoma", Font.BOLD, 18));
		panelInvoice.add(lblViewInvoice, BorderLayout.SOUTH);
		
		JLabel lblInvoiceBuffer = new JLabel("");
		panelInvoice.add(lblInvoiceBuffer, BorderLayout.NORTH);
		
		JPanel panelFooter = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panelFooter.getLayout();
		flowLayout.setAlignment(FlowLayout.TRAILING);
		panelFooter.setBackground(Color.BLUE);
		frame.getContentPane().add(panelFooter, BorderLayout.SOUTH);
		
		JLabel lblClock = new JLabel();
		lblClock.setForeground(Color.WHITE);
		lblClock.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblClock.setText(DateFormat.getDateTimeInstance().format(new Date()));
		panelFooter.add(lblClock);
		
		Timer timer = new Timer(1000, new ActionListener() {
			@Override
            public void actionPerformed(ActionEvent e) {
				lblClock.setText(DateFormat.getDateTimeInstance().format(new Date()));
            }
		});
		timer.setRepeats(true);
        timer.setCoalesce(true);
        timer.setInitialDelay(0);
        timer.start();
		
	}

	private void initContentPanel(BackgroundPane panelContent, EmpPanelFlat empPanel, ProdPanelFlat prodPanel, InvoicePanelFlat invoicePanel) {
		empPanel.setVisible(false);
		prodPanel.setVisible(false);
		invoicePanel.setVisible(false);
		frame.getContentPane().remove(empPanel);
		frame.getContentPane().remove(prodPanel);
		frame.getContentPane().remove(invoicePanel);
		frame.getContentPane().add(panelContent, BorderLayout.CENTER);
		frame.getContentPane().revalidate();
	}

}
