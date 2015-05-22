package Geometry;

import java.awt.Color;

import java.awt.Image;
import java.awt.Polygon;

import Other.OtherFunctions;

public class ColoredPolygon extends Polygon { //A sub-object of polygon
											  //It is a polygon which can store a color and an image
	
	public Color myColor;
	public Image img;
	public double minX = Double.MAX_VALUE;
	double maxX = -Double.MAX_VALUE;
	public double minY = Double.MAX_VALUE;
	double maxY = -Double.MAX_VALUE;

	
	public ColoredPolygon(Color toColor, int[] xs, int[] ys, Image myImage) {
		super(xs, ys, xs.length);
		myColor = toColor;
		for(int i = 0; i < super.npoints; i += 2) {
		    double x = super.xpoints[i];
		    double y = super.ypoints[i + 1];
		    minX = Math.min(minX, x);
		    maxX = Math.max(maxX, x);
		    minY = Math.min(minY, y);
		    maxY = Math.max(maxY, y);    
		}

		double width = maxX - minX;
		double height = maxY - minY;
		img = OtherFunctions.resizeImg(myImage, (int)(width), (int)(height));
	}
	
}
