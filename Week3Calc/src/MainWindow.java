import java.awt.*;
import java.awt.event.*;
//import javax.swing.*;

@SuppressWarnings("serial")
public class MainWindow extends Frame {
	Button btnBasic, btnBMI;
	Label lblUserChoice;
	
	public MainWindow() {
		setSize(300, 200);
		setLocationRelativeTo(null);
		setLayout(null);
		setResizable(false);
		setTitle("Week 3 Calculator");
		
		lblUserChoice = new Label("What would you like to use?");		
		btnBasic = new Button("Basic Calculator");
		btnBMI = new Button("BMI Calculator");
		
		add(lblUserChoice);
		add(btnBasic);
		add(btnBMI);
		
		lblUserChoice.setBounds(75, 50, 200, 30);
		btnBasic.setBounds(45, 100, 100, 50);
		btnBMI.setBounds(155, 100, 100, 50);
		
		btnBasic.addActionListener(new UserChoiceListener());
		btnBMI.addActionListener(new UserChoiceListener());
		addWindowListener(new CloseWindowListener());

		setVisible(true);
	}
	
	class UserChoiceListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Object userChoice = e.getSource();
			
			if (userChoice == btnBasic) {
				new BasicCalculator();
			}
			
			if (userChoice == btnBMI) {
				new BMICalculator();
			}
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
