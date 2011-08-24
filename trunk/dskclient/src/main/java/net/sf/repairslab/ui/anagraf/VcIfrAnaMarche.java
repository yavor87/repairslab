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

public class VcIfrAnaMarche extends JInternalFrame {
	
	static private Logger  logger = Logger.getLogger(VcIfrAnaMarche.class.getName());

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
	public VcIfrAnaMarche(VcMainFrame parent) {
		super();
		logger.debug("VcIfrAnaMarche constructor..."); //$NON-NLS-1$
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
		this.setTitle(Messages.getString("VcIfrAnaMarche.titleBrands")); //$NON-NLS-1$
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
			jContentPane.add(getPnlTableAnaMarche(), BorderLayout.CENTER);
		}
		return jContentPane;
	}
	
	private VcJDBCTablePanel getPnlTableAnaMarche() {
		if (pnlTableAnaMarche == null) {
			//String[] updatableCol = {"nomeStato","descrizione","Ultima modifica"};
			
			pnlTableAnaMarche = new VcJDBCTablePanel(con,QryUtil.QRY_ANA_MARCHI,true){
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;
				
				protected void onDelete(){
					boolean referenziato = false;
					try {
						logger.debug("Deleting..."); //$NON-NLS-1$
						Statement smtp = con.createStatement();
						String query = "select count(*) from " + QryUtil.TABLE_PREFIX + "modelli " + //$NON-NLS-1$
								"where idMarchi = "+getValueAt(currentRow(), 0); //$NON-NLS-1$
						ResultSet rs = smtp.executeQuery(query);
						while(rs.next()){
							int fk = rs.getInt(1);
							if(fk>0){
								referenziato = true;
							}
						}
						rs.close();
						smtp.close();
						smtp = con.createStatement();
						query = "select count(*) from " + QryUtil.TABLE_PREFIX + "schede " + //$NON-NLS-1$
								"where idMarca = "+getValueAt(currentRow(), 0); //$NON-NLS-1$
						rs = smtp.executeQuery(query);
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
									Messages.getString("VcIfrAnaMarche.msgReferenced"), //$NON-NLS-1$
									Messages.getString("VcIfrAnaMarche.msgTitleError"), JOptionPane.ERROR_MESSAGE); //$NON-NLS-1$
						}else{
							deleteRow(currentRow());
						}
					} catch (SQLException e) {
						logger.error("Exception in Deleting \n"+e+"\n", e); //$NON-NLS-1$ //$NON-NLS-2$
						//e.printStackTrace();
					}					
				}
			};
			pnlTableAnaMarche.createControlPanel();
			pnlTableAnaMarche.setColumnLabel(0, Messages.getString("VcIfrAnaMarche.qryId"));
			pnlTableAnaMarche.setColumnLabel(1, Messages.getString("VcIfrAnaMarche.qryName"));
			pnlTableAnaMarche.setColumnLabel(2, Messages.getString("VcIfrAnaMarche.qryDesc"));
			pnlTableAnaMarche.setColumnLabel(3, Messages.getString("VcIfrAnaMarche.qryFlag"));
			pnlTableAnaMarche.setCheckBoxColumn(3,"S","N"); //$NON-NLS-1$ //$NON-NLS-2$
		}
		return pnlTableAnaMarche;
	}

}
