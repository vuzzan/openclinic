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
    private Text txtSO_LUONG;
    private Text txtDON_GIA;
    private Text txtTHANH_TIEN;
    private Text txtMA_KHOA;
    private Text txtMA_BAC_SI;
    private Text txtMA_BENH;
    private Text txtMA_PTTT;
    private Text txtTYLE_TT;
    private Text txtCT_ID;
    private Text txtNT_ID;
    private Text txtTT_BH;
    private Text txtTT_NB;
    private Text txtKHO_NAME;
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
		Label lbltxtTT_BH = new Label(shell, SWT.NONE);
        lbltxtTT_BH.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtTT_BH.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtTT_BH.setText("TT_BH :");
		
		txtTT_BH = new Text(shell, SWT.BORDER);
        txtTT_BH.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtTT_BH.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtTT_BH.setText("TT_BH");
        txtTT_BH.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressThuocChitietDlg(e);
			}
		});
		Label lbltxtTT_NB = new Label(shell, SWT.NONE);
        lbltxtTT_NB.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtTT_NB.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtTT_NB.setText("TT_NB :");
		
		txtTT_NB = new Text(shell, SWT.BORDER);
        txtTT_NB.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtTT_NB.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtTT_NB.setText("TT_NB");
        txtTT_NB.addKeyListener(new KeyAdapter() {
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
            // Integer    = true
            objThuocChitiet.SO_LUONG = Utils.getInt( txtSO_LUONG.getText() );
            // Integer    = true
            objThuocChitiet.DON_GIA = Utils.getInt( txtDON_GIA.getText() );
            // Integer    = true
            objThuocChitiet.THANH_TIEN = Utils.getInt( txtTHANH_TIEN.getText() );
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
            objThuocChitiet.NT_ID = Utils.getInt( txtNT_ID.getText() );
            // Integer    = true
            objThuocChitiet.TT_BH = Utils.getInt( txtTT_BH.getText() );
            // Integer    = true
            objThuocChitiet.TT_NB = Utils.getInt( txtTT_NB.getText() );
            // String     = false
            objThuocChitiet.KHO_NAME = txtKHO_NAME.getText();
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
            if(objThuocChitiet.NT_ID==null)
                txtNT_ID.setText("");
            else
                txtNT_ID.setText(""+objThuocChitiet.NT_ID.toString());
            if(objThuocChitiet.TT_BH==null)
                txtTT_BH.setText("");
            else
                txtTT_BH.setText(""+objThuocChitiet.TT_BH.toString());
            if(objThuocChitiet.TT_NB==null)
                txtTT_NB.setText("");
            else
                txtTT_NB.setText(""+objThuocChitiet.TT_NB.toString());
            if(objThuocChitiet.KHO_NAME==null)
                txtKHO_NAME.setText("");
            else
                txtKHO_NAME.setText(""+objThuocChitiet.KHO_NAME.toString());
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
