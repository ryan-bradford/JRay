package JRay.Geometry;

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
	//The line object, for creating 3D lines
	public Line(double startingXPos, double startingYPos, double startingZPos,
			double endingXPos, double endingYPos, double endingZPos) {
		start = new Point3D(startingXPos, startingYPos, startingZPos);
		end = new Point3D(endingXPos, endingYPos, endingZPos);
		length = Math.sqrt(Math.pow(endingXPos - startingXPos, 2) + Math.pow(endingYPos - startingYPos, 2) + Math.pow(endingZPos - startingZPos, 2));
	}
	
	public double getVertAngle() { //Gets the vertical angle of the line (up down angle)
		double opposite = (end.zPos - start.zPos);
		double adjcent = Math.sqrt(Math.pow(Math.abs(end.yPos - start.yPos), 2) + Math.pow(Math.abs(end.xPos - start.xPos), 2));	
		double tanValue = Math.atan(Math.abs(opposite) / (adjcent));//Expands upon the java trig functions
		if(opposite < 0) {
			return Math.PI * 2 - tanValue;
		}
		return tanValue;
	}
	
	public double getHorAngle() { //Gets the horizontal angle of the line (left right angle)
		double opposite = (end.xPos - start.xPos);
		double adjcent = (end.yPos - start.yPos);	
		double tanValue = Math.atan(Math.abs(opposite) / Math.abs(adjcent));//Expands upon the java trig functions
		if(opposite < 0 && adjcent < 0) {									//Allows for more accurate
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
