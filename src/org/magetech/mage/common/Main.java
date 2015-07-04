/**
 * 
 */
package org.magetech.mage.common;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.io.FileUtils;
import org.magetech.mage.config.config;

import com.google.gson.Gson;

/**
 * @author Isaac
 * @version Jun 28, 2015 CopyRight: 2015 Isaac Wheeler All Rights Reserved
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Gson gson = new Gson();
		BufferedReader br = null;
		// TODO: Relocate to better location
		br = read("http://controlpanel.thepaq.org:8081/job/control%20panel/lastSuccessfulBuild/artifact/UpdateInfo.json");
		config Config = gson.fromJson(br, config.class);
		File controlPanel = new File("controlPanel.jar");
		if(controlPanel.exists()){
			controlPanel.delete();
		}
		try {
			controlPanel.createNewFile();
			FileUtils.copyURLToFile(new URL(Config.getDownloadLocation()), controlPanel);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
