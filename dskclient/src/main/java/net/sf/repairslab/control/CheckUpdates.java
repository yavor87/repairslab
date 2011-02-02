package net.sf.repairslab.control;

import net.sf.repairslab.EnvConstants;
import net.sf.repairslab.VersionReader;
import net.sf.repairslab.control.CommonMetodBin.CheckStatus;
import net.sf.repairslab.model.BinRelease;
import net.sf.repairslab.ui.VcMainFrame;

/**
 * Verifica se stai utilizzando l'ultima versione del programma
 * @author Fabrizio Ferraiuolo
 * 02/nov/2010
 * 17.55.28
 * Copyright (c)2009 Decisyon S.r.l.
 */
public class CheckUpdates {
	
	public static void check() {
		BinRelease lastBinRelease = getLastBinRelease();
		CommonMetodBin.getInstance().setActualRelease(lastBinRelease);
		if (lastBinRelease == null) {
			CommonMetodBin.getInstance().setStatusUpdate(CheckStatus.NOT_CHECKED);
			CommonMetodBin.getInstance().setActualRelease(new BinRelease());
		} else {
			if (!lastBinRelease.isMajor(CommonMetodBin.getInstance().getCurrentRelease()))
				CommonMetodBin.getInstance().setStatusUpdate(CheckStatus.LAST_UPDATE);
			else {
				CommonMetodBin.getInstance().setStatusUpdate(CheckStatus.NEW_UPDATE);
				((VcMainFrame)CommonMetodBin.getInstance().getMainFrame()).getStatusBar().setStatusNewUpdate();
			}
			CommonMetodBin.getInstance().setActualRelease(lastBinRelease);
		}
	}
	
	/**
	 * Return last version-release stable publisced
	 * @author Fabrizio Ferraiuolo 13/gen/2011 15.38.06
	 * @return 
	 */
	private static BinRelease getLastBinRelease() {
		VersionReader versionReader = new VersionReader(EnvConstants.LAST_REVISION_CHECK_URL);
    	return new BinRelease(versionReader.getAppname(), versionReader.getVersion(), versionReader.getRelease());
	}
	
	public static void main (String[] args) {
		CheckUpdates.check();
	}
}
