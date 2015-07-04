/**
 * 
 */
package org.magetech.mage.launch;

import java.io.File;
import java.io.IOException;

/**
 * @author Isaac
 * @version Jun 28, 2015 CopyRight: 2015 Isaac Wheeler All Rights Reserved
 */
public class launch {
	private Process controlPanel;

	public void startupControlPanel(String args) throws IOException,
			InterruptedException {
		File controlPanel = new File("controlPanel.jar");
		ProcessBuilder builder = new ProcessBuilder("java -jar "
				+ controlPanel.getAbsolutePath() +  args);
		builder.redirectErrorStream(true);
		builder.redirectOutput();
		builder.redirectInput();
		setControlPanel(builder.start());
	}

	/**
	 * Getter for controlPanel
	 * 
	 * @return the controlPanel
	 */
	public Process getControlPanel() {
		return controlPanel;
	}

	/**
	 * Setter for controlPanel
	 * 
	 * @param controlPanel
	 *            the controlPanel to set
	 */
	public void setControlPanel(Process controlPanel) {
		this.controlPanel = controlPanel;
	}

}
