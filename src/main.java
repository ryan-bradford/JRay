import javax.swing.JFrame;

import display.Display;


public class main {

	public static Display display = new Display(720, 1080);
	
	public static void main(String[] args) {
		display.pack();
		display.setBounds(0, 0, 720, 1080);
		display.setVisible(true);
		display.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
}
