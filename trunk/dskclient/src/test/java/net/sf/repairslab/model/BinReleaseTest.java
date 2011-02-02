package net.sf.repairslab.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class BinReleaseTest {
	
	@Test
	public void testIsMajor() {
		// BinRelease minor
		BinRelease beenReleaseMinor = new BinRelease();
		beenReleaseMinor.setAppname("RepairsLab");
		beenReleaseMinor.setMajorVersion(1);
		beenReleaseMinor.setMinorVersion(0);
		beenReleaseMinor.setRelease(1);
		beenReleaseMinor.setRevision(100);
		
		// BinRelease intermedial
		BinRelease beenReleaseIntermedial = new BinRelease();
		beenReleaseIntermedial.setAppname("RepairsLab");
		beenReleaseIntermedial.setMajorVersion(1);
		beenReleaseIntermedial.setMinorVersion(0);
		beenReleaseIntermedial.setRelease(1);
		beenReleaseIntermedial.setRevision(500);
		
		// BinRelease major
		BinRelease beenReleaseMajor = new BinRelease();
		beenReleaseMajor.setAppname("RepairsLab");
		beenReleaseMajor.setMajorVersion(1);
		beenReleaseMajor.setMinorVersion(0);
		beenReleaseMajor.setRelease(15);
		beenReleaseMajor.setRevision(800);
		
		assertTrue("testIsMajor 1", beenReleaseMajor.isMajor(beenReleaseMinor));
		
		assertFalse("testIsMajor 2", beenReleaseMinor.isMajor(beenReleaseMajor));
		
		assertTrue("testIsMajor 3", beenReleaseIntermedial.isMajor(beenReleaseMinor));
		
		assertFalse("testIsMajor 4", beenReleaseIntermedial.isMajor(beenReleaseIntermedial));
	}
	
}
