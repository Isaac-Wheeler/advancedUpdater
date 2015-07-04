/**
 * 
 */
package org.magetech.mage.launch;

import java.io.IOException;

import org.magetech.mage.common.args;

/**
 * @author Isaac
 * @version Jun 28, 2015
 * CopyRight: 2015 Isaac Wheeler All Rights Reserved
 */
public class launch {
	private Process controlPanel;
	
	public void startupControlPanel(args args) throws IOException, InterruptedException{
		ProcessBuilder builder = new ProcessBuilder("java -jar controlPanel.jar" + args);
		builder.redirectErrorStream(true);
		builder.redirectInput();
		builder.redirectOutput();
		setControlPanel(builder.start());
	}

	/**
	 * Getter for controlPanel
	 * @return the controlPanel
	 */
	public Process getControlPanel() {
		return controlPanel;
	}

	/**
	 * Setter for controlPanel
	 * @param controlPanel the controlPanel to set
	 */
	public void setControlPanel(Process controlPanel) {
		this.controlPanel = controlPanel;
	}
	
}
