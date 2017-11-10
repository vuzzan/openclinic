package com.openclinic;

import java.io.ObjectInputStream.GetField;
import java.util.Hashtable;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;

import swing2swt.layout.BorderLayout;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Monitor;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.sql2o.Connection;
import org.sql2o.Sql2oException;

import com.DbHelper;
import com.model.cache.MaCskcbCache;
import com.model.dao.Dichvu;
import com.model.dao.KhoaPhong;
import com.model.dao.Khohang;
import com.model.dao.MaCskcb;
import com.model.dao.Mabenh;
import com.model.dao.MstLieudung;
import com.model.dao.Phanquyen;
import com.model.dao.Users;
import com.model.dao.Vendor;
import com.openclinic.khambenh.FormKhamBenhDlg;
import com.openclinic.utils.Utils;

public class LoginDlg {
	static Logger logger = LogManager.getLogger(LoginDlg.class.getName());

	private static Text txtId;
	private static Text txtPw;

	private static INIFile ini;
	private static Shell shellLogin;

	private static Button button;

	private static Display display;
	static {
		
	}
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		logger.info("Start app");
		
		Display display = Display.getDefault();
		shellLogin = new Shell();
		shellLogin.setSize(436, 249);
		shellLogin.setText(Main.TITLE + ": Login");
		shellLogin.setLayout(new BorderLayout(0, 0));
		
		Composite composite = new Composite(shellLogin, SWT.NONE);
		composite.setLayoutData(BorderLayout.NORTH);
		
		Label label = new Label(composite, SWT.NONE);
		label.setText("Đăng nhập hệ thống ");
		label.setFont(SWTResourceManager.getFont("Tahoma", 16, SWT.NORMAL));
		label.setBounds(10, 10, 230, 25);
		
		Composite composite_1 = new Composite(shellLogin, SWT.NONE);
		composite_1.setLayoutData(BorderLayout.CENTER);
		composite_1.setLayout(null);
		
		Label label_1 = new Label(composite_1, SWT.NONE);
		label_1.setText("ID");
		label_1.setFont(SWTResourceManager.getFont("Tahoma", 12, SWT.NORMAL));
		label_1.setBounds(117, 20, 27, 27);
		
		txtId = new Text(composite_1, SWT.BORDER);
		txtId.setText("admin");
		txtId.setFont(SWTResourceManager.getFont("Tahoma", 12, SWT.NORMAL));
		txtId.setBounds(152, 20, 203, 25);
		txtId.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPress(e);
			}
		});
		
		txtPw = new Text(composite_1, SWT.BORDER | SWT.PASSWORD);
		txtPw.setText("admin");
		txtPw.setFont(SWTResourceManager.getFont("Tahoma", 12, SWT.NORMAL));
		txtPw.setBounds(152, 50, 203, 25);
		
		txtPw.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPress(e);
			}
		});
		
		
		Label label_2 = new Label(composite_1, SWT.NONE);
		label_2.setImage(SWTResourceManager.getImage(LoginDlg.class, "/png/lock-locked-2x.png"));
		label_2.setFont(SWTResourceManager.getFont("Tahoma", 12, SWT.NORMAL));
		label_2.setBounds(119, 53, 27, 19);
		
		button = new Button(composite_1, SWT.CHECK);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				rememberLogin(button.getSelection());
			}
		});
		button.setText("Nhớ ");
		button.setFont(SWTResourceManager.getFont("Tahoma", 12, SWT.NORMAL));
		button.setBounds(152, 80, 50, 19);
		
		Button btnCancel = new Button(composite_1, SWT.NONE);
		btnCancel.setText("Bỏ qua ");
		btnCancel.setFont(SWTResourceManager.getFont("Tahoma", 12, SWT.NORMAL));
		btnCancel.setBounds(48, 120, 121, 34);
		btnCancel.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				// genCode2D();
				shellLogin.close();
			}
		});
		
		Button btnLogin = new Button(composite_1, SWT.NONE);
		btnLogin.setText("Đăng nhập ");
		btnLogin.setImage(SWTResourceManager.getImage(LoginDlg.class, "/png/account-login-3x.png"));
		btnLogin.setFont(SWTResourceManager.getFont("Tahoma", 12, SWT.NORMAL));
		btnLogin.setBounds(184, 120, 171, 34);
		btnLogin.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				login();
			}
		});
		
		Monitor primary = display.getPrimaryMonitor();
		Rectangle bounds = primary.getBounds();
		Rectangle rect = shellLogin.getBounds();
		int x = bounds.x + (bounds.width - rect.width) / 2;
		int y = bounds.y + (bounds.height - rect.height) / 2;
		shellLogin.setLocation(x, y);
		

		startApp();
		
		shellLogin.open();
		shellLogin.layout();
		while (!shellLogin.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
	protected static void rememberLogin(boolean isRemember) {
		INIFile ini = new INIFile("openclinic.ini");
		if(isRemember==true){
			ini.setStringProperty("CONF", "LOGIN_ID", txtId.getText(), "Login ID");
			ini.setStringProperty("CONF", "LOGIN_PW", Encryptor.encrypt(txtPw.getText()), "Login PW");
			ini.setIntegerProperty("CONF", "REMEMBER", 1, "Rem");
			ini.setIntegerProperty("CONF", "MUCLUONGCOSO", Main.MAX_MUCLUONGCOSO, "Muc luong co so");
			ini.setIntegerProperty("CONF", "GIAKHAMVIENPHI", Main.GIAKHAMVIENPHI, "Gia cong kham vien phi");
		}
		else{
			ini.setStringProperty("CONF", "LOGIN_ID", txtId.getText(), "Login ID");
			ini.setStringProperty("CONF", "LOGIN_PW", "", "Login PW");
			ini.setIntegerProperty("CONF", "REMEMBER", 0, "Rem");
			ini.setIntegerProperty("CONF", "MUCLUONGCOSO", Main.MAX_MUCLUONGCOSO, "Muc luong co so");
			ini.setIntegerProperty("CONF", "GIAKHAMVIENPHI", Main.GIAKHAMVIENPHI, "Gia cong kham vien phi");
		}
		ini.save();
	}
	private static void startApp() {
		INIFile ini = new INIFile("openclinic.ini");
		String serverIP = ini.getStringProperty("CONF", "SERVER");
		Integer MUCLUONGCOSO = ini.getIntegerProperty("CONF", "MUCLUONGCOSO");
		if(MUCLUONGCOSO==null){
			Main.MAX_MUCLUONGCOSO = 1150000;
		}
		else{
			Main.MAX_MUCLUONGCOSO = MUCLUONGCOSO.intValue();
		}
		Integer GIAKHAMVIENPHI= ini.getIntegerProperty("CONF", "GIAKHAMVIENPHI");
		if(GIAKHAMVIENPHI==null){
			Main.GIAKHAMVIENPHI = 30000;
		}
		Main.DB_USER = ini.getStringProperty("CONF", "USERNAME");
		Main.DB_PASS = ini.getStringProperty("CONF", "PASSWORD");
		String DB_NAME = ini.getStringProperty("CONF", "DBNAME");
		//	public static final String DB_URL = "jdbc:mysql://pkap.ddns.net/openclinic?useUnicode=yes&characterEncoding=UTF-8";
		Main.DB_URL = "jdbc:mysql://"+serverIP+"/"+DB_NAME+"?useUnicode=yes&characterEncoding=UTF-8";
		//
		Integer rem = ini.getIntegerProperty("CONF", "REMEMBER");
		String sId = ini.getStringProperty("CONF", "LOGIN_ID");
		String sPW = ini.getStringProperty("CONF", "LOGIN_PW");
		if(rem==null){
			txtId.setText(sId==null?"":sId);
			txtPw.setText("");
			button.setSelection(false);

		}
		else{
			if(rem.intValue()==1){
				txtId.setText(sId==null?"":sId);
				txtPw.setText((sPW==null || sPW.length()==0)?"":(Encryptor.decrypt(sPW)));
				button.setSelection(true);
			}
			else{
				txtId.setText(sId==null?"":sId);
				txtPw.setText("");
				button.setSelection(false);
			}
		}
		if(txtId.getText().length()>0){
			txtPw.forceFocus();
		}
		else{
			txtId.forceFocus();
		}
		//		
		// MA HOC TAI NAN THUONG TICH - Bang 8
		Utils.mstArrayTAINANTHUONGTICH.add(0, "Không");
		Utils.mstArrayTAINANTHUONGTICH.add(1, "Tai nạn giao thông");
		Utils.mstArrayTAINANTHUONGTICH.add(2, "Tai nạn lao động");
		Utils.mstArrayTAINANTHUONGTICH.add(3, "Tai nạn dưới nước");
		Utils.mstArrayTAINANTHUONGTICH.add(4, "Bỏng");
		Utils.mstArrayTAINANTHUONGTICH.add(5, "Bạo lực, xung đột");
		Utils.mstArrayTAINANTHUONGTICH.add(6, "Tự tử");
		Utils.mstArrayTAINANTHUONGTICH.add(7, "Ngộ độc các loại");
		Utils.mstArrayTAINANTHUONGTICH.add(8, "Khác");
		//
		Utils.mstArrayTinhTrangPhieuKhamBenh.add(Utils.PHIEUKHAM_CHOKHAM_BS, "Chờ BS khám");
		Utils.mstArrayTinhTrangPhieuKhamBenh.add(Utils.PHIEUKHAM_BSKHAMXONG_CHO_CLS, "Chờ CLS");
		Utils.mstArrayTinhTrangPhieuKhamBenh.add(Utils.PHIEUKHAM_CLS_XONG_CHO_KQ_CLS, "Xong CLS");
		Utils.mstArrayTinhTrangPhieuKhamBenh.add(Utils.PHIEUKHAM_CO_KQ_CLS_QUAYLAI_BS, "Quay lại BS");
		Utils.mstArrayTinhTrangPhieuKhamBenh.add(Utils.PHIEUKHAM_KHAMXONG_CHO_LAYTHUOC, "Chờ lấy thuốc");
		Utils.mstArrayTinhTrangPhieuKhamBenh.add(Utils.PHIEUKHAM_KHAMXONG_RAVE, "Xong, về");
		//
		Utils.mstArrayTinhTrangCanLamSan.add(0, "Chờ gọi");
		Utils.mstArrayTinhTrangCanLamSan.add(1, "Xóa");
		Utils.mstArrayTinhTrangCanLamSan.add(2, "Đang CLS");
		Utils.mstArrayTinhTrangCanLamSan.add(3, "Có KQ");
		//
		//Utils.mstArrayTinhTrangThuoc.add(1, "Xóa");
		Utils.mstArrayTinhTrangThuoc.add("Đã cho thuốc");
		Utils.mstArrayTinhTrangThuoc.add("Đã nhận thuốc");
		//
		Utils.mstArrayTinhTrangPhieuKhamBenhColor.add(0,
				SWTResourceManager.getColor(SWT.COLOR_YELLOW));
		Utils.mstArrayTinhTrangPhieuKhamBenhColor.add(1,
				SWTResourceManager.getColor(SWT.COLOR_BLUE));
		Utils.mstArrayTinhTrangPhieuKhamBenhColor.add(2,
				SWTResourceManager.getColor(SWT.COLOR_GREEN));
		Utils.mstArrayTinhTrangPhieuKhamBenhColor.add(3,
				SWTResourceManager.getColor(SWT.COLOR_YELLOW));
		Utils.mstArrayTinhTrangPhieuKhamBenhColor.add(4,
				SWTResourceManager.getColor(SWT.COLOR_GREEN));
		Utils.mstArrayTinhTrangPhieuKhamBenhColor.add(5,
				SWTResourceManager.getColor(SWT.COLOR_GRAY));
		Utils.mstArrayTinhTrangPhieuKhamBenhColor.add(6,
				SWTResourceManager.getColor(SWT.COLOR_GRAY));

		
		DbHelper.startConnection();
		loadCacheDB(DbHelper.getSql2o());
		//
		
	}
	protected static void keyPress(KeyEvent e) {
		if (e.keyCode == 13) {
			login();
		}
		
	}
	protected static void login() {
		try {
			Object result = doLogin();
			if (result != null) {
				Users obj = (Users) result;
				DbHelper.setCurrentSessionUserId(obj);
				//
				rememberLogin( button.getSelection() );
				//
				String sql = "SELECT * FROM phanquyen where U_ID="+DbHelper.getCurrentSessionUserId();
				//logger.info("Get cache" + sql);
				List<Phanquyen> listPhanquyen = DbHelper.getSql2o().createQuery(sql).executeAndFetch(Phanquyen.class);
				for (Phanquyen objPhanquyen : listPhanquyen) {
					DbHelper.hashDataPhanquyen.put(objPhanquyen.TABLE_NAME, objPhanquyen);
				}
//				shlLogin.setMinimized(true);
//				//
				display = shellLogin.getDisplay();
				shellLogin.close();
				
				//
				Shell shell = new Shell (display, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
				openMainDlg(shell);
				///
			} else {
				DbHelper.setCurrentSessionUserId(null);
				
				MessageDialog.openError(shellLogin, "Có lỗi",
						"Có lỗi khi login hệ thống.\nKhông tìm thấy user ["
								+ txtId.getText() + "]");
				shellLogin.close();
			}
		} catch (Exception ex) {
			logger.info(ex);
			ex.printStackTrace();
			//MessageDialog.openError(shellLogin, "Có lỗi", "Có lỗi khi login hệ thống.\n" + ex.getMessage());
		}
	}
	private static void updatePhanQuyen(Users objUser) {
		String sql = "delete from phanquyen where U_ID=" + objUser.getU_ID();
		Connection con = DbHelper.getSql2o();
		con.createQuery(sql).executeUpdate();
		objUser.LOAI = objUser.LOAI.toUpperCase();
		//
		logger.info(objUser);
		//
		if (objUser.LOAI.equals("NV")) {
			
			sql = "insert into phanquyen (`U_ID`, `U_NAME`, `TABLE_NAME`, `READ`, `INSERT`, `UPDATE`, `DELETE`) values("
					+ objUser.getU_ID()
					+ ",'"
					+ objUser.U_NAME
					+ "','action_log',1,1,1,1)";
			System.out.println(sql);
			con.createQuery(sql).executeUpdate();// Table : action_log
			sql = "insert into phanquyen (`U_ID`, `U_NAME`, `TABLE_NAME`, `READ`, `INSERT`, `UPDATE`, `DELETE`) values("
					+ objUser.getU_ID()
					+ ",'"
					+ objUser.U_NAME
					+ "','mabenh',0,0,0,0)";
			System.out.println(sql);
			con.createQuery(sql).executeUpdate();// Table : action_log
			sql = "insert into phanquyen (`U_ID`, `U_NAME`, `TABLE_NAME`, `READ`, `INSERT`, `UPDATE`, `DELETE`) values("
					+ objUser.getU_ID()
					+ ",'"
					+ objUser.U_NAME
					+ "','benh_nhan',1,1,1,1)";
			con.createQuery(sql).executeUpdate();// Table : benh_nhan

			sql = "insert into phanquyen (`U_ID`, `U_NAME`, `TABLE_NAME`, `READ`, `INSERT`, `UPDATE`, `DELETE`) values("
					+ objUser.getU_ID()
					+ ",'"
					+ objUser.U_NAME
					+ "','dv_chitiet',1,1,1,1)";
			con.createQuery(sql).executeUpdate();// Table : dv_chitiet

			sql = "insert into phanquyen (`U_ID`, `U_NAME`, `TABLE_NAME`, `READ`, `INSERT`, `UPDATE`, `DELETE`) values("
					+ objUser.getU_ID()
					+ ",'"
					+ objUser.U_NAME
					+ "','kham_benh',1,1,1,1)";
			con.createQuery(sql).executeUpdate();// Table : kham_benh

		} else if (objUser.LOAI.equals("BS")) {
			
			sql = "insert into phanquyen (`U_ID`, `U_NAME`, `TABLE_NAME`, `READ`, `INSERT`, `UPDATE`, `DELETE`) values("
					+ objUser.getU_ID()
					+ ",'"
					+ objUser.U_NAME
					+ "','action_log',1,1,1,1)";
			System.out.println(sql);
			con.createQuery(sql).executeUpdate();// Table : action_log

			sql = "insert into phanquyen (`U_ID`, `U_NAME`, `TABLE_NAME`, `READ`, `INSERT`, `UPDATE`, `DELETE`) values("
					+ objUser.getU_ID()
					+ ",'"
					+ objUser.U_NAME
					+ "','dv_chitiet',1,1,1,1)";
			con.createQuery(sql).executeUpdate();// Table : dv_chitiet
			sql = "insert into phanquyen (`U_ID`, `U_NAME`, `TABLE_NAME`, `READ`, `INSERT`, `UPDATE`, `DELETE`) values("
					+ objUser.getU_ID()
					+ ",'"
					+ objUser.U_NAME
					+ "','ketqua_xn',1,1,1,1)";
			con.createQuery(sql).executeUpdate();// Table : ketqua_xn
			sql = "insert into phanquyen (`U_ID`, `U_NAME`, `TABLE_NAME`, `READ`, `INSERT`, `UPDATE`, `DELETE`) values("
					+ objUser.getU_ID()
					+ ",'"
					+ objUser.U_NAME
					+ "','kham_benh',1,1,1,1)";
			con.createQuery(sql).executeUpdate();// Table : kham_benh

			sql = "insert into phanquyen (`U_ID`, `U_NAME`, `TABLE_NAME`, `READ`, `INSERT`, `UPDATE`, `DELETE`) values("
					+ objUser.getU_ID()
					+ ",'"
					+ objUser.U_NAME
					+ "','thuoc',1,1,1,1)";
			con.createQuery(sql).executeUpdate();// Table : thuoc
			sql = "insert into phanquyen (`U_ID`, `U_NAME`, `TABLE_NAME`, `READ`, `INSERT`, `UPDATE`, `DELETE`) values("
					+ objUser.getU_ID()
					+ ",'"
					+ objUser.U_NAME
					+ "','thuoc_chitiet',1,1,1,1)";
			con.createQuery(sql).executeUpdate();// Table : thuoc_chitiet

		} else if (objUser.LOAI.equals("THUOC")) {
			
			sql = "insert into phanquyen (`U_ID`, `U_NAME`, `TABLE_NAME`, `READ`, `INSERT`, `UPDATE`, `DELETE`) values("
					+ objUser.getU_ID()
					+ ",'"
					+ objUser.U_NAME
					+ "','action_log',1,1,1,1)";
			con.createQuery(sql).executeUpdate();// Table : action_log

			sql = "insert into phanquyen (`U_ID`, `U_NAME`, `TABLE_NAME`, `READ`, `INSERT`, `UPDATE`, `DELETE`) values("
					+ objUser.getU_ID()
					+ ",'"
					+ objUser.U_NAME
					+ "','ct_nhapthuoc',1,1,1,1)";
			con.createQuery(sql).executeUpdate();// Table : ct_nhapthuoc

			sql = "insert into phanquyen (`U_ID`, `U_NAME`, `TABLE_NAME`, `READ`, `INSERT`, `UPDATE`, `DELETE`) values("
					+ objUser.getU_ID()
					+ ",'"
					+ objUser.U_NAME
					+ "','kham_benh',1,1,1,1)";
			con.createQuery(sql).executeUpdate();// Table : kham_benh

			sql = "insert into phanquyen (`U_ID`, `U_NAME`, `TABLE_NAME`, `READ`, `INSERT`, `UPDATE`, `DELETE`) values("
					+ objUser.getU_ID()
					+ ",'"
					+ objUser.U_NAME
					+ "','nhap_thuoc',1,1,1,1)";
			con.createQuery(sql).executeUpdate();// Table : nhap_thuoc

			sql = "insert into phanquyen (`U_ID`, `U_NAME`, `TABLE_NAME`, `READ`, `INSERT`, `UPDATE`, `DELETE`) values("
					+ objUser.getU_ID()
					+ ",'"
					+ objUser.U_NAME
					+ "','thuoc_chitiet',1,1,1,1)";
			con.createQuery(sql).executeUpdate();// Table : thuoc_chitiet

			sql = "insert into phanquyen (`U_ID`, `U_NAME`, `TABLE_NAME`, `READ`, `INSERT`, `UPDATE`, `DELETE`) values("
					+ objUser.getU_ID()
					+ ",'"
					+ objUser.U_NAME
					+ "','vendor',1,1,1,1)";
			con.createQuery(sql).executeUpdate();// Table : vendor

		} else if (objUser.LOAI.equals("ACCT")) {
			
			sql = "insert into phanquyen (`U_ID`, `U_NAME`, `TABLE_NAME`, `READ`, `INSERT`, `UPDATE`, `DELETE`) values("
					+ objUser.getU_ID()
					+ ",'"
					+ objUser.U_NAME
					+ "','action_log',1,1,1,1)";
			con.createQuery(sql).executeUpdate();// Table : action_log

			sql = "insert into phanquyen (`U_ID`, `U_NAME`, `TABLE_NAME`, `READ`, `INSERT`, `UPDATE`, `DELETE`) values("
					+ objUser.getU_ID()
					+ ",'"
					+ objUser.U_NAME
					+ "','ct_nhapthuoc',1,1,1,1)";
			con.createQuery(sql).executeUpdate();// Table : ct_nhapthuoc
			sql = "insert into phanquyen (`U_ID`, `U_NAME`, `TABLE_NAME`, `READ`, `INSERT`, `UPDATE`, `DELETE`) values("
					+ objUser.getU_ID()
					+ ",'"
					+ objUser.U_NAME
					+ "','dichvu',1,1,1,1)";
			con.createQuery(sql).executeUpdate();// Table : dichvu
			sql = "insert into phanquyen (`U_ID`, `U_NAME`, `TABLE_NAME`, `READ`, `INSERT`, `UPDATE`, `DELETE`) values("
					+ objUser.getU_ID()
					+ ",'"
					+ objUser.U_NAME
					+ "','donvi_tinh',1,1,1,1)";
			con.createQuery(sql).executeUpdate();// Table : donvi_tinh
			sql = "insert into phanquyen (`U_ID`, `U_NAME`, `TABLE_NAME`, `READ`, `INSERT`, `UPDATE`, `DELETE`) values("
					+ objUser.getU_ID()
					+ ",'"
					+ objUser.U_NAME
					+ "','duongdung',1,1,1,1)";
			con.createQuery(sql).executeUpdate();// Table : duongdung

			sql = "insert into phanquyen (`U_ID`, `U_NAME`, `TABLE_NAME`, `READ`, `INSERT`, `UPDATE`, `DELETE`) values("
					+ objUser.getU_ID()
					+ ",'"
					+ objUser.U_NAME
					+ "','kham_benh',1,1,1,1)";
			con.createQuery(sql).executeUpdate();// Table : kham_benh

			sql = "insert into phanquyen (`U_ID`, `U_NAME`, `TABLE_NAME`, `READ`, `INSERT`, `UPDATE`, `DELETE`) values("
					+ objUser.getU_ID()
					+ ",'"
					+ objUser.U_NAME
					+ "','khohang',1,1,1,1)";
			con.createQuery(sql).executeUpdate();// Table : khohang
			sql = "insert into phanquyen (`U_ID`, `U_NAME`, `TABLE_NAME`, `READ`, `INSERT`, `UPDATE`, `DELETE`) values("
					+ objUser.getU_ID()
					+ ",'"
					+ objUser.U_NAME
					+ "','mabenh',1,1,1,1)";
			con.createQuery(sql).executeUpdate();// Table : mabenh
			sql = "insert into phanquyen (`U_ID`, `U_NAME`, `TABLE_NAME`, `READ`, `INSERT`, `UPDATE`, `DELETE`) values("
					+ objUser.getU_ID()
					+ ",'"
					+ objUser.U_NAME
					+ "','mathe',1,1,1,1)";
			con.createQuery(sql).executeUpdate();// Table : mathe
			sql = "insert into phanquyen (`U_ID`, `U_NAME`, `TABLE_NAME`, `READ`, `INSERT`, `UPDATE`, `DELETE`) values("
					+ objUser.getU_ID()
					+ ",'"
					+ objUser.U_NAME
					+ "','ma_cskcb',1,1,1,1)";
			con.createQuery(sql).executeUpdate();// Table : ma_cskcb
			sql = "insert into phanquyen (`U_ID`, `U_NAME`, `TABLE_NAME`, `READ`, `INSERT`, `UPDATE`, `DELETE`) values("
					+ objUser.getU_ID()
					+ ",'"
					+ objUser.U_NAME
					+ "','ma_dkbh',1,1,1,1)";
			con.createQuery(sql).executeUpdate();// Table : ma_dkbh

			sql = "insert into phanquyen (`U_ID`, `U_NAME`, `TABLE_NAME`, `READ`, `INSERT`, `UPDATE`, `DELETE`) values("
					+ objUser.getU_ID()
					+ ",'"
					+ objUser.U_NAME
					+ "','mst_xahuyen',1,1,1,1)";
			con.createQuery(sql).executeUpdate();// Table : mst_xahuyen
			sql = "insert into phanquyen (`U_ID`, `U_NAME`, `TABLE_NAME`, `READ`, `INSERT`, `UPDATE`, `DELETE`) values("
					+ objUser.getU_ID()
					+ ",'"
					+ objUser.U_NAME
					+ "','nhap_thuoc',1,1,1,1)";
			con.createQuery(sql).executeUpdate();// Table : nhap_thuoc
			sql = "insert into phanquyen (`U_ID`, `U_NAME`, `TABLE_NAME`, `READ`, `INSERT`, `UPDATE`, `DELETE`) values("
					+ objUser.getU_ID()
					+ ",'"
					+ objUser.U_NAME
					+ "','payment',1,1,1,1)";
			con.createQuery(sql).executeUpdate();// Table : payment

			sql = "insert into phanquyen (`U_ID`, `U_NAME`, `TABLE_NAME`, `READ`, `INSERT`, `UPDATE`, `DELETE`) values("
					+ objUser.getU_ID()
					+ ",'"
					+ objUser.U_NAME
					+ "','users',1,1,1,1)";
			con.createQuery(sql).executeUpdate();// Table : users
			sql = "insert into phanquyen (`U_ID`, `U_NAME`, `TABLE_NAME`, `READ`, `INSERT`, `UPDATE`, `DELETE`) values("
					+ objUser.getU_ID()
					+ ",'"
					+ objUser.U_NAME
					+ "','vendor',1,1,1,1)";
			con.createQuery(sql).executeUpdate();// Table : vendor

		} else if (objUser.LOAI.equals("XN")) {
			
			sql = "insert into phanquyen (`U_ID`, `U_NAME`, `TABLE_NAME`, `READ`, `INSERT`, `UPDATE`, `DELETE`) values("
					+ objUser.getU_ID()
					+ ",'"
					+ objUser.U_NAME
					+ "','action_log',1,1,1,1)";
			con.createQuery(sql).executeUpdate();// Table : action_log

			sql = "insert into phanquyen (`U_ID`, `U_NAME`, `TABLE_NAME`, `READ`, `INSERT`, `UPDATE`, `DELETE`) values("
					+ objUser.getU_ID()
					+ ",'"
					+ objUser.U_NAME
					+ "','chisoxn',1,1,1,1)";
			con.createQuery(sql).executeUpdate();// Table : chisoxn

			sql = "insert into phanquyen (`U_ID`, `U_NAME`, `TABLE_NAME`, `READ`, `INSERT`, `UPDATE`, `DELETE`) values("
					+ objUser.getU_ID()
					+ ",'"
					+ objUser.U_NAME
					+ "','ketqua_xn',1,1,1,1)";
			con.createQuery(sql).executeUpdate();// Table : ketqua_xn
			sql = "insert into phanquyen (`U_ID`, `U_NAME`, `TABLE_NAME`, `READ`, `INSERT`, `UPDATE`, `DELETE`) values("
					+ objUser.getU_ID()
					+ ",'"
					+ objUser.U_NAME
					+ "','kham_benh',1,1,1,1)";
			con.createQuery(sql).executeUpdate();// Table : kham_benh

		} 
	}
	private static void openMainDlg(Shell shell) {
		//
//		List<Users> listUsers = DbHelper.getSql2o().createQuery("select * from users").executeAndFetch(Users.class);
//		for(Users user: listUsers){
//			updatePhanQuyen(user);
//		}
		//
		switch(DbHelper.currentSessionUserId.LOAI.toLowerCase()){
			case "thuoc":
				MainQLThuoc dlgMain4 = new MainQLThuoc(shell, 0);
				dlgMain4.open();
				break;
			case "nv":
			case "bs":
			case "acct":
				//Main dlgMain = new Main(shell, 0);
				FormKhamBenhDlg dlgMain = new FormKhamBenhDlg(shell, 0);
				dlgMain.open();
				break;
			case "admin":
				MainAdmin dlg = new MainAdmin();
				dlg.open();
				break;
			default:
				MessageDialog.openError(shell, "Có lỗi", "Mở không được");
				//donothing
		}
	}
	protected static Object doLogin() throws java.net.ConnectException {
		try{
			Connection con = DbHelper.getSql2o();
			logger.info("Start login");
			String sql = "SELECT * FROM users where u_name=:u_name and u_pass=:u_pass";
			List<Users> list = con.createQuery(sql)
					.addParameter("u_name", txtId.getText())
					.addParameter("u_pass", txtPw.getText())
					.executeAndFetch(Users.class);
			logger.info(list.size());
			if (list.size() >= 1) {
				//
				DbHelper.setCurrentSessionUserId(list.get(0));
				//
				//loadCacheDB(con);
				DbHelper.logDB("" + txtId.getText() + " login successful");
				return list.get(0);
				//
			}
			DbHelper.logDB("Login lỗi user=" + txtId.getText() + " Pw=" + txtPw.getText());
			//
			return null;
		} catch (Sql2oException e) {
			logger.error(e);
			// e.printStackTrace();
			// MessageDialog.openError(shlLogin, "Có lỗi", e.getMessage());
			// return null;
			throw e;
		}
	}
	
	private static void loadCacheDB(Connection con) {
		logger.info("START Connection");
		// LOAD ALL MABENH
		String sql = "SELECT * FROM mabenh ORDER BY MABENH_RANK DESC";
//		logger.info("Get cache" + sql);
		java.util.List<Mabenh> list = con.createQuery(sql).executeAndFetch(Mabenh.class);
		for (Mabenh obj : list) {
			DbHelper.hashDataMabenh.put(obj.MABENH_ID, obj);
		}
		
		//System.out.println(" TEST IDC: "+DbHelper.getIDC10TenBenh("K00.1"));
		//System.out.println(" TEST IDC2: "+DbHelper.getIDC10TenBenh("K01.1;K02"));
		// LOAD ALL MABENH
		sql = "SELECT * FROM ma_cskcb ORDER BY MA_CODE";
		logger.info("Get cache" + sql);
		java.util.List<MaCskcb> listMaCskcbDTO = con.createQuery(sql).executeAndFetch(MaCskcb.class);
		for (MaCskcb obj : listMaCskcbDTO) {
			MaCskcbCache.cacheArrayListKey.add(obj.MA_CODE);
			MaCskcbCache.putMaCskcb(obj);
		}
		
		sql = "SELECT * FROM MST_LIEUDUNG ORDER BY RANK DESC";
		logger.info("Get LIEU DUNG " + sql);
		DbHelper.listDataMstLieuDung = con.createQuery(sql).executeAndFetch(MstLieudung.class);
		for (MstLieudung obj : DbHelper.listDataMstLieuDung) {
			DbHelper.hashDataMstLieuDung.put(obj.LIEUDUNG_NAME, obj);
		}
		
		sql = "SELECT * FROM vendor ORDER BY V_NAME and STS=0";
		logger.info("Get cache" + sql);
		DbHelper.listDataVendor = con.createQuery(sql).executeAndFetch(Vendor.class);
		for (Vendor obj : DbHelper.listDataVendor) {
			DbHelper.hashDataVendor.put(obj.V_NAME, obj);
		}
		sql = "SELECT * FROM khohang ORDER BY KHO_NAME";
		logger.info("Get cache" + sql);
		DbHelper.listDataKhohang = con.createQuery(sql).executeAndFetch(Khohang.class);
		for (Khohang obj : DbHelper.listDataKhohang) {
			DbHelper.hashDataKhoHang.put(obj.KHO_NAME, obj);
		}

		sql = "SELECT * from dichvu where MANHOM_9324='13' order by MA_DVKT ASC";
		List<Dichvu> listDichvu = con.createQuery(sql).executeAndFetch(Dichvu.class);
		for (Dichvu obj : listDichvu) {
			//cbKhoa.add(obj.MA_DVKT +"-"+obj.TEN_DVKT+"("+obj.DON_GIA2+")");
			DbHelper.listCongKham.add(obj);
			DbHelper.hashCongKham.put(obj.MA_DVKT +"-"+obj.TEN_DVKT+"("+obj.DON_GIA2+")", obj);
		}
		
		sql = "SELECT * FROM khoa_phong ORDER BY KP_MAKHOA";
		//logger.info("Get cache" + sql);
		List<KhoaPhong> listKhoaphong = con.createQuery(sql).executeAndFetch(KhoaPhong.class);
		for (KhoaPhong obj : listKhoaphong) {
			//KhoaPhongCache.cacheArrayListKey.add(obj.KP_MAKHOA);
			//KhoaPhongCache.putKhoaPhong(obj);
			DbHelper.hashKhoaPhongKP_MABH.put(obj.KP_MABH, obj);
			DbHelper.hashKhoaPhongMAKHOA.put(obj.KP_MAKHOA, obj);
		}
		
//		sql = "SELECT * FROM phanquyen where U_ID="+DbHelper.getCurrentSessionUserId();
//		//logger.info("Get cache" + sql);
//		List<Phanquyen> listPhanquyen = con.createQuery(sql).executeAndFetch(Phanquyen.class);
//		for (Phanquyen obj : listPhanquyen) {
//			DbHelper.hashDataPhanquyen.put(obj.TABLE_NAME, obj);
//		}
		sql = "SELECT * FROM users where LOAI='BS' order by U_NAME";
		//logger.info("Get cache" + sql);
		DbHelper.listUsers = con.createQuery(sql).executeAndFetch(Users.class);
		for (Users obj : DbHelper.listUsers) {
			DbHelper.hashDataUsers.put(obj.U_NAME, obj);
			DbHelper.hashDataUsersMaCCHN.put(obj.MACCHN, obj);
		}
		
		DbHelper.hashLoaiDichVu.put("1", "Xét nghiệm");
		DbHelper.hashLoaiDichVu.put("2", "Chẩn đoán hình ảnh");
		DbHelper.hashLoaiDichVu.put("3", "Thăm dò chức năng");
		DbHelper.hashLoaiDichVu.put("8", "Thủ thuật, phẫu thuật");
		DbHelper.hashLoaiDichVu.put("14", "Giường điều trị ngoại trú");
		DbHelper.hashLoaiDichVu.put("13", "Khám bệnh");
//		List<Row> listKhoaphong = DbHelper.getSql2o().open().createQuery(sql)
//				.executeAndFetchTable().rows();
//		for (Row obj : listKhoaphong) {
//			// cbKhoa.add(obj.getString("KP_MAKHOA"));
//			hashKhoaPhong.put(obj.getString("KP_MAKHOA"), obj.getString("KP_NAME"));
//		}
		//
		// LOAD ALL MABENH
		//
	}
}
