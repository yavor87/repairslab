package net.sf.repairslab;

import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.util.Properties;

import junit.framework.Assert;
import net.sf.repairslab.VersionReader;

import org.junit.Test;

public class VersionReaderTest {
	
	@Test
	public void testGetInstanceString() {
		File versionFile = new File("target\\classes\\version.properties");
		if (!versionFile.exists())
			Assert.fail("File " + versionFile.getAbsolutePath() + " not exist.");
		Properties p = VersionReader.getInstance(versionFile.getAbsolutePath());
		assertNotNull("VersionReader instance null", p);
		String release = p.getProperty(VersionReader.VERSION);
		assertNotNull("VersionReader release null", release);
		int version = Integer.parseInt(p.getProperty(VersionReader.RELEASE));
	}
	
}
