package cao_nguyen_hai_duy;

import java.awt.*;
import java.awt.event.*;

public class MainWindow extends Frame {
	TextField txtA, txtB, txtSum;
	Label lblA, lblB, lblSum;
	Button btnAdd;

	public MainWindow() {
		setSize(300, 400);
		setLocationRelativeTo(null);
		setLayout(null);
		setResizable(false);
		setTitle("Sum Calculator");

		lblA = new Label("A = ");
		lblB = new Label("B = ");
		lblSum = new Label("A + B = ");

		txtA = new TextField();
		txtB = new TextField();
		txtSum = new TextField();

		txtSum.setEditable(false);

		btnAdd = new Button("A + B");

		add(lblA);
		add(txtA);
		add(lblB);
		add(txtB);
		add(btnAdd);
		add(lblSum);
		add(txtSum);

		lblA.setLocation(50, 80);
		lblA.setSize(20, 20);
		txtA.setLocation(80, 80);
		txtA.setSize(160, 20);

		lblB.setLocation(50, 140);
		lblB.setSize(20, 20);
		txtB.setLocation(80, 140);
		txtB.setSize(160, 20);

		btnAdd.setLocation(125, 220);
		btnAdd.setSize(50, 30);

		lblSum.setLocation(40, 300);
		lblSum.setSize(50, 20);
		txtSum.setLocation(90, 300);
		txtSum.setSize(160, 20);

		btnAdd.addActionListener(new BtnAddActionListener());

		addWindowListener(new CloseWindowListener());

		setVisible(true);
	}
	
	class BtnAddActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String strA = txtA.getText();
			String strB = txtB.getText();

			double a, b, sum;

			try {
				a = Double.parseDouble(strA);
			} catch (Exception ex) {
				a = 0;
				txtA.setText(String.valueOf(a));
			}

			try {
				b = Double.parseDouble(strB);
			} catch (Exception ex) {
				b = 0;
				txtB.setText(String.valueOf(b));
			}

			sum = a + b;
			txtSum.setText(String.valueOf(sum));
		}

	}

	class CloseWindowListener implements WindowListener {

		@Override
		public void windowOpened(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowClosing(WindowEvent e) {
			System.exit(0);
		}

		@Override
		public void windowClosed(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowIconified(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowDeiconified(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowActivated(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowDeactivated(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
}
