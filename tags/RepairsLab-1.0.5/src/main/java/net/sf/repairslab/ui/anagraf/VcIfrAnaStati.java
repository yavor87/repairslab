package net.sf.repairslab.ui.anagraf;

import java.awt.BorderLayout;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import net.sf.repairslab.control.CommonMetodBin;
import net.sf.repairslab.control.QryUtil;
import net.sf.repairslab.ui.VcMainFrame;
import net.sf.repairslab.ui.messages.Messages;
import net.sf.repairslab.util.VcJDBCTablePanel;

import org.apache.log4j.Logger;

public class VcIfrAnaStati extends JInternalFrame {
	
	static private Logger  logger = Logger.getLogger(VcIfrAnaStati.class.getName());

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private VcMainFrame parent = null;
	private VcJDBCTablePanel pnlTableAnaStati = null;
	private Connection con = null;
	/**
	 * This is the xxx default constructor
	 */
	public VcIfrAnaStati(VcMainFrame parent) {
		super();
		logger.debug("VcIfrAnaStati constructor..."); //$NON-NLS-1$
		this.parent = parent;
		con = CommonMetodBin.getConn();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(300, 200);
		this.setClosable(true);
		this.setTitle(Messages.getString("VcIfrAnaStati.titleStates")); //$NON-NLS-1$
		this.setContentPane(getJContentPane());
		this.addInternalFrameListener(new javax.swing.event.InternalFrameAdapter() {
			public void internalFrameClosed(
				javax.swing.event.InternalFrameEvent e) {
					try{
						logger.debug("Closing..."); //$NON-NLS-1$
						close();
					}catch(Exception e1){
						logger.error("Exception in Closing \n"+e1+"\n", e1); //$NON-NLS-1$ //$NON-NLS-2$
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
			jContentPane.add(getPnlTableAnaStati(), BorderLayout.CENTER);
		}
		return jContentPane;
	}

	/**
	 * This method initializes pnlTableAnaStati	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private VcJDBCTablePanel getPnlTableAnaStati() {
		if (pnlTableAnaStati == null) {
			//String[] updatableCol = {"nomeStato","descrizione","flagAttivo"};
			
			pnlTableAnaStati = new VcJDBCTablePanel(
					con,QryUtil.QRY_ANA_STATI,true) {

				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;
				
				protected void onDelete(){
					try {
						logger.debug("Deleting..."); //$NON-NLS-1$
						Statement smtp = con.createStatement();
						String query = "select count(*) from schede " + //$NON-NLS-1$
								"where idStato = "+getValueAt(currentRow(), 0); //$NON-NLS-1$
						ResultSet rs = smtp.executeQuery(query);
						while(rs.next()){
							int fk = rs.getInt(1);
							if(fk>0){
								System.out.println(fk);
								JOptionPane.showMessageDialog(getParent(),
										Messages.getString("VcIfrAnaStati.msgReferenced"), //$NON-NLS-1$
										Messages.getString("VcIfrAnaStati.msgTitleError"), JOptionPane.ERROR_MESSAGE); //$NON-NLS-1$
							}else{
								deleteRow(currentRow());
							}
						}
						rs.close();
						smtp.close();
					} catch (SQLException e) {
						logger.error("Exception in Deleting \n"+e+"\n", e); //$NON-NLS-1$ //$NON-NLS-2$
						e.printStackTrace();
					}					
				}
				
			};
			pnlTableAnaStati.setColumnLabel(0, Messages.getString("VcIfrAnaStati.qryId"));
			pnlTableAnaStati.setColumnLabel(1, Messages.getString("VcIfrAnaStati.qryName"));
			pnlTableAnaStati.setColumnLabel(2, Messages.getString("VcIfrAnaStati.qryDesc"));
			pnlTableAnaStati.setColumnLabel(3, Messages.getString("VcIfrAnaStati.qryFlag"));
			pnlTableAnaStati.createControlPanel();
			pnlTableAnaStati.setCheckBoxColumn(3,"S","N"); //$NON-NLS-1$ //$NON-NLS-2$
			
			
		}
		return pnlTableAnaStati;
	}
	
}
