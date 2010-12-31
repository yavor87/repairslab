package net.sf.repairslab.control;

import java.net.URL;

import net.sf.repairslab.EnvConstants;
import net.sf.repairslab.VersionReader;
import net.sf.repairslab.control.CommonMetodBin.CheckStatus;
import net.sf.repairslab.model.BinRelease;

/**
 * Verifica se stai utilizzando l'ultima versione del programma
 * @author Fabrizio Ferraiuolo
 * 02/nov/2010
 * 17.55.28
 * Copyright (c)2009 Decisyon S.r.l.
 */
public class CheckUpdates {
	
	public static void check() {
		String lastRelease = getLastRelease();
		CommonMetodBin.getInstance().setActualRelease(new BinRelease(lastRelease));
		if (lastRelease == null) {
			CommonMetodBin.getInstance().setStatusUpdate(CheckStatus.NOT_CHECKED);
			CommonMetodBin.getInstance().setActualRelease(new BinRelease(null));
		} else {
			BinRelease b = new BinRelease(lastRelease);
			CommonMetodBin.getInstance().setStatusUpdate(CommonMetodBin.getInstance().getCurrentRelease().isMajor(b) ? CheckStatus.LAST_UPDATE : CheckStatus.NEW_UPDATE);
			CommonMetodBin.getInstance().setActualRelease(b);
		}
	}
	
	private static String getLastRelease() {
		try {
	        return VersionReader.getInstance(new URL(EnvConstants.LAST_REVISION_CHECK_URL)).getProperty(VersionReader.VERSION);
        } catch (Exception e) {
        	return null;
        }
	}
	
	public static void main (String[] args) {
		CheckUpdates.check();
	}
}
