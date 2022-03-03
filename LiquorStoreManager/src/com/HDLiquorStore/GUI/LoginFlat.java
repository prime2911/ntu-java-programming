package com.HDLiquorStore.GUI;

import java.awt.EventQueue;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import com.HDLiquorStore.BLL.EmployeeBLL;
import com.HDLiquorStore.Utils.BackgroundPane;
import com.HDLiquorStore.Utils.PasswordEncryption;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

public class LoginFlat {

	JFrame frmLogin;
	private JTextField txtUsername;
	private JPasswordField txtPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFlat window = new LoginFlat();
					window.frmLogin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LoginFlat() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @wbp.parser.entryPoint
	 */
	private void initialize() {
		BackgroundPane background = new BackgroundPane();
        try {
			background.setBackground(ImageIO.read(getClass().getResource("/pub_logo.png")));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		frmLogin = new JFrame();
		
		frmLogin.setContentPane(background);
		frmLogin.getContentPane().setBackground(Color.ORANGE);
		frmLogin.setUndecorated(true);
		frmLogin.setResizable(false);
		frmLogin.setTitle("Pub Manager");
		frmLogin.setBounds(100, 100, 450, 300);
		frmLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLogin.getContentPane().setLayout(null);
		frmLogin.setLocationRelativeTo(null);
		
		txtUsername = new JTextField();
		txtUsername.setBounds(198, 82, 157, 19);
		frmLogin.getContentPane().add(txtUsername);
		txtUsername.setColumns(10);
		
		txtPassword = new JPasswordField();
		txtPassword.setBounds(198, 128, 157, 19);
		frmLogin.getContentPane().add(txtPassword);
		
		JPanel panelLogin = new JPanel();
		panelLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				EmployeeBLL empBLL = new EmployeeBLL();
				PasswordEncryption pe = new PasswordEncryption();
				boolean isValid = false;
				try {
					isValid = empBLL.loginCheck(txtUsername.getText(), pe.convertHashToString(String.valueOf(txtPassword.getPassword())));
				} catch (NoSuchAlgorithmException e1) {
					e1.printStackTrace();
				}
				
				if (isValid) {
					MainWindowFlat mainWindow = new MainWindowFlat();
					mainWindow.frame.setVisible(true);
					frmLogin.dispose();
				}
				else {
					JOptionPane.showMessageDialog(frmLogin, "Invalid credentials!", "Error!", JOptionPane.ERROR_MESSAGE);
				}
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				panelLogin.setBackground(Color.GREEN);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				panelLogin.setBackground(Color.BLUE);
			}
		});
		panelLogin.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelLogin.setBackground(Color.BLUE);
		panelLogin.setBounds(67, 206, 85, 37);
		frmLogin.getContentPane().add(panelLogin);
		panelLogin.setLayout(new BorderLayout(0, 0));
		
		JLabel lblLogin = new JLabel("Login");
		panelLogin.add(lblLogin, BorderLayout.CENTER);
		lblLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogin.setHorizontalTextPosition(SwingConstants.CENTER);
		lblLogin.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblLogin.setForeground(Color.YELLOW);
		
		JPanel panelExit = new JPanel();
		panelExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frmLogin.dispose();
//				System.exit(0);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				panelExit.setBackground(Color.MAGENTA);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				panelExit.setBackground(Color.RED);
			}
		});
		panelExit.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelExit.setBackground(Color.RED);
		panelExit.setBounds(270, 206, 85, 37);
		frmLogin.getContentPane().add(panelExit);
		panelExit.setLayout(new BorderLayout(0, 0));
		
		JLabel lblExit = new JLabel("Exit");
		panelExit.add(lblExit, BorderLayout.CENTER);
		lblExit.setHorizontalTextPosition(SwingConstants.CENTER);
		lblExit.setHorizontalAlignment(SwingConstants.CENTER);
		lblExit.setForeground(Color.YELLOW);
		lblExit.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JPanel panelUsername = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panelUsername.getLayout();
		flowLayout.setAlignment(FlowLayout.LEADING);
		panelUsername.setBackground(new Color(102, 205, 170));
		panelUsername.setBounds(67, 69, 121, 40);
		background.add(panelUsername);
		
		JLabel lblUserIcon = new JLabel("");
		panelUsername.add(lblUserIcon);
		lblUserIcon.setIcon(new ImageIcon(LoginFlat.class.getResource("/user.png")));
		
		JLabel lblUsername = new JLabel("Username");
		panelUsername.add(lblUsername);
		lblUsername.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JPanel panelPassword = new JPanel();
		panelPassword.setBackground(new Color(102, 205, 170));
		FlowLayout flowLayout_1 = (FlowLayout) panelPassword.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEADING);
		panelPassword.setBounds(67, 120, 121, 40);
		background.add(panelPassword);
		
		JLabel lblPasswordIcon = new JLabel("");
		panelPassword.add(lblPasswordIcon);
		lblPasswordIcon.setIcon(new ImageIcon(LoginFlat.class.getResource("/key.png")));
		
		JLabel lblPassword = new JLabel("Password");
		panelPassword.add(lblPassword);
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JPanel panelTitle = new JPanel();
		panelTitle.setBackground(new Color(0, 0, 128));
		panelTitle.setBounds(67, 20, 288, 39);
		background.add(panelTitle);
		panelTitle.setLayout(new BorderLayout(0, 0));
		
		JLabel lblAppTitle = new JLabel("LIQUOR STORE MANAGER");
		panelTitle.add(lblAppTitle);
		lblAppTitle.setForeground(new Color(255, 0, 0));
		lblAppTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblAppTitle.setFont(new Font("Tahoma", Font.BOLD, 18));
	}
}
