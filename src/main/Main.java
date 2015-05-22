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
	public static ArrayList<Display> displays = new ArrayList<Display>();

	public static void main(String[] args) { //The main method, starts the engine
		createDisplay();
		displays.get(0).addScene(new Scene(0));
	}

	public static void createDisplay() { //Inits and Makes the JFrame visible
		displays.add(new Display(screenWidth, screenHeight, displays.size()));
		displays.get(displays.size() - 1).pack();
		displays.get(displays.size() - 1).setBounds(0, 0, screenWidth, screenHeight);
		displays.get(displays.size() - 1).setVisible(true);
		displays.get(displays.size() - 1).setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		displays.get(displays.size() - 1).hideCursor(false);
	}

}
