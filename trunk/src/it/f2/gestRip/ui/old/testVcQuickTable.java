package it.f2.gestRip.ui.old;

import it.f2.gestRip.EnvProperties;
import it.f2.gestRip.ui.VcMainFrame;

import javax.swing.JPanel;
import javax.swing.JInternalFrame;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


import quick.dbtable.DBTable;

public class testVcQuickTable extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private VcMainFrame parent = null;
	private Connection con = null;

	/**
	 * This is the xxx default constructor
	 */
	public testVcQuickTable(VcMainFrame parent) {
		super();
		this.parent = parent;
		openCon();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(449, 302);
		this.setClosable(true);
		this.setTitle("Test Quick Table");
		this.setContentPane(getJContentPane());
		this.addInternalFrameListener(new javax.swing.event.InternalFrameAdapter() {
			public void internalFrameClosed(
				javax.swing.event.InternalFrameEvent e) {
					try{
						close();
					}catch(Exception e1){
						e1.printStackTrace();
					}
				}
			});
	}
	
	private void close(){
		try {
			if(con != null && !con.isClosed())
				con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		parent.closeTab(this);
	}
	
	private void openCon(){
		try {
			if(con == null || con.isClosed()){
				Class.forName(EnvProperties.getInstance().getProperty(EnvProperties.DB_DRIVER)).
					newInstance();
					
				con = DriverManager.getConnection(
						EnvProperties.getInstance().getProperty(EnvProperties.DB_URL), 
						EnvProperties.getInstance().getProperty(EnvProperties.DB_USER), 
						EnvProperties.getInstance().getProperty(EnvProperties.DB_PASSW));
				
				con.setAutoCommit(false);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			DBTable jTable = new DBTable();
			jContentPane.add(jTable);
			jTable.setConnection(con);
		    jTable.setSelectSql("select * from gestrip.anastati");
		    jTable.createControlPanel();
		    try {
		         //fetch the data from database to fill the table
		    	jTable.refresh();
		      }
		      catch(SQLException e) {
		         e.printStackTrace();
		      }
		}
		return jContentPane;
	}


}  //  @jve:decl-index=0:visual-constraint="10,10"
