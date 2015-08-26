package JRay.Geometry;

import java.awt.Color;

import main.Main;

public class Polygon3D {

	public Point3D[] myPoints;
	Color myColor;
	public double distanceFromCamera;
	public ColoredPolygon myPoly;

	public Polygon3D(Point3D[] toAdd, Color toColor) { // The 3D Polygon Object
		myColor = toColor;
		myPoints = toAdd;
	}

	public Line[] getLinesToPoint(Point3D toPathTo) { // Makes an array of lines
		Line[] lines = new Line[myPoints.length]; // Which stretch from the camera to each point on the polygon
		for (int i = 0; i < myPoints.length; i++) {
			lines[i] = new Line(toPathTo.xPos, toPathTo.yPos, toPathTo.zPos, myPoints[i].xPos, myPoints[i].yPos, myPoints[i].zPos);
		}
		return lines;
	}

	public void updateDistance() { // Gets the farthest points distance from the camera
		int maxID = 0; // Used to order how the ColoredPolygons are drawn
		Line[] current = getLinesToPoint(Main.display.currentScene.cameraLocation);
		for (int i = 1; i < current.length; i++) {
			if (current[i].length < current[maxID].length) {
				maxID = i;
			}
		}
		distanceFromCamera = current[maxID].length;
	}

	public void rasterizeToScreen() { // Rasterizes the polygon
		int[] xs = new int[myPoints.length];
		int[] ys = new int[myPoints.length];
		Line[] myLines = getLinesToPoint(Main.display.currentScene.cameraLocation); // Gets the lines to the camera
		for (int i = 0; i < myLines.length; i++) {
			xs[i] = Main.display.find.getWidthPixel(myLines[i].getHorAngle()); // Uses angle to get the x location of the polygons selected point
			ys[i] = Main.display.find.getHeightPixel(myLines[i].getVertAngle());// Uses angle to get the y location of the polygons selected point
		}
		myPoly = new ColoredPolygon(myColor, xs, ys);
	}
	
	public void shift(double d, double y, double z) {
		for(int i = 0; i < myPoints.length; i++) {
			myPoints[i].xPos += d;
			myPoints[i].yPos += y;
			myPoints[i].zPos += z;
		}
	}
	
	public void rotate(double radians, double centerX, double centerY) {
		for(int i = 0; i < myPoints.length; i++) {
			double x1 = myPoints[i].xPos - centerX;
			double y1 = myPoints[i].yPos - centerY;

			//APPLY ROTATION
			double temp = x1 * Math.cos(radians) - y1 * Math.sin(radians);
			y1 = x1 * Math.sin(radians) + y1 * Math.cos(radians);
			x1 = temp;

			//TRANSLATE BACK
			myPoints[i].xPos = (x1) + centerX;
			myPoints[i].yPos = (y1) + centerY;
		}
	}

}
