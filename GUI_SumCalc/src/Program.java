import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Program {

	public static void main(String[] args) {
		Frame mainFrame = new Frame("Sum Calculator");
		mainFrame.setSize(300, 400);
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setLayout(null);
		mainFrame.setResizable(false);

		Label lblA = new Label("A = ");
		Label lblB = new Label("B = ");
		Label lblSum = new Label("A + B = ");

		TextField txtA = new TextField();
		TextField txtB = new TextField();
		TextField txtSum = new TextField();

		txtSum.setEditable(false);

		Button btnAdd = new Button("A + B");

		mainFrame.add(lblA);
		mainFrame.add(txtA);
		mainFrame.add(lblB);
		mainFrame.add(txtB);
		mainFrame.add(btnAdd);
		mainFrame.add(lblSum);
		mainFrame.add(txtSum);

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

		ActionListener buttonPressed = new ActionListener() {

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

		};

		btnAdd.addActionListener(buttonPressed);

		mainFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		mainFrame.setVisible(true);
	}

}
