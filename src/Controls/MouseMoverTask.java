package Controls;

import java.awt.AWTException;


import java.awt.Color;
import java.awt.GraphicsEnvironment;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Robot;

import main.Main;
import Thread.Task;

public class MouseMoverTask extends Task {

	int currentX;
	int currentY;
	Robot mouseMover;
	boolean toMoveOrCalculate = false; // True is move, false is calculate
									   //The stages of this task, one centers one calculates angle moved
	int myID;
	
	public MouseMoverTask(int ID) {
		myID = ID;
		try {
			mouseMover = new Robot(); //The thing that centers the pointer
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void runTask() { // The default task object
		if (!Main.displays.get(myID).paused) {
			if (toMoveOrCalculate) {
				mouseMover.mouseMove((int)(Main.screenWidth / 2),
						(int)(Main.screenHeight / 2)); //Center mouse
				Main.displays.get(myID).setBounds((Main.screenWidth - Main.displays.get(myID).getWidth()) / 2, (Main.screenHeight - Main.displays.get(myID).getHeight() - Main.displays.get(myID).screenOffset) / 2,
						Main.displays.get(myID).getBounds().width, Main.displays.get(myID).getBounds().height); //Center and resize window
				toMoveOrCalculate = false; //Go to the other stage
			} else {
				Point point = MouseInfo.getPointerInfo().getLocation(); //Get mouse location
				currentX = (int) point.getX();
				currentY = (int) point.getY();
				double xAngle = Main.displays.get(myID).currentScene.xAngle + (Main.displays.get(myID).FOV / Main.screenWidth) / Main.displays.get(myID).sensitivity
						* (currentX - Main.screenWidth / 2); //Calculate xAngle moved
				double yAngle = Main.displays.get(myID).currentScene.yAngle + (Main.displays.get(myID).FOV / Main.screenHeight) / Main.displays.get(myID).sensitivity
						* (currentY - Main.screenHeight / 2);//Calculate yAngle moved
				Main.displays.get(myID).currentScene.xAngle = xAngle;
				Main.displays.get(myID).currentScene.yAngle = yAngle;
				toMoveOrCalculate = true;
			}
		}
		if(!Main.displays.get(myID).isActive() && !Main.displays.get(myID).paused) {//Pauses the game if you move the window to the background
			Main.displays.get(myID).paused = true;
			Main.displays.get(myID).display.background = new Color(255, 0, 0);
			Main.displays.get(myID).hideCursor(false);
			Main.displays.get(myID).keyControls.blackOrRed = false;
			Main.displays.get(myID).repaint();
		}
	}

	// Most methods will be overriden
	@Override
	public Boolean returnRunnable() {
		return true;
	}

	@Override
	public int getWait() {
		return 7;
	}

	@Override
	public int[] getData() {
		return new int[] { 0 };
	}

	@Override
	public int getCPULoad() {
		return 0; // 0 is no load, 3 is maximum load
	}
}
