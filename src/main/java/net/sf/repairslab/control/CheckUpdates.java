package net.sf.repairslab.control;

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
		BinRelease lastBinRelease = getLastBinRelease();
		CommonMetodBin.getInstance().setActualRelease(lastBinRelease);
		if (lastBinRelease == null) {
			CommonMetodBin.getInstance().setStatusUpdate(CheckStatus.LAST_UPDATE);
			CommonMetodBin.getInstance().setActualRelease(new BinRelease());
		} else {
			CommonMetodBin.getInstance().setStatusUpdate(CommonMetodBin.getInstance().getCurrentRelease().isMajor(lastBinRelease) ? CheckStatus.LAST_UPDATE : CheckStatus.NEW_UPDATE);
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
