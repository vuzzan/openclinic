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

public class CtNhapthuocDlg extends Dialog {
	static Logger logger = LogManager.getLogger(CtNhapthuocDlg.class.getName());

	protected Object result;
	protected Shell shell;
    private Text txtNT_ID;
    private Text txtV_ID;
    private Text txtTENKHO;
    private Text txtTHUOC_ID;
    private Text txtTENTHUOC;
    private Text txtDONVI;
    private Text txtHANDUNG;
    private Text txtLOT_ID;
    private Text txtDONGIA;
    private Text txtTHANHTIEN;
    private Text txtSOLUONG;
    private Text txtSL_TONKHO;
    private Text txtSL_OUTSTANDING;
    private Text txtSL_DADUNG;
    private Text txtVAT;
    private Text txtSTS;

    public CtNhapthuoc objCtNhapthuoc;
    Button btnNewButtonSaveCtNhapthuoc;

    public int intTypeDlgCtNhapthuoc;
	public static final int TYPE_DLG_VIEW = 1;
	public static final int TYPE_DLG_EDIT = 2;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public CtNhapthuocDlg(Shell parent, int style) {
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
		shell.setText("CtNhapthuocDlg EDIT/NEW");
		shell.setLayout(new GridLayout(2, false));
        shell.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.keyCode==SWT.ESC){
					objCtNhapthuoc = null;
				}
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
				keyPressCtNhapthuocDlg(e);
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
				keyPressCtNhapthuocDlg(e);
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
				keyPressCtNhapthuocDlg(e);
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
				keyPressCtNhapthuocDlg(e);
			}
		});
		Label lbltxtTENTHUOC = new Label(shell, SWT.NONE);
        lbltxtTENTHUOC.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtTENTHUOC.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtTENTHUOC.setText("TENTHUOC :");
		
		txtTENTHUOC = new Text(shell, SWT.BORDER);
        txtTENTHUOC.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtTENTHUOC.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtTENTHUOC.setText("TENTHUOC");
        txtTENTHUOC.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressCtNhapthuocDlg(e);
			}
		});
		Label lbltxtDONVI = new Label(shell, SWT.NONE);
        lbltxtDONVI.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtDONVI.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtDONVI.setText("DONVI :");
		
		txtDONVI = new Text(shell, SWT.BORDER);
        txtDONVI.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtDONVI.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtDONVI.setText("DONVI");
        txtDONVI.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressCtNhapthuocDlg(e);
			}
		});
		Label lbltxtHANDUNG = new Label(shell, SWT.NONE);
        lbltxtHANDUNG.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtHANDUNG.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtHANDUNG.setText("HANDUNG :");
		
		txtHANDUNG = new Text(shell, SWT.BORDER);
        txtHANDUNG.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtHANDUNG.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtHANDUNG.setText("HANDUNG");
        txtHANDUNG.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressCtNhapthuocDlg(e);
			}
		});
		Label lbltxtLOT_ID = new Label(shell, SWT.NONE);
        lbltxtLOT_ID.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtLOT_ID.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtLOT_ID.setText("LOT_ID :");
		
		txtLOT_ID = new Text(shell, SWT.BORDER);
        txtLOT_ID.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtLOT_ID.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtLOT_ID.setText("LOT_ID");
        txtLOT_ID.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressCtNhapthuocDlg(e);
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
				keyPressCtNhapthuocDlg(e);
			}
		});
		Label lbltxtSL_TONKHO = new Label(shell, SWT.NONE);
        lbltxtSL_TONKHO.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtSL_TONKHO.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtSL_TONKHO.setText("SL_TONKHO :");
		
		txtSL_TONKHO = new Text(shell, SWT.BORDER);
        txtSL_TONKHO.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtSL_TONKHO.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtSL_TONKHO.setText("SL_TONKHO");
        txtSL_TONKHO.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressCtNhapthuocDlg(e);
			}
		});
		Label lbltxtSL_OUTSTANDING = new Label(shell, SWT.NONE);
        lbltxtSL_OUTSTANDING.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtSL_OUTSTANDING.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtSL_OUTSTANDING.setText("SL_OUTSTANDING :");
		
		txtSL_OUTSTANDING = new Text(shell, SWT.BORDER);
        txtSL_OUTSTANDING.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtSL_OUTSTANDING.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtSL_OUTSTANDING.setText("SL_OUTSTANDING");
        txtSL_OUTSTANDING.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressCtNhapthuocDlg(e);
			}
		});
		Label lbltxtSL_DADUNG = new Label(shell, SWT.NONE);
        lbltxtSL_DADUNG.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtSL_DADUNG.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtSL_DADUNG.setText("SL_DADUNG :");
		
		txtSL_DADUNG = new Text(shell, SWT.BORDER);
        txtSL_DADUNG.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtSL_DADUNG.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtSL_DADUNG.setText("SL_DADUNG");
        txtSL_DADUNG.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressCtNhapthuocDlg(e);
			}
		});
		Label lbltxtDONGIA = new Label(shell, SWT.NONE);
        lbltxtDONGIA.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtDONGIA.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtDONGIA.setText("DONGIA :");
		
		txtDONGIA = new Text(shell, SWT.BORDER);
        txtDONGIA.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtDONGIA.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtDONGIA.setText("DONGIA");
        txtDONGIA.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressCtNhapthuocDlg(e);
			}
		});
		Label lbltxtTHANHTIEN = new Label(shell, SWT.NONE);
        lbltxtTHANHTIEN.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtTHANHTIEN.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtTHANHTIEN.setText("THANHTIEN :");
		
		txtTHANHTIEN = new Text(shell, SWT.BORDER);
        txtTHANHTIEN.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtTHANHTIEN.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtTHANHTIEN.setText("THANHTIEN");
        txtTHANHTIEN.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressCtNhapthuocDlg(e);
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
				keyPressCtNhapthuocDlg(e);
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
				keyPressCtNhapthuocDlg(e);
			}
		});
		new Label(shell, SWT.NONE);
		btnNewButtonSaveCtNhapthuoc = new Button(shell, SWT.NONE);
		btnNewButtonSaveCtNhapthuoc.setImage(SWTResourceManager.getImage(CtNhapthuocDlg.class, "/png/file-2x.png"));
        btnNewButtonSaveCtNhapthuoc.setFont(SWTResourceManager.getFont("Tahoma", 12, SWT.BOLD));
		btnNewButtonSaveCtNhapthuoc.setText("Save");
        
        btnNewButtonSaveCtNhapthuoc.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
                saveCtNhapthuocDlg();
			}
		});
        
        loadCtNhapthuocDlgData();
	}

    private void saveCtNhapthuocDlg(){
        if(intTypeDlgCtNhapthuoc==TYPE_DLG_VIEW){
            return;
        }
        
        if(objCtNhapthuoc == null){
            objCtNhapthuoc = new CtNhapthuoc();
        }
        if(objCtNhapthuoc!=null){
        // Integer    = true
            objCtNhapthuoc.NT_ID = Utils.getInt( txtNT_ID.getText() );
            // Integer    = true
            objCtNhapthuoc.V_ID = Utils.getInt( txtV_ID.getText() );
            // String     = false
            objCtNhapthuoc.TENKHO = txtTENKHO.getText();
            // Integer    = true
            objCtNhapthuoc.THUOC_ID = Utils.getInt( txtTHUOC_ID.getText() );
            // String     = false
            objCtNhapthuoc.TENTHUOC = txtTENTHUOC.getText();
            // String     = false
            objCtNhapthuoc.DONVI = txtDONVI.getText();
            // Date       = false
            // objCtNhapthuoc.HANDUNG = txtHANDUNG.getText();
            // String     = false
            objCtNhapthuoc.LOT_ID = txtLOT_ID.getText();
            // Integer    = true
            objCtNhapthuoc.SOLUONG = Utils.getInt( txtSOLUONG.getText() );
            // Integer    = true
            objCtNhapthuoc.SL_TONKHO = Utils.getInt( txtSL_TONKHO.getText() );
            // Integer    = true
            objCtNhapthuoc.SL_OUTSTANDING = Utils.getInt( txtSL_OUTSTANDING.getText() );
            // Integer    = true
            objCtNhapthuoc.SL_DADUNG = Utils.getInt( txtSL_DADUNG.getText() );
            // Integer    = true
            objCtNhapthuoc.DONGIA = Utils.getInt( txtDONGIA.getText() );
            // Integer    = true
            objCtNhapthuoc.THANHTIEN = Utils.getInt( txtTHANHTIEN.getText() );
            // Integer    = true
            objCtNhapthuoc.VAT = Utils.getInt( txtVAT.getText() );
            // Integer    = true
            objCtNhapthuoc.STS = Utils.getInt( txtSTS.getText() );
            }
        if(DbHelper.checkPhanQuyen(DbHelper.UPDATE, "ct_nhapthuoc")==false){
            logger.info("DON'T HAVE UPDATE RIGHT");
        }
        else if(DbHelper.checkPhanQuyen(DbHelper.INSERT, "ct_nhapthuoc")==false){
            logger.info("DON'T HAVE INSERT RIGHT");
        }        
        else{
            objCtNhapthuoc.insert();
        }
        shell.close();
    }
    
    public void setCtNhapthuocDlgData(CtNhapthuoc obj) {
		this.objCtNhapthuoc = obj;
	}
    
    public void loadCtNhapthuocDlgData(){
        if(intTypeDlgCtNhapthuoc==TYPE_DLG_VIEW){
            btnNewButtonSaveCtNhapthuoc.setEnabled(false);
        }
        else{
            btnNewButtonSaveCtNhapthuoc.setEnabled(true);
        }

        if(DbHelper.checkPhanQuyen(DbHelper.READ, "ct_nhapthuoc")==false){
            logger.info("DON'T HAVE READ RIGHT");
            return;
        }
        if(objCtNhapthuoc != null){
            if(objCtNhapthuoc.NT_ID==null)
                txtNT_ID.setText("");
            else
                txtNT_ID.setText(""+objCtNhapthuoc.NT_ID.toString());
            if(objCtNhapthuoc.V_ID==null)
                txtV_ID.setText("");
            else
                txtV_ID.setText(""+objCtNhapthuoc.V_ID.toString());
            if(objCtNhapthuoc.TENKHO==null)
                txtTENKHO.setText("");
            else
                txtTENKHO.setText(""+objCtNhapthuoc.TENKHO.toString());
            if(objCtNhapthuoc.THUOC_ID==null)
                txtTHUOC_ID.setText("");
            else
                txtTHUOC_ID.setText(""+objCtNhapthuoc.THUOC_ID.toString());
            if(objCtNhapthuoc.TENTHUOC==null)
                txtTENTHUOC.setText("");
            else
                txtTENTHUOC.setText(""+objCtNhapthuoc.TENTHUOC.toString());
            if(objCtNhapthuoc.DONVI==null)
                txtDONVI.setText("");
            else
                txtDONVI.setText(""+objCtNhapthuoc.DONVI.toString());
            if(objCtNhapthuoc.HANDUNG==null)
                txtHANDUNG.setText("");
            else
                txtHANDUNG.setText(""+objCtNhapthuoc.HANDUNG.toString());
            if(objCtNhapthuoc.LOT_ID==null)
                txtLOT_ID.setText("");
            else
                txtLOT_ID.setText(""+objCtNhapthuoc.LOT_ID.toString());
            if(objCtNhapthuoc.SOLUONG==null)
                txtSOLUONG.setText("");
            else
                txtSOLUONG.setText(""+objCtNhapthuoc.SOLUONG.toString());
            if(objCtNhapthuoc.SL_TONKHO==null)
                txtSL_TONKHO.setText("");
            else
                txtSL_TONKHO.setText(""+objCtNhapthuoc.SL_TONKHO.toString());
            if(objCtNhapthuoc.SL_OUTSTANDING==null)
                txtSL_OUTSTANDING.setText("");
            else
                txtSL_OUTSTANDING.setText(""+objCtNhapthuoc.SL_OUTSTANDING.toString());
            if(objCtNhapthuoc.SL_DADUNG==null)
                txtSL_DADUNG.setText("");
            else
                txtSL_DADUNG.setText(""+objCtNhapthuoc.SL_DADUNG.toString());
            if(objCtNhapthuoc.DONGIA==null)
                txtDONGIA.setText("");
            else
                txtDONGIA.setText(""+objCtNhapthuoc.DONGIA.toString());
            if(objCtNhapthuoc.THANHTIEN==null)
                txtTHANHTIEN.setText("");
            else
                txtTHANHTIEN.setText(""+objCtNhapthuoc.THANHTIEN.toString());
            if(objCtNhapthuoc.VAT==null)
                txtVAT.setText("");
            else
                txtVAT.setText(""+objCtNhapthuoc.VAT.toString());
            if(objCtNhapthuoc.STS==null)
                txtSTS.setText("");
            else
                txtSTS.setText(""+objCtNhapthuoc.STS.toString());
        }
    }
    
    protected void keyPressCtNhapthuocDlg(KeyEvent e) {
		if(e.keyCode==13){
			saveCtNhapthuocDlg();
		}
		
	}
}
