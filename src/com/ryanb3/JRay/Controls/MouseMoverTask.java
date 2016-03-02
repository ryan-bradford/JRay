package com.ryanb3.JRay.Controls;

import java.awt.AWTException;


import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Robot;

import com.ryanb3.JRay.Display.Display;
import com.ryanb3.TaskManager.Task;

public class MouseMoverTask extends Task {

	int currentX;
	int currentY;
	Robot mouseMover;
	boolean toMoveOrCalculate = false; // True is move, false is calculate
									   //The stages of this task, one centers one calculates angle moved
	Point point;
	public double sensitivity = 5;
	Display display;
	double xAngle;
	double yAngle;
	
	public MouseMoverTask(Display display) {
		this.display = display;
		try {
			mouseMover = new Robot(); //The thing that centers the pointer
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void runTask() { // The default task object
		if (!display.paused) {
			if (toMoveOrCalculate) {
				mouseMover.mouseMove((int)(display.screenWidth / 2),
						(int)(display.screenHeight / 2)); //Center mouse
				display.setBounds((display.screenWidth - display.getWidth()) / 2, (display.screenHeight -  display.getHeight() -  display.screenOffset) / 2,
						 display.getBounds().width,  display.getBounds().height); //Center and resize window
				toMoveOrCalculate = false; //Go to the other stage
			} else {
				point = MouseInfo.getPointerInfo().getLocation(); //Get mouse location
				currentX = (int) point.getX();
				currentY = (int) point.getY();
				xAngle =  display.currentScene.xAngle + ( display.FOV /  display.screenWidth) / sensitivity
						* (currentX -  display.screenWidth / 2); //Calculate xAngle moved
				yAngle =  display.currentScene.yAngle + ( display.FOV /  display.screenHeight) / sensitivity
						* (currentY -  display.screenHeight / 2);//Calculate yAngle moved
				 display.currentScene.xAngle = xAngle;
				 display.currentScene.yAngle = yAngle;
				toMoveOrCalculate = true;
			}
		}
		if(! display.isActive() && ! display.paused) {//Pauses the game if you move the window to the background
			 display.paused = true;
			 display.display.pauseGame();
			 display.hideCursor(false);
			 display.repaint();
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
