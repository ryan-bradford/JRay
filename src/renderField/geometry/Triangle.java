package renderField.geometry;

import math.MyMath;

//Triangle
//Check collision by splitting the shape into the parts around the point

public class Triangle {

	public Line l1; //Base
	public Line l2; //Touching Base
	public Line l3; //Touching Base
	public Line l4; //Touching Base
	public Line l5; //Touching Base
	public Line l6; //Not Touching Base
	
	public boolean isColided(double x, double y, double z) {
		double myArea = MyMath.triangleArea(this);
		
		return false;
	}

}
