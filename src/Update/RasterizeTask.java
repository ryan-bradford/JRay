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
	public void runTask() { //The default task object
		if (!Main.paused && !isDone) {
			lowerRange = (int) (Main.current.size() * percent * orderNum);
			upperRange = (int) (Main.current.size() * percent * (orderNum + 1));
			for(int i = lowerRange; i < upperRange; i++) {
				try {
					Main.toDraw[i] = Main.current.get(i).rasterizeToScreen();
				} catch(ArrayIndexOutOfBoundsException ex) {
					
				}
			}
			isDone = true;
		}
	}
	
	//Most methods will be overriden
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
		return 3; //0 is no load, 3 is maximum load
	}
}
