package Other;

import main.Main;

public class ScreenPointFinder {

	public int getWidthPixel(double widthAngle) {
		double currentFOV = Main.FOV;
		if(Main.display.getBounds().getWidth() < Main.display.getBounds().getHeight()) {
			currentFOV *= (double)(Main.display.getContentPane().getWidth()) / (double)(Main.display.getContentPane().getHeight());
		}
		double angle = TrigFunctions.findLowestAngle(widthAngle - Main.xAngle);
		double initAngle = angle + currentFOV/2;
		double changePerMove = currentFOV / Main.display.getContentPane().getWidth();
		int toReturn = (int) (initAngle / changePerMove);
		return toReturn;
	}

	public int getHeightPixel(double heightAngle) {
		double currentFOV = Main.FOV;
		if(Main.display.getContentPane().getHeight() < Main.display.getContentPane().getWidth()) {
			currentFOV *= (double)(Main.display.getContentPane().getHeight()) / (double)(Main.display.getContentPane().getWidth());
		}
		double angle = TrigFunctions.findLowestAngle(heightAngle - Main.yAngle);
		double initAngle = angle + currentFOV/2;
		double changePerMove = currentFOV / Main.display.getContentPane().getHeight();
		int toReturn = (int) (initAngle / changePerMove);
		return toReturn;
	}
}
