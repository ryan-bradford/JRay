package Display;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

import main.Main;
import Controls.KeyControls;
import Controls.MouseMoverTask;
import Other.ScreenPointFinder;
import Scene.Scene;
import Thread.TaskManager;
import Update.RasterizeTask;
import Update.UpdateTask;

public class Display extends JFrame { // Just a holder for a JPanel

	public DisplayPanel display;
	public RasterizeTask[] rasterizers; // The tasks which do the rasterization
	public UpdateTask update; // The class that orders how stuff should be drawn and what should be drawn
	public KeyControls keyControls; // The class that control the camera movement
	public boolean paused = false; // Whether the engine is paused or not
	public ScreenPointFinder find; // The algorithm that changes angle to pixel
	MouseMoverTask mover; // The task which controls mouse and window centering
							// Also translates movement to angle movement
	public Scene currentScene;
	public double FOV = 1.13446401379; // A nice number which looks good
	public double sensitivity = 2; // The sensitivity of the mouse, range of 1 to 10
	public int myID;
	public TaskManager myManage; //The thing that allocates "Tasks" to different CPU Cores
	public int screenOffset = 0;

	public Display(int screenWidth, int screenHeight, int ID) { // Inits the display panel
		initTaskManager();
		myID = ID;
		display = new DisplayPanel(screenWidth, screenHeight, myID);
		display.setBounds(0, 0, screenWidth, screenHeight);
		add(display);
		initPointFinder();
		initUserControls();
		startEngine();
	}

	public void addScene(Scene toLoad) {
		currentScene = toLoad;
	}

	public void hideCursor(boolean hideOrShow) { // True is hide, false
		// is show
		if (hideOrShow) { // Hides the cursor
			BufferedImage cursorImg = new BufferedImage(16, 16,// Makes it a blank cursor
					BufferedImage.TYPE_INT_ARGB);
			Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(cursorImg, new Point(0, 0), "blank cursor");
			super.getContentPane().setCursor(blankCursor);
		} else {
			super.getContentPane().setCursor(Cursor.getDefaultCursor()); // Shows the cursor, makes it the default
		}
	}

	public void initPointFinder() { // Makes some more stuff not null
		find = new ScreenPointFinder(myID);
	}
	
	public void initTaskManager() { //Makes some stuff not null
		myManage = new TaskManager();
	}

	public void startEngine() { // Starts the rasterizers and updater
		update = new UpdateTask(myID);
		double cores = Runtime.getRuntime().availableProcessors() - 1;
		rasterizers = new RasterizeTask[(int) cores];
		myManage.addTask(update);
		for (int i = 0; i < cores; i++) {
			rasterizers[i] = new RasterizeTask(1 / cores, i, myID);
			myManage.addTask(rasterizers[i]);
		}
	}

	public void initUserControls() { // Starts the user controls
		keyControls = new KeyControls(myID);
		this.addKeyListener(keyControls);
		mover = new MouseMoverTask(myID);
		myManage.addTask(mover);
	}

	public void setScene(Scene toSet) {
		currentScene = toSet;
	}
	
	public void removeScene() {
		currentScene = null;
	}
	
	public double findLowestAngle(double angle) { //Finds the smallest angle in the domain of -FOV - 2PI + FOV
		if(angle < -FOV) {
			angle += Math.PI * 2;
			return findLowestAngle(angle);
		} else if(angle > Math.PI * 2 - FOV) {
			angle -= Math.PI * 2;
			return findLowestAngle(angle);
		} else {
			return angle;
		}
	}
}
