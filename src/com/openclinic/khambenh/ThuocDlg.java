package com.openclinic.khambenh;

import java.util.Hashtable;
import java.util.List;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Monitor;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.internal.win32.OS;
import org.sql2o.Connection;

import com.DbHelper;
import com.model.dao.Dichvu;
import com.model.dao.DvChitiet;
import com.model.dao.Thuoc;
import com.model.dao.ThuocChitiet;
import com.openclinic.utils.Utils;

public class ThuocDlg extends Dialog {

	protected Object result;
	protected Shell shellThuoc;
	private Text txtMaHoatChat;
	private Text txtTenThuoc;
	private Text txtHamLuong;
	private Text txtDongGoi;
	private Combo cbThuoc;

	private Hashtable<String, Thuoc> hashThuoc = new Hashtable<>();
	private ThuocChitiet objThuocChiTiet;
	private Text txtSoLuong;
	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public ThuocDlg(Shell parent, int style) {
		super(parent, style);
		setText("SWT Dialog");
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public Object open() {
		createContents();
		shellThuoc.open();
		shellThuoc.layout();
		Display display = getParent().getDisplay();
		Monitor primary = display.getPrimaryMonitor();
		Rectangle bounds = primary.getBounds();
		Rectangle rect = shellThuoc.getBounds();
		int x = bounds.x + (bounds.width - rect.width) / 2;
		int y = bounds.y + (bounds.height - rect.height) / 2;
		shellThuoc.setLocation(x, y);
		
		while (!shellThuoc.isDisposed()) {
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
		shellThuoc = new Shell(getParent(), SWT.SHELL_TRIM);
		shellThuoc.setSize(562, 256);
		shellThuoc.setText("Thuốc");
		
		Composite composite = new Composite(shellThuoc, SWT.NONE);
		composite.setBounds(10, 10, 535, 209);
		
		Label lblMHotCht = new Label(composite, SWT.NONE);
		lblMHotCht.setText("Mã Hoạt Chất");
		lblMHotCht.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		lblMHotCht.setFont(SWTResourceManager.getFont("Tahoma", 12, SWT.NORMAL));
		lblMHotCht.setBounds(10, 44, 97, 23);
		
		txtTenThuoc = new Text(composite, SWT.BORDER);
		txtTenThuoc.setFont(SWTResourceManager.getFont("Tahoma", 16, SWT.NORMAL));
		txtTenThuoc.setBackground(SWTResourceManager.getColor(SWT.COLOR_YELLOW));
		txtTenThuoc.setBounds(113, 7, 414, 28);
		txtTenThuoc.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.keyCode==13){
					searchShowThuoc();
				}
				else{
					keyPress(e);
				}
			}
		});
		
		Label lblTnThuc = new Label(composite, SWT.NONE);
		lblTnThuc.setText("Tên thuốc");
		lblTnThuc.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		lblTnThuc.setFont(SWTResourceManager.getFont("Tahoma", 12, SWT.NORMAL));
		lblTnThuc.setBounds(10, 15, 97, 23);
		
		txtMaHoatChat = new Text(composite, SWT.BORDER);
		txtMaHoatChat.setEnabled(false);
		txtMaHoatChat.setEditable(false);
		
		txtMaHoatChat.setFont(SWTResourceManager.getFont("Tahoma", 16, SWT.NORMAL));
		txtMaHoatChat.setBackground(SWTResourceManager.getColor(SWT.COLOR_YELLOW));
		txtMaHoatChat.setBounds(113, 44, 414, 28);
		

		cbThuoc = new Combo(composite, SWT.NONE);
		cbThuoc.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.keyCode==13){
					selectThuoc();
				}
				else{
					keyPress(e);
				}
			}
		});
		cbThuoc.setFont(SWTResourceManager.getFont("Tahoma", 13, SWT.NORMAL));
		cbThuoc.setBounds(113, 9, 414, 29);
		
		Label lblHmLng = new Label(composite, SWT.NONE);
		lblHmLng.setText("Hàm lượng");
		lblHmLng.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		lblHmLng.setFont(SWTResourceManager.getFont("Tahoma", 12, SWT.NORMAL));
		lblHmLng.setBounds(10, 78, 97, 23);
		
		txtHamLuong = new Text(composite, SWT.BORDER);
		txtHamLuong.setEnabled(false);
		txtHamLuong.setEditable(false);
		txtHamLuong.setFont(SWTResourceManager.getFont("Tahoma", 16, SWT.NORMAL));
		txtHamLuong.setBackground(SWTResourceManager.getColor(SWT.COLOR_YELLOW));
		txtHamLuong.setBounds(113, 73, 414, 28);
		
		Label lblngGi = new Label(composite, SWT.NONE);
		lblngGi.setText("Đóng gói");
		lblngGi.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		lblngGi.setFont(SWTResourceManager.getFont("Tahoma", 12, SWT.NORMAL));
		lblngGi.setBounds(10, 107, 97, 23);
		
		txtDongGoi = new Text(composite, SWT.BORDER);
		txtDongGoi.setEnabled(false);
		txtDongGoi.setEditable(false);
		txtDongGoi.setFont(SWTResourceManager.getFont("Tahoma", 16, SWT.NORMAL));
		txtDongGoi.setBackground(SWTResourceManager.getColor(SWT.COLOR_YELLOW));
		txtDongGoi.setBounds(113, 107, 414, 28);
		
		Label lblNewLabel = new Label(composite, SWT.NONE);
		lblNewLabel.setBounds(113, 186, 49, 13);
		lblNewLabel.setText("F2 Lưu");
		
		txtSoLuong = new Text(composite, SWT.BORDER);
		txtSoLuong.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.keyCode==13){
					saveAndClose();
				}
				else{
					keyPress(e);
				}
			}
		});
		txtSoLuong.setText("1");
		txtSoLuong.setFont(SWTResourceManager.getFont("Tahoma", 16, SWT.NORMAL));
		txtSoLuong.setBackground(SWTResourceManager.getColor(SWT.COLOR_YELLOW));
		txtSoLuong.setBounds(113, 152, 414, 28);
		
		Label lblSLng = new Label(composite, SWT.NONE);
		lblSLng.setText("Số lượng");
		lblSLng.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		lblSLng.setFont(SWTResourceManager.getFont("Tahoma", 12, SWT.NORMAL));
		lblSLng.setBounds(10, 157, 97, 23);
		//
		startDlg();
	}

	private void startDlg() {
		if(objThuocChiTiet!=null && objThuocChiTiet.TEN_THUOC!=null){
			txtTenThuoc.setText(objThuocChiTiet.TEN_THUOC);
			txtDongGoi.setText(objThuocChiTiet.DONGGOI);
			txtHamLuong.setText(objThuocChiTiet.HAMLUONG);
			txtMaHoatChat.setText(objThuocChiTiet.HOATCHAT);
			txtSoLuong.setText(objThuocChiTiet.SOLUONG+"");
		}
		txtTenThuoc.forceFocus();
	}

	protected void selectThuoc() {
		try {
			Connection con = DbHelper.getSql2o();
			String MA_DVKT = cbThuoc.getText().trim();
			Thuoc obj = hashThuoc.get(MA_DVKT);
			//
			if(obj!=null){
				String sql = "update thuoc set THUOC_RANK=THUOC_RANK+1 where THUOC_ID="+ obj.THUOC_ID.intValue() + "";
				con.createQuery(sql).executeUpdate();
				//
				cbThuoc.setVisible(false);
				txtTenThuoc.setVisible(true);
				//
				txtTenThuoc.setText(obj.TEN_THUOC);
				txtMaHoatChat.setText(obj.HOATCHAT_AX);
				txtDongGoi.setText(obj.DONG_GOI);
				txtHamLuong.setText(obj.HAMLUONG_AX);
				//
				txtSoLuong.forceFocus();
				txtSoLuong.selectAll();
			}
			else{
				txtTenThuoc.setVisible(true);
				txtTenThuoc.forceFocus();
				txtTenThuoc.selectAll();
				
			}
			//
			//
			//con.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
	}

	
	protected void keyPress(KeyEvent e) {
		if(e.keyCode==SWT.F2){
			saveAndClose();
			
		}
		
	}

	private void saveAndClose() {
		//Save and close
		int SOLUONG = Utils.getInt( txtSoLuong.getText() );

		if(SOLUONG==0){
			txtSoLuong.setBackground(SWTResourceManager.getColor(SWT.COLOR_RED));
			txtSoLuong.forceFocus();
			txtSoLuong.selectAll();
			return;
		}
		else{
			txtSoLuong.setBackground(SWTResourceManager.getColor(SWT.COLOR_YELLOW));
		}
		//
		Thuoc objThuoc = hashThuoc.get(txtTenThuoc.getText());
		if(objThuoc!=null){
			objThuocChiTiet.SOLUONG = Utils.getInt( txtSoLuong.getText() );
			
			objThuocChiTiet.TEN_THUOC = objThuoc.TEN_THUOC;
			objThuocChiTiet.THUOC_ID = objThuoc.THUOC_ID;
			objThuocChiTiet.DON_GIA = objThuoc.DON_GIA;
			objThuocChiTiet.DON_VI_TINH = objThuoc.DON_VI_TINH;
			
			objThuocChiTiet.STS = -1;
			//objThuocChiTiet.MA_LK = "";
			//objThuocChiTiet.MA_BENH = 
			objThuocChiTiet.THANH_TIEN = objThuocChiTiet.DON_GIA * objThuocChiTiet.SOLUONG;
			objThuocChiTiet.TYLE_TT = (float)100.0;
			objThuocChiTiet.HOATCHAT = objThuoc.HOATCHAT_AX;
			objThuocChiTiet.HAMLUONG = objThuoc.HAMLUONG_AX;
			objThuocChiTiet.DONGGOI = objThuoc.DONG_GOI;
		}
		shellThuoc.close();
		//
		
	}

	protected void searchShowThuoc() {
		try {
			Connection con = DbHelper.getSql2o();
			String searchText = txtTenThuoc.getText().trim().toLowerCase();
			String sql = "select * from thuoc where LOWER(TEN_THUOC) like '"+searchText + "%'";//" order by DV_RANK DESC";
			System.out.println(sql);
			List<Thuoc> listRow = con.createQuery(sql).executeAndFetch(Thuoc.class);
			
			cbThuoc.setVisible(true);
			txtTenThuoc.setVisible(false);
			cbThuoc.removeAll();
			hashThuoc.clear();
			for (Thuoc row : listRow) {
				Thuoc obj = row;
				cbThuoc.add(obj.TEN_THUOC);
				hashThuoc.put(obj.TEN_THUOC, obj);
			}
			cbThuoc.select(0);
			cbThuoc.forceFocus();
			OS.SendMessage(cbThuoc.handle, OS.CB_SHOWDROPDOWN, 1, 0);
			//con.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
	}
}
