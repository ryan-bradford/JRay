package Update;

import java.util.Collections;
import java.util.Comparator;

import main.Main;
import Geometry.Polygon3D;
import Other.OtherFunctions;
import Thread.Task;

public class UpdateTask extends Task {

	int orderNum;
	double percent;
	boolean doneUpating;
	
	int myWait = 1;

	public UpdateTask() {

	}

	@Override
	public void runTask() { // The default task object
		try {
			myWait = Main.display.displayWait;
			if (!Main.display.paused && readyToUpdate()) { // Runs once for every time the rasterizer runs
				Main.display.repaint(); // Makes the screen call the paintComponent method
				//Make Polys Update Distance
				Main.display.currentScene.updateDistances();
				Main.display.currentScene.toRender = Main.display.currentScene.getCurrent();
				Collections.sort(Main.display.currentScene.toRender, new Comparator<Polygon3D>() {
			        @Override
			        public int compare(Polygon3D  poly1, Polygon3D  poly2)
			        {
			            return Double.compare(poly1.distanceFromCamera, poly2.distanceFromCamera);
			        }
			    });
				for (int i = 0; i < Main.display.rasterizers.length; i++) { // Tells the rasterizers to start
					Main.display.rasterizers[i].isDone = false;
				}
			}
		} catch (IndexOutOfBoundsException ex) {

		}
	}

	public boolean readyToUpdate() {
		for (int i = 0; i < Main.display.rasterizers.length; i++) {
			if (Main.display.rasterizers[i].isDone == false) {
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
	public int[] getData() {
		return new int[]{0};
	}

	@Override
	public int getCPULoad() {
		return 2; // 0 is no load, 3 is maximum load
	}
}
