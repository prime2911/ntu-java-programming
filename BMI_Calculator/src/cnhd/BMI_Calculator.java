package cnhd;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class BMI_Calculator {

	private JFrame frmBmiCalculator;
	private JTextField txtHeight;
	private JTextField txtWeight;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField txtBMI;
	private JTextField txtType;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BMI_Calculator window = new BMI_Calculator();
					window.frmBmiCalculator.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public BMI_Calculator() {
		initialize();
	}
	
	public double negativeCheck(double n) {
		if (n < 0)
			return n * -1;
		else
			return n;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmBmiCalculator = new JFrame();
		frmBmiCalculator.setTitle("BMI Calculator");
		frmBmiCalculator.setBounds(100, 100, 450, 500);
		frmBmiCalculator.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblHeight = new JLabel("Height (cm)");
		lblHeight.setBounds(48, 44, 77, 24);
		lblHeight.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblWeight = new JLabel("Weight (kg)");
		lblWeight.setBounds(48, 92, 77, 24);
		lblWeight.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		txtHeight = new JTextField();
		txtHeight.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtHeight.setBounds(135, 47, 241, 19);
		txtHeight.setColumns(10);
		
		txtWeight = new JTextField();
		txtWeight.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtWeight.setBounds(135, 95, 241, 19);
		txtWeight.setColumns(10);
		frmBmiCalculator.getContentPane().setLayout(null);
		frmBmiCalculator.getContentPane().add(lblHeight);
		frmBmiCalculator.getContentPane().add(lblWeight);
		frmBmiCalculator.getContentPane().add(txtHeight);
		frmBmiCalculator.getContentPane().add(txtWeight);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Region", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(84, 143, 252, 74);
		frmBmiCalculator.getContentPane().add(panel);
		
		JRadioButton rdbtnNonAsian = new JRadioButton("Non-Asian");
		rdbtnNonAsian.setSelected(true);
		panel.add(rdbtnNonAsian);
		buttonGroup.add(rdbtnNonAsian);
		rdbtnNonAsian.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JRadioButton rdbtnAsian = new JRadioButton("Asian");
		panel.add(rdbtnAsian);
		buttonGroup.add(rdbtnAsian);
		rdbtnAsian.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JButton btnNewButton = new JButton("Calculate BMI");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean isAsian = false;
				
				String strHeight = txtHeight.getText();
				String strWeight = txtWeight.getText();

				double h, w, bmi;

				try {
					h = negativeCheck(Double.parseDouble(strHeight));
				} catch (Exception ex) {
					h = 0;
					txtHeight.setText("Invalid input! Value defaulted to 0.");
				}
				
				try {
					w = negativeCheck(Double.parseDouble(strWeight));
				} catch (Exception ex) {
					w = 0;
					txtWeight.setText("Invalid input! Value defaulted to 0.");
				}

				bmi = w / Math.pow(h / 100, 2);
				
				txtBMI.setText(String.format("%.1f", bmi));
				
				if(rdbtnAsian.isSelected() == true)
					isAsian = true;
				
				if ((Double.isNaN(bmi)) || (Double.isInfinite(bmi)) || bmi == 0) {
					txtBMI.setText("Invalid BMI");
					txtType.setText("Invalid BMI");
				}
				else if (bmi < 18.5)
					txtType.setText("Underweight");
				else {
					if (isAsian) {
						if (bmi < 23)
							txtType.setText("Normal");
						else if (bmi < 25)
							txtType.setText("Overweight");
						else if (bmi < 30)
							txtType.setText("Pre-Obese");
						else if (bmi <= 40)
							txtType.setText("Obese Type 1");
						else if (bmi <= 50)
							txtType.setText("Obese Type 2");
					}
					else {
						if (bmi < 25)
							txtType.setText("Normal");
						else if (bmi < 30)
							txtType.setText("Overweight");
						else if (bmi <= 40)
							txtType.setText("Obese Type 1");
						else if (bmi <= 50)
							txtType.setText("Obese Type 2");
					}
				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton.setBounds(137, 227, 159, 61);
		frmBmiCalculator.getContentPane().add(btnNewButton);
		
		JLabel lblBMI = new JLabel("BMI");
		lblBMI.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblBMI.setBounds(84, 323, 34, 24);
		frmBmiCalculator.getContentPane().add(lblBMI);
		
		txtBMI = new JTextField();
		txtBMI.setEditable(false);
		txtBMI.setBounds(128, 328, 208, 19);
		frmBmiCalculator.getContentPane().add(txtBMI);
		txtBMI.setColumns(10);
		
		JLabel lblType = new JLabel("Type");
		lblType.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblType.setBounds(84, 372, 34, 24);
		frmBmiCalculator.getContentPane().add(lblType);
		
		txtType = new JTextField();
		txtType.setEditable(false);
		txtType.setColumns(10);
		txtType.setBounds(128, 377, 208, 19);
		frmBmiCalculator.getContentPane().add(txtType);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Results", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(59, 302, 317, 120);
		frmBmiCalculator.getContentPane().add(panel_1);
	}
}
