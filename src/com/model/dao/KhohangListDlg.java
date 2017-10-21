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
public class KhohangListDlg extends Dialog {
	static Logger logger = LogManager.getLogger(KhohangListDlg.class.getName());
	protected Object result;
	protected Shell shell;

	private class TableLabelProviderKhohang extends LabelProvider implements ITableLabelProvider {
		public Image getColumnImage(Object element, int columnIndex) {
			return null;
		}
		public String getColumnText(Object element, int columnIndex) {
			if(element instanceof Khohang){
				return ((Khohang) element).getIndex(columnIndex);
			}
			return "";
		}
	}
	private static class ContentProviderKhohang implements IStructuredContentProvider {
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
	private Table tableKhohang;
	private TableViewer tableViewerKhohang;
	private List<Khohang> listDataKhohang;
	private Text textSearchKhohang;
	private String textSearchKhohangString;
	public Khohang objKhohang = null;
	public int typeKhohangDlg = TYPE_DLG_CHOOSEN; 
	public static final int TYPE_DLG_MANAGER = 0;
	public static final int TYPE_DLG_CHOOSEN = 1;

    public int intTypeDlgKhohang;
	public static final int TYPE_DLG_VIEW = 1;
	public static final int TYPE_DLG_EDIT = 2;
	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public KhohangListDlg(Shell parent, int style) {
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
		shell.setImage(SWTResourceManager.getImage(KhohangDlg.class, "/png/list-2x.png"));
		shell.setSize(610, 340);
		shell.setText("Khohang List View");
		shell.setLayout(new BorderLayout(0, 0));
		shell.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.keyCode==SWT.ESC){
					objKhohang = null;
				}
			}
		});
        
        Composite compositeInShellKhohang = new Composite(shell, SWT.NONE);
		compositeInShellKhohang.setLayout(new BorderLayout(0, 0));
		compositeInShellKhohang.setLayoutData(BorderLayout.CENTER);
        
		Composite compositeHeaderKhohang = new Composite(compositeInShellKhohang, SWT.NONE);
		compositeHeaderKhohang.setLayoutData(BorderLayout.NORTH);
		compositeHeaderKhohang.setLayout(new GridLayout(2, false));

		textSearchKhohang = new Text(compositeHeaderKhohang, SWT.BORDER);
		textSearchKhohang.setFont(SWTResourceManager.getFont("Tahoma", 11, SWT.NORMAL));
		textSearchKhohang.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.keyCode==13){
					reloadTableKhohang();
				}
			}
		});
		
		Button btnNewButtonSearchKhohang = new Button(compositeHeaderKhohang, SWT.NONE);
		btnNewButtonSearchKhohang.setImage(SWTResourceManager.getImage(KhohangDlg.class, "/png/media-play-2x.png"));
		btnNewButtonSearchKhohang.setFont(SWTResourceManager.getFont("Tahoma", 12, SWT.NORMAL));

		btnNewButtonSearchKhohang.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				reloadTableKhohang();
			}
		});
		GridData gd_btnNewButtonKhohang = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnNewButtonKhohang.widthHint = 87;
		btnNewButtonSearchKhohang.setLayoutData(gd_btnNewButtonKhohang);
		btnNewButtonSearchKhohang.setText("Search");
        
		tableViewerKhohang = new TableViewer(compositeInShellKhohang, SWT.BORDER | SWT.FULL_SELECTION);
		tableKhohang = tableViewerKhohang.getTable();
		tableKhohang.setFont(SWTResourceManager.getFont("Tahoma", 11, SWT.NORMAL));
		tableKhohang.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.keyCode==SWT.F5){
					reloadTableKhohang();
                }
                if(e.keyCode==SWT.F4){
					editTableKhohang();
                }
				else if(e.keyCode==13){
					selectTableKhohang();
				}
                else if(e.keyCode==SWT.DEL){
					deleteTableKhohang();
				}
                else if(e.keyCode==SWT.F7){
					newItemKhohang();
				}
			}
		});
        tableKhohang.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				selectTableKhohang();
			}
		});
        
		tableKhohang.setLinesVisible(true);
		tableKhohang.setHeaderVisible(true);
		tableKhohang.setLayoutData(BorderLayout.CENTER);

		TableColumn tbTableColumnKhohangKHO_NAME = new TableColumn(tableKhohang, SWT.LEFT);
		tbTableColumnKhohangKHO_NAME.setWidth(100);
		tbTableColumnKhohangKHO_NAME.setText("KHO_NAME");

		TableColumn tbTableColumnKhohangSTS = new TableColumn(tableKhohang, SWT.RIGHT);
		tbTableColumnKhohangSTS.setWidth(100);
		tbTableColumnKhohangSTS.setText("STS");

        Menu menuKhohang = new Menu(tableKhohang);
		tableKhohang.setMenu(menuKhohang);
		
		MenuItem mntmNewItemKhohang = new MenuItem(menuKhohang, SWT.NONE);
		mntmNewItemKhohang.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				newItemKhohang();
			}
		});
		mntmNewItemKhohang.setImage(SWTResourceManager.getImage(KhohangDlg.class, "/png/arrow-circle-top-2x.png"));
		mntmNewItemKhohang.setText("New");
		
		MenuItem mntmEditItemKhohang = new MenuItem(menuKhohang, SWT.NONE);
		mntmEditItemKhohang.setImage(SWTResourceManager.getImage(KhohangDlg.class, "/png/wrench-2x.png"));
		mntmEditItemKhohang.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				editTableKhohang();
			}
		});
		mntmEditItemKhohang.setText("Edit");
		
		MenuItem mntmDeleteKhohang = new MenuItem(menuKhohang, SWT.NONE);
		mntmDeleteKhohang.setImage(SWTResourceManager.getImage(KhohangDlg.class, "/png/circle-x-2x.png"));
		mntmDeleteKhohang.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				deleteTableKhohang();
			}
		});
		mntmDeleteKhohang.setText("Delete");

		tableViewerKhohang.setLabelProvider(new TableLabelProviderKhohang());
		tableViewerKhohang.setContentProvider(new ContentProviderKhohang());
		tableViewerKhohang.setInput(listDataKhohang);
        //
        //
		loadDataKhohang();
		//
        reloadTableKhohang();
	}
    public void setDataKhohang(String textSearchString){
		this.textSearchKhohangString = textSearchString;
	}
	private void loadDataKhohang() {
		if(textSearchKhohangString!=null){
			textSearchKhohang.setText(textSearchKhohangString);
		}
	}
	protected void reloadTableKhohang() {
        if(DbHelper.checkPhanQuyen(DbHelper.READ, "khohang")==false){
			logger.info("DON'T HAVE READ RIGHT");
			return;
		}
    
        if(listDataKhohang!=null){
            // 
            tableViewerKhohang.setInput(listDataKhohang);
            tableViewerKhohang.refresh();
            //
            if(listDataKhohang.size()==0){
                textSearchKhohang.forceFocus();
            }
            else{
                tableKhohang.forceFocus();
                tableKhohang.setSelection(0);
            }
            return;
        }
		// Do search in the first time
		String searchString = textSearchKhohang.getText().toLowerCase().trim();
		String sql = "select * from khohang WHERE STS<> "+DbHelper.DELETE_STATUS+" ";
		if(searchString.length()>0){
            sql += " and ( 0 ";
        sql += " or LOWER(KHO_NAME) like '%"+searchString+"%'";
            sql += " )";
        }
		try  {
            logger.info(sql);
			Connection con = DbHelper.getSql2o();
			listDataKhohang = con.createQuery(sql).executeAndFetch(Khohang.class);
	    }   
	    catch(Exception e){
	    	logger.error(e);
	    	MessageDialog.openError(shell, "Error", e.getMessage());
	    }
		// 
		tableViewerKhohang.setInput(listDataKhohang);
		tableViewerKhohang.refresh();
        //
        if(listDataKhohang.size()==0){
            textSearchKhohang.forceFocus();
        }
        else{
            tableKhohang.forceFocus();
            tableKhohang.setSelection(0);
        }
	}
    
    protected void selectTableKhohang() {
		if(tableKhohang.getSelectionCount()==0){
			return;
		}
		TableItem item = tableKhohang.getSelection()[0];
		Khohang obj = (Khohang)item.getData();
        logger.info(obj.toString());
        //
        //
    	if(typeKhohangDlg==TYPE_DLG_CHOOSEN){
    		// Choosen and close dlg
    		objKhohang = obj;
    		shell.close();
    	}
    	else{
            if(DbHelper.checkPhanQuyen(DbHelper.UPDATE, "khohang")==false){
                logger.info("DON'T HAVE UPDATE RIGHT");
                return;
            }
            if(intTypeDlgKhohang==TYPE_DLG_VIEW){
                return;
            }
			KhohangDlg dlg = new KhohangDlg(shell, 0);
            dlg.setKhohangDlgData(obj);
            dlg.open();
            //
            reloadTableKhohang();
    	}
	}
    protected void editTableKhohang() {
        if(intTypeDlgKhohang==TYPE_DLG_VIEW){
            return;
        }
        if(DbHelper.checkPhanQuyen(DbHelper.UPDATE, "khohang")==false){
            logger.info("DON'T HAVE UPDATE RIGHT");
            return;
        }
    	if(tableKhohang.getSelectionCount()==0){
			return;
		}
		TableItem item = tableKhohang.getSelection()[0];
		Khohang obj = (Khohang)item.getData();
        logger.info(obj.toString());
        //
        //
		KhohangDlg dlg = new KhohangDlg(shell, 0);
        dlg.setKhohangDlgData(obj);
        dlg.open();
        //
        reloadTableKhohang();
	}
    protected void deleteTableKhohang() {
        if(intTypeDlgKhohang==TYPE_DLG_VIEW){
            return;
        }
        //
        if(DbHelper.checkPhanQuyen(DbHelper.DELETE, "khohang")==false){
            logger.info("DON'T HAVE DELETE RIGHT");
            return;
        }
		if(tableKhohang.getSelectionCount()==0){
			return;
		}
        if(MessageDialog.openConfirm(shell, "Confirm?", "Delete ?")==false){
			return;
		}
		
		TableItem item = tableKhohang.getSelection()[0];
		Khohang obj = (Khohang)item.getData();
        logger.info(obj.toString());
        //
		obj.delete();
        listDataKhohang.remove(obj);
        //
		reloadTableKhohang();
	}

	protected void newItemKhohang() {
        if(intTypeDlgKhohang==TYPE_DLG_VIEW){
            return;
        }
        //
        if(DbHelper.checkPhanQuyen(DbHelper.INSERT, "khohang")==false){
            logger.info("DON'T HAVE INSERT RIGHT");
            return;
        }
		//
		KhohangDlg dlg = new KhohangDlg(shell, 0);
        Khohang obj = new Khohang();
		dlg.setKhohangDlgData(obj);
		dlg.open();
        listDataKhohang.add(obj);
        //
		reloadTableKhohang();
		//
	}
}
