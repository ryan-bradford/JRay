package Geometry;

import java.awt.Color;
import java.awt.Image;

import main.Main;

public class Polygon3D {
	
	Point3D[] myPoints;
	Color myColor;
	public double distanceFromCamera;
	public Image image;
	public ColoredPolygon myPoly; 
	int myID;
	
	public Polygon3D(Point3D[] toAdd, Color toColor, Image image, int ID) { //The 3D Polygon Object
		myColor = toColor;
		myPoints = toAdd;
		this.image = image;
		myID = ID;
	}
	
	public void updateDistance() { //Gets the farthest points distance from the camera
		int maxID = 0;			   //Used to order how the ColoredPolygons are drawn
		Line[] current = getLinesToPoint(Main.displays.get(myID).currentScene.cameraLocation);
		for(int i = 1; i < current.length; i++) {
			if(current[i].length < current[maxID].length) {
				maxID = i;
			}
		}
		distanceFromCamera = current[maxID].length;
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
		Line[] lines = getLinesToPoint(Main.displays.get(myID).currentScene.cameraLocation); //Gets the lines to the camera
		for(int i = 0; i < lines.length; i++) {
			xs[i] = Main.displays.get(myID).find.getWidthPixel(lines[i].getHorAngle()); //Uses angle to get the x location of the polygons selected point
			ys[i] = Main.displays.get(myID).find.getHeightPixel(lines[i].getVertAngle());//Uses angle to get the y location of the polygons selected point
		}
		myPoly = new ColoredPolygon(myColor, xs, ys, image);
	}
	
	public void changeID(int newID) {
		myID = newID;
	}
	
}
