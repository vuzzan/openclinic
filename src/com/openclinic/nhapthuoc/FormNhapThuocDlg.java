package com.openclinic.nhapthuoc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import swing2swt.layout.BorderLayout;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.internal.win32.DLLVERSIONINFO;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Table;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Monitor;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.TableItem;

import com.DbHelper;
import com.model.dao.CtNhapthuoc;
import com.model.dao.CtNhapthuocDlg;
import com.model.dao.Khohang;
import com.model.dao.KhohangListDlg;
import com.model.dao.NhapThuoc;
import com.model.dao.NhapThuocDlg;
import com.model.dao.Thuoc;
import com.model.dao.ThuocDlg;
import com.model.dao.Vendor;
import com.model.dao.VendorListDlg;
import com.openclinic.DatePicker;
import com.openclinic.khambenh.FormCtNhapThuocDlg;
import com.openclinic.utils.Utils;

import org.eclipse.swt.layout.GridLayout;
import org.sql2o.Connection;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.ModifyEvent;

public class FormNhapThuocDlg extends Dialog {
	static Logger logger = LogManager.getLogger(FormNhapThuocDlg.class
			.getName());

	private class TableLabelProviderCtNhapthuoc extends LabelProvider implements
			ITableLabelProvider {
		public Image getColumnImage(Object element, int columnIndex) {
			return null;
		}

		public String getColumnText(Object element, int columnIndex) {
			if (element instanceof CtNhapthuoc) {
				CtNhapthuoc obj = (CtNhapthuoc) element;
				if (columnIndex == 0) {
					return obj.TENTHUOC;
				} else if (columnIndex == 1) {
					return obj.DONVI;
				} else if (columnIndex == 2) {
					return obj.SOLUONG + "";
				} else if (columnIndex == 3) {
					return Utils.getMoneyDefault(obj.DONGIA);
				} else if (columnIndex == 4) {
					return Utils.getMoneyDefault(obj.THANHTIEN);
				} else if (columnIndex == 5) {
					return Utils.getDatetimeDefault(obj.HANDUNG);
				} else if (columnIndex == 6) {
					return (obj.LOT_ID);
				}
				return "";
			}
			return "";
		}
	}

	private static class ContentProviderCtNhapthuoc implements
			IStructuredContentProvider {
		public Object[] getElements(Object inputElement) {
			if (inputElement instanceof ArrayList) {
				return ((ArrayList) inputElement).toArray();
			}
			return new Object[0];
		}

		public void dispose() {
		}

		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		}
	}

	private Table tableCtNhapthuoc;
	private TableViewer tableViewerCtNhapthuoc;
	private List<CtNhapthuoc> listDataCtNhapthuoc = new ArrayList();

	protected Object result;
	protected Shell shellNhapThuoc;

	public NhapThuoc objNhapThuoc;
	protected Khohang objKhohang;
	private Button btnPrint;
	private Button btnCancel;
	private Button btnSave;
	protected static Vendor objVendor;
    private DatePicker txtNGAY_HD;
    private Combo txtTENKHO;
    private Text txtSO_HOA_DON;
    private Text txtKH_HOA_DON;
    private Text txtTONGCONG;
    private Text txtTONGCONG_VAT;
    private Text txtVAT;
    public int intTypeDlgNhapThuoc = 2;
	public static final int TYPE_DLG_VIEW = 1;
	public static final int TYPE_DLG_EDIT = 2;

    public Thuoc objThuoc;
	private Combo txtVENDOR_NAME;

	// END ADD THUOC
	/**
	 * Create the dialog.
	 * 
	 * @param parent
	 * @param style
	 */
	public FormNhapThuocDlg(Shell parent, int style) {
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
		shellNhapThuoc.open();
		shellNhapThuoc.layout();
		Display display = getParent().getDisplay();

		//
		Monitor primary = display.getPrimaryMonitor();
		Rectangle bounds = primary.getBounds();
		Rectangle rect = shellNhapThuoc.getBounds();
		int x = bounds.x + (bounds.width - rect.width) / 2;
		int y = bounds.y + (bounds.height - rect.height) / 2;
		shellNhapThuoc.setLocation(x, y);
		//
		while (!shellNhapThuoc.isDisposed()) {
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
		shellNhapThuoc = new Shell(getParent(), SWT.DIALOG_TRIM | SWT.MIN
				| SWT.MAX);
		shellNhapThuoc.setSize(942, 604);
		shellNhapThuoc.setText("Nhập Thuốc");
		shellNhapThuoc.setLayout(new FormLayout());

		Composite compositeTableHeader = new Composite(shellNhapThuoc, SWT.NONE);
		compositeTableHeader.setLayout(new GridLayout(6, false));
		FormData fd_compositeTableHeader = new FormData();
		fd_compositeTableHeader.top = new FormAttachment(0);
		fd_compositeTableHeader.left = new FormAttachment(0, 10);
		fd_compositeTableHeader.right = new FormAttachment(100, -10);
		compositeTableHeader.setLayoutData(fd_compositeTableHeader);
		Label lbltxtVENDOR_NAME = new Label(compositeTableHeader, SWT.NONE);
		lbltxtVENDOR_NAME.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtVENDOR_NAME.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtVENDOR_NAME.setText("CÔNG TY :");
		
		txtVENDOR_NAME = new Combo(compositeTableHeader, SWT.NONE);
		txtVENDOR_NAME.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				objVendor = DbHelper.hashDataVendor.get(txtVENDOR_NAME.getText());
			}
		});
		txtVENDOR_NAME.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		Label lbltxtTENKHO = new Label(compositeTableHeader, SWT.NONE);
		lbltxtTENKHO.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtTENKHO.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtTENKHO.setText("TÊN KHO :");
		
		txtTENKHO = new Combo(compositeTableHeader, SWT.BORDER);
		txtTENKHO.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtTENKHO.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		txtTENKHO.setText("TENKHO");
		txtTENKHO.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				objKhohang = DbHelper.hashDataKhoHang.get(txtTENKHO.getText());
			}
		});
		txtTENKHO.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressNhapThuocDlg(e);
			}
		});
		Label lbltxtNGAY_HD = new Label(compositeTableHeader, SWT.NONE);
		lbltxtNGAY_HD.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtNGAY_HD.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtNGAY_HD.setText("NGÀY HĐ :");
		
		txtNGAY_HD = new DatePicker(compositeTableHeader, SWT.BORDER);
		txtNGAY_HD.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtNGAY_HD.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		txtNGAY_HD.setText("");
		txtNGAY_HD.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressNhapThuocDlg(e);
			}
		});
		
		Label lbltxtSO_HOA_DON = new Label(compositeTableHeader, SWT.NONE);
		lbltxtSO_HOA_DON.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtSO_HOA_DON.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtSO_HOA_DON.setText("SO_HOA_DON :");
		
		txtSO_HOA_DON = new Text(compositeTableHeader, SWT.BORDER | SWT.RIGHT);
		txtSO_HOA_DON.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtSO_HOA_DON.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		txtSO_HOA_DON.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressNhapThuocDlg(e);
			}
		});
		Label lbltxtKH_HOA_DON = new Label(compositeTableHeader, SWT.NONE);
		lbltxtKH_HOA_DON.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtKH_HOA_DON.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtKH_HOA_DON.setText("KÝ HIỆU HĐ :");
		
		txtKH_HOA_DON = new Text(compositeTableHeader, SWT.BORDER | SWT.RIGHT);
		txtKH_HOA_DON.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtKH_HOA_DON.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		txtKH_HOA_DON.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressNhapThuocDlg(e);
			}
		});
		new Label(compositeTableHeader, SWT.NONE);
		new Label(compositeTableHeader, SWT.NONE);
		Label lbltxtTONGCONG = new Label(compositeTableHeader, SWT.NONE);
		lbltxtTONGCONG.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtTONGCONG.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtTONGCONG.setText("TONGCONG :");
		
		txtTONGCONG = new Text(compositeTableHeader, SWT.BORDER | SWT.RIGHT);
		txtTONGCONG.setEditable(false);
		txtTONGCONG.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtTONGCONG.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		txtTONGCONG.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressNhapThuocDlg(e);
			}
		});
		Label lbltxtVAT = new Label(compositeTableHeader, SWT.NONE);
		lbltxtVAT.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtVAT.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtVAT.setText("VAT %:");
		
		txtVAT = new Text(compositeTableHeader, SWT.BORDER | SWT.RIGHT);
		txtVAT.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				int vat = Utils.getInt(txtVAT.getText());
				if(vat<=0){
					txtVAT.setBackground(SWTResourceManager.getColor(SWT.COLOR_RED));
				}
				else{
					txtVAT.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
					//
					if(objNhapThuoc!=null){
						doTinhTien();
					}
					//
				}
						
			}
		});
		txtVAT.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtVAT.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		txtVAT.setText("10");
		txtVAT.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressNhapThuocDlg(e);
			}
		});
		Label lbltxtTONGCONG_VAT = new Label(compositeTableHeader, SWT.NONE);
		lbltxtTONGCONG_VAT.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtTONGCONG_VAT.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtTONGCONG_VAT.setText("TONGCONG_VAT :");
		
		txtTONGCONG_VAT = new Text(compositeTableHeader, SWT.BORDER | SWT.RIGHT);
		txtTONGCONG_VAT.setEditable(false);
		txtTONGCONG_VAT.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtTONGCONG_VAT.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		txtTONGCONG_VAT.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressNhapThuocDlg(e);
			}
		});

		Composite compositeInShellCtNhapthuoc = new Composite(shellNhapThuoc,
				SWT.NONE);
		fd_compositeTableHeader.bottom = new FormAttachment(100, -482);
		compositeInShellCtNhapthuoc.setLayout(new BorderLayout(0, 0));
		FormData fd_compositeInShellCtNhapthuoc = new FormData();
		fd_compositeInShellCtNhapthuoc.top = new FormAttachment(compositeTableHeader, 6);
		fd_compositeInShellCtNhapthuoc.left = new FormAttachment(0, 10);
		fd_compositeInShellCtNhapthuoc.right = new FormAttachment(100, -10);
		compositeInShellCtNhapthuoc
				.setLayoutData(fd_compositeInShellCtNhapthuoc);
		

		tableViewerCtNhapthuoc = new TableViewer(compositeInShellCtNhapthuoc,
				SWT.BORDER | SWT.FULL_SELECTION);
		tableCtNhapthuoc = tableViewerCtNhapthuoc.getTable();
		tableCtNhapthuoc.setFont(SWTResourceManager.getFont("Tahoma", 11,
				SWT.NORMAL));
		tableCtNhapthuoc.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.keyCode == SWT.F5) {
					reloadTableCtNhapthuoc();
				} else if (e.keyCode == 13) {
					selectTableCtNhapthuoc();
				} else if (e.keyCode == SWT.DEL) {
					deleteTableCtNhapthuoc();
				} else if (e.keyCode == SWT.F7) {
					newItemCtNhapthuoc();
				}
			}
		});
		tableCtNhapthuoc.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				selectTableCtNhapthuoc();
			}
		});

		tableCtNhapthuoc.setLinesVisible(true);
		tableCtNhapthuoc.setHeaderVisible(true);
		tableCtNhapthuoc.setLayoutData(BorderLayout.CENTER);

		TableColumn tbTableColumnCtNhapthuocTENTHUOC = new TableColumn(
				tableCtNhapthuoc, SWT.LEFT);
		tbTableColumnCtNhapthuocTENTHUOC.setWidth(286);
		tbTableColumnCtNhapthuocTENTHUOC.setText("Tên Thuốc");

		TableColumn tbTableColumnCtNhapthuocDONVI = new TableColumn(
				tableCtNhapthuoc, SWT.LEFT);
		tbTableColumnCtNhapthuocDONVI.setWidth(66);
		tbTableColumnCtNhapthuocDONVI.setText("ĐV Tính");

		TableColumn tbTableColumnCtNhapthuocSOLUONG = new TableColumn(
				tableCtNhapthuoc, SWT.RIGHT);
		tbTableColumnCtNhapthuocSOLUONG.setWidth(77);
		tbTableColumnCtNhapthuocSOLUONG.setText("Số Lượng");

		TableColumn tbTableColumnCtNhapthuocDONGIA = new TableColumn(
				tableCtNhapthuoc, SWT.RIGHT);
		tbTableColumnCtNhapthuocDONGIA.setWidth(72);
		tbTableColumnCtNhapthuocDONGIA.setText("Đơn giá");

		TableColumn tbTableColumnCtNhapthuocTHANHTIEN = new TableColumn(
				tableCtNhapthuoc, SWT.RIGHT);
		tbTableColumnCtNhapthuocTHANHTIEN.setWidth(90);
		tbTableColumnCtNhapthuocTHANHTIEN.setText("Thành tiền");

		TableColumn tbTableColumnCtNhapthuocHANDUNG = new TableColumn(
				tableCtNhapthuoc, SWT.NONE);
		tbTableColumnCtNhapthuocHANDUNG.setWidth(107);
		tbTableColumnCtNhapthuocHANDUNG.setText("Hạn dùng");

		Menu menuCtNhapthuoc = new Menu(tableCtNhapthuoc);
		tableCtNhapthuoc.setMenu(menuCtNhapthuoc);

		MenuItem mntmNewItemCtNhapthuoc = new MenuItem(menuCtNhapthuoc,
				SWT.NONE);
		mntmNewItemCtNhapthuoc.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				newItemCtNhapthuoc();
			}
		});
		mntmNewItemCtNhapthuoc.setImage(SWTResourceManager.getImage(
				CtNhapthuocDlg.class, "/png/arrow-circle-top-2x.png"));
		mntmNewItemCtNhapthuoc.setText("New");

		MenuItem mntmNewItem_1CtNhapthuoc = new MenuItem(menuCtNhapthuoc,
				SWT.NONE);
		mntmNewItem_1CtNhapthuoc.setImage(SWTResourceManager.getImage(
				CtNhapthuocDlg.class, "/png/wrench-2x.png"));
		mntmNewItem_1CtNhapthuoc.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				selectTableCtNhapthuoc();
			}
		});
		mntmNewItem_1CtNhapthuoc.setText("Edit");

		MenuItem mntmDeleteCtNhapthuoc = new MenuItem(menuCtNhapthuoc, SWT.NONE);
		mntmDeleteCtNhapthuoc.setImage(SWTResourceManager.getImage(
				CtNhapthuocDlg.class, "/png/circle-x-2x.png"));
		mntmDeleteCtNhapthuoc.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				deleteTableCtNhapthuoc();
			}
		});
		mntmDeleteCtNhapthuoc.setText("Delete");
		
		TableColumn tblclmnLotid = new TableColumn(tableCtNhapthuoc, SWT.NONE);
		tblclmnLotid.setWidth(107);
		tblclmnLotid.setText("Số Lô");

		tableViewerCtNhapthuoc
				.setLabelProvider(new TableLabelProviderCtNhapthuoc());
		tableViewerCtNhapthuoc
				.setContentProvider(new ContentProviderCtNhapthuoc());

		Composite compositeFooter = new Composite(shellNhapThuoc, SWT.NONE);
		fd_compositeInShellCtNhapthuoc.bottom = new FormAttachment(compositeFooter, -6);
		compositeFooter.setLayout(new FormLayout());
		FormData fd_compositeFooter = new FormData();
		fd_compositeFooter.bottom = new FormAttachment(100, -10);
		fd_compositeFooter.top = new FormAttachment(0, 509);
		fd_compositeFooter.left = new FormAttachment(0, 10);
		fd_compositeFooter.right = new FormAttachment(100, -10);
		compositeFooter.setLayoutData(fd_compositeFooter);

		btnSave = new Button(compositeFooter, SWT.NONE);
		btnSave.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				saveDlg();
			}
		});
		
		FormData fd_button = new FormData();
		fd_button.right = new FormAttachment(100, -285);
		btnSave.setLayoutData(fd_button);
		btnSave.setText("Lưu Phiếu (F2)");
		btnSave.setImage(SWTResourceManager.getImage(FormNhapThuocDlg.class,
				"/png/check-3x.png"));
		btnSave.setFont(SWTResourceManager.getFont("Tahoma", 12, SWT.NORMAL));

		btnPrint = new Button(compositeFooter, SWT.NONE);
		fd_button.top = new FormAttachment(btnPrint, 0, SWT.TOP);
		fd_button.left = new FormAttachment(btnPrint, 6);
		FormData fd_button_1 = new FormData();
		fd_button_1.bottom = new FormAttachment(100, -10);
		fd_button_1.right = new FormAttachment(100, -470);
		btnPrint.setLayoutData(fd_button_1);
		btnPrint.setText("In Phiếu");
		btnPrint.setImage(SWTResourceManager.getImage(FormNhapThuocDlg.class,
				"/png/print-3x.png"));
		btnPrint.setFont(SWTResourceManager.getFont("Tahoma", 12, SWT.NORMAL));

		btnCancel = new Button(compositeFooter, SWT.NONE);
		fd_button_1.left = new FormAttachment(btnCancel, 6);
		FormData fd_button_2 = new FormData();
		fd_button_2.bottom = new FormAttachment(100, -10);
		fd_button_2.left = new FormAttachment(0, 10);
		fd_button_2.right = new FormAttachment(100, -655);
		btnCancel.setLayoutData(fd_button_2);
		btnCancel.setText("Bỏ (ESC)");
		btnCancel.setImage(SWTResourceManager.getImage(FormNhapThuocDlg.class,
				"/png/circle-x-3x.png"));
		btnCancel.setFont(SWTResourceManager.getFont("Tahoma", 12, SWT.NORMAL));

		//
		if (listDataCtNhapthuoc != null) {
			tableViewerCtNhapthuoc.setInput(listDataCtNhapthuoc);
			reloadTableCtNhapthuoc();
		}
		loadNhapThuocDlgData();
	}

	protected void keyPressNhapThuocDlg(KeyEvent e) {
		if(e.keyCode==SWT.F2){
			saveDlg();
		}
		else if(e.keyCode==SWT.F9){
			printPhieuNhapThuoc();
		}
		else if(e.keyCode==SWT.ESC){
			shellNhapThuoc.close();
		}
		
	}

	private void printPhieuNhapThuoc() {
		// TODO Auto-generated method stub
		
	}

	private void saveDlg() {
		if(TYPE_DLG_VIEW==intTypeDlgNhapThuoc){
			return;
		}
		if(objVendor==null){
			txtVENDOR_NAME.forceFocus();
			txtVENDOR_NAME.setBackground(SWTResourceManager.getColor(SWT.COLOR_RED));
			return;
		}
		else{
			txtVENDOR_NAME.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		}
		if(objKhohang==null){
			txtTENKHO.forceFocus();
			txtTENKHO.setBackground(SWTResourceManager.getColor(SWT.COLOR_RED));
			return;
		}
		else{
			txtTENKHO.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		}
		if(txtSO_HOA_DON.getText().trim().length()==0){
			txtSO_HOA_DON.forceFocus();
			txtSO_HOA_DON.setBackground(SWTResourceManager.getColor(SWT.COLOR_RED));
			return;
		}
		else{
			txtSO_HOA_DON.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		}
		if(txtKH_HOA_DON.getText().trim().length()==0){
			txtKH_HOA_DON.forceFocus();
			txtKH_HOA_DON.setBackground(SWTResourceManager.getColor(SWT.COLOR_RED));
			return;
		}
		else{
			txtKH_HOA_DON.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		}
		
		if(Utils.getInt( txtTONGCONG.getText() )<=0){
			txtTONGCONG.forceFocus();
			txtTONGCONG.setBackground(SWTResourceManager.getColor(SWT.COLOR_RED));
			return;
		}
		else{
			txtTONGCONG.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		}
		if(Utils.getInt( txtVAT.getText() )<=0){
			txtVAT.selectAll();
			txtVAT.forceFocus();
			txtVAT.setBackground(SWTResourceManager.getColor(SWT.COLOR_RED));
			return;
		}
		else{
			txtVAT.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		}
		if(listDataCtNhapthuoc.size()==0){
			return;
		}
		//
		if (objNhapThuoc == null) {
			objNhapThuoc = new NhapThuoc();
		}
		if (objNhapThuoc != null) {
			// Integer = true
			objNhapThuoc.V_ID = objVendor.V_ID;
			objNhapThuoc.VENDOR_NAME = objVendor.V_NAME;
			objNhapThuoc.VENDOR_ADDR = objVendor.V_ADDR;
			// Date = false
			objNhapThuoc.NGAY_NHAP = new Date();
			objNhapThuoc.NGAY_HD = txtNGAY_HD.getDate2().getTime();
			// String = false
			objNhapThuoc.TENKHO = txtTENKHO.getText();
			objNhapThuoc.KHO_ID = objKhohang.KHO_ID;
			// String = false
			objNhapThuoc.SO_HOA_DON = txtSO_HOA_DON.getText();
			objNhapThuoc.KH_HOA_DON = txtKH_HOA_DON.getText();
			// Integer = true
			objNhapThuoc.STS = 0;
		}
		objNhapThuoc.insert();
		//
		for (CtNhapthuoc obj2 : listDataCtNhapthuoc) {
			obj2.NT_ID = objNhapThuoc.NT_ID;
			obj2.V_ID = objNhapThuoc.V_ID;
			obj2.TENKHO = objNhapThuoc.TENKHO;
			obj2.VAT = objNhapThuoc.VAT;
			//
			obj2.insert();
		}
		shellNhapThuoc.close();
	}

	public void setNhapThuocDlgData(NhapThuoc obj) {
		this.objNhapThuoc = obj;
	}

	public void loadNhapThuocDlgData() {
		txtVENDOR_NAME.removeAll();
		for(Vendor obj: DbHelper.listDataVendor){
			txtVENDOR_NAME.add(obj.V_NAME);
		}
		//
		txtTENKHO.removeAll();
		for(Khohang obj: DbHelper.listDataKhohang){
			txtTENKHO.add(obj.KHO_NAME);
		}
		//
		if (objNhapThuoc != null) {
			//
			objVendor = Vendor.load(objNhapThuoc.V_ID);
			//
			objKhohang = Khohang.load(objNhapThuoc.KHO_ID);
			//
			txtTENKHO.setText("" + objNhapThuoc.TENKHO.toString());
			txtSO_HOA_DON.setText("" + objNhapThuoc.SO_HOA_DON.toString());
			txtVENDOR_NAME.setText("" + objNhapThuoc.VENDOR_NAME.toString());
			txtTONGCONG.setText("" + objNhapThuoc.TONGCONG.toString());
			txtTONGCONG_VAT.setText("" + objNhapThuoc.TONGCONG_VAT.toString());
			txtVAT.setText("" + objNhapThuoc.VAT);
			txtNGAY_HD.setText(objNhapThuoc.NGAY_HD);
			//
			reloadTableCtNhapthuoc();
			//
		}
		//
		if(intTypeDlgNhapThuoc==TYPE_DLG_VIEW){
			btnSave.setEnabled(false);
		}
		//
		
		//
	}

	protected void reloadTableCtNhapthuoc() {
		//if(listDataCtNhapthuoc==null || listDataCtNhapthuoc.size()==0)
		{
			if (objNhapThuoc != null && objNhapThuoc.NT_ID != null) {
				String sql = "select * from ct_nhapthuoc WHERE STS<> "
						+ DbHelper.DELETE_STATUS + " and NT_ID="
						+ objNhapThuoc.NT_ID.intValue();
				try {
					logger.info(sql);
					Connection con = DbHelper.getSql2o();
					listDataCtNhapthuoc = con.createQuery(sql).executeAndFetch(
							CtNhapthuoc.class);
				} catch (Exception e) {
					logger.error(e);
					MessageDialog
							.openError(shellNhapThuoc, "Error", e.getMessage());
				}
				//
				tableViewerCtNhapthuoc.setInput(listDataCtNhapthuoc);
				tableViewerCtNhapthuoc.refresh();
				//
				if (listDataCtNhapthuoc.size() == 0) {
					//
				} else {
					tableCtNhapthuoc.forceFocus();
					tableCtNhapthuoc.setSelection(0);
				}
			}
		}
	}

	protected void selectTableCtNhapthuoc() {
		if (tableCtNhapthuoc.getSelectionCount() == 0) {
			return;
		}
		TableItem item = tableCtNhapthuoc.getSelection()[0];
		CtNhapthuoc obj = (CtNhapthuoc) item.getData();
		logger.info(obj.toString());
		//
		FormCtNhapThuocDlg dlg = new FormCtNhapThuocDlg(shellNhapThuoc, 0);
		dlg.setCtNhapthuocDlgData(obj);
		dlg.open();
		//
		//reloadTableCtNhapthuoc();
		tableViewerCtNhapthuoc.refresh();

	}

	protected void deleteTableCtNhapthuoc() {
		if (tableCtNhapthuoc.getSelectionCount() == 0) {
			return;
		}
		if (MessageDialog.openConfirm(shellNhapThuoc, "Confirm?", "Delete ?") == false) {
			return;
		}

		TableItem item = tableCtNhapthuoc.getSelection()[0];
		CtNhapthuoc obj = (CtNhapthuoc) item.getData();
		logger.info(obj.toString());
		//
		obj.delete();
		listDataCtNhapthuoc.remove(obj);
		//
		//reloadTableCtNhapthuoc();
		tableViewerCtNhapthuoc.refresh();

	}

	protected void newItemCtNhapthuoc() {
		//
		FormCtNhapThuocDlg dlg = new FormCtNhapThuocDlg(shellNhapThuoc, 0);
		CtNhapthuoc obj = new CtNhapthuoc();
		dlg.setCtNhapthuocDlgData(obj);
		dlg.open();
		listDataCtNhapthuoc.add(obj);
		//
		doTinhTien();
		//
		tableViewerCtNhapthuoc.refresh();
	}

	private void doTinhTien() {
		if(objNhapThuoc==null){
			objNhapThuoc = new NhapThuoc();
		}
		objNhapThuoc.TONGCONG = 0;
		int taxVAT = Utils.getInt(txtVAT.getText());
		objNhapThuoc.VAT = taxVAT;
		//
		for (CtNhapthuoc obj2 : listDataCtNhapthuoc) {
			objNhapThuoc.TONGCONG += obj2.THANHTIEN;
		}
		objNhapThuoc.TONGCONG_VAT =(int)(((float)taxVAT/(float)100)*objNhapThuoc.TONGCONG.intValue());
		//
		if(txtTONGCONG!=null)
			txtTONGCONG.setText(objNhapThuoc.TONGCONG.toString());
		if(txtTONGCONG_VAT!=null)
			txtTONGCONG_VAT.setText(""+ objNhapThuoc.TONGCONG_VAT);
		//
	}
}
