package Other;

import java.awt.AlphaComposite;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import Geometry.Polygon3D;

public class OtherFunctions {
	
	public static ArrayList<Polygon3D> sortList(ArrayList<Polygon3D> toBeSorted) { //Sorts the list of polygons
		ArrayList<Polygon3D> sorted = new ArrayList<Polygon3D>();				   //Based on their distance from the camera
		int maxFoundID;															   //Makes it so they draw properly
		int sortedSize = toBeSorted.size();
		for(int i = 0; i < sortedSize; i++) {
			maxFoundID = 0;
			for(int x = 0; x < toBeSorted.size(); x++) {
				if(toBeSorted.get(x).distanceFromCamera > toBeSorted.get(maxFoundID).distanceFromCamera) {
					maxFoundID = x;
				}
			}
			sorted.add(toBeSorted.get(maxFoundID));
			toBeSorted.remove(maxFoundID);
		}
		return sorted;
	}

	public static Image resizeImg(Image originalImage, int biggerWidth, int biggerHeight) { //Resizes an image
	    int type = BufferedImage.TYPE_INT_ARGB;
	    //No actual clue how this works
	    if(biggerWidth == 0 || biggerHeight == 0) {
	    	return null;
	    } else {
	    BufferedImage resizedImage = new BufferedImage(biggerWidth, biggerHeight, type);
	    Graphics2D g = resizedImage.createGraphics();

	    g.setComposite(AlphaComposite.Src);
	    g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	    g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
	    g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

	    g.drawImage(originalImage, 0, 0, biggerWidth, biggerHeight, null);
	    g.dispose();
	    	return resizedImage;
	    }	
	}

}
