package ShapeGenerator;

import java.awt.Color;

import Geometry.Point3D;
import Geometry.Polygon3D;

public class ShapeFactory {
	//Z Rotation = 0 is flat
	//Y Rotation = 0 is straight
	//X Rotation = 0 is straight
	public Polygon3D[] generateSquare(int cornerX, int cornerY, int cornerZ, int width, double xRotation, double yRotation, double zRotation) {
		//double fourthArcLength = Math.PI * width / 2;
		Point3D p1 = new Point3D(cornerX, cornerY, cornerZ); //Corner Point
		Point3D p2 = new Point3D(cornerX - Math.sin(yRotation) * width, cornerY + Math.cos(yRotation) * width + Math.cos(zRotation) * width, cornerZ + Math.sin(zRotation) * width); //Above Corner
		Point3D p3 = new Point3D(cornerX + Math.cos(xRotation) * width - Math.sin(yRotation) * width, cornerY + Math.cos(yRotation) * width + Math.cos(zRotation) * width, cornerZ + Math.sin(zRotation) * width + Math.sin(xRotation) * width);	//Opposite Corner	
		Point3D p4 = new Point3D(cornerX + Math.cos(xRotation) * width - Math.sin(yRotation) * width, cornerY + Math.cos(yRotation) * width, cornerZ + Math.sin(xRotation) * width);//To the left of corner
		Point3D[] points = new Point3D[]{p1, p2, p3, p4};
		Polygon3D mine = new Polygon3D(points, new Color(0, 255, 0));
		Polygon3D[] toReturn = new Polygon3D[1];
		toReturn[0] = mine;
		return toReturn;
	}
	
}
