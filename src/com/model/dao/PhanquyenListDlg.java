/**

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
public class PhanquyenListDlg extends Dialog {
	static Logger logger = LogManager.getLogger(PhanquyenListDlg.class.getName());
	protected Object result;
	protected Shell shell;

	private class TableLabelProviderPhanquyen extends LabelProvider implements ITableLabelProvider {
		public Image getColumnImage(Object element, int columnIndex) {
			return null;
		}
		public String getColumnText(Object element, int columnIndex) {
			if(element instanceof Phanquyen){
				return ((Phanquyen) element).getIndex(columnIndex);
			}
			return "";
		}
	}
	private static class ContentProviderPhanquyen implements IStructuredContentProvider {
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
	private Table tablePhanquyen;
	private TableViewer tableViewerPhanquyen;
	private List<Phanquyen> listDataPhanquyen;
	private Text textSearchPhanquyen;
	private String textSearchPhanquyenString;
	public Phanquyen objPhanquyen = null;
	public int typePhanquyenDlg = TYPE_DLG_CHOOSEN; 
	public static final int TYPE_DLG_MANAGER = 0;
	public static final int TYPE_DLG_CHOOSEN = 1;

    public int intTypeDlgPhanquyen;
	public static final int TYPE_DLG_VIEW = 1;
	public static final int TYPE_DLG_EDIT = 2;
	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public PhanquyenListDlg(Shell parent, int style) {
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
		shell.setImage(SWTResourceManager.getImage(PhanquyenDlg.class, "/png/list-2x.png"));
		shell.setSize(610, 340);
		shell.setText("Phanquyen List View");
		shell.setLayout(new BorderLayout(0, 0));
		shell.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.keyCode==SWT.ESC){
					objPhanquyen = null;
				}
			}
		});
        
        Composite compositeInShellPhanquyen = new Composite(shell, SWT.NONE);
		compositeInShellPhanquyen.setLayout(new BorderLayout(0, 0));
		compositeInShellPhanquyen.setLayoutData(BorderLayout.CENTER);
        
		Composite compositeHeaderPhanquyen = new Composite(compositeInShellPhanquyen, SWT.NONE);
		compositeHeaderPhanquyen.setLayoutData(BorderLayout.NORTH);
		compositeHeaderPhanquyen.setLayout(new GridLayout(2, false));

		textSearchPhanquyen = new Text(compositeHeaderPhanquyen, SWT.BORDER);
		textSearchPhanquyen.setFont(SWTResourceManager.getFont("Tahoma", 11, SWT.NORMAL));
		textSearchPhanquyen.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.keyCode==13){
					reloadTablePhanquyen();
				}
			}
		});
		
		Button btnNewButtonSearchPhanquyen = new Button(compositeHeaderPhanquyen, SWT.NONE);
		btnNewButtonSearchPhanquyen.setImage(SWTResourceManager.getImage(PhanquyenDlg.class, "/png/media-play-2x.png"));
		btnNewButtonSearchPhanquyen.setFont(SWTResourceManager.getFont("Tahoma", 12, SWT.NORMAL));

		btnNewButtonSearchPhanquyen.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				reloadTablePhanquyen();
			}
		});
		GridData gd_btnNewButtonPhanquyen = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnNewButtonPhanquyen.widthHint = 87;
		btnNewButtonSearchPhanquyen.setLayoutData(gd_btnNewButtonPhanquyen);
		btnNewButtonSearchPhanquyen.setText("Search");
        
		tableViewerPhanquyen = new TableViewer(compositeInShellPhanquyen, SWT.BORDER | SWT.FULL_SELECTION);
		tablePhanquyen = tableViewerPhanquyen.getTable();
		tablePhanquyen.setFont(SWTResourceManager.getFont("Tahoma", 11, SWT.NORMAL));
		tablePhanquyen.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.keyCode==SWT.F5){
					reloadTablePhanquyen();
                }
                if(e.keyCode==SWT.F4){
					editTablePhanquyen();
                }
				else if(e.keyCode==13){
					selectTablePhanquyen();
				}
                else if(e.keyCode==SWT.DEL){
					deleteTablePhanquyen();
				}
                else if(e.keyCode==SWT.F7){
					newItemPhanquyen();
				}
			}
		});
        tablePhanquyen.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				selectTablePhanquyen();
			}
		});
        
		tablePhanquyen.setLinesVisible(true);
		tablePhanquyen.setHeaderVisible(true);
		tablePhanquyen.setLayoutData(BorderLayout.CENTER);

		TableColumn tbTableColumnPhanquyenU_ID = new TableColumn(tablePhanquyen, SWT.RIGHT);
		tbTableColumnPhanquyenU_ID.setWidth(100);
		tbTableColumnPhanquyenU_ID.setText("U_ID");

		TableColumn tbTableColumnPhanquyenU_NAME = new TableColumn(tablePhanquyen, SWT.LEFT);
		tbTableColumnPhanquyenU_NAME.setWidth(100);
		tbTableColumnPhanquyenU_NAME.setText("U_NAME");

		TableColumn tbTableColumnPhanquyenTABLE_NAME = new TableColumn(tablePhanquyen, SWT.LEFT);
		tbTableColumnPhanquyenTABLE_NAME.setWidth(100);
		tbTableColumnPhanquyenTABLE_NAME.setText("TABLE_NAME");

		TableColumn tbTableColumnPhanquyenREAD = new TableColumn(tablePhanquyen, SWT.RIGHT);
		tbTableColumnPhanquyenREAD.setWidth(100);
		tbTableColumnPhanquyenREAD.setText("READ");

		TableColumn tbTableColumnPhanquyenINSERT = new TableColumn(tablePhanquyen, SWT.RIGHT);
		tbTableColumnPhanquyenINSERT.setWidth(100);
		tbTableColumnPhanquyenINSERT.setText("INSERT");

		TableColumn tbTableColumnPhanquyenUPDATE = new TableColumn(tablePhanquyen, SWT.RIGHT);
		tbTableColumnPhanquyenUPDATE.setWidth(100);
		tbTableColumnPhanquyenUPDATE.setText("UPDATE");

		TableColumn tbTableColumnPhanquyenDELETE = new TableColumn(tablePhanquyen, SWT.RIGHT);
		tbTableColumnPhanquyenDELETE.setWidth(100);
		tbTableColumnPhanquyenDELETE.setText("DELETE");

		TableColumn tbTableColumnPhanquyenSTS = new TableColumn(tablePhanquyen, SWT.RIGHT);
		tbTableColumnPhanquyenSTS.setWidth(100);
		tbTableColumnPhanquyenSTS.setText("STS");

        Menu menuPhanquyen = new Menu(tablePhanquyen);
		tablePhanquyen.setMenu(menuPhanquyen);
		
		MenuItem mntmNewItemPhanquyen = new MenuItem(menuPhanquyen, SWT.NONE);
		mntmNewItemPhanquyen.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				newItemPhanquyen();
			}
		});
		mntmNewItemPhanquyen.setImage(SWTResourceManager.getImage(PhanquyenDlg.class, "/png/arrow-circle-top-2x.png"));
		mntmNewItemPhanquyen.setText("New");
		
		MenuItem mntmEditItemPhanquyen = new MenuItem(menuPhanquyen, SWT.NONE);
		mntmEditItemPhanquyen.setImage(SWTResourceManager.getImage(PhanquyenDlg.class, "/png/wrench-2x.png"));
		mntmEditItemPhanquyen.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				editTablePhanquyen();
			}
		});
		mntmEditItemPhanquyen.setText("Edit");
		
		MenuItem mntmDeletePhanquyen = new MenuItem(menuPhanquyen, SWT.NONE);
		mntmDeletePhanquyen.setImage(SWTResourceManager.getImage(PhanquyenDlg.class, "/png/circle-x-2x.png"));
		mntmDeletePhanquyen.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				deleteTablePhanquyen();
			}
		});
		mntmDeletePhanquyen.setText("Delete");

		tableViewerPhanquyen.setLabelProvider(new TableLabelProviderPhanquyen());
		tableViewerPhanquyen.setContentProvider(new ContentProviderPhanquyen());
		tableViewerPhanquyen.setInput(listDataPhanquyen);
        //
        //
		loadDataPhanquyen();
		//
        reloadTablePhanquyen();
	}
    public void setDataPhanquyen(String textSearchString){
		this.textSearchPhanquyenString = textSearchString;
	}
	private void loadDataPhanquyen() {
		if(textSearchPhanquyenString!=null){
			textSearchPhanquyen.setText(textSearchPhanquyenString);
		}
	}
	protected void reloadTablePhanquyen() {
        if(DbHelper.checkPhanQuyen(DbHelper.READ, "phanquyen")==false){
			logger.info("DON'T HAVE READ RIGHT");
			return;
		}
    
        if(listDataPhanquyen!=null){
            // 
            tableViewerPhanquyen.setInput(listDataPhanquyen);
            tableViewerPhanquyen.refresh();
            //
            if(listDataPhanquyen.size()==0){
                textSearchPhanquyen.forceFocus();
            }
            else{
                tablePhanquyen.forceFocus();
                tablePhanquyen.setSelection(0);
            }
            return;
        }
		// Do search in the first time
		String searchString = textSearchPhanquyen.getText().toLowerCase().trim();
		String sql = "select * from phanquyen WHERE STS<> "+DbHelper.DELETE_STATUS+" ";
		if(searchString.length()>0){
            sql += " and ( 0 ";
        sql += " or LOWER(U_NAME) like '%"+searchString+"%'";
        sql += " or LOWER(TABLE_NAME) like '%"+searchString+"%'";
            sql += " )";
        }
		try  {
            logger.info(sql);
			Connection con = DbHelper.getSql2o();
			listDataPhanquyen = con.createQuery(sql).executeAndFetch(Phanquyen.class);
	    }   
	    catch(Exception e){
	    	logger.error(e);
	    	MessageDialog.openError(shell, "Error", e.getMessage());
	    }
		// 
		tableViewerPhanquyen.setInput(listDataPhanquyen);
		tableViewerPhanquyen.refresh();
        //
        if(listDataPhanquyen.size()==0){
            textSearchPhanquyen.forceFocus();
        }
        else{
            tablePhanquyen.forceFocus();
            tablePhanquyen.setSelection(0);
        }
	}
    
    protected void selectTablePhanquyen() {
		if(tablePhanquyen.getSelectionCount()==0){
			return;
		}
		TableItem item = tablePhanquyen.getSelection()[0];
		Phanquyen obj = (Phanquyen)item.getData();
        logger.info(obj.toString());
        //
        //
    	if(typePhanquyenDlg==TYPE_DLG_CHOOSEN){
    		// Choosen and close dlg
    		objPhanquyen = obj;
    		shell.close();
    	}
    	else{
            if(DbHelper.checkPhanQuyen(DbHelper.UPDATE, "phanquyen")==false){
                logger.info("DON'T HAVE UPDATE RIGHT");
                return;
            }
            if(intTypeDlgPhanquyen==TYPE_DLG_VIEW){
                return;
            }
			PhanquyenDlg dlg = new PhanquyenDlg(shell, 0);
            dlg.setPhanquyenDlgData(obj);
            dlg.open();
            //
            reloadTablePhanquyen();
    	}
	}
    protected void editTablePhanquyen() {
        if(intTypeDlgPhanquyen==TYPE_DLG_VIEW){
            return;
        }
        if(DbHelper.checkPhanQuyen(DbHelper.UPDATE, "phanquyen")==false){
            logger.info("DON'T HAVE UPDATE RIGHT");
            return;
        }
    	if(tablePhanquyen.getSelectionCount()==0){
			return;
		}
		TableItem item = tablePhanquyen.getSelection()[0];
		Phanquyen obj = (Phanquyen)item.getData();
        logger.info(obj.toString());
        //
        //
		PhanquyenDlg dlg = new PhanquyenDlg(shell, 0);
        dlg.setPhanquyenDlgData(obj);
        dlg.open();
        //
        reloadTablePhanquyen();
	}
    protected void deleteTablePhanquyen() {
        if(intTypeDlgPhanquyen==TYPE_DLG_VIEW){
            return;
        }
        //
        if(DbHelper.checkPhanQuyen(DbHelper.DELETE, "phanquyen")==false){
            logger.info("DON'T HAVE DELETE RIGHT");
            return;
        }
		if(tablePhanquyen.getSelectionCount()==0){
			return;
		}
        if(MessageDialog.openConfirm(shell, "Confirm?", "Delete ?")==false){
			return;
		}
		
		TableItem item = tablePhanquyen.getSelection()[0];
		Phanquyen obj = (Phanquyen)item.getData();
        logger.info(obj.toString());
        //
		obj.delete();
        listDataPhanquyen.remove(obj);
        //
		reloadTablePhanquyen();
	}

	protected void newItemPhanquyen() {
        if(intTypeDlgPhanquyen==TYPE_DLG_VIEW){
            return;
        }
        //
        if(DbHelper.checkPhanQuyen(DbHelper.INSERT, "phanquyen")==false){
            logger.info("DON'T HAVE INSERT RIGHT");
            return;
        }
		//
		PhanquyenDlg dlg = new PhanquyenDlg(shell, 0);
        Phanquyen obj = new Phanquyen();
		dlg.setPhanquyenDlgData(obj);
		dlg.open();
        listDataPhanquyen.add(obj);
        //
		reloadTablePhanquyen();
		//
	}
}
