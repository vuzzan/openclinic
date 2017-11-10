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
public class MstLieudungListDlg extends Dialog {
	static Logger logger = LogManager.getLogger(MstLieudungListDlg.class.getName());
	protected Object result;
	protected Shell shell;

	private class TableLabelProviderMstLieudung extends LabelProvider implements ITableLabelProvider {
		public Image getColumnImage(Object element, int columnIndex) {
			return null;
		}
		public String getColumnText(Object element, int columnIndex) {
			if(element instanceof MstLieudung){
				return ((MstLieudung) element).getIndex(columnIndex);
			}
			return "";
		}
	}
	private static class ContentProviderMstLieudung implements IStructuredContentProvider {
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
	private Table tableMstLieudung;
	private TableViewer tableViewerMstLieudung;
	private List<MstLieudung> listDataMstLieudung;
	private Text textSearchMstLieudung;
	private String textSearchMstLieudungString;
	public MstLieudung objMstLieudung = null;
	public int typeMstLieudungDlg = TYPE_DLG_CHOOSEN; 
	public static final int TYPE_DLG_MANAGER = 0;
	public static final int TYPE_DLG_CHOOSEN = 1;

    public int intTypeDlgMstLieudung;
	public static final int TYPE_DLG_VIEW = 1;
	public static final int TYPE_DLG_EDIT = 2;
	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public MstLieudungListDlg(Shell parent, int style) {
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
		shell.setImage(SWTResourceManager.getImage(MstLieudungDlg.class, "/png/list-2x.png"));
		shell.setSize(610, 340);
		shell.setText("MstLieudung List View");
		shell.setLayout(new BorderLayout(0, 0));
		shell.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.keyCode==SWT.ESC){
					objMstLieudung = null;
				}
			}
		});
        
        Composite compositeInShellMstLieudung = new Composite(shell, SWT.NONE);
		compositeInShellMstLieudung.setLayout(new BorderLayout(0, 0));
		compositeInShellMstLieudung.setLayoutData(BorderLayout.CENTER);
        
		Composite compositeHeaderMstLieudung = new Composite(compositeInShellMstLieudung, SWT.NONE);
		compositeHeaderMstLieudung.setLayoutData(BorderLayout.NORTH);
		compositeHeaderMstLieudung.setLayout(new GridLayout(2, false));

		textSearchMstLieudung = new Text(compositeHeaderMstLieudung, SWT.BORDER);
		textSearchMstLieudung.setFont(SWTResourceManager.getFont("Tahoma", 11, SWT.NORMAL));
		textSearchMstLieudung.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.keyCode==13){
					reloadTableMstLieudung();
				}
			}
		});
		
		Button btnNewButtonSearchMstLieudung = new Button(compositeHeaderMstLieudung, SWT.NONE);
		btnNewButtonSearchMstLieudung.setImage(SWTResourceManager.getImage(MstLieudungDlg.class, "/png/media-play-2x.png"));
		btnNewButtonSearchMstLieudung.setFont(SWTResourceManager.getFont("Tahoma", 12, SWT.NORMAL));

		btnNewButtonSearchMstLieudung.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				reloadTableMstLieudung();
			}
		});
		GridData gd_btnNewButtonMstLieudung = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnNewButtonMstLieudung.widthHint = 87;
		btnNewButtonSearchMstLieudung.setLayoutData(gd_btnNewButtonMstLieudung);
		btnNewButtonSearchMstLieudung.setText("Search");
        
		tableViewerMstLieudung = new TableViewer(compositeInShellMstLieudung, SWT.BORDER | SWT.FULL_SELECTION);
		tableMstLieudung = tableViewerMstLieudung.getTable();
		tableMstLieudung.setFont(SWTResourceManager.getFont("Tahoma", 11, SWT.NORMAL));
		tableMstLieudung.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.keyCode==SWT.F5){
					reloadTableMstLieudung();
                }
                if(e.keyCode==SWT.F4){
					editTableMstLieudung();
                }
				else if(e.keyCode==13){
					selectTableMstLieudung();
				}
                else if(e.keyCode==SWT.DEL){
					deleteTableMstLieudung();
				}
                else if(e.keyCode==SWT.F7){
					newItemMstLieudung();
				}
			}
		});
        tableMstLieudung.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				selectTableMstLieudung();
			}
		});
        
		tableMstLieudung.setLinesVisible(true);
		tableMstLieudung.setHeaderVisible(true);
		tableMstLieudung.setLayoutData(BorderLayout.CENTER);

		TableColumn tbTableColumnMstLieudungLIEUDUNG_NAME = new TableColumn(tableMstLieudung, SWT.LEFT);
		tbTableColumnMstLieudungLIEUDUNG_NAME.setWidth(100);
		tbTableColumnMstLieudungLIEUDUNG_NAME.setText("LIEUDUNG_NAME");

		TableColumn tbTableColumnMstLieudungRANK = new TableColumn(tableMstLieudung, SWT.RIGHT);
		tbTableColumnMstLieudungRANK.setWidth(100);
		tbTableColumnMstLieudungRANK.setText("RANK");

		TableColumn tbTableColumnMstLieudungSTS = new TableColumn(tableMstLieudung, SWT.RIGHT);
		tbTableColumnMstLieudungSTS.setWidth(100);
		tbTableColumnMstLieudungSTS.setText("STS");

        Menu menuMstLieudung = new Menu(tableMstLieudung);
		tableMstLieudung.setMenu(menuMstLieudung);
		
		MenuItem mntmNewItemMstLieudung = new MenuItem(menuMstLieudung, SWT.NONE);
		mntmNewItemMstLieudung.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				newItemMstLieudung();
			}
		});
		mntmNewItemMstLieudung.setImage(SWTResourceManager.getImage(MstLieudungDlg.class, "/png/arrow-circle-top-2x.png"));
		mntmNewItemMstLieudung.setText("New");
		
		MenuItem mntmEditItemMstLieudung = new MenuItem(menuMstLieudung, SWT.NONE);
		mntmEditItemMstLieudung.setImage(SWTResourceManager.getImage(MstLieudungDlg.class, "/png/wrench-2x.png"));
		mntmEditItemMstLieudung.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				editTableMstLieudung();
			}
		});
		mntmEditItemMstLieudung.setText("Edit");
		
		MenuItem mntmDeleteMstLieudung = new MenuItem(menuMstLieudung, SWT.NONE);
		mntmDeleteMstLieudung.setImage(SWTResourceManager.getImage(MstLieudungDlg.class, "/png/circle-x-2x.png"));
		mntmDeleteMstLieudung.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				deleteTableMstLieudung();
			}
		});
		mntmDeleteMstLieudung.setText("Delete");

		tableViewerMstLieudung.setLabelProvider(new TableLabelProviderMstLieudung());
		tableViewerMstLieudung.setContentProvider(new ContentProviderMstLieudung());
		tableViewerMstLieudung.setInput(listDataMstLieudung);
        //
        //
		loadDataMstLieudung();
		//
        reloadTableMstLieudung();
	}
    public void setDataMstLieudung(String textSearchString){
		this.textSearchMstLieudungString = textSearchString;
	}
	private void loadDataMstLieudung() {
		if(textSearchMstLieudungString!=null){
			textSearchMstLieudung.setText(textSearchMstLieudungString);
		}
	}
	protected void reloadTableMstLieudung() {
        if(DbHelper.checkPhanQuyen(DbHelper.READ, "mst_lieudung")==false){
			logger.info("DON'T HAVE READ RIGHT");
			return;
		}
    
        if(listDataMstLieudung!=null){
            // 
            tableViewerMstLieudung.setInput(listDataMstLieudung);
            tableViewerMstLieudung.refresh();
            //
            if(listDataMstLieudung.size()==0){
                textSearchMstLieudung.forceFocus();
            }
            else{
                tableMstLieudung.forceFocus();
                tableMstLieudung.setSelection(0);
            }
            return;
        }
		// Do search in the first time
		String searchString = textSearchMstLieudung.getText().toLowerCase().trim();
		String sql = "select * from mst_lieudung WHERE STS<> "+DbHelper.DELETE_STATUS+" ";
		if(searchString.length()>0){
            sql += " and ( 0 ";
        sql += " or LOWER(LIEUDUNG_NAME) like '%"+searchString+"%'";
            sql += " )";
        }
		try  {
            logger.info(sql);
			Connection con = DbHelper.getSql2o();
			listDataMstLieudung = con.createQuery(sql).executeAndFetch(MstLieudung.class);
	    }   
	    catch(Exception e){
	    	logger.error(e);
	    	MessageDialog.openError(shell, "Error", e.getMessage());
	    }
		// 
		tableViewerMstLieudung.setInput(listDataMstLieudung);
		tableViewerMstLieudung.refresh();
        //
        if(listDataMstLieudung.size()==0){
            textSearchMstLieudung.forceFocus();
        }
        else{
            tableMstLieudung.forceFocus();
            tableMstLieudung.setSelection(0);
        }
	}
    
    protected void selectTableMstLieudung() {
		if(tableMstLieudung.getSelectionCount()==0){
			return;
		}
		TableItem item = tableMstLieudung.getSelection()[0];
		MstLieudung obj = (MstLieudung)item.getData();
        logger.info(obj.toString());
        //
        //
    	if(typeMstLieudungDlg==TYPE_DLG_CHOOSEN){
    		// Choosen and close dlg
    		objMstLieudung = obj;
    		shell.close();
    	}
    	else{
            if(DbHelper.checkPhanQuyen(DbHelper.UPDATE, "mst_lieudung")==false){
                logger.info("DON'T HAVE UPDATE RIGHT");
                return;
            }
            if(intTypeDlgMstLieudung==TYPE_DLG_VIEW){
                return;
            }
			MstLieudungDlg dlg = new MstLieudungDlg(shell, 0);
            dlg.setMstLieudungDlgData(obj);
            dlg.open();
            //
            reloadTableMstLieudung();
    	}
	}
    protected void editTableMstLieudung() {
        if(intTypeDlgMstLieudung==TYPE_DLG_VIEW){
            return;
        }
        if(DbHelper.checkPhanQuyen(DbHelper.UPDATE, "mst_lieudung")==false){
            logger.info("DON'T HAVE UPDATE RIGHT");
            return;
        }
    	if(tableMstLieudung.getSelectionCount()==0){
			return;
		}
		TableItem item = tableMstLieudung.getSelection()[0];
		MstLieudung obj = (MstLieudung)item.getData();
        logger.info(obj.toString());
        //
        //
		MstLieudungDlg dlg = new MstLieudungDlg(shell, 0);
        dlg.setMstLieudungDlgData(obj);
        dlg.open();
        //
        reloadTableMstLieudung();
	}
    protected void deleteTableMstLieudung() {
        if(intTypeDlgMstLieudung==TYPE_DLG_VIEW){
            return;
        }
        //
        if(DbHelper.checkPhanQuyen(DbHelper.DELETE, "mst_lieudung")==false){
            logger.info("DON'T HAVE DELETE RIGHT");
            return;
        }
		if(tableMstLieudung.getSelectionCount()==0){
			return;
		}
        if(MessageDialog.openConfirm(shell, "Confirm?", "Delete ?")==false){
			return;
		}
		
		TableItem item = tableMstLieudung.getSelection()[0];
		MstLieudung obj = (MstLieudung)item.getData();
        logger.info(obj.toString());
        //
		obj.delete();
        listDataMstLieudung.remove(obj);
        //
		reloadTableMstLieudung();
	}

	protected void newItemMstLieudung() {
        if(intTypeDlgMstLieudung==TYPE_DLG_VIEW){
            return;
        }
        //
        if(DbHelper.checkPhanQuyen(DbHelper.INSERT, "mst_lieudung")==false){
            logger.info("DON'T HAVE INSERT RIGHT");
            return;
        }
		//
		MstLieudungDlg dlg = new MstLieudungDlg(shell, 0);
        MstLieudung obj = new MstLieudung();
		dlg.setMstLieudungDlgData(obj);
		dlg.open();
        listDataMstLieudung.add(obj);
        //
		reloadTableMstLieudung();
		//
	}
}
