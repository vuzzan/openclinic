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

public class KhamBenhDlg extends Dialog {
	static Logger logger = LogManager.getLogger(KhamBenhDlg.class.getName());

	protected Object result;
	protected Shell shell;
    private Text txtSTT;
    private Text txtBN_ID;
    private Text txtTEN_BENH_NHAN;
    private Text txtTEN_BENH;
    private Text txtMA_BENH;
    private Text txtMA_BENHKHAC;
    private Text txtMA_LYDO_VVIEN;
    private Text txtMA_NOI_CHUYEN;
    private Text txtMA_TAI_NAN;
    private Text txtNGAY_VAO;
    private Text txtNGAY_RA;
    private Text txtSO_NGAY_DTRI;
    private Text txtKET_QUA_DTRI;
    private Text txtTINH_TRANG_RV;
    private Text txtNGAY_TTOAN;
    private Text txtMUC_HUONG;
    private Text txtT_THUOC;
    private Text txtT_VTYT;
    private Text txtT_TONGCHI;
    private Text txtT_BNTT;
    private Text txtT_BNCCT;
    private Text txtT_BHTT;
    private Text txtT_NGUONKHAC;
    private Text txtT_NGOAIDS;
    private Text txtNAM_QT;
    private Text txtTHANG_QT;
    private Text txtMA_LOAI_KCB;
    private Text txtMA_KHOA;
    private Text txtMA_CSKCB;
    private Text txtMA_KHUVUC;
    private Text txtMA_PTTT_QT;
    private Text txtCAN_NANG;
    private Text txtKB_DATE;
    private Text txtKIEU_TT;
    private Text txtCHANDOAN_BD;
    private Text txtNV_ID;
    private Text txtNV_NAME;
    private Text txtTABLE_ID;
    private Text txtNGAY_SINH;
    private Text txtGIOI_TINH;
    private Text txtDIA_CHI;
    private Text txtMA_THE;
    private Text txtMA_DKBD;
    private Text txtGT_THE_TU;
    private Text txtGT_THE_DEN;
    private Text txtNGAY_CAP;
    private Text txtMIEN_CUNG_CT;
    private Text txtMA_QUAN_LY;
    private Text txtTEN_CHA_ME;
    private Text txtMA_DT_SONG;
    private Text txtTHOIDIEM_NAMNAM;
    private Text txtCHUOI_KIEM_TRA;
    private Text txtGATE_INFO;
    private Text txtSTS;

    public KhamBenh objKhamBenh;
    Button btnNewButtonSaveKhamBenh;

    public int intTypeDlgKhamBenh;
	public static final int TYPE_DLG_VIEW = 1;
	public static final int TYPE_DLG_EDIT = 2;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public KhamBenhDlg(Shell parent, int style) {
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
		shell.setText("KhamBenhDlg EDIT/NEW");
		shell.setLayout(new GridLayout(2, false));
        shell.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.keyCode==SWT.ESC){
					objKhamBenh = null;
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
				keyPressKhamBenhDlg(e);
			}
		});
		Label lbltxtBN_ID = new Label(shell, SWT.NONE);
        lbltxtBN_ID.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtBN_ID.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtBN_ID.setText("BN_ID :");
		
		txtBN_ID = new Text(shell, SWT.BORDER);
        txtBN_ID.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtBN_ID.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtBN_ID.setText("BN_ID");
        txtBN_ID.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressKhamBenhDlg(e);
			}
		});
		Label lbltxtTEN_BENH_NHAN = new Label(shell, SWT.NONE);
        lbltxtTEN_BENH_NHAN.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtTEN_BENH_NHAN.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtTEN_BENH_NHAN.setText("TEN_BENH_NHAN :");
		
		txtTEN_BENH_NHAN = new Text(shell, SWT.BORDER);
        txtTEN_BENH_NHAN.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtTEN_BENH_NHAN.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtTEN_BENH_NHAN.setText("TEN_BENH_NHAN");
        txtTEN_BENH_NHAN.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressKhamBenhDlg(e);
			}
		});
		Label lbltxtTEN_BENH = new Label(shell, SWT.NONE);
        lbltxtTEN_BENH.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtTEN_BENH.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtTEN_BENH.setText("TEN_BENH :");
		
		txtTEN_BENH = new Text(shell, SWT.BORDER);
        txtTEN_BENH.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtTEN_BENH.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtTEN_BENH.setText("TEN_BENH");
        txtTEN_BENH.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressKhamBenhDlg(e);
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
				keyPressKhamBenhDlg(e);
			}
		});
		Label lbltxtMA_BENHKHAC = new Label(shell, SWT.NONE);
        lbltxtMA_BENHKHAC.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtMA_BENHKHAC.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtMA_BENHKHAC.setText("MA_BENHKHAC :");
		
		txtMA_BENHKHAC = new Text(shell, SWT.BORDER);
        txtMA_BENHKHAC.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtMA_BENHKHAC.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtMA_BENHKHAC.setText("MA_BENHKHAC");
        txtMA_BENHKHAC.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressKhamBenhDlg(e);
			}
		});
		Label lbltxtMA_LYDO_VVIEN = new Label(shell, SWT.NONE);
        lbltxtMA_LYDO_VVIEN.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtMA_LYDO_VVIEN.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtMA_LYDO_VVIEN.setText("MA_LYDO_VVIEN :");
		
		txtMA_LYDO_VVIEN = new Text(shell, SWT.BORDER);
        txtMA_LYDO_VVIEN.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtMA_LYDO_VVIEN.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtMA_LYDO_VVIEN.setText("MA_LYDO_VVIEN");
        txtMA_LYDO_VVIEN.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressKhamBenhDlg(e);
			}
		});
		Label lbltxtMA_NOI_CHUYEN = new Label(shell, SWT.NONE);
        lbltxtMA_NOI_CHUYEN.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtMA_NOI_CHUYEN.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtMA_NOI_CHUYEN.setText("MA_NOI_CHUYEN :");
		
		txtMA_NOI_CHUYEN = new Text(shell, SWT.BORDER);
        txtMA_NOI_CHUYEN.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtMA_NOI_CHUYEN.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtMA_NOI_CHUYEN.setText("MA_NOI_CHUYEN");
        txtMA_NOI_CHUYEN.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressKhamBenhDlg(e);
			}
		});
		Label lbltxtMA_TAI_NAN = new Label(shell, SWT.NONE);
        lbltxtMA_TAI_NAN.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtMA_TAI_NAN.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtMA_TAI_NAN.setText("MA_TAI_NAN :");
		
		txtMA_TAI_NAN = new Text(shell, SWT.BORDER);
        txtMA_TAI_NAN.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtMA_TAI_NAN.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtMA_TAI_NAN.setText("MA_TAI_NAN");
        txtMA_TAI_NAN.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressKhamBenhDlg(e);
			}
		});
		Label lbltxtNGAY_VAO = new Label(shell, SWT.NONE);
        lbltxtNGAY_VAO.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtNGAY_VAO.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtNGAY_VAO.setText("NGAY_VAO :");
		
		txtNGAY_VAO = new Text(shell, SWT.BORDER);
        txtNGAY_VAO.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtNGAY_VAO.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtNGAY_VAO.setText("NGAY_VAO");
        txtNGAY_VAO.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressKhamBenhDlg(e);
			}
		});
		Label lbltxtNGAY_RA = new Label(shell, SWT.NONE);
        lbltxtNGAY_RA.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtNGAY_RA.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtNGAY_RA.setText("NGAY_RA :");
		
		txtNGAY_RA = new Text(shell, SWT.BORDER);
        txtNGAY_RA.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtNGAY_RA.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtNGAY_RA.setText("NGAY_RA");
        txtNGAY_RA.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressKhamBenhDlg(e);
			}
		});
		Label lbltxtSO_NGAY_DTRI = new Label(shell, SWT.NONE);
        lbltxtSO_NGAY_DTRI.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtSO_NGAY_DTRI.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtSO_NGAY_DTRI.setText("SO_NGAY_DTRI :");
		
		txtSO_NGAY_DTRI = new Text(shell, SWT.BORDER);
        txtSO_NGAY_DTRI.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtSO_NGAY_DTRI.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtSO_NGAY_DTRI.setText("SO_NGAY_DTRI");
        txtSO_NGAY_DTRI.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressKhamBenhDlg(e);
			}
		});
		Label lbltxtKET_QUA_DTRI = new Label(shell, SWT.NONE);
        lbltxtKET_QUA_DTRI.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtKET_QUA_DTRI.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtKET_QUA_DTRI.setText("KET_QUA_DTRI :");
		
		txtKET_QUA_DTRI = new Text(shell, SWT.BORDER);
        txtKET_QUA_DTRI.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtKET_QUA_DTRI.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtKET_QUA_DTRI.setText("KET_QUA_DTRI");
        txtKET_QUA_DTRI.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressKhamBenhDlg(e);
			}
		});
		Label lbltxtTINH_TRANG_RV = new Label(shell, SWT.NONE);
        lbltxtTINH_TRANG_RV.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtTINH_TRANG_RV.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtTINH_TRANG_RV.setText("TINH_TRANG_RV :");
		
		txtTINH_TRANG_RV = new Text(shell, SWT.BORDER);
        txtTINH_TRANG_RV.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtTINH_TRANG_RV.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtTINH_TRANG_RV.setText("TINH_TRANG_RV");
        txtTINH_TRANG_RV.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressKhamBenhDlg(e);
			}
		});
		Label lbltxtNGAY_TTOAN = new Label(shell, SWT.NONE);
        lbltxtNGAY_TTOAN.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtNGAY_TTOAN.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtNGAY_TTOAN.setText("NGAY_TTOAN :");
		
		txtNGAY_TTOAN = new Text(shell, SWT.BORDER);
        txtNGAY_TTOAN.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtNGAY_TTOAN.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtNGAY_TTOAN.setText("NGAY_TTOAN");
        txtNGAY_TTOAN.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressKhamBenhDlg(e);
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
				keyPressKhamBenhDlg(e);
			}
		});
		Label lbltxtT_THUOC = new Label(shell, SWT.NONE);
        lbltxtT_THUOC.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtT_THUOC.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtT_THUOC.setText("T_THUOC :");
		
		txtT_THUOC = new Text(shell, SWT.BORDER);
        txtT_THUOC.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtT_THUOC.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtT_THUOC.setText("T_THUOC");
        txtT_THUOC.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressKhamBenhDlg(e);
			}
		});
		Label lbltxtT_VTYT = new Label(shell, SWT.NONE);
        lbltxtT_VTYT.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtT_VTYT.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtT_VTYT.setText("T_VTYT :");
		
		txtT_VTYT = new Text(shell, SWT.BORDER);
        txtT_VTYT.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtT_VTYT.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtT_VTYT.setText("T_VTYT");
        txtT_VTYT.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressKhamBenhDlg(e);
			}
		});
		Label lbltxtT_TONGCHI = new Label(shell, SWT.NONE);
        lbltxtT_TONGCHI.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtT_TONGCHI.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtT_TONGCHI.setText("T_TONGCHI :");
		
		txtT_TONGCHI = new Text(shell, SWT.BORDER);
        txtT_TONGCHI.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtT_TONGCHI.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtT_TONGCHI.setText("T_TONGCHI");
        txtT_TONGCHI.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressKhamBenhDlg(e);
			}
		});
		Label lbltxtT_BNTT = new Label(shell, SWT.NONE);
        lbltxtT_BNTT.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtT_BNTT.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtT_BNTT.setText("T_BNTT :");
		
		txtT_BNTT = new Text(shell, SWT.BORDER);
        txtT_BNTT.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtT_BNTT.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtT_BNTT.setText("T_BNTT");
        txtT_BNTT.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressKhamBenhDlg(e);
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
				keyPressKhamBenhDlg(e);
			}
		});
		Label lbltxtT_BHTT = new Label(shell, SWT.NONE);
        lbltxtT_BHTT.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtT_BHTT.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtT_BHTT.setText("T_BHTT :");
		
		txtT_BHTT = new Text(shell, SWT.BORDER);
        txtT_BHTT.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtT_BHTT.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtT_BHTT.setText("T_BHTT");
        txtT_BHTT.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressKhamBenhDlg(e);
			}
		});
		Label lbltxtT_NGUONKHAC = new Label(shell, SWT.NONE);
        lbltxtT_NGUONKHAC.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtT_NGUONKHAC.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtT_NGUONKHAC.setText("T_NGUONKHAC :");
		
		txtT_NGUONKHAC = new Text(shell, SWT.BORDER);
        txtT_NGUONKHAC.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtT_NGUONKHAC.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtT_NGUONKHAC.setText("T_NGUONKHAC");
        txtT_NGUONKHAC.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressKhamBenhDlg(e);
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
				keyPressKhamBenhDlg(e);
			}
		});
		Label lbltxtNAM_QT = new Label(shell, SWT.NONE);
        lbltxtNAM_QT.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtNAM_QT.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtNAM_QT.setText("NAM_QT :");
		
		txtNAM_QT = new Text(shell, SWT.BORDER);
        txtNAM_QT.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtNAM_QT.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtNAM_QT.setText("NAM_QT");
        txtNAM_QT.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressKhamBenhDlg(e);
			}
		});
		Label lbltxtTHANG_QT = new Label(shell, SWT.NONE);
        lbltxtTHANG_QT.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtTHANG_QT.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtTHANG_QT.setText("THANG_QT :");
		
		txtTHANG_QT = new Text(shell, SWT.BORDER);
        txtTHANG_QT.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtTHANG_QT.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtTHANG_QT.setText("THANG_QT");
        txtTHANG_QT.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressKhamBenhDlg(e);
			}
		});
		Label lbltxtMA_LOAI_KCB = new Label(shell, SWT.NONE);
        lbltxtMA_LOAI_KCB.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtMA_LOAI_KCB.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtMA_LOAI_KCB.setText("MA_LOAI_KCB :");
		
		txtMA_LOAI_KCB = new Text(shell, SWT.BORDER);
        txtMA_LOAI_KCB.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtMA_LOAI_KCB.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtMA_LOAI_KCB.setText("MA_LOAI_KCB");
        txtMA_LOAI_KCB.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressKhamBenhDlg(e);
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
				keyPressKhamBenhDlg(e);
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
				keyPressKhamBenhDlg(e);
			}
		});
		Label lbltxtMA_KHUVUC = new Label(shell, SWT.NONE);
        lbltxtMA_KHUVUC.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtMA_KHUVUC.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtMA_KHUVUC.setText("MA_KHUVUC :");
		
		txtMA_KHUVUC = new Text(shell, SWT.BORDER);
        txtMA_KHUVUC.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtMA_KHUVUC.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtMA_KHUVUC.setText("MA_KHUVUC");
        txtMA_KHUVUC.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressKhamBenhDlg(e);
			}
		});
		Label lbltxtMA_PTTT_QT = new Label(shell, SWT.NONE);
        lbltxtMA_PTTT_QT.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtMA_PTTT_QT.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtMA_PTTT_QT.setText("MA_PTTT_QT :");
		
		txtMA_PTTT_QT = new Text(shell, SWT.BORDER);
        txtMA_PTTT_QT.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtMA_PTTT_QT.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtMA_PTTT_QT.setText("MA_PTTT_QT");
        txtMA_PTTT_QT.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressKhamBenhDlg(e);
			}
		});
		Label lbltxtCAN_NANG = new Label(shell, SWT.NONE);
        lbltxtCAN_NANG.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtCAN_NANG.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtCAN_NANG.setText("CAN_NANG :");
		
		txtCAN_NANG = new Text(shell, SWT.BORDER);
        txtCAN_NANG.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtCAN_NANG.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtCAN_NANG.setText("CAN_NANG");
        txtCAN_NANG.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressKhamBenhDlg(e);
			}
		});
		Label lbltxtKB_DATE = new Label(shell, SWT.NONE);
        lbltxtKB_DATE.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtKB_DATE.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtKB_DATE.setText("KB_DATE :");
		
		txtKB_DATE = new Text(shell, SWT.BORDER);
        txtKB_DATE.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtKB_DATE.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtKB_DATE.setText("KB_DATE");
        txtKB_DATE.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressKhamBenhDlg(e);
			}
		});
		Label lbltxtKIEU_TT = new Label(shell, SWT.NONE);
        lbltxtKIEU_TT.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtKIEU_TT.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtKIEU_TT.setText("KIEU_TT :");
		
		txtKIEU_TT = new Text(shell, SWT.BORDER);
        txtKIEU_TT.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtKIEU_TT.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtKIEU_TT.setText("KIEU_TT");
        txtKIEU_TT.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressKhamBenhDlg(e);
			}
		});
		Label lbltxtCHANDOAN_BD = new Label(shell, SWT.NONE);
        lbltxtCHANDOAN_BD.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtCHANDOAN_BD.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtCHANDOAN_BD.setText("CHANDOAN_BD :");
		
		txtCHANDOAN_BD = new Text(shell, SWT.BORDER);
        txtCHANDOAN_BD.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtCHANDOAN_BD.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtCHANDOAN_BD.setText("CHANDOAN_BD");
        txtCHANDOAN_BD.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressKhamBenhDlg(e);
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
				keyPressKhamBenhDlg(e);
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
				keyPressKhamBenhDlg(e);
			}
		});
		Label lbltxtTABLE_ID = new Label(shell, SWT.NONE);
        lbltxtTABLE_ID.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtTABLE_ID.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtTABLE_ID.setText("TABLE_ID :");
		
		txtTABLE_ID = new Text(shell, SWT.BORDER);
        txtTABLE_ID.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtTABLE_ID.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtTABLE_ID.setText("TABLE_ID");
        txtTABLE_ID.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressKhamBenhDlg(e);
			}
		});
		Label lbltxtNGAY_SINH = new Label(shell, SWT.NONE);
        lbltxtNGAY_SINH.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtNGAY_SINH.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtNGAY_SINH.setText("NGAY_SINH :");
		
		txtNGAY_SINH = new Text(shell, SWT.BORDER);
        txtNGAY_SINH.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtNGAY_SINH.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtNGAY_SINH.setText("NGAY_SINH");
        txtNGAY_SINH.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressKhamBenhDlg(e);
			}
		});
		Label lbltxtGIOI_TINH = new Label(shell, SWT.NONE);
        lbltxtGIOI_TINH.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtGIOI_TINH.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtGIOI_TINH.setText("GIOI_TINH :");
		
		txtGIOI_TINH = new Text(shell, SWT.BORDER);
        txtGIOI_TINH.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtGIOI_TINH.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtGIOI_TINH.setText("GIOI_TINH");
        txtGIOI_TINH.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressKhamBenhDlg(e);
			}
		});
		Label lbltxtDIA_CHI = new Label(shell, SWT.NONE);
        lbltxtDIA_CHI.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtDIA_CHI.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtDIA_CHI.setText("DIA_CHI :");
		
		txtDIA_CHI = new Text(shell, SWT.BORDER);
        txtDIA_CHI.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtDIA_CHI.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtDIA_CHI.setText("DIA_CHI");
        txtDIA_CHI.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressKhamBenhDlg(e);
			}
		});
		Label lbltxtMA_THE = new Label(shell, SWT.NONE);
        lbltxtMA_THE.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtMA_THE.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtMA_THE.setText("MA_THE :");
		
		txtMA_THE = new Text(shell, SWT.BORDER);
        txtMA_THE.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtMA_THE.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtMA_THE.setText("MA_THE");
        txtMA_THE.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressKhamBenhDlg(e);
			}
		});
		Label lbltxtMA_DKBD = new Label(shell, SWT.NONE);
        lbltxtMA_DKBD.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtMA_DKBD.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtMA_DKBD.setText("MA_DKBD :");
		
		txtMA_DKBD = new Text(shell, SWT.BORDER);
        txtMA_DKBD.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtMA_DKBD.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtMA_DKBD.setText("MA_DKBD");
        txtMA_DKBD.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressKhamBenhDlg(e);
			}
		});
		Label lbltxtGT_THE_TU = new Label(shell, SWT.NONE);
        lbltxtGT_THE_TU.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtGT_THE_TU.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtGT_THE_TU.setText("GT_THE_TU :");
		
		txtGT_THE_TU = new Text(shell, SWT.BORDER);
        txtGT_THE_TU.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtGT_THE_TU.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtGT_THE_TU.setText("GT_THE_TU");
        txtGT_THE_TU.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressKhamBenhDlg(e);
			}
		});
		Label lbltxtGT_THE_DEN = new Label(shell, SWT.NONE);
        lbltxtGT_THE_DEN.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtGT_THE_DEN.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtGT_THE_DEN.setText("GT_THE_DEN :");
		
		txtGT_THE_DEN = new Text(shell, SWT.BORDER);
        txtGT_THE_DEN.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtGT_THE_DEN.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtGT_THE_DEN.setText("GT_THE_DEN");
        txtGT_THE_DEN.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressKhamBenhDlg(e);
			}
		});
		Label lbltxtNGAY_CAP = new Label(shell, SWT.NONE);
        lbltxtNGAY_CAP.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtNGAY_CAP.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtNGAY_CAP.setText("NGAY_CAP :");
		
		txtNGAY_CAP = new Text(shell, SWT.BORDER);
        txtNGAY_CAP.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtNGAY_CAP.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtNGAY_CAP.setText("NGAY_CAP");
        txtNGAY_CAP.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressKhamBenhDlg(e);
			}
		});
		Label lbltxtMIEN_CUNG_CT = new Label(shell, SWT.NONE);
        lbltxtMIEN_CUNG_CT.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtMIEN_CUNG_CT.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtMIEN_CUNG_CT.setText("MIEN_CUNG_CT :");
		
		txtMIEN_CUNG_CT = new Text(shell, SWT.BORDER);
        txtMIEN_CUNG_CT.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtMIEN_CUNG_CT.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtMIEN_CUNG_CT.setText("MIEN_CUNG_CT");
        txtMIEN_CUNG_CT.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressKhamBenhDlg(e);
			}
		});
		Label lbltxtMA_QUAN_LY = new Label(shell, SWT.NONE);
        lbltxtMA_QUAN_LY.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtMA_QUAN_LY.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtMA_QUAN_LY.setText("MA_QUAN_LY :");
		
		txtMA_QUAN_LY = new Text(shell, SWT.BORDER);
        txtMA_QUAN_LY.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtMA_QUAN_LY.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtMA_QUAN_LY.setText("MA_QUAN_LY");
        txtMA_QUAN_LY.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressKhamBenhDlg(e);
			}
		});
		Label lbltxtTEN_CHA_ME = new Label(shell, SWT.NONE);
        lbltxtTEN_CHA_ME.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtTEN_CHA_ME.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtTEN_CHA_ME.setText("TEN_CHA_ME :");
		
		txtTEN_CHA_ME = new Text(shell, SWT.BORDER);
        txtTEN_CHA_ME.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtTEN_CHA_ME.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtTEN_CHA_ME.setText("TEN_CHA_ME");
        txtTEN_CHA_ME.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressKhamBenhDlg(e);
			}
		});
		Label lbltxtMA_DT_SONG = new Label(shell, SWT.NONE);
        lbltxtMA_DT_SONG.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtMA_DT_SONG.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtMA_DT_SONG.setText("MA_DT_SONG :");
		
		txtMA_DT_SONG = new Text(shell, SWT.BORDER);
        txtMA_DT_SONG.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtMA_DT_SONG.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtMA_DT_SONG.setText("MA_DT_SONG");
        txtMA_DT_SONG.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressKhamBenhDlg(e);
			}
		});
		Label lbltxtTHOIDIEM_NAMNAM = new Label(shell, SWT.NONE);
        lbltxtTHOIDIEM_NAMNAM.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtTHOIDIEM_NAMNAM.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtTHOIDIEM_NAMNAM.setText("THOIDIEM_NAMNAM :");
		
		txtTHOIDIEM_NAMNAM = new Text(shell, SWT.BORDER);
        txtTHOIDIEM_NAMNAM.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtTHOIDIEM_NAMNAM.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtTHOIDIEM_NAMNAM.setText("THOIDIEM_NAMNAM");
        txtTHOIDIEM_NAMNAM.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressKhamBenhDlg(e);
			}
		});
		Label lbltxtCHUOI_KIEM_TRA = new Label(shell, SWT.NONE);
        lbltxtCHUOI_KIEM_TRA.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtCHUOI_KIEM_TRA.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtCHUOI_KIEM_TRA.setText("CHUOI_KIEM_TRA :");
		
		txtCHUOI_KIEM_TRA = new Text(shell, SWT.BORDER);
        txtCHUOI_KIEM_TRA.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtCHUOI_KIEM_TRA.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtCHUOI_KIEM_TRA.setText("CHUOI_KIEM_TRA");
        txtCHUOI_KIEM_TRA.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressKhamBenhDlg(e);
			}
		});
		Label lbltxtGATE_INFO = new Label(shell, SWT.NONE);
        lbltxtGATE_INFO.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtGATE_INFO.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtGATE_INFO.setText("GATE_INFO :");
		
		txtGATE_INFO = new Text(shell, SWT.BORDER);
        txtGATE_INFO.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtGATE_INFO.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtGATE_INFO.setText("GATE_INFO");
        txtGATE_INFO.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressKhamBenhDlg(e);
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
				keyPressKhamBenhDlg(e);
			}
		});
		new Label(shell, SWT.NONE);
		btnNewButtonSaveKhamBenh = new Button(shell, SWT.NONE);
		btnNewButtonSaveKhamBenh.setImage(SWTResourceManager.getImage(KhamBenhDlg.class, "/png/file-2x.png"));
        btnNewButtonSaveKhamBenh.setFont(SWTResourceManager.getFont("Tahoma", 12, SWT.BOLD));
		btnNewButtonSaveKhamBenh.setText("Save");
        
        btnNewButtonSaveKhamBenh.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
                saveKhamBenhDlg();
			}
		});
        
        loadKhamBenhDlgData();
	}

    private void saveKhamBenhDlg(){
        if(intTypeDlgKhamBenh==TYPE_DLG_VIEW){
            return;
        }
        
        if(objKhamBenh == null){
            objKhamBenh = new KhamBenh();
        }
        if(objKhamBenh!=null){
        // Integer    = true
            objKhamBenh.STT = Utils.getInt( txtSTT.getText() );
            // Integer    = true
            objKhamBenh.BN_ID = Utils.getInt( txtBN_ID.getText() );
            // String     = false
            objKhamBenh.TEN_BENH_NHAN = txtTEN_BENH_NHAN.getText();
            // String     = false
            objKhamBenh.TEN_BENH = txtTEN_BENH.getText();
            // String     = false
            objKhamBenh.MA_BENH = txtMA_BENH.getText();
            // String     = false
            objKhamBenh.MA_BENHKHAC = txtMA_BENHKHAC.getText();
            // Integer    = true
            objKhamBenh.MA_LYDO_VVIEN = Utils.getInt( txtMA_LYDO_VVIEN.getText() );
            // String     = false
            objKhamBenh.MA_NOI_CHUYEN = txtMA_NOI_CHUYEN.getText();
            // Integer    = true
            objKhamBenh.MA_TAI_NAN = Utils.getInt( txtMA_TAI_NAN.getText() );
            // String     = false
            objKhamBenh.NGAY_VAO = txtNGAY_VAO.getText();
            // String     = false
            objKhamBenh.NGAY_RA = txtNGAY_RA.getText();
            // Integer    = true
            objKhamBenh.SO_NGAY_DTRI = Utils.getInt( txtSO_NGAY_DTRI.getText() );
            // Integer    = true
            objKhamBenh.KET_QUA_DTRI = Utils.getInt( txtKET_QUA_DTRI.getText() );
            // Integer    = true
            objKhamBenh.TINH_TRANG_RV = Utils.getInt( txtTINH_TRANG_RV.getText() );
            // String     = false
            objKhamBenh.NGAY_TTOAN = txtNGAY_TTOAN.getText();
            // Integer    = true
            objKhamBenh.MUC_HUONG = Utils.getInt( txtMUC_HUONG.getText() );
            // Double     = false
            // objKhamBenh.T_THUOC = txtT_THUOC.getText();
            // Double     = false
            // objKhamBenh.T_VTYT = txtT_VTYT.getText();
            // Double     = false
            // objKhamBenh.T_TONGCHI = txtT_TONGCHI.getText();
            // Double     = false
            // objKhamBenh.T_BNTT = txtT_BNTT.getText();
            // Double     = false
            // objKhamBenh.T_BNCCT = txtT_BNCCT.getText();
            // Double     = false
            // objKhamBenh.T_BHTT = txtT_BHTT.getText();
            // Double     = false
            // objKhamBenh.T_NGUONKHAC = txtT_NGUONKHAC.getText();
            // Double     = false
            // objKhamBenh.T_NGOAIDS = txtT_NGOAIDS.getText();
            // Integer    = true
            objKhamBenh.NAM_QT = Utils.getInt( txtNAM_QT.getText() );
            // Integer    = true
            objKhamBenh.THANG_QT = Utils.getInt( txtTHANG_QT.getText() );
            // Integer    = true
            objKhamBenh.MA_LOAI_KCB = Utils.getInt( txtMA_LOAI_KCB.getText() );
            // String     = false
            objKhamBenh.MA_KHOA = txtMA_KHOA.getText();
            // String     = false
            objKhamBenh.MA_CSKCB = txtMA_CSKCB.getText();
            // String     = false
            objKhamBenh.MA_KHUVUC = txtMA_KHUVUC.getText();
            // String     = false
            objKhamBenh.MA_PTTT_QT = txtMA_PTTT_QT.getText();
            // Float      = false
            // objKhamBenh.CAN_NANG = txtCAN_NANG.getText();
            // Date       = false
            // objKhamBenh.KB_DATE = txtKB_DATE.getText();
            // Integer    = true
            objKhamBenh.KIEU_TT = Utils.getInt( txtKIEU_TT.getText() );
            // String     = false
            objKhamBenh.CHANDOAN_BD = txtCHANDOAN_BD.getText();
            // Integer    = true
            objKhamBenh.NV_ID = Utils.getInt( txtNV_ID.getText() );
            // String     = false
            objKhamBenh.NV_NAME = txtNV_NAME.getText();
            // Integer    = true
            objKhamBenh.TABLE_ID = Utils.getInt( txtTABLE_ID.getText() );
            // String     = false
            objKhamBenh.NGAY_SINH = txtNGAY_SINH.getText();
            // Integer    = true
            objKhamBenh.GIOI_TINH = Utils.getInt( txtGIOI_TINH.getText() );
            // String     = false
            objKhamBenh.DIA_CHI = txtDIA_CHI.getText();
            // String     = false
            objKhamBenh.MA_THE = txtMA_THE.getText();
            // String     = false
            objKhamBenh.MA_DKBD = txtMA_DKBD.getText();
            // String     = false
            objKhamBenh.GT_THE_TU = txtGT_THE_TU.getText();
            // String     = false
            objKhamBenh.GT_THE_DEN = txtGT_THE_DEN.getText();
            // String     = false
            objKhamBenh.NGAY_CAP = txtNGAY_CAP.getText();
            // String     = false
            objKhamBenh.MIEN_CUNG_CT = txtMIEN_CUNG_CT.getText();
            // String     = false
            objKhamBenh.MA_QUAN_LY = txtMA_QUAN_LY.getText();
            // String     = false
            objKhamBenh.TEN_CHA_ME = txtTEN_CHA_ME.getText();
            // Integer    = true
            objKhamBenh.MA_DT_SONG = Utils.getInt( txtMA_DT_SONG.getText() );
            // String     = false
            objKhamBenh.THOIDIEM_NAMNAM = txtTHOIDIEM_NAMNAM.getText();
            // String     = false
            objKhamBenh.CHUOI_KIEM_TRA = txtCHUOI_KIEM_TRA.getText();
            // String     = false
            objKhamBenh.GATE_INFO = txtGATE_INFO.getText();
            // Integer    = true
            objKhamBenh.STS = Utils.getInt( txtSTS.getText() );
            }
        if(DbHelper.checkPhanQuyen(DbHelper.UPDATE, "kham_benh")==false){
            logger.info("DON'T HAVE UPDATE RIGHT");
        }
        else if(DbHelper.checkPhanQuyen(DbHelper.INSERT, "kham_benh")==false){
            logger.info("DON'T HAVE INSERT RIGHT");
        }        
        else{
            objKhamBenh.insert();
        }
        shell.close();
    }
    
    public void setKhamBenhDlgData(KhamBenh obj) {
		this.objKhamBenh = obj;
	}
    
    public void loadKhamBenhDlgData(){
        if(intTypeDlgKhamBenh==TYPE_DLG_VIEW){
            btnNewButtonSaveKhamBenh.setEnabled(false);
        }
        else{
            btnNewButtonSaveKhamBenh.setEnabled(true);
        }

        if(DbHelper.checkPhanQuyen(DbHelper.READ, "kham_benh")==false){
            logger.info("DON'T HAVE READ RIGHT");
            return;
        }
        if(objKhamBenh != null){
            if(objKhamBenh.STT==null)
                txtSTT.setText("");
            else
                txtSTT.setText(""+objKhamBenh.STT.toString());
            if(objKhamBenh.BN_ID==null)
                txtBN_ID.setText("");
            else
                txtBN_ID.setText(""+objKhamBenh.BN_ID.toString());
            if(objKhamBenh.TEN_BENH_NHAN==null)
                txtTEN_BENH_NHAN.setText("");
            else
                txtTEN_BENH_NHAN.setText(""+objKhamBenh.TEN_BENH_NHAN.toString());
            if(objKhamBenh.TEN_BENH==null)
                txtTEN_BENH.setText("");
            else
                txtTEN_BENH.setText(""+objKhamBenh.TEN_BENH.toString());
            if(objKhamBenh.MA_BENH==null)
                txtMA_BENH.setText("");
            else
                txtMA_BENH.setText(""+objKhamBenh.MA_BENH.toString());
            if(objKhamBenh.MA_BENHKHAC==null)
                txtMA_BENHKHAC.setText("");
            else
                txtMA_BENHKHAC.setText(""+objKhamBenh.MA_BENHKHAC.toString());
            if(objKhamBenh.MA_LYDO_VVIEN==null)
                txtMA_LYDO_VVIEN.setText("");
            else
                txtMA_LYDO_VVIEN.setText(""+objKhamBenh.MA_LYDO_VVIEN.toString());
            if(objKhamBenh.MA_NOI_CHUYEN==null)
                txtMA_NOI_CHUYEN.setText("");
            else
                txtMA_NOI_CHUYEN.setText(""+objKhamBenh.MA_NOI_CHUYEN.toString());
            if(objKhamBenh.MA_TAI_NAN==null)
                txtMA_TAI_NAN.setText("");
            else
                txtMA_TAI_NAN.setText(""+objKhamBenh.MA_TAI_NAN.toString());
            if(objKhamBenh.NGAY_VAO==null)
                txtNGAY_VAO.setText("");
            else
                txtNGAY_VAO.setText(""+objKhamBenh.NGAY_VAO.toString());
            if(objKhamBenh.NGAY_RA==null)
                txtNGAY_RA.setText("");
            else
                txtNGAY_RA.setText(""+objKhamBenh.NGAY_RA.toString());
            if(objKhamBenh.SO_NGAY_DTRI==null)
                txtSO_NGAY_DTRI.setText("");
            else
                txtSO_NGAY_DTRI.setText(""+objKhamBenh.SO_NGAY_DTRI.toString());
            if(objKhamBenh.KET_QUA_DTRI==null)
                txtKET_QUA_DTRI.setText("");
            else
                txtKET_QUA_DTRI.setText(""+objKhamBenh.KET_QUA_DTRI.toString());
            if(objKhamBenh.TINH_TRANG_RV==null)
                txtTINH_TRANG_RV.setText("");
            else
                txtTINH_TRANG_RV.setText(""+objKhamBenh.TINH_TRANG_RV.toString());
            if(objKhamBenh.NGAY_TTOAN==null)
                txtNGAY_TTOAN.setText("");
            else
                txtNGAY_TTOAN.setText(""+objKhamBenh.NGAY_TTOAN.toString());
            if(objKhamBenh.MUC_HUONG==null)
                txtMUC_HUONG.setText("");
            else
                txtMUC_HUONG.setText(""+objKhamBenh.MUC_HUONG.toString());
            if(objKhamBenh.T_THUOC==null)
                txtT_THUOC.setText("");
            else
                txtT_THUOC.setText(""+objKhamBenh.T_THUOC.toString());
            if(objKhamBenh.T_VTYT==null)
                txtT_VTYT.setText("");
            else
                txtT_VTYT.setText(""+objKhamBenh.T_VTYT.toString());
            if(objKhamBenh.T_TONGCHI==null)
                txtT_TONGCHI.setText("");
            else
                txtT_TONGCHI.setText(""+objKhamBenh.T_TONGCHI.toString());
            if(objKhamBenh.T_BNTT==null)
                txtT_BNTT.setText("");
            else
                txtT_BNTT.setText(""+objKhamBenh.T_BNTT.toString());
            if(objKhamBenh.T_BNCCT==null)
                txtT_BNCCT.setText("");
            else
                txtT_BNCCT.setText(""+objKhamBenh.T_BNCCT.toString());
            if(objKhamBenh.T_BHTT==null)
                txtT_BHTT.setText("");
            else
                txtT_BHTT.setText(""+objKhamBenh.T_BHTT.toString());
            if(objKhamBenh.T_NGUONKHAC==null)
                txtT_NGUONKHAC.setText("");
            else
                txtT_NGUONKHAC.setText(""+objKhamBenh.T_NGUONKHAC.toString());
            if(objKhamBenh.T_NGOAIDS==null)
                txtT_NGOAIDS.setText("");
            else
                txtT_NGOAIDS.setText(""+objKhamBenh.T_NGOAIDS.toString());
            if(objKhamBenh.NAM_QT==null)
                txtNAM_QT.setText("");
            else
                txtNAM_QT.setText(""+objKhamBenh.NAM_QT.toString());
            if(objKhamBenh.THANG_QT==null)
                txtTHANG_QT.setText("");
            else
                txtTHANG_QT.setText(""+objKhamBenh.THANG_QT.toString());
            if(objKhamBenh.MA_LOAI_KCB==null)
                txtMA_LOAI_KCB.setText("");
            else
                txtMA_LOAI_KCB.setText(""+objKhamBenh.MA_LOAI_KCB.toString());
            if(objKhamBenh.MA_KHOA==null)
                txtMA_KHOA.setText("");
            else
                txtMA_KHOA.setText(""+objKhamBenh.MA_KHOA.toString());
            if(objKhamBenh.MA_CSKCB==null)
                txtMA_CSKCB.setText("");
            else
                txtMA_CSKCB.setText(""+objKhamBenh.MA_CSKCB.toString());
            if(objKhamBenh.MA_KHUVUC==null)
                txtMA_KHUVUC.setText("");
            else
                txtMA_KHUVUC.setText(""+objKhamBenh.MA_KHUVUC.toString());
            if(objKhamBenh.MA_PTTT_QT==null)
                txtMA_PTTT_QT.setText("");
            else
                txtMA_PTTT_QT.setText(""+objKhamBenh.MA_PTTT_QT.toString());
            if(objKhamBenh.CAN_NANG==null)
                txtCAN_NANG.setText("");
            else
                txtCAN_NANG.setText(""+objKhamBenh.CAN_NANG.toString());
            if(objKhamBenh.KB_DATE==null)
                txtKB_DATE.setText("");
            else
                txtKB_DATE.setText(""+objKhamBenh.KB_DATE.toString());
            if(objKhamBenh.KIEU_TT==null)
                txtKIEU_TT.setText("");
            else
                txtKIEU_TT.setText(""+objKhamBenh.KIEU_TT.toString());
            if(objKhamBenh.CHANDOAN_BD==null)
                txtCHANDOAN_BD.setText("");
            else
                txtCHANDOAN_BD.setText(""+objKhamBenh.CHANDOAN_BD.toString());
            if(objKhamBenh.NV_ID==null)
                txtNV_ID.setText("");
            else
                txtNV_ID.setText(""+objKhamBenh.NV_ID.toString());
            if(objKhamBenh.NV_NAME==null)
                txtNV_NAME.setText("");
            else
                txtNV_NAME.setText(""+objKhamBenh.NV_NAME.toString());
            if(objKhamBenh.TABLE_ID==null)
                txtTABLE_ID.setText("");
            else
                txtTABLE_ID.setText(""+objKhamBenh.TABLE_ID.toString());
            if(objKhamBenh.NGAY_SINH==null)
                txtNGAY_SINH.setText("");
            else
                txtNGAY_SINH.setText(""+objKhamBenh.NGAY_SINH.toString());
            if(objKhamBenh.GIOI_TINH==null)
                txtGIOI_TINH.setText("");
            else
                txtGIOI_TINH.setText(""+objKhamBenh.GIOI_TINH.toString());
            if(objKhamBenh.DIA_CHI==null)
                txtDIA_CHI.setText("");
            else
                txtDIA_CHI.setText(""+objKhamBenh.DIA_CHI.toString());
            if(objKhamBenh.MA_THE==null)
                txtMA_THE.setText("");
            else
                txtMA_THE.setText(""+objKhamBenh.MA_THE.toString());
            if(objKhamBenh.MA_DKBD==null)
                txtMA_DKBD.setText("");
            else
                txtMA_DKBD.setText(""+objKhamBenh.MA_DKBD.toString());
            if(objKhamBenh.GT_THE_TU==null)
                txtGT_THE_TU.setText("");
            else
                txtGT_THE_TU.setText(""+objKhamBenh.GT_THE_TU.toString());
            if(objKhamBenh.GT_THE_DEN==null)
                txtGT_THE_DEN.setText("");
            else
                txtGT_THE_DEN.setText(""+objKhamBenh.GT_THE_DEN.toString());
            if(objKhamBenh.NGAY_CAP==null)
                txtNGAY_CAP.setText("");
            else
                txtNGAY_CAP.setText(""+objKhamBenh.NGAY_CAP.toString());
            if(objKhamBenh.MIEN_CUNG_CT==null)
                txtMIEN_CUNG_CT.setText("");
            else
                txtMIEN_CUNG_CT.setText(""+objKhamBenh.MIEN_CUNG_CT.toString());
            if(objKhamBenh.MA_QUAN_LY==null)
                txtMA_QUAN_LY.setText("");
            else
                txtMA_QUAN_LY.setText(""+objKhamBenh.MA_QUAN_LY.toString());
            if(objKhamBenh.TEN_CHA_ME==null)
                txtTEN_CHA_ME.setText("");
            else
                txtTEN_CHA_ME.setText(""+objKhamBenh.TEN_CHA_ME.toString());
            if(objKhamBenh.MA_DT_SONG==null)
                txtMA_DT_SONG.setText("");
            else
                txtMA_DT_SONG.setText(""+objKhamBenh.MA_DT_SONG.toString());
            if(objKhamBenh.THOIDIEM_NAMNAM==null)
                txtTHOIDIEM_NAMNAM.setText("");
            else
                txtTHOIDIEM_NAMNAM.setText(""+objKhamBenh.THOIDIEM_NAMNAM.toString());
            if(objKhamBenh.CHUOI_KIEM_TRA==null)
                txtCHUOI_KIEM_TRA.setText("");
            else
                txtCHUOI_KIEM_TRA.setText(""+objKhamBenh.CHUOI_KIEM_TRA.toString());
            if(objKhamBenh.GATE_INFO==null)
                txtGATE_INFO.setText("");
            else
                txtGATE_INFO.setText(""+objKhamBenh.GATE_INFO.toString());
            if(objKhamBenh.STS==null)
                txtSTS.setText("");
            else
                txtSTS.setText(""+objKhamBenh.STS.toString());
        }
    }
    
    protected void keyPressKhamBenhDlg(KeyEvent e) {
		if(e.keyCode==13){
			saveKhamBenhDlg();
		}
		
	}
}
