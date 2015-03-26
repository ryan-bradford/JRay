package display;

import javax.swing.JFrame;

public class Display extends JFrame {
	
	DisplayPanel display;	
	
	public Display(int screenWidth, int screenHeight) {
		display = new DisplayPanel(screenWidth, screenHeight);
		display.setBounds(0, 0, screenWidth, screenHeight);
		add(display);
	}
	
	public void addColorPoint(ColorPoint toAdd) {
		display.addColorPoint(toAdd);
		repaint();
	}
	
	
}
