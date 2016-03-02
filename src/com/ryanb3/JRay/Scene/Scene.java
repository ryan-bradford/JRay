package com.ryanb3.JRay.Scene;

import java.awt.Color;
import java.util.ArrayList;

import com.ryanb3.JRay.Display.Display;
import com.ryanb3.JRay.Geometry.Point3D;
import com.ryanb3.JRay.Geometry.Polygon3D;
import com.ryanb3.JRay.ShapeGenerator.ShapeFactory;

public class Scene {
	
	ShapeFactory factory; //The object which can produce different polygons
	private ArrayList<Polygon3D> current; //The polygons that should be rasterized
	public ArrayList<Polygon3D> toRender; //The polygons that should be rasterized
	public Point3D cameraLocation = new Point3D(0, 0, 0);
	public double xAngle = 0; // The camera rotation to the x
	public double yAngle = 0; // The camera rotation to the Y
	Display display;
	//Stores a bunch of polygons to load to the display
	
	public Scene(Display display) {
		this.display = display;
		current = new ArrayList<Polygon3D>();
		factory = new ShapeFactory(display);
		for (int i = 0; i < 40; i++) {
			//addArray(factory.generateCube(i * 100, 200, 0, 50, new Color(255, 0, 0)));
			//addArray(factory.generateImage(i * 100, 200, 0, 50, "download.jpg"));
		}
		addArray(factory.generateCube(-100, 1000, 0, 100, new Color(255, 0, 0)));
	}
	

	public void addArray(Polygon3D[] toAdd) { //A method to add an array of polygons to current
		for (int i = 0; i < toAdd.length; i++) {
			current.add(toAdd[i]);
		}
		display.repaint();
	}

	public void addArray(ArrayList<Polygon3D> toAdd) {//A method to add an arraylist of polygons to current
		for (int i = 0; i < toAdd.size(); i++) {
			current.add(toAdd.get(i));
		}
		display.repaint();
	}
	
	public ArrayList<Polygon3D> getCurrent() {
		return current;
	}
	
	public void updateDistances() {
		for(int i = 0; i < display.currentScene.current.size(); i++) {
			display.currentScene.current.get(i).updateDistance();
		}
	}
	
}
