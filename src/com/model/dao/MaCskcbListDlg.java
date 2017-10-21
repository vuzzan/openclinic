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
public class MaCskcbListDlg extends Dialog {
	static Logger logger = LogManager.getLogger(MaCskcbListDlg.class.getName());
	protected Object result;
	protected Shell shell;

	private class TableLabelProviderMaCskcb extends LabelProvider implements ITableLabelProvider {
		public Image getColumnImage(Object element, int columnIndex) {
			return null;
		}
		public String getColumnText(Object element, int columnIndex) {
			if(element instanceof MaCskcb){
				return ((MaCskcb) element).getIndex(columnIndex);
			}
			return "";
		}
	}
	private static class ContentProviderMaCskcb implements IStructuredContentProvider {
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
	private Table tableMaCskcb;
	private TableViewer tableViewerMaCskcb;
	private List<MaCskcb> listDataMaCskcb;
	private Text textSearchMaCskcb;
	private String textSearchMaCskcbString;
	public MaCskcb objMaCskcb = null;
	public int typeMaCskcbDlg = TYPE_DLG_CHOOSEN; 
	public static final int TYPE_DLG_MANAGER = 0;
	public static final int TYPE_DLG_CHOOSEN = 1;

    public int intTypeDlgMaCskcb;
	public static final int TYPE_DLG_VIEW = 1;
	public static final int TYPE_DLG_EDIT = 2;
	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public MaCskcbListDlg(Shell parent, int style) {
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
		shell.setImage(SWTResourceManager.getImage(MaCskcbDlg.class, "/png/list-2x.png"));
		shell.setSize(610, 340);
		shell.setText("MaCskcb List View");
		shell.setLayout(new BorderLayout(0, 0));
		shell.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.keyCode==SWT.ESC){
					objMaCskcb = null;
				}
			}
		});
        
        Composite compositeInShellMaCskcb = new Composite(shell, SWT.NONE);
		compositeInShellMaCskcb.setLayout(new BorderLayout(0, 0));
		compositeInShellMaCskcb.setLayoutData(BorderLayout.CENTER);
        
		Composite compositeHeaderMaCskcb = new Composite(compositeInShellMaCskcb, SWT.NONE);
		compositeHeaderMaCskcb.setLayoutData(BorderLayout.NORTH);
		compositeHeaderMaCskcb.setLayout(new GridLayout(2, false));

		textSearchMaCskcb = new Text(compositeHeaderMaCskcb, SWT.BORDER);
		textSearchMaCskcb.setFont(SWTResourceManager.getFont("Tahoma", 11, SWT.NORMAL));
		textSearchMaCskcb.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.keyCode==13){
					reloadTableMaCskcb();
				}
			}
		});
		
		Button btnNewButtonSearchMaCskcb = new Button(compositeHeaderMaCskcb, SWT.NONE);
		btnNewButtonSearchMaCskcb.setImage(SWTResourceManager.getImage(MaCskcbDlg.class, "/png/media-play-2x.png"));
		btnNewButtonSearchMaCskcb.setFont(SWTResourceManager.getFont("Tahoma", 12, SWT.NORMAL));

		btnNewButtonSearchMaCskcb.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				reloadTableMaCskcb();
			}
		});
		GridData gd_btnNewButtonMaCskcb = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnNewButtonMaCskcb.widthHint = 87;
		btnNewButtonSearchMaCskcb.setLayoutData(gd_btnNewButtonMaCskcb);
		btnNewButtonSearchMaCskcb.setText("Search");
        
		tableViewerMaCskcb = new TableViewer(compositeInShellMaCskcb, SWT.BORDER | SWT.FULL_SELECTION);
		tableMaCskcb = tableViewerMaCskcb.getTable();
		tableMaCskcb.setFont(SWTResourceManager.getFont("Tahoma", 11, SWT.NORMAL));
		tableMaCskcb.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.keyCode==SWT.F5){
					reloadTableMaCskcb();
                }
                if(e.keyCode==SWT.F4){
					editTableMaCskcb();
                }
				else if(e.keyCode==13){
					selectTableMaCskcb();
				}
                else if(e.keyCode==SWT.DEL){
					deleteTableMaCskcb();
				}
                else if(e.keyCode==SWT.F7){
					newItemMaCskcb();
				}
			}
		});
        tableMaCskcb.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				selectTableMaCskcb();
			}
		});
        
		tableMaCskcb.setLinesVisible(true);
		tableMaCskcb.setHeaderVisible(true);
		tableMaCskcb.setLayoutData(BorderLayout.CENTER);

		TableColumn tbTableColumnMaCskcbMA_NAME = new TableColumn(tableMaCskcb, SWT.LEFT);
		tbTableColumnMaCskcbMA_NAME.setWidth(100);
		tbTableColumnMaCskcbMA_NAME.setText("MA_NAME");

		TableColumn tbTableColumnMaCskcbMA_TUYEN = new TableColumn(tableMaCskcb, SWT.LEFT);
		tbTableColumnMaCskcbMA_TUYEN.setWidth(100);
		tbTableColumnMaCskcbMA_TUYEN.setText("MA_TUYEN");

		TableColumn tbTableColumnMaCskcbMA_RANK = new TableColumn(tableMaCskcb, SWT.RIGHT);
		tbTableColumnMaCskcbMA_RANK.setWidth(100);
		tbTableColumnMaCskcbMA_RANK.setText("MA_RANK");

        Menu menuMaCskcb = new Menu(tableMaCskcb);
		tableMaCskcb.setMenu(menuMaCskcb);
		
		MenuItem mntmNewItemMaCskcb = new MenuItem(menuMaCskcb, SWT.NONE);
		mntmNewItemMaCskcb.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				newItemMaCskcb();
			}
		});
		mntmNewItemMaCskcb.setImage(SWTResourceManager.getImage(MaCskcbDlg.class, "/png/arrow-circle-top-2x.png"));
		mntmNewItemMaCskcb.setText("New");
		
		MenuItem mntmEditItemMaCskcb = new MenuItem(menuMaCskcb, SWT.NONE);
		mntmEditItemMaCskcb.setImage(SWTResourceManager.getImage(MaCskcbDlg.class, "/png/wrench-2x.png"));
		mntmEditItemMaCskcb.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				editTableMaCskcb();
			}
		});
		mntmEditItemMaCskcb.setText("Edit");
		
		MenuItem mntmDeleteMaCskcb = new MenuItem(menuMaCskcb, SWT.NONE);
		mntmDeleteMaCskcb.setImage(SWTResourceManager.getImage(MaCskcbDlg.class, "/png/circle-x-2x.png"));
		mntmDeleteMaCskcb.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				deleteTableMaCskcb();
			}
		});
		mntmDeleteMaCskcb.setText("Delete");

		tableViewerMaCskcb.setLabelProvider(new TableLabelProviderMaCskcb());
		tableViewerMaCskcb.setContentProvider(new ContentProviderMaCskcb());
		tableViewerMaCskcb.setInput(listDataMaCskcb);
        //
        //
		loadDataMaCskcb();
		//
        reloadTableMaCskcb();
	}
    public void setDataMaCskcb(String textSearchString){
		this.textSearchMaCskcbString = textSearchString;
	}
	private void loadDataMaCskcb() {
		if(textSearchMaCskcbString!=null){
			textSearchMaCskcb.setText(textSearchMaCskcbString);
		}
	}
	protected void reloadTableMaCskcb() {
        if(DbHelper.checkPhanQuyen(DbHelper.READ, "ma_cskcb")==false){
			logger.info("DON'T HAVE READ RIGHT");
			return;
		}
    
        if(listDataMaCskcb!=null){
            // 
            tableViewerMaCskcb.setInput(listDataMaCskcb);
            tableViewerMaCskcb.refresh();
            //
            if(listDataMaCskcb.size()==0){
                textSearchMaCskcb.forceFocus();
            }
            else{
                tableMaCskcb.forceFocus();
                tableMaCskcb.setSelection(0);
            }
            return;
        }
		// Do search in the first time
		String searchString = textSearchMaCskcb.getText().toLowerCase().trim();
		String sql = "select * from ma_cskcb WHERE STS<> "+DbHelper.DELETE_STATUS+" ";
		if(searchString.length()>0){
            sql += " and ( 0 ";
        sql += " or LOWER(MA_NAME) like '%"+searchString+"%'";
        sql += " or LOWER(MA_TUYEN) like '%"+searchString+"%'";
            sql += " )";
        }
		try  {
            logger.info(sql);
			Connection con = DbHelper.getSql2o();
			listDataMaCskcb = con.createQuery(sql).executeAndFetch(MaCskcb.class);
	    }   
	    catch(Exception e){
	    	logger.error(e);
	    	MessageDialog.openError(shell, "Error", e.getMessage());
	    }
		// 
		tableViewerMaCskcb.setInput(listDataMaCskcb);
		tableViewerMaCskcb.refresh();
        //
        if(listDataMaCskcb.size()==0){
            textSearchMaCskcb.forceFocus();
        }
        else{
            tableMaCskcb.forceFocus();
            tableMaCskcb.setSelection(0);
        }
	}
    
    protected void selectTableMaCskcb() {
		if(tableMaCskcb.getSelectionCount()==0){
			return;
		}
		TableItem item = tableMaCskcb.getSelection()[0];
		MaCskcb obj = (MaCskcb)item.getData();
        logger.info(obj.toString());
        //
        //
    	if(typeMaCskcbDlg==TYPE_DLG_CHOOSEN){
    		// Choosen and close dlg
    		objMaCskcb = obj;
    		shell.close();
    	}
    	else{
            if(DbHelper.checkPhanQuyen(DbHelper.UPDATE, "ma_cskcb")==false){
                logger.info("DON'T HAVE UPDATE RIGHT");
                return;
            }
            if(intTypeDlgMaCskcb==TYPE_DLG_VIEW){
                return;
            }
			MaCskcbDlg dlg = new MaCskcbDlg(shell, 0);
            dlg.setMaCskcbDlgData(obj);
            dlg.open();
            //
            reloadTableMaCskcb();
    	}
	}
    protected void editTableMaCskcb() {
        if(intTypeDlgMaCskcb==TYPE_DLG_VIEW){
            return;
        }
        if(DbHelper.checkPhanQuyen(DbHelper.UPDATE, "ma_cskcb")==false){
            logger.info("DON'T HAVE UPDATE RIGHT");
            return;
        }
    	if(tableMaCskcb.getSelectionCount()==0){
			return;
		}
		TableItem item = tableMaCskcb.getSelection()[0];
		MaCskcb obj = (MaCskcb)item.getData();
        logger.info(obj.toString());
        //
        //
		MaCskcbDlg dlg = new MaCskcbDlg(shell, 0);
        dlg.setMaCskcbDlgData(obj);
        dlg.open();
        //
        reloadTableMaCskcb();
	}
    protected void deleteTableMaCskcb() {
        if(intTypeDlgMaCskcb==TYPE_DLG_VIEW){
            return;
        }
        //
        if(DbHelper.checkPhanQuyen(DbHelper.DELETE, "ma_cskcb")==false){
            logger.info("DON'T HAVE DELETE RIGHT");
            return;
        }
		if(tableMaCskcb.getSelectionCount()==0){
			return;
		}
        if(MessageDialog.openConfirm(shell, "Confirm?", "Delete ?")==false){
			return;
		}
		
		TableItem item = tableMaCskcb.getSelection()[0];
		MaCskcb obj = (MaCskcb)item.getData();
        logger.info(obj.toString());
        //
		obj.delete();
        listDataMaCskcb.remove(obj);
        //
		reloadTableMaCskcb();
	}

	protected void newItemMaCskcb() {
        if(intTypeDlgMaCskcb==TYPE_DLG_VIEW){
            return;
        }
        //
        if(DbHelper.checkPhanQuyen(DbHelper.INSERT, "ma_cskcb")==false){
            logger.info("DON'T HAVE INSERT RIGHT");
            return;
        }
		//
		MaCskcbDlg dlg = new MaCskcbDlg(shell, 0);
        MaCskcb obj = new MaCskcb();
		dlg.setMaCskcbDlgData(obj);
		dlg.open();
        listDataMaCskcb.add(obj);
        //
		reloadTableMaCskcb();
		//
	}
}
