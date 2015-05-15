package Other;

import main.Main;

public class TrigFunctions {

	public static double findLowestAngle(double angle) { //Finds the smallest angle in the domain of -FOV - 2PI + FOV
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
	
	public static double getAngle(double opposite, double adjcent, boolean ignoreOpposite, boolean ignoreAdjcent) { 
		double tanValue = Math.atan(Math.abs(opposite) / Math.abs(adjcent));//Expands upon the java trig functions
		if(opposite < 0 && adjcent < 0) {									//Allows for more accurate
			return Math.PI + tanValue;
		}
		if(opposite < 0) {
			if(ignoreOpposite) {
				return tanValue;
			}
			return Math.PI * 2 - tanValue;
		}
		if(adjcent < 0) {
			if(ignoreAdjcent) {
				return tanValue;
			}
			return Math.PI - tanValue;
		}
		return tanValue;

	}
	
}
