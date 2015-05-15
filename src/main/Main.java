package main;

import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.JFrame;

import Controls.KeyControls;
import Controls.MouseMoverTask;
import Display.Display;
import Geometry.ColoredPolygon;
import Geometry.Point3D;
import Geometry.Polygon3D;
import Other.OtherFunctions;
import Other.ScreenPointFinder;
import ShapeGenerator.ShapeFactory;
import Thread.TaskManager;
import Update.UpdateTask;
import Update.RasterizeTask;

public class Main { //The class the begins the engine
					//It also manages and stores the engines objects

	public static int screenHeight = Toolkit.getDefaultToolkit()
			.getScreenSize().height;
	public static int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
	public static double FOV = 1.13446401379; //A nice number which looks good
	public static Point3D cameraLocation = new Point3D(0, 0, 0);
	public static ArrayList<Polygon3D> current; //The polygons that should be rasterized
	public static double xAngle = 0; //The camera rotation to the x
	public static double yAngle = 0; //The camera rotation to the Y
	public static Display display; //The JFrame which stuff is drawn on
	public static KeyControls keyControls; //The class that control the camera movement
	public static UpdateTask update; //The class that orders how stuff should be drawn and what should be drawn 
	public static TaskManager manage; //The thing that allocates "Tasks" to different CPU Cores
	public static ScreenPointFinder find; //The algorithm that changes angle to pixel
	public static boolean paused = false; //Whether the engine is paused or not
	public static ColoredPolygon[] toDraw; //What should be drawn
	public static ColoredPolygon[] toLoad; //The buffer between toDraw and current
										   //Makes sure the display wont draw some half filled list
	public static RasterizeTask[] rasterizers; //The tasks which do the rasterization
	public static double sensitivity = 2; //The sensitivity of the mouse, range of 1 to 10 
	static ShapeFactory factory; //The object which can produce different polygons
	static MouseMoverTask mover; //The task which controls mouse and window centering
								 //Also translates movement to angle movement

	public static void main(String[] args) { //The main method, starts the engine
		initMainClass();
		initTaskManager();
		initPointFinder();
		startEngine();
		createDisplay();
		initUserControls();
		OtherFunctions.hideCursor(true);
		for (int i = 0; i < 4; i++) {
			//addArray(factory.generateSquare(i * 100, 200, 0, 50));
			addArray(factory.generateImage(i * 100, 200, 0, 50, "download.jpg"));
		}
		//addArray(factory.generateImage(0, 100, 0, 100, "download.jpg"));
	}

	public static void createDisplay() { //Inits and Makes the JFrame visible
		display = new Display(screenWidth, screenHeight);
		display.pack();
		display.setBounds(0, 0, screenWidth, screenHeight);
		display.setVisible(true);
		display.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void initMainClass() { //Makes stuff not null
		factory = new ShapeFactory();
		current = new ArrayList<Polygon3D>();
	}

	public static void initUserControls() { //Starts the user controls
		keyControls = new KeyControls();
		display.addKeyListener(keyControls);
		mover = new MouseMoverTask();
		manage.addTask(mover);
	}

	public static void initPointFinder() { //Makes some more stuff not null
		find = new ScreenPointFinder();
	}

	public static void startEngine() { //Starts the rasterizers and updater
		update = new UpdateTask();
		double cores = Runtime.getRuntime().availableProcessors() - 1;
		rasterizers = new RasterizeTask[(int) cores];
		manage.addTask(update);
		for (int i = 0; i < cores; i++) {
			rasterizers[i] = new RasterizeTask(1 / cores, i);
			manage.addTask(rasterizers[i]);
		}
	}

	public static void addArray(Polygon3D[] toAdd) { //A method to add an array of polygons to current
		for (int i = 0; i < toAdd.length; i++) {
			current.add(toAdd[i]);
		}
		display.repaint();
	}

	public static void addArray(ArrayList<Polygon3D> toAdd) {//A method to add an arraylist of polygons to current
		for (int i = 0; i < toAdd.size(); i++) {
			current.add(toAdd.get(i));
		}
		display.repaint();
	}

	public static void initTaskManager() { //Makes some stuff not null
		manage = new TaskManager();
	}
}
