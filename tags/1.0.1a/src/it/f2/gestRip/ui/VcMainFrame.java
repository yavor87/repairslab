/**
 * @author FERRAF01
 * 
 * Questa classe rappresenta il JFrame Standard.
 * la parte grafica è stata costruita con Eclipse Visual Editor.
 */

package it.f2.gestRip.ui;

import it.f2.gestRip.EnvProperties;
import it.f2.gestRip.control.CommonMetodBin;
import it.f2.gestRip.ui.anagraf.VcIfrAnaClienti;
import it.f2.gestRip.ui.anagraf.VcIfrAnaMarche;
import it.f2.gestRip.ui.anagraf.VcIfrAnaModelli;
import it.f2.gestRip.ui.anagraf.VcIfrAnaStati;
import it.f2.gestRip.ui.anagraf.VcIfrAnaTipoDatiAcq;
import it.f2.gestRip.ui.anagraf.VcIfrAnaTipoOggetto;
import it.f2.gestRip.ui.anagraf.VcIfrAnaTipoRip;
import it.f2.util.ui.WindowUtil;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import org.apache.log4j.Logger;

public class VcMainFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JPanel jContentPane = null;
	private JMenuBar jJMenuBar = null;
	private JMenu fileMenu = null;
	private JMenu helpMenu = null;
	private JMenuItem exitMenuItem = null;
	private JMenuItem aboutMenuItem = null;
	private JTabbedPane tbpMain = null;
	private JMenu settingMenu = null;
	private JMenuItem optionsMenuItem = null;

	private JMenu gestMenu = null;

	private JMenuItem mniStati = null;

	private JMenuItem mniModelli = null;

	private JMenuItem mniTipoOggetto = null;

	private JMenuItem mniMarche = null;

	private JMenuItem mniAnaClienti = null;

	private JMenuItem mniTipoRip = null;

	private JMenuItem mniTipoDatiAcq = null;
	/**
	 * This is the default constructor
	 */
	public VcMainFrame() {
		super();
		Logger.getRootLogger().debug("VcMainFrame constructor...");
		initialize();
	}
	
	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(
				getClass().getResource("/it/f2/gestRip/ui/img/logo64.png")));
		this.setJMenuBar(getJJMenuBar());
		this.setSize(442, 300);
		this.setSize(Integer.parseInt(EnvProperties.getInstance().getProperty(
				EnvProperties.WIDTH)), Integer.parseInt(EnvProperties
				.getInstance().getProperty(EnvProperties.HEIGHT)));
		this.setContentPane(getJContentPane());
		this.setTitle(EnvProperties.getInstance().getProperty(
				EnvProperties.APPNAME));
		this.addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosing(java.awt.event.WindowEvent e) {
				onClosing();
			}
		});
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
			jContentPane.add(getTbpMain(), java.awt.BorderLayout.CENTER);
		}
		return jContentPane;
	}

	/**
	 * This method initializes jJMenuBar
	 * 
	 * @return javax.swing.JMenuBar
	 */
	private JMenuBar getJJMenuBar() {
		if (jJMenuBar == null) {
			jJMenuBar = new JMenuBar();
			jJMenuBar.add(getFileMenu());
			jJMenuBar.add(getGestMenu());
			jJMenuBar.add(getSettingMenu());
			jJMenuBar.add(getHelpMenu());
		}
		return jJMenuBar;
	}

	/**
	 * This method initializes jMenu
	 * 
	 * @return javax.swing.JMenu
	 */
	private JMenu getFileMenu() {
		if (fileMenu == null) {
			fileMenu = new JMenu();
			fileMenu.setText("File");
			fileMenu.addSeparator();
			fileMenu.addSeparator();
			fileMenu.addSeparator();
			fileMenu.add(getExitMenuItem());
		}
		return fileMenu;
	}

	/**
	 * This method initializes jMenu
	 * 
	 * @return javax.swing.JMenu
	 */
	private JMenu getHelpMenu() {
		if (helpMenu == null) {
			helpMenu = new JMenu();
			helpMenu.setText("Help");
			helpMenu.add(getAboutMenuItem());
		}
		return helpMenu;
	}

	/**
	 * This method initializes jMenuItem
	 * 
	 * @return javax.swing.JMenuItem
	 */
	private JMenuItem getExitMenuItem() {
		if (exitMenuItem == null) {
			exitMenuItem = new JMenuItem();
			exitMenuItem.setText("Exit");
			exitMenuItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					onClosing();
				}
			});
		}
		return exitMenuItem;
	}

	/**
	 * This method initializes jMenuItem
	 * 
	 * @return javax.swing.JMenuItem
	 */
	private JMenuItem getAboutMenuItem() {
		if (aboutMenuItem == null) {
			aboutMenuItem = new JMenuItem();
			aboutMenuItem.setText("About");
			aboutMenuItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					openDlgAbout();
				}
			});
		}
		return aboutMenuItem;
	}

	/**
	 * Qussto metodo apre la dialog About
	 */
	private void openDlgAbout() {
		VcDlgAbout d = new VcDlgAbout(this);
		WindowUtil.centerWindow(d);
		d.setVisible(true);
	}

	/**
	 * Questo metodo permette di modificare il titolo del tab selezionato.
	 * 
	 * @param txt
	 */
	public void setTabTitle(String txt) {
		getTbpMain().setTitleAt(getTbpMain().getSelectedIndex(), txt);
	}

	/**
	 * This method initializes tbpEdits
	 * 
	 * @return javax.swing.JTabbedPane
	 */
	private JTabbedPane getTbpMain() {
		if (tbpMain == null) {
			tbpMain = new JTabbedPane();
			//JInternalFrame ifr = new JInternalFrame();
			VcIfrMain ifr = new VcIfrMain(this);
			ifr.setTitle("Menù principale");
			//ifr.setMaximizable(true);
			//ifr.setClosable(true);
			tbpMain.addTab("Menù principale", ifr);
			/*tbpMain.addChangeListener(new javax.swing.event.ChangeListener() {
				public void stateChanged(javax.swing.event.ChangeEvent e) {
					if (getTbpMain().getSelectedIndex() != -1) {
						JInternalFrame ifr = (JInternalFrame) getTbpMain()
								.getSelectedComponent();
						try {
							VcIfrMain tx = (VcIfrMain) ifr;
							//tx.enableUndo();
						} catch (ClassCastException cce) {
						}
					}
				}
			});*/
		}
		return tbpMain;
	}
	
	public boolean isJitOpen(Class<?> className){
		boolean result = false;
		Component[] cm = getTbpMain().getComponents();
		for (int i = 0; i < cm.length; i++) {
			try {
				JInternalFrame jit = (JInternalFrame) cm[i];
				if(jit.getClass()==className){
					result = true;
					selectTab(jit);
				}

			} catch (ClassCastException cce) {
			}
		}
		return result;
	}

	/**
	 * Questo metodo permette di inserire un nuovo tab e se il parametro file è
	 * inizializzato inserisce nel nuovo tab l'oggetto DwbDocument.
	 * 
	 * @param file
	 */
	public void addTab(String title,JInternalFrame iframe) {
		getTbpMain().addTab(title, iframe);
	}
	
	public void selectTab(JInternalFrame iframe){
		getTbpMain().setSelectedComponent(iframe);
		//this.repaint();
	}
	
	public void closeTab(JInternalFrame iframe){
		if(isJitOpen(iframe.getClass())){
			getTbpMain().remove(iframe);
			iframe.dispose();
		}
	}

	/*
	 * Questo metodo viene chiamato alla chiusura dell'applicazione e prima di
	 * chiudere cancella tutti i file temporanei eventualmenete creati
	 * e salva sul file di property le variabili di altezza e larghezza dello
	 * schermo.
	 */
	private void onClosing() {
		Component[] cm = getTbpMain().getComponents();
		for (int i = 0; i < cm.length; i++) {
			try {
				JInternalFrame jit = (JInternalFrame) cm[i];
				jit.doDefaultCloseAction();

			} catch (ClassCastException cce) {
			}
		}
		CommonMetodBin.getInstance().closeConn();
		EnvProperties.getInstance().setProperty(EnvProperties.WIDTH,
				"" + this.getWidth());
		EnvProperties.getInstance().setProperty(EnvProperties.HEIGHT,
				"" + this.getHeight());
		EnvProperties.getInstance().saveFileProperty();
		System.exit(0);
	}

	/**
	 * This method initializes settingMenu
	 * 
	 * @return javax.swing.JMenu
	 */
	private JMenu getSettingMenu() {
		if (settingMenu == null) {
			settingMenu = new JMenu();
			settingMenu.setText("Strumenti");
			settingMenu.add(getOptionsMenuItem());
		}
		return settingMenu;
	}

	/**
	 * This method initializes optionsMenuItem
	 * 
	 * @return javax.swing.JMenuItem
	 */
	private JMenuItem getOptionsMenuItem() {
		if (optionsMenuItem == null) {
			optionsMenuItem = new JMenuItem();
			optionsMenuItem.setText("Opzioni");
			optionsMenuItem.setIcon(new ImageIcon(getClass().getResource(
					"/it/f2/gestRip/ui/img/Options16.png")));
			optionsMenuItem
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							openDialogOptions();
						}
					});
		}
		return optionsMenuItem;
	}

	/**
	 * Questo metodo apre la dialog DlgOptions.
	 */
	private void openDialogOptions() {
		VcDlgOptions d = new VcDlgOptions(this);
		WindowUtil.centerWindow(d);
		d.setVisible(true);
	}

	/**
	 * This method initializes gestMenu	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	private JMenu getGestMenu() {
		if (gestMenu == null) {
			gestMenu = new JMenu();
			gestMenu.setText("Gestione");
			gestMenu.add(getMniAnaClienti());
			gestMenu.add(getMniStati());
			gestMenu.add(getMniTipoRip());
			gestMenu.add(getMniTipoDatiAcq());
			gestMenu.add(getMniTipoOggetto());
			gestMenu.add(getMniMarche());
			gestMenu.add(getMniModelli());
		}
		return gestMenu;
	}

	/**
	 * This method initializes mniStati	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getMniStati() {
		if (mniStati == null) {
			mniStati = new JMenuItem();
			mniStati.setText("Stati");
			mniStati.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					openAnaStati();
				}
			});
		}
		return mniStati;
	}
	private void openAnaStati(){
		VcIfrAnaStati iframe = new VcIfrAnaStati(this);
		if(!this.isJitOpen(iframe.getClass())){
			this.addTab("Anagrafica stati", iframe);
			this.selectTab(iframe);
		}else{
			iframe.hide();
		}
	}

	/**
	 * This method initializes mniModelli	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getMniModelli() {
		if (mniModelli == null) {
			mniModelli = new JMenuItem();
			mniModelli.setText("Anagrafica Modelli");
			mniModelli.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					openAnaModelli();
				}
			});
		}
		return mniModelli;
	}
	
	private void openAnaModelli(){
		VcIfrAnaModelli iframe = new VcIfrAnaModelli(this);
		if(!this.isJitOpen(iframe.getClass())){
			this.addTab("Anagrafica Modelli", iframe);
			this.selectTab(iframe);
		}else{
			iframe.hide();
		}
	}

	/**
	 * This method initializes mniTipoOggetto	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getMniTipoOggetto() {
		if (mniTipoOggetto == null) {
			mniTipoOggetto = new JMenuItem();
			mniTipoOggetto.setText("Anagrafica Tipo Oggetto");
			mniTipoOggetto.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					openAnaTipoOggetto();
				}
			});
		}
		return mniTipoOggetto;
	}
	
	private void openAnaTipoOggetto(){
		VcIfrAnaTipoOggetto iframe = new VcIfrAnaTipoOggetto(this);
		if(!this.isJitOpen(iframe.getClass())){
			this.addTab("Anagrafica Tipologia Oggetti", iframe);
			this.selectTab(iframe);
		}else{
			iframe.hide();
		}
	}

	/**
	 * This method initializes mniMarche	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getMniMarche() {
		if (mniMarche == null) {
			mniMarche = new JMenuItem();
			mniMarche.setText("Anagrafica Marchi");
			mniMarche.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					openAnaMarche();
				}
			});
		}
		return mniMarche;
	}
	
	private void openAnaMarche(){
		VcIfrAnaMarche iframe = new VcIfrAnaMarche(this);
		if(!this.isJitOpen(iframe.getClass())){
			this.addTab("Anagrafica Marchi", iframe);
			this.selectTab(iframe);
		}else{
			iframe.hide();
		}
	}

	/**
	 * This method initializes mniAnaClienti	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getMniAnaClienti() {
		if (mniAnaClienti == null) {
			mniAnaClienti = new JMenuItem();
			mniAnaClienti.setText("Anagrafica Clienti");
			mniAnaClienti.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					openAnaClienti();
				}
			});
		}
		return mniAnaClienti;
	}
	
	private void openAnaClienti(){
		VcIfrAnaClienti iframe = new VcIfrAnaClienti(this);
		if(!this.isJitOpen(iframe.getClass())){
			this.addTab("Anagrafica Clienti", iframe);
			this.selectTab(iframe);
		}else{
			iframe.hide();
		}
	}

	/**
	 * This method initializes mniTipoRip	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getMniTipoRip() {
		if (mniTipoRip == null) {
			mniTipoRip = new JMenuItem();
			mniTipoRip.setText("Anagragica Tipo Riparazione");
			mniTipoRip.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					openAnaTipoRip();
				}
			});
		}
		return mniTipoRip;
	}
	
	private void openAnaTipoRip(){
		VcIfrAnaTipoRip iframe = new VcIfrAnaTipoRip(this);
		if(!this.isJitOpen(iframe.getClass())){
			this.addTab("Anagrafica Tipologia Riparazione", iframe);
			this.selectTab(iframe);
		}else{
			iframe.hide();
		}
	}

	/**
	 * This method initializes mniTipoDatiAcq	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getMniTipoDatiAcq() {
		if (mniTipoDatiAcq == null) {
			mniTipoDatiAcq = new JMenuItem();
			mniTipoDatiAcq.setText("Anagrafica Tipo Dati Acquisto");
			mniTipoDatiAcq.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					openAnaTipoDatiAcq();
				}
			});
		}
		return mniTipoDatiAcq;
	}
	
	private void openAnaTipoDatiAcq(){
		VcIfrAnaTipoDatiAcq iframe = new VcIfrAnaTipoDatiAcq(this);
		if(!this.isJitOpen(iframe.getClass())){
			this.addTab("Anagrafica Tipo Dati Acquisto", iframe);
			this.selectTab(iframe);
		}else{
			iframe.hide();
		}
	}

} //  @jve:decl-index=0:visual-constraint="10,10"
