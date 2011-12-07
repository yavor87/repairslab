package net.sf.repairslab.control;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import net.sf.repairslab.EnvProperties;
import net.sf.repairslab.model.BinRelease;
import net.sf.repairslab.ui.messages.Messages;

import org.apache.log4j.Logger;

public class CommonMetodBin {
	
	static private Logger  logger = Logger.getLogger(CommonMetodBin.class.getName());
	static public String  MYSQL_DRIVER = "com.mysql.jdbc.Driver";
	static public String  DERBYEMBEDDED_DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
	
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
			logger.debug("Connecting DB...");
			//if(con == null || con.isClosed()){
			if(EnvProperties.getInstance().getProperty(EnvProperties.DB_ISEMBEDDED).endsWith("S")){
				Class.forName(DERBYEMBEDDED_DRIVER).newInstance();		
//				con = DriverManager.getConnection("jdbc:derby:"+EnvProperties.getInstance().getProperty(EnvProperties.DB_DERBYDIR)+System.getProperty("file.separator")+"gestrip");
				con = DriverManager.getConnection("jdbc:derby:db/derby/gestrip");
				con.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
				con.setAutoCommit(false);
			}else{
				URL u = new URL("jar:file:" + EnvProperties.getInstance().getProperty(EnvProperties.DB_CLASSPATH) + "!/");
				URLClassLoader ucl = new URLClassLoader(new URL[] { u });
				Driver d = (Driver)Class.forName(EnvProperties.getInstance().getProperty(EnvProperties.DB_DRIVER), true, ucl).newInstance();
				DriverManager.registerDriver(new DriverShim(d));
				
//				Class.forName(EnvProperties.getInstance().getProperty(EnvProperties.DB_DRIVER)).newInstance();			
				con = DriverManager.getConnection(
						EnvProperties.getInstance().getProperty(EnvProperties.DB_URL), 
						EnvProperties.getInstance().getProperty(EnvProperties.DB_USER), 
						EnvProperties.getInstance().getProperty(EnvProperties.DB_PASSW));
				con.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
				con.setAutoCommit(false);
			}
			//}
		} catch (SQLException e) {
			logger.error("SQLException in Connecting DB \n"+e+"\n", e);
			JOptionPane.showMessageDialog(getInstance().mainFrame,
					Messages.getString("Core.connectionErrorMsg") + " "
                    +e.getMessage(),Messages.getString("Core.connectionError"),JOptionPane.ERROR_MESSAGE);
			//e.printStackTrace();
		} catch (InstantiationException e) {
			logger.error("InstantiationException in Connecting DB \n"+e+"\n",e);
			JOptionPane.showMessageDialog(getInstance().mainFrame,
					Messages.getString("Core.connectionErrorMsg") + " "
                    +e.getMessage(),Messages.getString("Core.connectionError"),JOptionPane.ERROR_MESSAGE);
			//e.printStackTrace();
		} catch (IllegalAccessException e) {
			logger.error("IllegalAccessException in Connecting DB \n"+e+"\n", e);
			JOptionPane.showMessageDialog(getInstance().mainFrame,
					Messages.getString("Core.connectionErrorMsg") + " "
                    +e.getMessage(),Messages.getString("Core.connectionError"),JOptionPane.ERROR_MESSAGE);
			//e.printStackTrace();
		} catch (ClassNotFoundException e) {
			logger.error("ClassNotFoundException in Connecting DB \n"+e+"\n", e);
			JOptionPane.showMessageDialog(getInstance().mainFrame,
					Messages.getString("Core.connectionErrorMsg") + " "
                    +e.getMessage(),Messages.getString("Core.connectionError"),JOptionPane.ERROR_MESSAGE);
			//e.printStackTrace();
		} catch (MalformedURLException e) {
			logger.error("MalformedURLException in Connecting DB \n"+e+"\n", e);
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
			logger.debug("Testing Connection DB...");
			
//			URL u = new URL("jar:file:C:\\Users\\fferraiu\\Downloads\\firebird\\Jaybird-2.1.6JDK_1.6\\jaybird-full-2.1.6.jar!/");
//			URLClassLoader ucl = new URLClassLoader(new URL[] { u });
//			Driver d = (Driver)Class.forName(driver, true, ucl).newInstance();
//			DriverManager.registerDriver(new DriverShim(d));
			Connection testCon = DriverManager.getConnection(url, user, psw);

			Statement smtp = testCon.createStatement();
			smtp.executeUpdate("CREATE TABLE TEST (id VARCHAR(100) NOT NULL) ENGINE = InnoDB");
			smtp.close();

			Statement smtp2 = testCon.createStatement();
			smtp2.executeUpdate("DROP TABLE TEST");
			smtp2.close();
			
			testCon.close();
			
			result = "Ok";
			
		} catch (SQLException e) {
			logger.warn("SQLException in Connecting DB \n"+e+"\n", e);
			result = Messages.getString("Core.connectionErrorMsg")+e.getErrorCode()+":"+e.getMessage();
//		} catch (InstantiationException e) {
//			logger.warn("InstantiationException in Connecting DB \n"+e+"\n", e);
//			result = e.getMessage();
//		} catch (IllegalAccessException e) {
//			logger.warn("IllegalAccessException in Connecting DB \n"+e+"\n", e);
//			result = e.getMessage();
//		} catch (ClassNotFoundException e) {
//			logger.warn("ClassNotFoundException in Connecting DB \n"+e+"\n", e);
//			result = e.getMessage();
		} catch (Exception e) {
			logger.warn("Exception in Connecting DB \n"+e+"\n", e);
			result = e.getMessage();
		}
		return result;
	}
	
	public String getInstalledMetadata() {
		String result = "";
		Connection con = getConn();
		try {
			logger.debug("Get Current metadata...");
			
			Statement smtp = con.createStatement();
			ResultSet rs = smtp.executeQuery(QryUtil.QRY_METADATA_VERSION);
			while (rs.next())
				result = rs.getString(1);
			
			smtp.close();
			
		} catch (SQLException e) {
			logger.warn("SQLException in Connecting DB \n"+e+"\n", e);
		} finally {
			closeConn(con);
		}
		return result;
	}
	
	public String testEmbConn(){
		String result = Messages.getString("Core.notConnected");
		try {
			logger.debug("Testing Connection Embedded DB...");
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
			logger.debug("Closing Connection DB...");
			if (con != null){
				if (!con.isClosed()){
					con.commit();
					con.close();
				}
			}
		} catch (SQLException e) {
			logger.error("Exception in Closing Connection DB \n"+e+"\n", e);
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
