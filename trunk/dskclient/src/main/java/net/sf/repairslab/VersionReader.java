package net.sf.repairslab;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * Singleton to read from file version.properties
 * @author Fabrizio Ferraiuolo
 * 13/gen/2011
 * 15.29.00
 * Copyright (c)2009 Decisyon S.r.l.
 */
public class VersionReader {
	
	static private Logger  logger = Logger.getLogger(VersionReader.class.getName());
	
	public static final String	VERSION = "version";
	public static final String	RELEASE = "release";

	private static Properties	properties;
	
	public static Properties getInstance() {
		return getInstance("version.properties");
	}
	
	public static Properties getInstance(String versionFile) {
		try {
	        InputStream is = new FileInputStream(versionFile);
	        return load(is);
        } catch (Exception e) {
        	logger.error("Exception in check updates: "+e);
        }
		return null;
	}
	
	public static Properties getInstance(URL url) {
		try {
	        InputStream is = url.openStream();
	        return load(is);
		} catch (Exception e) {
			logger.error("Exception in check updates: "+e);
		}
		return null;
	}
	
	private static Properties load(InputStream is) throws IOException {
		VersionReader.properties = new Properties();
		VersionReader.properties.load(is);
		return VersionReader.properties;
	}
	
	private VersionReader() {}
}
