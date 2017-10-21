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
public class KetquaXnListDlg extends Dialog {
	static Logger logger = LogManager.getLogger(KetquaXnListDlg.class.getName());
	protected Object result;
	protected Shell shell;

	private class TableLabelProviderKetquaXn extends LabelProvider implements ITableLabelProvider {
		public Image getColumnImage(Object element, int columnIndex) {
			return null;
		}
		public String getColumnText(Object element, int columnIndex) {
			if(element instanceof KetquaXn){
				return ((KetquaXn) element).getIndex(columnIndex);
			}
			return "";
		}
	}
	private static class ContentProviderKetquaXn implements IStructuredContentProvider {
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
	private Table tableKetquaXn;
	private TableViewer tableViewerKetquaXn;
	private List<KetquaXn> listDataKetquaXn;
	private Text textSearchKetquaXn;
	private String textSearchKetquaXnString;
	public KetquaXn objKetquaXn = null;
	public int typeKetquaXnDlg = TYPE_DLG_CHOOSEN; 
	public static final int TYPE_DLG_MANAGER = 0;
	public static final int TYPE_DLG_CHOOSEN = 1;

    public int intTypeDlgKetquaXn;
	public static final int TYPE_DLG_VIEW = 1;
	public static final int TYPE_DLG_EDIT = 2;
	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public KetquaXnListDlg(Shell parent, int style) {
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
		shell.setImage(SWTResourceManager.getImage(KetquaXnDlg.class, "/png/list-2x.png"));
		shell.setSize(610, 340);
		shell.setText("KetquaXn List View");
		shell.setLayout(new BorderLayout(0, 0));
		shell.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.keyCode==SWT.ESC){
					objKetquaXn = null;
				}
			}
		});
        
        Composite compositeInShellKetquaXn = new Composite(shell, SWT.NONE);
		compositeInShellKetquaXn.setLayout(new BorderLayout(0, 0));
		compositeInShellKetquaXn.setLayoutData(BorderLayout.CENTER);
        
		Composite compositeHeaderKetquaXn = new Composite(compositeInShellKetquaXn, SWT.NONE);
		compositeHeaderKetquaXn.setLayoutData(BorderLayout.NORTH);
		compositeHeaderKetquaXn.setLayout(new GridLayout(2, false));

		textSearchKetquaXn = new Text(compositeHeaderKetquaXn, SWT.BORDER);
		textSearchKetquaXn.setFont(SWTResourceManager.getFont("Tahoma", 11, SWT.NORMAL));
		textSearchKetquaXn.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.keyCode==13){
					reloadTableKetquaXn();
				}
			}
		});
		
		Button btnNewButtonSearchKetquaXn = new Button(compositeHeaderKetquaXn, SWT.NONE);
		btnNewButtonSearchKetquaXn.setImage(SWTResourceManager.getImage(KetquaXnDlg.class, "/png/media-play-2x.png"));
		btnNewButtonSearchKetquaXn.setFont(SWTResourceManager.getFont("Tahoma", 12, SWT.NORMAL));

		btnNewButtonSearchKetquaXn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				reloadTableKetquaXn();
			}
		});
		GridData gd_btnNewButtonKetquaXn = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnNewButtonKetquaXn.widthHint = 87;
		btnNewButtonSearchKetquaXn.setLayoutData(gd_btnNewButtonKetquaXn);
		btnNewButtonSearchKetquaXn.setText("Search");
        
		tableViewerKetquaXn = new TableViewer(compositeInShellKetquaXn, SWT.BORDER | SWT.FULL_SELECTION);
		tableKetquaXn = tableViewerKetquaXn.getTable();
		tableKetquaXn.setFont(SWTResourceManager.getFont("Tahoma", 11, SWT.NORMAL));
		tableKetquaXn.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.keyCode==SWT.F5){
					reloadTableKetquaXn();
                }
                if(e.keyCode==SWT.F4){
					editTableKetquaXn();
                }
				else if(e.keyCode==13){
					selectTableKetquaXn();
				}
                else if(e.keyCode==SWT.DEL){
					deleteTableKetquaXn();
				}
                else if(e.keyCode==SWT.F7){
					newItemKetquaXn();
				}
			}
		});
        tableKetquaXn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				selectTableKetquaXn();
			}
		});
        
		tableKetquaXn.setLinesVisible(true);
		tableKetquaXn.setHeaderVisible(true);
		tableKetquaXn.setLayoutData(BorderLayout.CENTER);

		TableColumn tbTableColumnKetquaXnCS_ID = new TableColumn(tableKetquaXn, SWT.RIGHT);
		tbTableColumnKetquaXnCS_ID.setWidth(100);
		tbTableColumnKetquaXnCS_ID.setText("CS_ID");

		TableColumn tbTableColumnKetquaXnDV_ID = new TableColumn(tableKetquaXn, SWT.RIGHT);
		tbTableColumnKetquaXnDV_ID.setWidth(100);
		tbTableColumnKetquaXnDV_ID.setText("DV_ID");

		TableColumn tbTableColumnKetquaXnMA_DVKT = new TableColumn(tableKetquaXn, SWT.LEFT);
		tbTableColumnKetquaXnMA_DVKT.setWidth(100);
		tbTableColumnKetquaXnMA_DVKT.setText("MA_DVKT");

		TableColumn tbTableColumnKetquaXnTEN_DVKT = new TableColumn(tableKetquaXn, SWT.LEFT);
		tbTableColumnKetquaXnTEN_DVKT.setWidth(100);
		tbTableColumnKetquaXnTEN_DVKT.setText("TEN_DVKT");

		TableColumn tbTableColumnKetquaXnCS_NAME = new TableColumn(tableKetquaXn, SWT.LEFT);
		tbTableColumnKetquaXnCS_NAME.setWidth(100);
		tbTableColumnKetquaXnCS_NAME.setText("CS_NAME");

		TableColumn tbTableColumnKetquaXnCS_VALUE = new TableColumn(tableKetquaXn, SWT.LEFT);
		tbTableColumnKetquaXnCS_VALUE.setWidth(100);
		tbTableColumnKetquaXnCS_VALUE.setText("CS_VALUE");

		TableColumn tbTableColumnKetquaXnCS_RANGE1 = new TableColumn(tableKetquaXn, SWT.LEFT);
		tbTableColumnKetquaXnCS_RANGE1.setWidth(100);
		tbTableColumnKetquaXnCS_RANGE1.setText("CS_RANGE1");

		TableColumn tbTableColumnKetquaXnCS_RANGE2 = new TableColumn(tableKetquaXn, SWT.LEFT);
		tbTableColumnKetquaXnCS_RANGE2.setWidth(100);
		tbTableColumnKetquaXnCS_RANGE2.setText("CS_RANGE2");

		TableColumn tbTableColumnKetquaXnCS_DEFAULT = new TableColumn(tableKetquaXn, SWT.LEFT);
		tbTableColumnKetquaXnCS_DEFAULT.setWidth(100);
		tbTableColumnKetquaXnCS_DEFAULT.setText("CS_DEFAULT");

		TableColumn tbTableColumnKetquaXnIMAGE_URL = new TableColumn(tableKetquaXn, SWT.LEFT);
		tbTableColumnKetquaXnIMAGE_URL.setWidth(100);
		tbTableColumnKetquaXnIMAGE_URL.setText("IMAGE_URL");

		TableColumn tbTableColumnKetquaXnMA_MAY = new TableColumn(tableKetquaXn, SWT.LEFT);
		tbTableColumnKetquaXnMA_MAY.setWidth(100);
		tbTableColumnKetquaXnMA_MAY.setText("MA_MAY");

		TableColumn tbTableColumnKetquaXnMO_TA = new TableColumn(tableKetquaXn, SWT.LEFT);
		tbTableColumnKetquaXnMO_TA.setWidth(100);
		tbTableColumnKetquaXnMO_TA.setText("MO_TA");

		TableColumn tbTableColumnKetquaXnKET_LUAN = new TableColumn(tableKetquaXn, SWT.LEFT);
		tbTableColumnKetquaXnKET_LUAN.setWidth(100);
		tbTableColumnKetquaXnKET_LUAN.setText("KET_LUAN");

		TableColumn tbTableColumnKetquaXnSTS = new TableColumn(tableKetquaXn, SWT.RIGHT);
		tbTableColumnKetquaXnSTS.setWidth(100);
		tbTableColumnKetquaXnSTS.setText("STS");

        Menu menuKetquaXn = new Menu(tableKetquaXn);
		tableKetquaXn.setMenu(menuKetquaXn);
		
		MenuItem mntmNewItemKetquaXn = new MenuItem(menuKetquaXn, SWT.NONE);
		mntmNewItemKetquaXn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				newItemKetquaXn();
			}
		});
		mntmNewItemKetquaXn.setImage(SWTResourceManager.getImage(KetquaXnDlg.class, "/png/arrow-circle-top-2x.png"));
		mntmNewItemKetquaXn.setText("New");
		
		MenuItem mntmEditItemKetquaXn = new MenuItem(menuKetquaXn, SWT.NONE);
		mntmEditItemKetquaXn.setImage(SWTResourceManager.getImage(KetquaXnDlg.class, "/png/wrench-2x.png"));
		mntmEditItemKetquaXn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				editTableKetquaXn();
			}
		});
		mntmEditItemKetquaXn.setText("Edit");
		
		MenuItem mntmDeleteKetquaXn = new MenuItem(menuKetquaXn, SWT.NONE);
		mntmDeleteKetquaXn.setImage(SWTResourceManager.getImage(KetquaXnDlg.class, "/png/circle-x-2x.png"));
		mntmDeleteKetquaXn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				deleteTableKetquaXn();
			}
		});
		mntmDeleteKetquaXn.setText("Delete");

		tableViewerKetquaXn.setLabelProvider(new TableLabelProviderKetquaXn());
		tableViewerKetquaXn.setContentProvider(new ContentProviderKetquaXn());
		tableViewerKetquaXn.setInput(listDataKetquaXn);
        //
        //
		loadDataKetquaXn();
		//
        reloadTableKetquaXn();
	}
    public void setDataKetquaXn(String textSearchString){
		this.textSearchKetquaXnString = textSearchString;
	}
	private void loadDataKetquaXn() {
		if(textSearchKetquaXnString!=null){
			textSearchKetquaXn.setText(textSearchKetquaXnString);
		}
	}
	protected void reloadTableKetquaXn() {
        if(DbHelper.checkPhanQuyen(DbHelper.READ, "ketqua_xn")==false){
			logger.info("DON'T HAVE READ RIGHT");
			return;
		}
    
        if(listDataKetquaXn!=null){
            // 
            tableViewerKetquaXn.setInput(listDataKetquaXn);
            tableViewerKetquaXn.refresh();
            //
            if(listDataKetquaXn.size()==0){
                textSearchKetquaXn.forceFocus();
            }
            else{
                tableKetquaXn.forceFocus();
                tableKetquaXn.setSelection(0);
            }
            return;
        }
		// Do search in the first time
		String searchString = textSearchKetquaXn.getText().toLowerCase().trim();
		String sql = "select * from ketqua_xn WHERE STS<> "+DbHelper.DELETE_STATUS+" ";
		if(searchString.length()>0){
            sql += " and ( 0 ";
        sql += " or LOWER(MA_DVKT) like '%"+searchString+"%'";
        sql += " or LOWER(TEN_DVKT) like '%"+searchString+"%'";
        sql += " or LOWER(CS_NAME) like '%"+searchString+"%'";
        sql += " or LOWER(CS_VALUE) like '%"+searchString+"%'";
        sql += " or LOWER(CS_RANGE1) like '%"+searchString+"%'";
        sql += " or LOWER(CS_RANGE2) like '%"+searchString+"%'";
        sql += " or LOWER(CS_DEFAULT) like '%"+searchString+"%'";
        sql += " or LOWER(IMAGE_URL) like '%"+searchString+"%'";
        sql += " or LOWER(MA_MAY) like '%"+searchString+"%'";
        sql += " or LOWER(MO_TA) like '%"+searchString+"%'";
        sql += " or LOWER(KET_LUAN) like '%"+searchString+"%'";
            sql += " )";
        }
		try  {
            logger.info(sql);
			Connection con = DbHelper.getSql2o();
			listDataKetquaXn = con.createQuery(sql).executeAndFetch(KetquaXn.class);
	    }   
	    catch(Exception e){
	    	logger.error(e);
	    	MessageDialog.openError(shell, "Error", e.getMessage());
	    }
		// 
		tableViewerKetquaXn.setInput(listDataKetquaXn);
		tableViewerKetquaXn.refresh();
        //
        if(listDataKetquaXn.size()==0){
            textSearchKetquaXn.forceFocus();
        }
        else{
            tableKetquaXn.forceFocus();
            tableKetquaXn.setSelection(0);
        }
	}
    
    protected void selectTableKetquaXn() {
		if(tableKetquaXn.getSelectionCount()==0){
			return;
		}
		TableItem item = tableKetquaXn.getSelection()[0];
		KetquaXn obj = (KetquaXn)item.getData();
        logger.info(obj.toString());
        //
        //
    	if(typeKetquaXnDlg==TYPE_DLG_CHOOSEN){
    		// Choosen and close dlg
    		objKetquaXn = obj;
    		shell.close();
    	}
    	else{
            if(DbHelper.checkPhanQuyen(DbHelper.UPDATE, "ketqua_xn")==false){
                logger.info("DON'T HAVE UPDATE RIGHT");
                return;
            }
            if(intTypeDlgKetquaXn==TYPE_DLG_VIEW){
                return;
            }
			KetquaXnDlg dlg = new KetquaXnDlg(shell, 0);
            dlg.setKetquaXnDlgData(obj);
            dlg.open();
            //
            reloadTableKetquaXn();
    	}
	}
    protected void editTableKetquaXn() {
        if(intTypeDlgKetquaXn==TYPE_DLG_VIEW){
            return;
        }
        if(DbHelper.checkPhanQuyen(DbHelper.UPDATE, "ketqua_xn")==false){
            logger.info("DON'T HAVE UPDATE RIGHT");
            return;
        }
    	if(tableKetquaXn.getSelectionCount()==0){
			return;
		}
		TableItem item = tableKetquaXn.getSelection()[0];
		KetquaXn obj = (KetquaXn)item.getData();
        logger.info(obj.toString());
        //
        //
		KetquaXnDlg dlg = new KetquaXnDlg(shell, 0);
        dlg.setKetquaXnDlgData(obj);
        dlg.open();
        //
        reloadTableKetquaXn();
	}
    protected void deleteTableKetquaXn() {
        if(intTypeDlgKetquaXn==TYPE_DLG_VIEW){
            return;
        }
        //
        if(DbHelper.checkPhanQuyen(DbHelper.DELETE, "ketqua_xn")==false){
            logger.info("DON'T HAVE DELETE RIGHT");
            return;
        }
		if(tableKetquaXn.getSelectionCount()==0){
			return;
		}
        if(MessageDialog.openConfirm(shell, "Confirm?", "Delete ?")==false){
			return;
		}
		
		TableItem item = tableKetquaXn.getSelection()[0];
		KetquaXn obj = (KetquaXn)item.getData();
        logger.info(obj.toString());
        //
		obj.delete();
        listDataKetquaXn.remove(obj);
        //
		reloadTableKetquaXn();
	}

	protected void newItemKetquaXn() {
        if(intTypeDlgKetquaXn==TYPE_DLG_VIEW){
            return;
        }
        //
        if(DbHelper.checkPhanQuyen(DbHelper.INSERT, "ketqua_xn")==false){
            logger.info("DON'T HAVE INSERT RIGHT");
            return;
        }
		//
		KetquaXnDlg dlg = new KetquaXnDlg(shell, 0);
        KetquaXn obj = new KetquaXn();
		dlg.setKetquaXnDlgData(obj);
		dlg.open();
        listDataKetquaXn.add(obj);
        //
		reloadTableKetquaXn();
		//
	}
}
