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
    private Text txtTEN_DICH_VU;
    private Text txtSO_LUONG;
    private Text txtDON_GIA;
    private Text txtDON_GIA2;
    private Text txtTHANH_TIEN;
    private Text txtTT_BH;
    private Text txtTT_NB;
    private Text txtMA_KHOA;
    private Text txtMA_BAC_SI;
    private Text txtMA_BENH;
    private Text txtNGAY_YL;
    private Text txtNGAY_KQ;
    private Text txtMA_PTTT;
    private Text txtTYLE_TT;
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
				keyPressDvChitietDlg(e);
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
            objDvChitiet.TEN_DICH_VU = txtTEN_DICH_VU.getText();
            // Integer    = true
            objDvChitiet.SO_LUONG = Utils.getInt( txtSO_LUONG.getText() );
            // Integer    = true
            objDvChitiet.DON_GIA = Utils.getInt( txtDON_GIA.getText() );
            // Integer    = true
            objDvChitiet.DON_GIA2 = Utils.getInt( txtDON_GIA2.getText() );
            // Integer    = true
            objDvChitiet.THANH_TIEN = Utils.getInt( txtTHANH_TIEN.getText() );
            // Integer    = true
            objDvChitiet.TT_BH = Utils.getInt( txtTT_BH.getText() );
            // Integer    = true
            objDvChitiet.TT_NB = Utils.getInt( txtTT_NB.getText() );
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
            objDvChitiet.TYLE_TT = Utils.getInt( txtTYLE_TT.getText() );
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
            if(objDvChitiet.TEN_DICH_VU==null)
                txtTEN_DICH_VU.setText("");
            else
                txtTEN_DICH_VU.setText(""+objDvChitiet.TEN_DICH_VU.toString());
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
            if(objDvChitiet.THANH_TIEN==null)
                txtTHANH_TIEN.setText("");
            else
                txtTHANH_TIEN.setText(""+objDvChitiet.THANH_TIEN.toString());
            if(objDvChitiet.TT_BH==null)
                txtTT_BH.setText("");
            else
                txtTT_BH.setText(""+objDvChitiet.TT_BH.toString());
            if(objDvChitiet.TT_NB==null)
                txtTT_NB.setText("");
            else
                txtTT_NB.setText(""+objDvChitiet.TT_NB.toString());
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
            if(objDvChitiet.TYLE_TT==null)
                txtTYLE_TT.setText("");
            else
                txtTYLE_TT.setText(""+objDvChitiet.TYLE_TT.toString());
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
