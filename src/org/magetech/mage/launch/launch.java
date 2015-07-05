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
	private ServerInput serverInput;

	public void startupControlPanel(String args) throws IOException,
			InterruptedException {
		File controlPanel = new File("controlPanel.jar");
		this.controlPanel = Runtime.getRuntime().exec(
				"java -jar " + controlPanel.getAbsolutePath() + args);

		// server output thread
		new Thread(new ServerOutput(this.controlPanel)).start();
		// server input thread
		serverInput = new ServerInput(this.controlPanel);
		new Thread(new ServerInput(this.controlPanel)).start();
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

	/**
	 * Getter for serverInput
	 * 
	 * @return the serverInput
	 */
	public ServerInput getServerInput() {
		return serverInput;
	}

	/**
	 * Setter for serverInput
	 * 
	 * @param serverInput
	 *            the serverInput to set
	 */
	public void setServerInput(ServerInput serverInput) {
		this.serverInput = serverInput;
	}

}
