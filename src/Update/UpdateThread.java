package Update;

import java.awt.MouseInfo;
import java.awt.Point;

import main.Main;

public class UpdateThread extends Thread {

	public void run() {
		
		while(true) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Point point = MouseInfo.getPointerInfo().getLocation(); 
			double xAngle = (-Main.FOV / 2) + (Main.FOV / Main.screenWidth) * point.getX();
			double yAngle = (-Main.FOV / 2) + (Main.FOV / Main.screenHeight) * point.getY();
			Main.xAngle = xAngle;
			Main.yAngle = yAngle;
			Main.display.repaint();

		}
		
	}
	
}
