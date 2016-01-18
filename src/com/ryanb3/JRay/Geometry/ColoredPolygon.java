package com.ryanb3.JRay.Geometry;

import java.awt.Color;
import java.awt.Polygon;

public class ColoredPolygon extends Polygon { // A sub-object of polygon
												// It is a polygon which can store a color and an image

	public Color myColor;

	public ColoredPolygon(Color toColor, int[] xs, int[] ys) {
		super(xs, ys, xs.length);
		myColor = toColor;
	}

}
