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
public class MaDkbhListDlg extends Dialog {
	static Logger logger = LogManager.getLogger(MaDkbhListDlg.class.getName());
	protected Object result;
	protected Shell shell;

	private class TableLabelProviderMaDkbh extends LabelProvider implements ITableLabelProvider {
		public Image getColumnImage(Object element, int columnIndex) {
			return null;
		}
		public String getColumnText(Object element, int columnIndex) {
			if(element instanceof MaDkbh){
				return ((MaDkbh) element).getIndex(columnIndex);
			}
			return "";
		}
	}
	private static class ContentProviderMaDkbh implements IStructuredContentProvider {
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
	private Table tableMaDkbh;
	private TableViewer tableViewerMaDkbh;
	private List<MaDkbh> listDataMaDkbh;
	private Text textSearchMaDkbh;
	private String textSearchMaDkbhString;
	public MaDkbh objMaDkbh = null;
	public int typeMaDkbhDlg = TYPE_DLG_CHOOSEN; 
	public static final int TYPE_DLG_MANAGER = 0;
	public static final int TYPE_DLG_CHOOSEN = 1;

    public int intTypeDlgMaDkbh;
	public static final int TYPE_DLG_VIEW = 1;
	public static final int TYPE_DLG_EDIT = 2;
	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public MaDkbhListDlg(Shell parent, int style) {
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
		shell.setImage(SWTResourceManager.getImage(MaDkbhDlg.class, "/png/list-2x.png"));
		shell.setSize(610, 340);
		shell.setText("MaDkbh List View");
		shell.setLayout(new BorderLayout(0, 0));
		shell.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.keyCode==SWT.ESC){
					objMaDkbh = null;
				}
			}
		});
        
        Composite compositeInShellMaDkbh = new Composite(shell, SWT.NONE);
		compositeInShellMaDkbh.setLayout(new BorderLayout(0, 0));
		compositeInShellMaDkbh.setLayoutData(BorderLayout.CENTER);
        
		Composite compositeHeaderMaDkbh = new Composite(compositeInShellMaDkbh, SWT.NONE);
		compositeHeaderMaDkbh.setLayoutData(BorderLayout.NORTH);
		compositeHeaderMaDkbh.setLayout(new GridLayout(2, false));

		textSearchMaDkbh = new Text(compositeHeaderMaDkbh, SWT.BORDER);
		textSearchMaDkbh.setFont(SWTResourceManager.getFont("Tahoma", 11, SWT.NORMAL));
		textSearchMaDkbh.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.keyCode==13){
					reloadTableMaDkbh();
				}
			}
		});
		
		Button btnNewButtonSearchMaDkbh = new Button(compositeHeaderMaDkbh, SWT.NONE);
		btnNewButtonSearchMaDkbh.setImage(SWTResourceManager.getImage(MaDkbhDlg.class, "/png/media-play-2x.png"));
		btnNewButtonSearchMaDkbh.setFont(SWTResourceManager.getFont("Tahoma", 12, SWT.NORMAL));

		btnNewButtonSearchMaDkbh.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				reloadTableMaDkbh();
			}
		});
		GridData gd_btnNewButtonMaDkbh = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnNewButtonMaDkbh.widthHint = 87;
		btnNewButtonSearchMaDkbh.setLayoutData(gd_btnNewButtonMaDkbh);
		btnNewButtonSearchMaDkbh.setText("Search");
        
		tableViewerMaDkbh = new TableViewer(compositeInShellMaDkbh, SWT.BORDER | SWT.FULL_SELECTION);
		tableMaDkbh = tableViewerMaDkbh.getTable();
		tableMaDkbh.setFont(SWTResourceManager.getFont("Tahoma", 11, SWT.NORMAL));
		tableMaDkbh.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.keyCode==SWT.F5){
					reloadTableMaDkbh();
                }
                if(e.keyCode==SWT.F4){
					editTableMaDkbh();
                }
				else if(e.keyCode==13){
					selectTableMaDkbh();
				}
                else if(e.keyCode==SWT.DEL){
					deleteTableMaDkbh();
				}
                else if(e.keyCode==SWT.F7){
					newItemMaDkbh();
				}
			}
		});
        tableMaDkbh.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				selectTableMaDkbh();
			}
		});
        
		tableMaDkbh.setLinesVisible(true);
		tableMaDkbh.setHeaderVisible(true);
		tableMaDkbh.setLayoutData(BorderLayout.CENTER);

		TableColumn tbTableColumnMaDkbhTEN_DKBD = new TableColumn(tableMaDkbh, SWT.LEFT);
		tbTableColumnMaDkbhTEN_DKBD.setWidth(100);
		tbTableColumnMaDkbhTEN_DKBD.setText("TEN_DKBD");

        Menu menuMaDkbh = new Menu(tableMaDkbh);
		tableMaDkbh.setMenu(menuMaDkbh);
		
		MenuItem mntmNewItemMaDkbh = new MenuItem(menuMaDkbh, SWT.NONE);
		mntmNewItemMaDkbh.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				newItemMaDkbh();
			}
		});
		mntmNewItemMaDkbh.setImage(SWTResourceManager.getImage(MaDkbhDlg.class, "/png/arrow-circle-top-2x.png"));
		mntmNewItemMaDkbh.setText("New");
		
		MenuItem mntmEditItemMaDkbh = new MenuItem(menuMaDkbh, SWT.NONE);
		mntmEditItemMaDkbh.setImage(SWTResourceManager.getImage(MaDkbhDlg.class, "/png/wrench-2x.png"));
		mntmEditItemMaDkbh.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				editTableMaDkbh();
			}
		});
		mntmEditItemMaDkbh.setText("Edit");
		
		MenuItem mntmDeleteMaDkbh = new MenuItem(menuMaDkbh, SWT.NONE);
		mntmDeleteMaDkbh.setImage(SWTResourceManager.getImage(MaDkbhDlg.class, "/png/circle-x-2x.png"));
		mntmDeleteMaDkbh.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				deleteTableMaDkbh();
			}
		});
		mntmDeleteMaDkbh.setText("Delete");

		tableViewerMaDkbh.setLabelProvider(new TableLabelProviderMaDkbh());
		tableViewerMaDkbh.setContentProvider(new ContentProviderMaDkbh());
		tableViewerMaDkbh.setInput(listDataMaDkbh);
        //
        //
		loadDataMaDkbh();
		//
        reloadTableMaDkbh();
	}
    public void setDataMaDkbh(String textSearchString){
		this.textSearchMaDkbhString = textSearchString;
	}
	private void loadDataMaDkbh() {
		if(textSearchMaDkbhString!=null){
			textSearchMaDkbh.setText(textSearchMaDkbhString);
		}
	}
	protected void reloadTableMaDkbh() {
        if(DbHelper.checkPhanQuyen(DbHelper.READ, "ma_dkbh")==false){
			logger.info("DON'T HAVE READ RIGHT");
			return;
		}
    
        if(listDataMaDkbh!=null){
            // 
            tableViewerMaDkbh.setInput(listDataMaDkbh);
            tableViewerMaDkbh.refresh();
            //
            if(listDataMaDkbh.size()==0){
                textSearchMaDkbh.forceFocus();
            }
            else{
                tableMaDkbh.forceFocus();
                tableMaDkbh.setSelection(0);
            }
            return;
        }
		// Do search in the first time
		String searchString = textSearchMaDkbh.getText().toLowerCase().trim();
		String sql = "select * from ma_dkbh WHERE STS<> "+DbHelper.DELETE_STATUS+" ";
		if(searchString.length()>0){
            sql += " and ( 0 ";
        sql += " or LOWER(TEN_DKBD) like '%"+searchString+"%'";
            sql += " )";
        }
		try  {
            logger.info(sql);
			Connection con = DbHelper.getSql2o();
			listDataMaDkbh = con.createQuery(sql).executeAndFetch(MaDkbh.class);
	    }   
	    catch(Exception e){
	    	logger.error(e);
	    	MessageDialog.openError(shell, "Error", e.getMessage());
	    }
		// 
		tableViewerMaDkbh.setInput(listDataMaDkbh);
		tableViewerMaDkbh.refresh();
        //
        if(listDataMaDkbh.size()==0){
            textSearchMaDkbh.forceFocus();
        }
        else{
            tableMaDkbh.forceFocus();
            tableMaDkbh.setSelection(0);
        }
	}
    
    protected void selectTableMaDkbh() {
		if(tableMaDkbh.getSelectionCount()==0){
			return;
		}
		TableItem item = tableMaDkbh.getSelection()[0];
		MaDkbh obj = (MaDkbh)item.getData();
        logger.info(obj.toString());
        //
        //
    	if(typeMaDkbhDlg==TYPE_DLG_CHOOSEN){
    		// Choosen and close dlg
    		objMaDkbh = obj;
    		shell.close();
    	}
    	else{
            if(DbHelper.checkPhanQuyen(DbHelper.UPDATE, "ma_dkbh")==false){
                logger.info("DON'T HAVE UPDATE RIGHT");
                return;
            }
            if(intTypeDlgMaDkbh==TYPE_DLG_VIEW){
                return;
            }
			MaDkbhDlg dlg = new MaDkbhDlg(shell, 0);
            dlg.setMaDkbhDlgData(obj);
            dlg.open();
            //
            reloadTableMaDkbh();
    	}
	}
    protected void editTableMaDkbh() {
        if(intTypeDlgMaDkbh==TYPE_DLG_VIEW){
            return;
        }
        if(DbHelper.checkPhanQuyen(DbHelper.UPDATE, "ma_dkbh")==false){
            logger.info("DON'T HAVE UPDATE RIGHT");
            return;
        }
    	if(tableMaDkbh.getSelectionCount()==0){
			return;
		}
		TableItem item = tableMaDkbh.getSelection()[0];
		MaDkbh obj = (MaDkbh)item.getData();
        logger.info(obj.toString());
        //
        //
		MaDkbhDlg dlg = new MaDkbhDlg(shell, 0);
        dlg.setMaDkbhDlgData(obj);
        dlg.open();
        //
        reloadTableMaDkbh();
	}
    protected void deleteTableMaDkbh() {
        if(intTypeDlgMaDkbh==TYPE_DLG_VIEW){
            return;
        }
        //
        if(DbHelper.checkPhanQuyen(DbHelper.DELETE, "ma_dkbh")==false){
            logger.info("DON'T HAVE DELETE RIGHT");
            return;
        }
		if(tableMaDkbh.getSelectionCount()==0){
			return;
		}
        if(MessageDialog.openConfirm(shell, "Confirm?", "Delete ?")==false){
			return;
		}
		
		TableItem item = tableMaDkbh.getSelection()[0];
		MaDkbh obj = (MaDkbh)item.getData();
        logger.info(obj.toString());
        //
		obj.delete();
        listDataMaDkbh.remove(obj);
        //
		reloadTableMaDkbh();
	}

	protected void newItemMaDkbh() {
        if(intTypeDlgMaDkbh==TYPE_DLG_VIEW){
            return;
        }
        //
        if(DbHelper.checkPhanQuyen(DbHelper.INSERT, "ma_dkbh")==false){
            logger.info("DON'T HAVE INSERT RIGHT");
            return;
        }
		//
		MaDkbhDlg dlg = new MaDkbhDlg(shell, 0);
        MaDkbh obj = new MaDkbh();
		dlg.setMaDkbhDlgData(obj);
		dlg.open();
        listDataMaDkbh.add(obj);
        //
		reloadTableMaDkbh();
		//
	}
}
