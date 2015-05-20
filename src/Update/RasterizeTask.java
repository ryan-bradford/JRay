package Update;

import main.Main;
import Thread.Task;

public class RasterizeTask extends Task {

	int orderNum;
	double percent;
	int lowerRange;
	int upperRange;
	public boolean shouldRasterize = false;
	public boolean isDone = false;
	
	public RasterizeTask(double percent, int orderNum) {
		this.orderNum = orderNum;
		this.percent = percent;
	}
	
	@Override
	public void runTask() { //Runs once for every time the updater runs
		if (!Main.paused && !isDone) { //Rasterizes the polygons it needs to that are within its range
			lowerRange = (int) (Main.current.size() * percent * orderNum);
			upperRange = (int) (Main.current.size() * percent * (orderNum + 1));
			for(int i = lowerRange; i < upperRange; i++) {
				try {
					Main.current.get(i).rasterizeToScreen(); //Sets a buffer polygon
				} catch(ArrayIndexOutOfBoundsException ex) {
					
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
	public int[] getData() {
		return new int[] {0};
	}
	
	@Override
	public int getCPULoad() {
		return 3; 
	}
}
