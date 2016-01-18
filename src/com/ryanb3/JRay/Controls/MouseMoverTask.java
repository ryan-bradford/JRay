package com.ryanb3.JRay.Controls;

import java.awt.AWTException;

import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Robot;

import com.ryanb3.JRay.Tests.Test;
import com.ryanb3.TaskManager.Task;

public class MouseMoverTask extends Task {

	int currentX;
	int currentY;
	Robot mouseMover;
	boolean toMoveOrCalculate = false; // True is move, false is calculate
									   //The stages of this task, one centers one calculates angle moved
	Point point;
	public double sensitivity = 5;
	
	double xAngle;
	double yAngle;
	
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
		if (!Test.display.paused) {
			if (toMoveOrCalculate) {
				mouseMover.mouseMove((int)(Test.screenWidth / 2),
						(int)(Test.screenHeight / 2)); //Center mouse
				Test.display.setBounds((Test.screenWidth - Test.display.getWidth()) / 2, (Test.screenHeight - Test.display.getHeight() - Test.display.screenOffset) / 2,
						Test.display.getBounds().width, Test.display.getBounds().height); //Center and resize window
				toMoveOrCalculate = false; //Go to the other stage
			} else {
				point = MouseInfo.getPointerInfo().getLocation(); //Get mouse location
				currentX = (int) point.getX();
				currentY = (int) point.getY();
				xAngle = Test.display.currentScene.xAngle + (Test.display.FOV / Test.screenWidth) / sensitivity
						* (currentX - Test.screenWidth / 2); //Calculate xAngle moved
				yAngle = Test.display.currentScene.yAngle + (Test.display.FOV / Test.screenHeight) / sensitivity
						* (currentY - Test.screenHeight / 2);//Calculate yAngle moved
				Test.display.currentScene.xAngle = xAngle;
				Test.display.currentScene.yAngle = yAngle;
				toMoveOrCalculate = true;
			}
		}
		if(!Test.display.isActive() && !Test.display.paused) {//Pauses the game if you move the window to the background
			Test.display.paused = true;
			Test.display.display.pauseGame();
			Test.display.hideCursor(false);
			Test.display.repaint();
		}
	}

	// Most methods will be overriden
	@Override
	public Boolean returnRunnable() {
		return true;
	}

	@Override
	public int getWait() {
		return 5;
	}

	@Override
	public int getCPULoad() {
		return 0; // 0 is no load, 3 is maximum load
	}
}
