package org.magetech.mage.launch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


/**
 * handles the thread for server output
 * @author Isaac Wheeler
 * @version 7:47:34 PM
 * CopyRight: 2015 Isaac Wheeler All Rights Reserved
 */
public class ServerOutput implements Runnable {
	
	/** Mc server Process */
	private Process p;
	/** input reader for minecraft server */
	private BufferedReader input;
	
	/**
	 * Constructor for Server output
	 * @param p mc server process
	 */
	public ServerOutput(Process p){
		this.p = p;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	public void run() {
		input = new BufferedReader(new InputStreamReader(p
				.getInputStream()));
		String line = null;

		try {
			while ((line = input.readLine()) != null) {
				System.out.println(line);
			}
		} catch (IOException e) {
			System.err
					.println("IOException while trying to read output from control panel");
			e.printStackTrace();
		}

	}
}
