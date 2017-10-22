package com.openclinic.khambenh;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
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
import com.model.dao.BenhNhan;
import com.model.dao.CtNhapthuoc;
import com.model.dao.KhamBenh;
import com.model.dao.NhapThuoc;
import com.model.dao.ThuocChitiet;
import com.openclinic.nhapthuoc.FormNhapThuocDlg;
import com.openclinic.utils.Utils;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

public class FormThuocChitietListDlg extends Dialog {
	static Logger logger = LogManager.getLogger(FormThuocChitietListDlg.class
			.getName());
	protected Object result;
	protected Shell shell;

	private class TableLabelProviderThuocChitiet extends LabelProvider
			implements ITableLabelProvider {
		public Image getColumnImage(Object element, int columnIndex) {
			return null;
		}

		public String getColumnText(Object element, int columnIndex) {
			if (element instanceof ThuocChitiet) {
				// return ((ThuocChitiet) element).getIndex(columnIndex);
				ThuocChitiet obj = (ThuocChitiet) element;
				if (columnIndex == 0) {
					return "" + obj.STT;
				} else if (columnIndex == 1) {
					return "" + obj.KHO_NAME;
				} else if (columnIndex == 2) {
					return "" + obj.CT_ID;
				} else if (columnIndex == 3) {
					return "" + obj.TEN_THUOC;
				} else if (columnIndex == 4) {
					return "" + obj.DON_VI_TINH;
				} else if (columnIndex == 5) {
					return "" + obj.SO_LUONG;
				} else if (columnIndex == 6) {
					return "" + obj.DON_GIA;
				} else if (columnIndex == 7) {
					return "" + obj.THANH_TIEN;
				} else if (columnIndex == 8) {
					return "" + obj.STS;
				} else if (columnIndex == 9) {
					return "" + obj.MA_THUOC;
				} else if (columnIndex == 10) {
					return "" + obj.HAM_LUONG;
				} else if (columnIndex == 11) {
					return "" + obj.DUONG_DUNG;
				}
			}
			return "";
		}
	}

	private static class ContentProviderThuocChitiet implements
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

	private Table tableThuocChitiet;
	private TableViewer tableViewerThuocChitiet;
	private List<ThuocChitiet> listDataThuocChitiet;
	private String textSearchThuocChitietString;
	public ThuocChitiet objThuocChitiet = null;
	public int typeThuocChitietDlg = TYPE_DLG_CHOOSEN;
	public static final int TYPE_DLG_MANAGER = 0;
	public static final int TYPE_DLG_CHOOSEN = 1;
	private Text txtDIACHI;
	private Text txtHOTEN;
	private KhamBenh objKhambenh;
	private BenhNhan objBenhNhan;

	/**
	 * Create the dialog.
	 * 
	 * @param parent
	 * @param style
	 */
	public FormThuocChitietListDlg(Shell parent, int style) {
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

	protected void doXuatThuoc() {
		logger.info("BEGIN XUAT THUOC ");
		objKhambenh.NGAY_RA = Utils.getDatetime(new Date(), "yyyyMMddHHmm");
		objKhambenh.STS = Utils.PHIEUKHAM_KHAMXONG_RAVE;
		logger.info("====BEGIN UPDATE PHIEU KHAM - UPDATE STS="+objKhambenh.STS);
		objKhambenh.update();
		logger.info("====END UPDATE PHIEU KHAM - UPDATE STS="+objKhambenh.STS);
		logger.info("====BEGIN UPDATE THUOCCHITIET THUOC_KHAMXONG_DALAYTHUOC STS="+Utils.THUOC_KHAMXONG_DALAYTHUOC);
		for(ThuocChitiet obj: listDataThuocChitiet){
			logger.info("======== BEGIN UPDATE ThuocChitiet" + obj.toString());
			if(obj.STS==Utils.THUOC_KHAMXONG_CHOTHUOC){
				obj.STS = Utils.THUOC_KHAMXONG_DALAYTHUOC;
				obj.update();
				logger.info("======== END UPDATE ThuocChitiet"+ obj.toString());
				// UPDATE KHO THUOC
				CtNhapthuoc objCtNhapthuoc = CtNhapthuoc.load(obj.CT_ID);
				logger.info("======== BEGIN UPDATE KHO THUOC: " + objCtNhapthuoc.toString());
				objCtNhapthuoc.SL_OUTSTANDING = objCtNhapthuoc.SL_OUTSTANDING - obj.SO_LUONG;
				objCtNhapthuoc.SL_DADUNG  = objCtNhapthuoc.SL_DADUNG + obj.SO_LUONG;
				objCtNhapthuoc.update();
				logger.info("======== END   UPDATE KHO THUOC: " + objCtNhapthuoc.toString());
			}
			//
			// END UPDATE KHO THUOC
		}
		logger.info("====END UPDATE THUOCCHITIET THUOC_KHAMXONG_DALAYTHUOC STS="+Utils.THUOC_KHAMXONG_DALAYTHUOC);
		logger.info("END XUAT THUOC ");
		//objPhieuKhamBenh.NGAY_RA = Utils.getDatetime(new Date(), "yyyyMMddHHmm");
		shell.close();
	}

	/**
	 * Create contents of the dialog.
	 */
	private void createContents() {
		shell = new Shell(getParent(), SWT.SHELL_TRIM | SWT.BORDER
				| SWT.PRIMARY_MODAL);
		shell.setImage(SWTResourceManager.getImage(
				FormThuocChitietListDlg.class, "/png/list-2x.png"));
		shell.setSize(1289, 498);
		shell.setText("ThuocChitiet List View");
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
		lblHTn.setText("Họ Tên");
		lblHTn.setFont(SWTResourceManager.getFont("Tahoma", 12, SWT.NORMAL));
		lblHTn.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false,
				1, 1));

		txtHOTEN = new Text(compositeHeaderThuocChitiet, SWT.BORDER);
		txtHOTEN.setFont(SWTResourceManager.getFont("Tahoma", 11, SWT.NORMAL));
		txtHOTEN.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false,
				1, 1));

		Label label_3 = new Label(compositeHeaderThuocChitiet, SWT.NONE);
		label_3.setText("Địa Chỉ");
		label_3.setFont(SWTResourceManager.getFont("Tahoma", 12, SWT.NORMAL));
		label_3.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false,
				1, 1));

		txtDIACHI = new Text(compositeHeaderThuocChitiet, SWT.BORDER);
		txtDIACHI.setFont(SWTResourceManager.getFont("Tahoma", 11, SWT.NORMAL));
		txtDIACHI.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false,
				1, 1));

		tableViewerThuocChitiet = new TableViewer(compositeInShellThuocChitiet,
				SWT.BORDER | SWT.FULL_SELECTION);
		tableThuocChitiet = tableViewerThuocChitiet.getTable();
		tableThuocChitiet.setFont(SWTResourceManager.getFont("Tahoma", 11,
				SWT.NORMAL));
		tableThuocChitiet.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.keyCode == SWT.F5) {
					reloadTableThuocChitiet();
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
		tableThuocChitiet.setLayoutData(BorderLayout.CENTER);

		TableColumn tbTableColumnThuocChitietSTT = new TableColumn(
				tableThuocChitiet, SWT.RIGHT);
		tbTableColumnThuocChitietSTT.setWidth(43);
		tbTableColumnThuocChitietSTT.setText("STT");

		TableColumn tbTableColumnThuocChitietKHO_NAME = new TableColumn(
				tableThuocChitiet, SWT.LEFT);
		tbTableColumnThuocChitietKHO_NAME.setWidth(90);
		tbTableColumnThuocChitietKHO_NAME.setText("TÊN KHO");

		TableColumn tblclmnLHng = new TableColumn(tableThuocChitiet, SWT.LEFT);
		tblclmnLHng.setWidth(78);
		tblclmnLHng.setText("LÔ HÀNG");

		TableColumn tbTableColumnThuocChitietTEN_THUOC = new TableColumn(
				tableThuocChitiet, SWT.LEFT);
		tbTableColumnThuocChitietTEN_THUOC.setWidth(162);
		tbTableColumnThuocChitietTEN_THUOC.setText("TEN_THUOC");

		TableColumn tbTableColumnThuocChitietDON_VI_TINH = new TableColumn(
				tableThuocChitiet, SWT.LEFT);
		tbTableColumnThuocChitietDON_VI_TINH.setWidth(65);
		tbTableColumnThuocChitietDON_VI_TINH.setText("DON_VI_TINH");

		TableColumn tbTableColumnThuocChitietSOLUONG = new TableColumn(
				tableThuocChitiet, SWT.RIGHT);
		tbTableColumnThuocChitietSOLUONG.setWidth(61);
		tbTableColumnThuocChitietSOLUONG.setText("SOLUONG");

		TableColumn tbTableColumnThuocChitietDON_GIA = new TableColumn(
				tableThuocChitiet, SWT.RIGHT);
		tbTableColumnThuocChitietDON_GIA.setWidth(100);
		tbTableColumnThuocChitietDON_GIA.setText("DON_GIA");

		TableColumn tbTableColumnThuocChitietTHANH_TIEN = new TableColumn(
				tableThuocChitiet, SWT.RIGHT);
		tbTableColumnThuocChitietTHANH_TIEN.setWidth(100);
		tbTableColumnThuocChitietTHANH_TIEN.setText("THANH_TIEN");

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
		
				Composite composite = new Composite(compositeInShellThuocChitiet, SWT.NONE);
				composite.setLayoutData(BorderLayout.SOUTH);
				composite.setLayout(new GridLayout(3, false));
				
						Button btnCancel = new Button(composite, SWT.NONE);
						GridData gd_btnCancel = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
						gd_btnCancel.widthHint = 220;
						btnCancel.setLayoutData(gd_btnCancel);
						btnCancel.setText("Bỏ (ESC)");
						btnCancel.setImage(SWTResourceManager.getImage(
								FormThuocChitietListDlg.class, "/png/circle-x-3x.png"));
						btnCancel.setFont(SWTResourceManager.getFont("Tahoma", 12, SWT.NORMAL));
						
								Button btnPrint = new Button(composite, SWT.NONE);
								GridData gd_btnPrint = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
								gd_btnPrint.widthHint = 215;
								btnPrint.setLayoutData(gd_btnPrint);
								btnPrint.setText("In Phiếu");
								btnPrint.setImage(SWTResourceManager.getImage(
										FormThuocChitietListDlg.class, "/png/print-3x.png"));
								btnPrint.setFont(SWTResourceManager.getFont("Tahoma", 12, SWT.NORMAL));
								
										Button btnSave = new Button(composite, SWT.NONE);
										GridData gd_btnSave = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
										gd_btnSave.widthHint = 282;
										btnSave.setLayoutData(gd_btnSave);
										btnSave.addSelectionListener(new SelectionAdapter() {
											@Override
											public void widgetSelected(SelectionEvent e) {
												doXuatThuoc();
											}
										});
										btnSave.setText("Lưu Phiếu (F2)");
										btnSave.setImage(SWTResourceManager.getImage(
												FormThuocChitietListDlg.class, "/png/check-3x.png"));
										btnSave.setFont(SWTResourceManager.getFont("Tahoma", 12, SWT.NORMAL));

		tableViewerThuocChitiet
				.setLabelProvider(new TableLabelProviderThuocChitiet());
		tableViewerThuocChitiet
				.setContentProvider(new ContentProviderThuocChitiet());
		tableViewerThuocChitiet.setInput(listDataThuocChitiet);
		//
		//
		//
		reloadTableThuocChitiet();
	}

	protected void doViewChitietLoThuoc() {
		if(tableThuocChitiet.getSelectionCount()==0){
			return;
		}
		TableItem item = tableThuocChitiet.getSelection()[0];
		ThuocChitiet obj = (ThuocChitiet)item.getData();
        logger.info(obj.toString());
		//
        NhapThuoc objNhapThuoc = (NhapThuoc)NhapThuoc.load(obj.NT_ID);
        if(objNhapThuoc!=null){
        	// Load to view
        	//
    		FormNhapThuocDlg dlg = new FormNhapThuocDlg(shell, 0);
    		dlg.intTypeDlgNhapThuoc = FormNhapThuocDlg.TYPE_DLG_VIEW;
    		dlg.setNhapThuocDlgData(objNhapThuoc);
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
		objBenhNhan = BenhNhan.load(objKhambenh.BN_ID);
		if (objBenhNhan != null) {
			txtHOTEN.setText(objBenhNhan.HO_TEN);
			txtDIACHI.setText(objBenhNhan.DIA_CHI);
		}
		//
		String sql = "select * from thuoc_chitiet WHERE STS<> "
				+ DbHelper.DELETE_STATUS + " and MA_LK="
				+ objKhambenh.MA_LK.intValue();
		try {
			logger.info(sql);
			Connection con = DbHelper.getSql2o();
			listDataThuocChitiet = con.createQuery(sql).executeAndFetch(
					ThuocChitiet.class);
			int i=1;
			for(ThuocChitiet obj: listDataThuocChitiet){
				obj.STT = i++;
			}
		} catch (Exception e) {
			logger.error(e);
			MessageDialog.openError(shell, "Error", e.getMessage());
		}
		//
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

	public void setKhamBenhDlgData(KhamBenh obj) {
		objKhambenh = obj;
	}
}
