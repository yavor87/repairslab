package net.sf.repairslab;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class VersionReaderTest {
	
	@Test
	public void testGetInstanceString() {
		VersionReader versionReader = new VersionReader();
		assertNotNull("VersionReader version null", versionReader.getVersion());
		assertNotNull("VersionReader release null", versionReader.getRelease());
	}
	
}
