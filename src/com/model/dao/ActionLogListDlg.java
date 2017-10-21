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
public class ActionLogListDlg extends Dialog {
	static Logger logger = LogManager.getLogger(ActionLogListDlg.class.getName());
	protected Object result;
	protected Shell shell;

	private class TableLabelProviderActionLog extends LabelProvider implements ITableLabelProvider {
		public Image getColumnImage(Object element, int columnIndex) {
			return null;
		}
		public String getColumnText(Object element, int columnIndex) {
			if(element instanceof ActionLog){
				return ((ActionLog) element).getIndex(columnIndex);
			}
			return "";
		}
	}
	private static class ContentProviderActionLog implements IStructuredContentProvider {
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
	private Table tableActionLog;
	private TableViewer tableViewerActionLog;
	private List<ActionLog> listDataActionLog;
	private Text textSearchActionLog;
	private String textSearchActionLogString;
	public ActionLog objActionLog = null;
	public int typeActionLogDlg = TYPE_DLG_CHOOSEN; 
	public static final int TYPE_DLG_MANAGER = 0;
	public static final int TYPE_DLG_CHOOSEN = 1;

    public int intTypeDlgActionLog;
	public static final int TYPE_DLG_VIEW = 1;
	public static final int TYPE_DLG_EDIT = 2;
	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public ActionLogListDlg(Shell parent, int style) {
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
		shell.setImage(SWTResourceManager.getImage(ActionLogDlg.class, "/png/list-2x.png"));
		shell.setSize(610, 340);
		shell.setText("ActionLog List View");
		shell.setLayout(new BorderLayout(0, 0));
		shell.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.keyCode==SWT.ESC){
					objActionLog = null;
				}
			}
		});
        
        Composite compositeInShellActionLog = new Composite(shell, SWT.NONE);
		compositeInShellActionLog.setLayout(new BorderLayout(0, 0));
		compositeInShellActionLog.setLayoutData(BorderLayout.CENTER);
        
		Composite compositeHeaderActionLog = new Composite(compositeInShellActionLog, SWT.NONE);
		compositeHeaderActionLog.setLayoutData(BorderLayout.NORTH);
		compositeHeaderActionLog.setLayout(new GridLayout(2, false));

		textSearchActionLog = new Text(compositeHeaderActionLog, SWT.BORDER);
		textSearchActionLog.setFont(SWTResourceManager.getFont("Tahoma", 11, SWT.NORMAL));
		textSearchActionLog.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.keyCode==13){
					reloadTableActionLog();
				}
			}
		});
		
		Button btnNewButtonSearchActionLog = new Button(compositeHeaderActionLog, SWT.NONE);
		btnNewButtonSearchActionLog.setImage(SWTResourceManager.getImage(ActionLogDlg.class, "/png/media-play-2x.png"));
		btnNewButtonSearchActionLog.setFont(SWTResourceManager.getFont("Tahoma", 12, SWT.NORMAL));

		btnNewButtonSearchActionLog.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				reloadTableActionLog();
			}
		});
		GridData gd_btnNewButtonActionLog = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnNewButtonActionLog.widthHint = 87;
		btnNewButtonSearchActionLog.setLayoutData(gd_btnNewButtonActionLog);
		btnNewButtonSearchActionLog.setText("Search");
        
		tableViewerActionLog = new TableViewer(compositeInShellActionLog, SWT.BORDER | SWT.FULL_SELECTION);
		tableActionLog = tableViewerActionLog.getTable();
		tableActionLog.setFont(SWTResourceManager.getFont("Tahoma", 11, SWT.NORMAL));
		tableActionLog.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.keyCode==SWT.F5){
					reloadTableActionLog();
                }
                if(e.keyCode==SWT.F4){
					editTableActionLog();
                }
				else if(e.keyCode==13){
					selectTableActionLog();
				}
                else if(e.keyCode==SWT.DEL){
					deleteTableActionLog();
				}
                else if(e.keyCode==SWT.F7){
					newItemActionLog();
				}
			}
		});
        tableActionLog.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				selectTableActionLog();
			}
		});
        
		tableActionLog.setLinesVisible(true);
		tableActionLog.setHeaderVisible(true);
		tableActionLog.setLayoutData(BorderLayout.CENTER);

		TableColumn tbTableColumnActionLogu_id = new TableColumn(tableActionLog, SWT.RIGHT);
		tbTableColumnActionLogu_id.setWidth(100);
		tbTableColumnActionLogu_id.setText("u_id");


		TableColumn tbTableColumnActionLogu_time = new TableColumn(tableActionLog, SWT.NONE);
		tbTableColumnActionLogu_time.setWidth(100);
		tbTableColumnActionLogu_time.setText("u_time");

		TableColumn tbTableColumnActionLogu_action = new TableColumn(tableActionLog, SWT.LEFT);
		tbTableColumnActionLogu_action.setWidth(100);
		tbTableColumnActionLogu_action.setText("u_action");

		TableColumn tbTableColumnActionLogdbtable = new TableColumn(tableActionLog, SWT.LEFT);
		tbTableColumnActionLogdbtable.setWidth(100);
		tbTableColumnActionLogdbtable.setText("dbtable");

		TableColumn tbTableColumnActionLogactionid = new TableColumn(tableActionLog, SWT.RIGHT);
		tbTableColumnActionLogactionid.setWidth(100);
		tbTableColumnActionLogactionid.setText("actionid");

		TableColumn tbTableColumnActionLogfieldid = new TableColumn(tableActionLog, SWT.RIGHT);
		tbTableColumnActionLogfieldid.setWidth(100);
		tbTableColumnActionLogfieldid.setText("fieldid");

        Menu menuActionLog = new Menu(tableActionLog);
		tableActionLog.setMenu(menuActionLog);
		
		MenuItem mntmNewItemActionLog = new MenuItem(menuActionLog, SWT.NONE);
		mntmNewItemActionLog.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				newItemActionLog();
			}
		});
		mntmNewItemActionLog.setImage(SWTResourceManager.getImage(ActionLogDlg.class, "/png/arrow-circle-top-2x.png"));
		mntmNewItemActionLog.setText("New");
		
		MenuItem mntmEditItemActionLog = new MenuItem(menuActionLog, SWT.NONE);
		mntmEditItemActionLog.setImage(SWTResourceManager.getImage(ActionLogDlg.class, "/png/wrench-2x.png"));
		mntmEditItemActionLog.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				editTableActionLog();
			}
		});
		mntmEditItemActionLog.setText("Edit");
		
		MenuItem mntmDeleteActionLog = new MenuItem(menuActionLog, SWT.NONE);
		mntmDeleteActionLog.setImage(SWTResourceManager.getImage(ActionLogDlg.class, "/png/circle-x-2x.png"));
		mntmDeleteActionLog.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				deleteTableActionLog();
			}
		});
		mntmDeleteActionLog.setText("Delete");

		tableViewerActionLog.setLabelProvider(new TableLabelProviderActionLog());
		tableViewerActionLog.setContentProvider(new ContentProviderActionLog());
		tableViewerActionLog.setInput(listDataActionLog);
        //
        //
		loadDataActionLog();
		//
        reloadTableActionLog();
	}
    public void setDataActionLog(String textSearchString){
		this.textSearchActionLogString = textSearchString;
	}
	private void loadDataActionLog() {
		if(textSearchActionLogString!=null){
			textSearchActionLog.setText(textSearchActionLogString);
		}
	}
	protected void reloadTableActionLog() {
        if(DbHelper.checkPhanQuyen(DbHelper.READ, "action_log")==false){
			logger.info("DON'T HAVE READ RIGHT");
			return;
		}
    
        if(listDataActionLog!=null){
            // 
            tableViewerActionLog.setInput(listDataActionLog);
            tableViewerActionLog.refresh();
            //
            if(listDataActionLog.size()==0){
                textSearchActionLog.forceFocus();
            }
            else{
                tableActionLog.forceFocus();
                tableActionLog.setSelection(0);
            }
            return;
        }
		// Do search in the first time
		String searchString = textSearchActionLog.getText().toLowerCase().trim();
		String sql = "select * from action_log WHERE STS<> "+DbHelper.DELETE_STATUS+" ";
		if(searchString.length()>0){
            sql += " and ( 0 ";
        sql += " or LOWER(u_action) like '%"+searchString+"%'";
        sql += " or LOWER(dbtable) like '%"+searchString+"%'";
            sql += " )";
        }
		try  {
            logger.info(sql);
			Connection con = DbHelper.getSql2o();
			listDataActionLog = con.createQuery(sql).executeAndFetch(ActionLog.class);
	    }   
	    catch(Exception e){
	    	logger.error(e);
	    	MessageDialog.openError(shell, "Error", e.getMessage());
	    }
		// 
		tableViewerActionLog.setInput(listDataActionLog);
		tableViewerActionLog.refresh();
        //
        if(listDataActionLog.size()==0){
            textSearchActionLog.forceFocus();
        }
        else{
            tableActionLog.forceFocus();
            tableActionLog.setSelection(0);
        }
	}
    
    protected void selectTableActionLog() {
		if(tableActionLog.getSelectionCount()==0){
			return;
		}
		TableItem item = tableActionLog.getSelection()[0];
		ActionLog obj = (ActionLog)item.getData();
        logger.info(obj.toString());
        //
        //
    	if(typeActionLogDlg==TYPE_DLG_CHOOSEN){
    		// Choosen and close dlg
    		objActionLog = obj;
    		shell.close();
    	}
    	else{
            if(DbHelper.checkPhanQuyen(DbHelper.UPDATE, "action_log")==false){
                logger.info("DON'T HAVE UPDATE RIGHT");
                return;
            }
            if(intTypeDlgActionLog==TYPE_DLG_VIEW){
                return;
            }
			ActionLogDlg dlg = new ActionLogDlg(shell, 0);
            dlg.setActionLogDlgData(obj);
            dlg.open();
            //
            reloadTableActionLog();
    	}
	}
    protected void editTableActionLog() {
        if(intTypeDlgActionLog==TYPE_DLG_VIEW){
            return;
        }
        if(DbHelper.checkPhanQuyen(DbHelper.UPDATE, "action_log")==false){
            logger.info("DON'T HAVE UPDATE RIGHT");
            return;
        }
    	if(tableActionLog.getSelectionCount()==0){
			return;
		}
		TableItem item = tableActionLog.getSelection()[0];
		ActionLog obj = (ActionLog)item.getData();
        logger.info(obj.toString());
        //
        //
		ActionLogDlg dlg = new ActionLogDlg(shell, 0);
        dlg.setActionLogDlgData(obj);
        dlg.open();
        //
        reloadTableActionLog();
	}
    protected void deleteTableActionLog() {
        if(intTypeDlgActionLog==TYPE_DLG_VIEW){
            return;
        }
        //
        if(DbHelper.checkPhanQuyen(DbHelper.DELETE, "action_log")==false){
            logger.info("DON'T HAVE DELETE RIGHT");
            return;
        }
		if(tableActionLog.getSelectionCount()==0){
			return;
		}
        if(MessageDialog.openConfirm(shell, "Confirm?", "Delete ?")==false){
			return;
		}
		
		TableItem item = tableActionLog.getSelection()[0];
		ActionLog obj = (ActionLog)item.getData();
        logger.info(obj.toString());
        //
		obj.delete();
        listDataActionLog.remove(obj);
        //
		reloadTableActionLog();
	}

	protected void newItemActionLog() {
        if(intTypeDlgActionLog==TYPE_DLG_VIEW){
            return;
        }
        //
        if(DbHelper.checkPhanQuyen(DbHelper.INSERT, "action_log")==false){
            logger.info("DON'T HAVE INSERT RIGHT");
            return;
        }
		//
		ActionLogDlg dlg = new ActionLogDlg(shell, 0);
        ActionLog obj = new ActionLog();
		dlg.setActionLogDlgData(obj);
		dlg.open();
        listDataActionLog.add(obj);
        //
		reloadTableActionLog();
		//
	}
}
