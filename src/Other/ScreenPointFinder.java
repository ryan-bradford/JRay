package Other;

import main.Main;

public class ScreenPointFinder {

	int myID;
	double angle;
	double currentFOV;
	double changePerMove;
	int toReturn;

	public ScreenPointFinder(int ID) {
		myID = ID;
	}

	public int getWidthPixel(double widthAngle) {// Converts an X Angle to pixel
		currentFOV = Main.displays.get(myID).FOV; // Gets the FOV
		if (Main.displays.get(myID).getBounds().getWidth() < Main.displays.get(myID).getBounds().getHeight()) { // Changes the FOV based on the width to height ratio
			currentFOV *= (double) (Main.displays.get(myID).getContentPane().getWidth()) / (double) (Main.displays.get(myID).getContentPane().getHeight());
		}
		angle = Main.displays.get(myID).findLowestAngle(widthAngle - Main.displays.get(myID).currentScene.xAngle) + currentFOV / 2; // Makes sure it is the smallest angle within the 2PI domain
		// Corrects for camera X Rotation
		// Creates the angle to calculate on
		// Adds FOV/2 because 0 radians is the center of the screen
		// Not the far left
		changePerMove = currentFOV / Main.displays.get(myID).getContentPane().getWidth(); // Calculates how many radians are in one pixel width
		toReturn = (int) (angle / changePerMove); // Finds how many pixel angles are in the given angle
		return toReturn; // Returns the angle
	}

	public int getHeightPixel(double heightAngle) { // Very similar to width pixel
		currentFOV = Main.displays.get(myID).FOV;
		if (Main.displays.get(myID).getContentPane().getHeight() < Main.displays.get(myID).getContentPane().getWidth()) {
			currentFOV *= (double) (Main.displays.get(myID).getContentPane().getHeight()) / (double) (Main.displays.get(myID).getContentPane().getWidth());
		}
		angle = Main.displays.get(myID).findLowestAngle(heightAngle - Main.displays.get(myID).currentScene.yAngle);
		changePerMove = currentFOV / Main.displays.get(myID).getContentPane().getHeight();
		toReturn = (int) (angle / changePerMove);
		return toReturn;
	}
}
