package org.magetech.mage.launch;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

/**
 * Handles server input thread
 * 
 * @author Isaac Wheeler
 * @version 7:55:30 PM
 * CopyRight: 2015 Isaac Wheeler All Rights Reserved
 */
public class ServerInput implements Runnable {

	/** mc server process */
	private Process p;
	/** writes to the server process */
	private BufferedWriter writer;
	/** msg for sending to MC server */
	private static String msg;

	/**
	 * @param msg
	 *            the msg to set
	 */
	public static void setMsg(String msg1) {
		msg = msg1;
	}

	/**
	 * Constructor for Server input
	 * 
	 * @param p
	 *            mc server process
	 */
	public ServerInput(Process p) {
		this.p = p;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	public void run() {
		try {
			writer = new BufferedWriter(new OutputStreamWriter(
					p.getOutputStream()));
			while (p.isAlive()) {
				String input = null;
				if ((input = msg) != null) {
					writer.write(input);
					writer.newLine();
					writer.flush();
					msg = null;
				}
				Scanner scan = new Scanner(System.in);
				if(scan.hasNextLine()){
					setMsg(scan.nextLine());
				}
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
