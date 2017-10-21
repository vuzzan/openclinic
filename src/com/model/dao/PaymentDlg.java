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

public class PaymentDlg extends Dialog {
	static Logger logger = LogManager.getLogger(PaymentDlg.class.getName());

	protected Object result;
	protected Shell shell;
    private Text txtHOADON;
    private Text txtTONGCONG;
    private Text txtV_ID;
    private Text txtNGAY_GIO;
    private Text txtSTS;

    public Payment objPayment;
    Button btnNewButtonSavePayment;

    public int intTypeDlgPayment;
	public static final int TYPE_DLG_VIEW = 1;
	public static final int TYPE_DLG_EDIT = 2;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public PaymentDlg(Shell parent, int style) {
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
		shell.setText("PaymentDlg EDIT/NEW");
		shell.setLayout(new GridLayout(2, false));
        shell.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.keyCode==SWT.ESC){
					objPayment = null;
				}
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
				keyPressPaymentDlg(e);
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
				keyPressPaymentDlg(e);
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
				keyPressPaymentDlg(e);
			}
		});
		Label lbltxtNGAY_GIO = new Label(shell, SWT.NONE);
        lbltxtNGAY_GIO.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtNGAY_GIO.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtNGAY_GIO.setText("NGAY_GIO :");
		
		txtNGAY_GIO = new Text(shell, SWT.BORDER);
        txtNGAY_GIO.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtNGAY_GIO.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtNGAY_GIO.setText("NGAY_GIO");
        txtNGAY_GIO.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressPaymentDlg(e);
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
				keyPressPaymentDlg(e);
			}
		});
		new Label(shell, SWT.NONE);
		btnNewButtonSavePayment = new Button(shell, SWT.NONE);
		btnNewButtonSavePayment.setImage(SWTResourceManager.getImage(PaymentDlg.class, "/png/file-2x.png"));
        btnNewButtonSavePayment.setFont(SWTResourceManager.getFont("Tahoma", 12, SWT.BOLD));
		btnNewButtonSavePayment.setText("Save");
        
        btnNewButtonSavePayment.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
                savePaymentDlg();
			}
		});
        
        loadPaymentDlgData();
	}

    private void savePaymentDlg(){
        if(intTypeDlgPayment==TYPE_DLG_VIEW){
            return;
        }
        
        if(objPayment == null){
            objPayment = new Payment();
        }
        if(objPayment!=null){
        // String     = false
            objPayment.HOADON = txtHOADON.getText();
            // Integer    = true
            objPayment.TONGCONG = Utils.getInt( txtTONGCONG.getText() );
            // Integer    = true
            objPayment.V_ID = Utils.getInt( txtV_ID.getText() );
            // Date       = false
            // objPayment.NGAY_GIO = txtNGAY_GIO.getText();
            // Integer    = true
            objPayment.STS = Utils.getInt( txtSTS.getText() );
            }
        if(DbHelper.checkPhanQuyen(DbHelper.UPDATE, "payment")==false){
            logger.info("DON'T HAVE UPDATE RIGHT");
        }
        else if(DbHelper.checkPhanQuyen(DbHelper.INSERT, "payment")==false){
            logger.info("DON'T HAVE INSERT RIGHT");
        }        
        else{
            objPayment.insert();
        }
        shell.close();
    }
    
    public void setPaymentDlgData(Payment obj) {
		this.objPayment = obj;
	}
    
    public void loadPaymentDlgData(){
        if(intTypeDlgPayment==TYPE_DLG_VIEW){
            btnNewButtonSavePayment.setEnabled(false);
        }
        else{
            btnNewButtonSavePayment.setEnabled(true);
        }

        if(DbHelper.checkPhanQuyen(DbHelper.READ, "payment")==false){
            logger.info("DON'T HAVE READ RIGHT");
            return;
        }
        if(objPayment != null){
            if(objPayment.HOADON==null)
                txtHOADON.setText("");
            else
                txtHOADON.setText(""+objPayment.HOADON.toString());
            if(objPayment.TONGCONG==null)
                txtTONGCONG.setText("");
            else
                txtTONGCONG.setText(""+objPayment.TONGCONG.toString());
            if(objPayment.V_ID==null)
                txtV_ID.setText("");
            else
                txtV_ID.setText(""+objPayment.V_ID.toString());
            if(objPayment.NGAY_GIO==null)
                txtNGAY_GIO.setText("");
            else
                txtNGAY_GIO.setText(""+objPayment.NGAY_GIO.toString());
            if(objPayment.STS==null)
                txtSTS.setText("");
            else
                txtSTS.setText(""+objPayment.STS.toString());
        }
    }
    
    protected void keyPressPaymentDlg(KeyEvent e) {
		if(e.keyCode==13){
			savePaymentDlg();
		}
		
	}
}
