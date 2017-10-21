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

public class ThuocChitietDlg extends Dialog {
	static Logger logger = LogManager.getLogger(ThuocChitietDlg.class.getName());

	protected Object result;
	protected Shell shell;
    private Text txtSTT;
    private Text txtMA_LK;
    private Text txtTHUOC_ID;
    private Text txtSOLUONG;
    private Text txtDON_GIA;
    private Text txtTEN_THUOC;
    private Text txtDON_VI_TINH;
    private Text txtHOATCHAT;
    private Text txtHAMLUONG;
    private Text txtDONGGOI;
    private Text txtTYLE_TT;
    private Text txtKHO_NAME;
    private Text txtTHANH_TIEN;
    private Text txtCT_ID;
    private Text txtNT_ID;
    private Text txtTT_BH;
    private Text txtTT_NB;
    private Text txtMA_BENH;
    private Text txtSTS;

    public ThuocChitiet objThuocChitiet;
    Button btnNewButtonSaveThuocChitiet;

    public int intTypeDlgThuocChitiet;
	public static final int TYPE_DLG_VIEW = 1;
	public static final int TYPE_DLG_EDIT = 2;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public ThuocChitietDlg(Shell parent, int style) {
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
		shell.setText("ThuocChitietDlg EDIT/NEW");
		shell.setLayout(new GridLayout(2, false));
        shell.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.keyCode==SWT.ESC){
					objThuocChitiet = null;
				}
			}
		});
        
		Label lbltxtSTT = new Label(shell, SWT.NONE);
        lbltxtSTT.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtSTT.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtSTT.setText("STT :");
		
		txtSTT = new Text(shell, SWT.BORDER);
        txtSTT.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtSTT.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtSTT.setText("STT");
        txtSTT.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressThuocChitietDlg(e);
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
				keyPressThuocChitietDlg(e);
			}
		});
		Label lbltxtTHUOC_ID = new Label(shell, SWT.NONE);
        lbltxtTHUOC_ID.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtTHUOC_ID.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtTHUOC_ID.setText("THUOC_ID :");
		
		txtTHUOC_ID = new Text(shell, SWT.BORDER);
        txtTHUOC_ID.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtTHUOC_ID.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtTHUOC_ID.setText("THUOC_ID");
        txtTHUOC_ID.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressThuocChitietDlg(e);
			}
		});
		Label lbltxtSOLUONG = new Label(shell, SWT.NONE);
        lbltxtSOLUONG.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtSOLUONG.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtSOLUONG.setText("SOLUONG :");
		
		txtSOLUONG = new Text(shell, SWT.BORDER);
        txtSOLUONG.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtSOLUONG.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtSOLUONG.setText("SOLUONG");
        txtSOLUONG.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressThuocChitietDlg(e);
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
				keyPressThuocChitietDlg(e);
			}
		});
		Label lbltxtTEN_THUOC = new Label(shell, SWT.NONE);
        lbltxtTEN_THUOC.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtTEN_THUOC.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtTEN_THUOC.setText("TEN_THUOC :");
		
		txtTEN_THUOC = new Text(shell, SWT.BORDER);
        txtTEN_THUOC.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtTEN_THUOC.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtTEN_THUOC.setText("TEN_THUOC");
        txtTEN_THUOC.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressThuocChitietDlg(e);
			}
		});
		Label lbltxtDON_VI_TINH = new Label(shell, SWT.NONE);
        lbltxtDON_VI_TINH.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtDON_VI_TINH.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtDON_VI_TINH.setText("DON_VI_TINH :");
		
		txtDON_VI_TINH = new Text(shell, SWT.BORDER);
        txtDON_VI_TINH.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtDON_VI_TINH.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtDON_VI_TINH.setText("DON_VI_TINH");
        txtDON_VI_TINH.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressThuocChitietDlg(e);
			}
		});
		Label lbltxtHOATCHAT = new Label(shell, SWT.NONE);
        lbltxtHOATCHAT.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtHOATCHAT.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtHOATCHAT.setText("HOATCHAT :");
		
		txtHOATCHAT = new Text(shell, SWT.BORDER);
        txtHOATCHAT.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtHOATCHAT.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtHOATCHAT.setText("HOATCHAT");
        txtHOATCHAT.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressThuocChitietDlg(e);
			}
		});
		Label lbltxtHAMLUONG = new Label(shell, SWT.NONE);
        lbltxtHAMLUONG.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtHAMLUONG.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtHAMLUONG.setText("HAMLUONG :");
		
		txtHAMLUONG = new Text(shell, SWT.BORDER);
        txtHAMLUONG.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtHAMLUONG.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtHAMLUONG.setText("HAMLUONG");
        txtHAMLUONG.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressThuocChitietDlg(e);
			}
		});
		Label lbltxtDONGGOI = new Label(shell, SWT.NONE);
        lbltxtDONGGOI.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtDONGGOI.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtDONGGOI.setText("DONGGOI :");
		
		txtDONGGOI = new Text(shell, SWT.BORDER);
        txtDONGGOI.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtDONGGOI.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtDONGGOI.setText("DONGGOI");
        txtDONGGOI.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressThuocChitietDlg(e);
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
				keyPressThuocChitietDlg(e);
			}
		});
		Label lbltxtKHO_NAME = new Label(shell, SWT.NONE);
        lbltxtKHO_NAME.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtKHO_NAME.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtKHO_NAME.setText("KHO_NAME :");
		
		txtKHO_NAME = new Text(shell, SWT.BORDER);
        txtKHO_NAME.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtKHO_NAME.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtKHO_NAME.setText("KHO_NAME");
        txtKHO_NAME.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressThuocChitietDlg(e);
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
				keyPressThuocChitietDlg(e);
			}
		});
		Label lbltxtCT_ID = new Label(shell, SWT.NONE);
        lbltxtCT_ID.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtCT_ID.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtCT_ID.setText("CT_ID :");
		
		txtCT_ID = new Text(shell, SWT.BORDER);
        txtCT_ID.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtCT_ID.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtCT_ID.setText("CT_ID");
        txtCT_ID.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressThuocChitietDlg(e);
			}
		});
		Label lbltxtNT_ID = new Label(shell, SWT.NONE);
        lbltxtNT_ID.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtNT_ID.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtNT_ID.setText("NT_ID :");
		
		txtNT_ID = new Text(shell, SWT.BORDER);
        txtNT_ID.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtNT_ID.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtNT_ID.setText("NT_ID");
        txtNT_ID.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressThuocChitietDlg(e);
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
				keyPressThuocChitietDlg(e);
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
				keyPressThuocChitietDlg(e);
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
				keyPressThuocChitietDlg(e);
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
				keyPressThuocChitietDlg(e);
			}
		});
		new Label(shell, SWT.NONE);
		btnNewButtonSaveThuocChitiet = new Button(shell, SWT.NONE);
		btnNewButtonSaveThuocChitiet.setImage(SWTResourceManager.getImage(ThuocChitietDlg.class, "/png/file-2x.png"));
        btnNewButtonSaveThuocChitiet.setFont(SWTResourceManager.getFont("Tahoma", 12, SWT.BOLD));
		btnNewButtonSaveThuocChitiet.setText("Save");
        
        btnNewButtonSaveThuocChitiet.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
                saveThuocChitietDlg();
			}
		});
        
        loadThuocChitietDlgData();
	}

    private void saveThuocChitietDlg(){
        if(intTypeDlgThuocChitiet==TYPE_DLG_VIEW){
            return;
        }
        
        if(objThuocChitiet == null){
            objThuocChitiet = new ThuocChitiet();
        }
        if(objThuocChitiet!=null){
        // Integer    = true
            objThuocChitiet.STT = Utils.getInt( txtSTT.getText() );
            // Integer    = true
            objThuocChitiet.MA_LK = Utils.getInt( txtMA_LK.getText() );
            // Integer    = true
            objThuocChitiet.THUOC_ID = Utils.getInt( txtTHUOC_ID.getText() );
            // Integer    = true
            objThuocChitiet.SOLUONG = Utils.getInt( txtSOLUONG.getText() );
            // Integer    = true
            objThuocChitiet.DON_GIA = Utils.getInt( txtDON_GIA.getText() );
            // String     = false
            objThuocChitiet.TEN_THUOC = txtTEN_THUOC.getText();
            // String     = false
            objThuocChitiet.DON_VI_TINH = txtDON_VI_TINH.getText();
            // String     = false
            objThuocChitiet.HOATCHAT = txtHOATCHAT.getText();
            // String     = false
            objThuocChitiet.HAMLUONG = txtHAMLUONG.getText();
            // String     = false
            objThuocChitiet.DONGGOI = txtDONGGOI.getText();
            // Float      = false
            // objThuocChitiet.TYLE_TT = txtTYLE_TT.getText();
            // String     = false
            objThuocChitiet.KHO_NAME = txtKHO_NAME.getText();
            // Integer    = true
            objThuocChitiet.THANH_TIEN = Utils.getInt( txtTHANH_TIEN.getText() );
            // Integer    = true
            objThuocChitiet.CT_ID = Utils.getInt( txtCT_ID.getText() );
            // Integer    = true
            objThuocChitiet.NT_ID = Utils.getInt( txtNT_ID.getText() );
            // Integer    = true
            objThuocChitiet.TT_BH = Utils.getInt( txtTT_BH.getText() );
            // Integer    = true
            objThuocChitiet.TT_NB = Utils.getInt( txtTT_NB.getText() );
            // String     = false
            objThuocChitiet.MA_BENH = txtMA_BENH.getText();
            // Integer    = true
            objThuocChitiet.STS = Utils.getInt( txtSTS.getText() );
            }
        if(DbHelper.checkPhanQuyen(DbHelper.UPDATE, "thuoc_chitiet")==false){
            logger.info("DON'T HAVE UPDATE RIGHT");
        }
        else if(DbHelper.checkPhanQuyen(DbHelper.INSERT, "thuoc_chitiet")==false){
            logger.info("DON'T HAVE INSERT RIGHT");
        }        
        else{
            objThuocChitiet.insert();
        }
        shell.close();
    }
    
    public void setThuocChitietDlgData(ThuocChitiet obj) {
		this.objThuocChitiet = obj;
	}
    
    public void loadThuocChitietDlgData(){
        if(intTypeDlgThuocChitiet==TYPE_DLG_VIEW){
            btnNewButtonSaveThuocChitiet.setEnabled(false);
        }
        else{
            btnNewButtonSaveThuocChitiet.setEnabled(true);
        }

        if(DbHelper.checkPhanQuyen(DbHelper.READ, "thuoc_chitiet")==false){
            logger.info("DON'T HAVE READ RIGHT");
            return;
        }
        if(objThuocChitiet != null){
            if(objThuocChitiet.STT==null)
                txtSTT.setText("");
            else
                txtSTT.setText(""+objThuocChitiet.STT.toString());
            if(objThuocChitiet.MA_LK==null)
                txtMA_LK.setText("");
            else
                txtMA_LK.setText(""+objThuocChitiet.MA_LK.toString());
            if(objThuocChitiet.THUOC_ID==null)
                txtTHUOC_ID.setText("");
            else
                txtTHUOC_ID.setText(""+objThuocChitiet.THUOC_ID.toString());
            if(objThuocChitiet.SOLUONG==null)
                txtSOLUONG.setText("");
            else
                txtSOLUONG.setText(""+objThuocChitiet.SOLUONG.toString());
            if(objThuocChitiet.DON_GIA==null)
                txtDON_GIA.setText("");
            else
                txtDON_GIA.setText(""+objThuocChitiet.DON_GIA.toString());
            if(objThuocChitiet.TEN_THUOC==null)
                txtTEN_THUOC.setText("");
            else
                txtTEN_THUOC.setText(""+objThuocChitiet.TEN_THUOC.toString());
            if(objThuocChitiet.DON_VI_TINH==null)
                txtDON_VI_TINH.setText("");
            else
                txtDON_VI_TINH.setText(""+objThuocChitiet.DON_VI_TINH.toString());
            if(objThuocChitiet.HOATCHAT==null)
                txtHOATCHAT.setText("");
            else
                txtHOATCHAT.setText(""+objThuocChitiet.HOATCHAT.toString());
            if(objThuocChitiet.HAMLUONG==null)
                txtHAMLUONG.setText("");
            else
                txtHAMLUONG.setText(""+objThuocChitiet.HAMLUONG.toString());
            if(objThuocChitiet.DONGGOI==null)
                txtDONGGOI.setText("");
            else
                txtDONGGOI.setText(""+objThuocChitiet.DONGGOI.toString());
            if(objThuocChitiet.TYLE_TT==null)
                txtTYLE_TT.setText("");
            else
                txtTYLE_TT.setText(""+objThuocChitiet.TYLE_TT.toString());
            if(objThuocChitiet.KHO_NAME==null)
                txtKHO_NAME.setText("");
            else
                txtKHO_NAME.setText(""+objThuocChitiet.KHO_NAME.toString());
            if(objThuocChitiet.THANH_TIEN==null)
                txtTHANH_TIEN.setText("");
            else
                txtTHANH_TIEN.setText(""+objThuocChitiet.THANH_TIEN.toString());
            if(objThuocChitiet.CT_ID==null)
                txtCT_ID.setText("");
            else
                txtCT_ID.setText(""+objThuocChitiet.CT_ID.toString());
            if(objThuocChitiet.NT_ID==null)
                txtNT_ID.setText("");
            else
                txtNT_ID.setText(""+objThuocChitiet.NT_ID.toString());
            if(objThuocChitiet.TT_BH==null)
                txtTT_BH.setText("");
            else
                txtTT_BH.setText(""+objThuocChitiet.TT_BH.toString());
            if(objThuocChitiet.TT_NB==null)
                txtTT_NB.setText("");
            else
                txtTT_NB.setText(""+objThuocChitiet.TT_NB.toString());
            if(objThuocChitiet.MA_BENH==null)
                txtMA_BENH.setText("");
            else
                txtMA_BENH.setText(""+objThuocChitiet.MA_BENH.toString());
            if(objThuocChitiet.STS==null)
                txtSTS.setText("");
            else
                txtSTS.setText(""+objThuocChitiet.STS.toString());
        }
    }
    
    protected void keyPressThuocChitietDlg(KeyEvent e) {
		if(e.keyCode==13){
			saveThuocChitietDlg();
		}
		
	}
}
