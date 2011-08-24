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

public class VcIfrAnaModelli extends JInternalFrame {
	
	static private Logger  logger = Logger.getLogger(VcIfrAnaModelli.class.getName());

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private VcMainFrame parent = null;
	private VcJDBCTablePanel pnlTableAnaModelli = null;
	private Connection con = null;

	/**
	 * This is the xxx default constructor
	 */
	public VcIfrAnaModelli(VcMainFrame parent) {
		super();
		logger.debug("VcIfrAnaModelli constructor..."); //$NON-NLS-1$
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
		this.setTitle(Messages.getString("VcIfrAnaModelli.titleModels")); //$NON-NLS-1$
		this.setContentPane(getJContentPane());
		this.addInternalFrameListener(new javax.swing.event.InternalFrameAdapter() {
			public void internalFrameClosed(
				javax.swing.event.InternalFrameEvent e) {
					try{
						logger.debug("Closing..."); //$NON-NLS-1$
						close();
					}catch(Exception e1){
						logger.error("Exception in Set modality \n"+e1+"\n", e1); //$NON-NLS-1$ //$NON-NLS-2$
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
			jContentPane.add(getPnlTableAnaModelli(), BorderLayout.CENTER);
		}
		return jContentPane;
	}
	
	private VcJDBCTablePanel getPnlTableAnaModelli() {
		if (pnlTableAnaModelli == null) {
			//String[] updatableCol = {"nomeStato","descrizione","Ultima modifica"};
			
			pnlTableAnaModelli = new VcJDBCTablePanel(con,QryUtil.QRY_ANA_MODELLI,true){
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;
				
				protected void onDelete(){
					logger.debug("Deleting..."); //$NON-NLS-1$
					try {
						Statement smtp = con.createStatement();
						String query = "select count(*) from " + QryUtil.TABLE_PREFIX + "schede " + //$NON-NLS-1$
								"where idModello = "+getValueAt(currentRow(), 0); //$NON-NLS-1$
						ResultSet rs = smtp.executeQuery(query);
						while(rs.next()){
							int fk = rs.getInt(1);
							if(fk>0){
								System.out.println(fk);
								JOptionPane.showMessageDialog(getParent(),
										Messages.getString("VcIfrAnaModelli.msgReferenced"), //$NON-NLS-1$
										Messages.getString("VcIfrAnaModelli.msgTitleError"), JOptionPane.ERROR_MESSAGE); //$NON-NLS-1$
							}else{
								deleteRow(currentRow());
							}
						}
						rs.close();
						smtp.close();
					} catch (SQLException e) {
						logger.error("Exception in Deleting \n"+e+"\n, e"); //$NON-NLS-1$ //$NON-NLS-2$
						e.printStackTrace();
					}					
				}
			};
			pnlTableAnaModelli.setColumnLabel(0, Messages.getString("VcIfrAnaModelli.qryId"));
			pnlTableAnaModelli.setColumnLabel(1, Messages.getString("VcIfrAnaModelli.qryName"));
			pnlTableAnaModelli.setColumnLabel(2, Messages.getString("VcIfrAnaModelli.qryDesc"));
			pnlTableAnaModelli.setColumnLabel(3, Messages.getString("VcIfrAnaModelli.qryIdMarchi"));
			pnlTableAnaModelli.setColumnLabel(4, Messages.getString("VcIfrAnaModelli.qryIdTipoApp"));
			pnlTableAnaModelli.setColumnLabel(5, Messages.getString("VcIfrAnaModelli.qryFlag"));
			pnlTableAnaModelli.createControlPanel();
			pnlTableAnaModelli.setCheckBoxColumn(5,"S","N"); //$NON-NLS-1$ //$NON-NLS-2$
			
			String qryLovModelli = "select id,nome,descrizione from " + QryUtil.TABLE_PREFIX + "marchi " + //$NON-NLS-1$
					"where flagAttivo = 'S'"; //$NON-NLS-1$
			String qryRenderModelli = "select nome from " + QryUtil.TABLE_PREFIX + "marchi " + //$NON-NLS-1$
					"where id = "; //$NON-NLS-1$
			pnlTableAnaModelli.setLovColumn(3,qryLovModelli,qryRenderModelli,"id","nome",50); //$NON-NLS-1$ //$NON-NLS-2$
			
			String qryLovTipoApp = "select id,nome,descrizione from " + QryUtil.TABLE_PREFIX + "tipoapparecchiature " + //$NON-NLS-1$
					"where flagAttivo = 'S'"; //$NON-NLS-1$
			String qryRenderTipoApp = "select nome from " + QryUtil.TABLE_PREFIX + "tipoapparecchiature " + //$NON-NLS-1$
					"where id = "; //$NON-NLS-1$
			pnlTableAnaModelli.setLovColumn(4,qryLovTipoApp,qryRenderTipoApp,"id","nome",50); //$NON-NLS-1$ //$NON-NLS-2$
		}
		return pnlTableAnaModelli;
	}

}
