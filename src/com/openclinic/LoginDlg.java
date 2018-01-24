package com.openclinic;

import java.io.File;
import java.io.RandomAccessFile;
import java.nio.channels.FileLock;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.program.Program;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Monitor;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;
import org.sql2o.Connection;
import org.sql2o.Sql2oException;

import swing2swt.layout.BorderLayout;

import com.CheckTheObj;
import com.DbHelper;
import com.model.cache.MaCskcbCache;
import com.model.dao.Configuration;
import com.model.dao.Dichvu;
import com.model.dao.KhoaPhong;
import com.model.dao.Khohang;
import com.model.dao.MaCskcb;
import com.model.dao.Mabenh;
import com.model.dao.MstLieudung;
import com.model.dao.Phanquyen;
import com.model.dao.Users;
import com.model.dao.Vendor;
import com.openclinic.khambenh.CheckTheDlg;
import com.openclinic.khambenh.FormKhamBenhDlg;
import com.openclinic.utils.Utils;

import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;

public class LoginDlg {
	static Logger logger = LogManager.getLogger(LoginDlg.class.getName());

	private static Text txtId;
	private static Text txtPw;

	private static INIFile ini;
	private static Shell shellLogin;

	private static Button button;

	private static Display display;

	private static String CHECKURL;
	private static String URL;
	private static String CHECKURL_OUTSIDE;
	private static String URL_OUTSIDE;
	private static Text textCode;
	protected static Image imageCodeISN;

	private static Canvas canvas;



	private static boolean isFileshipAlreadyRunning() {
	    // socket concept is shown at http://www.rbgrn.net/content/43-java-single-application-instance
	    // but this one is really great
	    try {
	        final File file = new File("openclinic_lock.txt");
	        final RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
	        final FileLock fileLock = randomAccessFile.getChannel().tryLock();
	        if (fileLock != null) {
	            Runtime.getRuntime().addShutdownHook(new Thread() {
	                public void run() {
	                    try {
	                        fileLock.release();
	                        randomAccessFile.close();
	                        file.delete();
	                    } catch (Exception e) {
	                        //log.error("Unable to remove lock file: " + lockFile, e);
	                    }
	                }
	            });
	            return true;
	        }
	    } catch (Exception e) {
	       // log.error("Unable to create and/or lock file: " + lockFile, e);
	    }
	    return false;
	}
	static {
		
	}
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		logger.info("Start app");
		//
		
		//
		Display display = Display.getDefault();
		if(!isFileshipAlreadyRunning()){
	        MessageDialog.openError(display.getActiveShell(), "Có lỗi. Co loi", "Lạy mấy má, con đã chạy rồi :D -- ");
	        //System.exit(1);
	    } 
		shellLogin = new Shell();
		shellLogin.setSize(452, 257);
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
		
		canvas = new Canvas(composite_1, SWT.NONE);
		canvas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				refreshCapcha();
			}
		});
		canvas.setBounds(80, 105, 64, 40);
		canvas.addPaintListener(new PaintListener() {
			public void paintControl(PaintEvent e) {
				if (imageCodeISN != null){
					canvas.setBackgroundImage(imageCodeISN);
				}
			}
		});
		
		textCode = new Text(composite_1, SWT.BORDER);
		textCode.setEnabled(false);
		textCode.setTextLimit(4);
		textCode.setFont(SWTResourceManager.getFont("Tahoma", 23, SWT.NORMAL));
		textCode.setBounds(153, 105, 102, 41);
		textCode.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPress(e);
			}
		});
		
		Label lblCode = new Label(composite_1, SWT.NONE);
		lblCode.setText("Code");
		lblCode.setFont(SWTResourceManager.getFont("Tahoma", 12, SWT.NORMAL));
		lblCode.setBounds(24, 105, 50, 27);

		Button btnCancel = new Button(composite_1, SWT.NONE);
		btnCancel.setText("Bỏ qua ");
		btnCancel.setFont(SWTResourceManager.getFont("Tahoma", 12, SWT.NORMAL));
		btnCancel.setBounds(48, 151, 121, 34);
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
		btnLogin.setBounds(184, 151, 171, 34);
		
		btnLogin.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				login();
			}
		});
		
		DatePicker datePicker = new DatePicker(composite_1, SWT.NONE);
		datePicker.setText("1/1/2017");
		datePicker.setBounds(48, 233, 136, 28);
		
		Button btnNewButton = new Button(composite_1, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				CheckTheDlg obj = new CheckTheDlg(shellLogin, 0);
				obj.open();
			}
		});
		btnNewButton.setBounds(204, 238, 68, 23);
		btnNewButton.setText("New Button");
		
		TheBHXH theBHXH = new TheBHXH(composite_1, SWT.NONE);
		theBHXH.setBounds(48, 191, 369, 36);
		
		
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
	protected static void refreshCapcha() {
		String fileName = CheckTheDlg.objBHYTThread2.getCapcha();
		System.out.println("FILE NAME=" + fileName);
		if (fileName != null && fileName.length() > 0) {
			//
			imageCodeISN = new Image(Display.getCurrent(), fileName);
			canvas.redraw();
			try {
				File file = new File(fileName);
				file.delete();
			} catch (Exception ee) {
				//
			}
			//
		}		
	}
	protected static void rememberLogin(boolean isRemember) {
		INIFile ini = new INIFile("openclinic.ini");
		if(isRemember==true){
			ini.setStringProperty("CONF", "LOGIN_ID", txtId.getText(), "Login ID");
			ini.setStringProperty("CONF", "LOGIN_PW", Encryptor.encrypt(txtPw.getText()), "Login PW");
			ini.setStringProperty("CONF", "USER_GATE_ID", Main.USER_GATE_ID, "Login USER_GATE_ID");
			ini.setStringProperty("CONF", "USER_GATE_PASSWORD", Main.USER_GATE_PASSWORD, "Login USER_GATE_PASSWORD");
			ini.setIntegerProperty("CONF", "REMEMBER", 1, "Rem");
			ini.setIntegerProperty("CONF", "MUCLUONGCOSO", Main.MAX_MUCLUONGCOSO, "Muc luong co so");
			ini.setIntegerProperty("CONF", "GIAKHAMVIENPHI", Main.GIAKHAMVIENPHI, "Gia cong kham vien phi");
			ini.setIntegerProperty("CONF", "TABLE_ID", Main.TABLE_ID, "Ban Kham");
		}
		else{
			ini.setStringProperty("CONF", "LOGIN_ID", txtId.getText(), "Login ID");
			ini.setStringProperty("CONF", "LOGIN_PW", "", "Login PW");
			ini.setStringProperty("CONF", "USER_GATE_ID", Main.USER_GATE_ID, "Login USER_GATE_ID");
			ini.setStringProperty("CONF", "USER_GATE_PASSWORD", Main.USER_GATE_PASSWORD, "Login USER_GATE_PASSWORD");
			ini.setIntegerProperty("CONF", "REMEMBER", 0, "Rem");
			ini.setIntegerProperty("CONF", "MUCLUONGCOSO", Main.MAX_MUCLUONGCOSO, "Muc luong co so");
			ini.setIntegerProperty("CONF", "GIAKHAMVIENPHI", Main.GIAKHAMVIENPHI, "Gia cong kham vien phi");
			ini.setIntegerProperty("CONF", "TABLE_ID", Main.TABLE_ID, "Ban Kham");
		}
		ini.save();
	}
	private static void startApp() {
		System.out.println("LOAD INI FILE");
		INIFile ini = new INIFile("openclinic.ini");
		//
		//System.out.println("CHECK URL="+CHECKURL);
		Main.serverIP = ini.getStringProperty("CONF", "SERVER");
		Integer tempInteger = ini.getIntegerProperty("CONF", "MUCLUONGCOSO");
		if(tempInteger==null){
			Main.MAX_MUCLUONGCOSO = 1150000;
		}
		else{
			Main.MAX_MUCLUONGCOSO = tempInteger.intValue();
		}
		tempInteger= ini.getIntegerProperty("CONF", "GIAKHAMVIENPHI");
		if(tempInteger==null){
			Main.GIAKHAMVIENPHI = 30000;
		}
		Main.GIAKHAMVIENPHI = tempInteger.intValue();
		//
		tempInteger= ini.getIntegerProperty("CONF", "TABLE_ID");
		if(tempInteger==null){
			Main.TABLE_ID = 0;
		}
		else{
			Main.TABLE_ID = tempInteger;
		}
		//
		Main.USER_GATE_ID = ini.getStringProperty("CONF", "USER_GATE_ID");
		if(Main.USER_GATE_ID==null || Main.USER_GATE_ID.length()==0){
			Main.USER_GATE_ID = "49172_BV";
		}
		Main.USER_GATE_PASSWORD = ini.getStringProperty("CONF", "USER_GATE_PASSWORD");
		if(Main.USER_GATE_PASSWORD==null|| Main.USER_GATE_PASSWORD.length()==0){
			Main.USER_GATE_PASSWORD = "ViNhanDan";
		}
		Main.DB_USER = ini.getStringProperty("CONF", "USERNAME");
		Main.DB_PASS = ini.getStringProperty("CONF", "PASSWORD");
		String DB_NAME = ini.getStringProperty("CONF", "DBNAME");
		//	public static final String DB_URL = "jdbc:mysql://pkap.ddns.net/openclinic?useUnicode=yes&characterEncoding=UTF-8";
		Main.DB_URL = "jdbc:mysql://"+Main.serverIP+"/"+DB_NAME+"?useUnicode=yes&characterEncoding=UTF-8";
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
		Utils.mstArrayTinhTrangPhieuKhamBenh.add(Utils.PHIEUKHAM_XOA_PHIEU_KHAM_0, "Xóa");
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

		//
		DbHelper.startConnection();
		//
		loadCacheDB(DbHelper.getSql2o());
		//
		if (CheckTheDlg.objBHYTThread2.httpclient == null) {
			String fileName = CheckTheDlg.objBHYTThread2.getCapcha();
			logger.info("CANVAS=" + fileName);
			if (fileName != null && fileName.length() > 0) {
				//
				imageCodeISN = new Image(Display.getCurrent(), fileName);
				canvas.redraw();
				try {
					File file = new File(fileName);
					file.delete();
				} catch (Exception ee) {
					//
				}
				//
			}
		} 
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
				// Do login checking system
				if( textCode.getText().trim().length()==4 ){
					//
					CheckTheDlg.objBHYTThread2.login2_del(Main.USER_GATE_ID, Main.USER_GATE_PASSWORD, textCode.getText().trim());
					//
				}
				else{
					CheckTheDlg.objBHYTThread2.login2(Main.USER_GATE_ID, Main.USER_GATE_PASSWORD, textCode.getText().trim());
				}
				//
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
			//
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
				DbHelper.logDB("" + txtId.getText() + " login successful. " + Main.TITLE);
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
	
	public static void checkVersion() {
//		String MESSAGE = Configuration.load(10).CONF_VALUE;
//		if(MESSAGE.length()>0){
//			if( Main.MESSAGE.equals(MESSAGE)==true){
//				// Not show
//			}
//			else{
//				MessageDialog.openInformation(shellLogin, "Thông báo mới", MESSAGE );
//				Main.MESSAGE = MESSAGE;
//			}
//		}
		//
		if(Main.isCheckVersion==true){
			//
			CHECKURL = Configuration.load(1).CONF_VALUE;
			URL = Configuration.load(2).CONF_VALUE;
			CHECKURL_OUTSIDE = Configuration.load(3).CONF_VALUE;
			URL_OUTSIDE = Configuration.load(4).CONF_VALUE;
			//System.out.println("CHECK URL="+CHECKURL);
			String strCHECKURL = CHECKURL;
			if( CHECKURL.indexOf(Main.serverIP)>-1 ){
				// Found in 
				strCHECKURL = CHECKURL;
			}
			else{
				strCHECKURL = CHECKURL_OUTSIDE;
			}
			System.out.println("strCHECKURL ="+strCHECKURL);
			long fileSize = DbHelper.objBHYTThread.getFileSize(strCHECKURL);
			System.out.println("fileSizeRemote ="+fileSize);
			long fileSize2 = 0; 
			try {
	            File file = new File("openclinic.jar");
	            fileSize2 = file.length();
	            System.out.println("openclinic.jar="+fileSize2);
	        } 
			catch (Exception e) {
				//e.printStackTrace();
	        }
			if(fileSize!=-1 && fileSize!=fileSize2){
				//
				logger.info("UPDATE NEW SOFTWARE " + fileSize +"@"+fileSize2);
				MessageDialog.openInformation(shellLogin, "Có phiên bản mới", "Phiên bản "+Main.TITLE+" đã cũ!.\nCó phiên bản mới, phần mềm tự cập nhật...");
				Program.launch("update.exe");
				System.exit(1);
				//
			}
			//
		}
	}
	
	private static void loadCacheDB(Connection con) {
		logger.info("START Connection");
		// LOAD ALL MABENH
		checkVersion();
		//
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
		
		sql = "SELECT * FROM mst_lieudung ORDER BY LIEUDUNG_ID ASC";
		logger.info("Get LIEU DUNG " + sql);
		DbHelper.listDataMstLieuDung = con.createQuery(sql).executeAndFetch(MstLieudung.class);
		for (MstLieudung obj : DbHelper.listDataMstLieuDung) {
			DbHelper.hashDataMstLieuDung.put(obj.LIEUDUNG_NAME, obj);
			DbHelper.hashDataMstLieuDungbyID.put(obj.LIEUDUNG_ID, obj);
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

		sql = "SELECT * from dichvu where (MANHOM_9324='13' || MANHOM_9324='14') order by MA_DVKT ASC";
		List<Dichvu> listDichvu = con.createQuery(sql).executeAndFetch(Dichvu.class);
		for (Dichvu obj : listDichvu) {
			//cbKhoa.add(obj.MA_DVKT +"-"+obj.TEN_DVKT+"("+obj.DON_GIA2+")");
			DbHelper.listCongKham.add(obj);
			//if(Utils.getInt(obj.MANHOM_9324)==13){
				DbHelper.hashCongKham.put(obj.DV_ID +"-"+obj.MA_DVKT +"-"+obj.TEN_DVKT+"("+obj.DON_GIA2+")", obj);
			//}
			//else{
				// CAP CUU
				//DbHelper.hashCongKham.put(obj.MA_DVKT, obj);
			//}
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
		sql = "SELECT * FROM users where LOAI='BS' order by U_ID";
		//logger.info("Get cache" + sql);
		DbHelper.listUsers = con.createQuery(sql).executeAndFetch(Users.class);
		for (Users obj : DbHelper.listUsers) {
			DbHelper.hashDataUsers.put(obj.U_ID+"-"+obj.TEN_NHANVIEN, obj);
			DbHelper.hashDataUsersMaCCHN.put(obj.MACCHN, obj);
		}
		sql = "SELECT * FROM users where LOAI='acct' or LOAI='nv' order by U_ID";
		//logger.info("Get cache" + sql);
		DbHelper.listUsersNhanVien = con.createQuery(sql).executeAndFetch(Users.class);
		for (Users obj : DbHelper.listUsersNhanVien) {
			DbHelper.hashDataUsersNhanVien.put(obj.U_ID+"-"+obj.TEN_NHANVIEN, obj);
		}
		
		DbHelper.hashLoaiDichVu.put("", "Dịch Vụ Kỹ Thuật");
		DbHelper.hashLoaiDichVu.put("1", "Xét nghiệm");
		DbHelper.hashLoaiDichVu.put("2", "Chẩn đoán hình ảnh");
		DbHelper.hashLoaiDichVu.put("3", "Thăm dò chức năng");
		DbHelper.hashLoaiDichVu.put("8", "Thủ thuật, phẫu thuật");
		DbHelper.hashLoaiDichVu.put("14", "Giường điều trị ngoại trú");
		DbHelper.hashLoaiDichVu.put("13", "Khám bệnh");
		DbHelper.hashLoaiDichVu.put("100", "DV Viện Phí");
		
		DbHelper.hashcheckThe.put(0,"Thông tin thẻ chính xác");
		DbHelper.hashcheckThe.put(1,"Thẻ hết giá trị sử dụng/Thẻ do BHXH Bộ Quốc Phòng quản lý, đề nghị kiểm tra thẻ và thông tin giấy tờ tùy thân");
		DbHelper.hashcheckThe.put(2,"Thẻ do BHXH Bộ Công An quản lý, đề nghị kiểm tra thẻ và thông tin giấy tờ tùy thân");
		DbHelper.hashcheckThe.put(10,"Thẻ hết giá trị sử dụng");
		DbHelper.hashcheckThe.put(51,"Mã thẻ không đúng");
		DbHelper.hashcheckThe.put(52,"Mã tỉnh cấp thẻ(kí tự thứ 4, 5 của mã thẻ) không đúng");
		DbHelper.hashcheckThe.put(53,"Mã quyền lợi thẻ(kí tự thứ 3 của mã thẻ) không đúng");
		DbHelper.hashcheckThe.put(50,"Khong thay thong tin the bhyt");
		DbHelper.hashcheckThe.put(3,"Hết hạn thẻ khi chưa ra viện");
		DbHelper.hashcheckThe.put(4,"Thẻ có giá trị khi đang nằm viện");
		DbHelper.hashcheckThe.put(401,"Thử lại lần nữa...");
		DbHelper.hashcheckThe.put(5,"KHÔNG THẤY THÔNG TIN THẺ BHYT..");
		DbHelper.hashcheckThe.put(6,"Thẻ sai họ tên");
		DbHelper.hashcheckThe.put(60,"Thẻ sai họ tên");
		DbHelper.hashcheckThe.put(61,"Thẻ sai họ tên(đúng kí tự đầu)");
		DbHelper.hashcheckThe.put(70,"Thẻ sai ngày sinh");
		DbHelper.hashcheckThe.put(7,"Thẻ sai ngày sinh");
		DbHelper.hashcheckThe.put(80,"Thẻ sai giới tính");
		DbHelper.hashcheckThe.put(8,"Thẻ sai giới tính");
		DbHelper.hashcheckThe.put(90,"Thẻ sai nơi đăng ký KCB ban đầu");
		DbHelper.hashcheckThe.put(9,"Thẻ sai nơi đăng ký KCB ban đầu");
		DbHelper.hashcheckThe.put(100,"Lỗi khi lấy dữ liệu sổ thẻ");
		DbHelper.hashcheckThe.put(101,"Lỗi server");
		DbHelper.hashcheckThe.put(110,"Thẻ đã thu hồi");
		DbHelper.hashcheckThe.put(120,"Thẻ đã báo giảm");
		DbHelper.hashcheckThe.put(121,"Thẻ đã báo giảm. Giảm chuyển ngoại tỉnh");
		DbHelper.hashcheckThe.put(122,"Thẻ đã báo giảm. Giảm chuyển nội tỉnh");
		DbHelper.hashcheckThe.put(123,"Thẻ đã báo giảm. Thu hồi do tăng lại cùng đơn vị");
		DbHelper.hashcheckThe.put(124,"Thẻ đã báo giảm. Ngừng tham gia");
		DbHelper.hashcheckThe.put(130,"Trẻ em không xuất trình thẻ");
		//
		

		//
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