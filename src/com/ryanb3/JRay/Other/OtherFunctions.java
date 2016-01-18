package com.ryanb3.JRay.Other;

import java.awt.AlphaComposite;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

public class OtherFunctions {

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
