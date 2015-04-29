package ShapeGenerator;

import java.awt.Color;

import Geometry.Point3D;
import Geometry.Polygon3D;

public class ShapeFactory {
	//Z Rotation = 0 is flat
	//Y Rotation = 0 is straight
	//X Rotation = 0 is straight
	public Polygon3D[] generateSquare(int cornerX, int cornerY, int cornerZ, int width) { //Stetches X - Z
		Point3D p1 = new Point3D(cornerX, cornerY, cornerZ); //Corner Point
		Point3D p2 = new Point3D(cornerX , cornerY, cornerZ + width); //Above Corner
		Point3D p3 = new Point3D(cornerX + width, cornerY, cornerZ + width);	//Opposite Corner	
		Point3D p4 = new Point3D(cornerX + width, cornerY, cornerZ);//To the left of corner
		Point3D[] points = new Point3D[]{p1, p2, p3, p4};
		Polygon3D mine = new Polygon3D(points, new Color(0, 255, 0));
		Polygon3D[] toReturn = new Polygon3D[1];
		toReturn[0] = mine;
		return toReturn;
	}
	
	public Polygon3D[] generateCube(int cornerX, int cornerY, int cornerZ, int width) { //Stetches X - Z
		Point3D p1 = new Point3D(cornerX, cornerY, cornerZ); //Corner Point
		Point3D p2 = new Point3D(cornerX , cornerY, cornerZ + width); //Above Corner
		Point3D p3 = new Point3D(cornerX + width, cornerY, cornerZ + width);	//Opposite Corner	
		Point3D p4 = new Point3D(cornerX + width, cornerY, cornerZ);//To the left of corner
		Point3D p5 = new Point3D(cornerX, cornerY + width, cornerZ); //Behind Corner Point
		Point3D p6 = new Point3D(cornerX , cornerY + width, cornerZ + width); //Behind Above Corner
		Point3D p7 = new Point3D(cornerX + width, cornerY + width, cornerZ + width);//Behind Opposite Corner	
		Point3D p8 = new Point3D(cornerX + width, cornerY + width, cornerZ);//Behind To the left of corner	
		Point3D[] f1Points = new Point3D[]{p1, p2, p3, p4};//Main Face
		Point3D[] f2Points = new Point3D[]{p1, p2, p6, p5};//Left
		Point3D[] f3Points = new Point3D[]{p1, p5, p8, p4};//Bottom
		Point3D[] f4Points = new Point3D[]{p2, p3, p7, p6};//Top
		Point3D[] f5Points = new Point3D[]{p4, p3, p7, p8};//Right
		Point3D[] f6Points = new Point3D[]{p5, p6, p7, p8};//Back
		Polygon3D f1 = new Polygon3D(f1Points, new Color(0, 255, 0));
		Polygon3D f2 = new Polygon3D(f2Points, new Color(0, 255, 0));
		Polygon3D f3 = new Polygon3D(f3Points, new Color(0, 255, 0));
		Polygon3D f4 = new Polygon3D(f4Points, new Color(0, 255, 0));
		Polygon3D f5 = new Polygon3D(f5Points, new Color(0, 255, 0));
		Polygon3D f6 = new Polygon3D(f6Points, new Color(0, 255, 0));
		Polygon3D[] toReturn = new Polygon3D[6];
		toReturn[0] = f1;
		toReturn[1] = f2;
		toReturn[2] = f3;
		toReturn[3] = f4;
		toReturn[4] = f5;
		toReturn[5] = f6;
		return toReturn;
	}
	
}
