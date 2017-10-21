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

public class MaDkbhDlg extends Dialog {
	static Logger logger = LogManager.getLogger(MaDkbhDlg.class.getName());

	protected Object result;
	protected Shell shell;
    private Text txtTEN_DKBD;

    public MaDkbh objMaDkbh;
    Button btnNewButtonSaveMaDkbh;

    public int intTypeDlgMaDkbh;
	public static final int TYPE_DLG_VIEW = 1;
	public static final int TYPE_DLG_EDIT = 2;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public MaDkbhDlg(Shell parent, int style) {
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
		shell.setText("MaDkbhDlg EDIT/NEW");
		shell.setLayout(new GridLayout(2, false));
        shell.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.keyCode==SWT.ESC){
					objMaDkbh = null;
				}
			}
		});
        
		Label lbltxtTEN_DKBD = new Label(shell, SWT.NONE);
        lbltxtTEN_DKBD.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtTEN_DKBD.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtTEN_DKBD.setText("TEN_DKBD :");
		
		txtTEN_DKBD = new Text(shell, SWT.BORDER);
        txtTEN_DKBD.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtTEN_DKBD.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtTEN_DKBD.setText("TEN_DKBD");
        txtTEN_DKBD.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressMaDkbhDlg(e);
			}
		});
		new Label(shell, SWT.NONE);
		btnNewButtonSaveMaDkbh = new Button(shell, SWT.NONE);
		btnNewButtonSaveMaDkbh.setImage(SWTResourceManager.getImage(MaDkbhDlg.class, "/png/file-2x.png"));
        btnNewButtonSaveMaDkbh.setFont(SWTResourceManager.getFont("Tahoma", 12, SWT.BOLD));
		btnNewButtonSaveMaDkbh.setText("Save");
        
        btnNewButtonSaveMaDkbh.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
                saveMaDkbhDlg();
			}
		});
        
        loadMaDkbhDlgData();
	}

    private void saveMaDkbhDlg(){
        if(intTypeDlgMaDkbh==TYPE_DLG_VIEW){
            return;
        }
        
        if(objMaDkbh == null){
            objMaDkbh = new MaDkbh();
        }
        if(objMaDkbh!=null){
        // String     = false
            objMaDkbh.TEN_DKBD = txtTEN_DKBD.getText();
            }
        if(DbHelper.checkPhanQuyen(DbHelper.UPDATE, "ma_dkbh")==false){
            logger.info("DON'T HAVE UPDATE RIGHT");
        }
        else if(DbHelper.checkPhanQuyen(DbHelper.INSERT, "ma_dkbh")==false){
            logger.info("DON'T HAVE INSERT RIGHT");
        }        
        else{
            objMaDkbh.insert();
        }
        shell.close();
    }
    
    public void setMaDkbhDlgData(MaDkbh obj) {
		this.objMaDkbh = obj;
	}
    
    public void loadMaDkbhDlgData(){
        if(intTypeDlgMaDkbh==TYPE_DLG_VIEW){
            btnNewButtonSaveMaDkbh.setEnabled(false);
        }
        else{
            btnNewButtonSaveMaDkbh.setEnabled(true);
        }

        if(DbHelper.checkPhanQuyen(DbHelper.READ, "ma_dkbh")==false){
            logger.info("DON'T HAVE READ RIGHT");
            return;
        }
        if(objMaDkbh != null){
            if(objMaDkbh.TEN_DKBD==null)
                txtTEN_DKBD.setText("");
            else
                txtTEN_DKBD.setText(""+objMaDkbh.TEN_DKBD.toString());
        }
    }
    
    protected void keyPressMaDkbhDlg(KeyEvent e) {
		if(e.keyCode==13){
			saveMaDkbhDlg();
		}
		
	}
}
