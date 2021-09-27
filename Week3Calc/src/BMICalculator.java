import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class BMICalculator extends JFrame {
	TextField txtHeight, txtWeight, txtBMI, txtType;
	Label lblHeight, lblWeight, lblBMI, lblType;
	Button btnCalc;
	JPanel pnlResults = new JPanel();

	public BMICalculator() {
		setSize(400, 400);
		setLocationRelativeTo(null);
		setLayout(null);
		setResizable(false);
		setTitle("BMI Calculator");
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		pnlResults.setLayout(null);

		lblHeight = new Label("Height (cm): ");
		lblWeight = new Label("Weight (kg): ");
		lblBMI = new Label("Your BMI: ");
		lblType = new Label("Category: ");

		txtHeight = new TextField();
		txtWeight = new TextField();
		txtBMI = new TextField();
		txtType = new TextField();

		txtBMI.setEditable(false);
		txtType.setEditable(false);

		btnCalc = new Button("Calculate BMI");

		add(lblHeight);
		add(txtHeight);
		add(lblWeight);
		add(txtWeight);
		add(btnCalc);
		add(pnlResults);

		pnlResults.add(lblBMI);
		pnlResults.add(txtBMI);
		pnlResults.add(lblType);
		pnlResults.add(txtType);

		lblHeight.setBounds(30, 30, 80, 20);
		txtHeight.setBounds(110, 30, 200, 20);

		lblWeight.setBounds(30, 70, 80, 20);
		txtWeight.setBounds(110, 70, 200, 20);

		btnCalc.setBounds(150, 100, 100, 50);

		pnlResults.setBounds(20, 150, 350, 180);
		pnlResults.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Results"));

		lblBMI.setBounds(30, 50, 80, 20);
		txtBMI.setBounds(110, 50, 200, 20);

		lblType.setBounds(30, 120, 80, 20);
		txtType.setBounds(110, 120, 200, 20);

		btnCalc.addActionListener(new CalcBtnListener());

		setVisible(true);
	}

	public double negativeCheck(double n) {
		if (n < 0)
			return n * -1;
		else
			return n;
	}

	class CalcBtnListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
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
			
			if ((Double.isNaN(bmi)) || (Double.isInfinite(bmi)) || bmi == 0) {
				txtBMI.setText("Invalid BMI");
				txtType.setText("Invalid BMI");
			}
			else if (bmi < 18.5)
				txtType.setText("Underweight");
			else if (bmi < 25)
				txtType.setText("Normal");
			else if (bmi < 30)
				txtType.setText("Overweight");
			else
				txtType.setText("Obese");
		}
	}
}
