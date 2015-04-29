package Update;

import java.awt.AWTException;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Robot;

import main.Main;
import Geometry.ColoredPolygon;
import Other.OtherFunctions;
import Thread.Task;

public class UpdateTask extends Task {

	int currentX;
	int currentY;
	Robot mouseMover;
	int orderNum;
	double percent;
	boolean doneUpating;

	public UpdateTask() {
		try {
			mouseMover = new Robot();
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void runTask() { // The default task object
		if (!Main.paused && readyToUpdate()) {
			Main.display.repaint();
			mouseMover.mouseMove(Main.screenWidth / 2, Main.screenHeight / 2);
		}
		try {
			Thread.sleep(2);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (!Main.paused && readyToUpdate()) {
			Point point = MouseInfo.getPointerInfo().getLocation();
			currentX = (int) point.getX();
			currentY = (int) point.getY();
			double xAngle = Main.xAngle + (Main.FOV / Main.screenWidth)
					* (currentX - Main.screenWidth / 2);
			double yAngle = Main.yAngle + (Main.FOV / Main.screenHeight)
					* (currentY - Main.screenHeight / 2);
			Main.xAngle = xAngle;
			Main.yAngle = yAngle;
			Main.current = OtherFunctions.sortList(Main.current);
			Main.toDraw = new ColoredPolygon[Main.current.size()];
			//Main.display.repaint();
			for(int i = 0; i < Main.rasterizers.length; i++) {
				Main.rasterizers[i].isDone = false;
			}
		}
	}
	
	public boolean readyToUpdate() {
		for(int i = 0; i < Main.rasterizers.length; i++) {
			if(Main.rasterizers[i].isDone == false) {
				return false;
			}
		}
		return true;
	}

	// Most methods will be overriden
	@Override
	public Boolean returnRunnable() {
		return true;
	}

	@Override
	public int getWait() {
		return 1;
	}

	@Override
	public int[] getData() {
		return new int[] { 0 };
	}

	@Override
	public int getCPULoad() {
		return 3; // 0 is no load, 3 is maximum load
	}
}
