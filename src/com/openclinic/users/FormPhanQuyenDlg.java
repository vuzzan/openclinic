package com.openclinic.users;

import java.util.List;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Monitor;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.layout.GridData;

import com.DbHelper;
import com.model.dao.Phanquyen;
import com.model.dao.Users;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Rectangle;
import org.sql2o.Connection;

public class FormPhanQuyenDlg extends Dialog {

	protected Object result;
	protected Shell shlQuyenTruyCap;
	private Text txtHOTEN;
	private Button btnNV;
	private Button btnBS;
	private Button btnThuoc;
	private Button btnACCT;
	private Button btnADMIN;
	private Button btnXN;
	private Users objUser;

	/**
	 * Create the dialog.
	 * 
	 * @param parent
	 * @param style
	 */
	public FormPhanQuyenDlg(Shell parent, int style) {
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
		shlQuyenTruyCap.open();
		shlQuyenTruyCap.layout();
		Display display = getParent().getDisplay();

		//
		Monitor primary = display.getPrimaryMonitor();
		Rectangle bounds = primary.getBounds();
		Rectangle rect = shlQuyenTruyCap.getBounds();
		int x = bounds.x + (bounds.width - rect.width) / 2;
		int y = bounds.y + (bounds.height - rect.height) / 2;
		shlQuyenTruyCap.setLocation(x, y);

		//
		while (!shlQuyenTruyCap.isDisposed()) {
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
		shlQuyenTruyCap = new Shell(getParent(), SWT.SHELL_TRIM
				| SWT.PRIMARY_MODAL);
		shlQuyenTruyCap.setSize(364, 257);
		shlQuyenTruyCap.setText("Quyền Truy cập");
		shlQuyenTruyCap.setLayout(new GridLayout(2, false));

		Label lblNewLabel_1 = new Label(shlQuyenTruyCap, SWT.NONE);
		lblNewLabel_1.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false,
				false, 1, 1));
		lblNewLabel_1.setFont(SWTResourceManager.getFont("Tahoma", 12,
				SWT.NORMAL));
		lblNewLabel_1.setText("Tên người dùng");

		txtHOTEN = new Text(shlQuyenTruyCap, SWT.BORDER);
		txtHOTEN.setFont(SWTResourceManager.getFont("Tahoma", 12, SWT.NORMAL));
		txtHOTEN.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false,
				1, 1));

		Label lblTableThng = new Label(shlQuyenTruyCap, SWT.NONE);
		lblTableThng.setFont(SWTResourceManager.getFont("Tahoma", 12,
				SWT.NORMAL));
		lblTableThng.setText("Quyền");

		btnNV = new Button(shlQuyenTruyCap, SWT.RADIO);
		btnNV.setFont(SWTResourceManager.getFont("Tahoma", 12, SWT.NORMAL));
		btnNV.setText("Nhân Viên");
		new Label(shlQuyenTruyCap, SWT.NONE);

		btnBS = new Button(shlQuyenTruyCap, SWT.RADIO);
		btnBS.setText("Bác sỹ");
		btnBS.setFont(SWTResourceManager.getFont("Tahoma", 12, SWT.NORMAL));
		new Label(shlQuyenTruyCap, SWT.NONE);

		btnXN = new Button(shlQuyenTruyCap, SWT.RADIO);
		btnXN.setText("Xét nghiệm");
		btnXN.setFont(SWTResourceManager.getFont("Tahoma", 12, SWT.NORMAL));
		new Label(shlQuyenTruyCap, SWT.NONE);

		btnThuoc = new Button(shlQuyenTruyCap, SWT.RADIO);
		btnThuoc.setText("Thuốc");
		btnThuoc.setFont(SWTResourceManager.getFont("Tahoma", 12, SWT.NORMAL));
		new Label(shlQuyenTruyCap, SWT.NONE);

		btnACCT = new Button(shlQuyenTruyCap, SWT.RADIO);
		btnACCT.setText("Kế toán");
		btnACCT.setFont(SWTResourceManager.getFont("Tahoma", 12, SWT.NORMAL));
		new Label(shlQuyenTruyCap, SWT.NONE);

		btnADMIN = new Button(shlQuyenTruyCap, SWT.RADIO);
		btnADMIN.setText("Admin");
		btnADMIN.setFont(SWTResourceManager.getFont("Tahoma", 12, SWT.NORMAL));
		new Label(shlQuyenTruyCap, SWT.NONE);

		Button btnLu = new Button(shlQuyenTruyCap, SWT.NONE);
		btnLu.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				saveAndClose();
			}
		});
		GridData gd_btnLu = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1,
				1);
		gd_btnLu.widthHint = 162;
		btnLu.setLayoutData(gd_btnLu);
		btnLu.setText("Lưu");
		btnLu.setImage(SWTResourceManager.getImage(FormPhanQuyenDlg.class,
				"/png/media-play-2x.png"));
		btnLu.setFont(SWTResourceManager.getFont("Tahoma", 12, SWT.NORMAL));

		loadData();
	}

	protected void saveAndClose() {
		if (objUser != null) {
			if (btnNV.getSelection() == true) {
				objUser.LOAI = "NV";
			} else if (btnBS.getSelection() == true) {
				objUser.LOAI = "BS";
			} else if (btnThuoc.getSelection() == true) {
				objUser.LOAI = "THUOC";
			} else if (btnACCT.getSelection() == true) {
				objUser.LOAI = "ACCT";
			} else if (btnXN.getSelection() == true) {
				objUser.LOAI = "XN";
			} else if (btnADMIN.getSelection() == true) {
				objUser.LOAI = "ADMIN";
			}
			//
			objUser.update();
			// Delete all and update PhanQuyen
			updatePhanQuyen(objUser);
			//

		}
		shlQuyenTruyCap.close();
	}

	private void updatePhanQuyen(Users objUser) {
		String sql = "delete from phanquyen where U_ID=" + objUser.getU_ID();
		Connection con = DbHelper.getSql2o();
		con.createQuery(sql).executeUpdate();
		//
		if (btnNV.getSelection() == true) {
			objUser.LOAI = "NV";
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

		} else if (btnBS.getSelection() == true) {
			objUser.LOAI = "BS";
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

		} else if (btnThuoc.getSelection() == true) {
			objUser.LOAI = "THUOC";
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

		} else if (btnACCT.getSelection() == true) {
			objUser.LOAI = "ACCT";
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

		} else if (btnXN.getSelection() == true) {
			objUser.LOAI = "XN";
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

		} else if (btnADMIN.getSelection() == true) {
			objUser.LOAI = "ADMIN";
		}

	}

	private void loadData() {
		if (objUser != null) {
			txtHOTEN.setText(objUser.TEN_NHANVIEN);
			if (objUser.LOAI.toUpperCase().equals("NV")) {
				btnNV.setSelection(true);
				btnBS.setSelection(false);
				btnThuoc.setSelection(false);
				btnXN.setSelection(false);
				btnACCT.setSelection(false);
				btnADMIN.setSelection(false);
			} else if (objUser.LOAI.toUpperCase().equals("BS")) {
				btnNV.setSelection(false);
				btnBS.setSelection(true);
				btnThuoc.setSelection(false);
				btnXN.setSelection(false);
				btnACCT.setSelection(false);
				btnADMIN.setSelection(false);
			} else if (objUser.LOAI.toUpperCase().equals("THUOC")) {
				btnNV.setSelection(false);
				btnBS.setSelection(false);
				btnThuoc.setSelection(true);
				btnXN.setSelection(false);
				btnACCT.setSelection(false);
				btnADMIN.setSelection(false);
			} else if (objUser.LOAI.toUpperCase().equals("XN")) {
				btnNV.setSelection(false);
				btnBS.setSelection(false);
				btnThuoc.setSelection(false);
				btnXN.setSelection(true);
				btnACCT.setSelection(false);
				btnADMIN.setSelection(false);
			} else if (objUser.LOAI.toUpperCase().equals("ACCT")) {
				btnNV.setSelection(false);
				btnBS.setSelection(true);
				btnThuoc.setSelection(false);
				btnXN.setSelection(false);
				btnACCT.setSelection(true);
				btnADMIN.setSelection(false);
			} else if (objUser.LOAI.toUpperCase().equals("ADMIN")) {
				btnNV.setSelection(false);
				btnBS.setSelection(false);
				btnThuoc.setSelection(false);
				btnXN.setSelection(false);
				btnACCT.setSelection(false);
				btnADMIN.setSelection(true);
			}

		}

	}

	public void setUsersDlgData(Users obj) {
		this.objUser = obj;
	}
}
