package renderField.geometry;

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
			double xRadians, double yRadians, double length) {
		this.xRadians = xRadians;
		this.yRadians = yRadians;
		this.length = length;
		start = new Point3D(startingXPos, startingYPos, startingZPos);
		end = new Point3D((start.xPos + length * Math.cos(yRadians)),
				(start.yPos + length * Math.sin(yRadians)),
				(start.zPos + length * Math.cos(xRadians)));
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
