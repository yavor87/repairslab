package it.f2.gestRip;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * Questa classe ha patern Singleton e valorizza le costanti e
 * propriet� da utilizzare in tutta l'applicazione.
 * 
 * @author ferraf01
 *
 */

public class EnvProperties {

//	public static String FILE_SEPARETOR = System.getProperty("file.separator");
//	public static String LINE_SEPARETOR = System.getProperty("line.separator");
	public static String PROP_FILE_NAME = "f2.propFileName";
	public static String DEFAULT_LANGUAGE = "f2.language.file";
	public static String LOOK = "f2.lookandfeel";
	public static String WIDTH = "f2.width";
	public static String HEIGHT = "f2.height";
	public static String FILELOGO = "f2.filelogo.file";
	public static String APPNAME = "f2.app.name";
	public static String DB_DRIVER = "f2.db.driver";
	public static String DB_URL = "f2.db.url";
	public static String DB_USER = "f2.db.user";
	public static String DB_PASSW = "f2.db.passw";
	public static String DB_ISEMBEDDED = "f2.db.embedded";
	public static String DB_DERBYDIR = "f2.db.derbyDir";
	public static String START_CMD = "f2.start.cmd";
	public static String SERVER_PROCESS = "f2.server.process";
	public static String INDIRIZZO = "f2.indirizzo";
	public static String INFOCLIENTE = "f2.infocliente";
	public static String DOPPIACOPIA = "f2.doppiaCopia";
	public static String LANGUAGE = "f2.language";
	public static String LOCALE = "f2.locale";
	public static String PREFIX_NUM = "f2.prefix.num";
	public static String JASPER = "f2.jasper";

	/* Singleton */
	private Properties properties;
	private static EnvProperties instance;

	/**
	 * Questo metodo inizializza tutte le proprietà se non è
	 * stato ancora fatto.
	 * 
	 * @return
	 */
	public Properties getProperties() {
		if (this.properties == null) {
			this.loadProperties();
		}
		return this.properties;
	}

	/**
	 * Questo metodo ritorna il valore della propriet� richiesto.
	 * 
	 * @param propName
	 * @return
	 */
	public String getProperty(String propName) {
		String result = this.getProperties().getProperty(propName);
		if(result != null){
			if (result.indexOf("\\")>0 || result.indexOf("/")>0){
				return result.replace("\\", System.getProperty("file.separator"));
			}
		}else{
			this.setProperty(propName, "");
			result="";
		}
		return result;
	}

	/**
	 * Questo metodo ritorna loggetto EnvProperties, nel caso in cui
	 * l'inizializzazione non sia ancora stata fatta provvede a cio.
	 * 
	 * @return
	 */
	public static EnvProperties getInstance() {
		if (EnvProperties.instance == null)
			EnvProperties.instance = new EnvProperties();
		return instance;
	}

	/**
	 * Questo metodo effettua il caricamento di tutte le proprietà
	 * sia da file di property che da proprietà di sistema.
	 */
	public void loadProperties() {
		this.properties = new Properties();
		try {
			this.properties.put("f2.propFileName", "conf" + System.getProperty("file.separator") + "f2.properties");
			this.properties.load(new FileInputStream(this.getProperty(EnvProperties.PROP_FILE_NAME)));

		} catch (IOException e) {
			Logger.getRootLogger().error("Exception in loadProperties \n"+e+"\n");
		}
	}

	/**
	 * Questo metodo setta il valore di una chiave.
	 * 
	 * @param key
	 * @param value
	 */
	public void setProperty(String key, String value) {
		this.properties.setProperty(key, value);
	}

	/**
	 * Questo metodo effettua il salvataggio di tutte le propriet�
	 * sul file di property.
	 */
	public void saveFileProperty() {
		try {
			FileOutputStream fileProp = new FileOutputStream(this.getProperty(EnvProperties.PROP_FILE_NAME));
			this.properties.store(fileProp, null);
		} catch (FileNotFoundException fnf) {
			//fnf.printStackTrace();
			Logger.getRootLogger().error("Exception in saveFileProperty \n"+fnf+"\n");
		} catch (IOException io) {
			//io.printStackTrace();
			Logger.getRootLogger().error("Exception in saveFileProperty \n"+io+"\n");
		}
	}

	/**
	 * Costruttore della classe.
	 */
	private EnvProperties() {
	}

	/**
	 * Questo metodo pulisce, cancella tutti i valori
	 * già settati.
	 */
	public void clear() {
		this.properties = null;
	}

	/**
	 * Questo metodo stampa a video tutti i valori settati.
	 */
	public void println() {
		Properties property = EnvProperties.getInstance().getProperties();
		Enumeration<?> enumeration = property.propertyNames();
		while (enumeration.hasMoreElements()) {
			String propName = (String) enumeration.nextElement();
			System.out.println(propName + "=" + property.getProperty(propName));
		}
	}
	
	public static void main(String[] args) {
		EnvProperties.getInstance().println();
	}

}
