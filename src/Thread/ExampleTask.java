package Thread;

import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Robot;

import main.Main;

public class ExampleTask extends Task {

	@Override
	public void runTask() { // The default task object

	}

	// Most methods will be overriden
	@Override
	public Boolean returnRunnable() {
		return false;
	}

	@Override
	public int getWait() {
		return 1000;
	}

	@Override
	public int[] getData() {
		return new int[] { 0 };
	}

	@Override
	public int getCPULoad() {
		return 0; // 0 is no load, 3 is maximum load
	}
}
