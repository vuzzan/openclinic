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
public class PhanLoaiThuocListDlg extends Dialog {
	static Logger logger = LogManager.getLogger(PhanLoaiThuocListDlg.class.getName());
	protected Object result;
	protected Shell shell;

	private class TableLabelProviderPhanLoaiThuoc extends LabelProvider implements ITableLabelProvider {
		public Image getColumnImage(Object element, int columnIndex) {
			return null;
		}
		public String getColumnText(Object element, int columnIndex) {
			if(element instanceof PhanLoaiThuoc){
				return ((PhanLoaiThuoc) element).getIndex(columnIndex);
			}
			return "";
		}
	}
	private static class ContentProviderPhanLoaiThuoc implements IStructuredContentProvider {
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
	private Table tablePhanLoaiThuoc;
	private TableViewer tableViewerPhanLoaiThuoc;
	private List<PhanLoaiThuoc> listDataPhanLoaiThuoc;
	private Text textSearchPhanLoaiThuoc;
	private String textSearchPhanLoaiThuocString;
	public PhanLoaiThuoc objPhanLoaiThuoc = null;
	public int typePhanLoaiThuocDlg = TYPE_DLG_CHOOSEN; 
	public static final int TYPE_DLG_MANAGER = 0;
	public static final int TYPE_DLG_CHOOSEN = 1;

    public int intTypeDlgPhanLoaiThuoc;
	public static final int TYPE_DLG_VIEW = 1;
	public static final int TYPE_DLG_EDIT = 2;
	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public PhanLoaiThuocListDlg(Shell parent, int style) {
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
		shell.setImage(SWTResourceManager.getImage(PhanLoaiThuocDlg.class, "/png/list-2x.png"));
		shell.setSize(610, 340);
		shell.setText("PhanLoaiThuoc List View");
		shell.setLayout(new BorderLayout(0, 0));
		shell.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.keyCode==SWT.ESC){
					objPhanLoaiThuoc = null;
				}
			}
		});
        
        Composite compositeInShellPhanLoaiThuoc = new Composite(shell, SWT.NONE);
		compositeInShellPhanLoaiThuoc.setLayout(new BorderLayout(0, 0));
		compositeInShellPhanLoaiThuoc.setLayoutData(BorderLayout.CENTER);
        
		Composite compositeHeaderPhanLoaiThuoc = new Composite(compositeInShellPhanLoaiThuoc, SWT.NONE);
		compositeHeaderPhanLoaiThuoc.setLayoutData(BorderLayout.NORTH);
		compositeHeaderPhanLoaiThuoc.setLayout(new GridLayout(5, false));

		textSearchPhanLoaiThuoc = new Text(compositeHeaderPhanLoaiThuoc, SWT.BORDER);
		textSearchPhanLoaiThuoc.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		textSearchPhanLoaiThuoc.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.keyCode==13){
					reloadTablePhanLoaiThuoc();
				}
			}
		});
		
		Button btnNewButtonSearchPhanLoaiThuoc = new Button(compositeHeaderPhanLoaiThuoc, SWT.NONE);
		btnNewButtonSearchPhanLoaiThuoc.setImage(SWTResourceManager.getImage(PhanLoaiThuocDlg.class, "/png/media-play-2x.png"));
		btnNewButtonSearchPhanLoaiThuoc.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));

		btnNewButtonSearchPhanLoaiThuoc.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				reloadTablePhanLoaiThuoc();
			}
		});
		Button btnNewButtonExportExcelPhanLoaiThuoc = new Button(compositeHeaderPhanLoaiThuoc, SWT.NONE);
		btnNewButtonExportExcelPhanLoaiThuoc.setText("Export Excel");
		btnNewButtonExportExcelPhanLoaiThuoc.setImage(SWTResourceManager.getImage(KhamBenhListDlg.class, "/png/spreadsheet-2x.png"));
		btnNewButtonExportExcelPhanLoaiThuoc.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		btnNewButtonExportExcelPhanLoaiThuoc.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				exportExcelTablePhanLoaiThuoc();
			}
		});
		
		
		GridData gd_btnNewButtonPhanLoaiThuoc = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnNewButtonPhanLoaiThuoc.widthHint = 87;
		btnNewButtonSearchPhanLoaiThuoc.setLayoutData(gd_btnNewButtonPhanLoaiThuoc);
		btnNewButtonSearchPhanLoaiThuoc.setText("Search");
        
		tableViewerPhanLoaiThuoc = new TableViewer(compositeInShellPhanLoaiThuoc, SWT.BORDER | SWT.FULL_SELECTION);
		tablePhanLoaiThuoc = tableViewerPhanLoaiThuoc.getTable();
		tablePhanLoaiThuoc.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		tablePhanLoaiThuoc.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.keyCode==SWT.F5){
					reloadTablePhanLoaiThuoc();
                }
                if(e.keyCode==SWT.F4){
					editTablePhanLoaiThuoc();
                }
				else if(e.keyCode==13){
					selectTablePhanLoaiThuoc();
				}
                else if(e.keyCode==SWT.DEL){
					deleteTablePhanLoaiThuoc();
				}
                else if(e.keyCode==SWT.F7){
					newItemPhanLoaiThuoc();
				}
			}
		});
        tablePhanLoaiThuoc.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				selectTablePhanLoaiThuoc();
			}
		});
        
		tablePhanLoaiThuoc.setLinesVisible(true);
		tablePhanLoaiThuoc.setHeaderVisible(true);
		tablePhanLoaiThuoc.setLayoutData(BorderLayout.CENTER);

		TableColumn tbTableColumnPhanLoaiThuocPL_CODE = new TableColumn(tablePhanLoaiThuoc, SWT.LEFT);
		tbTableColumnPhanLoaiThuocPL_CODE.setWidth(100);
		tbTableColumnPhanLoaiThuocPL_CODE.setText("PL_CODE");

		TableColumn tbTableColumnPhanLoaiThuocPL_NAME = new TableColumn(tablePhanLoaiThuoc, SWT.LEFT);
		tbTableColumnPhanLoaiThuocPL_NAME.setWidth(100);
		tbTableColumnPhanLoaiThuocPL_NAME.setText("PL_NAME");

        Menu menuPhanLoaiThuoc = new Menu(tablePhanLoaiThuoc);
		tablePhanLoaiThuoc.setMenu(menuPhanLoaiThuoc);
		
		MenuItem mntmNewItemPhanLoaiThuoc = new MenuItem(menuPhanLoaiThuoc, SWT.NONE);
		mntmNewItemPhanLoaiThuoc.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				newItemPhanLoaiThuoc();
			}
		});
		mntmNewItemPhanLoaiThuoc.setImage(SWTResourceManager.getImage(PhanLoaiThuocDlg.class, "/png/arrow-circle-top-2x.png"));
		mntmNewItemPhanLoaiThuoc.setText("New");
		
		MenuItem mntmEditItemPhanLoaiThuoc = new MenuItem(menuPhanLoaiThuoc, SWT.NONE);
		mntmEditItemPhanLoaiThuoc.setImage(SWTResourceManager.getImage(PhanLoaiThuocDlg.class, "/png/wrench-2x.png"));
		mntmEditItemPhanLoaiThuoc.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				editTablePhanLoaiThuoc();
			}
		});
		mntmEditItemPhanLoaiThuoc.setText("Edit");
		
		MenuItem mntmDeletePhanLoaiThuoc = new MenuItem(menuPhanLoaiThuoc, SWT.NONE);
		mntmDeletePhanLoaiThuoc.setImage(SWTResourceManager.getImage(PhanLoaiThuocDlg.class, "/png/circle-x-2x.png"));
		mntmDeletePhanLoaiThuoc.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				deleteTablePhanLoaiThuoc();
			}
		});
		mntmDeletePhanLoaiThuoc.setText("Delete");
		
		MenuItem mntmExportPhanLoaiThuoc = new MenuItem(menuPhanLoaiThuoc, SWT.NONE);
		mntmExportPhanLoaiThuoc.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				exportExcelTablePhanLoaiThuoc();
			}
		});
		mntmExportPhanLoaiThuoc.setImage(SWTResourceManager.getImage(PhanLoaiThuocDlg.class, "/png/spreadsheet-2x.png"));
		mntmExportPhanLoaiThuoc.setText("Export Excel");
		
		tableViewerPhanLoaiThuoc.setLabelProvider(new TableLabelProviderPhanLoaiThuoc());
		tableViewerPhanLoaiThuoc.setContentProvider(new ContentProviderPhanLoaiThuoc());
		tableViewerPhanLoaiThuoc.setInput(listDataPhanLoaiThuoc);
        //
        //
		loadDataPhanLoaiThuoc();
		//
        reloadTablePhanLoaiThuoc();
	}
    public void setDataPhanLoaiThuoc(String textSearchString){
		this.textSearchPhanLoaiThuocString = textSearchString;
	}
	private void loadDataPhanLoaiThuoc() {
		if(textSearchPhanLoaiThuocString!=null){
			textSearchPhanLoaiThuoc.setText(textSearchPhanLoaiThuocString);
		}
	}
	protected void exportExcelTablePhanLoaiThuoc() {
        if(DbHelper.checkPhanQuyen(DbHelper.READ, "phan_loai_thuoc")==false){
			logger.info("DON'T HAVE READ RIGHT");
			return;
		}
        if(listDataPhanLoaiThuoc!=null){
            // Export to EXCEL
    		
			StringBuffer buff_phan_loai_thuoc = new StringBuffer();
			String phan_loai_thuoc_filename = "phan_loai_thuoc_"+Utils.getDatetimeCurent().replaceAll(":", "_")+".xls";
			String delimiter = "</td><td>";
			// Get header...
			// Get header...
			buff_phan_loai_thuoc.append( "<table>");
			buff_phan_loai_thuoc.append( "<tr class='background-color:#dfdfdf'><td>");

			buff_phan_loai_thuoc.append( "PL_CODE" +delimiter);
			buff_phan_loai_thuoc.append( "PL_NAME" +delimiter);
			// End of header
			buff_phan_loai_thuoc.append( "</td></tr>");
			buff_phan_loai_thuoc.append( "\n");
			// Get data...
			for( PhanLoaiThuoc obj:  listDataPhanLoaiThuoc){
				buff_phan_loai_thuoc.append( "<tr><td>");
				buff_phan_loai_thuoc.append( obj.PL_CODE +delimiter);
				buff_phan_loai_thuoc.append( obj.PL_NAME +delimiter);
				// End of header
				buff_phan_loai_thuoc.append( "</td></tr>");
			}
			//
			buff_phan_loai_thuoc.append( "</table>");
			Writer out = null;
			try {
				out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(phan_loai_thuoc_filename), "UTF-8"));
				out.write('\uFEFF'); // BOM for UTF-*
			    out.write(buff_phan_loai_thuoc.toString());
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
	protected void reloadTablePhanLoaiThuoc() {
        if(DbHelper.checkPhanQuyen(DbHelper.READ, "phan_loai_thuoc")==false){
			logger.info("DON'T HAVE READ RIGHT");
			return;
		}
    
        if(listDataPhanLoaiThuoc!=null){
            // 
            tableViewerPhanLoaiThuoc.setInput(listDataPhanLoaiThuoc);
            tableViewerPhanLoaiThuoc.refresh();
            //
            if(listDataPhanLoaiThuoc.size()==0){
                textSearchPhanLoaiThuoc.forceFocus();
            }
            else{
                tablePhanLoaiThuoc.forceFocus();
                tablePhanLoaiThuoc.setSelection(0);
            }
            return;
        }
		// Do search in the first time
		String searchString = textSearchPhanLoaiThuoc.getText().toLowerCase().trim();
		String sql = "select * from phan_loai_thuoc WHERE STS<> "+DbHelper.DELETE_STATUS+" ";
		if(searchString.length()>0){
            sql += " and ( 0 ";
        sql += " or LOWER(PL_CODE) like '%"+searchString+"%'";
        sql += " or LOWER(PL_NAME) like '%"+searchString+"%'";
            sql += " )";
        }
		try  {
            logger.info(sql);
			Connection con = DbHelper.getSql2o();
			listDataPhanLoaiThuoc = con.createQuery(sql).executeAndFetch(PhanLoaiThuoc.class);
	    }   
	    catch(Exception e){
	    	logger.error(e);
	    	MessageDialog.openError(shell, "Error", e.getMessage());
	    }
		// 
		tableViewerPhanLoaiThuoc.setInput(listDataPhanLoaiThuoc);
		tableViewerPhanLoaiThuoc.refresh();
        //
        if(listDataPhanLoaiThuoc.size()==0){
            textSearchPhanLoaiThuoc.forceFocus();
        }
        else{
            tablePhanLoaiThuoc.forceFocus();
            tablePhanLoaiThuoc.setSelection(0);
        }
	}
    
    protected void selectTablePhanLoaiThuoc() {
		if(tablePhanLoaiThuoc.getSelectionCount()==0){
			return;
		}
		TableItem item = tablePhanLoaiThuoc.getSelection()[0];
		PhanLoaiThuoc obj = (PhanLoaiThuoc)item.getData();
        logger.info(obj.toString());
        //
        //
    	if(typePhanLoaiThuocDlg==TYPE_DLG_CHOOSEN){
    		// Choosen and close dlg
    		objPhanLoaiThuoc = obj;
    		shell.close();
    	}
    	else{
            if(DbHelper.checkPhanQuyen(DbHelper.UPDATE, "phan_loai_thuoc")==false){
                logger.info("DON'T HAVE UPDATE RIGHT");
                return;
            }
            if(intTypeDlgPhanLoaiThuoc==TYPE_DLG_VIEW){
                return;
            }
			PhanLoaiThuocDlg dlg = new PhanLoaiThuocDlg(shell, 0);
            dlg.setPhanLoaiThuocDlgData(obj);
            dlg.open();
            //
            reloadTablePhanLoaiThuoc();
    	}
	}
    protected void editTablePhanLoaiThuoc() {
        if(intTypeDlgPhanLoaiThuoc==TYPE_DLG_VIEW){
            return;
        }
        if(DbHelper.checkPhanQuyen(DbHelper.UPDATE, "phan_loai_thuoc")==false){
            logger.info("DON'T HAVE UPDATE RIGHT");
            return;
        }
    	if(tablePhanLoaiThuoc.getSelectionCount()==0){
			return;
		}
		TableItem item = tablePhanLoaiThuoc.getSelection()[0];
		PhanLoaiThuoc obj = (PhanLoaiThuoc)item.getData();
        logger.info(obj.toString());
        //
        //
		PhanLoaiThuocDlg dlg = new PhanLoaiThuocDlg(shell, 0);
        dlg.setPhanLoaiThuocDlgData(obj);
        dlg.open();
        //
        reloadTablePhanLoaiThuoc();
	}
    protected void deleteTablePhanLoaiThuoc() {
        if(intTypeDlgPhanLoaiThuoc==TYPE_DLG_VIEW){
            return;
        }
        //
        if(DbHelper.checkPhanQuyen(DbHelper.DELETE, "phan_loai_thuoc")==false){
            logger.info("DON'T HAVE DELETE RIGHT");
            return;
        }
		if(tablePhanLoaiThuoc.getSelectionCount()==0){
			return;
		}
        if(MessageDialog.openConfirm(shell, "Confirm?", "Delete ?")==false){
			return;
		}
		
		TableItem item = tablePhanLoaiThuoc.getSelection()[0];
		PhanLoaiThuoc obj = (PhanLoaiThuoc)item.getData();
        logger.info(obj.toString());
        //
		obj.delete();
        listDataPhanLoaiThuoc.remove(obj);
        //
		reloadTablePhanLoaiThuoc();
	}

	protected void newItemPhanLoaiThuoc() {
        if(intTypeDlgPhanLoaiThuoc==TYPE_DLG_VIEW){
            return;
        }
        //
        if(DbHelper.checkPhanQuyen(DbHelper.INSERT, "phan_loai_thuoc")==false){
            logger.info("DON'T HAVE INSERT RIGHT");
            return;
        }
		//
		PhanLoaiThuocDlg dlg = new PhanLoaiThuocDlg(shell, 0);
        PhanLoaiThuoc obj = new PhanLoaiThuoc();
		dlg.setPhanLoaiThuocDlgData(obj);
		dlg.open();
        listDataPhanLoaiThuoc.add(obj);
        //
		reloadTablePhanLoaiThuoc();
		//
	}
}
