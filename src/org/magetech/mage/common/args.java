/**
 * 
 */
package org.magetech.mage.common;

/**
 * @author Isaac
 * @version Jun 29, 2015 CopyRight: 2015 Isaac Wheeler All Rights Reserved
 */
public class args {
	String[] args;

	public args(String[] args) {
		this.args = args;
	}

	public String toString() {
		String temp = null;
		for (String hold : args) {
			temp += hold + " ";
		}
		return temp;
	}
}
