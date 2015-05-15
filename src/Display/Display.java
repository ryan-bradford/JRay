package Display;

import javax.swing.JFrame;

import Geometry.ColoredPolygon;

public class Display extends JFrame { //Just a holder for a JPanel
	
	public  DisplayPanel display;
	
	public Display(int screenWidth, int screenHeight) { //Inits the display panel
		display = new DisplayPanel(screenWidth, screenHeight);
		display.setBounds(0, 0, screenWidth, screenHeight);
		add(display);
	}
}
