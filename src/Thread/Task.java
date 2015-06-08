package Thread;

public class Task {
	
	int numShortWaitsPassed = 0;
	
	public void runTask() { //The default task object
		
	}
	
	//Most methods will be overriden
	
	public Boolean returnRunnable() {
		return false;
	}
	
	public int getWait() {
		return 1000;
	}
	
	public int[] getData() {
		return new int[] {0};
	}
	
	public int getCPULoad() {
		return 0; //0 is no load, 3 is maximum load
	}
}
