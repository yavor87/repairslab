package it.f2.gestRip.control;

import it.f2.gestRip.EnvConstants;
import it.f2.gestRip.control.CommonMetodBin.CheckStatus;
import it.f2.gestRip.model.BinRelease;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.log4j.Logger;

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
		if (lastRelease == null)
			CommonMetodBin.getInstance().setStatusUpdate(CheckStatus.NOT_CHECKED);
		else
			CommonMetodBin.getInstance().setStatusUpdate(lastRelease.equals(CommonMetodBin.getInstance().getCurrentRelease().toString()) ? CheckStatus.LAST_UPDATE : CheckStatus.NEW_UPDATE);
		Logger.getRootLogger().debug(lastRelease);
	}
	
	private static String getLastRelease() {
		try {
	        URL url = new URL(EnvConstants.LAST_REVISION_CHECK_URL);
	        InputStream is = url.openStream();
	        BufferedReader d = new BufferedReader(new InputStreamReader(is));
	        String s;
	        while ((s = d.readLine()) != null) {
	        	return s;
	        }
		} catch (FileNotFoundException e) {
			Logger.getRootLogger().error("Exception in check updates: "+e);
        } catch (MalformedURLException e) {
        	Logger.getRootLogger().error("Exception in check updates: "+e);
        } catch (IOException e) {
        	Logger.getRootLogger().error("Exception in check updates: "+e);
        }
        return null;
	}
	
	public static void main (String[] args) {
		CheckUpdates.check();
	}
}
