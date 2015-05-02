package Geometry;

import Other.TrigFunctions;

public class Line {

	public double length;
	double xRadians;
	double yRadians;
	public Point3D start;
	public Point3D end;

	/*
	 * X Distance = cos(yRadians) * distancePerItteration Y Distance =
	 * sin(yRadians) * distancePerItteration Z Distance = cos(xRadians) *
	 * distancePerItteration
	 */

	public Line(double startingXPos, double startingYPos, double startingZPos,
			double endingXPos, double endingYPos, double endingZPos) {
		start = new Point3D(startingXPos, startingYPos, startingZPos);
		end = new Point3D(endingXPos, endingYPos, endingZPos);
		length = Math.sqrt(Math.pow(endingXPos - startingXPos, 2) + Math.pow(endingYPos - startingYPos, 2) + Math.pow(endingZPos - startingZPos, 2));
	}
	
	public double getVertAngle() {
		return TrigFunctions.getAngle((end.zPos - start.zPos), (end.yPos - start.yPos), false, true);
	}
	
	public double getHorAngle() {
		return TrigFunctions.getAngle((end.xPos - start.xPos) , (end.yPos - start.yPos), false, false);
	}
	
	public double getXLength() {
		return end.xPos - start.xPos;
	}	
	
	public double getYLength() {
		return end.yPos - start.yPos;
	}

	public double getZLength() {
		return end.zPos - start.zPos;
	}
	
}
