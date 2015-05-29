package Display;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.lang.management.ManagementFactory;
import java.text.NumberFormat;

import javax.management.Attribute;
import javax.management.AttributeList;
import javax.management.InstanceNotFoundException;
import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import javax.management.ReflectionException;
import javax.swing.JPanel;

import main.Main;

public class DisplayPanel extends JPanel {

	int screenWidth = 0;
	int screenHeight = 0;
	int textGap = 14;
	public Color background = new Color(0, 0, 0);
	double timePassed;
	int FPS = 0;
	int fullFPS = 0;
	double lastTime = System.currentTimeMillis();
	int myID;

	public DisplayPanel(int screenWidth, int screenHeight, int ID) {
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;
		myID = ID;
	}

	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) (g);
		displayScene(g2);
		displaySystemInfo(g2);
	}

	void displaySystemInfo(Graphics2D g2) {
		String[] stringsToDraw = new String[9];
		timePassed += System.currentTimeMillis() - lastTime; // Record FPS
		lastTime = System.currentTimeMillis();
		FPS = FPS + 1;
		if (timePassed >= 1000) {
			fullFPS = FPS;
			FPS = 0;
			timePassed = 0.0;
		}
		g2.setColor(Color.YELLOW); // Draw FPS
		Runtime runtime = Runtime.getRuntime();
		NumberFormat format = NumberFormat.getInstance();
		long maxMemory = runtime.maxMemory();
		long allocatedMemory = runtime.totalMemory();
		long freeMemory = runtime.freeMemory();
		stringsToDraw[0] = "FPS: " + Integer.toString(fullFPS);
		stringsToDraw[1] = ("Free Memory: " + format.format(freeMemory / 1024 / 1024));
		stringsToDraw[2] = ("Allocated Memory: " + format.format(allocatedMemory / 1024 / 1024));
		stringsToDraw[3] = ("Max Memory: " + format.format(maxMemory / 1024 / 1024));
		stringsToDraw[4] = ("Total Free Memory: " + format.format((freeMemory + (maxMemory - allocatedMemory)) / 1024 / 1024));
		stringsToDraw[5] = "OS: " + System.getProperty("os.name");
		stringsToDraw[6] = "OS Version: " + System.getProperty("os.version");
		stringsToDraw[7] = "OS Architecture: " + System.getProperty("os.arch");
		stringsToDraw[8] = "0%";
		try {
			stringsToDraw[8] = "CPU Usage: " + getProcessCpuLoad();
		} catch (MalformedObjectNameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstanceNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ReflectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (int i = 0; i < stringsToDraw.length; i++) {
			g2.drawString(stringsToDraw[i], 5, textGap * (i + 1));
		}
	}

	void displayScene(Graphics2D g2) {
		g2.setColor(background); // Sets the background color
		g2.fillRect(0, 0, screenWidth, screenHeight); // Draws the background
		g2.setStroke(new BasicStroke(10));
		try {
			for (int i = 0; i < Main.displays.get(myID).currentScene.current.size(); i++) {
				try {
					g2.setColor(Main.displays.get(myID).currentScene.current.get(i).myPoly.myColor);
					g2.fillPolygon(Main.displays.get(myID).currentScene.current.get(i).myPoly);// Draws the rasterized polygon
					if (Main.displays.get(myID).currentScene.current.get(i).myPoly.img != null) {
						g2.drawImage(Main.displays.get(myID).currentScene.current.get(i).myPoly.img, (int) (Main.displays.get(myID).currentScene.current.get(i).myPoly.minX),
								(int) (Main.displays.get(myID).currentScene.current.get(i).myPoly.minY), null);// Draws its image (if needed)
					}
				} catch (NullPointerException ex) {
					// ex.printStackTrace();
				}
			}
		} catch (NullPointerException ex) {
			// ex.printStackTrace();
		}
	}
	
	public static double getProcessCpuLoad() throws MalformedObjectNameException, ReflectionException, InstanceNotFoundException {

	    MBeanServer mbs    = ManagementFactory.getPlatformMBeanServer();
	    ObjectName name    = ObjectName.getInstance("java.lang:type=OperatingSystem");
	    AttributeList list = mbs.getAttributes(name, new String[]{ "ProcessCpuLoad" });

	    if (list.isEmpty())     return Double.NaN;

	    Attribute att = (Attribute)list.get(0);
	    Double value  = (Double)att.getValue();

	    if (value == -1.0)      return Double.NaN;  // usually takes a couple of seconds before we get real values

	    return ((int)(value * 1000) / 10.0);        // returns a percentage value with 1 decimal point precision
	}
}
