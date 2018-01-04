package com;

import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Monitor;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import swing2swt.layout.BorderLayout;

public class ShowCheckTheDlg extends Dialog {

	protected Object result;
	protected Shell shlKiemTraThe;
	private Browser browser;
	//public String html = "";
	public CheckTheObj objCheckTheObj = null;
	private Composite composite;
	private Composite composite_1;
	private Text txtHoTen;
	private Text txtNgaysinh;
	private Text txtMaThe;
	private Text txtDiaChi;
	private Text txtNoiDKKCB;
	private Text txtThoiDiem5Nam;
	private Text txtTuNgay;
	private Text txtDenNgay;
	private Text txtGioitinh;
	public int isUpdate = 0;
	private Composite composite_2;
	private Button btnTheCoLoiTuChoi;
	private Button btnTheDung;
	private Button btnTheQuocPhong;
	
	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public ShowCheckTheDlg(Shell parent, int style) {
		super(parent, style);
		setText("SWT Dialog");
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public Object open() {
		createContents();
		shlKiemTraThe.open();
		shlKiemTraThe.layout();
		Display display = getParent().getDisplay();
		// Move to center
		Monitor primary = display.getPrimaryMonitor();
		Rectangle bounds = primary.getBounds();
		Rectangle rect = shlKiemTraThe.getBounds();
		int x = bounds.x + (bounds.width - rect.width) / 2;
		int y = bounds.y + (bounds.height - rect.height) / 2;
		shlKiemTraThe.setLocation(x, y);
		

		//
		startApp();
		while (!shlKiemTraThe.isDisposed()) {
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
		isUpdate = -1;
		shlKiemTraThe = new Shell(getParent(), SWT.SHELL_TRIM);
		shlKiemTraThe.setSize(667, 571);
		shlKiemTraThe.setText("Kiem tra the bao hiem");
		shlKiemTraThe.setLayout(new BorderLayout(0, 0));
		shlKiemTraThe.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.keyCode==SWT.ESC){
					e.doit = false;
				}
			}
		});
		composite = new Composite(shlKiemTraThe, SWT.NONE);
		composite.setLayoutData(BorderLayout.SOUTH);
		composite.setLayout(new GridLayout(3, false));
		
		btnTheDung = new Button(composite, SWT.NONE);
		GridData gd_btnClose = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnClose.widthHint = 142;
		btnTheDung.setLayoutData(gd_btnClose);
		btnTheDung.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				isUpdate = 1;
				shlKiemTraThe.close();
			}
		});
		btnTheDung.setFont(SWTResourceManager.getFont("Tahoma", 16, SWT.NORMAL));
		btnTheDung.setText("Thẻ đúng");
		
		btnTheQuocPhong = new Button(composite, SWT.NONE);
		GridData gd_btnThCVn = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnThCVn.widthHint = 263;
		btnTheQuocPhong.setLayoutData(gd_btnThCVn);
		btnTheQuocPhong.setText("Thẻ báo lỗi, nhưng cho qua");
		btnTheQuocPhong.setFont(SWTResourceManager.getFont("Tahoma", 16, SWT.NORMAL));
		btnTheQuocPhong.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				isUpdate = 2;
				shlKiemTraThe.close();
			}
		});
		btnTheCoLoiTuChoi = new Button(composite, SWT.NONE);
		
		GridData gd_btnThCLi = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnThCLi.widthHint = 179;
		btnTheCoLoiTuChoi.setLayoutData(gd_btnThCLi);
		btnTheCoLoiTuChoi.setText("Thẻ có lỗi, từ chối");
		btnTheCoLoiTuChoi.setFont(SWTResourceManager.getFont("Tahoma", 16, SWT.NORMAL));
		btnTheCoLoiTuChoi.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				isUpdate = 0;
				shlKiemTraThe.close();
			}
		});
		composite_1 = new Composite(shlKiemTraThe, SWT.NONE);
		composite_1.setLayoutData(BorderLayout.NORTH);
		composite_1.setLayout(null);
		
		txtMaThe = new Text(composite_1, SWT.BORDER);
		txtMaThe.setBounds(5, 5, 177, 22);
		txtMaThe.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		
		txtHoTen = new Text(composite_1, SWT.BORDER);
		txtHoTen.setBounds(187, 5, 255, 22);
		txtHoTen.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		
		txtNgaysinh = new Text(composite_1, SWT.BORDER);
		txtNgaysinh.setBounds(448, 5, 177, 22);
		txtNgaysinh.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		
		txtDiaChi = new Text(composite_1, SWT.BORDER);
		txtDiaChi.setBounds(5, 32, 620, 22);
		txtDiaChi.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		
		txtNoiDKKCB = new Text(composite_1, SWT.BORDER);
		txtNoiDKKCB.setBounds(5, 59, 177, 22);
		txtNoiDKKCB.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		
		txtThoiDiem5Nam = new Text(composite_1, SWT.BORDER);
		txtThoiDiem5Nam.setBounds(5, 86, 177, 22);
		txtThoiDiem5Nam.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		
		txtTuNgay = new Text(composite_1, SWT.BORDER);
		txtTuNgay.setBounds(5, 113, 177, 22);
		txtTuNgay.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		
		txtDenNgay = new Text(composite_1, SWT.BORDER);
		txtDenNgay.setBounds(187, 113, 256, 22);
		txtDenNgay.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		
		txtGioitinh = new Text(composite_1, SWT.BORDER);
		txtGioitinh.setBounds(448, 59, 177, 22);
		txtGioitinh.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		
		composite_2 = new Composite(shlKiemTraThe, SWT.NONE);
		composite_2.setLayoutData(BorderLayout.CENTER);
		composite_2.setLayout(new BorderLayout(0, 0));
		
		browser = new Browser(composite_2, SWT.NONE);
		browser.setLayoutData(BorderLayout.CENTER);		
	}

	private void startApp() {
		if(objCheckTheObj!=null){
			if( browser!=null ){
				String html ="<h1>CHECK THẺ TỪ CỔNG BẢO HIỂM</h1><br/>"+ 
						"<b>Họ tên: "+objCheckTheObj.strHoTen+"</b><br/>"+
						"<b>Mã Thẻ: "+objCheckTheObj.strMathe+"</b><br/>"+
						"<b>Ngày Sinh: "+objCheckTheObj.strNgaySinh+"</b><br/>"+
						"<b>Kết quả từ cổng:</b><br/><br/>"+
						objCheckTheObj.strMessage;
				System.out.println(objCheckTheObj.strMessage);
				browser.setText(objCheckTheObj.strMessage);
			}
			//
			txtHoTen.setText("");
			txtMaThe.setText("");
			txtNgaysinh.setText("");
			txtDiaChi.setText("");
			txtTuNgay.setText("");
			txtDenNgay.setText("");
			txtThoiDiem5Nam.setText("");
			txtNoiDKKCB.setText("");
			txtGioitinh.setText("");
			if(objCheckTheObj.strHoTen.length()>3 ){
				txtHoTen.setText(objCheckTheObj.strHoTen);
			}
			if(objCheckTheObj.strHoTen.length()>3 )
				txtMaThe.setText(objCheckTheObj.strMathe);
			if(objCheckTheObj.strNgaySinh.length()>3 )
				txtNgaysinh.setText(objCheckTheObj.strNgaySinh);
			if(objCheckTheObj.strDiaChi.length()>3 ){
				txtDiaChi.setText(objCheckTheObj.strDiaChi);
			}
			else{
				btnTheDung.setEnabled(false);
				btnTheQuocPhong.setEnabled(false);
				isUpdate = 1;
			}
			if(objCheckTheObj.strTuNgay.length()>3 )
				txtTuNgay.setText(objCheckTheObj.strTuNgay);
			if(objCheckTheObj.strDenNgay.length()>3 )
				txtDenNgay.setText(objCheckTheObj.strDenNgay);
			if(objCheckTheObj.strThoidiem5Nam.length()>5 )
				txtThoiDiem5Nam.setText(objCheckTheObj.strThoidiem5Nam);
			if(objCheckTheObj.strDKKCB.length()==5 ){
				txtNoiDKKCB.setText(objCheckTheObj.strDKKCB);
			}
			if(objCheckTheObj.gioitinh>0)
				txtGioitinh.setText(""+objCheckTheObj.gioitinh);
			//
		}
		//
	}
}
