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
		double tanValue = Math.atan(Math.abs(opposite) / Math.abs(adjcent));
		if(opposite < 0 && adjcent < 0) {
			return Math.PI + tanValue;
		}
		if(opposite < 0) {
			return Math.PI * 2 - tanValue;
		}
		if(adjcent < 0) {
			return Math.PI - tanValue;
		}
		return tanValue;

	}
	
}
