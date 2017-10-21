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

public class MstXahuyenDlg extends Dialog {
	static Logger logger = LogManager.getLogger(MstXahuyenDlg.class.getName());

	protected Object result;
	protected Shell shell;
    private Text txtDC_VALUE;
    private Text txtDC_RANK;

    public MstXahuyen objMstXahuyen;
    Button btnNewButtonSaveMstXahuyen;

    public int intTypeDlgMstXahuyen;
	public static final int TYPE_DLG_VIEW = 1;
	public static final int TYPE_DLG_EDIT = 2;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public MstXahuyenDlg(Shell parent, int style) {
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
		shell.setText("MstXahuyenDlg EDIT/NEW");
		shell.setLayout(new GridLayout(2, false));
        shell.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.keyCode==SWT.ESC){
					objMstXahuyen = null;
				}
			}
		});
        
		Label lbltxtDC_VALUE = new Label(shell, SWT.NONE);
        lbltxtDC_VALUE.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtDC_VALUE.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtDC_VALUE.setText("DC_VALUE :");
		
		txtDC_VALUE = new Text(shell, SWT.BORDER);
        txtDC_VALUE.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtDC_VALUE.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtDC_VALUE.setText("DC_VALUE");
        txtDC_VALUE.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressMstXahuyenDlg(e);
			}
		});
		Label lbltxtDC_RANK = new Label(shell, SWT.NONE);
        lbltxtDC_RANK.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtDC_RANK.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtDC_RANK.setText("DC_RANK :");
		
		txtDC_RANK = new Text(shell, SWT.BORDER);
        txtDC_RANK.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtDC_RANK.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtDC_RANK.setText("DC_RANK");
        txtDC_RANK.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressMstXahuyenDlg(e);
			}
		});
		new Label(shell, SWT.NONE);
		btnNewButtonSaveMstXahuyen = new Button(shell, SWT.NONE);
		btnNewButtonSaveMstXahuyen.setImage(SWTResourceManager.getImage(MstXahuyenDlg.class, "/png/file-2x.png"));
        btnNewButtonSaveMstXahuyen.setFont(SWTResourceManager.getFont("Tahoma", 12, SWT.BOLD));
		btnNewButtonSaveMstXahuyen.setText("Save");
        
        btnNewButtonSaveMstXahuyen.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
                saveMstXahuyenDlg();
			}
		});
        
        loadMstXahuyenDlgData();
	}

    private void saveMstXahuyenDlg(){
        if(intTypeDlgMstXahuyen==TYPE_DLG_VIEW){
            return;
        }
        
        if(objMstXahuyen == null){
            objMstXahuyen = new MstXahuyen();
        }
        if(objMstXahuyen!=null){
        // String     = false
            objMstXahuyen.DC_VALUE = txtDC_VALUE.getText();
            // Integer    = true
            objMstXahuyen.DC_RANK = Utils.getInt( txtDC_RANK.getText() );
            }
        if(DbHelper.checkPhanQuyen(DbHelper.UPDATE, "mst_xahuyen")==false){
            logger.info("DON'T HAVE UPDATE RIGHT");
        }
        else if(DbHelper.checkPhanQuyen(DbHelper.INSERT, "mst_xahuyen")==false){
            logger.info("DON'T HAVE INSERT RIGHT");
        }        
        else{
            objMstXahuyen.insert();
        }
        shell.close();
    }
    
    public void setMstXahuyenDlgData(MstXahuyen obj) {
		this.objMstXahuyen = obj;
	}
    
    public void loadMstXahuyenDlgData(){
        if(intTypeDlgMstXahuyen==TYPE_DLG_VIEW){
            btnNewButtonSaveMstXahuyen.setEnabled(false);
        }
        else{
            btnNewButtonSaveMstXahuyen.setEnabled(true);
        }

        if(DbHelper.checkPhanQuyen(DbHelper.READ, "mst_xahuyen")==false){
            logger.info("DON'T HAVE READ RIGHT");
            return;
        }
        if(objMstXahuyen != null){
            if(objMstXahuyen.DC_VALUE==null)
                txtDC_VALUE.setText("");
            else
                txtDC_VALUE.setText(""+objMstXahuyen.DC_VALUE.toString());
            if(objMstXahuyen.DC_RANK==null)
                txtDC_RANK.setText("");
            else
                txtDC_RANK.setText(""+objMstXahuyen.DC_RANK.toString());
        }
    }
    
    protected void keyPressMstXahuyenDlg(KeyEvent e) {
		if(e.keyCode==13){
			saveMstXahuyenDlg();
		}
		
	}
}
