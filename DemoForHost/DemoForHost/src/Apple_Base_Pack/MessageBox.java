package Apple_Base_Pack;

import java.awt.Component;
import javax.swing.JOptionPane;
import javax.swing.*;
import java.awt.event.*;

public class MessageBox {
	JFrame frame;
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		MessageBox db = new MessageBox();
	}

	public MessageBox() {
		frame = new JFrame("Show Message Box");
		JButton button = new JButton("Click");
		button.setAlignmentX((float) 100.00);

		button.addActionListener(new MyAction());
		frame.add(button);
		frame.setSize(300, 300);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public class MyAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog(frame, "Message Box");
		}
	}
}
