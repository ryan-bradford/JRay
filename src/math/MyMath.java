package math;

import renderField.geometry.Line;
import renderField.geometry.Triangle;

public class MyMath { //

	public static double triangleArea(Triangle toCalculate) {
		double baseLength = toCalculate.l1.length;
		double edgeLength = toCalculate.l2.length;
		double apothemLength = (Math.sin(getLinesAngle(toCalculate.l1, toCalculate.l2))) * toCalculate.l2.length;
		double heightLength = Math.sqrt(Math.pow(edgeLength, 2) + 1/3 * Math.pow(baseLength, 2));
		double baseArea = 1/2 * baseLength * apothemLength;
		double volume = baseArea * 1/3 * heightLength;
		return volume;
	}
	
	public static double getLinesAngle(Line l1, Line l2) {
		double angle1 = Math.acos(l1.getYLength()/l1.getXLength());
		double angle2 = Math.acos(l2.getYLength()/l2.getXLength());
		return Math.PI - angle1 - angle2;
	}
	
}
