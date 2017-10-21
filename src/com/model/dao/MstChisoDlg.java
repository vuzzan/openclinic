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

public class MstChisoDlg extends Dialog {
	static Logger logger = LogManager.getLogger(MstChisoDlg.class.getName());

	protected Object result;
	protected Shell shell;
    private Text txtCS_NAME;
    private Text txtCS_RANGE1;
    private Text txtCS_RANGE2;
    private Text txtCS_DEFAULT;
    private Text txtCS_DESC;
    private Text txtSTS;

    public MstChiso objMstChiso;
    Button btnNewButtonSaveMstChiso;

    public int intTypeDlgMstChiso;
	public static final int TYPE_DLG_VIEW = 1;
	public static final int TYPE_DLG_EDIT = 2;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public MstChisoDlg(Shell parent, int style) {
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
		shell.setText("MstChisoDlg EDIT/NEW");
		shell.setLayout(new GridLayout(2, false));
        shell.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.keyCode==SWT.ESC){
					objMstChiso = null;
				}
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
				keyPressMstChisoDlg(e);
			}
		});
		Label lbltxtCS_RANGE1 = new Label(shell, SWT.NONE);
        lbltxtCS_RANGE1.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtCS_RANGE1.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtCS_RANGE1.setText("CS_RANGE1 :");
		
		txtCS_RANGE1 = new Text(shell, SWT.BORDER);
        txtCS_RANGE1.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtCS_RANGE1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtCS_RANGE1.setText("CS_RANGE1");
        txtCS_RANGE1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressMstChisoDlg(e);
			}
		});
		Label lbltxtCS_RANGE2 = new Label(shell, SWT.NONE);
        lbltxtCS_RANGE2.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtCS_RANGE2.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtCS_RANGE2.setText("CS_RANGE2 :");
		
		txtCS_RANGE2 = new Text(shell, SWT.BORDER);
        txtCS_RANGE2.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtCS_RANGE2.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtCS_RANGE2.setText("CS_RANGE2");
        txtCS_RANGE2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressMstChisoDlg(e);
			}
		});
		Label lbltxtCS_DEFAULT = new Label(shell, SWT.NONE);
        lbltxtCS_DEFAULT.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtCS_DEFAULT.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtCS_DEFAULT.setText("CS_DEFAULT :");
		
		txtCS_DEFAULT = new Text(shell, SWT.BORDER);
        txtCS_DEFAULT.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtCS_DEFAULT.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtCS_DEFAULT.setText("CS_DEFAULT");
        txtCS_DEFAULT.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressMstChisoDlg(e);
			}
		});
		Label lbltxtCS_DESC = new Label(shell, SWT.NONE);
        lbltxtCS_DESC.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtCS_DESC.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtCS_DESC.setText("CS_DESC :");
		
		txtCS_DESC = new Text(shell, SWT.BORDER);
        txtCS_DESC.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtCS_DESC.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtCS_DESC.setText("CS_DESC");
        txtCS_DESC.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressMstChisoDlg(e);
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
				keyPressMstChisoDlg(e);
			}
		});
		new Label(shell, SWT.NONE);
		btnNewButtonSaveMstChiso = new Button(shell, SWT.NONE);
		btnNewButtonSaveMstChiso.setImage(SWTResourceManager.getImage(MstChisoDlg.class, "/png/file-2x.png"));
        btnNewButtonSaveMstChiso.setFont(SWTResourceManager.getFont("Tahoma", 12, SWT.BOLD));
		btnNewButtonSaveMstChiso.setText("Save");
        
        btnNewButtonSaveMstChiso.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
                saveMstChisoDlg();
			}
		});
        
        loadMstChisoDlgData();
	}

    private void saveMstChisoDlg(){
        if(intTypeDlgMstChiso==TYPE_DLG_VIEW){
            return;
        }
        
        if(objMstChiso == null){
            objMstChiso = new MstChiso();
        }
        if(objMstChiso!=null){
        // String     = false
            objMstChiso.CS_NAME = txtCS_NAME.getText();
            // String     = false
            objMstChiso.CS_RANGE1 = txtCS_RANGE1.getText();
            // String     = false
            objMstChiso.CS_RANGE2 = txtCS_RANGE2.getText();
            // String     = false
            objMstChiso.CS_DEFAULT = txtCS_DEFAULT.getText();
            // String     = false
            objMstChiso.CS_DESC = txtCS_DESC.getText();
            // Integer    = true
            objMstChiso.STS = Utils.getInt( txtSTS.getText() );
            }
        if(DbHelper.checkPhanQuyen(DbHelper.UPDATE, "mst_chiso")==false){
            logger.info("DON'T HAVE UPDATE RIGHT");
        }
        else if(DbHelper.checkPhanQuyen(DbHelper.INSERT, "mst_chiso")==false){
            logger.info("DON'T HAVE INSERT RIGHT");
        }        
        else{
            objMstChiso.insert();
        }
        shell.close();
    }
    
    public void setMstChisoDlgData(MstChiso obj) {
		this.objMstChiso = obj;
	}
    
    public void loadMstChisoDlgData(){
        if(intTypeDlgMstChiso==TYPE_DLG_VIEW){
            btnNewButtonSaveMstChiso.setEnabled(false);
        }
        else{
            btnNewButtonSaveMstChiso.setEnabled(true);
        }

        if(DbHelper.checkPhanQuyen(DbHelper.READ, "mst_chiso")==false){
            logger.info("DON'T HAVE READ RIGHT");
            return;
        }
        if(objMstChiso != null){
            if(objMstChiso.CS_NAME==null)
                txtCS_NAME.setText("");
            else
                txtCS_NAME.setText(""+objMstChiso.CS_NAME.toString());
            if(objMstChiso.CS_RANGE1==null)
                txtCS_RANGE1.setText("");
            else
                txtCS_RANGE1.setText(""+objMstChiso.CS_RANGE1.toString());
            if(objMstChiso.CS_RANGE2==null)
                txtCS_RANGE2.setText("");
            else
                txtCS_RANGE2.setText(""+objMstChiso.CS_RANGE2.toString());
            if(objMstChiso.CS_DEFAULT==null)
                txtCS_DEFAULT.setText("");
            else
                txtCS_DEFAULT.setText(""+objMstChiso.CS_DEFAULT.toString());
            if(objMstChiso.CS_DESC==null)
                txtCS_DESC.setText("");
            else
                txtCS_DESC.setText(""+objMstChiso.CS_DESC.toString());
            if(objMstChiso.STS==null)
                txtSTS.setText("");
            else
                txtSTS.setText(""+objMstChiso.STS.toString());
        }
    }
    
    protected void keyPressMstChisoDlg(KeyEvent e) {
		if(e.keyCode==13){
			saveMstChisoDlg();
		}
		
	}
}
