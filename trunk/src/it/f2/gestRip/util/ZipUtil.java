package it.f2.gestRip.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Utility per la zip/unzip
 * @author Fabrizio Ferraiuolo
 * 05/nov/2010
 * 16.57.49
 * Copyright (c)2009 Decisyon S.r.l.
 */
public class ZipUtil {
	
	private static final int BUFFER = 2048; 
	
	/**
	 * Esecuzione zip da una cartella
	 * @author Fabrizio Ferraiuolo 05/nov/2010 17.43.10
	 * @param inFolder
	 * @param outFile
	 * @return 
	 */
	public static File zipFolder(String inFolder, String outFile) {
		try {
			inFolder = inFolder.replace("\\", System.getProperty("file.separator"));
			inFolder = inFolder.replace("/", System.getProperty("file.separator"));
			inFolder = inFolder.endsWith(System.getProperty("file.separator")) ? inFolder : inFolder + System.getProperty("file.separator");
			ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(outFile));
			zipDir(inFolder, zos, inFolder);
			zos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new File(outFile + ".zip");
	}

	/**
	 * Esecuzione processo di zip della directory
	 * @author Fabrizio Ferraiuolo 05/nov/2010 17.43.33
	 * @param dir2zip
	 * @param zos
	 * @param inFolder 
	 */
	public static void zipDir(String dir2zip, ZipOutputStream zos, String inFolder) {
		try {
			File zipDir = new File(dir2zip);
			String[] dirList = zipDir.list();
			byte[] readBuffer = new byte[BUFFER];
			int bytesIn = 0;
			//loop through dirList, and zip the files 
			for (String dir : dirList) {
				File f = new File(zipDir, dir);
				if (f.isDirectory()) {
					String filePath = f.getPath();
					zipDir(filePath, zos, inFolder);
					continue;
				}
				FileInputStream fis = new FileInputStream(f);
				String fileZipPath = f.getPath().replace(inFolder, "");
				ZipEntry anEntry = new ZipEntry(fileZipPath);
				zos.putNextEntry(anEntry);
				while ((bytesIn = fis.read(readBuffer)) != -1) {
					zos.write(readBuffer, 0, bytesIn);
				}
				fis.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
