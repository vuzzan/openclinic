﻿/**

*/
package com.model.dao;

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
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.wb.swt.SWTResourceManager;
public class CtNhapthuocListDlg extends Dialog {
	static Logger logger = LogManager.getLogger(CtNhapthuocListDlg.class.getName());
	protected Object result;
	protected Shell shell;

	private class TableLabelProviderCtNhapthuoc extends LabelProvider implements ITableLabelProvider {
		public Image getColumnImage(Object element, int columnIndex) {
			return null;
		}
		public String getColumnText(Object element, int columnIndex) {
			if(element instanceof CtNhapthuoc){
				return ((CtNhapthuoc) element).getIndex(columnIndex);
			}
			return "";
		}
	}
	private static class ContentProviderCtNhapthuoc implements IStructuredContentProvider {
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
	private Table tableCtNhapthuoc;
	private TableViewer tableViewerCtNhapthuoc;
	private List<CtNhapthuoc> listDataCtNhapthuoc;
	private Text textSearchCtNhapthuoc;
	private String textSearchCtNhapthuocString;
	public CtNhapthuoc objCtNhapthuoc = null;
	public int typeCtNhapthuocDlg = TYPE_DLG_CHOOSEN; 
	public static final int TYPE_DLG_MANAGER = 0;
	public static final int TYPE_DLG_CHOOSEN = 1;

    public int intTypeDlgCtNhapthuoc;
	public static final int TYPE_DLG_VIEW = 1;
	public static final int TYPE_DLG_EDIT = 2;
	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public CtNhapthuocListDlg(Shell parent, int style) {
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
		shell.setImage(SWTResourceManager.getImage(CtNhapthuocDlg.class, "/png/list-2x.png"));
		shell.setSize(610, 340);
		shell.setText("CtNhapthuoc List View");
		shell.setLayout(new BorderLayout(0, 0));
		shell.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.keyCode==SWT.ESC){
					objCtNhapthuoc = null;
				}
			}
		});
        
        Composite compositeInShellCtNhapthuoc = new Composite(shell, SWT.NONE);
		compositeInShellCtNhapthuoc.setLayout(new BorderLayout(0, 0));
		compositeInShellCtNhapthuoc.setLayoutData(BorderLayout.CENTER);
        
		Composite compositeHeaderCtNhapthuoc = new Composite(compositeInShellCtNhapthuoc, SWT.NONE);
		compositeHeaderCtNhapthuoc.setLayoutData(BorderLayout.NORTH);
		compositeHeaderCtNhapthuoc.setLayout(new GridLayout(2, false));

		textSearchCtNhapthuoc = new Text(compositeHeaderCtNhapthuoc, SWT.BORDER);
		textSearchCtNhapthuoc.setFont(SWTResourceManager.getFont("Tahoma", 11, SWT.NORMAL));
		textSearchCtNhapthuoc.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.keyCode==13){
					reloadTableCtNhapthuoc();
				}
			}
		});
		
		Button btnNewButtonSearchCtNhapthuoc = new Button(compositeHeaderCtNhapthuoc, SWT.NONE);
		btnNewButtonSearchCtNhapthuoc.setImage(SWTResourceManager.getImage(CtNhapthuocDlg.class, "/png/media-play-2x.png"));
		btnNewButtonSearchCtNhapthuoc.setFont(SWTResourceManager.getFont("Tahoma", 12, SWT.NORMAL));

		btnNewButtonSearchCtNhapthuoc.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				reloadTableCtNhapthuoc();
			}
		});
		GridData gd_btnNewButtonCtNhapthuoc = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnNewButtonCtNhapthuoc.widthHint = 87;
		btnNewButtonSearchCtNhapthuoc.setLayoutData(gd_btnNewButtonCtNhapthuoc);
		btnNewButtonSearchCtNhapthuoc.setText("Search");
        
		tableViewerCtNhapthuoc = new TableViewer(compositeInShellCtNhapthuoc, SWT.BORDER | SWT.FULL_SELECTION);
		tableCtNhapthuoc = tableViewerCtNhapthuoc.getTable();
		tableCtNhapthuoc.setFont(SWTResourceManager.getFont("Tahoma", 11, SWT.NORMAL));
		tableCtNhapthuoc.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.keyCode==SWT.F5){
					reloadTableCtNhapthuoc();
                }
                if(e.keyCode==SWT.F4){
					editTableCtNhapthuoc();
                }
				else if(e.keyCode==13){
					selectTableCtNhapthuoc();
				}
                else if(e.keyCode==SWT.DEL){
					deleteTableCtNhapthuoc();
				}
                else if(e.keyCode==SWT.F7){
					newItemCtNhapthuoc();
				}
			}
		});
        tableCtNhapthuoc.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				selectTableCtNhapthuoc();
			}
		});
        
		tableCtNhapthuoc.setLinesVisible(true);
		tableCtNhapthuoc.setHeaderVisible(true);
		tableCtNhapthuoc.setLayoutData(BorderLayout.CENTER);

		TableColumn tbTableColumnCtNhapthuocNT_ID = new TableColumn(tableCtNhapthuoc, SWT.RIGHT);
		tbTableColumnCtNhapthuocNT_ID.setWidth(100);
		tbTableColumnCtNhapthuocNT_ID.setText("NT_ID");

		TableColumn tbTableColumnCtNhapthuocV_ID = new TableColumn(tableCtNhapthuoc, SWT.RIGHT);
		tbTableColumnCtNhapthuocV_ID.setWidth(100);
		tbTableColumnCtNhapthuocV_ID.setText("V_ID");

		TableColumn tbTableColumnCtNhapthuocTENKHO = new TableColumn(tableCtNhapthuoc, SWT.LEFT);
		tbTableColumnCtNhapthuocTENKHO.setWidth(100);
		tbTableColumnCtNhapthuocTENKHO.setText("TENKHO");

		TableColumn tbTableColumnCtNhapthuocTHUOC_ID = new TableColumn(tableCtNhapthuoc, SWT.RIGHT);
		tbTableColumnCtNhapthuocTHUOC_ID.setWidth(100);
		tbTableColumnCtNhapthuocTHUOC_ID.setText("THUOC_ID");

		TableColumn tbTableColumnCtNhapthuocTENTHUOC = new TableColumn(tableCtNhapthuoc, SWT.LEFT);
		tbTableColumnCtNhapthuocTENTHUOC.setWidth(100);
		tbTableColumnCtNhapthuocTENTHUOC.setText("TENTHUOC");

		TableColumn tbTableColumnCtNhapthuocDONVI = new TableColumn(tableCtNhapthuoc, SWT.LEFT);
		tbTableColumnCtNhapthuocDONVI.setWidth(100);
		tbTableColumnCtNhapthuocDONVI.setText("DONVI");


		TableColumn tbTableColumnCtNhapthuocHANDUNG = new TableColumn(tableCtNhapthuoc, SWT.NONE);
		tbTableColumnCtNhapthuocHANDUNG.setWidth(100);
		tbTableColumnCtNhapthuocHANDUNG.setText("HANDUNG");

		TableColumn tbTableColumnCtNhapthuocSOLUONG = new TableColumn(tableCtNhapthuoc, SWT.RIGHT);
		tbTableColumnCtNhapthuocSOLUONG.setWidth(100);
		tbTableColumnCtNhapthuocSOLUONG.setText("SOLUONG");

		TableColumn tbTableColumnCtNhapthuocSL_TONKHO = new TableColumn(tableCtNhapthuoc, SWT.RIGHT);
		tbTableColumnCtNhapthuocSL_TONKHO.setWidth(100);
		tbTableColumnCtNhapthuocSL_TONKHO.setText("SL_TONKHO");

		TableColumn tbTableColumnCtNhapthuocSL_OUTSTANDING = new TableColumn(tableCtNhapthuoc, SWT.RIGHT);
		tbTableColumnCtNhapthuocSL_OUTSTANDING.setWidth(100);
		tbTableColumnCtNhapthuocSL_OUTSTANDING.setText("SL_OUTSTANDING");

		TableColumn tbTableColumnCtNhapthuocSL_DADUNG = new TableColumn(tableCtNhapthuoc, SWT.RIGHT);
		tbTableColumnCtNhapthuocSL_DADUNG.setWidth(100);
		tbTableColumnCtNhapthuocSL_DADUNG.setText("SL_DADUNG");

		TableColumn tbTableColumnCtNhapthuocDONGIA = new TableColumn(tableCtNhapthuoc, SWT.RIGHT);
		tbTableColumnCtNhapthuocDONGIA.setWidth(100);
		tbTableColumnCtNhapthuocDONGIA.setText("DONGIA");

		TableColumn tbTableColumnCtNhapthuocTHANHTIEN = new TableColumn(tableCtNhapthuoc, SWT.RIGHT);
		tbTableColumnCtNhapthuocTHANHTIEN.setWidth(100);
		tbTableColumnCtNhapthuocTHANHTIEN.setText("THANHTIEN");

		TableColumn tbTableColumnCtNhapthuocSTS = new TableColumn(tableCtNhapthuoc, SWT.RIGHT);
		tbTableColumnCtNhapthuocSTS.setWidth(100);
		tbTableColumnCtNhapthuocSTS.setText("STS");

        Menu menuCtNhapthuoc = new Menu(tableCtNhapthuoc);
		tableCtNhapthuoc.setMenu(menuCtNhapthuoc);
		
		MenuItem mntmNewItemCtNhapthuoc = new MenuItem(menuCtNhapthuoc, SWT.NONE);
		mntmNewItemCtNhapthuoc.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				newItemCtNhapthuoc();
			}
		});
		mntmNewItemCtNhapthuoc.setImage(SWTResourceManager.getImage(CtNhapthuocDlg.class, "/png/arrow-circle-top-2x.png"));
		mntmNewItemCtNhapthuoc.setText("New");
		
		MenuItem mntmEditItemCtNhapthuoc = new MenuItem(menuCtNhapthuoc, SWT.NONE);
		mntmEditItemCtNhapthuoc.setImage(SWTResourceManager.getImage(CtNhapthuocDlg.class, "/png/wrench-2x.png"));
		mntmEditItemCtNhapthuoc.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				editTableCtNhapthuoc();
			}
		});
		mntmEditItemCtNhapthuoc.setText("Edit");
		
		MenuItem mntmDeleteCtNhapthuoc = new MenuItem(menuCtNhapthuoc, SWT.NONE);
		mntmDeleteCtNhapthuoc.setImage(SWTResourceManager.getImage(CtNhapthuocDlg.class, "/png/circle-x-2x.png"));
		mntmDeleteCtNhapthuoc.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				deleteTableCtNhapthuoc();
			}
		});
		mntmDeleteCtNhapthuoc.setText("Delete");

		tableViewerCtNhapthuoc.setLabelProvider(new TableLabelProviderCtNhapthuoc());
		tableViewerCtNhapthuoc.setContentProvider(new ContentProviderCtNhapthuoc());
		tableViewerCtNhapthuoc.setInput(listDataCtNhapthuoc);
        //
        //
		loadDataCtNhapthuoc();
		//
        reloadTableCtNhapthuoc();
	}
    public void setDataCtNhapthuoc(String textSearchString){
		this.textSearchCtNhapthuocString = textSearchString;
	}
	private void loadDataCtNhapthuoc() {
		if(textSearchCtNhapthuocString!=null){
			textSearchCtNhapthuoc.setText(textSearchCtNhapthuocString);
		}
	}
	protected void reloadTableCtNhapthuoc() {
        if(DbHelper.checkPhanQuyen(DbHelper.READ, "ct_nhapthuoc")==false){
			logger.info("DON'T HAVE READ RIGHT");
			return;
		}
    
        if(listDataCtNhapthuoc!=null){
            // 
            tableViewerCtNhapthuoc.setInput(listDataCtNhapthuoc);
            tableViewerCtNhapthuoc.refresh();
            //
            if(listDataCtNhapthuoc.size()==0){
                textSearchCtNhapthuoc.forceFocus();
            }
            else{
                tableCtNhapthuoc.forceFocus();
                tableCtNhapthuoc.setSelection(0);
            }
            return;
        }
		// Do search in the first time
		String searchString = textSearchCtNhapthuoc.getText().toLowerCase().trim();
		String sql = "select * from ct_nhapthuoc WHERE STS<> "+DbHelper.DELETE_STATUS+" ";
		if(searchString.length()>0){
            sql += " and ( 0 ";
        sql += " or LOWER(TENKHO) like '%"+searchString+"%'";
        sql += " or LOWER(TENTHUOC) like '%"+searchString+"%'";
        sql += " or LOWER(DONVI) like '%"+searchString+"%'";
            sql += " )";
        }
		try  {
            logger.info(sql);
			Connection con = DbHelper.getSql2o();
			listDataCtNhapthuoc = con.createQuery(sql).executeAndFetch(CtNhapthuoc.class);
	    }   
	    catch(Exception e){
	    	logger.error(e);
	    	MessageDialog.openError(shell, "Error", e.getMessage());
	    }
		// 
		tableViewerCtNhapthuoc.setInput(listDataCtNhapthuoc);
		tableViewerCtNhapthuoc.refresh();
        //
        if(listDataCtNhapthuoc.size()==0){
            textSearchCtNhapthuoc.forceFocus();
        }
        else{
            tableCtNhapthuoc.forceFocus();
            tableCtNhapthuoc.setSelection(0);
        }
	}
    
    protected void selectTableCtNhapthuoc() {
		if(tableCtNhapthuoc.getSelectionCount()==0){
			return;
		}
		TableItem item = tableCtNhapthuoc.getSelection()[0];
		CtNhapthuoc obj = (CtNhapthuoc)item.getData();
        logger.info(obj.toString());
        //
        //
    	if(typeCtNhapthuocDlg==TYPE_DLG_CHOOSEN){
    		// Choosen and close dlg
    		objCtNhapthuoc = obj;
    		shell.close();
    	}
    	else{
            if(DbHelper.checkPhanQuyen(DbHelper.UPDATE, "ct_nhapthuoc")==false){
                logger.info("DON'T HAVE UPDATE RIGHT");
                return;
            }
            if(intTypeDlgCtNhapthuoc==TYPE_DLG_VIEW){
                return;
            }
			CtNhapthuocDlg dlg = new CtNhapthuocDlg(shell, 0);
            dlg.setCtNhapthuocDlgData(obj);
            dlg.open();
            //
            reloadTableCtNhapthuoc();
    	}
	}
    protected void editTableCtNhapthuoc() {
        if(intTypeDlgCtNhapthuoc==TYPE_DLG_VIEW){
            return;
        }
        if(DbHelper.checkPhanQuyen(DbHelper.UPDATE, "ct_nhapthuoc")==false){
            logger.info("DON'T HAVE UPDATE RIGHT");
            return;
        }
    	if(tableCtNhapthuoc.getSelectionCount()==0){
			return;
		}
		TableItem item = tableCtNhapthuoc.getSelection()[0];
		CtNhapthuoc obj = (CtNhapthuoc)item.getData();
        logger.info(obj.toString());
        //
        //
		CtNhapthuocDlg dlg = new CtNhapthuocDlg(shell, 0);
        dlg.setCtNhapthuocDlgData(obj);
        dlg.open();
        //
        reloadTableCtNhapthuoc();
	}
    protected void deleteTableCtNhapthuoc() {
        if(intTypeDlgCtNhapthuoc==TYPE_DLG_VIEW){
            return;
        }
        //
        if(DbHelper.checkPhanQuyen(DbHelper.DELETE, "ct_nhapthuoc")==false){
            logger.info("DON'T HAVE DELETE RIGHT");
            return;
        }
		if(tableCtNhapthuoc.getSelectionCount()==0){
			return;
		}
        if(MessageDialog.openConfirm(shell, "Confirm?", "Delete ?")==false){
			return;
		}
		
		TableItem item = tableCtNhapthuoc.getSelection()[0];
		CtNhapthuoc obj = (CtNhapthuoc)item.getData();
        logger.info(obj.toString());
        //
		obj.delete();
        listDataCtNhapthuoc.remove(obj);
        //
		reloadTableCtNhapthuoc();
	}

	protected void newItemCtNhapthuoc() {
        if(intTypeDlgCtNhapthuoc==TYPE_DLG_VIEW){
            return;
        }
        //
        if(DbHelper.checkPhanQuyen(DbHelper.INSERT, "ct_nhapthuoc")==false){
            logger.info("DON'T HAVE INSERT RIGHT");
            return;
        }
		//
		CtNhapthuocDlg dlg = new CtNhapthuocDlg(shell, 0);
        CtNhapthuoc obj = new CtNhapthuoc();
		dlg.setCtNhapthuocDlgData(obj);
		dlg.open();
        listDataCtNhapthuoc.add(obj);
        //
		reloadTableCtNhapthuoc();
		//
	}
}
