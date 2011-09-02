package net.sf.repairslab.control.install;

import org.apache.log4j.Logger;

public class InstallUtil {
	
	static private Logger  logger = Logger.getLogger(InstallUtil.class.getName());
	
	public static void installDb(boolean isEmbedded) throws Exception {
		
		if (isEmbedded) {
			logger.debug("Embedded installazion");
			
		} else {
			logger.debug("Non Embedded installazion");
			MySqlFileScriptExec mySqlFileScriptExec = new MySqlFileScriptExec();
			mySqlFileScriptExec.execute();
		}
	}
	
	
}
