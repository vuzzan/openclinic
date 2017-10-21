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
public class MabenhListDlg extends Dialog {
	static Logger logger = LogManager.getLogger(MabenhListDlg.class.getName());
	protected Object result;
	protected Shell shell;

	private class TableLabelProviderMabenh extends LabelProvider implements ITableLabelProvider {
		public Image getColumnImage(Object element, int columnIndex) {
			return null;
		}
		public String getColumnText(Object element, int columnIndex) {
			if(element instanceof Mabenh){
				return ((Mabenh) element).getIndex(columnIndex);
			}
			return "";
		}
	}
	private static class ContentProviderMabenh implements IStructuredContentProvider {
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
	private Table tableMabenh;
	private TableViewer tableViewerMabenh;
	private List<Mabenh> listDataMabenh;
	private Text textSearchMabenh;
	private String textSearchMabenhString;
	public Mabenh objMabenh = null;
	public int typeMabenhDlg = TYPE_DLG_CHOOSEN; 
	public static final int TYPE_DLG_MANAGER = 0;
	public static final int TYPE_DLG_CHOOSEN = 1;

    public int intTypeDlgMabenh;
	public static final int TYPE_DLG_VIEW = 1;
	public static final int TYPE_DLG_EDIT = 2;
	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public MabenhListDlg(Shell parent, int style) {
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
		shell.setImage(SWTResourceManager.getImage(MabenhDlg.class, "/png/list-2x.png"));
		shell.setSize(610, 340);
		shell.setText("Mabenh List View");
		shell.setLayout(new BorderLayout(0, 0));
		shell.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.keyCode==SWT.ESC){
					objMabenh = null;
				}
			}
		});
        
        Composite compositeInShellMabenh = new Composite(shell, SWT.NONE);
		compositeInShellMabenh.setLayout(new BorderLayout(0, 0));
		compositeInShellMabenh.setLayoutData(BorderLayout.CENTER);
        
		Composite compositeHeaderMabenh = new Composite(compositeInShellMabenh, SWT.NONE);
		compositeHeaderMabenh.setLayoutData(BorderLayout.NORTH);
		compositeHeaderMabenh.setLayout(new GridLayout(2, false));

		textSearchMabenh = new Text(compositeHeaderMabenh, SWT.BORDER);
		textSearchMabenh.setFont(SWTResourceManager.getFont("Tahoma", 11, SWT.NORMAL));
		textSearchMabenh.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.keyCode==13){
					reloadTableMabenh();
				}
			}
		});
		
		Button btnNewButtonSearchMabenh = new Button(compositeHeaderMabenh, SWT.NONE);
		btnNewButtonSearchMabenh.setImage(SWTResourceManager.getImage(MabenhDlg.class, "/png/media-play-2x.png"));
		btnNewButtonSearchMabenh.setFont(SWTResourceManager.getFont("Tahoma", 12, SWT.NORMAL));

		btnNewButtonSearchMabenh.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				reloadTableMabenh();
			}
		});
		GridData gd_btnNewButtonMabenh = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnNewButtonMabenh.widthHint = 87;
		btnNewButtonSearchMabenh.setLayoutData(gd_btnNewButtonMabenh);
		btnNewButtonSearchMabenh.setText("Search");
        
		tableViewerMabenh = new TableViewer(compositeInShellMabenh, SWT.BORDER | SWT.FULL_SELECTION);
		tableMabenh = tableViewerMabenh.getTable();
		tableMabenh.setFont(SWTResourceManager.getFont("Tahoma", 11, SWT.NORMAL));
		tableMabenh.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.keyCode==SWT.F5){
					reloadTableMabenh();
                }
                if(e.keyCode==SWT.F4){
					editTableMabenh();
                }
				else if(e.keyCode==13){
					selectTableMabenh();
				}
                else if(e.keyCode==SWT.DEL){
					deleteTableMabenh();
				}
                else if(e.keyCode==SWT.F7){
					newItemMabenh();
				}
			}
		});
        tableMabenh.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				selectTableMabenh();
			}
		});
        
		tableMabenh.setLinesVisible(true);
		tableMabenh.setHeaderVisible(true);
		tableMabenh.setLayoutData(BorderLayout.CENTER);

		TableColumn tbTableColumnMabenhMABENH_NAME = new TableColumn(tableMabenh, SWT.LEFT);
		tbTableColumnMabenhMABENH_NAME.setWidth(100);
		tbTableColumnMabenhMABENH_NAME.setText("MABENH_NAME");

		TableColumn tbTableColumnMabenhMABENH_RANK = new TableColumn(tableMabenh, SWT.RIGHT);
		tbTableColumnMabenhMABENH_RANK.setWidth(100);
		tbTableColumnMabenhMABENH_RANK.setText("MABENH_RANK");

        Menu menuMabenh = new Menu(tableMabenh);
		tableMabenh.setMenu(menuMabenh);
		
		MenuItem mntmNewItemMabenh = new MenuItem(menuMabenh, SWT.NONE);
		mntmNewItemMabenh.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				newItemMabenh();
			}
		});
		mntmNewItemMabenh.setImage(SWTResourceManager.getImage(MabenhDlg.class, "/png/arrow-circle-top-2x.png"));
		mntmNewItemMabenh.setText("New");
		
		MenuItem mntmEditItemMabenh = new MenuItem(menuMabenh, SWT.NONE);
		mntmEditItemMabenh.setImage(SWTResourceManager.getImage(MabenhDlg.class, "/png/wrench-2x.png"));
		mntmEditItemMabenh.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				editTableMabenh();
			}
		});
		mntmEditItemMabenh.setText("Edit");
		
		MenuItem mntmDeleteMabenh = new MenuItem(menuMabenh, SWT.NONE);
		mntmDeleteMabenh.setImage(SWTResourceManager.getImage(MabenhDlg.class, "/png/circle-x-2x.png"));
		mntmDeleteMabenh.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				deleteTableMabenh();
			}
		});
		mntmDeleteMabenh.setText("Delete");

		tableViewerMabenh.setLabelProvider(new TableLabelProviderMabenh());
		tableViewerMabenh.setContentProvider(new ContentProviderMabenh());
		tableViewerMabenh.setInput(listDataMabenh);
        //
        //
		loadDataMabenh();
		//
        reloadTableMabenh();
	}
    public void setDataMabenh(String textSearchString){
		this.textSearchMabenhString = textSearchString;
	}
	private void loadDataMabenh() {
		if(textSearchMabenhString!=null){
			textSearchMabenh.setText(textSearchMabenhString);
		}
	}
	protected void reloadTableMabenh() {
        if(DbHelper.checkPhanQuyen(DbHelper.READ, "mabenh")==false){
			logger.info("DON'T HAVE READ RIGHT");
			return;
		}
    
        if(listDataMabenh!=null){
            // 
            tableViewerMabenh.setInput(listDataMabenh);
            tableViewerMabenh.refresh();
            //
            if(listDataMabenh.size()==0){
                textSearchMabenh.forceFocus();
            }
            else{
                tableMabenh.forceFocus();
                tableMabenh.setSelection(0);
            }
            return;
        }
		// Do search in the first time
		String searchString = textSearchMabenh.getText().toLowerCase().trim();
		String sql = "select * from mabenh WHERE STS<> "+DbHelper.DELETE_STATUS+" ";
		if(searchString.length()>0){
            sql += " and ( 0 ";
        sql += " or LOWER(MABENH_NAME) like '%"+searchString+"%'";
            sql += " )";
        }
		try  {
            logger.info(sql);
			Connection con = DbHelper.getSql2o();
			listDataMabenh = con.createQuery(sql).executeAndFetch(Mabenh.class);
	    }   
	    catch(Exception e){
	    	logger.error(e);
	    	MessageDialog.openError(shell, "Error", e.getMessage());
	    }
		// 
		tableViewerMabenh.setInput(listDataMabenh);
		tableViewerMabenh.refresh();
        //
        if(listDataMabenh.size()==0){
            textSearchMabenh.forceFocus();
        }
        else{
            tableMabenh.forceFocus();
            tableMabenh.setSelection(0);
        }
	}
    
    protected void selectTableMabenh() {
		if(tableMabenh.getSelectionCount()==0){
			return;
		}
		TableItem item = tableMabenh.getSelection()[0];
		Mabenh obj = (Mabenh)item.getData();
        logger.info(obj.toString());
        //
        //
    	if(typeMabenhDlg==TYPE_DLG_CHOOSEN){
    		// Choosen and close dlg
    		objMabenh = obj;
    		shell.close();
    	}
    	else{
            if(DbHelper.checkPhanQuyen(DbHelper.UPDATE, "mabenh")==false){
                logger.info("DON'T HAVE UPDATE RIGHT");
                return;
            }
            if(intTypeDlgMabenh==TYPE_DLG_VIEW){
                return;
            }
			MabenhDlg dlg = new MabenhDlg(shell, 0);
            dlg.setMabenhDlgData(obj);
            dlg.open();
            //
            reloadTableMabenh();
    	}
	}
    protected void editTableMabenh() {
        if(intTypeDlgMabenh==TYPE_DLG_VIEW){
            return;
        }
        if(DbHelper.checkPhanQuyen(DbHelper.UPDATE, "mabenh")==false){
            logger.info("DON'T HAVE UPDATE RIGHT");
            return;
        }
    	if(tableMabenh.getSelectionCount()==0){
			return;
		}
		TableItem item = tableMabenh.getSelection()[0];
		Mabenh obj = (Mabenh)item.getData();
        logger.info(obj.toString());
        //
        //
		MabenhDlg dlg = new MabenhDlg(shell, 0);
        dlg.setMabenhDlgData(obj);
        dlg.open();
        //
        reloadTableMabenh();
	}
    protected void deleteTableMabenh() {
        if(intTypeDlgMabenh==TYPE_DLG_VIEW){
            return;
        }
        //
        if(DbHelper.checkPhanQuyen(DbHelper.DELETE, "mabenh")==false){
            logger.info("DON'T HAVE DELETE RIGHT");
            return;
        }
		if(tableMabenh.getSelectionCount()==0){
			return;
		}
        if(MessageDialog.openConfirm(shell, "Confirm?", "Delete ?")==false){
			return;
		}
		
		TableItem item = tableMabenh.getSelection()[0];
		Mabenh obj = (Mabenh)item.getData();
        logger.info(obj.toString());
        //
		obj.delete();
        listDataMabenh.remove(obj);
        //
		reloadTableMabenh();
	}

	protected void newItemMabenh() {
        if(intTypeDlgMabenh==TYPE_DLG_VIEW){
            return;
        }
        //
        if(DbHelper.checkPhanQuyen(DbHelper.INSERT, "mabenh")==false){
            logger.info("DON'T HAVE INSERT RIGHT");
            return;
        }
		//
		MabenhDlg dlg = new MabenhDlg(shell, 0);
        Mabenh obj = new Mabenh();
		dlg.setMabenhDlgData(obj);
		dlg.open();
        listDataMabenh.add(obj);
        //
		reloadTableMabenh();
		//
	}
}
