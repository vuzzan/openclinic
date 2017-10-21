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
    private Text txtVENDOR_NAME;
    private Text txtNGAY_NHAP;
    private Text txtTENKHO;
    private Text txtHOADON;
    private Text txtTONGCONG;
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
		Label lbltxtHOADON = new Label(shell, SWT.NONE);
        lbltxtHOADON.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtHOADON.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtHOADON.setText("HOADON :");
		
		txtHOADON = new Text(shell, SWT.BORDER);
        txtHOADON.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtHOADON.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtHOADON.setText("HOADON");
        txtHOADON.addKeyListener(new KeyAdapter() {
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
            objNhapThuoc.VENDOR_NAME = txtVENDOR_NAME.getText();
            // Date       = false
            // objNhapThuoc.NGAY_NHAP = txtNGAY_NHAP.getText();
            // String     = false
            objNhapThuoc.TENKHO = txtTENKHO.getText();
            // String     = false
            objNhapThuoc.HOADON = txtHOADON.getText();
            // Integer    = true
            objNhapThuoc.TONGCONG = Utils.getInt( txtTONGCONG.getText() );
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
            if(objNhapThuoc.VENDOR_NAME==null)
                txtVENDOR_NAME.setText("");
            else
                txtVENDOR_NAME.setText(""+objNhapThuoc.VENDOR_NAME.toString());
            if(objNhapThuoc.NGAY_NHAP==null)
                txtNGAY_NHAP.setText("");
            else
                txtNGAY_NHAP.setText(""+objNhapThuoc.NGAY_NHAP.toString());
            if(objNhapThuoc.TENKHO==null)
                txtTENKHO.setText("");
            else
                txtTENKHO.setText(""+objNhapThuoc.TENKHO.toString());
            if(objNhapThuoc.HOADON==null)
                txtHOADON.setText("");
            else
                txtHOADON.setText(""+objNhapThuoc.HOADON.toString());
            if(objNhapThuoc.TONGCONG==null)
                txtTONGCONG.setText("");
            else
                txtTONGCONG.setText(""+objNhapThuoc.TONGCONG.toString());
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
