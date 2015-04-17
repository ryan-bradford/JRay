package Other;

import main.Main;

public class TrigFunctions {

	public static double findLowestAngle(double angle) {
		if(angle < -Main.FOV) {
			angle += Math.PI * 2;
			return findLowestAngle(angle);
		} else if(angle > Math.PI * 2 - Main.FOV) {
			angle -= Math.PI * 2;
			return findLowestAngle(angle);
		} else {
			return angle;
		}
	}
	
	public static double getAngle(double opposite, double adjcent) {
		double hypotenuse = Math.sqrt(Math.pow(opposite, 2) + Math.pow(adjcent, 2));
		double sinValue = Math.asin(opposite / hypotenuse);
		double cosValue = Math.acos(adjcent / hypotenuse);
		double tanValue = Math.atan(opposite / adjcent);
		if(opposite < 0 && adjcent < 0) {
			return Math.PI * 2 + tanValue;
		}
		if(opposite < 0) {
			return sinValue;
		}
		if(adjcent < 0) {
			return cosValue;
		}
		return sinValue;

	}
	
}
