package JRay.Scene;


import java.util.ArrayList;

import main.Main;
import JRay.Geometry.Point3D;
import JRay.Geometry.Polygon3D;

public class Scene {
	protected ArrayList<Polygon3D> current; //The polygons that should be rasterized
	public ArrayList<Polygon3D> toSort; //The polygons that should be rasterized
	public ArrayList<Polygon3D> toRender; //The polygons that should be rasterized
	public Point3D cameraLocation = new Point3D(0, 0, 0);
	public double xAngle = 0; // The camera rotation to the x
	public double yAngle = 0; // The camera rotation to the Y

	//Stores a bunch of polygons to load to the display
	
	public Scene() {
		current = new ArrayList<Polygon3D>();
		for (int i = 0; i < 40; i++) {
			//addArray(factory.generateCube(i * 100, 200, 0, 50, new Color(255, 0, 0)));
			//addArray(factory.generateImage(i * 100, 200, 0, 50, "download.jpg"));
		}
	}
	

	public void addArray(Polygon3D[] toAdd) { //A method to add an array of polygons to current
		for (int i = 0; i < toAdd.length; i++) {
			current.add(toAdd[i]);
		}
		Main.display.repaint();
	}

	public void addArray(ArrayList<Polygon3D> toAdd) {//A method to add an arraylist of polygons to current
		for (int i = 0; i < toAdd.size(); i++) {
			current.add(toAdd.get(i));
		}
		Main.display.repaint();
	}
	
	public ArrayList<Polygon3D> getCurrent() {
		return current;
	}
	
	public void updateDistances() {
		for(int i = 0; i < current.size(); i++) {
			current.get(i).updateDistance();
		}
	}
	
}
