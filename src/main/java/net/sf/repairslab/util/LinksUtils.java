package net.sf.repairslab.util;

import java.awt.Component;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.JOptionPane;

import net.sf.repairslab.ui.messages.Messages;

/**
 * Utility per link esterni a url e file
 * @author Fabrizio Ferraiuolo
 * 05/ott/2010
 * 12.16.13
 * Copyright (c)2009 Decisyon S.r.l.
 */
public class LinksUtils {

	public static void openUrl(Component parentComponent, String url){
    	if (Desktop.isDesktopSupported()) {
    		Desktop desktop = Desktop.getDesktop();
    		URI uri = null;
            try {
                uri = new URI(url);
                desktop.browse(uri);
            } catch(IOException ioe) {
                //ioe.printStackTrace();
            	JOptionPane.showMessageDialog(parentComponent, 
                		ioe.getMessage(), 
                		Messages.getString("VcMainFrame.msgTitleError"), 
                		JOptionPane.ERROR_MESSAGE);
            } catch(URISyntaxException use) {
                //use.printStackTrace();
            	JOptionPane.showMessageDialog(parentComponent, 
            			use.getMessage(), 
                		Messages.getString("VcMainFrame.msgTitleError"), 
                		JOptionPane.ERROR_MESSAGE);
            }
    
    	}
    }

	public static void openFile(Component parentComponent, String fileName){
    	if (Desktop.isDesktopSupported()) {
    		Desktop desktop = Desktop.getDesktop();
    		File file = new File(fileName);
    		try {
    			desktop.open(file);
    		} catch (IOException ioe) {
                //ioe.printStackTrace();
                JOptionPane.showMessageDialog(parentComponent, 
                		ioe.getMessage(), 
                		Messages.getString("VcMainFrame.msgTitleError"), 
                		JOptionPane.ERROR_MESSAGE);
            } catch (Exception e) {
                //ioe.printStackTrace();
                JOptionPane.showMessageDialog(parentComponent, 
                		e.getMessage(), 
                		Messages.getString("VcMainFrame.msgTitleError"), 
                		JOptionPane.ERROR_MESSAGE);
            }
    
    	}
    }

}
