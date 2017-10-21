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
public class MstChisoListDlg extends Dialog {
	static Logger logger = LogManager.getLogger(MstChisoListDlg.class.getName());
	protected Object result;
	protected Shell shell;

	private class TableLabelProviderMstChiso extends LabelProvider implements ITableLabelProvider {
		public Image getColumnImage(Object element, int columnIndex) {
			return null;
		}
		public String getColumnText(Object element, int columnIndex) {
			if(element instanceof MstChiso){
				return ((MstChiso) element).getIndex(columnIndex);
			}
			return "";
		}
	}
	private static class ContentProviderMstChiso implements IStructuredContentProvider {
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
	private Table tableMstChiso;
	private TableViewer tableViewerMstChiso;
	private List<MstChiso> listDataMstChiso;
	private Text textSearchMstChiso;
	private String textSearchMstChisoString;
	public MstChiso objMstChiso = null;
	public int typeMstChisoDlg = TYPE_DLG_CHOOSEN; 
	public static final int TYPE_DLG_MANAGER = 0;
	public static final int TYPE_DLG_CHOOSEN = 1;

    public int intTypeDlgMstChiso;
	public static final int TYPE_DLG_VIEW = 1;
	public static final int TYPE_DLG_EDIT = 2;
	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public MstChisoListDlg(Shell parent, int style) {
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
		shell.setImage(SWTResourceManager.getImage(MstChisoDlg.class, "/png/list-2x.png"));
		shell.setSize(610, 340);
		shell.setText("MstChiso List View");
		shell.setLayout(new BorderLayout(0, 0));
		shell.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.keyCode==SWT.ESC){
					objMstChiso = null;
				}
			}
		});
        
        Composite compositeInShellMstChiso = new Composite(shell, SWT.NONE);
		compositeInShellMstChiso.setLayout(new BorderLayout(0, 0));
		compositeInShellMstChiso.setLayoutData(BorderLayout.CENTER);
        
		Composite compositeHeaderMstChiso = new Composite(compositeInShellMstChiso, SWT.NONE);
		compositeHeaderMstChiso.setLayoutData(BorderLayout.NORTH);
		compositeHeaderMstChiso.setLayout(new GridLayout(2, false));

		textSearchMstChiso = new Text(compositeHeaderMstChiso, SWT.BORDER);
		textSearchMstChiso.setFont(SWTResourceManager.getFont("Tahoma", 11, SWT.NORMAL));
		textSearchMstChiso.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.keyCode==13){
					reloadTableMstChiso();
				}
			}
		});
		
		Button btnNewButtonSearchMstChiso = new Button(compositeHeaderMstChiso, SWT.NONE);
		btnNewButtonSearchMstChiso.setImage(SWTResourceManager.getImage(MstChisoDlg.class, "/png/media-play-2x.png"));
		btnNewButtonSearchMstChiso.setFont(SWTResourceManager.getFont("Tahoma", 12, SWT.NORMAL));

		btnNewButtonSearchMstChiso.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				reloadTableMstChiso();
			}
		});
		GridData gd_btnNewButtonMstChiso = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnNewButtonMstChiso.widthHint = 87;
		btnNewButtonSearchMstChiso.setLayoutData(gd_btnNewButtonMstChiso);
		btnNewButtonSearchMstChiso.setText("Search");
        
		tableViewerMstChiso = new TableViewer(compositeInShellMstChiso, SWT.BORDER | SWT.FULL_SELECTION);
		tableMstChiso = tableViewerMstChiso.getTable();
		tableMstChiso.setFont(SWTResourceManager.getFont("Tahoma", 11, SWT.NORMAL));
		tableMstChiso.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.keyCode==SWT.F5){
					reloadTableMstChiso();
                }
                if(e.keyCode==SWT.F4){
					editTableMstChiso();
                }
				else if(e.keyCode==13){
					selectTableMstChiso();
				}
                else if(e.keyCode==SWT.DEL){
					deleteTableMstChiso();
				}
                else if(e.keyCode==SWT.F7){
					newItemMstChiso();
				}
			}
		});
        tableMstChiso.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				selectTableMstChiso();
			}
		});
        
		tableMstChiso.setLinesVisible(true);
		tableMstChiso.setHeaderVisible(true);
		tableMstChiso.setLayoutData(BorderLayout.CENTER);

		TableColumn tbTableColumnMstChisoCS_NAME = new TableColumn(tableMstChiso, SWT.LEFT);
		tbTableColumnMstChisoCS_NAME.setWidth(100);
		tbTableColumnMstChisoCS_NAME.setText("CS_NAME");

		TableColumn tbTableColumnMstChisoCS_RANGE1 = new TableColumn(tableMstChiso, SWT.LEFT);
		tbTableColumnMstChisoCS_RANGE1.setWidth(100);
		tbTableColumnMstChisoCS_RANGE1.setText("CS_RANGE1");

		TableColumn tbTableColumnMstChisoCS_RANGE2 = new TableColumn(tableMstChiso, SWT.LEFT);
		tbTableColumnMstChisoCS_RANGE2.setWidth(100);
		tbTableColumnMstChisoCS_RANGE2.setText("CS_RANGE2");

		TableColumn tbTableColumnMstChisoCS_DEFAULT = new TableColumn(tableMstChiso, SWT.LEFT);
		tbTableColumnMstChisoCS_DEFAULT.setWidth(100);
		tbTableColumnMstChisoCS_DEFAULT.setText("CS_DEFAULT");

		TableColumn tbTableColumnMstChisoCS_DESC = new TableColumn(tableMstChiso, SWT.LEFT);
		tbTableColumnMstChisoCS_DESC.setWidth(100);
		tbTableColumnMstChisoCS_DESC.setText("CS_DESC");

		TableColumn tbTableColumnMstChisoSTS = new TableColumn(tableMstChiso, SWT.RIGHT);
		tbTableColumnMstChisoSTS.setWidth(100);
		tbTableColumnMstChisoSTS.setText("STS");

        Menu menuMstChiso = new Menu(tableMstChiso);
		tableMstChiso.setMenu(menuMstChiso);
		
		MenuItem mntmNewItemMstChiso = new MenuItem(menuMstChiso, SWT.NONE);
		mntmNewItemMstChiso.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				newItemMstChiso();
			}
		});
		mntmNewItemMstChiso.setImage(SWTResourceManager.getImage(MstChisoDlg.class, "/png/arrow-circle-top-2x.png"));
		mntmNewItemMstChiso.setText("New");
		
		MenuItem mntmEditItemMstChiso = new MenuItem(menuMstChiso, SWT.NONE);
		mntmEditItemMstChiso.setImage(SWTResourceManager.getImage(MstChisoDlg.class, "/png/wrench-2x.png"));
		mntmEditItemMstChiso.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				editTableMstChiso();
			}
		});
		mntmEditItemMstChiso.setText("Edit");
		
		MenuItem mntmDeleteMstChiso = new MenuItem(menuMstChiso, SWT.NONE);
		mntmDeleteMstChiso.setImage(SWTResourceManager.getImage(MstChisoDlg.class, "/png/circle-x-2x.png"));
		mntmDeleteMstChiso.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				deleteTableMstChiso();
			}
		});
		mntmDeleteMstChiso.setText("Delete");

		tableViewerMstChiso.setLabelProvider(new TableLabelProviderMstChiso());
		tableViewerMstChiso.setContentProvider(new ContentProviderMstChiso());
		tableViewerMstChiso.setInput(listDataMstChiso);
        //
        //
		loadDataMstChiso();
		//
        reloadTableMstChiso();
	}
    public void setDataMstChiso(String textSearchString){
		this.textSearchMstChisoString = textSearchString;
	}
	private void loadDataMstChiso() {
		if(textSearchMstChisoString!=null){
			textSearchMstChiso.setText(textSearchMstChisoString);
		}
	}
	protected void reloadTableMstChiso() {
        if(DbHelper.checkPhanQuyen(DbHelper.READ, "mst_chiso")==false){
			logger.info("DON'T HAVE READ RIGHT");
			return;
		}
    
        if(listDataMstChiso!=null){
            // 
            tableViewerMstChiso.setInput(listDataMstChiso);
            tableViewerMstChiso.refresh();
            //
            if(listDataMstChiso.size()==0){
                textSearchMstChiso.forceFocus();
            }
            else{
                tableMstChiso.forceFocus();
                tableMstChiso.setSelection(0);
            }
            return;
        }
		// Do search in the first time
		String searchString = textSearchMstChiso.getText().toLowerCase().trim();
		String sql = "select * from mst_chiso WHERE STS<> "+DbHelper.DELETE_STATUS+" ";
		if(searchString.length()>0){
            sql += " and ( 0 ";
        sql += " or LOWER(CS_NAME) like '%"+searchString+"%'";
        sql += " or LOWER(CS_RANGE1) like '%"+searchString+"%'";
        sql += " or LOWER(CS_RANGE2) like '%"+searchString+"%'";
        sql += " or LOWER(CS_DEFAULT) like '%"+searchString+"%'";
        sql += " or LOWER(CS_DESC) like '%"+searchString+"%'";
            sql += " )";
        }
		try  {
            logger.info(sql);
			Connection con = DbHelper.getSql2o();
			listDataMstChiso = con.createQuery(sql).executeAndFetch(MstChiso.class);
	    }   
	    catch(Exception e){
	    	logger.error(e);
	    	MessageDialog.openError(shell, "Error", e.getMessage());
	    }
		// 
		tableViewerMstChiso.setInput(listDataMstChiso);
		tableViewerMstChiso.refresh();
        //
        if(listDataMstChiso.size()==0){
            textSearchMstChiso.forceFocus();
        }
        else{
            tableMstChiso.forceFocus();
            tableMstChiso.setSelection(0);
        }
	}
    
    protected void selectTableMstChiso() {
		if(tableMstChiso.getSelectionCount()==0){
			return;
		}
		TableItem item = tableMstChiso.getSelection()[0];
		MstChiso obj = (MstChiso)item.getData();
        logger.info(obj.toString());
        //
        //
    	if(typeMstChisoDlg==TYPE_DLG_CHOOSEN){
    		// Choosen and close dlg
    		objMstChiso = obj;
    		shell.close();
    	}
    	else{
            if(DbHelper.checkPhanQuyen(DbHelper.UPDATE, "mst_chiso")==false){
                logger.info("DON'T HAVE UPDATE RIGHT");
                return;
            }
            if(intTypeDlgMstChiso==TYPE_DLG_VIEW){
                return;
            }
			MstChisoDlg dlg = new MstChisoDlg(shell, 0);
            dlg.setMstChisoDlgData(obj);
            dlg.open();
            //
            reloadTableMstChiso();
    	}
	}
    protected void editTableMstChiso() {
        if(intTypeDlgMstChiso==TYPE_DLG_VIEW){
            return;
        }
        if(DbHelper.checkPhanQuyen(DbHelper.UPDATE, "mst_chiso")==false){
            logger.info("DON'T HAVE UPDATE RIGHT");
            return;
        }
    	if(tableMstChiso.getSelectionCount()==0){
			return;
		}
		TableItem item = tableMstChiso.getSelection()[0];
		MstChiso obj = (MstChiso)item.getData();
        logger.info(obj.toString());
        //
        //
		MstChisoDlg dlg = new MstChisoDlg(shell, 0);
        dlg.setMstChisoDlgData(obj);
        dlg.open();
        //
        reloadTableMstChiso();
	}
    protected void deleteTableMstChiso() {
        if(intTypeDlgMstChiso==TYPE_DLG_VIEW){
            return;
        }
        //
        if(DbHelper.checkPhanQuyen(DbHelper.DELETE, "mst_chiso")==false){
            logger.info("DON'T HAVE DELETE RIGHT");
            return;
        }
		if(tableMstChiso.getSelectionCount()==0){
			return;
		}
        if(MessageDialog.openConfirm(shell, "Confirm?", "Delete ?")==false){
			return;
		}
		
		TableItem item = tableMstChiso.getSelection()[0];
		MstChiso obj = (MstChiso)item.getData();
        logger.info(obj.toString());
        //
		obj.delete();
        listDataMstChiso.remove(obj);
        //
		reloadTableMstChiso();
	}

	protected void newItemMstChiso() {
        if(intTypeDlgMstChiso==TYPE_DLG_VIEW){
            return;
        }
        //
        if(DbHelper.checkPhanQuyen(DbHelper.INSERT, "mst_chiso")==false){
            logger.info("DON'T HAVE INSERT RIGHT");
            return;
        }
		//
		MstChisoDlg dlg = new MstChisoDlg(shell, 0);
        MstChiso obj = new MstChiso();
		dlg.setMstChisoDlgData(obj);
		dlg.open();
        listDataMstChiso.add(obj);
        //
		reloadTableMstChiso();
		//
	}
}
