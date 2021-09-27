import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class BasicCalculator extends JFrame {
	TextField txtA, txtB, txtResult;
	Label lblA, lblB, lblResult;
	Button btnAdd, btnSub, btnMult, btnDiv;

	public BasicCalculator() {
		setSize(300, 375);
		setLocationRelativeTo(null);
		setLayout(null);
		setResizable(false);
		setTitle("Sum Calculator");
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		lblA = new Label("A = ");
		lblB = new Label("B = ");
		lblResult = new Label("Result: ");

		txtA = new TextField();
		txtB = new TextField();
		txtResult = new TextField();

		txtResult.setEditable(false);

		btnAdd = new Button("+");
		btnSub = new Button("-");
		btnMult = new Button("*");
		btnDiv = new Button("/");

		add(lblA);
		add(txtA);
		add(lblB);
		add(txtB);
		add(btnAdd);
		add(btnSub);
		add(btnMult);
		add(btnDiv);
		add(lblResult);
		add(txtResult);

		lblA.setBounds(40, 50, 20, 20);
		txtA.setBounds(70, 50, 180, 20);

		lblB.setBounds(40, 100, 20, 20);
		txtB.setBounds(70, 100, 180, 20);

		btnAdd.setBounds(90, 150, 50, 30);
		btnSub.setBounds(160, 150, 50, 30);
		btnMult.setBounds(90, 190, 50, 30);
		btnDiv.setBounds(160, 190, 50, 30);

		lblResult.setBounds(20, 250, 40, 20);
		txtResult.setBounds(70, 250, 200, 20);

		btnAdd.addActionListener(new CalcBtnListener());
		btnSub.addActionListener(new CalcBtnListener());
		btnMult.addActionListener(new CalcBtnListener());
		btnDiv.addActionListener(new CalcBtnListener());

		setVisible(true);
	}

	class CalcBtnListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Object calcOp = e.getSource();
			String strA = txtA.getText();
			String strB = txtB.getText();

			double a, b;

			try {
				a = Double.parseDouble(strA);
			} catch (Exception ex) {
				a = 0;
				txtA.setText("Invalid input! Value defaulted to 0.");
			}

			try {
				b = Double.parseDouble(strB);
			} catch (Exception ex) {
				b = 0;
				txtB.setText("Invalid input! Value defaulted to 0.");
			}

			if (calcOp == btnAdd) {
				txtResult.setText(String.valueOf(a + b));
			}

			if (calcOp == btnSub) {
				txtResult.setText(String.valueOf(a - b));
			}

			if (calcOp == btnMult) {
				txtResult.setText(String.valueOf(a * b));
			}

			if (calcOp == btnDiv) {
				txtResult.setText(String.valueOf(a / b));
			}
		}

	}
}
