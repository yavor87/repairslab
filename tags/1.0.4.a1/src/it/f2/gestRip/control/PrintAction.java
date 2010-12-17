package it.f2.gestRip.control;

import it.f2.gestRip.EnvProperties;
import it.f2.gestRip.ui.messages.Messages;
import it.f2.util.ui.WindowUtil;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JRViewer;

import org.apache.log4j.Logger;

public class PrintAction {

	public PrintAction() {
	}

	private JasperReport getReportRicevuta() throws JRException {
		InputStream rep;
		String report = EnvProperties.getInstance().getProperty(EnvProperties.JASPER);
		if (report.equals("RicevutaConsegnaApparato.jasper") || report.equals("RicevutaConsegnaApparatoMySql.jasper")) {
			rep = getClass().getResourceAsStream("/it/f2/gestRip/report/"+report); //$NON-NLS-1$
		} else {
			try {
	            rep = new BufferedInputStream(new FileInputStream(new File(report)));
            } catch (FileNotFoundException e) {
	            throw new JRException(e.getMessage());
            } 
		}
		JasperReport jasperReport = (JasperReport) JRLoader.loadObject(rep);
		return jasperReport;
	}

	private int nScheda;
	private JDialog dialog = null;
	private JFrame frame = null;
	private Connection con = null;
	
	public void callReportRicevuta(JDialog parent,int nScheda,Connection con){
		this.nScheda = nScheda;
		this.dialog = parent;
		this.con = con;
		initialize();
	}
	
	public void callReportRicevuta(JFrame parent,int nScheda,Connection con){
		this.nScheda = nScheda;
		this.frame = parent;
		this.con = con;
		initialize();
	}
	
	@SuppressWarnings("unchecked")
	private void initialize() {
		try {
			Map parameters = new HashMap();
			parameters.put("idScheda", nScheda); //$NON-NLS-1$
			parameters.put("prefixNum", EnvProperties.getInstance().getProperty(EnvProperties.PREFIX_NUM));
			parameters.put("infoCliente", EnvProperties.getInstance().getProperty(EnvProperties.INFOCLIENTE));
			parameters.put("indirizzoRivenditore", EnvProperties.getInstance().getProperty(EnvProperties.INDIRIZZO));
			parameters.put("logo", EnvProperties.getInstance().getProperty(EnvProperties.FILELOGO));
			boolean doppiaCopia = false;
			String optDC = EnvProperties.getInstance().getProperty(EnvProperties.DOPPIACOPIA);
			if(optDC.equals("S")) //$NON-NLS-1$
				doppiaCopia = true;
			parameters.put("stampaDoppia", doppiaCopia); //$NON-NLS-1$
			parameters.put(JRParameter.REPORT_LOCALE, Locale.getDefault());
			parameters.put(JRParameter.REPORT_RESOURCE_BUNDLE, Messages.getRESOURCE_BUNDLE());
			
			JasperPrint jp = JasperFillManager.fillReport(getReportRicevuta(), parameters, con);
			
			// Lancio JasperViewer
			if (jp.getPages() != null && jp.getPages().size() > 0) {
				JRViewer aViewer = new JRViewer(jp, Locale.getDefault());
				JDialog repDlg;
				if(dialog!=null)
					repDlg = new JDialog(dialog);
				else
					repDlg = new JDialog(frame);
                repDlg.setModal(true);
                repDlg.setTitle(Messages.getString("PrintAction.7")); //$NON-NLS-1$
                repDlg.getContentPane().add(aViewer);
                java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
                repDlg.setSize(1080, screenSize.height-80);
                java.awt.Insets insets = repDlg.getInsets();
                repDlg.setSize(repDlg.getWidth() + insets.left + insets.right, repDlg.getHeight() + insets.top + insets.bottom + 20);
                WindowUtil.centerWindow(repDlg);
                repDlg.setVisible(true);
			}
			
		} catch (JRException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(CommonMetodBin.getInstance().getMainFrame(),
					Messages.getString("PrintAction.8")+e+"\n", //$NON-NLS-1$ //$NON-NLS-2$
					Messages.getString("PrintAction.10"), JOptionPane.WARNING_MESSAGE); //$NON-NLS-1$
			Logger.getRootLogger().error(Messages.getString("PrintAction.11")+e+"\n"); //$NON-NLS-1$ //$NON-NLS-2$
		}
	}
}
