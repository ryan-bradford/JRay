package main;

import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.JFrame;

import Display.Display;
import Scene.Scene;

public class Main { //The class the begins the engine
					//It also manages and stores the engines objects
	
	//Make it so camera position is saved in the scene
	public static int screenHeight = Toolkit.getDefaultToolkit()
			.getScreenSize().height;
	public static int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
	public static ArrayList<Display> displays = new ArrayList<Display>();

	public static void main(String[] args) { //The main method, starts the engine
		createDisplay();
		Scene myScene = new Scene(0);
		displays.get(0).addScene(myScene);
	}

	public static void createDisplay() { //Inits and Makes the JFrame visible
		displays.add(new Display(screenWidth, screenHeight, displays.size()));
		int thisID = displays.size() - 1;
		displays.get(thisID).pack();
		displays.get(thisID).setBounds(0, 0, screenWidth, screenHeight);
		displays.get(thisID).setVisible(true);
		displays.get(thisID).setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		displays.get(thisID).hideCursor(true);
		displays.get(thisID).screenOffset = displays.get(thisID).getHeight() - displays.get(thisID).getRootPane().getHeight();
	}

}
