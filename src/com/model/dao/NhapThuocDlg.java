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

public class NhapThuocDlg extends Dialog {
	static Logger logger = LogManager.getLogger(NhapThuocDlg.class.getName());

	protected Object result;
	protected Shell shell;
    private Text txtV_ID;
    private Text txtKHO_ID;
    private Text txtTENKHO;
    private Text txtFROM_KHOID;
    private Text txtVENDOR_NAME;
    private Text txtVENDOR_ADDR;
    private Text txtNGAY_NHAP;
    private Text txtNGAY_HD;
    private Text txtSO_HOA_DON;
    private Text txtKH_HOA_DON;
    private Text txtTONGCONG;
    private Text txtTONGCONG_VAT;
    private Text txtVAT;
    private Text txtSTS;

    public NhapThuoc objNhapThuoc;
    Button btnNewButtonSaveNhapThuoc;

    public int intTypeDlgNhapThuoc;
	public static final int TYPE_DLG_VIEW = 1;
	public static final int TYPE_DLG_EDIT = 2;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public NhapThuocDlg(Shell parent, int style) {
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
		shell.setText("NhapThuocDlg EDIT/NEW");
		shell.setLayout(new GridLayout(2, false));
        shell.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.keyCode==SWT.ESC){
					objNhapThuoc = null;
				}
			}
		});
        
		Label lbltxtV_ID = new Label(shell, SWT.NONE);
        lbltxtV_ID.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtV_ID.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtV_ID.setText("V_ID :");
		
		txtV_ID = new Text(shell, SWT.BORDER);
        txtV_ID.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtV_ID.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtV_ID.setText("V_ID");
        txtV_ID.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressNhapThuocDlg(e);
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
				keyPressNhapThuocDlg(e);
			}
		});
		Label lbltxtTENKHO = new Label(shell, SWT.NONE);
        lbltxtTENKHO.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtTENKHO.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtTENKHO.setText("TENKHO :");
		
		txtTENKHO = new Text(shell, SWT.BORDER);
        txtTENKHO.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtTENKHO.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtTENKHO.setText("TENKHO");
        txtTENKHO.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressNhapThuocDlg(e);
			}
		});
		Label lbltxtFROM_KHOID = new Label(shell, SWT.NONE);
        lbltxtFROM_KHOID.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtFROM_KHOID.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtFROM_KHOID.setText("FROM_KHOID :");
		
		txtFROM_KHOID = new Text(shell, SWT.BORDER);
        txtFROM_KHOID.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtFROM_KHOID.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtFROM_KHOID.setText("FROM_KHOID");
        txtFROM_KHOID.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressNhapThuocDlg(e);
			}
		});
		Label lbltxtVENDOR_NAME = new Label(shell, SWT.NONE);
        lbltxtVENDOR_NAME.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtVENDOR_NAME.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtVENDOR_NAME.setText("VENDOR_NAME :");
		
		txtVENDOR_NAME = new Text(shell, SWT.BORDER);
        txtVENDOR_NAME.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtVENDOR_NAME.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtVENDOR_NAME.setText("VENDOR_NAME");
        txtVENDOR_NAME.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressNhapThuocDlg(e);
			}
		});
		Label lbltxtVENDOR_ADDR = new Label(shell, SWT.NONE);
        lbltxtVENDOR_ADDR.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtVENDOR_ADDR.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtVENDOR_ADDR.setText("VENDOR_ADDR :");
		
		txtVENDOR_ADDR = new Text(shell, SWT.BORDER);
        txtVENDOR_ADDR.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtVENDOR_ADDR.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtVENDOR_ADDR.setText("VENDOR_ADDR");
        txtVENDOR_ADDR.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressNhapThuocDlg(e);
			}
		});
		Label lbltxtNGAY_NHAP = new Label(shell, SWT.NONE);
        lbltxtNGAY_NHAP.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtNGAY_NHAP.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtNGAY_NHAP.setText("NGAY_NHAP :");
		
		txtNGAY_NHAP = new Text(shell, SWT.BORDER);
        txtNGAY_NHAP.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtNGAY_NHAP.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtNGAY_NHAP.setText("NGAY_NHAP");
        txtNGAY_NHAP.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressNhapThuocDlg(e);
			}
		});
		Label lbltxtNGAY_HD = new Label(shell, SWT.NONE);
        lbltxtNGAY_HD.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtNGAY_HD.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtNGAY_HD.setText("NGAY_HD :");
		
		txtNGAY_HD = new Text(shell, SWT.BORDER);
        txtNGAY_HD.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtNGAY_HD.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtNGAY_HD.setText("NGAY_HD");
        txtNGAY_HD.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressNhapThuocDlg(e);
			}
		});
		Label lbltxtSO_HOA_DON = new Label(shell, SWT.NONE);
        lbltxtSO_HOA_DON.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtSO_HOA_DON.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtSO_HOA_DON.setText("SO_HOA_DON :");
		
		txtSO_HOA_DON = new Text(shell, SWT.BORDER);
        txtSO_HOA_DON.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtSO_HOA_DON.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtSO_HOA_DON.setText("SO_HOA_DON");
        txtSO_HOA_DON.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressNhapThuocDlg(e);
			}
		});
		Label lbltxtKH_HOA_DON = new Label(shell, SWT.NONE);
        lbltxtKH_HOA_DON.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtKH_HOA_DON.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtKH_HOA_DON.setText("KH_HOA_DON :");
		
		txtKH_HOA_DON = new Text(shell, SWT.BORDER);
        txtKH_HOA_DON.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtKH_HOA_DON.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtKH_HOA_DON.setText("KH_HOA_DON");
        txtKH_HOA_DON.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressNhapThuocDlg(e);
			}
		});
		Label lbltxtTONGCONG = new Label(shell, SWT.NONE);
        lbltxtTONGCONG.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtTONGCONG.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtTONGCONG.setText("TONGCONG :");
		
		txtTONGCONG = new Text(shell, SWT.BORDER);
        txtTONGCONG.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtTONGCONG.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtTONGCONG.setText("TONGCONG");
        txtTONGCONG.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressNhapThuocDlg(e);
			}
		});
		Label lbltxtTONGCONG_VAT = new Label(shell, SWT.NONE);
        lbltxtTONGCONG_VAT.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtTONGCONG_VAT.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtTONGCONG_VAT.setText("TONGCONG_VAT :");
		
		txtTONGCONG_VAT = new Text(shell, SWT.BORDER);
        txtTONGCONG_VAT.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtTONGCONG_VAT.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtTONGCONG_VAT.setText("TONGCONG_VAT");
        txtTONGCONG_VAT.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressNhapThuocDlg(e);
			}
		});
		Label lbltxtVAT = new Label(shell, SWT.NONE);
        lbltxtVAT.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtVAT.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtVAT.setText("VAT :");
		
		txtVAT = new Text(shell, SWT.BORDER);
        txtVAT.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtVAT.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtVAT.setText("VAT");
        txtVAT.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressNhapThuocDlg(e);
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
				keyPressNhapThuocDlg(e);
			}
		});
		new Label(shell, SWT.NONE);
		btnNewButtonSaveNhapThuoc = new Button(shell, SWT.NONE);
		btnNewButtonSaveNhapThuoc.setImage(SWTResourceManager.getImage(NhapThuocDlg.class, "/png/file-2x.png"));
        btnNewButtonSaveNhapThuoc.setFont(SWTResourceManager.getFont("Tahoma", 12, SWT.BOLD));
		btnNewButtonSaveNhapThuoc.setText("Save");
        
        btnNewButtonSaveNhapThuoc.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
                saveNhapThuocDlg();
			}
		});
        
        loadNhapThuocDlgData();
	}

    private void saveNhapThuocDlg(){
        if(intTypeDlgNhapThuoc==TYPE_DLG_VIEW){
            return;
        }
        
        if(objNhapThuoc == null){
            objNhapThuoc = new NhapThuoc();
        }
        if(objNhapThuoc!=null){
        // Integer    = true
            objNhapThuoc.V_ID = Utils.getInt( txtV_ID.getText() );
            // Integer    = true
            objNhapThuoc.KHO_ID = Utils.getInt( txtKHO_ID.getText() );
            // String     = false
            objNhapThuoc.TENKHO = txtTENKHO.getText();
            // Integer    = true
            objNhapThuoc.FROM_KHOID = Utils.getInt( txtFROM_KHOID.getText() );
            // String     = false
            objNhapThuoc.VENDOR_NAME = txtVENDOR_NAME.getText();
            // String     = false
            objNhapThuoc.VENDOR_ADDR = txtVENDOR_ADDR.getText();
            // Date       = false
            // objNhapThuoc.NGAY_NHAP = txtNGAY_NHAP.getText();
            // Date       = false
            // objNhapThuoc.NGAY_HD = txtNGAY_HD.getText();
            // String     = false
            objNhapThuoc.SO_HOA_DON = txtSO_HOA_DON.getText();
            // String     = false
            objNhapThuoc.KH_HOA_DON = txtKH_HOA_DON.getText();
            // Integer    = true
            objNhapThuoc.TONGCONG = Utils.getInt( txtTONGCONG.getText() );
            // Integer    = true
            objNhapThuoc.TONGCONG_VAT = Utils.getInt( txtTONGCONG_VAT.getText() );
            // Integer    = true
            objNhapThuoc.VAT = Utils.getInt( txtVAT.getText() );
            // Integer    = true
            objNhapThuoc.STS = Utils.getInt( txtSTS.getText() );
            }
        if(DbHelper.checkPhanQuyen(DbHelper.UPDATE, "nhap_thuoc")==false){
            logger.info("DON'T HAVE UPDATE RIGHT");
        }
        else if(DbHelper.checkPhanQuyen(DbHelper.INSERT, "nhap_thuoc")==false){
            logger.info("DON'T HAVE INSERT RIGHT");
        }        
        else{
            objNhapThuoc.insert();
        }
        shell.close();
    }
    
    public void setNhapThuocDlgData(NhapThuoc obj) {
		this.objNhapThuoc = obj;
	}
    
    public void loadNhapThuocDlgData(){
        if(intTypeDlgNhapThuoc==TYPE_DLG_VIEW){
            btnNewButtonSaveNhapThuoc.setEnabled(false);
        }
        else{
            btnNewButtonSaveNhapThuoc.setEnabled(true);
        }

        if(DbHelper.checkPhanQuyen(DbHelper.READ, "nhap_thuoc")==false){
            logger.info("DON'T HAVE READ RIGHT");
            return;
        }
        if(objNhapThuoc != null){
            if(objNhapThuoc.V_ID==null)
                txtV_ID.setText("");
            else
                txtV_ID.setText(""+objNhapThuoc.V_ID.toString());
            if(objNhapThuoc.KHO_ID==null)
                txtKHO_ID.setText("");
            else
                txtKHO_ID.setText(""+objNhapThuoc.KHO_ID.toString());
            if(objNhapThuoc.TENKHO==null)
                txtTENKHO.setText("");
            else
                txtTENKHO.setText(""+objNhapThuoc.TENKHO.toString());
            if(objNhapThuoc.FROM_KHOID==null)
                txtFROM_KHOID.setText("");
            else
                txtFROM_KHOID.setText(""+objNhapThuoc.FROM_KHOID.toString());
            if(objNhapThuoc.VENDOR_NAME==null)
                txtVENDOR_NAME.setText("");
            else
                txtVENDOR_NAME.setText(""+objNhapThuoc.VENDOR_NAME.toString());
            if(objNhapThuoc.VENDOR_ADDR==null)
                txtVENDOR_ADDR.setText("");
            else
                txtVENDOR_ADDR.setText(""+objNhapThuoc.VENDOR_ADDR.toString());
            if(objNhapThuoc.NGAY_NHAP==null)
                txtNGAY_NHAP.setText("");
            else
                txtNGAY_NHAP.setText(""+objNhapThuoc.NGAY_NHAP.toString());
            if(objNhapThuoc.NGAY_HD==null)
                txtNGAY_HD.setText("");
            else
                txtNGAY_HD.setText(""+objNhapThuoc.NGAY_HD.toString());
            if(objNhapThuoc.SO_HOA_DON==null)
                txtSO_HOA_DON.setText("");
            else
                txtSO_HOA_DON.setText(""+objNhapThuoc.SO_HOA_DON.toString());
            if(objNhapThuoc.KH_HOA_DON==null)
                txtKH_HOA_DON.setText("");
            else
                txtKH_HOA_DON.setText(""+objNhapThuoc.KH_HOA_DON.toString());
            if(objNhapThuoc.TONGCONG==null)
                txtTONGCONG.setText("");
            else
                txtTONGCONG.setText(""+objNhapThuoc.TONGCONG.toString());
            if(objNhapThuoc.TONGCONG_VAT==null)
                txtTONGCONG_VAT.setText("");
            else
                txtTONGCONG_VAT.setText(""+objNhapThuoc.TONGCONG_VAT.toString());
            if(objNhapThuoc.VAT==null)
                txtVAT.setText("");
            else
                txtVAT.setText(""+objNhapThuoc.VAT.toString());
            if(objNhapThuoc.STS==null)
                txtSTS.setText("");
            else
                txtSTS.setText(""+objNhapThuoc.STS.toString());
        }
    }
    
    protected void keyPressNhapThuocDlg(KeyEvent e) {
		if(e.keyCode==13){
			saveNhapThuocDlg();
		}
		
	}
}
