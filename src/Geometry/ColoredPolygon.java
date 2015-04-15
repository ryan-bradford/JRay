package Geometry;

import java.awt.Color;
import java.awt.Polygon;

public class ColoredPolygon extends Polygon {
	
	public Color myColor;
	
	public ColoredPolygon(Color toColor, int[] xs, int[] ys) {
		super(xs, ys, xs.length);
		myColor = toColor;
	}
	
}
