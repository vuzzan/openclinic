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

public class ConfigurationDlg extends Dialog {
	static Logger logger = LogManager.getLogger(ConfigurationDlg.class.getName());

	protected Object result;
	protected Shell shell;
    private Text txtCONF_NAME;
    private Text txtCONF_VALUE;
    private Text txtSTS;

    public Configuration objConfiguration;
    Button btnNewButtonSaveConfiguration;

    public int intTypeDlgConfiguration;
	public static final int TYPE_DLG_VIEW = 1;
	public static final int TYPE_DLG_EDIT = 2;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public ConfigurationDlg(Shell parent, int style) {
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
		shell.setText("ConfigurationDlg EDIT/NEW");
		shell.setLayout(new GridLayout(2, false));
        shell.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.keyCode==SWT.ESC){
					objConfiguration = null;
				}
			}
		});
        
		Label lbltxtCONF_NAME = new Label(shell, SWT.NONE);
        lbltxtCONF_NAME.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtCONF_NAME.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtCONF_NAME.setText("CONF_NAME :");
		
		txtCONF_NAME = new Text(shell, SWT.BORDER);
        txtCONF_NAME.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtCONF_NAME.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtCONF_NAME.setText("CONF_NAME");
        txtCONF_NAME.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressConfigurationDlg(e);
			}
		});
		Label lbltxtCONF_VALUE = new Label(shell, SWT.NONE);
        lbltxtCONF_VALUE.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtCONF_VALUE.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtCONF_VALUE.setText("CONF_VALUE :");
		
		txtCONF_VALUE = new Text(shell, SWT.BORDER);
        txtCONF_VALUE.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtCONF_VALUE.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtCONF_VALUE.setText("CONF_VALUE");
        txtCONF_VALUE.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressConfigurationDlg(e);
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
				keyPressConfigurationDlg(e);
			}
		});
		new Label(shell, SWT.NONE);
		btnNewButtonSaveConfiguration = new Button(shell, SWT.NONE);
		btnNewButtonSaveConfiguration.setImage(SWTResourceManager.getImage(ConfigurationDlg.class, "/png/file-2x.png"));
        btnNewButtonSaveConfiguration.setFont(SWTResourceManager.getFont("Tahoma", 12, SWT.BOLD));
		btnNewButtonSaveConfiguration.setText("Save");
        
        btnNewButtonSaveConfiguration.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
                saveConfigurationDlg();
			}
		});
        
        loadConfigurationDlgData();
	}

    private void saveConfigurationDlg(){
        if(intTypeDlgConfiguration==TYPE_DLG_VIEW){
            return;
        }
        
        if(objConfiguration == null){
            objConfiguration = new Configuration();
        }
        if(objConfiguration!=null){
        // String     = false
            objConfiguration.CONF_NAME = txtCONF_NAME.getText();
            // String     = false
            objConfiguration.CONF_VALUE = txtCONF_VALUE.getText();
            // Integer    = true
            objConfiguration.STS = Utils.getInt( txtSTS.getText() );
            }
        if(DbHelper.checkPhanQuyen(DbHelper.UPDATE, "configuration")==false){
            logger.info("DON'T HAVE UPDATE RIGHT");
        }
        else if(DbHelper.checkPhanQuyen(DbHelper.INSERT, "configuration")==false){
            logger.info("DON'T HAVE INSERT RIGHT");
        }        
        else{
            objConfiguration.insert();
        }
        shell.close();
    }
    
    public void setConfigurationDlgData(Configuration obj) {
		this.objConfiguration = obj;
	}
    
    public void loadConfigurationDlgData(){
        if(intTypeDlgConfiguration==TYPE_DLG_VIEW){
            btnNewButtonSaveConfiguration.setEnabled(false);
        }
        else{
            btnNewButtonSaveConfiguration.setEnabled(true);
        }

        if(DbHelper.checkPhanQuyen(DbHelper.READ, "configuration")==false){
            logger.info("DON'T HAVE READ RIGHT");
            return;
        }
        if(objConfiguration != null){
            if(objConfiguration.CONF_NAME==null)
                txtCONF_NAME.setText("");
            else
                txtCONF_NAME.setText(""+objConfiguration.CONF_NAME.toString());
            if(objConfiguration.CONF_VALUE==null)
                txtCONF_VALUE.setText("");
            else
                txtCONF_VALUE.setText(""+objConfiguration.CONF_VALUE.toString());
            if(objConfiguration.STS==null)
                txtSTS.setText("");
            else
                txtSTS.setText(""+objConfiguration.STS.toString());
        }
    }
    
    protected void keyPressConfigurationDlg(KeyEvent e) {
		if(e.keyCode==13){
			saveConfigurationDlg();
		}
		
	}
}
