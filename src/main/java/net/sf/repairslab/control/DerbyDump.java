package net.sf.repairslab.control;

import java.io.File;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import net.sf.repairslab.EnvProperties;
import net.sf.repairslab.util.ZipUtil;

/**
 * Classe per la gestiorne del backup e restore dei dati del database di default derby
 * @author Fabrizio Ferraiuolo
 * 08/nov/2010
 * 15.00.01
 * Copyright (c)2009 Decisyon S.r.l.
 */
public class DerbyDump {
	
	private static String TEMP_DIR_PATH =  System.getProperty("java.io.tmpdir") + "RepairsLab";
	
	/**
	 * Creazione zip con il db
	 * @author Fabrizio Ferraiuolo 08/nov/2010 15.00.05
	 * @param zipFilePath 
	 */
	public static void exportInZipFile(String zipFilePath) throws Exception {
		File tempDir = new File(TEMP_DIR_PATH);
		export(tempDir.getPath());
		// Zip folfer
		ZipUtil.zipFolder(tempDir.getPath(), zipFilePath);
		deleteDir(tempDir);
	}

	/**
	 * Creazione folder con il db
	 * @author Fabrizio Ferraiuolo 08/nov/2010 15.00.07
	 * @param exportFilePath 
	 */
	public static void export(String exportFilePath) throws Exception{
		Connection con = CommonMetodBin.getConn();
		try {
			CallableStatement smtp = con.prepareCall("CALL SYSCS_UTIL.SYSCS_BACKUP_DATABASE(?)");
			smtp.setString(1, exportFilePath);
			smtp.execute();
			smtp.close();
		} catch (SQLException e) {
			throw e;
		}
		CommonMetodBin.closeConn(con);
		
		
	}
	
	/**
	 * Cancellazione directory a cascata
	 * @author Fabrizio Ferraiuolo 08/nov/2010 15.00.10
	 * @param dir
	 * @return 
	 */
	public static boolean deleteDir(File dir) {
		if (dir.isDirectory()) {
			String[] children = dir.list();
			for (int i = 0; i < children.length; i++) {
				boolean success = deleteDir(new File(dir, children[i]));
				if (!success) {
					return false;
				}
			}
		}
		return dir.delete();
	}
	
	/**
	 * Restore di un db da una direcrory
	 * @author Fabrizio Ferraiuolo 08/nov/2010 15.17.13
	 * @param importFilePath 
	 */
	public static void importData(String importFilePath) throws Exception {
		
		String dbFolder = EnvProperties.getInstance().getProperty(EnvProperties.DB_DERBYDIR)+System.getProperty("file.separator")+"gestrip";
		
//		CommonMetodBin.closeConn(CommonMetodBin.getConn());
		
		// Shutdown
		Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();
		try {
			Connection conSd = DriverManager.getConnection("jdbc:derby:;shutdown=true");
			conSd.close();
		} catch (SQLException e) {
		}
		
		String dbFromFolder = importFilePath+System.getProperty("file.separator")+"gestrip";
		Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();
		Connection conCreate = DriverManager.getConnection("jdbc:derby:"+dbFolder+";restoreFrom="+dbFromFolder);
		conCreate.close();
		
		//Se il db esiste
		// jdbc:derby:C:/wip/eclipse/decisyon/RepairsLab/db/derby/gestrip;restoreFrom=
		
		//Crea nuovo da bk 
		// jdbc:derby:C:/wip/eclipse/decisyon/RepairsLab/db/derby/gestrip;createFrom=
	}
	
	/**
	 * Restore di un db da un file zip
	 * @author Fabrizio Ferraiuolo 08/nov/2010 15.16.45
	 * @param zipFilePath
	 * @throws IOException 
	 */
	public static void importFormZipFile(String zipFilePath) throws Exception {
		File tempDir = new File(TEMP_DIR_PATH);
	    ZipUtil.unzipFolder(zipFilePath, tempDir.getPath());
	    importData(tempDir.getPath());
	    deleteDir(tempDir);
	}
	
	public static void main (String[] args) {
		try {
	        exportInZipFile("c:/mybackups/pippo1.zip");
        } catch (Exception e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
        }
		
	}
	
}
