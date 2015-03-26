package renderField.pathing;

import renderField.geometry.Vector;

public class LinePather extends Thread {
	
	boolean colided = false;
	Vector toPath;
	
	public LinePather(Vector toPath) {
		this.toPath = toPath;
	}
	
	public void run() {
		while(!colided) {
			toPath.moveOnce();
		}
	}
	
}
