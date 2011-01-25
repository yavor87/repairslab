package net.sf.repairslab;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * Read from file version.properties
 * @author Fabrizio Ferraiuolo
 * 13/gen/2011
 * 15.29.00
 * Copyright (c)2009 Decisyon S.r.l.
 */
public class VersionReader {
	
	static private Logger  logger = Logger.getLogger(VersionReader.class.getName());
	
	private static final String DEFAULT_VERSION_FILE = "version.properties";
	private static final String	VERSION_PROPERTY = "version";
	private static final String	RELEASE_PROPERTY = "release";
	private static final String	APPNAME_PROPERTY = "appname";
	
	private String version;
	private int release;
	private String appname;
	private Properties properties;
	
	public VersionReader() {
		InputStream is = getClass().getResourceAsStream(DEFAULT_VERSION_FILE);
		load(is);
	}
	
	public VersionReader(String urlString) {
        try {
        	URL url = new URL(EnvConstants.LAST_REVISION_CHECK_URL);
        	InputStream is = url.openStream();
	        load(is);
        } catch (IOException e) {
        	logger.error("Exception in load version file: "+e, e);
        	setVersion("");
        	setRelease(0);
		}
	}
	
	private void load(InputStream is) {
        try {
        	properties = new Properties();
	        properties.load(is);
	        setVersion(properties.getProperty(VERSION_PROPERTY));
	        setAppname(properties.getProperty(APPNAME_PROPERTY));
	        String releaseString = properties.getProperty(RELEASE_PROPERTY);
	        try {
	        	setRelease(Integer.parseInt(releaseString));
	        } catch (NumberFormatException e) {
	        	logger.error("Exception in load version file: "+e, e);
	        	setRelease(0);
	        }
        } catch (IOException e) {
        	logger.error("Exception in load version file: "+e, e);
        	setVersion("");
        	setRelease(0);
		} catch (NullPointerException e) {
			logger.error("Exception in load version file: "+e, e);
			setVersion("");
        	setRelease(0);
		}
	}

	/**
     * @return the version
     */
    public String getVersion() {
    	return this.version;
    }

	/**
     * @return the release
     */
    public int getRelease() {
    	return this.release;
    }

	/**
     * @param version the version to set
     */
    public void setVersion(String version) {
    	this.version = version;
    }

	/**
     * @param release the release to set
     */
    public void setRelease(int release) {
    	this.release = release;
    }

	/**
     * @return the appname
     */
    public String getAppname() {
    	return appname;
    }

	/**
     * @param appname the appname to set
     */
    public void setAppname(String appname) {
    	this.appname = appname;
    }

}
