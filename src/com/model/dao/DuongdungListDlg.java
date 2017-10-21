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
public class DuongdungListDlg extends Dialog {
	static Logger logger = LogManager.getLogger(DuongdungListDlg.class.getName());
	protected Object result;
	protected Shell shell;

	private class TableLabelProviderDuongdung extends LabelProvider implements ITableLabelProvider {
		public Image getColumnImage(Object element, int columnIndex) {
			return null;
		}
		public String getColumnText(Object element, int columnIndex) {
			if(element instanceof Duongdung){
				return ((Duongdung) element).getIndex(columnIndex);
			}
			return "";
		}
	}
	private static class ContentProviderDuongdung implements IStructuredContentProvider {
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
	private Table tableDuongdung;
	private TableViewer tableViewerDuongdung;
	private List<Duongdung> listDataDuongdung;
	private Text textSearchDuongdung;
	private String textSearchDuongdungString;
	public Duongdung objDuongdung = null;
	public int typeDuongdungDlg = TYPE_DLG_CHOOSEN; 
	public static final int TYPE_DLG_MANAGER = 0;
	public static final int TYPE_DLG_CHOOSEN = 1;

    public int intTypeDlgDuongdung;
	public static final int TYPE_DLG_VIEW = 1;
	public static final int TYPE_DLG_EDIT = 2;
	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public DuongdungListDlg(Shell parent, int style) {
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
		shell.setImage(SWTResourceManager.getImage(DuongdungDlg.class, "/png/list-2x.png"));
		shell.setSize(610, 340);
		shell.setText("Duongdung List View");
		shell.setLayout(new BorderLayout(0, 0));
		shell.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.keyCode==SWT.ESC){
					objDuongdung = null;
				}
			}
		});
        
        Composite compositeInShellDuongdung = new Composite(shell, SWT.NONE);
		compositeInShellDuongdung.setLayout(new BorderLayout(0, 0));
		compositeInShellDuongdung.setLayoutData(BorderLayout.CENTER);
        
		Composite compositeHeaderDuongdung = new Composite(compositeInShellDuongdung, SWT.NONE);
		compositeHeaderDuongdung.setLayoutData(BorderLayout.NORTH);
		compositeHeaderDuongdung.setLayout(new GridLayout(2, false));

		textSearchDuongdung = new Text(compositeHeaderDuongdung, SWT.BORDER);
		textSearchDuongdung.setFont(SWTResourceManager.getFont("Tahoma", 11, SWT.NORMAL));
		textSearchDuongdung.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.keyCode==13){
					reloadTableDuongdung();
				}
			}
		});
		
		Button btnNewButtonSearchDuongdung = new Button(compositeHeaderDuongdung, SWT.NONE);
		btnNewButtonSearchDuongdung.setImage(SWTResourceManager.getImage(DuongdungDlg.class, "/png/media-play-2x.png"));
		btnNewButtonSearchDuongdung.setFont(SWTResourceManager.getFont("Tahoma", 12, SWT.NORMAL));

		btnNewButtonSearchDuongdung.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				reloadTableDuongdung();
			}
		});
		GridData gd_btnNewButtonDuongdung = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnNewButtonDuongdung.widthHint = 87;
		btnNewButtonSearchDuongdung.setLayoutData(gd_btnNewButtonDuongdung);
		btnNewButtonSearchDuongdung.setText("Search");
        
		tableViewerDuongdung = new TableViewer(compositeInShellDuongdung, SWT.BORDER | SWT.FULL_SELECTION);
		tableDuongdung = tableViewerDuongdung.getTable();
		tableDuongdung.setFont(SWTResourceManager.getFont("Tahoma", 11, SWT.NORMAL));
		tableDuongdung.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.keyCode==SWT.F5){
					reloadTableDuongdung();
                }
                if(e.keyCode==SWT.F4){
					editTableDuongdung();
                }
				else if(e.keyCode==13){
					selectTableDuongdung();
				}
                else if(e.keyCode==SWT.DEL){
					deleteTableDuongdung();
				}
                else if(e.keyCode==SWT.F7){
					newItemDuongdung();
				}
			}
		});
        tableDuongdung.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				selectTableDuongdung();
			}
		});
        
		tableDuongdung.setLinesVisible(true);
		tableDuongdung.setHeaderVisible(true);
		tableDuongdung.setLayoutData(BorderLayout.CENTER);

		TableColumn tbTableColumnDuongdungDD_NAME = new TableColumn(tableDuongdung, SWT.LEFT);
		tbTableColumnDuongdungDD_NAME.setWidth(100);
		tbTableColumnDuongdungDD_NAME.setText("DD_NAME");

		TableColumn tbTableColumnDuongdungDD_MA = new TableColumn(tableDuongdung, SWT.LEFT);
		tbTableColumnDuongdungDD_MA.setWidth(100);
		tbTableColumnDuongdungDD_MA.setText("DD_MA");

        Menu menuDuongdung = new Menu(tableDuongdung);
		tableDuongdung.setMenu(menuDuongdung);
		
		MenuItem mntmNewItemDuongdung = new MenuItem(menuDuongdung, SWT.NONE);
		mntmNewItemDuongdung.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				newItemDuongdung();
			}
		});
		mntmNewItemDuongdung.setImage(SWTResourceManager.getImage(DuongdungDlg.class, "/png/arrow-circle-top-2x.png"));
		mntmNewItemDuongdung.setText("New");
		
		MenuItem mntmEditItemDuongdung = new MenuItem(menuDuongdung, SWT.NONE);
		mntmEditItemDuongdung.setImage(SWTResourceManager.getImage(DuongdungDlg.class, "/png/wrench-2x.png"));
		mntmEditItemDuongdung.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				editTableDuongdung();
			}
		});
		mntmEditItemDuongdung.setText("Edit");
		
		MenuItem mntmDeleteDuongdung = new MenuItem(menuDuongdung, SWT.NONE);
		mntmDeleteDuongdung.setImage(SWTResourceManager.getImage(DuongdungDlg.class, "/png/circle-x-2x.png"));
		mntmDeleteDuongdung.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				deleteTableDuongdung();
			}
		});
		mntmDeleteDuongdung.setText("Delete");

		tableViewerDuongdung.setLabelProvider(new TableLabelProviderDuongdung());
		tableViewerDuongdung.setContentProvider(new ContentProviderDuongdung());
		tableViewerDuongdung.setInput(listDataDuongdung);
        //
        //
		loadDataDuongdung();
		//
        reloadTableDuongdung();
	}
    public void setDataDuongdung(String textSearchString){
		this.textSearchDuongdungString = textSearchString;
	}
	private void loadDataDuongdung() {
		if(textSearchDuongdungString!=null){
			textSearchDuongdung.setText(textSearchDuongdungString);
		}
	}
	protected void reloadTableDuongdung() {
        if(DbHelper.checkPhanQuyen(DbHelper.READ, "duongdung")==false){
			logger.info("DON'T HAVE READ RIGHT");
			return;
		}
    
        if(listDataDuongdung!=null){
            // 
            tableViewerDuongdung.setInput(listDataDuongdung);
            tableViewerDuongdung.refresh();
            //
            if(listDataDuongdung.size()==0){
                textSearchDuongdung.forceFocus();
            }
            else{
                tableDuongdung.forceFocus();
                tableDuongdung.setSelection(0);
            }
            return;
        }
		// Do search in the first time
		String searchString = textSearchDuongdung.getText().toLowerCase().trim();
		String sql = "select * from duongdung WHERE STS<> "+DbHelper.DELETE_STATUS+" ";
		if(searchString.length()>0){
            sql += " and ( 0 ";
        sql += " or LOWER(DD_NAME) like '%"+searchString+"%'";
        sql += " or LOWER(DD_MA) like '%"+searchString+"%'";
            sql += " )";
        }
		try  {
            logger.info(sql);
			Connection con = DbHelper.getSql2o();
			listDataDuongdung = con.createQuery(sql).executeAndFetch(Duongdung.class);
	    }   
	    catch(Exception e){
	    	logger.error(e);
	    	MessageDialog.openError(shell, "Error", e.getMessage());
	    }
		// 
		tableViewerDuongdung.setInput(listDataDuongdung);
		tableViewerDuongdung.refresh();
        //
        if(listDataDuongdung.size()==0){
            textSearchDuongdung.forceFocus();
        }
        else{
            tableDuongdung.forceFocus();
            tableDuongdung.setSelection(0);
        }
	}
    
    protected void selectTableDuongdung() {
		if(tableDuongdung.getSelectionCount()==0){
			return;
		}
		TableItem item = tableDuongdung.getSelection()[0];
		Duongdung obj = (Duongdung)item.getData();
        logger.info(obj.toString());
        //
        //
    	if(typeDuongdungDlg==TYPE_DLG_CHOOSEN){
    		// Choosen and close dlg
    		objDuongdung = obj;
    		shell.close();
    	}
    	else{
            if(DbHelper.checkPhanQuyen(DbHelper.UPDATE, "duongdung")==false){
                logger.info("DON'T HAVE UPDATE RIGHT");
                return;
            }
            if(intTypeDlgDuongdung==TYPE_DLG_VIEW){
                return;
            }
			DuongdungDlg dlg = new DuongdungDlg(shell, 0);
            dlg.setDuongdungDlgData(obj);
            dlg.open();
            //
            reloadTableDuongdung();
    	}
	}
    protected void editTableDuongdung() {
        if(intTypeDlgDuongdung==TYPE_DLG_VIEW){
            return;
        }
        if(DbHelper.checkPhanQuyen(DbHelper.UPDATE, "duongdung")==false){
            logger.info("DON'T HAVE UPDATE RIGHT");
            return;
        }
    	if(tableDuongdung.getSelectionCount()==0){
			return;
		}
		TableItem item = tableDuongdung.getSelection()[0];
		Duongdung obj = (Duongdung)item.getData();
        logger.info(obj.toString());
        //
        //
		DuongdungDlg dlg = new DuongdungDlg(shell, 0);
        dlg.setDuongdungDlgData(obj);
        dlg.open();
        //
        reloadTableDuongdung();
	}
    protected void deleteTableDuongdung() {
        if(intTypeDlgDuongdung==TYPE_DLG_VIEW){
            return;
        }
        //
        if(DbHelper.checkPhanQuyen(DbHelper.DELETE, "duongdung")==false){
            logger.info("DON'T HAVE DELETE RIGHT");
            return;
        }
		if(tableDuongdung.getSelectionCount()==0){
			return;
		}
        if(MessageDialog.openConfirm(shell, "Confirm?", "Delete ?")==false){
			return;
		}
		
		TableItem item = tableDuongdung.getSelection()[0];
		Duongdung obj = (Duongdung)item.getData();
        logger.info(obj.toString());
        //
		obj.delete();
        listDataDuongdung.remove(obj);
        //
		reloadTableDuongdung();
	}

	protected void newItemDuongdung() {
        if(intTypeDlgDuongdung==TYPE_DLG_VIEW){
            return;
        }
        //
        if(DbHelper.checkPhanQuyen(DbHelper.INSERT, "duongdung")==false){
            logger.info("DON'T HAVE INSERT RIGHT");
            return;
        }
		//
		DuongdungDlg dlg = new DuongdungDlg(shell, 0);
        Duongdung obj = new Duongdung();
		dlg.setDuongdungDlgData(obj);
		dlg.open();
        listDataDuongdung.add(obj);
        //
		reloadTableDuongdung();
		//
	}
}
