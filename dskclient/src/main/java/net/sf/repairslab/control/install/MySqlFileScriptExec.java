package net.sf.repairslab.control.install;

import net.sf.repairslab.EnvProperties;

import org.apache.log4j.Logger;

/**
 * TODO Comment for class "MySql" must be completed
 * @author Fabrizio Ferraiuolo
 * 22/ago/2011
 * 11:18:49
 */
public class MySqlFileScriptExec extends AbstractFileScriptExec {
	
	
	public MySqlFileScriptExec() {
		
	}
	
	@Override
    public String getInstallFile() {
	    return "net/sf/repairslab/install/mysql/install.sql";
    }
	@Override
    public Logger getLogger() {
	    return Logger.getLogger(MySqlFileScriptExec.class.getName());
    }

	@Override
    public String getPrefix() {
	    return EnvProperties.getInstance().getProperty(EnvProperties.DB_TABLE_PREFIX);
    }
	
}
