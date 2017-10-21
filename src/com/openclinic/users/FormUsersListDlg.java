package com.openclinic.users;

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
import com.model.dao.Users;
import com.model.dao.UsersDlg;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.wb.swt.SWTResourceManager;

public class FormUsersListDlg extends Dialog {
	static Logger logger = LogManager.getLogger(FormUsersListDlg.class.getName());
	protected Object result;
	protected Shell shell;

	private class TableLabelProviderUsers extends LabelProvider implements ITableLabelProvider {
		public Image getColumnImage(Object element, int columnIndex) {
			return null;
		}
		public String getColumnText(Object element, int columnIndex) {
			if(element instanceof Users){
				return ((Users) element).getIndex(columnIndex);
			}
			return "";
		}
	}
	private static class ContentProviderUsers implements IStructuredContentProvider {
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
	private Table tableUsers;
	private TableViewer tableViewerUsers;
	private List<Users> listDataUsers;
	private Text textSearchUsers;
	private String textSearchUsersString;
	public Users objUsers = null;
	public int typeUsersDlg = TYPE_DLG_CHOOSEN; 
	public static final int TYPE_DLG_MANAGER = 0;
	public static final int TYPE_DLG_CHOOSEN = 1;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public FormUsersListDlg(Shell parent, int style) {
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
		shell.setImage(SWTResourceManager.getImage(UsersDlg.class, "/png/list-2x.png"));
		shell.setSize(610, 340);
		shell.setText("Users List View");
		shell.setLayout(new BorderLayout(0, 0));
		shell.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.keyCode==SWT.ESC){
					objUsers = null;
				}
			}
		});
        
        
        Composite compositeInShellUsers = new Composite(shell, SWT.NONE);
		compositeInShellUsers.setLayout(new BorderLayout(0, 0));
		compositeInShellUsers.setLayoutData(BorderLayout.CENTER);
        
		Composite compositeHeaderUsers = new Composite(compositeInShellUsers, SWT.NONE);
		compositeHeaderUsers.setLayoutData(BorderLayout.NORTH);
		compositeHeaderUsers.setLayout(new GridLayout(2, false));

		textSearchUsers = new Text(compositeHeaderUsers, SWT.BORDER);
		textSearchUsers.setFont(SWTResourceManager.getFont("Tahoma", 11, SWT.NORMAL));
		textSearchUsers.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.keyCode==13){
					reloadTableUsers();
				}
			}
		});
		
		Button btnNewButtonSearchUsers = new Button(compositeHeaderUsers, SWT.NONE);
		btnNewButtonSearchUsers.setImage(SWTResourceManager.getImage(UsersDlg.class, "/png/media-play-2x.png"));
		btnNewButtonSearchUsers.setFont(SWTResourceManager.getFont("Tahoma", 12, SWT.NORMAL));

		btnNewButtonSearchUsers.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				reloadTableUsers();
			}
		});
		GridData gd_btnNewButtonUsers = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnNewButtonUsers.widthHint = 87;
		btnNewButtonSearchUsers.setLayoutData(gd_btnNewButtonUsers);
		btnNewButtonSearchUsers.setText("Search");
        
		tableViewerUsers = new TableViewer(compositeInShellUsers, SWT.BORDER | SWT.FULL_SELECTION);
		tableUsers = tableViewerUsers.getTable();
		tableUsers.setFont(SWTResourceManager.getFont("Tahoma", 11, SWT.NORMAL));
		tableUsers.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.keyCode==SWT.F5){
					reloadTableUsers();
                }
                if(e.keyCode==SWT.F4){
					editTableUsers();
                }
				else if(e.keyCode==13){
					selectTableUsers();
				}
                else if(e.keyCode==SWT.DEL){
					deleteTableUsers();
				}
                else if(e.keyCode==SWT.F7){
					newItemUsers();
				}
			}
		});
        tableUsers.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				selectTableUsers();
			}
		});
        
		tableUsers.setLinesVisible(true);
		tableUsers.setHeaderVisible(true);
		tableUsers.setLayoutData(BorderLayout.CENTER);

		TableColumn tbTableColumnUsersMA_KHOA = new TableColumn(tableUsers, SWT.LEFT);
		tbTableColumnUsersMA_KHOA.setWidth(100);
		tbTableColumnUsersMA_KHOA.setText("MA_KHOA");

		TableColumn tbTableColumnUsersMACCHN = new TableColumn(tableUsers, SWT.LEFT);
		tbTableColumnUsersMACCHN.setWidth(100);
		tbTableColumnUsersMACCHN.setText("MACCHN");

		TableColumn tbTableColumnUsersTEN_NHANVIEN = new TableColumn(tableUsers, SWT.LEFT);
		tbTableColumnUsersTEN_NHANVIEN.setWidth(100);
		tbTableColumnUsersTEN_NHANVIEN.setText("TEN_NHANVIEN");

		TableColumn tbTableColumnUsersNGAYSINH = new TableColumn(tableUsers, SWT.LEFT);
		tbTableColumnUsersNGAYSINH.setWidth(100);
		tbTableColumnUsersNGAYSINH.setText("NGAYSINH");

		TableColumn tbTableColumnUsersDIA_CHI = new TableColumn(tableUsers, SWT.LEFT);
		tbTableColumnUsersDIA_CHI.setWidth(100);
		tbTableColumnUsersDIA_CHI.setText("DIA_CHI");

		TableColumn tbTableColumnUsersMA_CHUYENNGANH = new TableColumn(tableUsers, SWT.LEFT);
		tbTableColumnUsersMA_CHUYENNGANH.setWidth(100);
		tbTableColumnUsersMA_CHUYENNGANH.setText("MA_CHUYENNGANH");

		TableColumn tbTableColumnUsersU_NAME = new TableColumn(tableUsers, SWT.LEFT);
		tbTableColumnUsersU_NAME.setWidth(100);
		tbTableColumnUsersU_NAME.setText("U_NAME");

		TableColumn tbTableColumnUsersU_PASS = new TableColumn(tableUsers, SWT.LEFT);
		tbTableColumnUsersU_PASS.setWidth(100);
		tbTableColumnUsersU_PASS.setText("U_PASS");

		TableColumn tbTableColumnUsersLOAI = new TableColumn(tableUsers, SWT.LEFT);
		tbTableColumnUsersLOAI.setWidth(100);
		tbTableColumnUsersLOAI.setText("LOAI");

		TableColumn tbTableColumnUsersNGAYCAP_CCHN = new TableColumn(tableUsers, SWT.LEFT);
		tbTableColumnUsersNGAYCAP_CCHN.setWidth(100);
		tbTableColumnUsersNGAYCAP_CCHN.setText("NGAYCAP_CCHN");

		TableColumn tbTableColumnUsersNOICAP_CCHN = new TableColumn(tableUsers, SWT.LEFT);
		tbTableColumnUsersNOICAP_CCHN.setWidth(100);
		tbTableColumnUsersNOICAP_CCHN.setText("NOICAP_CCHN");

		TableColumn tbTableColumnUsersTUNGAY = new TableColumn(tableUsers, SWT.LEFT);
		tbTableColumnUsersTUNGAY.setWidth(100);
		tbTableColumnUsersTUNGAY.setText("TUNGAY");

		TableColumn tbTableColumnUsersDENNGAY = new TableColumn(tableUsers, SWT.LEFT);
		tbTableColumnUsersDENNGAY.setWidth(100);
		tbTableColumnUsersDENNGAY.setText("DENNGAY");

		TableColumn tbTableColumnUsersCHUNGCHI_KHAC = new TableColumn(tableUsers, SWT.LEFT);
		tbTableColumnUsersCHUNGCHI_KHAC.setWidth(100);
		tbTableColumnUsersCHUNGCHI_KHAC.setText("CHUNGCHI_KHAC");

		TableColumn tbTableColumnUsersSTS = new TableColumn(tableUsers, SWT.RIGHT);
		tbTableColumnUsersSTS.setWidth(100);
		tbTableColumnUsersSTS.setText("STS");

        Menu menuUsers = new Menu(tableUsers);
		tableUsers.setMenu(menuUsers);
		
		MenuItem mntmNewItemUsers = new MenuItem(menuUsers, SWT.NONE);
		mntmNewItemUsers.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				newItemUsers();
			}
		});
		mntmNewItemUsers.setImage(SWTResourceManager.getImage(FormUsersListDlg.class, "/png/arrow-circle-top-2x.png"));
		mntmNewItemUsers.setText("New");
		
		MenuItem mntmEditItemUsers = new MenuItem(menuUsers, SWT.NONE);
		mntmEditItemUsers.setImage(SWTResourceManager.getImage(FormUsersListDlg.class, "/png/wrench-2x.png"));
		mntmEditItemUsers.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				editTableUsers();
			}
		});
		mntmEditItemUsers.setText("Edit");
		
		MenuItem mntmQuyn = new MenuItem(menuUsers, SWT.NONE);
		mntmQuyn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				phanQuyen();
			}
		});
		mntmQuyn.setText("Quyền");
		mntmQuyn.setImage(SWTResourceManager.getImage(FormUsersListDlg.class, "/png/wrench-2x.png"));
		
		MenuItem mntmDeleteUsers = new MenuItem(menuUsers, SWT.NONE);
		mntmDeleteUsers.setImage(SWTResourceManager.getImage(FormUsersListDlg.class, "/png/circle-x-2x.png"));
		mntmDeleteUsers.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				deleteTableUsers();
			}
		});
		mntmDeleteUsers.setText("Delete");


		
		tableViewerUsers.setLabelProvider(new TableLabelProviderUsers());
		tableViewerUsers.setContentProvider(new ContentProviderUsers());
		tableViewerUsers.setInput(listDataUsers);
        //
        //
		loadDataUsers();
		//
        reloadTableUsers();
	}
    protected void phanQuyen() {
    	if(tableUsers.getSelectionCount()==0){
			return;
		}
		TableItem item = tableUsers.getSelection()[0];
		Users obj = (Users)item.getData();
        logger.info(obj.toString());
        //
        //
		FormPhanQuyenDlg dlg = new FormPhanQuyenDlg(shell, 0);
        dlg.setUsersDlgData(obj);
        dlg.open();
        //
        //
        reloadTableUsers();
	}

	public void setDataUsers(String textSearchString){
		this.textSearchUsersString = textSearchString;
	}
	private void loadDataUsers() {
		if(textSearchUsersString!=null){
			textSearchUsers.setText(textSearchUsersString);
		}
	}
	protected void reloadTableUsers() {
        if(DbHelper.checkPhanQuyen(DbHelper.READ, "users")==false){
			logger.info("DON'T HAVE READ RIGHT");
			return;
		}

    
        if(listDataUsers!=null){
            // 
            tableViewerUsers.setInput(listDataUsers);
            tableViewerUsers.refresh();
            //
            if(listDataUsers.size()==0){
                textSearchUsers.forceFocus();
            }
            else{
                tableUsers.forceFocus();
                tableUsers.setSelection(0);
            }
            return;
        }
		// Do search in the first time
		String searchString = textSearchUsers.getText().toLowerCase().trim();
		String sql = "select * from users WHERE STS<> "+DbHelper.DELETE_STATUS+" ";
		if(searchString.length()>0){
            sql += " and ( 0 ";
        sql += " or LOWER(MA_KHOA) like '%"+searchString+"%'";
        sql += " or LOWER(MACCHN) like '%"+searchString+"%'";
        sql += " or LOWER(TEN_NHANVIEN) like '%"+searchString+"%'";
        sql += " or LOWER(NGAYSINH) like '%"+searchString+"%'";
        sql += " or LOWER(DIA_CHI) like '%"+searchString+"%'";
        sql += " or LOWER(MA_CHUYENNGANH) like '%"+searchString+"%'";
        sql += " or LOWER(U_NAME) like '%"+searchString+"%'";
        sql += " or LOWER(U_PASS) like '%"+searchString+"%'";
        sql += " or LOWER(LOAI) like '%"+searchString+"%'";
        sql += " or LOWER(NGAYCAP_CCHN) like '%"+searchString+"%'";
        sql += " or LOWER(NOICAP_CCHN) like '%"+searchString+"%'";
        sql += " or LOWER(TUNGAY) like '%"+searchString+"%'";
        sql += " or LOWER(DENNGAY) like '%"+searchString+"%'";
        sql += " or LOWER(CHUNGCHI_KHAC) like '%"+searchString+"%'";
            sql += " )";
        }
		try  {
            logger.info(sql);
			Connection con = DbHelper.getSql2o();
			listDataUsers = con.createQuery(sql).executeAndFetch(Users.class);
	    }   
	    catch(Exception e){
	    	logger.error(e);
	    	MessageDialog.openError(shell, "Error", e.getMessage());
	    }
		// 
		tableViewerUsers.setInput(listDataUsers);
		tableViewerUsers.refresh();
        //
        if(listDataUsers.size()==0){
            textSearchUsers.forceFocus();
        }
        else{
            tableUsers.forceFocus();
            tableUsers.setSelection(0);
        }
	}
    
    protected void selectTableUsers() {
		if(tableUsers.getSelectionCount()==0){
			return;
		}
		TableItem item = tableUsers.getSelection()[0];
		Users obj = (Users)item.getData();
        logger.info(obj.toString());
        //
        //
    	if(typeUsersDlg==TYPE_DLG_CHOOSEN){
    		// Choosen and close dlg
    		objUsers = obj;
    		shell.close();
    	}
    	else{
            if(DbHelper.checkPhanQuyen(DbHelper.UPDATE, "users")==false){
                logger.info("DON'T HAVE UPDATE RIGHT");
                return;
            }
			UsersDlg dlg = new UsersDlg(shell, 0);
            dlg.setUsersDlgData(obj);
            dlg.open();
            //
            reloadTableUsers();
    	}
	}
    protected void editTableUsers() {
        if(DbHelper.checkPhanQuyen(DbHelper.UPDATE, "users")==false){
            logger.info("DON'T HAVE UPDATE RIGHT");
            return;
        }
    	if(tableUsers.getSelectionCount()==0){
			return;
		}
		TableItem item = tableUsers.getSelection()[0];
		Users obj = (Users)item.getData();
        logger.info(obj.toString());
        //
        //
		UsersDlg dlg = new UsersDlg(shell, 0);
        dlg.setUsersDlgData(obj);
        dlg.open();
        //
        reloadTableUsers();
	}
    protected void deleteTableUsers() {
        if(DbHelper.checkPhanQuyen(DbHelper.DELETE, "users")==false){
            logger.info("DON'T HAVE DELETE RIGHT");
            return;
        }
		if(tableUsers.getSelectionCount()==0){
			return;
		}
        if(MessageDialog.openConfirm(shell, "Confirm?", "Delete ?")==false){
			return;
		}
		
		TableItem item = tableUsers.getSelection()[0];
		Users obj = (Users)item.getData();
        logger.info(obj.toString());
        //
		obj.delete();
        listDataUsers.remove(obj);
        //
		reloadTableUsers();
	}

	protected void newItemUsers() {
        if(DbHelper.checkPhanQuyen(DbHelper.INSERT, "users")==false){
            logger.info("DON'T HAVE INSERT RIGHT");
            return;
        }
		//
		UsersDlg dlg = new UsersDlg(shell, 0);
        Users obj = new Users();
		dlg.setUsersDlgData(obj);
		dlg.open();
        listDataUsers.add(obj);
        //
		reloadTableUsers();
		//
	}
}
