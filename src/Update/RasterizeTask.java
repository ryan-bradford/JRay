package Update;

import com.ryanb3.TaskManager.Task;

import main.Main;

public class RasterizeTask extends Task {

	int orderNum;
	double percent;
	int lowerRange;
	int upperRange;
	public boolean shouldRasterize = false;
	public boolean isDone = true;
	

	public RasterizeTask(double percent, int orderNum) {
		this.orderNum = orderNum;
		this.percent = percent;
	}

	@Override
	public void runTask() { // Runs once for every time the updater runs
		if (!Main.display.paused && !isDone) { // Rasterizes the polygons it needs to that are within its range
			if ((orderNum + 1) != Runtime.getRuntime().availableProcessors()) {
				lowerRange = (int) (Math.ceil(Main.display.currentScene.toRender.size() * percent * orderNum));
				upperRange = (int) (Math.floor(Main.display.currentScene.toRender.size() * percent * (orderNum + 1)));
			} else {
				lowerRange = (int) (Math.ceil(Main.display.currentScene.toRender.size() * percent * orderNum));
				upperRange = Main.display.currentScene.toRender.size();
			}
			for (int i = lowerRange; i < upperRange; i++) {
				try {
					Main.display.currentScene.toRender.get(i).rasterizeToScreen(); // Sets a buffer polygon
				} catch (ArrayIndexOutOfBoundsException ex) {

				}
			}
			isDone = true;
		}
	}

	@Override
	public Boolean returnRunnable() {
		return true;
	}

	@Override
	public int getWait() {
		return 1;
	}

	@Override
	public int getCPULoad() {
		return 3;
	}
	
	public void nanoWait(int length){
	    final long INTERVAL = length;
	    long start = System.nanoTime();
	    long end=0;
	    do{
	        end = System.nanoTime();
	    }while(start + INTERVAL >= end);
	}
}
