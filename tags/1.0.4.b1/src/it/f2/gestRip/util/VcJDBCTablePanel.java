package it.f2.gestRip.util;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import org.apache.log4j.Logger;

import com.toedter.calendar.JDateChooserCellEditor;

public class VcJDBCTablePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JScrollPane scpJDBCTable = null;
	private JTable tblJDBCTable = null;
	private JPanel pnlContols = null;
	private JLabel lblRec = null;
	private JButton btnPageUp = null;
	private JButton btnRecUp = null;
	private JFormattedTextField txfRecNum = null;
	private JButton btnRecDown = null;
	private JButton btnPageDown = null;
	private JButton btnRefresh = null;
	private JDBCTableModel jtmJDBCTm = null;
	private JButton btnEdit = null;
	private JLabel lblPageNum = null;
	private JToolBar tlbEditTable = null;
	private JButton btnOk = null;
	private JButton btnDelete = null;
	private JButton btnRollback = null;
	
	private Connection con;
	private String query;
	private boolean updatable;
	private String[] updatableColumn;
	private mode modality = null;  //  @jve:decl-index=0:
	private ArrayList<Object[]> params;  //  @jve:decl-index=0:
	
	public static enum mode{
		update,view;
	};
	
	/**
	 * This is the default constructor
	 */
	public VcJDBCTablePanel(Connection con,String query,boolean updatable, String[] updatableColumn, ArrayList<Object[]> params) {
		super();
		this.con = con;
		this.query = query;
		this.updatable = updatable;
		this.updatableColumn = updatableColumn;
		this.params = params;
		this.modality = mode.view;
		initialize();
	}
	
	public VcJDBCTablePanel(Connection con,String query,boolean updatable) {
		this(con,query,updatable,null,null);
	}
	
	public VcJDBCTablePanel(Connection con,String query) {
		this(con,query,false,null,null);
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(300, 200);
		this.setLayout(new BorderLayout());
		this.add(getScpJDBCTable(), BorderLayout.CENTER);
		this.add(getPnlContols(), BorderLayout.SOUTH);
		this.add(getTlbEditTable(), BorderLayout.WEST);
	}
	
	public void createControlPanel(){
		getPnlContols().setVisible(true);
	}

	/**
	 * This method initializes scpJDBCTable	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getScpJDBCTable() {
		if (scpJDBCTable == null) {
			scpJDBCTable = new JScrollPane();
			scpJDBCTable.setViewportView(getTblJDBCTable());
			scpJDBCTable.setSize(10, 10);
		}
		return scpJDBCTable;
	}
	
	private JDBCTableModel getJtmJDBCTm(){
		if (jtmJDBCTm == null) {
			try {
				Logger.getRootLogger().debug("Calling JDBCTableModel...");
				jtmJDBCTm = new JDBCTableModel(con,query,false,updatableColumn,params);
			} catch (SQLException e) {
				Logger.getRootLogger().error("Exception in Calling JDBCTableModel \n"+e+"\n");
				//e.printStackTrace();
			}
			
		}
		return jtmJDBCTm;
	}
	
	public void setCheckBoxColumn(int col,String checkValue,String uncheckValue){
		getTblJDBCTable().getColumnModel().getColumn(col).
			setCellRenderer(new CheckBoxCellRenderer(
					getTblJDBCTable().getBackground(),checkValue,uncheckValue));
		
        getTblJDBCTable().getColumnModel().getColumn(col).
        	setCellEditor(new CheckBoxCellEditor(checkValue,uncheckValue));
        
        getTblJDBCTable().revalidate();
	}
	
	public void setLovColumn(int col,String queryLov,String queryRender,
			String colValue,String colLabel,int colPrecision){
		
		LovResultsBin lrb = new LovResultsBin();
		//lrb.load(getTblJDBCTable(),col,queryRender,colValue);
		
		getTblJDBCTable().getColumnModel().getColumn(col).
			setCellRenderer(new LovCellRenderer(getTblJDBCTable().
			getBackground(),lrb,queryRender,colValue,con));
		
        getTblJDBCTable().getColumnModel().getColumn(col).
        	setCellEditor(new LovCellEditor(lrb,queryLov,colValue,colLabel,con));
        
        TableColumn tblCol = getTblJDBCTable().getColumnModel().getColumn(col);
        tblCol.setWidth(colPrecision);
		tblCol.setPreferredWidth(colPrecision);
        
        getTblJDBCTable().revalidate();
	}
	
	public void setColPrecision(int col,int colPrecision){
		TableColumn tblCol = getTblJDBCTable().getColumnModel().getColumn(col);
        tblCol.setWidth(colPrecision);
		tblCol.setPreferredWidth(colPrecision);
	}
	
	public void setTableHeaderHeight(int height){
		Dimension d = new Dimension(getTblJDBCTable().getTableHeader().getWidth(),height);
		getTblJDBCTable().getTableHeader().setSize(d);
		getTblJDBCTable().getTableHeader().setPreferredSize(d);
		if(height == 0)
			getTblJDBCTable().getTableHeader().setVisible(false);
	}
	
	public int getIdxFromColName(String colName){
		int result = 0;
		for (int i=0;i<getJtmJDBCTm().getColumnCount();i++){
			TableColumn tblCol = getTblJDBCTable().getColumnModel().getColumn(i);
			if (((String)tblCol.getHeaderValue()).equalsIgnoreCase(colName)){
				result = i;
				return i;
			}
		}
		return result;
	}
	
	public void setColumnLabel(int column,String value){
		try{
			TableColumn tblCol = tblJDBCTable.getColumnModel().getColumn(column);
			tblCol.setHeaderValue(value);
		}catch(ArrayIndexOutOfBoundsException e){
			Logger.getRootLogger().debug("Non valid column header...");
		}
	}
	
	/**
	 * This method initializes tblJDBCTable	
	 * 	
	 * @return javax.swing.JTable	
	 */
	private JTable getTblJDBCTable() {
		if (tblJDBCTable == null) {
			tblJDBCTable = new JTable(getJtmJDBCTm());
			tblJDBCTable.setRowSorter(new TableRowSorter<TableModel>(getJtmJDBCTm()));
			tblJDBCTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			tblJDBCTable.setRowHeight(25);
			for (int i=0;i<getJtmJDBCTm().getColumnCount();i++){
				int colPrecision = getJtmJDBCTm().getColumnPrecision(i);
				TableColumn tblCol = tblJDBCTable.getColumnModel().getColumn(i);
				Class<?> colClass = getJtmJDBCTm().getColumnClass(i);

				tblCol.setWidth(colPrecision);
				tblCol.setPreferredWidth(colPrecision);

				if (colClass.equals(java.sql.Date.class) || colClass.equals(java.util.Date.class)){
					tblCol.setCellEditor(new JDateChooserCellEditor());
				}
			}
			
			tblJDBCTable.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
				public void mouseMoved(java.awt.event.MouseEvent e) {
					java.awt.Point p = e.getPoint();
				      // Locate the cell under the event location
				      int hitColumnIndex = tblJDBCTable.columnAtPoint(p);
				      int hitRowIndex = tblJDBCTable.rowAtPoint(p);
				      if (hitColumnIndex != -1 && hitRowIndex != -1){
				         Object obj = getJtmJDBCTm().getValueAt(
				        		 tblJDBCTable.convertRowIndexToModel(hitRowIndex),
				        		 tblJDBCTable.convertColumnIndexToModel(hitColumnIndex));
				         String tip = null;
				        if (obj != null)
				            tip = obj.toString();
				        tblJDBCTable.setToolTipText(tip);
				      }

				}
			});
			
			tblJDBCTable.getSelectionModel().addListSelectionListener(
				new ListSelectionListener() {
					public void valueChanged(ListSelectionEvent arg0) {
						getTxfRecNum().setText(""+(getTblJDBCTable().getSelectedRow()+1));
					}
				});
			getTblJDBCTable().getSelectionModel().setSelectionInterval(0,0);
		
		}
		return tblJDBCTable;
	}

	/**
	 * This method initializes pnlContols	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getPnlContols() {
		if (pnlContols == null) {
			lblPageNum = new JLabel();
			lblRec = new JLabel();
			lblRec.setText("Record: ");
			pnlContols = new JPanel();
			pnlContols.setVisible(false);
			pnlContols.setLayout(new FlowLayout());
			pnlContols.add(lblRec, null);
			pnlContols.add(getBtnPageUp(), null);
			pnlContols.add(getBtnRecUp(), null);
			pnlContols.add(getTxfRecNum(), null);
			pnlContols.add(getBtnRecDown(), null);
			pnlContols.add(getBtnPageDown(), null);
			pnlContols.add(lblPageNum, null);
			pnlContols.add(getBtnRefresh(), null);
			lblPageNum.setText("of "+getJtmJDBCTm().getRowCount());
		}
		return pnlContols;
	}

	/**
	 * This method initializes btnPageUp	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnPageUp() {
		if (btnPageUp == null) {
			btnPageUp = new JButton();
			btnPageUp.setToolTipText("First Record");
			btnPageUp.setIcon(new ImageIcon(getClass().getResource("/it/f2/gestRip/ui/img/2leftarrow.png")));
			btnPageUp.setMargin(new Insets(2, 2, 2, 3));
			btnPageUp.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					getTblJDBCTable().getSelectionModel().
						setSelectionInterval(0,0);
				}
			});
		}
		return btnPageUp;
	}

	/**
	 * This method initializes btnRecUp	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnRecUp() {
		if (btnRecUp == null) {
			btnRecUp = new JButton();
			btnRecUp.setToolTipText("Previous Record");
			btnRecUp.setIcon(new ImageIcon(getClass().getResource("/it/f2/gestRip/ui/img/previous.png")));
			btnRecUp.setMargin(new Insets(2, 2, 2, 3));
			btnRecUp.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					int selRow = getTblJDBCTable().getSelectedRow();
					if(selRow!=-1&&selRow>0)
						getTblJDBCTable().getSelectionModel().
							setSelectionInterval(selRow-1,selRow-1);
				}
			});
		}
		return btnRecUp;
	}

	/**
	 * This method initializes txfRecNum	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JFormattedTextField getTxfRecNum() {
		if (txfRecNum == null) {
			txfRecNum = new JFormattedTextField(NumberFormat.getIntegerInstance());
			txfRecNum.setHorizontalAlignment(JTextField.RIGHT);
			txfRecNum.setPreferredSize(new Dimension(50, 20));
			txfRecNum.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					int rowCount = getTblJDBCTable().getModel().getRowCount();
					int goTo = Integer.parseInt(getTxfRecNum().getText());
					if(goTo>0 && goTo < rowCount)
						getTblJDBCTable().getSelectionModel().
							setSelectionInterval(goTo-1,goTo-1);
					else if (goTo <= 0)
						getTblJDBCTable().getSelectionModel().
							setSelectionInterval(0,0);
					else if (goTo > rowCount-1)
						getTblJDBCTable().getSelectionModel().
							setSelectionInterval(rowCount-1,rowCount-1);
				}
			});
		}
		return txfRecNum;
	}

	/**
	 * This method initializes btnRecDown	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnRecDown() {
		if (btnRecDown == null) {
			btnRecDown = new JButton();
			btnRecDown.setToolTipText("Next Record");
			btnRecDown.setIcon(new ImageIcon(getClass().getResource("/it/f2/gestRip/ui/img/next.png")));
			btnRecDown.setMargin(new Insets(2, 2, 2, 3));
			btnRecDown.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					int selRow = getTblJDBCTable().getSelectedRow();
					int rowCount = getTblJDBCTable().getModel().getRowCount();
					if(selRow!=-1&&selRow<rowCount-1)
						getTblJDBCTable().getSelectionModel().
							setSelectionInterval(selRow+1,selRow+1);
				}
			});
		}
		return btnRecDown;
	}

	/**
	 * This method initializes btnPageDown	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnPageDown() {
		if (btnPageDown == null) {
			btnPageDown = new JButton();
			btnPageDown.setToolTipText("Last Record");
			btnPageDown.setIcon(new ImageIcon(getClass().getResource("/it/f2/gestRip/ui/img/2rightarrow.png")));
			btnPageDown.setMargin(new Insets(2, 2, 2, 3));
			btnPageDown.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					int rowCount = getTblJDBCTable().getModel().getRowCount();
						getTblJDBCTable().getSelectionModel().
							setSelectionInterval(rowCount-1,rowCount-1);
				}
			});
		}
		return btnPageDown;
	}

	/**
	 * This method initializes btnRefresh	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnRefresh() {
		if (btnRefresh == null) {
			btnRefresh = new JButton();
			btnRefresh.setToolTipText("Refresh");
			btnRefresh.setIcon(new ImageIcon(getClass().getResource("/it/f2/gestRip/ui/img/rebuild.png")));
			btnRefresh.setMargin(new Insets(2, 2, 2, 3));
			btnRefresh.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					refresh();
				}
			});
		}
		return btnRefresh;
	}
	
	public void refresh(){
		try {
			Logger.getRootLogger().debug("refreshing...");
			int selRow = currentRow();
			getJtmJDBCTm().refresh();
			getTblJDBCTable().getSelectionModel().setSelectionInterval(selRow,selRow);
		} catch (Exception e1) {
			Logger.getRootLogger().error("Exception in Srefreshing \n"+e1+"\n");
			//e1.printStackTrace();
		}
	}
	
	public void setParameters(ArrayList<Object[]> params){
    	this.params = params;
    	getJtmJDBCTm().setParameters(params);
    }
	
	public void setQuery(String query){
    	this.query = query;
    	getJtmJDBCTm().setQuery(query);
    }

	/**
	 * This method initializes btnEdit	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnEdit() {
		if (btnEdit == null) {
			btnEdit = new JButton();
			btnEdit.setToolTipText("Edit");
			btnEdit.setIcon(new ImageIcon(getClass().getResource("/it/f2/gestRip/ui/img/edit.png")));
			btnEdit.setMargin(new Insets(2, 2, 2, 3));
			if(!this.updatable) btnEdit.setEnabled(false);
			btnEdit.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					modality = mode.update;
					int selRow = getTblJDBCTable().getSelectedRow();
					getJtmJDBCTm().setEditable(true);
					btnEdit.setEnabled(false);
					getBtnOk().setEnabled(true);
					getBtnDelete().setEnabled(true);
					getBtnRollback().setEnabled(true);
					try {
						Logger.getRootLogger().debug("Editing...");
						getJtmJDBCTm().refresh();
					} catch (SQLException e1) {
						Logger.getRootLogger().error("Exception in Editing \n"+e+"\n");
						//e1.printStackTrace();
					}
					getTblJDBCTable().getSelectionModel().
						setSelectionInterval(selRow, selRow);
				}
			});
		}
		return btnEdit;
	}

	/**
	 * This method initializes tlbEditTable	
	 * 	
	 * @return javax.swing.JToolBar	
	 */
	private JToolBar getTlbEditTable() {
		if (tlbEditTable == null) {
			tlbEditTable = new JToolBar();
			tlbEditTable.setVisible(updatable);
			tlbEditTable.setOrientation(JToolBar.VERTICAL);
			tlbEditTable.add(getBtnEdit());
			tlbEditTable.add(getBtnDelete());
			tlbEditTable.add(getBtnOk());
			tlbEditTable.add(getBtnRollback());
		}
		return tlbEditTable;
	}

	/**
	 * This method initializes btnOk	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnOk() {
		if (btnOk == null) {
			btnOk = new JButton();
			btnOk.setEnabled(false);
			btnOk.setToolTipText("Save");
			btnOk.setIcon(new ImageIcon(getClass().getResource("/it/f2/gestRip/ui/img/filesave.png")));
			btnOk.setMargin(new Insets(2, 2, 2, 3));
			btnOk.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					commit();
				}
			});
		}
		return btnOk;
	}
	
	public void commit(){
		if(modality == mode.update){
			int selRow = getTblJDBCTable().getSelectedRow();
			try{
				getTblJDBCTable().getCellEditor().stopCellEditing();
			}catch(NullPointerException e1){}
			getJtmJDBCTm().setEditable(false);
			getBtnEdit().setEnabled(true);
			getBtnRollback().setEnabled(false);
			btnOk.setEnabled(false);
			getBtnDelete().setEnabled(false);
			getBtnRollback().setEnabled(false);
			try {
				Logger.getRootLogger().debug("Committing...");
				con.commit();
				getJtmJDBCTm().refresh();
			} catch (SQLException e1) {
				Logger.getRootLogger().error("Exception in Committing \n"+e1+"\n");
				//e1.printStackTrace();
			}
			getTblJDBCTable().getSelectionModel().
				setSelectionInterval(selRow, selRow);
			modality = mode.view;
		}
	}

	/**
	 * This method initializes btnDelete	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnDelete() {
		if (btnDelete == null) {
			btnDelete = new JButton();
			btnDelete.setToolTipText("Delete Row");
			btnDelete.setIcon(new ImageIcon(getClass().getResource("/it/f2/gestRip/ui/img/delete_table_row.png")));
			btnDelete.setMargin(new Insets(2, 2, 2, 3));
			btnDelete.setEnabled(false);
			btnDelete.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					onDelete();
				}
			});
		}
		return btnDelete;
	}
	
	protected void onDelete(){
		deleteRow(currentRow());
	}
	
	public void deleteRow(int rowToDelete){
		getJtmJDBCTm().deleteRow(rowToDelete);
		try {
			Logger.getRootLogger().debug("Deleting...");
			getJtmJDBCTm().refresh();
		} catch (SQLException e) {
			Logger.getRootLogger().error("Exception in Deleting \n"+e+"\n");
			//e.printStackTrace();
		}
		getTblJDBCTable().getSelectionModel().
			setSelectionInterval(rowToDelete, rowToDelete);
	}
	
	public int currentRow(){
		return getTblJDBCTable().getSelectedRow();
	}
	
	public Object getValueAt(int row, int col){
		return getTblJDBCTable().getValueAt(row, col);
	}

	/**
	 * This method initializes btnRollback	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnRollback() {
		if (btnRollback == null) {
			btnRollback = new JButton();
			btnRollback.setToolTipText("Save");
			btnRollback.setIcon(new ImageIcon(getClass().getResource("/it/f2/gestRip/ui/img/undo.png")));
			btnRollback.setMargin(new Insets(2, 2, 2, 3));
			btnRollback.setEnabled(false);
			btnRollback.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					rollback();
				}
			});
		}
		return btnRollback;
	}
	
	public void rollback(){
		if(modality == mode.update){
			int selRow = getTblJDBCTable().getSelectedRow();
			try{
				getTblJDBCTable().getCellEditor().stopCellEditing();
			}catch(NullPointerException e1){}
			getJtmJDBCTm().setEditable(false);
			getBtnEdit().setEnabled(true);
			getBtnOk().setEnabled(false);
			getBtnDelete().setEnabled(false);
			getBtnRollback().setEnabled(false);
			try {
				Logger.getRootLogger().debug("Rolbacking...");
				con.rollback();
				getJtmJDBCTm().refresh();
			} catch (SQLException e1) {
				Logger.getRootLogger().error("Exception in Rolbacking \n"+e1+"\n");
				//e1.printStackTrace();
			}
			getTblJDBCTable().getSelectionModel().setSelectionInterval(selRow, selRow);
		}
		modality = mode.view;
	}
	
	public mode getModality(){
		return this.modality;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
