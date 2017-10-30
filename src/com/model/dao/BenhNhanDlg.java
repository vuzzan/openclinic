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

public class BenhNhanDlg extends Dialog {
	static Logger logger = LogManager.getLogger(BenhNhanDlg.class.getName());

	protected Object result;
	protected Shell shell;
    private Text txtHO_TEN;
    private Text txtNGAY_SINH;
    private Text txtGIOI_TINH;
    private Text txtDIA_CHI;
    private Text txtMA_THE;
    private Text txtMA_DKBD;
    private Text txtGT_THE_TU;
    private Text txtGT_THE_DEN;
    private Text txtNGAY_CAP;
    private Text txtMA_QUAN_LY;
    private Text txtTEN_CHA_ME;
    private Text txtMA_DT_SONG;
    private Text txtTHOIDIEM_NAMNAM;
    private Text txtCHUOI_KIEM_TRA;
    private Text txtDATE_ADD;
    private Text txtLAST_EDIT;
    private Text txtSTS;

    public BenhNhan objBenhNhan;
    Button btnNewButtonSaveBenhNhan;

    public int intTypeDlgBenhNhan;
	public static final int TYPE_DLG_VIEW = 1;
	public static final int TYPE_DLG_EDIT = 2;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public BenhNhanDlg(Shell parent, int style) {
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
		shell.setText("BenhNhanDlg EDIT/NEW");
		shell.setLayout(new GridLayout(2, false));
        shell.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.keyCode==SWT.ESC){
					objBenhNhan = null;
				}
			}
		});
        
		Label lbltxtHO_TEN = new Label(shell, SWT.NONE);
        lbltxtHO_TEN.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtHO_TEN.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtHO_TEN.setText("HO_TEN :");
		
		txtHO_TEN = new Text(shell, SWT.BORDER);
        txtHO_TEN.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtHO_TEN.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtHO_TEN.setText("HO_TEN");
        txtHO_TEN.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressBenhNhanDlg(e);
			}
		});
		Label lbltxtNGAY_SINH = new Label(shell, SWT.NONE);
        lbltxtNGAY_SINH.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtNGAY_SINH.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtNGAY_SINH.setText("NGAY_SINH :");
		
		txtNGAY_SINH = new Text(shell, SWT.BORDER);
        txtNGAY_SINH.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtNGAY_SINH.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtNGAY_SINH.setText("NGAY_SINH");
        txtNGAY_SINH.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressBenhNhanDlg(e);
			}
		});
		Label lbltxtGIOI_TINH = new Label(shell, SWT.NONE);
        lbltxtGIOI_TINH.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtGIOI_TINH.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtGIOI_TINH.setText("GIOI_TINH :");
		
		txtGIOI_TINH = new Text(shell, SWT.BORDER);
        txtGIOI_TINH.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtGIOI_TINH.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtGIOI_TINH.setText("GIOI_TINH");
        txtGIOI_TINH.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressBenhNhanDlg(e);
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
				keyPressBenhNhanDlg(e);
			}
		});
		Label lbltxtMA_THE = new Label(shell, SWT.NONE);
        lbltxtMA_THE.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtMA_THE.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtMA_THE.setText("MA_THE :");
		
		txtMA_THE = new Text(shell, SWT.BORDER);
        txtMA_THE.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtMA_THE.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtMA_THE.setText("MA_THE");
        txtMA_THE.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressBenhNhanDlg(e);
			}
		});
		Label lbltxtMA_DKBD = new Label(shell, SWT.NONE);
        lbltxtMA_DKBD.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtMA_DKBD.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtMA_DKBD.setText("MA_DKBD :");
		
		txtMA_DKBD = new Text(shell, SWT.BORDER);
        txtMA_DKBD.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtMA_DKBD.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtMA_DKBD.setText("MA_DKBD");
        txtMA_DKBD.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressBenhNhanDlg(e);
			}
		});
		Label lbltxtGT_THE_TU = new Label(shell, SWT.NONE);
        lbltxtGT_THE_TU.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtGT_THE_TU.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtGT_THE_TU.setText("GT_THE_TU :");
		
		txtGT_THE_TU = new Text(shell, SWT.BORDER);
        txtGT_THE_TU.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtGT_THE_TU.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtGT_THE_TU.setText("GT_THE_TU");
        txtGT_THE_TU.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressBenhNhanDlg(e);
			}
		});
		Label lbltxtGT_THE_DEN = new Label(shell, SWT.NONE);
        lbltxtGT_THE_DEN.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtGT_THE_DEN.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtGT_THE_DEN.setText("GT_THE_DEN :");
		
		txtGT_THE_DEN = new Text(shell, SWT.BORDER);
        txtGT_THE_DEN.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtGT_THE_DEN.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtGT_THE_DEN.setText("GT_THE_DEN");
        txtGT_THE_DEN.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressBenhNhanDlg(e);
			}
		});
		Label lbltxtNGAY_CAP = new Label(shell, SWT.NONE);
        lbltxtNGAY_CAP.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtNGAY_CAP.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtNGAY_CAP.setText("NGAY_CAP :");
		
		txtNGAY_CAP = new Text(shell, SWT.BORDER);
        txtNGAY_CAP.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtNGAY_CAP.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtNGAY_CAP.setText("NGAY_CAP");
        txtNGAY_CAP.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressBenhNhanDlg(e);
			}
		});
		Label lbltxtMA_QUAN_LY = new Label(shell, SWT.NONE);
        lbltxtMA_QUAN_LY.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtMA_QUAN_LY.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtMA_QUAN_LY.setText("MA_QUAN_LY :");
		
		txtMA_QUAN_LY = new Text(shell, SWT.BORDER);
        txtMA_QUAN_LY.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtMA_QUAN_LY.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtMA_QUAN_LY.setText("MA_QUAN_LY");
        txtMA_QUAN_LY.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressBenhNhanDlg(e);
			}
		});
		Label lbltxtTEN_CHA_ME = new Label(shell, SWT.NONE);
        lbltxtTEN_CHA_ME.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtTEN_CHA_ME.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtTEN_CHA_ME.setText("TEN_CHA_ME :");
		
		txtTEN_CHA_ME = new Text(shell, SWT.BORDER);
        txtTEN_CHA_ME.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtTEN_CHA_ME.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtTEN_CHA_ME.setText("TEN_CHA_ME");
        txtTEN_CHA_ME.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressBenhNhanDlg(e);
			}
		});
		Label lbltxtMA_DT_SONG = new Label(shell, SWT.NONE);
        lbltxtMA_DT_SONG.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtMA_DT_SONG.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtMA_DT_SONG.setText("MA_DT_SONG :");
		
		txtMA_DT_SONG = new Text(shell, SWT.BORDER);
        txtMA_DT_SONG.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtMA_DT_SONG.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtMA_DT_SONG.setText("MA_DT_SONG");
        txtMA_DT_SONG.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressBenhNhanDlg(e);
			}
		});
		Label lbltxtTHOIDIEM_NAMNAM = new Label(shell, SWT.NONE);
        lbltxtTHOIDIEM_NAMNAM.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtTHOIDIEM_NAMNAM.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtTHOIDIEM_NAMNAM.setText("THOIDIEM_NAMNAM :");
		
		txtTHOIDIEM_NAMNAM = new Text(shell, SWT.BORDER);
        txtTHOIDIEM_NAMNAM.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtTHOIDIEM_NAMNAM.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtTHOIDIEM_NAMNAM.setText("THOIDIEM_NAMNAM");
        txtTHOIDIEM_NAMNAM.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressBenhNhanDlg(e);
			}
		});
		Label lbltxtCHUOI_KIEM_TRA = new Label(shell, SWT.NONE);
        lbltxtCHUOI_KIEM_TRA.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtCHUOI_KIEM_TRA.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtCHUOI_KIEM_TRA.setText("CHUOI_KIEM_TRA :");
		
		txtCHUOI_KIEM_TRA = new Text(shell, SWT.BORDER);
        txtCHUOI_KIEM_TRA.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtCHUOI_KIEM_TRA.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtCHUOI_KIEM_TRA.setText("CHUOI_KIEM_TRA");
        txtCHUOI_KIEM_TRA.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressBenhNhanDlg(e);
			}
		});
		Label lbltxtDATE_ADD = new Label(shell, SWT.NONE);
        lbltxtDATE_ADD.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtDATE_ADD.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtDATE_ADD.setText("DATE_ADD :");
		
		txtDATE_ADD = new Text(shell, SWT.BORDER);
        txtDATE_ADD.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtDATE_ADD.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtDATE_ADD.setText("DATE_ADD");
        txtDATE_ADD.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressBenhNhanDlg(e);
			}
		});
		Label lbltxtLAST_EDIT = new Label(shell, SWT.NONE);
        lbltxtLAST_EDIT.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtLAST_EDIT.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtLAST_EDIT.setText("LAST_EDIT :");
		
		txtLAST_EDIT = new Text(shell, SWT.BORDER);
        txtLAST_EDIT.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtLAST_EDIT.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtLAST_EDIT.setText("LAST_EDIT");
        txtLAST_EDIT.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressBenhNhanDlg(e);
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
				keyPressBenhNhanDlg(e);
			}
		});
		new Label(shell, SWT.NONE);
		btnNewButtonSaveBenhNhan = new Button(shell, SWT.NONE);
		btnNewButtonSaveBenhNhan.setImage(SWTResourceManager.getImage(BenhNhanDlg.class, "/png/file-2x.png"));
        btnNewButtonSaveBenhNhan.setFont(SWTResourceManager.getFont("Tahoma", 12, SWT.BOLD));
		btnNewButtonSaveBenhNhan.setText("Save");
        
        btnNewButtonSaveBenhNhan.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
                saveBenhNhanDlg();
			}
		});
        
        loadBenhNhanDlgData();
	}

    private void saveBenhNhanDlg(){
        if(intTypeDlgBenhNhan==TYPE_DLG_VIEW){
            return;
        }
        
        if(objBenhNhan == null){
            objBenhNhan = new BenhNhan();
        }
        if(objBenhNhan!=null){
        // String     = false
            objBenhNhan.HO_TEN = txtHO_TEN.getText();
            // String     = false
            objBenhNhan.NGAY_SINH = txtNGAY_SINH.getText();
            // Integer    = true
            objBenhNhan.GIOI_TINH = Utils.getInt( txtGIOI_TINH.getText() );
            // String     = false
            objBenhNhan.DIA_CHI = txtDIA_CHI.getText();
            // String     = false
            objBenhNhan.MA_THE = txtMA_THE.getText();
            // String     = false
            objBenhNhan.MA_DKBD = txtMA_DKBD.getText();
            // String     = false
            objBenhNhan.GT_THE_TU = txtGT_THE_TU.getText();
            // String     = false
            objBenhNhan.GT_THE_DEN = txtGT_THE_DEN.getText();
            // String     = false
            objBenhNhan.NGAY_CAP = txtNGAY_CAP.getText();
            // String     = false
            objBenhNhan.MA_QUAN_LY = txtMA_QUAN_LY.getText();
            // String     = false
            objBenhNhan.TEN_CHA_ME = txtTEN_CHA_ME.getText();
            // Integer    = true
            objBenhNhan.MA_DT_SONG = Utils.getInt( txtMA_DT_SONG.getText() );
            // String     = false
            objBenhNhan.THOIDIEM_NAMNAM = txtTHOIDIEM_NAMNAM.getText();
            // String     = false
            objBenhNhan.CHUOI_KIEM_TRA = txtCHUOI_KIEM_TRA.getText();
            // Date       = false
            // objBenhNhan.DATE_ADD = txtDATE_ADD.getText();
            // Date       = false
            // objBenhNhan.LAST_EDIT = txtLAST_EDIT.getText();
            // Integer    = true
            objBenhNhan.STS = Utils.getInt( txtSTS.getText() );
            }
        if(DbHelper.checkPhanQuyen(DbHelper.UPDATE, "benh_nhan")==false){
            logger.info("DON'T HAVE UPDATE RIGHT");
        }
        else if(DbHelper.checkPhanQuyen(DbHelper.INSERT, "benh_nhan")==false){
            logger.info("DON'T HAVE INSERT RIGHT");
        }        
        else{
            objBenhNhan.insert();
        }
        shell.close();
    }
    
    public void setBenhNhanDlgData(BenhNhan obj) {
		this.objBenhNhan = obj;
	}
    
    public void loadBenhNhanDlgData(){
        if(intTypeDlgBenhNhan==TYPE_DLG_VIEW){
            btnNewButtonSaveBenhNhan.setEnabled(false);
        }
        else{
            btnNewButtonSaveBenhNhan.setEnabled(true);
        }

        if(DbHelper.checkPhanQuyen(DbHelper.READ, "benh_nhan")==false){
            logger.info("DON'T HAVE READ RIGHT");
            return;
        }
        if(objBenhNhan != null){
            if(objBenhNhan.HO_TEN==null)
                txtHO_TEN.setText("");
            else
                txtHO_TEN.setText(""+objBenhNhan.HO_TEN.toString());
            if(objBenhNhan.NGAY_SINH==null)
                txtNGAY_SINH.setText("");
            else
                txtNGAY_SINH.setText(""+objBenhNhan.NGAY_SINH.toString());
            if(objBenhNhan.GIOI_TINH==null)
                txtGIOI_TINH.setText("");
            else
                txtGIOI_TINH.setText(""+objBenhNhan.GIOI_TINH.toString());
            if(objBenhNhan.DIA_CHI==null)
                txtDIA_CHI.setText("");
            else
                txtDIA_CHI.setText(""+objBenhNhan.DIA_CHI.toString());
            if(objBenhNhan.MA_THE==null)
                txtMA_THE.setText("");
            else
                txtMA_THE.setText(""+objBenhNhan.MA_THE.toString());
            if(objBenhNhan.MA_DKBD==null)
                txtMA_DKBD.setText("");
            else
                txtMA_DKBD.setText(""+objBenhNhan.MA_DKBD.toString());
            if(objBenhNhan.GT_THE_TU==null)
                txtGT_THE_TU.setText("");
            else
                txtGT_THE_TU.setText(""+objBenhNhan.GT_THE_TU.toString());
            if(objBenhNhan.GT_THE_DEN==null)
                txtGT_THE_DEN.setText("");
            else
                txtGT_THE_DEN.setText(""+objBenhNhan.GT_THE_DEN.toString());
            if(objBenhNhan.NGAY_CAP==null)
                txtNGAY_CAP.setText("");
            else
                txtNGAY_CAP.setText(""+objBenhNhan.NGAY_CAP.toString());
            if(objBenhNhan.MA_QUAN_LY==null)
                txtMA_QUAN_LY.setText("");
            else
                txtMA_QUAN_LY.setText(""+objBenhNhan.MA_QUAN_LY.toString());
            if(objBenhNhan.TEN_CHA_ME==null)
                txtTEN_CHA_ME.setText("");
            else
                txtTEN_CHA_ME.setText(""+objBenhNhan.TEN_CHA_ME.toString());
            if(objBenhNhan.MA_DT_SONG==null)
                txtMA_DT_SONG.setText("");
            else
                txtMA_DT_SONG.setText(""+objBenhNhan.MA_DT_SONG.toString());
            if(objBenhNhan.THOIDIEM_NAMNAM==null)
                txtTHOIDIEM_NAMNAM.setText("");
            else
                txtTHOIDIEM_NAMNAM.setText(""+objBenhNhan.THOIDIEM_NAMNAM.toString());
            if(objBenhNhan.CHUOI_KIEM_TRA==null)
                txtCHUOI_KIEM_TRA.setText("");
            else
                txtCHUOI_KIEM_TRA.setText(""+objBenhNhan.CHUOI_KIEM_TRA.toString());
            if(objBenhNhan.DATE_ADD==null)
                txtDATE_ADD.setText("");
            else
                txtDATE_ADD.setText(""+objBenhNhan.DATE_ADD.toString());
            if(objBenhNhan.LAST_EDIT==null)
                txtLAST_EDIT.setText("");
            else
                txtLAST_EDIT.setText(""+objBenhNhan.LAST_EDIT.toString());
            if(objBenhNhan.STS==null)
                txtSTS.setText("");
            else
                txtSTS.setText(""+objBenhNhan.STS.toString());
        }
    }
    
    protected void keyPressBenhNhanDlg(KeyEvent e) {
		if(e.keyCode==13){
			saveBenhNhanDlg();
		}
		
	}
}
