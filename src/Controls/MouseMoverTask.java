package Controls;

import java.awt.AWTException;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Robot;

import main.Main;
import Thread.Task;

public class MouseMoverTask extends Task {

	int currentX;
	int currentY;
	Robot mouseMover;
	boolean toMoveOrCalculate = false; // True is move, false is calculate

	public MouseMoverTask() {
		try {
			mouseMover = new Robot();
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void runTask() { // The default task object
		if (!Main.paused) {
			if (toMoveOrCalculate) {
				mouseMover.mouseMove(Main.screenWidth / 2,
						Main.screenHeight / 2);
				toMoveOrCalculate = false;
			} else {
				Point point = MouseInfo.getPointerInfo().getLocation();
				currentX = (int) point.getX();
				currentY = (int) point.getY();
				double xAngle = Main.xAngle + (Main.FOV / Main.screenWidth / 10)
						* (currentX - Main.screenWidth / 2);
				double yAngle = Main.yAngle + (Main.FOV / Main.screenHeight / 10)
						* (currentY - Main.screenHeight / 2);
				Main.xAngle = xAngle;
				Main.yAngle = yAngle;
				toMoveOrCalculate = true;
			}
		}
	}

	// Most methods will be overriden
	@Override
	public Boolean returnRunnable() {
		return true;
	}

	@Override
	public int getWait() {
		return 7;
	}

	@Override
	public int[] getData() {
		return new int[] { 0 };
	}

	@Override
	public int getCPULoad() {
		return 0; // 0 is no load, 3 is maximum load
	}
}
