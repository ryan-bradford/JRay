package Display;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JPanel;

import main.Main;
import Geometry.ColoredPolygon;
import Geometry.Polygon3D;

public class DisplayPanel extends JPanel {
	
	int screenWidth = 0;
	int screenHeight = 0;
	
	public DisplayPanel(int screenWidth, int screenHeight) {
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D)(g);
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, screenWidth, screenHeight);
		for(int i = 0; i < Main.current.size(); i++) {
			Polygon3D current = Main.current.get(i);
			ColoredPolygon toDraw = current.rasterizeToScreen();
			g.setColor(toDraw.myColor);
			g.fillPolygon(toDraw);
		}	
	}
}
