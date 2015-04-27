package main;

import java.awt.Color;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.JFrame;

import Controls.KeyControls;
import Display.Display;
import Geometry.Point3D;
import Geometry.Polygon3D;
import Other.OtherFunctions;
import Other.ScreenPointFinder;
import ShapeGenerator.ShapeFactory;
import Update.UpdateThread;

public class Main {

	public static int screenHeight = Toolkit.getDefaultToolkit()
			.getScreenSize().height;
	public static int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
	public static double FOV = Math.PI * 2 / 3;
	public static ArrayList<Double> widthAngles;
	public static ArrayList<Double> heightAngles;
	public static Point3D cameraLocation = new Point3D(0, 0, 0);
	public static ArrayList<Polygon3D> current;
	public static double xAngle = 0;
	public static double yAngle = 0;
	public static Display display;
	public static KeyControls keyControls;
	public static UpdateThread update;
	public static ScreenPointFinder find;
	static ShapeFactory factory;

	public static void main(String[] args) {
		initMainClass();
		initPointFinder();
		createDisplay();
		initUserControls();
		startEngine();
		OtherFunctions.hideCursor(true);
		addArray(factory.generateSquare(0, 10000, 0, 1000, 0, 0, Math.PI / 3));
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
	}
	
	public static void initPointFinder() {
		find = new ScreenPointFinder();
	}
	
	public static void startEngine() {
		update = new UpdateThread();
		update.start();
	}
	
	public static void addArray(Polygon3D[] toAdd) {
		for(int i = 0; i < toAdd.length; i++) {
			current.add(toAdd[i]);
		}
		display.repaint();
	}
	
	public static void addArray(ArrayList<Polygon3D> toAdd) {
		for(int i = 0; i < toAdd.size(); i++) {
			current.add(toAdd.get(i));
		}
		display.repaint();
	}
}
