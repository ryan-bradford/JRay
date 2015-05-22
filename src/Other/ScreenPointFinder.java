package Other;

import main.Main;

public class ScreenPointFinder {
	
	int myID;

	public ScreenPointFinder(int ID) {
		myID = ID;
	}
	
	public int getWidthPixel(double widthAngle) {// Converts an X Angle to pixel
		double currentFOV = Main.displays.get(myID).FOV; // Gets the FOV
		if (Main.displays.get(myID).getBounds().getWidth() < Main.displays.get(myID).getBounds().getHeight()) { // Changes the FOV based on the width to height ratio
			currentFOV *= (double) (Main.displays.get(myID).getContentPane().getWidth()) / (double) (Main.displays.get(myID).getContentPane().getHeight());
		}
		double angle = Main.displays.get(myID).findLowestAngle(widthAngle - Main.displays.get(myID).xAngle); // Makes sure it is the smallest angle within the 2PI domain
																				// Corrects for camera X Rotation
		double initAngle = angle + currentFOV / 2; // Creates the angle to calculate on
													// Adds FOV/2 because 0 radians is the center of the screen
													// Not the far left
		double changePerMove = currentFOV / Main.displays.get(myID).getContentPane().getWidth(); // Calculates how many radians are in one pixel width
		int toReturn = (int) (initAngle / changePerMove); // Finds how many pixel angles are in the given angle
		return toReturn; // Returns the angle
	}

	public int getHeightPixel(double heightAngle) { //Very similar to width pixel
		double currentFOV = Main.displays.get(myID).FOV;
		if (Main.displays.get(myID).getContentPane().getHeight() < Main.displays.get(myID).getContentPane().getWidth()) {
			currentFOV *= (double) (Main.displays.get(myID).getContentPane().getHeight()) / (double) (Main.displays.get(myID).getContentPane().getWidth());
		}
		double angle = Main.displays.get(myID).findLowestAngle(heightAngle - Main.displays.get(myID).yAngle);
		double initAngle = angle;
		//System.out.println(initAngle);
		double changePerMove = currentFOV / Main.displays.get(myID).getContentPane().getHeight();
		int toReturn = (int) (initAngle / changePerMove);
		return toReturn;
	}
}
