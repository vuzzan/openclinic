package com.openclinic.nhapthuoc;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CBanner;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
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
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;
import org.sql2o.Connection;

import swing2swt.layout.BorderLayout;

import com.DbHelper;
import com.model.dao.ActionLog;
import com.model.dao.BenhNhan;
import com.model.dao.CtNhapthuoc;
import com.model.dao.KhamBenh;
import com.model.dao.Khohang;
import com.model.dao.NhapThuoc;
import com.model.dao.Thuoc;
import com.model.dao.ThuocChitiet;
import com.openclinic.Main;
import com.openclinic.khambenh.CellModifierThuocVienPhi;
import com.openclinic.khambenh.ContentProviderCtNhapthuoc;
import com.openclinic.khambenh.ContentProviderThuocChitiet;
import com.openclinic.khambenh.FormBanThuocDlg;
import com.openclinic.khambenh.ReportDAO;
import com.openclinic.khambenh.SumReportDAO;
import com.openclinic.khambenh.TableLabelProviderCtNhapthuoc;
import com.openclinic.khambenh.TableLabelProviderThuocChitiet;
import com.openclinic.utils.Utils;

import org.eclipse.swt.widgets.Combo;

public class FormXuatThuocKhoDlg extends Dialog {

	static Logger logger = LogManager.getLogger(FormBanThuocDlg.class
			.getName());
	protected Object result;
	protected Shell shell;

	
	private Table tableCtNhapThuocChuyenKho;
	private TableViewer tableViewerCtNhapThuocChuyenKho;
	private List<CtNhapthuoc> listDataCtNhapThuocChuyenKho = new ArrayList<CtNhapthuoc>();
	private String textSearchThuocChitietString;
	public ThuocChitiet objThuocChitiet = null;
	public int typeThuocChitietDlg = TYPE_DLG_CHOOSEN;
	public static final int TYPE_DLG_MANAGER = 0;
	public static final int TYPE_DLG_CHOOSEN = 1;
	private Text txtSearchCtNhapthuoc;
	private Text txtThuocSoLuongChiDinh;
	private Table tableCtNhapthuoc;
	private TableViewer tableViewerCtNhapthuoc;
	public int iSoLuongChiDinh = 0;
	private List<CtNhapthuoc> listDataCtNhapthuoc;
	private Hashtable<Integer, CtNhapthuoc> hashDataCtNhapthuoc = new Hashtable<Integer, CtNhapthuoc>();
	private CtNhapthuoc objCtNhapthuoc;
	private double TONG_TIEN;
	private Combo cbToKho;
	private Combo cbFromKho;
	public Khohang objKhoHangFrom;
	public Khohang objKhoHangTo;
	private NhapThuoc objNhapThuoc;
	private Button btnSavePhieu;

	/**
	 * Create the dialog.
	 * 
	 * @param parent
	 * @param style
	 */
	public FormXuatThuocKhoDlg(Shell parent, int style) {
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
		shell = new Shell(getParent(), SWT.SHELL_TRIM | SWT.BORDER
				| SWT.PRIMARY_MODAL);
		shell.setImage(SWTResourceManager.getImage(
				FormBanThuocDlg.class, "/png/list-2x.png"));
		shell.setSize(1289, 498);
		shell.setText("Xuat thuoc qua kho");
		shell.setLayout(new BorderLayout(0, 0));
		shell.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.keyCode == SWT.ESC) {
					objThuocChitiet = null;
				}
			}
		});

		Composite compositeInShellThuocChitiet = new Composite(shell, SWT.NONE);
		compositeInShellThuocChitiet.setLayout(new BorderLayout(0, 0));
		compositeInShellThuocChitiet.setLayoutData(BorderLayout.CENTER);

		Composite compositeHeaderThuocChitiet = new Composite(
				compositeInShellThuocChitiet, SWT.NONE);
		compositeHeaderThuocChitiet.setLayoutData(BorderLayout.NORTH);
		compositeHeaderThuocChitiet.setLayout(new GridLayout(2, false));

		Label lblHTn = new Label(compositeHeaderThuocChitiet, SWT.NONE);
		lblHTn.setText("Từ kho");
		lblHTn.setFont(SWTResourceManager.getFont("Tahoma", 12, SWT.NORMAL));
		lblHTn.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false,
				1, 1));
		
		cbFromKho = new Combo(compositeHeaderThuocChitiet, SWT.NONE);
		cbFromKho.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				objKhoHangFrom = DbHelper.hashDataKhoHang.get(cbFromKho.getText());
				if( objKhoHangFrom != null ){
					reloadTableChitietNhapthuoc();
				}
			}
		});
		cbFromKho.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label lblQuaKho = new Label(compositeHeaderThuocChitiet, SWT.NONE);
		lblQuaKho.setText("Qua Kho");
		lblQuaKho.setFont(SWTResourceManager.getFont("Tahoma", 12, SWT.NORMAL));
		lblQuaKho.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false,
				1, 1));
		
		cbToKho = new Combo(compositeHeaderThuocChitiet, SWT.NONE);
		cbToKho.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				objKhoHangTo = DbHelper.hashDataKhoHang.get(cbToKho.getText());
				if(objKhoHangTo!=null){
					if(objKhoHangTo.KHO_ID==objKhoHangFrom.KHO_ID){
						//
						
						MessageDialog.openError(shell, "Có lỗi", "Chọn kho xuất khác.");
						//
					}
				}
			}
		});
		cbToKho.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		SashForm sashForm = new SashForm(compositeInShellThuocChitiet,
				SWT.VERTICAL);
		sashForm.setLayoutData(BorderLayout.CENTER);

		Composite composite = new Composite(sashForm, SWT.NONE);
		composite.setLayout(new BorderLayout(0, 0));

		Composite composite_1 = new Composite(composite, SWT.NONE);
		composite_1.setLayoutData(BorderLayout.NORTH);
		composite_1.setLayout(new GridLayout(2, false));

		txtSearchCtNhapthuoc = new Text(composite_1, SWT.BORDER);
		txtSearchCtNhapthuoc.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.keyCode == 13) {
					reloadTableChitietNhapthuoc();
				}
				else{
					keyPress(e);
				}
			}
		});
		GridData gd_txtSearchCtNhapthuoc = new GridData(SWT.LEFT, SWT.CENTER,
				false, false, 1, 1);
		gd_txtSearchCtNhapthuoc.widthHint = 221;
		txtSearchCtNhapthuoc.setLayoutData(gd_txtSearchCtNhapthuoc);
		txtSearchCtNhapthuoc.setFont(SWTResourceManager.getFont("Tahoma", 12,
				SWT.NORMAL));
		txtSearchCtNhapthuoc.setBackground(SWTResourceManager
				.getColor(SWT.COLOR_GREEN));

		txtThuocSoLuongChiDinh = new Text(composite_1, SWT.BORDER | SWT.RIGHT);
		txtThuocSoLuongChiDinh.addKeyListener(new KeyAdapter() {
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
		GridData gd_txtThuocSoLuongChiDinh = new GridData(SWT.LEFT, SWT.CENTER,
				false, false, 1, 1);
		gd_txtThuocSoLuongChiDinh.widthHint = 95;
		txtThuocSoLuongChiDinh.setLayoutData(gd_txtThuocSoLuongChiDinh);
		txtThuocSoLuongChiDinh.setText("0");
		txtThuocSoLuongChiDinh.setFont(SWTResourceManager.getFont("Tahoma", 12,
				SWT.NORMAL));
		txtThuocSoLuongChiDinh.setBackground(SWTResourceManager
				.getColor(SWT.COLOR_GREEN));

		
		tableViewerCtNhapthuoc = new TableViewer(composite, SWT.BORDER
				| SWT.FULL_SELECTION);
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
					txtThuocSoLuongChiDinh.selectAll();
					txtThuocSoLuongChiDinh.forceFocus();
				}
				else{
					keyPress(e);
				}
			}
		});
		
		tableCtNhapthuoc.setLinesVisible(true);
		tableCtNhapthuoc.setHeaderVisible(true);
		tableCtNhapthuoc.setBackground(SWTResourceManager
				.getColor(SWT.COLOR_GREEN));

		TableColumn tableColumn = new TableColumn(tableCtNhapthuoc, SWT.LEFT);
		tableColumn.setWidth(96);
		tableColumn.setText("KHO");

		TableColumn tableColumn_1 = new TableColumn(tableCtNhapthuoc, SWT.LEFT);
		tableColumn_1.setWidth(182);
		tableColumn_1.setText("TÊN THUỐC");

		TableColumn tableColumn_2 = new TableColumn(tableCtNhapthuoc, SWT.NONE);
		tableColumn_2.setWidth(72);
		tableColumn_2.setText("Hàm Lượng");

		TableColumn tableColumn_3 = new TableColumn(tableCtNhapthuoc, SWT.LEFT);
		tableColumn_3.setWidth(80);
		tableColumn_3.setText("ĐV TÍNH");

		TableColumn tableColumn_4 = new TableColumn(tableCtNhapthuoc, SWT.NONE);
		tableColumn_4.setWidth(108);
		tableColumn_4.setText("HẠN DÙNG");

		TableColumn tableColumn_5 = new TableColumn(tableCtNhapthuoc, SWT.RIGHT);
		tableColumn_5.setWidth(73);
		tableColumn_5.setText("ĐƠN GIÁ");

		TableColumn tableColumn_6 = new TableColumn(tableCtNhapthuoc, SWT.RIGHT);
		tableColumn_6.setWidth(90);
		tableColumn_6.setToolTipText("Tồn kho, chỉ cấp số lượng < số này");
		tableColumn_6.setText("TỒN KHO");

		TableColumn tableColumn_7 = new TableColumn(tableCtNhapthuoc, SWT.RIGHT);
		tableColumn_7.setWidth(76);
		tableColumn_7.setToolTipText("SL thuốc BS đã cho, đang đợi xuất kho");
		tableColumn_7.setText("SẼ CẤP");

		TableColumn tableColumn_8 = new TableColumn(tableCtNhapthuoc, SWT.RIGHT);
		tableColumn_8.setWidth(76);
		tableColumn_8.setToolTipText("SL thuốc đã cấp cho người bệnh");
		tableColumn_8.setText("ĐÃ CẤP");

		TableColumn tableColumn_9 = new TableColumn(tableCtNhapthuoc, SWT.RIGHT);
		tableColumn_9.setWidth(85);
		tableColumn_9.setToolTipText("SL nhập ban đầu");
		tableColumn_9.setText("SL NHẬP");

		tableViewerCtNhapthuoc
				.setLabelProvider(new TableLabelProviderCtNhapthuoc());
		tableViewerCtNhapthuoc
				.setContentProvider(new ContentProviderCtNhapthuoc());
		tableViewerCtNhapthuoc.setInput(listDataCtNhapthuoc);


		tableViewerCtNhapThuocChuyenKho = new TableViewer(sashForm, SWT.BORDER
				| SWT.FULL_SELECTION);
		tableCtNhapThuocChuyenKho = tableViewerCtNhapThuocChuyenKho.getTable();
		tableCtNhapThuocChuyenKho.setFont(SWTResourceManager.getFont("Tahoma", 9,
				SWT.NORMAL));
		tableCtNhapThuocChuyenKho.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.keyCode == SWT.F5) {
					reloadTableThuocChitiet();
				} else if (e.keyCode == SWT.F7) {
				}
				else{
					keyPress(e);
				}
			}
		});
		tableCtNhapThuocChuyenKho.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				// selectTableThuocChitiet();
			}
		});

		tableCtNhapThuocChuyenKho.setLinesVisible(true);
		tableCtNhapThuocChuyenKho.setHeaderVisible(true);

		TableColumn tbTableColumnThuocChitietSTT = new TableColumn(
				tableCtNhapThuocChuyenKho, SWT.RIGHT);
		tbTableColumnThuocChitietSTT.setWidth(43);
		tbTableColumnThuocChitietSTT.setText("STT");

		TableColumn tbTableColumnThuocChitietKHO_NAME = new TableColumn(
				tableCtNhapThuocChuyenKho, SWT.LEFT);
		tbTableColumnThuocChitietKHO_NAME.setWidth(90);
		tbTableColumnThuocChitietKHO_NAME.setText("TÊN KHO");

		TableColumn tblclmnLHng = new TableColumn(tableCtNhapThuocChuyenKho, SWT.LEFT);
		tblclmnLHng.setWidth(77);
		tblclmnLHng.setText("LÔ HÀNG");

		TableColumn tbTableColumnThuocChitietTEN_THUOC = new TableColumn(
				tableCtNhapThuocChuyenKho, SWT.LEFT);
		tbTableColumnThuocChitietTEN_THUOC.setWidth(162);
		tbTableColumnThuocChitietTEN_THUOC.setText("TEN_THUOC");

		TableColumn tbTableColumnThuocChitietDON_VI_TINH = new TableColumn(
				tableCtNhapThuocChuyenKho, SWT.LEFT);
		tbTableColumnThuocChitietDON_VI_TINH.setWidth(39);
		tbTableColumnThuocChitietDON_VI_TINH.setText("DV");

		TableColumn tbTableColumnThuocChitietSOLUONG = new TableColumn(
				tableCtNhapThuocChuyenKho, SWT.RIGHT);
		tbTableColumnThuocChitietSOLUONG.setWidth(61);
		tbTableColumnThuocChitietSOLUONG.setText("SOLUONG");

		TableColumn tbTableColumnThuocChitietDON_GIA = new TableColumn(
				tableCtNhapThuocChuyenKho, SWT.RIGHT);
		tbTableColumnThuocChitietDON_GIA.setWidth(71);
		tbTableColumnThuocChitietDON_GIA.setText("DON_GIA");

		TableColumn tbTableColumnThuocChitietTHANH_TIEN = new TableColumn(
				tableCtNhapThuocChuyenKho, SWT.RIGHT);
		tbTableColumnThuocChitietTHANH_TIEN.setWidth(85);
		tbTableColumnThuocChitietTHANH_TIEN.setText("THANH_TIEN");

		TableColumn tbTableColumnThuocChitietSTS = new TableColumn(
				tableCtNhapThuocChuyenKho, SWT.RIGHT);
		tbTableColumnThuocChitietSTS.setWidth(59);
		tbTableColumnThuocChitietSTS.setText("STS");

		TableColumn tbTableColumnThuocChitietHOATCHAT = new TableColumn(
				tableCtNhapThuocChuyenKho, SWT.LEFT);
		tbTableColumnThuocChitietHOATCHAT.setWidth(100);
		tbTableColumnThuocChitietHOATCHAT.setText("HOATCHAT");

		TableColumn tbTableColumnThuocChitietHAMLUONG = new TableColumn(
				tableCtNhapThuocChuyenKho, SWT.LEFT);
		tbTableColumnThuocChitietHAMLUONG.setWidth(100);
		tbTableColumnThuocChitietHAMLUONG.setText("HAMLUONG");

		TableColumn tbTableColumnThuocChitietDONGGOI = new TableColumn(
				tableCtNhapThuocChuyenKho, SWT.LEFT);
		tbTableColumnThuocChitietDONGGOI.setWidth(100);
		tbTableColumnThuocChitietDONGGOI.setText("DONGGOI");

		Menu menu = new Menu(tableCtNhapThuocChuyenKho);
		tableCtNhapThuocChuyenKho.setMenu(menu);

		MenuItem mntmNewItem = new MenuItem(menu, SWT.NONE);
		mntmNewItem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		mntmNewItem.setText("Xem Chi tiết Lô Thuốc");

		tableViewerCtNhapThuocChuyenKho
				.setLabelProvider(new TableLabelProviderCtNhapthuoc());
		tableViewerCtNhapThuocChuyenKho
				.setContentProvider(new ContentProviderCtNhapthuoc());
//		tableViewerCtNhapThuocChuyenKho.setCellModifier(new CellModifierThuocVienPhi(
//				tableViewerCtNhapThuocChuyenKho));
		sashForm.setWeights(new int[] { 162, 243 });
		tableViewerCtNhapThuocChuyenKho.setInput(listDataCtNhapThuocChuyenKho);
		//
		Composite compositeButtonGroup = new Composite(shell, SWT.NONE);
		compositeButtonGroup.setLayoutData(BorderLayout.SOUTH);
		compositeButtonGroup.setLayout(new GridLayout(2, false));

		btnSavePhieu = new Button(compositeButtonGroup, SWT.NONE);
		btnSavePhieu.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				savePhieu();
			}
		});
		btnSavePhieu.setToolTipText("Lưu phiếu, bấm F2");
		btnSavePhieu.setText("Lưu Phiếu (F2)");
		btnSavePhieu.setImage(SWTResourceManager.getImage(
				FormBanThuocDlg.class, "/png/check-3x.png"));
		btnSavePhieu.setFont(SWTResourceManager.getFont("Tahoma", 12, SWT.NORMAL));
		btnSavePhieu.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPress(e);
			}
		});		
		Button btnInPhieu = new Button(compositeButtonGroup, SWT.NONE);
		btnInPhieu.setToolTipText("In Phiếu, bấm F9");
		btnInPhieu.setText("In Phiếu (F9)");
		btnInPhieu.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				printPhieu();
			}
		});
		btnInPhieu.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPress(e);
			}
		});		
		btnInPhieu.setImage(SWTResourceManager.getImage(
				FormBanThuocDlg.class, "/png/print-3x.png"));
		btnInPhieu.setFont(SWTResourceManager.getFont("Tahoma", 12, SWT.NORMAL));
		//
		//
		reloadTableChitietNhapthuoc();
		reloadTableThuocChitiet();
		startDlg();
	}

	private void startDlg() {
		int khoFromSelect = 0;
		int khoToSelect = 0;
		int index = 0;
		for(Khohang obj: DbHelper.listDataKhohang){
			cbFromKho.add(obj.KHO_NAME);
			cbToKho.add(obj.KHO_NAME);
			if(objNhapThuoc!=null && objNhapThuoc.FROM_KHOID!=null && objNhapThuoc.FROM_KHOID>0){
				// Edit
				if(objNhapThuoc.FROM_KHOID==obj.KHO_ID){
					khoFromSelect = index;
				}
				if(objNhapThuoc.KHO_ID==obj.KHO_ID){
					khoToSelect = index;
				}
				//
			}
			index++;
		}
		//
		cbFromKho.select(khoFromSelect);
		cbToKho.select(khoToSelect);
		//
		if(khoToSelect>0 || khoFromSelect>0){
			btnSavePhieu.setEnabled(false);
		}
		objKhoHangTo = DbHelper.hashDataKhoHang.get(cbToKho.getText());
		objKhoHangFrom = DbHelper.hashDataKhoHang.get(cbFromKho.getText());
		//
	}

	protected boolean selectSoluongThuocAndAdd() {
		//
		if(objKhoHangTo!=null){
			if(objKhoHangTo.KHO_ID==objKhoHangFrom.KHO_ID){
				//
				
				MessageDialog.openError(shell, "Có lỗi", "Chọn kho xuất khác.");
				return false;
				//
			}
		}
		if (tableCtNhapthuoc.getSelectionCount() == 0) {
			objCtNhapthuoc = null;
			return false;
		}
		TableItem item = tableCtNhapthuoc.getSelection()[0];
		objCtNhapthuoc = (CtNhapthuoc) item.getData();
		//
		iSoLuongChiDinh = Utils.getInt(txtThuocSoLuongChiDinh.getText());
		if (iSoLuongChiDinh<=0 
				|| (iSoLuongChiDinh > 0 && objCtNhapthuoc.SL_TONKHO < iSoLuongChiDinh)) {
			txtThuocSoLuongChiDinh.setBackground(SWTResourceManager .getColor(SWT.COLOR_RED));
			txtThuocSoLuongChiDinh.forceFocus();
			txtThuocSoLuongChiDinh.selectAll();
			return false;
		} else {
			txtThuocSoLuongChiDinh.setBackground(SWTResourceManager
					.getColor(SWT.COLOR_YELLOW));
		}
		// ======================================================
		//
		// ======================================================
		//
		//
		if (objCtNhapthuoc != null) {
			if (objCtNhapthuoc.TENTHUOC != null
					&& objCtNhapthuoc.TENTHUOC.length() > 0) {
			
				CtNhapthuoc objCtNhapThuocChuyenKho = new CtNhapthuoc();
				objCtNhapThuocChuyenKho.copy(objCtNhapthuoc);
				objCtNhapThuocChuyenKho.SOLUONG = iSoLuongChiDinh;
				objCtNhapThuocChuyenKho.THANHTIEN = iSoLuongChiDinh * objCtNhapThuocChuyenKho.DONGIA;
				objCtNhapThuocChuyenKho.SL_TONKHO = iSoLuongChiDinh;
				objCtNhapThuocChuyenKho.SL_DADUNG = 0;
				objCtNhapThuocChuyenKho.CTID_FROM = objCtNhapthuoc.CT_ID;
				objCtNhapThuocChuyenKho.V_ID = 0;
				objCtNhapThuocChuyenKho.FROM_KHOID = objKhoHangFrom.KHO_ID;
				objCtNhapThuocChuyenKho.KHO_ID = objKhoHangTo.KHO_ID;
				objCtNhapThuocChuyenKho.TENKHO = objKhoHangTo.KHO_NAME;
				objCtNhapThuocChuyenKho.STS = -1;
				listDataCtNhapThuocChuyenKho.add(objCtNhapThuocChuyenKho);
			}
			//
			tableViewerCtNhapThuocChuyenKho.refresh();
		}
		// ======================================================
		//
		// ======================================================
		//
		return true;
	}

	protected void keyPress(KeyEvent e) {
		if(e.keyCode==SWT.F2){
			savePhieu();
		}
		else if(e.keyCode==SWT.F9){
			printPhieu();
		}
		else if(e.keyCode==SWT.ESC){
			shell.close();
		}
		
	}

	public void setDataThuocChitiet(String textSearchString) {
		this.textSearchThuocChitietString = textSearchString;
	}

	protected void reloadTableThuocChitiet() {
		if(objNhapThuoc==null || (objNhapThuoc!=null && (objNhapThuoc.NT_ID==null || objNhapThuoc.NT_ID==0)) ){
			tableViewerCtNhapThuocChuyenKho.setInput(listDataCtNhapThuocChuyenKho);
			tableViewerCtNhapThuocChuyenKho.refresh();
			return;
		}
		String sql = "select * from ct_nhapthuoc WHERE NT_ID="+objNhapThuoc.NT_ID;
		try {
			logger.info(sql);
			Connection con = DbHelper.getSql2o();
			listDataCtNhapThuocChuyenKho = con.createQuery(sql).executeAndFetch(CtNhapthuoc.class);
		} catch (Exception e) {
			logger.error(e);
			MessageDialog.openError(shell, "Error", e.getMessage());
		}
		//
		tableViewerCtNhapThuocChuyenKho.setInput(listDataCtNhapThuocChuyenKho);
		tableViewerCtNhapThuocChuyenKho.refresh();
		//
		if (listDataCtNhapThuocChuyenKho.size() == 0) {
			//
		} else {
			tableCtNhapThuocChuyenKho.forceFocus();
			tableCtNhapThuocChuyenKho.setSelection(0);
		}
	}

	protected void reloadTableChitietNhapthuoc() {
		//if(listDataCtNhapthuoc==null){
		// Do search
		String searchString = txtSearchCtNhapthuoc.getText().toLowerCase().trim();
		String sql = "select * from ct_nhapthuoc WHERE STS<> "
				+ DbHelper.DELETE_STATUS + " and SL_TONKHO>0 ";
		//
		if (searchString.length() > 0) {
			sql += " and ";
			sql += " LOWER(TENTHUOC) like '" + searchString + "%'";
		}
		//
		if(objKhoHangFrom!=null){
			sql += " and KHO_ID="+objKhoHangFrom.KHO_ID;
		}
		//
		sql += " order by HANDUNG ASC";
		try {
			logger.info(sql);
			Connection con = DbHelper.getSql2o();
			listDataCtNhapthuoc = con.createQuery(sql).executeAndFetch(
					CtNhapthuoc.class);
			if (listDataCtNhapthuoc != null || listDataCtNhapthuoc.size() > 0) {
				hashDataCtNhapthuoc.clear();
				for (CtNhapthuoc obj : listDataCtNhapthuoc) {
					hashDataCtNhapthuoc.put(obj.CT_ID, obj);
				}
			}
		} catch (Exception e) {
			logger.error(e);
			MessageDialog.openError(shell, "Error", e.getMessage());
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
	protected void printPhieu() {
		java.sql.Connection con = null;
		try {
			String reportName = "PhieuXuatKho.jasper";
			JasperReport jr = (JasperReport) JRLoader.loadObject(this.getClass().getResource("/" + reportName));
			// logger.info("JasperCompileManager " + jr);
			//
			ArrayList<CtNhapthuoc> thuoc = new ArrayList<>();
			int i=1;
			for( CtNhapthuoc obj: listDataCtNhapThuocChuyenKho){
				obj.STT = i++;
				thuoc.add(obj);
			}
			JRBeanCollectionDataSource report_thuoc = new JRBeanCollectionDataSource( thuoc);
			Map<String, Object> params = new HashMap<String, Object>();

			SumReportDAO sumCongkham = new SumReportDAO();
			sumCongkham.TT = objNhapThuoc.TONGCONG;
			sumCongkham.TT2 = objNhapThuoc.TONGCONG_VAT;
			
			params.put("TONGCONG_SUM", sumCongkham);
			params.put("NT", objNhapThuoc);
			params.put("FROMKHO", cbFromKho.getText());
			params.put("TOKHO", objNhapThuoc.TENKHO);
			params.put("THUOC_DataSource", report_thuoc);
			params.put("NGAY_GIO", Utils.getDatetimeLocal("Duy Xuyên", new Date()));
			params.put("NGUOI_LAP", DbHelper.currentSessionUserId.TEN_NHANVIEN);
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
	protected void savePhieu() {
		if(objKhoHangTo!=null){
			if(objKhoHangTo.KHO_ID==objKhoHangFrom.KHO_ID){
				//
				
				MessageDialog.openError(shell, "Có lỗi", "Chọn kho xuất khác.");
				return;
				//
			}
		}
		if(btnSavePhieu.getEnabled()==false){
			MessageDialog.openError(shell, "Có lỗi", "Không cho phép sửa");
			return;
		}
		if(objNhapThuoc!=null){
			TONG_TIEN = 0.0;
			double TONG_TIEN_VAT = (float)0.0;
			for (CtNhapthuoc obj : listDataCtNhapThuocChuyenKho) {
				TONG_TIEN += obj.THANHTIEN;
				TONG_TIEN_VAT += (obj.THANHTIEN * (((double)100+(double)obj.VAT)/(double)100));
			}			
			//
			objNhapThuoc.FROM_KHOID = objKhoHangFrom.KHO_ID;
			objNhapThuoc.KHO_ID = objKhoHangTo.KHO_ID;
			objNhapThuoc.TENKHO = objKhoHangTo.KHO_NAME;
			objNhapThuoc.KH_HOA_DON = "";
			objNhapThuoc.SO_HOA_DON = "";
			objNhapThuoc.VAT = 0;
			objNhapThuoc.V_ID = 0;
			objNhapThuoc.VENDOR_NAME = "Chuyển từ ["+objKhoHangFrom.KHO_NAME+"] sang ["+objKhoHangTo.KHO_NAME+"]";
			objNhapThuoc.VENDOR_ADDR = "";
			objNhapThuoc.TONGCONG=TONG_TIEN;
			objNhapThuoc.TONGCONG_VAT=TONG_TIEN_VAT;
			objNhapThuoc.insert();
			//
			for (CtNhapthuoc obj : listDataCtNhapThuocChuyenKho) {
				obj.NT_ID = objNhapThuoc.NT_ID;
				boolean isFirstTimeInsert = false;
				if (obj.CT_ID == null) {
					// First time insert
					isFirstTimeInsert = true;
					//
				}
				//
				if (obj.STS == -1) {
					obj.STS = 0;
				}
				// Insert thuoc chi tiet.
				obj.insert();
				// Update kho thuoc ....
				//
				if( isFirstTimeInsert==true){
					CtNhapthuoc objCtNhapthuoc = CtNhapthuoc.load(obj.CTID_FROM);
					if(objCtNhapthuoc!=null){
						logger.info("==================BEGIN UPDATE KHO THUOC === ");
						// Update outstanding
						logger.info("==================    LOHANG_ID="
								+ obj.CT_ID + " TON KHO="
								+ objCtNhapthuoc.SL_TONKHO + " CTID_FROM="
								+ objCtNhapthuoc.CTID_FROM);
						// Tru truc tiep
						objCtNhapthuoc.SL_TONKHO = objCtNhapthuoc.SL_TONKHO - obj.SOLUONG;
						//
						logger.info("==================    LOHANG_ID="
								+ obj.CT_ID + " TON KHO="
								+ objCtNhapthuoc.SL_TONKHO + " CTID_FROM="
								+ objCtNhapthuoc.CTID_FROM);
						//
						objCtNhapthuoc.update();
						ActionLog log = new ActionLog();
						log.u_id = DbHelper.getCurrentSessionUserId();
						log.dbtable = "ct_nhapthuoc";
						log.fieldid = objCtNhapthuoc.CT_ID;
						log.actionid = 2;
						log.u_action = "Chuyển kho SL_TONKHO="+(objCtNhapthuoc.SL_TONKHO+obj.SOLUONG)+" to "+objCtNhapthuoc.SL_TONKHO;
						log.insert();
						//
						logger.info("==================END UPDATE KHO THUOC === ");
						//
					}
				}
			}
			reloadTableChitietNhapthuoc();
			//
			tableViewerCtNhapThuocChuyenKho.refresh();
			//
		}
		
	}

	public void setNhapThuocDlgData(NhapThuoc obj) {
		objNhapThuoc = obj;
	}

}
