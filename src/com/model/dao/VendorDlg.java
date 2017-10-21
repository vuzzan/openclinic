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

public class VendorDlg extends Dialog {
	static Logger logger = LogManager.getLogger(VendorDlg.class.getName());

	protected Object result;
	protected Shell shell;
    private Text txtV_NAME;
    private Text txtV_ADDR;
    private Text txtV_MST;
    private Text txtV_CONTACT;
    private Text txtV_PHONE;
    private Text txtSTS;

    public Vendor objVendor;
    Button btnNewButtonSaveVendor;

    public int intTypeDlgVendor;
	public static final int TYPE_DLG_VIEW = 1;
	public static final int TYPE_DLG_EDIT = 2;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public VendorDlg(Shell parent, int style) {
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
		shell.setText("VendorDlg EDIT/NEW");
		shell.setLayout(new GridLayout(2, false));
        shell.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.keyCode==SWT.ESC){
					objVendor = null;
				}
			}
		});
        
		Label lbltxtV_NAME = new Label(shell, SWT.NONE);
        lbltxtV_NAME.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtV_NAME.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtV_NAME.setText("V_NAME :");
		
		txtV_NAME = new Text(shell, SWT.BORDER);
        txtV_NAME.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtV_NAME.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtV_NAME.setText("V_NAME");
        txtV_NAME.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressVendorDlg(e);
			}
		});
		Label lbltxtV_ADDR = new Label(shell, SWT.NONE);
        lbltxtV_ADDR.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtV_ADDR.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtV_ADDR.setText("V_ADDR :");
		
		txtV_ADDR = new Text(shell, SWT.BORDER);
        txtV_ADDR.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtV_ADDR.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtV_ADDR.setText("V_ADDR");
        txtV_ADDR.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressVendorDlg(e);
			}
		});
		Label lbltxtV_MST = new Label(shell, SWT.NONE);
        lbltxtV_MST.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtV_MST.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtV_MST.setText("V_MST :");
		
		txtV_MST = new Text(shell, SWT.BORDER);
        txtV_MST.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtV_MST.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtV_MST.setText("V_MST");
        txtV_MST.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressVendorDlg(e);
			}
		});
		Label lbltxtV_CONTACT = new Label(shell, SWT.NONE);
        lbltxtV_CONTACT.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtV_CONTACT.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtV_CONTACT.setText("V_CONTACT :");
		
		txtV_CONTACT = new Text(shell, SWT.BORDER);
        txtV_CONTACT.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtV_CONTACT.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtV_CONTACT.setText("V_CONTACT");
        txtV_CONTACT.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressVendorDlg(e);
			}
		});
		Label lbltxtV_PHONE = new Label(shell, SWT.NONE);
        lbltxtV_PHONE.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtV_PHONE.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtV_PHONE.setText("V_PHONE :");
		
		txtV_PHONE = new Text(shell, SWT.BORDER);
        txtV_PHONE.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtV_PHONE.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtV_PHONE.setText("V_PHONE");
        txtV_PHONE.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressVendorDlg(e);
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
				keyPressVendorDlg(e);
			}
		});
		new Label(shell, SWT.NONE);
		btnNewButtonSaveVendor = new Button(shell, SWT.NONE);
		btnNewButtonSaveVendor.setImage(SWTResourceManager.getImage(VendorDlg.class, "/png/file-2x.png"));
        btnNewButtonSaveVendor.setFont(SWTResourceManager.getFont("Tahoma", 12, SWT.BOLD));
		btnNewButtonSaveVendor.setText("Save");
        
        btnNewButtonSaveVendor.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
                saveVendorDlg();
			}
		});
        
        loadVendorDlgData();
	}

    private void saveVendorDlg(){
        if(intTypeDlgVendor==TYPE_DLG_VIEW){
            return;
        }
        
        if(objVendor == null){
            objVendor = new Vendor();
        }
        if(objVendor!=null){
        // String     = false
            objVendor.V_NAME = txtV_NAME.getText();
            // String     = false
            objVendor.V_ADDR = txtV_ADDR.getText();
            // String     = false
            objVendor.V_MST = txtV_MST.getText();
            // String     = false
            objVendor.V_CONTACT = txtV_CONTACT.getText();
            // String     = false
            objVendor.V_PHONE = txtV_PHONE.getText();
            // Integer    = true
            objVendor.STS = Utils.getInt( txtSTS.getText() );
            }
        if(DbHelper.checkPhanQuyen(DbHelper.UPDATE, "vendor")==false){
            logger.info("DON'T HAVE UPDATE RIGHT");
        }
        else if(DbHelper.checkPhanQuyen(DbHelper.INSERT, "vendor")==false){
            logger.info("DON'T HAVE INSERT RIGHT");
        }        
        else{
            objVendor.insert();
        }
        shell.close();
    }
    
    public void setVendorDlgData(Vendor obj) {
		this.objVendor = obj;
	}
    
    public void loadVendorDlgData(){
        if(intTypeDlgVendor==TYPE_DLG_VIEW){
            btnNewButtonSaveVendor.setEnabled(false);
        }
        else{
            btnNewButtonSaveVendor.setEnabled(true);
        }

        if(DbHelper.checkPhanQuyen(DbHelper.READ, "vendor")==false){
            logger.info("DON'T HAVE READ RIGHT");
            return;
        }
        if(objVendor != null){
            if(objVendor.V_NAME==null)
                txtV_NAME.setText("");
            else
                txtV_NAME.setText(""+objVendor.V_NAME.toString());
            if(objVendor.V_ADDR==null)
                txtV_ADDR.setText("");
            else
                txtV_ADDR.setText(""+objVendor.V_ADDR.toString());
            if(objVendor.V_MST==null)
                txtV_MST.setText("");
            else
                txtV_MST.setText(""+objVendor.V_MST.toString());
            if(objVendor.V_CONTACT==null)
                txtV_CONTACT.setText("");
            else
                txtV_CONTACT.setText(""+objVendor.V_CONTACT.toString());
            if(objVendor.V_PHONE==null)
                txtV_PHONE.setText("");
            else
                txtV_PHONE.setText(""+objVendor.V_PHONE.toString());
            if(objVendor.STS==null)
                txtSTS.setText("");
            else
                txtSTS.setText(""+objVendor.STS.toString());
        }
    }
    
    protected void keyPressVendorDlg(KeyEvent e) {
		if(e.keyCode==13){
			saveVendorDlg();
		}
		
	}
}
