package it.f2.gestRip.ui;

import it.f2.gestRip.EnvProperties;
import it.f2.util.ui.WindowUtil;
import it.f2.util.ui.cmb.TypeCmb;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.UIManager;
import javax.swing.JTextPane;
import java.awt.Rectangle;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;

import org.apache.log4j.Logger;
import javax.swing.JCheckBox;
import java.awt.event.KeyEvent;

/**
 * Questa classe rappresenta il JDialog per la gestione
 * delle opzioni di sistema creata con Eclipse Visual Editor.
 * 
 * @author ferraf01
 *
 */
public class VcDlgOptions extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JPanel pnlSettings = null;
	private JLabel lblFileLogo = null;
	private JButton btnDefDir = null;
	private File fileLogo = null;  //  @jve:decl-index=0:
	private JButton btnOk = null;
	private JButton btnCanc = null;
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
	/**
	 * This is the default constructor
	 */
	public VcDlgOptions(Frame owner) {
		super(owner, true);
		Logger.getRootLogger().debug("VcDlgOptions constructor...");
		this.parent = owner;
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(612, 532);
		this.setTitle("Opzioni");
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
			lblNoDoppiaCopia = new JLabel();
			lblNoDoppiaCopia.setBounds(new Rectangle(44, 363, 358, 20));
			lblNoDoppiaCopia.setText("Non Stampare in Doppia Copia");
			lblIndirizzo = new JLabel();
			lblIndirizzo.setBounds(new Rectangle(9, 255, 460, 18));
			lblIndirizzo.setText("Info indirizzo e telefono scheda stampata");
			lblInfoCliente = new JLabel();
			lblInfoCliente.setBounds(new Rectangle(10, 148, 430, 18));
			lblInfoCliente.setText("Informazioni per il cliente sulla scheda di manutenzione");
			lblLogo = new JLabel();
			lblLogo.setBounds(new Rectangle(10, 10, 238, 125));
			lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
			lblLogo.setDisplayedMnemonic(KeyEvent.VK_UNDEFINED);
			try{
				lblLogo.setIcon(new ImageIcon(getFileLogo().getPath()));
			}catch(NullPointerException e){
				lblLogo.setIcon(new ImageIcon(getClass().getResource(
					"/it/f2/gestRip/ui/img/logo64.png")));
			}
			lblLookAndFeel = new JLabel();
			lblLookAndFeel.setBounds(new Rectangle(72, 405, 135, 16));
			lblLookAndFeel.setFont(new java.awt.Font("Dialog",
					java.awt.Font.BOLD, 12));
			lblLookAndFeel.setText("Look And Feel");
			lblImgStyle = new JLabel();
			lblImgStyle.setBounds(new Rectangle(14, 405, 51, 44));
			lblImgStyle.setIcon(new ImageIcon(getClass().getResource(
					"/it/f2/gestRip/ui/img/style.png")));
			lblImgStyle
					.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
			lblImgStyle.setText("");
			lblFileLogo = new JLabel();
			lblFileLogo.setBounds(new Rectangle(255, 13, 138, 16));
			lblFileLogo.setFont(new java.awt.Font("Dialog", java.awt.Font.BOLD,
					12));
			lblFileLogo.setText("Logo");
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
		}
		return pnlSettings;
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
			btnDefDir.setText("Seleziona");
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

		int response = jfc.showDialog(this, "Seleziona");

		if (response == JFileChooser.APPROVE_OPTION) {
			File file = jfc.getSelectedFile();
			if (file != null) {
				if (file.isFile()) {
					setFileLogo(file);
					String curPath = new File("").getAbsolutePath();
					String filePath = getFileLogo().getAbsolutePath();
					try{
						if(filePath.substring(0,curPath.length()).
								equalsIgnoreCase(curPath)){
							filePath = 	filePath.substring(curPath.length()+1);
						}
					}catch(StringIndexOutOfBoundsException e){
						
					}
					EnvProperties.getInstance().setProperty(
							EnvProperties.FILELOGO,filePath);
					this.getTxpFileLogo().setText(filePath);
					this.lblLogo.setIcon(new ImageIcon(filePath));
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
			btnOk.setBounds(new Rectangle(231, 469, 93, 29));
			btnOk.setIcon(new ImageIcon(getClass().getResource(
					"/it/f2/gestRip/ui/img/filesave.png")));
			btnOk.setText("Salva");
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
			btnCanc.setBounds(new Rectangle(126, 469, 99, 29));
			btnCanc.setIcon(new ImageIcon(getClass().getResource(
					"/it/f2/gestRip/ui/img/button_cancel.png")));
			btnCanc.setText("Annulla");
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
		EnvProperties.getInstance().saveFileProperty();
		close();
	}

	/**
	 * This method initializes cmbLookAndFeel
	 * 
	 * @return javax.swing.JComboBox
	 */
	private JComboBox getCmbLookAndFeel() {
		if (cmbLookAndFeel == null) {
			cmbLookAndFeel = new JComboBox();
			cmbLookAndFeel.setBounds(new Rectangle(70, 422, 205, 24));
			cmbLookAndFeel
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							setLookAndFeel((TypeCmb) cmbLookAndFeel
									.getSelectedItem());
						}
					});
			Properties looks = new Properties();
			try {
				Logger.getRootLogger().debug("getCmbLookAndFeel...");
				TypeCmb seledted = null;
				/* Caricamento da file di property */
				String path = "conf"
						+ EnvProperties.FILE_SEPARETOR
						+ "LookAndFeel.properties";
				FileInputStream in = new FileInputStream(path);
				looks.load(in);
				Enumeration<?> enumeration = looks.propertyNames();
				loadCmbFlag = true;
				while (enumeration.hasMoreElements()) {
					String propName = (String) enumeration.nextElement();
					String[] propVals = looks.getProperty(propName).split("~");
					TypeCmb cmb1 = new TypeCmb();
					cmb1.setDesc(propVals[0]);
					cmb1.setValue(propVals[1]);
					cmbLookAndFeel.addItem(cmb1);
					if (EnvProperties.getInstance().getProperty(
							EnvProperties.LOOK).equals(cmb1.getValue()))
						seledted = cmb1;
				}
				/* Caricamento look di sistema */
				UIManager.LookAndFeelInfo[] info = UIManager
						.getInstalledLookAndFeels();
				for (int i = 0; i < info.length; i++) {
					TypeCmb cmb2 = new TypeCmb();
					cmb2.setDesc(info[i].getName());
					cmb2.setValue(info[i].getClassName());
					if (EnvProperties.getInstance().getProperty(
							EnvProperties.LOOK).equals(cmb2.getValue()))
						seledted = cmb2;
					cmbLookAndFeel.addItem(cmb2);
				}
				if (seledted != null)
					cmbLookAndFeel.setSelectedItem(seledted);
				loadCmbFlag = false;
			} catch (FileNotFoundException e) {
				Logger.getRootLogger().error("Exception getCmbLookAndFeel \n"+e+"\n");
				//e.printStackTrace();
			} catch (IOException e) {
				Logger.getRootLogger().error("Exception getCmbLookAndFeel \n"+e+"\n");
				//e.printStackTrace();
			}
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
			if (!EnvProperties.getInstance().getProperty(EnvProperties.LOOK)
					.equals(cmb.getValue())) {
				EnvProperties.getInstance().setProperty(EnvProperties.LOOK,
						cmb.getValue());
				JOptionPane.showMessageDialog(getParent(),
						"Salvare e riavviare "
								+ EnvProperties.getInstance().getProperty(
										EnvProperties.APPNAME)
								+ " per visualizzare in nuovo LookAndFeel",
						"Warning", JOptionPane.WARNING_MESSAGE);
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
			txpFileLogo.setFont(new java.awt.Font("Dialog",
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
			scpInfoCliente.setBounds(new Rectangle(9, 169, 587, 69));
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
			scpIndirizzo.setBounds(new Rectangle(9, 275, 589, 74));
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
			btnAdvOpt.setBounds(new Rectangle(333, 469, 140, 29));
			btnAdvOpt.setText("Opzioni Avanzate");
			btnAdvOpt.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					VcDlgAdvancedOptions dlgAo = new VcDlgAdvancedOptions(parent);
					WindowUtil.centerWindow(dlgAo);
					dlgAo.setVisible(true);
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
			chbNoDoppiaCopia.setBounds(new Rectangle(14, 364, 24, 21));
			String ck =  EnvProperties.getInstance().getProperty(
					EnvProperties.DOPPIACOPIA);
			if(ck.equals("N"))
				chbNoDoppiaCopia.setSelected(true);
			chbNoDoppiaCopia.addItemListener(new java.awt.event.ItemListener() {
				public void itemStateChanged(java.awt.event.ItemEvent e) {
					if(chbNoDoppiaCopia.isSelected())
						EnvProperties.getInstance().setProperty(
								EnvProperties.DOPPIACOPIA, "N");
					else
						EnvProperties.getInstance().setProperty(
								EnvProperties.DOPPIACOPIA, "S");
				}
			});
		}
		return chbNoDoppiaCopia;
	}

} //  @jve:decl-index=0:visual-constraint="10,10"
