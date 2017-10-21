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

public class MaCskcbDlg extends Dialog {
	static Logger logger = LogManager.getLogger(MaCskcbDlg.class.getName());

	protected Object result;
	protected Shell shell;
    private Text txtMA_NAME;
    private Text txtMA_TUYEN;
    private Text txtMA_RANK;

    public MaCskcb objMaCskcb;
    Button btnNewButtonSaveMaCskcb;

    public int intTypeDlgMaCskcb;
	public static final int TYPE_DLG_VIEW = 1;
	public static final int TYPE_DLG_EDIT = 2;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public MaCskcbDlg(Shell parent, int style) {
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
		shell.setText("MaCskcbDlg EDIT/NEW");
		shell.setLayout(new GridLayout(2, false));
        shell.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.keyCode==SWT.ESC){
					objMaCskcb = null;
				}
			}
		});
        
		Label lbltxtMA_NAME = new Label(shell, SWT.NONE);
        lbltxtMA_NAME.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtMA_NAME.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtMA_NAME.setText("MA_NAME :");
		
		txtMA_NAME = new Text(shell, SWT.BORDER);
        txtMA_NAME.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtMA_NAME.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtMA_NAME.setText("MA_NAME");
        txtMA_NAME.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressMaCskcbDlg(e);
			}
		});
		Label lbltxtMA_TUYEN = new Label(shell, SWT.NONE);
        lbltxtMA_TUYEN.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtMA_TUYEN.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtMA_TUYEN.setText("MA_TUYEN :");
		
		txtMA_TUYEN = new Text(shell, SWT.BORDER);
        txtMA_TUYEN.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtMA_TUYEN.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtMA_TUYEN.setText("MA_TUYEN");
        txtMA_TUYEN.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressMaCskcbDlg(e);
			}
		});
		Label lbltxtMA_RANK = new Label(shell, SWT.NONE);
        lbltxtMA_RANK.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtMA_RANK.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtMA_RANK.setText("MA_RANK :");
		
		txtMA_RANK = new Text(shell, SWT.BORDER);
        txtMA_RANK.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtMA_RANK.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtMA_RANK.setText("MA_RANK");
        txtMA_RANK.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressMaCskcbDlg(e);
			}
		});
		new Label(shell, SWT.NONE);
		btnNewButtonSaveMaCskcb = new Button(shell, SWT.NONE);
		btnNewButtonSaveMaCskcb.setImage(SWTResourceManager.getImage(MaCskcbDlg.class, "/png/file-2x.png"));
        btnNewButtonSaveMaCskcb.setFont(SWTResourceManager.getFont("Tahoma", 12, SWT.BOLD));
		btnNewButtonSaveMaCskcb.setText("Save");
        
        btnNewButtonSaveMaCskcb.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
                saveMaCskcbDlg();
			}
		});
        
        loadMaCskcbDlgData();
	}

    private void saveMaCskcbDlg(){
        if(intTypeDlgMaCskcb==TYPE_DLG_VIEW){
            return;
        }
        
        if(objMaCskcb == null){
            objMaCskcb = new MaCskcb();
        }
        if(objMaCskcb!=null){
        // String     = false
            objMaCskcb.MA_NAME = txtMA_NAME.getText();
            // String     = false
            objMaCskcb.MA_TUYEN = txtMA_TUYEN.getText();
            // Integer    = true
            objMaCskcb.MA_RANK = Utils.getInt( txtMA_RANK.getText() );
            }
        if(DbHelper.checkPhanQuyen(DbHelper.UPDATE, "ma_cskcb")==false){
            logger.info("DON'T HAVE UPDATE RIGHT");
        }
        else if(DbHelper.checkPhanQuyen(DbHelper.INSERT, "ma_cskcb")==false){
            logger.info("DON'T HAVE INSERT RIGHT");
        }        
        else{
            objMaCskcb.insert();
        }
        shell.close();
    }
    
    public void setMaCskcbDlgData(MaCskcb obj) {
		this.objMaCskcb = obj;
	}
    
    public void loadMaCskcbDlgData(){
        if(intTypeDlgMaCskcb==TYPE_DLG_VIEW){
            btnNewButtonSaveMaCskcb.setEnabled(false);
        }
        else{
            btnNewButtonSaveMaCskcb.setEnabled(true);
        }

        if(DbHelper.checkPhanQuyen(DbHelper.READ, "ma_cskcb")==false){
            logger.info("DON'T HAVE READ RIGHT");
            return;
        }
        if(objMaCskcb != null){
            if(objMaCskcb.MA_NAME==null)
                txtMA_NAME.setText("");
            else
                txtMA_NAME.setText(""+objMaCskcb.MA_NAME.toString());
            if(objMaCskcb.MA_TUYEN==null)
                txtMA_TUYEN.setText("");
            else
                txtMA_TUYEN.setText(""+objMaCskcb.MA_TUYEN.toString());
            if(objMaCskcb.MA_RANK==null)
                txtMA_RANK.setText("");
            else
                txtMA_RANK.setText(""+objMaCskcb.MA_RANK.toString());
        }
    }
    
    protected void keyPressMaCskcbDlg(KeyEvent e) {
		if(e.keyCode==13){
			saveMaCskcbDlg();
		}
		
	}
}
