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
import Scene.Scene;
import ShapeGenerator.ShapeFactory;
import Thread.TaskManager;
import Update.UpdateTask;
import Update.RasterizeTask;

public class Main { //The class the begins the engine
					//It also manages and stores the engines objects

	public static int screenHeight = Toolkit.getDefaultToolkit()
			.getScreenSize().height;
	public static int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
	public static Display display; //The JFrame which stuff is drawn on
	public static TaskManager manage; //The thing that allocates "Tasks" to different CPU Cores

	public static void main(String[] args) { //The main method, starts the engine
		initTaskManager();
		createDisplay();
		OtherFunctions.hideCursor(true);
		display.addScene(new Scene());
	}

	public static void createDisplay() { //Inits and Makes the JFrame visible
		display = new Display(screenWidth, screenHeight);
		display.pack();
		display.setBounds(0, 0, screenWidth, screenHeight);
		display.setVisible(true);
		display.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void initTaskManager() { //Makes some stuff not null
		manage = new TaskManager();
	}
}
