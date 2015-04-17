package Other;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

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

}
