package it.f2.gestRip.control;

import it.f2.gestRip.EnvProperties;

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
	
	private Connection con = null;
	
	public Connection openConn(){
		try {
			Logger.getRootLogger().debug("Connecting DB...");
			if(con == null || con.isClosed()){
				Class.forName(EnvProperties.getInstance().getProperty(EnvProperties.DB_DRIVER)).
					newInstance();
					
				con = DriverManager.getConnection(
						EnvProperties.getInstance().getProperty(EnvProperties.DB_URL), 
						EnvProperties.getInstance().getProperty(EnvProperties.DB_USER), 
						EnvProperties.getInstance().getProperty(EnvProperties.DB_PASSW));
				
				con.setAutoCommit(false);
			}
		} catch (SQLException e) {
			Logger.getRootLogger().error("Exception in Connecting DB \n"+e+"\n");
			JOptionPane.showMessageDialog(mainFrame,
                    "Errore durante la connsessione al server: "
                    +e.getMessage(),"Errore...",JOptionPane.ERROR_MESSAGE);
			//e.printStackTrace();
		} catch (InstantiationException e) {
			Logger.getRootLogger().error("Exception in Connecting DB \n"+e+"\n");
			JOptionPane.showMessageDialog(mainFrame,
                    "Errore durante la connsessione al server: "
                    +e.getMessage(),"Errore...",JOptionPane.ERROR_MESSAGE);
			//e.printStackTrace();
		} catch (IllegalAccessException e) {
			Logger.getRootLogger().error("Exception in Connecting DB \n"+e+"\n");
			JOptionPane.showMessageDialog(mainFrame,
                    "Errore durante la connsessione al server: "
                    +e.getMessage(),"Errore...",JOptionPane.ERROR_MESSAGE);
			//e.printStackTrace();
		} catch (ClassNotFoundException e) {
			Logger.getRootLogger().error("Exception in Connecting DB \n"+e+"\n");
			JOptionPane.showMessageDialog(mainFrame,
                    "Errore durante la connsessione al server: "
                    +e.getMessage(),"Errore...",JOptionPane.ERROR_MESSAGE);
			//e.printStackTrace();
		}
		return con;
	}	
	
	public String testConn(String driver,String url,String user,String psw){
		String result = "Non Connesso";
		try {
			Logger.getRootLogger().debug("Testing Connection DB...");
			Class.forName(driver).newInstance();
				
			Connection testCon = DriverManager.getConnection(url, user, psw);
			
			Statement smtp = testCon.createStatement();
			ResultSet rs = smtp.executeQuery("select count(*) as countSchede from gestrip.schede");
			
			while (rs.next()) {
				System.out.println(rs.getInt("countSchede"));
			}
			
			rs.close();
			smtp.close();
			testCon.close();
			
			result = "Ok";
			
		} catch (SQLException e) {
			result = "Errore:"+e.getErrorCode()+":"+e.getMessage();
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
	
	public void closeConn(){
		try {
			Logger.getRootLogger().debug("Closing Connection DB...");
			if (con != null){
				if (!con.isClosed()){
					con.close();
				}
			}
		} catch (SQLException e) {
			Logger.getRootLogger().error("Exception in Closing Connection DB \n"+e+"\n");
			JOptionPane.showMessageDialog(mainFrame,
                    "Errore durante la chiusura del server: "
                    +e.getMessage(),"Errore...",JOptionPane.ERROR_MESSAGE);
			//e.printStackTrace();
		}
	}
	
	private JFrame mainFrame;
	public JFrame getMainFrame(){
		return mainFrame;
	}
	public void setMainFrame(JFrame value){
		mainFrame = value;
	}

}