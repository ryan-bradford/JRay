package Controls;

import java.awt.AWTException;

import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Robot;

import com.ryanb3.TaskManager.Task;

import main.Main;

public class MouseMoverTask extends Task {

	int currentX;
	int currentY;
	Robot mouseMover;
	boolean toMoveOrCalculate = false; // True is move, false is calculate
									   //The stages of this task, one centers one calculates angle moved
	Point point;
	public double sensitivity = 5;
	
	double xAngle;
	double yAngle;
	
	public MouseMoverTask() {
		try {
			mouseMover = new Robot(); //The thing that centers the pointer
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void runTask() { // The default task object
		if (!Main.display.paused) {
			if (toMoveOrCalculate) {
				mouseMover.mouseMove((int)(Main.screenWidth / 2),
						(int)(Main.screenHeight / 2)); //Center mouse
				Main.display.setBounds((Main.screenWidth - Main.display.getWidth()) / 2, (Main.screenHeight - Main.display.getHeight() - Main.display.screenOffset) / 2,
						Main.display.getBounds().width, Main.display.getBounds().height); //Center and resize window
				toMoveOrCalculate = false; //Go to the other stage
			} else {
				point = MouseInfo.getPointerInfo().getLocation(); //Get mouse location
				currentX = (int) point.getX();
				currentY = (int) point.getY();
				xAngle = Main.display.currentScene.xAngle + (Main.display.FOV / Main.screenWidth) / sensitivity
						* (currentX - Main.screenWidth / 2); //Calculate xAngle moved
				yAngle = Main.display.currentScene.yAngle + (Main.display.FOV / Main.screenHeight) / sensitivity
						* (currentY - Main.screenHeight / 2);//Calculate yAngle moved
				Main.display.currentScene.xAngle = xAngle;
				Main.display.currentScene.yAngle = yAngle;
				toMoveOrCalculate = true;
			}
		}
		if(!Main.display.isActive() && !Main.display.paused) {//Pauses the game if you move the window to the background
			Main.display.paused = true;
			Main.display.display.pauseGame();
			Main.display.hideCursor(false);
			Main.display.repaint();
		}
	}

	// Most methods will be overriden
	@Override
	public Boolean returnRunnable() {
		return true;
	}

	@Override
	public int getWait() {
		return 5;
	}

	@Override
	public int getCPULoad() {
		return 0; // 0 is no load, 3 is maximum load
	}
}
