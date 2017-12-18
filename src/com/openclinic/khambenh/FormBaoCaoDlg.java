package com.openclinic.khambenh;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.jws.soap.SOAPBinding.Use;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.jface.dialogs.MessageDialog;
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
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;
import org.sql2o.Connection;
import org.sql2o.data.Row;

import swing2swt.layout.BorderLayout;

import com.DbHelper;
import com.model.dao.DvChitiet;
import com.model.dao.DvChitietDlg;
import com.model.dao.KhamBenh;
import com.model.dao.KhamBenhDlg;
import com.model.dao.ThuocChitiet;
import com.model.dao.ThuocChitietDlg;
import com.openclinic.DatePicker;
import com.openclinic.utils.Utils;

import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Label;

public class FormBaoCaoDlg extends Dialog {
	private class TableLabelProviderPhieuKham extends LabelProvider implements ITableLabelProvider {
		public Image getColumnImage(Object element, int columnIndex) {
			return null;
		}
		public String getColumnText(Object element, int columnIndex) {
			if(element instanceof Row){
				Row obj = (Row)element;
				if(columnIndex==0){
					return Utils.getKieuThanhToan( obj.getInteger("KIEU_TT").intValue() );
				}
				else if(columnIndex==1){
					return Utils.getDatetime( obj.getDate("KB_DATE"), "HH:mm dd/MM/YY");
				}
				else if(columnIndex==2){
					return obj.getString("TEN_BENH_NHAN").toString();
				}
				else if(columnIndex==3){
					return obj.getString("NGAY_TTOAN").toString();
				}
				else if(columnIndex==4){
					return obj.getString("MUC_HUONG").toString();
				}
				else if(columnIndex==5){
					return obj.getString("T_TONGCHI").toString();
				}
				else if(columnIndex==6){
					return obj.getString("T_BNTT").toString();
				}
				else if(columnIndex==7){
					return obj.getString("T_BHTT").toString();
				}
				else if(columnIndex==8){
					String MAKHOA =obj.getString("MA_KHOA").toString();
					return MAKHOA;
				}
				else if(columnIndex==9){
					return obj.getString("NV_NAME").toString();
				}
				else if(columnIndex==10){
					return obj.getString("TABLE_ID").toString();
				}
				
			}
			return "";
		}
	}
	private static class ContentProviderPhieuKham implements IStructuredContentProvider {
		public Object[] getElements(Object inputElement) {
			if(inputElement instanceof org.sql2o.data.Table)
				return ((List<Row>)((org.sql2o.data.Table)inputElement).rows()).toArray();
			//
			return new Object[0];
		}
		public void dispose() {
		}
		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		}
	}
	static Logger logger = LogManager
			.getLogger(FormBaoCaoDlg.class.getName());
	protected Object result;
	protected Shell shlOpenClinic;
	private static DatePicker datePickerFrom;
	private static DatePicker datePickerTo;
	public static org.sql2o.data.Table tableDataPhieuKham = null;

	private class TableLabelProviderDvChitiet extends LabelProvider implements ITableLabelProvider {
		public Image getColumnImage(Object element, int columnIndex) {
			return null;
		}
		public String getColumnText(Object element, int columnIndex) {
			if(element instanceof DvChitiet){
				return ((DvChitiet) element).getIndex(columnIndex);
			}
			return "";
		}
	}
	private static class ContentProviderDvChitiet implements IStructuredContentProvider {
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
	private Table tableDvChitiet;
	private TableViewer tableViewerDvChitiet;
	private List<DvChitiet> listDataDvChitiet;
	private Text textSearchDvChitiet;
	private String textSearchDvChitietString;
	private class TableLabelProviderKhamBenh extends LabelProvider implements ITableLabelProvider {
		public Image getColumnImage(Object element, int columnIndex) {
			return null;
		}
		public String getColumnText(Object element, int columnIndex) {
			if(element instanceof KhamBenh){
				return ((KhamBenh) element).getIndex(columnIndex);
			}
			return "";
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
	private List<KhamBenh> listDataKhamBenh;
	
	
	private class TableLabelProviderThuocChitiet extends LabelProvider implements ITableLabelProvider {
		public Image getColumnImage(Object element, int columnIndex) {
			return null;
		}
		public String getColumnText(Object element, int columnIndex) {
			if(element instanceof ThuocChitiet){
				return ((ThuocChitiet) element).getIndex(columnIndex);
			}
			return "";
		}
	}
	private static class ContentProviderThuocChitiet implements IStructuredContentProvider {
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
	private Table tableThuocChitiet;
	private TableViewer tableViewerThuocChitiet;
	private List<ThuocChitiet> listDataThuocChitiet;
	private Combo cbType;
	private Combo cbBacSy;
	private Combo cbLoai;
	
	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public FormBaoCaoDlg(Shell parent, int style) {
		super(parent, style);
		setText("SWT Dialog");
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public Object open() {
		createContents();
		shlOpenClinic.open();
		shlOpenClinic.layout();
		Display display = getParent().getDisplay();
		while (!shlOpenClinic.isDisposed()) {
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
		shlOpenClinic = new Shell(getParent(), SWT.SHELL_TRIM | SWT.PRIMARY_MODAL);
		shlOpenClinic.setSize(852, 486);
		shlOpenClinic.setText("Open Clinic : Báo Cáo");
		shlOpenClinic.setLayout(new BorderLayout(0, 0));
		
		Composite composite = new Composite(shlOpenClinic, SWT.NONE);
		composite.setLayoutData(BorderLayout.NORTH);
		composite.setLayout(new GridLayout(7, false));
		
		Label lblTNgy = new Label(composite, SWT.NONE);
		lblTNgy.setText("Từ ngày");
		
		datePickerFrom = new DatePicker(composite, SWT.NONE);
		
		Label lblnNgy = new Label(composite, SWT.NONE);
		lblnNgy.setText("Đến ngày");
		
		datePickerTo = new DatePicker(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		
		Label lblKiu = new Label(composite, SWT.NONE);
		lblKiu.setText("Kiểu");
		lblKiu.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		
		cbType = new Combo(composite, SWT.NONE);
		cbType.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		cbType.setItems(new String[] {"Tất cả", "TT Bảo hiểm", "Viện Phí/Mua CLS"});
		cbType.select(0);
		
		Label lblBs = new Label(composite, SWT.NONE);
		lblBs.setText("BS");
		
		cbBacSy = new Combo(composite, SWT.NONE);
		cbBacSy.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		Label lblNhom = new Label(composite, SWT.NONE);
		lblNhom.setText("Nhóm");
		
		Combo cbNhom = new Combo(composite, SWT.NONE);
		
		Button btnXem = new Button(composite, SWT.NONE);
		GridData gd_btnXem = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnXem.widthHint = 125;
		btnXem.setLayoutData(gd_btnXem);
		btnXem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				viewAll();
			}
		});
		btnXem.setText("Xem ");
		
		TabFolder tabFolder = new TabFolder(shlOpenClinic, SWT.NONE);
		
		TabItem tbtmPhiuKhmBnh = new TabItem(tabFolder, SWT.NONE);
		tbtmPhiuKhmBnh.setText("Phiếu khám bệnh");
		
		Composite compositeInShellKhamBenh = new Composite(tabFolder, SWT.NONE);
		tbtmPhiuKhmBnh.setControl(compositeInShellKhamBenh);
		compositeInShellKhamBenh.setLayout(new BorderLayout(0, 0));
		//=============
		
		tableViewerKhamBenh = new TableViewer(compositeInShellKhamBenh, SWT.BORDER | SWT.FULL_SELECTION);
		tableKhamBenh = tableViewerKhamBenh.getTable();
		tableKhamBenh.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		tableKhamBenh.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.keyCode==SWT.F5){
					reloadTableKhamBenh();
                }
			}
		});
        tableKhamBenh.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				//selectTableKhamBenh();
			}
		});
        
		tableKhamBenh.setLinesVisible(true);
		tableKhamBenh.setHeaderVisible(true);
		tableKhamBenh.setLayoutData(BorderLayout.CENTER);

		TableColumn tbTableColumnKhamBenhSTT = new TableColumn(tableKhamBenh, SWT.RIGHT);
		tbTableColumnKhamBenhSTT.setWidth(100);
		tbTableColumnKhamBenhSTT.setText("STT");

		TableColumn tbTableColumnKhamBenhBN_ID = new TableColumn(tableKhamBenh, SWT.RIGHT);
		tbTableColumnKhamBenhBN_ID.setWidth(100);
		tbTableColumnKhamBenhBN_ID.setText("BN_ID");

		TableColumn tbTableColumnKhamBenhTEN_BENH_NHAN = new TableColumn(tableKhamBenh, SWT.LEFT);
		tbTableColumnKhamBenhTEN_BENH_NHAN.setWidth(100);
		tbTableColumnKhamBenhTEN_BENH_NHAN.setText("TEN_BENH_NHAN");

		TableColumn tbTableColumnKhamBenhTEN_BENH = new TableColumn(tableKhamBenh, SWT.LEFT);
		tbTableColumnKhamBenhTEN_BENH.setWidth(100);
		tbTableColumnKhamBenhTEN_BENH.setText("TEN_BENH");

		TableColumn tbTableColumnKhamBenhMA_BENH = new TableColumn(tableKhamBenh, SWT.LEFT);
		tbTableColumnKhamBenhMA_BENH.setWidth(100);
		tbTableColumnKhamBenhMA_BENH.setText("MA_BENH");

		TableColumn tbTableColumnKhamBenhMA_BENHKHAC = new TableColumn(tableKhamBenh, SWT.LEFT);
		tbTableColumnKhamBenhMA_BENHKHAC.setWidth(100);
		tbTableColumnKhamBenhMA_BENHKHAC.setText("MA_BENHKHAC");

		TableColumn tbTableColumnKhamBenhMA_LYDO_VVIEN = new TableColumn(tableKhamBenh, SWT.RIGHT);
		tbTableColumnKhamBenhMA_LYDO_VVIEN.setWidth(100);
		tbTableColumnKhamBenhMA_LYDO_VVIEN.setText("MA_LYDO_VVIEN");

		TableColumn tbTableColumnKhamBenhMA_NOI_CHUYEN = new TableColumn(tableKhamBenh, SWT.LEFT);
		tbTableColumnKhamBenhMA_NOI_CHUYEN.setWidth(100);
		tbTableColumnKhamBenhMA_NOI_CHUYEN.setText("MA_NOI_CHUYEN");

		TableColumn tbTableColumnKhamBenhMA_TAI_NAN = new TableColumn(tableKhamBenh, SWT.RIGHT);
		tbTableColumnKhamBenhMA_TAI_NAN.setWidth(100);
		tbTableColumnKhamBenhMA_TAI_NAN.setText("MA_TAI_NAN");

		TableColumn tbTableColumnKhamBenhNGAY_VAO = new TableColumn(tableKhamBenh, SWT.LEFT);
		tbTableColumnKhamBenhNGAY_VAO.setWidth(100);
		tbTableColumnKhamBenhNGAY_VAO.setText("NGAY_VAO");

		TableColumn tbTableColumnKhamBenhNGAY_RA = new TableColumn(tableKhamBenh, SWT.LEFT);
		tbTableColumnKhamBenhNGAY_RA.setWidth(100);
		tbTableColumnKhamBenhNGAY_RA.setText("NGAY_RA");

		TableColumn tbTableColumnKhamBenhSO_NGAY_DTRI = new TableColumn(tableKhamBenh, SWT.RIGHT);
		tbTableColumnKhamBenhSO_NGAY_DTRI.setWidth(100);
		tbTableColumnKhamBenhSO_NGAY_DTRI.setText("SO_NGAY_DTRI");

		TableColumn tbTableColumnKhamBenhKET_QUA_DTRI = new TableColumn(tableKhamBenh, SWT.RIGHT);
		tbTableColumnKhamBenhKET_QUA_DTRI.setWidth(100);
		tbTableColumnKhamBenhKET_QUA_DTRI.setText("KET_QUA_DTRI");

		TableColumn tbTableColumnKhamBenhTINH_TRANG_RV = new TableColumn(tableKhamBenh, SWT.RIGHT);
		tbTableColumnKhamBenhTINH_TRANG_RV.setWidth(100);
		tbTableColumnKhamBenhTINH_TRANG_RV.setText("TINH_TRANG_RV");

		TableColumn tbTableColumnKhamBenhNGAY_TTOAN = new TableColumn(tableKhamBenh, SWT.LEFT);
		tbTableColumnKhamBenhNGAY_TTOAN.setWidth(100);
		tbTableColumnKhamBenhNGAY_TTOAN.setText("NGAY_TTOAN");

		TableColumn tbTableColumnKhamBenhMUC_HUONG = new TableColumn(tableKhamBenh, SWT.RIGHT);
		tbTableColumnKhamBenhMUC_HUONG.setWidth(100);
		tbTableColumnKhamBenhMUC_HUONG.setText("MUC_HUONG");

		TableColumn tbTableColumnKhamBenhT_THUOC = new TableColumn(tableKhamBenh, SWT.RIGHT);
		tbTableColumnKhamBenhT_THUOC.setWidth(100);
		tbTableColumnKhamBenhT_THUOC.setText("T_THUOC");

		TableColumn tbTableColumnKhamBenhT_VTYT = new TableColumn(tableKhamBenh, SWT.RIGHT);
		tbTableColumnKhamBenhT_VTYT.setWidth(100);
		tbTableColumnKhamBenhT_VTYT.setText("T_VTYT");

		TableColumn tbTableColumnKhamBenhT_TONGCHI = new TableColumn(tableKhamBenh, SWT.RIGHT);
		tbTableColumnKhamBenhT_TONGCHI.setWidth(100);
		tbTableColumnKhamBenhT_TONGCHI.setText("T_TONGCHI");

		TableColumn tbTableColumnKhamBenhT_BNTT = new TableColumn(tableKhamBenh, SWT.RIGHT);
		tbTableColumnKhamBenhT_BNTT.setWidth(100);
		tbTableColumnKhamBenhT_BNTT.setText("T_BNTT");

		TableColumn tbTableColumnKhamBenhT_BHTT = new TableColumn(tableKhamBenh, SWT.RIGHT);
		tbTableColumnKhamBenhT_BHTT.setWidth(100);
		tbTableColumnKhamBenhT_BHTT.setText("T_BHTT");

		TableColumn tbTableColumnKhamBenhT_NGUONKHAC = new TableColumn(tableKhamBenh, SWT.RIGHT);
		tbTableColumnKhamBenhT_NGUONKHAC.setWidth(100);
		tbTableColumnKhamBenhT_NGUONKHAC.setText("T_NGUONKHAC");

		TableColumn tbTableColumnKhamBenhT_NGOAIDS = new TableColumn(tableKhamBenh, SWT.RIGHT);
		tbTableColumnKhamBenhT_NGOAIDS.setWidth(100);
		tbTableColumnKhamBenhT_NGOAIDS.setText("T_NGOAIDS");

		TableColumn tbTableColumnKhamBenhNAM_QT = new TableColumn(tableKhamBenh, SWT.RIGHT);
		tbTableColumnKhamBenhNAM_QT.setWidth(100);
		tbTableColumnKhamBenhNAM_QT.setText("NAM_QT");

		TableColumn tbTableColumnKhamBenhTHANG_QT = new TableColumn(tableKhamBenh, SWT.RIGHT);
		tbTableColumnKhamBenhTHANG_QT.setWidth(100);
		tbTableColumnKhamBenhTHANG_QT.setText("THANG_QT");

		TableColumn tbTableColumnKhamBenhMA_LOAI_KCB = new TableColumn(tableKhamBenh, SWT.RIGHT);
		tbTableColumnKhamBenhMA_LOAI_KCB.setWidth(100);
		tbTableColumnKhamBenhMA_LOAI_KCB.setText("MA_LOAI_KCB");

		TableColumn tbTableColumnKhamBenhMA_KHOA = new TableColumn(tableKhamBenh, SWT.LEFT);
		tbTableColumnKhamBenhMA_KHOA.setWidth(100);
		tbTableColumnKhamBenhMA_KHOA.setText("MA_KHOA");

		TableColumn tbTableColumnKhamBenhMA_CSKCB = new TableColumn(tableKhamBenh, SWT.LEFT);
		tbTableColumnKhamBenhMA_CSKCB.setWidth(100);
		tbTableColumnKhamBenhMA_CSKCB.setText("MA_CSKCB");

		TableColumn tbTableColumnKhamBenhMA_KHUVUC = new TableColumn(tableKhamBenh, SWT.LEFT);
		tbTableColumnKhamBenhMA_KHUVUC.setWidth(100);
		tbTableColumnKhamBenhMA_KHUVUC.setText("MA_KHUVUC");

		TableColumn tbTableColumnKhamBenhMA_PTTT_QT = new TableColumn(tableKhamBenh, SWT.LEFT);
		tbTableColumnKhamBenhMA_PTTT_QT.setWidth(100);
		tbTableColumnKhamBenhMA_PTTT_QT.setText("MA_PTTT_QT");


		TableColumn tbTableColumnKhamBenhCAN_NANG = new TableColumn(tableKhamBenh, SWT.NONE);
		tbTableColumnKhamBenhCAN_NANG.setWidth(100);
		tbTableColumnKhamBenhCAN_NANG.setText("CAN_NANG");


		TableColumn tbTableColumnKhamBenhKB_DATE = new TableColumn(tableKhamBenh, SWT.NONE);
		tbTableColumnKhamBenhKB_DATE.setWidth(100);
		tbTableColumnKhamBenhKB_DATE.setText("KB_DATE");

		TableColumn tbTableColumnKhamBenhKIEU_TT = new TableColumn(tableKhamBenh, SWT.RIGHT);
		tbTableColumnKhamBenhKIEU_TT.setWidth(100);
		tbTableColumnKhamBenhKIEU_TT.setText("KIEU_TT");

		TableColumn tbTableColumnKhamBenhCHANDOAN_BD = new TableColumn(tableKhamBenh, SWT.LEFT);
		tbTableColumnKhamBenhCHANDOAN_BD.setWidth(100);
		tbTableColumnKhamBenhCHANDOAN_BD.setText("CHANDOAN_BD");

		TableColumn tbTableColumnKhamBenhNV_ID = new TableColumn(tableKhamBenh, SWT.RIGHT);
		tbTableColumnKhamBenhNV_ID.setWidth(100);
		tbTableColumnKhamBenhNV_ID.setText("NV_ID");

		TableColumn tbTableColumnKhamBenhNV_NAME = new TableColumn(tableKhamBenh, SWT.LEFT);
		tbTableColumnKhamBenhNV_NAME.setWidth(100);
		tbTableColumnKhamBenhNV_NAME.setText("NV_NAME");

		TableColumn tbTableColumnKhamBenhTABLE_ID = new TableColumn(tableKhamBenh, SWT.RIGHT);
		tbTableColumnKhamBenhTABLE_ID.setWidth(100);
		tbTableColumnKhamBenhTABLE_ID.setText("TABLE_ID");

		TableColumn tbTableColumnKhamBenhSTS = new TableColumn(tableKhamBenh, SWT.RIGHT);
		tbTableColumnKhamBenhSTS.setWidth(100);
		tbTableColumnKhamBenhSTS.setText("STS");

        Menu menuKhamBenh = new Menu(tableKhamBenh);
		tableKhamBenh.setMenu(menuKhamBenh);
		
		MenuItem mntmExportKhamBenh = new MenuItem(menuKhamBenh, SWT.NONE);
		mntmExportKhamBenh.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				exportExcelTableKhamBenh();
			}
		});
		mntmExportKhamBenh.setImage(SWTResourceManager.getImage(KhamBenhDlg.class, "/png/spreadsheet-2x.png"));
		mntmExportKhamBenh.setText("Export Excel");
		
		tableViewerKhamBenh.setLabelProvider(new TableLabelProviderKhamBenh());
		tableViewerKhamBenh.setContentProvider(new ContentProviderKhamBenh());
		tableViewerKhamBenh.setInput(listDataKhamBenh);
		//===============================================================
		TabItem tbtmCnLmSn = new TabItem(tabFolder, SWT.NONE);
		tbtmCnLmSn.setText("Cận lâm sàn/Dịch Vụ");
		
		Composite compositeInShellDvChitiet = new Composite(tabFolder, SWT.NONE);
		tbtmCnLmSn.setControl(compositeInShellDvChitiet);
		compositeInShellDvChitiet.setLayout(new BorderLayout(0, 0));
		tableViewerDvChitiet = new TableViewer(compositeInShellDvChitiet, SWT.BORDER | SWT.FULL_SELECTION);
		tableDvChitiet = tableViewerDvChitiet.getTable();
		tableDvChitiet.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		
        tableDvChitiet.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				//selectTableDvChitiet();
			}
		});
        
		tableDvChitiet.setLinesVisible(true);
		tableDvChitiet.setHeaderVisible(true);
		tableDvChitiet.setLayoutData(BorderLayout.CENTER);

		TableColumn tbTableColumnDvChitietBN_ID = new TableColumn(tableDvChitiet, SWT.RIGHT);
		tbTableColumnDvChitietBN_ID.setWidth(73);
		tbTableColumnDvChitietBN_ID.setText("BN_ID");

		TableColumn tbTableColumnDvChitietMA_LK = new TableColumn(tableDvChitiet, SWT.RIGHT);
		tbTableColumnDvChitietMA_LK.setWidth(74);
		tbTableColumnDvChitietMA_LK.setText("MA_LK");

		TableColumn tbTableColumnDvChitietDV_ID = new TableColumn(tableDvChitiet, SWT.RIGHT);
		tbTableColumnDvChitietDV_ID.setWidth(100);
		tbTableColumnDvChitietDV_ID.setText("DV_ID");

		TableColumn tbTableColumnDvChitietMA_DICH_VU = new TableColumn(tableDvChitiet, SWT.LEFT);
		tbTableColumnDvChitietMA_DICH_VU.setWidth(100);
		tbTableColumnDvChitietMA_DICH_VU.setText("MA_DICH_VU");

		TableColumn tbTableColumnDvChitietMA_VAT_TU = new TableColumn(tableDvChitiet, SWT.LEFT);
		tbTableColumnDvChitietMA_VAT_TU.setWidth(100);
		tbTableColumnDvChitietMA_VAT_TU.setText("MA_VAT_TU");

		TableColumn tbTableColumnDvChitietMA_NHOM = new TableColumn(tableDvChitiet, SWT.RIGHT);
		tbTableColumnDvChitietMA_NHOM.setWidth(100);
		tbTableColumnDvChitietMA_NHOM.setText("MA_NHOM");

		TableColumn tbTableColumnDvChitietTEN_DICH_VU = new TableColumn(tableDvChitiet, SWT.LEFT);
		tbTableColumnDvChitietTEN_DICH_VU.setWidth(100);
		tbTableColumnDvChitietTEN_DICH_VU.setText("TEN_DICH_VU");

		TableColumn tbTableColumnDvChitietSO_LUONG = new TableColumn(tableDvChitiet, SWT.RIGHT);
		tbTableColumnDvChitietSO_LUONG.setWidth(100);
		tbTableColumnDvChitietSO_LUONG.setText("SO_LUONG");

		TableColumn tbTableColumnDvChitietDON_GIA = new TableColumn(tableDvChitiet, SWT.RIGHT);
		tbTableColumnDvChitietDON_GIA.setWidth(100);
		tbTableColumnDvChitietDON_GIA.setText("DON_GIA");

		TableColumn tbTableColumnDvChitietDON_GIA2 = new TableColumn(tableDvChitiet, SWT.RIGHT);
		tbTableColumnDvChitietDON_GIA2.setWidth(100);
		tbTableColumnDvChitietDON_GIA2.setText("DON_GIA2");

		TableColumn tbTableColumnDvChitietTHANH_TIEN = new TableColumn(tableDvChitiet, SWT.RIGHT);
		tbTableColumnDvChitietTHANH_TIEN.setWidth(100);
		tbTableColumnDvChitietTHANH_TIEN.setText("THANH_TIEN");

		TableColumn tbTableColumnDvChitietTHANH_TIEN2 = new TableColumn(tableDvChitiet, SWT.RIGHT);
		tbTableColumnDvChitietTHANH_TIEN2.setWidth(100);
		tbTableColumnDvChitietTHANH_TIEN2.setText("THANH_TIEN2");

		TableColumn tbTableColumnDvChitietTT_BH = new TableColumn(tableDvChitiet, SWT.RIGHT);
		tbTableColumnDvChitietTT_BH.setWidth(100);
		tbTableColumnDvChitietTT_BH.setText("TT_BH");

		TableColumn tbTableColumnDvChitietTT_NB = new TableColumn(tableDvChitiet, SWT.RIGHT);
		tbTableColumnDvChitietTT_NB.setWidth(100);
		tbTableColumnDvChitietTT_NB.setText("TT_NB");

		TableColumn tbTableColumnDvChitietMA_KHOA = new TableColumn(tableDvChitiet, SWT.LEFT);
		tbTableColumnDvChitietMA_KHOA.setWidth(100);
		tbTableColumnDvChitietMA_KHOA.setText("MA_KHOA");

		TableColumn tbTableColumnDvChitietMA_BAC_SI = new TableColumn(tableDvChitiet, SWT.LEFT);
		tbTableColumnDvChitietMA_BAC_SI.setWidth(100);
		tbTableColumnDvChitietMA_BAC_SI.setText("MA_BAC_SI");

		TableColumn tbTableColumnDvChitietMA_BENH = new TableColumn(tableDvChitiet, SWT.LEFT);
		tbTableColumnDvChitietMA_BENH.setWidth(100);
		tbTableColumnDvChitietMA_BENH.setText("MA_BENH");

		TableColumn tbTableColumnDvChitietNGAY_YL = new TableColumn(tableDvChitiet, SWT.LEFT);
		tbTableColumnDvChitietNGAY_YL.setWidth(100);
		tbTableColumnDvChitietNGAY_YL.setText("NGAY_YL");

		TableColumn tbTableColumnDvChitietNGAY_KQ = new TableColumn(tableDvChitiet, SWT.LEFT);
		tbTableColumnDvChitietNGAY_KQ.setWidth(100);
		tbTableColumnDvChitietNGAY_KQ.setText("NGAY_KQ");

		TableColumn tbTableColumnDvChitietMA_PTTT = new TableColumn(tableDvChitiet, SWT.RIGHT);
		tbTableColumnDvChitietMA_PTTT.setWidth(100);
		tbTableColumnDvChitietMA_PTTT.setText("MA_PTTT");

		TableColumn tbTableColumnDvChitietTYLE_TT = new TableColumn(tableDvChitiet, SWT.RIGHT);
		tbTableColumnDvChitietTYLE_TT.setWidth(100);
		tbTableColumnDvChitietTYLE_TT.setText("TYLE_TT");


		TableColumn tbTableColumnDvChitietCUR_DATE = new TableColumn(tableDvChitiet, SWT.NONE);
		tbTableColumnDvChitietCUR_DATE.setWidth(100);
		tbTableColumnDvChitietCUR_DATE.setText("CUR_DATE");

		TableColumn tbTableColumnDvChitietTYP = new TableColumn(tableDvChitiet, SWT.RIGHT);
		tbTableColumnDvChitietTYP.setWidth(100);
		tbTableColumnDvChitietTYP.setText("TYP");

		TableColumn tbTableColumnDvChitietTHANHTOAN = new TableColumn(tableDvChitiet, SWT.RIGHT);
		tbTableColumnDvChitietTHANHTOAN.setWidth(100);
		tbTableColumnDvChitietTHANHTOAN.setText("THANHTOAN");

		TableColumn tbTableColumnDvChitietSTS = new TableColumn(tableDvChitiet, SWT.RIGHT);
		tbTableColumnDvChitietSTS.setWidth(100);
		tbTableColumnDvChitietSTS.setText("STS");

        Menu menuDvChitiet = new Menu(tableDvChitiet);
		tableDvChitiet.setMenu(menuDvChitiet);
		
		MenuItem mntmNewItemDvChitiet = new MenuItem(menuDvChitiet, SWT.NONE);
		mntmNewItemDvChitiet.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				//newItemDvChitiet();
			}
		});
		mntmNewItemDvChitiet.setImage(SWTResourceManager.getImage(DvChitietDlg.class, "/png/arrow-circle-top-2x.png"));
		mntmNewItemDvChitiet.setText("New");
		
		MenuItem mntmEditItemDvChitiet = new MenuItem(menuDvChitiet, SWT.NONE);
		mntmEditItemDvChitiet.setImage(SWTResourceManager.getImage(DvChitietDlg.class, "/png/wrench-2x.png"));
		mntmEditItemDvChitiet.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				//editTableDvChitiet();
			}
		});
		mntmEditItemDvChitiet.setText("Edit");
		
		MenuItem mntmDeleteDvChitiet = new MenuItem(menuDvChitiet, SWT.NONE);
		mntmDeleteDvChitiet.setImage(SWTResourceManager.getImage(DvChitietDlg.class, "/png/circle-x-2x.png"));
		mntmDeleteDvChitiet.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				//deleteTableDvChitiet();
			}
		});
		mntmDeleteDvChitiet.setText("Delete");
		MenuItem mntmExportDvChitiet = new MenuItem(menuDvChitiet, SWT.NONE);
		mntmExportDvChitiet.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				exportExcelTableDvChitiet();
			}
		});
		mntmExportDvChitiet.setImage(SWTResourceManager.getImage(DvChitietDlg.class, "/png/spreadsheet-2x.png"));
		mntmExportDvChitiet.setText("Export Excel");
		
		Composite composite_1 = new Composite(compositeInShellDvChitiet, SWT.NONE);
		composite_1.setLayoutData(BorderLayout.NORTH);
		composite_1.setLayout(new GridLayout(2, false));
		
		Label lblPhnLoi = new Label(composite_1, SWT.NONE);
		lblPhnLoi.setText("LoaiDV");
		
		cbLoai = new Combo(composite_1, SWT.NONE);
		
		tableViewerDvChitiet.setLabelProvider(new TableLabelProviderDvChitiet());
		tableViewerDvChitiet.setContentProvider(new ContentProviderDvChitiet());
		tableViewerDvChitiet.setInput(listDataDvChitiet);
		
		TabItem tbtmThucBh = new TabItem(tabFolder, SWT.NONE);
		tbtmThucBh.setText("Chi tiết Thuốc");
		
		Composite compositeInShellThuocChitiet = new Composite(tabFolder, SWT.NONE);
		tbtmThucBh.setControl(compositeInShellThuocChitiet);
		compositeInShellThuocChitiet.setLayout(new BorderLayout(0, 0));
		
		tableViewerThuocChitiet = new TableViewer(compositeInShellThuocChitiet, SWT.BORDER | SWT.FULL_SELECTION);
		tableThuocChitiet = tableViewerThuocChitiet.getTable();
		tableThuocChitiet.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		tableThuocChitiet.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.keyCode==SWT.F5){
					reloadTableThuocChitiet();
                }
                
			}
		});
        tableThuocChitiet.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				//selectTableThuocChitiet();
			}
		});
        
		tableThuocChitiet.setLinesVisible(true);
		tableThuocChitiet.setHeaderVisible(true);
		tableThuocChitiet.setLayoutData(BorderLayout.CENTER);

		TableColumn tbTableColumnThuocChitietSTT = new TableColumn(tableThuocChitiet, SWT.RIGHT);
		tbTableColumnThuocChitietSTT.setWidth(100);
		tbTableColumnThuocChitietSTT.setText("STT");

		TableColumn tbTableColumnThuocChitietMA_LK = new TableColumn(tableThuocChitiet, SWT.RIGHT);
		tbTableColumnThuocChitietMA_LK.setWidth(100);
		tbTableColumnThuocChitietMA_LK.setText("MA_LK");

		TableColumn tbTableColumnThuocChitietTHUOC_ID = new TableColumn(tableThuocChitiet, SWT.RIGHT);
		tbTableColumnThuocChitietTHUOC_ID.setWidth(100);
		tbTableColumnThuocChitietTHUOC_ID.setText("THUOC_ID");

		TableColumn tbTableColumnThuocChitietMA_THUOC = new TableColumn(tableThuocChitiet, SWT.LEFT);
		tbTableColumnThuocChitietMA_THUOC.setWidth(100);
		tbTableColumnThuocChitietMA_THUOC.setText("MA_THUOC");

		TableColumn tbTableColumnThuocChitietMA_NHOM = new TableColumn(tableThuocChitiet, SWT.LEFT);
		tbTableColumnThuocChitietMA_NHOM.setWidth(100);
		tbTableColumnThuocChitietMA_NHOM.setText("MA_NHOM");

		TableColumn tbTableColumnThuocChitietTEN_THUOC = new TableColumn(tableThuocChitiet, SWT.LEFT);
		tbTableColumnThuocChitietTEN_THUOC.setWidth(100);
		tbTableColumnThuocChitietTEN_THUOC.setText("TEN_THUOC");

		TableColumn tbTableColumnThuocChitietDON_VI_TINH = new TableColumn(tableThuocChitiet, SWT.LEFT);
		tbTableColumnThuocChitietDON_VI_TINH.setWidth(100);
		tbTableColumnThuocChitietDON_VI_TINH.setText("DON_VI_TINH");

		TableColumn tbTableColumnThuocChitietHAM_LUONG = new TableColumn(tableThuocChitiet, SWT.LEFT);
		tbTableColumnThuocChitietHAM_LUONG.setWidth(100);
		tbTableColumnThuocChitietHAM_LUONG.setText("HAM_LUONG");

		TableColumn tbTableColumnThuocChitietDUONG_DUNG = new TableColumn(tableThuocChitiet, SWT.LEFT);
		tbTableColumnThuocChitietDUONG_DUNG.setWidth(100);
		tbTableColumnThuocChitietDUONG_DUNG.setText("DUONG_DUNG");

		TableColumn tbTableColumnThuocChitietLIEU_DUNG = new TableColumn(tableThuocChitiet, SWT.LEFT);
		tbTableColumnThuocChitietLIEU_DUNG.setWidth(100);
		tbTableColumnThuocChitietLIEU_DUNG.setText("LIEU_DUNG");

		TableColumn tbTableColumnThuocChitietSO_DANG_KY = new TableColumn(tableThuocChitiet, SWT.LEFT);
		tbTableColumnThuocChitietSO_DANG_KY.setWidth(100);
		tbTableColumnThuocChitietSO_DANG_KY.setText("SO_DANG_KY");

		TableColumn tbTableColumnThuocChitietSO_LUONG = new TableColumn(tableThuocChitiet, SWT.RIGHT);
		tbTableColumnThuocChitietSO_LUONG.setWidth(100);
		tbTableColumnThuocChitietSO_LUONG.setText("SO_LUONG");

		TableColumn tbTableColumnThuocChitietDON_GIA = new TableColumn(tableThuocChitiet, SWT.RIGHT);
		tbTableColumnThuocChitietDON_GIA.setWidth(100);
		tbTableColumnThuocChitietDON_GIA.setText("DON_GIA");

		TableColumn tbTableColumnThuocChitietTHANH_TIEN = new TableColumn(tableThuocChitiet, SWT.RIGHT);
		tbTableColumnThuocChitietTHANH_TIEN.setWidth(100);
		tbTableColumnThuocChitietTHANH_TIEN.setText("THANH_TIEN");

		TableColumn tbTableColumnThuocChitietMA_KHOA = new TableColumn(tableThuocChitiet, SWT.LEFT);
		tbTableColumnThuocChitietMA_KHOA.setWidth(100);
		tbTableColumnThuocChitietMA_KHOA.setText("MA_KHOA");

		TableColumn tbTableColumnThuocChitietMA_BAC_SI = new TableColumn(tableThuocChitiet, SWT.LEFT);
		tbTableColumnThuocChitietMA_BAC_SI.setWidth(100);
		tbTableColumnThuocChitietMA_BAC_SI.setText("MA_BAC_SI");

		TableColumn tbTableColumnThuocChitietMA_BENH = new TableColumn(tableThuocChitiet, SWT.LEFT);
		tbTableColumnThuocChitietMA_BENH.setWidth(100);
		tbTableColumnThuocChitietMA_BENH.setText("MA_BENH");

		TableColumn tbTableColumnThuocChitietMA_PTTT = new TableColumn(tableThuocChitiet, SWT.RIGHT);
		tbTableColumnThuocChitietMA_PTTT.setWidth(100);
		tbTableColumnThuocChitietMA_PTTT.setText("MA_PTTT");


		TableColumn tbTableColumnThuocChitietTYLE_TT = new TableColumn(tableThuocChitiet, SWT.NONE);
		tbTableColumnThuocChitietTYLE_TT.setWidth(100);
		tbTableColumnThuocChitietTYLE_TT.setText("TYLE_TT");

		TableColumn tbTableColumnThuocChitietCT_ID = new TableColumn(tableThuocChitiet, SWT.RIGHT);
		tbTableColumnThuocChitietCT_ID.setWidth(100);
		tbTableColumnThuocChitietCT_ID.setText("CT_ID");

		TableColumn tbTableColumnThuocChitietNT_ID = new TableColumn(tableThuocChitiet, SWT.RIGHT);
		tbTableColumnThuocChitietNT_ID.setWidth(100);
		tbTableColumnThuocChitietNT_ID.setText("NT_ID");

		TableColumn tbTableColumnThuocChitietTT_BH = new TableColumn(tableThuocChitiet, SWT.RIGHT);
		tbTableColumnThuocChitietTT_BH.setWidth(100);
		tbTableColumnThuocChitietTT_BH.setText("TT_BH");

		TableColumn tbTableColumnThuocChitietTT_NB = new TableColumn(tableThuocChitiet, SWT.RIGHT);
		tbTableColumnThuocChitietTT_NB.setWidth(100);
		tbTableColumnThuocChitietTT_NB.setText("TT_NB");

		TableColumn tbTableColumnThuocChitietKHO_NAME = new TableColumn(tableThuocChitiet, SWT.LEFT);
		tbTableColumnThuocChitietKHO_NAME.setWidth(100);
		tbTableColumnThuocChitietKHO_NAME.setText("KHO_NAME");


		TableColumn tbTableColumnThuocChitietCUR_DATE = new TableColumn(tableThuocChitiet, SWT.NONE);
		tbTableColumnThuocChitietCUR_DATE.setWidth(100);
		tbTableColumnThuocChitietCUR_DATE.setText("CUR_DATE");

		TableColumn tbTableColumnThuocChitietTYP = new TableColumn(tableThuocChitiet, SWT.RIGHT);
		tbTableColumnThuocChitietTYP.setWidth(100);
		tbTableColumnThuocChitietTYP.setText("TYP");

		TableColumn tbTableColumnThuocChitietTHANHTOAN = new TableColumn(tableThuocChitiet, SWT.RIGHT);
		tbTableColumnThuocChitietTHANHTOAN.setWidth(100);
		tbTableColumnThuocChitietTHANHTOAN.setText("THANHTOAN");

		TableColumn tbTableColumnThuocChitietSTS = new TableColumn(tableThuocChitiet, SWT.RIGHT);
		tbTableColumnThuocChitietSTS.setWidth(100);
		tbTableColumnThuocChitietSTS.setText("STS");

        Menu menuThuocChitiet = new Menu(tableThuocChitiet);
		tableThuocChitiet.setMenu(menuThuocChitiet);
		
		MenuItem mntmExportThuocChitiet = new MenuItem(menuThuocChitiet, SWT.NONE);
		mntmExportThuocChitiet.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				exportExcelTableThuocChitiet();
			}
		});
		mntmExportThuocChitiet.setImage(SWTResourceManager.getImage(ThuocChitietDlg.class, "/png/spreadsheet-2x.png"));
		mntmExportThuocChitiet.setText("Export Excel");
		
		Composite composite_2 = new Composite(compositeInShellThuocChitiet, SWT.NONE);
		composite_2.setLayoutData(BorderLayout.NORTH);
		composite_2.setLayout(new GridLayout(1, false));
		
		tableViewerThuocChitiet.setLabelProvider(new TableLabelProviderThuocChitiet());
		tableViewerThuocChitiet.setContentProvider(new ContentProviderThuocChitiet());
		tableViewerThuocChitiet.setInput(listDataThuocChitiet);

		startDlg();
	}

	private void startDlg() {
		datePickerFrom.setText(new Date());
		datePickerTo.setText(new Date());
		//
		cbBacSy.add("Tất cả");
		for(com.model.dao.Users obj: DbHelper.listUsers){
			cbBacSy.add(""+obj.U_NAME +"-" + obj.MACCHN);
		}
		//
	}

	protected void viewAll() {
		reloadTableKhamBenh();
		reloadTableDvChitiet();
		reloadTableThuocChitiet();
	}

//	public static void doSearchBenhNhan() {
//		try {
//			Connection con = DbHelper.getSql2o();
//			String sql = "SELECT * FROM kham_benh as kb where ";
//			sql += " KB_DATE >= STR_TO_DATE('"+datePickerFrom.getDate()+"', '%d/%m/%Y')";
//			sql += " and KB_DATE <= STR_TO_DATE('"+datePickerTo.getDate()+"', '%d/%m/%Y') + INTERVAL 1 DAY";
//			sql += " order by KB_DATE desc";
//			System.out.println(sql);
//			tableDataPhieuKham = con.createQuery(sql).executeAndFetchTable();
//		} catch (Exception e) {
//			logger.error(e);
//		}
//		Display.getDefault().asyncExec(new Runnable() {
//			@Override
//			public void run() {
//				int selectIndex = tablePhieuKham.getSelectionIndex();
//				tableViewerPhieuKham.setInput(tableDataPhieuKham);
//				tableViewerPhieuKham.refresh();
//				tablePhieuKham.setSelection(selectIndex);
//				Utils.exportListRowToExcel("Phieukhambenh_"+Utils.getDatetimeCurent().replaceAll(":", "_")+".csv", tableDataPhieuKham);
//			}
//		});
//	}
	protected void reloadTableDvChitiet() {
        
        if(listDataDvChitiet!=null){
            // 
            tableViewerDvChitiet.setInput(listDataDvChitiet);
            tableViewerDvChitiet.refresh();
            //
            if(listDataDvChitiet.size()==0){
                textSearchDvChitiet.forceFocus();
            }
            else{
                tableDvChitiet.forceFocus();
                tableDvChitiet.setSelection(0);
            }
            return;
        }
		// Do search in the first time
		//String searchString = textSearchDvChitiet==null?"":textSearchDvChitiet.getText().toLowerCase().trim();
		String sql = "select * from dv_chitiet WHERE STS<> "+DbHelper.DELETE_STATUS+" ";
		sql += " and CUR_DATE >= STR_TO_DATE('"+datePickerFrom.getDate()+"', '%d/%m/%Y')";
		sql += " and CUR_DATE <= STR_TO_DATE('"+datePickerTo.getDate()+"', '%d/%m/%Y') + INTERVAL 1 DAY";
		sql += " order by CUR_DATE desc";
		try  {
            logger.info(sql);
			Connection con = DbHelper.getSql2o();
			listDataDvChitiet = con.createQuery(sql).executeAndFetch(DvChitiet.class);
	    }   
	    catch(Exception e){
	    	logger.error(e);
	    	MessageDialog.openError(shlOpenClinic, "Error", e.getMessage());
	    }
		// 
		tableViewerDvChitiet.setInput(listDataDvChitiet);
		tableViewerDvChitiet.refresh();
        //
        if(listDataDvChitiet.size()==0){
            textSearchDvChitiet.forceFocus();
        }
        else{
            tableDvChitiet.forceFocus();
            tableDvChitiet.setSelection(0);
        }
	}
	
	protected void exportExcelTableDvChitiet() {
        if(DbHelper.checkPhanQuyen(DbHelper.READ, "dv_chitiet")==false){
			logger.info("DON'T HAVE READ RIGHT");
			return;
		}
        if(listDataDvChitiet!=null){
            // Export to EXCEL
    		
			StringBuffer buff_dv_chitiet = new StringBuffer();
			String dv_chitiet_filename = "dv_chitiet_"+Utils.getDatetimeCurent().replaceAll(":", "_")+".csv";
			String delimiter = ",";
			// Get header...
			buff_dv_chitiet.append( "BN_ID" +delimiter);
			buff_dv_chitiet.append( "MA_LK" +delimiter);
			buff_dv_chitiet.append( "DV_ID" +delimiter);
			buff_dv_chitiet.append( "MA_DICH_VU" +delimiter);
			buff_dv_chitiet.append( "MA_VAT_TU" +delimiter);
			buff_dv_chitiet.append( "MA_NHOM" +delimiter);
			buff_dv_chitiet.append( "TEN_DICH_VU" +delimiter);
			buff_dv_chitiet.append( "SO_LUONG" +delimiter);
			buff_dv_chitiet.append( "DON_GIA" +delimiter);
			buff_dv_chitiet.append( "DON_GIA2" +delimiter);
			buff_dv_chitiet.append( "THANH_TIEN" +delimiter);
			buff_dv_chitiet.append( "THANH_TIEN2" +delimiter);
			buff_dv_chitiet.append( "TT_BH" +delimiter);
			buff_dv_chitiet.append( "TT_NB" +delimiter);
			buff_dv_chitiet.append( "MA_KHOA" +delimiter);
			buff_dv_chitiet.append( "MA_BAC_SI" +delimiter);
			buff_dv_chitiet.append( "MA_BENH" +delimiter);
			buff_dv_chitiet.append( "NGAY_YL" +delimiter);
			buff_dv_chitiet.append( "NGAY_KQ" +delimiter);
			buff_dv_chitiet.append( "MA_PTTT" +delimiter);
			buff_dv_chitiet.append( "TYLE_TT" +delimiter);
			buff_dv_chitiet.append( "CUR_DATE" +delimiter);
			buff_dv_chitiet.append( "TYP" +delimiter);
			buff_dv_chitiet.append( "THANHTOAN" +delimiter);
			buff_dv_chitiet.append( "STS" +delimiter);
			// End of header
			buff_dv_chitiet.append( "\n");
			// Get data...
			for( DvChitiet obj:  listDataDvChitiet){
				buff_dv_chitiet.append( obj.BN_ID +delimiter);
				buff_dv_chitiet.append( obj.MA_LK +delimiter);
				buff_dv_chitiet.append( obj.DV_ID +delimiter);
				buff_dv_chitiet.append( obj.MA_DICH_VU +delimiter);
				buff_dv_chitiet.append( obj.MA_VAT_TU +delimiter);
				buff_dv_chitiet.append( obj.MA_NHOM +delimiter);
				buff_dv_chitiet.append( obj.TEN_DICH_VU +delimiter);
				buff_dv_chitiet.append( obj.SO_LUONG +delimiter);
				buff_dv_chitiet.append( obj.DON_GIA +delimiter);
				buff_dv_chitiet.append( obj.DON_GIA2 +delimiter);
				buff_dv_chitiet.append( obj.THANH_TIEN +delimiter);
				buff_dv_chitiet.append( obj.THANH_TIEN2 +delimiter);
				buff_dv_chitiet.append( obj.TT_BHTT +delimiter);
				buff_dv_chitiet.append( obj.TT_BNTT +delimiter);
				buff_dv_chitiet.append( obj.MA_KHOA +delimiter);
				buff_dv_chitiet.append( obj.MA_BAC_SI +delimiter);
				buff_dv_chitiet.append( obj.MA_BENH +delimiter);
				buff_dv_chitiet.append( obj.NGAY_YL +delimiter);
				buff_dv_chitiet.append( obj.NGAY_KQ +delimiter);
				buff_dv_chitiet.append( obj.MA_PTTT +delimiter);
				buff_dv_chitiet.append( obj.MUC_HUONG +delimiter);
				buff_dv_chitiet.append( obj.CUR_DATE +delimiter);
				buff_dv_chitiet.append( obj.TYP +delimiter);
				buff_dv_chitiet.append( obj.THANHTOAN +delimiter);
				buff_dv_chitiet.append( obj.STS +delimiter);
				
				// End of header
				buff_dv_chitiet.append( "\n");
			}
			//
			Writer out = null;
			try {
				out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(dv_chitiet_filename), "UTF-8"));
				out.write('\uFEFF'); // BOM for UTF-*
			    out.write(buff_dv_chitiet.toString());
			} 
			catch(Exception ee){
				ee.printStackTrace();
			}
			finally {
			    try {
					out.close();
				} catch (Exception e) {
					e.printStackTrace();
					logger.error(e);
				}
			}
			//
            return;
        }
		// End of export
	}
	
	protected void reloadTableKhamBenh() {
        if(DbHelper.checkPhanQuyen(DbHelper.READ, "kham_benh")==false){
			logger.info("DON'T HAVE READ RIGHT");
			return;
		}
    
        if(listDataKhamBenh!=null){
            // 
            tableViewerKhamBenh.setInput(listDataKhamBenh);
            tableViewerKhamBenh.refresh();
            //
            if(listDataKhamBenh.size()==0){
                //textSearchKhamBenh.forceFocus();
            }
            else{
                tableKhamBenh.forceFocus();
                tableKhamBenh.setSelection(0);
            }
            return;
        }
		// Do search in the first time
		//String searchString = textSearchKhamBenh.getText().toLowerCase().trim();
		String sql = "select * from kham_benh WHERE STS<> "+DbHelper.DELETE_STATUS+" ";
		sql += " and KB_DATE >= STR_TO_DATE('"+datePickerFrom.getDate()+"', '%d/%m/%Y')";
		sql += " and KB_DATE <= STR_TO_DATE('"+datePickerTo.getDate()+"', '%d/%m/%Y') + INTERVAL 1 DAY";
		sql += " order by KB_DATE desc";
		try  {
            logger.info(sql);
			Connection con = DbHelper.getSql2o();
			listDataKhamBenh = con.createQuery(sql).executeAndFetch(KhamBenh.class);
	    }   
	    catch(Exception e){
	    	logger.error(e);
	    	MessageDialog.openError(shlOpenClinic, "Error", e.getMessage());
	    }
		// 
		tableViewerKhamBenh.setInput(listDataKhamBenh);
		tableViewerKhamBenh.refresh();
        //
        if(listDataKhamBenh.size()==0){
            //textSearchKhamBenh.forceFocus();
        }
        else{
            tableKhamBenh.forceFocus();
            tableKhamBenh.setSelection(0);
        }
	}
	
	protected void exportExcelTableKhamBenh() {
        if(DbHelper.checkPhanQuyen(DbHelper.READ, "kham_benh")==false){
			logger.info("DON'T HAVE READ RIGHT");
			return;
		}
        if(listDataKhamBenh!=null){
            // Export to EXCEL
    		
			StringBuffer buff_kham_benh = new StringBuffer();
			String kham_benh_filename = "kham_benh_"+Utils.getDatetimeCurent().replaceAll(":", "_")+".csv";
			String delimiter = ",";
			// Get header...
			buff_kham_benh.append( "STT" +delimiter);
			buff_kham_benh.append( "BN_ID" +delimiter);
			buff_kham_benh.append( "TEN_BENH_NHAN" +delimiter);
			buff_kham_benh.append( "TEN_BENH" +delimiter);
			buff_kham_benh.append( "MA_BENH" +delimiter);
			buff_kham_benh.append( "MA_BENHKHAC" +delimiter);
			buff_kham_benh.append( "MA_LYDO_VVIEN" +delimiter);
			buff_kham_benh.append( "MA_NOI_CHUYEN" +delimiter);
			buff_kham_benh.append( "MA_TAI_NAN" +delimiter);
			buff_kham_benh.append( "NGAY_VAO" +delimiter);
			buff_kham_benh.append( "NGAY_RA" +delimiter);
			buff_kham_benh.append( "SO_NGAY_DTRI" +delimiter);
			buff_kham_benh.append( "KET_QUA_DTRI" +delimiter);
			buff_kham_benh.append( "TINH_TRANG_RV" +delimiter);
			buff_kham_benh.append( "NGAY_TTOAN" +delimiter);
			buff_kham_benh.append( "MUC_HUONG" +delimiter);
			buff_kham_benh.append( "T_THUOC" +delimiter);
			buff_kham_benh.append( "T_VTYT" +delimiter);
			buff_kham_benh.append( "T_TONGCHI" +delimiter);
			buff_kham_benh.append( "T_BNTT" +delimiter);
			buff_kham_benh.append( "T_BHTT" +delimiter);
			buff_kham_benh.append( "T_NGUONKHAC" +delimiter);
			buff_kham_benh.append( "T_NGOAIDS" +delimiter);
			buff_kham_benh.append( "NAM_QT" +delimiter);
			buff_kham_benh.append( "THANG_QT" +delimiter);
			buff_kham_benh.append( "MA_LOAI_KCB" +delimiter);
			buff_kham_benh.append( "MA_KHOA" +delimiter);
			buff_kham_benh.append( "MA_CSKCB" +delimiter);
			buff_kham_benh.append( "MA_KHUVUC" +delimiter);
			buff_kham_benh.append( "MA_PTTT_QT" +delimiter);
			buff_kham_benh.append( "CAN_NANG" +delimiter);
			buff_kham_benh.append( "KB_DATE" +delimiter);
			buff_kham_benh.append( "KIEU_TT" +delimiter);
			buff_kham_benh.append( "CHANDOAN_BD" +delimiter);
			buff_kham_benh.append( "NV_ID" +delimiter);
			buff_kham_benh.append( "NV_NAME" +delimiter);
			buff_kham_benh.append( "TABLE_ID" +delimiter);
			buff_kham_benh.append( "STS" +delimiter);
			// End of header
			buff_kham_benh.append( "\n");
			// Get data...
			for( KhamBenh obj:  listDataKhamBenh){
				buff_kham_benh.append( obj.STT +delimiter);
				buff_kham_benh.append( obj.BN_ID +delimiter);
				buff_kham_benh.append( obj.TEN_BENH_NHAN +delimiter);
				buff_kham_benh.append( obj.TEN_BENH +delimiter);
				buff_kham_benh.append( obj.MA_BENH +delimiter);
				buff_kham_benh.append( obj.MA_BENHKHAC +delimiter);
				buff_kham_benh.append( obj.MA_LYDO_VVIEN +delimiter);
				buff_kham_benh.append( obj.MA_NOI_CHUYEN +delimiter);
				buff_kham_benh.append( obj.MA_TAI_NAN +delimiter);
				buff_kham_benh.append( obj.NGAY_VAO +delimiter);
				buff_kham_benh.append( obj.NGAY_RA +delimiter);
				buff_kham_benh.append( obj.SO_NGAY_DTRI +delimiter);
				buff_kham_benh.append( obj.KET_QUA_DTRI +delimiter);
				buff_kham_benh.append( obj.TINH_TRANG_RV +delimiter);
				buff_kham_benh.append( obj.NGAY_TTOAN +delimiter);
				buff_kham_benh.append( obj.MUC_HUONG +delimiter);
				buff_kham_benh.append( obj.T_THUOC +delimiter);
				buff_kham_benh.append( obj.T_VTYT +delimiter);
				buff_kham_benh.append( obj.T_TONGCHI +delimiter);
				buff_kham_benh.append( obj.T_BNTT +delimiter);
				buff_kham_benh.append( obj.T_BHTT +delimiter);
				buff_kham_benh.append( obj.T_NGUONKHAC +delimiter);
				buff_kham_benh.append( obj.T_NGOAIDS +delimiter);
				buff_kham_benh.append( obj.NAM_QT +delimiter);
				buff_kham_benh.append( obj.THANG_QT +delimiter);
				buff_kham_benh.append( obj.MA_LOAI_KCB +delimiter);
				buff_kham_benh.append( obj.MA_KHOA +delimiter);
				buff_kham_benh.append( obj.MA_CSKCB +delimiter);
				buff_kham_benh.append( obj.MA_KHUVUC +delimiter);
				buff_kham_benh.append( obj.MA_PTTT_QT +delimiter);
				buff_kham_benh.append( obj.CAN_NANG +delimiter);
				buff_kham_benh.append( obj.KB_DATE +delimiter);
				buff_kham_benh.append( obj.KIEU_TT +delimiter);
				buff_kham_benh.append( obj.CHANDOAN_BD +delimiter);
				buff_kham_benh.append( obj.NV_ID +delimiter);
				buff_kham_benh.append( obj.NV_NAME +delimiter);
				buff_kham_benh.append( obj.TABLE_ID +delimiter);
				buff_kham_benh.append( obj.STS +delimiter);
				
				// End of header
				buff_kham_benh.append( "\n");
			}
			//
			Writer out = null;
			try {
				out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(kham_benh_filename), "UTF-8"));
				out.write('\uFEFF'); // BOM for UTF-*
			    out.write(buff_kham_benh.toString());
			} 
			catch(Exception ee){
				ee.printStackTrace();
			}
			finally {
			    try {
					out.close();
				} catch (Exception e) {
					e.printStackTrace();
					logger.error(e);
				}
			}
			//
            return;
        }
		// End of export
	}
	
	protected void exportExcelTableThuocChitiet() {
        if(DbHelper.checkPhanQuyen(DbHelper.READ, "thuoc_chitiet")==false){
			logger.info("DON'T HAVE READ RIGHT");
			return;
		}
        if(listDataThuocChitiet!=null){
            // Export to EXCEL
    		
			StringBuffer buff_thuoc_chitiet = new StringBuffer();
			String thuoc_chitiet_filename = "thuoc_chitiet_"+Utils.getDatetimeCurent().replaceAll(":", "_")+".xls";
			String delimiter = "</td><td>";
			// Get header...
			// Get header...
			buff_thuoc_chitiet.append( "<table>");
			buff_thuoc_chitiet.append( "<tr class='background-color:#dfdfdf'><td>");

			buff_thuoc_chitiet.append( "STT" +delimiter);
			buff_thuoc_chitiet.append( "MA_LK" +delimiter);
			buff_thuoc_chitiet.append( "THUOC_ID" +delimiter);
			buff_thuoc_chitiet.append( "MA_THUOC" +delimiter);
			buff_thuoc_chitiet.append( "MA_NHOM" +delimiter);
			buff_thuoc_chitiet.append( "TEN_THUOC" +delimiter);
			buff_thuoc_chitiet.append( "DON_VI_TINH" +delimiter);
			buff_thuoc_chitiet.append( "HAM_LUONG" +delimiter);
			buff_thuoc_chitiet.append( "DUONG_DUNG" +delimiter);
			buff_thuoc_chitiet.append( "LIEU_DUNG" +delimiter);
			buff_thuoc_chitiet.append( "SO_DANG_KY" +delimiter);
			buff_thuoc_chitiet.append( "SO_LUONG" +delimiter);
			buff_thuoc_chitiet.append( "DON_GIA" +delimiter);
			buff_thuoc_chitiet.append( "THANH_TIEN" +delimiter);
			buff_thuoc_chitiet.append( "MA_KHOA" +delimiter);
			buff_thuoc_chitiet.append( "MA_BAC_SI" +delimiter);
			buff_thuoc_chitiet.append( "MA_BENH" +delimiter);
			buff_thuoc_chitiet.append( "MA_PTTT" +delimiter);
			buff_thuoc_chitiet.append( "TYLE_TT" +delimiter);
			buff_thuoc_chitiet.append( "CT_ID" +delimiter);
			buff_thuoc_chitiet.append( "NT_ID" +delimiter);
			buff_thuoc_chitiet.append( "TT_BH" +delimiter);
			buff_thuoc_chitiet.append( "TT_NB" +delimiter);
			buff_thuoc_chitiet.append( "KHO_NAME" +delimiter);
			buff_thuoc_chitiet.append( "CUR_DATE" +delimiter);
			buff_thuoc_chitiet.append( "TYP" +delimiter);
			buff_thuoc_chitiet.append( "THANHTOAN" +delimiter);
			buff_thuoc_chitiet.append( "STS");
			// End of header
			buff_thuoc_chitiet.append( "</td></tr>");
			buff_thuoc_chitiet.append( "\n");
			// Get data...
			for( ThuocChitiet obj:  listDataThuocChitiet){
				buff_thuoc_chitiet.append( "<tr><td>");
				buff_thuoc_chitiet.append( obj.STT +delimiter);
				buff_thuoc_chitiet.append( obj.MA_LK +delimiter);
				buff_thuoc_chitiet.append( obj.THUOC_ID +delimiter);
				buff_thuoc_chitiet.append( obj.MA_THUOC +delimiter);
				buff_thuoc_chitiet.append( obj.MA_NHOM +delimiter);
				buff_thuoc_chitiet.append( obj.TEN_THUOC +delimiter);
				buff_thuoc_chitiet.append( obj.DON_VI_TINH +delimiter);
				buff_thuoc_chitiet.append( obj.HAM_LUONG +delimiter);
				buff_thuoc_chitiet.append( obj.DUONG_DUNG +delimiter);
				buff_thuoc_chitiet.append( obj.LIEU_DUNG +delimiter);
				buff_thuoc_chitiet.append( obj.SO_DANG_KY +delimiter);
				buff_thuoc_chitiet.append( obj.SO_LUONG +delimiter);
				buff_thuoc_chitiet.append( obj.DON_GIA +delimiter);
				buff_thuoc_chitiet.append( obj.THANH_TIEN +delimiter);
				buff_thuoc_chitiet.append( obj.MA_KHOA +delimiter);
				buff_thuoc_chitiet.append( obj.MA_BAC_SI +delimiter);
				buff_thuoc_chitiet.append( obj.MA_BENH +delimiter);
				buff_thuoc_chitiet.append( obj.MA_PTTT +delimiter);
				buff_thuoc_chitiet.append( obj.MUC_HUONG +delimiter);
				buff_thuoc_chitiet.append( obj.CT_ID +delimiter);
				buff_thuoc_chitiet.append( obj.NT_ID +delimiter);
				buff_thuoc_chitiet.append( obj.TT_BHTT +delimiter);
				buff_thuoc_chitiet.append( obj.TT_BNTT +delimiter);
				buff_thuoc_chitiet.append( obj.KHO_NAME +delimiter);
				buff_thuoc_chitiet.append( obj.CUR_DATE +delimiter);
				buff_thuoc_chitiet.append( obj.TYP +delimiter);
				buff_thuoc_chitiet.append( obj.THANHTOAN +delimiter);
				buff_thuoc_chitiet.append( obj.STS );
				// End of header
				buff_thuoc_chitiet.append( "</td></tr>");
			}
			//
			buff_thuoc_chitiet.append( "</table>");
			Writer out = null;
			try {
				out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(thuoc_chitiet_filename), "UTF-8"));
				out.write('\uFEFF'); // BOM for UTF-*
			    out.write(buff_thuoc_chitiet.toString());
			} 
			catch(Exception ee){
				ee.printStackTrace();
			}
			finally {
			    try {
					out.close();
				} catch (Exception e) {
					e.printStackTrace();
					logger.error(e);
				}
			}
			//
            return;
        }
		// End of export
	}
	
	protected void reloadTableThuocChitiet() {
        if(DbHelper.checkPhanQuyen(DbHelper.READ, "thuoc_chitiet")==false){
			logger.info("DON'T HAVE READ RIGHT");
			return;
		}
    
        if(listDataThuocChitiet!=null){
            // 
            tableViewerThuocChitiet.setInput(listDataThuocChitiet);
            tableViewerThuocChitiet.refresh();
            //
            return;
        }
		// Do search in the first time
		String sql = "select * from thuoc_chitiet WHERE STS<> "+DbHelper.DELETE_STATUS+" ";
		sql += " and CUR_DATE >= STR_TO_DATE('"+datePickerFrom.getDate()+"', '%d/%m/%Y')";
		sql += " and CUR_DATE <= STR_TO_DATE('"+datePickerTo.getDate()+"', '%d/%m/%Y') + INTERVAL 1 DAY";
		sql += " order by CUR_DATE desc";
		//
		try  {
            logger.info(sql);
			Connection con = DbHelper.getSql2o();
			listDataThuocChitiet = con.createQuery(sql).executeAndFetch(ThuocChitiet.class);
	    }   
	    catch(Exception e){
	    	logger.error(e);
	    	MessageDialog.openError(shlOpenClinic, "Error", e.getMessage());
	    }
		// 
		tableViewerThuocChitiet.setInput(listDataThuocChitiet);
		tableViewerThuocChitiet.refresh();
        //
        if(listDataThuocChitiet.size()==0){
            //textSearchThuocChitiet.forceFocus();
        }
        else{
            tableThuocChitiet.forceFocus();
            tableThuocChitiet.setSelection(0);
        }
	}
}