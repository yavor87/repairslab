package net.sf.repairslab.control.install;

import net.sf.repairslab.EnvProperties;

import org.apache.log4j.Logger;

/**
 * MySql sql file instructions
 * @author Fabrizio Ferraiuolo
 * 24/ago/2011
 * 16:11:45
 */
public class MySqlFileScriptExec extends AbstractFileScriptExec {
	
	/**
	 * Costruttore della classe MySqlFileScriptExec.java
	 * @author Fabrizio Ferraiuolo
	 * 24/ago/2011
	 * 16:12:16
	 */
	public MySqlFileScriptExec() {
		
	}
	
	/**
	 * @see net.sf.repairslab.control.install.AbstractFileScriptExec#getInstallFile()
	 */
	@Override
    public String getInstallFile() {
	    return "net/sf/repairslab/metadata/install/mysql/install.sql";
    }
	
	/**
	 * @see net.sf.repairslab.control.install.AbstractFileScriptExec#getLogger()
	 */
	@Override
    public Logger getLogger() {
	    return Logger.getLogger(MySqlFileScriptExec.class.getName());
    }

	/**
	 * @see net.sf.repairslab.control.install.AbstractFileScriptExec#getPrefix()
	 */
	@Override
    public String getPrefix() {
	    return EnvProperties.getInstance().getProperty(EnvProperties.DB_TABLE_PREFIX);
    }
	
}
