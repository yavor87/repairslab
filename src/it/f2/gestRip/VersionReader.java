package it.f2.gestRip;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

import org.apache.log4j.Logger;

public class VersionReader {
	public static final String	VERSION = "version";

	private static Properties	properties;
	
	public static Properties getInstance() {
		return getInstance("version.properties");
	}
	
	public static Properties getInstance(String versionFile) {
		try {
	        InputStream is = new FileInputStream(versionFile);
	        return load(is);
        } catch (Exception e) {
        	Logger.getRootLogger().error("Exception in check updates: "+e);
        }
		return null;
	}
	
	public static Properties getInstance(URL url) {
		try {
	        InputStream is = url.openStream();
	        return load(is);
		} catch (Exception e) {
			Logger.getRootLogger().error("Exception in check updates: "+e);
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
