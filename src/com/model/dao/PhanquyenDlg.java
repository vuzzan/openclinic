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

public class PhanquyenDlg extends Dialog {
	static Logger logger = LogManager.getLogger(PhanquyenDlg.class.getName());

	protected Object result;
	protected Shell shell;
    private Text txtU_ID;
    private Text txtU_NAME;
    private Text txtTABLE_NAME;
    private Text txtREAD;
    private Text txtINSERT;
    private Text txtUPDATE;
    private Text txtDELETE;
    private Text txtSTS;

    public Phanquyen objPhanquyen;
    Button btnNewButtonSavePhanquyen;

    public int intTypeDlgPhanquyen;
	public static final int TYPE_DLG_VIEW = 1;
	public static final int TYPE_DLG_EDIT = 2;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public PhanquyenDlg(Shell parent, int style) {
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
		shell.setText("PhanquyenDlg EDIT/NEW");
		shell.setLayout(new GridLayout(2, false));
        shell.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.keyCode==SWT.ESC){
					objPhanquyen = null;
				}
			}
		});
        
		Label lbltxtU_ID = new Label(shell, SWT.NONE);
        lbltxtU_ID.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtU_ID.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtU_ID.setText("U_ID :");
		
		txtU_ID = new Text(shell, SWT.BORDER);
        txtU_ID.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtU_ID.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtU_ID.setText("U_ID");
        txtU_ID.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressPhanquyenDlg(e);
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
				keyPressPhanquyenDlg(e);
			}
		});
		Label lbltxtTABLE_NAME = new Label(shell, SWT.NONE);
        lbltxtTABLE_NAME.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtTABLE_NAME.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtTABLE_NAME.setText("TABLE_NAME :");
		
		txtTABLE_NAME = new Text(shell, SWT.BORDER);
        txtTABLE_NAME.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtTABLE_NAME.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtTABLE_NAME.setText("TABLE_NAME");
        txtTABLE_NAME.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressPhanquyenDlg(e);
			}
		});
		Label lbltxtREAD = new Label(shell, SWT.NONE);
        lbltxtREAD.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtREAD.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtREAD.setText("READ :");
		
		txtREAD = new Text(shell, SWT.BORDER);
        txtREAD.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtREAD.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtREAD.setText("READ");
        txtREAD.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressPhanquyenDlg(e);
			}
		});
		Label lbltxtINSERT = new Label(shell, SWT.NONE);
        lbltxtINSERT.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtINSERT.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtINSERT.setText("INSERT :");
		
		txtINSERT = new Text(shell, SWT.BORDER);
        txtINSERT.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtINSERT.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtINSERT.setText("INSERT");
        txtINSERT.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressPhanquyenDlg(e);
			}
		});
		Label lbltxtUPDATE = new Label(shell, SWT.NONE);
        lbltxtUPDATE.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtUPDATE.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtUPDATE.setText("UPDATE :");
		
		txtUPDATE = new Text(shell, SWT.BORDER);
        txtUPDATE.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtUPDATE.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtUPDATE.setText("UPDATE");
        txtUPDATE.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressPhanquyenDlg(e);
			}
		});
		Label lbltxtDELETE = new Label(shell, SWT.NONE);
        lbltxtDELETE.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtDELETE.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtDELETE.setText("DELETE :");
		
		txtDELETE = new Text(shell, SWT.BORDER);
        txtDELETE.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtDELETE.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtDELETE.setText("DELETE");
        txtDELETE.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressPhanquyenDlg(e);
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
				keyPressPhanquyenDlg(e);
			}
		});
		new Label(shell, SWT.NONE);
		btnNewButtonSavePhanquyen = new Button(shell, SWT.NONE);
		btnNewButtonSavePhanquyen.setImage(SWTResourceManager.getImage(PhanquyenDlg.class, "/png/file-2x.png"));
        btnNewButtonSavePhanquyen.setFont(SWTResourceManager.getFont("Tahoma", 12, SWT.BOLD));
		btnNewButtonSavePhanquyen.setText("Save");
        
        btnNewButtonSavePhanquyen.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
                savePhanquyenDlg();
			}
		});
        
        loadPhanquyenDlgData();
	}

    private void savePhanquyenDlg(){
        if(intTypeDlgPhanquyen==TYPE_DLG_VIEW){
            return;
        }
        
        if(objPhanquyen == null){
            objPhanquyen = new Phanquyen();
        }
        if(objPhanquyen!=null){
        // Integer    = true
            objPhanquyen.U_ID = Utils.getInt( txtU_ID.getText() );
            // String     = false
            objPhanquyen.U_NAME = txtU_NAME.getText();
            // String     = false
            objPhanquyen.TABLE_NAME = txtTABLE_NAME.getText();
            // Integer    = true
            objPhanquyen.READ = Utils.getInt( txtREAD.getText() );
            // Integer    = true
            objPhanquyen.INSERT = Utils.getInt( txtINSERT.getText() );
            // Integer    = true
            objPhanquyen.UPDATE = Utils.getInt( txtUPDATE.getText() );
            // Integer    = true
            objPhanquyen.DELETE = Utils.getInt( txtDELETE.getText() );
            // Integer    = true
            objPhanquyen.STS = Utils.getInt( txtSTS.getText() );
            }
        if(DbHelper.checkPhanQuyen(DbHelper.UPDATE, "phanquyen")==false){
            logger.info("DON'T HAVE UPDATE RIGHT");
        }
        else if(DbHelper.checkPhanQuyen(DbHelper.INSERT, "phanquyen")==false){
            logger.info("DON'T HAVE INSERT RIGHT");
        }        
        else{
            objPhanquyen.insert();
        }
        shell.close();
    }
    
    public void setPhanquyenDlgData(Phanquyen obj) {
		this.objPhanquyen = obj;
	}
    
    public void loadPhanquyenDlgData(){
        if(intTypeDlgPhanquyen==TYPE_DLG_VIEW){
            btnNewButtonSavePhanquyen.setEnabled(false);
        }
        else{
            btnNewButtonSavePhanquyen.setEnabled(true);
        }

        if(DbHelper.checkPhanQuyen(DbHelper.READ, "phanquyen")==false){
            logger.info("DON'T HAVE READ RIGHT");
            return;
        }
        if(objPhanquyen != null){
            if(objPhanquyen.U_ID==null)
                txtU_ID.setText("");
            else
                txtU_ID.setText(""+objPhanquyen.U_ID.toString());
            if(objPhanquyen.U_NAME==null)
                txtU_NAME.setText("");
            else
                txtU_NAME.setText(""+objPhanquyen.U_NAME.toString());
            if(objPhanquyen.TABLE_NAME==null)
                txtTABLE_NAME.setText("");
            else
                txtTABLE_NAME.setText(""+objPhanquyen.TABLE_NAME.toString());
            if(objPhanquyen.READ==null)
                txtREAD.setText("");
            else
                txtREAD.setText(""+objPhanquyen.READ.toString());
            if(objPhanquyen.INSERT==null)
                txtINSERT.setText("");
            else
                txtINSERT.setText(""+objPhanquyen.INSERT.toString());
            if(objPhanquyen.UPDATE==null)
                txtUPDATE.setText("");
            else
                txtUPDATE.setText(""+objPhanquyen.UPDATE.toString());
            if(objPhanquyen.DELETE==null)
                txtDELETE.setText("");
            else
                txtDELETE.setText(""+objPhanquyen.DELETE.toString());
            if(objPhanquyen.STS==null)
                txtSTS.setText("");
            else
                txtSTS.setText(""+objPhanquyen.STS.toString());
        }
    }
    
    protected void keyPressPhanquyenDlg(KeyEvent e) {
		if(e.keyCode==13){
			savePhanquyenDlg();
		}
		
	}
}
