package net.sf.repairslab.control.install;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import net.sf.repairslab.control.CommonMetodBin;

import org.apache.log4j.Logger;

/**
 * Abstract class for execution of sql files
 * @author Fabrizio Ferraiuolo
 * 24/ago/2011
 * 16:05:03
 */
public abstract class AbstractFileScriptExec {
	
	private static final String NEWLINE = System.getProperty("line.separator");
	private static final String PREFIX_STRING = "&PREF&"; 
	
	/**
	 * String with install files
	 * @author Fabrizio Ferraiuolo 24/ago/2011 16:06:29
	 * @return 
	 */
	public abstract String getInstallFile();
	
	/**
	 * Logger class
	 * @author Fabrizio Ferraiuolo 24/ago/2011 16:06:51
	 * @return 
	 */
	public abstract Logger getLogger();
	
	/**
	 * Prefix to replace to PREFIX_STRING
	 * @author Fabrizio Ferraiuolo 24/ago/2011 16:07:03
	 * @return 
	 */
	public abstract String getPrefix();
	
	/**
	 * Execution instruction
	 * @author Fabrizio Ferraiuolo 24/ago/2011 16:10:32
	 * @param instruction
	 * @throws SQLException 
	 */
	public void executeInstruction(String instruction) throws SQLException {
		Connection con = CommonMetodBin.getConn();
		try {
			Statement smtp = con.createStatement();
			smtp.executeUpdate(instruction);
			smtp.close();
		} catch (SQLException e) {
			getLogger().error("Exception in executing instruction \n"+e+"\n", e); //$NON-NLS-1$
//			e.printStackTrace();
			throw e;
		} finally {
			CommonMetodBin.closeConn(con);
		}
	}
	
	/**
	 * Return the list of instruction in sql file
	 * @author Fabrizio Ferraiuolo 24/ago/2011 16:11:06
	 * @return
	 * @throws IOException 
	 */
	public List<String> getInstallInstructions() throws IOException {
        FileReader fr = new FileReader(new File(getInstallFile()));
        
		String fileContent = new String();
		
		BufferedReader in = new BufferedReader(fr);
		String line = new String();
		while ((line = in.readLine()) != null)
			fileContent += line + NEWLINE;
		in.close();
	        
		List<String> result = new LinkedList<String>();
		for (String instr : fileContent.split(";")) {
			instr = instr.replace("\n", " ").replace("\r", " ");
			if (instr.trim().length() > 0) {
				instr = instr.replace(PREFIX_STRING, getPrefix());
				result.add(instr.trim());
			}
		}
		
		return result;
	}
	
}
