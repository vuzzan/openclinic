package com.openclinic.khambenh;

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
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
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
import com.model.cache.MaCskcbCache;
import com.model.dao.ActionLog;
import com.model.dao.BenhNhan;
import com.model.dao.CtNhapthuoc;
import com.model.dao.DvChitiet;
import com.model.dao.KhamBenh;
import com.model.dao.KhoaPhong;
import com.model.dao.NhapThuoc;
import com.model.dao.Thuoc;
import com.model.dao.ThuocChitiet;
import com.model.dao.Users;
import com.openclinic.Main;
import com.openclinic.nhapthuoc.FormNhapThuocDlg;
import com.openclinic.utils.Utils;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;

public class FormBanThuocDlg extends Dialog {
	static Logger logger = LogManager.getLogger(FormBanThuocDlg.class.getName());
	protected Object result;
	protected Shell shell;

	private Table tableThuocChitiet;
	private TableViewer tableViewerThuocChitiet;
	private List<ThuocChitiet> listDataThuocChitiet = new ArrayList<ThuocChitiet>();
	public int KHO_ID = 3;
	private String textSearchThuocChitietString;
	public ThuocChitiet objThuocChitiet = null;
	public int typeThuocChitietDlg = TYPE_DLG_CHOOSEN;
	public static final int TYPE_DLG_MANAGER = 0;
	public static final int TYPE_DLG_CHOOSEN = 1;
	private Text txtDIACHI;
	private Text txtHOTEN;
	private KhamBenh objKhambenh;
	//private BenhNhan objBenhNhan;
	private Text txtSearchCtNhapthuoc;
	private Text txtThuocSoLuongChiDinh;
	private Table tableCtNhapthuoc;
	private TableViewer tableViewerCtNhapthuoc;
	public int iSoLuongChiDinh = 0;
	private List<CtNhapthuoc> listDataCtNhapthuoc;
	private Hashtable<Integer, CtNhapthuoc> hashDataCtNhapthuoc = new Hashtable<Integer, CtNhapthuoc>();
	private CtNhapthuoc objCtNhapthuoc;
	private double TONG_TIEN_THUOC_VP;
	private double TONG_TIEN_THUOC_BH;
	private Button btnThanhToan;
	private Button btnSavePhieu;
	private Button btnInPhieu;

	/**
	 * Create the dialog.
	 * 
	 * @param parent
	 * @param style
	 */
	public FormBanThuocDlg(Shell parent, int style) {
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
		shell = new Shell(getParent(), SWT.SHELL_TRIM | SWT.BORDER | SWT.PRIMARY_MODAL);
		shell.setImage(SWTResourceManager.getImage(FormBanThuocDlg.class, "/png/list-2x.png"));
		shell.setSize(1289, 498);
		shell.setText("ThuocChitiet List View");
		shell.setLayout(new BorderLayout(0, 0));
		shell.addDisposeListener(new DisposeListener() {
			public void widgetDisposed(DisposeEvent e) {
				boolean isThanhToan = true;
				for(ThuocChitiet obj: listDataThuocChitiet){
					if(obj.THANHTOAN==0){
						isThanhToan = false;
						break;
					}
				}
				System.out.println("THANH TOAN  = " + isThanhToan);
				if(isThanhToan==false){
					if( MessageDialog.openConfirm(shell, "Xác nhận", "Có muốn đánh dấu phiếu đã thanh toán không???") == true ){
						thanhtoanDonThuoc();
					}
					else{
						
					}
				}
				//
			}
		});
		shell.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.keyCode == SWT.ESC) 
				{
					objThuocChitiet = null;
					//
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
		lblHTn.setText("Họ Tên");
		lblHTn.setFont(SWTResourceManager.getFont("Tahoma", 12, SWT.NORMAL));
		lblHTn.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false,
				1, 1));

		txtHOTEN = new Text(compositeHeaderThuocChitiet, SWT.BORDER);
		txtHOTEN.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				txtHOTEN.setText(Utils.doCapitalizeFirstLetterInString(txtHOTEN.getText()));
			}
		});
		
		txtHOTEN.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPress(e);
			}
		});
		txtHOTEN.setFont(SWTResourceManager.getFont("Tahoma", 14, SWT.NORMAL));
		txtHOTEN.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false,
				1, 1));

		Label label_3 = new Label(compositeHeaderThuocChitiet, SWT.NONE);
		label_3.setText("Địa Chỉ");
		label_3.setFont(SWTResourceManager.getFont("Tahoma", 12, SWT.NORMAL));
		label_3.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false,
				1, 1));

		txtDIACHI = new Text(compositeHeaderThuocChitiet, SWT.BORDER);
		txtDIACHI.setFont(SWTResourceManager.getFont("Tahoma", 14, SWT.NORMAL));
		txtDIACHI.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false,
				1, 1));
		txtDIACHI.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPress(e);
			}
		});
		txtDIACHI.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				txtDIACHI.setText(Utils.doCapitalizeFirstLetterInString(txtDIACHI.getText()));
			}

			@Override
			public void focusGained(FocusEvent e) {
				txtDIACHI.selectAll();
			}

		});
		
		SashForm sashForm = new SashForm(compositeInShellThuocChitiet,SWT.VERTICAL);
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
		txtThuocSoLuongChiDinh.setFont(SWTResourceManager.getFont("Tahoma", 15, SWT.NORMAL));
		txtThuocSoLuongChiDinh.setBackground(SWTResourceManager
				.getColor(SWT.COLOR_GREEN));

		
		tableViewerCtNhapthuoc = new TableViewer(composite, SWT.BORDER | SWT.FULL_SELECTION);
		tableCtNhapthuoc = tableViewerCtNhapthuoc.getTable();
		tableCtNhapthuoc.setFont(SWTResourceManager.getFont("Tahoma", 11, SWT.NORMAL));
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
		tableColumn_1.setWidth(262);
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
		tableColumn_5.setWidth(88);
		tableColumn_5.setText("ĐƠN GIÁ");

		TableColumn tableColumn_6 = new TableColumn(tableCtNhapthuoc, SWT.RIGHT);
		tableColumn_6.setWidth(98);
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


		tableViewerThuocChitiet = new TableViewer(sashForm, SWT.BORDER
				| SWT.FULL_SELECTION);
		tableThuocChitiet = tableViewerThuocChitiet.getTable();
		tableThuocChitiet.setFont(SWTResourceManager.getFont("Tahoma", 11, SWT.NORMAL));
		tableThuocChitiet.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
//				if (e.keyCode == SWT.F5) {
//					reloadTableThuocChitiet();
//				} else if (e.keyCode == SWT.F7) {
//					addNewThuocChitiet();
//				} else 
				if (e.keyCode == SWT.DEL) {
					deleteThuocChitiet();
				}
				else{
					keyPress(e);
				}
			}
		});
		tableThuocChitiet.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				// selectTableThuocChitiet();
			}
		});

		tableThuocChitiet.setLinesVisible(true);
		tableThuocChitiet.setHeaderVisible(true);

		TableColumn tbTableColumnThuocChitietSTT = new TableColumn(
				tableThuocChitiet, SWT.RIGHT);
		tbTableColumnThuocChitietSTT.setWidth(43);
		tbTableColumnThuocChitietSTT.setText("STT");

		TableColumn tbTableColumnThuocChitietKHO_NAME = new TableColumn(
				tableThuocChitiet, SWT.LEFT);
		tbTableColumnThuocChitietKHO_NAME.setWidth(90);
		tbTableColumnThuocChitietKHO_NAME.setText("TÊN KHO");

		TableColumn tblclmnLHng = new TableColumn(tableThuocChitiet, SWT.LEFT);
		tblclmnLHng.setWidth(77);
		tblclmnLHng.setText("LÔ HÀNG");

		TableColumn tbTableColumnThuocChitietTEN_THUOC = new TableColumn(
				tableThuocChitiet, SWT.LEFT);
		tbTableColumnThuocChitietTEN_THUOC.setWidth(258);
		tbTableColumnThuocChitietTEN_THUOC.setText("TEN_THUOC");

		TableColumn tbTableColumnThuocChitietDON_VI_TINH = new TableColumn(
				tableThuocChitiet, SWT.LEFT);
		tbTableColumnThuocChitietDON_VI_TINH.setWidth(61);
		tbTableColumnThuocChitietDON_VI_TINH.setText("DV");

		TableColumn tbTableColumnThuocChitietSOLUONG = new TableColumn(
				tableThuocChitiet, SWT.RIGHT);
		tbTableColumnThuocChitietSOLUONG.setWidth(61);
		tbTableColumnThuocChitietSOLUONG.setText("SL");

		TableColumn tbTableColumnThuocChitietDON_GIA = new TableColumn(
				tableThuocChitiet, SWT.RIGHT);
		tbTableColumnThuocChitietDON_GIA.setWidth(71);
		tbTableColumnThuocChitietDON_GIA.setText("GIA");

		TableColumn tbTableColumnThuocChitietTHANH_TIEN = new TableColumn(
				tableThuocChitiet, SWT.RIGHT);
		tbTableColumnThuocChitietTHANH_TIEN.setWidth(85);
		tbTableColumnThuocChitietTHANH_TIEN.setText("TTIEN");

		TableColumn tbTableColumnThuocChitietSTS = new TableColumn(
				tableThuocChitiet, SWT.RIGHT);
		tbTableColumnThuocChitietSTS.setWidth(59);
		tbTableColumnThuocChitietSTS.setText("STS");

		TableColumn tbTableColumnThuocChitietHOATCHAT = new TableColumn(
				tableThuocChitiet, SWT.LEFT);
		tbTableColumnThuocChitietHOATCHAT.setWidth(100);
		tbTableColumnThuocChitietHOATCHAT.setText("HOATCHAT");

		TableColumn tbTableColumnThuocChitietHAMLUONG = new TableColumn(
				tableThuocChitiet, SWT.LEFT);
		tbTableColumnThuocChitietHAMLUONG.setWidth(100);
		tbTableColumnThuocChitietHAMLUONG.setText("HAMLUONG");

		TableColumn tbTableColumnThuocChitietDONGGOI = new TableColumn(
				tableThuocChitiet, SWT.LEFT);
		tbTableColumnThuocChitietDONGGOI.setWidth(100);
		tbTableColumnThuocChitietDONGGOI.setText("DONGGOI");

		Menu menu = new Menu(tableThuocChitiet);
		tableThuocChitiet.setMenu(menu);

		MenuItem mntmNewItem = new MenuItem(menu, SWT.NONE);
		mntmNewItem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				doViewChitietLoThuoc();
			}
		});
		mntmNewItem.setText("Xem Chi tiết Lô Thuốc");

		tableViewerThuocChitiet
				.setLabelProvider(new TableLabelProviderThuocChitiet());
		tableViewerThuocChitiet
				.setContentProvider(new ContentProviderThuocChitiet());
		//tableViewerThuocChitiet.setCellModifier(new CellModifierThuocVienPhi(tableViewerThuocChitiet));
		sashForm.setWeights(new int[] { 162, 243 });
		tableViewerThuocChitiet.setInput(listDataThuocChitiet);
		//
		Composite compositeButtonGroup = new Composite(shell, SWT.NONE);
		compositeButtonGroup.setLayoutData(BorderLayout.SOUTH);
		compositeButtonGroup.setLayout(new GridLayout(3, false));

		btnSavePhieu = new Button(compositeButtonGroup, SWT.NONE);
		btnSavePhieu.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				saveDonThuoc();
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
		btnInPhieu = new Button(compositeButtonGroup, SWT.NONE);
		btnInPhieu.setEnabled(false);
		btnInPhieu.setToolTipText("In Phiếu, bấm F9");
		btnInPhieu.setText("In Phiếu (F9)");
		btnInPhieu.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				inDonThuoc();
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
		
		btnThanhToan = new Button(compositeButtonGroup, SWT.NONE);
		btnThanhToan.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				thanhtoanDonThuoc();
			}
		});
		btnThanhToan.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPress(e);
			}
		});		
		btnThanhToan.setFont(SWTResourceManager.getFont("Tahoma", 12, SWT.NORMAL));
		GridData gd_button_2 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_button_2.widthHint = 201;
		btnThanhToan.setLayoutData(gd_button_2);
		btnThanhToan.setText("THANH TOÁN(F5)");
		btnThanhToan.setImage(SWTResourceManager.getImage(FormBanThuocDlg.class, "/png/spreadsheet-3x.png"));
		//
		//
		reloadTableChitietNhapthuoc();
		reloadTableThuocChitiet();
		startDlg();
	}

	protected void deleteThuocChitiet() {
		if (tableThuocChitiet.getSelectionCount() == 0) {
			return;
		}
		TableItem item = tableThuocChitiet.getSelection()[0];
		ThuocChitiet obj = (ThuocChitiet) item.getData();
		if(obj!=null){
//			if(obj.THANHTOAN==1 ){
//				MessageDialog.openError(shell, "Có lỗi", "Đã thanh toán, không xóa được");
//				return;
//			}
			if(obj.STS==-1){
				listDataThuocChitiet.remove(obj);
				tableViewerThuocChitiet.refresh();
				reloadTableChitietNhapthuoc();
			}
			else if(obj.STS>=0){
				CtNhapthuoc objCtNhapthuoc = CtNhapthuoc.load(obj.CT_ID);
				if(objCtNhapthuoc!=null){
					logger.info("==================BEGIN UPDATE KHO THUOC + === ");
					// Update outstanding
					logger.info("==================    LOHANG_ID="
							+ obj.CT_ID + " TON KHO="
							+ objCtNhapthuoc.SL_TONKHO + " CTID_FROM="
							+ objCtNhapthuoc.CTID_FROM);
					// Tru truc tiep
					objCtNhapthuoc.SL_TONKHO = objCtNhapthuoc.SL_TONKHO + obj.SO_LUONG;
					//
					logger.info("==================    LOHANG_ID="
							+ obj.CT_ID + " TON KHO="
							+ objCtNhapthuoc.SL_TONKHO + " CTID_FROM="
							+ objCtNhapthuoc.CTID_FROM);
					//
					objCtNhapthuoc.update();
					//
					ActionLog log = new ActionLog();
					log.u_id = DbHelper.getCurrentSessionUserId();
					log.dbtable = "ct_nhapthuoc";
					log.fieldid = objCtNhapthuoc.CT_ID;
					log.actionid = 5;
					log.u_action = "Undo Bán thuốc "+obj.SO_LUONG+", cộng kho: SL_TONKHO="+(objCtNhapthuoc.SL_TONKHO-obj.SO_LUONG)+" to "+objCtNhapthuoc.SL_TONKHO;
					//log.u_action = "Bán thuốc SL_TONKHO="+(objCtNhapthuoc.SL_TONKHO-obj.SO_LUONG)+" to "+objCtNhapthuoc.SL_TONKHO;
					log.insert();
					//
					logger.info("==================END UPDATE KHO THUOC + === ");
					//
				}
				listDataThuocChitiet.remove(obj);
				obj.deleteRow();
				tableViewerThuocChitiet.refresh();
				reloadTableChitietNhapthuoc();
			}
			else{
				MessageDialog.openError(shell, "Có lỗi", "Không xóa được");
			}
			logger.info(obj.toString());
		}
		//
		
		//

		
	}

	private void startDlg() {
		if(objKhambenh!=null){
			txtHOTEN.setText(objKhambenh.TEN_BENH_NHAN);
			txtDIACHI.setText(objKhambenh.DIA_CHI);
			if(objKhambenh.MA_LK!=null && objKhambenh.MA_LK>0){
				//txtHOTEN.setText(objKhambenh.TEN_BENH_NHAN+"-"+objKhambenh.MA_LK.intValue());
				//objKhambenh.MA_LK = null;
			}
			if(objKhambenh.NGAY_TTOAN!=null && objKhambenh.NGAY_TTOAN.length()>0){
//				btnSavePhieu.setEnabled(false);
//				btnThanhToan.setEnabled(false);
			}
		}
		txtHOTEN.selectAll();
		txtHOTEN.forceFocus();
		btnInPhieu.setEnabled(false);
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
		ThuocChitiet objThuocChiTiet = new ThuocChitiet();
		objThuocChiTiet.THUOC_ID = objCtNhapthuoc.THUOC_ID;
		objThuocChiTiet.SO_LUONG = 1;
		//
		//
		if (objCtNhapthuoc != null) {
			if (objCtNhapthuoc.TENTHUOC != null
					&& objCtNhapthuoc.TENTHUOC.length() > 0) {
				if( objCtNhapthuoc.TYP==2 && objCtNhapthuoc.DONGIA_BAN <=0 ){
					//
					MessageDialog.openError(shell, "Có lỗi", "Đơn giá bán không đúng" );
					return false;
					//
				}
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
				objThuocChiTiet.KHO_ID = objCtNhapthuoc.KHO_ID;
				objThuocChiTiet.KHO_NAME = objCtNhapthuoc.TENKHO;
				//
				objThuocChiTiet.TYP = objCtNhapthuoc.TYP;
				if(objThuocChiTiet.TYP==1){
					// THUOC VIEN PHI
					objThuocChiTiet.DON_GIA = objCtNhapthuoc.DONGIA;
				}
				else{
					objThuocChiTiet.DON_GIA = objCtNhapthuoc.DONGIA_BAN;
					if(objCtNhapthuoc.DONGIA_BAN==0){
						objThuocChiTiet.DON_GIA = objCtNhapthuoc.DONGIA;
					}
				}
				//
				objThuocChiTiet.THUOC_ID = objCtNhapthuoc.THUOC_ID;
				objThuocChiTiet.CT_ID = objCtNhapthuoc.CT_ID;
				objThuocChiTiet.NT_ID = objCtNhapthuoc.NT_ID;
				objThuocChiTiet.SO_LUONG = iSoLuongChiDinh;
				objThuocChiTiet.THANH_TIEN = iSoLuongChiDinh*objThuocChiTiet.DON_GIA;
				objThuocChiTiet.TT_BNTT = objThuocChiTiet.THANH_TIEN;
				//
				objThuocChiTiet.STT = listDataThuocChitiet.size() + 1;
				objThuocChiTiet.STS = -1;  // JuSY ADD 
				listDataThuocChitiet.add(objThuocChiTiet);
			}
			//
			tableViewerThuocChitiet.refresh();
		}
		// ======================================================
		//
		// ======================================================
		//
		return true;
	}

	protected void keyPress(KeyEvent e) {
		if( e.keyCode==SWT.F2){
			saveDonThuoc();
		}
		else if( e.keyCode==SWT.F5){
			inDonThuoc();
		}
		else if( e.keyCode==SWT.F9){
			thanhtoanDonThuoc();
		}
	}

	protected void addNewThuocChitiet() {
		ThuocChitiet objCtNhapthuoc = new ThuocChitiet();
		listDataThuocChitiet.add(objCtNhapthuoc);
		tableViewerThuocChitiet.refresh();
	}

	protected void doViewChitietLoThuoc() {
		if (tableThuocChitiet.getSelectionCount() == 0) {
			return;
		}
		TableItem item = tableThuocChitiet.getSelection()[0];
		ThuocChitiet obj = (ThuocChitiet) item.getData();
		logger.info(obj.toString());
		//
		NhapThuoc objNhapThuoc = (NhapThuoc) NhapThuoc.load(obj.NT_ID);
		if (objNhapThuoc != null) {
			// Load to view
			//
			FormNhapThuocDlg dlg = new FormNhapThuocDlg(shell, 0);
			dlg.intTypeDlgNhapThuoc = FormNhapThuocDlg.TYPE_DLG_VIEW;
			dlg.setNhapThuocDlgData(objNhapThuoc, "");
			dlg.open();
			//
		}
		//
	}

	public void setDataThuocChitiet(String textSearchString) {
		this.textSearchThuocChitietString = textSearchString;
	}

	protected void reloadTableThuocChitiet() {
		if (objKhambenh == null) {
			return;
		}
		if (objKhambenh.MA_LK == null) {
			return;
		}
		//objBenhNhan = BenhNhan.load(objKhambenh.BN_ID);
		if (objKhambenh != null) {
			txtHOTEN.setText(objKhambenh.TEN_BENH_NHAN);
			txtDIACHI.setText(objKhambenh.DIA_CHI);
		}
		//
		//String sql = "select * from thuoc_chitiet WHERE KHO_NAME='Kho viện phí' and STS<> "
		String sql = "select * from thuoc_chitiet WHERE KHO_ID<>2 and STS<> "
				+ DbHelper.DELETE_STATUS + " and MA_LK="
				+ objKhambenh.MA_LK.intValue();
		try {
			logger.info(sql);
			Connection con = DbHelper.getSql2o();
			listDataThuocChitiet = con.createQuery(sql).executeAndFetch(ThuocChitiet.class);
			int i = 1;
			for (ThuocChitiet obj : listDataThuocChitiet) {
				obj.STT = i++;
			}
		} catch (Exception e) {
			logger.error(e);
			MessageDialog.openError(shell, "Error", e.getMessage());
		}
		//
		logger.info("listDataThuocChitiet size="+listDataThuocChitiet.size());

		tableViewerThuocChitiet.setInput(listDataThuocChitiet);
		tableViewerThuocChitiet.refresh();
		//
		if (listDataThuocChitiet.size() == 0) {
			//
		} else {
			tableThuocChitiet.forceFocus();
			tableThuocChitiet.setSelection(0);
		}
	}

	protected void reloadTableChitietNhapthuoc() {
		// if(listDataCtNhapthuoc==null){
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
		sql += " and KHO_ID="+KHO_ID;
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

	public void setKhamBenhDlgData(KhamBenh obj) {
		objKhambenh = obj;
	}

	protected void inDonThuoc() {
		// REPORT BHYT ALL DICH VU
		ReportDAO reportAll = new ReportDAO();
		//
		reportAll.kb = objKhambenh;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");// 201711091458
																	// //yyyy-MM-dd
																	// HH:mm:ss
		//
		reportAll.sumThuoc = new SumReportDAO();
		//
		reportAll.thuoc = new ArrayList<>();
		// Process all
		for (int i = 0; i < listDataThuocChitiet.size(); i++) {
			ThuocChitiet obj = listDataThuocChitiet.get(i);
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
		//
		reportAll.sumTongCong = new SumReportDAO();
		reportAll.sumTongCong.TT2 = reportAll.sumThuoc.TT2;
		reportAll.sumTongCong.TT = reportAll.sumThuoc.TT2;
		reportAll.sumTongCong.BH = (float)0.0;
		reportAll.sumTongCong.NB = reportAll.sumThuoc.TT2;
		reportAll.sumTongCong.NB2 = reportAll.sumThuoc.TT2;
		reportAll.sumTongCong.KH = (float)0.0;
		//
		reportAll.strNguoiLap = DbHelper.currentSessionUserId.TEN_NHANVIEN;
		//
		reportAll.ngayGio = Utils.getDatetimeLocal("Duy Xuyên", new Date());
		//
		inPhieuMuaThuoc(reportAll);
		
	}
	private void inPhieuMuaThuoc(ReportDAO report) {
		java.sql.Connection con = null;
		try {
			String reportName = "PhieuMuaThuoc.jasper";
			JasperReport jr = (JasperReport) JRLoader.loadObject(this.getClass().getResource("/" + reportName));
			// logger.info("JasperCompileManager " + jr);
			//
			JRBeanCollectionDataSource report_cong = new JRBeanCollectionDataSource( report.congkham);
			JRBeanCollectionDataSource report_dv = new JRBeanCollectionDataSource( report.listdv);
			JRBeanCollectionDataSource report_thuoc = new JRBeanCollectionDataSource( report.thuoc);
			Map<String, Object> params = new HashMap<String, Object>();
			// System.out.println(" COUNT = " + report_dv.getRecordCount());
			params.put("BN", report.bn);
			params.put("KB", report.kb);
			params.put("THUOC_DataSource", report_thuoc);
			params.put("DICHVU_SUM", report.sumDv);
			params.put("THUOC_SUM", report.sumThuoc);
			params.put("TENKHOAPHONG", "Phòng Thuốc");

			params.put("NGAY_GIO", report.ngayGio);
			params.put("NGUOI_LAP", report.strNguoiLap == null ? "": report.strNguoiLap);
			params.put("TONGCONG_SUM", report.sumTongCong);
			JasperPrint jp = JasperFillManager.fillReport(jr, params,new JREmptyDataSource());
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
	protected void saveDonThuoc() {
		if( txtHOTEN.getText().trim().length()==0 ){
			txtHOTEN.forceFocus();
			return;
		}
		if( txtDIACHI.getText().trim().length()==0 ){
			txtDIACHI.forceFocus();
			return;
		}
		if(listDataThuocChitiet.size()==0){
			MessageDialog.openError(shell, "Có lỗi", "Nhập thuốc để lưu");
			return;
		}
		if(objKhambenh!=null)
		{
			TONG_TIEN_THUOC_VP = (float)0.0;
			TONG_TIEN_THUOC_BH = (float)0.0;
			for (ThuocChitiet obj : listDataThuocChitiet) 
			{
				if( obj.TYP==2 ){
					obj.MUC_HUONG 			= 0;
					obj.THANH_TIEN 			= obj.SO_LUONG*obj.DON_GIA;
					obj.TT_BNTT 			= obj.THANH_TIEN;
					TONG_TIEN_THUOC_VP 		+= obj.THANH_TIEN;
				}
				else{
					//obj.THANH_TIEN 			= obj.SO_LUONG*obj.DON_GIA;
					TONG_TIEN_THUOC_BH 		+= obj.THANH_TIEN;
					//
				}
				//
			}
			if(objKhambenh.BN_ID==0){
				// Khach vang lai
				objKhambenh.TEN_BENH_NHAN = txtHOTEN.getText().trim();
				objKhambenh.DIA_CHI 		= txtDIACHI.getText().trim();
				objKhambenh.KIEU_TT 		= Utils.THANHTOAN_MUA_THUOC;
				objKhambenh.T_TONGCHI 		= TONG_TIEN_THUOC_VP;
				objKhambenh.T_THUOC 		= TONG_TIEN_THUOC_VP;
				objKhambenh.T_BNTT 			= TONG_TIEN_THUOC_VP;
				objKhambenh.T_NGOAIDS 		= TONG_TIEN_THUOC_VP;
				objKhambenh.T_BHTT 			= 0.0;
				if(objKhambenh.NV_ID==0){
					objKhambenh.NV_ID = DbHelper.currentSessionUserId.U_ID;
					objKhambenh.NV_NAME = DbHelper.currentSessionUserId.U_NAME;
					objKhambenh.TABLE_ID = Main.TABLE_ID;
				}
			}
			else{
				//objKhambenh.T_TONGCHI 		= TONG_TIEN_THUOC_VP;
				//objKhambenh.T_THUOC 		= TONG_TIEN_THUOC_VP;
				//objKhambenh.T_VTYT 			= TONG_TIEN_THUOC_VP;
				objKhambenh.T_NGOAIDS 		= TONG_TIEN_THUOC_VP;
				//objKhambenh.T_BNTT 			= TONG_TIEN_THUOC_VP;
				//objKhambenh.T_BHTT 			= 0.0;
				if(objKhambenh.NV_ID==0){
					objKhambenh.NV_ID = DbHelper.currentSessionUserId.U_ID;
					objKhambenh.NV_NAME = DbHelper.currentSessionUserId.U_NAME;
					objKhambenh.TABLE_ID = Main.TABLE_ID;
				}
			}
			objKhambenh.insert();
			for (ThuocChitiet obj : listDataThuocChitiet) {
				obj.MA_LK = objKhambenh.MA_LK;
				boolean isFirstTimeInsert = false;
				if (obj.TCT_ID == null) {
					// First time insert
					isFirstTimeInsert = true;
					//
				}

				//
				if (obj.STS == -1) {
					obj.STS = 0;
				}
				if(obj.THANHTOAN==0){
					// Insert thuoc chi tiet.
					obj.insert();
					// Update kho thuoc ....
					//
				}
				if( isFirstTimeInsert==true){
					CtNhapthuoc objCtNhapthuoc = CtNhapthuoc.load(obj.CT_ID);
					if(objCtNhapthuoc!=null){
						logger.info("==================BEGIN UPDATE KHO THUOC === ");
						// Update outstanding
						logger.info("==================    LOHANG_ID="
								+ obj.CT_ID + " TON KHO="
								+ objCtNhapthuoc.SL_TONKHO + " CTID_FROM="
								+ objCtNhapthuoc.CTID_FROM);
						// Tru truc tiep
						objCtNhapthuoc.SL_TONKHO = objCtNhapthuoc.SL_TONKHO - obj.SO_LUONG;
						//
						logger.info("==================    LOHANG_ID="
								+ obj.CT_ID + " TON KHO="
								+ objCtNhapthuoc.SL_TONKHO + " CTID_FROM="
								+ objCtNhapthuoc.CTID_FROM);
						//
						objCtNhapthuoc.update();
						//
						//
						ActionLog log = new ActionLog();
						log.u_id = DbHelper.getCurrentSessionUserId();
						log.dbtable = "ct_nhapthuoc";
						log.fieldid = objCtNhapthuoc.CT_ID;
						log.actionid = 2;
						log.u_action = "Bán thuốc "+obj.SO_LUONG+", trừ kho: SL_TONKHO="+(objCtNhapthuoc.SL_TONKHO+obj.SO_LUONG)+" to "+objCtNhapthuoc.SL_TONKHO;
						log.insert();
						//
						logger.info("==================END UPDATE KHO THUOC === ");
						//
					}
				}
			}
			reloadTableChitietNhapthuoc();
			//
			tableViewerThuocChitiet.refresh();
			//
		}
		btnInPhieu.setEnabled(true);
	}
	
	protected void thanhtoanDonThuoc() {
		if( txtHOTEN.getText().trim().length()==0 ){
			txtHOTEN.forceFocus();
			return;
		}
		if( txtDIACHI.getText().trim().length()==0 ){
			txtDIACHI.forceFocus();
			return;
		}
		if(objKhambenh!=null){
			if(objKhambenh.BN_ID==0){
				// Khach vang lai
				objKhambenh.TEN_BENH_NHAN = txtHOTEN.getText().trim();
				objKhambenh.DIA_CHI = txtDIACHI.getText().trim();
			}
			if(objKhambenh.KIEU_TT==Utils.THANHTOAN_BHYT || objKhambenh.KIEU_TT==Utils.THANHTOAN_BHYT_2){
			
			}
			else{
				objKhambenh.NGAY_TTOAN = Utils.getDatetime(new Date(), "yyyyMMddHHmm");
			}
			objKhambenh.insert();
		}
		for (ThuocChitiet obj : listDataThuocChitiet) {
			boolean isFirstTimeInsert = false;
			if (obj.TCT_ID == null) {
				// First time insert
				isFirstTimeInsert = true;
				//
			}

			//
			if(obj.THANHTOAN==0 ){
				obj.THANH_TIEN = obj.SO_LUONG*obj.DON_GIA;
				obj.TT_BNTT = obj.THANH_TIEN;
				obj.THANHTOAN = 1;
				
				if(obj.NV_ID==0){
					obj.NV_ID = DbHelper.currentSessionUserId.U_ID;
					obj.NV_NAME = DbHelper.currentSessionUserId.U_NAME;
				}
				// Insert thuoc chi tiet.
				
				obj.insert();
				//
			}
			//
			if( isFirstTimeInsert==true){
				CtNhapthuoc objCtNhapthuoc = CtNhapthuoc.load(obj.CT_ID);
				if(objCtNhapthuoc!=null){
					logger.info("==================BEGIN UPDATE KHO THUOC === ");
					// Update outstanding
					logger.info("==================    LOHANG_ID="
							+ obj.CT_ID + " TON KHO="
							+ objCtNhapthuoc.SL_TONKHO + " CTID_FROM="
							+ objCtNhapthuoc.CTID_FROM);
					// Tru truc tiep
					objCtNhapthuoc.SL_TONKHO = objCtNhapthuoc.SL_TONKHO - obj.SO_LUONG;
					//
					logger.info("==================    LOHANG_ID="
							+ obj.CT_ID + " TON KHO="
							+ objCtNhapthuoc.SL_TONKHO + " CTID_FROM="
							+ objCtNhapthuoc.CTID_FROM);
					//
					objCtNhapthuoc.update();
					//
					ActionLog log = new ActionLog();
					log.u_id = DbHelper.getCurrentSessionUserId();
					log.dbtable = "ct_nhapthuoc";
					log.fieldid = objCtNhapthuoc.CT_ID;
					log.actionid = 2;
					log.u_action = "Bán thuốc 2 SL_TONKHO="+(objCtNhapthuoc.SL_TONKHO+obj.SO_LUONG)+" to "+objCtNhapthuoc.SL_TONKHO;
					log.insert();
					//
					//
					logger.info("==================END UPDATE KHO THUOC === ");
					//
					//
				}
			}
		}
		reloadTableChitietNhapthuoc();
		//
		tableViewerThuocChitiet.refresh();
		//
	}
}