package miniTwitter;

import javax.swing.*;

public class Driver {

	public static void main(String[] args) {
		JFrame frame = new JFrame ("Admin Control");
		frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(AdminControlPanel.getAdControlInstance());
		frame.pack();
		frame.setVisible(true);
	}

}
