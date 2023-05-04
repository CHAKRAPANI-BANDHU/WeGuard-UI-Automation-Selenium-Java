package com.weguard.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class Configuration {
	Properties pro;

	public Configuration() {
		File src = new File("./Configuration/config.properties");
		try {
			FileInputStream fis = new FileInputStream(src);
			pro = new Properties();
			pro.load(fis);
		} catch (Exception e) {
			System.out.println("Exception is" + e.getMessage());
		}
	}

	public String getChromepath() {
		String chromepath = pro.getProperty("chromepath");
		return chromepath;
	}
	
	public String getEmailConfig() {
		String emailconfig = pro.getProperty("email-config.json");
		return emailconfig;
	}
//	public String getEdgepath() {
//		String edgepath = pro.getProperty("edgepath");
//		return edgepath;
//	}
	
}
