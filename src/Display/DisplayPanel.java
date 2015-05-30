package Display;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.lang.management.ManagementFactory;

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

	int textGap = 14;
	public Color background = new Color(0, 0, 0);
	double timePassed;
	int FPS = 0;
	int fullFPS = 0;
	double lastTime = System.currentTimeMillis();
	int myID;
	public boolean showDebugInfo = true;
	SettingsScreen settings;

	public DisplayPanel(int ID) {
		myID = ID;
		this.setLayout(null);
	}

	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) (g);
		super.paintComponent(g2);
		displayScene(g2);
		if(showDebugInfo) {
			displaySystemInfo(g2);
		}
	}

	void displaySystemInfo(Graphics2D g2) {
		String[] stringsToDraw = new String[10];
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
		long maxMemory = runtime.maxMemory();
		long allocatedMemory = runtime.totalMemory();
		long freeMemory = runtime.freeMemory();
		stringsToDraw[0] = "FPS: " + Integer.toString(fullFPS);
		stringsToDraw[1] = ("Free Memory: " +  (freeMemory / 1024 / 256));
		stringsToDraw[2] = ("Allocated Memory: " + (allocatedMemory / 1024 / 256));
		stringsToDraw[3] = ("Used Memory: " +  ((allocatedMemory - freeMemory) / 1024 / 256));
		stringsToDraw[4] = ("Max Memory: " +  (maxMemory / 1024 / 256));
		stringsToDraw[5] = ("Total Free Memory: " +  ((freeMemory + (maxMemory - allocatedMemory)) / 1024 / 256));
		stringsToDraw[6] = "OS: " + System.getProperty("os.name");
		stringsToDraw[7] = "OS Version: " + System.getProperty("os.version");
		stringsToDraw[8] = "OS Architecture: " + System.getProperty("os.arch");
		stringsToDraw[9] = "0%";
		try {
			stringsToDraw[9] = "CPU Usage: " + getProcessCpuLoad();
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
		g2.fillRect(0, 0, this.getWidth(), this.getHeight()); // Draws the background
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
				} catch(IndexOutOfBoundsException ex) {
					
				}
			}
		} catch (NullPointerException ex) {
			// ex.printStackTrace();
		}
	}
	
	public void pauseGame() {
		Main.displays.get(myID).paused = true;
		Main.displays.get(myID).hideCursor(false);
		this.setFocusable(false);
		Main.displays.get(myID).setFocusable(false);
		settings.setFocusable(true);
		settings.setVisible(true);
		settings.requestFocus();
		settings.repaint();
		Main.displays.get(myID).repaint();
	}
	
	public void startEngine() {
		Main.displays.get(myID).updateKeyControls();
		Main.displays.get(myID).paused = false;
		Main.displays.get(myID).hideCursor(true);
		settings.setVisible(false);
		settings.setFocusable(false);
		Main.displays.get(myID).setFocusable(true);
		Main.displays.get(myID).requestFocus();
		Main.displays.get(myID).repaint();
	}
	
	public void loadSettings() {
		settings = new SettingsScreen(myID);
		settings.setBounds(0, 0, Main.displays.get(myID).getWidth(), Main.displays.get(myID).getHeight());
		settings.setVisible(false);
		settings.setFocusable(false);
		settings.repaint();
		add(settings);
	}
	
	public double getProcessCpuLoad() throws MalformedObjectNameException, ReflectionException, InstanceNotFoundException {
		MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
	    ObjectName name    = ObjectName.getInstance("java.lang:type=OperatingSystem");
	    AttributeList list = mbs.getAttributes(name, new String[]{ "ProcessCpuLoad" });

	    if (list.isEmpty())     return Double.NaN;

	    Attribute att = (Attribute)list.get(0);
	    Double value  = (Double)att.getValue();

	    if (value == -1.0)      return Double.NaN;  // usually takes a couple of seconds before we get real values

	    return ((int)(value * 1000) / 10.0);        // returns a percentage value with 1 decimal point precision
	}
}
