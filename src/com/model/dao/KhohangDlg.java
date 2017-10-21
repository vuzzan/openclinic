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

public class KhohangDlg extends Dialog {
	static Logger logger = LogManager.getLogger(KhohangDlg.class.getName());

	protected Object result;
	protected Shell shell;
    private Text txtKHO_NAME;
    private Text txtSTS;

    public Khohang objKhohang;
    Button btnNewButtonSaveKhohang;

    public int intTypeDlgKhohang;
	public static final int TYPE_DLG_VIEW = 1;
	public static final int TYPE_DLG_EDIT = 2;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public KhohangDlg(Shell parent, int style) {
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
		shell.setText("KhohangDlg EDIT/NEW");
		shell.setLayout(new GridLayout(2, false));
        shell.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.keyCode==SWT.ESC){
					objKhohang = null;
				}
			}
		});
        
		Label lbltxtKHO_NAME = new Label(shell, SWT.NONE);
        lbltxtKHO_NAME.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtKHO_NAME.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtKHO_NAME.setText("KHO_NAME :");
		
		txtKHO_NAME = new Text(shell, SWT.BORDER);
        txtKHO_NAME.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtKHO_NAME.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtKHO_NAME.setText("KHO_NAME");
        txtKHO_NAME.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressKhohangDlg(e);
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
				keyPressKhohangDlg(e);
			}
		});
		new Label(shell, SWT.NONE);
		btnNewButtonSaveKhohang = new Button(shell, SWT.NONE);
		btnNewButtonSaveKhohang.setImage(SWTResourceManager.getImage(KhohangDlg.class, "/png/file-2x.png"));
        btnNewButtonSaveKhohang.setFont(SWTResourceManager.getFont("Tahoma", 12, SWT.BOLD));
		btnNewButtonSaveKhohang.setText("Save");
        
        btnNewButtonSaveKhohang.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
                saveKhohangDlg();
			}
		});
        
        loadKhohangDlgData();
	}

    private void saveKhohangDlg(){
        if(intTypeDlgKhohang==TYPE_DLG_VIEW){
            return;
        }
        
        if(objKhohang == null){
            objKhohang = new Khohang();
        }
        if(objKhohang!=null){
        // String     = false
            objKhohang.KHO_NAME = txtKHO_NAME.getText();
            // Integer    = true
            objKhohang.STS = Utils.getInt( txtSTS.getText() );
            }
        if(DbHelper.checkPhanQuyen(DbHelper.UPDATE, "khohang")==false){
            logger.info("DON'T HAVE UPDATE RIGHT");
        }
        else if(DbHelper.checkPhanQuyen(DbHelper.INSERT, "khohang")==false){
            logger.info("DON'T HAVE INSERT RIGHT");
        }        
        else{
            objKhohang.insert();
        }
        shell.close();
    }
    
    public void setKhohangDlgData(Khohang obj) {
		this.objKhohang = obj;
	}
    
    public void loadKhohangDlgData(){
        if(intTypeDlgKhohang==TYPE_DLG_VIEW){
            btnNewButtonSaveKhohang.setEnabled(false);
        }
        else{
            btnNewButtonSaveKhohang.setEnabled(true);
        }

        if(DbHelper.checkPhanQuyen(DbHelper.READ, "khohang")==false){
            logger.info("DON'T HAVE READ RIGHT");
            return;
        }
        if(objKhohang != null){
            if(objKhohang.KHO_NAME==null)
                txtKHO_NAME.setText("");
            else
                txtKHO_NAME.setText(""+objKhohang.KHO_NAME.toString());
            if(objKhohang.STS==null)
                txtSTS.setText("");
            else
                txtSTS.setText(""+objKhohang.STS.toString());
        }
    }
    
    protected void keyPressKhohangDlg(KeyEvent e) {
		if(e.keyCode==13){
			saveKhohangDlg();
		}
		
	}
}
