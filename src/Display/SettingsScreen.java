package Display;

import java.awt.Graphics;



import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import Controls.PauseKeyControls;
import main.Main;

public class SettingsScreen extends JPanel {
	
	JButton saveDebugInfo;
	JButton showOrHideDebug;
	PauseKeyControls keys;
	int myID;
	
	public SettingsScreen(int ID) {
		this.setLayout(null);
		keys = new PauseKeyControls(myID);
		showOrHideDebug = new JButton();
		showOrHideDebug.addActionListener(new DebugButtonListener());
		showOrHideDebug.setText("Hide Debug Info");
		showOrHideDebug.setBounds(0, 0, 50, 50);
		showOrHideDebug.addKeyListener(keys);
		add(showOrHideDebug);
		myID = ID;
	}
	
	@Override 
	public void paintComponent(Graphics g) {
		this.addKeyListener(keys);
	}
	

	public class DebugButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			Main.displays.get(myID).display.showDebugInfo = !Main.displays.get(myID).display.showDebugInfo;
			if(Main.displays.get(myID).display.showDebugInfo) {
				showOrHideDebug.setText("Hide Debug Info");
			} else {
				showOrHideDebug.setText("Show Debug Info");
			}
		}
	
	}	
}
