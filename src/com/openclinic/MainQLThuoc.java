package com.openclinic;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IColorProvider;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.internal.win32.OS;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;
import org.sql2o.Connection;

import swing2swt.layout.BorderLayout;

import com.DbHelper;
import com.model.dao.KhamBenh;
import com.model.dao.KhamBenhDlg;
import com.model.dao.NhapThuoc;
import com.model.dao.NhapThuocDlg;
import com.model.dao.UsersListDlg;
import com.model.dao.VendorListDlg;
import com.openclinic.khambenh.FormThuocChitietListDlg;
import com.openclinic.nhapthuoc.FormNhapThuocDlg;
import com.openclinic.users.FormUsersListDlg;
import com.openclinic.utils.Utils;


public class MainQLThuoc extends Dialog{
	static Logger logger = LogManager.getLogger(NhapThuocDlg.class.getName());
	
	private class TableLabelProviderKhamBenh extends LabelProvider implements ITableLabelProvider, IColorProvider {
		public Image getColumnImage(Object element, int columnIndex) {
			return null;
		}
		public String getColumnText(Object element, int columnIndex) {
			if(element instanceof KhamBenh){
				//return ((KhamBenh) element).getIndex(columnIndex);
				KhamBenh obj = (KhamBenh)element;
				if(columnIndex==0){
					return ""+obj.STT;
				}
				else if(columnIndex==1){
					return ""+obj.MA_LK;
				}
				else if(columnIndex==2){
					return ""+obj.TEN_BENH_NHAN;
				}
				else if(columnIndex==3){
					return ""+  Utils.getKieuThanhToan(obj.KIEU_TT);
				}
				else if(columnIndex==4){
					return ""+obj.MUC_HUONG+"%";
				}
				else if(columnIndex==5){
					return ""+ Utils.getMoneyDefault( obj.T_THUOC );
				}
				else if(columnIndex==6){
					return ""+ Utils.getTinhTrangPhieuKham(obj.STS);
				}
				return "";
			}
			return "";
		}
		@Override
		public Color getForeground(Object element) {
			// TODO Auto-generated method stub
			return null;
		}
		@Override
		public Color getBackground(Object element) {
			if(element instanceof KhamBenh){
				KhamBenh obj = (KhamBenh)element;
				return Utils.getTinhTrangPhieuKhamColor(obj.STS);
			}
			return null;
		}
	}
	private static class ContentProviderKhamBenh implements IStructuredContentProvider {
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
	private Table tableKhamBenh;
	private TableViewer tableViewerKhamBenh;
	private List<KhamBenh> listDataKhamBenh = new ArrayList();
	private Text textSearchKhamBenh;
	private static final void toggleAlwaysOnTop(Shell shell, boolean isOnTop){
	    long handle = shell.handle;
	    Point location = shell.getLocation();
	    Point dimension = shell.getSize();
	    OS.SetWindowPos((int) handle, isOnTop ? OS.HWND_TOPMOST : OS.HWND_NOTOPMOST,location.x, location.y, dimension.x, dimension.y, 0);
	}
	public MainQLThuoc(Shell parent, int style) {
		super(parent, SWT.DIALOG_TRIM | SWT.MIN | SWT.MAX);
		// TODO Auto-generated constructor stub
	}

	protected Shell shell;

	
	private class TableLabelProviderNhapThuoc extends LabelProvider implements ITableLabelProvider {
		public Image getColumnImage(Object element, int columnIndex) {
			return null;
		}
		public String getColumnText(Object element, int columnIndex) {
			if(element instanceof NhapThuoc){
				//return ((NhapThuoc) element).getIndex(columnIndex);
				NhapThuoc obj = (NhapThuoc)element;
				if(columnIndex==0){
					return Utils.getDatetimeDefault(obj.NGAY_NHAP);
				}
				else if(columnIndex==1){
					return obj.SO_HOA_DON;
				}
				else if(columnIndex==2){
					return obj.TENKHO;
				}
				else if(columnIndex==3){
					return Utils.getMoneyDefault( obj.TONGCONG);
				}
				else if(columnIndex==4){
					return Utils.getPaymentDefault( obj.STS);
				}
				else if(columnIndex==5){
					return obj.VENDOR_NAME;
				}
			}
			return "";
		}
	}
	private static class ContentProviderNhapThuoc implements IStructuredContentProvider {
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
	private Table tableNhapThuoc;
	private TableViewer tableViewerNhapThuoc;
	private List<NhapThuoc> listDataNhapThuoc = new ArrayList();
	private Text textSearchNhapThuoc;
	private TabFolder tabFolder;
	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		
		shell.setImage(SWTResourceManager.getImage(MainQLThuoc.class, "/png/beaker-3x.png"));
		shell.setSize(830, 543);
		shell.setText(Main.TITLE + " -LOGIN: "+DbHelper.currentSessionUserId.TEN_NHANVIEN +" (" + DbHelper.currentSessionUserId.MACCHN  + ")");
		shell.setLayout(new BorderLayout(0, 0));
		shell.setActive();
		
		Menu menu = new Menu(shell, SWT.BAR);
		shell.setMenuBar(menu);
		
		MenuItem mntmNewItem = new MenuItem(menu, SWT.NONE);
		mntmNewItem.setText("New Item");
		
		MenuItem mntmNewSubmenu = new MenuItem(menu, SWT.CASCADE);
		mntmNewSubmenu.setText("Admin");
		
		Menu menu_1 = new Menu(mntmNewSubmenu);
		mntmNewSubmenu.setMenu(menu_1);
		
		MenuItem mntmNhCc = new MenuItem(menu_1, SWT.NONE);
		mntmNhCc.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				VendorListDlg dlg = new VendorListDlg(shell, 0);
				dlg.open();
			}
		});
		mntmNhCc.setText("Nhà CC");
		
		MenuItem mntmUser = new MenuItem(menu_1, SWT.NONE);
		mntmUser.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				FormUsersListDlg dlg = new FormUsersListDlg(shell, 0);
				dlg.open();
			}
		});
		mntmUser.setText("User");
		
		tabFolder = new TabFolder(shell, SWT.NONE);
		
		TabItem tbtmKhoThuc = new TabItem(tabFolder, 0);
		tbtmKhoThuc.setText("Phát Thuốc");
		
		Composite compositeInShellKhamBenh = new Composite(tabFolder, SWT.NONE);
		tbtmKhoThuc.setControl(compositeInShellKhamBenh);
		compositeInShellKhamBenh.setLayout(new BorderLayout(0, 0));

		Composite compositeHeaderKhamBenh = new Composite(compositeInShellKhamBenh, SWT.NONE);
		compositeHeaderKhamBenh.setLayoutData(BorderLayout.NORTH);
		compositeHeaderKhamBenh.setLayout(new GridLayout(2, false));

		textSearchKhamBenh = new Text(compositeHeaderKhamBenh, SWT.BORDER);
		GridData gd_textSearchKhamBenh = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_textSearchKhamBenh.widthHint = 328;
		textSearchKhamBenh.setLayoutData(gd_textSearchKhamBenh);
		textSearchKhamBenh.setFont(SWTResourceManager.getFont("Tahoma", 11, SWT.NORMAL));
		textSearchKhamBenh.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.keyCode==13){
					reloadTableKhamBenh();
				}
			}
		});
		
		Button btnNewButtonSearchKhamBenh = new Button(compositeHeaderKhamBenh, SWT.NONE);
		btnNewButtonSearchKhamBenh.setImage(SWTResourceManager.getImage(KhamBenhDlg.class, "/png/media-play-2x.png"));
		btnNewButtonSearchKhamBenh.setFont(SWTResourceManager.getFont("Tahoma", 12, SWT.NORMAL));

		btnNewButtonSearchKhamBenh.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				reloadTableKhamBenh();
			}
		});
		GridData gd_btnNewButtonKhamBenh = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnNewButtonKhamBenh.widthHint = 122;
		btnNewButtonSearchKhamBenh.setLayoutData(gd_btnNewButtonKhamBenh);
		btnNewButtonSearchKhamBenh.setText("Tìm kiếm");
        
		tableViewerKhamBenh = new TableViewer(compositeInShellKhamBenh, SWT.BORDER | SWT.FULL_SELECTION);
		tableKhamBenh = tableViewerKhamBenh.getTable();
		tableKhamBenh.setFont(SWTResourceManager.getFont("Tahoma", 11, SWT.NORMAL));
		tableKhamBenh.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.keyCode==SWT.F5){
					reloadTableKhamBenh();
                }
				else if(e.keyCode==13){
					selectTableKhamBenh();
				}
                else if(e.keyCode==SWT.DEL){
					deleteTableKhamBenh();
				}
                else if(e.keyCode==SWT.F7){
					newItemKhamBenh();
				}
			}
		});
        tableKhamBenh.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				selectTableKhamBenh();
			}
		});
        
		tableKhamBenh.setLinesVisible(true);
		tableKhamBenh.setHeaderVisible(true);
		tableKhamBenh.setLayoutData(BorderLayout.CENTER);

		TableColumn tbTableColumnSTT = new TableColumn(tableKhamBenh, SWT.RIGHT);
		tbTableColumnSTT.setWidth(51);
		tbTableColumnSTT.setText("STT");
		
		TableColumn tblclmnMLk = new TableColumn(tableKhamBenh, SWT.RIGHT);
		tblclmnMLk.setWidth(94);
		tblclmnMLk.setText("MÃ LK");
		
		TableColumn tbTableColumnKhamBenhTEN_BENH_NHAN = new TableColumn(tableKhamBenh, SWT.LEFT);
		tbTableColumnKhamBenhTEN_BENH_NHAN.setWidth(255);
		tbTableColumnKhamBenhTEN_BENH_NHAN.setText("HỌ TÊN");
		
		TableColumn tblclmnKiuTt = new TableColumn(tableKhamBenh, SWT.RIGHT);
		tblclmnKiuTt.setWidth(123);
		tblclmnKiuTt.setText("KIỂU TT");

		TableColumn tbTableColumnMUC_HUONG = new TableColumn(tableKhamBenh, SWT.RIGHT);
		tbTableColumnMUC_HUONG.setWidth(114);
		tbTableColumnMUC_HUONG.setText("MỨC BHYT %");

		TableColumn tbTableColumnT_THUOC = new TableColumn(tableKhamBenh, SWT.RIGHT);
		tbTableColumnT_THUOC.setWidth(114);
		tbTableColumnT_THUOC.setText("THUỐC");
		
		TableColumn tableColumnSTS = new TableColumn(tableKhamBenh, SWT.RIGHT);
		tableColumnSTS.setWidth(114);
		tableColumnSTS.setText("Status");


		
		tableViewerKhamBenh.setLabelProvider(new TableLabelProviderKhamBenh());
		tableViewerKhamBenh.setContentProvider(new ContentProviderKhamBenh());
		tableViewerKhamBenh.setInput(listDataKhamBenh);
        //
        reloadTableKhamBenh();
		
		TabItem tbtmQunLNhp = new TabItem(tabFolder, SWT.NONE);
		tbtmQunLNhp.setText("Quản Lý Nhập");
		Composite compositeInShellNhapThuoc = new Composite(tabFolder, SWT.NONE);
		tbtmQunLNhp.setControl(compositeInShellNhapThuoc);
		compositeInShellNhapThuoc.setLayout(new BorderLayout(0, 0));

		Composite compositeHeaderNhapThuoc = new Composite(compositeInShellNhapThuoc, SWT.NONE);
		compositeHeaderNhapThuoc.setLayoutData(BorderLayout.NORTH);
		compositeHeaderNhapThuoc.setLayout(new GridLayout(2, false));

		textSearchNhapThuoc = new Text(compositeHeaderNhapThuoc, SWT.BORDER);
		textSearchNhapThuoc.setFont(SWTResourceManager.getFont("Tahoma", 11, SWT.NORMAL));
		textSearchNhapThuoc.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.keyCode==13){
					reloadTableNhapThuoc();
				}
			}
		});
		
		Button btnNewButtonSearchNhapThuoc = new Button(compositeHeaderNhapThuoc, SWT.NONE);
		btnNewButtonSearchNhapThuoc.setImage(SWTResourceManager.getImage(NhapThuocDlg.class, "/png/media-play-2x.png"));
		btnNewButtonSearchNhapThuoc.setFont(SWTResourceManager.getFont("Tahoma", 12, SWT.NORMAL));

		btnNewButtonSearchNhapThuoc.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				reloadTableNhapThuoc();
			}
		});
		GridData gd_btnNewButtonNhapThuoc = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnNewButtonNhapThuoc.widthHint = 87;
		btnNewButtonSearchNhapThuoc.setLayoutData(gd_btnNewButtonNhapThuoc);
		btnNewButtonSearchNhapThuoc.setText("Search");
        
		tableViewerNhapThuoc = new TableViewer(compositeInShellNhapThuoc, SWT.BORDER | SWT.FULL_SELECTION);
		tableNhapThuoc = tableViewerNhapThuoc.getTable();
		tableNhapThuoc.setFont(SWTResourceManager.getFont("Tahoma", 11, SWT.NORMAL));
		tableNhapThuoc.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.keyCode==SWT.F5){
					reloadTableNhapThuoc();
                }
				else if(e.keyCode==13){
					selectTableNhapThuoc();
				}
                else if(e.keyCode==SWT.DEL){
					deleteTableNhapThuoc();
				}
                else if(e.keyCode==SWT.F7){
					newItemNhapThuoc();
				}
			}
		});
        tableNhapThuoc.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				selectTableNhapThuoc();
			}
		});
        
		tableNhapThuoc.setLinesVisible(true);
		tableNhapThuoc.setHeaderVisible(true);
		tableNhapThuoc.setLayoutData(BorderLayout.CENTER);
		
		
				TableColumn tbTableColumnNGAY_NHAP = new TableColumn(tableNhapThuoc, SWT.NONE);
				tbTableColumnNGAY_NHAP.setWidth(152);
				tbTableColumnNGAY_NHAP.setText("NGAY_NHAP");
		
				TableColumn tbTableColumnTENKHO = new TableColumn(tableNhapThuoc, SWT.LEFT);
				tbTableColumnTENKHO.setWidth(116);
				tbTableColumnTENKHO.setText("TÊN KHO");
		
				TableColumn tbTableColumnHOADON = new TableColumn(tableNhapThuoc, SWT.LEFT);
				tbTableColumnHOADON.setWidth(100);
				tbTableColumnHOADON.setText("HÓA ĐƠN");

		TableColumn tbTableColumnV_ID = new TableColumn(tableNhapThuoc, SWT.RIGHT);
		tbTableColumnV_ID.setWidth(100);
		tbTableColumnV_ID.setText("TỔNG CỘNG");
		
		TableColumn tblclmnSts = new TableColumn(tableNhapThuoc, SWT.RIGHT);
		tblclmnSts.setWidth(66);
		tblclmnSts.setText("STS");

		TableColumn tbTableColumnTONGCONG = new TableColumn(tableNhapThuoc, SWT.RIGHT);
		tbTableColumnTONGCONG.setWidth(260);
		tbTableColumnTONGCONG.setText("NHÀCC");

        Menu menuNhapThuoc = new Menu(tableNhapThuoc);
		tableNhapThuoc.setMenu(menuNhapThuoc);
		
		MenuItem mntmNewItemNhapThuoc = new MenuItem(menuNhapThuoc, SWT.NONE);
		mntmNewItemNhapThuoc.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				newItemNhapThuoc();
			}
		});
		mntmNewItemNhapThuoc.setImage(SWTResourceManager.getImage(NhapThuocDlg.class, "/png/arrow-circle-top-2x.png"));
		mntmNewItemNhapThuoc.setText("New");
		
		MenuItem mntmNewItem_1NhapThuoc = new MenuItem(menuNhapThuoc, SWT.NONE);
		mntmNewItem_1NhapThuoc.setImage(SWTResourceManager.getImage(NhapThuocDlg.class, "/png/wrench-2x.png"));
		mntmNewItem_1NhapThuoc.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				selectTableNhapThuoc();
			}
		});
		mntmNewItem_1NhapThuoc.setText("Edit");
		
		MenuItem mntmDeleteNhapThuoc = new MenuItem(menuNhapThuoc, SWT.NONE);
		mntmDeleteNhapThuoc.setImage(SWTResourceManager.getImage(NhapThuocDlg.class, "/png/circle-x-2x.png"));
		mntmDeleteNhapThuoc.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				deleteTableNhapThuoc();
			}
		});
		mntmDeleteNhapThuoc.setText("Delete");

		tableViewerNhapThuoc.setLabelProvider(new TableLabelProviderNhapThuoc());
		tableViewerNhapThuoc.setContentProvider(new ContentProviderNhapThuoc());
		tableViewerNhapThuoc.setInput(listDataNhapThuoc);
		//
		shell.setMaximized(true);
		//toggleAlwaysOnTop(shell, true);
        //
        reloadTableNhapThuoc();
        //
	}
	
	protected void reloadTableNhapThuoc() {
		// Do search
		String searchString = textSearchNhapThuoc.getText().toLowerCase().trim();
		String sql = "select * from nhap_thuoc WHERE STS<> "+DbHelper.DELETE_STATUS+" ";
		if(searchString.length()>0){
            sql += " and ( 0 ";
        sql += " or LOWER(TENKHO) like '%"+searchString+"%'";
        sql += " or LOWER(HOADON) like '%"+searchString+"%'";
            sql += " )";
        }
		try  {
			Connection con = DbHelper.getSql2o();
			listDataNhapThuoc = con.createQuery(sql).executeAndFetch(NhapThuoc.class);
            System.out.println(sql);
	    }   
	    catch(Exception e){
	    	logger.error(e);
	    	MessageDialog.openError(shell, "Error", e.getMessage());
	    }
		// 
		tableViewerNhapThuoc.setInput(listDataNhapThuoc);
		tableViewerNhapThuoc.refresh();
        //
        if(listDataNhapThuoc.size()==0){
            textSearchNhapThuoc.forceFocus();
        }
        else{
            tableNhapThuoc.forceFocus();
            tableNhapThuoc.setSelection(0);
        }
	}
    
    
    protected void selectTableNhapThuoc() {
		if(tableNhapThuoc.getSelectionCount()==0){
			return;
		}
		TableItem item = tableNhapThuoc.getSelection()[0];
		NhapThuoc obj = (NhapThuoc)item.getData();
        logger.info(obj.toString());
        //
		FormNhapThuocDlg dlg = new FormNhapThuocDlg(shell, 0);
		dlg.intTypeDlgNhapThuoc = FormNhapThuocDlg.TYPE_DLG_VIEW;
		dlg.setNhapThuocDlgData(obj);
		dlg.open();
        //
		reloadTableNhapThuoc();
	}
    
    protected void deleteTableNhapThuoc() {
		if(tableNhapThuoc.getSelectionCount()==0){
			return;
		}
        if(MessageDialog.openConfirm(shell, "Confirm?", "Delete ?")==false){
			return;
		}
		
		TableItem item = tableNhapThuoc.getSelection()[0];
		NhapThuoc obj = (NhapThuoc)item.getData();
        logger.info(obj.toString());
        //
		obj.delete();
        listDataNhapThuoc.remove(obj);
        //
		reloadTableNhapThuoc();
	}

	protected void newItemNhapThuoc() {
		//
		FormNhapThuocDlg dlg = new FormNhapThuocDlg(shell, 0);
        NhapThuoc obj = new NhapThuoc();
		dlg.setNhapThuocDlgData(obj);
		dlg.open();
        listDataNhapThuoc.add(obj);
        //
		reloadTableNhapThuoc();
		//
	}
	
	protected void reloadTableKhamBenh() {
		// Do search
		String sql = "select * from kham_benh WHERE STS<>"+DbHelper.DELETE_STATUS+" and T_THUOC>0 ";//="+Utils.PHIEUKHAM_KHAMXONG_CHO_LAYTHUOC;
        sql += " order by STS ASC, MA_LK";
		try  {
			Connection con = DbHelper.getSql2o();
			listDataKhamBenh = con.createQuery(sql).executeAndFetch(KhamBenh.class);
	    }   
	    catch(Exception e){
	    	logger.error(e);
	    	MessageDialog.openError(shell, "Error", e.getMessage());
	    }
		// 
		tableViewerKhamBenh.setInput(listDataKhamBenh);
		tableViewerKhamBenh.refresh();
        //
        if(listDataKhamBenh.size()==0){
            textSearchKhamBenh.forceFocus();
        }
        else{
            tableKhamBenh.forceFocus();
            tableKhamBenh.setSelection(0);
        }
	}
    
    
    protected void selectTableKhamBenh() {
		if(tableKhamBenh.getSelectionCount()==0){
			return;
		}
		TableItem item = tableKhamBenh.getSelection()[0];
		KhamBenh obj = (KhamBenh)item.getData();
        logger.info(obj.toString());
        //
		FormThuocChitietListDlg dlg = new FormThuocChitietListDlg(shell, 0);
		dlg.setKhamBenhDlgData(obj);
		dlg.open();
        //
		reloadTableKhamBenh();
	}
    
    protected void deleteTableKhamBenh() {
		if(tableKhamBenh.getSelectionCount()==0){
			return;
		}
        if(MessageDialog.openConfirm(shell, "Confirm?", "Delete ?")==false){
			return;
		}
		
		TableItem item = tableKhamBenh.getSelection()[0];
		KhamBenh obj = (KhamBenh)item.getData();
        logger.info(obj.toString());
        //
		obj.delete();
        listDataKhamBenh.remove(obj);
        //
		reloadTableKhamBenh();
	}

	protected void newItemKhamBenh() {
		//
		KhamBenhDlg dlg = new KhamBenhDlg(shell, 0);
        KhamBenh obj = new KhamBenh();
		dlg.setKhamBenhDlgData(obj);
		dlg.open();
        listDataKhamBenh.add(obj);
        //
		reloadTableKhamBenh();
		//
	}
}