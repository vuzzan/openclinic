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

public class DonviTinhDlg extends Dialog {
	static Logger logger = LogManager.getLogger(DonviTinhDlg.class.getName());

	protected Object result;
	protected Shell shell;
    private Text txtDVT_NAME;

    public DonviTinh objDonviTinh;
    Button btnNewButtonSaveDonviTinh;

    public int intTypeDlgDonviTinh;
	public static final int TYPE_DLG_VIEW = 1;
	public static final int TYPE_DLG_EDIT = 2;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public DonviTinhDlg(Shell parent, int style) {
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
		shell.setText("DonviTinhDlg EDIT/NEW");
		shell.setLayout(new GridLayout(2, false));
        shell.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.keyCode==SWT.ESC){
					objDonviTinh = null;
				}
			}
		});
        
		Label lbltxtDVT_NAME = new Label(shell, SWT.NONE);
        lbltxtDVT_NAME.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtDVT_NAME.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtDVT_NAME.setText("DVT_NAME :");
		
		txtDVT_NAME = new Text(shell, SWT.BORDER);
        txtDVT_NAME.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtDVT_NAME.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtDVT_NAME.setText("DVT_NAME");
        txtDVT_NAME.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressDonviTinhDlg(e);
			}
		});
		new Label(shell, SWT.NONE);
		btnNewButtonSaveDonviTinh = new Button(shell, SWT.NONE);
		btnNewButtonSaveDonviTinh.setImage(SWTResourceManager.getImage(DonviTinhDlg.class, "/png/file-2x.png"));
        btnNewButtonSaveDonviTinh.setFont(SWTResourceManager.getFont("Tahoma", 12, SWT.BOLD));
		btnNewButtonSaveDonviTinh.setText("Save");
        
        btnNewButtonSaveDonviTinh.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
                saveDonviTinhDlg();
			}
		});
        
        loadDonviTinhDlgData();
	}

    private void saveDonviTinhDlg(){
        if(intTypeDlgDonviTinh==TYPE_DLG_VIEW){
            return;
        }
        
        if(objDonviTinh == null){
            objDonviTinh = new DonviTinh();
        }
        if(objDonviTinh!=null){
        // String     = false
            objDonviTinh.DVT_NAME = txtDVT_NAME.getText();
            }
        if(DbHelper.checkPhanQuyen(DbHelper.UPDATE, "donvi_tinh")==false){
            logger.info("DON'T HAVE UPDATE RIGHT");
        }
        else if(DbHelper.checkPhanQuyen(DbHelper.INSERT, "donvi_tinh")==false){
            logger.info("DON'T HAVE INSERT RIGHT");
        }        
        else{
            objDonviTinh.insert();
        }
        shell.close();
    }
    
    public void setDonviTinhDlgData(DonviTinh obj) {
		this.objDonviTinh = obj;
	}
    
    public void loadDonviTinhDlgData(){
        if(intTypeDlgDonviTinh==TYPE_DLG_VIEW){
            btnNewButtonSaveDonviTinh.setEnabled(false);
        }
        else{
            btnNewButtonSaveDonviTinh.setEnabled(true);
        }

        if(DbHelper.checkPhanQuyen(DbHelper.READ, "donvi_tinh")==false){
            logger.info("DON'T HAVE READ RIGHT");
            return;
        }
        if(objDonviTinh != null){
            if(objDonviTinh.DVT_NAME==null)
                txtDVT_NAME.setText("");
            else
                txtDVT_NAME.setText(""+objDonviTinh.DVT_NAME.toString());
        }
    }
    
    protected void keyPressDonviTinhDlg(KeyEvent e) {
		if(e.keyCode==13){
			saveDonviTinhDlg();
		}
		
	}
}
