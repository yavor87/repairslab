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
		assertNotNull("Loading value null.", l);
	}
	
//	/**
//	 * Testa il salvataggio di un file di properties
//	 * @author Fabrizio Ferraiuolo 24/gen/2011 18.02.45 
//	 */
//	@Test
//	public void testSaveFileProperty() {
//		System.out.println(System.getProperty("basedir"));
//		String curLang = EnvProperties.getInstance().getProperty(EnvProperties.LANGUAGE);
//		EnvProperties.getInstance().setProperty(EnvProperties.LANGUAGE, "nn");
//		EnvProperties.getInstance().saveFileProperty();
//		EnvProperties.getInstance().loadProperties();
//		assertEquals("Saving properties value error.", "nn", EnvProperties.getInstance().getProperty(EnvProperties.LANGUAGE));
//		EnvProperties.getInstance().setProperty(EnvProperties.LANGUAGE, curLang);
//		EnvProperties.getInstance().saveFileProperty();
//		EnvProperties.getInstance().loadProperties();
//		assertEquals("Saving properties value error.", curLang, EnvProperties.getInstance().getProperty(EnvProperties.LANGUAGE));
//	}
}
