package com.openclinic.khambenh;

import java.util.Date;
import java.util.Hashtable;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.internal.win32.OS;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Monitor;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;
import org.sql2o.Connection;

import swing2swt.layout.BorderLayout;

import com.DbHelper;
import com.model.dao.Dichvu;
import com.model.dao.DvChitiet;
import com.openclinic.utils.Utils;

import org.eclipse.swt.widgets.Button;

public class DichVuXetNghiemDlg extends Dialog {

	protected Object result;
	protected Shell shlDichvu;
	private Text txtMaDV;
	private Text txtTenDV;
	private Combo cbDichVu;

	private Hashtable<String, Dichvu> hashDichVu = new Hashtable<>();
	public DvChitiet objDVChiTiet;
	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public DichVuXetNghiemDlg(Shell parent, int style) {
		super(parent, style);
		setText("SWT Dialog");
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public Object open() {
		createContents();
		
		startDlg();
		shlDichvu.open();
		shlDichvu.layout();
		Display display = getParent().getDisplay();
		Monitor primary = display.getPrimaryMonitor();
		Rectangle bounds = primary.getBounds();
		Rectangle rect = shlDichvu.getBounds();
		int x = bounds.x + (bounds.width - rect.width) / 2;
		int y = bounds.y + (bounds.height - rect.height) / 2;
		shlDichvu.setLocation(x, y);
		while (!shlDichvu.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		return result;
	}

	private void startDlg() {
		
	}

	/**
	 * Create contents of the dialog.
	 */
	private void createContents() {
		shlDichvu = new Shell(getParent(), SWT.SHELL_TRIM);
		shlDichvu.setSize(543, 162);
		shlDichvu.setText("DichVu");
		shlDichvu.setLayout(new BorderLayout(0, 0));
		
		Composite composite_1 = new Composite(shlDichvu, SWT.NONE);
		composite_1.setLayoutData(BorderLayout.CENTER);
		
		Label lblTnDv = new Label(composite_1, SWT.NONE);
		lblTnDv.setText("Mã DV");
		lblTnDv.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		lblTnDv.setFont(SWTResourceManager.getFont("Tahoma", 12, SWT.NORMAL));
		lblTnDv.setBounds(10, 15, 97, 23);
		
		txtMaDV = new Text(composite_1, SWT.BORDER);
		txtMaDV.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
//				System.out.println(e.keyCode);
				if(e.keyCode==13){
					searchShowDichVu();
				}
				else if(e.keyCode==8){
					//back space
					txtMaDV.setText("");
					txtTenDV.setText("");
					txtMaDV.forceFocus();
					e.doit = false;
				}
				else{
					keyPress(e);
				}
			}
		});
		txtMaDV.setFont(SWTResourceManager.getFont("Tahoma", 16, SWT.NORMAL));
		txtMaDV.setBackground(SWTResourceManager.getColor(SWT.COLOR_YELLOW));
		txtMaDV.setBounds(113, 10, 414, 28);
		
		txtTenDV = new Text(composite_1, SWT.BORDER);
		txtTenDV.setFont(SWTResourceManager.getFont("Tahoma", 16, SWT.NORMAL));
		txtTenDV.setBackground(SWTResourceManager.getColor(SWT.COLOR_YELLOW));
		txtTenDV.setBounds(113, 44, 414, 28);
		
		Label label = new Label(composite_1, SWT.NONE);
		label.setText("Tên DV");
		label.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label.setFont(SWTResourceManager.getFont("Tahoma", 12, SWT.NORMAL));
		label.setBounds(10, 49, 97, 23);
		
		cbDichVu = new Combo(composite_1, SWT.NONE);
		cbDichVu.setFont(SWTResourceManager.getFont("Tahoma", 13, SWT.NORMAL));
		cbDichVu.setBounds(113, 9, 414, 29);
		cbDichVu.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.keyCode==13){
					selectDichVu();
				}
				else{
					keyPress(e);
				}
			}
		});
		
		Composite composite_2 = new Composite(shlDichvu, SWT.NONE);
		composite_2.setLayoutData(BorderLayout.NORTH);
		composite_2.setLayout(new GridLayout(1, false));
		
		Label lblNewLabel = new Label(composite_2, SWT.NONE);
		GridData gd_lblNewLabel = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_lblNewLabel.widthHint = 449;
		lblNewLabel.setLayoutData(gd_lblNewLabel);
		lblNewLabel.setFont(SWTResourceManager.getFont("Tahoma", 16, SWT.NORMAL));
		lblNewLabel.setText("Dịch vụ kỹ thuật/Xét nghiệm");
		
		
		startDLg();

	}

	protected void keyPress(KeyEvent e) {
		if(e.keyCode==SWT.F2){
			//Save and close
			saveAndClose();
			
		}
		
	}

	private void saveAndClose() {
		//
		Dichvu objDv = hashDichVu.get(txtMaDV.getText());
		if(objDv!=null){
			objDVChiTiet.MA_DICH_VU = objDv.MA_DVKT;
			objDVChiTiet.TEN_DICH_VU = objDv.TEN_DVKT;
			objDVChiTiet.DON_GIA = objDv.DON_GIA;
			objDVChiTiet.DON_GIA2 = objDv.DON_GIA2;
			objDVChiTiet.MA_NHOM = Utils.getInt( objDv.MANHOM_9324 );
			objDVChiTiet.SO_LUONG = 1;
			objDVChiTiet.MA_VAT_TU = "";
			objDVChiTiet.MA_BENH = "";
			objDVChiTiet.MA_PTTT = 0;
			objDVChiTiet.NGAY_YL = Utils.getDatetime(new Date(), "yyyyMMddHHmm"); 
			objDVChiTiet.NGAY_KQ = Utils.getDatetime(new Date(), "yyyyMMddHHmm"); 
			objDVChiTiet.THANH_TIEN = objDv.DON_GIA2;
			objDVChiTiet.TYLE_TT = 100;
			objDVChiTiet.TT_BH = 0;
			objDVChiTiet.TT_NB = 0;
			//
			objDVChiTiet.MA_BAC_SI = "";
			objDVChiTiet.MA_KHOA = "";
		}
		shlDichvu.close();
		//
	}

	private void startDLg() {
		if(objDVChiTiet!=null){
			txtMaDV.setText(objDVChiTiet.MA_DICH_VU);
			txtTenDV.setText(objDVChiTiet.TEN_DICH_VU);
		}
	}

	protected void selectDichVu() {
		try {
			Connection con = DbHelper.getSql2o();
			String str[] = cbDichVu.getText().split("-");
			String MA_DVKT = str[0].trim();
			String sql = "update dichvu set DV_RANK=DV_RANK+1 where MA_DVKT='"+ MA_DVKT + "'";
			//System.out.println(sql);
			con.createQuery(sql).executeUpdate();
			cbDichVu.setVisible(false);
			txtMaDV.setVisible(true);
			txtMaDV.setText(str[0].trim());
			txtMaDV.forceFocus();
			txtTenDV.setText(str[1].trim());
			//
			//
			//con.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	protected void searchShowDichVu() {
		String searchText = txtMaDV.getText().trim().toLowerCase();
		Dichvu obj2 = hashDichVu.get(searchText);
		if(obj2!=null){
			//
			saveAndClose();
			return;
			//
		}
		try {
			Connection con = DbHelper.getSql2o();
			//
			String sql = "select * from dichvu where LOWER(MA_DVKT) like '%"
					+ searchText
					+ "%' or LOWER(TEN_DVKT) like '%"
					+ searchText + "%' and MANHOM_9324<>'13' order by DV_RANK DESC";
			//System.out.println(sql);
			List<Dichvu> listRow = con.createQuery(sql).executeAndFetch(Dichvu.class);
			cbDichVu.setVisible(true);
			txtMaDV.setVisible(false);
			cbDichVu.removeAll();
			hashDichVu.clear();
			for (Dichvu row : listRow) {
				Dichvu obj = row;
				cbDichVu.add(obj.MA_DVKT + "-"+ obj.TEN_DVKT);
				hashDichVu.put(obj.MA_DVKT, obj);
			}
			cbDichVu.select(0);
			cbDichVu.forceFocus();
			OS.SendMessage(cbDichVu.handle, OS.CB_SHOWDROPDOWN, 1, 0);
			//con.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
	}
}
