/**
 * 
 */
package org.magetech.mage.common;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.io.FileUtils;
import org.magetech.mage.config.config;
import org.magetech.mage.launch.ServerInput;
import org.magetech.mage.launch.launch;

import com.google.gson.Gson;

/**
 * @author Isaac
 * @version Jun 28, 2015 CopyRight: 2015 Isaac Wheeler All Rights Reserved
 */
public class Main {

	private static config Config;
	private static String Temp;
	private static launch launch;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		if (args.length < 1) {
			System.err
					.println("error missing argument for when to check for update in hours");
			System.exit(1);
		}

		int Minutes = Integer.parseInt(args[0]);
		final int milli =  100 * Minutes;
		final Gson gson = new Gson();
		BufferedReader br = null;
		// TODO: Relocate to better location
		br = read("http://testbed.mage-tech.org:8080/job/control%20panel"
				+ "/lastSuccessfulBuild/artifact/target/UpdateInfo.json");
		Config = gson.fromJson(br, config.class);
		File controlPanel = new File("controlPanel.jar");
		if (controlPanel.exists()) {
			controlPanel.delete();
		}
		try {
			controlPanel.createNewFile();
			FileUtils.copyURLToFile(new URL(Config.getDownloadLocation()),
					controlPanel);

			if(args.length == 2){
				Temp = args[1];
			}else{
				Temp = "";
			}
			launch = new launch();
			launch.startupControlPanel(Temp);
			
			Thread thread = new Thread() {
				public void run() {
					while (true) {
						try {
							BufferedReader br = read("http://testbed.mage-tech.org"
									+ ":8080/job/control%20panel/"
									+ "lastSuccessfulBuild/artifact/target"
									+ "/UpdateInfo.json");
							config newConfig = gson.fromJson(br, config.class);

							if (!(Config.equals(newConfig))) {
								System.out.println("updateing");
								Config = newConfig;
								launch.getControlPanel().destroyForcibly();
								System.out.println("waiting");
								launch.getControlPanel().waitFor();

								File controlPanel = new File("controlPanel.jar");
								if (controlPanel.exists()) {
									controlPanel.delete();
								}

								controlPanel.createNewFile();
								FileUtils.copyURLToFile(
										new URL(Config.getDownloadLocation()),
										controlPanel);
							}
							
							
							if(!launch.getControlPanel().isAlive()){
								launch.startupControlPanel(Temp);
							}
							
							
							
							Thread.sleep(milli);

						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							System.exit(1);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							System.exit(1);
						}
					}
				}
			};
			thread.start();
			
			Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
				public void run() {
					launch.getControlPanel().destroyForcibly();
					try {
						launch.getControlPanel().waitFor();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			}, "Shutdown-thread"));
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	private static BufferedReader read(String url) {

		try {
			return new BufferedReader(new InputStreamReader(
					new URL(url).openStream()));
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
