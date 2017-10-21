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

public class DuongdungDlg extends Dialog {
	static Logger logger = LogManager.getLogger(DuongdungDlg.class.getName());

	protected Object result;
	protected Shell shell;
    private Text txtDD_NAME;
    private Text txtDD_MA;

    public Duongdung objDuongdung;
    Button btnNewButtonSaveDuongdung;

    public int intTypeDlgDuongdung;
	public static final int TYPE_DLG_VIEW = 1;
	public static final int TYPE_DLG_EDIT = 2;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public DuongdungDlg(Shell parent, int style) {
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
		shell.setText("DuongdungDlg EDIT/NEW");
		shell.setLayout(new GridLayout(2, false));
        shell.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.keyCode==SWT.ESC){
					objDuongdung = null;
				}
			}
		});
        
		Label lbltxtDD_NAME = new Label(shell, SWT.NONE);
        lbltxtDD_NAME.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtDD_NAME.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtDD_NAME.setText("DD_NAME :");
		
		txtDD_NAME = new Text(shell, SWT.BORDER);
        txtDD_NAME.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtDD_NAME.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtDD_NAME.setText("DD_NAME");
        txtDD_NAME.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressDuongdungDlg(e);
			}
		});
		Label lbltxtDD_MA = new Label(shell, SWT.NONE);
        lbltxtDD_MA.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtDD_MA.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtDD_MA.setText("DD_MA :");
		
		txtDD_MA = new Text(shell, SWT.BORDER);
        txtDD_MA.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtDD_MA.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtDD_MA.setText("DD_MA");
        txtDD_MA.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressDuongdungDlg(e);
			}
		});
		new Label(shell, SWT.NONE);
		btnNewButtonSaveDuongdung = new Button(shell, SWT.NONE);
		btnNewButtonSaveDuongdung.setImage(SWTResourceManager.getImage(DuongdungDlg.class, "/png/file-2x.png"));
        btnNewButtonSaveDuongdung.setFont(SWTResourceManager.getFont("Tahoma", 12, SWT.BOLD));
		btnNewButtonSaveDuongdung.setText("Save");
        
        btnNewButtonSaveDuongdung.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
                saveDuongdungDlg();
			}
		});
        
        loadDuongdungDlgData();
	}

    private void saveDuongdungDlg(){
        if(intTypeDlgDuongdung==TYPE_DLG_VIEW){
            return;
        }
        
        if(objDuongdung == null){
            objDuongdung = new Duongdung();
        }
        if(objDuongdung!=null){
        // String     = false
            objDuongdung.DD_NAME = txtDD_NAME.getText();
            // String     = false
            objDuongdung.DD_MA = txtDD_MA.getText();
            }
        if(DbHelper.checkPhanQuyen(DbHelper.UPDATE, "duongdung")==false){
            logger.info("DON'T HAVE UPDATE RIGHT");
        }
        else if(DbHelper.checkPhanQuyen(DbHelper.INSERT, "duongdung")==false){
            logger.info("DON'T HAVE INSERT RIGHT");
        }        
        else{
            objDuongdung.insert();
        }
        shell.close();
    }
    
    public void setDuongdungDlgData(Duongdung obj) {
		this.objDuongdung = obj;
	}
    
    public void loadDuongdungDlgData(){
        if(intTypeDlgDuongdung==TYPE_DLG_VIEW){
            btnNewButtonSaveDuongdung.setEnabled(false);
        }
        else{
            btnNewButtonSaveDuongdung.setEnabled(true);
        }

        if(DbHelper.checkPhanQuyen(DbHelper.READ, "duongdung")==false){
            logger.info("DON'T HAVE READ RIGHT");
            return;
        }
        if(objDuongdung != null){
            if(objDuongdung.DD_NAME==null)
                txtDD_NAME.setText("");
            else
                txtDD_NAME.setText(""+objDuongdung.DD_NAME.toString());
            if(objDuongdung.DD_MA==null)
                txtDD_MA.setText("");
            else
                txtDD_MA.setText(""+objDuongdung.DD_MA.toString());
        }
    }
    
    protected void keyPressDuongdungDlg(KeyEvent e) {
		if(e.keyCode==13){
			saveDuongdungDlg();
		}
		
	}
}
