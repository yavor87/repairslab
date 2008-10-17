package it.f2.gestRip.ui;

import it.f2.gestRip.control.CommonMetodBin;
import it.f2.gestRip.control.DbSchedaAction;
import it.f2.gestRip.control.PrintAction;
import it.f2.gestRip.util.JDBCComboBoxModel;
import it.f2.gestRip.util.VcJDBCTablePanel;
import it.f2.util.ui.WindowUtil;
import it.f2.util.ui.cmb.TypeCmb;

import java.awt.BorderLayout;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;

import javax.swing.JPanel;
import javax.swing.JInternalFrame;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JToolBar;
import javax.swing.JButton;
import javax.swing.JScrollPane;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

import java.awt.Rectangle;
import java.awt.Dimension;
import java.awt.Point;
import javax.swing.SwingConstants;

import org.apache.log4j.Logger;

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
		Logger.getRootLogger().debug("VcIfrListaSchede constructor...");
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
		this.setTitle("Lista Schede");
		this.setContentPane(getJContentPane());
		this.addInternalFrameListener(new javax.swing.event.InternalFrameAdapter() {   
			public void internalFrameClosed(
				javax.swing.event.InternalFrameEvent e) {
					try{
						Logger.getRootLogger().debug("Closing...");
						close();
					}catch(Exception e1){
						Logger.getRootLogger().error("Exception in Closing \n"+e+"\n");
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
			btnView.setText("Visualizza Scheda");
			btnView.setIcon(new ImageIcon(getClass().getResource(
				"/it/f2/gestRip/ui/img/view_remove.png")));
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
			String qry = "SELECT "
				+ "schede.id                 \"Numero\", "
				+ "anastati.nomeStato        \"Stato\", "
				+ "schede.dataInserimento    \"Data Ingresso\", "
				+ "schede.dataChiusura       \"Data Chiusura\", "
				+ "tipoapparecchiature.nome  \"Tipo\", "
				+ "marchi.nome               \"Marca\", "
				+ "modelli.nome              \"Modello\", "
				+ "clienti.nome              \"Nome Cliente\", "
				+ "clienti.cognome           \"Cognome Cliente\" "
				+ "FROM "
				+ "schede "
				+ "LEFT JOIN anastati on schede.idStato=anastati.id "
				+ "LEFT JOIN tipoapparecchiature on schede.idTipoApparecchiatura=tipoapparecchiature.id "
				+ "LEFT JOIN marchi on schede.idMarca=marchi.id "
				+ "LEFT JOIN modelli on schede.idModello=modelli.id "
				+ "LEFT JOIN clienti on schede.idCliente=clienti.id "
				+ "WHERE schede.deleted is null ";
			
			tblList = new VcJDBCTablePanel(con, qry, false,null,null);
			
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
			btnEdit.setText("Modifica Scheda");
			btnEdit.setIcon(new ImageIcon(getClass().getResource(
				"/it/f2/gestRip/ui/img/edit.png")));
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
			btnNew.setText("Inserisci Scheda");
			btnNew.setIcon(new ImageIcon(getClass().getResource(
				"/it/f2/gestRip/ui/img/insert_table_row.png")));
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
			btnDelete.setText("Elimina Scheda");
			btnDelete.setIcon(new ImageIcon(getClass().getResource(
				"/it/f2/gestRip/ui/img/delete_table_row.png")));
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
					"La scheda di riparazione n."+id_scheda+" verrà spostata nel cestino schede. Si desidera procedere?",
					"Info", JOptionPane.OK_CANCEL_OPTION);
			if (confirm == JOptionPane.OK_OPTION){
				try {
					Logger.getRootLogger().debug("Removing...");
					DbSchedaAction.cestinaScheda(con,id_scheda);
					con.commit();
					getTblList().refresh();
				} catch (SQLException e) {
					Logger.getRootLogger().error("Exception in Removing \n"+e+"\n");
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
			lblTipoAppa.setText("Tipo Apparecchio");
			lblSerial = new JLabel();
			lblSerial.setBounds(new Rectangle(643, 27, 89, 16));
			lblSerial.setHorizontalAlignment(SwingConstants.RIGHT);
			lblSerial.setText("Num di serie");
			lblDIA = new JLabel();
			lblDIA.setBounds(new Rectangle(452, 27, 38, 16));
			lblDIA.setHorizontalAlignment(SwingConstants.RIGHT);
			lblDIA.setText("a");
			lblDataIng = new JLabel();
			lblDataIng.setBounds(new Rectangle(182, 28, 132, 16));
			lblDataIng.setHorizontalAlignment(SwingConstants.RIGHT);
			lblDataIng.setText("Data ingresso da");
			lblParRic = new JLabel();
			lblParRic.setBounds(new Rectangle(6, 6, 157, 16));
			lblParRic.setText("Parametri di ricerca");
			lblNum = new JLabel();
			lblNum.setBounds(new Rectangle(6, 28, 76, 12));
			lblNum.setHorizontalAlignment(SwingConstants.RIGHT);
			lblNum.setText("N. Scheda");
			lblNomeCogn = new JLabel();
			lblNomeCogn.setBounds(new Rectangle(218, 53, 121, 20));
			lblNomeCogn.setHorizontalAlignment(SwingConstants.RIGHT);
			lblNomeCogn.setText("Nome e Cognome");
			lblStatoRip = new JLabel();
			lblStatoRip.setText("Stato Riparazione");
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
			btnFind.setText("Applica Filtro");
			btnFind.setIcon(new ImageIcon(getClass().getResource(
				"/it/f2/gestRip/ui/img/button_ok.png")));
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
					boolean filterCognome = (!getTxfCognome().getText().equals(""));
					boolean filterNome = (!getTxfNome().getText().equals(""));
					boolean filterDataIng = (dataIns!=null);
					boolean filterDataUsc = (dataUsc!=null);
					boolean filterSerial = (!getTxfSerial().getText().equals(""));
					boolean filterIdTipoAppa = (idTipoAppa!=999);
					
					String qry = "SELECT "
						+ "schede.id                 \"Numero\", "
						+ "anastati.nomeStato        \"Stato\", "
						+ "schede.dataInserimento    \"Data Ingresso\", "
						+ "schede.dataChiusura       \"Data Chiusura\", "
						+ "tipoapparecchiature.nome  \"Tipo\", "
						+ "marchi.nome               \"Marca\", "
						+ "modelli.nome              \"Modello\", "
						+ "clienti.nome              \"Nome Cliente\", "
						+ "clienti.cognome           \"Cognome Cliente\" "
						+ "FROM "
						+ "schede "
						+ "LEFT JOIN anastati on schede.idStato=anastati.id "
						+ "LEFT JOIN tipoapparecchiature on schede.idTipoApparecchiatura=tipoapparecchiature.id "
						+ "LEFT JOIN marchi on schede.idMarca=marchi.id "
						+ "LEFT JOIN modelli on schede.idModello=modelli.id "
						+ "LEFT JOIN clienti on schede.idCliente=clienti.id "
						+ "WHERE " 
						+ (filterNScheda ? "schede.id = :id AND " : "" )
						+ (filterIdstato ? "schede.idStato = :idSta AND " : "" )
						+ (filterCognome ? "clienti.cognome like :cognome AND " : "") 
						+ (filterNome ? "clienti.nome like :nome AND " : "")
						+ (filterDataIng ? "schede.dataInserimento >= :dataIns AND " : "")
						+ (filterDataUsc ? "schede.dataInserimento <= :dataUsc AND " : "")
						+ (filterSerial ? "schede.serial like :serial AND " : "")
						+ (filterIdTipoAppa ? "schede.idTipoApparecchiatura = :idTipoAppa AND " : "" )
						+ "1=1 "
						+ "AND schede.deleted is null ";
					//System.out.println(qry);
					getTblList().setQuery(qry);
					
					ArrayList<Object[]> params1 = new ArrayList<Object[]>();
					if (filterNScheda) {
						Object[] pId = new Object[2];
						pId[0] = "id";
						pId[1] = nScheda;
						params1.add(pId);
					}
					if (filterIdstato) {
						Object[] pStato = new Object[2];
						pStato[0] = "idSta";
						pStato[1] = idStato;
						params1.add(pStato);
					}
					if (filterCognome) {
						Object[] pCognome = new Object[2];
						pCognome[0] = "cognome";
						pCognome[1] = getTxfCognome().getText();
						params1.add(pCognome);
					}
					if (filterNome) {
						Object[] pNome = new Object[2];
						pNome[0] = "nome";
						pNome[1] = getTxfNome().getText();
						params1.add(pNome);
					}
					if (filterDataIng) {
						Object[] pDi = new Object[2];
						pDi[0] = "dataIns";
						pDi[1] = dataIns;
						params1.add(pDi);
					}
					if (filterDataUsc) {
						Object[] pDu = new Object[2];
						pDu[0] = "dataUsc";
						pDu[1] = dataUsc;
						params1.add(pDu);
					}
					if (filterSerial) {
						Object[] pSer = new Object[2];
						pSer[0] = "serial";
						pSer[1] = getTxfSerial().getText();
						params1.add(pSer);
					}
					if (filterIdTipoAppa) {
						Object[] pTa = new Object[2];
						pTa[0] = "idTipoAppa";
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
			String qry = "select id,nomeStato,flagAttivo from anastati";
			cmbStato.setModel(new JDBCComboBoxModel(
					con,qry,0+"","S"));
			cmbStato.setBounds(new Rectangle(123, 50, 87, 25));
			TypeCmb tAll = new TypeCmb();
			tAll.setValue("999");
			tAll.setDesc("TUTTI");
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
			btnReset.setText("Cancella Filtro");
			btnReset.setIcon(new ImageIcon(getClass().getResource(
				"/it/f2/gestRip/ui/img/button_cancel.png")));
			btnReset.setBounds(new Rectangle(150, 80, 135, 26));
			btnReset.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					String qry = "SELECT "
						+ "schede.id                 \"Numero\", "
						+ "anastati.nomeStato        \"Stato\", "
						+ "schede.dataInserimento    \"Data Ingresso\", "
						+ "schede.dataChiusura       \"Data Chiusura\", "
						+ "tipoapparecchiature.nome  \"Tipo\", "
						+ "marchi.nome               \"Marca\", "
						+ "modelli.nome              \"Modello\", "
						+ "clienti.nome              \"Nome Cliente\", "
						+ "clienti.cognome           \"Cognome Cliente\" "
						+ "FROM "
						+ "schede "
						+ "LEFT JOIN anastati on schede.idStato=anastati.id "
						+ "LEFT JOIN tipoapparecchiature on schede.idTipoApparecchiatura=tipoapparecchiature.id "
						+ "LEFT JOIN marchi on schede.idMarca=marchi.id "
						+ "LEFT JOIN modelli on schede.idModello=modelli.id "
						+ "LEFT JOIN clienti on schede.idCliente=clienti.id "
						+ "WHERE schede.deleted is null ";
					getTblList().setQuery(qry);
					getTblList().setParameters(null);
					getTblList().refresh();
					
					getTxfNumeroScheda().setText("");
					getCmbStato().setSelectedIndex(getCmbStato().getModel().getSize()-1);
					getTxfDataIngresso().setDate(null);
					getTxfDataUscita().setDate(null);
					getTxfCognome().setText("");
					getTxfNome().setText("");
					getTxfSerial().setText("");
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
			txfCognome.setText("");
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
			String qry = "select id,nome,flagAttivo from tipoapparecchiature";
			cmbTipoAppa.setModel(new JDBCComboBoxModel(
					con,qry,"","S"));
			TypeCmb tAll = new TypeCmb();
			tAll.setValue("999");
			tAll.setDesc("TUTTI");
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
			btnPrint.setText("Stampa Scheda");
			btnPrint.setIcon(new ImageIcon(getClass().getResource(
				"/it/f2/gestRip/ui/img/fileprint.png")));
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
		Logger.getRootLogger().debug("printing");
		PrintAction pa = new PrintAction();
		pa.callReportRicevuta(this.parent,nScheda,con);
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
