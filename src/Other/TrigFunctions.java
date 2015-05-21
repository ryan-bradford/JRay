package Other;

import main.Main;

public class TrigFunctions {

	public static double findLowestAngle(double angle) { //Finds the smallest angle in the domain of -FOV - 2PI + FOV
		if(angle < -Main.display.FOV) {
			angle += Math.PI * 2;
			return findLowestAngle(angle);
		} else if(angle > Math.PI * 2 - Main.display.FOV) {
			angle -= Math.PI * 2;
			return findLowestAngle(angle);
		} else {
			return angle;
		}
	}
	
}
