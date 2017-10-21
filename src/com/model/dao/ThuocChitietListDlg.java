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
public class ThuocChitietListDlg extends Dialog {
	static Logger logger = LogManager.getLogger(ThuocChitietListDlg.class.getName());
	protected Object result;
	protected Shell shell;

	private class TableLabelProviderThuocChitiet extends LabelProvider implements ITableLabelProvider {
		public Image getColumnImage(Object element, int columnIndex) {
			return null;
		}
		public String getColumnText(Object element, int columnIndex) {
			if(element instanceof ThuocChitiet){
				return ((ThuocChitiet) element).getIndex(columnIndex);
			}
			return "";
		}
	}
	private static class ContentProviderThuocChitiet implements IStructuredContentProvider {
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
	private Table tableThuocChitiet;
	private TableViewer tableViewerThuocChitiet;
	private List<ThuocChitiet> listDataThuocChitiet;
	private Text textSearchThuocChitiet;
	private String textSearchThuocChitietString;
	public ThuocChitiet objThuocChitiet = null;
	public int typeThuocChitietDlg = TYPE_DLG_CHOOSEN; 
	public static final int TYPE_DLG_MANAGER = 0;
	public static final int TYPE_DLG_CHOOSEN = 1;

    public int intTypeDlgThuocChitiet;
	public static final int TYPE_DLG_VIEW = 1;
	public static final int TYPE_DLG_EDIT = 2;
	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public ThuocChitietListDlg(Shell parent, int style) {
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
		shell.setImage(SWTResourceManager.getImage(ThuocChitietDlg.class, "/png/list-2x.png"));
		shell.setSize(610, 340);
		shell.setText("ThuocChitiet List View");
		shell.setLayout(new BorderLayout(0, 0));
		shell.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.keyCode==SWT.ESC){
					objThuocChitiet = null;
				}
			}
		});
        
        Composite compositeInShellThuocChitiet = new Composite(shell, SWT.NONE);
		compositeInShellThuocChitiet.setLayout(new BorderLayout(0, 0));
		compositeInShellThuocChitiet.setLayoutData(BorderLayout.CENTER);
        
		Composite compositeHeaderThuocChitiet = new Composite(compositeInShellThuocChitiet, SWT.NONE);
		compositeHeaderThuocChitiet.setLayoutData(BorderLayout.NORTH);
		compositeHeaderThuocChitiet.setLayout(new GridLayout(2, false));

		textSearchThuocChitiet = new Text(compositeHeaderThuocChitiet, SWT.BORDER);
		textSearchThuocChitiet.setFont(SWTResourceManager.getFont("Tahoma", 11, SWT.NORMAL));
		textSearchThuocChitiet.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.keyCode==13){
					reloadTableThuocChitiet();
				}
			}
		});
		
		Button btnNewButtonSearchThuocChitiet = new Button(compositeHeaderThuocChitiet, SWT.NONE);
		btnNewButtonSearchThuocChitiet.setImage(SWTResourceManager.getImage(ThuocChitietDlg.class, "/png/media-play-2x.png"));
		btnNewButtonSearchThuocChitiet.setFont(SWTResourceManager.getFont("Tahoma", 12, SWT.NORMAL));

		btnNewButtonSearchThuocChitiet.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				reloadTableThuocChitiet();
			}
		});
		GridData gd_btnNewButtonThuocChitiet = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnNewButtonThuocChitiet.widthHint = 87;
		btnNewButtonSearchThuocChitiet.setLayoutData(gd_btnNewButtonThuocChitiet);
		btnNewButtonSearchThuocChitiet.setText("Search");
        
		tableViewerThuocChitiet = new TableViewer(compositeInShellThuocChitiet, SWT.BORDER | SWT.FULL_SELECTION);
		tableThuocChitiet = tableViewerThuocChitiet.getTable();
		tableThuocChitiet.setFont(SWTResourceManager.getFont("Tahoma", 11, SWT.NORMAL));
		tableThuocChitiet.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.keyCode==SWT.F5){
					reloadTableThuocChitiet();
                }
                if(e.keyCode==SWT.F4){
					editTableThuocChitiet();
                }
				else if(e.keyCode==13){
					selectTableThuocChitiet();
				}
                else if(e.keyCode==SWT.DEL){
					deleteTableThuocChitiet();
				}
                else if(e.keyCode==SWT.F7){
					newItemThuocChitiet();
				}
			}
		});
        tableThuocChitiet.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				selectTableThuocChitiet();
			}
		});
        
		tableThuocChitiet.setLinesVisible(true);
		tableThuocChitiet.setHeaderVisible(true);
		tableThuocChitiet.setLayoutData(BorderLayout.CENTER);

		TableColumn tbTableColumnThuocChitietSTT = new TableColumn(tableThuocChitiet, SWT.RIGHT);
		tbTableColumnThuocChitietSTT.setWidth(100);
		tbTableColumnThuocChitietSTT.setText("STT");

		TableColumn tbTableColumnThuocChitietMA_LK = new TableColumn(tableThuocChitiet, SWT.RIGHT);
		tbTableColumnThuocChitietMA_LK.setWidth(100);
		tbTableColumnThuocChitietMA_LK.setText("MA_LK");

		TableColumn tbTableColumnThuocChitietTHUOC_ID = new TableColumn(tableThuocChitiet, SWT.RIGHT);
		tbTableColumnThuocChitietTHUOC_ID.setWidth(100);
		tbTableColumnThuocChitietTHUOC_ID.setText("THUOC_ID");

		TableColumn tbTableColumnThuocChitietSOLUONG = new TableColumn(tableThuocChitiet, SWT.RIGHT);
		tbTableColumnThuocChitietSOLUONG.setWidth(100);
		tbTableColumnThuocChitietSOLUONG.setText("SOLUONG");

		TableColumn tbTableColumnThuocChitietDON_GIA = new TableColumn(tableThuocChitiet, SWT.RIGHT);
		tbTableColumnThuocChitietDON_GIA.setWidth(100);
		tbTableColumnThuocChitietDON_GIA.setText("DON_GIA");

		TableColumn tbTableColumnThuocChitietTEN_THUOC = new TableColumn(tableThuocChitiet, SWT.LEFT);
		tbTableColumnThuocChitietTEN_THUOC.setWidth(100);
		tbTableColumnThuocChitietTEN_THUOC.setText("TEN_THUOC");

		TableColumn tbTableColumnThuocChitietDON_VI_TINH = new TableColumn(tableThuocChitiet, SWT.LEFT);
		tbTableColumnThuocChitietDON_VI_TINH.setWidth(100);
		tbTableColumnThuocChitietDON_VI_TINH.setText("DON_VI_TINH");

		TableColumn tbTableColumnThuocChitietHOATCHAT = new TableColumn(tableThuocChitiet, SWT.LEFT);
		tbTableColumnThuocChitietHOATCHAT.setWidth(100);
		tbTableColumnThuocChitietHOATCHAT.setText("HOATCHAT");

		TableColumn tbTableColumnThuocChitietHAMLUONG = new TableColumn(tableThuocChitiet, SWT.LEFT);
		tbTableColumnThuocChitietHAMLUONG.setWidth(100);
		tbTableColumnThuocChitietHAMLUONG.setText("HAMLUONG");

		TableColumn tbTableColumnThuocChitietDONGGOI = new TableColumn(tableThuocChitiet, SWT.LEFT);
		tbTableColumnThuocChitietDONGGOI.setWidth(100);
		tbTableColumnThuocChitietDONGGOI.setText("DONGGOI");


		TableColumn tbTableColumnThuocChitietTYLE_TT = new TableColumn(tableThuocChitiet, SWT.NONE);
		tbTableColumnThuocChitietTYLE_TT.setWidth(100);
		tbTableColumnThuocChitietTYLE_TT.setText("TYLE_TT");

		TableColumn tbTableColumnThuocChitietKHO_NAME = new TableColumn(tableThuocChitiet, SWT.LEFT);
		tbTableColumnThuocChitietKHO_NAME.setWidth(100);
		tbTableColumnThuocChitietKHO_NAME.setText("KHO_NAME");

		TableColumn tbTableColumnThuocChitietTHANH_TIEN = new TableColumn(tableThuocChitiet, SWT.RIGHT);
		tbTableColumnThuocChitietTHANH_TIEN.setWidth(100);
		tbTableColumnThuocChitietTHANH_TIEN.setText("THANH_TIEN");

		TableColumn tbTableColumnThuocChitietCT_ID = new TableColumn(tableThuocChitiet, SWT.RIGHT);
		tbTableColumnThuocChitietCT_ID.setWidth(100);
		tbTableColumnThuocChitietCT_ID.setText("CT_ID");

		TableColumn tbTableColumnThuocChitietNT_ID = new TableColumn(tableThuocChitiet, SWT.RIGHT);
		tbTableColumnThuocChitietNT_ID.setWidth(100);
		tbTableColumnThuocChitietNT_ID.setText("NT_ID");

		TableColumn tbTableColumnThuocChitietTT_BH = new TableColumn(tableThuocChitiet, SWT.RIGHT);
		tbTableColumnThuocChitietTT_BH.setWidth(100);
		tbTableColumnThuocChitietTT_BH.setText("TT_BH");

		TableColumn tbTableColumnThuocChitietTT_NB = new TableColumn(tableThuocChitiet, SWT.RIGHT);
		tbTableColumnThuocChitietTT_NB.setWidth(100);
		tbTableColumnThuocChitietTT_NB.setText("TT_NB");

		TableColumn tbTableColumnThuocChitietMA_BENH = new TableColumn(tableThuocChitiet, SWT.LEFT);
		tbTableColumnThuocChitietMA_BENH.setWidth(100);
		tbTableColumnThuocChitietMA_BENH.setText("MA_BENH");

		TableColumn tbTableColumnThuocChitietSTS = new TableColumn(tableThuocChitiet, SWT.RIGHT);
		tbTableColumnThuocChitietSTS.setWidth(100);
		tbTableColumnThuocChitietSTS.setText("STS");

        Menu menuThuocChitiet = new Menu(tableThuocChitiet);
		tableThuocChitiet.setMenu(menuThuocChitiet);
		
		MenuItem mntmNewItemThuocChitiet = new MenuItem(menuThuocChitiet, SWT.NONE);
		mntmNewItemThuocChitiet.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				newItemThuocChitiet();
			}
		});
		mntmNewItemThuocChitiet.setImage(SWTResourceManager.getImage(ThuocChitietDlg.class, "/png/arrow-circle-top-2x.png"));
		mntmNewItemThuocChitiet.setText("New");
		
		MenuItem mntmEditItemThuocChitiet = new MenuItem(menuThuocChitiet, SWT.NONE);
		mntmEditItemThuocChitiet.setImage(SWTResourceManager.getImage(ThuocChitietDlg.class, "/png/wrench-2x.png"));
		mntmEditItemThuocChitiet.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				editTableThuocChitiet();
			}
		});
		mntmEditItemThuocChitiet.setText("Edit");
		
		MenuItem mntmDeleteThuocChitiet = new MenuItem(menuThuocChitiet, SWT.NONE);
		mntmDeleteThuocChitiet.setImage(SWTResourceManager.getImage(ThuocChitietDlg.class, "/png/circle-x-2x.png"));
		mntmDeleteThuocChitiet.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				deleteTableThuocChitiet();
			}
		});
		mntmDeleteThuocChitiet.setText("Delete");

		tableViewerThuocChitiet.setLabelProvider(new TableLabelProviderThuocChitiet());
		tableViewerThuocChitiet.setContentProvider(new ContentProviderThuocChitiet());
		tableViewerThuocChitiet.setInput(listDataThuocChitiet);
        //
        //
		loadDataThuocChitiet();
		//
        reloadTableThuocChitiet();
	}
    public void setDataThuocChitiet(String textSearchString){
		this.textSearchThuocChitietString = textSearchString;
	}
	private void loadDataThuocChitiet() {
		if(textSearchThuocChitietString!=null){
			textSearchThuocChitiet.setText(textSearchThuocChitietString);
		}
	}
	protected void reloadTableThuocChitiet() {
        if(DbHelper.checkPhanQuyen(DbHelper.READ, "thuoc_chitiet")==false){
			logger.info("DON'T HAVE READ RIGHT");
			return;
		}
    
        if(listDataThuocChitiet!=null){
            // 
            tableViewerThuocChitiet.setInput(listDataThuocChitiet);
            tableViewerThuocChitiet.refresh();
            //
            if(listDataThuocChitiet.size()==0){
                textSearchThuocChitiet.forceFocus();
            }
            else{
                tableThuocChitiet.forceFocus();
                tableThuocChitiet.setSelection(0);
            }
            return;
        }
		// Do search in the first time
		String searchString = textSearchThuocChitiet.getText().toLowerCase().trim();
		String sql = "select * from thuoc_chitiet WHERE STS<> "+DbHelper.DELETE_STATUS+" ";
		if(searchString.length()>0){
            sql += " and ( 0 ";
        sql += " or LOWER(TEN_THUOC) like '%"+searchString+"%'";
        sql += " or LOWER(DON_VI_TINH) like '%"+searchString+"%'";
        sql += " or LOWER(HOATCHAT) like '%"+searchString+"%'";
        sql += " or LOWER(HAMLUONG) like '%"+searchString+"%'";
        sql += " or LOWER(DONGGOI) like '%"+searchString+"%'";
        sql += " or LOWER(KHO_NAME) like '%"+searchString+"%'";
        sql += " or LOWER(MA_BENH) like '%"+searchString+"%'";
            sql += " )";
        }
		try  {
            logger.info(sql);
			Connection con = DbHelper.getSql2o();
			listDataThuocChitiet = con.createQuery(sql).executeAndFetch(ThuocChitiet.class);
	    }   
	    catch(Exception e){
	    	logger.error(e);
	    	MessageDialog.openError(shell, "Error", e.getMessage());
	    }
		// 
		tableViewerThuocChitiet.setInput(listDataThuocChitiet);
		tableViewerThuocChitiet.refresh();
        //
        if(listDataThuocChitiet.size()==0){
            textSearchThuocChitiet.forceFocus();
        }
        else{
            tableThuocChitiet.forceFocus();
            tableThuocChitiet.setSelection(0);
        }
	}
    
    protected void selectTableThuocChitiet() {
		if(tableThuocChitiet.getSelectionCount()==0){
			return;
		}
		TableItem item = tableThuocChitiet.getSelection()[0];
		ThuocChitiet obj = (ThuocChitiet)item.getData();
        logger.info(obj.toString());
        //
        //
    	if(typeThuocChitietDlg==TYPE_DLG_CHOOSEN){
    		// Choosen and close dlg
    		objThuocChitiet = obj;
    		shell.close();
    	}
    	else{
            if(DbHelper.checkPhanQuyen(DbHelper.UPDATE, "thuoc_chitiet")==false){
                logger.info("DON'T HAVE UPDATE RIGHT");
                return;
            }
            if(intTypeDlgThuocChitiet==TYPE_DLG_VIEW){
                return;
            }
			ThuocChitietDlg dlg = new ThuocChitietDlg(shell, 0);
            dlg.setThuocChitietDlgData(obj);
            dlg.open();
            //
            reloadTableThuocChitiet();
    	}
	}
    protected void editTableThuocChitiet() {
        if(intTypeDlgThuocChitiet==TYPE_DLG_VIEW){
            return;
        }
        if(DbHelper.checkPhanQuyen(DbHelper.UPDATE, "thuoc_chitiet")==false){
            logger.info("DON'T HAVE UPDATE RIGHT");
            return;
        }
    	if(tableThuocChitiet.getSelectionCount()==0){
			return;
		}
		TableItem item = tableThuocChitiet.getSelection()[0];
		ThuocChitiet obj = (ThuocChitiet)item.getData();
        logger.info(obj.toString());
        //
        //
		ThuocChitietDlg dlg = new ThuocChitietDlg(shell, 0);
        dlg.setThuocChitietDlgData(obj);
        dlg.open();
        //
        reloadTableThuocChitiet();
	}
    protected void deleteTableThuocChitiet() {
        if(intTypeDlgThuocChitiet==TYPE_DLG_VIEW){
            return;
        }
        //
        if(DbHelper.checkPhanQuyen(DbHelper.DELETE, "thuoc_chitiet")==false){
            logger.info("DON'T HAVE DELETE RIGHT");
            return;
        }
		if(tableThuocChitiet.getSelectionCount()==0){
			return;
		}
        if(MessageDialog.openConfirm(shell, "Confirm?", "Delete ?")==false){
			return;
		}
		
		TableItem item = tableThuocChitiet.getSelection()[0];
		ThuocChitiet obj = (ThuocChitiet)item.getData();
        logger.info(obj.toString());
        //
		obj.delete();
        listDataThuocChitiet.remove(obj);
        //
		reloadTableThuocChitiet();
	}

	protected void newItemThuocChitiet() {
        if(intTypeDlgThuocChitiet==TYPE_DLG_VIEW){
            return;
        }
        //
        if(DbHelper.checkPhanQuyen(DbHelper.INSERT, "thuoc_chitiet")==false){
            logger.info("DON'T HAVE INSERT RIGHT");
            return;
        }
		//
		ThuocChitietDlg dlg = new ThuocChitietDlg(shell, 0);
        ThuocChitiet obj = new ThuocChitiet();
		dlg.setThuocChitietDlgData(obj);
		dlg.open();
        listDataThuocChitiet.add(obj);
        //
		reloadTableThuocChitiet();
		//
	}
}