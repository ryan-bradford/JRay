package Controls;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import main.Main;

public class PauseKeyControls implements KeyListener{

	
	
	public PauseKeyControls() {

	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_ESCAPE) {
			Main.display.paused = false;
			Main.display.display.startEngine();
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
