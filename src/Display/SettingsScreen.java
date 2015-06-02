package Display;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;

import main.Main;
import Controls.PauseKeyControls;

public class SettingsScreen extends JPanel {

	JButton saveDebugInfo;
	JButton showOrHideDebug;
	JButton updateFPSButton;
	public JSlider sensitivitySlider;
	public JSlider FOVSlider;
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
		initSliders();
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
		saveDebugInfo = new JButton("Output a Debug File");
		saveDebugInfo.addActionListener(new SaveButtonListener());
		saveDebugInfo.addKeyListener(keys);
		saveDebugInfo.setBounds((Main.displays.get(myID).getWidth() - width) / 2 + (int)(width * 1.5), Main.displays.get(myID).getHeight() / 12, width, height);
		add(saveDebugInfo);
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
	
	private void initSliders() {
		initSensivitySlider();
		initFOVSlider();
	}
	
	private void initFOVSlider() {
		FOVSlider = new JSlider();
		FOVSlider.setMaximum(10);
		FOVSlider.setMinimum(0);
		FOVSlider.setValue(1);
		FOVSlider.setToolTipText("Sensitivity Slider");
		FOVSlider.setMajorTickSpacing(2);
		Hashtable labelTable = new Hashtable();
		labelTable.put( 10, new JLabel("Quake Pro!") );
		labelTable.put( 0, new JLabel("Pretty Much Blind") );
		FOVSlider.setPaintLabels(true);
		FOVSlider.setLabelTable( labelTable );
		FOVSlider.setOrientation(JSlider.VERTICAL);
		FOVSlider.addKeyListener(keys);
		FOVSlider.setBounds(100, 200, 120, 200);
		FOVSlider.setVisible(true);
		add(FOVSlider);
		JLabel myLabel = new JLabel();
		myLabel.setBounds(10, 255, 130, 50);
		myLabel.setText("Change FOV");
		add(myLabel);
	}
	
	private void initSensivitySlider() {
		sensitivitySlider = new JSlider();
		sensitivitySlider.setMaximum(Main.sensitivityUpper);
		sensitivitySlider.setMinimum(Main.sensitivityLower);
		sensitivitySlider.setValue((int) Main.displays.get(myID).mover.sensitivity);
		sensitivitySlider.setToolTipText("Sensitivity Slider");
		sensitivitySlider.setMajorTickSpacing(2);
		sensitivitySlider.setMinorTickSpacing(1);
		sensitivitySlider.setPaintTicks(true);
		sensitivitySlider.setPaintLabels(true);
		sensitivitySlider.setOrientation(JSlider.HORIZONTAL);
		sensitivitySlider.addKeyListener(keys);
		sensitivitySlider.setBounds(50, 100, 100, 50);
		sensitivitySlider.setVisible(true);
		add(sensitivitySlider);
		JLabel myLabel = new JLabel();
		myLabel.setBounds(50, 55, 130, 50);
		myLabel.setText("Change Sensitivity");
		add(myLabel);
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
	
	private class SaveButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			Main.displays.get(myID).display.saveVARs();			
		}
	}

	private class FPSLimitListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			String fpsText = updateFPS.getText();
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
