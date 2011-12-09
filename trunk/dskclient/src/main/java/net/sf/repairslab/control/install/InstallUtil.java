package net.sf.repairslab.control.install;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;

import org.apache.log4j.Logger;

public class InstallUtil {
	
	static private Logger  logger = Logger.getLogger(InstallUtil.class.getName());
	
	private boolean isEmbedded;
	private JProgressBar progress;
	private JLabel instruction;
	private JTextArea logArea;
	private boolean installComplete = false;
	private int installPercent = 0;
	private String installInstruction = "Starting Metadata Installation...";
	
	public InstallUtil(boolean isEmbedded, JProgressBar progress, JLabel instruction, JTextArea logArea) {
		this.isEmbedded = isEmbedded;
		this.progress = progress;
		this.instruction = instruction;
		this.logArea = logArea;
	}
	
	public void run() throws Exception {
		
		if (isEmbedded) {
			logger.debug("Embedded installazion");
			
		} else {
			logger.debug("Server installazion");
			MySqlFileScriptExec mySqlFileScriptExec = new MySqlFileScriptExec();
			
			try {
				List<String> instInstrs = mySqlFileScriptExec.getInstallInstructions();
				float increment = 100 / instInstrs.size();
				float percent = 0;
				for (String instr : instInstrs) {
					try {
						logger.debug("Script in execution: " + instr);
						percent = percent + increment;
						instruction.setText(instr);
						logArea.append(instr + "\n\n");
						progress.setValue(Math.round(percent));
						mySqlFileScriptExec.executeInstruction(instr);
	                } catch (SQLException e) {
		                logger.error(e+"\n", e); 
		                throw e;
	                }
				}
	        } catch (IOException e) {
	        	logger.error(e+"\n", e); 
	        	throw e;
	        } finally {
	        	setInstallComplete(true);
	        }
		}
	}

	/**
     * @return the installComplete
     */
    public boolean isInstallComplete() {
    	return installComplete;
    }

	/**
     * @param installComplete the installComplete to set
     */
    public void setInstallComplete(boolean installComplete) {
    	this.installComplete = installComplete;
    }

	/**
     * @return the installPercent
     */
    public int getInstallPercent() {
    	return installPercent;
    }

	/**
     * @param installPercent the installPercent to set
     */
    public void setInstallPercent(int installPercent) {
    	this.installPercent = installPercent;
    }

	/**
     * @return the installInstruction
     */
    public String getInstallInstruction() {
    	return installInstruction;
    }

	/**
     * @param installInstruction the installInstruction to set
     */
    public void setInstallInstruction(String installInstruction) {
    	this.installInstruction = installInstruction;
    }
	
	
}
