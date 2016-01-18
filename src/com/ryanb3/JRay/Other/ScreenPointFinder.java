package com.ryanb3.JRay.Other;

import com.ryanb3.JRay.Tests.Test;

public class ScreenPointFinder {

	
	double angle;
	double currentFOV;
	double changePerMove;
	int toReturn;

	public ScreenPointFinder() {

	}

	public int getWidthPixel(double widthAngle) {// Converts an X Angle to pixel
		currentFOV = Test.display.FOV; // Gets the FOV
		if (Test.display.getBounds().getWidth() < Test.display.getBounds().getHeight()) { // Changes the FOV based on the width to height ratio
			currentFOV *= (double) (Test.display.getContentPane().getWidth()) / (double) (Test.display.getContentPane().getHeight());
		}
		angle = Test.display.findLowestAngle(widthAngle - Test.display.currentScene.xAngle) + currentFOV / 2; // Makes sure it is the smallest angle within the 2PI domain
		// Corrects for camera X Rotation
		// Creates the angle to calculate on
		// Adds FOV/2 because 0 radians is the center of the screen
		// Not the far left
		changePerMove = currentFOV / Test.display.getContentPane().getWidth(); // Calculates how many radians are in one pixel width
		toReturn = (int) (angle / changePerMove); // Finds how many pixel angles are in the given angle
		return toReturn; // Returns the angle
	}

	public int getHeightPixel(double heightAngle) { // Very similar to width pixel
		currentFOV = Test.display.FOV;
		if (Test.display.getContentPane().getHeight() < Test.display.getContentPane().getWidth()) {
			currentFOV *= (double) (Test.display.getContentPane().getHeight()) / (double) (Test.display.getContentPane().getWidth());
		}
		angle = Test.display.findLowestAngle(heightAngle - Test.display.currentScene.yAngle);
		changePerMove = currentFOV / Test.display.getContentPane().getHeight();
		toReturn = (int) (angle / changePerMove);
		return toReturn;
	}
}
