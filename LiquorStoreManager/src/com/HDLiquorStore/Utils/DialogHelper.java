package com.HDLiquorStore.Utils;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class DialogHelper {
	
	// Displays dialog
	public void showDialog(String msg, String title, int msgType) {
		JFrame frame = new JFrame("JDialog");
		JOptionPane.showMessageDialog(frame, msg, title, msgType);
	}
}
