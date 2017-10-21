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
public class DonviTinhListDlg extends Dialog {
	static Logger logger = LogManager.getLogger(DonviTinhListDlg.class.getName());
	protected Object result;
	protected Shell shell;

	private class TableLabelProviderDonviTinh extends LabelProvider implements ITableLabelProvider {
		public Image getColumnImage(Object element, int columnIndex) {
			return null;
		}
		public String getColumnText(Object element, int columnIndex) {
			if(element instanceof DonviTinh){
				return ((DonviTinh) element).getIndex(columnIndex);
			}
			return "";
		}
	}
	private static class ContentProviderDonviTinh implements IStructuredContentProvider {
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
	private Table tableDonviTinh;
	private TableViewer tableViewerDonviTinh;
	private List<DonviTinh> listDataDonviTinh;
	private Text textSearchDonviTinh;
	private String textSearchDonviTinhString;
	public DonviTinh objDonviTinh = null;
	public int typeDonviTinhDlg = TYPE_DLG_CHOOSEN; 
	public static final int TYPE_DLG_MANAGER = 0;
	public static final int TYPE_DLG_CHOOSEN = 1;

    public int intTypeDlgDonviTinh;
	public static final int TYPE_DLG_VIEW = 1;
	public static final int TYPE_DLG_EDIT = 2;
	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public DonviTinhListDlg(Shell parent, int style) {
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
		shell.setImage(SWTResourceManager.getImage(DonviTinhDlg.class, "/png/list-2x.png"));
		shell.setSize(610, 340);
		shell.setText("DonviTinh List View");
		shell.setLayout(new BorderLayout(0, 0));
		shell.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.keyCode==SWT.ESC){
					objDonviTinh = null;
				}
			}
		});
        
        Composite compositeInShellDonviTinh = new Composite(shell, SWT.NONE);
		compositeInShellDonviTinh.setLayout(new BorderLayout(0, 0));
		compositeInShellDonviTinh.setLayoutData(BorderLayout.CENTER);
        
		Composite compositeHeaderDonviTinh = new Composite(compositeInShellDonviTinh, SWT.NONE);
		compositeHeaderDonviTinh.setLayoutData(BorderLayout.NORTH);
		compositeHeaderDonviTinh.setLayout(new GridLayout(2, false));

		textSearchDonviTinh = new Text(compositeHeaderDonviTinh, SWT.BORDER);
		textSearchDonviTinh.setFont(SWTResourceManager.getFont("Tahoma", 11, SWT.NORMAL));
		textSearchDonviTinh.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.keyCode==13){
					reloadTableDonviTinh();
				}
			}
		});
		
		Button btnNewButtonSearchDonviTinh = new Button(compositeHeaderDonviTinh, SWT.NONE);
		btnNewButtonSearchDonviTinh.setImage(SWTResourceManager.getImage(DonviTinhDlg.class, "/png/media-play-2x.png"));
		btnNewButtonSearchDonviTinh.setFont(SWTResourceManager.getFont("Tahoma", 12, SWT.NORMAL));

		btnNewButtonSearchDonviTinh.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				reloadTableDonviTinh();
			}
		});
		GridData gd_btnNewButtonDonviTinh = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnNewButtonDonviTinh.widthHint = 87;
		btnNewButtonSearchDonviTinh.setLayoutData(gd_btnNewButtonDonviTinh);
		btnNewButtonSearchDonviTinh.setText("Search");
        
		tableViewerDonviTinh = new TableViewer(compositeInShellDonviTinh, SWT.BORDER | SWT.FULL_SELECTION);
		tableDonviTinh = tableViewerDonviTinh.getTable();
		tableDonviTinh.setFont(SWTResourceManager.getFont("Tahoma", 11, SWT.NORMAL));
		tableDonviTinh.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.keyCode==SWT.F5){
					reloadTableDonviTinh();
                }
                if(e.keyCode==SWT.F4){
					editTableDonviTinh();
                }
				else if(e.keyCode==13){
					selectTableDonviTinh();
				}
                else if(e.keyCode==SWT.DEL){
					deleteTableDonviTinh();
				}
                else if(e.keyCode==SWT.F7){
					newItemDonviTinh();
				}
			}
		});
        tableDonviTinh.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				selectTableDonviTinh();
			}
		});
        
		tableDonviTinh.setLinesVisible(true);
		tableDonviTinh.setHeaderVisible(true);
		tableDonviTinh.setLayoutData(BorderLayout.CENTER);

		TableColumn tbTableColumnDonviTinhDVT_NAME = new TableColumn(tableDonviTinh, SWT.LEFT);
		tbTableColumnDonviTinhDVT_NAME.setWidth(100);
		tbTableColumnDonviTinhDVT_NAME.setText("DVT_NAME");

        Menu menuDonviTinh = new Menu(tableDonviTinh);
		tableDonviTinh.setMenu(menuDonviTinh);
		
		MenuItem mntmNewItemDonviTinh = new MenuItem(menuDonviTinh, SWT.NONE);
		mntmNewItemDonviTinh.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				newItemDonviTinh();
			}
		});
		mntmNewItemDonviTinh.setImage(SWTResourceManager.getImage(DonviTinhDlg.class, "/png/arrow-circle-top-2x.png"));
		mntmNewItemDonviTinh.setText("New");
		
		MenuItem mntmEditItemDonviTinh = new MenuItem(menuDonviTinh, SWT.NONE);
		mntmEditItemDonviTinh.setImage(SWTResourceManager.getImage(DonviTinhDlg.class, "/png/wrench-2x.png"));
		mntmEditItemDonviTinh.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				editTableDonviTinh();
			}
		});
		mntmEditItemDonviTinh.setText("Edit");
		
		MenuItem mntmDeleteDonviTinh = new MenuItem(menuDonviTinh, SWT.NONE);
		mntmDeleteDonviTinh.setImage(SWTResourceManager.getImage(DonviTinhDlg.class, "/png/circle-x-2x.png"));
		mntmDeleteDonviTinh.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				deleteTableDonviTinh();
			}
		});
		mntmDeleteDonviTinh.setText("Delete");

		tableViewerDonviTinh.setLabelProvider(new TableLabelProviderDonviTinh());
		tableViewerDonviTinh.setContentProvider(new ContentProviderDonviTinh());
		tableViewerDonviTinh.setInput(listDataDonviTinh);
        //
        //
		loadDataDonviTinh();
		//
        reloadTableDonviTinh();
	}
    public void setDataDonviTinh(String textSearchString){
		this.textSearchDonviTinhString = textSearchString;
	}
	private void loadDataDonviTinh() {
		if(textSearchDonviTinhString!=null){
			textSearchDonviTinh.setText(textSearchDonviTinhString);
		}
	}
	protected void reloadTableDonviTinh() {
        if(DbHelper.checkPhanQuyen(DbHelper.READ, "donvi_tinh")==false){
			logger.info("DON'T HAVE READ RIGHT");
			return;
		}
    
        if(listDataDonviTinh!=null){
            // 
            tableViewerDonviTinh.setInput(listDataDonviTinh);
            tableViewerDonviTinh.refresh();
            //
            if(listDataDonviTinh.size()==0){
                textSearchDonviTinh.forceFocus();
            }
            else{
                tableDonviTinh.forceFocus();
                tableDonviTinh.setSelection(0);
            }
            return;
        }
		// Do search in the first time
		String searchString = textSearchDonviTinh.getText().toLowerCase().trim();
		String sql = "select * from donvi_tinh WHERE STS<> "+DbHelper.DELETE_STATUS+" ";
		if(searchString.length()>0){
            sql += " and ( 0 ";
        sql += " or LOWER(DVT_NAME) like '%"+searchString+"%'";
            sql += " )";
        }
		try  {
            logger.info(sql);
			Connection con = DbHelper.getSql2o();
			listDataDonviTinh = con.createQuery(sql).executeAndFetch(DonviTinh.class);
	    }   
	    catch(Exception e){
	    	logger.error(e);
	    	MessageDialog.openError(shell, "Error", e.getMessage());
	    }
		// 
		tableViewerDonviTinh.setInput(listDataDonviTinh);
		tableViewerDonviTinh.refresh();
        //
        if(listDataDonviTinh.size()==0){
            textSearchDonviTinh.forceFocus();
        }
        else{
            tableDonviTinh.forceFocus();
            tableDonviTinh.setSelection(0);
        }
	}
    
    protected void selectTableDonviTinh() {
		if(tableDonviTinh.getSelectionCount()==0){
			return;
		}
		TableItem item = tableDonviTinh.getSelection()[0];
		DonviTinh obj = (DonviTinh)item.getData();
        logger.info(obj.toString());
        //
        //
    	if(typeDonviTinhDlg==TYPE_DLG_CHOOSEN){
    		// Choosen and close dlg
    		objDonviTinh = obj;
    		shell.close();
    	}
    	else{
            if(DbHelper.checkPhanQuyen(DbHelper.UPDATE, "donvi_tinh")==false){
                logger.info("DON'T HAVE UPDATE RIGHT");
                return;
            }
            if(intTypeDlgDonviTinh==TYPE_DLG_VIEW){
                return;
            }
			DonviTinhDlg dlg = new DonviTinhDlg(shell, 0);
            dlg.setDonviTinhDlgData(obj);
            dlg.open();
            //
            reloadTableDonviTinh();
    	}
	}
    protected void editTableDonviTinh() {
        if(intTypeDlgDonviTinh==TYPE_DLG_VIEW){
            return;
        }
        if(DbHelper.checkPhanQuyen(DbHelper.UPDATE, "donvi_tinh")==false){
            logger.info("DON'T HAVE UPDATE RIGHT");
            return;
        }
    	if(tableDonviTinh.getSelectionCount()==0){
			return;
		}
		TableItem item = tableDonviTinh.getSelection()[0];
		DonviTinh obj = (DonviTinh)item.getData();
        logger.info(obj.toString());
        //
        //
		DonviTinhDlg dlg = new DonviTinhDlg(shell, 0);
        dlg.setDonviTinhDlgData(obj);
        dlg.open();
        //
        reloadTableDonviTinh();
	}
    protected void deleteTableDonviTinh() {
        if(intTypeDlgDonviTinh==TYPE_DLG_VIEW){
            return;
        }
        //
        if(DbHelper.checkPhanQuyen(DbHelper.DELETE, "donvi_tinh")==false){
            logger.info("DON'T HAVE DELETE RIGHT");
            return;
        }
		if(tableDonviTinh.getSelectionCount()==0){
			return;
		}
        if(MessageDialog.openConfirm(shell, "Confirm?", "Delete ?")==false){
			return;
		}
		
		TableItem item = tableDonviTinh.getSelection()[0];
		DonviTinh obj = (DonviTinh)item.getData();
        logger.info(obj.toString());
        //
		obj.delete();
        listDataDonviTinh.remove(obj);
        //
		reloadTableDonviTinh();
	}

	protected void newItemDonviTinh() {
        if(intTypeDlgDonviTinh==TYPE_DLG_VIEW){
            return;
        }
        //
        if(DbHelper.checkPhanQuyen(DbHelper.INSERT, "donvi_tinh")==false){
            logger.info("DON'T HAVE INSERT RIGHT");
            return;
        }
		//
		DonviTinhDlg dlg = new DonviTinhDlg(shell, 0);
        DonviTinh obj = new DonviTinh();
		dlg.setDonviTinhDlgData(obj);
		dlg.open();
        listDataDonviTinh.add(obj);
        //
		reloadTableDonviTinh();
		//
	}
}
