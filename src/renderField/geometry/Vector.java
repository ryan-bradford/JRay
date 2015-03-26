package renderField.geometry;

public class Vector {

	double amountPerItteration;
	double xRadians;
	double yRadians;
	double xAmountPerItteration;
	double yAmountPerItteration;
	double zAmountPerItteration;
	double xPos = 0;
	double yPos = 0;
	double zPos = 0;
	
	/* 
	 * X Distance = cos(yRadians * distancePerItteration
	 * Y Distance = sin(yRadians * distancePerItteration
	 * Z Distance = cos(xRadians * distancePerItteration
	 */
	
	public Vector(double xRadians, double yRadians) {
		this.xRadians = xRadians;
		this.yRadians = yRadians;
		amountPerItteration = 1;
		xAmountPerItteration = amountPerItteration * Math.cos(yRadians);
		yAmountPerItteration = amountPerItteration * Math.sin(yRadians);
		zAmountPerItteration = amountPerItteration * Math.cos(xRadians);
	}
	
	public void moveOnce() {
		xPos += xAmountPerItteration;
		yPos += yAmountPerItteration;
		zPos += zAmountPerItteration;
	}
	
	public double getX() {
		return xPos;
	}
	
	public double getY() {
		return yPos;
	}
	
	public double getZ() {
		return zPos;
	}
	
	
	
}
