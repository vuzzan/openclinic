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

public class PhanLoaiThuocDlg extends Dialog {
	static Logger logger = LogManager.getLogger(PhanLoaiThuocDlg.class.getName());

	protected Object result;
	protected Shell shell;
    private Text txtPL_CODE;
    private Text txtPL_NAME;

    public PhanLoaiThuoc objPhanLoaiThuoc;
    Button btnNewButtonSavePhanLoaiThuoc;

    public int intTypeDlgPhanLoaiThuoc;
	public static final int TYPE_DLG_VIEW = 1;
	public static final int TYPE_DLG_EDIT = 2;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public PhanLoaiThuocDlg(Shell parent, int style) {
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
		shell.setText("PhanLoaiThuocDlg EDIT/NEW");
		shell.setLayout(new GridLayout(2, false));
        shell.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.keyCode==SWT.ESC){
					objPhanLoaiThuoc = null;
				}
			}
		});
        
		Label lbltxtPL_CODE = new Label(shell, SWT.NONE);
        lbltxtPL_CODE.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtPL_CODE.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtPL_CODE.setText("PL_CODE :");
		
		txtPL_CODE = new Text(shell, SWT.BORDER);
        txtPL_CODE.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtPL_CODE.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtPL_CODE.setText("PL_CODE");
        txtPL_CODE.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressPhanLoaiThuocDlg(e);
			}
		});
		Label lbltxtPL_NAME = new Label(shell, SWT.NONE);
        lbltxtPL_NAME.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtPL_NAME.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtPL_NAME.setText("PL_NAME :");
		
		txtPL_NAME = new Text(shell, SWT.BORDER);
        txtPL_NAME.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtPL_NAME.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtPL_NAME.setText("PL_NAME");
        txtPL_NAME.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressPhanLoaiThuocDlg(e);
			}
		});
		new Label(shell, SWT.NONE);
		btnNewButtonSavePhanLoaiThuoc = new Button(shell, SWT.NONE);
		btnNewButtonSavePhanLoaiThuoc.setImage(SWTResourceManager.getImage(PhanLoaiThuocDlg.class, "/png/file-2x.png"));
        btnNewButtonSavePhanLoaiThuoc.setFont(SWTResourceManager.getFont("Tahoma", 12, SWT.BOLD));
		btnNewButtonSavePhanLoaiThuoc.setText("Save");
        
        btnNewButtonSavePhanLoaiThuoc.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
                savePhanLoaiThuocDlg();
			}
		});
        
        loadPhanLoaiThuocDlgData();
	}

    private void savePhanLoaiThuocDlg(){
        if(intTypeDlgPhanLoaiThuoc==TYPE_DLG_VIEW){
            return;
        }
        
        if(objPhanLoaiThuoc == null){
            objPhanLoaiThuoc = new PhanLoaiThuoc();
        }
        if(objPhanLoaiThuoc!=null){
        // String     = false
            objPhanLoaiThuoc.PL_CODE = txtPL_CODE.getText();
            // String     = false
            objPhanLoaiThuoc.PL_NAME = txtPL_NAME.getText();
            }
        if(DbHelper.checkPhanQuyen(DbHelper.UPDATE, "phan_loai_thuoc")==false){
            logger.info("DON'T HAVE UPDATE RIGHT");
        }
        else if(DbHelper.checkPhanQuyen(DbHelper.INSERT, "phan_loai_thuoc")==false){
            logger.info("DON'T HAVE INSERT RIGHT");
        }        
        else{
            objPhanLoaiThuoc.insert();
        }
        shell.close();
    }
    
    public void setPhanLoaiThuocDlgData(PhanLoaiThuoc obj) {
		this.objPhanLoaiThuoc = obj;
	}
    
    public void loadPhanLoaiThuocDlgData(){
        if(intTypeDlgPhanLoaiThuoc==TYPE_DLG_VIEW){
            btnNewButtonSavePhanLoaiThuoc.setEnabled(false);
        }
        else{
            btnNewButtonSavePhanLoaiThuoc.setEnabled(true);
        }

        if(DbHelper.checkPhanQuyen(DbHelper.READ, "phan_loai_thuoc")==false){
            logger.info("DON'T HAVE READ RIGHT");
            return;
        }
        if(objPhanLoaiThuoc != null){
            if(objPhanLoaiThuoc.PL_CODE==null)
                txtPL_CODE.setText("");
            else
                txtPL_CODE.setText(""+objPhanLoaiThuoc.PL_CODE.toString());
            if(objPhanLoaiThuoc.PL_NAME==null)
                txtPL_NAME.setText("");
            else
                txtPL_NAME.setText(""+objPhanLoaiThuoc.PL_NAME.toString());
        }
    }
    
    protected void keyPressPhanLoaiThuocDlg(KeyEvent e) {
		if(e.keyCode==13){
			savePhanLoaiThuocDlg();
		}
		
	}
}
