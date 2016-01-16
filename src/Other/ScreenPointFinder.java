package Other;

import main.Main;

public class ScreenPointFinder {

	
	double angle;
	double currentFOV;
	double changePerMove;
	int toReturn;

	public ScreenPointFinder() {

	}

	public int getWidthPixel(double widthAngle) {// Converts an X Angle to pixel
		currentFOV = Main.display.FOV; // Gets the FOV
		if (Main.display.getBounds().getWidth() < Main.display.getBounds().getHeight()) { // Changes the FOV based on the width to height ratio
			currentFOV *= (double) (Main.display.getContentPane().getWidth()) / (double) (Main.display.getContentPane().getHeight());
		}
		angle = Main.display.findLowestAngle(widthAngle - Main.display.currentScene.xAngle) + currentFOV / 2; // Makes sure it is the smallest angle within the 2PI domain
		// Corrects for camera X Rotation
		// Creates the angle to calculate on
		// Adds FOV/2 because 0 radians is the center of the screen
		// Not the far left
		changePerMove = currentFOV / Main.display.getContentPane().getWidth(); // Calculates how many radians are in one pixel width
		toReturn = (int) (angle / changePerMove); // Finds how many pixel angles are in the given angle
		return toReturn; // Returns the angle
	}

	public int getHeightPixel(double heightAngle) { // Very similar to width pixel
		currentFOV = Main.display.FOV;
		if (Main.display.getContentPane().getHeight() < Main.display.getContentPane().getWidth()) {
			currentFOV *= (double) (Main.display.getContentPane().getHeight()) / (double) (Main.display.getContentPane().getWidth());
		}
		angle = Main.display.findLowestAngle(heightAngle - Main.display.currentScene.yAngle);
		changePerMove = currentFOV / Main.display.getContentPane().getHeight();
		toReturn = (int) (angle / changePerMove);
		return toReturn;
	}
}
