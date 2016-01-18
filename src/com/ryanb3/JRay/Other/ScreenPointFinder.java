package com.ryanb3.JRay.Other;

import com.ryanb3.JRay.Display.Display;
import com.ryanb3.JRay.Tests.Test;

public class ScreenPointFinder {

	
	double angle;
	double currentFOV;
	double changePerMove;
	int toReturn;
	Display display;
	
	public ScreenPointFinder(Display display) {
		this.display = display;
	}

	public int getWidthPixel(double widthAngle) {// Converts an X Angle to pixel
		currentFOV = display.FOV; // Gets the FOV
		if (display.getBounds().getWidth() < display.getBounds().getHeight()) { // Changes the FOV based on the width to height ratio
			currentFOV *= (double) (display.getContentPane().getWidth()) / (double) (display.getContentPane().getHeight());
		}
		angle = display.findLowestAngle(widthAngle - display.currentScene.xAngle) + currentFOV / 2; // Makes sure it is the smallest angle within the 2PI domain
		// Corrects for camera X Rotation
		// Creates the angle to calculate on
		// Adds FOV/2 because 0 radians is the center of the screen
		// Not the far left
		changePerMove = currentFOV / display.getContentPane().getWidth(); // Calculates how many radians are in one pixel width
		toReturn = (int) (angle / changePerMove); // Finds how many pixel angles are in the given angle
		return toReturn; // Returns the angle
	}

	public int getHeightPixel(double heightAngle) { // Very similar to width pixel
		currentFOV = display.FOV;
		if (display.getContentPane().getHeight() < display.getContentPane().getWidth()) {
			currentFOV *= (double) (display.getContentPane().getHeight()) / (double) (display.getContentPane().getWidth());
		}
		angle = display.findLowestAngle(heightAngle - display.currentScene.yAngle);
		changePerMove = currentFOV / display.getContentPane().getHeight();
		toReturn = (int) (angle / changePerMove);
		return toReturn;
	}
}
