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
public class MstXahuyenListDlg extends Dialog {
	static Logger logger = LogManager.getLogger(MstXahuyenListDlg.class.getName());
	protected Object result;
	protected Shell shell;

	private class TableLabelProviderMstXahuyen extends LabelProvider implements ITableLabelProvider {
		public Image getColumnImage(Object element, int columnIndex) {
			return null;
		}
		public String getColumnText(Object element, int columnIndex) {
			if(element instanceof MstXahuyen){
				return ((MstXahuyen) element).getIndex(columnIndex);
			}
			return "";
		}
	}
	private static class ContentProviderMstXahuyen implements IStructuredContentProvider {
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
	private Table tableMstXahuyen;
	private TableViewer tableViewerMstXahuyen;
	private List<MstXahuyen> listDataMstXahuyen;
	private Text textSearchMstXahuyen;
	private String textSearchMstXahuyenString;
	public MstXahuyen objMstXahuyen = null;
	public int typeMstXahuyenDlg = TYPE_DLG_CHOOSEN; 
	public static final int TYPE_DLG_MANAGER = 0;
	public static final int TYPE_DLG_CHOOSEN = 1;

    public int intTypeDlgMstXahuyen;
	public static final int TYPE_DLG_VIEW = 1;
	public static final int TYPE_DLG_EDIT = 2;
	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public MstXahuyenListDlg(Shell parent, int style) {
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
		shell.setImage(SWTResourceManager.getImage(MstXahuyenDlg.class, "/png/list-2x.png"));
		shell.setSize(610, 340);
		shell.setText("MstXahuyen List View");
		shell.setLayout(new BorderLayout(0, 0));
		shell.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.keyCode==SWT.ESC){
					objMstXahuyen = null;
				}
			}
		});
        
        Composite compositeInShellMstXahuyen = new Composite(shell, SWT.NONE);
		compositeInShellMstXahuyen.setLayout(new BorderLayout(0, 0));
		compositeInShellMstXahuyen.setLayoutData(BorderLayout.CENTER);
        
		Composite compositeHeaderMstXahuyen = new Composite(compositeInShellMstXahuyen, SWT.NONE);
		compositeHeaderMstXahuyen.setLayoutData(BorderLayout.NORTH);
		compositeHeaderMstXahuyen.setLayout(new GridLayout(2, false));

		textSearchMstXahuyen = new Text(compositeHeaderMstXahuyen, SWT.BORDER);
		textSearchMstXahuyen.setFont(SWTResourceManager.getFont("Tahoma", 11, SWT.NORMAL));
		textSearchMstXahuyen.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.keyCode==13){
					reloadTableMstXahuyen();
				}
			}
		});
		
		Button btnNewButtonSearchMstXahuyen = new Button(compositeHeaderMstXahuyen, SWT.NONE);
		btnNewButtonSearchMstXahuyen.setImage(SWTResourceManager.getImage(MstXahuyenDlg.class, "/png/media-play-2x.png"));
		btnNewButtonSearchMstXahuyen.setFont(SWTResourceManager.getFont("Tahoma", 12, SWT.NORMAL));

		btnNewButtonSearchMstXahuyen.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				reloadTableMstXahuyen();
			}
		});
		GridData gd_btnNewButtonMstXahuyen = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnNewButtonMstXahuyen.widthHint = 87;
		btnNewButtonSearchMstXahuyen.setLayoutData(gd_btnNewButtonMstXahuyen);
		btnNewButtonSearchMstXahuyen.setText("Search");
        
		tableViewerMstXahuyen = new TableViewer(compositeInShellMstXahuyen, SWT.BORDER | SWT.FULL_SELECTION);
		tableMstXahuyen = tableViewerMstXahuyen.getTable();
		tableMstXahuyen.setFont(SWTResourceManager.getFont("Tahoma", 11, SWT.NORMAL));
		tableMstXahuyen.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.keyCode==SWT.F5){
					reloadTableMstXahuyen();
                }
                if(e.keyCode==SWT.F4){
					editTableMstXahuyen();
                }
				else if(e.keyCode==13){
					selectTableMstXahuyen();
				}
                else if(e.keyCode==SWT.DEL){
					deleteTableMstXahuyen();
				}
                else if(e.keyCode==SWT.F7){
					newItemMstXahuyen();
				}
			}
		});
        tableMstXahuyen.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				selectTableMstXahuyen();
			}
		});
        
		tableMstXahuyen.setLinesVisible(true);
		tableMstXahuyen.setHeaderVisible(true);
		tableMstXahuyen.setLayoutData(BorderLayout.CENTER);

		TableColumn tbTableColumnMstXahuyenDC_VALUE = new TableColumn(tableMstXahuyen, SWT.LEFT);
		tbTableColumnMstXahuyenDC_VALUE.setWidth(100);
		tbTableColumnMstXahuyenDC_VALUE.setText("DC_VALUE");

		TableColumn tbTableColumnMstXahuyenDC_RANK = new TableColumn(tableMstXahuyen, SWT.RIGHT);
		tbTableColumnMstXahuyenDC_RANK.setWidth(100);
		tbTableColumnMstXahuyenDC_RANK.setText("DC_RANK");

        Menu menuMstXahuyen = new Menu(tableMstXahuyen);
		tableMstXahuyen.setMenu(menuMstXahuyen);
		
		MenuItem mntmNewItemMstXahuyen = new MenuItem(menuMstXahuyen, SWT.NONE);
		mntmNewItemMstXahuyen.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				newItemMstXahuyen();
			}
		});
		mntmNewItemMstXahuyen.setImage(SWTResourceManager.getImage(MstXahuyenDlg.class, "/png/arrow-circle-top-2x.png"));
		mntmNewItemMstXahuyen.setText("New");
		
		MenuItem mntmEditItemMstXahuyen = new MenuItem(menuMstXahuyen, SWT.NONE);
		mntmEditItemMstXahuyen.setImage(SWTResourceManager.getImage(MstXahuyenDlg.class, "/png/wrench-2x.png"));
		mntmEditItemMstXahuyen.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				editTableMstXahuyen();
			}
		});
		mntmEditItemMstXahuyen.setText("Edit");
		
		MenuItem mntmDeleteMstXahuyen = new MenuItem(menuMstXahuyen, SWT.NONE);
		mntmDeleteMstXahuyen.setImage(SWTResourceManager.getImage(MstXahuyenDlg.class, "/png/circle-x-2x.png"));
		mntmDeleteMstXahuyen.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				deleteTableMstXahuyen();
			}
		});
		mntmDeleteMstXahuyen.setText("Delete");

		tableViewerMstXahuyen.setLabelProvider(new TableLabelProviderMstXahuyen());
		tableViewerMstXahuyen.setContentProvider(new ContentProviderMstXahuyen());
		tableViewerMstXahuyen.setInput(listDataMstXahuyen);
        //
        //
		loadDataMstXahuyen();
		//
        reloadTableMstXahuyen();
	}
    public void setDataMstXahuyen(String textSearchString){
		this.textSearchMstXahuyenString = textSearchString;
	}
	private void loadDataMstXahuyen() {
		if(textSearchMstXahuyenString!=null){
			textSearchMstXahuyen.setText(textSearchMstXahuyenString);
		}
	}
	protected void reloadTableMstXahuyen() {
        if(DbHelper.checkPhanQuyen(DbHelper.READ, "mst_xahuyen")==false){
			logger.info("DON'T HAVE READ RIGHT");
			return;
		}
    
        if(listDataMstXahuyen!=null){
            // 
            tableViewerMstXahuyen.setInput(listDataMstXahuyen);
            tableViewerMstXahuyen.refresh();
            //
            if(listDataMstXahuyen.size()==0){
                textSearchMstXahuyen.forceFocus();
            }
            else{
                tableMstXahuyen.forceFocus();
                tableMstXahuyen.setSelection(0);
            }
            return;
        }
		// Do search in the first time
		String searchString = textSearchMstXahuyen.getText().toLowerCase().trim();
		String sql = "select * from mst_xahuyen WHERE STS<> "+DbHelper.DELETE_STATUS+" ";
		if(searchString.length()>0){
            sql += " and ( 0 ";
        sql += " or LOWER(DC_VALUE) like '%"+searchString+"%'";
            sql += " )";
        }
		try  {
            logger.info(sql);
			Connection con = DbHelper.getSql2o();
			listDataMstXahuyen = con.createQuery(sql).executeAndFetch(MstXahuyen.class);
	    }   
	    catch(Exception e){
	    	logger.error(e);
	    	MessageDialog.openError(shell, "Error", e.getMessage());
	    }
		// 
		tableViewerMstXahuyen.setInput(listDataMstXahuyen);
		tableViewerMstXahuyen.refresh();
        //
        if(listDataMstXahuyen.size()==0){
            textSearchMstXahuyen.forceFocus();
        }
        else{
            tableMstXahuyen.forceFocus();
            tableMstXahuyen.setSelection(0);
        }
	}
    
    protected void selectTableMstXahuyen() {
		if(tableMstXahuyen.getSelectionCount()==0){
			return;
		}
		TableItem item = tableMstXahuyen.getSelection()[0];
		MstXahuyen obj = (MstXahuyen)item.getData();
        logger.info(obj.toString());
        //
        //
    	if(typeMstXahuyenDlg==TYPE_DLG_CHOOSEN){
    		// Choosen and close dlg
    		objMstXahuyen = obj;
    		shell.close();
    	}
    	else{
            if(DbHelper.checkPhanQuyen(DbHelper.UPDATE, "mst_xahuyen")==false){
                logger.info("DON'T HAVE UPDATE RIGHT");
                return;
            }
            if(intTypeDlgMstXahuyen==TYPE_DLG_VIEW){
                return;
            }
			MstXahuyenDlg dlg = new MstXahuyenDlg(shell, 0);
            dlg.setMstXahuyenDlgData(obj);
            dlg.open();
            //
            reloadTableMstXahuyen();
    	}
	}
    protected void editTableMstXahuyen() {
        if(intTypeDlgMstXahuyen==TYPE_DLG_VIEW){
            return;
        }
        if(DbHelper.checkPhanQuyen(DbHelper.UPDATE, "mst_xahuyen")==false){
            logger.info("DON'T HAVE UPDATE RIGHT");
            return;
        }
    	if(tableMstXahuyen.getSelectionCount()==0){
			return;
		}
		TableItem item = tableMstXahuyen.getSelection()[0];
		MstXahuyen obj = (MstXahuyen)item.getData();
        logger.info(obj.toString());
        //
        //
		MstXahuyenDlg dlg = new MstXahuyenDlg(shell, 0);
        dlg.setMstXahuyenDlgData(obj);
        dlg.open();
        //
        reloadTableMstXahuyen();
	}
    protected void deleteTableMstXahuyen() {
        if(intTypeDlgMstXahuyen==TYPE_DLG_VIEW){
            return;
        }
        //
        if(DbHelper.checkPhanQuyen(DbHelper.DELETE, "mst_xahuyen")==false){
            logger.info("DON'T HAVE DELETE RIGHT");
            return;
        }
		if(tableMstXahuyen.getSelectionCount()==0){
			return;
		}
        if(MessageDialog.openConfirm(shell, "Confirm?", "Delete ?")==false){
			return;
		}
		
		TableItem item = tableMstXahuyen.getSelection()[0];
		MstXahuyen obj = (MstXahuyen)item.getData();
        logger.info(obj.toString());
        //
		obj.delete();
        listDataMstXahuyen.remove(obj);
        //
		reloadTableMstXahuyen();
	}

	protected void newItemMstXahuyen() {
        if(intTypeDlgMstXahuyen==TYPE_DLG_VIEW){
            return;
        }
        //
        if(DbHelper.checkPhanQuyen(DbHelper.INSERT, "mst_xahuyen")==false){
            logger.info("DON'T HAVE INSERT RIGHT");
            return;
        }
		//
		MstXahuyenDlg dlg = new MstXahuyenDlg(shell, 0);
        MstXahuyen obj = new MstXahuyen();
		dlg.setMstXahuyenDlgData(obj);
		dlg.open();
        listDataMstXahuyen.add(obj);
        //
		reloadTableMstXahuyen();
		//
	}
}
