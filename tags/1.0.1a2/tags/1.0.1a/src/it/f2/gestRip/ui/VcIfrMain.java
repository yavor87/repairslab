package it.f2.gestRip.ui;

import it.f2.gestRip.EnvProperties;
import it.f2.gestRip.control.DbSchedaAction;
import it.f2.gestRip.control.PrintAction;
import it.f2.util.ui.WindowUtil;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JInternalFrame;
import javax.swing.JButton;
import javax.swing.JToolBar;
import java.awt.BorderLayout;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.SQLException;

import javax.swing.JEditorPane;

import org.apache.log4j.Logger;

public class VcIfrMain extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private VcMainFrame parent = null;
	private JButton btnInsScheda = null;
	private JButton btnListaSchede = null;
	private JToolBar tlbFunctions = null;
	private JButton btnViewScheda = null;
	private JButton btnModScheda = null;
	private JButton brnStampaScheda = null;
	private JEditorPane edpMainScreen = null;
	
	/**
	 * This is the xxx default constructor
	 */
	public VcIfrMain(VcMainFrame parent) {
		super();
		Logger.getRootLogger().debug("Loading Main..");
		this.parent = parent;
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(793, 516);
		this.setContentPane(getJContentPane());
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(new BorderLayout());
			jContentPane.add(getTlbFunctions(), BorderLayout.NORTH);
			jContentPane.add(getEdpMainScreen(), BorderLayout.CENTER);
		}
		return jContentPane;
	}

	/**
	 * This method initializes btnInsScheda	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnInsScheda() {
		if (btnInsScheda == null) {
			btnInsScheda = new JButton();
			btnInsScheda.setText("Nuova Scheda");
			btnInsScheda.setIcon(new ImageIcon(getClass().getResource(
				"/it/f2/gestRip/ui/img/newtodo.png")));
			btnInsScheda.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					VcDlgDetailScheda dialog = new VcDlgDetailScheda(parent,null,VcDlgDetailScheda.mode.insert,0);
					WindowUtil.centerWindow(dialog);
					dialog.setVisible(true);
				}
			});
		}
		return btnInsScheda;
	}

	/**
	 * This method initializes btnListaSchede	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnListaSchede() {
		if (btnListaSchede == null) {
			btnListaSchede = new JButton();
			btnListaSchede.setText("Lista Schede");
			btnListaSchede.setIcon(new ImageIcon(getClass().getResource(
				"/it/f2/gestRip/ui/img/view_detailed.png")));
			btnListaSchede.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					VcIfrListaSchede iframe = new VcIfrListaSchede(parent);
					if(!parent.isJitOpen(iframe.getClass())){
						parent.addTab("Lista Schede", iframe);
						parent.selectTab(iframe);
					}else{
						iframe.hide();
					}
				}
			});
		}
		return btnListaSchede;
	}

	/**
	 * This method initializes tlbFunctions	
	 * 	
	 * @return javax.swing.JToolBar	
	 */
	private JToolBar getTlbFunctions() {
		if (tlbFunctions == null) {
			tlbFunctions = new JToolBar();
			tlbFunctions.add(getBtnListaSchede());
			tlbFunctions.add(getBtnInsScheda());
			tlbFunctions.add(getBtnViewScheda());
			tlbFunctions.add(getBtnModScheda());
			tlbFunctions.add(getBrnStampaScheda());
		}
		return tlbFunctions;
	}

	/**
	 * This method initializes btnViewScheda	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnViewScheda() {
		if (btnViewScheda == null) {
			btnViewScheda = new JButton();
			btnViewScheda.setText("Visualizza Scheda");
			btnViewScheda.setIcon(new ImageIcon(getClass().getResource(
				"/it/f2/gestRip/ui/img/fileopen.png")));
			btnViewScheda.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					String mes = JOptionPane.showInputDialog(getParent(),"Inserire numero Scheda di riparazione");
					int num = 0;
					try{
						num = Integer.parseInt(mes);
						try {
							if(DbSchedaAction.existScheda(num)){
								openDetail(VcDlgDetailScheda.mode.view,num);
							}else{
								JOptionPane.showMessageDialog(getParent(),
										"Scheda inesistente.",
										"Warning", JOptionPane.WARNING_MESSAGE);
							}
						} catch (SQLException e1) {
							Logger.getRootLogger().error("Exception in Connectiond DB \n"+e+"\n");
							//e1.printStackTrace();
						}
						
					}catch(NumberFormatException ex){
						JOptionPane.showMessageDialog(getParent(),
								"Numero scheda non valido.",
								"Warning", JOptionPane.WARNING_MESSAGE);
					}
				}
			});
		}
		return btnViewScheda;
	}
	
	private void openDetail(VcDlgDetailScheda.mode modality,int id_scheda){
		VcDlgDetailScheda dialog = new VcDlgDetailScheda(parent,null,modality,id_scheda);
		WindowUtil.centerWindow(dialog);
		dialog.setVisible(true);
		dialog.setVisible(closable);
	}

	/**
	 * This method initializes btnModScheda	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnModScheda() {
		if (btnModScheda == null) {
			btnModScheda = new JButton();
			btnModScheda.setText("Modifica Scheda");
			btnModScheda.setIcon(new ImageIcon(getClass().getResource(
				"/it/f2/gestRip/ui/img/kate.png")));
			btnModScheda.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					String mes = JOptionPane.showInputDialog(getParent(),"Inserire numero Scheda di riparazione");
					int num = 0;
					try{
						num = Integer.parseInt(mes);
						try {
							if(DbSchedaAction.existScheda(num)){
								openDetail(VcDlgDetailScheda.mode.update,num);
							}else{
								JOptionPane.showMessageDialog(getParent(),
										"Scheda inesistente.",
										"Warning", JOptionPane.WARNING_MESSAGE);
							}
						} catch (SQLException e1) {
							Logger.getRootLogger().error("Exception in Connectiond DB \n"+e+"\n");
							//e1.printStackTrace();
						}
						
					}catch(NumberFormatException ex){
						JOptionPane.showMessageDialog(getParent(),
								"Numero scheda non valido.",
								"Warning", JOptionPane.WARNING_MESSAGE);
					}
				}
			});
		}
		return btnModScheda;
	}

	/**
	 * This method initializes brnStampaScheda	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBrnStampaScheda() {
		if (brnStampaScheda == null) {
			brnStampaScheda = new JButton();
			brnStampaScheda.setText("Stampa Scheda");
			brnStampaScheda.setIcon(new ImageIcon(getClass().getResource(
				"/it/f2/gestRip/ui/img/fileprint_.png")));
			brnStampaScheda.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					String mes = JOptionPane.showInputDialog(getParent(),"Inserire numero Scheda di riparazione");
					int num = 0;
					try{
						num = Integer.parseInt(mes);
						try {
							if(DbSchedaAction.existScheda(num)){
								print(num);
							}else{
								JOptionPane.showMessageDialog(getParent(),
										"Scheda inesistente.",
										"Warning", JOptionPane.WARNING_MESSAGE);
							}
						} catch (SQLException e1) {
							Logger.getRootLogger().error("Exception in Connectiond DB \n"+e+"\n");
							//e1.printStackTrace();
						}
						
					}catch(NumberFormatException ex){
						JOptionPane.showMessageDialog(getParent(),
								"Numero scheda non valido.",
								"Warning", JOptionPane.WARNING_MESSAGE);
					}
				}
			});
		}
		return brnStampaScheda;
	}
	
	private void print(int nScheda){
		Logger.getRootLogger().debug("printing");
		PrintAction pa = new PrintAction();
		pa.callReportRicevuta(this.parent,nScheda);
	}

	/**
	 * This method initializes edpMainScreen	
	 * 	
	 * @return javax.swing.JEditorPane	
	 */
	private JEditorPane getEdpMainScreen() {
		if (edpMainScreen == null) {
			File html = new File(
				"conf"+
				EnvProperties.getInstance().getProperty(EnvProperties.FILE_SEPARETOR)+
				"mainScreen.html");
			edpMainScreen = new JEditorPane();
			edpMainScreen.setContentType("text/html");
			try {
				Logger.getRootLogger().debug("Setting main html page...");
				edpMainScreen.setPage(html.toURI().toURL());
			} catch (MalformedURLException e) {
				Logger.getRootLogger().error("Exception in Setting main html page\n"+e+"\n");
				//e.printStackTrace();
			} catch (IOException e) {
				Logger.getRootLogger().error("Exception in Setting main html page\n"+e+"\n");
				//e.printStackTrace();
			}
			edpMainScreen.setEditable(false);
			//HTMLEditorKit he = new HTMLEditorKit();
			//edpMainScreen.setEditorKit(he);
		}
		return edpMainScreen;
	}


}  //  @jve:decl-index=0:visual-constraint="10,10"
