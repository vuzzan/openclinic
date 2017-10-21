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

public class MabenhDlg extends Dialog {
	static Logger logger = LogManager.getLogger(MabenhDlg.class.getName());

	protected Object result;
	protected Shell shell;
    private Text txtMABENH_NAME;
    private Text txtMABENH_RANK;

    public Mabenh objMabenh;
    Button btnNewButtonSaveMabenh;

    public int intTypeDlgMabenh;
	public static final int TYPE_DLG_VIEW = 1;
	public static final int TYPE_DLG_EDIT = 2;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public MabenhDlg(Shell parent, int style) {
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
		shell.setText("MabenhDlg EDIT/NEW");
		shell.setLayout(new GridLayout(2, false));
        shell.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.keyCode==SWT.ESC){
					objMabenh = null;
				}
			}
		});
        
		Label lbltxtMABENH_NAME = new Label(shell, SWT.NONE);
        lbltxtMABENH_NAME.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtMABENH_NAME.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtMABENH_NAME.setText("MABENH_NAME :");
		
		txtMABENH_NAME = new Text(shell, SWT.BORDER);
        txtMABENH_NAME.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtMABENH_NAME.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtMABENH_NAME.setText("MABENH_NAME");
        txtMABENH_NAME.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressMabenhDlg(e);
			}
		});
		Label lbltxtMABENH_RANK = new Label(shell, SWT.NONE);
        lbltxtMABENH_RANK.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtMABENH_RANK.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtMABENH_RANK.setText("MABENH_RANK :");
		
		txtMABENH_RANK = new Text(shell, SWT.BORDER);
        txtMABENH_RANK.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtMABENH_RANK.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtMABENH_RANK.setText("MABENH_RANK");
        txtMABENH_RANK.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressMabenhDlg(e);
			}
		});
		new Label(shell, SWT.NONE);
		btnNewButtonSaveMabenh = new Button(shell, SWT.NONE);
		btnNewButtonSaveMabenh.setImage(SWTResourceManager.getImage(MabenhDlg.class, "/png/file-2x.png"));
        btnNewButtonSaveMabenh.setFont(SWTResourceManager.getFont("Tahoma", 12, SWT.BOLD));
		btnNewButtonSaveMabenh.setText("Save");
        
        btnNewButtonSaveMabenh.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
                saveMabenhDlg();
			}
		});
        
        loadMabenhDlgData();
	}

    private void saveMabenhDlg(){
        if(intTypeDlgMabenh==TYPE_DLG_VIEW){
            return;
        }
        
        if(objMabenh == null){
            objMabenh = new Mabenh();
        }
        if(objMabenh!=null){
        // String     = false
            objMabenh.MABENH_NAME = txtMABENH_NAME.getText();
            // Integer    = true
            objMabenh.MABENH_RANK = Utils.getInt( txtMABENH_RANK.getText() );
            }
        if(DbHelper.checkPhanQuyen(DbHelper.UPDATE, "mabenh")==false){
            logger.info("DON'T HAVE UPDATE RIGHT");
        }
        else if(DbHelper.checkPhanQuyen(DbHelper.INSERT, "mabenh")==false){
            logger.info("DON'T HAVE INSERT RIGHT");
        }        
        else{
            objMabenh.insert();
        }
        shell.close();
    }
    
    public void setMabenhDlgData(Mabenh obj) {
		this.objMabenh = obj;
	}
    
    public void loadMabenhDlgData(){
        if(intTypeDlgMabenh==TYPE_DLG_VIEW){
            btnNewButtonSaveMabenh.setEnabled(false);
        }
        else{
            btnNewButtonSaveMabenh.setEnabled(true);
        }

        if(DbHelper.checkPhanQuyen(DbHelper.READ, "mabenh")==false){
            logger.info("DON'T HAVE READ RIGHT");
            return;
        }
        if(objMabenh != null){
            if(objMabenh.MABENH_NAME==null)
                txtMABENH_NAME.setText("");
            else
                txtMABENH_NAME.setText(""+objMabenh.MABENH_NAME.toString());
            if(objMabenh.MABENH_RANK==null)
                txtMABENH_RANK.setText("");
            else
                txtMABENH_RANK.setText(""+objMabenh.MABENH_RANK.toString());
        }
    }
    
    protected void keyPressMabenhDlg(KeyEvent e) {
		if(e.keyCode==13){
			saveMabenhDlg();
		}
		
	}
}
