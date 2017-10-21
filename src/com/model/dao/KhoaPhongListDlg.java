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
public class KhoaPhongListDlg extends Dialog {
	static Logger logger = LogManager.getLogger(KhoaPhongListDlg.class.getName());
	protected Object result;
	protected Shell shell;

	private class TableLabelProviderKhoaPhong extends LabelProvider implements ITableLabelProvider {
		public Image getColumnImage(Object element, int columnIndex) {
			return null;
		}
		public String getColumnText(Object element, int columnIndex) {
			if(element instanceof KhoaPhong){
				return ((KhoaPhong) element).getIndex(columnIndex);
			}
			return "";
		}
	}
	private static class ContentProviderKhoaPhong implements IStructuredContentProvider {
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
	private Table tableKhoaPhong;
	private TableViewer tableViewerKhoaPhong;
	private List<KhoaPhong> listDataKhoaPhong;
	private Text textSearchKhoaPhong;
	private String textSearchKhoaPhongString;
	public KhoaPhong objKhoaPhong = null;
	public int typeKhoaPhongDlg = TYPE_DLG_CHOOSEN; 
	public static final int TYPE_DLG_MANAGER = 0;
	public static final int TYPE_DLG_CHOOSEN = 1;

    public int intTypeDlgKhoaPhong;
	public static final int TYPE_DLG_VIEW = 1;
	public static final int TYPE_DLG_EDIT = 2;
	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public KhoaPhongListDlg(Shell parent, int style) {
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
		shell.setImage(SWTResourceManager.getImage(KhoaPhongDlg.class, "/png/list-2x.png"));
		shell.setSize(610, 340);
		shell.setText("KhoaPhong List View");
		shell.setLayout(new BorderLayout(0, 0));
		shell.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.keyCode==SWT.ESC){
					objKhoaPhong = null;
				}
			}
		});
        
        Composite compositeInShellKhoaPhong = new Composite(shell, SWT.NONE);
		compositeInShellKhoaPhong.setLayout(new BorderLayout(0, 0));
		compositeInShellKhoaPhong.setLayoutData(BorderLayout.CENTER);
        
		Composite compositeHeaderKhoaPhong = new Composite(compositeInShellKhoaPhong, SWT.NONE);
		compositeHeaderKhoaPhong.setLayoutData(BorderLayout.NORTH);
		compositeHeaderKhoaPhong.setLayout(new GridLayout(2, false));

		textSearchKhoaPhong = new Text(compositeHeaderKhoaPhong, SWT.BORDER);
		textSearchKhoaPhong.setFont(SWTResourceManager.getFont("Tahoma", 11, SWT.NORMAL));
		textSearchKhoaPhong.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.keyCode==13){
					reloadTableKhoaPhong();
				}
			}
		});
		
		Button btnNewButtonSearchKhoaPhong = new Button(compositeHeaderKhoaPhong, SWT.NONE);
		btnNewButtonSearchKhoaPhong.setImage(SWTResourceManager.getImage(KhoaPhongDlg.class, "/png/media-play-2x.png"));
		btnNewButtonSearchKhoaPhong.setFont(SWTResourceManager.getFont("Tahoma", 12, SWT.NORMAL));

		btnNewButtonSearchKhoaPhong.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				reloadTableKhoaPhong();
			}
		});
		GridData gd_btnNewButtonKhoaPhong = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnNewButtonKhoaPhong.widthHint = 87;
		btnNewButtonSearchKhoaPhong.setLayoutData(gd_btnNewButtonKhoaPhong);
		btnNewButtonSearchKhoaPhong.setText("Search");
        
		tableViewerKhoaPhong = new TableViewer(compositeInShellKhoaPhong, SWT.BORDER | SWT.FULL_SELECTION);
		tableKhoaPhong = tableViewerKhoaPhong.getTable();
		tableKhoaPhong.setFont(SWTResourceManager.getFont("Tahoma", 11, SWT.NORMAL));
		tableKhoaPhong.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.keyCode==SWT.F5){
					reloadTableKhoaPhong();
                }
                if(e.keyCode==SWT.F4){
					editTableKhoaPhong();
                }
				else if(e.keyCode==13){
					selectTableKhoaPhong();
				}
                else if(e.keyCode==SWT.DEL){
					deleteTableKhoaPhong();
				}
                else if(e.keyCode==SWT.F7){
					newItemKhoaPhong();
				}
			}
		});
        tableKhoaPhong.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				selectTableKhoaPhong();
			}
		});
        
		tableKhoaPhong.setLinesVisible(true);
		tableKhoaPhong.setHeaderVisible(true);
		tableKhoaPhong.setLayoutData(BorderLayout.CENTER);

		TableColumn tbTableColumnKhoaPhongKP_MABH = new TableColumn(tableKhoaPhong, SWT.LEFT);
		tbTableColumnKhoaPhongKP_MABH.setWidth(100);
		tbTableColumnKhoaPhongKP_MABH.setText("KP_MABH");

		TableColumn tbTableColumnKhoaPhongKP_MAKHOA = new TableColumn(tableKhoaPhong, SWT.LEFT);
		tbTableColumnKhoaPhongKP_MAKHOA.setWidth(100);
		tbTableColumnKhoaPhongKP_MAKHOA.setText("KP_MAKHOA");

		TableColumn tbTableColumnKhoaPhongKP_NAME = new TableColumn(tableKhoaPhong, SWT.LEFT);
		tbTableColumnKhoaPhongKP_NAME.setWidth(100);
		tbTableColumnKhoaPhongKP_NAME.setText("KP_NAME");

		TableColumn tbTableColumnKhoaPhongKP_SOPHONG = new TableColumn(tableKhoaPhong, SWT.LEFT);
		tbTableColumnKhoaPhongKP_SOPHONG.setWidth(100);
		tbTableColumnKhoaPhongKP_SOPHONG.setText("KP_SOPHONG");

        Menu menuKhoaPhong = new Menu(tableKhoaPhong);
		tableKhoaPhong.setMenu(menuKhoaPhong);
		
		MenuItem mntmNewItemKhoaPhong = new MenuItem(menuKhoaPhong, SWT.NONE);
		mntmNewItemKhoaPhong.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				newItemKhoaPhong();
			}
		});
		mntmNewItemKhoaPhong.setImage(SWTResourceManager.getImage(KhoaPhongDlg.class, "/png/arrow-circle-top-2x.png"));
		mntmNewItemKhoaPhong.setText("New");
		
		MenuItem mntmEditItemKhoaPhong = new MenuItem(menuKhoaPhong, SWT.NONE);
		mntmEditItemKhoaPhong.setImage(SWTResourceManager.getImage(KhoaPhongDlg.class, "/png/wrench-2x.png"));
		mntmEditItemKhoaPhong.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				editTableKhoaPhong();
			}
		});
		mntmEditItemKhoaPhong.setText("Edit");
		
		MenuItem mntmDeleteKhoaPhong = new MenuItem(menuKhoaPhong, SWT.NONE);
		mntmDeleteKhoaPhong.setImage(SWTResourceManager.getImage(KhoaPhongDlg.class, "/png/circle-x-2x.png"));
		mntmDeleteKhoaPhong.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				deleteTableKhoaPhong();
			}
		});
		mntmDeleteKhoaPhong.setText("Delete");

		tableViewerKhoaPhong.setLabelProvider(new TableLabelProviderKhoaPhong());
		tableViewerKhoaPhong.setContentProvider(new ContentProviderKhoaPhong());
		tableViewerKhoaPhong.setInput(listDataKhoaPhong);
        //
        //
		loadDataKhoaPhong();
		//
        reloadTableKhoaPhong();
	}
    public void setDataKhoaPhong(String textSearchString){
		this.textSearchKhoaPhongString = textSearchString;
	}
	private void loadDataKhoaPhong() {
		if(textSearchKhoaPhongString!=null){
			textSearchKhoaPhong.setText(textSearchKhoaPhongString);
		}
	}
	protected void reloadTableKhoaPhong() {
        if(DbHelper.checkPhanQuyen(DbHelper.READ, "khoa_phong")==false){
			logger.info("DON'T HAVE READ RIGHT");
			return;
		}
    
        if(listDataKhoaPhong!=null){
            // 
            tableViewerKhoaPhong.setInput(listDataKhoaPhong);
            tableViewerKhoaPhong.refresh();
            //
            if(listDataKhoaPhong.size()==0){
                textSearchKhoaPhong.forceFocus();
            }
            else{
                tableKhoaPhong.forceFocus();
                tableKhoaPhong.setSelection(0);
            }
            return;
        }
		// Do search in the first time
		String searchString = textSearchKhoaPhong.getText().toLowerCase().trim();
		String sql = "select * from khoa_phong WHERE STS<> "+DbHelper.DELETE_STATUS+" ";
		if(searchString.length()>0){
            sql += " and ( 0 ";
        sql += " or LOWER(KP_MABH) like '%"+searchString+"%'";
        sql += " or LOWER(KP_MAKHOA) like '%"+searchString+"%'";
        sql += " or LOWER(KP_NAME) like '%"+searchString+"%'";
        sql += " or LOWER(KP_SOPHONG) like '%"+searchString+"%'";
            sql += " )";
        }
		try  {
            logger.info(sql);
			Connection con = DbHelper.getSql2o();
			listDataKhoaPhong = con.createQuery(sql).executeAndFetch(KhoaPhong.class);
	    }   
	    catch(Exception e){
	    	logger.error(e);
	    	MessageDialog.openError(shell, "Error", e.getMessage());
	    }
		// 
		tableViewerKhoaPhong.setInput(listDataKhoaPhong);
		tableViewerKhoaPhong.refresh();
        //
        if(listDataKhoaPhong.size()==0){
            textSearchKhoaPhong.forceFocus();
        }
        else{
            tableKhoaPhong.forceFocus();
            tableKhoaPhong.setSelection(0);
        }
	}
    
    protected void selectTableKhoaPhong() {
		if(tableKhoaPhong.getSelectionCount()==0){
			return;
		}
		TableItem item = tableKhoaPhong.getSelection()[0];
		KhoaPhong obj = (KhoaPhong)item.getData();
        logger.info(obj.toString());
        //
        //
    	if(typeKhoaPhongDlg==TYPE_DLG_CHOOSEN){
    		// Choosen and close dlg
    		objKhoaPhong = obj;
    		shell.close();
    	}
    	else{
            if(DbHelper.checkPhanQuyen(DbHelper.UPDATE, "khoa_phong")==false){
                logger.info("DON'T HAVE UPDATE RIGHT");
                return;
            }
            if(intTypeDlgKhoaPhong==TYPE_DLG_VIEW){
                return;
            }
			KhoaPhongDlg dlg = new KhoaPhongDlg(shell, 0);
            dlg.setKhoaPhongDlgData(obj);
            dlg.open();
            //
            reloadTableKhoaPhong();
    	}
	}
    protected void editTableKhoaPhong() {
        if(intTypeDlgKhoaPhong==TYPE_DLG_VIEW){
            return;
        }
        if(DbHelper.checkPhanQuyen(DbHelper.UPDATE, "khoa_phong")==false){
            logger.info("DON'T HAVE UPDATE RIGHT");
            return;
        }
    	if(tableKhoaPhong.getSelectionCount()==0){
			return;
		}
		TableItem item = tableKhoaPhong.getSelection()[0];
		KhoaPhong obj = (KhoaPhong)item.getData();
        logger.info(obj.toString());
        //
        //
		KhoaPhongDlg dlg = new KhoaPhongDlg(shell, 0);
        dlg.setKhoaPhongDlgData(obj);
        dlg.open();
        //
        reloadTableKhoaPhong();
	}
    protected void deleteTableKhoaPhong() {
        if(intTypeDlgKhoaPhong==TYPE_DLG_VIEW){
            return;
        }
        //
        if(DbHelper.checkPhanQuyen(DbHelper.DELETE, "khoa_phong")==false){
            logger.info("DON'T HAVE DELETE RIGHT");
            return;
        }
		if(tableKhoaPhong.getSelectionCount()==0){
			return;
		}
        if(MessageDialog.openConfirm(shell, "Confirm?", "Delete ?")==false){
			return;
		}
		
		TableItem item = tableKhoaPhong.getSelection()[0];
		KhoaPhong obj = (KhoaPhong)item.getData();
        logger.info(obj.toString());
        //
		obj.delete();
        listDataKhoaPhong.remove(obj);
        //
		reloadTableKhoaPhong();
	}

	protected void newItemKhoaPhong() {
        if(intTypeDlgKhoaPhong==TYPE_DLG_VIEW){
            return;
        }
        //
        if(DbHelper.checkPhanQuyen(DbHelper.INSERT, "khoa_phong")==false){
            logger.info("DON'T HAVE INSERT RIGHT");
            return;
        }
		//
		KhoaPhongDlg dlg = new KhoaPhongDlg(shell, 0);
        KhoaPhong obj = new KhoaPhong();
		dlg.setKhoaPhongDlgData(obj);
		dlg.open();
        listDataKhoaPhong.add(obj);
        //
		reloadTableKhoaPhong();
		//
	}
}
