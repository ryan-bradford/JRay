package Update;

import java.awt.AWTException;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Robot;

import main.Main;

public class UpdateThread extends Thread {

	public boolean paused = false;
	int currentX;
	int currentY;
	Robot mouseMover;
	
	public UpdateThread() {
		try {
			mouseMover = new Robot();
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void run() {

		while (true) {
			if (!paused) {
				mouseMover.mouseMove(Main.screenWidth / 2, Main.screenHeight / 2);
			}
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (!paused) {
				Point point = MouseInfo.getPointerInfo().getLocation();
				currentX = (int) point.getX();
				currentY = (int) point.getY();
				double xAngle = Main.xAngle + (Main.FOV / Main.screenWidth) * (currentX - Main.screenWidth / 2);
				double yAngle = Main.yAngle + (Main.FOV / Main.screenHeight) * (currentY - Main.screenHeight / 2);
				Main.xAngle = xAngle;
				Main.yAngle = yAngle;
			}
			Main.display.repaint();

		}

	}

}
