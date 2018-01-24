package com.openclinic;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Monitor;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;
import org.sql2o.Connection;
import org.sql2o.data.Row;

import swing2swt.layout.BorderLayout;

import com.DbHelper;
import com.model.dao.BenhNhan;
import com.model.dao.KhamBenh;
import com.openclinic.benhnhan.ListBenhNhanDlg;
import com.openclinic.khambenh.FormKhamBenhDlg;
import com.openclinic.nhanvien.FrmKhamBenhBHYTDlg;
import com.openclinic.utils.Utils;
import com.providers.ContentProviderBenhNhan;
import com.providers.TableLabelProviderBenhNhan;

public class Main extends Dialog{
	static Logger logger = LogManager.getLogger(Main.class.getName());
	private static Shell shlOpenclinic;
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	public static final String TITLE = "Openclinic 2.40";
	public static boolean isCheckVersion = true;
	
	public static final int DELETE_STATUS = 1;
	public static String DB_URL = "jdbc:mysql://pkap.ddns.net/openclinic?useUnicode=yes&characterEncoding=UTF-8";
	public static String DB_USER = "root";
	public static String DB_PASS = "ok";
	private static Text txtTen;
	private static Text txtMaThe;

	private static Table table;
	private static TableViewer tableViewer;
	private static Button btnRefresh;
	public static int MAX_MUCLUONGCOSO  = 1300000;
	public static int GIAKHAMVIENPHI = 30000;
	public static int TABLE_ID = 1;
	public static String USER_GATE_ID;
	public static String USER_GATE_PASSWORD;
	public static String serverIP = "localhost";
	public static String MESSAGE = "";
	
	public Main(Shell parent, int style) {
		super(parent, style);
	}


	/**
	 * Launch the application.
	 * @param args
	 *
	 */
	public void open() {

	//public static void main(String[] args) {
		//Locale.setDefault(Locale.ITALY);
		logger.info("Start app");
		Display display = Display.getDefault();
		shlOpenclinic = new Shell();
		shlOpenclinic.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPress(e);
			}
		});

		shlOpenclinic.setImage(SWTResourceManager.getImage(Main.class, "/png/cog-8x.png"));
		shlOpenclinic.setSize(963, 573);
		shlOpenclinic.setText("OpenClinic");
		shlOpenclinic.setLayout(new BorderLayout(0, 0));
		
		Menu menu = new Menu(shlOpenclinic, SWT.BAR);
		shlOpenclinic.setMenuBar(menu);
		
		MenuItem mntmNewSubmenu = new MenuItem(menu, SWT.CASCADE);
		mntmNewSubmenu.setText("Ch\u1EE9c n\u0103ng");
		
		Menu menu_1 = new Menu(mntmNewSubmenu);
		mntmNewSubmenu.setMenu(menu_1);
		
		MenuItem mntmHSBnh = new MenuItem(menu_1, SWT.NONE);
		mntmHSBnh.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				ListBenhNhanDlg dlg = new ListBenhNhanDlg(shlOpenclinic, 0);
				dlg.open();
			}
		});
		mntmHSBnh.setText("Hồ Sơ Bệnh Nhân");
		
		MenuItem mntmNewItem = new MenuItem(menu_1, SWT.NONE);
		mntmNewItem.setImage(SWTResourceManager.getImage(Main.class, "/png/account-logout-2x.png"));
		mntmNewItem.setText("Tho\u00E1t");
		
		MenuItem mntmNewItem_1 = new MenuItem(menu, SWT.NONE);
		mntmNewItem_1.setText("Trợ giúp");
		
		TabFolder tabFolder = new TabFolder(shlOpenclinic, SWT.NONE);
		tabFolder.setFont(SWTResourceManager.getFont("Tahoma", 8, SWT.BOLD));
		tabFolder.setLayoutData(BorderLayout.CENTER);
		
		TabItem tablePhieuKhamBenh = new TabItem(tabFolder, SWT.NONE);
		tablePhieuKhamBenh.setText("Phiếu Khám Bệnh");
		
		Composite composite = new Composite(tabFolder, SWT.NONE);
		tablePhieuKhamBenh.setControl(composite);
		composite.setLayout(new BorderLayout(0, 0));
		
		Composite composite_1 = new Composite(composite, SWT.NONE);
		composite_1.setLayoutData(BorderLayout.NORTH);
		composite_1.setLayout(new GridLayout(4, false));
		
		Label lblTheoMTh = new Label(composite_1, SWT.NONE);
		lblTheoMTh.setFont(SWTResourceManager.getFont("Tahoma", 12, SWT.NORMAL));
		lblTheoMTh.setText("Theo mã thẻ");
		
		txtMaThe = new Text(composite_1, SWT.BORDER);
		txtMaThe.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtMaThe.selectAll();
			}
		});
		GridData gd_txtMaThe = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_txtMaThe.widthHint = 173;
		txtMaThe.setLayoutData(gd_txtMaThe);
		txtMaThe.setFont(SWTResourceManager.getFont("Tahoma", 12, SWT.NORMAL));
		txtMaThe.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPress(e);
			}
		});
		new Label(composite_1, SWT.NONE);
		new Label(composite_1, SWT.NONE);
		
		Label lblTmTheoTn = new Label(composite_1, SWT.NONE);
		lblTmTheoTn.setFont(SWTResourceManager.getFont("Tahoma", 12, SWT.NORMAL));
		lblTmTheoTn.setText("Tìm theo tên");
		
		txtTen = new Text(composite_1, SWT.BORDER);
		txtTen.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		txtTen.setFont(SWTResourceManager.getFont("Tahoma", 12, SWT.NORMAL));
		txtTen.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtTen.selectAll();
			}
		});
		btnRefresh = new Button(composite_1, SWT.NONE);
		btnRefresh.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				doSearchBenhNhan(1);
			}
		});
		
		

		btnRefresh.setImage(SWTResourceManager.getImage(Main.class, "/png/magnifying-glass-2x.png"));
		GridData gd_btnRefresh = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnRefresh.widthHint = 122;
		btnRefresh.setLayoutData(gd_btnRefresh);
		btnRefresh.setFont(SWTResourceManager.getFont("Tahoma", 12, SWT.NORMAL));
		btnRefresh.setText("Tìm");
		
		Button btnTtC = new Button(composite_1, SWT.NONE);
		btnTtC.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				doSearchBenhNhan(0);
			}
		});
		GridData gd_btnTtC = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnTtC.widthHint = 111;
		btnTtC.setLayoutData(gd_btnTtC);
		btnTtC.setText("Tất cả");
		btnTtC.setImage(SWTResourceManager.getImage(Main.class, "/png/magnifying-glass-2x.png"));
		btnTtC.setFont(SWTResourceManager.getFont("Tahoma", 12, SWT.NORMAL));
		txtTen.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPress(e);
			}
		});
		
		Composite composite_2 = new Composite(composite, SWT.NONE);
		composite_2.setLayoutData(BorderLayout.CENTER);
		composite_2.setLayout(new BorderLayout(0, 0));
		
		tableViewer = new TableViewer(composite_2, SWT.BORDER | SWT.FULL_SELECTION);
		table = tableViewer.getTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				enterTableViewer();
			}
		});
		table.setFont(SWTResourceManager.getFont("Tahoma", 12, SWT.NORMAL));
		table.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPress(e);
			}
		});
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		table.setLayoutData(BorderLayout.CENTER);
		
		TableColumn tblclmnTnhTrng_1 = new TableColumn(table, SWT.NONE);
		tblclmnTnhTrng_1.setWidth(107);
		tblclmnTnhTrng_1.setText("Tình trạng");
		
		TableColumn tblclmnNewColumn = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn.setWidth(128);
		tblclmnNewColumn.setText("Thời gian vào");
		
		TableColumn tblclmnNewColumn_2 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_2.setWidth(172);
		tblclmnNewColumn_2.setText("Họ tên");
		
		TableColumn tblclmnNewColumn_1 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_1.setWidth(176);
		tblclmnNewColumn_1.setText("Mã Thẻ");
		
		TableColumn tblclmnTyp = new TableColumn(table, SWT.NONE);
		tblclmnTyp.setWidth(57);
		tblclmnTyp.setToolTipText("Status của người bệnh, (Đang cận LS, xong cận LS nào đó hay chưa )");
		tblclmnTyp.setText("TYP");
		
		TableColumn tblclmnSDvthuc = new TableColumn(table, SWT.NONE);
		tblclmnSDvthuc.setWidth(65);
		tblclmnSDvthuc.setText("SL CLS");
		
		TableColumn tblclmnThuc = new TableColumn(table, SWT.NONE);
		tblclmnThuc.setWidth(67);
		tblclmnThuc.setText("TToán");
		
		TableColumn tblclmnTnhTrng = new TableColumn(table, SWT.NONE);
		tblclmnTnhTrng.setToolTipText("Status của người bệnh, (Đang cận LS, xong cận LS nào đó hay chưa )");
		tblclmnTnhTrng.setWidth(40);
		tblclmnTnhTrng.setText("K");
		
		Menu menu_2 = new Menu(table);
		table.setMenu(menu_2);
		
		MenuItem mntmnTipNgi = new MenuItem(menu_2, SWT.NONE);
		mntmnTipNgi.setText("Đón tiếp người mới");
		
		new MenuItem(menu_2, SWT.SEPARATOR);
		
		MenuItem mntmXemPhinKhm = new MenuItem(menu_2, SWT.NONE);
		mntmXemPhinKhm.setText("Xem phiên khám bệnh");
		
		MenuItem mntmChnhCn = new MenuItem(menu_2, SWT.NONE);
		mntmChnhCn.setText("Chỉ định Cận Lâm Sàn");
		
		MenuItem mntmChnhThuc = new MenuItem(menu_2, SWT.NONE);
		mntmChnhThuc.setText("Chỉ định thuốc");
		
		new MenuItem(menu_2, SWT.SEPARATOR);
		
		MenuItem mntmTiKhm = new MenuItem(menu_2, SWT.NONE);
		mntmTiKhm.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				createNewTaiKham();
			}
		});
		mntmTiKhm.setText("Tái khám ->");
		
		new MenuItem(menu_2, SWT.SEPARATOR);
		
		MenuItem mntmXemLchS = new MenuItem(menu_2, SWT.NONE);
		mntmXemLchS.setText("Xem lịch sử KCB");
		
		TableColumn tblclmnBn = new TableColumn(table, SWT.NONE);
		tblclmnBn.setWidth(110);
		tblclmnBn.setToolTipText("Status của người bệnh, (Đang cận LS, xong cận LS nào đó hay chưa )");
		tblclmnBn.setText("Khoa");
		tableViewer.setLabelProvider(new TableLabelProviderBenhNhan());
		tableViewer.setContentProvider(new ContentProviderBenhNhan());
		
		
		Composite composite_3 = new Composite(shlOpenclinic, SWT.NONE);
		composite_3.setLayoutData(BorderLayout.SOUTH);
		composite_3.setLayout(null);
		
		Button btnKhamBaoHiem = new Button(composite_3, SWT.NONE);
		btnKhamBaoHiem.setBounds(5, 5, 164, 33);
		btnKhamBaoHiem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				FrmKhamBenhBHYTDlg dlg = new FrmKhamBenhBHYTDlg(shlOpenclinic, 0);
				dlg.open();
			}
		});
		btnKhamBaoHiem.setFont(SWTResourceManager.getFont("Tahoma", 14, SWT.BOLD));
		btnKhamBaoHiem.setText("Khám Bảo Hiểm");
		
		Button btnKhamVienPhi = new Button(composite_3, SWT.NONE);
		btnKhamVienPhi.setBounds(174, 5, 150, 33);
		btnKhamVienPhi.setText("Khám Viện Phí");
		btnKhamVienPhi.setFont(SWTResourceManager.getFont("Tahoma", 14, SWT.BOLD));
		
		Button btnMuaPhieu = new Button(composite_3, SWT.NONE);
		btnMuaPhieu.setBounds(329, 5, 169, 33);
		btnMuaPhieu.setText("Mua phiếu khám");
		btnMuaPhieu.setFont(SWTResourceManager.getFont("Tahoma", 14, SWT.BOLD));
		
		
		//
		shlOpenclinic.open();
		shlOpenclinic.layout();
		// Move to center
		Monitor primary = display.getPrimaryMonitor();
		Rectangle bounds = primary.getBounds();
		Rectangle rect = shlOpenclinic.getBounds();
		int x = bounds.x + (bounds.width - rect.width) / 2;
		int y = bounds.y + (bounds.height - rect.height) / 2;
		shlOpenclinic.setLocation(x, y);
		//
		startApp();
		//
		startDlg();
		// End move to center
		while (!shlOpenclinic.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	

	private static void startDlg() {
		shlOpenclinic.setText(Main.TITLE + " "+DbHelper.currentSessionUserId.TEN_NHANVIEN +" (" + DbHelper.currentSessionUserId.MACCHN  + ")");
		
	}



	protected static void doSearchBenhNhan(int isSearchAll) {
		List<Row> listData = null;
		try  {
			Connection con = DbHelper.getSql2o();
			String sql = "SELECT * FROM benh_nhan as bn, kham_benh as kb where bn.BN_ID=kb.BN_ID ";
			if(isSearchAll==1){
				sql += " and DATE(KB_DATE) = CURDATE()";
			}
			if(txtMaThe.getText().length()>0){
				sql += " and LOWER(bn.MA_THE) like '%"+txtMaThe.getText().toLowerCase()+"%'";
			}
			if(txtTen.getText().length()>0){
				sql += " and LOWER(bn.HO_TEN) like '%"+txtTen.getText().toLowerCase()+"%'";
			}
			sql += " order by KB_DATE desc";
			System.out.println(sql);
			listData = con.createQuery(sql).executeAndFetchTable().rows();
	    }   
	    catch(Exception e){
	    	logger.error(e);
	    	MessageDialog.openError(shlOpenclinic, "Có lỗi", e.getMessage());
	    }
		//System.out.println(listData.size());
		tableViewer.setInput(listData);
		tableViewer.refresh();
		//table.forceFocus();
		//table.select(0);
	}

	private static void startApp() {
		txtMaThe.forceFocus();
		doSearchBenhNhan(0);
	}
	
	/***
	 * 
	 */
	protected static void createNewTaiKham() {
		if(table.getSelectionCount()==0){
			return;
		}
		TableItem item = table.getSelection()[0];
		Row row = (Row)item.getData();
		Integer BN_ID = (Integer)row.getInteger("BN_ID");
		Integer KIEU_TT = (Integer)row.getInteger("KIEU_TT");
		System.out.println("TAI KHAM KIEU_TT=" + KIEU_TT);
		if(KIEU_TT.intValue()==Utils.THANHTOAN_BHYT || KIEU_TT.intValue()==Utils.THANHTOAN_BHYT_2){
			return;
		}
		//
		FormKhamBenhDlg dlg = new FormKhamBenhDlg(shlOpenclinic, 0);
		BenhNhan objBenhNhan = BenhNhan.load(BN_ID);
		dlg.KIEU_THANH_TOAN = Utils.THANHTOAN_TAI_KHAM;
		dlg.setData(objBenhNhan, null);
		dlg.open();
		doSearchBenhNhan(0);
	}
	protected static void keyPress(KeyEvent e) {
        if(((e.stateMask & SWT.CTRL) == SWT.CTRL)){
			switch(e.keyCode){
				case '1':
						enterTableViewer();
					break;
				case 'F':
				case 'f':
				case 'T':
				case 't':
					txtTen.forceFocus();
					break;
				case 'M':
				case 'm':
					txtMaThe.forceFocus();
					break;
					
				case 13:
					//createNewTaiKham();
					break;
				default:
			}
        }
        else{
			switch(e.keyCode){
				case 13:
					doSearchBenhNhan(0);
					break;
				case SWT.INSERT:
				case SWT.F7:
					// New kham benh BHYT
					FormKhamBenhDlg dlg = new FormKhamBenhDlg(shlOpenclinic, 0);
					dlg.open();
					doSearchBenhNhan(0);
					//
					break;
				default:
			}
        }
	}

	private static void enterTableViewer() {
		if(table.getSelectionCount()==0){
			return;
		}
		TableItem item = table.getSelection()[0];
		Row row = (Row)item.getData();
		Integer BN_ID = (Integer)row.getInteger("BN_ID");
		Integer MA_LK = (Integer)row.getInteger("MA_LK");
		System.out.println("BN ID="+BN_ID+" MALK="+MA_LK);
		FormKhamBenhDlg dlg = new FormKhamBenhDlg(shlOpenclinic, 0);
		BenhNhan objBenhNhan = BenhNhan.load(BN_ID);
		KhamBenh objKhamBenh = KhamBenh.load(MA_LK);
		// Check phieu KHONG CONG KHAM
		//
		dlg.KIEU_THANH_TOAN = objKhamBenh.KIEU_TT;
		logger.info("objKhamBenh " + objKhamBenh.TEN_BENH);
		dlg.setData(objBenhNhan, objKhamBenh);
		dlg.open();
		doSearchBenhNhan(0);
	}
}
