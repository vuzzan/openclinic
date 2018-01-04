package com.openclinic;

import java.util.Hashtable;

import javax.xml.bind.DatatypeConverter;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CBanner;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import com.openclinic.utils.Utils;

public class TheBHXH extends Composite {
	private Combo txt1;
	private Text txt2;
	private Text txt6;
	private Text txt5;
	private Text txt3;
	private Text txt4;
	private Hashtable<String, Integer> hashValue = new Hashtable<String, Integer>();
	private String[] listString = new String[] {"DN", "TV", "HX", "CH", "NN", "TK", "HC", "XK", "CA", "HT", "TB", "MS", "XB", "XN", "TN", "CC", "CK", "CB", "KC", "HD", "BT", "HN", "TC", "TQ", "TA", "TY", "TE", "HG", "LS", "CN", "HS", "GD", "TL", "XV", "NO"};
	private int[] muchuong = {0, 100, 100, 95, 80, 100, 100, 100};
	//                        0  1    2    3   4   5    6    7
	private Label txt7;
	public String strMathe = "";
	/* (non-Javadoc)
	 * @see org.eclipse.swt.widgets.Control#setEnabled(boolean)
	 */
	@Override
	public void setEnabled(boolean enabled) {
		super.setEnabled(enabled);
		if(enabled==true){
			this.setColor(SWTResourceManager.getColor(SWT.COLOR_YELLOW));
//			updateMaThe(txtMathe);
		}
		else{
			this.setColor(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
//			txt1.setText("");
//			txt2.setText("");
//			txt3.setText("");
//			txt4.setText("");
//			txt5.setText("");
//			txt6.setText("");
		}
	}
	public int iTyLeHuong= 0;
	//
	public String txtMathe;
	public String txtHoTen;
	public String txtNgaySinh;
	public String txtGioiTinh;
	public String txtDiaChi;
	public String txtMaDKKCBBD;
	public String txtTuNgay;
	public String txtDenNgay;
	public String txtNgayCap;
	public String txtMaQuanLy;
	public String txtTenChaMe;
	public String txtMaHuongChiPhiDichVuCao;
	public String txtThoiDiemHuongChiPhiDichVuCao;
	public String txtChuoiKiemTra;	
	private Composite parent;
	public TheBHXH(Composite parent, int style) {
		super(parent, style);
		this.parent = parent;
		addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(txt1!=null){
					txt1.forceFocus();
				}
			}
		});
		setToolTipText("Bấm qua lại để chỉnh ngày tháng năm, gõ vào Năm số tuổi");
		initData();
		initialize();
		updateMaThe("");
	}

	private void initData() {
		hashValue.put("TY3",95);
		hashValue.put("XB1",100);
		hashValue.put("XB3",95);
		hashValue.put("XK1",100);
		hashValue.put("XK3",95);
		hashValue.put("XN1",100);
		hashValue.put("XN3",95);
		hashValue.put("TQ3",95);
		hashValue.put("TA2",100);
		hashValue.put("TY4",80);
		hashValue.put("HG4",80);
		hashValue.put("LS4",80);
		hashValue.put("CN5",80);
		hashValue.put("HS4",80);
		hashValue.put("SV4",80);
		hashValue.put("GB4",80);
		hashValue.put("GD4",80);
		hashValue.put("CK2",100);
		hashValue.put("CB2",100);
		hashValue.put("KC2",100);
		hashValue.put("HD4",80);
		hashValue.put("TE1",100);
		hashValue.put("BT2",100);
		hashValue.put("HN2",100);
		hashValue.put("DT2",100);
		hashValue.put("DK2",100);
		hashValue.put("XD2",100);
		hashValue.put("TS2",100);
		hashValue.put("TC3",95);
		hashValue.put("TQ4",80);
		hashValue.put("TA4",80);
		hashValue.put("HX4",80);
		hashValue.put("CH4",80);
		hashValue.put("NN4",80);
		hashValue.put("TK4",80);
		hashValue.put("HC4",80);
		hashValue.put("XK4",80);
		hashValue.put("HT3",95);
		hashValue.put("TB4",80);
		hashValue.put("NO4",80);
		hashValue.put("CT4",80);
		hashValue.put("XB4",80);
		hashValue.put("TN4",80);
		hashValue.put("CS4",80);
		hashValue.put("QN5",100);
		hashValue.put("CA5",100);
		hashValue.put("CY5",100);
		hashValue.put("XN4",80);
		hashValue.put("MS4",80);
		hashValue.put("CC1",100);
		hashValue.put("DN4",80);
		hashValue.put("BT1",100);
		hashValue.put("CB1",100);
		hashValue.put("CK1",100);
		hashValue.put("DK1",100);
		hashValue.put("DT1",100);
		hashValue.put("HN1",100);
		hashValue.put("KC1",100);
		hashValue.put("TS1",100);
		hashValue.put("XD1",100);
		hashValue.put("CN2",100);
		hashValue.put("HT2",100);
		hashValue.put("TC2",100);
		hashValue.put("CN1",100);
		hashValue.put("HT1",100);
		hashValue.put("TC1",100);
		hashValue.put("CH2",100);
		hashValue.put("CS2",100);
		hashValue.put("CT2",100);
		hashValue.put("DN2",100);
		hashValue.put("HC2",100);
		hashValue.put("HD2",100);
		hashValue.put("HG2",100);
		hashValue.put("HS2",100);
		hashValue.put("HX2",100);
		hashValue.put("MS2",100);
		hashValue.put("NN2",100);
		hashValue.put("NO2",100);
		hashValue.put("TA2",100);
		hashValue.put("TB2",100);
		hashValue.put("TK2",100);
		hashValue.put("TN2",100);
		hashValue.put("TQ2",100);
		hashValue.put("TY2",100);
		hashValue.put("XB2",100);
		hashValue.put("XK2",100);
		hashValue.put("XN2",100);
		hashValue.put("SV2",100);
		hashValue.put("CH1",100);
		hashValue.put("CH3",95);
		hashValue.put("CS1",100);
		hashValue.put("CS3",95);
		hashValue.put("CT1",100);
		hashValue.put("CT3",95);
		hashValue.put("DN1",100);
		hashValue.put("DN3",95);
		hashValue.put("HC3",95);
		hashValue.put("HD3",95);
		hashValue.put("HS3",95);
		hashValue.put("HX1",100);
		hashValue.put("HX3",95);
		hashValue.put("LS3",95);
		hashValue.put("MS1",100);
		hashValue.put("MS3",95);
		hashValue.put("NN1",100);
		hashValue.put("NN3",95);
		hashValue.put("NO1",100);
		hashValue.put("NO3",95);
		hashValue.put("SV3",95);
		hashValue.put("TA3",95);
		hashValue.put("TB1",100);
		hashValue.put("TB3",95);
		hashValue.put("TK1",100);
		hashValue.put("TK3",95);
		hashValue.put("TN1",100);
		hashValue.put("TN3",95);
		hashValue.put("HT5",95);
		hashValue.put("MS7",80);
		hashValue.put("CN3",95);
		hashValue.put("PV4",80);
		hashValue.put("QN2",100);
		hashValue.put("HC1",100);
		hashValue.put("HG3",95);
		hashValue.put("TQ7",80);
		hashValue.put("PV2",100);
		hashValue.put("GB2",100);
		hashValue.put("GD2",100);
		hashValue.put("XB7",80);
		hashValue.put("KC7",80);
		hashValue.put("TV4",80);
	}

	private void initialize() {
		GridData gridData100 = new org.eclipse.swt.layout.GridData();
		gridData100.widthHint = 40;
		
		GridData gridData = new org.eclipse.swt.layout.GridData();
		gridData.horizontalAlignment = org.eclipse.swt.layout.GridData.END;
		gridData.verticalAlignment = org.eclipse.swt.layout.GridData.FILL;
		setSize(new Point(394, 35));
		GridLayout gridLayout = new GridLayout(7, false);
		gridLayout.marginWidth = 0;
		setLayout(gridLayout);
		
		txt1 = new Combo(this, SWT.NONE);
		txt1.setItems(listString);
		GridData gd_txt1 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_txt1.widthHint = 34;
		txt1.setLayoutData(gd_txt1);
		txt1.setFont(SWTResourceManager.getFont("Courier New", 12, SWT.BOLD));
		txt1.select(0);
		txt1.setText("TE");
		
		txt2 = new Text(this, SWT.BORDER);
		txt2.setToolTipText("Bấm Ctrl+Left, Ctrl+Right để qua lại, Tab để qua tới");
		GridData gd_txt2 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_txt2.widthHint = 27;
		txt2.setLayoutData(gd_txt2);
		txt2.setText("3");
		txt2.setTextLimit(1);
		txt2.setFont(SWTResourceManager.getFont("Courier New", 12, SWT.BOLD));
		
		txt3 = new Text(this, SWT.BORDER);
		GridData gd_txt3 = new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1);
		gd_txt3.widthHint = 35;
		txt3.setLayoutData(gd_txt3);
		txt3.setText("45");
		txt3.setTextLimit(2);
		txt3.setFont(SWTResourceManager.getFont("Courier New", 12, SWT.BOLD));
		
		txt4 = new Text(this, SWT.BORDER);
		GridData gd_txt4 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_txt4.widthHint = 35;
		txt4.setLayoutData(gd_txt4);
		txt4.setText("67");
		txt4.setTextLimit(2);
		txt4.setFont(SWTResourceManager.getFont("Courier New", 12, SWT.BOLD));
		
		txt5 = new Text(this, SWT.BORDER);
		GridData gd_txt5 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_txt5.widthHint = 42;
		txt5.setLayoutData(gd_txt5);
		txt5.setText("789");
		txt5.setTextLimit(3);
		txt5.setFont(SWTResourceManager.getFont("Courier New", 12, SWT.BOLD));
		
		txt6 = new Text(this, SWT.BORDER);
		GridData gd_txt6 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_txt6.widthHint = 58;
		txt6.setLayoutData(gd_txt6);
		txt6.setText("12345");
		txt6.setTextLimit(5);
		txt6.setFont(SWTResourceManager.getFont("Courier New", 12, SWT.BOLD));
		
		txt7 = new Label(this, SWT.NONE);
		GridData gd_lblLbtext = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_lblLbtext.widthHint = 26;
		txt7.setLayoutData(gd_lblLbtext);
		txt7.setText("90");
		
		txt1.setToolTipText("Bấm Ctrl+Left, Ctrl+Right để qua lại, Tab để qua tới");
		txt3.setToolTipText("Bấm Ctrl+Left, Ctrl+Right để qua lại, Tab để qua tới");
		txt4.setToolTipText("Bấm Ctrl+Left, Ctrl+Right để qua lại, Tab để qua tới");
		txt5.setToolTipText("Bấm Ctrl+Left, Ctrl+Right để qua lại, Tab để qua tới");
		txt6.setToolTipText("Bấm Ctrl+Left, Ctrl+Right để qua lại, Tab để qua tới");
		txt1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				//System.out.println("e.keyCode="+e.keyCode +" e.character="+e.character);
				//if (e.keyCode==13 || (e.keyCode == 52 && e.character == '$')) {
				if (e.keyCode==13 ) {
					if(e.character == '$'){
						extractData(txt1.getText() + "$");
						e.doit = false;
						return;
					}
					if ((txt1.getText().trim().length()<100)) {
						extractDataThe(txt1.getText().trim());
						e.doit = false;
						return;
					}
				}
				
				if(Character.isDigit(e.character)==false){
					if(e.keyCode==SWT.ARROW_RIGHT &&  (((e.stateMask & SWT.CTRL) == SWT.CTRL))){
						txt2.forceFocus();
						e.doit = false;
					}
					else if(e.keyCode==SWT.ARROW_LEFT &&  (((e.stateMask & SWT.CTRL) == SWT.CTRL))){
						txt6.forceFocus();
						e.doit = false;
					}
					else{
					}
				}
				else{
					controlKey(e);
				}
			}
		});
		txt1.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				txt1.setText(txt1.getText().toUpperCase());
				int idx = txt1.getSelectionIndex();
				if(idx<0){
					//System.out.println("ERROR ");
					//txt1.setBackground(SWTResourceManager.getColor(SWT.COLOR_RED));
				}
				else{
					//txt1.setBackground(SWTResourceManager.getColor(SWT.COLOR_YELLOW));
				}
			}
		});
		txt2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(Character.isDigit(e.character)==false){
					if(e.keyCode==SWT.ARROW_RIGHT &&  (((e.stateMask & SWT.CTRL) == SWT.CTRL))){
						txt3.forceFocus();
					}
					else if(e.keyCode==SWT.ARROW_LEFT &&  (((e.stateMask & SWT.CTRL) == SWT.CTRL))){
						txt1.forceFocus();
					}
					else{
						//e.doit = false;
					}
					controlKey(e);
				}
				else{
					if(txt2.getText().length()==1){
						//txt3.forceFocus();
					}
				}
			}
			@Override
			public void keyReleased(KeyEvent e) {
				if(txt2.getText().length()==1){
					txt2.forceFocus();
					//getParent().forceFocus();
				}
			}
		});
		txt3.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(Character.isDigit(e.character)==false){
					if(e.keyCode==SWT.ARROW_RIGHT &&  (((e.stateMask & SWT.CTRL) == SWT.CTRL))){
						txt4.forceFocus();
					}
					else if(e.keyCode==SWT.ARROW_LEFT &&  (((e.stateMask & SWT.CTRL) == SWT.CTRL))){
						txt2.forceFocus();
					}
					else{
						//e.doit = false;
					}
					controlKey(e);
				}
				else{
					if(txt3.getText().length()==2){
						//txt4.forceFocus();
					}
				}
			}
			@Override
			public void keyReleased(KeyEvent e) {
				if(txt3.getText().length()==2){
					//txt4.forceFocus();
					txt3.selectAll();
				}
			}
		});
		txt4.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(Character.isDigit(e.character)==false){
					if(e.keyCode==SWT.ARROW_RIGHT &&  (((e.stateMask & SWT.CTRL) == SWT.CTRL))){
						txt5.forceFocus();
					}
					else if(e.keyCode==SWT.ARROW_LEFT &&  (((e.stateMask & SWT.CTRL) == SWT.CTRL))){
						txt3.forceFocus();
					}
					else{
						//e.doit = false;
					}
					controlKey(e);

				}
				else{
					if(txt4.getText().length()==2){
						//txt5.forceFocus();
					}
				}
			}
			@Override
			public void keyReleased(KeyEvent e) {
				if(txt4.getText().length()==2){
					//txt4.forceFocus();
					txt4.selectAll();
				}
			}
		});
		txt5.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(Character.isDigit(e.character)==false){
					if(e.keyCode==SWT.ARROW_RIGHT &&  (((e.stateMask & SWT.CTRL) == SWT.CTRL))){
						txt6.forceFocus();
					}
					else if(e.keyCode==SWT.ARROW_LEFT &&  (((e.stateMask & SWT.CTRL) == SWT.CTRL))){
						txt4.forceFocus();
					}
					else{
						//e.doit = false;
					}
					controlKey(e);

				}
				else{
					if(txt5.getText().length()==3){
						//txt6.forceFocus();
					}
				}
			}
			@Override
			public void keyReleased(KeyEvent e) {
				if(txt5.getText().length()==3){
					//txt4.forceFocus();
					txt5.selectAll();
				}
			}
		});
		txt6.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(Character.isDigit(e.character)==false){
					if(e.keyCode==SWT.ARROW_RIGHT &&  (((e.stateMask & SWT.CTRL) == SWT.CTRL))){
						txt1.forceFocus();
					}
					else if(e.keyCode==SWT.ARROW_LEFT &&  (((e.stateMask & SWT.CTRL) == SWT.CTRL))){
						txt5.forceFocus();
					}
					else{
						//e.doit = false;
					}
				}
				else{
					if(txt6.getText().length()==5){
						getParent().forceFocus();
					}
				}
			}
			@Override
			public void keyReleased(KeyEvent e) {
				if(txt6.getText().length()==5){
					//txt4.forceFocus();
					//getParent().forceFocus();
				}
			}
		});
		txt2.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				//txt2.selectAll();
				updateValue();
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(txt2.getText().trim().length()!=1){
					txt2.setBackground(SWTResourceManager.getColor(SWT.COLOR_RED));
				}
				else{
					txt2.setBackground(SWTResourceManager.getColor(SWT.COLOR_YELLOW));
					updateValue();
				}
			}
		});
		txt3.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				//txt3.selectAll();
				updateValue();
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(txt3.getText().trim().length()!=2){
					//MessageDialog.openError(getShell(), "Lỗi", "2 ky tự");
					txt3.setBackground(SWTResourceManager.getColor(SWT.COLOR_RED));
				}
				else{
					txt3.setBackground(SWTResourceManager.getColor(SWT.COLOR_YELLOW));
					updateValue();
				}
			}
		});
		txt4.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				//txt4.selectAll();
				updateValue();
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(txt4.getText().trim().length()!=2){
					txt4.setBackground(SWTResourceManager.getColor(SWT.COLOR_RED));
				}
				else{
					txt4.setBackground(SWTResourceManager.getColor(SWT.COLOR_YELLOW));
				}
				txt4.setText(txt4.getText().toUpperCase());
				updateValue();
			}
		});
		txt5.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				//txt5.selectAll();
				updateValue();
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(txt5.getText().trim().length()!=3){
					txt5.setBackground(SWTResourceManager.getColor(SWT.COLOR_RED));
				}
				else{
					txt5.setBackground(SWTResourceManager.getColor(SWT.COLOR_YELLOW));
					updateValue();
				}
			}
		});
		txt6.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				//txt6.selectAll();
				updateValue();
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(txt6.getText().trim().length()!=5){
					txt6.setBackground(SWTResourceManager.getColor(SWT.COLOR_RED));
				}
				else{
					txt6.setBackground(SWTResourceManager.getColor(SWT.COLOR_YELLOW));
					updateValue();
				}
			}
		});

	}
	protected void extractDataThe(String hexData) {
		hexData = hexData.replaceAll(" ", "");
		System.out.println("--------------ExtractThe "+ hexData);
		if (hexData.length() == 15) {
			//String hexData = "GD1234567890123";
			System.out.println(hexData.substring(0,2));
			System.out.println(hexData.substring(2,3));
			System.out.println(hexData.substring(3,5));
			System.out.println(hexData.substring(5,7));
			System.out.println(hexData.substring(7,10));
			System.out.println(hexData.substring(10,15));
			//
			txt1.setText(hexData.substring(0,2));
			txt2.setText(hexData.substring(2,3));
			txt3.setText(hexData.substring(3,5));
			txt4.setText(hexData.substring(5,7));
			txt5.setText(hexData.substring(7,10));
			txt6.setText(hexData.substring(10,15));
			//
		}
	}

	protected void controlKey(KeyEvent e) {
		if (((e.stateMask & SWT.CTRL) == SWT.CTRL)) {
			Event ev = new Event();
		    ev.type = SWT.KeyDown;
		    ev.stateMask = SWT.CTRL;
		    ev.keyCode = e.keyCode;
		    //
		    //
			getParent().notifyListeners(SWT.KeyDown, ev);
			return;
		}
		Event ev = new Event();
	    ev.type = SWT.KeyDown;
	    ev.keyCode = e.keyCode;
		//getParent().notifyListeners(SWT.KeyDown, ev);
	}

	public static String convertHex2String(String hexStr) {
		// String hxStr =
		// "4E677579E1BB856E204E67E1BB8D63205068C6B0C6A16E67205468616E68";
		String dest = "";
		try {
			dest = new String(DatatypeConverter.parseHexBinary(hexStr), "UTF-8");
		} catch (Exception ex) {

		}
		return dest;
	}
	private static boolean checkValid(String[] data) {
		//
		String name = convertHex2String(data[1]);
		String address = convertHex2String(data[4]);
		if (name.length() < 5 || address.length() < 5) {
			return false;
		}

		return true;
	}
	
	protected void extractData(String hexData) {
		System.out.println("Extract "+ hexData);
		String data[] = hexData.split("\\|");
		if (data.length == 15) {
			txtMathe = data[0];
			txtHoTen = convertHex2String(data[1]);
			txtNgaySinh = data[2];
			txtGioiTinh = data[3];
			txtDiaChi = convertHex2String(data[4]);
			txtMaDKKCBBD = data[5].replace("-", "").replace(" ","");
			txtTuNgay = data[6];
			txtDenNgay  = data[7];
			
			txtNgayCap = data[8];
			txtMaQuanLy = data[9];
			txtTenChaMe = convertHex2String(data[10]);
			txtMaHuongChiPhiDichVuCao = data[11];
			txtThoiDiemHuongChiPhiDichVuCao = data[12];
			txtChuoiKiemTra = data[13];
			// Process tylehuong
			strMathe = txtMathe;
			//
			processMaThe(strMathe);
			updateMaThe(strMathe);
			this.getParent().forceFocus();
			//
		}
	}

	public void setText(String strMathe){
		processMaThe(strMathe);
		updateMaThe(strMathe);
		
	}
	public void updateMaThe(String strMathe2) {
		//HS7790900400931
		//0123456789
		if(txt1==null || strMathe2==null){
			return;
		}
		strMathe = strMathe2;
//TE3790900400931
		System.out.println("updateMaThe "+strMathe.length());
		if(strMathe.length()==15){
			txt1.setText(strMathe2.substring(0, 2));//2
			txt2.setText(strMathe2.substring(2, 3));//1
			txt3.setText(strMathe2.substring(3, 5));//2
			txt4.setText(strMathe2.substring(5, 7));//2
			txt5.setText(strMathe2.substring(7, 10));//3
			txt6.setText(strMathe2.substring(10, 15));//5
			txtMathe = strMathe;
			setColor(SWTResourceManager.getColor(SWT.COLOR_YELLOW));
			txt7.setText(""+iTyLeHuong);
		}
		else{
			txt1.setText("");//2
			txt2.setText("");//2
			txt3.setText("");//2
			txt4.setText("");//2
			txt5.setText("");//2
			txt6.setText("");//2
			txt7.setText(""+iTyLeHuong);
		}
	}

	public void processMaThe(String strMathe2) {
		if(strMathe2.length()==0){
			iTyLeHuong = 0;
			return;
		}
		String str1 = strMathe2.substring(0, 2);
		String str2 = strMathe2.substring(2, 3);
		//System.out.println("["+str1+"] ["+str2+"]");
		Integer val = hashValue.get(str1+str2);
		//
		if(val!=null){
			iTyLeHuong = val.intValue();
		}
		else{
			int muc = Utils.getInt(str2);
			if(muc<muchuong.length){
				int value = muchuong[muc];
				iTyLeHuong = value;
			}
			else{
				iTyLeHuong = 80;
			}
		}
		//
		System.out.println("Tyle="+iTyLeHuong+" ");
		//
	}
	public void setColor(Color color) {
		txt1.setBackground(color);
		txt2.setBackground(color);
		txt3.setBackground(color);
		txt4.setBackground(color);
		txt5.setBackground(color);
		txt6.setBackground(color);
	}
	public String getMaThe(){
		return strMathe;
	}
	public int getTyLeHuong(){
		return iTyLeHuong;
	}
	protected void updateValue() {
		Integer val = hashValue.get(txt1.getText()+txt2.getText());
		//
		if(val!=null){
			iTyLeHuong = val.intValue();
		}
		else{
			int muc = Utils.getInt(txt2.getText());
			if(muc<muchuong.length){
				int value = muchuong[muc];
				iTyLeHuong = value;
			}
			else{
				iTyLeHuong = 80;
			}
		}
		strMathe = txt1.getText()+txt2.getText()+txt3.getText()+txt4.getText()+txt5.getText()+txt6.getText();
		strMathe = strMathe.toUpperCase();
		txtMathe = strMathe; 
		txt7.setText(""+iTyLeHuong);
		//processMaThe("HS1234567891011");
		System.out.println("updateValue strMathe="+strMathe);
		//
	}
}