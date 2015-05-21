package Controls;

import java.awt.AWTException;

import java.awt.Color;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Robot;

import main.Main;

import Other.OtherFunctions;
import Thread.Task;

public class MouseMoverTask extends Task {

	int currentX;
	int currentY;
	Robot mouseMover;
	boolean toMoveOrCalculate = false; // True is move, false is calculate
									   //The stages of this task, one centers one calculates angle moved

	public MouseMoverTask() {
		try {
			mouseMover = new Robot(); //The thing that centers the pointer
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void runTask() { // The default task object
		if (!Main.display.paused) {
			if (toMoveOrCalculate) {
				mouseMover.mouseMove((int)(Main.screenWidth / 2),
						(int)(Main.screenHeight / 2)); //Center mouse
				Main.display.setBounds((Main.screenWidth - Main.display.getWidth()) / 2, (Main.screenHeight - Main.display.getHeight()) / 2,
						Main.display.getWidth(), Main.display.getHeight()); //Center and resize window
				toMoveOrCalculate = false; //Go to the other stage
			} else {
				Point point = MouseInfo.getPointerInfo().getLocation(); //Get mouse location
				currentX = (int) point.getX();
				currentY = (int) point.getY();
				double xAngle = Main.display.xAngle + (Main.display.FOV / Main.screenWidth) / Main.display.sensitivity
						* (currentX - Main.screenWidth / 2); //Calculate xAngle moved
				double yAngle = Main.display.yAngle + (Main.display.FOV / Main.screenHeight) / Main.display.sensitivity
						* (currentY - Main.screenHeight / 2);//Calculate yAngle moved
				Main.display.xAngle = xAngle;
				Main.display.yAngle = yAngle;
				toMoveOrCalculate = true;
			}
		}
		if(!Main.display.isActive() && !Main.display.paused) {//Pauses the game if you move the window to the background
			Main.display.paused = true;
			Main.display.display.background = new Color(255, 0, 0);
			OtherFunctions.hideCursor(false);
			Main.display.keyControls.blackOrRed = false;
			Main.display.repaint();
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
