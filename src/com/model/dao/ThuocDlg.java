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

public class ThuocDlg extends Dialog {
	static Logger logger = LogManager.getLogger(ThuocDlg.class.getName());

	protected Object result;
	protected Shell shell;
    private Text txtMA_HOAT_CHAT;
    private Text txtMA_AX;
    private Text txtHOAT_CHAT;
    private Text txtHOATCHAT_AX;
    private Text txtMA_DUONG_DUNG;
    private Text txtMA_DUONGDUNG_AX;
    private Text txtDUONG_DUNG;
    private Text txtDUONGDUNG_AX;
    private Text txtHAM_LUONG;
    private Text txtHAMLUONG_AX;
    private Text txtTEN_THUOC;
    private Text txtTENTHUOC_AX;
    private Text txtSO_DANG_KY;
    private Text txtSODANGKY_AX;
    private Text txtDONG_GOI;
    private Text txtDON_VI_TINH;
    private Text txtDON_GIA;
    private Text txtDON_GIA_TT;
    private Text txtSO_LUONG;
    private Text txtMA_CSKCB;
    private Text txtHANG_SX;
    private Text txtNUOC_SX;
    private Text txtNHA_THAU;
    private Text txtQUYET_DINH;
    private Text txtCONG_BO;
    private Text txtMA_THUOC_BV;
    private Text txtLOAI_THUOC;
    private Text txtLOAI_THAU;
    private Text txtNHOM_THAU;
    private Text txtMANHOM_9324;
    private Text txtHIEULUC;
    private Text txtKETQUA;
    private Text txtTYP;
    private Text txtTHUOC_RANK;

    public Thuoc objThuoc;
    Button btnNewButtonSaveThuoc;

    public int intTypeDlgThuoc;
	public static final int TYPE_DLG_VIEW = 1;
	public static final int TYPE_DLG_EDIT = 2;

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
		shell.setText("ThuocDlg EDIT/NEW");
		shell.setLayout(new GridLayout(2, false));
        shell.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.keyCode==SWT.ESC){
					objThuoc = null;
				}
			}
		});
        
		Label lbltxtMA_HOAT_CHAT = new Label(shell, SWT.NONE);
        lbltxtMA_HOAT_CHAT.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtMA_HOAT_CHAT.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtMA_HOAT_CHAT.setText("MA_HOAT_CHAT :");
		
		txtMA_HOAT_CHAT = new Text(shell, SWT.BORDER);
        txtMA_HOAT_CHAT.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtMA_HOAT_CHAT.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtMA_HOAT_CHAT.setText("MA_HOAT_CHAT");
        txtMA_HOAT_CHAT.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressThuocDlg(e);
			}
		});
		Label lbltxtMA_AX = new Label(shell, SWT.NONE);
        lbltxtMA_AX.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtMA_AX.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtMA_AX.setText("MA_AX :");
		
		txtMA_AX = new Text(shell, SWT.BORDER);
        txtMA_AX.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtMA_AX.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtMA_AX.setText("MA_AX");
        txtMA_AX.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressThuocDlg(e);
			}
		});
		Label lbltxtHOAT_CHAT = new Label(shell, SWT.NONE);
        lbltxtHOAT_CHAT.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtHOAT_CHAT.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtHOAT_CHAT.setText("HOAT_CHAT :");
		
		txtHOAT_CHAT = new Text(shell, SWT.BORDER);
        txtHOAT_CHAT.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtHOAT_CHAT.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtHOAT_CHAT.setText("HOAT_CHAT");
        txtHOAT_CHAT.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressThuocDlg(e);
			}
		});
		Label lbltxtHOATCHAT_AX = new Label(shell, SWT.NONE);
        lbltxtHOATCHAT_AX.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtHOATCHAT_AX.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtHOATCHAT_AX.setText("HOATCHAT_AX :");
		
		txtHOATCHAT_AX = new Text(shell, SWT.BORDER);
        txtHOATCHAT_AX.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtHOATCHAT_AX.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtHOATCHAT_AX.setText("HOATCHAT_AX");
        txtHOATCHAT_AX.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressThuocDlg(e);
			}
		});
		Label lbltxtMA_DUONG_DUNG = new Label(shell, SWT.NONE);
        lbltxtMA_DUONG_DUNG.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtMA_DUONG_DUNG.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtMA_DUONG_DUNG.setText("MA_DUONG_DUNG :");
		
		txtMA_DUONG_DUNG = new Text(shell, SWT.BORDER);
        txtMA_DUONG_DUNG.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtMA_DUONG_DUNG.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtMA_DUONG_DUNG.setText("MA_DUONG_DUNG");
        txtMA_DUONG_DUNG.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressThuocDlg(e);
			}
		});
		Label lbltxtMA_DUONGDUNG_AX = new Label(shell, SWT.NONE);
        lbltxtMA_DUONGDUNG_AX.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtMA_DUONGDUNG_AX.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtMA_DUONGDUNG_AX.setText("MA_DUONGDUNG_AX :");
		
		txtMA_DUONGDUNG_AX = new Text(shell, SWT.BORDER);
        txtMA_DUONGDUNG_AX.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtMA_DUONGDUNG_AX.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtMA_DUONGDUNG_AX.setText("MA_DUONGDUNG_AX");
        txtMA_DUONGDUNG_AX.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressThuocDlg(e);
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
				keyPressThuocDlg(e);
			}
		});
		Label lbltxtDUONGDUNG_AX = new Label(shell, SWT.NONE);
        lbltxtDUONGDUNG_AX.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtDUONGDUNG_AX.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtDUONGDUNG_AX.setText("DUONGDUNG_AX :");
		
		txtDUONGDUNG_AX = new Text(shell, SWT.BORDER);
        txtDUONGDUNG_AX.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtDUONGDUNG_AX.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtDUONGDUNG_AX.setText("DUONGDUNG_AX");
        txtDUONGDUNG_AX.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressThuocDlg(e);
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
				keyPressThuocDlg(e);
			}
		});
		Label lbltxtHAMLUONG_AX = new Label(shell, SWT.NONE);
        lbltxtHAMLUONG_AX.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtHAMLUONG_AX.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtHAMLUONG_AX.setText("HAMLUONG_AX :");
		
		txtHAMLUONG_AX = new Text(shell, SWT.BORDER);
        txtHAMLUONG_AX.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtHAMLUONG_AX.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtHAMLUONG_AX.setText("HAMLUONG_AX");
        txtHAMLUONG_AX.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressThuocDlg(e);
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
				keyPressThuocDlg(e);
			}
		});
		Label lbltxtTENTHUOC_AX = new Label(shell, SWT.NONE);
        lbltxtTENTHUOC_AX.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtTENTHUOC_AX.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtTENTHUOC_AX.setText("TENTHUOC_AX :");
		
		txtTENTHUOC_AX = new Text(shell, SWT.BORDER);
        txtTENTHUOC_AX.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtTENTHUOC_AX.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtTENTHUOC_AX.setText("TENTHUOC_AX");
        txtTENTHUOC_AX.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressThuocDlg(e);
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
				keyPressThuocDlg(e);
			}
		});
		Label lbltxtSODANGKY_AX = new Label(shell, SWT.NONE);
        lbltxtSODANGKY_AX.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtSODANGKY_AX.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtSODANGKY_AX.setText("SODANGKY_AX :");
		
		txtSODANGKY_AX = new Text(shell, SWT.BORDER);
        txtSODANGKY_AX.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtSODANGKY_AX.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtSODANGKY_AX.setText("SODANGKY_AX");
        txtSODANGKY_AX.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressThuocDlg(e);
			}
		});
		Label lbltxtDONG_GOI = new Label(shell, SWT.NONE);
        lbltxtDONG_GOI.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtDONG_GOI.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtDONG_GOI.setText("DONG_GOI :");
		
		txtDONG_GOI = new Text(shell, SWT.BORDER);
        txtDONG_GOI.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtDONG_GOI.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtDONG_GOI.setText("DONG_GOI");
        txtDONG_GOI.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressThuocDlg(e);
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
				keyPressThuocDlg(e);
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
				keyPressThuocDlg(e);
			}
		});
		Label lbltxtDON_GIA_TT = new Label(shell, SWT.NONE);
        lbltxtDON_GIA_TT.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtDON_GIA_TT.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtDON_GIA_TT.setText("DON_GIA_TT :");
		
		txtDON_GIA_TT = new Text(shell, SWT.BORDER);
        txtDON_GIA_TT.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtDON_GIA_TT.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtDON_GIA_TT.setText("DON_GIA_TT");
        txtDON_GIA_TT.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressThuocDlg(e);
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
				keyPressThuocDlg(e);
			}
		});
		Label lbltxtMA_CSKCB = new Label(shell, SWT.NONE);
        lbltxtMA_CSKCB.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtMA_CSKCB.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtMA_CSKCB.setText("MA_CSKCB :");
		
		txtMA_CSKCB = new Text(shell, SWT.BORDER);
        txtMA_CSKCB.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtMA_CSKCB.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtMA_CSKCB.setText("MA_CSKCB");
        txtMA_CSKCB.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressThuocDlg(e);
			}
		});
		Label lbltxtHANG_SX = new Label(shell, SWT.NONE);
        lbltxtHANG_SX.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtHANG_SX.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtHANG_SX.setText("HANG_SX :");
		
		txtHANG_SX = new Text(shell, SWT.BORDER);
        txtHANG_SX.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtHANG_SX.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtHANG_SX.setText("HANG_SX");
        txtHANG_SX.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressThuocDlg(e);
			}
		});
		Label lbltxtNUOC_SX = new Label(shell, SWT.NONE);
        lbltxtNUOC_SX.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtNUOC_SX.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtNUOC_SX.setText("NUOC_SX :");
		
		txtNUOC_SX = new Text(shell, SWT.BORDER);
        txtNUOC_SX.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtNUOC_SX.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtNUOC_SX.setText("NUOC_SX");
        txtNUOC_SX.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressThuocDlg(e);
			}
		});
		Label lbltxtNHA_THAU = new Label(shell, SWT.NONE);
        lbltxtNHA_THAU.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtNHA_THAU.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtNHA_THAU.setText("NHA_THAU :");
		
		txtNHA_THAU = new Text(shell, SWT.BORDER);
        txtNHA_THAU.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtNHA_THAU.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtNHA_THAU.setText("NHA_THAU");
        txtNHA_THAU.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressThuocDlg(e);
			}
		});
		Label lbltxtQUYET_DINH = new Label(shell, SWT.NONE);
        lbltxtQUYET_DINH.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtQUYET_DINH.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtQUYET_DINH.setText("QUYET_DINH :");
		
		txtQUYET_DINH = new Text(shell, SWT.BORDER);
        txtQUYET_DINH.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtQUYET_DINH.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtQUYET_DINH.setText("QUYET_DINH");
        txtQUYET_DINH.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressThuocDlg(e);
			}
		});
		Label lbltxtCONG_BO = new Label(shell, SWT.NONE);
        lbltxtCONG_BO.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtCONG_BO.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtCONG_BO.setText("CONG_BO :");
		
		txtCONG_BO = new Text(shell, SWT.BORDER);
        txtCONG_BO.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtCONG_BO.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtCONG_BO.setText("CONG_BO");
        txtCONG_BO.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressThuocDlg(e);
			}
		});
		Label lbltxtMA_THUOC_BV = new Label(shell, SWT.NONE);
        lbltxtMA_THUOC_BV.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtMA_THUOC_BV.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtMA_THUOC_BV.setText("MA_THUOC_BV :");
		
		txtMA_THUOC_BV = new Text(shell, SWT.BORDER);
        txtMA_THUOC_BV.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtMA_THUOC_BV.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtMA_THUOC_BV.setText("MA_THUOC_BV");
        txtMA_THUOC_BV.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressThuocDlg(e);
			}
		});
		Label lbltxtLOAI_THUOC = new Label(shell, SWT.NONE);
        lbltxtLOAI_THUOC.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtLOAI_THUOC.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtLOAI_THUOC.setText("LOAI_THUOC :");
		
		txtLOAI_THUOC = new Text(shell, SWT.BORDER);
        txtLOAI_THUOC.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtLOAI_THUOC.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtLOAI_THUOC.setText("LOAI_THUOC");
        txtLOAI_THUOC.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressThuocDlg(e);
			}
		});
		Label lbltxtLOAI_THAU = new Label(shell, SWT.NONE);
        lbltxtLOAI_THAU.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtLOAI_THAU.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtLOAI_THAU.setText("LOAI_THAU :");
		
		txtLOAI_THAU = new Text(shell, SWT.BORDER);
        txtLOAI_THAU.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtLOAI_THAU.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtLOAI_THAU.setText("LOAI_THAU");
        txtLOAI_THAU.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressThuocDlg(e);
			}
		});
		Label lbltxtNHOM_THAU = new Label(shell, SWT.NONE);
        lbltxtNHOM_THAU.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtNHOM_THAU.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtNHOM_THAU.setText("NHOM_THAU :");
		
		txtNHOM_THAU = new Text(shell, SWT.BORDER);
        txtNHOM_THAU.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtNHOM_THAU.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtNHOM_THAU.setText("NHOM_THAU");
        txtNHOM_THAU.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressThuocDlg(e);
			}
		});
		Label lbltxtMANHOM_9324 = new Label(shell, SWT.NONE);
        lbltxtMANHOM_9324.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtMANHOM_9324.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtMANHOM_9324.setText("MANHOM_9324 :");
		
		txtMANHOM_9324 = new Text(shell, SWT.BORDER);
        txtMANHOM_9324.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtMANHOM_9324.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtMANHOM_9324.setText("MANHOM_9324");
        txtMANHOM_9324.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressThuocDlg(e);
			}
		});
		Label lbltxtHIEULUC = new Label(shell, SWT.NONE);
        lbltxtHIEULUC.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtHIEULUC.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtHIEULUC.setText("HIEULUC :");
		
		txtHIEULUC = new Text(shell, SWT.BORDER);
        txtHIEULUC.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtHIEULUC.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtHIEULUC.setText("HIEULUC");
        txtHIEULUC.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressThuocDlg(e);
			}
		});
		Label lbltxtKETQUA = new Label(shell, SWT.NONE);
        lbltxtKETQUA.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtKETQUA.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtKETQUA.setText("KETQUA :");
		
		txtKETQUA = new Text(shell, SWT.BORDER);
        txtKETQUA.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtKETQUA.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtKETQUA.setText("KETQUA");
        txtKETQUA.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressThuocDlg(e);
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
				keyPressThuocDlg(e);
			}
		});
		Label lbltxtTHUOC_RANK = new Label(shell, SWT.NONE);
        lbltxtTHUOC_RANK.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtTHUOC_RANK.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtTHUOC_RANK.setText("THUOC_RANK :");
		
		txtTHUOC_RANK = new Text(shell, SWT.BORDER);
        txtTHUOC_RANK.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtTHUOC_RANK.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtTHUOC_RANK.setText("THUOC_RANK");
        txtTHUOC_RANK.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressThuocDlg(e);
			}
		});
		new Label(shell, SWT.NONE);
		btnNewButtonSaveThuoc = new Button(shell, SWT.NONE);
		btnNewButtonSaveThuoc.setImage(SWTResourceManager.getImage(ThuocDlg.class, "/png/file-2x.png"));
        btnNewButtonSaveThuoc.setFont(SWTResourceManager.getFont("Tahoma", 12, SWT.BOLD));
		btnNewButtonSaveThuoc.setText("Save");
        
        btnNewButtonSaveThuoc.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
                saveThuocDlg();
			}
		});
        
        loadThuocDlgData();
	}

    private void saveThuocDlg(){
        if(intTypeDlgThuoc==TYPE_DLG_VIEW){
            return;
        }
        
        if(objThuoc == null){
            objThuoc = new Thuoc();
        }
        if(objThuoc!=null){
        // String     = false
            objThuoc.MA_HOAT_CHAT = txtMA_HOAT_CHAT.getText();
            // String     = false
            objThuoc.MA_AX = txtMA_AX.getText();
            // String     = false
            objThuoc.HOAT_CHAT = txtHOAT_CHAT.getText();
            // String     = false
            objThuoc.HOATCHAT_AX = txtHOATCHAT_AX.getText();
            // String     = false
            objThuoc.MA_DUONG_DUNG = txtMA_DUONG_DUNG.getText();
            // String     = false
            objThuoc.MA_DUONGDUNG_AX = txtMA_DUONGDUNG_AX.getText();
            // String     = false
            objThuoc.DUONG_DUNG = txtDUONG_DUNG.getText();
            // String     = false
            objThuoc.DUONGDUNG_AX = txtDUONGDUNG_AX.getText();
            // String     = false
            objThuoc.HAM_LUONG = txtHAM_LUONG.getText();
            // String     = false
            objThuoc.HAMLUONG_AX = txtHAMLUONG_AX.getText();
            // String     = false
            objThuoc.TEN_THUOC = txtTEN_THUOC.getText();
            // String     = false
            objThuoc.TENTHUOC_AX = txtTENTHUOC_AX.getText();
            // String     = false
            objThuoc.SO_DANG_KY = txtSO_DANG_KY.getText();
            // String     = false
            objThuoc.SODANGKY_AX = txtSODANGKY_AX.getText();
            // String     = false
            objThuoc.DONG_GOI = txtDONG_GOI.getText();
            // String     = false
            objThuoc.DON_VI_TINH = txtDON_VI_TINH.getText();
            // Double     = false
            // objThuoc.DON_GIA = txtDON_GIA.getText();
            // Double     = false
            // objThuoc.DON_GIA_TT = txtDON_GIA_TT.getText();
            // Integer    = true
            objThuoc.SO_LUONG = Utils.getInt( txtSO_LUONG.getText() );
            // String     = false
            objThuoc.MA_CSKCB = txtMA_CSKCB.getText();
            // String     = false
            objThuoc.HANG_SX = txtHANG_SX.getText();
            // String     = false
            objThuoc.NUOC_SX = txtNUOC_SX.getText();
            // String     = false
            objThuoc.NHA_THAU = txtNHA_THAU.getText();
            // String     = false
            objThuoc.QUYET_DINH = txtQUYET_DINH.getText();
            // String     = false
            objThuoc.CONG_BO = txtCONG_BO.getText();
            // String     = false
            objThuoc.MA_THUOC_BV = txtMA_THUOC_BV.getText();
            // Integer    = true
            objThuoc.LOAI_THUOC = Utils.getInt( txtLOAI_THUOC.getText() );
            // Integer    = true
            objThuoc.LOAI_THAU = Utils.getInt( txtLOAI_THAU.getText() );
            // String     = false
            objThuoc.NHOM_THAU = txtNHOM_THAU.getText();
            // Integer    = true
            objThuoc.MANHOM_9324 = Utils.getInt( txtMANHOM_9324.getText() );
            // Integer    = true
            objThuoc.HIEULUC = Utils.getInt( txtHIEULUC.getText() );
            // Integer    = true
            objThuoc.KETQUA = Utils.getInt( txtKETQUA.getText() );
            // Integer    = true
            objThuoc.TYP = Utils.getInt( txtTYP.getText() );
            // Integer    = true
            objThuoc.THUOC_RANK = Utils.getInt( txtTHUOC_RANK.getText() );
            }
        if(DbHelper.checkPhanQuyen(DbHelper.UPDATE, "thuoc")==false){
            logger.info("DON'T HAVE UPDATE RIGHT");
        }
        else if(DbHelper.checkPhanQuyen(DbHelper.INSERT, "thuoc")==false){
            logger.info("DON'T HAVE INSERT RIGHT");
        }        
        else{
            objThuoc.insert();
        }
        shell.close();
    }
    
    public void setThuocDlgData(Thuoc obj) {
		this.objThuoc = obj;
	}
    
    public void loadThuocDlgData(){
        if(intTypeDlgThuoc==TYPE_DLG_VIEW){
            btnNewButtonSaveThuoc.setEnabled(false);
        }
        else{
            btnNewButtonSaveThuoc.setEnabled(true);
        }

        if(DbHelper.checkPhanQuyen(DbHelper.READ, "thuoc")==false){
            logger.info("DON'T HAVE READ RIGHT");
            return;
        }
        if(objThuoc != null){
            if(objThuoc.MA_HOAT_CHAT==null)
                txtMA_HOAT_CHAT.setText("");
            else
                txtMA_HOAT_CHAT.setText(""+objThuoc.MA_HOAT_CHAT.toString());
            if(objThuoc.MA_AX==null)
                txtMA_AX.setText("");
            else
                txtMA_AX.setText(""+objThuoc.MA_AX.toString());
            if(objThuoc.HOAT_CHAT==null)
                txtHOAT_CHAT.setText("");
            else
                txtHOAT_CHAT.setText(""+objThuoc.HOAT_CHAT.toString());
            if(objThuoc.HOATCHAT_AX==null)
                txtHOATCHAT_AX.setText("");
            else
                txtHOATCHAT_AX.setText(""+objThuoc.HOATCHAT_AX.toString());
            if(objThuoc.MA_DUONG_DUNG==null)
                txtMA_DUONG_DUNG.setText("");
            else
                txtMA_DUONG_DUNG.setText(""+objThuoc.MA_DUONG_DUNG.toString());
            if(objThuoc.MA_DUONGDUNG_AX==null)
                txtMA_DUONGDUNG_AX.setText("");
            else
                txtMA_DUONGDUNG_AX.setText(""+objThuoc.MA_DUONGDUNG_AX.toString());
            if(objThuoc.DUONG_DUNG==null)
                txtDUONG_DUNG.setText("");
            else
                txtDUONG_DUNG.setText(""+objThuoc.DUONG_DUNG.toString());
            if(objThuoc.DUONGDUNG_AX==null)
                txtDUONGDUNG_AX.setText("");
            else
                txtDUONGDUNG_AX.setText(""+objThuoc.DUONGDUNG_AX.toString());
            if(objThuoc.HAM_LUONG==null)
                txtHAM_LUONG.setText("");
            else
                txtHAM_LUONG.setText(""+objThuoc.HAM_LUONG.toString());
            if(objThuoc.HAMLUONG_AX==null)
                txtHAMLUONG_AX.setText("");
            else
                txtHAMLUONG_AX.setText(""+objThuoc.HAMLUONG_AX.toString());
            if(objThuoc.TEN_THUOC==null)
                txtTEN_THUOC.setText("");
            else
                txtTEN_THUOC.setText(""+objThuoc.TEN_THUOC.toString());
            if(objThuoc.TENTHUOC_AX==null)
                txtTENTHUOC_AX.setText("");
            else
                txtTENTHUOC_AX.setText(""+objThuoc.TENTHUOC_AX.toString());
            if(objThuoc.SO_DANG_KY==null)
                txtSO_DANG_KY.setText("");
            else
                txtSO_DANG_KY.setText(""+objThuoc.SO_DANG_KY.toString());
            if(objThuoc.SODANGKY_AX==null)
                txtSODANGKY_AX.setText("");
            else
                txtSODANGKY_AX.setText(""+objThuoc.SODANGKY_AX.toString());
            if(objThuoc.DONG_GOI==null)
                txtDONG_GOI.setText("");
            else
                txtDONG_GOI.setText(""+objThuoc.DONG_GOI.toString());
            if(objThuoc.DON_VI_TINH==null)
                txtDON_VI_TINH.setText("");
            else
                txtDON_VI_TINH.setText(""+objThuoc.DON_VI_TINH.toString());
            if(objThuoc.DON_GIA==null)
                txtDON_GIA.setText("");
            else
                txtDON_GIA.setText(""+objThuoc.DON_GIA.toString());
            if(objThuoc.DON_GIA_TT==null)
                txtDON_GIA_TT.setText("");
            else
                txtDON_GIA_TT.setText(""+objThuoc.DON_GIA_TT.toString());
            if(objThuoc.SO_LUONG==null)
                txtSO_LUONG.setText("");
            else
                txtSO_LUONG.setText(""+objThuoc.SO_LUONG.toString());
            if(objThuoc.MA_CSKCB==null)
                txtMA_CSKCB.setText("");
            else
                txtMA_CSKCB.setText(""+objThuoc.MA_CSKCB.toString());
            if(objThuoc.HANG_SX==null)
                txtHANG_SX.setText("");
            else
                txtHANG_SX.setText(""+objThuoc.HANG_SX.toString());
            if(objThuoc.NUOC_SX==null)
                txtNUOC_SX.setText("");
            else
                txtNUOC_SX.setText(""+objThuoc.NUOC_SX.toString());
            if(objThuoc.NHA_THAU==null)
                txtNHA_THAU.setText("");
            else
                txtNHA_THAU.setText(""+objThuoc.NHA_THAU.toString());
            if(objThuoc.QUYET_DINH==null)
                txtQUYET_DINH.setText("");
            else
                txtQUYET_DINH.setText(""+objThuoc.QUYET_DINH.toString());
            if(objThuoc.CONG_BO==null)
                txtCONG_BO.setText("");
            else
                txtCONG_BO.setText(""+objThuoc.CONG_BO.toString());
            if(objThuoc.MA_THUOC_BV==null)
                txtMA_THUOC_BV.setText("");
            else
                txtMA_THUOC_BV.setText(""+objThuoc.MA_THUOC_BV.toString());
            if(objThuoc.LOAI_THUOC==null)
                txtLOAI_THUOC.setText("");
            else
                txtLOAI_THUOC.setText(""+objThuoc.LOAI_THUOC.toString());
            if(objThuoc.LOAI_THAU==null)
                txtLOAI_THAU.setText("");
            else
                txtLOAI_THAU.setText(""+objThuoc.LOAI_THAU.toString());
            if(objThuoc.NHOM_THAU==null)
                txtNHOM_THAU.setText("");
            else
                txtNHOM_THAU.setText(""+objThuoc.NHOM_THAU.toString());
            if(objThuoc.MANHOM_9324==null)
                txtMANHOM_9324.setText("");
            else
                txtMANHOM_9324.setText(""+objThuoc.MANHOM_9324.toString());
            if(objThuoc.HIEULUC==null)
                txtHIEULUC.setText("");
            else
                txtHIEULUC.setText(""+objThuoc.HIEULUC.toString());
            if(objThuoc.KETQUA==null)
                txtKETQUA.setText("");
            else
                txtKETQUA.setText(""+objThuoc.KETQUA.toString());
            if(objThuoc.TYP==null)
                txtTYP.setText("");
            else
                txtTYP.setText(""+objThuoc.TYP.toString());
            if(objThuoc.THUOC_RANK==null)
                txtTHUOC_RANK.setText("");
            else
                txtTHUOC_RANK.setText(""+objThuoc.THUOC_RANK.toString());
        }
    }
    
    protected void keyPressThuocDlg(KeyEvent e) {
		if(e.keyCode==13){
			saveThuocDlg();
		}
		
	}
}
