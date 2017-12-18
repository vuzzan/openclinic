/**

*/
package com.model.dao;


import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Monitor;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

import swing2swt.layout.BorderLayout;

import org.eclipse.swt.widgets.Table;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.GridData;
import org.sql2o.Connection;

import com.DbHelper;
import com.openclinic.utils.Utils;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.wb.swt.SWTResourceManager;
public class DichvuListDlg extends Dialog {
	static Logger logger = LogManager.getLogger(DichvuListDlg.class.getName());
	protected Object result;
	protected Shell shell;

	private class TableLabelProviderDichvu extends LabelProvider implements ITableLabelProvider {
		public Image getColumnImage(Object element, int columnIndex) {
			return null;
		}
		public String getColumnText(Object element, int columnIndex) {
			if(element instanceof Dichvu){
				return ((Dichvu) element).getIndex(columnIndex);
			}
			return "";
		}
	}
	private static class ContentProviderDichvu implements IStructuredContentProvider {
		public Object[] getElements(Object inputElement) {
			if(inputElement instanceof ArrayList){
				return ((ArrayList) inputElement).toArray();
			}
			return new Object[0];
		}
		public void dispose() {
		}
		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		}
	}
	private Table tableDichvu;
	private TableViewer tableViewerDichvu;
	private List<Dichvu> listDataDichvu;
	private Text textSearchDichvu;
	private String textSearchDichvuString;
	public Dichvu objDichvu = null;
	public int typeDichvuDlg = TYPE_DLG_CHOOSEN; 
	public static final int TYPE_DLG_MANAGER = 0;
	public static final int TYPE_DLG_CHOOSEN = 1;

    public int intTypeDlgDichvu;
	public static final int TYPE_DLG_VIEW = 1;
	public static final int TYPE_DLG_EDIT = 2;
	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public DichvuListDlg(Shell parent, int style) {
		super(parent, style);
		setText("SWT Dialog");
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public Object open() {
		createContents();
		shell.open();
		shell.layout();
		Display display = getParent().getDisplay();
        //
        Monitor primary = display.getPrimaryMonitor();
		Rectangle bounds = primary.getBounds();
		Rectangle rect = shell.getBounds();
		int x = bounds.x + (bounds.width - rect.width) / 2;
		int y = bounds.y + (bounds.height - rect.height) / 2;
		shell.setLocation(x, y);
        
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		return result;
	}

	/**
	 * Create contents of the dialog.
	 */
	private void createContents() {
		shell = new Shell(getParent(), SWT.SHELL_TRIM | SWT.BORDER | SWT.PRIMARY_MODAL);
		shell.setImage(SWTResourceManager.getImage(DichvuDlg.class, "/png/list-2x.png"));
		shell.setSize(610, 340);
		shell.setText("Dichvu List View");
		shell.setLayout(new BorderLayout(0, 0));
		shell.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.keyCode==SWT.ESC){
					objDichvu = null;
				}
			}
		});
        
        Composite compositeInShellDichvu = new Composite(shell, SWT.NONE);
		compositeInShellDichvu.setLayout(new BorderLayout(0, 0));
		compositeInShellDichvu.setLayoutData(BorderLayout.CENTER);
        
		Composite compositeHeaderDichvu = new Composite(compositeInShellDichvu, SWT.NONE);
		compositeHeaderDichvu.setLayoutData(BorderLayout.NORTH);
		compositeHeaderDichvu.setLayout(new GridLayout(5, false));

		textSearchDichvu = new Text(compositeHeaderDichvu, SWT.BORDER);
		textSearchDichvu.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		textSearchDichvu.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.keyCode==13){
					reloadTableDichvu();
				}
			}
		});
		
		Button btnNewButtonSearchDichvu = new Button(compositeHeaderDichvu, SWT.NONE);
		btnNewButtonSearchDichvu.setImage(SWTResourceManager.getImage(DichvuDlg.class, "/png/media-play-2x.png"));
		btnNewButtonSearchDichvu.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));

		btnNewButtonSearchDichvu.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				reloadTableDichvu();
			}
		});
		Button btnNewButtonExportExcelDichvu = new Button(compositeHeaderDichvu, SWT.NONE);
		btnNewButtonExportExcelDichvu.setText("Export Excel");
		btnNewButtonExportExcelDichvu.setImage(SWTResourceManager.getImage(KhamBenhListDlg.class, "/png/spreadsheet-2x.png"));
		btnNewButtonExportExcelDichvu.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		btnNewButtonExportExcelDichvu.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				exportExcelTableDichvu();
			}
		});
		
		
		GridData gd_btnNewButtonDichvu = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnNewButtonDichvu.widthHint = 87;
		btnNewButtonSearchDichvu.setLayoutData(gd_btnNewButtonDichvu);
		btnNewButtonSearchDichvu.setText("Search");
        
		tableViewerDichvu = new TableViewer(compositeInShellDichvu, SWT.BORDER | SWT.FULL_SELECTION);
		tableDichvu = tableViewerDichvu.getTable();
		tableDichvu.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		tableDichvu.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.keyCode==SWT.F5){
					reloadTableDichvu();
                }
                if(e.keyCode==SWT.F4){
					editTableDichvu();
                }
				else if(e.keyCode==13){
					selectTableDichvu();
				}
                else if(e.keyCode==SWT.DEL){
					deleteTableDichvu();
				}
                else if(e.keyCode==SWT.F7){
					newItemDichvu();
				}
			}
		});
        tableDichvu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				selectTableDichvu();
			}
		});
        
		tableDichvu.setLinesVisible(true);
		tableDichvu.setHeaderVisible(true);
		tableDichvu.setLayoutData(BorderLayout.CENTER);

		TableColumn tbTableColumnDichvuMA_DVKT = new TableColumn(tableDichvu, SWT.LEFT);
		tbTableColumnDichvuMA_DVKT.setWidth(100);
		tbTableColumnDichvuMA_DVKT.setText("MA_DVKT");

		TableColumn tbTableColumnDichvuTEN_DVKT = new TableColumn(tableDichvu, SWT.LEFT);
		tbTableColumnDichvuTEN_DVKT.setWidth(100);
		tbTableColumnDichvuTEN_DVKT.setText("TEN_DVKT");

		TableColumn tbTableColumnDichvuMA_GIA = new TableColumn(tableDichvu, SWT.LEFT);
		tbTableColumnDichvuMA_GIA.setWidth(100);
		tbTableColumnDichvuMA_GIA.setText("MA_GIA");

		TableColumn tbTableColumnDichvuDON_GIA = new TableColumn(tableDichvu, SWT.RIGHT);
		tbTableColumnDichvuDON_GIA.setWidth(100);
		tbTableColumnDichvuDON_GIA.setText("DON_GIA");

		TableColumn tbTableColumnDichvuDON_GIA2 = new TableColumn(tableDichvu, SWT.RIGHT);
		tbTableColumnDichvuDON_GIA2.setWidth(100);
		tbTableColumnDichvuDON_GIA2.setText("DON_GIA2");

		TableColumn tbTableColumnDichvuQUYET_DINH = new TableColumn(tableDichvu, SWT.LEFT);
		tbTableColumnDichvuQUYET_DINH.setWidth(100);
		tbTableColumnDichvuQUYET_DINH.setText("QUYET_DINH");

		TableColumn tbTableColumnDichvuCONG_BO = new TableColumn(tableDichvu, SWT.LEFT);
		tbTableColumnDichvuCONG_BO.setWidth(100);
		tbTableColumnDichvuCONG_BO.setText("CONG_BO");

		TableColumn tbTableColumnDichvuNHOM_DV = new TableColumn(tableDichvu, SWT.RIGHT);
		tbTableColumnDichvuNHOM_DV.setWidth(100);
		tbTableColumnDichvuNHOM_DV.setText("NHOM_DV");

		TableColumn tbTableColumnDichvuMANHOM_9324 = new TableColumn(tableDichvu, SWT.LEFT);
		tbTableColumnDichvuMANHOM_9324.setWidth(100);
		tbTableColumnDichvuMANHOM_9324.setText("MANHOM_9324");

		TableColumn tbTableColumnDichvuHIEULUC = new TableColumn(tableDichvu, SWT.LEFT);
		tbTableColumnDichvuHIEULUC.setWidth(100);
		tbTableColumnDichvuHIEULUC.setText("HIEULUC");

		TableColumn tbTableColumnDichvuTYP = new TableColumn(tableDichvu, SWT.RIGHT);
		tbTableColumnDichvuTYP.setWidth(100);
		tbTableColumnDichvuTYP.setText("TYP");

		TableColumn tbTableColumnDichvuDV_RANK = new TableColumn(tableDichvu, SWT.RIGHT);
		tbTableColumnDichvuDV_RANK.setWidth(100);
		tbTableColumnDichvuDV_RANK.setText("DV_RANK");

		TableColumn tbTableColumnDichvuSTS = new TableColumn(tableDichvu, SWT.RIGHT);
		tbTableColumnDichvuSTS.setWidth(100);
		tbTableColumnDichvuSTS.setText("STS");

        Menu menuDichvu = new Menu(tableDichvu);
		tableDichvu.setMenu(menuDichvu);
		
		MenuItem mntmNewItemDichvu = new MenuItem(menuDichvu, SWT.NONE);
		mntmNewItemDichvu.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				newItemDichvu();
			}
		});
		mntmNewItemDichvu.setImage(SWTResourceManager.getImage(DichvuDlg.class, "/png/arrow-circle-top-2x.png"));
		mntmNewItemDichvu.setText("New");
		
		MenuItem mntmEditItemDichvu = new MenuItem(menuDichvu, SWT.NONE);
		mntmEditItemDichvu.setImage(SWTResourceManager.getImage(DichvuDlg.class, "/png/wrench-2x.png"));
		mntmEditItemDichvu.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				editTableDichvu();
			}
		});
		mntmEditItemDichvu.setText("Edit");
		
		MenuItem mntmDeleteDichvu = new MenuItem(menuDichvu, SWT.NONE);
		mntmDeleteDichvu.setImage(SWTResourceManager.getImage(DichvuDlg.class, "/png/circle-x-2x.png"));
		mntmDeleteDichvu.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				deleteTableDichvu();
			}
		});
		mntmDeleteDichvu.setText("Delete");
		
		MenuItem mntmExportDichvu = new MenuItem(menuDichvu, SWT.NONE);
		mntmExportDichvu.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				exportExcelTableDichvu();
			}
		});
		mntmExportDichvu.setImage(SWTResourceManager.getImage(DichvuDlg.class, "/png/spreadsheet-2x.png"));
		mntmExportDichvu.setText("Export Excel");
		
		tableViewerDichvu.setLabelProvider(new TableLabelProviderDichvu());
		tableViewerDichvu.setContentProvider(new ContentProviderDichvu());
		tableViewerDichvu.setInput(listDataDichvu);
        //
        //
		loadDataDichvu();
		//
        reloadTableDichvu();
	}
    public void setDataDichvu(String textSearchString){
		this.textSearchDichvuString = textSearchString;
	}
	private void loadDataDichvu() {
		if(textSearchDichvuString!=null){
			textSearchDichvu.setText(textSearchDichvuString);
		}
	}
	protected void exportExcelTableDichvu() {
        if(DbHelper.checkPhanQuyen(DbHelper.READ, "dichvu")==false){
			logger.info("DON'T HAVE READ RIGHT");
			return;
		}
        if(listDataDichvu!=null){
            // Export to EXCEL
    		
			StringBuffer buff_dichvu = new StringBuffer();
			String dichvu_filename = "dichvu_"+Utils.getDatetimeCurent().replaceAll(":", "_")+".xls";
			String delimiter = "</td><td>";
			// Get header...
			// Get header...
			buff_dichvu.append( "<table>");
			buff_dichvu.append( "<tr class='background-color:#dfdfdf'><td>");

			buff_dichvu.append( "MA_DVKT" +delimiter);
			buff_dichvu.append( "TEN_DVKT" +delimiter);
			buff_dichvu.append( "MA_GIA" +delimiter);
			buff_dichvu.append( "DON_GIA" +delimiter);
			buff_dichvu.append( "DON_GIA2" +delimiter);
			buff_dichvu.append( "QUYET_DINH" +delimiter);
			buff_dichvu.append( "CONG_BO" +delimiter);
			buff_dichvu.append( "NHOM_DV" +delimiter);
			buff_dichvu.append( "MANHOM_9324" +delimiter);
			buff_dichvu.append( "HIEULUC" +delimiter);
			buff_dichvu.append( "TYP" +delimiter);
			buff_dichvu.append( "DV_RANK" +delimiter);
			buff_dichvu.append( "STS");
			// End of header
			buff_dichvu.append( "</td></tr>");
			buff_dichvu.append( "\n");
			// Get data...
			for( Dichvu obj:  listDataDichvu){
				buff_dichvu.append( "<tr><td>");
				buff_dichvu.append( obj.MA_DVKT +delimiter);
				buff_dichvu.append( obj.TEN_DVKT +delimiter);
				buff_dichvu.append( obj.MA_GIA +delimiter);
				buff_dichvu.append( obj.DON_GIA +delimiter);
				buff_dichvu.append( obj.DON_GIA2 +delimiter);
				buff_dichvu.append( obj.QUYET_DINH +delimiter);
				buff_dichvu.append( obj.CONG_BO +delimiter);
				buff_dichvu.append( obj.NHOM_DV +delimiter);
				buff_dichvu.append( obj.MANHOM_9324 +delimiter);
				buff_dichvu.append( obj.HIEULUC +delimiter);
				buff_dichvu.append( obj.TYP +delimiter);
				buff_dichvu.append( obj.DV_RANK +delimiter);
				buff_dichvu.append( obj.STS );
				// End of header
				buff_dichvu.append( "</td></tr>");
			}
			//
			buff_dichvu.append( "</table>");
			Writer out = null;
			try {
				out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(dichvu_filename), "UTF-8"));
				out.write('\uFEFF'); // BOM for UTF-*
			    out.write(buff_dichvu.toString());
			} 
			catch(Exception ee){
				ee.printStackTrace();
			}
			finally {
			    try {
					out.close();
				} catch (Exception e) {
					e.printStackTrace();
					logger.error(e);
				}
			}
			//
            return;
        }
		// End of export
	}
	protected void reloadTableDichvu() {
        if(DbHelper.checkPhanQuyen(DbHelper.READ, "dichvu")==false){
			logger.info("DON'T HAVE READ RIGHT");
			return;
		}
    
        if(listDataDichvu!=null){
            // 
            tableViewerDichvu.setInput(listDataDichvu);
            tableViewerDichvu.refresh();
            //
            if(listDataDichvu.size()==0){
                textSearchDichvu.forceFocus();
            }
            else{
                tableDichvu.forceFocus();
                tableDichvu.setSelection(0);
            }
            return;
        }
		// Do search in the first time
		String searchString = textSearchDichvu.getText().toLowerCase().trim();
		String sql = "select * from dichvu WHERE STS<> "+DbHelper.DELETE_STATUS+" ";
		if(searchString.length()>0){
            sql += " and ( 0 ";
        sql += " or LOWER(MA_DVKT) like '%"+searchString+"%'";
        sql += " or LOWER(TEN_DVKT) like '%"+searchString+"%'";
        sql += " or LOWER(MA_GIA) like '%"+searchString+"%'";
        sql += " or LOWER(QUYET_DINH) like '%"+searchString+"%'";
        sql += " or LOWER(CONG_BO) like '%"+searchString+"%'";
        sql += " or LOWER(MANHOM_9324) like '%"+searchString+"%'";
        sql += " or LOWER(HIEULUC) like '%"+searchString+"%'";
            sql += " )";
        }
		try  {
            logger.info(sql);
			Connection con = DbHelper.getSql2o();
			listDataDichvu = con.createQuery(sql).executeAndFetch(Dichvu.class);
	    }   
	    catch(Exception e){
	    	logger.error(e);
	    	MessageDialog.openError(shell, "Error", e.getMessage());
	    }
		// 
		tableViewerDichvu.setInput(listDataDichvu);
		tableViewerDichvu.refresh();
        //
        if(listDataDichvu.size()==0){
            textSearchDichvu.forceFocus();
        }
        else{
            tableDichvu.forceFocus();
            tableDichvu.setSelection(0);
        }
	}
    
    protected void selectTableDichvu() {
		if(tableDichvu.getSelectionCount()==0){
			return;
		}
		TableItem item = tableDichvu.getSelection()[0];
		Dichvu obj = (Dichvu)item.getData();
        logger.info(obj.toString());
        //
        //
    	if(typeDichvuDlg==TYPE_DLG_CHOOSEN){
    		// Choosen and close dlg
    		objDichvu = obj;
    		shell.close();
    	}
    	else{
            if(DbHelper.checkPhanQuyen(DbHelper.UPDATE, "dichvu")==false){
                logger.info("DON'T HAVE UPDATE RIGHT");
                return;
            }
            if(intTypeDlgDichvu==TYPE_DLG_VIEW){
                return;
            }
			DichvuDlg dlg = new DichvuDlg(shell, 0);
            dlg.setDichvuDlgData(obj);
            dlg.open();
            //
            reloadTableDichvu();
    	}
	}
    protected void editTableDichvu() {
        if(intTypeDlgDichvu==TYPE_DLG_VIEW){
            return;
        }
        if(DbHelper.checkPhanQuyen(DbHelper.UPDATE, "dichvu")==false){
            logger.info("DON'T HAVE UPDATE RIGHT");
            return;
        }
    	if(tableDichvu.getSelectionCount()==0){
			return;
		}
		TableItem item = tableDichvu.getSelection()[0];
		Dichvu obj = (Dichvu)item.getData();
        logger.info(obj.toString());
        //
        //
		DichvuDlg dlg = new DichvuDlg(shell, 0);
        dlg.setDichvuDlgData(obj);
        dlg.open();
        //
        reloadTableDichvu();
	}
    protected void deleteTableDichvu() {
        if(intTypeDlgDichvu==TYPE_DLG_VIEW){
            return;
        }
        //
        if(DbHelper.checkPhanQuyen(DbHelper.DELETE, "dichvu")==false){
            logger.info("DON'T HAVE DELETE RIGHT");
            return;
        }
		if(tableDichvu.getSelectionCount()==0){
			return;
		}
        if(MessageDialog.openConfirm(shell, "Confirm?", "Delete ?")==false){
			return;
		}
		
		TableItem item = tableDichvu.getSelection()[0];
		Dichvu obj = (Dichvu)item.getData();
        logger.info(obj.toString());
        //
		obj.delete();
        listDataDichvu.remove(obj);
        //
		reloadTableDichvu();
	}

	protected void newItemDichvu() {
        if(intTypeDlgDichvu==TYPE_DLG_VIEW){
            return;
        }
        //
        if(DbHelper.checkPhanQuyen(DbHelper.INSERT, "dichvu")==false){
            logger.info("DON'T HAVE INSERT RIGHT");
            return;
        }
		//
		DichvuDlg dlg = new DichvuDlg(shell, 0);
        Dichvu obj = new Dichvu();
		dlg.setDichvuDlgData(obj);
		dlg.open();
        listDataDichvu.add(obj);
        //
		reloadTableDichvu();
		//
	}
}
