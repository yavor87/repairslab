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

public class VcIfrAnaTipoDatiAcq extends JInternalFrame {
	
	static private Logger  logger = Logger.getLogger(VcIfrAnaTipoDatiAcq.class.getName());

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private VcMainFrame parent = null;
	private VcJDBCTablePanel pnlTableAnaTipoDatiAcq = null;
	private Connection con = null;

	/**
	 * This is the xxx default constructor
	 */
	public VcIfrAnaTipoDatiAcq(VcMainFrame parent) {
		super();
		logger.debug("VcDlgDetailScheda constructor..."); //$NON-NLS-1$
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
		this.setSize(300, 200);
		this.setClosable(true);
		this.setTitle(Messages.getString("VcIfrAnaTipoDatiAcq.titlePurchasing")); //$NON-NLS-1$
		this.setContentPane(getJContentPane());
		this.addInternalFrameListener(new javax.swing.event.InternalFrameAdapter() {
			public void internalFrameClosed(
				javax.swing.event.InternalFrameEvent e) {
					try{
						logger.debug("Closing..."); //$NON-NLS-1$
						close();
					}catch(Exception e1){
						logger.error("Exception in Closing \n"+e1+"\n"); //$NON-NLS-1$ //$NON-NLS-2$
						e1.printStackTrace();
					}
				}
			});
	}
	
	private void close(){
		//CommonMetodBin.getInstance().closeConn();
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
			jContentPane.add(getPnlTableAnaTipoDatiAcq(), BorderLayout.CENTER);
		}
		return jContentPane;
	}
	
	private VcJDBCTablePanel getPnlTableAnaTipoDatiAcq() {
		if (pnlTableAnaTipoDatiAcq == null) {
			
			pnlTableAnaTipoDatiAcq = new VcJDBCTablePanel(
					con,QryUtil.QRY_ANA_TIPO_DATI_ACQ,true){
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;
				
				protected void onDelete(){
					try {
						logger.debug("Deleting..."); //$NON-NLS-1$
						Statement smtp = con.createStatement();
						String query = "select count(*) from " + QryUtil.TABLE_PREFIX + "schede " + //$NON-NLS-1$
								"where idTipoApparecchiatura = "+getValueAt(currentRow(), 0); //$NON-NLS-1$
						ResultSet rs = smtp.executeQuery(query);
						while(rs.next()){
							int fk = rs.getInt(1);
							if(fk>0){
								System.out.println(fk);
								JOptionPane.showMessageDialog(getParent(),
										Messages.getString("VcIfrAnaTipoDatiAcq.msgReferenced"), //$NON-NLS-1$
										Messages.getString("VcIfrAnaTipoDatiAcq.msgTitleError"), JOptionPane.ERROR_MESSAGE); //$NON-NLS-1$
							}else{
								deleteRow(currentRow());
							}
						}
						rs.close();
						smtp.close();
					} catch (SQLException e) {
						logger.error("Exception in Deleting \n"+e+"\n"); //$NON-NLS-1$ //$NON-NLS-2$
						//e.printStackTrace();
					}					
				}
			};
			pnlTableAnaTipoDatiAcq.setColumnLabel(0, Messages.getString("VcIfrAnaTipoDatiAcq.qryId"));
			pnlTableAnaTipoDatiAcq.setColumnLabel(1, Messages.getString("VcIfrAnaTipoDatiAcq.qryName"));
			pnlTableAnaTipoDatiAcq.createControlPanel();
		}
		return pnlTableAnaTipoDatiAcq;
	}

}
