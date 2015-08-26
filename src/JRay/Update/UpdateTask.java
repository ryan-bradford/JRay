package JRay.Update;

import java.util.ArrayList;
import main.Main;
import JRay.Geometry.Polygon3D;
import JRay.Thread.Task;

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
			if (!Main.display.paused && readyToUpdate()) { // Runs once for every time the rasterizers run
				Main.display.repaint(); // Makes the screen call the paintComponent method
				Main.display.currentScene.updateDistances();
				Main.display.currentScene.toRender = sortArray(Main.display.currentScene.getCurrent());
				//Main.display.currentScene.toRender = toSort;
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
		return myWait; // 1000 / wiat + 1 = FPS
	}

	@Override
	public int[] getData() {
		return new int[]{0};
	}

	@Override
	public int getCPULoad() {
		return 2; // 0 is no load, 3 is maximum load
	}
	
	public ArrayList<Polygon3D> sortArray(ArrayList<Polygon3D> toSort) {
		int greatestID = 0;
		for(int i = 0; i < toSort.size(); i++) {
			for(int x = i; x < toSort.size(); x++) {
				if(toSort.get(x).distanceFromCamera < toSort.get(greatestID).distanceFromCamera) {
					greatestID = x;
				}
			}
			toSort = swapValues(greatestID, i, toSort);
		}
		return toSort;
	}
	
	public ArrayList<Polygon3D> swapValues(int swap1, int swap2, ArrayList<Polygon3D> toChange) {
		Polygon3D buffer = toChange.get(swap1);
		toChange.set(swap1, toChange.get(swap2));
		toChange.set(swap2, buffer);
		return toChange;
	}
}
