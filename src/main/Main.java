package main;

import java.awt.Toolkit;

import java.util.ArrayList;

import javax.swing.JFrame;
import Display.Display;
import Scene.Scene;

public class Main { //The class the begins the engine
					//It also manages and stores the engines objects

	public static int screenHeight = Toolkit.getDefaultToolkit()
			.getScreenSize().height;
	public static int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
	public static ArrayList<Display> displays = new ArrayList<Display>();

	public static void main(String[] args) { //The main method, starts the engine
		createDisplay();
		createDisplay();
		Scene myScene = new Scene(0);
		displays.get(0).addScene(new Scene(0));
		displays.get(1).addScene(myScene.switchScreens(1));
		myScene = null;
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
