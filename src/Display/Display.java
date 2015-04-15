package Display;

import javax.swing.JFrame;

import Geometry.ColoredPolygon;

public class Display extends JFrame {
	
	public  DisplayPanel display;
	
	public Display(int screenWidth, int screenHeight) {
		display = new DisplayPanel(screenWidth, screenHeight);
		display.setBounds(0, 0, screenWidth, screenHeight);
		add(display);
	}
}
