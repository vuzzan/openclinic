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
public class VendorListDlg extends Dialog {
	static Logger logger = LogManager.getLogger(VendorListDlg.class.getName());
	protected Object result;
	protected Shell shell;

	private class TableLabelProviderVendor extends LabelProvider implements ITableLabelProvider {
		public Image getColumnImage(Object element, int columnIndex) {
			return null;
		}
		public String getColumnText(Object element, int columnIndex) {
			if(element instanceof Vendor){
				return ((Vendor) element).getIndex(columnIndex);
			}
			return "";
		}
	}
	private static class ContentProviderVendor implements IStructuredContentProvider {
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
	private Table tableVendor;
	private TableViewer tableViewerVendor;
	private List<Vendor> listDataVendor;
	private Text textSearchVendor;
	private String textSearchVendorString;
	public Vendor objVendor = null;
	public int typeVendorDlg = TYPE_DLG_CHOOSEN; 
	public static final int TYPE_DLG_MANAGER = 0;
	public static final int TYPE_DLG_CHOOSEN = 1;

    public int intTypeDlgVendor;
	public static final int TYPE_DLG_VIEW = 1;
	public static final int TYPE_DLG_EDIT = 2;
	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public VendorListDlg(Shell parent, int style) {
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
		shell.setImage(SWTResourceManager.getImage(VendorDlg.class, "/png/list-2x.png"));
		shell.setSize(610, 340);
		shell.setText("Vendor List View");
		shell.setLayout(new BorderLayout(0, 0));
		shell.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.keyCode==SWT.ESC){
					objVendor = null;
				}
			}
		});
        
        Composite compositeInShellVendor = new Composite(shell, SWT.NONE);
		compositeInShellVendor.setLayout(new BorderLayout(0, 0));
		compositeInShellVendor.setLayoutData(BorderLayout.CENTER);
        
		Composite compositeHeaderVendor = new Composite(compositeInShellVendor, SWT.NONE);
		compositeHeaderVendor.setLayoutData(BorderLayout.NORTH);
		compositeHeaderVendor.setLayout(new GridLayout(2, false));

		textSearchVendor = new Text(compositeHeaderVendor, SWT.BORDER);
		textSearchVendor.setFont(SWTResourceManager.getFont("Tahoma", 11, SWT.NORMAL));
		textSearchVendor.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.keyCode==13){
					reloadTableVendor();
				}
			}
		});
		
		Button btnNewButtonSearchVendor = new Button(compositeHeaderVendor, SWT.NONE);
		btnNewButtonSearchVendor.setImage(SWTResourceManager.getImage(VendorDlg.class, "/png/media-play-2x.png"));
		btnNewButtonSearchVendor.setFont(SWTResourceManager.getFont("Tahoma", 12, SWT.NORMAL));

		btnNewButtonSearchVendor.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				reloadTableVendor();
			}
		});
		GridData gd_btnNewButtonVendor = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnNewButtonVendor.widthHint = 87;
		btnNewButtonSearchVendor.setLayoutData(gd_btnNewButtonVendor);
		btnNewButtonSearchVendor.setText("Search");
        
		tableViewerVendor = new TableViewer(compositeInShellVendor, SWT.BORDER | SWT.FULL_SELECTION);
		tableVendor = tableViewerVendor.getTable();
		tableVendor.setFont(SWTResourceManager.getFont("Tahoma", 11, SWT.NORMAL));
		tableVendor.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.keyCode==SWT.F5){
					reloadTableVendor();
                }
                if(e.keyCode==SWT.F4){
					editTableVendor();
                }
				else if(e.keyCode==13){
					selectTableVendor();
				}
                else if(e.keyCode==SWT.DEL){
					deleteTableVendor();
				}
                else if(e.keyCode==SWT.F7){
					newItemVendor();
				}
			}
		});
        tableVendor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				selectTableVendor();
			}
		});
        
		tableVendor.setLinesVisible(true);
		tableVendor.setHeaderVisible(true);
		tableVendor.setLayoutData(BorderLayout.CENTER);

		TableColumn tbTableColumnVendorV_NAME = new TableColumn(tableVendor, SWT.LEFT);
		tbTableColumnVendorV_NAME.setWidth(100);
		tbTableColumnVendorV_NAME.setText("V_NAME");

		TableColumn tbTableColumnVendorV_ADDR = new TableColumn(tableVendor, SWT.LEFT);
		tbTableColumnVendorV_ADDR.setWidth(100);
		tbTableColumnVendorV_ADDR.setText("V_ADDR");

		TableColumn tbTableColumnVendorV_MST = new TableColumn(tableVendor, SWT.LEFT);
		tbTableColumnVendorV_MST.setWidth(100);
		tbTableColumnVendorV_MST.setText("V_MST");

		TableColumn tbTableColumnVendorV_CONTACT = new TableColumn(tableVendor, SWT.LEFT);
		tbTableColumnVendorV_CONTACT.setWidth(100);
		tbTableColumnVendorV_CONTACT.setText("V_CONTACT");

		TableColumn tbTableColumnVendorV_PHONE = new TableColumn(tableVendor, SWT.LEFT);
		tbTableColumnVendorV_PHONE.setWidth(100);
		tbTableColumnVendorV_PHONE.setText("V_PHONE");

		TableColumn tbTableColumnVendorSTS = new TableColumn(tableVendor, SWT.RIGHT);
		tbTableColumnVendorSTS.setWidth(100);
		tbTableColumnVendorSTS.setText("STS");

        Menu menuVendor = new Menu(tableVendor);
		tableVendor.setMenu(menuVendor);
		
		MenuItem mntmNewItemVendor = new MenuItem(menuVendor, SWT.NONE);
		mntmNewItemVendor.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				newItemVendor();
			}
		});
		mntmNewItemVendor.setImage(SWTResourceManager.getImage(VendorDlg.class, "/png/arrow-circle-top-2x.png"));
		mntmNewItemVendor.setText("New");
		
		MenuItem mntmEditItemVendor = new MenuItem(menuVendor, SWT.NONE);
		mntmEditItemVendor.setImage(SWTResourceManager.getImage(VendorDlg.class, "/png/wrench-2x.png"));
		mntmEditItemVendor.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				editTableVendor();
			}
		});
		mntmEditItemVendor.setText("Edit");
		
		MenuItem mntmDeleteVendor = new MenuItem(menuVendor, SWT.NONE);
		mntmDeleteVendor.setImage(SWTResourceManager.getImage(VendorDlg.class, "/png/circle-x-2x.png"));
		mntmDeleteVendor.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				deleteTableVendor();
			}
		});
		mntmDeleteVendor.setText("Delete");

		tableViewerVendor.setLabelProvider(new TableLabelProviderVendor());
		tableViewerVendor.setContentProvider(new ContentProviderVendor());
		tableViewerVendor.setInput(listDataVendor);
        //
        //
		loadDataVendor();
		//
        reloadTableVendor();
	}
    public void setDataVendor(String textSearchString){
		this.textSearchVendorString = textSearchString;
	}
	private void loadDataVendor() {
		if(textSearchVendorString!=null){
			textSearchVendor.setText(textSearchVendorString);
		}
	}
	protected void reloadTableVendor() {
        if(DbHelper.checkPhanQuyen(DbHelper.READ, "vendor")==false){
			logger.info("DON'T HAVE READ RIGHT");
			return;
		}
    
        if(listDataVendor!=null){
            // 
            tableViewerVendor.setInput(listDataVendor);
            tableViewerVendor.refresh();
            //
            if(listDataVendor.size()==0){
                textSearchVendor.forceFocus();
            }
            else{
                tableVendor.forceFocus();
                tableVendor.setSelection(0);
            }
            return;
        }
		// Do search in the first time
		String searchString = textSearchVendor.getText().toLowerCase().trim();
		String sql = "select * from vendor WHERE STS<> "+DbHelper.DELETE_STATUS+" ";
		if(searchString.length()>0){
            sql += " and ( 0 ";
        sql += " or LOWER(V_NAME) like '%"+searchString+"%'";
        sql += " or LOWER(V_ADDR) like '%"+searchString+"%'";
        sql += " or LOWER(V_MST) like '%"+searchString+"%'";
        sql += " or LOWER(V_CONTACT) like '%"+searchString+"%'";
        sql += " or LOWER(V_PHONE) like '%"+searchString+"%'";
            sql += " )";
        }
		try  {
            logger.info(sql);
			Connection con = DbHelper.getSql2o();
			listDataVendor = con.createQuery(sql).executeAndFetch(Vendor.class);
	    }   
	    catch(Exception e){
	    	logger.error(e);
	    	MessageDialog.openError(shell, "Error", e.getMessage());
	    }
		// 
		tableViewerVendor.setInput(listDataVendor);
		tableViewerVendor.refresh();
        //
        if(listDataVendor.size()==0){
            textSearchVendor.forceFocus();
        }
        else{
            tableVendor.forceFocus();
            tableVendor.setSelection(0);
        }
	}
    
    protected void selectTableVendor() {
		if(tableVendor.getSelectionCount()==0){
			return;
		}
		TableItem item = tableVendor.getSelection()[0];
		Vendor obj = (Vendor)item.getData();
        logger.info(obj.toString());
        //
        //
    	if(typeVendorDlg==TYPE_DLG_CHOOSEN){
    		// Choosen and close dlg
    		objVendor = obj;
    		shell.close();
    	}
    	else{
            if(DbHelper.checkPhanQuyen(DbHelper.UPDATE, "vendor")==false){
                logger.info("DON'T HAVE UPDATE RIGHT");
                return;
            }
            if(intTypeDlgVendor==TYPE_DLG_VIEW){
                return;
            }
			VendorDlg dlg = new VendorDlg(shell, 0);
            dlg.setVendorDlgData(obj);
            dlg.open();
            //
            reloadTableVendor();
    	}
	}
    protected void editTableVendor() {
        if(intTypeDlgVendor==TYPE_DLG_VIEW){
            return;
        }
        if(DbHelper.checkPhanQuyen(DbHelper.UPDATE, "vendor")==false){
            logger.info("DON'T HAVE UPDATE RIGHT");
            return;
        }
    	if(tableVendor.getSelectionCount()==0){
			return;
		}
		TableItem item = tableVendor.getSelection()[0];
		Vendor obj = (Vendor)item.getData();
        logger.info(obj.toString());
        //
        //
		VendorDlg dlg = new VendorDlg(shell, 0);
        dlg.setVendorDlgData(obj);
        dlg.open();
        //
        reloadTableVendor();
	}
    protected void deleteTableVendor() {
        if(intTypeDlgVendor==TYPE_DLG_VIEW){
            return;
        }
        //
        if(DbHelper.checkPhanQuyen(DbHelper.DELETE, "vendor")==false){
            logger.info("DON'T HAVE DELETE RIGHT");
            return;
        }
		if(tableVendor.getSelectionCount()==0){
			return;
		}
        if(MessageDialog.openConfirm(shell, "Confirm?", "Delete ?")==false){
			return;
		}
		
		TableItem item = tableVendor.getSelection()[0];
		Vendor obj = (Vendor)item.getData();
        logger.info(obj.toString());
        //
		obj.delete();
        listDataVendor.remove(obj);
        //
		reloadTableVendor();
	}

	protected void newItemVendor() {
        if(intTypeDlgVendor==TYPE_DLG_VIEW){
            return;
        }
        //
        if(DbHelper.checkPhanQuyen(DbHelper.INSERT, "vendor")==false){
            logger.info("DON'T HAVE INSERT RIGHT");
            return;
        }
		//
		VendorDlg dlg = new VendorDlg(shell, 0);
        Vendor obj = new Vendor();
		dlg.setVendorDlgData(obj);
		dlg.open();
        listDataVendor.add(obj);
        //
		reloadTableVendor();
		//
	}
}
