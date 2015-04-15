package main;

import java.awt.Color;

import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.JFrame;

import Controls.KeyControls;
import Display.Display;
import Geometry.Point3D;
import Geometry.Polygon3D;
import Other.ScreenPointFinder;
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

	public static void main(String[] args) {
		current = new ArrayList<Polygon3D>();
		initPointFinder();
		createDisplay();
		initUserControls();
		startEngine();
		Point3D p1 = new Point3D(0, 1000, 100);
		Point3D p2 = new Point3D(0, 1000, 0);
		Point3D p3 = new Point3D(100, 1000, 0);		
		Point3D p4 = new Point3D(100, 1000, 100);	
		Point3D p5 = new Point3D(0, 1200, 100);
		Point3D p6 = new Point3D(0, 1200, 0);
		Point3D p7 = new Point3D(100, 1200, 0);		
		Point3D p8 = new Point3D(100, 1200, 100);	
		Point3D[] points = new Point3D[]{p1, p2, p3, p4};
		Polygon3D mine = new Polygon3D(points, new Color(255, 0, 0));
		current.add(mine);
		display.repaint();
	}
	
	public static void createDisplay() {
		display = new Display(screenWidth, screenHeight);
		display.pack();
		display.setBounds(0, 0, screenWidth, screenHeight);
		display.setVisible(true);
		display.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
}
