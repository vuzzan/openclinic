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

public class ChisoxnDlg extends Dialog {
	static Logger logger = LogManager.getLogger(ChisoxnDlg.class.getName());

	protected Object result;
	protected Shell shell;
    private Text txtDV_ID;
    private Text txtCS_ID;
    private Text txtMA_DVKT;
    private Text txtTEN_DVKT;
    private Text txtCS_NAME;
    private Text txtSTS;

    public Chisoxn objChisoxn;
    Button btnNewButtonSaveChisoxn;

    public int intTypeDlgChisoxn;
	public static final int TYPE_DLG_VIEW = 1;
	public static final int TYPE_DLG_EDIT = 2;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public ChisoxnDlg(Shell parent, int style) {
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
		shell.setText("ChisoxnDlg EDIT/NEW");
		shell.setLayout(new GridLayout(2, false));
        shell.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.keyCode==SWT.ESC){
					objChisoxn = null;
				}
			}
		});
        
		Label lbltxtDV_ID = new Label(shell, SWT.NONE);
        lbltxtDV_ID.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtDV_ID.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtDV_ID.setText("DV_ID :");
		
		txtDV_ID = new Text(shell, SWT.BORDER);
        txtDV_ID.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtDV_ID.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtDV_ID.setText("DV_ID");
        txtDV_ID.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressChisoxnDlg(e);
			}
		});
		Label lbltxtCS_ID = new Label(shell, SWT.NONE);
        lbltxtCS_ID.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtCS_ID.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtCS_ID.setText("CS_ID :");
		
		txtCS_ID = new Text(shell, SWT.BORDER);
        txtCS_ID.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtCS_ID.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtCS_ID.setText("CS_ID");
        txtCS_ID.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressChisoxnDlg(e);
			}
		});
		Label lbltxtMA_DVKT = new Label(shell, SWT.NONE);
        lbltxtMA_DVKT.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtMA_DVKT.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtMA_DVKT.setText("MA_DVKT :");
		
		txtMA_DVKT = new Text(shell, SWT.BORDER);
        txtMA_DVKT.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtMA_DVKT.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtMA_DVKT.setText("MA_DVKT");
        txtMA_DVKT.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressChisoxnDlg(e);
			}
		});
		Label lbltxtTEN_DVKT = new Label(shell, SWT.NONE);
        lbltxtTEN_DVKT.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtTEN_DVKT.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtTEN_DVKT.setText("TEN_DVKT :");
		
		txtTEN_DVKT = new Text(shell, SWT.BORDER);
        txtTEN_DVKT.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtTEN_DVKT.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtTEN_DVKT.setText("TEN_DVKT");
        txtTEN_DVKT.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressChisoxnDlg(e);
			}
		});
		Label lbltxtCS_NAME = new Label(shell, SWT.NONE);
        lbltxtCS_NAME.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtCS_NAME.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtCS_NAME.setText("CS_NAME :");
		
		txtCS_NAME = new Text(shell, SWT.BORDER);
        txtCS_NAME.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtCS_NAME.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtCS_NAME.setText("CS_NAME");
        txtCS_NAME.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressChisoxnDlg(e);
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
				keyPressChisoxnDlg(e);
			}
		});
		new Label(shell, SWT.NONE);
		btnNewButtonSaveChisoxn = new Button(shell, SWT.NONE);
		btnNewButtonSaveChisoxn.setImage(SWTResourceManager.getImage(ChisoxnDlg.class, "/png/file-2x.png"));
        btnNewButtonSaveChisoxn.setFont(SWTResourceManager.getFont("Tahoma", 12, SWT.BOLD));
		btnNewButtonSaveChisoxn.setText("Save");
        
        btnNewButtonSaveChisoxn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
                saveChisoxnDlg();
			}
		});
        
        loadChisoxnDlgData();
	}

    private void saveChisoxnDlg(){
        if(intTypeDlgChisoxn==TYPE_DLG_VIEW){
            return;
        }
        
        if(objChisoxn == null){
            objChisoxn = new Chisoxn();
        }
        if(objChisoxn!=null){
        // Integer    = true
            objChisoxn.DV_ID = Utils.getInt( txtDV_ID.getText() );
            // Integer    = true
            objChisoxn.CS_ID = Utils.getInt( txtCS_ID.getText() );
            // String     = false
            objChisoxn.MA_DVKT = txtMA_DVKT.getText();
            // String     = false
            objChisoxn.TEN_DVKT = txtTEN_DVKT.getText();
            // String     = false
            objChisoxn.CS_NAME = txtCS_NAME.getText();
            // Integer    = true
            objChisoxn.STS = Utils.getInt( txtSTS.getText() );
            }
        if(DbHelper.checkPhanQuyen(DbHelper.UPDATE, "chisoxn")==false){
            logger.info("DON'T HAVE UPDATE RIGHT");
        }
        else if(DbHelper.checkPhanQuyen(DbHelper.INSERT, "chisoxn")==false){
            logger.info("DON'T HAVE INSERT RIGHT");
        }        
        else{
            objChisoxn.insert();
        }
        shell.close();
    }
    
    public void setChisoxnDlgData(Chisoxn obj) {
		this.objChisoxn = obj;
	}
    
    public void loadChisoxnDlgData(){
        if(intTypeDlgChisoxn==TYPE_DLG_VIEW){
            btnNewButtonSaveChisoxn.setEnabled(false);
        }
        else{
            btnNewButtonSaveChisoxn.setEnabled(true);
        }

        if(DbHelper.checkPhanQuyen(DbHelper.READ, "chisoxn")==false){
            logger.info("DON'T HAVE READ RIGHT");
            return;
        }
        if(objChisoxn != null){
            if(objChisoxn.DV_ID==null)
                txtDV_ID.setText("");
            else
                txtDV_ID.setText(""+objChisoxn.DV_ID.toString());
            if(objChisoxn.CS_ID==null)
                txtCS_ID.setText("");
            else
                txtCS_ID.setText(""+objChisoxn.CS_ID.toString());
            if(objChisoxn.MA_DVKT==null)
                txtMA_DVKT.setText("");
            else
                txtMA_DVKT.setText(""+objChisoxn.MA_DVKT.toString());
            if(objChisoxn.TEN_DVKT==null)
                txtTEN_DVKT.setText("");
            else
                txtTEN_DVKT.setText(""+objChisoxn.TEN_DVKT.toString());
            if(objChisoxn.CS_NAME==null)
                txtCS_NAME.setText("");
            else
                txtCS_NAME.setText(""+objChisoxn.CS_NAME.toString());
            if(objChisoxn.STS==null)
                txtSTS.setText("");
            else
                txtSTS.setText(""+objChisoxn.STS.toString());
        }
    }
    
    protected void keyPressChisoxnDlg(KeyEvent e) {
		if(e.keyCode==13){
			saveChisoxnDlg();
		}
		
	}
}
