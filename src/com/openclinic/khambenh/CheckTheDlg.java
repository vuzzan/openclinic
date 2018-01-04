package com.openclinic.khambenh;

import java.io.File;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Monitor;
import org.eclipse.swt.widgets.Shell;

import swing2swt.layout.BorderLayout;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.GridData;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Rectangle;

import com.BHYTThread;
import com.openclinic.Main;

import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;

public class CheckTheDlg extends Dialog {

	protected Object result;
	protected Shell shellCheckThe;
	private Text txtCode;
	private Canvas canvas;
	public static BHYTThread objBHYTThread2 = new BHYTThread();
	protected static Image imageCodeISN;
	private Composite composite_1;
	private Text txtMaThe;
	private Text txtHoTen;
	private Text txtNgaySinh;
	private Button btnCheck;
	private Text txtHtml;
	private Text txtMessage;
	private Text txtNoidangKy;
	private Text txtTuNgay;
	private Text txtDenNgay;
	private Button btnClose;
	private Text txtDiaChi;

	public String strMathe = "";
	public String strHoTen = "";
	public String strNgaySinh = "";
	
	public String strDiaChi = "";
	public String strTuNgay = "";
	public String strDenNgay  = "";
	public String strDKKCB = "";
	public boolean isCloseLoginOK = false;
	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public CheckTheDlg(Shell parent, int style) {
		super(parent, style);
		setText("SWT Dialog");
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public Object open() {
		createContents();
		shellCheckThe.open();
		shellCheckThe.layout();
		Display display = getParent().getDisplay();
		// Move to center
		Monitor primary = display.getPrimaryMonitor();
		Rectangle bounds = primary.getBounds();
		Rectangle rect = shellCheckThe.getBounds();
		int x = bounds.x + (bounds.width - rect.width) / 2;
		int y = bounds.y + (bounds.height - rect.height) / 2;
		shellCheckThe.setLocation(x, y);
		while (!shellCheckThe.isDisposed()) {
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
		shellCheckThe = new Shell(getParent(), SWT.DIALOG_TRIM | SWT.PRIMARY_MODAL);
		shellCheckThe.setSize(561, 110);
		shellCheckThe.setText("Check Thẻ");
		shellCheckThe.setLayout(new BorderLayout(0, 0));
		
		Composite composite = new Composite(shellCheckThe, SWT.NONE);
		composite.setLayoutData(BorderLayout.NORTH);
		composite.setLayout(null);
		
		Label lblNhpCode = new Label(composite, SWT.NONE);
		lblNhpCode.setBounds(5, 5, 274, 23);
		lblNhpCode.setFont(SWTResourceManager.getFont("Tahoma", 14, SWT.NORMAL));
		lblNhpCode.setText("Nhập code 4 kỳ tự và bấm Login");
		
		canvas = new Canvas(composite, SWT.NONE);
		canvas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				String fileName = CheckTheDlg.objBHYTThread2.getCapcha();
				System.out.println("FILE NAME=" + fileName);
				if (fileName != null && fileName.length() > 0) {
					//
					imageCodeISN = new Image(Display.getCurrent(), fileName);
					canvas.redraw();
					try {
						File file = new File(fileName);
						file.delete();
					} catch (Exception ee) {
						//
					}
					//
				}	
			}
		});
		canvas.setBounds(53, 34, 64, 41);
		canvas.addPaintListener(new PaintListener() {
			public void paintControl(PaintEvent e) {
				if (imageCodeISN != null){
					canvas.setBackgroundImage(imageCodeISN);
				}
			}
		});

		
		txtCode = new Text(composite, SWT.BORDER);
		txtCode.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.keyCode==13){
					loginCheckSystem();
				}
			}
		});
		txtCode.setBounds(137, 34, 142, 34);
		txtCode.setFont(SWTResourceManager.getFont("Tahoma", 15, SWT.NORMAL));
		txtCode.setTextLimit(4);
		
		Button btnNewButton = new Button(composite, SWT.NONE);
		btnNewButton.setBounds(284, 34, 223, 42);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				loginCheckSystem();
				
			}
		});
		btnNewButton.setFont(SWTResourceManager.getFont("Tahoma", 14, SWT.NORMAL));
		btnNewButton.setText("Login");
		
		composite_1 = new Composite(shellCheckThe, SWT.NONE);
		composite_1.setLayoutData(BorderLayout.CENTER);
		
		txtMaThe = new Text(composite_1, SWT.BORDER);
		txtMaThe.setBounds(10, 10, 127, 27);
		
		txtHoTen = new Text(composite_1, SWT.BORDER);
		txtHoTen.setBounds(153, 10, 127, 27);
		
		txtNgaySinh = new Text(composite_1, SWT.BORDER);
		txtNgaySinh.setBounds(286, 10, 127, 27);
		
		btnCheck = new Button(composite_1, SWT.NONE);
		btnCheck.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				doCheckMaThe();
			}
		});
		btnCheck.setText("Check");
		btnCheck.setFont(SWTResourceManager.getFont("Tahoma", 14, SWT.NORMAL));
		btnCheck.setBounds(431, 10, 114, 41);
		
		txtHtml = new Text(composite_1, SWT.BORDER | SWT.MULTI);
		txtHtml.setFont(SWTResourceManager.getFont("Tahoma", 12, SWT.NORMAL));
		txtHtml.setBounds(10, 73, 687, 110);
		
		txtMessage = new Text(composite_1, SWT.BORDER);
		txtMessage.setBounds(10, 189, 687, 27);
		
		txtNoidangKy = new Text(composite_1, SWT.BORDER);
		txtNoidangKy.setBounds(10, 254, 127, 27);
		
		txtTuNgay = new Text(composite_1, SWT.BORDER);
		txtTuNgay.setBounds(153, 254, 127, 27);
		
		txtDenNgay = new Text(composite_1, SWT.BORDER);
		txtDenNgay.setBounds(286, 254, 127, 27);
		
		btnClose = new Button(composite_1, SWT.NONE);
		btnClose.setText("Close");
		btnClose.setFont(SWTResourceManager.getFont("Tahoma", 14, SWT.NORMAL));
		btnClose.setBounds(564, 255, 133, 61);
		
		txtDiaChi = new Text(composite_1, SWT.BORDER);
		txtDiaChi.setBounds(10, 222, 687, 27);

		startDlg();
	}

	protected void loginCheckSystem() {
		//if (objBHYTThread2.httpclient == null || objBHYTThread2.isLogin == false) {
		if (objBHYTThread2.isLogin == false) {
			objBHYTThread2.login2(Main.USER_GATE_ID, Main.USER_GATE_PASSWORD, txtCode.getText());
			if(objBHYTThread2.isLogin==false){
				String fileName = objBHYTThread2.getCapcha();
				System.out.println("FILE NAME=" + fileName);
				if (fileName != null && fileName.length() > 0) {
					//
					imageCodeISN = new Image(Display.getCurrent(), fileName);
					canvas.redraw();
					try {
						File file = new File(fileName);
						file.delete();
					} catch (Exception ee) {

					}
					//
				}
			}
			else{
				//MessageDialog.openInformation(shellCheckThe, "Login OK", "Login OK. Bấm Check Thẻ để bắt đầu check thẻ....");
				btnCheck.setEnabled(true);
				if(isCloseLoginOK==true){
					shellCheckThe.close();
				}
			}
		} 
		else 
		{
			objBHYTThread2.login2(Main.USER_GATE_ID, Main.USER_GATE_PASSWORD, txtCode.getText());
			if(objBHYTThread2.isLogin==false){
				String fileName = objBHYTThread2.getCapcha();
				System.out.println("FILE NAME=" + fileName);
				if (fileName != null && fileName.length() > 0) {
					//
					imageCodeISN = new Image(Display.getCurrent(), fileName);
					canvas.redraw();
					try {
						File file = new File(fileName);
						file.delete();
					} catch (Exception ee) {

					}
					//
				}
			}
			else{
				//MessageDialog.openInformation(shellCheckThe, "Login OK", "Login OK. Bấm Check Thẻ để bắt đầu check thẻ....");
				btnCheck.setEnabled(true);
				if(isCloseLoginOK==true){
					shellCheckThe.close();
				}
			}
		}
		
	}

	private void startDlg() {
		if(objBHYTThread2!=null){
			if(objBHYTThread2.isLogin==false){
				btnCheck.setEnabled(false);
			}
			else{
				btnCheck.setEnabled(true);
			}
		}
		//
		txtMaThe.setText( strMathe );
		txtHoTen.setText( strHoTen );
		txtNgaySinh.setText( strNgaySinh );
		
		if (objBHYTThread2.httpclient == null || objBHYTThread2.isLogin==false) {
			String fileName = objBHYTThread2.getCapcha();
			System.out.println("FILE NAME=" + fileName);
			if (fileName != null && fileName.length() > 0) {
				//
				imageCodeISN = new Image(Display.getCurrent(), fileName);
				canvas.redraw();
				try {
					File file = new File(fileName);
					file.delete();
				} catch (Exception ee) {
					//
				}
				//
			}
		} 
	}

	protected void doCheckMaThe() {
		if(objBHYTThread2!=null){
			String ret[] = objBHYTThread2.checkMaThe(txtMaThe.getText(), txtHoTen.getText(), txtNgaySinh.getText());
			if(ret[0].length()==0){
				txtHtml.setText(ret[0]);
				// Có lỗi
			}
			else{
				txtHtml.setText(ret[0]);
				String tmp[] = ret[1].split("|");
				//ret[1] = message+"|"+hoten+"|"+ngaysinh+"|"+diachi+"|"+KCBBD+"|"+tungaydenngay;
				if(tmp.length>4){
					txtMessage.setText(tmp[0]);
					txtDiaChi.setText(tmp[3]);
					txtNoidangKy.setText(tmp[4]);
					txtTuNgay.setText(tmp[5]);
					txtDenNgay.setText(tmp[6]);
					//
					strDiaChi = tmp[3];
					strDKKCB = tmp[4];
					strTuNgay = tmp[5];
					strDenNgay= tmp[6];
					//
				}
				else{
					strDiaChi = "";
					strDKKCB = "";
					strTuNgay = "";
					strDenNgay= "";
				}
			}
		}
		
	}
}
