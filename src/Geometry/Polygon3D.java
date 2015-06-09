package Geometry;

import java.awt.Color;

import main.Main;

public class Polygon3D {
	
	Point3D[] myPoints;
	Color myColor;
	public double distanceFromCamera;
	public ColoredPolygon myPoly; 
	int myID;
	int[] xs;
	int[] ys;
	Line[] myLines;
	
	public Polygon3D(Point3D[] toAdd, Color toColor, int ID) { //The 3D Polygon Object
		myColor = toColor;
		myPoints = toAdd;
		myID = ID;
	}
	
	public Line[] getLinesToPoint(Point3D toPathTo) { //Makes an array of lines	
		Line[] lines = new Line[myPoints.length];	  //Which stretch from the camera to each point on the polygon
		for(int i = 0; i < myPoints.length; i++) {
			lines[i] = new Line(toPathTo.xPos, toPathTo.yPos, toPathTo.zPos, myPoints[i].xPos, myPoints[i].yPos, myPoints[i].zPos);
		}
		return lines;
	}
	
	public void rasterizeToScreen() { //Rasterizes the polygon
		xs = new int[myPoints.length];
		ys = new int[myPoints.length];
		myLines = getLinesToPoint(Main.displays.get(myID).currentScene.cameraLocation); //Gets the lines to the camera
		for(int i = 0; i < myLines.length; i++) {
			xs[i] = Main.displays.get(myID).find.getWidthPixel(myLines[i].getHorAngle()); //Uses angle to get the x location of the polygons selected point
			ys[i] = Main.displays.get(myID).find.getHeightPixel(myLines[i].getVertAngle());//Uses angle to get the y location of the polygons selected point
		}
		myPoly = new ColoredPolygon(myColor, xs, ys);
	}
	
	public void changeID(int newID) {
		myID = newID;
	}
	
}
