package it.f2.gestRip.control;

import it.f2.gestRip.EnvProperties;
import it.f2.gestRip.model.BinRelease;
import it.f2.gestRip.ui.messages.Messages;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.apache.log4j.Logger;

public class CommonMetodBin {
	
	private CommonMetodBin(){
	}
	
	/* Singleton */
	private static CommonMetodBin instance;
	
	public static CommonMetodBin getInstance() {
		if (CommonMetodBin.instance == null)
			CommonMetodBin.instance = new CommonMetodBin();
		return instance;
	}
	
	//private Connection con = null;
	
	public static Connection getConn(){
		Connection con = null;
		try {
			Logger.getRootLogger().debug("Connecting DB...");
			//if(con == null || con.isClosed()){
			if(EnvProperties.getInstance().getProperty(EnvProperties.DB_ISEMBEDDED).endsWith("S")){
				Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();		
//				con = DriverManager.getConnection("jdbc:derby:"+EnvProperties.getInstance().getProperty(EnvProperties.DB_DERBYDIR)+System.getProperty("file.separator")+"gestrip");
				con = DriverManager.getConnection("jdbc:derby:db/derby/gestrip");
				con.setAutoCommit(false);
			}else{
				Class.forName(EnvProperties.getInstance().getProperty(EnvProperties.DB_DRIVER)).newInstance();			
				con = DriverManager.getConnection(
						EnvProperties.getInstance().getProperty(EnvProperties.DB_URL), 
						EnvProperties.getInstance().getProperty(EnvProperties.DB_USER), 
						EnvProperties.getInstance().getProperty(EnvProperties.DB_PASSW));
				con.setAutoCommit(false);
			}
			//}
		} catch (SQLException e) {
			Logger.getRootLogger().error("Exception in Connecting DB \n"+e+"\n");
			JOptionPane.showMessageDialog(getInstance().mainFrame,
					Messages.getString("Core.connectionErrorMsg") + " "
                    +e.getMessage(),Messages.getString("Core.connectionError"),JOptionPane.ERROR_MESSAGE);
			//e.printStackTrace();
		} catch (InstantiationException e) {
			Logger.getRootLogger().error("Exception in Connecting DB \n"+e+"\n");
			JOptionPane.showMessageDialog(getInstance().mainFrame,
					Messages.getString("Core.connectionErrorMsg") + " "
                    +e.getMessage(),Messages.getString("Core.connectionError"),JOptionPane.ERROR_MESSAGE);
			//e.printStackTrace();
		} catch (IllegalAccessException e) {
			Logger.getRootLogger().error("Exception in Connecting DB \n"+e+"\n");
			JOptionPane.showMessageDialog(getInstance().mainFrame,
					Messages.getString("Core.connectionErrorMsg") + " "
                    +e.getMessage(),Messages.getString("Core.connectionError"),JOptionPane.ERROR_MESSAGE);
			//e.printStackTrace();
		} catch (ClassNotFoundException e) {
			Logger.getRootLogger().error("Exception in Connecting DB \n"+e+"\n");
			JOptionPane.showMessageDialog(getInstance().mainFrame,
					Messages.getString("Core.connectionErrorMsg") + " "
                    +e.getMessage(),Messages.getString("Core.connectionError"),JOptionPane.ERROR_MESSAGE);
			//e.printStackTrace();
		}
		return con;
	}
	
	public String testServerConn(String driver,String url,String user,String psw){
		String result = Messages.getString("Core.notConnected");
		try {
			Logger.getRootLogger().debug("Testing Connection DB...");
			Class.forName(driver).newInstance();
				
			Connection testCon = DriverManager.getConnection(url, user, psw);
			
			Statement smtp = testCon.createStatement();
			ResultSet rs = smtp.executeQuery("select count(*) as countSchede from schede");
			
			while (rs.next()) {
				System.out.println(rs.getInt("countSchede"));
			}
			
			rs.close();
			smtp.close();
			testCon.close();
			
			result = "Ok";
			
		} catch (SQLException e) {
			result = Messages.getString("Core.connectionErrorMsg")+e.getErrorCode()+":"+e.getMessage();
		} catch (InstantiationException e) {
			result = e.getMessage();
		} catch (IllegalAccessException e) {
			result = e.getMessage();
		} catch (ClassNotFoundException e) {
			result = e.getMessage();
		} catch (Exception e) {
			result = e.getMessage();
		}
		return result;
	}
	
	public String testEmbConn(){
		String result = Messages.getString("Core.notConnected");
		try {
			Logger.getRootLogger().debug("Testing Connection Embedded DB...");
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();
				
			Connection testCon = DriverManager.getConnection( "jdbc:derby:db/derby/gestrip");
			
			Statement smtp = testCon.createStatement();
			ResultSet rs = smtp.executeQuery("select count(*) as countSchede from schede");
			
			while (rs.next()) {
				System.out.println(rs.getInt("countSchede"));
			}
			
			rs.close();
			smtp.close();
			testCon.close();
			
			result = "Ok";
			
		} catch (SQLException e) {
			result = Messages.getString("Core.connectionErrorMsg")+e.getErrorCode()+":"+e.getMessage();
			e.printStackTrace();
		} catch (InstantiationException e) {
			result = e.getMessage();
		} catch (IllegalAccessException e) {
			result = e.getMessage();
		} catch (ClassNotFoundException e) {
			result = e.getMessage();
		} catch (Exception e) {
			result = e.getMessage();
		}
		return result;
	}
	
	public static void closeConn(Connection con){
		try {
			Logger.getRootLogger().debug("Closing Connection DB...");
			if (con != null){
				if (!con.isClosed()){
					con.commit();
					con.close();
				}
			}
		} catch (SQLException e) {
			Logger.getRootLogger().error("Exception in Closing Connection DB \n"+e+"\n");
			JOptionPane.showMessageDialog(getInstance().mainFrame,
					Messages.getString("Core.connCloseErr") + " "
                    +e.getMessage(),Messages.getString("Core.connectionErrorMsg"),JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}
	
	private JFrame mainFrame;
	public JFrame getMainFrame(){
		return mainFrame;
	}
	public void setMainFrame(JFrame value){
		mainFrame = value;
	}
	
	public enum CheckStatus {
		NOT_CHECKED,
		LAST_UPDATE,
		NEW_UPDATE;
	}
	private CheckStatus statusUpdate = CheckStatus.NOT_CHECKED;

	/**
     * @return the statusUpdate
     */
    public CheckStatus getStatusUpdate() {
    	return statusUpdate;
    }

	/**
     * @param statusUpdate the statusUpdate to set
     */
    public void setStatusUpdate(CheckStatus statusUpdate) {
    	this.statusUpdate = statusUpdate;
    }

	private BinRelease actualRelease = null;

	/**
     * @return the lastRelease
     */
    public BinRelease getActualRelease() {
    	return actualRelease;
    }

	/**
     * @param lastRelease the lastRelease to set
     */
    public void setActualRelease(BinRelease actualRelease) {
    	this.actualRelease = actualRelease;
    }
    
    private BinRelease currentRelease = null;

	/**
     * @return the currentRelease
     */
    public BinRelease getCurrentRelease() {
    	return currentRelease;
    }

	/**
     * @param currentRelease the currentRelease to set
     */
    public void setCurrentRelease(BinRelease currentRelease) {
    	this.currentRelease = currentRelease;
    }
    
	

}
