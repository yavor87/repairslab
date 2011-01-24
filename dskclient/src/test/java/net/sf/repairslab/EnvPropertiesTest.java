package net.sf.repairslab;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class EnvPropertiesTest {
	
	/**
	 * Verifico che ritorni il valore per la lingua
	 * @author Fabrizio Ferraiuolo 18/gen/2011 16.57.45 
	 */
	@Test
	public void testGetProperty() {
		String l = EnvProperties.getInstance().getProperty(EnvProperties.LANGUAGE);
		assertNotNull(l);
	}
	
	
}
