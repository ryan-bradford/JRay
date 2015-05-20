package Update;

import main.Main;
import Geometry.ColoredPolygon;
import Other.OtherFunctions;
import Thread.Task;

public class UpdateTask extends Task {

	int orderNum;
	double percent;
	boolean doneUpating;

	@Override
	public void runTask() { // The default task object
		if (!Main.paused && readyToUpdate()) { //Runs once for every time the rasterizer runs
			Main.display.repaint(); //Makes the screen call the paintComponent method
			Main.current = OtherFunctions.sortList(Main.current); //Sorts the list
			for (int i = 0; i < Main.rasterizers.length; i++) { //Tells the rasterizers to start
				Main.rasterizers[i].isDone = false;
			}
		}
	}

	public boolean readyToUpdate() {
		for (int i = 0; i < Main.rasterizers.length; i++) {
			if (Main.rasterizers[i].isDone == false) {
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
		return 2; // 0 is no load, 3 is maximum load
	}
}
