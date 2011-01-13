package net.sf.repairslab.util.so;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.Properties;

/**
 * La classe fornisce funzionalita' di ritrovamento delle variabili d'ambiente 
 * su sistemi operativi Windows e Unix.
 * @author Antonio Vivalda
 * @version 1.0.0.0
 */
public class EnvVarRetriever {
	private Properties envProps;

	private static EnvVarRetriever instance = new EnvVarRetriever();

	private EnvVarRetriever() {
		this.loadVars();
	}

	/**
	 * Il metodo carica all'interno della classe Properties tutti i valori
	 * delle variabili di ambiente presenti nel Sistema.
	 * @return 
	 * @throws java.lang.Exception
	 */
	private void loadVars() {
		try {
			Process p = null;
			this.envProps = new Properties();
			Runtime r = Runtime.getRuntime();
			String OS = System.getProperty("os.name").toLowerCase();
			if (OS.indexOf("windows 9") > -1) {
				p = r.exec("command.com /c set");
			} else if ((OS.indexOf("nt") > -1) || (OS.indexOf("NT") > -1)
					|| (OS.indexOf("windows 2000") > -1)
					|| (OS.indexOf("windows 2003") > -1)
					|| (OS.indexOf("windows xp") > -1)) {
				p = r.exec("cmd.exe /c set");
			} else {
				// our last hope, we assume Unix
				p = r.exec("env");
			}
			BufferedReader br = new BufferedReader(new InputStreamReader(p
					.getInputStream()));
			String line;
			while ((line = br.readLine()) != null) {
				int idx = line.indexOf('=');
				String key = line.substring(0, idx);
				String value = line.substring(idx + 1);
				this.envProps.setProperty(key, value);
			}
		} catch (Throwable g) {
			g.printStackTrace();
		}
	}

	/**
	 * Il metodo ritorna le coppie chiave-valore delle variabili
	 * d'ambiente del sistema.
	 * @return 
	 */
	public Properties getEnvVars() {
		if (this.envProps == null)
			this.loadVars();
		return this.envProps;
	}

	/**
	 * Il metodo ricarica le variabili d'ambiente presenti nel sistema
	 */
	public void reload() {
		this.loadVars();
	}

	/**
	 * Il metodo ritorna una stringa contenente il valore della variabile di ambiente
	 * varName passata come parametro.
	 * Se la variabile nn viene trovata, il metodo ritorna null.
	 * @param varName - nome della variabile di ambiente di cui avere il valore.
	 * @return - un oggetto string.
	 * @throws java.lang.Exception
	 */
	public String getEnvValue(String varName) {
		Enumeration<?> names = this.getEnvVars().propertyNames();
		for (Enumeration<?> e = names; e.hasMoreElements();) {
			String name = (String) e.nextElement();
			if (name.equals(varName))
				return envProps.getProperty(name);
		}
		return null;
	}

	/**
	 * Ritorna l'istanza singleton di classe
	 * @return 
	 */
	public static EnvVarRetriever getInstance() {
		if (EnvVarRetriever.instance == null)
			EnvVarRetriever.instance = new EnvVarRetriever();
		return EnvVarRetriever.instance;
	}

	/**
	 * Ritorna una stringa contenente le associazioni chiave-valore 
	 * delle variabili di ambiente
	 * @return 
	 */
	public String toString() {
		String result = "";

		Enumeration<?> names = this.getEnvVars().propertyNames();
		for (Enumeration<?> e = names; e.hasMoreElements();) {
			String name = (String) e.nextElement();
			String value = envProps.getProperty(name);
			result = result + name + "=" + value + "\n";
		}
		return result;
	}

	public static void main(String[] args) throws Exception {
		// Usage of GetEnv Class... 
		System.out.println(EnvVarRetriever.getInstance().toString());
	}

}
