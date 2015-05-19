package Other;

import main.Main;

public class ScreenPointFinder {

	public int getWidthPixel(double widthAngle) {// Converts an X Angle to pixel
		double currentFOV = Main.FOV; // Gets the FOV
		if (Main.display.getBounds().getWidth() < Main.display.getBounds().getHeight()) { // Changes the FOV based on the width to height ratio
			currentFOV *= (double) (Main.display.getContentPane().getWidth()) / (double) (Main.display.getContentPane().getHeight());
		}
		double angle = TrigFunctions.findLowestAngle(widthAngle - Main.xAngle); // Makes sure it is the smallest angle within the 2PI domain
																				// Corrects for camera X Rotation
		double initAngle = angle + currentFOV / 2; // Creates the angle to calculate on
													// Adds FOV/2 because 0 radians is the center of the screen
													// Not the far left
		double changePerMove = currentFOV / Main.display.getContentPane().getWidth(); // Calculates how many radians are in one pixel width
		int toReturn = (int) (initAngle / changePerMove); // Finds how many pixel angles are in the given angle
		return toReturn; // Returns the angle
	}

	public int getHeightPixel(double heightAngle) { //Very similar to width pixel
		double currentFOV = Main.FOV;
		if (Main.display.getContentPane().getHeight() < Main.display.getContentPane().getWidth()) {
			currentFOV *= (double) (Main.display.getContentPane().getHeight()) / (double) (Main.display.getContentPane().getWidth());
		}
		double angle = TrigFunctions.findLowestAngle(heightAngle - Main.yAngle);
		double initAngle = angle;
		//System.out.println(initAngle);
		double changePerMove = currentFOV / Main.display.getContentPane().getHeight();
		int toReturn = (int) (initAngle / changePerMove);
		return toReturn;
	}
}
