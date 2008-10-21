package it.f2.gestRip.control;

import it.f2.gestRip.EnvProperties;
import it.f2.util.ui.WindowUtil;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.apache.log4j.Logger;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JRViewer;

public class PrintAction {

	public PrintAction() {
	}

	private JasperReport getReportRicevuta() throws JRException {
		InputStream rep = getClass()
			.getResourceAsStream("/it/f2/gestRip/report/RicevutaConsegnaApparato.jasper");
		JasperReport jasperReport = null;
			jasperReport = (JasperReport) JRLoader.loadObject(rep);
		return jasperReport;
	}

	private int nScheda;
	private JDialog dialog = null;
	private JFrame frame = null;
	
	public void callReportRicevuta(JDialog parent,int nScheda){
		this.nScheda = nScheda;
		this.dialog = parent;
		initialize();
	}
	
	public void callReportRicevuta(JFrame parent,int nScheda){
		this.nScheda = nScheda;
		this.frame = parent;
		initialize();
	}
	
	@SuppressWarnings("unchecked")
	private void initialize() {
		try {
			Map parameters = new HashMap();
			parameters.put("idScheda", nScheda);
			parameters.put("infoCliente", EnvProperties.getInstance().getProperty(
					EnvProperties.INFOCLIENTE));
			parameters.put("indirizzoRivenditore", EnvProperties.getInstance().getProperty(
					EnvProperties.INDIRIZZO));
			parameters.put("logo", EnvProperties.getInstance().getProperty(
					EnvProperties.FILELOGO));
			boolean doppiaCopia = false;
			String optDC = EnvProperties.getInstance().getProperty(
					EnvProperties.DOPPIACOPIA);
			if(optDC.equals("S"))
				doppiaCopia = true;
			parameters.put("stampaDoppia", doppiaCopia);
			
			JasperPrint jp = JasperFillManager.fillReport(getReportRicevuta(), 
					parameters,CommonMetodBin.getInstance().openConn());
			
			// Lancio JasperViewer
			if (jp.getPages() != null && jp.getPages().size() > 0) {
				//JasperViewer.viewReport(jp, true, Locale.ITALY);
				JRViewer aViewer = new JRViewer(jp, Locale.ITALY);
				JDialog repDlg;
				if(dialog!=null)
					repDlg = new JDialog(dialog);
				else
					repDlg = new JDialog(frame);
                repDlg.setModal(true);
                repDlg.setTitle("Anteprima di Stampa Ricevuta Scheda di Riparazione");
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
					"Errore inizializzazione report \n"+e+"\n",
					"Warning", JOptionPane.WARNING_MESSAGE);
			Logger.getRootLogger().error("Exception in Loading report \n"+e+"\n");
		}
	}
}
