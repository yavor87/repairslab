package net.sf.repairslab.ui;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Locale;
import java.util.Properties;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import net.sf.repairslab.EnvProperties;
import net.sf.repairslab.ui.installwizard.VcDlgMetadataSetting;
import net.sf.repairslab.ui.messages.Messages;
import net.sf.repairslab.util.ui.WindowUtil;
import net.sf.repairslab.util.ui.cmb.TypeCmb;
import net.sf.repairslab.util.ui.txf.JTextFieldLimit;

import org.apache.log4j.Logger;

/**
 * Questa classe rappresenta il JDialog per la gestione
 * delle opzioni di sistema creata con Eclipse Visual Editor.
 * 
 * @author ferraf01
 *
 */
public class VcDlgOptions extends JDialog {
	
	static private Logger  logger = Logger.getLogger(VcDlgOptions.class.getName());

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JPanel pnlSettings = null;
	private JLabel lblFileLogo = null;
	private JButton btnDefDir = null;
	private File fileLogo = null;  //  @jve:decl-index=0:
	private JButton btnOk = null;
	private JButton btnCanc = null;
	private JTextField txpJasper = null;
	private JLabel lblJasper = null;
	private JLabel lblImgStyle = null;
	private JLabel lblLookAndFeel = null;
	private JComboBox cmbLookAndFeel = null;
	private boolean loadCmbFlag = false;
	private JTextPane txpFileLogo = null;
	private JLabel lblLogo = null;
	private JScrollPane scpInfoCliente = null;
	private JScrollPane scpIndirizzo = null;
	private JTextPane txpInfoCliente = null;
	private JTextPane txpIndirizzo = null;
	private JLabel lblInfoCliente = null;
	private JLabel lblIndirizzo = null;
	private JButton btnAdvOpt = null;
	private Frame parent = null;
	private JCheckBox chbNoDoppiaCopia = null;
	private JLabel lblNoDoppiaCopia = null;
	private JComboBox cmbLanguage = null;
	private JComboBox cmbLocale = null;
	private JLabel lblLanguage = null;
	private JLabel lblPrefixNumber = null;
	private JTextField txfPrefixNumber = null;
	private JLabel lblLocale = null;
	private JRadioButton rbtReportTypeDefault = null;
	private JRadioButton rbtReportTypeMysql = null;
	private JRadioButton rbtReportTypeCustom = null;
	private ButtonGroup buttonGroup = null;  //  @jve:decl-index=0:visual-constraint="690,563"
	
	/**
	 * This is the default constructor
	 */
	public VcDlgOptions(Frame owner) {
		super(owner, true);
		logger.debug("VcDlgOptions constructor..."); //$NON-NLS-1$
		this.parent = owner;
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(627, 664);
		this.setTitle(Messages.getString("VcDlgOptions.titleOptions")); //$NON-NLS-1$
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
			jContentPane.add(getPnlSettings(), java.awt.BorderLayout.CENTER);
		}
		return jContentPane;
	}
	
	/**
	 * This method initializes pnlSettings
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getPnlSettings() {
		if (pnlSettings == null) {
			lblPrefixNumber = new JLabel();
			lblPrefixNumber.setBounds(new Rectangle(11, 146, 156, 16));
			lblPrefixNumber.setText(Messages.getString("VcDlgOptions.prexSheetLbl"));
			lblLanguage = new JLabel();
			lblLanguage.setBounds(new Rectangle(250, 431, 162, 16));
			lblLanguage.setText(Messages.getString("VcDlgOptions.language")); //$NON-NLS-1$
			lblLocale = new JLabel();
			lblLocale.setBounds(new Rectangle(430, 431, 162, 16));
			lblLocale.setText(Messages.getString("VcDlgOptions.locale")); //$NON-NLS-1$
			lblNoDoppiaCopia = new JLabel();
			lblNoDoppiaCopia.setBounds(new Rectangle(44, 389, 358, 20));
			lblNoDoppiaCopia.setText(Messages.getString("VcDlgOptions.lblDuplicatePrint")); //$NON-NLS-1$
			lblIndirizzo = new JLabel();
			lblIndirizzo.setBounds(new Rectangle(9, 281, 460, 18));
			lblIndirizzo.setText(Messages.getString("VcDlgOptions.lblInfoAddress")); //$NON-NLS-1$
			lblInfoCliente = new JLabel();
			lblInfoCliente.setBounds(new Rectangle(10, 174, 430, 18));
			lblInfoCliente.setText(Messages.getString("VcDlgOptions.lblInfoCustomer")); //$NON-NLS-1$
			lblLogo = new JLabel();
			lblLogo.setBounds(new Rectangle(10, 10, 238, 125));
			lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
			lblLogo.setDisplayedMnemonic(KeyEvent.VK_UNDEFINED);
			try{
				ImageIcon img = new ImageIcon(getFileLogo().getPath());
				lblLogo.setIcon(scale(img, lblLogo.getWidth(), lblLogo.getHeight()));
			}catch(NullPointerException e){
				lblLogo.setIcon(new ImageIcon(getClass().getResource("/net/sf/repairslab/ui/img/logo64.png"))); //$NON-NLS-1$
			}
			lblLookAndFeel = new JLabel();
			lblLookAndFeel.setBounds(new Rectangle(72, 431, 135, 16));
			lblLookAndFeel.setFont(new java.awt.Font("Dialog", //$NON-NLS-1$
					java.awt.Font.BOLD, 12));
			lblLookAndFeel.setText(Messages.getString("VcDlgOptions.lblLookAndFeel")); //$NON-NLS-1$
			lblImgStyle = new JLabel();
			lblImgStyle.setBounds(new Rectangle(14, 431, 51, 44));
			lblImgStyle.setIcon(new ImageIcon(getClass().getResource("/net/sf/repairslab/ui/img/style.png"))); //$NON-NLS-1$
			lblImgStyle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
			lblImgStyle.setText(""); //$NON-NLS-1$
			lblFileLogo = new JLabel();
			lblFileLogo.setBounds(new Rectangle(255, 13, 138, 16));
			lblFileLogo.setFont(new java.awt.Font("Dialog", java.awt.Font.BOLD,12)); //$NON-NLS-1$
			lblFileLogo.setText(Messages.getString("VcDlgOptions.lblLogo")); //$NON-NLS-1$
			pnlSettings = new JPanel();
			pnlSettings.setLayout(null);
			pnlSettings.add(lblFileLogo, null);
			pnlSettings.add(getBtnDefDir(), null);
			pnlSettings.add(getBtnOk(), null);
			pnlSettings.add(getBtnCanc(), null);
			pnlSettings.add(lblImgStyle, null);
			pnlSettings.add(lblLookAndFeel, null);
			pnlSettings.add(getCmbLookAndFeel(), null);
			pnlSettings.add(getTxpFileLogo(), null);
			pnlSettings.add(lblLogo, null);
			pnlSettings.add(getScpInfoCliente(), null);
			pnlSettings.add(getScpIndirizzo(), null);
			pnlSettings.add(lblInfoCliente, null);
			pnlSettings.add(lblIndirizzo, null);
			pnlSettings.add(getBtnAdvOpt(), null);
			pnlSettings.add(getChbNoDoppiaCopia(), null);
			pnlSettings.add(lblNoDoppiaCopia, null);
			pnlSettings.add(getCmbLanguage(), null);
			pnlSettings.add(getCmbLocale(), null);
			pnlSettings.add(lblLanguage, null);
			pnlSettings.add(lblPrefixNumber, null);
			pnlSettings.add(getTxfPrefixNumber(), null);
			pnlSettings.add(lblLocale, null);
			pnlSettings.add(getTxpJasper(), null);
			lblJasper = new JLabel();
			lblJasper.setBounds(new Rectangle(14, 485, 98, 30));
			lblJasper.setHorizontalAlignment(SwingConstants.RIGHT);
			lblJasper.setText(Messages.getString("VcDlgOptions.lblJasper")); //$NON-NLS-1$
			pnlSettings.add(lblJasper, null);

			pnlSettings.add(getRbtReportTypeDefault(), null);
			pnlSettings.add(getRbtReportTypeMysql(), null);
			pnlSettings.add(getRbtReportTypeCustom(), null);
			getButtonGroup();
		}
		return pnlSettings;
	}
	
	/**
	 * Scala l'immagine alla dimensione lable
	 * @author Fabrizio Ferraiuolo 24/set/2010 16.56.03
	 * @param srcIcon
	 * @param lblWidth
	 * @param lblHeight
	 * @return 
	 */
	private ImageIcon scale(ImageIcon srcIcon, int lblWidth, int lblHeight) {
		Image src = srcIcon.getImage();
		
		if (src.getWidth(this) <= lblWidth && src.getHeight(this) <= lblHeight)
			return srcIcon; // Non occorre lo stretch
		
		double percentW = (double)lblWidth / (double)src.getWidth(this);
		if(percentW > 1)
			percentW = 0;
		
		double percentH = (double)lblHeight / (double)src.getHeight(this);
		if(percentH > 1)
			percentH = 0;
		
		double scale = percentH;
		if (percentW > percentH)
			scale = percentW;
		
		if (scale <= 0)
			return srcIcon;
		
        int w = (int)(scale*src.getWidth(this));
        int h = (int)(scale*src.getHeight(this));
        int type = BufferedImage.TRANSLUCENT;
        BufferedImage dst = new BufferedImage(w, h, type);
        Graphics2D g2 = dst.createGraphics();
        g2.drawImage(src, 0, 0, w, h, this);
        g2.dispose();
        return new ImageIcon(dst);
    }


	/**
	 * This method initializes btnDefDir
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getBtnDefDir() {
		if (btnDefDir == null) {
			btnDefDir = new JButton();
			btnDefDir.setBounds(new Rectangle(496, 100, 94, 25));
			btnDefDir.setText(Messages.getString("VcDlgOptions.btnSelect")); //$NON-NLS-1$
			btnDefDir.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					selectFileLogo();
				}
			});
		}
		return btnDefDir;
	}

	
	/**
	 * Questo metodo apre il JFileChooser e permette di modificare
	 * il valore della variabile DEFAULTEDITDIR.
	 */
	private void selectFileLogo() {
		JFileChooser jfc = null;
		if (getFileLogo() == null)
			jfc = new JFileChooser();
		else
			jfc = new JFileChooser(getFileLogo());
		jfc.setAcceptAllFileFilterUsed(false);
		jfc.setFileSelectionMode(javax.swing.JFileChooser.FILES_ONLY);

		int response = jfc.showDialog(this, Messages.getString("VcDlgOptions.dlgTitleSelect")); //$NON-NLS-1$

		if (response == JFileChooser.APPROVE_OPTION) {
			File file = jfc.getSelectedFile();
			if (file != null) {
				if (file.isFile()) {
					setFileLogo(file);
					String curPath = new File("").getAbsolutePath(); //$NON-NLS-1$
					String filePath = getFileLogo().getAbsolutePath();
					try{
						if(filePath.substring(0,curPath.length()).equalsIgnoreCase(curPath)){
							filePath = 	filePath.substring(curPath.length()+1);
						}
					}catch(StringIndexOutOfBoundsException e){
						
					}
					EnvProperties.getInstance().setProperty(EnvProperties.FILELOGO,filePath);
					this.getTxpFileLogo().setText(filePath);
					this.lblLogo.setIcon(scale(new ImageIcon(filePath), lblLogo.getWidth(), lblLogo.getHeight()));
					}
			}
		}
	}

	/**
	 * Questo metodo ritorna il File che rappresenta la
	 * directory settata come default.
	 * @return
	 */
	private File getFileLogo() {
		if (fileLogo == null) {
			//System.out.println(EnvProperties.getInstance().getProperty(
			//		EnvProperties.FILELOGO));
			fileLogo = new File(EnvProperties.getInstance().getProperty(
					EnvProperties.FILELOGO));
			
			if (!fileLogo.isFile()) {
				fileLogo = null;
			}
		}
		return fileLogo;
	}

	/**
	 * Questo metodo setta il valore dell'attributo openDirectory.
	 * @param _value
	 */
	private void setFileLogo(File _value) {
		fileLogo = _value;
	}

	/**
	 * This method initializes btnOk
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getBtnOk() {
		if (btnOk == null) {
			btnOk = new JButton();
			btnOk.setBounds(new Rectangle(233, 588, 93, 29));
			btnOk.setIcon(new ImageIcon(getClass().getResource("/net/sf/repairslab/ui/img/filesave.png"))); //$NON-NLS-1$
			btnOk.setText(Messages.getString("VcDlgOptions.btnSave")); //$NON-NLS-1$
			btnOk.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					save();
				}
			});
		}
		return btnOk;
	}

	/**
	 * This method initializes btnCanc
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getBtnCanc() {
		if (btnCanc == null) {
			btnCanc = new JButton();
			btnCanc.setBounds(new Rectangle(128, 588, 99, 29));
			btnCanc.setIcon(new ImageIcon(getClass().getResource("/net/sf/repairslab/ui/img/button_cancel.png"))); //$NON-NLS-1$
			btnCanc.setText(Messages.getString("VcDlgOptions.btnCanc")); //$NON-NLS-1$
			btnCanc.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					close();
				}
			});
		}
		return btnCanc;
	}

	/**
	 * Effettua la chiusura della dialog.
	 */
	private void close() {
		this.dispose();
	}

	/**
	 * Effettua il salvataggio di EnvProperties e richiama la chiusura.
	 */
	private void save() {
		EnvProperties.getInstance().setProperty(EnvProperties.JASPER, getTxpJasper().getText());
		EnvProperties.getInstance().saveFileProperty();
		close();
		JOptionPane.showMessageDialog(getParent(),Messages.getString("VcDlgOptions.msgRestart"),Messages.getString("VcDlgOptions.msgTitleWarning"), JOptionPane.WARNING_MESSAGE); //$NON-NLS-1$
	}

	/**
	 * This method initializes cmbLookAndFeel
	 * 
	 * @return javax.swing.JComboBox
	 */
	private JComboBox getCmbLookAndFeel() {
		if (cmbLookAndFeel == null) {
			cmbLookAndFeel = new JComboBox();
			cmbLookAndFeel.setBounds(new Rectangle(70, 448, 150, 24));
			cmbLookAndFeel.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							setLookAndFeel((TypeCmb) cmbLookAndFeel.getSelectedItem());
						}
					});
			Properties looks = new Properties();
			logger.debug("getCmbLookAndFeel..."); //$NON-NLS-1$
			TypeCmb selected = null;
			
			/* Caricamento look di sistema */
			UIManager.LookAndFeelInfo[] info = UIManager.getInstalledLookAndFeels();
			for (int i = 0; i < info.length; i++) {
				TypeCmb cmb2 = new TypeCmb();
				cmb2.setDesc(info[i].getName());
				cmb2.setValue(info[i].getClassName());
				if (EnvProperties.getInstance().getProperty(EnvProperties.LOOK).equals(cmb2.getValue()))
					selected = cmb2;
				cmbLookAndFeel.addItem(cmb2);
			}
			if (selected != null)
				cmbLookAndFeel.setSelectedItem(selected);
			loadCmbFlag = false;
		}
		return cmbLookAndFeel;
	}

	/**
	 * Effettua la modifica del parametro LOOK e avverte l'utente
	 * che le modifiche saranno disponibili a prossimo riavvio.
	 * @param cmb
	 */
	private void setLookAndFeel(TypeCmb cmb) {
		if (!loadCmbFlag) {
			if (!EnvProperties.getInstance().getProperty(EnvProperties.LOOK).equals(cmb.getValue())) {
				EnvProperties.getInstance().setProperty(EnvProperties.LOOK,cmb.getValue());
			}
		}
	}

	/**
	 * This method initializes txpFileLogo
	 * 
	 * @return javax.swing.JTextPane
	 */
	private JTextPane getTxpFileLogo() {
		if (txpFileLogo == null) {
			txpFileLogo = new JTextPane();
			txpFileLogo.setBounds(new Rectangle(255, 33, 332, 62));
			txpFileLogo.setFont(new java.awt.Font("Dialog", //$NON-NLS-1$
					java.awt.Font.PLAIN, 12));
			txpFileLogo.setEditable(false);
			txpFileLogo.setText(EnvProperties.getInstance().getProperty(
					EnvProperties.FILELOGO));
		}
		return txpFileLogo;
	}
	/**
	 * This method initializes scpInfoCliente	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getScpInfoCliente() {
		if (scpInfoCliente == null) {
			scpInfoCliente = new JScrollPane();
			scpInfoCliente.setBounds(new Rectangle(9, 195, 587, 69));
			scpInfoCliente.setViewportView(getTxpInfoCliente());
		}
		return scpInfoCliente;
	}

	/**
	 * This method initializes scpIndirizzo	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getScpIndirizzo() {
		if (scpIndirizzo == null) {
			scpIndirizzo = new JScrollPane();
			scpIndirizzo.setBounds(new Rectangle(9, 301, 589, 74));
			scpIndirizzo.setViewportView(getTxpIndirizzo());
		}
		return scpIndirizzo;
	}

	/**
	 * This method initializes txpInfoCliente	
	 * 	
	 * @return javax.swing.JTextPane	
	 */
	private JTextPane getTxpInfoCliente() {
		if (txpInfoCliente == null) {
			txpInfoCliente = new JTextPane();
			txpInfoCliente.setText(EnvProperties.getInstance().getProperty(
					EnvProperties.INFOCLIENTE));
			txpInfoCliente.addFocusListener(new java.awt.event.FocusAdapter() {
				public void focusLost(java.awt.event.FocusEvent e) {
					EnvProperties.getInstance().setProperty(
							EnvProperties.INFOCLIENTE,txpInfoCliente.getText());
				}
			});
		}
		return txpInfoCliente;
	}

	/**
	 * This method initializes txpIndirizzo	
	 * 	
	 * @return javax.swing.JTextPane	
	 */
	private JTextPane getTxpIndirizzo() {
		if (txpIndirizzo == null) {
			txpIndirizzo = new JTextPane();
			txpIndirizzo.setText(EnvProperties.getInstance().getProperty(
					EnvProperties.INDIRIZZO));
			txpIndirizzo.addFocusListener(new java.awt.event.FocusAdapter() {
				public void focusLost(java.awt.event.FocusEvent e) {
					EnvProperties.getInstance().setProperty(
							EnvProperties.INDIRIZZO,txpIndirizzo.getText());
				}
			});
		}
		return txpIndirizzo;
	}

	/**
	 * This method initializes btnAdvOpt	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnAdvOpt() {
		if (btnAdvOpt == null) {
			btnAdvOpt = new JButton();
			btnAdvOpt.setBounds(new Rectangle(335, 588, 140, 29));
			btnAdvOpt.setText(Messages.getString("VcDlgOptions.btnMetadataSetting")); //$NON-NLS-1$
			btnAdvOpt.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
//					VcDlgAdvancedOptions dlgAo = new VcDlgAdvancedOptions(parent);
//					WindowUtil.centerWindow(dlgAo);
//					dlgAo.setVisible(true);
					VcDlgMetadataSetting dialog = new VcDlgMetadataSetting(parent);
					WindowUtil.centerWindow(dialog);
					dialog.setVisible(true);
				}
			});
		}
		return btnAdvOpt;
	}

	/**
	 * This method initializes chbNoDoppiaCopia	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	private JCheckBox getChbNoDoppiaCopia() {
		if (chbNoDoppiaCopia == null) {
			chbNoDoppiaCopia = new JCheckBox();
			chbNoDoppiaCopia.setBounds(new Rectangle(14, 390, 24, 21));
			String ck =  EnvProperties.getInstance().getProperty(EnvProperties.DOPPIACOPIA);
			if(ck.equals("N")) //$NON-NLS-1$
				chbNoDoppiaCopia.setSelected(true);
			chbNoDoppiaCopia.addItemListener(new java.awt.event.ItemListener() {
				public void itemStateChanged(java.awt.event.ItemEvent e) {
					if(chbNoDoppiaCopia.isSelected())
						EnvProperties.getInstance().setProperty(EnvProperties.DOPPIACOPIA, "N"); //$NON-NLS-1$
					else
						EnvProperties.getInstance().setProperty(EnvProperties.DOPPIACOPIA, "S"); //$NON-NLS-1$
				}
			});
		}
		return chbNoDoppiaCopia;
	}
	private JTextField getTxpJasper() {
		if (txpJasper == null) {
			txpJasper = new JTextField();
			txpJasper.setBounds(new Rectangle(215, 536, 240, 21));
			txpJasper.setText(EnvProperties.getInstance().getProperty(EnvProperties.JASPER));
			if (!getRbtReportTypeCustom().isSelected())
				txpJasper.setEnabled(false);
		}
		return txpJasper;
	}
	/**
	 * This method initializes cmbLanguage	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getCmbLanguage() {
		if (cmbLanguage == null) {
			cmbLanguage = new JComboBox();
			cmbLanguage.setBounds(new Rectangle(250, 448, 150, 24));
			String selVal = EnvProperties.getInstance().getProperty(EnvProperties.LANGUAGE);
			
			for (String key : Messages.getLanguageMaps().keySet()) {
				TypeCmb cmb = new TypeCmb();
//				System.out.println(key + " - " + Messages.getLanguageMaps().get(key));
				cmb.setValue(key);
				cmb.setDesc(Messages.getLanguageMaps().get(key));
				cmbLanguage.addItem(cmb);
				if(selVal.equals(key)) cmbLanguage.setSelectedItem(cmb);
			}
			
			cmbLanguage.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					EnvProperties.getInstance().setProperty(EnvProperties.LANGUAGE, 
							((TypeCmb)cmbLanguage.getSelectedItem()).getValue());
				}
			});
		}
		return cmbLanguage;
	}
	
	private JComboBox getCmbLocale() {
		if (cmbLocale == null) {
			cmbLocale = new JComboBox();
			cmbLocale.setBounds(new Rectangle(430, 448, 150, 24));
			String selVal = EnvProperties.getInstance().getProperty(EnvProperties.LOCALE);
			
			Locale locales[] = Locale.getAvailableLocales();

			Comparator<Locale> localeComparator = new Comparator<Locale>() {
				public int compare(Locale locale1, Locale locale2) {
					return locale1.toString().compareTo(locale2.toString());
				}
			};
			Arrays.sort(locales, localeComparator);

			for (Locale loc : locales) {
				TypeCmb cmb = new TypeCmb();
				if (!loc.getCountry().equals("") && !loc.getLanguage().equals("")) {
					String locId = loc.getLanguage() + "-" + loc.getCountry();
					cmb.setValue(locId);
					cmb.setDesc(loc.getDisplayName());
					cmbLocale.addItem(cmb);
					if(selVal.equals(locId)) 
						cmbLocale.setSelectedItem(cmb);
				}
				
			}
			
			cmbLocale.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					EnvProperties.getInstance().setProperty(EnvProperties.LOCALE, 
							((TypeCmb)cmbLocale.getSelectedItem()).getValue());
				}
			});
		}
		return cmbLocale;
	}

	/**
	 * This method initializes txfPrefixNumber	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxfPrefixNumber() {
		if (txfPrefixNumber == null) {
			txfPrefixNumber = new JTextField();
			txfPrefixNumber.setDocument(new JTextFieldLimit(7));
			String pref = EnvProperties.getInstance().getProperty(
					EnvProperties.PREFIX_NUM);
			if(pref!=null && pref.length()>7){
				pref = pref.substring(0,7);
				EnvProperties.getInstance().setProperty(
						EnvProperties.PREFIX_NUM, pref);
			}
			txfPrefixNumber.setText(pref);
			txfPrefixNumber.setBounds(new Rectangle(397, 144, 193, 25));
			txfPrefixNumber.addFocusListener(new java.awt.event.FocusAdapter() {
				public void focusLost(java.awt.event.FocusEvent e) {
					EnvProperties.getInstance().setProperty(
							EnvProperties.PREFIX_NUM,txfPrefixNumber.getText());
				}
			});
		}
		return txfPrefixNumber;
	}

	/**
     * This method initializes rbtReportType	
     * 	
     * @return javax.swing.JRadioButton	
     */
    private JRadioButton getRbtReportTypeDefault() {
    	if (rbtReportTypeDefault == null) {
    		rbtReportTypeDefault = new JRadioButton();
    		final String report = "RicevutaConsegnaApparato.jasper";
    		if (EnvProperties.getInstance().getProperty(EnvProperties.JASPER).equals(report))
    			rbtReportTypeDefault.setSelected(true);
    		rbtReportTypeDefault.setBounds(new Rectangle(122, 489, 68, 21));
    		rbtReportTypeDefault.setText("Default");
    		rbtReportTypeDefault.addItemListener(new java.awt.event.ItemListener() {
    			public void itemStateChanged(java.awt.event.ItemEvent e) {
    				getTxpJasper().setText(report);
    				getTxpJasper().setEnabled(false);
    			}
    		});
    	}
    	return rbtReportTypeDefault;
    }

	/**
     * This method initializes rbtReportTypeMysql	
     * 	
     * @return javax.swing.JRadioButton	
     */
    private JRadioButton getRbtReportTypeMysql() {
    	if (rbtReportTypeMysql == null) {
    		rbtReportTypeMysql = new JRadioButton();
    		final String report = "RicevutaConsegnaApparatoMySql.jasper";
    		if (EnvProperties.getInstance().getProperty(EnvProperties.JASPER).equals(report))
    			rbtReportTypeMysql.setSelected(true);
    		rbtReportTypeMysql.setBounds(new Rectangle(122, 512, 65, 24));
    		rbtReportTypeMysql.setText("MySql");
    		rbtReportTypeMysql.addItemListener(new java.awt.event.ItemListener() {
    			public void itemStateChanged(java.awt.event.ItemEvent e) {
    				getTxpJasper().setText(report);
    				getTxpJasper().setEnabled(false);
    			}
    		});
    	}
    	return rbtReportTypeMysql;
    }

	/**
     * This method initializes rbtReportTypeCustom	
     * 	
     * @return javax.swing.JRadioButton	
     */
    private JRadioButton getRbtReportTypeCustom() {
    	if (rbtReportTypeCustom == null) {
    		rbtReportTypeCustom = new JRadioButton();
    		if (!EnvProperties.getInstance().getProperty(EnvProperties.JASPER).equals("RicevutaConsegnaApparato.jasper")
    				&& !EnvProperties.getInstance().getProperty(EnvProperties.JASPER).equals("RicevutaConsegnaApparatoMySql.jasper"))
    			rbtReportTypeCustom.setSelected(true);
    		rbtReportTypeCustom.setBounds(new Rectangle(122, 536, 73, 24));
    		rbtReportTypeCustom.setText("Custom");
    		rbtReportTypeCustom.addItemListener(new java.awt.event.ItemListener() {
    			public void itemStateChanged(java.awt.event.ItemEvent e) {
    				getTxpJasper().setEnabled(true);
    			}
    		});
    	}
    	return rbtReportTypeCustom;
    }

	/**
     * This method initializes buttonGroup	
     * 	
     * @return javax.swing.ButtonGroup	
     */
    private ButtonGroup getButtonGroup() {
    	if (buttonGroup == null) {
    		buttonGroup = new ButtonGroup();
    		buttonGroup.add(getRbtReportTypeCustom());
    		buttonGroup.add(getRbtReportTypeDefault());
    		buttonGroup.add(getRbtReportTypeMysql());
    	}
    	return buttonGroup;
    }

} //  @jve:decl-index=0:visual-constraint="10,10"

 	  	 
