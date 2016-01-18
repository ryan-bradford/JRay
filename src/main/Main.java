package main;

import java.awt.GraphicsEnvironment;

import java.awt.Toolkit;

import javax.swing.JFrame;

import com.ryanb3.TaskManager.TaskManager;

import Display.Display;
import Scene.Scene;

public class Main { // The class the begins the engine
					// It also manages and stores the engines objects

	// Make it so camera position is saved in the scene
	public static int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
	public static int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
	public static Display display;
	public static int sensitivityLower = 1;
	public static int sensitivityUpper = 10;

	public static void main(String[] args) { // The main method, starts the engine
		createDisplay();
		Scene myScene = new Scene();
		display.addScene(myScene);
	}

	public static void createDisplay() { // Inits and Makes the JFrame visible
		display = new Display(new TaskManager());
		display.pack();
		display.setBounds(0, 0, (int) (GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().getWidth()),
				(int) (GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().getHeight()));
		display.setVisible(true);
		display.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		display.hideCursor(true);
		display.screenOffset = (int) ((int) (screenHeight) - GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().getHeight());
		display.display.loadSettings();
		display.setLayout(null);
	}

}
