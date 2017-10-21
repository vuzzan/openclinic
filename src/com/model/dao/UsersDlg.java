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

public class UsersDlg extends Dialog {
	static Logger logger = LogManager.getLogger(UsersDlg.class.getName());

	protected Object result;
	protected Shell shell;
    private Text txtMA_KHOA;
    private Text txtMACCHN;
    private Text txtTEN_NHANVIEN;
    private Text txtNGAYSINH;
    private Text txtDIA_CHI;
    private Text txtMA_CHUYENNGANH;
    private Text txtU_NAME;
    private Text txtU_PASS;
    private Text txtLOAI;
    private Text txtNGAYCAP_CCHN;
    private Text txtNOICAP_CCHN;
    private Text txtTUNGAY;
    private Text txtDENNGAY;
    private Text txtCHUNGCHI_KHAC;
    private Text txtSTS;

    public Users objUsers;
    Button btnNewButtonSaveUsers;

    public int intTypeDlgUsers;
	public static final int TYPE_DLG_VIEW = 1;
	public static final int TYPE_DLG_EDIT = 2;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public UsersDlg(Shell parent, int style) {
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
		shell.setText("UsersDlg EDIT/NEW");
		shell.setLayout(new GridLayout(2, false));
        shell.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.keyCode==SWT.ESC){
					objUsers = null;
				}
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
				keyPressUsersDlg(e);
			}
		});
		Label lbltxtMACCHN = new Label(shell, SWT.NONE);
        lbltxtMACCHN.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtMACCHN.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtMACCHN.setText("MACCHN :");
		
		txtMACCHN = new Text(shell, SWT.BORDER);
        txtMACCHN.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtMACCHN.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtMACCHN.setText("MACCHN");
        txtMACCHN.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressUsersDlg(e);
			}
		});
		Label lbltxtTEN_NHANVIEN = new Label(shell, SWT.NONE);
        lbltxtTEN_NHANVIEN.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtTEN_NHANVIEN.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtTEN_NHANVIEN.setText("TEN_NHANVIEN :");
		
		txtTEN_NHANVIEN = new Text(shell, SWT.BORDER);
        txtTEN_NHANVIEN.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtTEN_NHANVIEN.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtTEN_NHANVIEN.setText("TEN_NHANVIEN");
        txtTEN_NHANVIEN.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressUsersDlg(e);
			}
		});
		Label lbltxtNGAYSINH = new Label(shell, SWT.NONE);
        lbltxtNGAYSINH.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtNGAYSINH.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtNGAYSINH.setText("NGAYSINH :");
		
		txtNGAYSINH = new Text(shell, SWT.BORDER);
        txtNGAYSINH.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtNGAYSINH.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtNGAYSINH.setText("NGAYSINH");
        txtNGAYSINH.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressUsersDlg(e);
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
				keyPressUsersDlg(e);
			}
		});
		Label lbltxtMA_CHUYENNGANH = new Label(shell, SWT.NONE);
        lbltxtMA_CHUYENNGANH.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtMA_CHUYENNGANH.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtMA_CHUYENNGANH.setText("MA_CHUYENNGANH :");
		
		txtMA_CHUYENNGANH = new Text(shell, SWT.BORDER);
        txtMA_CHUYENNGANH.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtMA_CHUYENNGANH.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtMA_CHUYENNGANH.setText("MA_CHUYENNGANH");
        txtMA_CHUYENNGANH.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressUsersDlg(e);
			}
		});
		Label lbltxtU_NAME = new Label(shell, SWT.NONE);
        lbltxtU_NAME.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtU_NAME.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtU_NAME.setText("U_NAME :");
		
		txtU_NAME = new Text(shell, SWT.BORDER);
        txtU_NAME.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtU_NAME.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtU_NAME.setText("U_NAME");
        txtU_NAME.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressUsersDlg(e);
			}
		});
		Label lbltxtU_PASS = new Label(shell, SWT.NONE);
        lbltxtU_PASS.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtU_PASS.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtU_PASS.setText("U_PASS :");
		
		txtU_PASS = new Text(shell, SWT.BORDER);
        txtU_PASS.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtU_PASS.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtU_PASS.setText("U_PASS");
        txtU_PASS.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressUsersDlg(e);
			}
		});
		Label lbltxtLOAI = new Label(shell, SWT.NONE);
        lbltxtLOAI.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtLOAI.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtLOAI.setText("LOAI :");
		
		txtLOAI = new Text(shell, SWT.BORDER);
        txtLOAI.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtLOAI.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtLOAI.setText("LOAI");
        txtLOAI.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressUsersDlg(e);
			}
		});
		Label lbltxtNGAYCAP_CCHN = new Label(shell, SWT.NONE);
        lbltxtNGAYCAP_CCHN.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtNGAYCAP_CCHN.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtNGAYCAP_CCHN.setText("NGAYCAP_CCHN :");
		
		txtNGAYCAP_CCHN = new Text(shell, SWT.BORDER);
        txtNGAYCAP_CCHN.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtNGAYCAP_CCHN.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtNGAYCAP_CCHN.setText("NGAYCAP_CCHN");
        txtNGAYCAP_CCHN.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressUsersDlg(e);
			}
		});
		Label lbltxtNOICAP_CCHN = new Label(shell, SWT.NONE);
        lbltxtNOICAP_CCHN.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtNOICAP_CCHN.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtNOICAP_CCHN.setText("NOICAP_CCHN :");
		
		txtNOICAP_CCHN = new Text(shell, SWT.BORDER);
        txtNOICAP_CCHN.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtNOICAP_CCHN.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtNOICAP_CCHN.setText("NOICAP_CCHN");
        txtNOICAP_CCHN.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressUsersDlg(e);
			}
		});
		Label lbltxtTUNGAY = new Label(shell, SWT.NONE);
        lbltxtTUNGAY.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtTUNGAY.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtTUNGAY.setText("TUNGAY :");
		
		txtTUNGAY = new Text(shell, SWT.BORDER);
        txtTUNGAY.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtTUNGAY.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtTUNGAY.setText("TUNGAY");
        txtTUNGAY.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressUsersDlg(e);
			}
		});
		Label lbltxtDENNGAY = new Label(shell, SWT.NONE);
        lbltxtDENNGAY.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtDENNGAY.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtDENNGAY.setText("DENNGAY :");
		
		txtDENNGAY = new Text(shell, SWT.BORDER);
        txtDENNGAY.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtDENNGAY.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtDENNGAY.setText("DENNGAY");
        txtDENNGAY.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressUsersDlg(e);
			}
		});
		Label lbltxtCHUNGCHI_KHAC = new Label(shell, SWT.NONE);
        lbltxtCHUNGCHI_KHAC.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtCHUNGCHI_KHAC.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtCHUNGCHI_KHAC.setText("CHUNGCHI_KHAC :");
		
		txtCHUNGCHI_KHAC = new Text(shell, SWT.BORDER);
        txtCHUNGCHI_KHAC.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtCHUNGCHI_KHAC.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtCHUNGCHI_KHAC.setText("CHUNGCHI_KHAC");
        txtCHUNGCHI_KHAC.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressUsersDlg(e);
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
				keyPressUsersDlg(e);
			}
		});
		new Label(shell, SWT.NONE);
		btnNewButtonSaveUsers = new Button(shell, SWT.NONE);
		btnNewButtonSaveUsers.setImage(SWTResourceManager.getImage(UsersDlg.class, "/png/file-2x.png"));
        btnNewButtonSaveUsers.setFont(SWTResourceManager.getFont("Tahoma", 12, SWT.BOLD));
		btnNewButtonSaveUsers.setText("Save");
        
        btnNewButtonSaveUsers.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
                saveUsersDlg();
			}
		});
        
        loadUsersDlgData();
	}

    private void saveUsersDlg(){
        if(intTypeDlgUsers==TYPE_DLG_VIEW){
            return;
        }
        
        if(objUsers == null){
            objUsers = new Users();
        }
        if(objUsers!=null){
        // String     = false
            objUsers.MA_KHOA = txtMA_KHOA.getText();
            // String     = false
            objUsers.MACCHN = txtMACCHN.getText();
            // String     = false
            objUsers.TEN_NHANVIEN = txtTEN_NHANVIEN.getText();
            // String     = false
            objUsers.NGAYSINH = txtNGAYSINH.getText();
            // String     = false
            objUsers.DIA_CHI = txtDIA_CHI.getText();
            // String     = false
            objUsers.MA_CHUYENNGANH = txtMA_CHUYENNGANH.getText();
            // String     = false
            objUsers.U_NAME = txtU_NAME.getText();
            // String     = false
            objUsers.U_PASS = txtU_PASS.getText();
            // String     = false
            objUsers.LOAI = txtLOAI.getText();
            // String     = false
            objUsers.NGAYCAP_CCHN = txtNGAYCAP_CCHN.getText();
            // String     = false
            objUsers.NOICAP_CCHN = txtNOICAP_CCHN.getText();
            // String     = false
            objUsers.TUNGAY = txtTUNGAY.getText();
            // String     = false
            objUsers.DENNGAY = txtDENNGAY.getText();
            // String     = false
            objUsers.CHUNGCHI_KHAC = txtCHUNGCHI_KHAC.getText();
            // Integer    = true
            objUsers.STS = Utils.getInt( txtSTS.getText() );
            }
        if(DbHelper.checkPhanQuyen(DbHelper.UPDATE, "users")==false){
            logger.info("DON'T HAVE UPDATE RIGHT");
        }
        else if(DbHelper.checkPhanQuyen(DbHelper.INSERT, "users")==false){
            logger.info("DON'T HAVE INSERT RIGHT");
        }        
        else{
            objUsers.insert();
        }
        shell.close();
    }
    
    public void setUsersDlgData(Users obj) {
		this.objUsers = obj;
	}
    
    public void loadUsersDlgData(){
        if(intTypeDlgUsers==TYPE_DLG_VIEW){
            btnNewButtonSaveUsers.setEnabled(false);
        }
        else{
            btnNewButtonSaveUsers.setEnabled(true);
        }

        if(DbHelper.checkPhanQuyen(DbHelper.READ, "users")==false){
            logger.info("DON'T HAVE READ RIGHT");
            return;
        }
        if(objUsers != null){
            if(objUsers.MA_KHOA==null)
                txtMA_KHOA.setText("");
            else
                txtMA_KHOA.setText(""+objUsers.MA_KHOA.toString());
            if(objUsers.MACCHN==null)
                txtMACCHN.setText("");
            else
                txtMACCHN.setText(""+objUsers.MACCHN.toString());
            if(objUsers.TEN_NHANVIEN==null)
                txtTEN_NHANVIEN.setText("");
            else
                txtTEN_NHANVIEN.setText(""+objUsers.TEN_NHANVIEN.toString());
            if(objUsers.NGAYSINH==null)
                txtNGAYSINH.setText("");
            else
                txtNGAYSINH.setText(""+objUsers.NGAYSINH.toString());
            if(objUsers.DIA_CHI==null)
                txtDIA_CHI.setText("");
            else
                txtDIA_CHI.setText(""+objUsers.DIA_CHI.toString());
            if(objUsers.MA_CHUYENNGANH==null)
                txtMA_CHUYENNGANH.setText("");
            else
                txtMA_CHUYENNGANH.setText(""+objUsers.MA_CHUYENNGANH.toString());
            if(objUsers.U_NAME==null)
                txtU_NAME.setText("");
            else
                txtU_NAME.setText(""+objUsers.U_NAME.toString());
            if(objUsers.U_PASS==null)
                txtU_PASS.setText("");
            else
                txtU_PASS.setText(""+objUsers.U_PASS.toString());
            if(objUsers.LOAI==null)
                txtLOAI.setText("");
            else
                txtLOAI.setText(""+objUsers.LOAI.toString());
            if(objUsers.NGAYCAP_CCHN==null)
                txtNGAYCAP_CCHN.setText("");
            else
                txtNGAYCAP_CCHN.setText(""+objUsers.NGAYCAP_CCHN.toString());
            if(objUsers.NOICAP_CCHN==null)
                txtNOICAP_CCHN.setText("");
            else
                txtNOICAP_CCHN.setText(""+objUsers.NOICAP_CCHN.toString());
            if(objUsers.TUNGAY==null)
                txtTUNGAY.setText("");
            else
                txtTUNGAY.setText(""+objUsers.TUNGAY.toString());
            if(objUsers.DENNGAY==null)
                txtDENNGAY.setText("");
            else
                txtDENNGAY.setText(""+objUsers.DENNGAY.toString());
            if(objUsers.CHUNGCHI_KHAC==null)
                txtCHUNGCHI_KHAC.setText("");
            else
                txtCHUNGCHI_KHAC.setText(""+objUsers.CHUNGCHI_KHAC.toString());
            if(objUsers.STS==null)
                txtSTS.setText("");
            else
                txtSTS.setText(""+objUsers.STS.toString());
        }
    }
    
    protected void keyPressUsersDlg(KeyEvent e) {
		if(e.keyCode==13){
			saveUsersDlg();
		}
		
	}
}
