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

public abstract class AbstractFileScriptExec {
	
	private static final String NEWLINE = System.getProperty("line.separator");
	private static final String PREFIX_STRING = "&PREF&"; 
	
	public abstract String getInstallFile();
	public abstract Logger getLogger();
	public abstract String getPrefix();
	
	public void execute() {
		
		getLogger().debug("Start file execution..."); //$NON-NLS-1$
		try {
			for (String instr : getInstallInstructions()) {
				try {
	                executeInstruction(instr);
                } catch (SQLException e) {
	                // TODO Auto-generated catch block
	                e.printStackTrace();
                }
			}
        } catch (IOException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
        }
		
	}
	
	private void executeInstruction(String instruction) throws SQLException {
		Connection con = CommonMetodBin.getConn();
		try {
			Statement smtp = con.createStatement();
			smtp.executeUpdate(instruction);
			smtp.close();
		} catch (SQLException e) {
			getLogger().error("Exception in executing instruction \n"+e+"\n", e); //$NON-NLS-1$
			e.printStackTrace();
			throw e;
		} finally {
			CommonMetodBin.closeConn(con);
		}
	}
	
	private List<String> getInstallInstructions() throws IOException {
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
