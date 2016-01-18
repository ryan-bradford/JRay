package com.ryanb3.JRay.Update;

import java.util.Collections;
import java.util.Comparator;

import com.ryanb3.JRay.Display.Display;
import com.ryanb3.JRay.Geometry.Polygon3D;
import com.ryanb3.TaskManager.Task;

public class UpdateTask extends Task {

	int orderNum;
	double percent;
	boolean doneUpating;
	Display display;
	int myWait = 1;

	public UpdateTask(Display display) {
		this.display = display;
	}

	@Override
	public void runTask() { // The default task object
		try {
			myWait = display.displayWait;
			if (!display.paused && readyToUpdate()) { // Runs once for every time the rasterizer runs
				display.repaint(); // Makes the screen call the paintComponent method
				//Make Polys Update Distance
				display.currentScene.updateDistances();
				display.currentScene.toRender = display.currentScene.getCurrent();
				Collections.sort(display.currentScene.toRender, new Comparator<Polygon3D>() {
			        @Override
			        public int compare(Polygon3D  poly1, Polygon3D  poly2)
			        {
			            return Double.compare(poly1.distanceFromCamera, poly2.distanceFromCamera);
			        }
			    });
				for (int i = 0; i < display.rasterizers.length; i++) { // Tells the rasterizers to start
					display.rasterizers[i].isDone = false;
				}
			}
		} catch (IndexOutOfBoundsException ex) {

		}
	}

	public boolean readyToUpdate() {
		for (int i = 0; i < display.rasterizers.length; i++) {
			if (display.rasterizers[i].isDone == false) {
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
		return myWait; //1000 / wiat + 1 = FPS
	}

	@Override
	public int getCPULoad() {
		return 2; // 0 is no load, 3 is maximum load
	}
}
