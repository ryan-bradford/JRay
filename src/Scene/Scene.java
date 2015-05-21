package Scene;

import java.util.ArrayList;

import main.Main;
import Geometry.Point3D;
import Geometry.Polygon3D;
import ShapeGenerator.ShapeFactory;

public class Scene {
	static ShapeFactory factory; //The object which can produce different polygons
	public static ArrayList<Polygon3D> current; //The polygons that should be rasterized
	public static Point3D cameraLocation = new Point3D(0, 0, 0);


	//Stores a bunch of polygons to load to the display
	
	public Scene() {
		current = new ArrayList<Polygon3D>();
		factory = new ShapeFactory();
		for (int i = 0; i < 4; i++) {
			//addArray(factory.generateSquare(i * 100, 200, 0, 50));
			//addArray(factory.generateImage(i * 100, 200, 0, 50, "download.jpg"));
		}
		addArray(factory.generateSquare(-100, 1000, 0, 100));
	}
	

	public static void addArray(Polygon3D[] toAdd) { //A method to add an array of polygons to current
		for (int i = 0; i < toAdd.length; i++) {
			current.add(toAdd[i]);
		}
		Main.display.repaint();
	}

	public static void addArray(ArrayList<Polygon3D> toAdd) {//A method to add an arraylist of polygons to current
		for (int i = 0; i < toAdd.size(); i++) {
			current.add(toAdd.get(i));
		}
		Main.display.repaint();
	}
	
}
