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

public class VcIfrAnaClienti extends JInternalFrame {
	
	static private Logger  logger = Logger.getLogger(VcIfrAnaClienti.class.getName());

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private VcMainFrame parent = null;
	private VcJDBCTablePanel pnlTableAnaMarche = null;
	private Connection con = null;

	/**
	 * This is the xxx default constructor
	 */
	public VcIfrAnaClienti(VcMainFrame parent) {
		super();
		logger.debug("VcIfrAnaClienti constructor..."); //$NON-NLS-1$
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
		this.setTitle(Messages.getString("VcIfrAnaClienti.titleCustomers")); //$NON-NLS-1$
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
		if(checkCompleteUpdate())
			parent.closeTab(this);
	}
	
	public boolean checkCompleteUpdate(){
		boolean result = false;
		if(getPnlTableAnaClienti().getModality() == VcJDBCTablePanel.mode.update){
			int confirm = JOptionPane.showConfirmDialog(getParent(),
					Messages.getString("VcIfrAnaClienti.msgSave"), //$NON-NLS-1$
					Messages.getString("VcIfrAnaClienti.msgTitleInfo"), JOptionPane.INFORMATION_MESSAGE); //$NON-NLS-1$
			if (confirm == JOptionPane.OK_OPTION){
				getPnlTableAnaClienti().commit();
				result = true;
			} else if (confirm == JOptionPane.NO_OPTION){
				getPnlTableAnaClienti().rollback();
				result = true;
			} else if (confirm == JOptionPane.CANCEL_OPTION){
				result = false;
			}
		} else {
			result = true;
		}
		return result;
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
			jContentPane.add(getPnlTableAnaClienti(), BorderLayout.CENTER);
		}
		return jContentPane;
	}
	
	private VcJDBCTablePanel getPnlTableAnaClienti() {
		if (pnlTableAnaMarche == null) {
			
			pnlTableAnaMarche = new VcJDBCTablePanel(con,QryUtil.QRY_ANA_CLIENTI,true){

				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;
						
				protected void onDelete(){
					boolean referenziato = false;
					try {
						logger.debug("Deleting..."); //$NON-NLS-1$
						Statement smtp = con.createStatement();
						String query = "select count(*) from " + QryUtil.TABLE_PREFIX + "schede " + //$NON-NLS-1$
								"where idCliente = "+getValueAt(currentRow(), 0); //$NON-NLS-1$
						ResultSet rs = smtp.executeQuery(query);
						while(rs.next()){
							int fk = rs.getInt(1);
							if(fk>0){
								referenziato = true;
							}
						}
						rs.close();
						smtp.close();
						if(referenziato){
							JOptionPane.showMessageDialog(getParent(),
									Messages.getString("VcIfrAnaClienti.msgReferenced"), //$NON-NLS-1$
									Messages.getString("VcIfrAnaClienti.msgTitleError"), JOptionPane.ERROR_MESSAGE); //$NON-NLS-1$
						}else{
							deleteRow(currentRow());
						}
					} catch (SQLException e) {
						logger.error("Exception in Deleting \n"+e+"\n"); //$NON-NLS-1$ //$NON-NLS-2$
						//e.printStackTrace();
					}
				}
				
			};
			pnlTableAnaMarche.setColumnLabel(0, Messages.getString("VcIfrAnaClienti.qryId"));
			pnlTableAnaMarche.setColumnLabel(1, Messages.getString("VcIfrAnaClienti.qryNome"));
			pnlTableAnaMarche.setColumnLabel(2, Messages.getString("VcIfrAnaClienti.qryCognome"));
			pnlTableAnaMarche.setColumnLabel(3, Messages.getString("VcIfrAnaClienti.qryPiva"));
			pnlTableAnaMarche.setColumnLabel(4, Messages.getString("VcIfrAnaClienti.qryAzienda"));
			pnlTableAnaMarche.setColumnLabel(5, Messages.getString("VcIfrAnaClienti.qryPhone"));
			pnlTableAnaMarche.setColumnLabel(6, Messages.getString("VcIfrAnaClienti.qryMobilePhone"));
			pnlTableAnaMarche.setColumnLabel(7, Messages.getString("VcIfrAnaClienti.qryEmail"));
			pnlTableAnaMarche.setColumnLabel(8, Messages.getString("VcIfrAnaClienti.qryIndirizzo"));
			pnlTableAnaMarche.setColumnLabel(9, Messages.getString("VcIfrAnaClienti.qryCity"));
			pnlTableAnaMarche.createControlPanel();
		}
		return pnlTableAnaMarche;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
