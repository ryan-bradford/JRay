package display;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JPanel;

public class DisplayPanel extends JPanel {
	
	int screenWidth = 0;
	int screenHeight = 0;
	ArrayList<ColorPoint> points = new ArrayList<ColorPoint>();

	public DisplayPanel(int screenWidth, int screenHeight) {
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D)(g);
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, screenWidth, screenHeight);
		for(int i = 0; i < points.size(); i++) {
			g.setColor(new Color(points.get(i).redValue, points.get(i).greenValue, points.get(i).blueValue));
			g.drawLine(points.get(i).xPos, points.get(i).yPos, points.get(i).xPos, points.get(i).yPos);
		}	
	}
	
	public void addColorPoint(ColorPoint toAdd) {
		this.points.add(toAdd);
		repaint();
	}
}
