package Controls;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import main.Main;

public class PauseKeyControls implements KeyListener{

	int myID;
	
	public PauseKeyControls(int ID) {
		myID = ID;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_ESCAPE) {
			Main.displays.get(myID).paused = false;
			Main.displays.get(myID).display.startEngine();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
