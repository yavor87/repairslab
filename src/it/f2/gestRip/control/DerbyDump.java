package it.f2.gestRip.control;

import it.f2.gestRip.util.ZipUtil;

import java.io.File;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class DerbyDump {
	
	public static void exportInZipFile(String zipFilePath) {
		String tempFile = "c:\\temp\\exp";
		File tempDir = new File(tempFile);
		export(tempDir.getPath());
		// Zip folfer
		ZipUtil.zipFolder(tempDir.getPath(), zipFilePath);
		deleteDir(tempDir);
		
	}

	public static void export(String exportFilePath) {
		
		Connection con = CommonMetodBin.getConn();
		try {
			CallableStatement smtp = con.prepareCall("CALL SYSCS_UTIL.SYSCS_BACKUP_DATABASE(?)");
			smtp.setString(1, exportFilePath);
			smtp.execute();
			smtp.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		CommonMetodBin.closeConn(con);
		
		
	}
	
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
		
	public static void restore() {
		
		
		//Se il db esiste
		//jdbc:derby:sample;restoreFrom=c:\mybackups\sample
		
		//Crea nuovo da bk 
		//jdbc:derby:sample;createFrom=c:\mybackups\sample
		
		
	}
	
	public static void main (String[] args) {
		exportInZipFile("c:/mybackups/pippo1.zip");
		
	}
	
}
