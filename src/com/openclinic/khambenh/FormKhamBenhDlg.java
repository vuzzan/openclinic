package com.openclinic.khambenh;

import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.TraverseEvent;
import org.eclipse.swt.events.TraverseListener;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.internal.win32.OS;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
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
import org.sql2o.Sql2o;
import org.sql2o.data.Row;

import swing2swt.layout.BorderLayout;

import com.CheckTheObj;
import com.DbHelper;
import com.model.cache.MaCskcbCache;
import com.model.cache.MstXahuyenCache;
import com.model.dao.BenhNhan;
import com.model.dao.CtNhapthuoc;
import com.model.dao.Dichvu;
import com.model.dao.DvChitiet;
import com.model.dao.KhamBenh;
import com.model.dao.KhoaPhong;
import com.model.dao.Khohang;
import com.model.dao.MaCskcb;
import com.model.dao.Mabenh;
import com.model.dao.MstLieudung;
import com.model.dao.MstXahuyen;
import com.model.dao.Thuoc;
import com.model.dao.ThuocChitiet;
import com.model.dao.Users;
import com.openclinic.DatePicker;
import com.openclinic.Main;
import com.openclinic.SearchTextDlg;
import com.openclinic.TheBHXH;
import com.openclinic.TimerThread;
import com.openclinic.benhnhan.ListBenhNhanDlg;
import com.openclinic.utils.Utils;
import com.providers.ContentProviderBenhNhan;
import com.providers.ContentProviderCLS;
import com.providers.ContentProviderTHUOC;
import com.providers.TableLabelProviderBenhNhan;
import com.providers.TableLabelProviderCLS;
import com.providers.TableLabelProviderTHUOC;

public class FormKhamBenhDlg extends Dialog {
	static Logger logger = LogManager
			.getLogger(FormKhamBenhDlg.class.getName());

	protected Object result;
	protected Shell shellKhamBenh;
	private TheBHXH txtMathe;
	private Text txtHoten;
	private Combo cbNoiDKKB;
	private Text txtDiachi;
	private Label txtTenCSKCB;
	private Button btnGIOITINH;
	private Combo cbMaHuyen;
	private Button btnNewPhieu;
	private Button btnSave;

	private DatePicker txtTuNgay;
	private DatePicker txtDenNgay;
	private DatePicker txtNgaySinh;
	private Text txtDichVuCao;
	private Text txtTenChaMe;
	private Text txtMaQuanLy;
	private Text txtChuoiKiemTra;
	private DatePicker txtThoiDiem5Nam;
	private DatePicker txtNgayCap;
	private Label lblStatusPhieu;

	private Combo cbTenCSKCB;
	private Combo cbKhoa;
	private Combo cbMaBenh;
	private Label txtTenBenh;
	private Combo cbTenBenh;
	private Combo cbMabenhKhac;
	private Combo cbTenBenhPhu;
	private Text txtTenBenhPhu;

	private Button btnRadio1;
	private Button btnRadio2;
	private Button btnRadio3;
	private Button btnRadio4;
	private Button btnRadio5;
	private Text txtCanNang;
	private Label lblKg;
	private Table tableSelectedCLS;

	private TableViewer tableViewerSelectedCLS;

	private TabFolder tabFolder;
	protected boolean isShowDichVu = false;

	// private Hashtable<String, Dichvu> hashDichVu = new Hashtable<>();

	public int KIEU_THANH_TOAN = Utils.THANHTOAN_BHYT_2;
	public int CHI_XET_NGHIEM = 0;
	public int IS_UPDATE = Utils.FORM_NEW;

	private BenhNhan objBenhNhan = new BenhNhan();
	private KhamBenh objPhieuKhamBenh;
	private DvChitiet objCongKham = new DvChitiet();
	private ArrayList<DvChitiet> listCLSData = new ArrayList<>();
	private Hashtable<String, DvChitiet> hashDichVuChiTiet = new Hashtable<>();
	private ArrayList<ThuocChitiet> listThuocData = new ArrayList<>();
	// private Hashtable<String, ThuocChitiet> hashThuocChiTiet = new
	// Hashtable<>();
	private int TONGCONG_BHYT = 0;
	private int TONGCONG = 0;
	private int TONGCONG_BH = 0;
	private int TONGCONG_NB = 0;

	private int T_THUOC = 0;

	private boolean isSaveCLS;
	private boolean isSaveThuoc;
	private Table tableTHUOC;

	private TableViewer tableViewerTHUOC;
	private static Table tablePhieuKham;
	private static TableViewer tableViewerPhieuKham;

	private Button btnTHANHTOAN;

	public int intTypeDlgNhapThuoc = 1;
	//
	public static final int TYPE_DLG_NEW = 0;
	public static final int TYPE_DLG_VIEW = 1;
	public static final int TYPE_DLG_EDIT = 2;

	// Use for cap thuoc
	public CtNhapthuoc objCtNhapthuoc = null;
	private Text txtSearchCtNhapthuoc;
	private Text txtThuocSoLuongChiDinh;
	public int iSoLuongChiDinh = 0;
	private List<CtNhapthuoc> listDataCtNhapthuoc;
	private Hashtable<Integer, CtNhapthuoc> hashDataCtNhapthuoc = new Hashtable<Integer, CtNhapthuoc>();
	private Table tableCtNhapthuoc;
	private TableViewer tableViewerCtNhapthuoc;
	// User for can lam san
	private Table tableCLS;
	private Text txtSearchCLS;
	private TableViewer tableViewerCLS;
	public DvChitiet objDVChiTiet;
	private List<Dichvu> listDichVu;

	private Combo cbListBacSiCLS;

	private Combo cbListBacsiThuoc;
	private Composite compositeListPhieuKham;
	private FormData fd_compositeListPhieuKham;

	private ViewerFilter searchFilter;
	private Text txtSearchPhieuKham;

	public String strSearchPhieuKham = "";

	private SearchTextDlg dlgSearchTextDlg;

	private Button btnXetNghiem;

	private Button btnInPhieu;
	private Text txtBenhBanDau;

	private Combo cbBenhBanDau;

	private Button btnVp;
	//private Text txtLieuDung;

	private Combo cbLieuDung;

	private Combo cbListBacSiForm;

	private static TimerThread timerThread;
	public static List<Row> listData = null;
	private Text txtTongCong;
	private Text txtInfo;

	private Combo cbCapCuu;

	private Button btnHuyPhieu;

	private Combo cbKhoThuoc;

	protected Khohang objKhoHang = DbHelper.hashDataKhoHang.get("Kho bảo hiểm");

	private Label txtNgaySinhTheoThang;

	protected boolean isBarcode;

	protected CheckTheObj objCheck = null;

	private org.eclipse.swt.widgets.List listKhamBenh;

	protected boolean isCapCuu;

	private static DateTime dateTime0;

	private static DateTime dateTime1;

	private static Combo comboKieuThanhToan;

	private static Combo comboNhanVien;

	private static Combo comboTinhTrangThanhToan;

	private static Label lbTONG_BN;
	private static long tongBN = 0;
	private static long tongChi = 0;	
	private static Button btnCheckButtonNgayXem;
	private static Button btnCheckButtonTatCaPhieu;
	private static Button btnCheckButtonTatCaNV;
	private static Button btnCheckButtonTatCaTHANHTOAN;
	
	private static boolean btnCheckButtonNgayXemDta = false;
	private static boolean  btnCheckButtonTatCaPhieuDta= false;
	private static boolean  btnCheckButtonTatCaNVDta= false;
	private static boolean  btnCheckButtonTatCaTHANHTOANDta= false;
	
	/**
	 * Create the dialog.
	 * 
	 * @param parent
	 * @param style
	 */
	public FormKhamBenhDlg(Shell parent, int style) {
		super(parent, style);
		setText("SWT Dialog");
	}

	/**
	 * Open the dialog.
	 * 
	 * @return the result
	 */
	public Object open() {
		shellKhamBenh = new Shell(getParent(), SWT.DIALOG_TRIM | SWT.MIN
				| SWT.MAX | SWT.PRIMARY_MODAL);
		shellKhamBenh.addDisposeListener(new DisposeListener() {
			public void widgetDisposed(DisposeEvent e) {
				if (timerThread != null) {
					timerThread.isStop = true;
				}
			}
		});
		shellKhamBenh.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				// System.out.println("FOCUS shellKhamBenh");
				// if (txtMathe != null)
				// txtMathe.forceFocus();
			}
		});
		shellKhamBenh.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPress(e);
			}
		});
		shellKhamBenh.setSize(1046, 723);
		shellKhamBenh.setText("Phiên Khám Bệnh");

		createContents();

		Display display = getParent().getDisplay();
		// Move to center
		Monitor primary = display.getPrimaryMonitor();
		Rectangle bounds = primary.getBounds();
		Rectangle rect = shellKhamBenh.getBounds();
		int x = bounds.x + (bounds.width - rect.width) / 2;
		int y = bounds.y + (bounds.height - rect.height) / 2;
		shellKhamBenh.setLocation(x, y);
		//
		comboNhanVien.removeAll();
		comboNhanVien.add("Tất cả");
		//comboNhanVien.add("NV Login");
		for (Users obj : DbHelper.listUsersNhanVien) {
			comboNhanVien.add(obj.U_ID + "-"+obj.TEN_NHANVIEN);
			//
		}
		comboNhanVien.select(0);
		//
		// End move to center
		logger.info("LOAD KIEU_THANH_TOAN = " + KIEU_THANH_TOAN);
		doSearchBenhNhan();
		
		startDialog();
		//
		timerThread = new TimerThread();
		timerThread.start();
		// shellKhamBenh.setMaximized(true);
		shellKhamBenh.open();
		shellKhamBenh.layout();
		while (!shellKhamBenh.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		return result;
	}

	protected void doSavePhieuXetNghiem() {
		boolean isError = false;
		String errorText = "Có lỗi: \n";
		if (txtHoten.getText().trim().length() == 0) {
			tabFolder.setSelection(0);
			txtHoten.forceFocus();
			txtHoten.setBackground(SWTResourceManager.getColor(SWT.COLOR_RED));
			errorText = "- Nhập họ tên: \n";
			isError = true;
		} else {
			txtHoten.setBackground(SWTResourceManager
					.getColor(SWT.COLOR_YELLOW));
		}
		if (txtDiachi.getText().trim().length() == 0) {
			tabFolder.setSelection(0);
			txtDiachi.forceFocus();
			txtDiachi.setBackground(SWTResourceManager.getColor(SWT.COLOR_RED));
			errorText = "- Nhập địa chỉ: \n";
			isError = true;
		} else {
			txtDiachi.setBackground(SWTResourceManager
					.getColor(SWT.COLOR_YELLOW));
		}

		if (listCLSData == null || listCLSData.size() == 0) {
			objPhieuKhamBenh = new KhamBenh();
			objPhieuKhamBenh.BN_ID = objBenhNhan.BN_ID;
			objPhieuKhamBenh.KIEU_TT = KIEU_THANH_TOAN;
			//
			tabFolder.setSelection(1);
			tableSelectedCLS.forceFocus();
			errorText = "- Nhập ít nhất 1 CLS: \n";
			isError = true;
		}
		if (isError) {
			MessageDialog.openError(shellKhamBenh, "Có lỗi", errorText);
			return;
		}
		
		if (objPhieuKhamBenh == null) {
			setCHI_XET_NGHIEM(1);
			KIEU_THANH_TOAN = Utils.THANHTOAN_MUA_CLS;
		} else {
			if (objPhieuKhamBenh.STS > 0) {
				//
				//
			}
		}
		if (objCongKham != null) {
			//
			if (MessageDialog.openConfirm(shellKhamBenh, "Lưu ý", "Phiếu đã lưu dưới dạng Viện Phí, có chắc chắn muốn chuyển thành MuaCLS??") == true) {
				// remove cong kham
				objCongKham.deleteRow();
				objCongKham = null;
				//
				setCHI_XET_NGHIEM(1);
				KIEU_THANH_TOAN = Utils.THANHTOAN_MUA_CLS;
			} else {
				return;
			}
			//
		}
		if (savePhieuXetNghiem() == false) {
			//
			doSearchBenhNhan();
			refreshPhieuKham();
		}
	}

	protected void doSaveFormKhamBenh() {

		if (objPhieuKhamBenh == null) {
			setCHI_XET_NGHIEM(0);
		}
		if (CHI_XET_NGHIEM == 1) {
		}
		if (saveFormKhamBenh() == false) {
			doSearchBenhNhan();
			refreshPhieuKham();
		}
	}

	private void refreshPhieuKham() {
		tableViewerSelectedCLS.refresh();
		tableViewerTHUOC.refresh();
		reloadTableChitietNhapthuoc();
	}

	protected void doViewCLS() {
		//
		// if(DbHelper.currentSessionUserId.LOAI.toUpperCase().equals("BS")==false){
		// if(DbHelper.currentSessionUserId.LOAI.toUpperCase().equals("NV")==false){
		// String message = "Không có quyền chỉ định CLS/Dịch vụ";
		// MessageDialog.openError(shellKhamBenh, "Có lỗi", message );
		// logger.info(message);
		// return;
		// }
		// }
		if (tableSelectedCLS.getSelectionCount() == 0) {
			return;
		}
		TableItem item = tableSelectedCLS.getSelection()[0];
		DvChitiet objDVChiTiet = (DvChitiet) item.getData();
		//
		// DichVuXetNghiemDlg dlg = new DichVuXetNghiemDlg(shellKhamBenh, 0);
		// dlg.objDVChiTiet = objDVChiTiet;
		ListCLSDlg dlg = new ListCLSDlg(shellKhamBenh, 0);
		dlg.objDVChiTiet = objDVChiTiet;
		dlg.open();
		// System.out.println("LOAD objDVChiTiet="+objDVChiTiet.hashCode());
		objDVChiTiet.MA_KHOA = DbHelper.getMAKHOA(cbKhoa.getText());
		//
		updateTinhTien();
		//
		tableViewerSelectedCLS.refresh();
		//
	}

	protected void loadCanLamSan() {
		if (objBenhNhan.BN_ID == null || objPhieuKhamBenh == null
				|| objPhieuKhamBenh.MA_LK == null) {
			System.out.println("NULL CLS");
			return;
		}
		//
		try {
			// System.out.println("LOAD CLS");
			Connection con = DbHelper.getSql2o();
			String sql = "select * from dv_chitiet where MA_LK="
					+ objPhieuKhamBenh.MA_LK + " and (MA_NHOM<>13 and MA_NHOM<>14)";
			// System.out.println(sql);
			List<DvChitiet> list = con.createQuery(sql).executeAndFetch(
					DvChitiet.class);
			// System.out.println("size=" + list.size());
			// con.close();
			listCLSData.clear();
			hashDichVuChiTiet.clear();
			for (int i = 0; i < list.size(); i++) {
				DvChitiet obj = (DvChitiet) list.get(i);
				listCLSData.add(obj);
				hashDichVuChiTiet.put(obj.MA_DICH_VU, obj);
			}
			tableViewerSelectedCLS.refresh();
		} catch (Exception ex) {
			// ex.printStackTrace();
			logger.error(ex);
			MessageDialog.openError(shellKhamBenh, "Có lỗi", ex.getMessage());
		}
	}

	protected void newDichVuCLS() {
		//
		if (DbHelper.currentSessionUserId.LOAI.toUpperCase().equals("BS") == false) {
			if (DbHelper.currentSessionUserId.LOAI.toUpperCase().equals("NV") == false) {
				String message = "Không có quyền chỉ định CLS/Dịch vụ";
				MessageDialog.openError(shellKhamBenh, "Có lỗi", message);
				logger.info(message);
				return;
			} else {
				if (listCLSData != null && listCLSData.size() >= 1) {
					String message = "Không có quyền chỉ định CLS/Dịch vụ";
					MessageDialog.openError(shellKhamBenh, "Có lỗi", message);
					logger.info(message);
					return;
				}
			}
		}
		// ======================================================
		//
		// ======================================================
		if (objPhieuKhamBenh == null || objPhieuKhamBenh.MA_LK == null) {
			if (KIEU_THANH_TOAN == Utils.THANHTOAN_VIENPHI) {
				//
				objPhieuKhamBenh = new KhamBenh();
				objPhieuKhamBenh.BN_ID = objBenhNhan.BN_ID;
				//
			} else {
				return;
			}
		}
		// ======================================================
		//
		// ======================================================
		DvChitiet objDVChiTiet = new DvChitiet();
		objDVChiTiet.BN_ID = objBenhNhan.BN_ID;
		objDVChiTiet.MA_LK = objPhieuKhamBenh.MA_LK;
		objDVChiTiet.MA_DICH_VU = "";
		objDVChiTiet.TEN_DICH_VU = "";
		//
		ListCLSDlg dlg = new ListCLSDlg(shellKhamBenh, 0);
		dlg.objDVChiTiet = objDVChiTiet;
		dlg.objDVChiTiet.DV_ID = listCLSData.size() + 1;
		dlg.open();
		//
		objDVChiTiet.MA_KHOA = DbHelper.getMAKHOA(cbKhoa.getText());
		objDVChiTiet.STS = 0;
		if (objDVChiTiet.MA_DICH_VU != null
				&& objDVChiTiet.MA_DICH_VU.length() > 0) {
			listCLSData.add(objDVChiTiet);
			hashDichVuChiTiet.put(objDVChiTiet.MA_DICH_VU, objDVChiTiet);
		}
		//
		updateTinhTien();
		// ======================================================
		//
		// ======================================================
		tableViewerSelectedCLS.refresh();
		// ======================================================
		//
		// ======================================================
	}

	protected void checkCanNangTreem() {
		Calendar today = Calendar.getInstance();
		today.setTime(new Date());
		long dayDiff = Utils.differenceInDay(txtNgaySinh.getDate2(), today);

		if (dayDiff < 365) {
			// System.out.println("dayDiff="+dayDiff+" < 365");
			//
			txtCanNang.setEditable(true);
			// txtCanNang.setEnabled(true);
			// txtCanNang.setText("4");
			txtCanNang.selectAll();
			txtCanNang.forceFocus();
			//
		} else {
			// System.out.println("dayDiff="+dayDiff+" < 365");
			//
			txtCanNang.setEditable(false);
			txtCanNang.setText("");
			// btnGIOITINH.forceFocus();
			//
		}

	}

	protected void checkTheoTenBenhNhan() {
		//
		try {
			Connection con = DbHelper.getSql2o();
			String hoten = txtHoten.getText().trim().toLowerCase();
			String sql = "select * from benh_nhan where LOWER(HO_TEN) like '%"
					+ hoten + "%'";
			// System.out.println(sql);
			List<BenhNhan> listBenhNhan = con.createQuery(sql).executeAndFetch(
					BenhNhan.class);
			// con.close();
			if (listBenhNhan != null && listBenhNhan.size() >= 1) {
				//
				ListBenhNhanDlg dlg = new ListBenhNhanDlg(shellKhamBenh, 0);
				dlg.strHoTen = txtHoten.getText();
				dlg.strMathe = "";
				dlg.open();
				//
				if (dlg.objBenhNhan != null) {
					objBenhNhan = dlg.objBenhNhan;
					updateDlg();
					cbKhoa.forceFocus();
				} else {
					txtHoten.selectAll();
					txtHoten.forceFocus();
				}
				//
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error(ex);
			MessageDialog.openError(shellKhamBenh, "Có lỗi", ex.getMessage());
		}

	}

	protected void checkMaTheAndLoad() {
		String mathe = txtMathe.getMaThe();
		if (mathe.trim().length() == 0) {
			return;
		}
		//
		try {
			Connection con = DbHelper.getSql2o();
			String str[] = cbTenBenhPhu.getText().split("-");
			String sql = "select * from benh_nhan where MA_THE='" + mathe + "'";
			// System.out.println(sql);
			List<BenhNhan> listBenhNhan = con.createQuery(sql).executeAndFetch(BenhNhan.class);
			// con.close();
			if (listBenhNhan != null && listBenhNhan.size() == 1) {
				//
				objBenhNhan = listBenhNhan.get(0);
				updateDlg();
				//
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error(ex);
			MessageDialog.openError(shellKhamBenh, "Có lỗi", ex.getMessage());
		}
		//

	}

	protected void updateFormFromMaThe() {
		if (txtMathe.txtMathe != null) {
			System.out.println("UPDATE FROM TU BARCODE");
			// Read by barcode
			objBenhNhan.setMA_THE(txtMathe.txtMathe);
			objBenhNhan.setHO_TEN(txtMathe.txtHoTen);
			objBenhNhan.setNGAY_SINH(txtMathe.txtNgaySinh);
			objBenhNhan.setGIOI_TINH(Utils.getInt(txtMathe.txtGioiTinh));
			objBenhNhan.setDIA_CHI(txtMathe.txtDiaChi);
			objBenhNhan.setMA_DKBD(txtMathe.txtMaDKKCBBD);
			objBenhNhan.setGT_THE_TU(txtMathe.txtTuNgay);
			objBenhNhan.setGT_THE_DEN(txtMathe.txtDenNgay);

			objBenhNhan.setNGAY_CAP(txtMathe.txtNgayCap);
			objBenhNhan.setTEN_CHA_ME(txtMathe.txtTenChaMe);
			objBenhNhan.setMA_DT_SONG(Utils
					.getInt(txtMathe.txtMaHuongChiPhiDichVuCao));
			objBenhNhan
					.setTHOIDIEM_NAMNAM(txtMathe.txtThoiDiemHuongChiPhiDichVuCao);
			objBenhNhan.setCHUOI_KIEM_TRA(txtMathe.txtChuoiKiemTra);
			objBenhNhan.setMA_QUAN_LY(txtMathe.txtMaQuanLy);
			//
			if (objBenhNhan.BN_ID != null) {
				if (objPhieuKhamBenh != null) {
					objPhieuKhamBenh.BN_ID = objBenhNhan.BN_ID;
				}
			}
			updateDlg();
		}
	}

	private void updateForm() {
		//
		// System.out.println("UPDATE " + txtHoten.getText());
		if (KIEU_THANH_TOAN == Utils.THANHTOAN_BHYT
				|| KIEU_THANH_TOAN == Utils.THANHTOAN_BHYT_2) {
			objBenhNhan.setMA_THE(txtMathe.strMathe);
			objBenhNhan.setMA_DKBD(cbNoiDKKB.getText());
			objBenhNhan.setGT_THE_TU(txtTuNgay.getDate());
			objBenhNhan.setGT_THE_DEN(txtDenNgay.getDate());
			objBenhNhan.setNGAY_CAP(txtNgayCap.getDate());
			objBenhNhan.setTEN_CHA_ME(txtTenChaMe.getText());
			objBenhNhan.setMA_DT_SONG(Utils.getInt(txtDichVuCao.getText()));
			objBenhNhan.setTHOIDIEM_NAMNAM(txtThoiDiem5Nam.getDate());
			objBenhNhan.setCHUOI_KIEM_TRA(txtChuoiKiemTra.getText());
			objBenhNhan.setMA_QUAN_LY(txtMaQuanLy.getText());
		} else if (KIEU_THANH_TOAN == Utils.THANHTOAN_VIENPHI
				|| KIEU_THANH_TOAN == Utils.THANHTOAN_TAI_KHAM
				|| KIEU_THANH_TOAN == Utils.THANHTOAN_VIENPHI_FREE) {
			logger.info("Update MA THE " + txtMathe.strMathe);
			if (txtMathe.strMathe.length() == 15) {
				objBenhNhan.setMA_THE(txtMathe.strMathe);
			} else {
				objBenhNhan.setMA_THE("");
			}
			if (objBenhNhan.MA_DKBD == null
					|| (objBenhNhan.MA_DKBD.length() == 0)) {
				objBenhNhan.setMA_DKBD("VP");
				objBenhNhan.setGT_THE_TU("");
				objBenhNhan.setGT_THE_DEN("");
				objBenhNhan.setNGAY_CAP("");
				objBenhNhan.setTEN_CHA_ME("");
				objBenhNhan.setMA_DT_SONG(0);
				objBenhNhan.setTHOIDIEM_NAMNAM("");
				objBenhNhan.setCHUOI_KIEM_TRA("");
				objBenhNhan.setMA_QUAN_LY("");
			}
		}
		objBenhNhan.setHO_TEN(txtHoten.getText());
		objBenhNhan.setNGAY_SINH(txtNgaySinh.getDate());
		if (btnGIOITINH.getSelection() == true) {
			objBenhNhan.setGIOI_TINH(2);
			btnGIOITINH.setSelection(true);
		} else {
			objBenhNhan.setGIOI_TINH(1);
			btnGIOITINH.setSelection(false);
		}
		objBenhNhan.setDIA_CHI(txtDiachi.getText());
		//
		if (objPhieuKhamBenh == null) {
			objPhieuKhamBenh = new KhamBenh();
			objPhieuKhamBenh.BN_ID = objBenhNhan.BN_ID;
			objPhieuKhamBenh.KIEU_TT = KIEU_THANH_TOAN;
		}
		objPhieuKhamBenh.TABLE_ID = Main.TABLE_ID;
		objPhieuKhamBenh.NV_ID = DbHelper.currentSessionUserId.U_ID;
		objPhieuKhamBenh.NV_NAME  = DbHelper.currentSessionUserId.U_NAME;
		//
		//
	}

	private boolean savePhieuXetNghiem() {
		//
		boolean isError = false;
		String errorText = "Có lỗi: \n";
		if (txtHoten.getText().trim().length() == 0) {
			tabFolder.setSelection(0);
			txtHoten.forceFocus();
			txtHoten.setBackground(SWTResourceManager.getColor(SWT.COLOR_RED));
			errorText = "- Nhập họ tên: \n";
			isError = true;
		} else {
			txtHoten.setBackground(SWTResourceManager
					.getColor(SWT.COLOR_YELLOW));
		}
		if (txtDiachi.getText().trim().length() == 0) {
			tabFolder.setSelection(0);
			txtDiachi.forceFocus();
			txtDiachi.setBackground(SWTResourceManager.getColor(SWT.COLOR_RED));
			errorText = "- Nhập địa chỉ: \n";
			isError = true;
		} else {
			txtDiachi.setBackground(SWTResourceManager
					.getColor(SWT.COLOR_YELLOW));
		}

		if (listCLSData == null || listCLSData.size() == 0) {
			objPhieuKhamBenh = new KhamBenh();
			objPhieuKhamBenh.BN_ID = objBenhNhan.BN_ID;
			objPhieuKhamBenh.KIEU_TT = KIEU_THANH_TOAN;
			//
			tabFolder.setSelection(1);
			tableSelectedCLS.forceFocus();
			errorText = "- Nhập ít nhất 1 CLS: \n";
			isError = true;
		}
		if (isError) {
			MessageDialog.openError(shellKhamBenh, "Có lỗi", errorText);
			return isError;
		}
		
		setCHI_XET_NGHIEM(1);
		KIEU_THANH_TOAN = Utils.THANHTOAN_MUA_CLS;
		
		selectVienPhi();

		
		//
		updateForm();
		//
		//
		if (objBenhNhan.HO_TEN != null && objBenhNhan.HO_TEN.length() > 0) {
			// Do save
			try {
				// logger.info("BEGIN SAVE BN");
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
				//
				objPhieuKhamBenh.KIEU_TT = Utils.THANHTOAN_VIENPHI;
				// System.out.println("objPhieuKhamBenh.KIEU_TT = "
				// + objPhieuKhamBenh.KIEU_TT + " KIEUTT="
				// + KIEU_THANH_TOAN);
				//
				objPhieuKhamBenh.BN_ID = objBenhNhan.BN_ID;
				objPhieuKhamBenh.TEN_BENH_NHAN = objBenhNhan.HO_TEN;
				//
				logger.info("BEGIN SAVE PHIEU KHAM " + IS_UPDATE + " MALK="
						+ objPhieuKhamBenh.MA_LK);
				//
				updateTinhTien();
				//
				//
				logger.info("=========BEGIN UPDATE KHAM BENH === listThuocData.size()="
						+ listThuocData.size() + " sts=" + objPhieuKhamBenh.STS);
				if (listThuocData.size() > 0) {
					// DA CHO THUOC, UPDATE STATUS OF PHIEU KHAM BENH
					objPhieuKhamBenh.STS = Utils.updateStatusPhieuKham(
							objPhieuKhamBenh.STS,
							Utils.PHIEUKHAM_KHAMXONG_CHO_LAYTHUOC);
					//
					logger.info("=========STS KHAM BENH === "
							+ objPhieuKhamBenh.STS);
				}
				objPhieuKhamBenh.CHANDOAN_BD = txtBenhBanDau.getText();
				objPhieuKhamBenh.KIEU_TT = Utils.THANHTOAN_MUA_CLS;
				// Check is Thanh toan
				boolean isThanhToan = true;
				
				for (DvChitiet obj : listCLSData) {
					if(obj.THANHTOAN==0){
						isThanhToan = false;
						break;
					}
				}
				for (ThuocChitiet obj : listThuocData) {
					if(obj.THANHTOAN==0){
						isThanhToan = false;
						break;
					}
				}
				if(isThanhToan==false){
					objPhieuKhamBenh.NGAY_TTOAN = "";
				}
				else{
					objPhieuKhamBenh.NGAY_TTOAN = Utils.getDatetime(new Date(), "yyyyMMddHHmm");
				}
				//
				updateBenhNhan2PhieuKham(objBenhNhan, objPhieuKhamBenh);
				//
				objPhieuKhamBenh.insert();
				//
				//
				for (DvChitiet obj : listCLSData) {
					obj.BN_ID = objPhieuKhamBenh.BN_ID;
					obj.MA_LK = objPhieuKhamBenh.MA_LK;
					if (obj.STS == -1) {
						obj.STS = 0;
					}
					
					obj.insert();
				}
				//
			}
		}
		return isError;
	}

	private void updateBenhNhan2PhieuKham(BenhNhan objBenhNhan0, KhamBenh objPhieuKhamBenh0) 
	{
		if(objBenhNhan0==null || objPhieuKhamBenh0==null){
			return;
		}
		objPhieuKhamBenh0.NGAY_SINH = objBenhNhan0.NGAY_SINH;
		objPhieuKhamBenh0.GIOI_TINH = objBenhNhan0.GIOI_TINH;
		objPhieuKhamBenh0.DIA_CHI = objBenhNhan0.DIA_CHI;
		objPhieuKhamBenh0.MA_THE = objBenhNhan0.MA_THE;
		objPhieuKhamBenh0.MA_DKBD = objBenhNhan0.MA_DKBD;
		objPhieuKhamBenh0.GT_THE_TU = objBenhNhan0.GT_THE_TU;
		objPhieuKhamBenh0.GT_THE_DEN = objBenhNhan0.GT_THE_DEN;
		objPhieuKhamBenh0.NGAY_CAP = objBenhNhan0.NGAY_CAP;
		objPhieuKhamBenh0.MA_QUAN_LY = objBenhNhan0.MA_QUAN_LY;
		objPhieuKhamBenh0.TEN_CHA_ME = objBenhNhan0.TEN_CHA_ME;
		objPhieuKhamBenh0.MA_DT_SONG = objBenhNhan0.MA_DT_SONG;
		objPhieuKhamBenh0.THOIDIEM_NAMNAM = objBenhNhan0.THOIDIEM_NAMNAM;
		objPhieuKhamBenh0.CHUOI_KIEM_TRA = objBenhNhan0.CHUOI_KIEM_TRA;
	}

	protected boolean saveFormKhamBenh() {
		if (KIEU_THANH_TOAN == Utils.THANHTOAN_BHYT
				|| KIEU_THANH_TOAN == Utils.THANHTOAN_BHYT_2) {
			if(objBenhNhan.GATE_INFO==null ){
				if(objCheck==null || (objCheck.checkCode!=0)){
					MessageDialog.openError(shellKhamBenh, "Có lổi", "Bấm check mã thẻ trước khi lưu");
					return false;
				}
			}
		}

		//
		boolean isError = false;
		String errorText = "Có lỗi: \n";
		if (KIEU_THANH_TOAN == Utils.THANHTOAN_BHYT
				|| KIEU_THANH_TOAN == Utils.THANHTOAN_BHYT_2) {
			
			if (txtMathe.txtMathe == null
					|| (txtMathe.txtMathe != null && txtMathe.txtMathe.trim()
							.length() == 0)
					|| (txtMathe.txtMathe != null && txtMathe.txtMathe.trim()
							.length() != 15)) {
				tabFolder.setSelection(0);
				// System.out.println("SAVE TXTMATHE = " + txtMathe.txtMathe);
				//MessageDialog.openError(shellKhamBenh, "Có lỗi", "Mã thẻ không hợp lệ");
				txtMathe.setColor(SWTResourceManager.getColor(SWT.COLOR_RED));
				txtMathe.forceFocus();
				errorText += "- Mã thẻ không hợp lệ. Thử bấm check thẻ\n";
				isError = true;
			} else {
				//System.out.println("SAVE TXTMATHE2 = " + txtMathe.txtMathe);
				txtMathe.setColor(SWTResourceManager.getColor(SWT.COLOR_YELLOW));
			}
			
			if (cbNoiDKKB.getText().trim().length() == 0) {
				tabFolder.setSelection(0);
				cbNoiDKKB.forceFocus();
				cbNoiDKKB.setBackground(SWTResourceManager
						.getColor(SWT.COLOR_RED));
				errorText += "- Nơi đăng ký khám chữa bệnh\n";
				isError = true;
			} else {
				cbNoiDKKB.setBackground(SWTResourceManager.getColor(SWT.COLOR_YELLOW));
			}

			if (Utils.differenceInDay(txtTuNgay.getDate2(), txtDenNgay.getDate2()) <= 0) {
				tabFolder.setSelection(0);
				txtTuNgay.setColor(SWTResourceManager.getColor(SWT.COLOR_RED));
				txtDenNgay.setColor(SWTResourceManager.getColor(SWT.COLOR_RED));
				//MessageDialog.openError(shellKhamBenh, "Có lỗi", "Thẻ hết hạn");
				errorText += "- Thẻ hết hạn\n";
				isError = true;
			} else {
				txtTuNgay.setColor(SWTResourceManager
						.getColor(SWT.COLOR_YELLOW));
				txtDenNgay.setColor(SWTResourceManager
						.getColor(SWT.COLOR_YELLOW));
			}

			if (Utils.differenceInDay(Calendar.getInstance(),
					txtDenNgay.getDate2()) < 0) {
				tabFolder.setSelection(0);
				txtDenNgay.setColor(SWTResourceManager.getColor(SWT.COLOR_RED));
				errorText += "- Ngày trên thẻ không đúng\n";
				isError = true;
			} else {
				txtDenNgay.setColor(SWTResourceManager
						.getColor(SWT.COLOR_YELLOW));
			}
			// Check Nam/Kham
			double monthDiff = Utils.differenceInMonths(Calendar.getInstance(), txtNgaySinh.getDate2());
			if (monthDiff <= 6 * 12) {
				// Tre em
				if( Utils.getDouble( txtCanNang.getText() ) <=0  ){
					txtCanNang.setBackground(SWTResourceManager.getColor(SWT.COLOR_RED));
					txtCanNang.setEditable(true);
					txtCanNang.forceFocus();
					errorText += "- Nhập cân nặng với trẻ em\n";
					isError = true;
				}
				else{
					txtCanNang.setBackground(SWTResourceManager.getColor(SWT.COLOR_GREEN));
				}
			}
			// Check kham phu khoa
			// Tre em
			if (cbKhoa.getText().indexOf("13.1899") > -1) {
				// Phu khoa
				if (btnGIOITINH.getSelection() == true) {
					// objBenhNhan.setGIOI_TINH(2);// NU OK
				} else {
					MessageDialog.openWarning(shellKhamBenh, "Có lỗi",
							"Chọn khám phụ sản cho Nam Giới..");
					cbKhoa.select(0);
					cbKhoa.forceFocus();
					errorText += "- Chọn khám phụ sản cho NAM GIOI\n";
					isError = true;
					// objBenhNhan.setGIOI_TINH(1);
				}
			}
			// Check ICD 
			if(listThuocData.size()>0){
				if(cbMaBenh.getText().trim().length()==0){
					errorText += "- Phải có Mã bệnh chính\n";
					cbMaBenh.setBackground(SWTResourceManager.getColor(SWT.COLOR_RED));
					tabFolder.setSelection(0);
					cbMaBenh.forceFocus();
					isError = true;
				}
				else{
					cbMaBenh.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
				}
			}
			// Load latest kham của mã thẻ đó
			if(txtMathe.strMathe!=null || txtMathe.strMathe.length()>10){
				if (objPhieuKhamBenh == null || objPhieuKhamBenh.MA_LK==null
						|| (objPhieuKhamBenh != null && objPhieuKhamBenh.MA_LK == 0)) {
					BenhNhan objTemp = BenhNhan.load(txtMathe.strMathe);
					if (objTemp != null) {
						// Load latest khambenh of this
						List<KhamBenh> listTempKhamBenh = KhamBenh.loadLatest(objTemp.BN_ID);
						if (listTempKhamBenh != null && listTempKhamBenh.size() > 0) {
							KhamBenh tmpKhamBenh = listTempKhamBenh.get(0);
							if (tmpKhamBenh.NGAY_VAO != null) {
								Date NGAY_VAO = Utils.getDateTimeByDate(
										tmpKhamBenh.NGAY_VAO, "yyyyMMddHHmm");
								if (Utils.differenceInDay(NGAY_VAO, Calendar
										.getInstance().getTime()) < 1) {
									txtMathe.setColor(SWTResourceManager
											.getColor(SWT.COLOR_RED));
									errorText += "- Bệnh nhân với mã thẻ "
													+ objTemp.MA_THE
													+ " đã khám trong ngày rồi..\n";
									isError = true;
								} else {
									txtMathe.setColor(SWTResourceManager
											.getColor(SWT.COLOR_YELLOW));
								}
							} else {
								txtMathe.setColor(SWTResourceManager
										.getColor(SWT.COLOR_YELLOW));
							}
						} else {
							txtMathe.setColor(SWTResourceManager
									.getColor(SWT.COLOR_YELLOW));
						}
					}
				}
				//
			} else {
				txtMathe.setColor(SWTResourceManager.getColor(SWT.COLOR_YELLOW));
				cbNoiDKKB.setBackground(SWTResourceManager
						.getColor(SWT.COLOR_YELLOW));
				txtDenNgay.setColor(SWTResourceManager.getColor(SWT.COLOR_YELLOW));
			}
		}
		//
//		if (txtCanNang.getEditable() == true) {
//			if (Utils.getDouble(txtCanNang.getText()) == 0.0) {
//				tabFolder.setSelection(0);
//				//System.out.println("SAVE CAN NANG");
//				txtCanNang.forceFocus();
//				txtCanNang.setBackground(SWTResourceManager
//						.getColor(SWT.COLOR_RED));
//				errorText += "- Cân nặng sai...\n";
//				isError = true;
//			} else {
//				System.out.println("SAVE CAN NANG1");
//				txtCanNang.setBackground(SWTResourceManager
//						.getColor(SWT.COLOR_YELLOW));
//			}
//		} else {
//			System.out.println("SAVE CAN NANG2");
//			txtCanNang.setBackground(SWTResourceManager
//					.getColor(SWT.COLOR_YELLOW));
//		}

		if (txtHoten.getText().trim().length() == 0) {
			tabFolder.setSelection(0);
			txtHoten.forceFocus();
			txtHoten.setBackground(SWTResourceManager.getColor(SWT.COLOR_RED));
			errorText += "- Nhập họ tên...\n";
			isError = true;
		} else {
			txtHoten.setBackground(SWTResourceManager
					.getColor(SWT.COLOR_YELLOW));
		}

		if (txtDiachi.getText().trim().length() == 0) {
			tabFolder.setSelection(0);
			txtDiachi.forceFocus();
			txtDiachi.setBackground(SWTResourceManager.getColor(SWT.COLOR_RED));
			errorText += "- Nhập địa chỉ...\n";
			isError = true;
		} else {
			txtDiachi.setBackground(SWTResourceManager
					.getColor(SWT.COLOR_YELLOW));
		}

		if (CHI_XET_NGHIEM == 0) {
			if( cbCapCuu.getSelectionIndex()==0){
				if (cbKhoa.getText().trim().length() == 0) {
					tabFolder.setSelection(0);
					cbKhoa.forceFocus();
					cbKhoa.setBackground(SWTResourceManager.getColor(SWT.COLOR_RED));
					errorText += "- Nhập khoa phòng...\n";
					isError = true;
				} else {
					cbKhoa.setBackground(SWTResourceManager
							.getColor(SWT.COLOR_YELLOW));
				}
			}
			//
		}
		if (isError) {
			MessageDialog.openError(shellKhamBenh, "Có lỗi", errorText);
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
				objBenhNhan.MA_DKBD = objBenhNhan.MA_DKBD.replaceAll("-", "");
				objBenhNhan.setGATE_INFO(objCheck==null?"":objCheck.jsonText);
				objBenhNhan.insert();
			} catch (Exception e) {
				e.printStackTrace();
				objBenhNhan = BenhNhan.load(objBenhNhan.MA_THE);
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
				System.out.println("==========================objPhieuKhamBenh.KIEU_TT = "
						+ objPhieuKhamBenh.KIEU_TT + " KIEUTT="
						+ KIEU_THANH_TOAN);
				objPhieuKhamBenh.KIEU_TT = KIEU_THANH_TOAN;
				System.out.println("objPhieuKhamBenh.KIEU_TT = "
						+ objPhieuKhamBenh.KIEU_TT + " KIEUTT="
						+ KIEU_THANH_TOAN);
				//
				objPhieuKhamBenh.BN_ID = objBenhNhan.BN_ID;
				//
				updateTinhTien();
				//
				objPhieuKhamBenh.STT = 0;
				//
				logger.info("=========BEGIN UPDATE KHAM BENH === listThuocData.size()="
						+ listThuocData.size() + " sts=" + objPhieuKhamBenh.STS);
				if (listThuocData.size() > 0) {
					// DA CHO THUOC, UPDATE STATUS OF PHIEU KHAM BENH
					objPhieuKhamBenh.STS = Utils.updateStatusPhieuKham( objPhieuKhamBenh.STS, Utils.PHIEUKHAM_KHAMXONG_CHO_LAYTHUOC);
					//
					logger.info("=========STS KHAM BENH === " + objPhieuKhamBenh.STS);
				}
				objPhieuKhamBenh.CHANDOAN_BD = txtBenhBanDau.getText();
				// Check is Thanh toan
				boolean isThanhToan = true;
				String maBacSi = "";
				if(objCongKham==null || (objCongKham!=null && objCongKham.THANHTOAN==0)){
					isThanhToan = false;
					logger.info("=========CHECK THANHTOAN CONG KHAM FAIL=== " );
				}
				for (DvChitiet obj : listCLSData) {
					if(obj.THANHTOAN==0){
						isThanhToan = false;
						logger.info("=========CHECK THANHTOAN CLS FAIL=== " + obj.toString());
						break;
					}
					if( DbHelper.hashDataUsers.get( cbListBacSiCLS.getText() )!=null ){
						maBacSi = DbHelper.hashDataUsers.get( cbListBacSiCLS.getText() ).MACCHN;
					}
				}
				for (ThuocChitiet obj : listThuocData) {
					if(obj.THANHTOAN==0){
						isThanhToan = false;
						logger.info("=========CHECK THANHTOAN THUOC FAIL=== " + obj.toString());
						break;
					}
					if( DbHelper.hashDataUsers.get( cbListBacsiThuoc.getText() )!=null ){
						maBacSi = DbHelper.hashDataUsers.get( cbListBacsiThuoc.getText() ).MACCHN;
					}
				}
				//
				if(objCongKham!=null){
					objCongKham.MA_BAC_SI = maBacSi;
				}
				//
				if(isThanhToan==false){
					objPhieuKhamBenh.NGAY_TTOAN = "";
				}
				else{
					objPhieuKhamBenh.NGAY_TTOAN = Utils.getDatetime(new Date(), "yyyyMMddHHmm");
				}
				objPhieuKhamBenh.MA_CSKCB = objPhieuKhamBenh.MA_CSKCB.replaceAll("-", "");
				updateBenhNhan2PhieuKham(objBenhNhan, objPhieuKhamBenh);
				objPhieuKhamBenh.insert();
				logger.info("=========END UPDATE KHAM BENH === " + objPhieuKhamBenh.MA_LK);
				// ============================================================
				// Save cong kham
				// if(KIEU_THANH_TOAN!=Utils.THANHTOAN_VIENPHI_FREE ){
				// ============================================================
				if (CHI_XET_NGHIEM == 0) {
					if( isCapCuu==true && (KIEU_THANH_TOAN!=Utils.THANHTOAN_BHYT_2 && KIEU_THANH_TOAN!=Utils.THANHTOAN_BHYT)){
						//
						MessageDialog.openInformation(shellKhamBenh, "", "Không save cong kham");
					}
					else{
	
						if (objCongKham == null) {
							updateCongKham();
						}
						objCongKham.BN_ID = objBenhNhan.BN_ID;
						objCongKham.MA_LK = objPhieuKhamBenh.MA_LK;
						// Nghia: Da thanh toan roi, nhung van update lai CONG KHAM  
						Users bacsy = DbHelper.hashDataUsers.get(cbListBacSiForm.getText());
						if(bacsy!=null && bacsy.MACCHN!=objCongKham.MA_BAC_SI){
							objCongKham.MA_BAC_SI = bacsy.MACCHN;
							logger.info("UPDATE CONGKHAM: "+ objCongKham.toString());
						}
						//
						
						logger.info("=============BEGIN UPDATE CONG KHAM BENH === ");
						objCongKham.insert();
						logger.info("=============END UPDATE CONG KHAM BENH === ");
					}
				}
				//
				logger.info("=============BEGIN UPDATE DICH VU CHI TIET === ");
				for (DvChitiet obj : listCLSData) {
					obj.BN_ID = objBenhNhan.BN_ID;
					obj.MA_LK = objPhieuKhamBenh.MA_LK;

					if (obj.STS == -1) {
						obj.STS = 0;
					}

					//
					obj.insert();
				}
				logger.info("=============END UPDATE DICH VU CHI TIET === ");
				logger.info("=============BEGIN UPDATE THONG TIN THUOC === ");
				for (ThuocChitiet obj : listThuocData) {
					obj.MA_LK = objPhieuKhamBenh.MA_LK;
					//
					boolean isFirstTimeInsert = false;
					if (obj.TCT_ID == null) {
						// First time insert
						isFirstTimeInsert = true;
						//
					}
					if (obj.STS == -1) {
						obj.STS = 0;
					}
					// Insert thuoc chi tiet.
					obj.insert();
					// Update kho thuoc ....
					if (isFirstTimeInsert == true) {
						logger.info("==================BEGIN UPDATE KHO THUOC === ");
						// Update outstanding
						CtNhapthuoc objCtNhapthuoc = CtNhapthuoc
								.load(obj.CT_ID);
						logger.info("==================    LOHANG_ID="
								+ obj.CT_ID + " TON KHO="
								+ objCtNhapthuoc.SL_TONKHO + " CTID_FROM="
								+ objCtNhapthuoc.CTID_FROM);

						// Tru truc tiep
						objCtNhapthuoc.SL_TONKHO = objCtNhapthuoc.SOLUONG
								- obj.SO_LUONG;
						//
						logger.info("==================    LOHANG_ID="
								+ obj.CT_ID + " TON KHO="
								+ objCtNhapthuoc.SL_TONKHO + " CTID_FROM="
								+ objCtNhapthuoc.CTID_FROM);
						//
						objCtNhapthuoc.update();
						//
						logger.info("==================END UPDATE KHO THUOC === ");
					}
					//
				}
				logger.info("=============END UPDATE THONG TIN THUOC === ");
				logger.info("===END UPDATE THONG TIN LUOT KHAM BENH === ");
				//
			}
		}
		// waitingDlgHide();
		return isError;
		//
	}

	protected BenhNhan getBenhNhanDlg() {
		BenhNhan obj = new BenhNhan();
		obj.setMA_THE(txtMathe.strMathe);
		if(btnGIOITINH.getSelection()==false){
			obj.GIOI_TINH = 1;
		}
		else{
			obj.GIOI_TINH = 2;
		}
		obj.setHO_TEN(txtHoten.getText());
		if(txtNgaySinh.isNgaySinh0101==true){
			obj.setNGAY_SINH(txtNgaySinh.getDateByYear	());
		}
		else{
			obj.setNGAY_SINH(txtNgaySinh.getDate());
		}
		obj.setMA_DKBD(cbNoiDKKB.getText());
		obj.setGT_THE_TU(txtTuNgay.getDate());
		obj.setGT_THE_DEN(txtDenNgay.getDate());
		obj.setNGAY_CAP(txtNgayCap.getDate());
		obj.setTEN_CHA_ME(txtTenChaMe.getText());
		obj.setMA_DT_SONG(Utils.getInt(txtDichVuCao.getText()));
		obj.setTHOIDIEM_NAMNAM(txtThoiDiem5Nam.getDate());
		obj.setCHUOI_KIEM_TRA(txtChuoiKiemTra.getText());
		obj.setMA_QUAN_LY(txtMaQuanLy.getText());
		logger.info("CHECK THe "+ obj);
		return obj;
	}
	protected void updateDlg() {
		if (objBenhNhan != null && objBenhNhan.HO_TEN != null) {
			// Read by barcode
			String info = "";
			txtMathe.setText(objBenhNhan.MA_THE);
			txtHoten.setText(objBenhNhan.HO_TEN);
			txtNgaySinh.setText(objBenhNhan.NGAY_SINH);
			//
			txtNgaySinhTheoThang.setText( ""+txtNgaySinh.toMonthDay() );
			//
			info += objBenhNhan.HO_TEN + ". Tuổi: " + txtNgaySinh.getTuoi()
					+ "(" + objBenhNhan.NGAY_SINH + ").";
			if (objBenhNhan.GIOI_TINH.intValue() == 1) {
				btnGIOITINH.setSelection(false);
				btnGIOITINH.setText("NAM(1)");
				info += " NAM.";
			} else {
				btnGIOITINH.setSelection(true);
				btnGIOITINH.setText("NU(2)");
				info += " NỮ.";
			}
			info += "" + objBenhNhan.DIA_CHI;
			if (objBenhNhan.HO_TEN.length() > 0) {
				txtInfo.setText(info);
			} else {
				txtInfo.setText("");
			}
			txtDiachi.setText(objBenhNhan.DIA_CHI);
			cbNoiDKKB.setText(objBenhNhan.MA_DKBD);
			MaCskcb objMaCskcb = MaCskcbCache.getMaCskcb(objBenhNhan.MA_DKBD);
			if (objMaCskcb != null)
				txtTenCSKCB.setText(objMaCskcb.MA_NAME + " "
						+ objMaCskcb.MA_TUYEN);
			txtTuNgay.setText(objBenhNhan.GT_THE_TU);
			txtDenNgay.setText(objBenhNhan.GT_THE_DEN);
			//
			txtNgayCap.setText(objBenhNhan.NGAY_CAP);
			txtTenChaMe.setText(objBenhNhan.TEN_CHA_ME);
			txtDichVuCao.setText("" + objBenhNhan.MA_DT_SONG.intValue());
			txtThoiDiem5Nam.setText(objBenhNhan.THOIDIEM_NAMNAM);
			txtChuoiKiemTra.setText(objBenhNhan.CHUOI_KIEM_TRA);
			txtMaQuanLy.setText(objBenhNhan.MA_QUAN_LY);
			if (objPhieuKhamBenh != null && objPhieuKhamBenh.KIEU_TT != null) {
				KIEU_THANH_TOAN = objPhieuKhamBenh.KIEU_TT;
				System.out.println("KIEU_THANH_TOAN = " + KIEU_THANH_TOAN);
			}
			//
			updateKIEUTHANHTOAN();
			//
			buttonRefresh(false);
			//
			Calendar today = Calendar.getInstance();
			today.setTime(new Date());
			long dayDiff = Utils.differenceInDay(txtNgaySinh.getDate2(), today);

			if (dayDiff < 365) {
				txtCanNang.setEditable(true);
				//
			} else {
				txtCanNang.setEditable(false);
				txtCanNang.setText("");
			}
			//
		}
		if (objPhieuKhamBenh != null) {
			// System.out.println("objPhieuKhamBenh MA_LK="+
			// objPhieuKhamBenh.MA_LK);
			//
			txtCanNang.setText(""+objPhieuKhamBenh.CAN_NANG);
			lblStatusPhieu.setText(Utils
					.getTinhTrangPhieuKham(objPhieuKhamBenh.STS.intValue()));
			lblStatusPhieu
					.setBackground(Utils
							.getTinhTrangPhieuKhamColor(objPhieuKhamBenh.STS
									.intValue()));
			txtBenhBanDau.setText(objPhieuKhamBenh.CHANDOAN_BD == null ? ""
					: objPhieuKhamBenh.CHANDOAN_BD);
			cbBenhBanDau.setText(txtBenhBanDau.getText());
			//
			txtTenBenh.setText(objPhieuKhamBenh.TEN_BENH);
			cbMaBenh.setText(objPhieuKhamBenh.MA_BENH);
			txtTenBenhPhu.setText(objPhieuKhamBenh.MA_BENHKHAC);
			//
			cbCapCuu.select(objPhieuKhamBenh.MA_TAI_NAN);
			// `
		} else {
			lblStatusPhieu.setText("Tạo mới");
			lblStatusPhieu.setBackground(SWTResourceManager
					.getColor(SWT.COLOR_WHITE));
			cbCapCuu.select(0);
		}
		//
		if (objCongKham != null) {
			System.out.println("CONG KHAM -----" + objCongKham.toString());
			
			if (objCongKham.TEN_DICH_VU == null) {
				cbKhoa.select(0);
			} else {
				if(cbCapCuu.getSelectionIndex()==0){
					
				}
				else{
					//cbKhoa.select(cbKhoa.getItemCount()-1);
					
				}
				//cbKhoa.setText(objCongKham.MA_DICH_VU + "-" +objCongKham.TEN_DICH_VU + "(" + objCongKham.DON_GIA2 + ")");
			}
			//
			Users objBacSi = DbHelper.hashDataUsersMaCCHN.get(objCongKham.MA_BAC_SI);
			if(objBacSi!=null){
				cbListBacSiForm.setText(objBacSi.U_ID+"-"+objBacSi.TEN_NHANVIEN);
				cbListBacSiCLS.setText(objBacSi.U_ID+"-"+objBacSi.TEN_NHANVIEN);
				cbListBacsiThuoc.setText(objBacSi.U_ID+"-"+objBacSi.TEN_NHANVIEN);
			}
			//
		} else {
			System.out.println("CONG KHAM KO CO");
			cbKhoa.select(-1);
			cbKhoa.setText("");
			// cbKhoa.setText("");
		}
		// load list CLS
		loadCanLamSan();
		//
		loadThuoc();
		//
	}

	protected void onchangeNoiKCB() {
		if (txtTenCSKCB == null) {
			return;
		}
		MaCskcb obj = MaCskcbCache.getMaCskcb(cbNoiDKKB.getText());
		if (obj != null) {
			txtTenCSKCB.setText(obj.MA_NAME + " - " + obj.MA_TUYEN);
		} else {
			txtTenCSKCB.setText("không tìm thấy mã [" + cbNoiDKKB.getText()
					+ "]");
		}
	}

	protected void daytimeKeyPress(KeyEvent e) {
		if (e.keyCode == 13) {
		}

	}

	/**
	 * Create contents of the dialog.
	 */
	private void createContents() {
		dlgSearchTextDlg = new SearchTextDlg(shellKhamBenh, 0);
		dlgSearchTextDlg.parentDlg = this;
		//
		shellKhamBenh.setLayout(new FormLayout());
		shellKhamBenh.addListener(SWT.Traverse, new Listener() {
			public void handleEvent(Event e) {
				if (e.detail == SWT.TRAVERSE_ESCAPE) {
					resetFormKhamBenh();
					e.doit = false;
				}
			}
		});
		compositeListPhieuKham = new Composite(shellKhamBenh, SWT.NONE);
		fd_compositeListPhieuKham = new FormData();
		fd_compositeListPhieuKham.top = new FormAttachment(0, 10);
		fd_compositeListPhieuKham.left = new FormAttachment(0, 10);
		fd_compositeListPhieuKham.right = new FormAttachment(0, 1030);
		compositeListPhieuKham.setLayoutData(fd_compositeListPhieuKham);
		compositeListPhieuKham.setLayout(new BorderLayout(0, 0));

		tableViewerPhieuKham = new TableViewer(compositeListPhieuKham,
				SWT.BORDER | SWT.FULL_SELECTION);
		tablePhieuKham = tableViewerPhieuKham.getTable();
		tablePhieuKham.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.keyCode == SWT.ARROW_DOWN || e.keyCode == SWT.ARROW_UP) {
					//
				}
				else if (e.keyCode == 13) {
					//
					txtHoten.forceFocus();
					//
				} 
				else {
					txtSearchPhieuKham.selectAll();
					txtSearchPhieuKham.forceFocus();
				}
			}
		});
		tablePhieuKham.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				selectPhieuKham();
			}
		});
		tablePhieuKham.setLayoutData(BorderLayout.CENTER);
		tablePhieuKham.setLinesVisible(true);
		tablePhieuKham.setHeaderVisible(true);

		TableColumn tableColumn_6 = new TableColumn(tablePhieuKham, SWT.NONE);
		tableColumn_6.setWidth(107);
		tableColumn_6.setText("Tình trạng");

		TableColumn tableColumn_9 = new TableColumn(tablePhieuKham, SWT.NONE);
		tableColumn_9.setWidth(95);
		tableColumn_9.setText("Thời gian vào");

		TableColumn tableColumn_10 = new TableColumn(tablePhieuKham, SWT.NONE);
		tableColumn_10.setWidth(172);
		tableColumn_10.setText("Họ tên");

		TableColumn tableColumn_11 = new TableColumn(tablePhieuKham, SWT.NONE);
		tableColumn_11.setWidth(121);
		tableColumn_11.setText("Mã Thẻ");

		TableColumn tableColumn_12 = new TableColumn(tablePhieuKham, SWT.NONE);
		tableColumn_12.setWidth(57);
		tableColumn_12
				.setToolTipText("Status của người bệnh, (Đang cận LS, xong cận LS nào đó hay chưa )");
		tableColumn_12.setText("TYP");

		TableColumn tblclmnBntng = new TableColumn(tablePhieuKham, SWT.NONE);
		tblclmnBntng.setWidth(102);
		tblclmnBntng.setText("BN/TỔNG");

		TableColumn tableColumn_14 = new TableColumn(tablePhieuKham, SWT.NONE);
		tableColumn_14.setWidth(49);
		tableColumn_14.setText("TToán");

		TableColumn tableColumn_15 = new TableColumn(tablePhieuKham, SWT.NONE);
		tableColumn_15.setWidth(40);
		tableColumn_15
				.setToolTipText("Status của người bệnh, (Đang cận LS, xong cận LS nào đó hay chưa )");
		tableColumn_15.setText("K");

		TableColumn tableColumn_16 = new TableColumn(tablePhieuKham, SWT.NONE);
		tableColumn_16.setWidth(110);
		tableColumn_16
				.setToolTipText("Status của người bệnh, (Đang cận LS, xong cận LS nào đó hay chưa )");
		tableColumn_16.setText("Khoa");

		TableColumn tblclmnMalkbnid = new TableColumn(tablePhieuKham, SWT.NONE);
		tblclmnMalkbnid.setWidth(100);
		tblclmnMalkbnid.setText("MA_LK/BNID");
		tableViewerPhieuKham.setLabelProvider(new TableLabelProviderBenhNhan());
		tableViewerPhieuKham.setContentProvider(new ContentProviderBenhNhan());
		tableViewerPhieuKham.setInput(listData);

		searchFilter = new ViewerFilter() {
			@Override
			public boolean select(Viewer arg0, Object element, Object element2) {
				// System.out.println("] element="+element+" arg02="+element2);
				strSearchPhieuKham = txtSearchPhieuKham.getText();
				String suchbegriff = strSearchPhieuKham;
				if (suchbegriff.equals("")) {
					return true;
				}

				// System.out.println("["+suchbegriff + "] element="+element);
				if (element2 instanceof Row) {
					Row kunde = (Row) element2;
					// System.out.println( kunde.toString() );
					return kunde.getString("TEN_BENH_NHAN").toUpperCase()
							.contains(suchbegriff.toUpperCase())
							|| kunde.getString("MA_THE").toUpperCase()
									.contains(suchbegriff.toUpperCase())
							|| (kunde.getInteger("MA_LK").intValue() == Utils
									.getInt(suchbegriff))
							|| (kunde.getInteger("BN_ID").intValue() == Utils
									.getInt(suchbegriff));
				}
				return false;
			}
		};
		tableViewerPhieuKham.addFilter(searchFilter);

		Composite compositePhieuKham = new Composite(shellKhamBenh, SWT.NONE);
		fd_compositeListPhieuKham.bottom = new FormAttachment(
				compositePhieuKham, -6);
		FormData fd_compositePhieuKham = new FormData();
		fd_compositePhieuKham.left = new FormAttachment(0, 10);
		fd_compositePhieuKham.right = new FormAttachment(100, -10);
		fd_compositePhieuKham.top = new FormAttachment(0, 213);
		fd_compositePhieuKham.bottom = new FormAttachment(100);

		Composite compositeHeaderSearch = new Composite(compositeListPhieuKham,
				SWT.NONE);
		compositeHeaderSearch.setLayoutData(BorderLayout.SOUTH);
		compositeHeaderSearch.setLayout(new GridLayout(2, false));

		txtInfo = new Text(compositeHeaderSearch, SWT.NONE);
		GridData gd_txtInfo = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_txtInfo.widthHint = 537;
		txtInfo.setLayoutData(gd_txtInfo);
		txtInfo.setFont(SWTResourceManager.getFont("Tahoma", 12, SWT.NORMAL));
		txtInfo.setBackground(SWTResourceManager
				.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		
				txtTongCong = new Text(compositeHeaderSearch, SWT.NONE);
				txtTongCong.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseDown(MouseEvent e) {
						maytinhtien();
					}
				});
				GridData gd_txtTongCong = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
				gd_txtTongCong.widthHint = 451;
				txtTongCong.setLayoutData(gd_txtTongCong);
				txtTongCong.setEditable(false);
				txtTongCong.setBackground(SWTResourceManager
						.getColor(SWT.COLOR_WIDGET_BACKGROUND));
				txtTongCong.setFont(SWTResourceManager
						.getFont("Tahoma", 13, SWT.NORMAL));
		
		Composite composite = new Composite(compositeListPhieuKham, SWT.NONE);
		composite.setLayoutData(BorderLayout.NORTH);
		composite.setLayout(new GridLayout(12, false));
		
				txtSearchPhieuKham = new Text(composite, SWT.BORDER);
				GridData gd_txtSearchPhieuKham = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
				gd_txtSearchPhieuKham.widthHint = 170;
				txtSearchPhieuKham.setLayoutData(gd_txtSearchPhieuKham);
				txtSearchPhieuKham.setToolTipText("Bấm Control để tìm phiếu PHUC HOI CHUC NANG. Enter binh thuong de tim phieu kham benh trong ngay.");
				txtSearchPhieuKham.setBackground(SWTResourceManager
						.getColor(SWT.COLOR_GREEN));
				txtSearchPhieuKham.setFont(SWTResourceManager.getFont("Tahoma", 14,
						SWT.NORMAL));
				txtSearchPhieuKham.addKeyListener(new KeyAdapter() {
					@Override
					public void keyReleased(KeyEvent e) {
						if(e.keyCode==13){
							doSearchBenhNhan();
							tableViewerPhieuKham.refresh();
						}
						if (e.keyCode == 13 || e.keyCode == SWT.ARROW_DOWN) {
							tablePhieuKham.forceFocus();
						}
					}
				});
		
		dateTime0 = new DateTime(composite, SWT.BORDER);
		
		dateTime1 = new DateTime(composite, SWT.BORDER);
		
		comboKieuThanhToan = new Combo(composite, SWT.NONE);
		comboKieuThanhToan.setItems(new String[] {"Tất cả", "Bảo hiểm", "Viện Phí"});
		comboKieuThanhToan.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		comboKieuThanhToan.select(0);
		
		comboNhanVien = new Combo(composite, SWT.NONE);
		//comboNhanVien.setItems(new String[] {"Tất cả", "NV Login"});
		comboNhanVien.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		comboTinhTrangThanhToan = new Combo(composite, SWT.NONE);
		comboTinhTrangThanhToan.setItems(new String[] {"Tất cả", "Chưa Thanh Toán", "Đã Thanh Toán"});
		comboTinhTrangThanhToan.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		comboTinhTrangThanhToan.select(0);
		
		btnCheckButtonNgayXem = new Button(composite, SWT.CHECK);
		btnCheckButtonNgayXem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if( btnCheckButtonNgayXem.getSelection()==true){
					btnCheckButtonNgayXem.setText("30 ngày trước");
				}
				else{
					btnCheckButtonNgayXem.setText("Trong ngày");
				}
				btnCheckButtonNgayXemDta = btnCheckButtonNgayXem.getSelection();
				doSearchBenhNhan();
			}
		});
		btnCheckButtonNgayXem.setText("Trong ngày");
		btnCheckButtonNgayXem.setSelection(btnCheckButtonNgayXemDta);
		
		btnCheckButtonTatCaPhieu = new Button(composite, SWT.CHECK);
		btnCheckButtonTatCaPhieu.setText("Tất cả phiếu");
		btnCheckButtonTatCaPhieu.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if( btnCheckButtonTatCaPhieu.getSelection()==true){
					btnCheckButtonTatCaPhieu.setText("Phiếu PHCN");
				}
				else{
					btnCheckButtonTatCaPhieu.setText("Tất cả phiếu");
				}
				btnCheckButtonTatCaPhieuDta = btnCheckButtonTatCaPhieu.getSelection();
				doSearchBenhNhan();
			}
		});
		btnCheckButtonTatCaPhieuDta = false;
		btnCheckButtonTatCaPhieu.setSelection(btnCheckButtonTatCaPhieuDta);
		
		btnCheckButtonTatCaNV = new Button(composite, SWT.CHECK);
		btnCheckButtonTatCaNV.setText("Tất cả NV");
		btnCheckButtonTatCaNV.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if( btnCheckButtonTatCaNV.getSelection()==true){
					btnCheckButtonTatCaNV.setText("Chỉ "+DbHelper.currentSessionUserId.U_NAME);
				}
				else{
					btnCheckButtonTatCaNV.setText("Tất cả NV");
				}
				btnCheckButtonTatCaNVDta = btnCheckButtonTatCaNV.getSelection();
				doSearchBenhNhan();
			}
		});
		btnCheckButtonTatCaNVDta = true;
		btnCheckButtonTatCaNV.setSelection(btnCheckButtonTatCaNVDta);

		btnCheckButtonTatCaTHANHTOAN = new Button(composite, SWT.CHECK);
		btnCheckButtonTatCaTHANHTOAN.setText("Chưa TTOÁN");
		btnCheckButtonTatCaTHANHTOAN.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if( btnCheckButtonTatCaTHANHTOAN.getSelection()==true){
					btnCheckButtonTatCaTHANHTOAN.setText("Tất cả TTOÁN");
				}
				else{
					btnCheckButtonTatCaTHANHTOAN.setText("Chưa TTOÁN");
				}
				btnCheckButtonTatCaTHANHTOANDta = btnCheckButtonTatCaTHANHTOAN.getSelection();
				doSearchBenhNhan();
			}
		});
		btnCheckButtonTatCaTHANHTOANDta = true;
		btnCheckButtonTatCaTHANHTOAN.setSelection(btnCheckButtonTatCaTHANHTOANDta);
		
		lbTONG_BN = new Label(composite, SWT.RIGHT);
		lbTONG_BN.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		GridData gd_lbTONG_BN = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_lbTONG_BN.widthHint = 105;
		lbTONG_BN.setLayoutData(gd_lbTONG_BN);
		lbTONG_BN.setText("New Label");
		
		Button btnThongBao = new Button(composite, SWT.NONE);
		GridData gd_btnThongBao = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnThongBao.widthHint = 36;
		btnThongBao.setLayoutData(gd_btnThongBao);

		compositePhieuKham.setLayoutData(fd_compositePhieuKham);
		compositePhieuKham.setLayout(new BorderLayout(0, 0));

		tabFolder = new TabFolder(compositePhieuKham, SWT.NONE);
		tabFolder.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPress(e);
			}
		});
		tabFolder.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				// System.out.println(tabFolder.getSelectionIndex() +
				// " selected");
			}
		});
		tabFolder.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				// System.out.println("SELECT AT " +
				// tabFolder.getSelectionIndex());
				setFocusTabFolder();

			}
		});
		tabFolder.setFont(SWTResourceManager.getFont("Tahoma", 12, SWT.BOLD));

		TabItem tabItemThongtinBenhNhan = new TabItem(tabFolder, SWT.NONE);
		tabItemThongtinBenhNhan.setToolTipText("Ctrl+1");
		tabItemThongtinBenhNhan.setImage(SWTResourceManager.getImage(
				FormKhamBenhDlg.class, "/png/person-3x.png"));
		tabItemThongtinBenhNhan.setText("Thông tin (Ctrl+1)");

		Composite compositeThongTinBenh = new Composite(tabFolder, SWT.NONE);
		compositeThongTinBenh.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				// System.out.println("FOR CUS MEEEEEE");
			}
		});
		tabItemThongtinBenhNhan.setControl(compositeThongTinBenh);
		compositeThongTinBenh.setLayout(null);

		Group grpBhyt = new Group(compositeThongTinBenh, SWT.NONE);
		grpBhyt.setBounds(0, 70, 939, 343);
		grpBhyt.setFont(SWTResourceManager.getFont("Tahoma", 8, SWT.BOLD));
		grpBhyt.addListener(SWT.KeyDown, new Listener() {

			@Override
			public void handleEvent(Event event) {
				// System.out.println("EVENT FROM DATEPICKER");
				checkCanNangTreem();

			}
		});
		grpBhyt.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				System.out.println("KEY EVENT RECV");
				keyPress(e);
			}
		});
		grpBhyt.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				System.out.println("PARENT FOCUS");
				//
				updateFormFromMaThe();
				//
				//
				txtMathe.forceFocus();
				//
			}
		});
		grpBhyt.setText("Thông tin bệnh nhân");
		grpBhyt.setLayout(null);

		txtHoten = new Text(grpBhyt, SWT.BORDER);
		txtHoten.setBounds(113, 22, 503, 28);
		txtHoten.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.keyCode == 13 || (e.character == '$')) {
					if (txtHoten.getText().length() > 150) {
						extractData(txtHoten.getText() + "$");
						//tabFolder.setSelection(0);
						txtHoten.forceFocus();
						e.doit = false;
						isBarcode = true;
						return;
					}
					else{
						checkTheoTenBenhNhan();
						if (txtNgaySinh.getTuoi() <= 6) {
							if( cbCapCuu.getSelectionIndex()==0){
								cbKhoa.select(0);
							}
						}
						cbKhoa.forceFocus();
						isBarcode = false;

					}
				}
				else {
					///System.out.println("txtHoten= " + txtHoten.getText());
					if( isBarcode==false ){
						keyPress(e);
					}
					//
				}
//				if (e.keyCode == 13) {
//					checkTheoTenBenhNhan();
//					if (txtNgaySinh.getTuoi() <= 6) {
//						cbKhoa.select(0);
//					}
//					cbKhoa.forceFocus();
//					isBarcode = false;
//				} 
//				else {
//					///System.out.println("txtHoten= " + txtHoten.getText());
//					if( isBarcode==false ){
//						keyPress(e);
//					}
//					//
//				}
			}
		});
		txtHoten.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				txtHoten.setText(Utils.doCapitalizeFirstLetterInString(txtHoten
						.getText()));
			}

			@Override
			public void focusGained(FocusEvent e) {
				if (KIEU_THANH_TOAN == Utils.THANHTOAN_BHYT) {
					checkMaTheAndLoad();
				}
				// System.out.println("focusLost MATHE " + txtMathe.getMaThe());
			}
		});
		txtHoten.setFont(SWTResourceManager.getFont("Tahoma", 16, SWT.NORMAL));

		txtNgaySinh = new DatePicker(grpBhyt, SWT.NONE);
		txtNgaySinh.setBounds(113, 56, 127, 28);

		txtCanNang = new Text(grpBhyt, SWT.BORDER);
		txtCanNang.setBounds(293, 56, 57, 28);
		txtCanNang.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				//
				// System.out.println(txtNgaySinh.getTuoi());
				if (txtNgaySinh.getTuoi() <= 6) {
					cbKhoa.select(0);
					txtCanNang.setBackground(SWTResourceManager
							.getColor(SWT.COLOR_YELLOW));
					return;
				}
				//
				if (txtCanNang.getEditable() == false) {
					btnGIOITINH.forceFocus();
				}
			}
		});
		txtCanNang.setText("");
		txtCanNang
				.setFont(SWTResourceManager.getFont("Tahoma", 16, SWT.NORMAL));
		txtCanNang.setTextLimit(5);
		txtCanNang.setEnabled(true);

		btnGIOITINH = new Button(grpBhyt, SWT.TOGGLE);
		btnGIOITINH.setBounds(402, 56, 97, 28);
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
		btnGIOITINH.setImage(SWTResourceManager.getImage(FormKhamBenhDlg.class,
				"/png/person-2x.png"));
		btnGIOITINH.setFont(SWTResourceManager
				.getFont("Tahoma", 14, SWT.NORMAL));
		btnGIOITINH.setText("NAM(1)");
		btnGIOITINH.setSelection(false);

		txtDiachi = new Text(grpBhyt, SWT.NONE);
		txtDiachi.setBounds(113, 89, 503, 28);
		txtDiachi.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				cbKhoa.forceFocus();
			}
		});
		txtDiachi.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				System.out.println("KEY CODE = " + e.keyCode);
				if (e.keyCode == 13) {
					// search ma huyen xa
					String sql = "";
					try (Connection con = DbHelper.getSql2o()) {
						String searchText = Utils
								.doCapitalizeFirstLetterInString(txtDiachi
										.getText());
						sql = "SELECT * FROM mst_xahuyen where DC_VALUE like '%"
								+ searchText + "%' order by DC_RANK DESC";
						System.out.println(sql);
						java.util.List<MstXahuyen> listMstXahuyen = con
								.createQuery(sql)
								// .addParameter("DC_VALUE", searchText)
								.executeAndFetch(MstXahuyen.class);
						System.out.println(listMstXahuyen.size());
						cbMaHuyen.setVisible(true);
						cbMaHuyen.removeAll();
						for (MstXahuyen obj : listMstXahuyen) {
							cbMaHuyen.add(obj.DC_ID + "-" + obj.DC_VALUE);
							MstXahuyenCache.putMstXahuyen(obj);
						}
						cbMaHuyen.select(0);
						// txtDiachi.setText(cbMaHuyen.getText());
						cbMaHuyen.forceFocus();
						OS.SendMessage(cbMaHuyen.handle, OS.CB_SHOWDROPDOWN, 1,
								0);
						// con.close();
					} catch (Exception ee) {
						ee.printStackTrace();
					}
					//
				}
				// else if(e.keyCode==SWT.TAB){
				// //
				// System.out.println("TAB");
				// cbKhoa.forceFocus();
				// return;
				// //
				// }
				keyPress(e);
			}
		});
		txtDiachi.setFont(SWTResourceManager.getFont("Tahoma", 16, SWT.NORMAL));

		Label lbDiaChi = new Label(grpBhyt, SWT.NONE);
		lbDiaChi.setBounds(10, 94, 97, 23);
		lbDiaChi.setText("Địa Chỉ");
		lbDiaChi.setFont(SWTResourceManager.getFont("Tahoma", 12, SWT.NORMAL));

		Label lblNewLabel_1 = new Label(grpBhyt, SWT.NONE);
		lblNewLabel_1.setBounds(10, 132, 97, 23);
		lblNewLabel_1.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		lblNewLabel_1.setFont(SWTResourceManager.getFont("Tahoma", 12,
				SWT.NORMAL));
		lblNewLabel_1.setText("Mã Thẻ");
		txtMathe = new TheBHXH(grpBhyt, SWT.BORDER);
		txtMathe.setBounds(113, 123, 386, 37);
		txtMathe.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				// System.out.println("FOrcus to txtMathe");
				txtMathe.setFocus();
				// MessageDialog.openInformation(shellKhamBenh, "FOCUS",
				// "FOCUS");
			}

			@Override
			public void focusLost(FocusEvent e) {

			}
		});
		txtMathe.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPress(e);
			}
		});
		txtMathe.setFont(SWTResourceManager.getFont("Tahoma", 16, SWT.NORMAL));

		cbNoiDKKB = new Combo(grpBhyt, SWT.DROP_DOWN | SWT.BORDER);
		cbNoiDKKB.setBounds(113, 162, 89, 33);
		cbNoiDKKB
				.setToolTipText("Bấm F4 để đổ danh sách nơi khám chữa bệnh, hoặc gõ vào tìm");
		cbNoiDKKB.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				OS.SendMessage(cbNoiDKKB.handle, OS.CB_SHOWDROPDOWN, 0, 0);
			}
		});
		cbNoiDKKB.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				onchangeNoiKCB();
			}
		});
		cbNoiDKKB.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.keyCode == 13) {
					String sql = "";
					try (Connection con = DbHelper.getSql2o()) {
						String searchText = cbNoiDKKB.getText().trim()
								.toLowerCase();
						sql = "SELECT * FROM ma_cskcb where LOWER(MA_CODE) like '%"
								+ searchText
								+ "%' or LOWER(MA_NAME) like '%"
								+ searchText + "%' order by MA_RANK DESC";
						System.out.println(sql);
						java.util.List<MaCskcb> listMstXahuyen = con
								.createQuery(sql)
								.executeAndFetch(MaCskcb.class);
						cbTenCSKCB.setVisible(true);
						cbTenCSKCB.removeAll();
						for (MaCskcb obj : listMstXahuyen) {
							cbTenCSKCB.add(obj.MA_CODE + "-" + obj.MA_NAME);
						}
						cbTenCSKCB.select(0);
						cbTenCSKCB.forceFocus();
						OS.SendMessage(cbTenCSKCB.handle, OS.CB_SHOWDROPDOWN, 1, 0);
						// con.close();
					} catch (Exception ee) {
						ee.printStackTrace();
					}
					return;
				} else {
					keyPress(e);
				}
				onchangeNoiKCB();
			}
		});
		cbNoiDKKB.setFont(SWTResourceManager.getFont("Tahoma", 16, SWT.NORMAL));
		cbNoiDKKB.setText("49172");

		txtTuNgay = new DatePicker(grpBhyt, SWT.NONE);
		txtTuNgay.setToolTipText("Từ ngày 2");
		txtTuNgay.setBounds(112, 201, 144, 28);

		txtDenNgay = new DatePicker(grpBhyt, SWT.NONE);
		txtDenNgay.setToolTipText("Đến ngày 1");
		txtDenNgay.setBounds(325, 201, 145, 28);

		cbCapCuu = new Combo(grpBhyt, SWT.NONE);
		cbCapCuu.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				loadCongKhamData();
				int index = cbCapCuu.getSelectionIndex();
				if(index>0){
					updateCongKham();
					isCapCuu = true;
				}
				else{
					isCapCuu = false;
				}
			}
		});
		cbCapCuu.setItems(new String[] { "Không", "1. Tai nạn giao thông",
				"2. Tai nạn lao động", "3. Tai nạn dưới nước", "4. Bỏng",
				"5. Bạo lực, xung đột", "6. Tự tử", "7. Ngộ độc các loại",
				"8. Khác" });
		cbCapCuu.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		cbCapCuu.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		cbCapCuu.setFont(SWTResourceManager.getFont("Tahoma", 12, SWT.BOLD));
		cbCapCuu.setBounds(476, 201, 140, 27);
		cbCapCuu.select(0);
		cbCapCuu.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPress(e);
			}
		});

		Label lblHTn = new Label(grpBhyt, SWT.NONE);
		lblHTn.setBounds(10, 27, 97, 23);
		lblHTn.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		lblHTn.setText("Họ Tên");
		lblHTn.setFont(SWTResourceManager.getFont("Tahoma", 12, SWT.NORMAL));

		Label lblNgySinh = new Label(grpBhyt, SWT.NONE);
		lblNgySinh.setBounds(10, 59, 97, 23);
		lblNgySinh.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		lblNgySinh.setText("Ngày Sinh");
		lblNgySinh
				.setFont(SWTResourceManager.getFont("Tahoma", 12, SWT.NORMAL));

		Label lblNikkcb = new Label(grpBhyt, SWT.NONE);
		lblNikkcb.setBounds(10, 170, 97, 23);
		lblNikkcb.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		lblNikkcb.setText("Nơi ĐKKCB");
		lblNikkcb.setFont(SWTResourceManager.getFont("Tahoma", 12, SWT.NORMAL));

		txtTenCSKCB = new Label(grpBhyt, SWT.NONE);
		txtTenCSKCB.setBounds(208, 166, 408, 28);
		txtTenCSKCB.setFont(SWTResourceManager
				.getFont("Tahoma", 12, SWT.NORMAL));

		Label lblTNgy = new Label(grpBhyt, SWT.NONE);
		lblTNgy.setBounds(10, 201, 76, 23);
		lblTNgy.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		lblTNgy.setText("Từ Ngày");
		lblTNgy.setFont(SWTResourceManager.getFont("Tahoma", 12, SWT.NORMAL));

		Label lblnNgy = new Label(grpBhyt, SWT.NONE);
		lblnNgy.setBounds(268, 201, 40, 23);
		lblnNgy.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		lblnNgy.setText("Đến");
		lblnNgy.setFont(SWTResourceManager.getFont("Tahoma", 12, SWT.NORMAL));

		cbMaHuyen = new Combo(grpBhyt, SWT.NONE);
		cbMaHuyen.setBounds(113, 168, 470, 27);
		cbMaHuyen.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				String dc[] = cbMaHuyen.getText().split("-");
				if (dc.length == 2 && dc[1].length() > 0) {
					txtDiachi.setText(dc[1]);
				}
			}
		});
		cbMaHuyen.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.keyCode == 13) {
					// Choose
					Sql2o sql2o = new Sql2o(Main.DB_URL, Main.DB_USER,
							Main.DB_PASS);

					String sql = "";
					try (Connection con = sql2o.open()) {
						cbMaHuyen.setVisible(false);
						String dc[] = cbMaHuyen.getText().split("-");
						int id = Integer.parseInt(dc[0]);
						sql = "update mst_xahuyen set DC_RANK=DC_RANK+1 where DC_ID="
								+ id;
						Object res = con.createQuery(sql).executeUpdate()
								.getKey();
						// System.out.println(sql + res);
						// /
						if (dc.length == 2 && dc[1].length() > 0) {
							txtDiachi.setText(dc[1]);
						}
						txtDiachi.forceFocus();
						// con.close();
					} catch (Exception ee) {
						ee.printStackTrace();
					}
				}
			}
		});
		cbMaHuyen.setFont(SWTResourceManager.getFont("Tahoma", 12, SWT.NORMAL));
		cbMaHuyen.setVisible(false);

		Label lblHngDvcao = new Label(grpBhyt, SWT.NONE);
		lblHngDvcao.setBounds(10, 239, 97, 23);
		lblHngDvcao.setText("DVCao");
		lblHngDvcao.setFont(SWTResourceManager
				.getFont("Tahoma", 12, SWT.NORMAL));

		txtDichVuCao = new Text(grpBhyt, SWT.NONE);
		txtDichVuCao.setEnabled(false);
		txtDichVuCao.setBounds(113, 234, 46, 28);
		txtDichVuCao.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				// System.out.println("GO txtDichVuCao");
				// cbKhoa.forceFocus();
			}
		});
		txtDichVuCao.setText("-");
		txtDichVuCao.setFont(SWTResourceManager.getFont("Tahoma", 16,
				SWT.NORMAL));
		txtDichVuCao.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPress(e);
			}
		});
		txtThoiDiem5Nam = new DatePicker(grpBhyt, SWT.NONE);
		txtThoiDiem5Nam.setEnabled(false);
		txtThoiDiem5Nam.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		txtThoiDiem5Nam.setBounds(243, 234, 127, 28);
		txtThoiDiem5Nam.setColor(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		txtThoiDiem5Nam.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPress(e);
			}
		});

		Label lblThiim = new Label(grpBhyt, SWT.NONE);
		lblThiim.setBounds(165, 239, 76, 23);
		lblThiim.setText("Thời điểm");
		lblThiim.setFont(SWTResourceManager.getFont("Tahoma", 12, SWT.NORMAL));

		Label lblTnChaM = new Label(grpBhyt, SWT.NONE);
		lblTnChaM.setBounds(10, 278, 97, 23);
		lblTnChaM.setText("Tên Cha mẹ");
		lblTnChaM.setFont(SWTResourceManager.getFont("Tahoma", 12, SWT.NORMAL));

		txtTenChaMe = new Text(grpBhyt, SWT.BORDER);
		txtTenChaMe.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPress(e);
			}
		});
		txtTenChaMe.setBounds(113, 273, 503, 28);
		txtTenChaMe.setFont(SWTResourceManager
				.getFont("Tahoma", 16, SWT.NORMAL));

		Label lblNgyCp = new Label(grpBhyt, SWT.NONE);
		lblNgyCp.setBounds(386, 230, 82, 23);
		lblNgyCp.setText("Ngày cấp");
		lblNgyCp.setFont(SWTResourceManager.getFont("Tahoma", 12, SWT.NORMAL));

		txtNgayCap = new DatePicker(grpBhyt, SWT.NONE);
		txtNgayCap.setEnabled(false);
		txtNgayCap.setBounds(486, 230, 130, 28);
		txtNgayCap.setColor(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		txtNgayCap.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPress(e);
			}
		});

		txtMaQuanLy = new Text(grpBhyt, SWT.BORDER);
		txtMaQuanLy.setEnabled(false);
		txtMaQuanLy.setBounds(536, 123, 40, 28);
		txtMaQuanLy.setFont(SWTResourceManager
				.getFont("Tahoma", 16, SWT.NORMAL));

		txtChuoiKiemTra = new Text(grpBhyt, SWT.BORDER);
		txtChuoiKiemTra.setEnabled(false);
		txtChuoiKiemTra.setBounds(582, 123, 34, 28);
		txtChuoiKiemTra.setFont(SWTResourceManager.getFont("Tahoma", 16,
				SWT.NORMAL));

		cbTenCSKCB = new Combo(grpBhyt, SWT.NONE);
		cbTenCSKCB.setBounds(113, 167, 503, 27);
		cbTenCSKCB.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.keyCode == 13) {
					// Choose
					String sql = "";
					try (Connection con = DbHelper.getSql2o()) {
						cbTenCSKCB.setVisible(false);
						String dc[] = cbTenCSKCB.getText().split("-");
						String id = dc[0].trim();
						sql = "update ma_cskcb set MA_RANK=MA_RANK+1 where MA_CODE='"
								+ id + "'";
						con.createQuery(sql).executeUpdate();
						// /
						if (dc.length == 2 && dc[1].length() > 0) {
							txtTenCSKCB.setText(dc[1].trim());
						}
						cbNoiDKKB.setText(id);
						cbNoiDKKB.forceFocus();
						// con.close();
					} catch (Exception ee) {
						ee.printStackTrace();
					}
				}
			}
		});
		cbTenCSKCB
				.setFont(SWTResourceManager.getFont("Tahoma", 12, SWT.NORMAL));
		cbTenCSKCB.setVisible(false);

		Label lblNs = new Label(grpBhyt, SWT.NONE);
		lblNs.setBounds(370, 61, 26, 23);
		lblNs.setText("NS");
		lblNs.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		lblNs.setFont(SWTResourceManager.getFont("Tahoma", 12, SWT.NORMAL));

		lblKg = new Label(grpBhyt, SWT.NONE);
		lblKg.setBounds(253, 61, 34, 23);
		lblKg.setText("Cân");
		lblKg.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		lblKg.setFont(SWTResourceManager.getFont("Tahoma", 12, SWT.NORMAL));

		cbKhoa = new Combo(grpBhyt, SWT.NONE);
		cbKhoa.setBounds(113, 300, 328, 33);
		cbKhoa.addTraverseListener(new TraverseListener() {
			public void keyTraversed(TraverseEvent e) {

			}
		});
		cbKhoa.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				updateCongKham();

				//
				//
			}
		});
		cbKhoa.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				OS.SendMessage(cbKhoa.handle, OS.CB_SHOWDROPDOWN, 1, 0);
				if (e.keyCode == 13) {
					return;
				}
				//
				keyPress(e);
			}
		});
		cbKhoa.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {

			}
		});
		cbKhoa.setToolTipText("Bấm ENTER để tìm bệnh");
		cbKhoa.setFont(SWTResourceManager.getFont("Tahoma", 16, SWT.NORMAL));
		cbKhoa.setText("");

		cbListBacSiForm = new Combo(grpBhyt, SWT.NONE);
		cbListBacSiForm.setBounds(446, 300, 171, 33);
		cbListBacSiForm.setToolTipText("List DS bac si theo khoa phong");
		cbListBacSiForm.setFont(SWTResourceManager.getFont("Tahoma", 16,
				SWT.NORMAL));
		cbListBacSiForm.setText("");
		
		btnHuyPhieu = new Button(grpBhyt, SWT.NONE);
		btnHuyPhieu.setEnabled(false);
		btnHuyPhieu.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (objPhieuKhamBenh != null) {
					MessageDialog.openInformation(shellKhamBenh, "Chưa có",
							"Chức năng này chưa có");
					// FormHuyGiamPhieuKhamDlg dlg = new
					// FormHuyGiamPhieuKhamDlg(shellKhamBenh, 0);
					// dlg.objPhieuKhamBenh = objPhieuKhamBenh;
					// dlg.open();
				}
			}
		});
		btnHuyPhieu.setToolTipText("Hủy Phiếu/Giảm Tiền");
		btnHuyPhieu.setText("Hủy Phiếu/Giảm Tiền");
		btnHuyPhieu.setImage(SWTResourceManager.getImage(FormKhamBenhDlg.class,
				"/png/delete-3x.png"));
		btnHuyPhieu.setFont(SWTResourceManager
				.getFont("Tahoma", 12, SWT.NORMAL));
		btnHuyPhieu.setBounds(652, 300, 230, 34);
		
		listKhamBenh = new org.eclipse.swt.widgets.List(grpBhyt, SWT.BORDER);
		//listKhamBenh.setEnabled(false);
		listKhamBenh.setBounds(652, 22, 277, 256);

		cbListBacSiForm.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPress(e);
			}
		});

		Group group = new Group(compositeThongTinBenh, SWT.NONE);
		group.setBounds(0, 10, 939, 54);

		lblStatusPhieu = new Label(group, SWT.NONE);
		lblStatusPhieu.setBounds(572, 21, 357, 23);
		lblStatusPhieu.setText("Tình trạng phiếu");
		lblStatusPhieu.setFont(SWTResourceManager.getFont("Tahoma", 12,
				SWT.NORMAL));
		btnRadio4 = new Button(group, SWT.RADIO);
		btnRadio4.setBounds(313, 28, 115, 16);
		btnRadio4.setForeground(SWTResourceManager
				.getColor(SWT.COLOR_LIST_SELECTION));
		btnRadio4.setText("TÁI KHÁM");
		btnRadio4.setFont(SWTResourceManager.getFont("Tahoma", 12, SWT.BOLD));
		btnRadio4.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPress(e);
			}
		});
		btnRadio5 = new Button(group, SWT.RADIO);
		btnRadio5.setEnabled(false);
		btnRadio5.setBounds(344, 28, 87, 16);
		btnRadio5.setForeground(SWTResourceManager
				.getColor(SWT.COLOR_LIST_SELECTION));
		btnRadio5.setText("FREE");
		btnRadio5.setFont(SWTResourceManager.getFont("Tahoma", 12, SWT.BOLD));
		btnRadio5.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPress(e);
			}
		});
		btnRadio3 = new Button(group, SWT.RADIO);
		btnRadio3.setToolTipText("Ctrl+6");
		btnRadio3.setBounds(206, 29, 115, 16);
		btnRadio3.setForeground(SWTResourceManager
				.getColor(SWT.COLOR_LIST_SELECTION));
		btnRadio3.setText("VIỆN PHÍ");
		btnRadio3.setFont(SWTResourceManager.getFont("Tahoma", 12, SWT.BOLD));
		btnRadio3.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPress(e);
			}
		});
		btnRadio2 = new Button(group, SWT.RADIO);
		btnRadio2.setToolTipText("Ctrl+5");
		btnRadio2.setBounds(118, 29, 82, 16);
		btnRadio2.setForeground(SWTResourceManager
				.getColor(SWT.COLOR_LIST_SELECTION));
		btnRadio2.setText("BH39");
		btnRadio2.setFont(SWTResourceManager.getFont("Tahoma", 12, SWT.BOLD));
		btnRadio2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPress(e);
			}
		});
		btnRadio1 = new Button(group, SWT.RADIO);
		btnRadio1.setToolTipText("Ctrl+4");
		btnRadio1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPress(e);
			}
		});
		btnRadio1.setBounds(30, 29, 82, 16);
		btnRadio1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (btnRadio1.getSelection() == true) {
					KIEU_THANH_TOAN = Utils.THANHTOAN_BHYT;
					logger.info("DOI KIEU_THANH_TOAN="+KIEU_THANH_TOAN);
					//update cong kham
					if( objCongKham!=null){
						objCongKham.DON_GIA2 = objCongKham.DON_GIA;
					}
					//
					selectBHYT();
					updateTinhTien();
				}
			}
		});
		btnRadio1.setForeground(SWTResourceManager
				.getColor(SWT.COLOR_LIST_SELECTION));
		btnRadio1.setSelection(true);
		btnRadio1.setFont(SWTResourceManager.getFont("Tahoma", 12, SWT.BOLD));
		btnRadio1.setText("BH29");
		btnRadio2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (btnRadio2.getSelection() == true) {
					KIEU_THANH_TOAN = Utils.THANHTOAN_BHYT_2;
					logger.info("DOI KIEUTHANH TOAN "+KIEU_THANH_TOAN);
					//update cong kham
					if( objCongKham!=null){
						objCongKham.DON_GIA2 = objCongKham.DON_GIA+10000;
					}
					selectBHYT();
					updateTinhTien();
				}
			}
		});
		btnRadio3.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (btnRadio3.getSelection() == true) {
					KIEU_THANH_TOAN = Utils.THANHTOAN_VIENPHI;
					logger.info("DOI KIEUTHANH TOAN "+KIEU_THANH_TOAN);
					selectVienPhi();
					updateTinhTien();
				}
			}
		});
		btnRadio5.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (btnRadio5.getSelection() == true) {
					KIEU_THANH_TOAN = Utils.THANHTOAN_VIENPHI_FREE;
					selectVienPhi();
					updateTinhTien();
				}
			}
		});
		btnRadio4.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (btnRadio4.getSelection() == true) {
					KIEU_THANH_TOAN = Utils.THANHTOAN_TAI_KHAM;
					selectTaiKham();
					updateTinhTien();
				}
			}
		});
		TabItem tabItemXetNghiemCanLamSan = new TabItem(tabFolder, SWT.NONE);
		tabItemXetNghiemCanLamSan.setToolTipText("Ctrl+2");
		tabItemXetNghiemCanLamSan.setImage(SWTResourceManager.getImage(
				FormKhamBenhDlg.class, "/png/aperture-3x.png"));
		tabItemXetNghiemCanLamSan.setText("CLS/Xét Nghiệm (Ctrl+2)");

		Composite compositeCLS = new Composite(tabFolder, SWT.NONE);
		tabItemXetNghiemCanLamSan.setControl(compositeCLS);
		compositeCLS.setLayout(new BorderLayout(0, 0));

		Composite compositeTab1 = new Composite(compositeCLS, SWT.NONE);
		compositeTab1.setLayout(null);
		cbListBacSiCLS = new Combo(compositeTab1, SWT.NONE);
		cbListBacSiCLS.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				cbListBacSiForm.setText(cbListBacSiCLS.getText());
				cbListBacsiThuoc.setText(cbListBacSiCLS.getText());
			}
		});
		cbListBacSiCLS.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.keyCode == 13) {
					if (((e.stateMask & SWT.CTRL) == SWT.CTRL)) {
						// Update BS cho CLS. UPDATE ALL
						for (DvChitiet obj : listCLSData) {
							if (DbHelper.hashDataUsers.get(cbListBacSiCLS.getText()) != null) {
								obj.MA_BAC_SI = DbHelper.hashDataUsers.get(cbListBacSiCLS.getText()).MACCHN;
							}
						}
						tableViewerSelectedCLS.refresh();
					} else if (((e.stateMask & SWT.ALT) == SWT.ALT)) {
						// Update BS cho CLS. UPDATE ONLY SELECTED
						if (tableSelectedCLS.getSelectionCount() == 0) {
							return;
						}
						TableItem item = tableSelectedCLS.getSelection()[0];
						DvChitiet objDVChiTiet = (DvChitiet) item.getData();
						if (objDVChiTiet != null) {
							if (DbHelper.hashDataUsers.get(cbListBacSiCLS
									.getText()) != null) {
								objDVChiTiet.MA_BAC_SI = DbHelper.hashDataUsers
										.get(cbListBacSiCLS.getText()).MACCHN;
							}
						}
						//
						tableViewerSelectedCLS.refresh();
					} else {
						txtBenhBanDau.forceFocus();
					}
				} else if (e.keyCode == SWT.ARROW_RIGHT) {
					txtBenhBanDau.forceFocus();
				} else {
					// keyPressCLS(e);
				}
				keyPress(e);
			}
		});
		cbListBacSiCLS.setBackground(SWTResourceManager.getColor(SWT.COLOR_GREEN));
		cbListBacSiCLS.setFont(SWTResourceManager.getFont("Tahoma", 14,
				SWT.NORMAL));
		cbListBacSiCLS.setBounds(0, 12, 160, 31);

		txtBenhBanDau = new Text(compositeTab1, SWT.BORDER);
		txtBenhBanDau.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.keyCode == 13 || e.keyCode == SWT.ARROW_DOWN) {
					// Do list all benh ban dau
					txtBenhBanDau.setVisible(false);
					loadListBenhBanDau();
					cbBenhBanDau.setVisible(true);
					cbBenhBanDau.setText(txtBenhBanDau.getText());
					cbBenhBanDau.forceFocus();
					//
				} else if (e.keyCode == SWT.ARROW_LEFT) {
					if (txtBenhBanDau.getCaretPosition() == 0) {
						cbListBacSiCLS.forceFocus();
					}
				} else if (e.keyCode == SWT.ARROW_RIGHT) {
					if (txtBenhBanDau.getCaretPosition() == txtBenhBanDau
							.getText().length()) {
						txtSearchCLS.forceFocus();
					}
				} else {
					keyPress(e);
				}
			}
		});
		txtBenhBanDau.setFont(SWTResourceManager.getFont("Tahoma", 12,
				SWT.NORMAL));
		txtBenhBanDau.setBackground(SWTResourceManager
				.getColor(SWT.COLOR_GREEN));
		txtBenhBanDau.setBounds(166, 10, 402, 33);

		cbBenhBanDau = new Combo(compositeTab1, SWT.NONE);
		cbBenhBanDau.setFont(SWTResourceManager.getFont("Tahoma", 14,
				SWT.NORMAL));
		cbBenhBanDau.setBounds(176, 12, 392, 31);
		cbBenhBanDau.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.keyCode == 13) {
					// Do list all benh ban dau
					txtBenhBanDau.setVisible(true);
					cbBenhBanDau.setVisible(false);
					String temp = cbBenhBanDau.getText();
					temp = Utils.doCapitalizeFirstLetterInString(temp);
					txtBenhBanDau.setText(temp);
					// Do load example code
					// doLoadCanLamSanLatest(temp);
					//
					txtSearchCLS.selectAll();
					txtSearchCLS.forceFocus();
					//
				} else {
					keyPress(e);
				}
			}
		});

		txtSearchCLS = new Text(compositeTab1, SWT.BORDER);
		txtSearchCLS.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.keyCode == 13) {
					reloadTableCtListCLS();
				} else if (e.keyCode == SWT.ARROW_LEFT) {
					// System.out.println("txtSearchCLS ="+txtSearchCLS.getCaretPosition());
					if (txtSearchCLS.getCaretPosition() == 0) {
						txtBenhBanDau.forceFocus();
					}
				} else if (e.keyCode == SWT.ARROW_RIGHT) {
					//
				} else {
					keyPress(e);
				}
			}
		});
		txtSearchCLS
				.setBackground(SWTResourceManager.getColor(SWT.COLOR_GREEN));
		txtSearchCLS.setFont(SWTResourceManager.getFont("Tahoma", 12,
				SWT.NORMAL));
		txtSearchCLS.setBounds(574, 10, 232, 33);

		btnVp = new Button(compositeTab1, SWT.CHECK);
		btnVp.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPress(e);
			}
		});
		btnVp.setBounds(814, 27, 47, 16);
		btnVp.setText("Tự CT (Ctrl+Enter)");
		btnVp.setSelection(true);

		tableViewerCLS = new TableViewer(compositeTab1, SWT.BORDER
				| SWT.FULL_SELECTION);
		tableCLS = tableViewerCLS.getTable();
		tableCLS.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.keyCode == SWT.ARROW_UP) {
					// check is top, go to txtSearchCLS
					if (tableCLS.getSelectionIndex() == 0) {
						// Top
						txtSearchCLS.selectAll();
						txtSearchCLS.forceFocus();
					}
					//
				} else if (e.keyCode == 13) {
					// Move to list bac si
					if (cbListBacSiCLS.getSelectionIndex() == 0) {
						// cbListBacSiCLS.forceFocus();
					}
					if (((e.stateMask & SWT.CTRL) == SWT.CTRL)) {
						btnVp.setSelection(true);
					} else {
						btnVp.setSelection(false);
					}
					//
					saveAndAddCLS();
					//
					txtSearchCLS.selectAll();
					txtSearchCLS.forceFocus();
					//
				} else {
					keyPress(e);
				}
			}
		});
		tableCLS.setBackground(SWTResourceManager.getColor(SWT.COLOR_GREEN));
		tableCLS.setBounds(0, 49, 949, 139);
		tableCLS.setLinesVisible(true);
		tableCLS.setHeaderVisible(true);

		TableColumn tableColumn_17 = new TableColumn(tableCLS, SWT.NONE);
		tableColumn_17.setWidth(121);
		tableColumn_17.setText("MÃ DV");

		TableColumn tableColumn_18 = new TableColumn(tableCLS, SWT.NONE);
		tableColumn_18.setWidth(275);
		tableColumn_18.setText("TÊN DV");

		TableColumn tableColumn_19 = new TableColumn(tableCLS, SWT.RIGHT);
		tableColumn_19.setWidth(65);
		tableColumn_19.setText("GIÁ BH");

		TableColumn tableColumn_20 = new TableColumn(tableCLS, SWT.RIGHT);
		tableColumn_20.setWidth(65);
		tableColumn_20.setText("GIÁ ");

		TableColumn tableColumn_21 = new TableColumn(tableCLS, SWT.NONE);
		tableColumn_21.setWidth(53);
		tableColumn_21.setText("NHÓM");

		TableColumn tableColumn_22 = new TableColumn(tableCLS, SWT.NONE);
		tableColumn_22.setWidth(85);
		tableColumn_22.setText("QĐ");

		TableColumn tableColumn_23 = new TableColumn(tableCLS, SWT.NONE);
		tableColumn_23.setWidth(100);
		tableColumn_23.setText("New Column");

		TableColumn tableColumn_24 = new TableColumn(tableCLS, SWT.NONE);
		tableColumn_24.setWidth(100);
		tableColumn_24.setText("New Column");

		TableColumn tableColumn_25 = new TableColumn(tableCLS, SWT.NONE);
		tableColumn_25.setWidth(100);
		tableColumn_25.setText("New Column");
		//
		tableViewerSelectedCLS = new TableViewer(compositeTab1, SWT.BORDER
				| SWT.FULL_SELECTION);
		tableSelectedCLS = tableViewerSelectedCLS.getTable();
		tableSelectedCLS.setBounds(0, 194, 949, 172);
		tableSelectedCLS.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				doViewCLS();
			}
		});
		tableSelectedCLS.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				tableSelectedCLS.select(0);
			}

			@Override
			public void focusLost(FocusEvent e) {
				tableCLS.forceFocus();
			}
		});
		tableSelectedCLS.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressCLS(e);
			}
		});
		tableSelectedCLS.setLinesVisible(true);
		tableSelectedCLS.setHeaderVisible(true);
		//
		tableViewerSelectedCLS.setLabelProvider(new TableLabelProviderCLS());
		tableViewerSelectedCLS.setContentProvider(new ContentProviderCLS());
		tableViewerSelectedCLS.setInput(listCLSData);

		TableColumn tableColumn = new TableColumn(tableSelectedCLS, SWT.NONE);
		tableColumn.setWidth(42);
		tableColumn.setText("STT");

		TableColumn tableColumnMaCLS = new TableColumn(tableSelectedCLS,
				SWT.NONE);
		tableColumnMaCLS.setWidth(121);
		tableColumnMaCLS.setText("Mã CLS");

		TableColumn tblclmnTnCnLm = new TableColumn(tableSelectedCLS, SWT.NONE);
		tblclmnTnCnLm.setWidth(189);
		tblclmnTnCnLm.setText("Tên cận lâm sàn");

		TableColumn tableColumn_3 = new TableColumn(tableSelectedCLS, SWT.NONE);
		tableColumn_3.setWidth(45);
		tableColumn_3.setText("SL");

		TableColumn tableColumn_4 = new TableColumn(tableSelectedCLS, SWT.NONE);
		tableColumn_4.setWidth(100);
		tableColumn_4
				.setToolTipText("Status của người bệnh, (Đang cận LS, xong cận LS nào đó hay chưa )");
		tableColumn_4.setText("Tình trạng");

		Menu menu_1 = new Menu(tableSelectedCLS);
		tableSelectedCLS.setMenu(menu_1);

		MenuItem menuItem_12 = new MenuItem(menu_1, SWT.NONE);
		menuItem_12.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				newDichVuCLS();
			}
		});
		menuItem_12.setText("Chỉ định Cận Lâm Sàn");

		TableColumn tblclmnTng = new TableColumn(tableSelectedCLS, SWT.NONE);
		tblclmnTng.setWidth(100);
		tblclmnTng
				.setToolTipText("Status của người bệnh, (Đang cận LS, xong cận LS nào đó hay chưa )");
		tblclmnTng.setText("Tổng");

		TableColumn tblclmnBh = new TableColumn(tableSelectedCLS, SWT.NONE);
		tblclmnBh.setWidth(78);
		tblclmnBh
				.setToolTipText("Status của người bệnh, (Đang cận LS, xong cận LS nào đó hay chưa )");
		tblclmnBh.setText("BH");

		TableColumn tblclmnNb = new TableColumn(tableSelectedCLS, SWT.NONE);
		tblclmnNb.setWidth(69);
		tblclmnNb
				.setToolTipText("Status của người bệnh, (Đang cận LS, xong cận LS nào đó hay chưa )");
		tblclmnNb.setText("NB");

		TableColumn tblclmnBsChnh = new TableColumn(tableSelectedCLS, SWT.NONE);
		tblclmnBsChnh.setWidth(74);
		tblclmnBsChnh.setText("BS Chỉ Định");

		TableColumn tblclmnKiuTt = new TableColumn(tableSelectedCLS, SWT.NONE);
		tblclmnKiuTt.setWidth(50);
		tblclmnKiuTt.setText("Kiểu TT");
		
		TableColumn tblclmnTht = new TableColumn(tableSelectedCLS, SWT.NONE);
		tblclmnTht.setWidth(46);
		tblclmnTht.setText("ThT");
		// Tab THUOC BEGIN
		TabItem tabItemThuoc = new TabItem(tabFolder, SWT.NONE);
		tabItemThuoc.setToolTipText("Ctrl+3");
		tabItemThuoc.setImage(SWTResourceManager.getImage(
				FormKhamBenhDlg.class, "/png/medical-cross-3x.png"));
		tabItemThuoc.setText("Thuốc (Ctrl+3)");

		Composite compositeThuoc = new Composite(tabFolder, SWT.NONE);
		tabItemThuoc.setControl(compositeThuoc);
		compositeThuoc.setLayout(null);

		
		Composite compositeIDCBenh = new Composite(compositeThuoc, SWT.NONE);
		compositeIDCBenh.setBounds(0, 0, 1012, 107);
		compositeIDCBenh.setLayout(null);

		Group grpMBnhTn = new Group(compositeIDCBenh, SWT.NONE);
		grpMBnhTn.setBounds(10, 0, 910, 107);

		cbMaBenh = new Combo(grpMBnhTn, SWT.NONE);
		cbMaBenh.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				Mabenh obj = DbHelper.hashDataMabenh.get(cbMaBenh.getText().trim());
				if(obj!=null){
					txtTenBenh.setText(obj.MABENH_NAME);
				}
				else{
					MessageDialog.openError(shellKhamBenh, "Có lỗi", "Tên Bệnh phải có");
					cbMaBenh.forceFocus();
				}
			}
		});
		cbMaBenh.setFont(SWTResourceManager.getFont("Tahoma", 12, SWT.NORMAL));
		cbMaBenh.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.keyCode == 13) {
					//
					if (DbHelper.checkPhanQuyen(DbHelper.DELETE, "mabenh") == false) {
						logger.info("Bạn ("
								+ DbHelper.getCurrentSessionUserName()
								+ "), không có quyền làm");
						MessageDialog.openError(shellKhamBenh, "Có lỗi",
								"Bạn (" + DbHelper.getCurrentSessionUserName()
										+ "), không có quyền làm");
						return;
					}
					//
					try {
						Connection con = DbHelper.getSql2o();
						String searchText = cbMaBenh.getText().trim()
								.toLowerCase();
						String sql = "select MABENH_ID, MABENH_NAME from mabenh where LOWER(MABENH_ID) like '%"
								+ searchText
								+ "%' or LOWER(MABENH_NAME) like '%"
								+ searchText + "%' order by MABENH_RANK DESC";
						// System.out.println(sql);
						List<Row> listRow = con.createQuery(sql)
								.executeAndFetchTable().rows();
						cbTenBenh.setVisible(true);
						cbTenBenh.removeAll();
						for (Row row : listRow) {
							Mabenh obj = new Mabenh();
							obj.MABENH_ID = row.getString(0);
							obj.MABENH_NAME = row.getString(1);
							cbTenBenh
									.add(obj.MABENH_ID + "-" + obj.MABENH_NAME);
						}
						cbTenBenh.select(0);
						cbTenBenh.forceFocus();
						OS.SendMessage(cbTenBenh.handle, OS.CB_SHOWDROPDOWN, 1,
								0);
						// con.close();
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				} else {
					keyPressThuoc(e);
				}
			}
		});
		cbMaBenh.setBounds(130, 10, 161, 31);
		cbMaBenh.setToolTipText("Bấm ENTER để tìm bệnh");
		cbMaBenh.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));

		Label lblMBnhChnh = new Label(grpMBnhTn, SWT.NONE);
		lblMBnhChnh.setBounds(10, 18, 114, 23);
		lblMBnhChnh.setText("Mã bệnh chính");

		txtTenBenh = new Label(grpMBnhTn, SWT.BORDER);
		txtTenBenh
				.setFont(SWTResourceManager.getFont("Tahoma", 13, SWT.NORMAL));
		txtTenBenh.setBackground(SWTResourceManager
				.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		txtTenBenh.setBounds(297, 12, 603, 28);

		cbTenBenh = new Combo(grpMBnhTn, SWT.NONE);
		cbTenBenh.setBounds(130, 10, 628, 33);
		cbTenBenh.setFont(SWTResourceManager.getFont("Tahoma", 14, SWT.NORMAL));
		cbTenBenh.setVisible(false);
		cbTenBenh.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.keyCode == 13) {
					try {
						//
						if (DbHelper.checkPhanQuyen(DbHelper.DELETE, "mabenh") == false) {
							logger.info("Bạn ("
									+ DbHelper.getCurrentSessionUserName()
									+ "), không có quyền làm");
							MessageDialog.openError(
									shellKhamBenh,
									"Có lỗi",
									"Bạn ("
											+ DbHelper
													.getCurrentSessionUserName()
											+ "), không có quyền làm");
							return;
						}
						//
						Connection con = DbHelper.getSql2o();
						String str[] = cbTenBenh.getText().split("-");
						String sql = "update mabenh set MABENH_RANK=MABENH_RANK+1 where MABENH_ID='"+ str[0].trim() + "'";
						// System.out.println(sql);
						con.createQuery(sql).executeUpdate();
						cbTenBenh.setVisible(false);
						String MA_BENH = str[0].trim();
						String TEN_BENH = str[1].trim();
						cbMaBenh.setText(MA_BENH);
						cbMaBenh.forceFocus();
						txtTenBenh.setText(TEN_BENH);
						//
						if(MA_BENH.length()>0){
							//doLoadThuocLastest(MA_BENH);
						}
						// con.close();
					} catch (Exception ex) {
						logger.error(ex);
					}
				}
			}
		});
		Label lblMBnhPh = new Label(grpMBnhTn, SWT.NONE);
		lblMBnhPh.setBounds(10, 47, 114, 23);
		lblMBnhPh.setText("Mã bệnh phụ");

		cbMabenhKhac = new Combo(grpMBnhTn, SWT.NONE);
		cbMabenhKhac.setFont(SWTResourceManager.getFont("Tahoma", 12,
				SWT.NORMAL));
		cbMabenhKhac.setBounds(130, 49, 161, 33);
		cbMabenhKhac.setToolTipText("Bấm ENTER để tìm bệnh");
		cbMabenhKhac
				.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		cbMabenhKhac.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.keyCode == 13) {
					try {
						Connection con = DbHelper.getSql2o();
						String searchText = cbMabenhKhac.getText().trim()
								.toLowerCase();
						String sql = "select MABENH_ID, MABENH_NAME from mabenh where LOWER(MABENH_ID) like '%"
								+ searchText
								+ "%' or LOWER(MABENH_NAME) like '%"
								+ searchText + "%' order by MABENH_RANK DESC";
						// System.out.println(sql);
						List<Row> listRow = con.createQuery(sql)
								.executeAndFetchTable().rows();
						cbTenBenhPhu.setVisible(true);
						cbTenBenhPhu.removeAll();
						for (Row row : listRow) {
							Mabenh obj = new Mabenh();
							obj.MABENH_ID = row.getString(0);
							obj.MABENH_NAME = row.getString(1);
							cbTenBenhPhu.add(obj.MABENH_ID + "-"
									+ obj.MABENH_NAME);
						}
						cbTenBenhPhu.select(0);
						cbTenBenhPhu.forceFocus();
						OS.SendMessage(cbTenBenhPhu.handle, OS.CB_SHOWDROPDOWN,
								1, 0);
						// con.close();
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				} else {
					keyPressThuoc(e);
				}
			}
		});

		txtTenBenhPhu = new Text(grpMBnhTn, SWT.BORDER);
		txtTenBenhPhu.setFont(SWTResourceManager.getFont("Tahoma", 13,
				SWT.NORMAL));
		txtTenBenhPhu.setBackground(SWTResourceManager
				.getColor(SWT.COLOR_WHITE));
		txtTenBenhPhu.setBounds(297, 48, 603, 28);
		txtTenBenhPhu.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.keyCode == SWT.DEL) {
					String tmpText = "";
					String tmp[] = txtTenBenhPhu.getText().trim().split(";");
					if (tmp.length > 1) {
						for (int i = 0; i < tmp.length - 1; i++) {
							tmpText += tmp[i];
							if (i < tmp.length - 2) {
								tmpText += ";";
							}
						}
					} else {
						txtTenBenhPhu.setText("");
						cbTenBenhPhu.forceFocus();
					}
					//
					if (tmpText.length() > 0) {
						txtTenBenhPhu.setText(tmpText);
					}
					e.doit = false;
				}
			}
		});
		cbTenBenhPhu = new Combo(grpMBnhTn, SWT.NONE);
		cbTenBenhPhu.setBounds(130, 49, 628, 29);
		cbTenBenhPhu.setFont(SWTResourceManager.getFont("Tahoma", 14,
				SWT.NORMAL));
		cbTenBenhPhu.setVisible(false);
		cbTenBenhPhu.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.keyCode == 13) {
					try {
						Connection con = DbHelper.getSql2o();
						String str[] = cbTenBenhPhu.getText().split("-");
						String mabenh = str[0].trim();
						//
						if (mabenh.length() == cbMaBenh.getText().length()
								&& cbMaBenh.getText().indexOf(mabenh) > -1) {
							cbTenBenhPhu.setVisible(false);
							cbMabenhKhac.forceFocus();
							return;
						}
						//
						String sql = "update mabenh set MABENH_RANK=MABENH_RANK+1 where MABENH_ID='"
								+ str[0].trim() + "'";
						// System.out.println(sql);
						con.createQuery(sql).executeUpdate();
						cbTenBenhPhu.setVisible(false);
						cbMabenhKhac.forceFocus();
						String current = txtTenBenhPhu.getText();
						if (current.length() == 0) {
							txtTenBenhPhu.setText(str[0].trim());
						} else {
							txtTenBenhPhu.setText(txtTenBenhPhu.getText() + ";"
									+ str[0].trim());
						}
						// con.close();
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
			}
		});
		Label lblBcSCh = new Label(grpMBnhTn, SWT.NONE);
		lblBcSCh.setText("Bác sĩ chỉ định");
		lblBcSCh.setBounds(10, 76, 82, 13);

		cbListBacsiThuoc = new Combo(grpMBnhTn, SWT.NONE);
		cbListBacsiThuoc.setBounds(130, 76, 161, 21);
		cbListBacsiThuoc.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				cbListBacSiForm.setText(cbListBacsiThuoc.getText());
			}
		});

		Composite compositeTHUOCCenter = new Composite(compositeThuoc, SWT.NONE);
		compositeTHUOCCenter.setBounds(0, 113, 1012, 280);
		compositeTHUOCCenter.setLayout(null);

		cbKhoThuoc = new Combo(compositeTHUOCCenter, SWT.NONE);
		cbKhoThuoc.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String khoname = cbKhoThuoc.getText().trim();
				objKhoHang = DbHelper.hashDataKhoHang.get(khoname);
				reloadTableChitietNhapthuoc();
			}
		});
		cbKhoThuoc
				.setFont(SWTResourceManager.getFont("Tahoma", 13, SWT.NORMAL));
		cbKhoThuoc.setBounds(0, 13, 127, 28);
		cbKhoThuoc.setText("Kho bảo hiểm");

		txtSearchCtNhapthuoc = new Text(compositeTHUOCCenter, SWT.BORDER);
		txtSearchCtNhapthuoc.setBounds(133, 13, 225, 33);
		txtSearchCtNhapthuoc.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.keyCode == 13) {
					reloadTableChitietNhapthuoc();
				}
			}
		});
		txtSearchCtNhapthuoc.setFont(SWTResourceManager.getFont("Tahoma", 12,
				SWT.NORMAL));
		txtSearchCtNhapthuoc.setBackground(SWTResourceManager
				.getColor(SWT.COLOR_GREEN));

		txtThuocSoLuongChiDinh = new Text(compositeTHUOCCenter, SWT.BORDER
				| SWT.RIGHT);
		txtThuocSoLuongChiDinh.setBounds(364, 13, 57, 33);

		txtThuocSoLuongChiDinh.setText("0");
		txtThuocSoLuongChiDinh.setFont(SWTResourceManager.getFont("Tahoma", 12,
				SWT.NORMAL));
		txtThuocSoLuongChiDinh.setBackground(SWTResourceManager
				.getColor(SWT.COLOR_GREEN));
		cbLieuDung = new Combo(compositeTHUOCCenter, SWT.NONE);
		cbLieuDung.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				OS.SendMessage(cbLieuDung.handle, OS.CB_SHOWDROPDOWN, 1, 0);

			}
		});
		cbLieuDung.setBounds(427, 10, 384, 31);
		cbLieuDung.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.keyCode == 13) {
					// Do list all benh ban dau
					txtThuocSoLuongChiDinh.selectAll();
					txtThuocSoLuongChiDinh.forceFocus();
					// Add thuoc
					if (selectSoluongThuocAndAdd() == true) {
						// Move to add thuoc
						txtSearchCtNhapthuoc.selectAll();
						txtSearchCtNhapthuoc.forceFocus();
					} 
					//
				}
				else
				{
					keyPress(e);
				}
			}
		});
		cbLieuDung.setFont(SWTResourceManager.getFont("Tahoma", 14, SWT.NORMAL));
		cbLieuDung.setVisible(true);
		cbLieuDung.select(0);

		//
		
		tableViewerCtNhapthuoc = new TableViewer(compositeTHUOCCenter,SWT.BORDER | SWT.FULL_SELECTION);
		tableCtNhapthuoc = tableViewerCtNhapthuoc.getTable();
		tableCtNhapthuoc.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.keyCode == SWT.ARROW_UP) {
					if (tableCtNhapthuoc.getSelectionIndex() == 0) {
						//
						txtSearchCtNhapthuoc.selectAll();
						txtSearchCtNhapthuoc.forceFocus();
						//
					}
				} else if (e.keyCode == 13) {
					// Move to lieu dung
					// txtLieuDung.selectAll();
					// txtLieuDung.forceFocus();
					// Move to soluong
					txtThuocSoLuongChiDinh.selectAll();
					txtThuocSoLuongChiDinh.forceFocus();
				}
			}
		});
		tableCtNhapthuoc.setBounds(0, 52, 1012, 98);
		tableCtNhapthuoc.setBackground(SWTResourceManager
				.getColor(SWT.COLOR_GREEN));
		tableCtNhapthuoc.setLinesVisible(true);
		tableCtNhapthuoc.setHeaderVisible(true);

		TableColumn tableColumn_26 = new TableColumn(tableCtNhapthuoc, SWT.LEFT);
		tableColumn_26.setWidth(96);
		tableColumn_26.setText("KHO");

		TableColumn tableColumn_27 = new TableColumn(tableCtNhapthuoc, SWT.LEFT);
		tableColumn_27.setWidth(182);
		tableColumn_27.setText("TÊN THUỐC");
		
		TableColumn tableColumn_2 = new TableColumn(tableCtNhapthuoc, SWT.NONE);
		tableColumn_2.setWidth(72);
		tableColumn_2.setText("Hàm Lượng");

		TableColumn tableColumn_28 = new TableColumn(tableCtNhapthuoc, SWT.LEFT);
		tableColumn_28.setWidth(80);
		tableColumn_28.setText("ĐV TÍNH");

		TableColumn tableColumn_29 = new TableColumn(tableCtNhapthuoc, SWT.NONE);
		tableColumn_29.setWidth(108);
		tableColumn_29.setText("HẠN DÙNG");

		TableColumn tableColumn_30 = new TableColumn(tableCtNhapthuoc,
				SWT.RIGHT);
		tableColumn_30.setWidth(73);
		tableColumn_30.setText("ĐƠN GIÁ");

		TableColumn tableColumn_31 = new TableColumn(tableCtNhapthuoc,
				SWT.RIGHT);
		tableColumn_31.setWidth(90);
		tableColumn_31.setToolTipText("Tồn kho, chỉ cấp số lượng < số này");
		tableColumn_31.setText("TỒN KHO");

		TableColumn tableColumn_32 = new TableColumn(tableCtNhapthuoc,
				SWT.RIGHT);
		tableColumn_32.setWidth(76);
		tableColumn_32.setToolTipText("SL thuốc BS đã cho, đang đợi xuất kho");
		tableColumn_32.setText("SẼ CẤP");

		TableColumn tableColumn_33 = new TableColumn(tableCtNhapthuoc,
				SWT.RIGHT);
		tableColumn_33.setWidth(76);
		tableColumn_33.setToolTipText("SL thuốc đã cấp cho người bệnh");
		tableColumn_33.setText("ĐÃ CẤP");

		TableColumn tableColumn_34 = new TableColumn(tableCtNhapthuoc,
				SWT.RIGHT);
		tableColumn_34.setWidth(85);
		tableColumn_34.setToolTipText("SL nhập ban đầu");
		tableColumn_34.setText("SL NHẬP");

		tableViewerTHUOC = new TableViewer(compositeTHUOCCenter, SWT.BORDER
				| SWT.FULL_SELECTION);
		tableTHUOC = tableViewerTHUOC.getTable();
		tableTHUOC.setBounds(0, 156, 1012, 124);
		tableTHUOC.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				tableTHUOC.setSelection(0);
			}
		});
		tableTHUOC.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				doViewThuoc();
			}
		});
		tableTHUOC.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		tableTHUOC.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressThuoc(e);
			}
		});
		tableTHUOC.setLinesVisible(true);
		tableTHUOC.setHeaderVisible(true);

		TableColumn tableColumn_1 = new TableColumn(tableTHUOC, SWT.NONE);
		tableColumn_1.setWidth(45);
		tableColumn_1.setText("STT");

		TableColumn tblclmnTnThuc = new TableColumn(tableTHUOC, SWT.NONE);
		tblclmnTnThuc.setWidth(200);
		tblclmnTnThuc.setText("Tên thuốc");

		TableColumn tblclmnHotCht = new TableColumn(tableTHUOC, SWT.NONE);
		tblclmnHotCht.setWidth(106);
		tblclmnHotCht.setText("Hoạt chất");

		TableColumn tblclmnngGi = new TableColumn(tableTHUOC, SWT.NONE);
		tblclmnngGi.setWidth(121);
		tblclmnngGi.setText("Đóng gói");

		TableColumn tblclmnMThuc = new TableColumn(tableTHUOC, SWT.NONE);
		tblclmnMThuc.setWidth(103);
		tblclmnMThuc.setText("Hàm Lượng");

		TableColumn tableColumn_7 = new TableColumn(tableTHUOC, SWT.NONE);
		tableColumn_7.setWidth(42);
		tableColumn_7.setText("SL");

		TableColumn tableColumn_8 = new TableColumn(tableTHUOC, SWT.NONE);
		tableColumn_8.setWidth(70);
		tableColumn_8
				.setToolTipText("Status của người bệnh, (Đang cận LS, xong cận LS nào đó hay chưa )");
		tableColumn_8.setText("Tình trạng");

		Menu menu = new Menu(tableTHUOC);
		tableTHUOC.setMenu(menu);

		MenuItem mntmChnhThuc = new MenuItem(menu, SWT.NONE);
		mntmChnhThuc.setText("Chỉ định thuốc");

		TableColumn tblclmnTt = new TableColumn(tableTHUOC, SWT.NONE);
		tblclmnTt.setWidth(40);
		tblclmnTt
				.setToolTipText("Status của người bệnh, (Đang cận LS, xong cận LS nào đó hay chưa )");
		tblclmnTt.setText("TT");

		TableColumn tableColumn_5 = new TableColumn(tableTHUOC, SWT.NONE);
		tableColumn_5.setWidth(40);
		tableColumn_5
				.setToolTipText("Status của người bệnh, (Đang cận LS, xong cận LS nào đó hay chưa )");
		tableColumn_5.setText("TT");

		TableColumn tblclmnNewColumn = new TableColumn(tableTHUOC, SWT.NONE);
		tblclmnNewColumn.setWidth(183);
		tblclmnNewColumn.setText("Liều Dùng");
		
		TableColumn tblclmnTht_1 = new TableColumn(tableTHUOC, SWT.NONE);
		tblclmnTht_1.setWidth(48);
		tblclmnTht_1.setText("ThT");

		
		txtNgaySinhTheoThang = new Label(compositeTHUOCCenter, SWT.NONE);
		txtNgaySinhTheoThang.setBounds(830, 13, 172, 33);
		//
		
		//
		txtThuocSoLuongChiDinh.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.keyCode == 13) {
					// Move to lieu dung
					cbLieuDung.forceFocus();
					//
				}
			}
		});
		tableViewerTHUOC.setLabelProvider(new TableLabelProviderTHUOC());
		tableViewerTHUOC.setContentProvider(new ContentProviderTHUOC());
		tableViewerTHUOC.setColumnProperties(CellModifierThuoc.columnPropeties);
		tableViewerTHUOC.setCellModifier(new CellModifierThuoc(tableViewerTHUOC));
		tableViewerTHUOC.setInput(listThuocData);

		// Nghialuong3
		Composite compositeGroupButtonEnd = new Composite(compositePhieuKham,
				SWT.NONE);
		compositeGroupButtonEnd.setLayoutData(BorderLayout.SOUTH);
		compositeGroupButtonEnd.setFont(SWTResourceManager.getFont("Tahoma",
				12, SWT.NORMAL));
		compositeGroupButtonEnd.setLayout(new GridLayout(7, false));

		btnNewPhieu = new Button(compositeGroupButtonEnd, SWT.NONE);
		btnNewPhieu.setToolTipText("Tạo mới, bấm ESC");
		btnNewPhieu.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				// shellKhamBenh.close();
				resetFormKhamBenh();
			}
		});
		btnNewPhieu.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPress(e);
			}
		});
		btnNewPhieu.setText("Mới (ESC)");
		btnNewPhieu.setImage(SWTResourceManager.getImage(FormKhamBenhDlg.class,
				"/png/fire-3x.png"));
		btnNewPhieu.setFont(SWTResourceManager
				.getFont("Tahoma", 12, SWT.NORMAL));
		btnNewPhieu.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				txtMathe.forceFocus();
			}
		});
		
		Button btnCheckMTh = new Button(compositeGroupButtonEnd, SWT.NONE);
		btnCheckMTh.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(KIEU_THANH_TOAN==Utils.THANHTOAN_BHYT || KIEU_THANH_TOAN==Utils.THANHTOAN_BHYT_2){
					BenhNhan obj = getBenhNhanDlg();
					if(obj.MA_THE==null || (obj.MA_THE!=null && obj.MA_THE.length()==0)){
						MessageDialog.openError(shellKhamBenh, "Có lỗi", "Chưa có MÃ THẺ");
						return;
					}
					objCheck = DbHelper.objBHYTThread.checkDataBHYT(obj);
					if(objCheck.checkCode!=0){
						//
						//
						if(objCheck.checkCode==7){
							// Sai ngay sinh...
							txtNgaySinh.isNgaySinh0101 = true;
							obj.setNGAY_SINH(txtNgaySinh.getDateByYear());
							objCheck = DbHelper.objBHYTThread.checkDataBHYT(obj);
							if(objCheck.checkCode==7){
								MessageDialog.openError(shellKhamBenh, "Có lỗi", objCheck.checkText + ". Ngày sinh: " + obj.NGAY_SINH+". Thử check lại lần nữa.");
							}
							else{
								// OK
								btnSave.setEnabled(true);
								btnTHANHTOAN.setEnabled(true);
								btnInPhieu.setEnabled(true);
								//
								listKhamBenh.removeAll();
								for(int i=0; i<objCheck.listKhamBenh.size(); i++){
									listKhamBenh.add(objCheck.listKhamBenh.get(i));
								}
							}
						}
						else{
							MessageDialog.openError(shellKhamBenh, "Có lỗi", objCheck.checkText);
						}
						if(objCheck.checkCode==401){
							//Relogin
							DbHelper.objBHYTThread.login(Main.USER_GATE_ID, Main.USER_GATE_PASSWORD);
						}
						//
						btnSave.setEnabled(false);
						btnTHANHTOAN.setEnabled(false);
						btnInPhieu.setEnabled(false);
					}
					else{
						//
						btnSave.setEnabled(true);
						btnTHANHTOAN.setEnabled(true);
						btnInPhieu.setEnabled(true);
						//
						listKhamBenh.removeAll();
						for(int i=0; i<objCheck.listKhamBenh.size(); i++){
							listKhamBenh.add(objCheck.listKhamBenh.get(i));
						}
						//
					}
				}
				else{
					MessageDialog.openInformation(shellKhamBenh, "Không check thẻ", "Không check thẻ với khám viện phí");
				}
			}
		});
		btnCheckMTh.setToolTipText("Lưu phiếu, bấm F2");
		btnCheckMTh.setText("Check Thẻ");
		btnCheckMTh.setImage(SWTResourceManager.getImage(FormKhamBenhDlg.class, "/png/check-3x.png"));
		btnCheckMTh.setFont(SWTResourceManager.getFont("Tahoma", 12, SWT.NORMAL));
		btnSave = new Button(compositeGroupButtonEnd, SWT.NONE);
		btnSave.setToolTipText("Lưu phiếu, bấm F2");
		btnSave.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				doSaveFormKhamBenh();
			}
		});
		btnSave.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				// System.out.println("LOST ME set focus to txtMathe");
				txtMathe.setFocus();
				txtMathe.forceFocus();
			}
		});
		btnSave.setImage(SWTResourceManager.getImage(FormKhamBenhDlg.class,
				"/png/check-3x.png"));
		btnSave.setFont(SWTResourceManager.getFont("Tahoma", 12, SWT.NORMAL));
		btnSave.setText("Lưu Phiếu (F2)");
		btnSave.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPress(e);
			}
		});
		
				btnXetNghiem = new Button(compositeGroupButtonEnd, SWT.NONE);
				btnXetNghiem.setToolTipText("Mua phiếu xét nghiệm, bấm F3");
				btnXetNghiem.addSelectionListener(new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent e) {
						doSavePhieuXetNghiem();
					}
				});
				btnXetNghiem.setText("Bán phiếu CLS (F3)");
				btnXetNghiem.setImage(SWTResourceManager.getImage(
						FormKhamBenhDlg.class, "/png/cog-3x.png"));
				btnXetNghiem.setFont(SWTResourceManager.getFont("Tahoma", 12,
						SWT.NORMAL));
		
		Button btnInTt = new Button(compositeGroupButtonEnd, SWT.NONE);
		btnInTt.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				doInThanhToanOnly();
			}
		});
		btnInTt.setToolTipText("In Phiếu, bấm F9");
		btnInTt.setText("In TT");
		btnInTt.setImage(SWTResourceManager.getImage(FormKhamBenhDlg.class, "/png/print-3x.png"));
		btnInTt.setFont(SWTResourceManager.getFont("Tahoma", 12, SWT.NORMAL));
		
		btnTHANHTOAN = new Button(compositeGroupButtonEnd, SWT.NONE);
		GridData gd_btnTHANHTOAN = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnTHANHTOAN.widthHint = 104;
		btnTHANHTOAN.setLayoutData(gd_btnTHANHTOAN);
		btnTHANHTOAN.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				doThanhToanOnly();
				//
			}
		});
		btnTHANHTOAN.setImage(SWTResourceManager.getImage(FormKhamBenhDlg.class, "/png/spreadsheet-3x.png"));
		btnTHANHTOAN.setText("THANH TOÁN ");
		
				btnInPhieu = new Button(compositeGroupButtonEnd, SWT.NONE);
				btnInPhieu.setToolTipText("In Phiếu, bấm F9");
				btnInPhieu.addSelectionListener(new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent e) {
						printPhieu();
					}
				});
				btnInPhieu.setText("In Phiếu (F9)");
				btnInPhieu.setImage(SWTResourceManager.getImage(FormKhamBenhDlg.class, "/png/print-3x.png"));
				btnInPhieu.setFont(SWTResourceManager.getFont("Tahoma", 12, SWT.NORMAL));

	}

	protected void maytinhtien() {
		FormMayTinhTienDlg dlg = new FormMayTinhTienDlg(shellKhamBenh, 0);
		dlg.TONGTIEN = ""+TONGCONG_NB;
		dlg.open();
	}
	protected void doInThanhToanOnly() {
		// Check ICD
		if( KIEU_THANH_TOAN==Utils.THANHTOAN_BHYT || KIEU_THANH_TOAN==Utils.THANHTOAN_BHYT_2){
			if(cbMaBenh.getText().trim().length()==0){
				tabFolder.setSelection(2);
				cbMaBenh.forceFocus();
				MessageDialog.openError(shellKhamBenh, "Có lỗi", "ICD phải có");
				return;
			}
		}
		// Set Thanh Toan
		//
		if (CHI_XET_NGHIEM == 1) {
			doSavePhieuXetNghiem();
		}
		else{
			doSaveFormKhamBenh();
		}
		//Print 
		printPhieu(false);
	}
	protected void doThanhToanOnly() {
		if( KIEU_THANH_TOAN==Utils.THANHTOAN_BHYT || KIEU_THANH_TOAN==Utils.THANHTOAN_BHYT_2){
			if(cbMaBenh.getText().trim().length()==0){
				tabFolder.setSelection(2);
				cbMaBenh.forceFocus();
				MessageDialog.openError(shellKhamBenh, "Có lỗi", "ICD phải có");
				return;
			}
		}
		//Update thanh toan
		//=================================================================================
		if(objCongKham!=null){
			if( isCapCuu==true && (KIEU_THANH_TOAN!=Utils.THANHTOAN_BHYT_2 && KIEU_THANH_TOAN!=Utils.THANHTOAN_BHYT)){
				//
				MessageDialog.openInformation(shellKhamBenh, "", "Không save cong kham");
			}
			else{
				// Nghia: Da thanh toan roi, nhung van update lai CONG KHAM  
				Users bacsy = DbHelper.hashDataUsers.get(cbListBacSiForm.getText());
				if(bacsy!=null && bacsy.MACCHN!=objCongKham.MA_BAC_SI){
					objCongKham.MA_BAC_SI = bacsy.MACCHN;
					logger.info("UPDATE CONGKHAM: "+ objCongKham.toString());
				}
				//
				if(objCongKham.THANHTOAN==0){
					objCongKham.THANHTOAN = 1;
					if(objCongKham.NV_ID==0){
						objCongKham.NV_ID = DbHelper.currentSessionUserId.U_ID;
						objCongKham.NV_NAME = DbHelper.currentSessionUserId.U_NAME;
					}
					objCongKham.insert();
				}
			}
		}
		for (DvChitiet obj : listCLSData) {
			obj.BN_ID = objBenhNhan.BN_ID;
			obj.MA_LK = objPhieuKhamBenh.MA_LK;

			if (obj.STS == -1) {
				obj.STS = 0;
			}
			//
			if(obj.THANHTOAN==0){
				obj.THANHTOAN = 1;
				if(obj.NV_ID==0){
					obj.NV_ID = DbHelper.currentSessionUserId.U_ID;
					obj.NV_NAME = DbHelper.currentSessionUserId.U_NAME;
				}
				//
				obj.insert();
			}
		}
		for (ThuocChitiet obj : listThuocData) {
			if (obj.STS == -1) {
				obj.STS = 0;
			}
			if(obj.THANHTOAN==0){
				obj.THANHTOAN = 1;
				if(obj.NV_ID==0){
					obj.NV_ID = DbHelper.currentSessionUserId.U_ID;
					obj.NV_NAME = DbHelper.currentSessionUserId.U_NAME;
				}
				// Insert thuoc chi tiet.
				obj.insert();
				// Update kho thuoc ....
				//
			}
		}
		//
		if(objPhieuKhamBenh!=null){
			objPhieuKhamBenh.NGAY_TTOAN = Utils.getDatetime(new Date(), "yyyyMMddHHmm");
			objPhieuKhamBenh.update();
		}
		//
		if (CHI_XET_NGHIEM == 1) {
			doSavePhieuXetNghiem();
		}
		else{
			doSaveFormKhamBenh();
		}
		tableViewerCLS.refresh();
		tableViewerSelectedCLS.refresh();
		tableViewerTHUOC.refresh();
		tableViewerPhieuKham.refresh();
		//=================================================================================
	}

	protected void updateCongKham() {
		//
			Dichvu congkham = DbHelper.hashCongKham.get(cbKhoa.getText());
			//
			if (congkham != null) {
				if (objCongKham == null) {
					objCongKham = new DvChitiet();
				}
				objCongKham.DON_GIA = congkham.DON_GIA;
				objCongKham.DON_GIA2 = congkham.DON_GIA2;
				objCongKham.DV_ID = 1;
				objCongKham.SO_LUONG = 1;
				objCongKham.MA_DICH_VU = congkham.MA_DVKT;
				objCongKham.TEN_DICH_VU = congkham.TEN_DVKT;
				objCongKham.MA_BAC_SI = "";
				objCongKham.MA_BENH = "";
				objCongKham.MA_KHOA = DbHelper.getMAKHOA(congkham.MA_DVKT);
				objCongKham.MA_NHOM = Utils.getInt(congkham.MANHOM_9324);
				objCongKham.MA_PTTT = 1;
				objCongKham.MA_VAT_TU = "";
				// format = "yyyy-MM-dd HH:mm:ss";
				objCongKham.NGAY_KQ = Utils.getDatetime(new Date(), "yyyyMMddHHmm");
				objCongKham.NGAY_YL = Utils.getDatetime(new Date(), "yyyyMMddHHmm");
				objCongKham.THANH_TIEN = objCongKham.DON_GIA2 * objCongKham.SO_LUONG;
				objCongKham.TT_BHTT = 0;
				objCongKham.TT_BNTT = 0;
				objCongKham.MUC_HUONG = 100;
				//System.out.println("NGHIA LUONG " + objCongKham.toString());
				//
				cbListBacSiForm.removeAll();
				cbListBacSiCLS.removeAll();
				cbListBacsiThuoc.removeAll();
				for (Users obj : DbHelper.listUsers) {
					//if (obj.MA_KHOA.equals(congkham.MA_DVKT) == true) {
						cbListBacSiForm.add(obj.U_ID+"-"+obj.TEN_NHANVIEN);
						cbListBacSiCLS.add(obj.U_ID+"-"+obj.TEN_NHANVIEN);
						cbListBacsiThuoc.add(obj.U_ID+"-"+obj.TEN_NHANVIEN);
					//}
				}
				cbListBacSiForm.select(0);
				cbListBacSiCLS.select(0);
				cbListBacsiThuoc.select(0);
			}
	}

	protected void doLoadThuocLastest(String MA_BENH) {
		//
		//
		if (listThuocData.size() > 0) {
			// Do not load if already have
			return;
		}
		try {
			Connection con = DbHelper.getSql2o();
			String sql = "select * from kham_benh where MA_BENH='" + MA_BENH
					+ "' and STS>=0 order by MA_LK DESC LIMIT 1";
			// System.out.println(sql);
			KhamBenh objKhamBenh = con.createQuery(sql).executeAndFetchFirst(
					KhamBenh.class);
			if (objKhamBenh != null) {
				sql = "select * from thuoc_chitiet where MA_LK="+ objKhamBenh.MA_LK.intValue();
				List<ThuocChitiet> listDV = con.createQuery(sql).executeAndFetch(ThuocChitiet.class);
				// Check so luong thuoc ...
				// Nghialuong
				if (listDV != null) {
					int dvId = 1;
					for (ThuocChitiet obj : listDV) {
						// check in CTNhap
						CtNhapthuoc objCtNhapthuoc = null;
						// find thuoc ID in listNhapThuoc
						for(int i=0; i<listDataCtNhapthuoc.size(); i++){
							if( listDataCtNhapthuoc.get(i).THUOC_ID==obj.THUOC_ID ){
								// FOUND it
								objCtNhapthuoc = listDataCtNhapthuoc.get(i);
								break;
							}
						}
						// If found thuoc, add vo
						if (objCtNhapthuoc == null
								|| (objCtNhapthuoc != null && objCtNhapthuoc.SL_TONKHO < obj.SO_LUONG)) {
							//
							String error = "Không tìm thấy thuốc trong kho !";
							if (objCtNhapthuoc != null) {
								error = "Số lượng thuốc trong kho không đủ. "
										+ objCtNhapthuoc.SL_TONKHO + " < "
										+ obj.SO_LUONG;
							}
							logger.info(error);
							MessageDialog.openError(shellKhamBenh, "Có lỗi", error);
							//
							continue;
						}
						//
						obj.TCT_ID = null;
						obj.STS = -1;
						obj.STT = dvId++;
						obj.MA_BENH = MA_BENH;
						obj.TYP = objCtNhapthuoc.TYP;
						obj.THANHTOAN = 0;
						obj.SO_DANG_KY = objCtNhapthuoc.SO_DANG_KY;
						obj.TT_THAU = objCtNhapthuoc.TT_THAU;
						//
						//
						listThuocData.add(obj);
					}
					tableViewerTHUOC.refresh();
					return;
				}
				//
			}
			//
			// txtSearchCtNhapthuoc.selectAll();
			// txtSearchCtNhapthuoc.forceFocus();
			//
		} catch (Exception ex) {
			logger.error(ex);
		}
		//
	}

	protected void setFocusTabFolder() {
		if (tabFolder.getSelectionIndex() == 0) {
			// System.out.println("CLS SELECT");
			if (txtHoten != null) {
				txtHoten.forceFocus();
			}
			//
			// System.out.println("CLS SELECT");
		} else if (tabFolder.getSelectionIndex() == 1) {
			// savePhieuXetNghiem();
			if (cbListBacSiCLS.getSelectionIndex() == 0) {
				// Chua chon BS
				cbListBacSiCLS.forceFocus();
			} else {
				txtBenhBanDau.selectAll();
				txtBenhBanDau.forceFocus();
			}
			cbBenhBanDau.setVisible(false);
		} else if (tabFolder.getSelectionIndex() == 2) {
			// System.out.println("THUOC SELECT");
			if (cbMaBenh != null) {
				cbMaBenh.forceFocus();
			}
		}

	}

	protected void doLoadCanLamSanLatest(String CHANDOAN_BD) {
		//
		try {
			Connection con = DbHelper.getSql2o();
			String sql = "select * from kham_benh where CHANDOAN_BD='"
					+ CHANDOAN_BD + "' and STS>=0 order by MA_LK DESC LIMIT 1";
			// System.out.println(sql);
			KhamBenh objKhamBenh = con.createQuery(sql).executeAndFetchFirst(
					KhamBenh.class);
			if (objKhamBenh != null) {
				sql = "select * from dv_chitiet where MA_LK="
						+ objKhamBenh.MA_LK.intValue();
				List<DvChitiet> listDV = con.createQuery(sql).executeAndFetch(DvChitiet.class);
				//
				if (listDV != null) {
					int dvId = 1;
					for (DvChitiet obj : listDV) {
						obj.DVCT_ID = null;
						obj.STS = -1;
						obj.DV_ID = dvId++;
						listCLSData.add(obj);
					}
					tableViewerSelectedCLS.refresh();
					tableSelectedCLS.forceFocus();
					return;
				}
				//
			}
			//
			txtSearchCLS.selectAll();
			txtSearchCLS.forceFocus();
			//
		} catch (Exception ex) {
			logger.error(ex);
		}

	}

	protected void loadListLieuDung() {
		cbLieuDung.removeAll();
		//
		try {
			Connection con = DbHelper.getSql2o();
//			String sql = "select DISTINCT LIEU_DUNG from thuoc_chitiet where LENGTH(LIEU_DUNG)>0";
//			//System.out.println(sql);
//			org.sql2o.data.Table tb = con.createQuery(sql).executeAndFetchTable();
			String sql = "select LIEUDUNG_ID, LIEUDUNG_NAME  from mst_lieudung order by RANK DESC";
			org.sql2o.data.Table tb = con.createQuery(sql).executeAndFetchTable();
			for (Row r : tb.rows()) {
				String LIEUDUNG_NAME = r.getString("LIEUDUNG_NAME");
				Integer LIEUDUNG_ID = r.getInteger("LIEUDUNG_ID");
				cbLieuDung.add(LIEUDUNG_ID.intValue()+ "#"+LIEUDUNG_NAME);
				// System.out.println("Add "+LIEU_DUNG);
			}
			cbLieuDung.select(0);
			//
		} catch (Exception ex) {
			logger.error(ex);
		}
		//

	}

	protected void loadListBenhBanDau() {
		cbBenhBanDau.removeAll();
		//
		try {
			Connection con = DbHelper.getSql2o();
			String sql = "select DISTINCT CHANDOAN_BD from kham_benh where LENGTH(CHANDOAN_BD)>0";
			// System.out.println(sql);
			org.sql2o.data.Table tb = con.createQuery(sql)
					.executeAndFetchTable();
			for (Row r : tb.rows()) {
				String CHANDOAN_BD = r.getString("CHANDOAN_BD");
				cbBenhBanDau.add(CHANDOAN_BD);
			}
			cbBenhBanDau.select(0);
		} catch (Exception ex) {
			logger.error(ex);
		}
		//
	}

	protected void extractData(String hexData) {
		System.out.println("Extract " + hexData);
		String data[] = hexData.split("\\|");
		if (data.length == 15) {
			txtMathe.strMathe = data[0];
			txtHoten.setText(TheBHXH.convertHex2String(data[1]));
			txtNgaySinh.setText(data[2]);
			//
			int iGT = Utils.getInt(data[3]);
			if (iGT == 1) {
				btnGIOITINH.setText("NAM(1)");
				btnGIOITINH.setSelection(false);
			} else {
				btnGIOITINH.setText("NỮ(2)");
				btnGIOITINH.setSelection(true);
			}
			txtDiachi.setText(TheBHXH.convertHex2String(data[4]));
			cbNoiDKKB.setText(data[5].replaceAll("-", "").replaceAll(" ", ""));
			txtTuNgay.setText(data[6]);
			txtDenNgay.setText(data[7]);

			txtNgayCap.setText(data[8]);
			txtMaQuanLy.setText(data[9]);
			txtTenChaMe.setText(TheBHXH.convertHex2String(data[10]));
			txtDichVuCao.setText(data[11]);
			// txtMaHuongChiPhiDichVuCao = data[11];
			txtThoiDiem5Nam.setText(data[12]);
			// txtThoiDiemHuongChiPhiDichVuCao = data[12];
			txtChuoiKiemTra.setText(data[13]);
			// Process tylehuong
			//
			checkCanNangTreem();
			//
			txtMathe.processMaThe(txtMathe.strMathe);
			txtMathe.updateMaThe(txtMathe.strMathe);
			// tabFolder.setSelection(0);
			//
		}
	}

	// Print all item
	protected void printPhieu() {
		// Check ICD 
		if( KIEU_THANH_TOAN==Utils.THANHTOAN_BHYT || KIEU_THANH_TOAN==Utils.THANHTOAN_BHYT_2 ){
			if(cbMaBenh.getText().trim().length()==0){
				tabFolder.setSelection(2);
				cbMaBenh.forceFocus();
				MessageDialog.openError(shellKhamBenh, "Có lỗi", "ICD phải có");
				return;
			}
		}
		//
		printPhieu(true);
	}
	/**
	 * 
	 * @param isPrintAll = true: Print all
	 * false: Only print chưa thanh toán
	 */
	protected void printPhieu(boolean isPrintAll) {
		try {
			if (CHI_XET_NGHIEM == 1) {
				printPhieuXetNghiem(isPrintAll);
			} else {
				if( KIEU_THANH_TOAN == Utils.THANHTOAN_BHYT || KIEU_THANH_TOAN == Utils.THANHTOAN_BHYT_2){
					printPhieuBHYT(isPrintAll);
				}
				else{
					printPhieuVienPhi(isPrintAll);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
	}

	protected void selectPhieuKham() {
		if (tablePhieuKham.getSelectionCount() == 0) {
			return;
		}
		TableItem item = tablePhieuKham.getSelection()[0];
		Row row = (Row) item.getData();
		Integer MA_LK = (Integer) row.getInteger("MA_LK");
		Integer BN_ID = (Integer) row.getInteger("BN_ID");
		// System.out.println("LOAD BN ID=" + BN_ID + " MALK=" + MA_LK);
		//
		BenhNhan objBenhNhan = BenhNhan.load(BN_ID);
		KhamBenh objKhamBenh = KhamBenh.load(MA_LK);
		this.objBenhNhan = objBenhNhan;
		this.objPhieuKhamBenh = objKhamBenh;
		//
		loadCongkhamFromDB();
		//
		updateDlg();
		//
		updateTinhTien();
		//
		txtSearchCLS.setText("");
		txtSearchCtNhapthuoc.setText("");
		txtBenhBanDau.setText("");
		txtSearchCtNhapthuoc.setText("");
		//
		//tabFolder.setSelection(1);
		//cbListBacSiCLS.forceFocus();
		//
		listKhamBenh.removeAll();
		if(objBenhNhan.GATE_INFO!=null || (objBenhNhan.GATE_INFO!=null && objBenhNhan.GATE_INFO.length()>0)){
			String listKham[] = objBenhNhan.GATE_INFO.split("@");
			for(int i=0; i<listKham.length; i++){
				listKhamBenh.add(listKham[i]);
			}
		}
		//
	}

	protected void resetFormKhamBenh() {
		// Reset form kham
		objBenhNhan = new BenhNhan();
		objPhieuKhamBenh = null;
		objDVChiTiet = null;
		objCongKham = null;
		objCtNhapthuoc = null;
		//
		listDataCtNhapthuoc.clear();
		hashDichVuChiTiet.clear();
		hashDataCtNhapthuoc.clear();
		listCLSData.clear();
		listDichVu.clear();
		listThuocData.clear();
		//
		cbMabenhKhac.setText("");
		txtTenBenhPhu.setText("");
		//
		TONGCONG = 0;
		TONGCONG_BH = 0;
		TONGCONG_BHYT = 0;
		TONGCONG_NB = 0;
		//
		KIEU_THANH_TOAN = Utils.THANHTOAN_BHYT_2;
		CHI_XET_NGHIEM = 0;
		//
		updateTinhTien();
		//
		startDialog();
		cbBenhBanDau.setVisible(false);
		txtBenhBanDau.setVisible(true);
		tabFolder.setSelection(0);
		cbKhoThuoc.setText("Kho bảo hiểm");
		setFocusTabFolder();
		//
	}

	private void startDialog() {
		shellKhamBenh.setText(Main.TITLE + " "
				+ DbHelper.currentSessionUserId.TEN_NHANVIEN + " ("
				+ DbHelper.currentSessionUserId.MACCHN + ")");
		//
		loadCongkhamFromDB();
		//
		if (objPhieuKhamBenh != null && objPhieuKhamBenh.TEN_BENH != null) {
			// ====================================================
			txtTenBenh.setText(objPhieuKhamBenh.TEN_BENH);
			cbMaBenh.setText(objPhieuKhamBenh.MA_BENH);
			txtTenBenhPhu.setText(objPhieuKhamBenh.MA_BENHKHAC);
			// ====================================================
		}
		setDefaultField();
		//
		updateDlg();
		//
		loadCanLamSan();
		//
		loadThuoc();
		//
		// String strDate = "12/2017";
		//
		if (DbHelper.currentSessionUserId.LOAI.toUpperCase().equals("ACCT") == true) {
			btnTHANHTOAN.setEnabled(true);
		} else {
			//btnTHANHTOAN.setEnabled(false);
		}
		// list bs
		cbKhoThuoc.removeAll();
		cbKhoThuoc.add("Tất cả");
		for(Khohang obj: DbHelper.listDataKhohang){
			if(obj.TYPE==0){
				// Add only kho thuoc.
				cbKhoThuoc.add(obj.KHO_NAME);
			}
		}
		// list bs
		cbLieuDung.removeAll();
		for(MstLieudung obj: DbHelper.listDataMstLieuDung){
			cbLieuDung.add(obj.LIEUDUNG_ID + "#" + obj.LIEUDUNG_NAME);
		}
		//
		// doSearchBenhNhan(1);
		//
		tableViewerCtNhapthuoc
				.setLabelProvider(new TableLabelProviderCtNhapthuoc());
		tableViewerCtNhapthuoc
				.setContentProvider(new ContentProviderCtNhapthuoc());
		tableViewerCtNhapthuoc.setInput(listDataCtNhapthuoc);
		reloadTableChitietNhapthuoc();
		tableViewerCLS.setLabelProvider(new TableLabelProvider());
		tableViewerCLS.setContentProvider(new ContentProvider());
		tableViewerCLS.setInput(listDichVu);
		reloadTableCtListCLS();
		tableViewerSelectedCLS.refresh();
		tableViewerTHUOC.refresh();
		//
		tabFolder.setSelection(0);
		//
		buttonRefresh(false);
		//
		setBeginFocus();
		//
		
	}

	private void setBeginFocus() {
		if (DbHelper.currentSessionUserId.LOAI.toLowerCase() == "nv") {
			txtSearchPhieuKham.selectAll();
			txtSearchPhieuKham.forceFocus();
		} else {
			txtSearchPhieuKham.selectAll();
			txtSearchPhieuKham.forceFocus();
		}
	}

	public static void doSearchBenhNhan() {
	
		try {
			Connection con = DbHelper.getSql2o();
			//String sql = "SELECT * FROM benh_nhan as bn, kham_benh as kb where bn.BN_ID=kb.BN_ID ";
			String sql = "SELECT * FROM kham_benh where STS>-1 ";
			if( btnCheckButtonTatCaPhieuDta==true){
				sql += " and MA_KHOA='K31' ";
			}
			
			if( btnCheckButtonNgayXemDta==false){
				sql += " and DATE(KB_DATE) = CURDATE()";
			}
			else{
				sql += " and DATEDIFF(NOW(), KB_DATE) <= 30 ";
			}
			if( btnCheckButtonTatCaTHANHTOANDta==true){
				sql += " ";//and LENGTH(NGAY_TTOAN)>0";
			}
			else{
				sql += " and LENGTH(NGAY_TTOAN)=0";
			}
			if( btnCheckButtonTatCaNVDta==false){
				sql += " ";
			}
			else{
				sql += " and NV_ID="+DbHelper.currentSessionUserId.U_ID.intValue();
			}

			sql += " order by KB_DATE desc";
			logger.info("SQL PHIEUKHAM=" + sql);
			listData = con.createQuery(sql).executeAndFetchTable().rows();
			//
			tongBN = 0;
			tongChi = 0;
			for(Row obj: listData){
				tongBN+=obj.getInteger("T_BNTT").intValue();
				tongChi+=obj.getInteger("T_TONGCHI").intValue();
			}
			//
		} catch (Exception e) {
			logger.error(e);
		}
		Display.getDefault().asyncExec(new Runnable() {
			@Override
			public void run() {
				int selectIndex = tablePhieuKham.getSelectionIndex();
				tableViewerPhieuKham.setInput(listData);
				tableViewerPhieuKham.refresh();
				tablePhieuKham.setSelection(selectIndex);
				lbTONG_BN.setText("Thu BN:"+java.text.NumberFormat.getInstance(java.util.Locale.ITALY)
						.format(tongBN) +"/Tổng Thu:"+java.text.NumberFormat.getInstance(java.util.Locale.ITALY)
						.format(tongChi));
			}
		});
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
					+ objPhieuKhamBenh.MA_LK + " and (MA_NHOM=13 or MA_NHOM=14)";
					//+ objPhieuKhamBenh.MA_LK + " and MA_NHOM=13";
			//System.out.println(sql);
			List<DvChitiet> list = con.createQuery(sql).executeAndFetch(DvChitiet.class);
			//System.out.println("List<DvChitiet> list size=" + list.size());
			// con.close();
			//
			if (list.size() == 1) {
				objCongKham = list.get(0);
				setCHI_XET_NGHIEM(0);
				logger.info("CONG KHAM LOAD " + objCongKham.TEN_DICH_VU);
			} else {
				logger.info("----------------- CONG KHAM LOAD: " + objCongKham+ " size=" + list.size());
				objCongKham = null;
				setCHI_XET_NGHIEM(1);
				KIEU_THANH_TOAN = Utils.THANHTOAN_VIENPHI;
			}
			//
		} catch (Exception ex) {
			// ex.printStackTrace();
			logger.error(ex);
			MessageDialog.openError(shellKhamBenh, "Có lỗi", ex.getMessage());
		}

	}

	private void loadThuoc() {
		if (objBenhNhan.BN_ID == null || objPhieuKhamBenh == null
				|| objPhieuKhamBenh.MA_LK == null) {
			System.out.println("NULL THUOC");
			return;
		}
		//
		try {
			System.out.println("LOAD THUOC");
			Connection con = DbHelper.getSql2o();
			String sql = "select * from thuoc_chitiet where MA_LK=" + objPhieuKhamBenh.MA_LK + "";
			System.out.println(sql);
			List<ThuocChitiet> list = con.createQuery(sql).executeAndFetch(
					ThuocChitiet.class);
			System.out.println("size=" + list.size());
			// con.close();
			listThuocData.clear();
			// hashThuocChiTiet.clear();
			for (int i = 0; i < list.size(); i++) {
				ThuocChitiet obj = (ThuocChitiet) list.get(i);
				listThuocData.add(obj);
				// hashThuocChiTiet.put(obj.TEN_THUOC, obj);
			}
			tableViewerTHUOC.refresh();
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error(ex);
			MessageDialog.openError(shellKhamBenh, "Có lỗi", ex.getMessage());
		}

	}

	private void selectTaiKham() {
		updateTinhTien();
	}

	private void setCHI_XET_NGHIEM(int value) {
		CHI_XET_NGHIEM = value;
	}

	private void loadCongKhamData() {
		String name = cbKhoa.getText();
		String names[] = name.split("-");
		if (objCongKham != null) {
			names[0] = objCongKham.MA_DICH_VU;
		}
		// System.out.println("CONG KHAM=" + name + " "+names[0]);
		//
		cbKhoa.removeAll();
		//
		if (CHI_XET_NGHIEM == 1) {
			logger.info("CHI_XET_NGHIEM=" + CHI_XET_NGHIEM);
			return;
		}
		//
		int idxFound = -1;
		int idx0 = -1;
		for (String key : DbHelper.hashCongKham.keySet()) {
			Dichvu obj = DbHelper.hashCongKham.get(key);
			if(cbCapCuu.getSelectionIndex()==0){
				if(Utils.getInt(obj.MANHOM_9324)==14){
					continue;
				}
			}
			else{
				if(Utils.getInt(obj.MANHOM_9324)==13){
					continue;
				}
				
			}
			// System.out.println(obj.DON_GIA2);
			if (KIEU_THANH_TOAN == Utils.THANHTOAN_BHYT) {
				if (obj.DON_GIA2 == 29000) {
					idx0++;
					// System.out.println(obj.MA_DVKT
					// +"-"+obj.TEN_DVKT+"("+obj.DON_GIA2+")");
					cbKhoa.add(obj.DV_ID +"-"+obj.MA_DVKT + "-" + obj.TEN_DVKT + "(" + obj.DON_GIA2 + ")");

					if (obj.MA_DVKT.indexOf(names[0]) > -1) {
						idxFound = idx0;
						System.out.println("FOUND IDX=" + idxFound + " "+ obj.MA_DVKT + " " + names[0]);
					}
				}
			} else {
				// System.out.println(obj.MA_DVKT
				// +"-"+obj.TEN_DVKT+"("+obj.DON_GIA2+")");
				if (obj.DON_GIA2 > 29000) {
					idx0++;
					cbKhoa.add(obj.DV_ID +"-"+ obj.MA_DVKT + "-" + obj.TEN_DVKT + "("+ obj.DON_GIA2 + ")");
					if (obj.MA_DVKT.indexOf(names[0]) > -1) {
						idxFound = idx0;
						System.out.println("FOUND IDX=" + idxFound + " "
								+ obj.MA_DVKT + " " + names[0]);
					}
				}
			}
		}
		//Dichvu objCapCuu = DbHelper.hashCongKham.get("238-K02.1908-Giường Hồi sức cấp cứu Hạng IV - Khoa Hồi sức cấp cứu(40000)");
		//cbKhoa.add(objCapCuu.MA_DVKT + "-" + objCapCuu.TEN_DVKT + "(" + objCapCuu.DON_GIA2 + ")");
		// Reload
		// System.out.println("-------------IDX=" + idxFound);
		if (objCongKham != null) {
			//
			if (objCongKham.DON_GIA2 == 0 || objCongKham.DON_GIA2 > 29000) {
				KIEU_THANH_TOAN = Utils.THANHTOAN_BHYT_2;
				//System.out.println("-------------KIEU_THANH_TOAN = Utils.THANHTOAN_BHYT_2;" + KIEU_THANH_TOAN);
			} else {
				KIEU_THANH_TOAN = Utils.THANHTOAN_BHYT;
				//System.out.println("-------------KIEU_THANH_TOAN = Utils.THANHTOAN_BHYT" + KIEU_THANH_TOAN);
			}
			//
			cbKhoa.select(idxFound);
			System.out.println("COUNT=" + cbKhoa.getItemCount() + " index="
					+ idxFound);
			//
			cbListBacSiForm.removeAll();
			cbListBacSiCLS.removeAll();
			cbListBacsiThuoc.removeAll();
			for (Users obj : DbHelper.listUsers) {
				//if (obj.MACCHN.equals(objCongKham.MA_BAC_SI) == true) {
					cbListBacSiForm.add(obj.U_ID+"-"+obj.TEN_NHANVIEN);
					cbListBacSiCLS.add(obj.U_ID+"-"+obj.TEN_NHANVIEN);
					cbListBacsiThuoc.add(obj.U_ID+"-"+obj.TEN_NHANVIEN);
				//}
			}
			cbListBacSiForm.select(0);
			cbListBacSiCLS.select(0);
			cbListBacsiThuoc.select(0);

			//
		} else {
			cbKhoa.select(0);
		}
		//
	}

	//
	private void updateKIEUTHANHTOAN() {
		if (KIEU_THANH_TOAN == Utils.THANHTOAN_TAI_KHAM) {
			// TAI KHAM
			btnRadio1.setSelection(false);
			btnRadio2.setSelection(false);
			btnRadio3.setSelection(false);
			btnRadio4.setSelection(true);
			btnRadio5.setSelection(false);

			btnRadio1.setEnabled(false);
			btnRadio2.setEnabled(false);
			btnRadio3.setEnabled(false);
			btnRadio4.setEnabled(true);
			btnRadio5.setEnabled(false);
		} else if (KIEU_THANH_TOAN == Utils.THANHTOAN_VIENPHI) {
			btnRadio1.setSelection(false);
			btnRadio2.setSelection(false);
			btnRadio3.setSelection(true);
			btnRadio4.setSelection(false);
			btnRadio5.setSelection(false);
			selectVienPhi();
		} else if (KIEU_THANH_TOAN == Utils.THANHTOAN_VIENPHI_FREE) {
			btnRadio1.setSelection(false);
			btnRadio2.setSelection(false);
			btnRadio3.setSelection(false);
			btnRadio4.setSelection(false);
			btnRadio5.setSelection(true);
			selectVienPhi();
		} else if (KIEU_THANH_TOAN == Utils.THANHTOAN_BHYT) {
			btnRadio1.setSelection(true);
			btnRadio2.setSelection(false);
			btnRadio3.setSelection(false);
			btnRadio4.setSelection(false);
			btnRadio5.setSelection(false);
			selectBHYT();
		} else if (KIEU_THANH_TOAN == Utils.THANHTOAN_BHYT_2) {
			// BHYT
			btnRadio1.setSelection(false);
			btnRadio2.setSelection(true);
			btnRadio3.setSelection(false);
			btnRadio4.setSelection(false);
			btnRadio5.setSelection(false);
			selectBHYT();
		} else if (KIEU_THANH_TOAN == Utils.THANHTOAN_MUA_CLS) {
			// BHYT
			btnRadio1.setSelection(false);
			btnRadio2.setSelection(false);
			btnRadio3.setSelection(true);
			btnRadio4.setSelection(false);
			btnRadio5.setSelection(false);
			selectVienPhi();
		}
	}

	private void setDefaultField() {
		//
		txtTenBenh.setText("");
		txtTenBenhPhu.setText("");
		txtBenhBanDau.setText("");
		cbTenBenh.setText("");
		cbTenBenhPhu.setText("");
		cbCapCuu.select(0);
		loadCongKhamData();
		btnRadio4.setEnabled(false);
		//
		updateKIEUTHANHTOAN();
		//
		//
	}

	protected void selectCLS() {
		txtMathe.setEnabled(false);
		txtHoten.setEnabled(true);
		txtTuNgay.setEnabled(false);
		txtDenNgay.setEnabled(false);
		cbNoiDKKB.setEnabled(false);
		txtChuoiKiemTra.setEnabled(false);
		txtMaQuanLy.setEnabled(false);
	}

	private void selectVienPhi() {
		txtMathe.setEnabled(false);
		txtHoten.setEnabled(true);
		txtTuNgay.setEnabled(false);
		txtDenNgay.setEnabled(false);
		cbNoiDKKB.setEnabled(false);

		txtChuoiKiemTra.setEnabled(false);
		txtMaQuanLy.setEnabled(false);
		//
	}

	private void selectBHYT() {
		txtMathe.setEnabled(true);
		txtHoten.setEnabled(true);
		txtTuNgay.setEnabled(true);
		txtDenNgay.setEnabled(true);

		cbNoiDKKB.setEnabled(true);

		txtChuoiKiemTra.setEnabled(false);
		txtMaQuanLy.setEnabled(false);
		//
		loadCongKhamData();
		//
	}

	public void setData(BenhNhan objBenhNhan, KhamBenh objKhamBenh) {
		this.objBenhNhan = objBenhNhan;
		this.objPhieuKhamBenh = objKhamBenh;
	}

	protected void keyPressThuoc(KeyEvent e) {
		if (e.keyCode == SWT.F2) {
			//
			if (isSaveThuoc == false)
				saveThuoc();
			else {
				keyPress(e);
				return;
			}
			//
		} else if (e.keyCode == SWT.F7) {
			//
			// if (cbMaBenh.getText().length() == 0) {
			// MessageDialog.openError(shellKhamBenh, "Có lỗi",
			// "Chỉ định mã bệnh trước");
			// cbMaBenh.forceFocus();
			// return;
			// }
			// //
			// isSaveThuoc = false;
			// newThuoc();
		} else if (e.keyCode == SWT.DEL) {
			//
			// isSaveThuoc = false;
			// doViewThuoc();
			deleteThuocChitiet();
			//
		}
		controlKey(e);
		//

	}

	private void deleteThuocChitiet() {
		if (tableTHUOC.getSelectionCount() == 0) {
			return;
		}
		TableItem item = tableTHUOC.getSelection()[0];
		ThuocChitiet objThuocChitiet = (ThuocChitiet) item.getData();
		if (objThuocChitiet != null) {
			if (objThuocChitiet.STS <= 0 && objThuocChitiet.THANHTOAN==0) {
				//
				if(objThuocChitiet.STS==0){
					CtNhapthuoc objCtNhapthuoc = hashDataCtNhapthuoc.get(objThuocChitiet.CT_ID);
					if(objCtNhapthuoc!=null){
						// Rollback soluong TON KHO
						objCtNhapthuoc.SL_TONKHO = objCtNhapthuoc.SL_TONKHO +  objThuocChitiet.SO_LUONG;
						objCtNhapthuoc.update();
					}
				}
				listThuocData.remove(objThuocChitiet);
				//
				objThuocChitiet.deleteRow();
				//
				updateTinhTien();
				//
				tableViewerTHUOC.refresh();
				reloadTableChitietNhapthuoc();
				//
			} else {
				MessageDialog.openError(shellKhamBenh, "Có lỗi", "Thuốc đã phát/Đã thanh toán, không xóa được");
			}
		}

	}

	private void saveThuoc() {
		if (DbHelper.currentSessionUserId.LOAI.toUpperCase().equals("BS") == false) {
			String message = "Không có quyền chỉ định Thuốc";
			MessageDialog.openError(shellKhamBenh, "Có lỗi", message);
			logger.info(message);
			return;
		}

		try {
			Connection con = DbHelper.getSql2o();
			for (int i = 0; i < listThuocData.size(); i++) {
				ThuocChitiet obj = (ThuocChitiet) listThuocData.get(i);
				obj.STS = 0;
				obj.insert();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error(ex);
			MessageDialog.openError(shellKhamBenh, "Có lỗi", ex.getMessage());
		}
		isSaveThuoc = true;
		tableViewerTHUOC.refresh();

	}

	private void doViewThuoc() {
		// if (DbHelper.currentSessionUserId.LOAI.toUpperCase().equals("BS") ==
		// false) {
		// String message = "Không có quyền chỉ định Thuốc";
		// MessageDialog.openError(shellKhamBenh, "Có lỗi", message);
		// logger.info(message);
		// return;
		// }
		// if (tableTHUOC.getSelectionCount() == 0) {
		// return;
		// }
		// TableItem item = tableTHUOC.getSelection()[0];
		// ThuocChitiet objThuocChiTiet = (ThuocChitiet) item.getData();
		// FormCtNhapthuocListDlg dlg = new
		// FormCtNhapthuocListDlg(shellKhamBenh,
		// 0);
		// dlg.setDataCtNhapthuoc(objThuocChiTiet.TEN_THUOC);
		// dlg.open();
		// if (dlg.objCtNhapthuoc != null) {
		// if (objThuocChiTiet.TEN_THUOC != null
		// && objThuocChiTiet.TEN_THUOC.length() > 0) {
		// Thuoc objThuoc = Thuoc.load(dlg.objCtNhapthuoc.THUOC_ID);
		// if (objThuoc != null) {
		// objThuocChiTiet.DONGGOI = objThuoc.DONG_GOI;
		// objThuocChiTiet.HAMLUONG = objThuoc.HAMLUONG_AX;
		// objThuocChiTiet.HOATCHAT = objThuoc.HOATCHAT_AX;
		// }
		// objThuocChiTiet.TEN_THUOC = dlg.objCtNhapthuoc.TENTHUOC;
		// objThuocChiTiet.KHO_NAME = dlg.objCtNhapthuoc.TENKHO;
		// objThuocChiTiet.DON_GIA = dlg.objCtNhapthuoc.DONGIA;
		// objThuocChiTiet.DON_VI_TINH = dlg.objCtNhapthuoc.DONVI;
		// objThuocChiTiet.THUOC_ID = dlg.objCtNhapthuoc.THUOC_ID;
		// objThuocChiTiet.CT_ID = dlg.objCtNhapthuoc.THUOC_ID;
		// objThuocChiTiet.NT_ID = dlg.objCtNhapthuoc.NT_ID;
		// //
		// objThuocChiTiet.STT = listThuocData.size() + 1;
		// }
		// //
		// updateGiaTien();
		// }
		// //
		// tableViewerTHUOC.refresh();
	}

	private void newThuoc_DELETE() {
		// // ======================================================
		// //
		// // ======================================================
		// if (objPhieuKhamBenh == null || objPhieuKhamBenh.MA_LK == null) {
		// return;
		// }
		// //
		// if (DbHelper.currentSessionUserId.LOAI.toUpperCase().equals("BS") ==
		// false) {
		// String message = "Không có quyền chỉ định Thuốc";
		// MessageDialog.openError(shellKhamBenh, "Có lỗi", message);
		// logger.info(message);
		// return;
		// }
		//
		// // ======================================================
		// //
		// // ======================================================
		// ThuocChitiet objThuocChiTiet = new ThuocChitiet();
		// objThuocChiTiet.MA_BENH = cbMaBenh.getText();
		// objThuocChiTiet.TYLE_TT = (float) 100.0;
		// objThuocChiTiet.MA_LK = objPhieuKhamBenh.MA_LK;
		// objThuocChiTiet.SO_LUONG = 1;
		// //
		// FormCtNhapthuocListDlg dlg = new
		// FormCtNhapthuocListDlg(shellKhamBenh,
		// 0);
		// dlg.setDataCtNhapthuoc("");
		// dlg.iSoLuongChiDinh = objThuocChiTiet.SO_LUONG;
		// dlg.open();
		// if (dlg.objCtNhapthuoc != null) {
		// if (objThuocChiTiet.TEN_THUOC != null
		// && objThuocChiTiet.TEN_THUOC.length() > 0) {
		// Thuoc objThuoc = Thuoc.load(dlg.objCtNhapthuoc.THUOC_ID);
		// if (objThuoc != null) {
		// objThuocChiTiet.MA_THUOC = objThuoc.MA_AX;
		// objThuocChiTiet.TEN_THUOC = objThuoc.TENTHUOC_AX;
		// objThuocChiTiet.HAM_LUONG= objThuoc.HAMLUONG_AX;
		// objThuocChiTiet.DUONG_DUNG = objThuoc.DUONGDUNG_AX;
		// }
		// objThuocChiTiet.TEN_THUOC = dlg.objCtNhapthuoc.TENTHUOC;
		// objThuocChiTiet.KHO_NAME = dlg.objCtNhapthuoc.TENKHO;
		// objThuocChiTiet.DON_GIA = dlg.objCtNhapthuoc.DONGIA;
		// objThuocChiTiet.DON_VI_TINH = dlg.objCtNhapthuoc.DONVI;
		// objThuocChiTiet.THUOC_ID = dlg.objCtNhapthuoc.THUOC_ID;
		// objThuocChiTiet.CT_ID = dlg.objCtNhapthuoc.CT_ID;
		// objThuocChiTiet.NT_ID = dlg.objCtNhapthuoc.NT_ID;
		// objThuocChiTiet.SO_LUONG = dlg.iSoLuongChiDinh;
		// //
		// objThuocChiTiet.STT = listThuocData.size() + 1;
		// listThuocData.add(objThuocChiTiet);
		// }
		// //
		// updateGiaTien();
		// tableViewerTHUOC.refresh();
		// }
		// // ======================================================
		// //
		// // ======================================================
		// // tableViewerTHUOC.setInput(listThuocData);
		// // ======================================================
		// //
		// // ======================================================

	}

	private void buttonRefresh(boolean isall) {
		if (objBenhNhan == null || objPhieuKhamBenh == null) {
			btnSave.setEnabled(true);
			btnXetNghiem.setEnabled(true);
			btnInPhieu.setEnabled(true);
		} else {
			btnSave.setEnabled(true);
			btnXetNghiem.setEnabled(false);
			btnInPhieu.setEnabled(true);
		}
	}

	protected void keyPressCLS(KeyEvent e) {
		// System.out.println(" CLS " + e.keyCode);
		if (e.keyCode == SWT.F2) {
			//
			if (isSaveCLS == false)
				saveCLS();
			else {
				keyPress(e);
				return;
			}
			//
		} else if (e.keyCode == 32) {
			// Change kieu Thanh Toan
			if(KIEU_THANH_TOAN==Utils.THANHTOAN_MUA_CLS){
				updateBacSiCLS();
			}
			else{
				updateKieuThanhToanCLS();
			}
			//
		} else if (e.keyCode == SWT.DEL) {
			//
			deleteCLS();
			//
		} else if (e.keyCode == SWT.F7) {
			//
			isSaveCLS = false;
			newDichVuCLS();
		} else if (e.keyCode == SWT.F9) {
			//
			printPhieu();
		} else if (e.keyCode == 13) {
			//
			isSaveCLS = false;
			doViewCLS();
			//
		}
		//controlKey(e);
		//
		keyPress(e);
		//
	}

	private void updateBacSiCLS() {
		if (tableSelectedCLS.getSelectionCount() == 0) {
			return;
		}
		TableItem item = tableSelectedCLS.getSelection()[0];
		DvChitiet objDVChiTiet = (DvChitiet) item.getData();
		if (objDVChiTiet.STS <= 0 && objDVChiTiet.THANHTOAN==0) {
			//
		} else {
			MessageDialog.openError(shellKhamBenh, "Có lỗi", "CLS đang/đã thực hiện/đã thanh toán, không chỉnh được");
			return;
		}
		if (objDVChiTiet != null) {
			if (objDVChiTiet.MA_BAC_SI!=null && objDVChiTiet.MA_BAC_SI.length()>0) {
				objDVChiTiet.MA_BAC_SI = ""; 
			} else {
				if(DbHelper.hashDataUsers.get(cbListBacSiCLS.getText())!=null){
					objDVChiTiet.MA_BAC_SI = DbHelper.hashDataUsers.get(cbListBacSiCLS.getText()).MACCHN;
				}
			}
		}
		objDVChiTiet.MA_KHOA = DbHelper.getMAKHOA(cbKhoa.getText());
		//
		tableViewerSelectedCLS.refresh();
		
	}

	private void deleteCLS() {
		if (tableSelectedCLS.getSelectionCount() == 0) {
			return;
		}
		TableItem item = tableSelectedCLS.getSelection()[0];
		DvChitiet objDVChiTiet = (DvChitiet) item.getData();
		if (objDVChiTiet != null) {
			if (objDVChiTiet.STS <= 0 && objDVChiTiet.THANHTOAN==0) {
				// nghialuong
				listCLSData.remove(objDVChiTiet);
				//
				hashDichVuChiTiet.remove(objDVChiTiet.MA_DICH_VU);
				//
				objDVChiTiet.deleteRow();
				//
				updateTinhTien();
				//
				tableViewerSelectedCLS.refresh();
				//
			} else {
				MessageDialog.openError(shellKhamBenh, "Có lỗi", "CLS đang/đã thực hiện/đã thanh toán, không xóa được");
			}
		}
	}

	private void updateKieuThanhToanCLS() {
		if (tableSelectedCLS.getSelectionCount() == 0) {
			return;
		}
		TableItem item = tableSelectedCLS.getSelection()[0];
		DvChitiet objDVChiTiet = (DvChitiet) item.getData();
		if (objDVChiTiet.STS <= 0 && objDVChiTiet.THANHTOAN==0) {
			//
		} else {
			MessageDialog.openError(shellKhamBenh, "Có lỗi", "CLS đang/đã thực hiện/đã thanh toán, không chỉnh được");
			return;
		}
		if (objDVChiTiet != null) {
			if (objDVChiTiet.MUC_HUONG == 0) {
				objDVChiTiet.MUC_HUONG = txtMathe.iTyLeHuong;
			} else {
				objDVChiTiet.MUC_HUONG = 0;
			}
		}
		objDVChiTiet.MA_KHOA = DbHelper.getMAKHOA(cbKhoa.getText());
		//
		updateTinhTien();
		//
		tableViewerSelectedCLS.refresh();
	}

	private void controlKey(KeyEvent e) {
		if (((e.stateMask & SWT.CTRL) == SWT.CTRL)) {
			switch (e.keyCode) {
			case '1': // CTRL + 1
				tabFolder.setSelection(0);
				setFocusTabFolder();
				break;
			case '2': // CTRL + 1
				tabFolder.setSelection(1);
				setFocusTabFolder();
				Date obj = null;
				obj.clone();
				break;
			case '3': // CTRL + 1
				tabFolder.setSelection(2);
				setFocusTabFolder();
				break;
			case '4': // CTRL + 1
				btnRadio1.setFocus();
				break;
			case '5': // CTRL + 1
				btnRadio2.setFocus();
				break;
			case '6': // CTRL + 1
				btnRadio3.setFocus();
				break;
			default:
				break;
			}
		}

	}

	protected void keyPress(KeyEvent e) {
		//
		switch (e.keyCode) {
			case SWT.F2:
				doSaveFormKhamBenh();
				return;
			case SWT.F3:
				doSavePhieuXetNghiem();
				return;
			case SWT.ESC:
				// shellKhamBenh.close();
				return;
		}
		controlKey(e);
	}

	private void saveCLS() {
		//
		if (DbHelper.currentSessionUserId.LOAI.toUpperCase().equals("BS") == false) {
			String message = "Không có quyền chỉ định CLS/Dịch vụ";
			MessageDialog.openError(shellKhamBenh, "Có lỗi", message);
			logger.info(message);
			return;
		}

		if (objBenhNhan.BN_ID == null) {
			savePhieuXetNghiem();
		}
		//
		//
		try {
			Connection con = DbHelper.getSql2o();
			//
			for (int i = 0; i < listCLSData.size(); i++) {
				DvChitiet obj = (DvChitiet) listCLSData.get(i);
				obj.BN_ID = objBenhNhan.BN_ID;
				obj.MA_LK = objPhieuKhamBenh.MA_LK;
				obj.insert();
				obj.STS = 0;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error(ex);
			MessageDialog.openError(shellKhamBenh, "Có lỗi", ex.getMessage());
		}
		//
		isSaveCLS = true;
		tableViewerSelectedCLS.refresh();
	}

	private void waitingDlgHide() {
		Display.getDefault().asyncExec(new Runnable() {
			public void run() {
				synchronized (SaveRunningOperation.objNotifyDone) {
					// System.out.println("WAIT 2s");
					SaveRunningOperation.objNotifyDone.notify();
					// System.out.println("SAVE DONE NOTIFYED ");
				}
			}
		});

	}

	private void waitingDlgShow() {
		Display.getDefault().asyncExec(new Runnable() {
			public void run() {
				try {
					ProgressMonitorDialog pmd = new ProgressMonitorDialog(
							shellKhamBenh) {
						@Override
						protected void setShellStyle(int newShellStyle) {
							super.setShellStyle(SWT.CLOSE | SWT.MODELESS
									| SWT.BORDER | SWT.TITLE);
							setBlockOnOpen(false);
						}

						//

						/*
						 * (non-Javadoc)
						 * 
						 * @see org.eclipse.jface.dialogs.ProgressMonitorDialog#
						 * configureShell(org.eclipse.swt.widgets.Shell)
						 */
						@Override
						protected void configureShell(Shell shell) {
							super.configureShell(shell);
							shell.setText("Làm ơn đợi...");

						}

						/*
						 * (non-Javadoc)
						 * 
						 * @see org.eclipse.jface.dialogs.ProgressMonitorDialog#
						 * createDialogArea(org.eclipse.swt.widgets.Composite)
						 */
						@Override
						protected Control createDialogArea(Composite parent) {
							return super.createDialogArea(parent);
						}
					};
					pmd.getProgressMonitor().setTaskName("Đợi xử lý.....");
					pmd.run(true, true, new SaveRunningOperation());
					//
				} catch (InvocationTargetException e) {
					MessageDialog.openError(shellKhamBenh, "Error",
							e.getMessage());
				} catch (InterruptedException e) {
					MessageDialog.openInformation(shellKhamBenh, "Cancelled",
							e.getMessage());
				}
			}
		});

	}

	private void inPhieuVienPhi(ReportDAO report) {
		java.sql.Connection con = null;
		try {
			String reportName = "PhieuVienPhi.jasper";
			JasperReport jr = (JasperReport) JRLoader.loadObject(this.getClass().getResource("/" + reportName));
			// logger.info("JasperCompileManager " + jr);
			//
			JRBeanCollectionDataSource report_cong = new JRBeanCollectionDataSource( report.congkham);
			JRBeanCollectionDataSource report_dv = new JRBeanCollectionDataSource( report.listdv);
			JRBeanCollectionDataSource report_thuoc = new JRBeanCollectionDataSource( report.thuoc);
			Map<String, Object> params = new HashMap<String, Object>();
			// System.out.println(" COUNT = " + report_dv.getRecordCount());
			if (report.bn.MA_DKBD.equals("VP")) {
				// report.bn.MA_DKBD = "";
			}
			params.put("BN", report.bn);
			params.put("KB", report.kb);
			params.put("TIENCONG_DataSource", report_cong);
			params.put("LISTDICHVU_DataSource", report_dv);
			params.put("THUOC_DataSource", report_thuoc);
			params.put("DICHVU_SUM", report.sumDv);
			params.put("TIENCONG_SUM", report.sumCongkham);
			params.put("THUOC_SUM", report.sumThuoc);
			params.put("NGAYVAO", report.tuNgay);
			params.put("NGAYRA", report.denNgay);

			if (objBenhNhan.MA_DKBD == null
					|| objBenhNhan.MA_DKBD.length() == 0) {
				params.put("TEN_CSKCB", "");
			} else {
				params.put(
						"TEN_CSKCB",
						MaCskcbCache.getMaCskcb(objBenhNhan.MA_DKBD) != null ? MaCskcbCache
								.getMaCskcb(objBenhNhan.MA_DKBD).MA_NAME : "");
			}

			if (report.congkham != null) {
				KhoaPhong objKhoaPhong = DbHelper.hashKhoaPhongMAKHOA.get(report.congkham.get(0).MA_KHOA);
				if (objKhoaPhong != null) {
					params.put("TENKHOAPHONG", objKhoaPhong.KP_NAME);

				} else {
					params.put("TENKHOAPHONG", report.congkham.get(0).MA_KHOA);
				}
				//
				if(report.congkham.get(0).MA_NHOM.intValue()==14){
					// CAP CUU
					params.put("TENKHOAPHONG", "Cấp Cứu");
					params.put("CONGKHAM_TITLE", "Tiền phòng điều trị cấp cứu");
				}
				else{
					params.put("CONGKHAM_TITLE", "Tiền công khám bệnh");
				}
				//
				Users bs = DbHelper.hashDataUsersMaCCHN.get(report.congkham.get(0).MA_BAC_SI);
				if (bs != null) {
					params.put("TENBACSI", bs.TEN_NHANVIEN);
				} else {
					params.put("TENBACSI", report.congkham.get(0).MA_BAC_SI);
				}
			}

			params.put("NGAY_GIO", report.ngayGio);
			params.put("NGUOI_LAP", report.strNguoiLap == null ? ""
					: report.strNguoiLap);
			params.put("TONGCONG_SUM", report.sumTongCong);
			JasperPrint jp = JasperFillManager.fillReport(jr, params,new JREmptyDataSource());
			JasperViewer.viewReport(jp, false);
			// JasperPrintManager.printReport(jp, true);
			// JasperPrintManager.printReport(jp_all, true);
			// JasperPrintManager.printReport(jp,false);
			//
			//
			// OutputStream os = new FileOutputStream("STUDENT_MARK_"+".pdf");
			// JasperExportManager.exportReportToPdfStream(jp, os);

			// os.flush();
			// os.close();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		} finally {
			try {
				if (con != null && !con.isClosed()) {
					// con.close();
				}
			} catch (Exception e) {
				logger.error(e);
				e.printStackTrace();
			}
		}
	}

	private void inPhieuTongHopAll(ReportDAO report) {
		java.sql.Connection con = null;
		try {
			String reportName = "PhieuBHYT4BN.jasper";
			JasperReport jr = (JasperReport) JRLoader.loadObject(this.getClass().getResource("/" + reportName));
			// logger.info("JasperCompileManager " + jr);
			JRBeanCollectionDataSource report_cong = new JRBeanCollectionDataSource( report.congkham);
			JRBeanCollectionDataSource report_dv = new JRBeanCollectionDataSource( report.listdv);
			JRBeanCollectionDataSource report_thuoc = new JRBeanCollectionDataSource( report.thuoc);
			Map<String, Object> params = new HashMap<String, Object>();
			// System.out.println(" COUNT = " + report_dv.getRecordCount());
			if (report.bn.MA_DKBD.equals("VP")) {
				// report.bn.MA_DKBD = "";
			}
			params.put("BN", report.bn);
			params.put("KB", report.kb);
			params.put("TIENCONG_DataSource", report_cong);
			params.put("LISTDICHVU_DataSource", report_dv);
			params.put("THUOC_DataSource", report_thuoc);
			params.put("DICHVU_SUM", report.sumDv);
			params.put("TIENCONG_SUM", report.sumCongkham);
			params.put("THUOC_SUM", report.sumThuoc);
			params.put("NGAYVAO", report.tuNgay);
			params.put("NGAYRA", report.denNgay);

			if (objBenhNhan.MA_DKBD == null
					|| objBenhNhan.MA_DKBD.length() == 0) {
				params.put("TEN_CSKCB", "");
			} else {
				params.put(
						"TEN_CSKCB",
						MaCskcbCache.getMaCskcb(objBenhNhan.MA_DKBD) != null ? MaCskcbCache
								.getMaCskcb(objBenhNhan.MA_DKBD).MA_NAME : "");
			}

			if (report.congkham != null) {
				KhoaPhong objKhoaPhong = DbHelper.hashKhoaPhongMAKHOA
						.get(report.congkham.get(0).MA_KHOA);
				if (objKhoaPhong != null) {
					params.put("TENKHOAPHONG", objKhoaPhong.KP_NAME);

				} else {
					params.put("TENKHOAPHONG", report.congkham.get(0).MA_KHOA);
				}
				if(report.congkham.get(0).MA_NHOM.intValue()==14){
					// CAP CUU
					params.put("TENKHOAPHONG", "Cấp Cứu");
					params.put("CONGKHAM_TITLE", "Tiền phòng điều trị cấp cứu");
				}
				else{
					params.put("CONGKHAM_TITLE", "Tiền công khám bệnh");
				}
				Users bs = DbHelper.hashDataUsersMaCCHN.get(report.congkham.get(0).MA_BAC_SI);
				if (bs != null) {
					params.put("TENBACSI", bs.TEN_NHANVIEN);
				} else {
					params.put("TENBACSI", report.congkham.get(0).MA_BAC_SI);
				}
			}

			params.put("NGAY_GIO", report.ngayGio);
			params.put("NGUOI_LAP", report.strNguoiLap == null ? ""
					: report.strNguoiLap);
			params.put("TONGCONG_SUM", report.sumTongCong);
			JasperPrint jp = JasperFillManager.fillReport(jr, params,new JREmptyDataSource());
			JasperViewer.viewReport(jp, false);
			// JasperPrintManager.printReport(jp, true);
			// JasperPrintManager.printReport(jp_all, true);
			// JasperPrintManager.printReport(jp,false);
			//
			//
			// OutputStream os = new FileOutputStream("STUDENT_MARK_"+".pdf");
			// JasperExportManager.exportReportToPdfStream(jp, os);

			// os.flush();
			// os.close();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		} finally {
			try {
				if (con != null && !con.isClosed()) {
					// con.close();
				}
			} catch (Exception e) {
				logger.error(e);
				e.printStackTrace();
			}
		}
	}
	
	private void inPhieuBHYT(ReportDAO report) {
		java.sql.Connection con = null;
		try {
			//
			String reportName = "PhieuBHYT.jasper";
			if (KIEU_THANH_TOAN == Utils.THANHTOAN_BHYT
					|| KIEU_THANH_TOAN == Utils.THANHTOAN_BHYT_2) {
				reportName = "PhieuBHYT.jasper";
			} else {
				reportName = "PhieuVienPhi.jasper";
			}
			JasperReport jr = (JasperReport) JRLoader.loadObject(this.getClass().getResource("/" + reportName));
			// logger.info("JasperCompileManager " + jr);
			JRBeanCollectionDataSource report_cong = new JRBeanCollectionDataSource( report.congkham);
			JRBeanCollectionDataSource report_dv = new JRBeanCollectionDataSource( report.listdv);
			JRBeanCollectionDataSource report_thuoc = new JRBeanCollectionDataSource( report.thuoc);
			// System.out.println(" COUNT = " + report_dv.getRecordCount());
			Map<String, Object> params = new HashMap<String, Object>();
			if (report.bn.MA_DKBD.equals("VP")) {
				// report.bn.MA_DKBD = "";
			}
			params.put("BN", report.bn);
			params.put("KB", report.kb);
			params.put("TIENCONG_DataSource", report_cong);
			params.put("LISTDICHVU_DataSource", report_dv);
			params.put("THUOC_DataSource", report_thuoc);
			params.put("DICHVU_SUM", report.sumDv);
			params.put("TIENCONG_SUM", report.sumCongkham);
			params.put("THUOC_SUM", report.sumThuoc);
			params.put("NGAYVAO", report.tuNgay);
			params.put("NGAYRA", report.denNgay);

			if (objBenhNhan.MA_DKBD == null
					|| objBenhNhan.MA_DKBD.length() == 0) {
				params.put("TEN_CSKCB", "");
			} else {
				params.put(
						"TEN_CSKCB",
						MaCskcbCache.getMaCskcb(objBenhNhan.MA_DKBD) != null ? MaCskcbCache
								.getMaCskcb(objBenhNhan.MA_DKBD).MA_NAME : "");
			}

			if (report.congkham != null) {
				KhoaPhong objKhoaPhong = DbHelper.hashKhoaPhongMAKHOA.get(report.congkham.get(0).MA_KHOA);
				if (objKhoaPhong != null) {
					params.put("TENKHOAPHONG", objKhoaPhong.KP_NAME);
				} else {
					params.put("TENKHOAPHONG", report.congkham.get(0).MA_KHOA);
				}
				if(report.congkham.get(0).MA_NHOM.intValue()==14){
					// CAP CUU
					params.put("TENKHOAPHONG", "Cấp Cứu");
					params.put("CONGKHAM_TITLE", "Tiền phòng điều trị cấp cứu");
				}
				else{
					params.put("CONGKHAM_TITLE", "Tiền công khám bệnh");
				}
				Users bs = DbHelper.hashDataUsersMaCCHN.get(report.congkham.get(0).MA_BAC_SI);
				if (bs != null) {
					params.put("TENBACSI", bs.TEN_NHANVIEN);
				} else {
					params.put("TENBACSI", report.congkham.get(0).MA_BAC_SI);
				}
			}

			params.put("NGAY_GIO", report.ngayGio);
			params.put("NGUOI_LAP", report.strNguoiLap == null ? "": report.strNguoiLap);
			params.put("TONGCONG_SUM", report.sumTongCong);
			JasperPrint jp = JasperFillManager.fillReport(jr, params,
					new JREmptyDataSource());
			JasperViewer.viewReport(jp, false);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		} finally {
			try {
				if (con != null && !con.isClosed()) {
					// con.close();
				}
			} catch (Exception e) {
				logger.error(e);
				e.printStackTrace();
			}
		}
	}

	private void inPhieuXetNghiem(ReportDAO report) {
		java.sql.Connection con = null;
		try {
			// String source = "report/PhieuVienPhi.jrxml";
			// JasperReport jr = JasperCompileManager.compileReport(source);
			JasperReport jr = (JasperReport) JRLoader.loadObject(this
					.getClass().getResource("/PhieuCLS.jasper"));

			JRBeanCollectionDataSource report_dv = new JRBeanCollectionDataSource(report.listdv);
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("BN", report.bn);
			params.put("KB", report.kb);
			params.put("LISTDICHVU_DataSource", report_dv);
			params.put("DICHVU_SUM", report.sumDv);
			params.put("NGAYVAO", report.tuNgay);
			params.put("NGAYRA", report.denNgay);
			if (objBenhNhan.MA_DKBD == null
					|| objBenhNhan.MA_DKBD.length() == 0) {
				params.put("TEN_CSKCB", "");
			} else {
				params.put(
						"TEN_CSKCB",
						MaCskcbCache.getMaCskcb(objBenhNhan.MA_DKBD) != null ? MaCskcbCache
								.getMaCskcb(objBenhNhan.MA_DKBD).MA_NAME : "");
			}
			//
			objPhieuKhamBenh.MA_KHOA = DbHelper.getMAKHOA(cbKhoa.getText());
			if (objPhieuKhamBenh == null || objPhieuKhamBenh.MA_KHOA == null
					|| objPhieuKhamBenh.MA_KHOA.length() == 0) {
				params.put("TENKHOAPHONG", "");
			}
			//
			params.put("NGAY_GIO", report.ngayGio);
			params.put("NGUOI_LAP", report.strNguoiLap == null ? ""
					: report.strNguoiLap);
			params.put("TONGCONG_SUM", report.sumTongCong);

			JasperPrint jp = JasperFillManager.fillReport(jr, params,
					new JREmptyDataSource());
			JasperViewer.viewReport(jp, false);
			// JasperPrintManager.printReport(jp,false);
			//
			//
			// OutputStream os = new FileOutputStream("STUDENT_MARK_"+".pdf");
			// JasperExportManager.exportReportToPdfStream(jp, os);

			// os.flush();
			// os.close();
		} catch (Exception e) {
			logger.error(e);
		} finally {
			try {
				if (con != null && !con.isClosed()) {
					// con.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private void printPhieuXetNghiem() {
		printPhieuXetNghiem(true);
	}
	private void printPhieuXetNghiem(boolean isPrintAll) {
		updateTinhTien();
		//
		ReportDAO report = new ReportDAO();
		report.bn = objBenhNhan;
		report.kb = objPhieuKhamBenh;

		report.listdv = new ArrayList<>();
		report.sumDv = new SumReportDAO();
		report.sumThuoc = new SumReportDAO();
		//
		Hashtable<Integer, SumReportDAO> hashPhanLoaiDV = new Hashtable<Integer, SumReportDAO>();
		//
		for (int i = 0; i < listCLSData.size(); i++) {
			DvChitiet objDvChitiet = listCLSData.get(i);
			int MANHOM = objDvChitiet.MA_NHOM;
			if (hashPhanLoaiDV.get(MANHOM) == null) {
				SumReportDAO obj = new SumReportDAO();
				//
				obj.TT2 += objDvChitiet.DON_GIA2 * 1;
				obj.TT += objDvChitiet.DON_GIA2 * 1;
				obj.BH += 0;
				obj.NB += objDvChitiet.DON_GIA2 * 1;
				obj.NB2 += objDvChitiet.DON_GIA2 * 1;
				obj.KH += 0;
				obj.dv.add(objDvChitiet);
				obj.setPhanLoaiDichVu("" + objDvChitiet.MA_NHOM);
				//
				//
				report.listdv.add(obj);
				hashPhanLoaiDV.put(MANHOM, obj);
			} else {
				// Exist add...
				SumReportDAO obj = hashPhanLoaiDV.get(MANHOM);
				//
				obj.TT2 += objDvChitiet.DON_GIA2 * 1;
				obj.TT += objDvChitiet.DON_GIA2 * 1;
				obj.BH += 0;
				obj.NB += objDvChitiet.DON_GIA2 * 1;
				obj.NB2 += objDvChitiet.DON_GIA2 * 1;
				obj.KH += 0;
				obj.dv.add(objDvChitiet);
				obj.setPhanLoaiDichVu("" + objDvChitiet.MA_NHOM);
				//
			}
			//
			report.sumDv.TT2 += objDvChitiet.THANH_TIEN2;
			report.sumDv.TT += objDvChitiet.THANH_TIEN2;
			report.sumDv.BH += 0;
			report.sumDv.NB += objDvChitiet.THANH_TIEN2;
			report.sumDv.KH += 0;
			//
		}
		//
		report.sumTongCong = report.sumDv;
		//
		report.strNguoiLap = DbHelper.currentSessionUserId.TEN_NHANVIEN;
		//
		report.ngayGio = Utils.getDatetimeLocal("Duy Xuyên", new Date());
		//
		inPhieuXetNghiem(report);
	}

	private void printPhieuBHYT() {
		try {
			printPhieuBHYT(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	protected void printPhieuBHYT(boolean isPrintAll) throws Exception {
		//
		updateTinhTien(isPrintAll);
		if(isPrintAll==true){
			// REPORT BHYT ALL DICH VU
			ReportDAO reportAll = new ReportDAO();
			ReportDAO reportBHYT = new ReportDAO();
			//
			reportAll.bn = objBenhNhan;
			reportAll.kb = objPhieuKhamBenh;
			reportBHYT.bn = objBenhNhan;
			reportBHYT.kb = objPhieuKhamBenh;
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");// 201711091458
																		// //yyyy-MM-dd
																		// HH:mm:ss
			//
			reportAll.tuNgay = Utils.getDatetime(sdf.parse(objPhieuKhamBenh.NGAY_VAO), "HH:mm dd/MM/yyyy");
			reportAll.denNgay = Utils.getDatetime(sdf.parse(objPhieuKhamBenh.NGAY_RA), "HH:mm dd/MM/yyyy");
			reportBHYT.tuNgay = Utils.getDatetime(sdf.parse(objPhieuKhamBenh.NGAY_VAO), "HH:mm dd/MM/yyyy");
			reportBHYT.denNgay = Utils.getDatetime(sdf.parse(objPhieuKhamBenh.NGAY_RA), "HH:mm dd/MM/yyyy");
			
			reportAll.listdv = new ArrayList<>();
			reportAll.sumDv = new SumReportDAO();
			reportAll.sumThuoc = new SumReportDAO();
			//
			reportBHYT.listdv = new ArrayList<>();
			reportBHYT.sumDv = new SumReportDAO();
			reportBHYT.sumThuoc = new SumReportDAO();
			//
			Hashtable<Integer, SumReportDAO> hashPhanLoaiDV = new Hashtable<Integer, SumReportDAO>();
			Hashtable<Integer, SumReportDAO> hashPhanLoaiDV_ForBH = new Hashtable<Integer, SumReportDAO>();
			//
			for (int i = 0; i < listCLSData.size(); i++) {
				//
				DvChitiet objDvChitiet = listCLSData.get(i);
				objDvChitiet.DV_ID = i + 1;
				int MANHOM = objDvChitiet.MA_NHOM;
				
				// Process for PHIEU TONG HOP
				if (hashPhanLoaiDV.get(MANHOM) == null) {
					SumReportDAO obj = new SumReportDAO();
					//
					obj.TT2 += objDvChitiet.THANH_TIEN2;
					obj.TT += objDvChitiet.THANH_TIEN;
					obj.BH += objDvChitiet.TT_BHTT;
					obj.NB += objDvChitiet.TT_BNTT;
					obj.KH += (objDvChitiet.THANH_TIEN - objDvChitiet.TT_BHTT - objDvChitiet.TT_BNTT);
					obj.dv.add(objDvChitiet);
					obj.setPhanLoaiDichVu("" + objDvChitiet.MA_NHOM);
					//
					//
					hashPhanLoaiDV.put(MANHOM, obj);
					reportAll.listdv.add(obj);
				} else 
				{
					// Exist add...
					SumReportDAO obj = hashPhanLoaiDV.get(MANHOM);
					//
					obj.TT2 += objDvChitiet.THANH_TIEN2;
					obj.TT += objDvChitiet.THANH_TIEN;
					obj.BH += objDvChitiet.TT_BHTT;
					obj.NB += objDvChitiet.TT_BNTT;
					obj.NB2 += objDvChitiet.THANH_TIEN2 - objDvChitiet.THANH_TIEN
							+ objDvChitiet.TT_BNTT;
					obj.KH += 0;
					obj.dv.add(objDvChitiet);
					obj.setPhanLoaiDichVu("" + objDvChitiet.MA_NHOM);
					//
				}
				
				// Process for PHIEU BAO HIEM
				if(objDvChitiet.TYP==1 && objDvChitiet.MUC_HUONG!=0){
					if (hashPhanLoaiDV_ForBH.get(MANHOM) == null) {
						SumReportDAO obj = new SumReportDAO();
						//
						obj.TT2 += objDvChitiet.THANH_TIEN2;
						obj.TT += objDvChitiet.THANH_TIEN;
						obj.BH += objDvChitiet.TT_BHTT;
						obj.NB += objDvChitiet.TT_BNTT;
						obj.KH += (objDvChitiet.THANH_TIEN - objDvChitiet.TT_BHTT - objDvChitiet.TT_BNTT);
						obj.dv.add(objDvChitiet);
						obj.setPhanLoaiDichVu("" + objDvChitiet.MA_NHOM);
						//
						//
						hashPhanLoaiDV_ForBH.put(MANHOM, obj);
						reportBHYT.listdv.add(obj);
					} else {
						// Exist add...
						SumReportDAO obj = hashPhanLoaiDV_ForBH.get(MANHOM);
						//
						obj.TT2 += objDvChitiet.THANH_TIEN2;
						obj.TT += objDvChitiet.THANH_TIEN;
						obj.BH += objDvChitiet.TT_BHTT;
						obj.NB += objDvChitiet.TT_BNTT;
						obj.NB2 += objDvChitiet.THANH_TIEN2 - objDvChitiet.THANH_TIEN + objDvChitiet.TT_BNTT;
						obj.KH += 0;
						obj.dv.add(objDvChitiet);
						obj.setPhanLoaiDichVu("" + objDvChitiet.MA_NHOM);
						//
					}
				}
				// For BHYT
				//
				if (KIEU_THANH_TOAN == Utils.THANHTOAN_BHYT
						|| KIEU_THANH_TOAN == Utils.THANHTOAN_BHYT_2) {
					reportAll.sumDv.TT2 += objDvChitiet.THANH_TIEN2;
					reportAll.sumDv.TT += objDvChitiet.THANH_TIEN;
					reportAll.sumDv.BH += objDvChitiet.TT_BHTT;
					reportAll.sumDv.NB += objDvChitiet.TT_BNTT;
					reportAll.sumDv.NB2 += objDvChitiet.THANH_TIEN2 - objDvChitiet.THANH_TIEN + objDvChitiet.TT_BNTT;
					reportAll.sumDv.KH += (objDvChitiet.THANH_TIEN - objDvChitiet.TT_BHTT - objDvChitiet.TT_BNTT);
					//
					if(objDvChitiet.TYP==1 && objDvChitiet.MUC_HUONG!=0){
						reportBHYT.sumDv.TT2 += objDvChitiet.THANH_TIEN2;
						reportBHYT.sumDv.TT += objDvChitiet.THANH_TIEN;
						reportBHYT.sumDv.BH += objDvChitiet.TT_BHTT;
						reportBHYT.sumDv.NB += objDvChitiet.TT_BNTT;
						reportBHYT.sumDv.NB2 += objDvChitiet.THANH_TIEN2 - objDvChitiet.THANH_TIEN + objDvChitiet.TT_BNTT;
						reportBHYT.sumDv.KH += (objDvChitiet.THANH_TIEN - objDvChitiet.TT_BHTT - objDvChitiet.TT_BNTT);
					}
					//
				} else {
					reportAll.sumDv.TT2 += objDvChitiet.THANH_TIEN2;
					reportAll.sumDv.TT += objDvChitiet.THANH_TIEN2;
					reportAll.sumDv.BH += 0;
					reportAll.sumDv.NB += objDvChitiet.THANH_TIEN2;
					reportAll.sumDv.NB2 += objDvChitiet.THANH_TIEN2;
					reportAll.sumDv.KH += 0;
				}
				//
			}
	
			reportAll.thuoc = new ArrayList<>();
			reportBHYT.thuoc = new ArrayList<>();
			
			
			// Process all
			for (int i = 0; i < listThuocData.size(); i++) {
				ThuocChitiet obj = listThuocData.get(i);
				//
				reportAll.sumThuoc.TT2 += obj.THANH_TIEN;
				reportAll.sumThuoc.TT += obj.THANH_TIEN;
				reportAll.sumThuoc.BH += obj.TT_BHTT;
				reportAll.sumThuoc.NB += obj.TT_BNTT;
				reportAll.sumThuoc.NB2 += obj.TT_BNTT;
				reportAll.sumThuoc.KH += (obj.THANH_TIEN - obj.TT_BHTT - obj.TT_BNTT);
				reportAll.thuoc.add(obj);
				//
				if(obj.TYP==1 && obj.MUC_HUONG!=0){
					// DV BAO HIEM  + KHONG PHAI TU CHI TRA
					reportBHYT.sumThuoc.TT2 += obj.THANH_TIEN;
					reportBHYT.sumThuoc.TT += obj.THANH_TIEN;
					reportBHYT.sumThuoc.BH += obj.TT_BHTT;
					reportBHYT.sumThuoc.NB += obj.TT_BNTT;
					reportBHYT.sumThuoc.NB2 += obj.TT_BNTT;
					reportBHYT.sumThuoc.KH += (obj.THANH_TIEN - obj.TT_BHTT - obj.TT_BNTT);
					reportBHYT.thuoc.add(obj);
				}
				//
			}
			// 
			
			
			System.out.println("Report cls=" + listCLSData.size() + " thuoc="
					+ listThuocData.size() + " report.sumDv.TT=" + reportAll.sumDv.TT);
			//
			reportAll.congkham = new ArrayList<>();
			reportAll.congkham.add(objCongKham);
			reportBHYT.congkham = new ArrayList<>();
			reportBHYT.congkham.add(objCongKham);
			if (KIEU_THANH_TOAN == Utils.THANHTOAN_BHYT
					|| KIEU_THANH_TOAN == Utils.THANHTOAN_BHYT_2) {
				
			}
			else{
				objCongKham.THANH_TIEN = Main.GIAKHAMVIENPHI;
				objCongKham.THANH_TIEN2 = Main.GIAKHAMVIENPHI;
				objCongKham.TT_BNTT = Main.GIAKHAMVIENPHI;
				objCongKham.TT_BHTT = 0;
			}
			System.out.println("Cong khamobjCongKham =" + objCongKham.toString());
			//
			reportAll.sumCongkham = new SumReportDAO();
			reportAll.sumCongkham.TT2 = objCongKham.THANH_TIEN2;
			reportAll.sumCongkham.TT = objCongKham.THANH_TIEN;
			//
			reportBHYT.sumCongkham = new SumReportDAO();
			reportBHYT.sumCongkham.TT2 = objCongKham.THANH_TIEN2;
			reportBHYT.sumCongkham.TT = objCongKham.THANH_TIEN;
			if (KIEU_THANH_TOAN == Utils.THANHTOAN_BHYT
					|| KIEU_THANH_TOAN == Utils.THANHTOAN_BHYT_2) {
				reportAll.sumCongkham.BH = objCongKham.TT_BHTT;
				reportAll.sumCongkham.NB = objCongKham.TT_BNTT;
				reportAll.sumCongkham.NB2 = (objCongKham.TT_BNTT + objCongKham.THANH_TIEN2 - objCongKham.THANH_TIEN);
				reportAll.sumCongkham.KH = (objCongKham.THANH_TIEN - objCongKham.TT_BHTT - objCongKham.TT_BNTT);
				//
				reportBHYT.sumCongkham.BH = objCongKham.TT_BHTT;
				reportBHYT.sumCongkham.NB = objCongKham.TT_BNTT;
				reportBHYT.sumCongkham.NB2 = (objCongKham.TT_BNTT + objCongKham.THANH_TIEN2 - objCongKham.THANH_TIEN);
				reportBHYT.sumCongkham.KH = (objCongKham.THANH_TIEN - objCongKham.TT_BHTT - objCongKham.TT_BNTT);
			} else {
				reportAll.sumCongkham.BH = 0;
				reportAll.sumCongkham.NB = objCongKham.THANH_TIEN;
				reportAll.sumCongkham.NB2 = objCongKham.THANH_TIEN2;
				reportAll.sumCongkham.KH = 0;
			}
			System.out.println("Cong kham =" + reportAll.sumCongkham.toString());
			//
			reportAll.sumTongCong = new SumReportDAO();
			reportBHYT.sumTongCong = new SumReportDAO();
			if (KIEU_THANH_TOAN == Utils.THANHTOAN_BHYT || KIEU_THANH_TOAN == Utils.THANHTOAN_BHYT_2) {
				reportAll.sumTongCong.TT2 = reportAll.sumDv.TT2 + reportAll.sumThuoc.TT2+ reportAll.sumCongkham.TT2;
				reportAll.sumTongCong.TT = reportAll.sumDv.TT + reportAll.sumThuoc.TT+ reportAll.sumCongkham.TT;
				reportAll.sumTongCong.BH = reportAll.sumDv.BH + reportAll.sumThuoc.BH+ reportAll.sumCongkham.BH;
				reportAll.sumTongCong.NB = reportAll.sumDv.NB + reportAll.sumThuoc.NB+ reportAll.sumCongkham.NB;
				reportAll.sumTongCong.NB2 = reportAll.sumDv.NB2 + reportAll.sumThuoc.NB2 + reportAll.sumCongkham.NB2;
				reportAll.sumTongCong.KH = reportAll.sumDv.KH + reportAll.sumThuoc.KH + reportAll.sumCongkham.KH;
				
				reportBHYT.sumTongCong.TT2 = reportBHYT.sumDv.TT2 + reportBHYT.sumThuoc.TT2+ reportBHYT.sumCongkham.TT2;
				reportBHYT.sumTongCong.TT = reportBHYT.sumDv.TT + reportBHYT.sumThuoc.TT+ reportBHYT.sumCongkham.TT;
				reportBHYT.sumTongCong.BH = reportBHYT.sumDv.BH + reportBHYT.sumThuoc.BH+ reportBHYT.sumCongkham.BH;
				reportBHYT.sumTongCong.NB = reportBHYT.sumDv.NB + reportBHYT.sumThuoc.NB+ reportBHYT.sumCongkham.NB;
				reportBHYT.sumTongCong.NB2 = reportBHYT.sumDv.NB2 + reportBHYT.sumThuoc.NB2 + reportBHYT.sumCongkham.NB2;
				reportBHYT.sumTongCong.KH = reportBHYT.sumDv.KH + reportBHYT.sumThuoc.KH + reportBHYT.sumCongkham.KH;
			} else {
				reportAll.sumTongCong.TT2 = reportAll.sumDv.TT2 + reportAll.sumThuoc.TT2+ reportAll.sumCongkham.TT2;
				reportAll.sumTongCong.TT = reportAll.sumTongCong.TT2;
				reportAll.sumTongCong.BH = 0;
				reportAll.sumTongCong.NB = reportAll.sumTongCong.TT2;
				reportAll.sumTongCong.NB2 = reportAll.sumTongCong.TT2;
				reportAll.sumTongCong.KH = 0;
			}
			System.out.println("SUM TT" + reportAll.sumTongCong.TT + " " + reportAll.sumTongCong.getTTString());
			System.out.println("SUM BH" + reportAll.sumTongCong.BH + " " + reportAll.sumTongCong.getBHString());
			//
			reportAll.strNguoiLap = DbHelper.currentSessionUserId.TEN_NHANVIEN;
			reportBHYT.strNguoiLap = DbHelper.currentSessionUserId.TEN_NHANVIEN;
			//
			reportAll.ngayGio = Utils.getDatetimeLocal("Duy Xuyên", new Date());
			reportBHYT.ngayGio = Utils.getDatetimeLocal("Duy Xuyên", new Date());
			//
			inPhieuBHYT(reportBHYT);
			inPhieuTongHopAll(reportAll);
		}
		else{
			// Only print the new....
			// REPORT BHYT ALL DICH VU
			ReportDAO reportAll = new ReportDAO();
			//
			reportAll.bn = objBenhNhan;
			reportAll.kb = objPhieuKhamBenh;
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");// 201711091458
																		// //yyyy-MM-dd
																		// HH:mm:ss
			//
			reportAll.tuNgay = Utils.getDatetime(sdf.parse(objPhieuKhamBenh.NGAY_VAO), "HH:mm dd/MM/yyyy");
			reportAll.denNgay = Utils.getDatetime(sdf.parse(objPhieuKhamBenh.NGAY_RA), "HH:mm dd/MM/yyyy");
		
			reportAll.listdv = new ArrayList<>();
			reportAll.sumDv = new SumReportDAO();
			reportAll.sumThuoc = new SumReportDAO();
			//
			//
			Hashtable<Integer, SumReportDAO> hashPhanLoaiDV = new Hashtable<Integer, SumReportDAO>();
			//
			for (int i = 0; i < listCLSData.size(); i++) {
				//
				DvChitiet objDvChitiet = listCLSData.get(i);
				if(objDvChitiet.THANHTOAN==1){
					continue;
				}
				
				objDvChitiet.DV_ID = i + 1;
				int MANHOM = objDvChitiet.MA_NHOM;

				if (hashPhanLoaiDV.get(MANHOM) == null) {
					SumReportDAO obj = new SumReportDAO();
					//
					obj.TT2 += objDvChitiet.THANH_TIEN2;
					obj.TT += objDvChitiet.THANH_TIEN;
					obj.BH += objDvChitiet.TT_BHTT;
					obj.NB += objDvChitiet.TT_BNTT;
					obj.KH += (objDvChitiet.THANH_TIEN - objDvChitiet.TT_BHTT - objDvChitiet.TT_BNTT);
					obj.dv.add(objDvChitiet);
					obj.setPhanLoaiDichVu("" + objDvChitiet.MA_NHOM);
					//
					hashPhanLoaiDV.put(MANHOM, obj);
					reportAll.listdv.add(obj);
					
				} else {
					// Exist add...
					SumReportDAO obj = hashPhanLoaiDV.get(MANHOM);
					//
					obj.TT2 += objDvChitiet.THANH_TIEN2;
					obj.TT += objDvChitiet.THANH_TIEN;
					obj.BH += objDvChitiet.TT_BHTT;
					obj.NB += objDvChitiet.TT_BNTT;
					obj.NB2 += objDvChitiet.THANH_TIEN2 - objDvChitiet.THANH_TIEN
							+ objDvChitiet.TT_BNTT;
					obj.KH += 0;
					obj.dv.add(objDvChitiet);
					obj.setPhanLoaiDichVu("" + objDvChitiet.MA_NHOM);
					//
				}
				//
				if (KIEU_THANH_TOAN == Utils.THANHTOAN_BHYT
						|| KIEU_THANH_TOAN == Utils.THANHTOAN_BHYT_2) {
					reportAll.sumDv.TT2 += objDvChitiet.THANH_TIEN2;
					reportAll.sumDv.TT += objDvChitiet.THANH_TIEN;
					reportAll.sumDv.BH += objDvChitiet.TT_BHTT;
					reportAll.sumDv.NB += objDvChitiet.TT_BNTT;
					reportAll.sumDv.NB2 += objDvChitiet.THANH_TIEN2 - objDvChitiet.THANH_TIEN + objDvChitiet.TT_BNTT;
					reportAll.sumDv.KH += (objDvChitiet.THANH_TIEN - objDvChitiet.TT_BHTT - objDvChitiet.TT_BNTT);
					
					//
				} else {
					reportAll.sumDv.TT2 += objDvChitiet.THANH_TIEN2;
					reportAll.sumDv.TT += objDvChitiet.THANH_TIEN2;
					reportAll.sumDv.BH += 0;
					reportAll.sumDv.NB += objDvChitiet.THANH_TIEN2;
					reportAll.sumDv.NB2 += objDvChitiet.THANH_TIEN2;
					reportAll.sumDv.KH += 0;
				}
				//
			}
	
			reportAll.thuoc = new ArrayList<>();
	
			for (int i = 0; i < listThuocData.size(); i++) {
				ThuocChitiet obj = listThuocData.get(i);
				if(obj.THANHTOAN==1){
					continue;
				}
				//
				reportAll.sumThuoc.TT2 += obj.THANH_TIEN;
				reportAll.sumThuoc.TT += obj.THANH_TIEN;
				reportAll.sumThuoc.BH += obj.TT_BHTT;
				reportAll.sumThuoc.NB += obj.TT_BNTT;
				reportAll.sumThuoc.NB2 += obj.TT_BNTT;
				reportAll.sumThuoc.KH += (obj.THANH_TIEN - obj.TT_BHTT - obj.TT_BNTT);
				reportAll.thuoc.add(obj);
				
				//
			}
			System.out.println("Report cls=" + listCLSData.size() + " thuoc="
					+ listThuocData.size() + " report.sumDv.TT=" + reportAll.sumDv.TT);
			//
			reportAll.congkham = new ArrayList<>();
			reportAll.sumCongkham = new SumReportDAO();
			reportAll.congkham.add(objCongKham);
			//
			if(objCongKham.THANHTOAN==0){
				if (KIEU_THANH_TOAN == Utils.THANHTOAN_BHYT
						|| KIEU_THANH_TOAN == Utils.THANHTOAN_BHYT_2) {
					
				}
				else{
					objCongKham.THANH_TIEN = Main.GIAKHAMVIENPHI;
					objCongKham.THANH_TIEN2 = Main.GIAKHAMVIENPHI;
					objCongKham.TT_BNTT = Main.GIAKHAMVIENPHI;
					objCongKham.TT_BHTT = 0;
				}
				System.out.println("Cong khamobjCongKham =" + objCongKham.toString());
				//
				reportAll.sumCongkham.TT2 = objCongKham.THANH_TIEN2;
				reportAll.sumCongkham.TT = objCongKham.THANH_TIEN;
				//
				if (KIEU_THANH_TOAN == Utils.THANHTOAN_BHYT
						|| KIEU_THANH_TOAN == Utils.THANHTOAN_BHYT_2) {
					reportAll.sumCongkham.BH = objCongKham.TT_BHTT;
					reportAll.sumCongkham.NB = objCongKham.TT_BNTT;
					reportAll.sumCongkham.NB2 = (objCongKham.TT_BNTT + objCongKham.THANH_TIEN2 - objCongKham.THANH_TIEN);
					reportAll.sumCongkham.KH = (objCongKham.THANH_TIEN - objCongKham.TT_BHTT - objCongKham.TT_BNTT);
					//
				} else {
					reportAll.sumCongkham.BH = 0;
					reportAll.sumCongkham.NB = objCongKham.THANH_TIEN;
					reportAll.sumCongkham.NB2 = objCongKham.THANH_TIEN2;
					reportAll.sumCongkham.KH = 0;
				}
				System.out.println("Cong kham =" + reportAll.sumCongkham.toString());
			}
			//
			reportAll.sumTongCong = new SumReportDAO();
			if (KIEU_THANH_TOAN == Utils.THANHTOAN_BHYT || KIEU_THANH_TOAN == Utils.THANHTOAN_BHYT_2) {
				reportAll.sumTongCong.TT2 = reportAll.sumDv.TT2 + reportAll.sumThuoc.TT2+ reportAll.sumCongkham.TT2;
				reportAll.sumTongCong.TT = reportAll.sumDv.TT + reportAll.sumThuoc.TT+ reportAll.sumCongkham.TT;
				reportAll.sumTongCong.BH = reportAll.sumDv.BH + reportAll.sumThuoc.BH+ reportAll.sumCongkham.BH;
				reportAll.sumTongCong.NB = reportAll.sumDv.NB + reportAll.sumThuoc.NB+ reportAll.sumCongkham.NB;
				reportAll.sumTongCong.NB2 = reportAll.sumDv.NB2 + reportAll.sumThuoc.NB2 + reportAll.sumCongkham.NB2;
				reportAll.sumTongCong.KH = reportAll.sumDv.KH + reportAll.sumThuoc.KH + reportAll.sumCongkham.KH;
			} else {
				reportAll.sumTongCong.TT2 = reportAll.sumDv.TT2 + reportAll.sumThuoc.TT2+ reportAll.sumCongkham.TT2;
				reportAll.sumTongCong.TT = reportAll.sumTongCong.TT2;
				reportAll.sumTongCong.BH = 0;
				reportAll.sumTongCong.NB = reportAll.sumTongCong.TT2;
				reportAll.sumTongCong.NB2 = reportAll.sumTongCong.TT2;
				reportAll.sumTongCong.KH = 0;
			}
			System.out.println("SUM TT" + reportAll.sumTongCong.TT + " " + reportAll.sumTongCong.getTTString());
			System.out.println("SUM BH" + reportAll.sumTongCong.BH + " " + reportAll.sumTongCong.getBHString());
			//
			reportAll.strNguoiLap = DbHelper.currentSessionUserId.TEN_NHANVIEN;
			//
			reportAll.ngayGio = Utils.getDatetimeLocal("Duy Xuyên", new Date());
			//
			inPhieuTongHopAll(reportAll);
			//
		}
	}
	
	protected void printPhieuVienPhi(boolean isPrintAll) throws Exception {
		//
		updateTinhTien(isPrintAll);
		if(isPrintAll==true){
			// REPORT BHYT ALL DICH VU
			ReportDAO reportAll = new ReportDAO();
			//
			reportAll.bn = objBenhNhan;
			reportAll.kb = objPhieuKhamBenh;
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");// 201711091458
																		// //yyyy-MM-dd
																		// HH:mm:ss
			//
			reportAll.tuNgay = Utils.getDatetime(sdf.parse(objPhieuKhamBenh.NGAY_VAO), "HH:mm dd/MM/yyyy");
			reportAll.denNgay = Utils.getDatetime(sdf.parse(objPhieuKhamBenh.NGAY_RA), "HH:mm dd/MM/yyyy");
			
			reportAll.listdv = new ArrayList<>();
			reportAll.sumDv = new SumReportDAO();
			reportAll.sumThuoc = new SumReportDAO();
			//
			Hashtable<Integer, SumReportDAO> hashPhanLoaiDV = new Hashtable<Integer, SumReportDAO>();
			//
			for (int i = 0; i < listCLSData.size(); i++) {
				//
				DvChitiet objDvChitiet = listCLSData.get(i);
				objDvChitiet.DV_ID = i + 1;
				int MANHOM = objDvChitiet.MA_NHOM;
				
				// Process for PHIEU TONG HOP
				if (hashPhanLoaiDV.get(MANHOM) == null) {
					SumReportDAO obj = new SumReportDAO();
					//
					obj.TT2 += objDvChitiet.THANH_TIEN2;
					obj.TT += objDvChitiet.THANH_TIEN2;
					obj.BH += 0;
					obj.NB += objDvChitiet.THANH_TIEN2;
					obj.KH += 0;
					obj.dv.add(objDvChitiet);
					obj.setPhanLoaiDichVu("" + objDvChitiet.MA_NHOM);
					//
					//
					hashPhanLoaiDV.put(MANHOM, obj);
					reportAll.listdv.add(obj);
				} else 
				{
					// Exist add...
					SumReportDAO obj = hashPhanLoaiDV.get(MANHOM);
					//
					obj.TT2 += objDvChitiet.THANH_TIEN2;
					obj.TT += objDvChitiet.THANH_TIEN2;
					obj.BH += 0;
					obj.NB += objDvChitiet.THANH_TIEN2;
					obj.NB2 += objDvChitiet.THANH_TIEN2;
					obj.KH += 0;
					obj.dv.add(objDvChitiet);
					obj.setPhanLoaiDichVu("" + objDvChitiet.MA_NHOM);
					//
				}
				
				//
				reportAll.sumDv.TT2 += objDvChitiet.THANH_TIEN2;
				reportAll.sumDv.TT += objDvChitiet.THANH_TIEN2;
				reportAll.sumDv.BH += 0;
				reportAll.sumDv.NB += objDvChitiet.THANH_TIEN2;
				reportAll.sumDv.NB2 += objDvChitiet.THANH_TIEN2;
				reportAll.sumDv.KH += 0;
				//
			}
	
			reportAll.thuoc = new ArrayList<>();
			// Process all
			for (int i = 0; i < listThuocData.size(); i++) {
				ThuocChitiet obj = listThuocData.get(i);
				//
				reportAll.sumThuoc.TT2 += obj.THANH_TIEN;
				reportAll.sumThuoc.TT += obj.THANH_TIEN;
				reportAll.sumThuoc.BH += 0;
				reportAll.sumThuoc.NB += obj.THANH_TIEN;
				reportAll.sumThuoc.NB2 += obj.THANH_TIEN;
				reportAll.sumThuoc.KH += 0;
				reportAll.thuoc.add(obj);
				//
			}
			// 
			
			
			System.out.println("Report cls=" + listCLSData.size() + " thuoc="
					+ listThuocData.size() + " report.sumDv.TT=" + reportAll.sumDv.TT);
			//
			reportAll.congkham = new ArrayList<>();
			reportAll.congkham.add(objCongKham);
			if (KIEU_THANH_TOAN == Utils.THANHTOAN_BHYT
					|| KIEU_THANH_TOAN == Utils.THANHTOAN_BHYT_2) {
				
			}
			else{
				objCongKham.THANH_TIEN = objCongKham.THANH_TIEN2;
				objCongKham.THANH_TIEN2 = objCongKham.THANH_TIEN2;
				objCongKham.TT_BNTT = objCongKham.THANH_TIEN2;
				objCongKham.TT_BHTT = 0;
			}
			System.out.println("Cong khamobjCongKham =" + objCongKham.toString());
			//
			reportAll.sumCongkham = new SumReportDAO();
			reportAll.sumCongkham.TT2 = objCongKham.THANH_TIEN2;
			reportAll.sumCongkham.TT = objCongKham.THANH_TIEN;
			//
			reportAll.sumCongkham.BH = 0;
			reportAll.sumCongkham.NB = objCongKham.THANH_TIEN;
			reportAll.sumCongkham.NB2 = objCongKham.THANH_TIEN2;
			reportAll.sumCongkham.KH = 0;
			System.out.println("Cong kham =" + reportAll.sumCongkham.toString());
			//
			reportAll.sumTongCong = new SumReportDAO();
			reportAll.sumTongCong.TT2 = reportAll.sumDv.TT2 + reportAll.sumThuoc.TT2+ reportAll.sumCongkham.TT2;
			reportAll.sumTongCong.TT = reportAll.sumTongCong.TT2;
			reportAll.sumTongCong.BH = 0;
			reportAll.sumTongCong.NB = reportAll.sumTongCong.TT2;
			reportAll.sumTongCong.NB2 = reportAll.sumTongCong.TT2;
			reportAll.sumTongCong.KH = 0;
			System.out.println("SUM TT" + reportAll.sumTongCong.TT + " " + reportAll.sumTongCong.getTTString());
			System.out.println("SUM BH" + reportAll.sumTongCong.BH + " " + reportAll.sumTongCong.getBHString());
			//
			reportAll.strNguoiLap = DbHelper.currentSessionUserId.TEN_NHANVIEN;
			//
			reportAll.ngayGio = Utils.getDatetimeLocal("Duy Xuyên", new Date());
			//
			inPhieuVienPhi(reportAll);
		}
		else{
			// Only print the new....
			// REPORT BHYT ALL DICH VU
			ReportDAO reportAll = new ReportDAO();
			//
			reportAll.bn = objBenhNhan;
			reportAll.kb = objPhieuKhamBenh;
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");// 201711091458
																		// //yyyy-MM-dd
																		// HH:mm:ss
			//
			reportAll.tuNgay = Utils.getDatetime(sdf.parse(objPhieuKhamBenh.NGAY_VAO), "HH:mm dd/MM/yyyy");
			reportAll.denNgay = Utils.getDatetime(sdf.parse(objPhieuKhamBenh.NGAY_RA), "HH:mm dd/MM/yyyy");
		
			reportAll.listdv = new ArrayList<>();
			reportAll.sumDv = new SumReportDAO();
			reportAll.sumThuoc = new SumReportDAO();
			//
			//
			Hashtable<Integer, SumReportDAO> hashPhanLoaiDV = new Hashtable<Integer, SumReportDAO>();
			//
			for (int i = 0; i < listCLSData.size(); i++) {
				//
				DvChitiet objDvChitiet = listCLSData.get(i);
				if(objDvChitiet.THANHTOAN==1){
					continue;
				}
				
				objDvChitiet.DV_ID = i + 1;
				int MANHOM = objDvChitiet.MA_NHOM;

				if (hashPhanLoaiDV.get(MANHOM) == null) {
					SumReportDAO obj = new SumReportDAO();
					//
					obj.TT2 += objDvChitiet.THANH_TIEN2;
					obj.TT += objDvChitiet.THANH_TIEN2;
					obj.BH +=0;
					obj.NB += objDvChitiet.THANH_TIEN2;
					obj.KH += 0;
					obj.dv.add(objDvChitiet);
					obj.setPhanLoaiDichVu("" + objDvChitiet.MA_NHOM);
					//
					hashPhanLoaiDV.put(MANHOM, obj);
					reportAll.listdv.add(obj);
					
				} else {
					// Exist add...
					SumReportDAO obj = hashPhanLoaiDV.get(MANHOM);
					//
					obj.TT2 += objDvChitiet.THANH_TIEN2;
					obj.TT += objDvChitiet.THANH_TIEN2;
					obj.BH += 0;
					obj.NB += objDvChitiet.THANH_TIEN2;
					obj.NB2 += objDvChitiet.THANH_TIEN2 ;
					obj.KH += 0;
					obj.dv.add(objDvChitiet);
					obj.setPhanLoaiDichVu("" + objDvChitiet.MA_NHOM);
					//
				}
				//
				reportAll.sumDv.TT2 += objDvChitiet.THANH_TIEN2;
				reportAll.sumDv.TT += objDvChitiet.THANH_TIEN2;
				reportAll.sumDv.BH += 0;
				reportAll.sumDv.NB += objDvChitiet.THANH_TIEN2;
				reportAll.sumDv.NB2 += objDvChitiet.THANH_TIEN2;
				reportAll.sumDv.KH += 0;
				//
			}
	
			reportAll.thuoc = new ArrayList<>();
	
			for (int i = 0; i < listThuocData.size(); i++) {
				ThuocChitiet obj = listThuocData.get(i);
				if(obj.THANHTOAN==1){
					continue;
				}
				//
				reportAll.sumThuoc.TT2 += obj.THANH_TIEN;
				reportAll.sumThuoc.TT += obj.THANH_TIEN;
				reportAll.sumThuoc.BH += 0;
				reportAll.sumThuoc.NB += obj.THANH_TIEN;
				reportAll.sumThuoc.NB2 += obj.THANH_TIEN;
				reportAll.sumThuoc.KH += 0;
				reportAll.thuoc.add(obj);
				
				//
			}
			System.out.println("Report cls=" + listCLSData.size() + " thuoc="
					+ listThuocData.size() + " report.sumDv.TT=" + reportAll.sumDv.TT);
			//
			reportAll.congkham = new ArrayList<>();
			reportAll.sumCongkham = new SumReportDAO();
			reportAll.congkham.add(objCongKham);
			//
			if(objCongKham.THANHTOAN==0){
				objCongKham.THANH_TIEN = objCongKham.THANH_TIEN2;
				objCongKham.THANH_TIEN2 = objCongKham.THANH_TIEN2;
				objCongKham.TT_BNTT = objCongKham.THANH_TIEN2;
				objCongKham.TT_BHTT = 0;
				System.out.println("Cong khamobjCongKham =" + objCongKham.toString());
				//
				reportAll.sumCongkham.TT2 = objCongKham.THANH_TIEN2;
				reportAll.sumCongkham.TT = objCongKham.THANH_TIEN;
				//
				reportAll.sumCongkham.BH = 0;
				reportAll.sumCongkham.NB = objCongKham.THANH_TIEN;
				reportAll.sumCongkham.NB2 = objCongKham.THANH_TIEN2;
				reportAll.sumCongkham.KH = 0;
				System.out.println("Cong kham =" + reportAll.sumCongkham.toString());
			}
			//
			reportAll.sumTongCong = new SumReportDAO();
			reportAll.sumTongCong.TT2 = reportAll.sumDv.TT2 + reportAll.sumThuoc.TT2+ reportAll.sumCongkham.TT2;
			reportAll.sumTongCong.TT = reportAll.sumTongCong.TT2;
			reportAll.sumTongCong.BH = 0;
			reportAll.sumTongCong.NB = reportAll.sumTongCong.TT2;
			reportAll.sumTongCong.NB2 = reportAll.sumTongCong.TT2;
			reportAll.sumTongCong.KH = 0;
			System.out.println("SUM TT" + reportAll.sumTongCong.TT + " " + reportAll.sumTongCong.getTTString());
			System.out.println("SUM BH" + reportAll.sumTongCong.BH + " " + reportAll.sumTongCong.getBHString());
			//
			reportAll.strNguoiLap = DbHelper.currentSessionUserId.TEN_NHANVIEN;
			//
			reportAll.ngayGio = Utils.getDatetimeLocal("Duy Xuyên", new Date());
			//
			inPhieuVienPhi(reportAll);
			//
		}
	}

	//
	private void updateGiaTien(float tyleHuongBH, boolean isAll) {
		logger.info("Update tien:" + tyleHuongBH);
		TONGCONG_BHYT = 0;
		TONGCONG = 0;
		TONGCONG_BH = 0;
		TONGCONG_NB = 0;
		T_THUOC = 0;
		// ============================================================================================
		// UPDATE CONG KHAM
		// ============================================================================================
		if (KIEU_THANH_TOAN == Utils.THANHTOAN_BHYT) {
			// chỉ đổi giá công khám
			if (objCongKham != null && (objCongKham.THANHTOAN==0 || isAll==true)) {
				//
				objCongKham.BN_ID = objBenhNhan.BN_ID;
				if (objPhieuKhamBenh != null)
					objCongKham.MA_LK = objPhieuKhamBenh.MA_LK;
				objCongKham.MUC_HUONG = txtMathe.iTyLeHuong;
				if(tyleHuongBH==1){
					objCongKham.MUC_HUONG = 100;
				}
				objCongKham.TYLE_TT = 100;

				// Update field
				objCongKham.MA_PTTT = 0;
				objCongKham.MA_KHOA = DbHelper.getMAKHOA(cbKhoa.getText());
				//objCongKham.MA_BAC_SI = DbHelper.currentSessionUserId.MACCHN;
				objCongKham.MA_BENH = cbMaBenh.getText();
				//
				String key = cbKhoa.getText();
				Dichvu obj = DbHelper.hashCongKham.get(key);
				if (obj != null) {
					objCongKham.DON_GIA = obj.DON_GIA;// GIA BH
					objCongKham.THANH_TIEN = obj.DON_GIA * 1;
					objCongKham.DON_GIA2 = obj.DON_GIA2;// THUC THU
					objCongKham.THANH_TIEN2 = obj.DON_GIA2 * 1;
					//
					objCongKham.TT_BHTT = (int) ((float) obj.DON_GIA * tyleHuongBH);
					objCongKham.TT_BNTT = objCongKham.THANH_TIEN - objCongKham.TT_BHTT;
					//
					TONGCONG += objCongKham.THANH_TIEN2;
					TONGCONG_BHYT += objCongKham.THANH_TIEN;
					TONGCONG_BH += objCongKham.TT_BHTT;
					TONGCONG_NB += objCongKham.THANH_TIEN2 - objCongKham.TT_BHTT;
					//
					System.out.println("Công khám: " + objCongKham.toString()
							+ " TONGCONG=" + TONGCONG + " TONGCONG_BH"
							+ TONGCONG_BH + " TONGCONG_NB=" + TONGCONG_NB);
				}
			}
			//
		} else if (KIEU_THANH_TOAN == Utils.THANHTOAN_BHYT_2) {
			// chỉ đổi giá công khám
			if (objCongKham != null && (objCongKham.THANHTOAN==0 || isAll==true)) {
				objCongKham.BN_ID = objBenhNhan.BN_ID;
				if (objPhieuKhamBenh != null)
					objCongKham.MA_LK = objPhieuKhamBenh.MA_LK;
				objCongKham.MUC_HUONG = txtMathe.iTyLeHuong;
				if(tyleHuongBH==1){
					objCongKham.MUC_HUONG = 100;
				}
				objCongKham.TYLE_TT = 100;
				
				// Update field
				objCongKham.MA_PTTT = 0;
				objCongKham.MA_KHOA = DbHelper.getMAKHOA(cbKhoa.getText());
				//objCongKham.MA_BAC_SI = DbHelper.currentSessionUserId.MACCHN;
				objCongKham.MA_BENH = cbMaBenh.getText();
				//
				String key = cbKhoa.getText();
				Dichvu obj = DbHelper.hashCongKham.get(key);
				if (obj != null) {
					objCongKham.TEN_DICH_VU = obj.TEN_DVKT;
					objCongKham.MA_DICH_VU = obj.MA_DVKT;
					objCongKham.MA_NHOM = Utils.getInt(obj.MANHOM_9324);
					objCongKham.DV_ID = obj.DV_ID;
					objCongKham.DV_ID = obj.DV_ID;

					objCongKham.SO_LUONG = 1;// GIA BH
					objCongKham.DON_GIA = obj.DON_GIA;// GIA BH
					objCongKham.THANH_TIEN = obj.DON_GIA * 1;
					objCongKham.DON_GIA2 = obj.DON_GIA2;// THUC THU
					objCongKham.THANH_TIEN2 = obj.DON_GIA2 * 1;
					// objCongKham.DON_GIA2 = obj.DON_GIA;
					// objCongKham.DON_GIA = obj.DON_GIA2;
					// objCongKham.THANH_TIEN = obj.DON_GIA2 * 1;
					objCongKham.TT_BHTT = (int) ((float) obj.DON_GIA * tyleHuongBH);
					objCongKham.TT_BNTT = objCongKham.THANH_TIEN
							- objCongKham.TT_BHTT;
					//
					TONGCONG += objCongKham.THANH_TIEN2;
					TONGCONG_BHYT += objCongKham.THANH_TIEN;
					TONGCONG_BH += objCongKham.TT_BHTT;
					TONGCONG_NB += objCongKham.THANH_TIEN2 - objCongKham.TT_BHTT;
					//
				}
			}
			//
		} else if (KIEU_THANH_TOAN == Utils.THANHTOAN_VIENPHI) {
			// chỉ đổi giá công khám
			if (CHI_XET_NGHIEM == 1) {
				// Khong co cong kham benh
			} else {
				if (objCongKham != null && (objCongKham.THANHTOAN==0 || isAll==true)) {
					if( isCapCuu==true ){
						//
						//MessageDialog.openInformation(shellKhamBenh, "", "Không save cong kham");
					}
					else{
						objCongKham.BN_ID = objBenhNhan.BN_ID;
						if (objPhieuKhamBenh != null)
							objCongKham.MA_LK = objPhieuKhamBenh.MA_LK;
						objCongKham.MUC_HUONG = 0;
						// Update field
						objCongKham.MA_PTTT = 0;
						objCongKham.MA_KHOA = DbHelper.getMAKHOA(cbKhoa.getText());
						//objCongKham.MA_BAC_SI = DbHelper.currentSessionUserId.MACCHN;
						objCongKham.MA_BENH = cbMaBenh.getText();
						//
	
						String key = cbKhoa.getText();
						Dichvu obj = DbHelper.hashCongKham.get(key);
						if (obj != null) {
							objCongKham.DON_GIA = obj.DON_GIA;// GIA BH
							objCongKham.THANH_TIEN = obj.DON_GIA * 1;
							if( objCongKham.MA_NHOM==13){
								objCongKham.DON_GIA2 = Main.GIAKHAMVIENPHI;// THUC THU
							}
							objCongKham.THANH_TIEN2 = objCongKham.DON_GIA2 * 1;
							objCongKham.TT_BHTT = 0;
							objCongKham.TT_BNTT = objCongKham.THANH_TIEN2 - objCongKham.TT_BHTT;
							//
							TONGCONG += objCongKham.THANH_TIEN2;
							TONGCONG_BHYT += objCongKham.TT_BHTT;
							TONGCONG_BH += objCongKham.TT_BHTT;
							TONGCONG_NB += objCongKham.TT_BNTT;
							//
						}
					}
				}
			}
			//
		} else if (KIEU_THANH_TOAN == Utils.THANHTOAN_TAI_KHAM
				|| KIEU_THANH_TOAN == Utils.THANHTOAN_VIENPHI_FREE) {
			if (CHI_XET_NGHIEM == 1) {
				// Khong co cong kham benh
			} else {
				if (objCongKham != null && (objCongKham.THANHTOAN==0 || isAll==true)) {
					// Update field
					String key = cbKhoa.getText();
					Dichvu obj = DbHelper.hashCongKham.get(key);
					if (obj != null) {
						objCongKham.DON_GIA2 = obj.DON_GIA;
						objCongKham.DON_GIA = obj.DON_GIA2;
					}
					objCongKham.MA_PTTT = 0;
					objCongKham.MA_KHOA = DbHelper.getMAKHOA(cbKhoa.getText());
					//objCongKham.MA_BAC_SI = DbHelper.currentSessionUserId.MACCHN;
					objCongKham.MA_BENH = cbMaBenh.getText();
					//
					objCongKham.THANH_TIEN = 0;
					objCongKham.TT_BHTT = 0;
					objCongKham.TT_BNTT = 0;
					//
					TONGCONG += objCongKham.THANH_TIEN;
					TONGCONG_BHYT += objCongKham.THANH_TIEN;
					TONGCONG_BH += objCongKham.TT_BHTT;
					TONGCONG_NB += objCongKham.TT_BNTT;
					//
				}
			}
		}
		// UPDATE THUOC
		// THUOC HUONG theo ty le %
		for (ThuocChitiet obj : listThuocData) {
			if( obj.THANHTOAN==1 && isAll == false){
				continue;
			}
			if (KIEU_THANH_TOAN == Utils.THANHTOAN_BHYT
					|| KIEU_THANH_TOAN == Utils.THANHTOAN_BHYT_2) {
				//
				if( obj.TYP==1 ){
					// THUOC BAO HIEM
					obj.MUC_HUONG = (float) txtMathe.iTyLeHuong;
					if(tyleHuongBH==1){
						obj.MUC_HUONG = (float)100;
					}
					obj.TYLE_TT = 100;
					obj.THANH_TIEN = obj.DON_GIA * obj.SO_LUONG;
					obj.TT_BHTT = (int) (obj.THANH_TIEN * tyleHuongBH);
					obj.TT_BNTT = obj.THANH_TIEN - obj.TT_BHTT;
					obj.MA_BENH = cbMaBenh.getText();
					//
					TONGCONG += obj.THANH_TIEN;
					TONGCONG_BHYT += obj.THANH_TIEN;
					TONGCONG_BH += obj.TT_BHTT;
					TONGCONG_NB += obj.THANH_TIEN - obj.TT_BHTT;
					//
					T_THUOC += obj.THANH_TIEN;
				}
				else{
					// THUOC VIEN PHI
					obj.MUC_HUONG = (float)0;
					obj.THANH_TIEN = obj.DON_GIA * obj.SO_LUONG;
					obj.TT_BHTT = 0;
					obj.TT_BNTT = obj.THANH_TIEN;
					obj.MA_BENH = cbMaBenh.getText();
					//
					TONGCONG += obj.THANH_TIEN;
					TONGCONG_BHYT += 0;
					TONGCONG_BH += 0;
					TONGCONG_NB += obj.THANH_TIEN;
					//
					T_THUOC += obj.THANH_TIEN;
				}
				//
			} else {
				obj.MUC_HUONG = (float) 0.0;
				obj.THANH_TIEN = obj.DON_GIA * obj.SO_LUONG;
				obj.TT_BHTT = 0;
				obj.TT_BNTT = obj.THANH_TIEN;
				//
				TONGCONG += obj.THANH_TIEN;
				TONGCONG_BHYT += obj.THANH_TIEN;
				TONGCONG_BH += 0;
				TONGCONG_NB += obj.THANH_TIEN;
				//
				//
				T_THUOC += obj.THANH_TIEN;
				//
			}
		}
		// UPDATE DICH VU
		for (DvChitiet obj : listCLSData) {
			if (objBenhNhan != null) {
				obj.BN_ID = objBenhNhan.BN_ID;
			}
			if (objPhieuKhamBenh != null) {
				obj.MA_LK = objPhieuKhamBenh.MA_LK;
			}
			if( obj.THANHTOAN==1 && isAll == false){
				continue;
			}
			//
			if (KIEU_THANH_TOAN == Utils.THANHTOAN_BHYT
					|| KIEU_THANH_TOAN == Utils.THANHTOAN_BHYT_2
					) {
				// Update field
				// obj.TYLE_TT = txtMathe.iTyLeHuong;
				if(tyleHuongBH==1){
					obj.MUC_HUONG = 100;
				}
				obj.TYLE_TT = 100;
				obj.MA_PTTT = 0;
				obj.MA_KHOA = DbHelper.getMAKHOA(cbKhoa.getText());
				// obj.MA_BAC_SI = DbHelper.currentSessionUserId.MACCHN;
				obj.MA_BENH = cbMaBenh.getText();
				//
				if( obj.TYP==1 ){
				//if(objDvChitiet.TYP==1 && objDvChitiet.TYLE_TT!=0){

					// CLS bảo hiểm 
					obj.THANH_TIEN = obj.DON_GIA * obj.SO_LUONG; // BHTT
					obj.THANH_TIEN2 = obj.DON_GIA2 * obj.SO_LUONG; // THUC THU CHENH LECH
					if (obj.MUC_HUONG == 0) {
						// BN tu chi tra 100%
						obj.TT_BHTT = 0;
						obj.TT_BNTT = obj.THANH_TIEN - obj.TT_BHTT;
						TONGCONG_BHYT += 0;
					} else {
						obj.TT_BHTT = (int) (obj.DON_GIA * obj.SO_LUONG * tyleHuongBH);
						obj.TT_BNTT = obj.THANH_TIEN - obj.TT_BHTT;
						TONGCONG_BHYT += obj.THANH_TIEN;
					}
					//
					//
					TONGCONG += obj.THANH_TIEN2;
					TONGCONG_BH += obj.TT_BHTT;
					TONGCONG_NB += obj.THANH_TIEN2 - obj.TT_BHTT;
				}
				else{
					// CLS viện phí
					obj.THANH_TIEN = obj.DON_GIA2 * obj.SO_LUONG; // BHTT
					obj.THANH_TIEN2 = obj.DON_GIA2 * obj.SO_LUONG; // THUC THU CHENH LECH
					obj.MUC_HUONG = 0;
					obj.TT_BHTT = 0;
					obj.TT_BNTT = obj.THANH_TIEN2 - obj.TT_BHTT;
					TONGCONG_BHYT += 0;
					TONGCONG += obj.THANH_TIEN2;
					TONGCONG_BH += obj.TT_BHTT;
					TONGCONG_NB += obj.THANH_TIEN2;
				}
				//
				//
//			} 
//			else if (KIEU_THANH_TOAN == Utils.THANHTOAN_BHYT_2) {
//				// Update field
//				// obj.TYLE_TT = txtMathe.iTyLeHuong;
//				obj.MA_PTTT = 0;
//				obj.MA_KHOA = DbHelper.getMAKHOA(cbKhoa.getText());
//				// obj.MA_BAC_SI = DbHelper.currentSessionUserId.MACCHN;
//				obj.MA_BENH = cbMaBenh.getText();
//				//
//				obj.THANH_TIEN = obj.DON_GIA * obj.SO_LUONG; // BHTT
//				obj.THANH_TIEN2 = obj.DON_GIA2 * obj.SO_LUONG; // THUC THU CHENH
//																// LECH
//				if (obj.TYLE_TT == 0) {
//					// BN tu chi tra 100%
//					obj.T_BHTT = 0;
//					obj.T_BNTT = obj.THANH_TIEN - obj.T_BHTT;
//					TONGCONG_BHYT += 0;
//				} else {
//					obj.T_BHTT = (int) (obj.DON_GIA * obj.SO_LUONG * tyleHuongBH);
//					obj.T_BNTT = obj.THANH_TIEN - obj.T_BHTT;
//					TONGCONG_BHYT += obj.THANH_TIEN;
//				} //
//					//
//				TONGCONG += obj.THANH_TIEN2;
//				TONGCONG_BH += obj.T_BHTT;
//				TONGCONG_NB += obj.THANH_TIEN2 - obj.T_BHTT;
//				//
//				//
			} else if (KIEU_THANH_TOAN == Utils.THANHTOAN_VIENPHI) {
				// Update field
				obj.MUC_HUONG = 0;
				obj.MA_PTTT = 0;
				obj.MA_KHOA = DbHelper.getMAKHOA(cbKhoa.getText());
				// obj.MA_BAC_SI = DbHelper.currentSessionUserId.MACCHN;
				obj.MA_BENH = cbMaBenh.getText();
				//
				obj.THANH_TIEN = obj.DON_GIA * obj.SO_LUONG;
				obj.THANH_TIEN2 = obj.DON_GIA2 * obj.SO_LUONG;
				obj.TT_BHTT = 0;
				obj.TT_BNTT = obj.THANH_TIEN2;
				//
				TONGCONG += obj.THANH_TIEN2;
				TONGCONG_BHYT += 0;
				TONGCONG_BH += obj.TT_BHTT;
				TONGCONG_NB += obj.TT_BNTT;
				//
				//
			} else if (KIEU_THANH_TOAN == Utils.THANHTOAN_MUA_CLS) {
				// Update field
				obj.MUC_HUONG = 0;
				obj.MA_PTTT = 0;
				obj.MA_KHOA = DbHelper.getMAKHOA(cbKhoa.getText());
				// obj.MA_BAC_SI = DbHelper.currentSessionUserId.MACCHN;
				obj.MA_BENH = cbMaBenh.getText();
				//
				obj.THANH_TIEN = obj.DON_GIA2 * obj.SO_LUONG;
				obj.THANH_TIEN2 = obj.DON_GIA2 * obj.SO_LUONG;
				obj.TT_BHTT = 0;
				obj.TT_BNTT = obj.THANH_TIEN2;
				//
				TONGCONG += obj.THANH_TIEN2;
				TONGCONG_BHYT += 0;
				TONGCONG_BH += obj.TT_BHTT;
				TONGCONG_NB += obj.TT_BNTT;
				//
				//
			} else if (KIEU_THANH_TOAN == Utils.THANHTOAN_VIENPHI_FREE) {
				// Update field
				obj.MUC_HUONG = txtMathe.iTyLeHuong;
				obj.MA_PTTT = 0;
				obj.MA_KHOA = DbHelper.getMAKHOA(cbKhoa.getText());
				// obj.MA_BAC_SI = DbHelper.currentSessionUserId.MACCHN;
				obj.MA_BENH = cbMaBenh.getText();
				//
				obj.THANH_TIEN = obj.DON_GIA * obj.SO_LUONG;
				obj.THANH_TIEN2 = obj.DON_GIA2 * obj.SO_LUONG;
				obj.TT_BHTT = 0;
				obj.TT_BNTT = 0;
				//
				// 0914080456
				TONGCONG += obj.THANH_TIEN2;
				TONGCONG_BHYT += 0;
				TONGCONG_BH += obj.TT_BHTT;
				TONGCONG_NB += obj.TT_BNTT;
				//
				//
			} else if (KIEU_THANH_TOAN == Utils.THANHTOAN_TAI_KHAM) {
				// Update field
				obj.MUC_HUONG = txtMathe.iTyLeHuong;
				obj.MA_PTTT = 0;
				obj.MA_KHOA = DbHelper.getMAKHOA(cbKhoa.getText());
				// obj.MA_BAC_SI = DbHelper.currentSessionUserId.MACCHN;
				obj.MA_BENH = cbMaBenh.getText();
				//
				obj.THANH_TIEN = obj.DON_GIA * obj.SO_LUONG;
				obj.THANH_TIEN2 = obj.DON_GIA2 * obj.SO_LUONG;
				obj.TT_BHTT = 0;
				obj.TT_BNTT = obj.THANH_TIEN;
				//
				//
				TONGCONG += obj.THANH_TIEN2;
				TONGCONG_BHYT += 0;
				TONGCONG_BH += obj.TT_BHTT;
				TONGCONG_NB += obj.TT_BNTT;
				//
				//
			}
		}
		logger.info("Update tien TONGCONG:" + TONGCONG + " TONGCONG_BHYT:" + TONGCONG_BHYT);
	}

	//
	private void updateTinhTien() {
		updateTinhTien(true);
	}
	private void updateTinhTien(boolean isAll) {
		// logger.info("---------------------------- updateGiaTien ");
		// UPDATE CONG KHAM
		float tyleHuongBH = (float) txtMathe.iTyLeHuong / (float) 100;
		updateGiaTien(tyleHuongBH, isAll);
		// Check voi muc luong co so, neu tong chi phi <15% MUC LUONG CO SO, thi BHYT chi tra 100%
		if (tyleHuongBH < 1
				&& (TONGCONG_BHYT < (int) (Main.MAX_MUCLUONGCOSO * 0.15))) {
			if (CHI_XET_NGHIEM == 1
					|| KIEU_THANH_TOAN == Utils.THANHTOAN_VIENPHI) {

			} else {
				// Update cong kham, ky thuat dich vu...
				// UPDATE CONG KHAM
				if( isAll==true ){
					updateGiaTien(1, isAll);
					tableViewerSelectedCLS.refresh();
				}
			}
			//
		}
		//
		// UPDATE PHIEU KHAM BENH
		if (objPhieuKhamBenh != null) {
			if (KIEU_THANH_TOAN == Utils.THANHTOAN_BHYT
					|| KIEU_THANH_TOAN == Utils.THANHTOAN_BHYT_2) {
				objPhieuKhamBenh.MA_BENH = cbMaBenh.getText();
				objPhieuKhamBenh.MA_BENHKHAC = txtTenBenhPhu.getText();
				objPhieuKhamBenh.TEN_BENH = txtTenBenh.getText();
				objPhieuKhamBenh.SO_NGAY_DTRI = 1;
				objPhieuKhamBenh.MA_LYDO_VVIEN = 1;// Mã hóa đối tượng đến khám
													// BHYT (1 : Đúng tuyến; 2
													// : Cấp cứu; 3 : Trái
													// tuyến)
				if (objPhieuKhamBenh.NGAY_VAO.length() == 0) {
					objPhieuKhamBenh.NGAY_VAO = Utils.getDatetime(new Date(), "yyyyMMddHHmm");
				}
				objPhieuKhamBenh.NGAY_RA = Utils.getDatetime(new Date(), "yyyyMMddHHmm");
				//
				objPhieuKhamBenh.KET_QUA_DTRI = 2;
				objPhieuKhamBenh.TINH_TRANG_RV = 1;
				objPhieuKhamBenh.MUC_HUONG = txtMathe.iTyLeHuong;
				objPhieuKhamBenh.MA_LOAI_KCB = 1; // Mã hóa hình thức KCB (1:
													// Khám bệnh; 2: Điều trị
													// ngoại trú; 3: Điều trị
													// nội trú)
				objPhieuKhamBenh.MA_CSKCB = "49172";
				objPhieuKhamBenh.MA_KHUVUC = "20";
				objPhieuKhamBenh.MA_PTTT_QT = "";
				objPhieuKhamBenh.CAN_NANG = Utils.getDouble(txtCanNang.getText());
				objPhieuKhamBenh.NAM_QT = new Date().getYear() + 1900;
				objPhieuKhamBenh.THANG_QT = new Date().getMonth() + 1;
				objPhieuKhamBenh.MA_NOI_CHUYEN = "";
				//
				//
//				if (btnTHANHTOAN.getSelection() == true) {
//					objPhieuKhamBenh.NGAY_TTOAN = Utils.getDatetime(new Date(), "yyyyMMddHHmm");
//				} else {
//					objPhieuKhamBenh.NGAY_TTOAN = "Chưa";
//				}
				objPhieuKhamBenh.T_THUOC = T_THUOC;
				objPhieuKhamBenh.T_VTYT = 0;
				objPhieuKhamBenh.T_BHTT = TONGCONG_BH;
				objPhieuKhamBenh.T_BNTT = TONGCONG_NB;
				objPhieuKhamBenh.T_NGOAIDS = 0;
				objPhieuKhamBenh.T_TONGCHI = TONGCONG;
				objPhieuKhamBenh.T_NGUONKHAC = 0;
				objPhieuKhamBenh.MA_KHOA = DbHelper.getMAKHOA(cbKhoa.getText());
				// logger.info("Update hieu kham1 " + TONGCONG);
			} else {
				//
				objPhieuKhamBenh.MA_BENH = cbMaBenh.getText();
				objPhieuKhamBenh.MA_BENHKHAC = txtTenBenhPhu.getText();
				objPhieuKhamBenh.TEN_BENH = txtTenBenh.getText();
				objPhieuKhamBenh.MA_LYDO_VVIEN = 1;// Mã hóa đối tượng đến khám
													// BHYT (1 : Đúng tuyến; 2
													// : Cấp cứu; 3 : Trái
													// tuyến)
				objPhieuKhamBenh.SO_NGAY_DTRI = 1;
				objPhieuKhamBenh.NGAY_VAO = Utils.getDatetime(new Date(),
						"yyyyMMddHHmm");
				objPhieuKhamBenh.NGAY_RA = Utils.getDatetime(new Date(),
						"yyyyMMddHHmm");
				objPhieuKhamBenh.KET_QUA_DTRI = 2;
				objPhieuKhamBenh.TINH_TRANG_RV = 1;
				objPhieuKhamBenh.MUC_HUONG = txtMathe.iTyLeHuong;
				objPhieuKhamBenh.MA_LOAI_KCB = 1; // Mã hóa hình thức KCB (1:
													// Khám bệnh; 2: Điều trị
													// ngoại trú; 3: Điều trị
													// nội trú)
				objPhieuKhamBenh.MA_CSKCB = "49172";
				objPhieuKhamBenh.MA_KHUVUC = "";
				objPhieuKhamBenh.MA_PTTT_QT = "";
				objPhieuKhamBenh.CAN_NANG = Utils.getDouble(txtCanNang
						.getText());
				objPhieuKhamBenh.NAM_QT = new Date().getYear() + 1900;
				// new Date().getYear() + 1970 = 2087 
				//                117            2017
				objPhieuKhamBenh.THANG_QT = new Date().getMonth() + 1;
				objPhieuKhamBenh.MA_NOI_CHUYEN = "";
				//
//				if (btnTHANHTOAN.getSelection() == true) {
//					objPhieuKhamBenh.NGAY_TTOAN = Utils.getDatetime(new Date(),
//							"yyyyMMddHHmm");
//				} else {
//					objPhieuKhamBenh.NGAY_TTOAN = "Chưa";
//				}
				objPhieuKhamBenh.MUC_HUONG = 0;
				objPhieuKhamBenh.T_THUOC = T_THUOC;
				objPhieuKhamBenh.T_VTYT = 0;
				objPhieuKhamBenh.T_BHTT = 0;
				objPhieuKhamBenh.T_BNTT = TONGCONG;
				objPhieuKhamBenh.T_NGOAIDS = 0;
				objPhieuKhamBenh.T_TONGCHI = TONGCONG;
				objPhieuKhamBenh.T_NGUONKHAC = 0;
				objPhieuKhamBenh.MA_KHOA = DbHelper.getMAKHOA(cbKhoa.getText());
				// logger.info("Update hieu kham " + TONGCONG);
			}
			// CAP CUU
			if (cbCapCuu.getSelectionIndex() > 0) {
				// Cấp cứu
				objPhieuKhamBenh.MA_LYDO_VVIEN = 2;// Mã hóa đối tượng đến khám
				// BHYT (1 : Đúng tuyến; 2
				// : Cấp cứu; 3 : Trái
				// tuyến)
				objPhieuKhamBenh.MA_TAI_NAN = cbCapCuu.getSelectionIndex();
				//
				logger.info("CAP CUU: " + cbCapCuu.getText());
			}
			//
		}
		txtTongCong.setText("Tổng="
				+ java.text.NumberFormat.getInstance(java.util.Locale.ITALY)
						.format(TONGCONG)
				+ ". BH: "
				+ java.text.NumberFormat.getInstance(java.util.Locale.ITALY)
						.format(TONGCONG_BH)
				+ " BHChi:"
				+ java.text.NumberFormat.getInstance(java.util.Locale.ITALY)
						.format(TONGCONG_BHYT)
				+ " Thu: "
				+ java.text.NumberFormat.getInstance(java.util.Locale.ITALY)
						.format(TONGCONG_NB));
		//
		tableViewerTHUOC.refresh();
		tableViewerSelectedCLS.refresh();
		//
		//
	}

	protected void reloadTableChitietNhapthuoc() {
		// if(listDataCtNhapthuoc==null){
		// Do search
		String searchString = txtSearchCtNhapthuoc.getText().toLowerCase()
				.trim();
		String sql = "select * from ct_nhapthuoc WHERE STS<> "
				+ DbHelper.DELETE_STATUS + " and SL_TONKHO>0 ";
		//
		if (searchString.length() > 0) {
			sql += " and ";
			sql += " LOWER(TENTHUOC) like '" + searchString + "%'";
		}
		//
		if(objKhoHang!=null){
			sql += " and KHO_ID="+objKhoHang.KHO_ID.intValue();
		}
		
		//
		sql += " order by HANDUNG ASC";
		try {
			logger.info(sql);
			Connection con = DbHelper.getSql2o();
			listDataCtNhapthuoc = con.createQuery(sql).executeAndFetch(CtNhapthuoc.class);
			if (listDataCtNhapthuoc != null || listDataCtNhapthuoc.size() > 0) {
				hashDataCtNhapthuoc.clear();
				for (CtNhapthuoc obj : listDataCtNhapthuoc) {
					hashDataCtNhapthuoc.put(obj.CT_ID, obj);
				}
			}
		} catch (Exception e) {
			logger.error(e);
			MessageDialog.openError(shellKhamBenh, "Error", e.getMessage());
		}
		// }
		//
		tableViewerCtNhapthuoc.setInput(listDataCtNhapthuoc);
		tableViewerCtNhapthuoc.refresh();
		//
		if (listDataCtNhapthuoc.size() == 0) {
			txtSearchCtNhapthuoc.forceFocus();
		} else {
			tableCtNhapthuoc.forceFocus();
			tableCtNhapthuoc.setSelection(0);
		}
	}

	protected boolean selectSoluongThuocAndAdd() {
		//
		if (tableCtNhapthuoc.getSelectionCount() == 0) {
			objCtNhapthuoc = null;
			return false;
		}
		TableItem item = tableCtNhapthuoc.getSelection()[0];
		objCtNhapthuoc = (CtNhapthuoc) item.getData();

		iSoLuongChiDinh = Utils.getInt(txtThuocSoLuongChiDinh.getText());
		if (iSoLuongChiDinh<=0 
				|| (iSoLuongChiDinh > 0 && objCtNhapthuoc.SL_TONKHO <= iSoLuongChiDinh)) {
			txtThuocSoLuongChiDinh.setBackground(SWTResourceManager .getColor(SWT.COLOR_RED));
			txtThuocSoLuongChiDinh.forceFocus();
			txtThuocSoLuongChiDinh.selectAll();
			return false;
		} else {
			txtThuocSoLuongChiDinh.setBackground(SWTResourceManager
					.getColor(SWT.COLOR_YELLOW));
		}
		if (cbLieuDung.getText().length()==0) {
			cbLieuDung.setBackground(SWTResourceManager .getColor(SWT.COLOR_RED));
			cbLieuDung.forceFocus();
			return false;
		} else {
			cbLieuDung.setBackground(SWTResourceManager.getColor(SWT.COLOR_YELLOW));
		}
		// ======================================================
		//
		// ======================================================
		ThuocChitiet objThuocChiTiet = new ThuocChitiet();
		objThuocChiTiet.THUOC_ID = objCtNhapthuoc.THUOC_ID;
		objThuocChiTiet.MA_BENH = cbMaBenh.getText();
		objThuocChiTiet.MUC_HUONG = (float) 100.0;
		if (objPhieuKhamBenh != null) {
			objThuocChiTiet.MA_LK = objPhieuKhamBenh.MA_LK;
		}
		objThuocChiTiet.SO_LUONG = 1;
		//
		String lieudung[] = cbLieuDung.getText().split("#");
		objThuocChiTiet.LIEU_DUNG = lieudung.length==2?lieudung[1]:cbLieuDung.getText();
		//
		if( DbHelper.hashDataMstLieuDung.get(objThuocChiTiet.LIEU_DUNG)!=null){
			DbHelper.hashDataMstLieuDung.get(objThuocChiTiet.LIEU_DUNG).RANK++;
			DbHelper.hashDataMstLieuDung.get(objThuocChiTiet.LIEU_DUNG).update();
		}
		//
		if (DbHelper.hashDataUsers.get(cbListBacsiThuoc.getText()) != null) {
			objThuocChiTiet.MA_BAC_SI = DbHelper.hashDataUsers
					.get(cbListBacsiThuoc.getText()).MACCHN;
		} else {
			objThuocChiTiet.MA_BAC_SI = "";
		}
		objThuocChiTiet.MA_KHOA = DbHelper.getMAKHOA(cbKhoa.getText());
		//
		if (objCtNhapthuoc != null) {
			if (objCtNhapthuoc.TENTHUOC != null
					&& objCtNhapthuoc.TENTHUOC.length() > 0) {
				Thuoc objThuoc = Thuoc.load(objCtNhapthuoc.THUOC_ID);
				if (objThuoc != null) {
					if( objThuoc.TENTHUOC_AX!=null && objThuoc.TENTHUOC_AX.length()>0 ){
						objThuocChiTiet.MA_THUOC = objThuoc.MA_AX;
						objThuocChiTiet.MA_NHOM = "" + objThuoc.MANHOM_9324;
						// THUOC BAO HIEM
						objThuocChiTiet.TEN_THUOC = objThuoc.TENTHUOC_AX;
						objThuocChiTiet.DON_VI_TINH = objThuoc.DON_VI_TINH;
						objThuocChiTiet.HAM_LUONG = objThuoc.HAMLUONG_AX;
						objThuocChiTiet.DUONG_DUNG = objThuoc.MA_DUONGDUNG_AX;
						objThuocChiTiet.SO_DANG_KY = objThuoc.SODANGKY_AX;
					}
					else{
						objThuocChiTiet.TEN_THUOC = objThuoc.TEN_THUOC;
						objThuocChiTiet.DON_VI_TINH = objThuoc.DON_VI_TINH;
						objThuocChiTiet.MA_NHOM = "" + objThuoc.NHOM_THAU;
						objThuocChiTiet.HAM_LUONG = "";
						objThuocChiTiet.DUONG_DUNG = "";
						objThuocChiTiet.SO_DANG_KY = "";
					}
				}
				objThuocChiTiet.KHO_NAME = objCtNhapthuoc.TENKHO;
				//
				objThuocChiTiet.TYP = objCtNhapthuoc.TYP;
				if(objThuocChiTiet.TYP==1){
					// THUOC VIEN PHI
					objThuocChiTiet.DON_GIA = objCtNhapthuoc.DONGIA;
				}
				else{
					objThuocChiTiet.DON_GIA = objCtNhapthuoc.DONGIA_BAN;
				}
				//
				objThuocChiTiet.THUOC_ID = objCtNhapthuoc.THUOC_ID;
				objThuocChiTiet.CT_ID = objCtNhapthuoc.CT_ID;
				objThuocChiTiet.NT_ID = objCtNhapthuoc.NT_ID;
				objThuocChiTiet.TT_THAU = objCtNhapthuoc.TT_THAU;
				objThuocChiTiet.SO_DANG_KY = objCtNhapthuoc.SO_DANG_KY;
				objThuocChiTiet.SO_LUONG = iSoLuongChiDinh;
				//
				objThuocChiTiet.STT = listThuocData.size() + 1;
				objThuocChiTiet.STS = -1;  // JuSY ADD 
				listThuocData.add(objThuocChiTiet);
				// hashThuocChiTiet.put(objThuocChiTiet.TEN_THUOC,objThuocChiTiet);
			}
			//
			updateTinhTien();
			tableViewerTHUOC.refresh();
		}
		// ======================================================
		//
		// ======================================================
		//
		return true;
	}

	private void reloadTableCtListCLS() {
		String searchText = txtSearchCLS.getText().trim().toLowerCase();
		try {
			Connection con = DbHelper.getSql2o();
			//
			String sql = "select * from dichvu where LOWER(MA_DVKT) like '%"
					+ searchText + "%' or LOWER(TEN_DVKT) like '%" + searchText
					+ "%' and MANHOM_9324<>'13' order by DV_RANK DESC";
			// System.out.println(sql);
			listDichVu = con.createQuery(sql).executeAndFetch(Dichvu.class);
			// hashDichVu.clear();
			// for (Dichvu obj: listDichVu) {
			// hashDichVu.put(obj.MA_DVKT, obj);
			// }
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		tableViewerCLS.setInput(listDichVu);
		tableViewerCLS.refresh();
		//
		if (listDichVu.size() == 0) {
			txtSearchCLS.forceFocus();
			txtSearchCLS.selectAll();
			return;
		}
		tableCLS.forceFocus();
		tableCLS.select(0);
	}

	private void saveAndAddCLS() {
		if (objPhieuKhamBenh == null) {
			// MessageDialog.openError(shellKhamBenh, "Có lỗi",
			// "Lưu form kham bệnh trước");
			// return;
		}
		if (tableCLS.getSelectionCount() == 0) {
			return;
		}
		TableItem item = tableCLS.getSelection()[0];
		Dichvu objDv = (Dichvu) item.getData();
		//
		if (objDv != null) {
			// Check is exist
			
			if (hashDichVuChiTiet.get(objDv.MA_DVKT) != null) {
				//
				logger.info(objPhieuKhamBenh);
				if(objPhieuKhamBenh!=null){
					if( objPhieuKhamBenh.MA_KHOA!=null && objPhieuKhamBenh.MA_KHOA.equals("K31")){
						// PHUC HOI CHUC NANG
					}
					else{
						// exist
						MessageDialog.openError(shellKhamBenh, "Có lỗi", "Đã có dịch vụ đó trong danh sách");
						return;
					}
				}
			}

			DvChitiet objDVChiTiet = new DvChitiet();
			// objDVChiTiet.BN_ID = objBenhNhan.BN_ID;
			// objDVChiTiet.MA_LK = objPhieuKhamBenh.MA_LK;
			objDVChiTiet.MA_DICH_VU = "";
			objDVChiTiet.TEN_DICH_VU = "";
			//
			objDVChiTiet.DV_ID = listCLSData.size() + 1;
			//
			objDVChiTiet.MA_DICH_VU = objDv.MA_DVKT;
			objDVChiTiet.TEN_DICH_VU = objDv.TEN_DVKT;
			if( objPhieuKhamBenh!=null && objPhieuKhamBenh.MA_KHOA!=null && objPhieuKhamBenh.MA_KHOA.equals("K31")){
				objDVChiTiet.TEN_DICH_VU = objDv.TEN_DVKT +"("+Utils.getDatetime(new Date(), "dd/MM")+ ")";
			}
			//
			objDVChiTiet.TYP = objDv.TYP;
			if(objDVChiTiet.TYP==1 ){
				objDVChiTiet.DON_GIA = objDv.DON_GIA;
				objDVChiTiet.DON_GIA2 = objDv.DON_GIA2;
				objDVChiTiet.MA_NHOM = Utils.getInt(objDv.MANHOM_9324);
			}
			else{
				objDVChiTiet.DON_GIA = objDv.DON_GIA2;
				objDVChiTiet.DON_GIA2 = objDv.DON_GIA2;
				objDVChiTiet.MA_NHOM = Utils.getInt(objDv.MANHOM_9324);
			}
			objDVChiTiet.NHOM_DV = objDv.NHOM_DV;
			objDVChiTiet.SO_LUONG = 1;
			objDVChiTiet.MA_VAT_TU = "";
			objDVChiTiet.MA_BENH = "";
			objDVChiTiet.MA_PTTT = 0;
			objDVChiTiet.NGAY_YL = Utils.getDatetime(new Date(), "yyyyMMddHHmm");
			objDVChiTiet.NGAY_KQ = Utils.getDatetime(new Date(), "yyyyMMddHHmm");
			objDVChiTiet.THANH_TIEN = objDv.DON_GIA2;
			if (btnVp.getSelection() == true) {
				// Tu Chi Tra
				objDVChiTiet.MUC_HUONG = 0;
			} else {
				objDVChiTiet.MUC_HUONG = txtMathe.iTyLeHuong;
			}
			objDVChiTiet.TT_BHTT = 0;
			objDVChiTiet.TT_BNTT = 0;
			//
			objDVChiTiet.MA_BAC_SI = "";
			objDVChiTiet.MA_KHOA = "";
			if (DbHelper.hashDataUsers.get(cbListBacSiCLS.getText().trim()) != null) {
				objDVChiTiet.MA_BAC_SI = DbHelper.hashDataUsers
						.get(cbListBacSiCLS.getText().trim()).MACCHN;
				objDVChiTiet.MA_KHOA = DbHelper.hashDataUsers
						.get(cbListBacSiCLS.getText().trim()).MA_KHOA;
			}
			//
			objDVChiTiet.MA_KHOA = DbHelper.getMAKHOA(cbKhoa.getText());
			objDVChiTiet.STS = -1;
			//
			if (objDVChiTiet.MA_DICH_VU != null
					&& objDVChiTiet.MA_DICH_VU.length() > 0) {
				listCLSData.add(objDVChiTiet);
				hashDichVuChiTiet.put(objDVChiTiet.MA_DICH_VU, objDVChiTiet);
			}
			//
			// System.out.println("Add list dv chi iet");
			//
			updateTinhTien();
			// ======================================================
			//
			// ======================================================
			tableViewerSelectedCLS.refresh();
			//
			txtSearchCLS.forceFocus();
			//
		}
	}

	public void updateSearchText() {
		tableViewerPhieuKham.refresh();
	}
}