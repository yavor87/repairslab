package it.f2.gestRip.ui;

import it.f2.gestRip.EnvProperties;
import it.f2.gestRip.control.CommonMetodBin;
import it.f2.gestRip.control.DbSchedaAction;
import it.f2.gestRip.control.PrintAction;
import it.f2.gestRip.control.QryUtil;
import it.f2.gestRip.ui.messages.Messages;
import it.f2.gestRip.util.JDBCComboBoxModel;
import it.f2.gestRip.util.VcJDBCTablePanel;
import it.f2.util.ui.WindowUtil;
import it.f2.util.ui.cmb.TypeCmb;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;

import org.apache.log4j.Logger;

import com.toedter.calendar.JDateChooser;

public class VcIfrListaSchede extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JToolBar tldCommands = null;
	private JButton btnView = null;
	private JScrollPane ScpList = null;
	private VcJDBCTablePanel tblList = null;
	private VcMainFrame parent = null;
	private JButton btnEdit = null;
	private JButton btnNew = null;
	private JButton btnDelete = null;
	private JPanel pnlFilter = null;
	private JButton btnFind = null;
	private JLabel lblStatoRip = null;
	private JComboBox cmbStato = null;
	private JButton btnReset = null;
	private JTextField txfCognome = null;
	private JTextField txfNome = null;
	private JLabel lblNomeCogn = null;
	private JTextField txfNumeroScheda = null;
	private JLabel lblNum = null;
	private JDateChooser txfDataIngresso = null;
	private JDateChooser txfDataUscita = null;
	private JLabel lblParRic = null;
	private JLabel lblDataIng = null;
	private JLabel lblDIA = null;
	private JTextField txfSerial = null;
	private JLabel lblSerial = null;
	private JComboBox cmbTipoAppa = null;
	private JLabel lblTipoAppa = null;
	private JPanel pnlList = null;
	private JButton btnPrint = null;
	private Connection con = null;
	
	/**
	 * This is the xxx default constructor
	 */
	public VcIfrListaSchede(VcMainFrame parent) {
		super();
		Logger.getRootLogger().debug("VcIfrListaSchede constructor..."); //$NON-NLS-1$
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
		this.setSize(901, 571);
		this.setClosable(true);
		this.setTitle(Messages.getString("VcIfrListaSchede.titleListSheet") + " " + EnvProperties.getInstance().getProperty(EnvProperties.PREFIX_NUM)); //$NON-NLS-1$
		this.setContentPane(getJContentPane());
		this.addInternalFrameListener(new javax.swing.event.InternalFrameAdapter() {   
			public void internalFrameClosed(
				javax.swing.event.InternalFrameEvent e) {
					try{
						Logger.getRootLogger().debug("Closing..."); //$NON-NLS-1$
						close();
					}catch(Exception e1){
						Logger.getRootLogger().error("Exception in Closing \n"+e+"\n"); //$NON-NLS-1$ //$NON-NLS-2$
						//e1.printStackTrace();
					}
				}
			});
	}
	
	private void close(){
		CommonMetodBin.closeConn(con);
		parent.closeTab(this);
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
			jContentPane.add(getPnlFilter(), BorderLayout.NORTH);
			jContentPane.add(getPnlList(), BorderLayout.CENTER);
		}
		return jContentPane;
	}

	/**
	 * This method initializes tldCommands	
	 * 	
	 * @return javax.swing.JToolBar	
	 */
	private JToolBar getTldCommands() {
		if (tldCommands == null) {
			tldCommands = new JToolBar();
			tldCommands.setOrientation(JToolBar.HORIZONTAL);
			tldCommands.add(getBtnView());
			tldCommands.add(getBtnEdit());
			tldCommands.add(getBtnNew());
			tldCommands.add(getBtnDelete());
			tldCommands.add(getBtnPrint());
		}
		return tldCommands;
	}

	/**
	 * This method initializes btnView	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnView() {
		if (btnView == null) {
			btnView = new JButton();
			btnView.setText(Messages.getString("VcIfrListaSchede.btnShowSheet")); //$NON-NLS-1$
			btnView.setMnemonic(KeyEvent.VK_V);
			btnView.setIcon(new ImageIcon(getClass().getResource("/it/f2/gestRip/ui/img/view_remove.png"))); //$NON-NLS-1$
			btnView.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					try{
						int id_scheda = (Integer)getTblList().getValueAt(getTblList().currentRow(), 0);
						openDetail(VcDlgDetailScheda.mode.view,id_scheda);
					}catch(IndexOutOfBoundsException ex){}
				}
			});
		}
		return btnView;
	}

	/**
	 * This method initializes ScpList	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getScpList() {
		if (ScpList == null) {
			ScpList = new JScrollPane();
			ScpList.setViewportView(getTblList());
		}
		return ScpList;
	}

	/**
	 * This method initializes tblList	
	 * 	
	 * @return javax.swing.JTable	
	 */
	public VcJDBCTablePanel getTblList() {
		if (tblList == null) {
			tblList = new VcJDBCTablePanel(con, QryUtil.QRY_LISTA_SCHEDE_ALL, false,null,null);
			tblList.createControlPanel();
		}
		return tblList;
	}

	/**
	 * This method initializes btnEdit	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnEdit() {
		if (btnEdit == null) {
			btnEdit = new JButton();
			btnEdit.setText(Messages.getString("VcIfrListaSchede.btpUpdSheet")); //$NON-NLS-1$
			btnEdit.setMnemonic(KeyEvent.VK_E);
			btnEdit.setIcon(new ImageIcon(getClass().getResource("/it/f2/gestRip/ui/img/edit.png"))); //$NON-NLS-1$
			btnEdit.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					try{
						int id_scheda = (Integer)getTblList().getValueAt(getTblList().currentRow(), 0);
						openDetail(VcDlgDetailScheda.mode.update,id_scheda);
					}catch(IndexOutOfBoundsException ex){}
				}
			});
		}
		return btnEdit;
	}

	/**
	 * This method initializes btnNew	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnNew() {
		if (btnNew == null) {
			btnNew = new JButton();
			btnNew.setText(Messages.getString("VcIfrListaSchede.btnInsSheet")); //$NON-NLS-1$
			btnNew.setMnemonic(KeyEvent.VK_N);
			btnNew.setIcon(new ImageIcon(getClass().getResource("/it/f2/gestRip/ui/img/insert_table_row.png"))); //$NON-NLS-1$
			btnNew.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					openDetail(VcDlgDetailScheda.mode.insert,0);
				}
			});
		}
		return btnNew;
	}
	
	private void openDetail(VcDlgDetailScheda.mode modality,int id_scheda){
		VcDlgDetailScheda dialog = new VcDlgDetailScheda(parent,this,modality,id_scheda);
		WindowUtil.centerWindow(dialog);
		dialog.setVisible(true);
	}

	/**
	 * This method initializes btnDelete	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnDelete() {
		if (btnDelete == null) {
			btnDelete = new JButton();
			btnDelete.setText(Messages.getString("VcIfrListaSchede.btnDelSheet")); //$NON-NLS-1$
			btnDelete.setMnemonic(KeyEvent.VK_C);
			btnDelete.setIcon(new ImageIcon(getClass().getResource("/it/f2/gestRip/ui/img/delete_table_row.png"))); //$NON-NLS-1$
			btnDelete.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					deleteScheda();
				}
			});
		}
		return btnDelete;
	}
	
	private void deleteScheda(){
		try{
			int id_scheda = (Integer)getTblList().getValueAt(getTblList().currentRow(), 0);
			int confirm = JOptionPane.showConfirmDialog(getParent(),
					Messages.getString("VcIfrListaSchede.msgDelSheet1")+id_scheda+Messages.getString("VcIfrListaSchede.msgDelSheet2"), //$NON-NLS-1$ //$NON-NLS-2$
					Messages.getString("VcIfrListaSchede.msgTitleInfo"), JOptionPane.OK_CANCEL_OPTION); //$NON-NLS-1$
			if (confirm == JOptionPane.OK_OPTION){
				try {
					Logger.getRootLogger().debug("Removing..."); //$NON-NLS-1$
					DbSchedaAction.cestinaScheda(con,id_scheda);
					con.commit();
					getTblList().refresh();
				} catch (SQLException e) {
					Logger.getRootLogger().error("Exception in Removing \n"+e+"\n"); //$NON-NLS-1$ //$NON-NLS-2$
					//e.printStackTrace();
				}
			}
		}catch(IndexOutOfBoundsException ex){}
	}

	/**
	 * This method initializes pnlFilter	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getPnlFilter() {
		if (pnlFilter == null) {
			lblTipoAppa = new JLabel();
			lblTipoAppa.setBounds(new Rectangle(609, 53, 124, 16));
			lblTipoAppa.setHorizontalAlignment(SwingConstants.RIGHT);
			lblTipoAppa.setText(Messages.getString("VcIfrListaSchede.lblFilterEqp")); //$NON-NLS-1$
			lblSerial = new JLabel();
			lblSerial.setBounds(new Rectangle(643, 27, 89, 16));
			lblSerial.setHorizontalAlignment(SwingConstants.RIGHT);
			lblSerial.setText(Messages.getString("VcIfrListaSchede.lblFilterserial")); //$NON-NLS-1$
			lblDIA = new JLabel();
			lblDIA.setBounds(new Rectangle(452, 27, 38, 16));
			lblDIA.setHorizontalAlignment(SwingConstants.RIGHT);
			lblDIA.setText(Messages.getString("VcIfrListaSchede.lblFilterTo")); //$NON-NLS-1$
			lblDataIng = new JLabel();
			lblDataIng.setBounds(new Rectangle(182, 28, 132, 16));
			lblDataIng.setHorizontalAlignment(SwingConstants.RIGHT);
			lblDataIng.setText(Messages.getString("VcIfrListaSchede.lblFilterEntryDate")); //$NON-NLS-1$
			lblParRic = new JLabel();
			lblParRic.setBounds(new Rectangle(6, 6, 157, 16));
			lblParRic.setText(Messages.getString("VcIfrListaSchede.lblFilterSearchParam")); //$NON-NLS-1$
			lblNum = new JLabel();
			lblNum.setBounds(new Rectangle(6, 28, 76, 12));
			lblNum.setHorizontalAlignment(SwingConstants.RIGHT);
			lblNum.setText(Messages.getString("VcIfrListaSchede.lblFilterSheetNum")); //$NON-NLS-1$
			lblNomeCogn = new JLabel();
			lblNomeCogn.setBounds(new Rectangle(218, 53, 121, 20));
			lblNomeCogn.setHorizontalAlignment(SwingConstants.RIGHT);
			lblNomeCogn.setText(Messages.getString("VcIfrListaSchede.lblFilterNameSur")); //$NON-NLS-1$
			lblStatoRip = new JLabel();
			lblStatoRip.setText(Messages.getString("VcIfrListaSchede.lblFilterState")); //$NON-NLS-1$
			lblStatoRip.setHorizontalAlignment(SwingConstants.RIGHT);
			lblStatoRip.setBounds(new Rectangle(8, 55, 111, 16));
			pnlFilter = new JPanel();
			pnlFilter.setSize(100, 100);
			pnlFilter.setLayout(null);
			pnlFilter.setPreferredSize(new Dimension(100, 110));
			pnlFilter.add(lblStatoRip, null);
			pnlFilter.add(getCmbStato(), null);
			pnlFilter.add(getTxfCognome(), null);
			pnlFilter.add(getBtnFind(), null);
			pnlFilter.add(getBtnReset(), null);
			pnlFilter.add(getTxfNome(), null);
			pnlFilter.add(lblNomeCogn, null);
			pnlFilter.add(getTxfNumeroScheda(), null);
			pnlFilter.add(lblNum, null);
			pnlFilter.add(getTxfDataIngresso(), null);
			pnlFilter.add(getTxfDataUscita(), null);
			pnlFilter.add(lblParRic, null);
			pnlFilter.add(lblDataIng, null);
			pnlFilter.add(lblDIA, null);
			pnlFilter.add(getTxfSerial(), null);
			pnlFilter.add(lblSerial, null);
			pnlFilter.add(getCmbTipoAppa(), null);
			pnlFilter.add(lblTipoAppa, null);
		}
		return pnlFilter;
	}

	/**
	 * This method initializes btnFind	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnFind() {
		if (btnFind == null) {
			btnFind = new JButton();
			btnFind.setText(Messages.getString("VcIfrListaSchede.btnFilterApply")); //$NON-NLS-1$
			btnFind.setIcon(new ImageIcon(getClass().getResource( "/it/f2/gestRip/ui/img/button_ok.png"))); //$NON-NLS-1$
			btnFind.setBounds(new Rectangle(6, 80, 135, 26));
			btnFind.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					
					int nScheda = 0;
					try{
						nScheda = Integer.parseInt(getTxfNumeroScheda().getText());
					}catch(NullPointerException ex){
					}catch(NumberFormatException ex){
					}
					
					int idStato = 999;
					try{
						idStato = Integer.parseInt(((TypeCmb)getCmbStato().getSelectedItem()).getValue());
					}catch(NullPointerException ex){
					}catch(NumberFormatException ex){
					}
					
					Date dataIns = null;
					try{
						dataIns = new java.sql.Date(getTxfDataIngresso().getDate().getTime());
					}catch(NullPointerException ex){
					}catch(NumberFormatException ex){
					}
					
					Date dataUsc = null;
					try{
						dataUsc = new java.sql.Date(getTxfDataUscita().getDate().getTime());
					}catch(NullPointerException ex){
					}catch(NumberFormatException ex){
					}
					
					int idTipoAppa = 999;
					try{
						idTipoAppa = Integer.parseInt(((TypeCmb)getCmbTipoAppa().getSelectedItem()).getValue());
					}catch(NullPointerException ex){
					}catch(NumberFormatException ex){
					}
					
					boolean filterNScheda = (nScheda!=0);
					boolean filterIdstato = (idStato!=999);
					boolean filterCognome = (!getTxfCognome().getText().equals("")); //$NON-NLS-1$
					boolean filterNome = (!getTxfNome().getText().equals("")); //$NON-NLS-1$
					boolean filterDataIng = (dataIns!=null);
					boolean filterDataUsc = (dataUsc!=null);
					boolean filterSerial = (!getTxfSerial().getText().equals("")); //$NON-NLS-1$
					boolean filterIdTipoAppa = (idTipoAppa!=999);
					
					String qry = QryUtil.QRY_LISTA_SCHEDE_ALL 
						+ (filterNScheda ? " AND schede.id = :id " : "" ) //$NON-NLS-1$ //$NON-NLS-2$
						+ (filterIdstato ? " AND schede.idStato = :idSta " : "" ) //$NON-NLS-1$ //$NON-NLS-2$
						+ (filterCognome ? " AND clienti.cognome like :cognome " : "")  //$NON-NLS-1$ //$NON-NLS-2$
						+ (filterNome ? " AND clienti.nome like :nome " : "") //$NON-NLS-1$ //$NON-NLS-2$
						+ (filterDataIng ? " AND schede.dataInserimento >= :dataIns " : "") //$NON-NLS-1$ //$NON-NLS-2$
						+ (filterDataUsc ? " AND schede.dataInserimento <= :dataUsc " : "") //$NON-NLS-1$ //$NON-NLS-2$
						+ (filterSerial ? " AND schede.serial like :serial " : "") //$NON-NLS-1$ //$NON-NLS-2$
						+ (filterIdTipoAppa ? " AND schede.idTipoApparecchiatura = :idTipoAppa " : "" ); //$NON-NLS-1$ //$NON-NLS-2$
					//System.out.println(qry);
					
					getTblList().setQuery(qry);
					
					ArrayList<Object[]> params1 = new ArrayList<Object[]>();
					if (filterNScheda) {
						Object[] pId = new Object[2];
						pId[0] = "id"; //$NON-NLS-1$
						pId[1] = nScheda;
						params1.add(pId);
					}
					if (filterIdstato) {
						Object[] pStato = new Object[2];
						pStato[0] = "idSta"; //$NON-NLS-1$
						pStato[1] = idStato;
						params1.add(pStato);
					}
					if (filterCognome) {
						Object[] pCognome = new Object[2];
						pCognome[0] = "cognome"; //$NON-NLS-1$
						pCognome[1] = getTxfCognome().getText();
						params1.add(pCognome);
					}
					if (filterNome) {
						Object[] pNome = new Object[2];
						pNome[0] = "nome"; //$NON-NLS-1$
						pNome[1] = getTxfNome().getText();
						params1.add(pNome);
					}
					if (filterDataIng) {
						Object[] pDi = new Object[2];
						pDi[0] = "dataIns"; //$NON-NLS-1$
						pDi[1] = dataIns;
						params1.add(pDi);
					}
					if (filterDataUsc) {
						Object[] pDu = new Object[2];
						pDu[0] = "dataUsc"; //$NON-NLS-1$
						pDu[1] = dataUsc;
						params1.add(pDu);
					}
					if (filterSerial) {
						Object[] pSer = new Object[2];
						pSer[0] = "serial"; //$NON-NLS-1$
						pSer[1] = getTxfSerial().getText();
						params1.add(pSer);
					}
					if (filterIdTipoAppa) {
						Object[] pTa = new Object[2];
						pTa[0] = "idTipoAppa"; //$NON-NLS-1$
						pTa[1] = idTipoAppa;
						params1.add(pTa);
					}
					getTblList().setParameters(params1);
					getTblList().refresh();
				}
			});
		}
		return btnFind;
	}

	/**
	 * This method initializes cmbStato	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getCmbStato() {
		if (cmbStato == null) {
			cmbStato = new JComboBox();
			String qry = "select id,nomeStato,flagAttivo from anastati"; //$NON-NLS-1$
			cmbStato.setModel(new JDBCComboBoxModel(
					con,qry,0+"","S")); //$NON-NLS-1$ //$NON-NLS-2$
			cmbStato.setBounds(new Rectangle(123, 50, 87, 25));
			TypeCmb tAll = new TypeCmb();
			tAll.setValue("999"); //$NON-NLS-1$
			tAll.setDesc(Messages.getString("VcIfrListaSchede.cmbStatusAll")); //$NON-NLS-1$
			cmbStato.addItem(tAll);
			cmbStato.setSelectedItem(tAll);
		}
		return cmbStato;
	}

	/**
	 * This method initializes btnReset	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnReset() {
		if (btnReset == null) {
			btnReset = new JButton();
			btnReset.setText(Messages.getString("VcIfrListaSchede.btnFilterReset")); //$NON-NLS-1$
			btnReset.setIcon(new ImageIcon(getClass().getResource(
				"/it/f2/gestRip/ui/img/button_cancel.png"))); //$NON-NLS-1$
			btnReset.setBounds(new Rectangle(150, 80, 135, 26));
			btnReset.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					getTblList().setQuery(QryUtil.QRY_LISTA_SCHEDE_ALL);
					getTblList().setParameters(null);
					getTblList().refresh();
					
					getTxfNumeroScheda().setText(""); //$NON-NLS-1$
					getCmbStato().setSelectedIndex(getCmbStato().getModel().getSize()-1);
					getTxfDataIngresso().setDate(null);
					getTxfDataUscita().setDate(null);
					getTxfCognome().setText(""); //$NON-NLS-1$
					getTxfNome().setText(""); //$NON-NLS-1$
					getTxfSerial().setText(""); //$NON-NLS-1$
					getCmbTipoAppa().setSelectedIndex(getCmbTipoAppa().getModel().getSize()-1);
				}
			});
		}
		return btnReset;
	}

	/**
	 * This method initializes txfCognome	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxfCognome() {
		if (txfCognome == null) {
			txfCognome = new JTextField();
			txfCognome.setSize(152, 25);
			txfCognome.setText(""); //$NON-NLS-1$
			txfCognome.setLocation(new Point(446, 50));
		}
		return txfCognome;
	}

	/**
	 * This method initializes txfNome	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxfNome() {
		if (txfNome == null) {
			txfNome = new JTextField();
			txfNome.setBounds(new Rectangle(342, 50, 104, 25));
		}
		return txfNome;
	}

	/**
	 * This method initializes txfNumeroScheda	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxfNumeroScheda() {
		if (txfNumeroScheda == null) {
			txfNumeroScheda = new JTextField();
			txfNumeroScheda.setBounds(new Rectangle(85, 23, 81, 25));
		}
		return txfNumeroScheda;
	}
	
	private JDateChooser getTxfDataIngresso() {
		if (txfDataIngresso == null) {
			txfDataIngresso = new JDateChooser();
			txfDataIngresso.setBounds(new Rectangle(315, 23, 128, 25));
		}
		return txfDataIngresso;
	}
	
	private JDateChooser getTxfDataUscita() {
		if (txfDataUscita == null) {
			txfDataUscita = new JDateChooser();
			txfDataUscita.setBounds(new Rectangle(490, 23, 128, 25));
		}
		return txfDataUscita;
	}

	/**
	 * This method initializes txfSerial	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxfSerial() {
		if (txfSerial == null) {
			txfSerial = new JTextField();
			txfSerial.setBounds(new Rectangle(733, 23, 120, 25));
		}
		return txfSerial;
	}

	/**
	 * This method initializes cmbTipoAppa	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getCmbTipoAppa() {
		if (cmbTipoAppa == null) {
			cmbTipoAppa = new JComboBox();
			cmbTipoAppa.setBounds(new Rectangle(733, 50, 120, 25));
			String qry = "select id,nome,flagAttivo from tipoapparecchiature"; //$NON-NLS-1$
			cmbTipoAppa.setModel(new JDBCComboBoxModel(
					con,qry,"","S")); //$NON-NLS-1$ //$NON-NLS-2$
			TypeCmb tAll = new TypeCmb();
			tAll.setValue("999"); //$NON-NLS-1$
			tAll.setDesc(Messages.getString("VcIfrListaSchede.cmbEqpAll")); //$NON-NLS-1$
			cmbTipoAppa.addItem(tAll);
			cmbTipoAppa.setSelectedItem(tAll);
		}
		return cmbTipoAppa;
	}

	/**
	 * This method initializes pnlList	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getPnlList() {
		if (pnlList == null) {
			pnlList = new JPanel();
			pnlList.setLayout(new BorderLayout());
			pnlList.add(getTldCommands(), BorderLayout.NORTH);
			pnlList.add(getScpList(), BorderLayout.CENTER);
		}
		return pnlList;
	}

	/**
	 * This method initializes btnPrint	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnPrint() {
		if (btnPrint == null) {
			btnPrint = new JButton();
			btnPrint.setText(Messages.getString("VcIfrListaSchede.btnPrintSheet")); //$NON-NLS-1$
			btnPrint.setMnemonic(KeyEvent.VK_P);
			btnPrint.setIcon(new ImageIcon(getClass().getResource("/it/f2/gestRip/ui/img/fileprint.png"))); //$NON-NLS-1$
			btnPrint.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					try{
						int id_scheda = (Integer)getTblList().getValueAt(getTblList().currentRow(), 0);
						print(id_scheda);
					}catch(IndexOutOfBoundsException ex){}
				}
			});
		}
		return btnPrint;
	}
	
	private void print(int nScheda){
		Logger.getRootLogger().debug("printing"); //$NON-NLS-1$
		PrintAction pa = new PrintAction();
		pa.callReportRicevuta(this.parent,nScheda,con);
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
