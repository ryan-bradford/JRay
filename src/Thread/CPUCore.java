package Thread;

import java.util.ArrayList;

//One core in the CPU will run one thread
public class CPUCore extends Thread { // A Single thread that handles a passed list of processes
	public ArrayList<Task> tasks; // The ammount of tasks assigned
	Integer shortestWait; // The shortest wait of all the tasks
	int load; // What is the total "load" gave by this thread (Is entireley arbritrary)
	int myID = (int) (Math.random() * 100);

	public CPUCore(ArrayList<Task> tasks1) { // Inits the core to a list of tasks, this task list is most commonly passed as null
		load = 0;
		tasks = new ArrayList<Task>();
		try {
			if (tasks1 != null) {
				tasks = tasks1;
			}
			for (int i = 0; i < tasks.size(); i++) { // Finds the shortest wait
				tasks.get(i).numShortWaitsPassed = 0;
			}
			shortestWait = 1;
		} catch (NullPointerException ex) {

		}
	}

	public void run() {
		while (true) {
			try {
				try {
					CPUCore.sleep(shortestWait); // Sleeps the shortest wait every cycle
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				for (int i = 0; i < tasks.size(); i++) { // Calculates if the shortest wait adds up to the wait of the selected
															// It does this by multiplying the short wait count by the short wait
															// And checks if it is >= to the wait of the task
					if (tasks.get(i).numShortWaitsPassed * shortestWait >= tasks.get(i).getWait()) { // If it does it will execute the task
						tasks.get(i).numShortWaitsPassed = 0; // It will reset the short waits passed for said task
						if (tasks.get(i).returnRunnable() == true) {
							tasks.get(i).runTask();
						}
					} else { // If it isnt
						tasks.get(i).numShortWaitsPassed++; // It will add one to the short waits passed
					}
				}
			} catch (NullPointerException ex) {
			}
		}
	}

	public int addTask(Task task1) { // Adds a task to the thread
		tasks.add(task1);
		load = load + task1.getCPULoad();
		return tasks.size();
	}

	public int getLoad() {
		return load;
	}

}
