package Display;

import java.util.ArrayList;

import javax.swing.JFrame;

import main.Main;
import Controls.KeyControls;
import Controls.MouseMoverTask;
import Other.OtherFunctions;
import Other.ScreenPointFinder;
import Scene.Scene;
import Update.RasterizeTask;
import Update.UpdateTask;

public class Display extends JFrame { //Just a holder for a JPanel
	
	public DisplayPanel display;
	public RasterizeTask[] rasterizers; //The tasks which do the rasterization
	public UpdateTask update; //The class that orders how stuff should be drawn and what should be drawn 
	public KeyControls keyControls; //The class that control the camera movement
	public boolean paused = false; //Whether the engine is paused or not
	public ScreenPointFinder find; //The algorithm that changes angle to pixel
	MouseMoverTask mover; //The task which controls mouse and window centering
						  //Also translates movement to angle movement
	public Scene currentScene;
	public double xAngle = 0; //The camera rotation to the x
	public double yAngle = 0; //The camera rotation to the Y
	public static double FOV = 1.13446401379; //A nice number which looks good
	public static double sensitivity = 2; //The sensitivity of the mouse, range of 1 to 10 
	
	public Display(int screenWidth, int screenHeight) { //Inits the display panel
		display = new DisplayPanel(screenWidth, screenHeight);
		display.setBounds(0, 0, screenWidth, screenHeight);
		add(display);
		initPointFinder();
		initUserControls();
		startEngine();
	}
	
	public void addScene(Scene toLoad) {
		currentScene = toLoad;
	}
	
	public void initPointFinder() { //Makes some more stuff not null
		find = new ScreenPointFinder();
	}
	
	public void startEngine() { //Starts the rasterizers and updater
		update = new UpdateTask();
		double cores = Runtime.getRuntime().availableProcessors() - 1;
		rasterizers = new RasterizeTask[(int) cores];
		Main.manage.addTask(update);
		for (int i = 0; i < cores; i++) {
			rasterizers[i] = new RasterizeTask(1 / cores, i);
			Main.manage.addTask(rasterizers[i]);
		}
	}
	
	public void initUserControls() { //Starts the user controls
		keyControls = new KeyControls();
		this.addKeyListener(keyControls);
		mover = new MouseMoverTask();
		Main.manage.addTask(mover);
	}
	
	public void setScene(Scene toSet) {
		currentScene = toSet;
	}
}
