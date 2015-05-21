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
import Other.OtherFunctions;

public class DisplayPanel extends JPanel {

	int screenWidth = 0;
	int screenHeight = 0;
	public Color background = new Color(0, 0, 0);
	double timePassed;
	int FPS = 0;
	int fullFPS = 0;
	double lastTime = System.currentTimeMillis();

	public DisplayPanel(int screenWidth, int screenHeight) {
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;
	}

	@Override
	protected void paintComponent(Graphics g) {
		ArrayList<ColoredPolygon> drawnPolys = new ArrayList<ColoredPolygon>();
		Graphics2D g2 = (Graphics2D) (g);
		g2.setColor(background); // Sets the background color
		g2.fillRect(0, 0, screenWidth, screenHeight); // Draws the background
		g2.setStroke(new BasicStroke(10));
		try {
			for (int i = 0; i < Main.display.currentScene.current.size(); i++) {
				try {
					g2.setColor(Main.display.currentScene.current.get(i).myPoly.myColor);
					g2.fillPolygon(Main.display.currentScene.current.get(i).myPoly);// Draws the rasterized polygon
					if (Main.display.currentScene.current.get(i).myPoly.img != null) {
						g2.drawImage(Main.display.currentScene.current.get(i).myPoly.img, (int) (Main.display.currentScene.current.get(i).myPoly.minX),
								(int) (Main.display.currentScene.current.get(i).myPoly.minY), null);// Draws its image (if needed)
					}
				} catch (NullPointerException ex) {
					// ex.printStackTrace();
				}
			}
		} catch (NullPointerException ex) {
			// ex.printStackTrace();
		}
		timePassed += System.currentTimeMillis() - lastTime; // Record FPS
		lastTime = System.currentTimeMillis();
		FPS = FPS + 1;
		if (timePassed >= 1000) {
			fullFPS = FPS;
			FPS = 0;
			timePassed = 0.0;
		}
		g2.setColor(Color.YELLOW); // Draw FPS
		g2.drawString(Integer.toString(fullFPS), 10, 13);
	}
}
