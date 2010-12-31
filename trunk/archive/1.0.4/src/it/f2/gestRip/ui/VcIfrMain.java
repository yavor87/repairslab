package it.f2.gestRip.ui;

import it.f2.gestRip.EnvConstants;
import it.f2.gestRip.control.CommonMetodBin;
import it.f2.gestRip.control.DbSchedaAction;
import it.f2.gestRip.control.PrintAction;
import it.f2.gestRip.ui.messages.Messages;
import it.f2.gestRip.util.LinksUtils;
import it.f2.util.ui.WindowUtil;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Locale;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

import org.apache.log4j.Logger;

public class VcIfrMain extends JInternalFrame implements ActionListener {

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
	private JButton BtnStatistic = null;
	private JEditorPane edpMainScreen = null;
	private Connection con = null;
	
	/**
	 * This is the xxx default constructor
	 */
	public VcIfrMain(VcMainFrame parent) {
		super();
		Logger.getRootLogger().debug("Loading Main.."); //$NON-NLS-1$
		this.parent = parent;
		this.con = CommonMetodBin.getConn();
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
		this.addInternalFrameListener(new javax.swing.event.InternalFrameAdapter() {
			public void internalFrameClosed(javax.swing.event.InternalFrameEvent e) {
				close();
			}
		});
	}
	
	private void close(){
		CommonMetodBin.closeConn(con);
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
			btnInsScheda.setMnemonic(KeyEvent.VK_N);
			btnInsScheda.setText(Messages.getString("VcIfrMain.btnNewSheet")); //$NON-NLS-1$
			btnInsScheda.setIcon(new ImageIcon(getClass().getResource("/it/f2/gestRip/ui/img/newtodo.png"))); //$NON-NLS-1$
			btnInsScheda.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent e) {
					VcDlgDetailScheda dialog = new VcDlgDetailScheda(parent,null,VcDlgDetailScheda.mode.insert,0);
					WindowUtil.centerWindow(dialog);
					dialog.setVisible(true);
				}}
			);
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
			btnListaSchede.setMnemonic(KeyEvent.VK_L);
			btnListaSchede.setText(Messages.getString("VcIfrMain.btnListSheet")); //$NON-NLS-1$
			btnListaSchede.setIcon(new ImageIcon(getClass().getResource("/it/f2/gestRip/ui/img/view_detailed.png"))); //$NON-NLS-1$
			btnListaSchede.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					VcIfrListaSchede iframe = new VcIfrListaSchede(parent);
					if(!parent.isJitOpen(iframe.getClass())){
						parent.addTab(Messages.getString("VcIfrMain.tabListSheet"), iframe); //$NON-NLS-1$
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
			btnViewScheda.setMnemonic(KeyEvent.VK_V);
			btnViewScheda.setText(Messages.getString("VcIfrMain.btnShowSheet")); //$NON-NLS-1$
			btnViewScheda.setIcon(new ImageIcon(getClass().getResource("/it/f2/gestRip/ui/img/fileopen.png"))); //$NON-NLS-1$
			btnViewScheda.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					String mes = JOptionPane.showInputDialog(getParent(),Messages.getString("VcIfrMain.msgIsnNumberSheet")); //$NON-NLS-1$
					int num = 0;
					try{
						num = Integer.parseInt(mes);
						try {
							if(DbSchedaAction.existScheda(con,num)){
								openDetail(VcDlgDetailScheda.mode.view,num);
							}else{
								JOptionPane.showMessageDialog(getParent(),
										Messages.getString("VcIfrMain.msgSheetNotExist"), //$NON-NLS-1$
										Messages.getString("VcIfrMain.msgWarningTit"), JOptionPane.WARNING_MESSAGE); //$NON-NLS-1$
							}
						} catch (SQLException e1) {
							Logger.getRootLogger().error("Exception in Connectiond DB \n"+e+"\n"); //$NON-NLS-1$ //$NON-NLS-2$
							//e1.printStackTrace();
						}
						
					}catch(NumberFormatException ex){
						JOptionPane.showMessageDialog(getParent(),
								Messages.getString("VcIfrMain.msgSheetNumberNotValid"), //$NON-NLS-1$
								Messages.getString("VcIfrMain.msgWarningTit"), JOptionPane.WARNING_MESSAGE); //$NON-NLS-1$
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
			btnModScheda.setMnemonic(KeyEvent.VK_E);
			btnModScheda.setText(Messages.getString("VcIfrMain.btnUpdSheet")); //$NON-NLS-1$
			btnModScheda.setIcon(new ImageIcon(getClass().getResource(
				"/it/f2/gestRip/ui/img/kate.png"))); //$NON-NLS-1$
			btnModScheda.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					String mes = JOptionPane.showInputDialog(getParent(),Messages.getString("VcIfrMain.msgIsnNumberSheet")); //$NON-NLS-1$
					int num = 0;
					try{
						num = Integer.parseInt(mes);
						try {
							if(DbSchedaAction.existScheda(con,num)){
								openDetail(VcDlgDetailScheda.mode.update,num);
							}else{
								JOptionPane.showMessageDialog(getParent(),
										Messages.getString("VcIfrMain.msgSheetNotExist"), //$NON-NLS-1$
										Messages.getString("VcIfrMain.msgWarningTit"), JOptionPane.WARNING_MESSAGE); //$NON-NLS-1$
							}
						} catch (SQLException e1) {
							Logger.getRootLogger().error("Exception in Connectiond DB \n"+e+"\n"); //$NON-NLS-1$ //$NON-NLS-2$
							//e1.printStackTrace();
						}
						
					}catch(NumberFormatException ex){
						JOptionPane.showMessageDialog(getParent(),
								Messages.getString("VcIfrMain.msgSheetNumberNotValid"), //$NON-NLS-1$
								Messages.getString("VcIfrMain.msgWarningTit"), JOptionPane.WARNING_MESSAGE); //$NON-NLS-1$
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
			brnStampaScheda.setMnemonic(KeyEvent.VK_P);
			brnStampaScheda.setText(Messages.getString("VcIfrMain.btnPrintSheet")); //$NON-NLS-1$
			brnStampaScheda.setIcon(new ImageIcon(getClass().getResource(
				"/it/f2/gestRip/ui/img/fileprint_.png"))); //$NON-NLS-1$
			brnStampaScheda.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					String mes = JOptionPane.showInputDialog(getParent(),Messages.getString("VcIfrMain.msgIsnNumberSheet")); //$NON-NLS-1$
					int num = 0;
					try{
						num = Integer.parseInt(mes);
						try {
							if(DbSchedaAction.existScheda(con,num)){
								print(num);
							}else{
								JOptionPane.showMessageDialog(getParent(),
										Messages.getString("VcIfrMain.msgSheetNotExist"), //$NON-NLS-1$
										Messages.getString("VcIfrMain.msgWarningTit"), JOptionPane.WARNING_MESSAGE); //$NON-NLS-1$
							}
						} catch (SQLException e1) {
							Logger.getRootLogger().error("Exception in Connectiond DB \n"+e+"\n"); //$NON-NLS-1$ //$NON-NLS-2$
							//e1.printStackTrace();
						}
						
					}catch(NumberFormatException ex){
						JOptionPane.showMessageDialog(getParent(),
								Messages.getString("VcIfrMain.msgSheetNumberNotValid"), //$NON-NLS-1$
								Messages.getString("VcIfrMain.msgWarningTit"), JOptionPane.WARNING_MESSAGE); //$NON-NLS-1$
					}
				}
			});
		}
		return brnStampaScheda;
	}
	
	private void print(int nScheda){
		Logger.getRootLogger().debug("printing"); //$NON-NLS-1$
		PrintAction pa = new PrintAction();
		pa.callReportRicevuta(this.parent,nScheda,con);
	}

	/**
	 * This method initializes edpMainScreen	
	 * 	
	 * @return javax.swing.JEditorPane	
	 */
	private JEditorPane getEdpMainScreen() {
		if (edpMainScreen == null) {
			
			String mainScreenFile = "mainScreen_en.html";
			if (Locale.getDefault().equals(Locale.ITALY))
				mainScreenFile = "mainScreen_it.html";
			File html = new File( "resource" + System.getProperty("file.separator") +  mainScreenFile);
			edpMainScreen = new JEditorPane();
			edpMainScreen.setContentType("text/html"); //$NON-NLS-1$
			edpMainScreen.addHyperlinkListener(new HyperlinkListener(){
                public void hyperlinkUpdate(HyperlinkEvent e) {
					if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
						if (Locale.getDefault().equals(Locale.ITALY))
							LinksUtils.openUrl(edpMainScreen.getParent(), EnvConstants.LINK_DONATE_IT);
						else
							LinksUtils.openUrl(edpMainScreen.getParent(), EnvConstants.LINK_DONATE_EN);
					}
                }
				
			});
			
			try {
				Logger.getRootLogger().debug("Setting main html page..."); //$NON-NLS-1$
				edpMainScreen.setPage(html.toURI().toURL());
			} catch (MalformedURLException e) {
				Logger.getRootLogger().error("Exception in Setting main html page\n"+e+"\n"); //$NON-NLS-1$ //$NON-NLS-2$
				//e.printStackTrace();
			} catch (IOException e) {
				Logger.getRootLogger().error("Exception in Setting main html page\n"+e+"\n"); //$NON-NLS-1$ //$NON-NLS-2$
				//e.printStackTrace();
			}
			edpMainScreen.setEditable(false);
			//HTMLEditorKit he = new HTMLEditorKit();
			//edpMainScreen.setEditorKit(he);
			
		}
		return edpMainScreen;
		

	}  //  @jve:decl-index=0:visual-constraint="10,10"

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}