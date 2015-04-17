package Other;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import Geometry.Polygon3D;
import main.Main;

public class OtherFunctions {

	public static void hideCursor(boolean hideOrShow) { // True is hide, false
														// is show
		if (hideOrShow) {
			BufferedImage cursorImg = new BufferedImage(16, 16,
					BufferedImage.TYPE_INT_ARGB);
			Cursor blankCursor = Toolkit.getDefaultToolkit()
					.createCustomCursor(cursorImg, new Point(0, 0),
							"blank cursor");
			Main.display.getContentPane().setCursor(blankCursor);
		} else {
			Main.display.getContentPane().setCursor(Cursor.getDefaultCursor());
		}
	}
	
	public static ArrayList<Polygon3D> sortList(ArrayList<Polygon3D> toBeSorted) {
		ArrayList<Polygon3D> sorted = new ArrayList<Polygon3D>();
		int maxFoundID;
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

}
