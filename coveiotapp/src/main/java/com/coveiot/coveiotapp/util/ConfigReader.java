package com.coveiot.coveiotapp.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class ConfigReader {
    private static ConfigReader singleton = null;
	static{
		ConfigReader config =  new ConfigReader();
		try {
			config.loadAppProperties();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static String emailHost;
	
	public static String email_port;
	public static String email_authRequired;
	public static String email_authUsername;
	public  static String email_authPassword;
	public static String basePath;
	

	private void loadAppProperties() throws IOException{
		Properties prop = new Properties();
		String propFileName = "application.properties";
 
		InputStream inputStream=getClass().getClassLoader().getResourceAsStream(propFileName);
        

		if (inputStream != null) {
			prop.load(inputStream);//
//			this.pdfappPath = prop.getProperty("app_path");
			this.emailHost = prop.getProperty("email_host");			
			this.email_port = prop.getProperty("email_port");
			this.email_authRequired = prop.getProperty("email_authRequired");
			this.email_authUsername = prop.getProperty("email_authUsername");
			this.email_authPassword = prop.getProperty("email_authPassword");	
			this.basePath = prop.getProperty("basepath");
		} else {
			throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
		}

	}


    /* Static 'instance' method */
    public static ConfigReader getInstance() {
        if(singleton == null) {
            singleton = new ConfigReader();
        }
        return singleton;
    }
}
