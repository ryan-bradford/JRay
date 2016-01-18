package com.ryanb3.JRay.Tests;

import java.awt.GraphicsEnvironment;

import java.awt.Toolkit;

import javax.swing.JFrame;

import com.ryanb3.JRay.Display.Display;
import com.ryanb3.JRay.Scene.Scene;
import com.ryanb3.TaskManager.TaskManager;

public class Test { // The class the begins the engine
					// It also manages and stores the engines objects

	// Make it so camera position is saved in the scene
	public int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
	public int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
	public Display display;
	public static final int sensitivityLower = 1;
	public static final int sensitivityUpper = 10;

	public static void main(String[] args) { // The main method, starts the engine
		new Test();
	}
	
	public Test() {
		createDisplay();
		Scene myScene = new Scene(display);
		display.addScene(myScene);
	}

	public void createDisplay() { // Inits and Makes the JFrame visible
		display = new Display(new TaskManager(), screenWidth, screenHeight);
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
