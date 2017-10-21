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

public class KhoaPhongDlg extends Dialog {
	static Logger logger = LogManager.getLogger(KhoaPhongDlg.class.getName());

	protected Object result;
	protected Shell shell;
    private Text txtKP_MABH;
    private Text txtKP_MAKHOA;
    private Text txtKP_NAME;
    private Text txtKP_SOPHONG;

    public KhoaPhong objKhoaPhong;
    Button btnNewButtonSaveKhoaPhong;

    public int intTypeDlgKhoaPhong;
	public static final int TYPE_DLG_VIEW = 1;
	public static final int TYPE_DLG_EDIT = 2;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public KhoaPhongDlg(Shell parent, int style) {
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
		shell.setText("KhoaPhongDlg EDIT/NEW");
		shell.setLayout(new GridLayout(2, false));
        shell.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.keyCode==SWT.ESC){
					objKhoaPhong = null;
				}
			}
		});
        
		Label lbltxtKP_MABH = new Label(shell, SWT.NONE);
        lbltxtKP_MABH.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtKP_MABH.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtKP_MABH.setText("KP_MABH :");
		
		txtKP_MABH = new Text(shell, SWT.BORDER);
        txtKP_MABH.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtKP_MABH.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtKP_MABH.setText("KP_MABH");
        txtKP_MABH.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressKhoaPhongDlg(e);
			}
		});
		Label lbltxtKP_MAKHOA = new Label(shell, SWT.NONE);
        lbltxtKP_MAKHOA.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtKP_MAKHOA.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtKP_MAKHOA.setText("KP_MAKHOA :");
		
		txtKP_MAKHOA = new Text(shell, SWT.BORDER);
        txtKP_MAKHOA.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtKP_MAKHOA.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtKP_MAKHOA.setText("KP_MAKHOA");
        txtKP_MAKHOA.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressKhoaPhongDlg(e);
			}
		});
		Label lbltxtKP_NAME = new Label(shell, SWT.NONE);
        lbltxtKP_NAME.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtKP_NAME.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtKP_NAME.setText("KP_NAME :");
		
		txtKP_NAME = new Text(shell, SWT.BORDER);
        txtKP_NAME.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtKP_NAME.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtKP_NAME.setText("KP_NAME");
        txtKP_NAME.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressKhoaPhongDlg(e);
			}
		});
		Label lbltxtKP_SOPHONG = new Label(shell, SWT.NONE);
        lbltxtKP_SOPHONG.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtKP_SOPHONG.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtKP_SOPHONG.setText("KP_SOPHONG :");
		
		txtKP_SOPHONG = new Text(shell, SWT.BORDER);
        txtKP_SOPHONG.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtKP_SOPHONG.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtKP_SOPHONG.setText("KP_SOPHONG");
        txtKP_SOPHONG.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressKhoaPhongDlg(e);
			}
		});
		new Label(shell, SWT.NONE);
		btnNewButtonSaveKhoaPhong = new Button(shell, SWT.NONE);
		btnNewButtonSaveKhoaPhong.setImage(SWTResourceManager.getImage(KhoaPhongDlg.class, "/png/file-2x.png"));
        btnNewButtonSaveKhoaPhong.setFont(SWTResourceManager.getFont("Tahoma", 12, SWT.BOLD));
		btnNewButtonSaveKhoaPhong.setText("Save");
        
        btnNewButtonSaveKhoaPhong.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
                saveKhoaPhongDlg();
			}
		});
        
        loadKhoaPhongDlgData();
	}

    private void saveKhoaPhongDlg(){
        if(intTypeDlgKhoaPhong==TYPE_DLG_VIEW){
            return;
        }
        
        if(objKhoaPhong == null){
            objKhoaPhong = new KhoaPhong();
        }
        if(objKhoaPhong!=null){
        // String     = false
            objKhoaPhong.KP_MABH = txtKP_MABH.getText();
            // String     = false
            objKhoaPhong.KP_MAKHOA = txtKP_MAKHOA.getText();
            // String     = false
            objKhoaPhong.KP_NAME = txtKP_NAME.getText();
            // String     = false
            objKhoaPhong.KP_SOPHONG = txtKP_SOPHONG.getText();
            }
        if(DbHelper.checkPhanQuyen(DbHelper.UPDATE, "khoa_phong")==false){
            logger.info("DON'T HAVE UPDATE RIGHT");
        }
        else if(DbHelper.checkPhanQuyen(DbHelper.INSERT, "khoa_phong")==false){
            logger.info("DON'T HAVE INSERT RIGHT");
        }        
        else{
            objKhoaPhong.insert();
        }
        shell.close();
    }
    
    public void setKhoaPhongDlgData(KhoaPhong obj) {
		this.objKhoaPhong = obj;
	}
    
    public void loadKhoaPhongDlgData(){
        if(intTypeDlgKhoaPhong==TYPE_DLG_VIEW){
            btnNewButtonSaveKhoaPhong.setEnabled(false);
        }
        else{
            btnNewButtonSaveKhoaPhong.setEnabled(true);
        }

        if(DbHelper.checkPhanQuyen(DbHelper.READ, "khoa_phong")==false){
            logger.info("DON'T HAVE READ RIGHT");
            return;
        }
        if(objKhoaPhong != null){
            if(objKhoaPhong.KP_MABH==null)
                txtKP_MABH.setText("");
            else
                txtKP_MABH.setText(""+objKhoaPhong.KP_MABH.toString());
            if(objKhoaPhong.KP_MAKHOA==null)
                txtKP_MAKHOA.setText("");
            else
                txtKP_MAKHOA.setText(""+objKhoaPhong.KP_MAKHOA.toString());
            if(objKhoaPhong.KP_NAME==null)
                txtKP_NAME.setText("");
            else
                txtKP_NAME.setText(""+objKhoaPhong.KP_NAME.toString());
            if(objKhoaPhong.KP_SOPHONG==null)
                txtKP_SOPHONG.setText("");
            else
                txtKP_SOPHONG.setText(""+objKhoaPhong.KP_SOPHONG.toString());
        }
    }
    
    protected void keyPressKhoaPhongDlg(KeyEvent e) {
		if(e.keyCode==13){
			saveKhoaPhongDlg();
		}
		
	}
}
