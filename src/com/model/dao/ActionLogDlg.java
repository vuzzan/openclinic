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

public class ActionLogDlg extends Dialog {
	static Logger logger = LogManager.getLogger(ActionLogDlg.class.getName());

	protected Object result;
	protected Shell shell;
    private Text txtu_id;
    private Text txtu_time;
    private Text txtu_action;
    private Text txtdbtable;
    private Text txtactionid;
    private Text txtfieldid;

    public ActionLog objActionLog;
    Button btnNewButtonSaveActionLog;

    public int intTypeDlgActionLog;
	public static final int TYPE_DLG_VIEW = 1;
	public static final int TYPE_DLG_EDIT = 2;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public ActionLogDlg(Shell parent, int style) {
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
		shell.setText("ActionLogDlg EDIT/NEW");
		shell.setLayout(new GridLayout(2, false));
        shell.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.keyCode==SWT.ESC){
					objActionLog = null;
				}
			}
		});
        
		Label lbltxtu_id = new Label(shell, SWT.NONE);
        lbltxtu_id.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtu_id.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtu_id.setText("u_id :");
		
		txtu_id = new Text(shell, SWT.BORDER);
        txtu_id.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtu_id.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtu_id.setText("u_id");
        txtu_id.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressActionLogDlg(e);
			}
		});
		Label lbltxtu_time = new Label(shell, SWT.NONE);
        lbltxtu_time.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtu_time.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtu_time.setText("u_time :");
		
		txtu_time = new Text(shell, SWT.BORDER);
        txtu_time.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtu_time.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtu_time.setText("u_time");
        txtu_time.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressActionLogDlg(e);
			}
		});
		Label lbltxtu_action = new Label(shell, SWT.NONE);
        lbltxtu_action.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtu_action.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtu_action.setText("u_action :");
		
		txtu_action = new Text(shell, SWT.BORDER);
        txtu_action.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtu_action.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtu_action.setText("u_action");
        txtu_action.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressActionLogDlg(e);
			}
		});
		Label lbltxtdbtable = new Label(shell, SWT.NONE);
        lbltxtdbtable.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtdbtable.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtdbtable.setText("dbtable :");
		
		txtdbtable = new Text(shell, SWT.BORDER);
        txtdbtable.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtdbtable.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtdbtable.setText("dbtable");
        txtdbtable.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressActionLogDlg(e);
			}
		});
		Label lbltxtactionid = new Label(shell, SWT.NONE);
        lbltxtactionid.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtactionid.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtactionid.setText("actionid :");
		
		txtactionid = new Text(shell, SWT.BORDER);
        txtactionid.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtactionid.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtactionid.setText("actionid");
        txtactionid.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressActionLogDlg(e);
			}
		});
		Label lbltxtfieldid = new Label(shell, SWT.NONE);
        lbltxtfieldid.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtfieldid.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtfieldid.setText("fieldid :");
		
		txtfieldid = new Text(shell, SWT.BORDER);
        txtfieldid.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtfieldid.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtfieldid.setText("fieldid");
        txtfieldid.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressActionLogDlg(e);
			}
		});
		new Label(shell, SWT.NONE);
		btnNewButtonSaveActionLog = new Button(shell, SWT.NONE);
		btnNewButtonSaveActionLog.setImage(SWTResourceManager.getImage(ActionLogDlg.class, "/png/file-2x.png"));
        btnNewButtonSaveActionLog.setFont(SWTResourceManager.getFont("Tahoma", 12, SWT.BOLD));
		btnNewButtonSaveActionLog.setText("Save");
        
        btnNewButtonSaveActionLog.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
                saveActionLogDlg();
			}
		});
        
        loadActionLogDlgData();
	}

    private void saveActionLogDlg(){
        if(intTypeDlgActionLog==TYPE_DLG_VIEW){
            return;
        }
        
        if(objActionLog == null){
            objActionLog = new ActionLog();
        }
        if(objActionLog!=null){
        // Integer    = true
            objActionLog.u_id = Utils.getInt( txtu_id.getText() );
            // Date       = false
            // objActionLog.u_time = txtu_time.getText();
            // String     = false
            objActionLog.u_action = txtu_action.getText();
            // String     = false
            objActionLog.dbtable = txtdbtable.getText();
            // Integer    = true
            objActionLog.actionid = Utils.getInt( txtactionid.getText() );
            // Integer    = true
            objActionLog.fieldid = Utils.getInt( txtfieldid.getText() );
            }
        if(DbHelper.checkPhanQuyen(DbHelper.UPDATE, "action_log")==false){
            logger.info("DON'T HAVE UPDATE RIGHT");
        }
        else if(DbHelper.checkPhanQuyen(DbHelper.INSERT, "action_log")==false){
            logger.info("DON'T HAVE INSERT RIGHT");
        }        
        else{
            objActionLog.insert();
        }
        shell.close();
    }
    
    public void setActionLogDlgData(ActionLog obj) {
		this.objActionLog = obj;
	}
    
    public void loadActionLogDlgData(){
        if(intTypeDlgActionLog==TYPE_DLG_VIEW){
            btnNewButtonSaveActionLog.setEnabled(false);
        }
        else{
            btnNewButtonSaveActionLog.setEnabled(true);
        }

        if(DbHelper.checkPhanQuyen(DbHelper.READ, "action_log")==false){
            logger.info("DON'T HAVE READ RIGHT");
            return;
        }
        if(objActionLog != null){
            if(objActionLog.u_id==null)
                txtu_id.setText("");
            else
                txtu_id.setText(""+objActionLog.u_id.toString());
            if(objActionLog.u_time==null)
                txtu_time.setText("");
            else
                txtu_time.setText(""+objActionLog.u_time.toString());
            if(objActionLog.u_action==null)
                txtu_action.setText("");
            else
                txtu_action.setText(""+objActionLog.u_action.toString());
            if(objActionLog.dbtable==null)
                txtdbtable.setText("");
            else
                txtdbtable.setText(""+objActionLog.dbtable.toString());
            if(objActionLog.actionid==null)
                txtactionid.setText("");
            else
                txtactionid.setText(""+objActionLog.actionid.toString());
            if(objActionLog.fieldid==null)
                txtfieldid.setText("");
            else
                txtfieldid.setText(""+objActionLog.fieldid.toString());
        }
    }
    
    protected void keyPressActionLogDlg(KeyEvent e) {
		if(e.keyCode==13){
			saveActionLogDlg();
		}
		
	}
}
