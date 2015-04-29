package Display;

import java.awt.BasicStroke;
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
	public Color background = new Color(0, 0, 0);
	double timePassed;
	int FPS = 0;
	double lastTime = System.currentTimeMillis();

	public DisplayPanel(int screenWidth, int screenHeight) {
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;
	}

	@Override
	protected void paintComponent(Graphics g) {
		ArrayList<ColoredPolygon> drawnPolys = new ArrayList<ColoredPolygon>();
		Graphics2D g2 = (Graphics2D) (g);
		g2.setColor(background);
		g2.fillRect(0, 0, screenWidth, screenHeight);
		g2.setStroke(new BasicStroke(2));
		for (int i = 0; i < Main.toDraw.length; i++) {
			try {
				g2.setColor(Main.toDraw[i].myColor);
				g2.fillPolygon(Main.toDraw[i]);
			} catch (NullPointerException ex) {

			}
		}
		timePassed += System.currentTimeMillis() - lastTime;
		lastTime =  System.currentTimeMillis();
		FPS = FPS + 1;
		if(timePassed >= 1000) {
			 System.out.println(FPS);
			 FPS = 0;
			 timePassed = 0.0;
		}
		// for(int i = 0; i < drawnPolys.size(); i++) {
		// g2.setColor(Color.GRAY);
		// for(int x = 1; x < drawnPolys.get(i).npoints + 1; x++) {
		// if(x != drawnPolys.get(i).npoints) {
		// g2.drawLine(drawnPolys.get(i).xpoints[x - 1],
		// drawnPolys.get(i).ypoints[x - 1], drawnPolys.get(i).xpoints[x],
		// drawnPolys.get(i).ypoints[x]);
		// } else {
		// g2.drawLine(drawnPolys.get(i).xpoints[x - 1],
		// drawnPolys.get(i).ypoints[x - 1], drawnPolys.get(i).xpoints[0],
		// drawnPolys.get(i).ypoints[0]);
		// }
		// }
		// }
	}
}
