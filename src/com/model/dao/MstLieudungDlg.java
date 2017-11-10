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

public class MstLieudungDlg extends Dialog {
	static Logger logger = LogManager.getLogger(MstLieudungDlg.class.getName());

	protected Object result;
	protected Shell shell;
    private Text txtLIEUDUNG_NAME;
    private Text txtRANK;
    private Text txtSTS;

    public MstLieudung objMstLieudung;
    Button btnNewButtonSaveMstLieudung;

    public int intTypeDlgMstLieudung;
	public static final int TYPE_DLG_VIEW = 1;
	public static final int TYPE_DLG_EDIT = 2;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public MstLieudungDlg(Shell parent, int style) {
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
		shell.setText("MstLieudungDlg EDIT/NEW");
		shell.setLayout(new GridLayout(2, false));
        shell.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.keyCode==SWT.ESC){
					objMstLieudung = null;
				}
			}
		});
        
		Label lbltxtLIEUDUNG_NAME = new Label(shell, SWT.NONE);
        lbltxtLIEUDUNG_NAME.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtLIEUDUNG_NAME.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtLIEUDUNG_NAME.setText("LIEUDUNG_NAME :");
		
		txtLIEUDUNG_NAME = new Text(shell, SWT.BORDER);
        txtLIEUDUNG_NAME.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtLIEUDUNG_NAME.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtLIEUDUNG_NAME.setText("LIEUDUNG_NAME");
        txtLIEUDUNG_NAME.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressMstLieudungDlg(e);
			}
		});
		Label lbltxtRANK = new Label(shell, SWT.NONE);
        lbltxtRANK.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtRANK.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtRANK.setText("RANK :");
		
		txtRANK = new Text(shell, SWT.BORDER);
        txtRANK.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtRANK.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtRANK.setText("RANK");
        txtRANK.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressMstLieudungDlg(e);
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
				keyPressMstLieudungDlg(e);
			}
		});
		new Label(shell, SWT.NONE);
		btnNewButtonSaveMstLieudung = new Button(shell, SWT.NONE);
		btnNewButtonSaveMstLieudung.setImage(SWTResourceManager.getImage(MstLieudungDlg.class, "/png/file-2x.png"));
        btnNewButtonSaveMstLieudung.setFont(SWTResourceManager.getFont("Tahoma", 12, SWT.BOLD));
		btnNewButtonSaveMstLieudung.setText("Save");
        
        btnNewButtonSaveMstLieudung.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
                saveMstLieudungDlg();
			}
		});
        
        loadMstLieudungDlgData();
	}

    private void saveMstLieudungDlg(){
        if(intTypeDlgMstLieudung==TYPE_DLG_VIEW){
            return;
        }
        
        if(objMstLieudung == null){
            objMstLieudung = new MstLieudung();
        }
        if(objMstLieudung!=null){
        // String     = false
            objMstLieudung.LIEUDUNG_NAME = txtLIEUDUNG_NAME.getText();
            // Integer    = true
            objMstLieudung.RANK = Utils.getInt( txtRANK.getText() );
            // Integer    = true
            objMstLieudung.STS = Utils.getInt( txtSTS.getText() );
            }
        if(DbHelper.checkPhanQuyen(DbHelper.UPDATE, "mst_lieudung")==false){
            logger.info("DON'T HAVE UPDATE RIGHT");
        }
        else if(DbHelper.checkPhanQuyen(DbHelper.INSERT, "mst_lieudung")==false){
            logger.info("DON'T HAVE INSERT RIGHT");
        }        
        else{
            objMstLieudung.insert();
        }
        shell.close();
    }
    
    public void setMstLieudungDlgData(MstLieudung obj) {
		this.objMstLieudung = obj;
	}
    
    public void loadMstLieudungDlgData(){
        if(intTypeDlgMstLieudung==TYPE_DLG_VIEW){
            btnNewButtonSaveMstLieudung.setEnabled(false);
        }
        else{
            btnNewButtonSaveMstLieudung.setEnabled(true);
        }

        if(DbHelper.checkPhanQuyen(DbHelper.READ, "mst_lieudung")==false){
            logger.info("DON'T HAVE READ RIGHT");
            return;
        }
        if(objMstLieudung != null){
            if(objMstLieudung.LIEUDUNG_NAME==null)
                txtLIEUDUNG_NAME.setText("");
            else
                txtLIEUDUNG_NAME.setText(""+objMstLieudung.LIEUDUNG_NAME.toString());
            if(objMstLieudung.RANK==null)
                txtRANK.setText("");
            else
                txtRANK.setText(""+objMstLieudung.RANK.toString());
            if(objMstLieudung.STS==null)
                txtSTS.setText("");
            else
                txtSTS.setText(""+objMstLieudung.STS.toString());
        }
    }
    
    protected void keyPressMstLieudungDlg(KeyEvent e) {
		if(e.keyCode==13){
			saveMstLieudungDlg();
		}
		
	}
}
