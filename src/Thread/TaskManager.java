package Thread;

public class TaskManager { // The thing that distributes the tasks to the different cores
	public CPUCore[] cores;
	int lowestLoadAmount;
	int lowestLoadCoreNum;

	public TaskManager() {
		lowestLoadAmount = 50000; // Random value
		cores = new CPUCore[Runtime.getRuntime().availableProcessors()]; // Gets the amount of available cores
		for (int i = 0; i < cores.length; i++) {
			cores[i] = new CPUCore(null); // Creates the core objects
		}
	}

	public int[] addTask(Task task1) { // Adds a task
		int taskNum;
		for (int i = 0; i < cores.length; i++) { // Finds the core with the lowest load
			if (cores[i].getLoad() < lowestLoadAmount) {
				lowestLoadAmount = cores[i].getLoad();
				lowestLoadCoreNum = i;
			}
		}
		taskNum = cores[lowestLoadCoreNum].addTask(task1); // Adds the task to this core
		if (cores[lowestLoadCoreNum].getState() == Thread.State.NEW) { // Checks if the core is not running
			cores[lowestLoadCoreNum].start(); // Starts it if it isn't
		}
		lowestLoadAmount = cores[lowestLoadCoreNum].getLoad();
		return new int[] { lowestLoadCoreNum, taskNum }; // Returns the core number the task was added to
	}

	public int[] addTask(Task task1, int coreNum) { // Adds a task
		int taskNum;
		taskNum = cores[coreNum].addTask(task1); // Adds the task to this core
		if (cores[coreNum].getState() == Thread.State.NEW) { // Checks if the core is not running
			cores[coreNum].start(); // Starts it if it isn't
		}
		for (int i = 0; i < cores.length; i++) { // Finds the core with the lowest load
			if (cores[i].getLoad() < lowestLoadAmount) {
				lowestLoadAmount = cores[i].getLoad();
				lowestLoadCoreNum = i;
			}
		}
		return new int[] { coreNum, taskNum }; // Returns the core number the task was added to
	}
}
