package org.magetech.mage.config;

/**
 * config for checking if there are updates
 * @author Isaac Wheeler
 * @version Jun 28, 2015
 * CopyRight: 2015 Isaac Wheeler All Rights Reserved
 */
public class config {
	/** the version number of the controlPanel */
	private String versionNumber;
	/** the DownloadLocation of the controlPanel */
	private String DownloadLocation;
	

	/**
	 * Constructor for config
	 * @param args
	 * @param DownloadLocation
	 */
	public config(String args, String DownloadLocation){
		this.versionNumber = args;
		this.DownloadLocation = DownloadLocation;
	}

	/**
	 * Getter for versionNumber
	 * @return the versionNumber
	 */
	public String getVersionNumber() {
		return versionNumber;
	}

	/**
	 * Setter for versionNumber
	 * @param versionNumber the versionNumber to set
	 */
	public void setVersionNumber(String versionNumber) {
		this.versionNumber = versionNumber;
	}

	/**
	 * Getter for downloadLocation
	 * @return the downloadLocation
	 */
	public String getDownloadLocation() {
		return DownloadLocation;
	}

	/**
	 * Setter for downloadLocation
	 * @param downloadLocation the downloadLocation to set
	 */
	public void setDownloadLocation(String downloadLocation) {
		DownloadLocation = downloadLocation;
	}
	
	/**
	 * checks if two configs are equal to each other
	 * @param newConfig the other config
	 * @return true if equal false if not
	 */
	public boolean equals(config newConfig){
		if(versionNumber.equals(newConfig.versionNumber)){
			if(DownloadLocation.equals(newConfig.DownloadLocation)){
				return true;
			}
		}
		return false;
	}
}
