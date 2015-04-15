package Other;

import java.awt.Toolkit;
import java.util.ArrayList;

import main.Main;

public class ScreenPointFinder {
	
	public double FOV = Main.FOV;
	public ArrayList<Double> widthAngles;
	public ArrayList<Double> heightAngles;
	public int screenHeight = Toolkit.getDefaultToolkit()
			.getScreenSize().height;
	public int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;

	public ScreenPointFinder() {
		generateScreenAngles();
	}
	
	public void generateScreenAngles() {
		widthAngles = new ArrayList<Double>();
		heightAngles = new ArrayList<Double>();
		double startingAngle = -FOV / 2;
		double changePerMove = FOV / screenHeight;
		for (int i = 0; i < screenHeight; i++) {
			heightAngles.add(startingAngle + changePerMove * i);
		}
		startingAngle = -FOV / 2;
		changePerMove = FOV / screenWidth;
		for (int i = 0; i < screenWidth; i++) {
			widthAngles.add(startingAngle + changePerMove * i);
		}
	}

	public int getWidthPixel(double widthAngle) {
		int toReturn = screenWidth + 1000;
		for (int i = 0; i < widthAngles.size(); i++) {
			if (widthAngle - Main.xAngle < widthAngles.get(i)) {
				toReturn = i;
				break;
			}
		}
		return toReturn;
	}

	public int getHeightPixel(double heightAngle) {
		int toReturn = screenHeight + 1000;
		for (int i = 0; i < heightAngles.size(); i++) {
			if (heightAngle - Main.yAngle < heightAngles.get(i)) {
				toReturn = i;
				break;
			}
		}
		return toReturn;
	}
}
