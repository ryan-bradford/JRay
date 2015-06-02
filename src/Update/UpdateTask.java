package Update;

import main.Main;
import Other.OtherFunctions;
import Thread.Task;

public class UpdateTask extends Task {

	int orderNum;
	double percent;
	boolean doneUpating;
	int myID;
	int myWait = 1;

	public UpdateTask(int ID) {
		myID = ID;
	}

	@Override
	public void runTask() { // The default task object
		try {
			Main.displays.get(myID).FOV = Math.PI / 3 + Math.PI * Main.displays.get(myID).display.settings.FOVSlider.getValue() / 10;
			myWait = Main.displays.get(myID).displayWait;
			if (!Main.displays.get(myID).paused && readyToUpdate()) { // Runs once for every time the rasterizer runs
				Main.displays.get(myID).repaint(); // Makes the screen call the paintComponent method
				Main.displays.get(myID).currentScene.current = OtherFunctions.sortList(Main.displays.get(myID).currentScene.current); // Sorts the list
				for (int i = 0; i < Main.displays.get(myID).rasterizers.length; i++) { // Tells the rasterizers to start
					Main.displays.get(myID).rasterizers[i].isDone = false;
				}
			}
		} catch (IndexOutOfBoundsException ex) {

		}
	}

	public boolean readyToUpdate() {
		for (int i = 0; i < Main.displays.get(myID).rasterizers.length; i++) {
			if (Main.displays.get(myID).rasterizers[i].isDone == false) {
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
