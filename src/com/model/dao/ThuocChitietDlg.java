package com.model.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Monitor;

import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;

import com.openclinic.utils.Utils;
import com.DbHelper;

public class ThuocChitietDlg extends Dialog {
	static Logger logger = LogManager.getLogger(ThuocChitietDlg.class.getName());

	protected Object result;
	protected Shell shell;
    private Text txtSTT;
    private Text txtMA_LK;
    private Text txtTHUOC_ID;
    private Text txtMA_THUOC;
    private Text txtMA_NHOM;
    private Text txtTEN_THUOC;
    private Text txtDON_VI_TINH;
    private Text txtHAM_LUONG;
    private Text txtDUONG_DUNG;
    private Text txtLIEU_DUNG;
    private Text txtSO_DANG_KY;
    private Text txtTT_THAU;
    private Text txtPHAM_VI;
    private Text txtSO_LUONG;
    private Text txtDON_GIA;
    private Text txtTHANH_TIEN;
    private Text txtMA_KHOA;
    private Text txtMA_BAC_SI;
    private Text txtMA_BENH;
    private Text txtMA_PTTT;
    private Text txtTYLE_TT;
    private Text txtCT_ID;
    private Text txtMUC_HUONG;
    private Text txtT_NGUON_KHAC;
    private Text txtT_BNCCT;
    private Text txtT_NGOAIDS;
    private Text txtNT_ID;
    private Text txtTT_BHTT;
    private Text txtTT_BNTT;
    private Text txtKHO_ID;
    private Text txtKHO_NAME;
    private Text txtCUR_DATE;
    private Text txtTYP;
    private Text txtTHANHTOAN;
    private Text txtNV_ID;
    private Text txtNV_NAME;
    private Text txtSTS;

    public ThuocChitiet objThuocChitiet;
    Button btnNewButtonSaveThuocChitiet;

    public int intTypeDlgThuocChitiet;
	public static final int TYPE_DLG_VIEW = 1;
	public static final int TYPE_DLG_EDIT = 2;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public ThuocChitietDlg(Shell parent, int style) {
		super(parent, style);
		setText("SWT Dialog");
	}

	/**
	 * Open the dialog.
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
        //
		while (!shell.isDisposed()) {
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
		shell = new Shell(getParent(), SWT.SHELL_TRIM | SWT.BORDER | SWT.PRIMARY_MODAL);
		shell.setSize(450, 300);
		shell.setText("ThuocChitietDlg EDIT/NEW");
		shell.setLayout(new GridLayout(2, false));
        shell.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.keyCode==SWT.ESC){
					objThuocChitiet = null;
				}
			}
		});
        
		Label lbltxtSTT = new Label(shell, SWT.NONE);
        lbltxtSTT.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtSTT.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtSTT.setText("STT :");
		
		txtSTT = new Text(shell, SWT.BORDER);
        txtSTT.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtSTT.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtSTT.setText("STT");
        txtSTT.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressThuocChitietDlg(e);
			}
		});
		Label lbltxtMA_LK = new Label(shell, SWT.NONE);
        lbltxtMA_LK.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtMA_LK.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtMA_LK.setText("MA_LK :");
		
		txtMA_LK = new Text(shell, SWT.BORDER);
        txtMA_LK.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtMA_LK.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtMA_LK.setText("MA_LK");
        txtMA_LK.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressThuocChitietDlg(e);
			}
		});
		Label lbltxtTHUOC_ID = new Label(shell, SWT.NONE);
        lbltxtTHUOC_ID.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtTHUOC_ID.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtTHUOC_ID.setText("THUOC_ID :");
		
		txtTHUOC_ID = new Text(shell, SWT.BORDER);
        txtTHUOC_ID.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtTHUOC_ID.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtTHUOC_ID.setText("THUOC_ID");
        txtTHUOC_ID.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressThuocChitietDlg(e);
			}
		});
		Label lbltxtMA_THUOC = new Label(shell, SWT.NONE);
        lbltxtMA_THUOC.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtMA_THUOC.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtMA_THUOC.setText("MA_THUOC :");
		
		txtMA_THUOC = new Text(shell, SWT.BORDER);
        txtMA_THUOC.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtMA_THUOC.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtMA_THUOC.setText("MA_THUOC");
        txtMA_THUOC.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressThuocChitietDlg(e);
			}
		});
		Label lbltxtMA_NHOM = new Label(shell, SWT.NONE);
        lbltxtMA_NHOM.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtMA_NHOM.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtMA_NHOM.setText("MA_NHOM :");
		
		txtMA_NHOM = new Text(shell, SWT.BORDER);
        txtMA_NHOM.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtMA_NHOM.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtMA_NHOM.setText("MA_NHOM");
        txtMA_NHOM.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressThuocChitietDlg(e);
			}
		});
		Label lbltxtTEN_THUOC = new Label(shell, SWT.NONE);
        lbltxtTEN_THUOC.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtTEN_THUOC.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtTEN_THUOC.setText("TEN_THUOC :");
		
		txtTEN_THUOC = new Text(shell, SWT.BORDER);
        txtTEN_THUOC.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtTEN_THUOC.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtTEN_THUOC.setText("TEN_THUOC");
        txtTEN_THUOC.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressThuocChitietDlg(e);
			}
		});
		Label lbltxtDON_VI_TINH = new Label(shell, SWT.NONE);
        lbltxtDON_VI_TINH.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtDON_VI_TINH.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtDON_VI_TINH.setText("DON_VI_TINH :");
		
		txtDON_VI_TINH = new Text(shell, SWT.BORDER);
        txtDON_VI_TINH.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtDON_VI_TINH.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtDON_VI_TINH.setText("DON_VI_TINH");
        txtDON_VI_TINH.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressThuocChitietDlg(e);
			}
		});
		Label lbltxtHAM_LUONG = new Label(shell, SWT.NONE);
        lbltxtHAM_LUONG.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtHAM_LUONG.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtHAM_LUONG.setText("HAM_LUONG :");
		
		txtHAM_LUONG = new Text(shell, SWT.BORDER);
        txtHAM_LUONG.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtHAM_LUONG.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtHAM_LUONG.setText("HAM_LUONG");
        txtHAM_LUONG.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressThuocChitietDlg(e);
			}
		});
		Label lbltxtDUONG_DUNG = new Label(shell, SWT.NONE);
        lbltxtDUONG_DUNG.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtDUONG_DUNG.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtDUONG_DUNG.setText("DUONG_DUNG :");
		
		txtDUONG_DUNG = new Text(shell, SWT.BORDER);
        txtDUONG_DUNG.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtDUONG_DUNG.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtDUONG_DUNG.setText("DUONG_DUNG");
        txtDUONG_DUNG.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressThuocChitietDlg(e);
			}
		});
		Label lbltxtLIEU_DUNG = new Label(shell, SWT.NONE);
        lbltxtLIEU_DUNG.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtLIEU_DUNG.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtLIEU_DUNG.setText("LIEU_DUNG :");
		
		txtLIEU_DUNG = new Text(shell, SWT.BORDER);
        txtLIEU_DUNG.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtLIEU_DUNG.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtLIEU_DUNG.setText("LIEU_DUNG");
        txtLIEU_DUNG.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressThuocChitietDlg(e);
			}
		});
		Label lbltxtSO_DANG_KY = new Label(shell, SWT.NONE);
        lbltxtSO_DANG_KY.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtSO_DANG_KY.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtSO_DANG_KY.setText("SO_DANG_KY :");
		
		txtSO_DANG_KY = new Text(shell, SWT.BORDER);
        txtSO_DANG_KY.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtSO_DANG_KY.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtSO_DANG_KY.setText("SO_DANG_KY");
        txtSO_DANG_KY.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressThuocChitietDlg(e);
			}
		});
		Label lbltxtTT_THAU = new Label(shell, SWT.NONE);
        lbltxtTT_THAU.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtTT_THAU.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtTT_THAU.setText("TT_THAU :");
		
		txtTT_THAU = new Text(shell, SWT.BORDER);
        txtTT_THAU.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtTT_THAU.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtTT_THAU.setText("TT_THAU");
        txtTT_THAU.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressThuocChitietDlg(e);
			}
		});
		Label lbltxtPHAM_VI = new Label(shell, SWT.NONE);
        lbltxtPHAM_VI.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtPHAM_VI.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtPHAM_VI.setText("PHAM_VI :");
		
		txtPHAM_VI = new Text(shell, SWT.BORDER);
        txtPHAM_VI.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtPHAM_VI.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtPHAM_VI.setText("PHAM_VI");
        txtPHAM_VI.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressThuocChitietDlg(e);
			}
		});
		Label lbltxtSO_LUONG = new Label(shell, SWT.NONE);
        lbltxtSO_LUONG.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtSO_LUONG.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtSO_LUONG.setText("SO_LUONG :");
		
		txtSO_LUONG = new Text(shell, SWT.BORDER);
        txtSO_LUONG.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtSO_LUONG.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtSO_LUONG.setText("SO_LUONG");
        txtSO_LUONG.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressThuocChitietDlg(e);
			}
		});
		Label lbltxtDON_GIA = new Label(shell, SWT.NONE);
        lbltxtDON_GIA.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtDON_GIA.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtDON_GIA.setText("DON_GIA :");
		
		txtDON_GIA = new Text(shell, SWT.BORDER);
        txtDON_GIA.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtDON_GIA.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtDON_GIA.setText("DON_GIA");
        txtDON_GIA.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressThuocChitietDlg(e);
			}
		});
		Label lbltxtTHANH_TIEN = new Label(shell, SWT.NONE);
        lbltxtTHANH_TIEN.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtTHANH_TIEN.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtTHANH_TIEN.setText("THANH_TIEN :");
		
		txtTHANH_TIEN = new Text(shell, SWT.BORDER);
        txtTHANH_TIEN.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtTHANH_TIEN.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtTHANH_TIEN.setText("THANH_TIEN");
        txtTHANH_TIEN.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressThuocChitietDlg(e);
			}
		});
		Label lbltxtMA_KHOA = new Label(shell, SWT.NONE);
        lbltxtMA_KHOA.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtMA_KHOA.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtMA_KHOA.setText("MA_KHOA :");
		
		txtMA_KHOA = new Text(shell, SWT.BORDER);
        txtMA_KHOA.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtMA_KHOA.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtMA_KHOA.setText("MA_KHOA");
        txtMA_KHOA.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressThuocChitietDlg(e);
			}
		});
		Label lbltxtMA_BAC_SI = new Label(shell, SWT.NONE);
        lbltxtMA_BAC_SI.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtMA_BAC_SI.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtMA_BAC_SI.setText("MA_BAC_SI :");
		
		txtMA_BAC_SI = new Text(shell, SWT.BORDER);
        txtMA_BAC_SI.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtMA_BAC_SI.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtMA_BAC_SI.setText("MA_BAC_SI");
        txtMA_BAC_SI.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressThuocChitietDlg(e);
			}
		});
		Label lbltxtMA_BENH = new Label(shell, SWT.NONE);
        lbltxtMA_BENH.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtMA_BENH.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtMA_BENH.setText("MA_BENH :");
		
		txtMA_BENH = new Text(shell, SWT.BORDER);
        txtMA_BENH.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtMA_BENH.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtMA_BENH.setText("MA_BENH");
        txtMA_BENH.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressThuocChitietDlg(e);
			}
		});
		Label lbltxtMA_PTTT = new Label(shell, SWT.NONE);
        lbltxtMA_PTTT.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtMA_PTTT.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtMA_PTTT.setText("MA_PTTT :");
		
		txtMA_PTTT = new Text(shell, SWT.BORDER);
        txtMA_PTTT.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtMA_PTTT.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtMA_PTTT.setText("MA_PTTT");
        txtMA_PTTT.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressThuocChitietDlg(e);
			}
		});
		Label lbltxtTYLE_TT = new Label(shell, SWT.NONE);
        lbltxtTYLE_TT.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtTYLE_TT.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtTYLE_TT.setText("TYLE_TT :");
		
		txtTYLE_TT = new Text(shell, SWT.BORDER);
        txtTYLE_TT.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtTYLE_TT.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtTYLE_TT.setText("TYLE_TT");
        txtTYLE_TT.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressThuocChitietDlg(e);
			}
		});
		Label lbltxtCT_ID = new Label(shell, SWT.NONE);
        lbltxtCT_ID.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtCT_ID.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtCT_ID.setText("CT_ID :");
		
		txtCT_ID = new Text(shell, SWT.BORDER);
        txtCT_ID.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtCT_ID.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtCT_ID.setText("CT_ID");
        txtCT_ID.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressThuocChitietDlg(e);
			}
		});
		Label lbltxtMUC_HUONG = new Label(shell, SWT.NONE);
        lbltxtMUC_HUONG.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtMUC_HUONG.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtMUC_HUONG.setText("MUC_HUONG :");
		
		txtMUC_HUONG = new Text(shell, SWT.BORDER);
        txtMUC_HUONG.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtMUC_HUONG.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtMUC_HUONG.setText("MUC_HUONG");
        txtMUC_HUONG.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressThuocChitietDlg(e);
			}
		});
		Label lbltxtT_NGUON_KHAC = new Label(shell, SWT.NONE);
        lbltxtT_NGUON_KHAC.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtT_NGUON_KHAC.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtT_NGUON_KHAC.setText("T_NGUON_KHAC :");
		
		txtT_NGUON_KHAC = new Text(shell, SWT.BORDER);
        txtT_NGUON_KHAC.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtT_NGUON_KHAC.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtT_NGUON_KHAC.setText("T_NGUON_KHAC");
        txtT_NGUON_KHAC.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressThuocChitietDlg(e);
			}
		});
		Label lbltxtT_BNCCT = new Label(shell, SWT.NONE);
        lbltxtT_BNCCT.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtT_BNCCT.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtT_BNCCT.setText("T_BNCCT :");
		
		txtT_BNCCT = new Text(shell, SWT.BORDER);
        txtT_BNCCT.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtT_BNCCT.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtT_BNCCT.setText("T_BNCCT");
        txtT_BNCCT.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressThuocChitietDlg(e);
			}
		});
		Label lbltxtT_NGOAIDS = new Label(shell, SWT.NONE);
        lbltxtT_NGOAIDS.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtT_NGOAIDS.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtT_NGOAIDS.setText("T_NGOAIDS :");
		
		txtT_NGOAIDS = new Text(shell, SWT.BORDER);
        txtT_NGOAIDS.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtT_NGOAIDS.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtT_NGOAIDS.setText("T_NGOAIDS");
        txtT_NGOAIDS.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressThuocChitietDlg(e);
			}
		});
		Label lbltxtNT_ID = new Label(shell, SWT.NONE);
        lbltxtNT_ID.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtNT_ID.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtNT_ID.setText("NT_ID :");
		
		txtNT_ID = new Text(shell, SWT.BORDER);
        txtNT_ID.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtNT_ID.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtNT_ID.setText("NT_ID");
        txtNT_ID.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressThuocChitietDlg(e);
			}
		});
		Label lbltxtTT_BHTT = new Label(shell, SWT.NONE);
        lbltxtTT_BHTT.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtTT_BHTT.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtTT_BHTT.setText("TT_BHTT :");
		
		txtTT_BHTT = new Text(shell, SWT.BORDER);
        txtTT_BHTT.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtTT_BHTT.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtTT_BHTT.setText("TT_BHTT");
        txtTT_BHTT.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressThuocChitietDlg(e);
			}
		});
		Label lbltxtTT_BNTT = new Label(shell, SWT.NONE);
        lbltxtTT_BNTT.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtTT_BNTT.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtTT_BNTT.setText("TT_BNTT :");
		
		txtTT_BNTT = new Text(shell, SWT.BORDER);
        txtTT_BNTT.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtTT_BNTT.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtTT_BNTT.setText("TT_BNTT");
        txtTT_BNTT.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressThuocChitietDlg(e);
			}
		});
		Label lbltxtKHO_ID = new Label(shell, SWT.NONE);
        lbltxtKHO_ID.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtKHO_ID.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtKHO_ID.setText("KHO_ID :");
		
		txtKHO_ID = new Text(shell, SWT.BORDER);
        txtKHO_ID.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtKHO_ID.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtKHO_ID.setText("KHO_ID");
        txtKHO_ID.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressThuocChitietDlg(e);
			}
		});
		Label lbltxtKHO_NAME = new Label(shell, SWT.NONE);
        lbltxtKHO_NAME.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtKHO_NAME.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtKHO_NAME.setText("KHO_NAME :");
		
		txtKHO_NAME = new Text(shell, SWT.BORDER);
        txtKHO_NAME.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtKHO_NAME.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtKHO_NAME.setText("KHO_NAME");
        txtKHO_NAME.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressThuocChitietDlg(e);
			}
		});
		Label lbltxtCUR_DATE = new Label(shell, SWT.NONE);
        lbltxtCUR_DATE.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtCUR_DATE.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtCUR_DATE.setText("CUR_DATE :");
		
		txtCUR_DATE = new Text(shell, SWT.BORDER);
        txtCUR_DATE.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtCUR_DATE.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtCUR_DATE.setText("CUR_DATE");
        txtCUR_DATE.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressThuocChitietDlg(e);
			}
		});
		Label lbltxtTYP = new Label(shell, SWT.NONE);
        lbltxtTYP.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtTYP.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtTYP.setText("TYP :");
		
		txtTYP = new Text(shell, SWT.BORDER);
        txtTYP.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtTYP.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtTYP.setText("TYP");
        txtTYP.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressThuocChitietDlg(e);
			}
		});
		Label lbltxtTHANHTOAN = new Label(shell, SWT.NONE);
        lbltxtTHANHTOAN.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtTHANHTOAN.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtTHANHTOAN.setText("THANHTOAN :");
		
		txtTHANHTOAN = new Text(shell, SWT.BORDER);
        txtTHANHTOAN.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtTHANHTOAN.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtTHANHTOAN.setText("THANHTOAN");
        txtTHANHTOAN.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressThuocChitietDlg(e);
			}
		});
		Label lbltxtNV_ID = new Label(shell, SWT.NONE);
        lbltxtNV_ID.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtNV_ID.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtNV_ID.setText("NV_ID :");
		
		txtNV_ID = new Text(shell, SWT.BORDER);
        txtNV_ID.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtNV_ID.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtNV_ID.setText("NV_ID");
        txtNV_ID.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressThuocChitietDlg(e);
			}
		});
		Label lbltxtNV_NAME = new Label(shell, SWT.NONE);
        lbltxtNV_NAME.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtNV_NAME.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtNV_NAME.setText("NV_NAME :");
		
		txtNV_NAME = new Text(shell, SWT.BORDER);
        txtNV_NAME.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtNV_NAME.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtNV_NAME.setText("NV_NAME");
        txtNV_NAME.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressThuocChitietDlg(e);
			}
		});
		Label lbltxtSTS = new Label(shell, SWT.NONE);
        lbltxtSTS.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtSTS.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtSTS.setText("STS :");
		
		txtSTS = new Text(shell, SWT.BORDER);
        txtSTS.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtSTS.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtSTS.setText("STS");
        txtSTS.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressThuocChitietDlg(e);
			}
		});
		new Label(shell, SWT.NONE);
		btnNewButtonSaveThuocChitiet = new Button(shell, SWT.NONE);
		btnNewButtonSaveThuocChitiet.setImage(SWTResourceManager.getImage(ThuocChitietDlg.class, "/png/file-2x.png"));
        btnNewButtonSaveThuocChitiet.setFont(SWTResourceManager.getFont("Tahoma", 12, SWT.BOLD));
		btnNewButtonSaveThuocChitiet.setText("Save");
        
        btnNewButtonSaveThuocChitiet.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
                saveThuocChitietDlg();
			}
		});
        
        loadThuocChitietDlgData();
	}

    private void saveThuocChitietDlg(){
        if(intTypeDlgThuocChitiet==TYPE_DLG_VIEW){
            return;
        }
        
        if(objThuocChitiet == null){
            objThuocChitiet = new ThuocChitiet();
        }
        if(objThuocChitiet!=null){
        // Integer    = true
            objThuocChitiet.STT = Utils.getInt( txtSTT.getText() );
            // Integer    = true
            objThuocChitiet.MA_LK = Utils.getInt( txtMA_LK.getText() );
            // Integer    = true
            objThuocChitiet.THUOC_ID = Utils.getInt( txtTHUOC_ID.getText() );
            // String     = false
            objThuocChitiet.MA_THUOC = txtMA_THUOC.getText();
            // String     = false
            objThuocChitiet.MA_NHOM = txtMA_NHOM.getText();
            // String     = false
            objThuocChitiet.TEN_THUOC = txtTEN_THUOC.getText();
            // String     = false
            objThuocChitiet.DON_VI_TINH = txtDON_VI_TINH.getText();
            // String     = false
            objThuocChitiet.HAM_LUONG = txtHAM_LUONG.getText();
            // String     = false
            objThuocChitiet.DUONG_DUNG = txtDUONG_DUNG.getText();
            // String     = false
            objThuocChitiet.LIEU_DUNG = txtLIEU_DUNG.getText();
            // String     = false
            objThuocChitiet.SO_DANG_KY = txtSO_DANG_KY.getText();
            // String     = false
            objThuocChitiet.TT_THAU = txtTT_THAU.getText();
            // Integer    = true
            objThuocChitiet.PHAM_VI = Utils.getInt( txtPHAM_VI.getText() );
            // Integer    = true
            objThuocChitiet.SO_LUONG = Utils.getInt( txtSO_LUONG.getText() );
            // Double     = false
            // objThuocChitiet.DON_GIA = txtDON_GIA.getText();
            // Double     = false
            // objThuocChitiet.THANH_TIEN = txtTHANH_TIEN.getText();
            // String     = false
            objThuocChitiet.MA_KHOA = txtMA_KHOA.getText();
            // String     = false
            objThuocChitiet.MA_BAC_SI = txtMA_BAC_SI.getText();
            // String     = false
            objThuocChitiet.MA_BENH = txtMA_BENH.getText();
            // Integer    = true
            objThuocChitiet.MA_PTTT = Utils.getInt( txtMA_PTTT.getText() );
            // Float      = false
            // objThuocChitiet.TYLE_TT = txtTYLE_TT.getText();
            // Integer    = true
            objThuocChitiet.CT_ID = Utils.getInt( txtCT_ID.getText() );
            // Integer    = true
            objThuocChitiet.MUC_HUONG = Utils.getInt( txtMUC_HUONG.getText() );
            // Double     = false
            // objThuocChitiet.T_NGUON_KHAC = txtT_NGUON_KHAC.getText();
            // Double     = false
            // objThuocChitiet.T_BNCCT = txtT_BNCCT.getText();
            // Double     = false
            // objThuocChitiet.T_NGOAIDS = txtT_NGOAIDS.getText();
            // Integer    = true
            objThuocChitiet.NT_ID = Utils.getInt( txtNT_ID.getText() );
            // Double     = false
            // objThuocChitiet.TT_BHTT = txtTT_BHTT.getText();
            // Double     = false
            // objThuocChitiet.TT_BNTT = txtTT_BNTT.getText();
            // Integer    = true
            objThuocChitiet.KHO_ID = Utils.getInt( txtKHO_ID.getText() );
            // String     = false
            objThuocChitiet.KHO_NAME = txtKHO_NAME.getText();
            // Date       = false
            // objThuocChitiet.CUR_DATE = txtCUR_DATE.getText();
            // Integer    = true
            objThuocChitiet.TYP = Utils.getInt( txtTYP.getText() );
            // Integer    = true
            objThuocChitiet.THANHTOAN = Utils.getInt( txtTHANHTOAN.getText() );
            // Integer    = true
            objThuocChitiet.NV_ID = Utils.getInt( txtNV_ID.getText() );
            // String     = false
            objThuocChitiet.NV_NAME = txtNV_NAME.getText();
            // Integer    = true
            objThuocChitiet.STS = Utils.getInt( txtSTS.getText() );
            }
        if(DbHelper.checkPhanQuyen(DbHelper.UPDATE, "thuoc_chitiet")==false){
            logger.info("DON'T HAVE UPDATE RIGHT");
        }
        else if(DbHelper.checkPhanQuyen(DbHelper.INSERT, "thuoc_chitiet")==false){
            logger.info("DON'T HAVE INSERT RIGHT");
        }        
        else{
            objThuocChitiet.insert();
        }
        shell.close();
    }
    
    public void setThuocChitietDlgData(ThuocChitiet obj) {
		this.objThuocChitiet = obj;
	}
    
    public void loadThuocChitietDlgData(){
        if(intTypeDlgThuocChitiet==TYPE_DLG_VIEW){
            btnNewButtonSaveThuocChitiet.setEnabled(false);
        }
        else{
            btnNewButtonSaveThuocChitiet.setEnabled(true);
        }

        if(DbHelper.checkPhanQuyen(DbHelper.READ, "thuoc_chitiet")==false){
            logger.info("DON'T HAVE READ RIGHT");
            return;
        }
        if(objThuocChitiet != null){
            if(objThuocChitiet.STT==null)
                txtSTT.setText("");
            else
                txtSTT.setText(""+objThuocChitiet.STT.toString());
            if(objThuocChitiet.MA_LK==null)
                txtMA_LK.setText("");
            else
                txtMA_LK.setText(""+objThuocChitiet.MA_LK.toString());
            if(objThuocChitiet.THUOC_ID==null)
                txtTHUOC_ID.setText("");
            else
                txtTHUOC_ID.setText(""+objThuocChitiet.THUOC_ID.toString());
            if(objThuocChitiet.MA_THUOC==null)
                txtMA_THUOC.setText("");
            else
                txtMA_THUOC.setText(""+objThuocChitiet.MA_THUOC.toString());
            if(objThuocChitiet.MA_NHOM==null)
                txtMA_NHOM.setText("");
            else
                txtMA_NHOM.setText(""+objThuocChitiet.MA_NHOM.toString());
            if(objThuocChitiet.TEN_THUOC==null)
                txtTEN_THUOC.setText("");
            else
                txtTEN_THUOC.setText(""+objThuocChitiet.TEN_THUOC.toString());
            if(objThuocChitiet.DON_VI_TINH==null)
                txtDON_VI_TINH.setText("");
            else
                txtDON_VI_TINH.setText(""+objThuocChitiet.DON_VI_TINH.toString());
            if(objThuocChitiet.HAM_LUONG==null)
                txtHAM_LUONG.setText("");
            else
                txtHAM_LUONG.setText(""+objThuocChitiet.HAM_LUONG.toString());
            if(objThuocChitiet.DUONG_DUNG==null)
                txtDUONG_DUNG.setText("");
            else
                txtDUONG_DUNG.setText(""+objThuocChitiet.DUONG_DUNG.toString());
            if(objThuocChitiet.LIEU_DUNG==null)
                txtLIEU_DUNG.setText("");
            else
                txtLIEU_DUNG.setText(""+objThuocChitiet.LIEU_DUNG.toString());
            if(objThuocChitiet.SO_DANG_KY==null)
                txtSO_DANG_KY.setText("");
            else
                txtSO_DANG_KY.setText(""+objThuocChitiet.SO_DANG_KY.toString());
            if(objThuocChitiet.TT_THAU==null)
                txtTT_THAU.setText("");
            else
                txtTT_THAU.setText(""+objThuocChitiet.TT_THAU.toString());
            if(objThuocChitiet.PHAM_VI==null)
                txtPHAM_VI.setText("");
            else
                txtPHAM_VI.setText(""+objThuocChitiet.PHAM_VI.toString());
            if(objThuocChitiet.SO_LUONG==null)
                txtSO_LUONG.setText("");
            else
                txtSO_LUONG.setText(""+objThuocChitiet.SO_LUONG.toString());
            if(objThuocChitiet.DON_GIA==null)
                txtDON_GIA.setText("");
            else
                txtDON_GIA.setText(""+objThuocChitiet.DON_GIA.toString());
            if(objThuocChitiet.THANH_TIEN==null)
                txtTHANH_TIEN.setText("");
            else
                txtTHANH_TIEN.setText(""+objThuocChitiet.THANH_TIEN.toString());
            if(objThuocChitiet.MA_KHOA==null)
                txtMA_KHOA.setText("");
            else
                txtMA_KHOA.setText(""+objThuocChitiet.MA_KHOA.toString());
            if(objThuocChitiet.MA_BAC_SI==null)
                txtMA_BAC_SI.setText("");
            else
                txtMA_BAC_SI.setText(""+objThuocChitiet.MA_BAC_SI.toString());
            if(objThuocChitiet.MA_BENH==null)
                txtMA_BENH.setText("");
            else
                txtMA_BENH.setText(""+objThuocChitiet.MA_BENH.toString());
            if(objThuocChitiet.MA_PTTT==null)
                txtMA_PTTT.setText("");
            else
                txtMA_PTTT.setText(""+objThuocChitiet.MA_PTTT.toString());
            if(objThuocChitiet.TYLE_TT==null)
                txtTYLE_TT.setText("");
            else
                txtTYLE_TT.setText(""+objThuocChitiet.TYLE_TT.toString());
            if(objThuocChitiet.CT_ID==null)
                txtCT_ID.setText("");
            else
                txtCT_ID.setText(""+objThuocChitiet.CT_ID.toString());
            if(objThuocChitiet.MUC_HUONG==null)
                txtMUC_HUONG.setText("");
            else
                txtMUC_HUONG.setText(""+objThuocChitiet.MUC_HUONG.toString());
            if(objThuocChitiet.T_NGUON_KHAC==null)
                txtT_NGUON_KHAC.setText("");
            else
                txtT_NGUON_KHAC.setText(""+objThuocChitiet.T_NGUON_KHAC.toString());
            if(objThuocChitiet.T_BNCCT==null)
                txtT_BNCCT.setText("");
            else
                txtT_BNCCT.setText(""+objThuocChitiet.T_BNCCT.toString());
            if(objThuocChitiet.T_NGOAIDS==null)
                txtT_NGOAIDS.setText("");
            else
                txtT_NGOAIDS.setText(""+objThuocChitiet.T_NGOAIDS.toString());
            if(objThuocChitiet.NT_ID==null)
                txtNT_ID.setText("");
            else
                txtNT_ID.setText(""+objThuocChitiet.NT_ID.toString());
            if(objThuocChitiet.TT_BHTT==null)
                txtTT_BHTT.setText("");
            else
                txtTT_BHTT.setText(""+objThuocChitiet.TT_BHTT.toString());
            if(objThuocChitiet.TT_BNTT==null)
                txtTT_BNTT.setText("");
            else
                txtTT_BNTT.setText(""+objThuocChitiet.TT_BNTT.toString());
            if(objThuocChitiet.KHO_ID==null)
                txtKHO_ID.setText("");
            else
                txtKHO_ID.setText(""+objThuocChitiet.KHO_ID.toString());
            if(objThuocChitiet.KHO_NAME==null)
                txtKHO_NAME.setText("");
            else
                txtKHO_NAME.setText(""+objThuocChitiet.KHO_NAME.toString());
            if(objThuocChitiet.CUR_DATE==null)
                txtCUR_DATE.setText("");
            else
                txtCUR_DATE.setText(""+objThuocChitiet.CUR_DATE.toString());
            if(objThuocChitiet.TYP==null)
                txtTYP.setText("");
            else
                txtTYP.setText(""+objThuocChitiet.TYP.toString());
            if(objThuocChitiet.THANHTOAN==null)
                txtTHANHTOAN.setText("");
            else
                txtTHANHTOAN.setText(""+objThuocChitiet.THANHTOAN.toString());
            if(objThuocChitiet.NV_ID==null)
                txtNV_ID.setText("");
            else
                txtNV_ID.setText(""+objThuocChitiet.NV_ID.toString());
            if(objThuocChitiet.NV_NAME==null)
                txtNV_NAME.setText("");
            else
                txtNV_NAME.setText(""+objThuocChitiet.NV_NAME.toString());
            if(objThuocChitiet.STS==null)
                txtSTS.setText("");
            else
                txtSTS.setText(""+objThuocChitiet.STS.toString());
        }
    }
    
    protected void keyPressThuocChitietDlg(KeyEvent e) {
		if(e.keyCode==13){
			saveThuocChitietDlg();
		}
		
	}
}
