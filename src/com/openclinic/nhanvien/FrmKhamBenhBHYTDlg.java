package com.openclinic.nhanvien;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Monitor;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.custom.CBanner;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;

import com.model.cache.MaCskcbCache;
import com.model.dao.BenhNhan;
import com.model.dao.CtNhapthuoc;
import com.model.dao.Dichvu;
import com.model.dao.DvChitiet;
import com.model.dao.KhamBenh;
import com.model.dao.ThuocChitiet;
import com.openclinic.benhnhan.ListBenhNhanDlg;
import com.openclinic.utils.Utils;
import com.DbHelper;

import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;

import com.openclinic.Main;
import com.openclinic.TheBHXH;
import com.openclinic.DatePicker;

import org.eclipse.swt.widgets.Combo;
import org.sql2o.Connection;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;

public class FrmKhamBenhBHYTDlg extends Dialog {
	static Logger logger = LogManager.getLogger(FrmKhamBenhBHYTDlg.class
			.getName());

	protected Object result;
	protected Shell shellKhamBenh;

	public KhamBenh objKhamBenh;

	public int intTypeDlgKhamBenh;
	public static final int TYPE_DLG_VIEW = 1;
	public static final int TYPE_DLG_EDIT = 2;
	public int KIEU_THANH_TOAN = Utils.THANHTOAN_BHYT;

	private Text txtHoTen;
	private Text txtCanNang;
	private Text txtDiachi;
	private Text txtNoiDKBCB;
	private Text txtMA_DKBD;

	private BenhNhan objBenhNhan = new BenhNhan();
	private KhamBenh objPhieuKhamBenh;
	private DvChitiet objCongKham = new DvChitiet();

	private Combo cbKhoa;

	private TheBHXH txtMathe;

	private DatePicker txtTuNgay;

	private DatePicker txtDenNgay;

	private Button btnGIOITINH;

	private DatePicker txtNgaySinh;

	/**
	 * Create the dialog.
	 * 
	 * @param parent
	 * @param style
	 */
	public FrmKhamBenhBHYTDlg(Shell parent, int style) {
		super(parent, style);
		setText("SWT Dialog");
	}

	/**
	 * Open the dialog.
	 * 
	 * @return the result
	 */
	public Object open() {
		createContents();
		shellKhamBenh.open();
		shellKhamBenh.layout();
		Display display = getParent().getDisplay();
		//
		Monitor primary = display.getPrimaryMonitor();
		Rectangle bounds = primary.getBounds();
		Rectangle rect = shellKhamBenh.getBounds();
		int x = bounds.x + (bounds.width - rect.width) / 2;
		int y = bounds.y + (bounds.height - rect.height) / 2;
		shellKhamBenh.setLocation(x, y);

		startDialog();
		//
		while (!shellKhamBenh.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		return result;
	}

	private void startDialog() {
		shellKhamBenh.setText(Main.TITLE + " "
				+ DbHelper.currentSessionUserId.TEN_NHANVIEN + " ("
				+ DbHelper.currentSessionUserId.MACCHN + ")");
		loadCongkhamFromDB();
	}

	private void loadCongkhamFromDB() {
		if (objBenhNhan.BN_ID == null || objPhieuKhamBenh == null
				|| objPhieuKhamBenh.MA_LK == null) {
			return;
		}
		//
		try {
			// logger.info("LOAD CONGKHAM");
			Connection con = DbHelper.getSql2o();
			String sql = "select * from dv_chitiet where MA_LK="
					+ objPhieuKhamBenh.MA_LK + " and MA_NHOM=13";
			System.out.println(sql);
			List<DvChitiet> list = con.createQuery(sql).executeAndFetch(
					DvChitiet.class);
			cbKhoa.removeAll();
			for (DvChitiet obj : list) {
				cbKhoa.add(obj.MA_KHOA + "[" + obj.DON_GIA2 + "]");
			}
			//
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error(ex);
			MessageDialog.openError(shellKhamBenh, "Có lỗi", ex.getMessage());
		}

	}

	/**
	 * Create contents of the dialog.
	 */
	private void createContents() {
		shellKhamBenh = new Shell(getParent(), SWT.SHELL_TRIM | SWT.BORDER
				| SWT.PRIMARY_MODAL);
		shellKhamBenh.setSize(541, 443);
		shellKhamBenh.setText("Khám bệnh bảo hiểm y tế");
		shellKhamBenh.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.keyCode == SWT.ESC) {
					objKhamBenh = null;
				}
			}
		});
		shellKhamBenh.setLayout(null);

		Group grpThngTinBnh = new Group(shellKhamBenh, SWT.NONE);
		grpThngTinBnh.setBounds(10, 0, 516, 271);
		grpThngTinBnh.setFont(SWTResourceManager
				.getFont("Tahoma", 11, SWT.BOLD));
		grpThngTinBnh.setLayout(null);
		grpThngTinBnh.setText("Thông tin bệnh nhân");

		Label lblTnBnh = new Label(grpThngTinBnh, SWT.NONE);
		lblTnBnh.setBounds(9, 27, 54, 16);
		lblTnBnh.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lblTnBnh.setText("Tên bệnh");

		txtHoTen = new Text(grpThngTinBnh, SWT.BORDER);
		txtHoTen.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.keyCode == 13) {
					//
					ListBenhNhanDlg dlg = new ListBenhNhanDlg(shellKhamBenh, 0);
					dlg.strHoTen = txtHoTen.getText().trim();
					dlg.open();
					if (dlg.objBenhNhan != null) {
						objBenhNhan = dlg.objBenhNhan;
						//
						txtHoTen.setText(dlg.objBenhNhan.HO_TEN);
						txtDiachi.setText(dlg.objBenhNhan.DIA_CHI);
						txtMA_DKBD.setText(dlg.objBenhNhan.MA_DKBD);
						if(MaCskcbCache.getMaCskcb(dlg.objBenhNhan.MA_DKBD)==null){
							txtNoiDKBCB.setText(dlg.objBenhNhan.MA_DKBD);
						}
						else{
							txtNoiDKBCB.setText(MaCskcbCache.getMaCskcb(dlg.objBenhNhan.MA_DKBD).MA_NAME);
						}

						txtNgaySinh.setText(dlg.objBenhNhan.NGAY_SINH);
						txtTuNgay.setText(dlg.objBenhNhan.GT_THE_TU);
						txtDenNgay.setText(dlg.objBenhNhan.GT_THE_DEN);
						txtMathe.setText(dlg.objBenhNhan.MA_THE);
						if (dlg.objBenhNhan.GIOI_TINH == 1) {
							btnGIOITINH.setSelection(false);
							btnGIOITINH.setText("NAM(1)");
						} else {
							btnGIOITINH.setSelection(true);
							btnGIOITINH.setText("NỮ(2)");
						}
						//
					}
					//
				} else
					keyPress(e);
			}
		});
		txtHoTen.setBounds(80, 25, 336, 22);
		txtHoTen.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));

		Label lblNgySinh = new Label(grpThngTinBnh, SWT.NONE);
		lblNgySinh.setBounds(8, 57, 55, 16);
		lblNgySinh.setText("Ngày sinh");
		lblNgySinh
				.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));

		txtNgaySinh = new DatePicker(grpThngTinBnh, SWT.NONE);
		txtNgaySinh.setBounds(80, 52, 128, 28);

		Label lblCnNng = new Label(grpThngTinBnh, SWT.NONE);
		lblCnNng.setText("Cân nặng");
		lblCnNng.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lblCnNng.setBounds(214, 58, 55, 16);

		txtCanNang = new Text(grpThngTinBnh, SWT.BORDER);
		txtCanNang.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				checkCanNangTreem();
			}
		});
		txtCanNang
				.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtCanNang.setBounds(275, 52, 141, 22);
		txtCanNang.setTextLimit(5);

		Label lblGiiTnh = new Label(grpThngTinBnh, SWT.NONE);
		lblGiiTnh.setBounds(8, 92, 46, 16);
		lblGiiTnh.setText("Giới tính");
		lblGiiTnh.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));

		btnGIOITINH = new Button(grpThngTinBnh, SWT.TOGGLE);
		btnGIOITINH.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		btnGIOITINH.setBounds(80, 85, 86, 24);
		btnGIOITINH.setText("NAM(1)");
		btnGIOITINH.setSelection(false);
		btnGIOITINH.setImage(SWTResourceManager.getImage(
				FrmKhamBenhBHYTDlg.class, "/png/person-2x.png"));
		btnGIOITINH.setFont(SWTResourceManager
				.getFont("Tahoma", 10, SWT.NORMAL));
		btnGIOITINH.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.keyCode == '1') {
					btnGIOITINH.setText("NAM(1)");
					btnGIOITINH.setSelection(false);
				} else if (e.keyCode == '2') {
					btnGIOITINH.setText("NỮ(2)");
					btnGIOITINH.setSelection(true);
				}
			}
		});
		btnGIOITINH.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				boolean gt = btnGIOITINH.getSelection();
				if (gt == false) {
					btnGIOITINH.setText("NAM(1)");
				} else {
					btnGIOITINH.setText("NỮ(2)");
				}
			}
		});
		Label lblaCh = new Label(grpThngTinBnh, SWT.NONE);
		lblaCh.setText("Địa chỉ");
		lblaCh.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lblaCh.setBounds(9, 120, 54, 16);

		txtDiachi = new Text(grpThngTinBnh, SWT.BORDER);
		txtDiachi.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtDiachi.setBounds(80, 118, 336, 22);

		Label lblMTh = new Label(grpThngTinBnh, SWT.NONE);
		lblMTh.setBounds(9, 169, 39, 16);
		lblMTh.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lblMTh.setText("Mã thẻ");

		txtMathe = new TheBHXH(grpThngTinBnh, SWT.NONE);
		txtMathe.setBounds(80, 160, 410, 36);

		Label lblTNgy = new Label(grpThngTinBnh, SWT.NONE);
		lblTNgy.setText("Từ ngày");
		lblTNgy.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lblTNgy.setBounds(9, 202, 55, 16);

		txtTuNgay = new DatePicker(grpThngTinBnh, SWT.NONE);
		txtTuNgay.setBounds(80, 202, 128, 28);

		Label lblnNgy = new Label(grpThngTinBnh, SWT.NONE);
		lblnNgy.setText("Đến ngày");
		lblnNgy.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lblnNgy.setBounds(227, 202, 55, 16);

		txtDenNgay = new DatePicker(grpThngTinBnh, SWT.NONE);
		txtDenNgay.setBounds(296, 202, 128, 28);

		Label lblNikkcb = new Label(grpThngTinBnh, SWT.NONE);
		lblNikkcb.setText("Nơi ĐKKCB");
		lblNikkcb.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lblNikkcb.setBounds(9, 239, 67, 16);

		txtNoiDKBCB = new Text(grpThngTinBnh, SWT.BORDER);
		txtNoiDKBCB.setFont(SWTResourceManager
				.getFont("Tahoma", 10, SWT.NORMAL));
		txtNoiDKBCB.setBounds(80, 236, 336, 22);

		txtMA_DKBD = new Text(grpThngTinBnh, SWT.BORDER);
		txtMA_DKBD
				.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtMA_DKBD.setBounds(430, 236, 60, 22);

		Group grpThngTinKhm = new Group(shellKhamBenh, SWT.NONE);
		grpThngTinKhm.setFont(SWTResourceManager.getFont("Tahoma", 10,
				SWT.NORMAL));
		grpThngTinKhm.setBounds(10, 277, 516, 67);
		grpThngTinKhm.setText("Thông tin khám ban đầu");
		grpThngTinKhm.setLayout(null);

		Label lblKhoa = new Label(grpThngTinKhm, SWT.NONE);
		lblKhoa.setBounds(8, 19, 28, 16);
		lblKhoa.setText("Khoa");
		lblKhoa.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));

		cbKhoa = new Combo(grpThngTinKhm, SWT.NONE);
		cbKhoa.setToolTipText("Bấm ENTER để tìm bệnh");
		cbKhoa.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		cbKhoa.setBackground(SWTResourceManager.getColor(SWT.COLOR_YELLOW));
		cbKhoa.setBounds(78, 19, 428, 33);
		cbKhoa.setText("");

		Button button = new Button(shellKhamBenh, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				doSaveFormKhamBenh();
			}
		});
		button.setBounds(339, 360, 184, 34);
		button.setText("Lưu Phiếu (F2)");
		button.setImage(SWTResourceManager.getImage(FrmKhamBenhBHYTDlg.class,
				"/png/check-3x.png"));
		button.setFont(SWTResourceManager.getFont("Tahoma", 11, SWT.NORMAL));

		Button button_1 = new Button(shellKhamBenh, SWT.NONE);
		button_1.setBounds(81, 360, 123, 34);
		button_1.setText("Bỏ (ESC)");
		button_1.setImage(SWTResourceManager.getImage(FrmKhamBenhBHYTDlg.class,
				"/png/circle-x-3x.png"));
		button_1.setFont(SWTResourceManager.getFont("Tahoma", 11, SWT.NORMAL));

		Button button_2 = new Button(shellKhamBenh, SWT.NONE);
		button_2.setBounds(210, 360, 123, 34);
		button_2.setText("In Phiếu");
		button_2.setImage(SWTResourceManager.getImage(FrmKhamBenhBHYTDlg.class,
				"/png/print-3x.png"));
		button_2.setFont(SWTResourceManager.getFont("Tahoma", 11, SWT.NORMAL));

		loadKhamBenhDlgData();
		loadCongKhamData();
	}

	protected void doSaveFormKhamBenh() {
		if (saveFormKhamBenh() == false) {
			shellKhamBenh.close();
		}

	}

	protected boolean saveFormKhamBenh() {
		// waitingDlgShow();
		//
		boolean isError = false;
		if (txtMathe.txtMathe == null
				|| (txtMathe.txtMathe != null && txtMathe.txtMathe.trim()
						.length() == 0)) {
			System.out.println("SAVE TXTMATHE = " + txtMathe.txtMathe);
			txtMathe.setColor(SWTResourceManager.getColor(SWT.COLOR_RED));
			txtMathe.forceFocus();
			isError = true;
		} else {
			System.out.println("SAVE TXTMATHE2 = " + txtMathe.txtMathe);
			txtMathe.setColor(SWTResourceManager.getColor(SWT.COLOR_YELLOW));
		}
		if (txtMA_DKBD.getText().trim().length() == 0) {
			txtMA_DKBD.forceFocus();
			txtMA_DKBD
					.setBackground(SWTResourceManager.getColor(SWT.COLOR_RED));
			isError = true;
		} else {
			txtMA_DKBD.setBackground(SWTResourceManager
					.getColor(SWT.COLOR_YELLOW));
		}
		if (Utils.differenceInDay(txtTuNgay.getDate2(), txtDenNgay.getDate2()) <= 0) {
			txtDenNgay.setColor(SWTResourceManager.getColor(SWT.COLOR_RED));
			isError = true;
		} else {
			txtDenNgay.setColor(SWTResourceManager.getColor(SWT.COLOR_YELLOW));
		}

		//
		if (txtCanNang.getEditable() == true) {
			if (Utils.getDouble(txtCanNang.getText()) == 0.0) {
				System.out.println("SAVE CAN NANG");
				txtCanNang.forceFocus();
				txtCanNang.setBackground(SWTResourceManager
						.getColor(SWT.COLOR_RED));
				isError = true;
			} else {
				System.out.println("SAVE CAN NANG1");
				txtCanNang.setBackground(SWTResourceManager
						.getColor(SWT.COLOR_YELLOW));
			}
		} else {
			System.out.println("SAVE CAN NANG2");
			txtCanNang.setBackground(SWTResourceManager
					.getColor(SWT.COLOR_YELLOW));
		}

		if (txtHoTen.getText().trim().length() == 0) {
			txtHoTen.forceFocus();
			txtHoTen.setBackground(SWTResourceManager.getColor(SWT.COLOR_RED));
			isError = true;
		} else {
			txtHoTen.setBackground(SWTResourceManager
					.getColor(SWT.COLOR_YELLOW));
		}

		if (txtDiachi.getText().trim().length() == 0) {
			txtDiachi.forceFocus();
			txtDiachi.setBackground(SWTResourceManager.getColor(SWT.COLOR_RED));
			isError = true;
		} else {
			txtDiachi.setBackground(SWTResourceManager
					.getColor(SWT.COLOR_YELLOW));
		}

		if (cbKhoa.getText().trim().length() == 0) {
			cbKhoa.forceFocus();
			cbKhoa.setBackground(SWTResourceManager.getColor(SWT.COLOR_RED));
			isError = true;
		} else {
			cbKhoa.setBackground(SWTResourceManager.getColor(SWT.COLOR_YELLOW));
		}
		if (isError) {
			// waitingDlgHide();
			return isError;
		}
		//
		updateForm();
		//
		if (objBenhNhan.HO_TEN != null && objBenhNhan.HO_TEN.length() > 0) {
			// Do save
			try {
				logger.info("===BEGIN UPDATE THONG TIN LUOT KHAM BENH === ");
				objBenhNhan.insert();
			} catch (Exception e) {
				// logger.info("BEGIN LOAD BN");
				objBenhNhan = BenhNhan.load(objBenhNhan.MA_THE);
				// e.printStackTrace();
				//
			}
			if (objBenhNhan.BN_ID != null) {
				// Update ID key
				if (objPhieuKhamBenh == null) {
					objPhieuKhamBenh = new KhamBenh();
				}
				//
				objPhieuKhamBenh.BN_ID = objBenhNhan.BN_ID;
				objPhieuKhamBenh.TEN_BENH_NHAN = objBenhNhan.HO_TEN;
				//
				//
				objPhieuKhamBenh.BN_ID = objBenhNhan.BN_ID;
				//
				// logger.info("BEGIN SAVE PHIEU KHAM " + IS_UPDATE + " MALK="+
				// objPhieuKhamBenh.MA_LK);
				//
				//
				objPhieuKhamBenh.STT = 0;
				//
				objPhieuKhamBenh.insert();
				logger.info("===END UPDATE THONG TIN LUOT KHAM BENH === ");
				//
			}
		}
		// waitingDlgHide();
		return isError;
	}

	private void updateForm() {
		//
		// System.out.println("UPDATE " + txtHoten.getText());
		objBenhNhan.setMA_THE(txtMathe.strMathe);
		objBenhNhan.setGT_THE_TU(txtTuNgay.getDate());
		objBenhNhan.setGT_THE_DEN(txtDenNgay.getDate());

		objBenhNhan.setHO_TEN(txtHoTen.getText());
		objBenhNhan.setNGAY_SINH(txtNgaySinh.getDate());
		if (btnGIOITINH.getSelection() == true) {
			objBenhNhan.setGIOI_TINH(2);
		} else {
			objBenhNhan.setGIOI_TINH(1);
		}
		objBenhNhan.setDIA_CHI(txtDiachi.getText());
		//
		objBenhNhan.setMA_DKBD(txtMA_DKBD.getText());
		if( txtMathe.txtMaHuongChiPhiDichVuCao!=null ){
			objBenhNhan.setNGAY_CAP(txtMathe.txtNgayCap);
			objBenhNhan.setTEN_CHA_ME(txtMathe.txtTenChaMe);
			objBenhNhan.setMA_DT_SONG(Utils.getInt(txtMathe.txtMaHuongChiPhiDichVuCao));
			objBenhNhan.setTHOIDIEM_NAMNAM(txtMathe.txtThoiDiemHuongChiPhiDichVuCao);
			objBenhNhan.setCHUOI_KIEM_TRA(txtMathe.txtChuoiKiemTra);
			objBenhNhan.setMA_QUAN_LY(txtMathe.txtMaQuanLy);
		}
		//
		if (objPhieuKhamBenh == null) {
			objPhieuKhamBenh = new KhamBenh();
			objPhieuKhamBenh.BN_ID = objBenhNhan.BN_ID;
			objPhieuKhamBenh.KIEU_TT = KIEU_THANH_TOAN;
		}
		//
		//
	}

	protected void keyPress(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	private void saveKhamBenhDlg() {
		if (intTypeDlgKhamBenh == TYPE_DLG_VIEW) {
			return;
		}

		if (objKhamBenh == null) {
			objKhamBenh = new KhamBenh();
		}
		if (objKhamBenh != null) {

		}
		if (DbHelper.checkPhanQuyen(DbHelper.UPDATE, "kham_benh") == false) {
			logger.info("DON'T HAVE UPDATE RIGHT");
		} else if (DbHelper.checkPhanQuyen(DbHelper.INSERT, "kham_benh") == false) {
			logger.info("DON'T HAVE INSERT RIGHT");
		} else {
			objKhamBenh.insert();
		}
		shellKhamBenh.close();
	}

	public void setKhamBenhDlgData(KhamBenh obj) {
		this.objKhamBenh = obj;
	}

	public void loadKhamBenhDlgData() {
		// if(intTypeDlgKhamBenh==TYPE_DLG_VIEW){
		// btnNewButtonSaveKhamBenh.setEnabled(false);
		// }
		// else{
		// btnNewButtonSaveKhamBenh.setEnabled(true);
		// }

		if (DbHelper.checkPhanQuyen(DbHelper.READ, "kham_benh") == false) {
			logger.info("DON'T HAVE READ RIGHT");
			return;
		}
		if (objKhamBenh != null) {

		}
	}

	private void loadCongKhamData() {
		// Begin cong kham =
		String name = cbKhoa.getText();
		String names[] = name.split("-");
		//
		cbKhoa.removeAll();
		//
		//
		int idxFound = -1;
		int idx0 = -1;
		for (Dichvu obj : DbHelper.listCongKham) {
			// Dichvu obj = DbHelper.hashCongKham.get(key);
			if (obj.DON_GIA2 == 29000) {
				idx0++;
				cbKhoa.add(obj.MA_DVKT + "-" + obj.TEN_DVKT + "("
						+ obj.DON_GIA2 + ")");

				if (obj.MA_DVKT.indexOf(names[0]) > -1) {
					idxFound = idx0;
				}
			}

			if (obj.DON_GIA2 > 29000) {
				idx0++;
				cbKhoa.add(obj.MA_DVKT + "-" + obj.TEN_DVKT + "("
						+ obj.DON_GIA2 + ")");

				if (obj.MA_DVKT.indexOf(names[0]) > -1) {
					idxFound = idx0;
				}
			}
		}
		// Reload
		System.out.println("-------------IDX=" + idxFound);
		if (objCongKham != null) {
			cbKhoa.select(idxFound);
		} else {
			cbKhoa.select(-1);
		}
		//
	}

	protected void checkCanNangTreem() {
		System.out.println("CHECK CAN NANG");
		Calendar today = Calendar.getInstance();
		today.setTime(new Date());
		long dayDiff = Utils.differenceInDay(txtNgaySinh.getDate2(), today);

		if (dayDiff < 365) {
			System.out.println("dayDiff=" + dayDiff + " < 365");
			//
			txtCanNang.setEditable(true);
			txtCanNang.selectAll();
			// txtCanNang.forceFocus();
			//
		} else {
			System.out.println("dayDiff=" + dayDiff + " < 365");
			//
			txtCanNang.setEditable(false);
			txtCanNang.setText("");
			txtDiachi.forceFocus();
			// btnGIOITINH.forceFocus();
			//
		}

	}

}