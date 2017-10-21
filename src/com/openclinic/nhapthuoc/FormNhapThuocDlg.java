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
import com.model.dao.Vendor;
import com.model.dao.VendorListDlg;
import com.openclinic.khambenh.FormCtNhapThuocDlg;
import com.openclinic.utils.Utils;

import org.eclipse.swt.layout.GridLayout;
import org.sql2o.Connection;
import org.eclipse.swt.widgets.Combo;

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
	private Text txtNGAY_NHAP;
	private Text txtTENKHO;
	private Text txtHOADON;

	public NhapThuoc objNhapThuoc;
	protected Khohang objKhohang;
	private Button btnPrint;
	private Button btnCancel;
	private Button btnSave;
	private static Text txt_VID;
	protected static Vendor objVendor;

	
    public int intTypeDlgNhapThuoc = 2;
	public static final int TYPE_DLG_VIEW = 1;
	public static final int TYPE_DLG_EDIT = 2;
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
		shellNhapThuoc.setSize(877, 485);
		shellNhapThuoc.setText("Nhập Thuốc");
		shellNhapThuoc.setLayout(new FormLayout());

		Composite compositeTableHeader = new Composite(shellNhapThuoc, SWT.NONE);
		compositeTableHeader.setLayout(new GridLayout(2, false));
		FormData fd_compositeTableHeader = new FormData();
		fd_compositeTableHeader.top = new FormAttachment(0);
		fd_compositeTableHeader.left = new FormAttachment(0, 10);
		fd_compositeTableHeader.right = new FormAttachment(100, -547);
		compositeTableHeader.setLayoutData(fd_compositeTableHeader);
		Label lbltxtV_ID = new Label(compositeTableHeader, SWT.NONE);
		lbltxtV_ID
				.setFont(SWTResourceManager.getFont("Tahoma", 12, SWT.NORMAL));
		lbltxtV_ID.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false,
				false, 1, 1));
		lbltxtV_ID.setText("Nhà cung cấp");

		txt_VID = new Text(compositeTableHeader, SWT.BORDER);
		txt_VID.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.keyCode == 13) {
					// Search NCC
					VendorListDlg dlg = new VendorListDlg(shellNhapThuoc, 0);
					dlg.typeVendorDlg = VendorListDlg.TYPE_DLG_CHOOSEN;
					dlg.setDataVendor(txt_VID.getText());
					dlg.open();
					if (dlg.objVendor != null) {
						objVendor = dlg.objVendor;
						txt_VID.setText(objVendor.V_NAME);
					}
					//
				}
			}
		});
		txt_VID.setFont(SWTResourceManager.getFont("Tahoma", 12, SWT.NORMAL));
		txt_VID.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false,
				1, 1));

		Label lbltxtNGAY_NHAP = new Label(compositeTableHeader, SWT.NONE);
		lbltxtNGAY_NHAP.setFont(SWTResourceManager.getFont("Tahoma", 12,
				SWT.NORMAL));
		lbltxtNGAY_NHAP.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER,
				false, false, 1, 1));
		lbltxtNGAY_NHAP.setText("Ngày nhập");

		txtNGAY_NHAP = new Text(compositeTableHeader, SWT.BORDER);
		txtNGAY_NHAP.setEditable(false);
		txtNGAY_NHAP.setFont(SWTResourceManager.getFont("Tahoma", 12,
				SWT.NORMAL));
		txtNGAY_NHAP.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
				false, 1, 1));
		txtNGAY_NHAP.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPress(e);
			}
		});
		Label lbltxtTENKHO = new Label(compositeTableHeader, SWT.NONE);
		lbltxtTENKHO.setFont(SWTResourceManager.getFont("Tahoma", 12,
				SWT.NORMAL));
		lbltxtTENKHO.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false,
				false, 1, 1));
		lbltxtTENKHO.setText("Tên kho");

		txtTENKHO = new Text(compositeTableHeader, SWT.BORDER);
		txtTENKHO.setFont(SWTResourceManager.getFont("Tahoma", 12, SWT.NORMAL));
		txtTENKHO.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false,
				1, 1));
		txtTENKHO.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.keyCode == 13) {
					// Search NCC
					KhohangListDlg dlg = new KhohangListDlg(shellNhapThuoc, 0);
					dlg.typeKhohangDlg = KhohangListDlg.TYPE_DLG_CHOOSEN;
					dlg.setDataKhohang(txtTENKHO.getText());
					dlg.open();
					if (dlg.objKhohang != null) {
						objKhohang = dlg.objKhohang;
						txtTENKHO.setText(objKhohang.KHO_NAME);
					}
					//
				}
			}
		});
		Label lbltxtHOADON = new Label(compositeTableHeader, SWT.NONE);
		lbltxtHOADON.setFont(SWTResourceManager.getFont("Tahoma", 12,
				SWT.NORMAL));
		lbltxtHOADON.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false,
				false, 1, 1));
		lbltxtHOADON.setText("Số Hóa Đơn");

		txtHOADON = new Text(compositeTableHeader, SWT.BORDER);
		txtHOADON.setFont(SWTResourceManager.getFont("Tahoma", 12, SWT.NORMAL));
		txtHOADON.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false,
				1, 1));
		txtHOADON.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPress(e);
			}
		});

		Composite compositeInShellCtNhapthuoc = new Composite(shellNhapThuoc,
				SWT.NONE);
		fd_compositeTableHeader.bottom = new FormAttachment(
				compositeInShellCtNhapthuoc, -6);
		compositeInShellCtNhapthuoc.setLayout(new BorderLayout(0, 0));
		FormData fd_compositeInShellCtNhapthuoc = new FormData();
		fd_compositeInShellCtNhapthuoc.left = new FormAttachment(0, 10);
		fd_compositeInShellCtNhapthuoc.right = new FormAttachment(100, -10);
		fd_compositeInShellCtNhapthuoc.top = new FormAttachment(0, 153);
		compositeInShellCtNhapthuoc
				.setLayoutData(fd_compositeInShellCtNhapthuoc);

		Composite compositeHeaderCtNhapthuoc = new Composite(
				compositeInShellCtNhapthuoc, SWT.NONE);
		compositeHeaderCtNhapthuoc.setLayoutData(BorderLayout.NORTH);
		compositeHeaderCtNhapthuoc.setLayout(new GridLayout(1, false));

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

		tableViewerCtNhapthuoc
				.setLabelProvider(new TableLabelProviderCtNhapthuoc());
		tableViewerCtNhapthuoc
				.setContentProvider(new ContentProviderCtNhapthuoc());

		Composite compositeFooter = new Composite(shellNhapThuoc, SWT.NONE);
		fd_compositeInShellCtNhapthuoc.bottom = new FormAttachment(100, -76);
		compositeFooter.setLayout(new FormLayout());
		FormData fd_compositeFooter = new FormData();
		fd_compositeFooter.left = new FormAttachment(0, 10);
		fd_compositeFooter.right = new FormAttachment(100, -10);
		fd_compositeFooter.top = new FormAttachment(
				compositeInShellCtNhapthuoc, 6);
		fd_compositeFooter.bottom = new FormAttachment(100, -10);
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

	private void saveDlg() {
		if(TYPE_DLG_VIEW==intTypeDlgNhapThuoc){
			return;
		}
		if(objVendor==null){
			txt_VID.forceFocus();
			txt_VID.setBackground(SWTResourceManager.getColor(SWT.COLOR_RED));
			return;
		}
		else{
			txt_VID.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		}
		if(objKhohang==null){
			txtTENKHO.forceFocus();
			txtTENKHO.setBackground(SWTResourceManager.getColor(SWT.COLOR_RED));
			return;
		}
		else{
			txtTENKHO.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		}
		//
		if (objNhapThuoc == null) {
			objNhapThuoc = new NhapThuoc();
		}
		if (objNhapThuoc != null) {
			// Integer = true
			objNhapThuoc.V_ID = objVendor.V_ID;
			//
			objNhapThuoc.VENDOR_NAME = objVendor.V_NAME;
			// Date = false
			objNhapThuoc.NGAY_NHAP = new Date();
			// String = false
			objNhapThuoc.TENKHO = txtTENKHO.getText();
			// String = false
			objNhapThuoc.HOADON = txtHOADON.getText();
			// Integer = true
			objNhapThuoc.TONGCONG = 0;
			for (CtNhapthuoc obj2 : listDataCtNhapthuoc) {
				objNhapThuoc.TONGCONG += obj2.THANHTIEN;
			}
			// Integer = true
			objNhapThuoc.STS = 0;
		}
		objNhapThuoc.insert();
		//
		for (CtNhapthuoc obj2 : listDataCtNhapthuoc) {
			obj2.NT_ID = objNhapThuoc.NT_ID;
			obj2.TENKHO = objNhapThuoc.TENKHO;
			obj2.V_ID = obj2.V_ID;
			obj2.insert();
		}
		shellNhapThuoc.close();
	}

	public void setNhapThuocDlgData(NhapThuoc obj) {
		this.objNhapThuoc = obj;
	}

	public void loadNhapThuocDlgData() {
		
		if (objNhapThuoc != null) {
			//
			objVendor = Vendor.load(objNhapThuoc.V_ID);
			objKhohang = Khohang.load(objNhapThuoc.KHO_ID);
			//
			txtNGAY_NHAP.setText("" + objNhapThuoc.NGAY_NHAP.toString());
			txtTENKHO.setText("" + objNhapThuoc.TENKHO.toString());
			txtHOADON.setText("" + objNhapThuoc.HOADON.toString());
			txt_VID.setText("" + objNhapThuoc.VENDOR_NAME.toString());
			//
			reloadTableCtNhapthuoc();
			//
		}
		//
		if(intTypeDlgNhapThuoc==TYPE_DLG_VIEW){
			btnSave.setEnabled(false);
		}
	}

	protected void keyPress(KeyEvent e) {
		if (e.keyCode == 13) {
			saveDlg();
		}

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
		//reloadTableCtNhapthuoc();
		//
		tableViewerCtNhapthuoc.refresh();
	}
}
