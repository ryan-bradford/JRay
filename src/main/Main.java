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

public class Main {

	public static int screenHeight = Toolkit.getDefaultToolkit()
			.getScreenSize().height;
	public static int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
	public static double FOV = 1.65806278939;
	public static ArrayList<Double> widthAngles;
	public static ArrayList<Double> heightAngles;
	public static Point3D cameraLocation = new Point3D(0, 0, 0);
	public static ArrayList<Polygon3D> current;
	public static double xAngle = 0;
	public static double yAngle = 0;
	public static Display display;
	public static KeyControls keyControls;
	public static UpdateTask update;
	public static TaskManager manage;
	public static ScreenPointFinder find;
	public static boolean paused = false;
	public static ColoredPolygon[] toDraw;
	public static ColoredPolygon[] toLoad;
	public static RasterizeTask[] rasterizers;
	static ShapeFactory factory;
	static MouseMoverTask mover;

	public static void main(String[] args) {
		initMainClass();
		initTaskManager();
		initPointFinder();
		startEngine();
		createDisplay();
		initUserControls();
		OtherFunctions.hideCursor(true);
		for (int i = 0; i < 4; i++) {
			addArray(factory.generateSquare(i * 100, 200, 0, 50));
		}
		// addArray(factory.generateImage(0, 100, 0, 100, "hi.jpg"));
	}

	public static void createDisplay() {
		display = new Display(screenWidth, screenHeight);
		display.pack();
		display.setBounds(0, 0, screenWidth, screenHeight);
		display.setVisible(true);
		display.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void initMainClass() {
		factory = new ShapeFactory();
		current = new ArrayList<Polygon3D>();
	}

	public static void initUserControls() {
		keyControls = new KeyControls();
		display.addKeyListener(keyControls);
		mover = new MouseMoverTask();
		manage.addTask(mover);
	}

	public static void initPointFinder() {
		find = new ScreenPointFinder();
	}

	public static void startEngine() {
		update = new UpdateTask();
		double cores = Runtime.getRuntime().availableProcessors() - 1;
		rasterizers = new RasterizeTask[(int) cores];
		manage.addTask(update);
		for (int i = 0; i < cores; i++) {
			rasterizers[i] = new RasterizeTask(1 / cores, i);
			manage.addTask(rasterizers[i]);
		}
	}

	public static void addArray(Polygon3D[] toAdd) {
		for (int i = 0; i < toAdd.length; i++) {
			current.add(toAdd[i]);
		}
		display.repaint();
	}

	public static void addArray(ArrayList<Polygon3D> toAdd) {
		for (int i = 0; i < toAdd.size(); i++) {
			current.add(toAdd.get(i));
		}
		display.repaint();
	}

	public static void initTaskManager() {
		manage = new TaskManager();
	}
}
