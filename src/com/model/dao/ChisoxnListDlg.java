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
public class ChisoxnListDlg extends Dialog {
	static Logger logger = LogManager.getLogger(ChisoxnListDlg.class.getName());
	protected Object result;
	protected Shell shell;

	private class TableLabelProviderChisoxn extends LabelProvider implements ITableLabelProvider {
		public Image getColumnImage(Object element, int columnIndex) {
			return null;
		}
		public String getColumnText(Object element, int columnIndex) {
			if(element instanceof Chisoxn){
				return ((Chisoxn) element).getIndex(columnIndex);
			}
			return "";
		}
	}
	private static class ContentProviderChisoxn implements IStructuredContentProvider {
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
	private Table tableChisoxn;
	private TableViewer tableViewerChisoxn;
	private List<Chisoxn> listDataChisoxn;
	private Text textSearchChisoxn;
	private String textSearchChisoxnString;
	public Chisoxn objChisoxn = null;
	public int typeChisoxnDlg = TYPE_DLG_CHOOSEN; 
	public static final int TYPE_DLG_MANAGER = 0;
	public static final int TYPE_DLG_CHOOSEN = 1;

    public int intTypeDlgChisoxn;
	public static final int TYPE_DLG_VIEW = 1;
	public static final int TYPE_DLG_EDIT = 2;
	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public ChisoxnListDlg(Shell parent, int style) {
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
		shell.setImage(SWTResourceManager.getImage(ChisoxnDlg.class, "/png/list-2x.png"));
		shell.setSize(610, 340);
		shell.setText("Chisoxn List View");
		shell.setLayout(new BorderLayout(0, 0));
		shell.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.keyCode==SWT.ESC){
					objChisoxn = null;
				}
			}
		});
        
        Composite compositeInShellChisoxn = new Composite(shell, SWT.NONE);
		compositeInShellChisoxn.setLayout(new BorderLayout(0, 0));
		compositeInShellChisoxn.setLayoutData(BorderLayout.CENTER);
        
		Composite compositeHeaderChisoxn = new Composite(compositeInShellChisoxn, SWT.NONE);
		compositeHeaderChisoxn.setLayoutData(BorderLayout.NORTH);
		compositeHeaderChisoxn.setLayout(new GridLayout(2, false));

		textSearchChisoxn = new Text(compositeHeaderChisoxn, SWT.BORDER);
		textSearchChisoxn.setFont(SWTResourceManager.getFont("Tahoma", 11, SWT.NORMAL));
		textSearchChisoxn.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.keyCode==13){
					reloadTableChisoxn();
				}
			}
		});
		
		Button btnNewButtonSearchChisoxn = new Button(compositeHeaderChisoxn, SWT.NONE);
		btnNewButtonSearchChisoxn.setImage(SWTResourceManager.getImage(ChisoxnDlg.class, "/png/media-play-2x.png"));
		btnNewButtonSearchChisoxn.setFont(SWTResourceManager.getFont("Tahoma", 12, SWT.NORMAL));

		btnNewButtonSearchChisoxn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				reloadTableChisoxn();
			}
		});
		GridData gd_btnNewButtonChisoxn = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnNewButtonChisoxn.widthHint = 87;
		btnNewButtonSearchChisoxn.setLayoutData(gd_btnNewButtonChisoxn);
		btnNewButtonSearchChisoxn.setText("Search");
        
		tableViewerChisoxn = new TableViewer(compositeInShellChisoxn, SWT.BORDER | SWT.FULL_SELECTION);
		tableChisoxn = tableViewerChisoxn.getTable();
		tableChisoxn.setFont(SWTResourceManager.getFont("Tahoma", 11, SWT.NORMAL));
		tableChisoxn.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.keyCode==SWT.F5){
					reloadTableChisoxn();
                }
                if(e.keyCode==SWT.F4){
					editTableChisoxn();
                }
				else if(e.keyCode==13){
					selectTableChisoxn();
				}
                else if(e.keyCode==SWT.DEL){
					deleteTableChisoxn();
				}
                else if(e.keyCode==SWT.F7){
					newItemChisoxn();
				}
			}
		});
        tableChisoxn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				selectTableChisoxn();
			}
		});
        
		tableChisoxn.setLinesVisible(true);
		tableChisoxn.setHeaderVisible(true);
		tableChisoxn.setLayoutData(BorderLayout.CENTER);

		TableColumn tbTableColumnChisoxnDV_ID = new TableColumn(tableChisoxn, SWT.RIGHT);
		tbTableColumnChisoxnDV_ID.setWidth(100);
		tbTableColumnChisoxnDV_ID.setText("DV_ID");

		TableColumn tbTableColumnChisoxnCS_ID = new TableColumn(tableChisoxn, SWT.RIGHT);
		tbTableColumnChisoxnCS_ID.setWidth(100);
		tbTableColumnChisoxnCS_ID.setText("CS_ID");

		TableColumn tbTableColumnChisoxnMA_DVKT = new TableColumn(tableChisoxn, SWT.LEFT);
		tbTableColumnChisoxnMA_DVKT.setWidth(100);
		tbTableColumnChisoxnMA_DVKT.setText("MA_DVKT");

		TableColumn tbTableColumnChisoxnTEN_DVKT = new TableColumn(tableChisoxn, SWT.LEFT);
		tbTableColumnChisoxnTEN_DVKT.setWidth(100);
		tbTableColumnChisoxnTEN_DVKT.setText("TEN_DVKT");

		TableColumn tbTableColumnChisoxnCS_NAME = new TableColumn(tableChisoxn, SWT.LEFT);
		tbTableColumnChisoxnCS_NAME.setWidth(100);
		tbTableColumnChisoxnCS_NAME.setText("CS_NAME");

		TableColumn tbTableColumnChisoxnSTS = new TableColumn(tableChisoxn, SWT.RIGHT);
		tbTableColumnChisoxnSTS.setWidth(100);
		tbTableColumnChisoxnSTS.setText("STS");

        Menu menuChisoxn = new Menu(tableChisoxn);
		tableChisoxn.setMenu(menuChisoxn);
		
		MenuItem mntmNewItemChisoxn = new MenuItem(menuChisoxn, SWT.NONE);
		mntmNewItemChisoxn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				newItemChisoxn();
			}
		});
		mntmNewItemChisoxn.setImage(SWTResourceManager.getImage(ChisoxnDlg.class, "/png/arrow-circle-top-2x.png"));
		mntmNewItemChisoxn.setText("New");
		
		MenuItem mntmEditItemChisoxn = new MenuItem(menuChisoxn, SWT.NONE);
		mntmEditItemChisoxn.setImage(SWTResourceManager.getImage(ChisoxnDlg.class, "/png/wrench-2x.png"));
		mntmEditItemChisoxn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				editTableChisoxn();
			}
		});
		mntmEditItemChisoxn.setText("Edit");
		
		MenuItem mntmDeleteChisoxn = new MenuItem(menuChisoxn, SWT.NONE);
		mntmDeleteChisoxn.setImage(SWTResourceManager.getImage(ChisoxnDlg.class, "/png/circle-x-2x.png"));
		mntmDeleteChisoxn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				deleteTableChisoxn();
			}
		});
		mntmDeleteChisoxn.setText("Delete");

		tableViewerChisoxn.setLabelProvider(new TableLabelProviderChisoxn());
		tableViewerChisoxn.setContentProvider(new ContentProviderChisoxn());
		tableViewerChisoxn.setInput(listDataChisoxn);
        //
        //
		loadDataChisoxn();
		//
        reloadTableChisoxn();
	}
    public void setDataChisoxn(String textSearchString){
		this.textSearchChisoxnString = textSearchString;
	}
	private void loadDataChisoxn() {
		if(textSearchChisoxnString!=null){
			textSearchChisoxn.setText(textSearchChisoxnString);
		}
	}
	protected void reloadTableChisoxn() {
        if(DbHelper.checkPhanQuyen(DbHelper.READ, "chisoxn")==false){
			logger.info("DON'T HAVE READ RIGHT");
			return;
		}
    
        if(listDataChisoxn!=null){
            // 
            tableViewerChisoxn.setInput(listDataChisoxn);
            tableViewerChisoxn.refresh();
            //
            if(listDataChisoxn.size()==0){
                textSearchChisoxn.forceFocus();
            }
            else{
                tableChisoxn.forceFocus();
                tableChisoxn.setSelection(0);
            }
            return;
        }
		// Do search in the first time
		String searchString = textSearchChisoxn.getText().toLowerCase().trim();
		String sql = "select * from chisoxn WHERE STS<> "+DbHelper.DELETE_STATUS+" ";
		if(searchString.length()>0){
            sql += " and ( 0 ";
        sql += " or LOWER(MA_DVKT) like '%"+searchString+"%'";
        sql += " or LOWER(TEN_DVKT) like '%"+searchString+"%'";
        sql += " or LOWER(CS_NAME) like '%"+searchString+"%'";
            sql += " )";
        }
		try  {
            logger.info(sql);
			Connection con = DbHelper.getSql2o();
			listDataChisoxn = con.createQuery(sql).executeAndFetch(Chisoxn.class);
	    }   
	    catch(Exception e){
	    	logger.error(e);
	    	MessageDialog.openError(shell, "Error", e.getMessage());
	    }
		// 
		tableViewerChisoxn.setInput(listDataChisoxn);
		tableViewerChisoxn.refresh();
        //
        if(listDataChisoxn.size()==0){
            textSearchChisoxn.forceFocus();
        }
        else{
            tableChisoxn.forceFocus();
            tableChisoxn.setSelection(0);
        }
	}
    
    protected void selectTableChisoxn() {
		if(tableChisoxn.getSelectionCount()==0){
			return;
		}
		TableItem item = tableChisoxn.getSelection()[0];
		Chisoxn obj = (Chisoxn)item.getData();
        logger.info(obj.toString());
        //
        //
    	if(typeChisoxnDlg==TYPE_DLG_CHOOSEN){
    		// Choosen and close dlg
    		objChisoxn = obj;
    		shell.close();
    	}
    	else{
            if(DbHelper.checkPhanQuyen(DbHelper.UPDATE, "chisoxn")==false){
                logger.info("DON'T HAVE UPDATE RIGHT");
                return;
            }
            if(intTypeDlgChisoxn==TYPE_DLG_VIEW){
                return;
            }
			ChisoxnDlg dlg = new ChisoxnDlg(shell, 0);
            dlg.setChisoxnDlgData(obj);
            dlg.open();
            //
            reloadTableChisoxn();
    	}
	}
    protected void editTableChisoxn() {
        if(intTypeDlgChisoxn==TYPE_DLG_VIEW){
            return;
        }
        if(DbHelper.checkPhanQuyen(DbHelper.UPDATE, "chisoxn")==false){
            logger.info("DON'T HAVE UPDATE RIGHT");
            return;
        }
    	if(tableChisoxn.getSelectionCount()==0){
			return;
		}
		TableItem item = tableChisoxn.getSelection()[0];
		Chisoxn obj = (Chisoxn)item.getData();
        logger.info(obj.toString());
        //
        //
		ChisoxnDlg dlg = new ChisoxnDlg(shell, 0);
        dlg.setChisoxnDlgData(obj);
        dlg.open();
        //
        reloadTableChisoxn();
	}
    protected void deleteTableChisoxn() {
        if(intTypeDlgChisoxn==TYPE_DLG_VIEW){
            return;
        }
        //
        if(DbHelper.checkPhanQuyen(DbHelper.DELETE, "chisoxn")==false){
            logger.info("DON'T HAVE DELETE RIGHT");
            return;
        }
		if(tableChisoxn.getSelectionCount()==0){
			return;
		}
        if(MessageDialog.openConfirm(shell, "Confirm?", "Delete ?")==false){
			return;
		}
		
		TableItem item = tableChisoxn.getSelection()[0];
		Chisoxn obj = (Chisoxn)item.getData();
        logger.info(obj.toString());
        //
		obj.delete();
        listDataChisoxn.remove(obj);
        //
		reloadTableChisoxn();
	}

	protected void newItemChisoxn() {
        if(intTypeDlgChisoxn==TYPE_DLG_VIEW){
            return;
        }
        //
        if(DbHelper.checkPhanQuyen(DbHelper.INSERT, "chisoxn")==false){
            logger.info("DON'T HAVE INSERT RIGHT");
            return;
        }
		//
		ChisoxnDlg dlg = new ChisoxnDlg(shell, 0);
        Chisoxn obj = new Chisoxn();
		dlg.setChisoxnDlgData(obj);
		dlg.open();
        listDataChisoxn.add(obj);
        //
		reloadTableChisoxn();
		//
	}
}
