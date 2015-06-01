package Display;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import main.Main;
import Controls.PauseKeyControls;

public class SettingsScreen extends JPanel {

	JButton saveDebugInfo;
	JButton showOrHideDebug;
	JButton updateFPSButton;
	JTextField updateFPS;
	PauseKeyControls keys;
	int myID;
	int width = 150;
	int height = 40;

	public SettingsScreen(int ID) {
		this.setLayout(null);
		myID = ID;
		keys = new PauseKeyControls(myID);
		initDebugButtons();
		initFPSControls();
	}

	@Override
	public void paintComponent(Graphics g) {
		this.addKeyListener(keys);
	}
	
	private void initDebugButtons() {
		showOrHideDebug = new JButton("Hide Debug Info");
		showOrHideDebug.addActionListener(new DebugButtonListener());
		showOrHideDebug.addKeyListener(keys);
		width = 150;
		height = 40;
		showOrHideDebug.setBounds((Main.displays.get(myID).getWidth() - width) / 2, Main.displays.get(myID).getHeight() / 12, width, height);
		add(showOrHideDebug);
	}
	
	private void initFPSControls() {
		updateFPS = new JTextField("Enter Your FPS Limit: ");
		width = 150;
		height = 20;
		updateFPS.setBounds((Main.displays.get(myID).getWidth() - width) / 2, (int) (2.5 * Main.displays.get(myID).getHeight() / 12), width, height);
		updateFPS.addKeyListener(keys);
		add(updateFPS);
		updateFPSButton = new JButton("Press To Update Your FPS Limit");
		width = 220;
		updateFPSButton.setBounds((Main.displays.get(myID).getWidth() - width) / 2, 3 * Main.displays.get(myID).getHeight() / 12, width, height);
		updateFPSButton.addKeyListener(keys);
		updateFPSButton.addActionListener(new FPSLimitListener());
		add(updateFPSButton);
	}

	private class DebugButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			Main.displays.get(myID).display.showDebugInfo = !Main.displays.get(myID).display.showDebugInfo;
			if (Main.displays.get(myID).display.showDebugInfo) {
				showOrHideDebug.setText("Hide Debug Info");
			} else {
				showOrHideDebug.setText("Show Debug Info");
			}
		}
	}

	private class FPSLimitListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			String fpsText = updateFPS.getText();
			Main.displays.get(myID).display.saveVARs();
			int numStart = 0;
			for (int i = 0; i < fpsText.length(); i++) {
				if (fpsText.charAt(i) == '1' || fpsText.charAt(i) == '2' || fpsText.charAt(i) == '3' || fpsText.charAt(i) == '4' || fpsText.charAt(i) == '5' || fpsText.charAt(i) == '6'
						|| fpsText.charAt(i) == '7' || fpsText.charAt(i) == '8' || fpsText.charAt(i) == '9') {
					numStart = i;
					break;
				}
			}
			int fps = Integer.parseInt(fpsText.substring(numStart, fpsText.length()));
			Main.displays.get(myID).displayWait = 1000 / fps - 1;
		}
	}
}
