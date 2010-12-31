package it.f2.gestRip.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
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
	public static File zipFolder(String inFolder, String outFile) throws Exception {
		inFolder = inFolder.replace("\\", System.getProperty("file.separator"));
		inFolder = inFolder.replace("/", System.getProperty("file.separator"));
		inFolder = inFolder.endsWith(System.getProperty("file.separator")) ? inFolder : inFolder + System.getProperty("file.separator");
		ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(outFile));
		zipDir(inFolder, zos, inFolder);
		zos.close();
		return new File(outFile + ".zip");
	}

	/**
	 * Esecuzione processo di zip della directory
	 * @author Fabrizio Ferraiuolo 05/nov/2010 17.43.33
	 * @param dir2zip
	 * @param zos
	 * @param inFolder 
	 */
	public static void zipDir(String dir2zip, ZipOutputStream zos, String inFolder) throws IOException {
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
	}
	
	/**
	 * TODO Comment for method "unzipFolder" must be completed
	 * @author Fabrizio Ferraiuolo 08/nov/2010 14.59.17
	 * @param zip
	 * @param extractTo
	 * @throws IOException 
	 */
	public static final void unzipFolder(String inFile, String outFolder) throws IOException {
		File zip = new File(inFile);
		File extractTo = new File(outFolder);
		
		ZipFile archive = new ZipFile(zip);
		Enumeration e = archive.entries();
		while (e.hasMoreElements()) {
			ZipEntry entry = (ZipEntry) e.nextElement();
			File file = new File(extractTo, entry.getName());
			if (entry.isDirectory() && !file.exists()) {
				file.mkdirs();
			} else {
				if (!file.getParentFile().exists()) {
					file.getParentFile().mkdirs();
				}
				
				InputStream in = archive.getInputStream(entry);
				BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(file));
				
				byte[] buffer = new byte[8192];
				int read;
				
				while (-1 != (read = in.read(buffer))) {
					out.write(buffer, 0, read);
				}
				
				in.close();
				out.close();
			}
		}
	}

}
