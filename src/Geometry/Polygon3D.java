package Geometry;

import java.awt.Color;

import main.Main;

public class Polygon3D {
	
	Point3D[] myPoints;
	Color myColor;
	public double distanceFromCamera;
	public ColoredPolygon myPoly; 
	
	
	public Polygon3D(Point3D[] toAdd, Color toColor) { //The 3D Polygon Object
		myColor = toColor;
		myPoints = toAdd;
	}
	
	public Line[] getLinesToPoint(Point3D toPathTo) { //Makes an array of lines	
		Line[] lines = new Line[myPoints.length];	  //Which stretch from the camera to each point on the polygon
		for(int i = 0; i < myPoints.length; i++) {
			lines[i] = new Line(toPathTo.xPos, toPathTo.yPos, toPathTo.zPos, myPoints[i].xPos, myPoints[i].yPos, myPoints[i].zPos);
		}
		return lines;
	}
	
	public void rasterizeToScreen() { //Rasterizes the polygon
		int[] xs = new int[myPoints.length];
		int[] ys = new int[myPoints.length];
		Line[] myLines = getLinesToPoint(Main.display.currentScene.cameraLocation); //Gets the lines to the camera
		for(int i = 0; i < myLines.length; i++) {
			xs[i] = Main.display.find.getWidthPixel(myLines[i].getHorAngle()); //Uses angle to get the x location of the polygons selected point
			ys[i] = Main.display.find.getHeightPixel(myLines[i].getVertAngle());//Uses angle to get the y location of the polygons selected point
		}
		myPoly = new ColoredPolygon(myColor, xs, ys);
	}
	
}
