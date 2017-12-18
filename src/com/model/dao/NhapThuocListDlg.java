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
public class NhapThuocListDlg extends Dialog {
	static Logger logger = LogManager.getLogger(NhapThuocListDlg.class.getName());
	protected Object result;
	protected Shell shell;

	private class TableLabelProviderNhapThuoc extends LabelProvider implements ITableLabelProvider {
		public Image getColumnImage(Object element, int columnIndex) {
			return null;
		}
		public String getColumnText(Object element, int columnIndex) {
			if(element instanceof NhapThuoc){
				return ((NhapThuoc) element).getIndex(columnIndex);
			}
			return "";
		}
	}
	private static class ContentProviderNhapThuoc implements IStructuredContentProvider {
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
	private Table tableNhapThuoc;
	private TableViewer tableViewerNhapThuoc;
	private List<NhapThuoc> listDataNhapThuoc;
	private Text textSearchNhapThuoc;
	private String textSearchNhapThuocString;
	public NhapThuoc objNhapThuoc = null;
	public int typeNhapThuocDlg = TYPE_DLG_CHOOSEN; 
	public static final int TYPE_DLG_MANAGER = 0;
	public static final int TYPE_DLG_CHOOSEN = 1;

    public int intTypeDlgNhapThuoc;
	public static final int TYPE_DLG_VIEW = 1;
	public static final int TYPE_DLG_EDIT = 2;
	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public NhapThuocListDlg(Shell parent, int style) {
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
		shell.setImage(SWTResourceManager.getImage(NhapThuocDlg.class, "/png/list-2x.png"));
		shell.setSize(610, 340);
		shell.setText("NhapThuoc List View");
		shell.setLayout(new BorderLayout(0, 0));
		shell.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.keyCode==SWT.ESC){
					objNhapThuoc = null;
				}
			}
		});
        
        Composite compositeInShellNhapThuoc = new Composite(shell, SWT.NONE);
		compositeInShellNhapThuoc.setLayout(new BorderLayout(0, 0));
		compositeInShellNhapThuoc.setLayoutData(BorderLayout.CENTER);
        
		Composite compositeHeaderNhapThuoc = new Composite(compositeInShellNhapThuoc, SWT.NONE);
		compositeHeaderNhapThuoc.setLayoutData(BorderLayout.NORTH);
		compositeHeaderNhapThuoc.setLayout(new GridLayout(5, false));

		textSearchNhapThuoc = new Text(compositeHeaderNhapThuoc, SWT.BORDER);
		textSearchNhapThuoc.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		textSearchNhapThuoc.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.keyCode==13){
					reloadTableNhapThuoc();
				}
			}
		});
		
		Button btnNewButtonSearchNhapThuoc = new Button(compositeHeaderNhapThuoc, SWT.NONE);
		btnNewButtonSearchNhapThuoc.setImage(SWTResourceManager.getImage(NhapThuocDlg.class, "/png/media-play-2x.png"));
		btnNewButtonSearchNhapThuoc.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));

		btnNewButtonSearchNhapThuoc.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				reloadTableNhapThuoc();
			}
		});
		Button btnNewButtonExportExcelNhapThuoc = new Button(compositeHeaderNhapThuoc, SWT.NONE);
		btnNewButtonExportExcelNhapThuoc.setText("Export Excel");
		btnNewButtonExportExcelNhapThuoc.setImage(SWTResourceManager.getImage(KhamBenhListDlg.class, "/png/spreadsheet-2x.png"));
		btnNewButtonExportExcelNhapThuoc.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		btnNewButtonExportExcelNhapThuoc.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				exportExcelTableNhapThuoc();
			}
		});
		
		
		GridData gd_btnNewButtonNhapThuoc = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnNewButtonNhapThuoc.widthHint = 87;
		btnNewButtonSearchNhapThuoc.setLayoutData(gd_btnNewButtonNhapThuoc);
		btnNewButtonSearchNhapThuoc.setText("Search");
        
		tableViewerNhapThuoc = new TableViewer(compositeInShellNhapThuoc, SWT.BORDER | SWT.FULL_SELECTION);
		tableNhapThuoc = tableViewerNhapThuoc.getTable();
		tableNhapThuoc.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		tableNhapThuoc.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.keyCode==SWT.F5){
					reloadTableNhapThuoc();
                }
                if(e.keyCode==SWT.F4){
					editTableNhapThuoc();
                }
				else if(e.keyCode==13){
					selectTableNhapThuoc();
				}
                else if(e.keyCode==SWT.DEL){
					deleteTableNhapThuoc();
				}
                else if(e.keyCode==SWT.F7){
					newItemNhapThuoc();
				}
			}
		});
        tableNhapThuoc.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				selectTableNhapThuoc();
			}
		});
        
		tableNhapThuoc.setLinesVisible(true);
		tableNhapThuoc.setHeaderVisible(true);
		tableNhapThuoc.setLayoutData(BorderLayout.CENTER);

		TableColumn tbTableColumnNhapThuocV_ID = new TableColumn(tableNhapThuoc, SWT.RIGHT);
		tbTableColumnNhapThuocV_ID.setWidth(100);
		tbTableColumnNhapThuocV_ID.setText("V_ID");

		TableColumn tbTableColumnNhapThuocKHO_ID = new TableColumn(tableNhapThuoc, SWT.RIGHT);
		tbTableColumnNhapThuocKHO_ID.setWidth(100);
		tbTableColumnNhapThuocKHO_ID.setText("KHO_ID");

		TableColumn tbTableColumnNhapThuocTENKHO = new TableColumn(tableNhapThuoc, SWT.LEFT);
		tbTableColumnNhapThuocTENKHO.setWidth(100);
		tbTableColumnNhapThuocTENKHO.setText("TENKHO");

		TableColumn tbTableColumnNhapThuocFROM_KHOID = new TableColumn(tableNhapThuoc, SWT.RIGHT);
		tbTableColumnNhapThuocFROM_KHOID.setWidth(100);
		tbTableColumnNhapThuocFROM_KHOID.setText("FROM_KHOID");

		TableColumn tbTableColumnNhapThuocVENDOR_NAME = new TableColumn(tableNhapThuoc, SWT.LEFT);
		tbTableColumnNhapThuocVENDOR_NAME.setWidth(100);
		tbTableColumnNhapThuocVENDOR_NAME.setText("VENDOR_NAME");

		TableColumn tbTableColumnNhapThuocVENDOR_ADDR = new TableColumn(tableNhapThuoc, SWT.LEFT);
		tbTableColumnNhapThuocVENDOR_ADDR.setWidth(100);
		tbTableColumnNhapThuocVENDOR_ADDR.setText("VENDOR_ADDR");


		TableColumn tbTableColumnNhapThuocNGAY_NHAP = new TableColumn(tableNhapThuoc, SWT.NONE);
		tbTableColumnNhapThuocNGAY_NHAP.setWidth(100);
		tbTableColumnNhapThuocNGAY_NHAP.setText("NGAY_NHAP");


		TableColumn tbTableColumnNhapThuocNGAY_HD = new TableColumn(tableNhapThuoc, SWT.NONE);
		tbTableColumnNhapThuocNGAY_HD.setWidth(100);
		tbTableColumnNhapThuocNGAY_HD.setText("NGAY_HD");

		TableColumn tbTableColumnNhapThuocSO_HOA_DON = new TableColumn(tableNhapThuoc, SWT.LEFT);
		tbTableColumnNhapThuocSO_HOA_DON.setWidth(100);
		tbTableColumnNhapThuocSO_HOA_DON.setText("SO_HOA_DON");

		TableColumn tbTableColumnNhapThuocKH_HOA_DON = new TableColumn(tableNhapThuoc, SWT.LEFT);
		tbTableColumnNhapThuocKH_HOA_DON.setWidth(100);
		tbTableColumnNhapThuocKH_HOA_DON.setText("KH_HOA_DON");

		TableColumn tbTableColumnNhapThuocTONGCONG = new TableColumn(tableNhapThuoc, SWT.RIGHT);
		tbTableColumnNhapThuocTONGCONG.setWidth(100);
		tbTableColumnNhapThuocTONGCONG.setText("TONGCONG");

		TableColumn tbTableColumnNhapThuocTONGCONG_VAT = new TableColumn(tableNhapThuoc, SWT.RIGHT);
		tbTableColumnNhapThuocTONGCONG_VAT.setWidth(100);
		tbTableColumnNhapThuocTONGCONG_VAT.setText("TONGCONG_VAT");

		TableColumn tbTableColumnNhapThuocVAT = new TableColumn(tableNhapThuoc, SWT.RIGHT);
		tbTableColumnNhapThuocVAT.setWidth(100);
		tbTableColumnNhapThuocVAT.setText("VAT");

		TableColumn tbTableColumnNhapThuocSTS = new TableColumn(tableNhapThuoc, SWT.RIGHT);
		tbTableColumnNhapThuocSTS.setWidth(100);
		tbTableColumnNhapThuocSTS.setText("STS");

        Menu menuNhapThuoc = new Menu(tableNhapThuoc);
		tableNhapThuoc.setMenu(menuNhapThuoc);
		
		MenuItem mntmNewItemNhapThuoc = new MenuItem(menuNhapThuoc, SWT.NONE);
		mntmNewItemNhapThuoc.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				newItemNhapThuoc();
			}
		});
		mntmNewItemNhapThuoc.setImage(SWTResourceManager.getImage(NhapThuocDlg.class, "/png/arrow-circle-top-2x.png"));
		mntmNewItemNhapThuoc.setText("New");
		
		MenuItem mntmEditItemNhapThuoc = new MenuItem(menuNhapThuoc, SWT.NONE);
		mntmEditItemNhapThuoc.setImage(SWTResourceManager.getImage(NhapThuocDlg.class, "/png/wrench-2x.png"));
		mntmEditItemNhapThuoc.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				editTableNhapThuoc();
			}
		});
		mntmEditItemNhapThuoc.setText("Edit");
		
		MenuItem mntmDeleteNhapThuoc = new MenuItem(menuNhapThuoc, SWT.NONE);
		mntmDeleteNhapThuoc.setImage(SWTResourceManager.getImage(NhapThuocDlg.class, "/png/circle-x-2x.png"));
		mntmDeleteNhapThuoc.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				deleteTableNhapThuoc();
			}
		});
		mntmDeleteNhapThuoc.setText("Delete");
		
		MenuItem mntmExportNhapThuoc = new MenuItem(menuNhapThuoc, SWT.NONE);
		mntmExportNhapThuoc.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				exportExcelTableNhapThuoc();
			}
		});
		mntmExportNhapThuoc.setImage(SWTResourceManager.getImage(NhapThuocDlg.class, "/png/spreadsheet-2x.png"));
		mntmExportNhapThuoc.setText("Export Excel");
		
		tableViewerNhapThuoc.setLabelProvider(new TableLabelProviderNhapThuoc());
		tableViewerNhapThuoc.setContentProvider(new ContentProviderNhapThuoc());
		tableViewerNhapThuoc.setInput(listDataNhapThuoc);
        //
        //
		loadDataNhapThuoc();
		//
        reloadTableNhapThuoc();
	}
    public void setDataNhapThuoc(String textSearchString){
		this.textSearchNhapThuocString = textSearchString;
	}
	private void loadDataNhapThuoc() {
		if(textSearchNhapThuocString!=null){
			textSearchNhapThuoc.setText(textSearchNhapThuocString);
		}
	}
	protected void exportExcelTableNhapThuoc() {
        if(DbHelper.checkPhanQuyen(DbHelper.READ, "nhap_thuoc")==false){
			logger.info("DON'T HAVE READ RIGHT");
			return;
		}
        if(listDataNhapThuoc!=null){
            // Export to EXCEL
    		
			StringBuffer buff_nhap_thuoc = new StringBuffer();
			String nhap_thuoc_filename = "nhap_thuoc_"+Utils.getDatetimeCurent().replaceAll(":", "_")+".xls";
			String delimiter = "</td><td>";
			// Get header...
			// Get header...
			buff_nhap_thuoc.append( "<table>");
			buff_nhap_thuoc.append( "<tr class='background-color:#dfdfdf'><td>");

			buff_nhap_thuoc.append( "V_ID" +delimiter);
			buff_nhap_thuoc.append( "KHO_ID" +delimiter);
			buff_nhap_thuoc.append( "TENKHO" +delimiter);
			buff_nhap_thuoc.append( "FROM_KHOID" +delimiter);
			buff_nhap_thuoc.append( "VENDOR_NAME" +delimiter);
			buff_nhap_thuoc.append( "VENDOR_ADDR" +delimiter);
			buff_nhap_thuoc.append( "NGAY_NHAP" +delimiter);
			buff_nhap_thuoc.append( "NGAY_HD" +delimiter);
			buff_nhap_thuoc.append( "SO_HOA_DON" +delimiter);
			buff_nhap_thuoc.append( "KH_HOA_DON" +delimiter);
			buff_nhap_thuoc.append( "TONGCONG" +delimiter);
			buff_nhap_thuoc.append( "TONGCONG_VAT" +delimiter);
			buff_nhap_thuoc.append( "VAT" +delimiter);
			buff_nhap_thuoc.append( "STS");
			// End of header
			buff_nhap_thuoc.append( "</td></tr>");
			buff_nhap_thuoc.append( "\n");
			// Get data...
			for( NhapThuoc obj:  listDataNhapThuoc){
				buff_nhap_thuoc.append( "<tr><td>");
				buff_nhap_thuoc.append( obj.V_ID +delimiter);
				buff_nhap_thuoc.append( obj.KHO_ID +delimiter);
				buff_nhap_thuoc.append( obj.TENKHO +delimiter);
				buff_nhap_thuoc.append( obj.FROM_KHOID +delimiter);
				buff_nhap_thuoc.append( obj.VENDOR_NAME +delimiter);
				buff_nhap_thuoc.append( obj.VENDOR_ADDR +delimiter);
				buff_nhap_thuoc.append( obj.NGAY_NHAP +delimiter);
				buff_nhap_thuoc.append( obj.NGAY_HD +delimiter);
				buff_nhap_thuoc.append( obj.SO_HOA_DON +delimiter);
				buff_nhap_thuoc.append( obj.KH_HOA_DON +delimiter);
				buff_nhap_thuoc.append( obj.TONGCONG +delimiter);
				buff_nhap_thuoc.append( obj.TONGCONG_VAT +delimiter);
				buff_nhap_thuoc.append( obj.VAT +delimiter);
				buff_nhap_thuoc.append( obj.STS );
				// End of header
				buff_nhap_thuoc.append( "</td></tr>");
			}
			//
			buff_nhap_thuoc.append( "</table>");
			Writer out = null;
			try {
				out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(nhap_thuoc_filename), "UTF-8"));
				out.write('\uFEFF'); // BOM for UTF-*
			    out.write(buff_nhap_thuoc.toString());
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
	protected void reloadTableNhapThuoc() {
        if(DbHelper.checkPhanQuyen(DbHelper.READ, "nhap_thuoc")==false){
			logger.info("DON'T HAVE READ RIGHT");
			return;
		}
    
        if(listDataNhapThuoc!=null){
            // 
            tableViewerNhapThuoc.setInput(listDataNhapThuoc);
            tableViewerNhapThuoc.refresh();
            //
            if(listDataNhapThuoc.size()==0){
                textSearchNhapThuoc.forceFocus();
            }
            else{
                tableNhapThuoc.forceFocus();
                tableNhapThuoc.setSelection(0);
            }
            return;
        }
		// Do search in the first time
		String searchString = textSearchNhapThuoc.getText().toLowerCase().trim();
		String sql = "select * from nhap_thuoc WHERE STS<> "+DbHelper.DELETE_STATUS+" ";
		if(searchString.length()>0){
            sql += " and ( 0 ";
        sql += " or LOWER(TENKHO) like '%"+searchString+"%'";
        sql += " or LOWER(VENDOR_NAME) like '%"+searchString+"%'";
        sql += " or LOWER(VENDOR_ADDR) like '%"+searchString+"%'";
        sql += " or LOWER(SO_HOA_DON) like '%"+searchString+"%'";
        sql += " or LOWER(KH_HOA_DON) like '%"+searchString+"%'";
            sql += " )";
        }
		try  {
            logger.info(sql);
			Connection con = DbHelper.getSql2o();
			listDataNhapThuoc = con.createQuery(sql).executeAndFetch(NhapThuoc.class);
	    }   
	    catch(Exception e){
	    	logger.error(e);
	    	MessageDialog.openError(shell, "Error", e.getMessage());
	    }
		// 
		tableViewerNhapThuoc.setInput(listDataNhapThuoc);
		tableViewerNhapThuoc.refresh();
        //
        if(listDataNhapThuoc.size()==0){
            textSearchNhapThuoc.forceFocus();
        }
        else{
            tableNhapThuoc.forceFocus();
            tableNhapThuoc.setSelection(0);
        }
	}
    
    protected void selectTableNhapThuoc() {
		if(tableNhapThuoc.getSelectionCount()==0){
			return;
		}
		TableItem item = tableNhapThuoc.getSelection()[0];
		NhapThuoc obj = (NhapThuoc)item.getData();
        logger.info(obj.toString());
        //
        //
    	if(typeNhapThuocDlg==TYPE_DLG_CHOOSEN){
    		// Choosen and close dlg
    		objNhapThuoc = obj;
    		shell.close();
    	}
    	else{
            if(DbHelper.checkPhanQuyen(DbHelper.UPDATE, "nhap_thuoc")==false){
                logger.info("DON'T HAVE UPDATE RIGHT");
                return;
            }
            if(intTypeDlgNhapThuoc==TYPE_DLG_VIEW){
                return;
            }
			NhapThuocDlg dlg = new NhapThuocDlg(shell, 0);
            dlg.setNhapThuocDlgData(obj);
            dlg.open();
            //
            reloadTableNhapThuoc();
    	}
	}
    protected void editTableNhapThuoc() {
        if(intTypeDlgNhapThuoc==TYPE_DLG_VIEW){
            return;
        }
        if(DbHelper.checkPhanQuyen(DbHelper.UPDATE, "nhap_thuoc")==false){
            logger.info("DON'T HAVE UPDATE RIGHT");
            return;
        }
    	if(tableNhapThuoc.getSelectionCount()==0){
			return;
		}
		TableItem item = tableNhapThuoc.getSelection()[0];
		NhapThuoc obj = (NhapThuoc)item.getData();
        logger.info(obj.toString());
        //
        //
		NhapThuocDlg dlg = new NhapThuocDlg(shell, 0);
        dlg.setNhapThuocDlgData(obj);
        dlg.open();
        //
        reloadTableNhapThuoc();
	}
    protected void deleteTableNhapThuoc() {
        if(intTypeDlgNhapThuoc==TYPE_DLG_VIEW){
            return;
        }
        //
        if(DbHelper.checkPhanQuyen(DbHelper.DELETE, "nhap_thuoc")==false){
            logger.info("DON'T HAVE DELETE RIGHT");
            return;
        }
		if(tableNhapThuoc.getSelectionCount()==0){
			return;
		}
        if(MessageDialog.openConfirm(shell, "Confirm?", "Delete ?")==false){
			return;
		}
		
		TableItem item = tableNhapThuoc.getSelection()[0];
		NhapThuoc obj = (NhapThuoc)item.getData();
        logger.info(obj.toString());
        //
		obj.delete();
        listDataNhapThuoc.remove(obj);
        //
		reloadTableNhapThuoc();
	}

	protected void newItemNhapThuoc() {
        if(intTypeDlgNhapThuoc==TYPE_DLG_VIEW){
            return;
        }
        //
        if(DbHelper.checkPhanQuyen(DbHelper.INSERT, "nhap_thuoc")==false){
            logger.info("DON'T HAVE INSERT RIGHT");
            return;
        }
		//
		NhapThuocDlg dlg = new NhapThuocDlg(shell, 0);
        NhapThuoc obj = new NhapThuoc();
		dlg.setNhapThuocDlgData(obj);
		dlg.open();
        listDataNhapThuoc.add(obj);
        //
		reloadTableNhapThuoc();
		//
	}
}
