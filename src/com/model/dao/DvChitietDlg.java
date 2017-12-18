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

public class DvChitietDlg extends Dialog {
	static Logger logger = LogManager.getLogger(DvChitietDlg.class.getName());

	protected Object result;
	protected Shell shell;
    private Text txtBN_ID;
    private Text txtMA_LK;
    private Text txtDV_ID;
    private Text txtMA_DICH_VU;
    private Text txtMA_VAT_TU;
    private Text txtMA_NHOM;
    private Text txtGOI_VTYT;
    private Text txtTEN_VAT_TU;
    private Text txtTEN_DICH_VU;
    private Text txtDON_VI_TINH;
    private Text txtPHAM_VI;
    private Text txtSO_LUONG;
    private Text txtDON_GIA;
    private Text txtDON_GIA2;
    private Text txtTT_THAU;
    private Text txtTHANH_TIEN;
    private Text txtTHANH_TIEN2;
    private Text txtT_TRANTT;
    private Text txtMUC_HUONG;
    private Text txtT_NGUONKHAC;
    private Text txtTT_BHTT;
    private Text txtTT_BNTT;
    private Text txtT_BNCCT;
    private Text txtT_NGOAIDS;
    private Text txtMA_GIUONG;
    private Text txtMA_KHOA;
    private Text txtMA_BAC_SI;
    private Text txtMA_BENH;
    private Text txtNGAY_YL;
    private Text txtNGAY_KQ;
    private Text txtMA_PTTT;
    private Text txtTYLE_TT;
    private Text txtCUR_DATE;
    private Text txtTYP;
    private Text txtTHANHTOAN;
    private Text txtNV_ID;
    private Text txtNV_NAME;
    private Text txtNHOM_DV;
    private Text txtSTS;

    public DvChitiet objDvChitiet;
    Button btnNewButtonSaveDvChitiet;

    public int intTypeDlgDvChitiet;
	public static final int TYPE_DLG_VIEW = 1;
	public static final int TYPE_DLG_EDIT = 2;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public DvChitietDlg(Shell parent, int style) {
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
		shell.setText("DvChitietDlg EDIT/NEW");
		shell.setLayout(new GridLayout(2, false));
        shell.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.keyCode==SWT.ESC){
					objDvChitiet = null;
				}
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
				keyPressDvChitietDlg(e);
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
				keyPressDvChitietDlg(e);
			}
		});
		Label lbltxtDV_ID = new Label(shell, SWT.NONE);
        lbltxtDV_ID.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtDV_ID.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtDV_ID.setText("DV_ID :");
		
		txtDV_ID = new Text(shell, SWT.BORDER);
        txtDV_ID.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtDV_ID.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtDV_ID.setText("DV_ID");
        txtDV_ID.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressDvChitietDlg(e);
			}
		});
		Label lbltxtMA_DICH_VU = new Label(shell, SWT.NONE);
        lbltxtMA_DICH_VU.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtMA_DICH_VU.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtMA_DICH_VU.setText("MA_DICH_VU :");
		
		txtMA_DICH_VU = new Text(shell, SWT.BORDER);
        txtMA_DICH_VU.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtMA_DICH_VU.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtMA_DICH_VU.setText("MA_DICH_VU");
        txtMA_DICH_VU.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressDvChitietDlg(e);
			}
		});
		Label lbltxtMA_VAT_TU = new Label(shell, SWT.NONE);
        lbltxtMA_VAT_TU.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtMA_VAT_TU.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtMA_VAT_TU.setText("MA_VAT_TU :");
		
		txtMA_VAT_TU = new Text(shell, SWT.BORDER);
        txtMA_VAT_TU.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtMA_VAT_TU.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtMA_VAT_TU.setText("MA_VAT_TU");
        txtMA_VAT_TU.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressDvChitietDlg(e);
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
				keyPressDvChitietDlg(e);
			}
		});
		Label lbltxtGOI_VTYT = new Label(shell, SWT.NONE);
        lbltxtGOI_VTYT.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtGOI_VTYT.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtGOI_VTYT.setText("GOI_VTYT :");
		
		txtGOI_VTYT = new Text(shell, SWT.BORDER);
        txtGOI_VTYT.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtGOI_VTYT.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtGOI_VTYT.setText("GOI_VTYT");
        txtGOI_VTYT.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressDvChitietDlg(e);
			}
		});
		Label lbltxtTEN_VAT_TU = new Label(shell, SWT.NONE);
        lbltxtTEN_VAT_TU.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtTEN_VAT_TU.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtTEN_VAT_TU.setText("TEN_VAT_TU :");
		
		txtTEN_VAT_TU = new Text(shell, SWT.BORDER);
        txtTEN_VAT_TU.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtTEN_VAT_TU.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtTEN_VAT_TU.setText("TEN_VAT_TU");
        txtTEN_VAT_TU.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressDvChitietDlg(e);
			}
		});
		Label lbltxtTEN_DICH_VU = new Label(shell, SWT.NONE);
        lbltxtTEN_DICH_VU.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtTEN_DICH_VU.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtTEN_DICH_VU.setText("TEN_DICH_VU :");
		
		txtTEN_DICH_VU = new Text(shell, SWT.BORDER);
        txtTEN_DICH_VU.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtTEN_DICH_VU.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtTEN_DICH_VU.setText("TEN_DICH_VU");
        txtTEN_DICH_VU.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressDvChitietDlg(e);
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
				keyPressDvChitietDlg(e);
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
				keyPressDvChitietDlg(e);
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
				keyPressDvChitietDlg(e);
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
				keyPressDvChitietDlg(e);
			}
		});
		Label lbltxtDON_GIA2 = new Label(shell, SWT.NONE);
        lbltxtDON_GIA2.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtDON_GIA2.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtDON_GIA2.setText("DON_GIA2 :");
		
		txtDON_GIA2 = new Text(shell, SWT.BORDER);
        txtDON_GIA2.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtDON_GIA2.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtDON_GIA2.setText("DON_GIA2");
        txtDON_GIA2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressDvChitietDlg(e);
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
				keyPressDvChitietDlg(e);
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
				keyPressDvChitietDlg(e);
			}
		});
		Label lbltxtTHANH_TIEN2 = new Label(shell, SWT.NONE);
        lbltxtTHANH_TIEN2.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtTHANH_TIEN2.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtTHANH_TIEN2.setText("THANH_TIEN2 :");
		
		txtTHANH_TIEN2 = new Text(shell, SWT.BORDER);
        txtTHANH_TIEN2.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtTHANH_TIEN2.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtTHANH_TIEN2.setText("THANH_TIEN2");
        txtTHANH_TIEN2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressDvChitietDlg(e);
			}
		});
		Label lbltxtT_TRANTT = new Label(shell, SWT.NONE);
        lbltxtT_TRANTT.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtT_TRANTT.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtT_TRANTT.setText("T_TRANTT :");
		
		txtT_TRANTT = new Text(shell, SWT.BORDER);
        txtT_TRANTT.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtT_TRANTT.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtT_TRANTT.setText("T_TRANTT");
        txtT_TRANTT.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressDvChitietDlg(e);
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
				keyPressDvChitietDlg(e);
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
				keyPressDvChitietDlg(e);
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
				keyPressDvChitietDlg(e);
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
				keyPressDvChitietDlg(e);
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
				keyPressDvChitietDlg(e);
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
				keyPressDvChitietDlg(e);
			}
		});
		Label lbltxtMA_GIUONG = new Label(shell, SWT.NONE);
        lbltxtMA_GIUONG.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtMA_GIUONG.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtMA_GIUONG.setText("MA_GIUONG :");
		
		txtMA_GIUONG = new Text(shell, SWT.BORDER);
        txtMA_GIUONG.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtMA_GIUONG.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtMA_GIUONG.setText("MA_GIUONG");
        txtMA_GIUONG.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressDvChitietDlg(e);
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
				keyPressDvChitietDlg(e);
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
				keyPressDvChitietDlg(e);
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
				keyPressDvChitietDlg(e);
			}
		});
		Label lbltxtNGAY_YL = new Label(shell, SWT.NONE);
        lbltxtNGAY_YL.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtNGAY_YL.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtNGAY_YL.setText("NGAY_YL :");
		
		txtNGAY_YL = new Text(shell, SWT.BORDER);
        txtNGAY_YL.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtNGAY_YL.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtNGAY_YL.setText("NGAY_YL");
        txtNGAY_YL.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressDvChitietDlg(e);
			}
		});
		Label lbltxtNGAY_KQ = new Label(shell, SWT.NONE);
        lbltxtNGAY_KQ.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtNGAY_KQ.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtNGAY_KQ.setText("NGAY_KQ :");
		
		txtNGAY_KQ = new Text(shell, SWT.BORDER);
        txtNGAY_KQ.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtNGAY_KQ.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtNGAY_KQ.setText("NGAY_KQ");
        txtNGAY_KQ.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressDvChitietDlg(e);
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
				keyPressDvChitietDlg(e);
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
				keyPressDvChitietDlg(e);
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
				keyPressDvChitietDlg(e);
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
				keyPressDvChitietDlg(e);
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
				keyPressDvChitietDlg(e);
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
				keyPressDvChitietDlg(e);
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
				keyPressDvChitietDlg(e);
			}
		});
		Label lbltxtNHOM_DV = new Label(shell, SWT.NONE);
        lbltxtNHOM_DV.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtNHOM_DV.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtNHOM_DV.setText("NHOM_DV :");
		
		txtNHOM_DV = new Text(shell, SWT.BORDER);
        txtNHOM_DV.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtNHOM_DV.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtNHOM_DV.setText("NHOM_DV");
        txtNHOM_DV.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressDvChitietDlg(e);
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
				keyPressDvChitietDlg(e);
			}
		});
		new Label(shell, SWT.NONE);
		btnNewButtonSaveDvChitiet = new Button(shell, SWT.NONE);
		btnNewButtonSaveDvChitiet.setImage(SWTResourceManager.getImage(DvChitietDlg.class, "/png/file-2x.png"));
        btnNewButtonSaveDvChitiet.setFont(SWTResourceManager.getFont("Tahoma", 12, SWT.BOLD));
		btnNewButtonSaveDvChitiet.setText("Save");
        
        btnNewButtonSaveDvChitiet.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
                saveDvChitietDlg();
			}
		});
        
        loadDvChitietDlgData();
	}

    private void saveDvChitietDlg(){
        if(intTypeDlgDvChitiet==TYPE_DLG_VIEW){
            return;
        }
        
        if(objDvChitiet == null){
            objDvChitiet = new DvChitiet();
        }
        if(objDvChitiet!=null){
        // Integer    = true
            objDvChitiet.BN_ID = Utils.getInt( txtBN_ID.getText() );
            // Integer    = true
            objDvChitiet.MA_LK = Utils.getInt( txtMA_LK.getText() );
            // Integer    = true
            objDvChitiet.DV_ID = Utils.getInt( txtDV_ID.getText() );
            // String     = false
            objDvChitiet.MA_DICH_VU = txtMA_DICH_VU.getText();
            // String     = false
            objDvChitiet.MA_VAT_TU = txtMA_VAT_TU.getText();
            // Integer    = true
            objDvChitiet.MA_NHOM = Utils.getInt( txtMA_NHOM.getText() );
            // String     = false
            objDvChitiet.GOI_VTYT = txtGOI_VTYT.getText();
            // String     = false
            objDvChitiet.TEN_VAT_TU = txtTEN_VAT_TU.getText();
            // String     = false
            objDvChitiet.TEN_DICH_VU = txtTEN_DICH_VU.getText();
            // String     = false
            objDvChitiet.DON_VI_TINH = txtDON_VI_TINH.getText();
            // Integer    = true
            objDvChitiet.PHAM_VI = Utils.getInt( txtPHAM_VI.getText() );
            // Integer    = true
            objDvChitiet.SO_LUONG = Utils.getInt( txtSO_LUONG.getText() );
            // Integer    = true
            objDvChitiet.DON_GIA = Utils.getInt( txtDON_GIA.getText() );
            // Integer    = true
            objDvChitiet.DON_GIA2 = Utils.getInt( txtDON_GIA2.getText() );
            // String     = false
            objDvChitiet.TT_THAU = txtTT_THAU.getText();
            // Integer    = true
            objDvChitiet.THANH_TIEN = Utils.getInt( txtTHANH_TIEN.getText() );
            // Integer    = true
            objDvChitiet.THANH_TIEN2 = Utils.getInt( txtTHANH_TIEN2.getText() );
            // Integer    = true
            objDvChitiet.T_TRANTT = Utils.getInt( txtT_TRANTT.getText() );
            // Integer    = true
            objDvChitiet.TYLE_TT = Utils.getInt( txtMUC_HUONG.getText() );
            // Integer    = true
            objDvChitiet.T_NGUONKHAC = Utils.getInt( txtT_NGUONKHAC.getText() );
            // Integer    = true
            objDvChitiet.TT_BHTT = Utils.getInt( txtTT_BHTT.getText() );
            // Integer    = true
            objDvChitiet.TT_BNTT = Utils.getInt( txtTT_BNTT.getText() );
            // Integer    = true
            objDvChitiet.T_BNCCT = Utils.getInt( txtT_BNCCT.getText() );
            // Integer    = true
            objDvChitiet.T_NGOAIDS = Utils.getInt( txtT_NGOAIDS.getText() );
            // String     = false
            objDvChitiet.MA_GIUONG = txtMA_GIUONG.getText();
            // String     = false
            objDvChitiet.MA_KHOA = txtMA_KHOA.getText();
            // String     = false
            objDvChitiet.MA_BAC_SI = txtMA_BAC_SI.getText();
            // String     = false
            objDvChitiet.MA_BENH = txtMA_BENH.getText();
            // String     = false
            objDvChitiet.NGAY_YL = txtNGAY_YL.getText();
            // String     = false
            objDvChitiet.NGAY_KQ = txtNGAY_KQ.getText();
            // Integer    = true
            objDvChitiet.MA_PTTT = Utils.getInt( txtMA_PTTT.getText() );
            // Integer    = true
            objDvChitiet.MUC_HUONG = Utils.getInt( txtTYLE_TT.getText() );
            // Date       = false
            // objDvChitiet.CUR_DATE = txtCUR_DATE.getText();
            // Integer    = true
            objDvChitiet.TYP = Utils.getInt( txtTYP.getText() );
            // Integer    = true
            objDvChitiet.THANHTOAN = Utils.getInt( txtTHANHTOAN.getText() );
            // Integer    = true
            objDvChitiet.NV_ID = Utils.getInt( txtNV_ID.getText() );
            // String     = false
            objDvChitiet.NV_NAME = txtNV_NAME.getText();
            // Integer    = true
            objDvChitiet.NHOM_DV = Utils.getInt( txtNHOM_DV.getText() );
            // Integer    = true
            objDvChitiet.STS = Utils.getInt( txtSTS.getText() );
            }
        if(DbHelper.checkPhanQuyen(DbHelper.UPDATE, "dv_chitiet")==false){
            logger.info("DON'T HAVE UPDATE RIGHT");
        }
        else if(DbHelper.checkPhanQuyen(DbHelper.INSERT, "dv_chitiet")==false){
            logger.info("DON'T HAVE INSERT RIGHT");
        }        
        else{
            objDvChitiet.insert();
        }
        shell.close();
    }
    
    public void setDvChitietDlgData(DvChitiet obj) {
		this.objDvChitiet = obj;
	}
    
    public void loadDvChitietDlgData(){
        if(intTypeDlgDvChitiet==TYPE_DLG_VIEW){
            btnNewButtonSaveDvChitiet.setEnabled(false);
        }
        else{
            btnNewButtonSaveDvChitiet.setEnabled(true);
        }

        if(DbHelper.checkPhanQuyen(DbHelper.READ, "dv_chitiet")==false){
            logger.info("DON'T HAVE READ RIGHT");
            return;
        }
        if(objDvChitiet != null){
            if(objDvChitiet.BN_ID==null)
                txtBN_ID.setText("");
            else
                txtBN_ID.setText(""+objDvChitiet.BN_ID.toString());
            if(objDvChitiet.MA_LK==null)
                txtMA_LK.setText("");
            else
                txtMA_LK.setText(""+objDvChitiet.MA_LK.toString());
            if(objDvChitiet.DV_ID==null)
                txtDV_ID.setText("");
            else
                txtDV_ID.setText(""+objDvChitiet.DV_ID.toString());
            if(objDvChitiet.MA_DICH_VU==null)
                txtMA_DICH_VU.setText("");
            else
                txtMA_DICH_VU.setText(""+objDvChitiet.MA_DICH_VU.toString());
            if(objDvChitiet.MA_VAT_TU==null)
                txtMA_VAT_TU.setText("");
            else
                txtMA_VAT_TU.setText(""+objDvChitiet.MA_VAT_TU.toString());
            if(objDvChitiet.MA_NHOM==null)
                txtMA_NHOM.setText("");
            else
                txtMA_NHOM.setText(""+objDvChitiet.MA_NHOM.toString());
            if(objDvChitiet.GOI_VTYT==null)
                txtGOI_VTYT.setText("");
            else
                txtGOI_VTYT.setText(""+objDvChitiet.GOI_VTYT.toString());
            if(objDvChitiet.TEN_VAT_TU==null)
                txtTEN_VAT_TU.setText("");
            else
                txtTEN_VAT_TU.setText(""+objDvChitiet.TEN_VAT_TU.toString());
            if(objDvChitiet.TEN_DICH_VU==null)
                txtTEN_DICH_VU.setText("");
            else
                txtTEN_DICH_VU.setText(""+objDvChitiet.TEN_DICH_VU.toString());
            if(objDvChitiet.DON_VI_TINH==null)
                txtDON_VI_TINH.setText("");
            else
                txtDON_VI_TINH.setText(""+objDvChitiet.DON_VI_TINH.toString());
            if(objDvChitiet.PHAM_VI==null)
                txtPHAM_VI.setText("");
            else
                txtPHAM_VI.setText(""+objDvChitiet.PHAM_VI.toString());
            if(objDvChitiet.SO_LUONG==null)
                txtSO_LUONG.setText("");
            else
                txtSO_LUONG.setText(""+objDvChitiet.SO_LUONG.toString());
            if(objDvChitiet.DON_GIA==null)
                txtDON_GIA.setText("");
            else
                txtDON_GIA.setText(""+objDvChitiet.DON_GIA.toString());
            if(objDvChitiet.DON_GIA2==null)
                txtDON_GIA2.setText("");
            else
                txtDON_GIA2.setText(""+objDvChitiet.DON_GIA2.toString());
            if(objDvChitiet.TT_THAU==null)
                txtTT_THAU.setText("");
            else
                txtTT_THAU.setText(""+objDvChitiet.TT_THAU.toString());
            if(objDvChitiet.THANH_TIEN==null)
                txtTHANH_TIEN.setText("");
            else
                txtTHANH_TIEN.setText(""+objDvChitiet.THANH_TIEN.toString());
            if(objDvChitiet.THANH_TIEN2==null)
                txtTHANH_TIEN2.setText("");
            else
                txtTHANH_TIEN2.setText(""+objDvChitiet.THANH_TIEN2.toString());
            if(objDvChitiet.T_TRANTT==null)
                txtT_TRANTT.setText("");
            else
                txtT_TRANTT.setText(""+objDvChitiet.T_TRANTT.toString());
            if(objDvChitiet.TYLE_TT==null)
                txtMUC_HUONG.setText("");
            else
                txtMUC_HUONG.setText(""+objDvChitiet.TYLE_TT.toString());
            if(objDvChitiet.T_NGUONKHAC==null)
                txtT_NGUONKHAC.setText("");
            else
                txtT_NGUONKHAC.setText(""+objDvChitiet.T_NGUONKHAC.toString());
            if(objDvChitiet.TT_BHTT==null)
                txtTT_BHTT.setText("");
            else
                txtTT_BHTT.setText(""+objDvChitiet.TT_BHTT.toString());
            if(objDvChitiet.TT_BNTT==null)
                txtTT_BNTT.setText("");
            else
                txtTT_BNTT.setText(""+objDvChitiet.TT_BNTT.toString());
            if(objDvChitiet.T_BNCCT==null)
                txtT_BNCCT.setText("");
            else
                txtT_BNCCT.setText(""+objDvChitiet.T_BNCCT.toString());
            if(objDvChitiet.T_NGOAIDS==null)
                txtT_NGOAIDS.setText("");
            else
                txtT_NGOAIDS.setText(""+objDvChitiet.T_NGOAIDS.toString());
            if(objDvChitiet.MA_GIUONG==null)
                txtMA_GIUONG.setText("");
            else
                txtMA_GIUONG.setText(""+objDvChitiet.MA_GIUONG.toString());
            if(objDvChitiet.MA_KHOA==null)
                txtMA_KHOA.setText("");
            else
                txtMA_KHOA.setText(""+objDvChitiet.MA_KHOA.toString());
            if(objDvChitiet.MA_BAC_SI==null)
                txtMA_BAC_SI.setText("");
            else
                txtMA_BAC_SI.setText(""+objDvChitiet.MA_BAC_SI.toString());
            if(objDvChitiet.MA_BENH==null)
                txtMA_BENH.setText("");
            else
                txtMA_BENH.setText(""+objDvChitiet.MA_BENH.toString());
            if(objDvChitiet.NGAY_YL==null)
                txtNGAY_YL.setText("");
            else
                txtNGAY_YL.setText(""+objDvChitiet.NGAY_YL.toString());
            if(objDvChitiet.NGAY_KQ==null)
                txtNGAY_KQ.setText("");
            else
                txtNGAY_KQ.setText(""+objDvChitiet.NGAY_KQ.toString());
            if(objDvChitiet.MA_PTTT==null)
                txtMA_PTTT.setText("");
            else
                txtMA_PTTT.setText(""+objDvChitiet.MA_PTTT.toString());
            if(objDvChitiet.MUC_HUONG==null)
                txtTYLE_TT.setText("");
            else
                txtTYLE_TT.setText(""+objDvChitiet.MUC_HUONG.toString());
            if(objDvChitiet.CUR_DATE==null)
                txtCUR_DATE.setText("");
            else
                txtCUR_DATE.setText(""+objDvChitiet.CUR_DATE.toString());
            if(objDvChitiet.TYP==null)
                txtTYP.setText("");
            else
                txtTYP.setText(""+objDvChitiet.TYP.toString());
            if(objDvChitiet.THANHTOAN==null)
                txtTHANHTOAN.setText("");
            else
                txtTHANHTOAN.setText(""+objDvChitiet.THANHTOAN.toString());
            if(objDvChitiet.NV_ID==null)
                txtNV_ID.setText("");
            else
                txtNV_ID.setText(""+objDvChitiet.NV_ID.toString());
            if(objDvChitiet.NV_NAME==null)
                txtNV_NAME.setText("");
            else
                txtNV_NAME.setText(""+objDvChitiet.NV_NAME.toString());
            if(objDvChitiet.NHOM_DV==null)
                txtNHOM_DV.setText("");
            else
                txtNHOM_DV.setText(""+objDvChitiet.NHOM_DV.toString());
            if(objDvChitiet.STS==null)
                txtSTS.setText("");
            else
                txtSTS.setText(""+objDvChitiet.STS.toString());
        }
    }
    
    protected void keyPressDvChitietDlg(KeyEvent e) {
		if(e.keyCode==13){
			saveDvChitietDlg();
		}
		
	}
}
