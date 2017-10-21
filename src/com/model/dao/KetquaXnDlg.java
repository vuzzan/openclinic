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

public class KetquaXnDlg extends Dialog {
	static Logger logger = LogManager.getLogger(KetquaXnDlg.class.getName());

	protected Object result;
	protected Shell shell;
    private Text txtCS_ID;
    private Text txtDV_ID;
    private Text txtMA_DVKT;
    private Text txtTEN_DVKT;
    private Text txtCS_NAME;
    private Text txtCS_VALUE;
    private Text txtCS_RANGE1;
    private Text txtCS_RANGE2;
    private Text txtCS_DEFAULT;
    private Text txtIMAGE_URL;
    private Text txtMA_MAY;
    private Text txtMO_TA;
    private Text txtKET_LUAN;
    private Text txtSTS;

    public KetquaXn objKetquaXn;
    Button btnNewButtonSaveKetquaXn;

    public int intTypeDlgKetquaXn;
	public static final int TYPE_DLG_VIEW = 1;
	public static final int TYPE_DLG_EDIT = 2;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public KetquaXnDlg(Shell parent, int style) {
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
		shell.setText("KetquaXnDlg EDIT/NEW");
		shell.setLayout(new GridLayout(2, false));
        shell.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.keyCode==SWT.ESC){
					objKetquaXn = null;
				}
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
				keyPressKetquaXnDlg(e);
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
				keyPressKetquaXnDlg(e);
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
				keyPressKetquaXnDlg(e);
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
				keyPressKetquaXnDlg(e);
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
				keyPressKetquaXnDlg(e);
			}
		});
		Label lbltxtCS_VALUE = new Label(shell, SWT.NONE);
        lbltxtCS_VALUE.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtCS_VALUE.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtCS_VALUE.setText("CS_VALUE :");
		
		txtCS_VALUE = new Text(shell, SWT.BORDER);
        txtCS_VALUE.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtCS_VALUE.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtCS_VALUE.setText("CS_VALUE");
        txtCS_VALUE.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressKetquaXnDlg(e);
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
				keyPressKetquaXnDlg(e);
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
				keyPressKetquaXnDlg(e);
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
				keyPressKetquaXnDlg(e);
			}
		});
		Label lbltxtIMAGE_URL = new Label(shell, SWT.NONE);
        lbltxtIMAGE_URL.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtIMAGE_URL.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtIMAGE_URL.setText("IMAGE_URL :");
		
		txtIMAGE_URL = new Text(shell, SWT.BORDER);
        txtIMAGE_URL.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtIMAGE_URL.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtIMAGE_URL.setText("IMAGE_URL");
        txtIMAGE_URL.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressKetquaXnDlg(e);
			}
		});
		Label lbltxtMA_MAY = new Label(shell, SWT.NONE);
        lbltxtMA_MAY.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtMA_MAY.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtMA_MAY.setText("MA_MAY :");
		
		txtMA_MAY = new Text(shell, SWT.BORDER);
        txtMA_MAY.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtMA_MAY.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtMA_MAY.setText("MA_MAY");
        txtMA_MAY.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressKetquaXnDlg(e);
			}
		});
		Label lbltxtMO_TA = new Label(shell, SWT.NONE);
        lbltxtMO_TA.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtMO_TA.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtMO_TA.setText("MO_TA :");
		
		txtMO_TA = new Text(shell, SWT.BORDER);
        txtMO_TA.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtMO_TA.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtMO_TA.setText("MO_TA");
        txtMO_TA.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressKetquaXnDlg(e);
			}
		});
		Label lbltxtKET_LUAN = new Label(shell, SWT.NONE);
        lbltxtKET_LUAN.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtKET_LUAN.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtKET_LUAN.setText("KET_LUAN :");
		
		txtKET_LUAN = new Text(shell, SWT.BORDER);
        txtKET_LUAN.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtKET_LUAN.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtKET_LUAN.setText("KET_LUAN");
        txtKET_LUAN.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressKetquaXnDlg(e);
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
				keyPressKetquaXnDlg(e);
			}
		});
		new Label(shell, SWT.NONE);
		btnNewButtonSaveKetquaXn = new Button(shell, SWT.NONE);
		btnNewButtonSaveKetquaXn.setImage(SWTResourceManager.getImage(KetquaXnDlg.class, "/png/file-2x.png"));
        btnNewButtonSaveKetquaXn.setFont(SWTResourceManager.getFont("Tahoma", 12, SWT.BOLD));
		btnNewButtonSaveKetquaXn.setText("Save");
        
        btnNewButtonSaveKetquaXn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
                saveKetquaXnDlg();
			}
		});
        
        loadKetquaXnDlgData();
	}

    private void saveKetquaXnDlg(){
        if(intTypeDlgKetquaXn==TYPE_DLG_VIEW){
            return;
        }
        
        if(objKetquaXn == null){
            objKetquaXn = new KetquaXn();
        }
        if(objKetquaXn!=null){
        // Integer    = true
            objKetquaXn.CS_ID = Utils.getInt( txtCS_ID.getText() );
            // Integer    = true
            objKetquaXn.DV_ID = Utils.getInt( txtDV_ID.getText() );
            // String     = false
            objKetquaXn.MA_DVKT = txtMA_DVKT.getText();
            // String     = false
            objKetquaXn.TEN_DVKT = txtTEN_DVKT.getText();
            // String     = false
            objKetquaXn.CS_NAME = txtCS_NAME.getText();
            // String     = false
            objKetquaXn.CS_VALUE = txtCS_VALUE.getText();
            // String     = false
            objKetquaXn.CS_RANGE1 = txtCS_RANGE1.getText();
            // String     = false
            objKetquaXn.CS_RANGE2 = txtCS_RANGE2.getText();
            // String     = false
            objKetquaXn.CS_DEFAULT = txtCS_DEFAULT.getText();
            // String     = false
            objKetquaXn.IMAGE_URL = txtIMAGE_URL.getText();
            // String     = false
            objKetquaXn.MA_MAY = txtMA_MAY.getText();
            // String     = false
            objKetquaXn.MO_TA = txtMO_TA.getText();
            // String     = false
            objKetquaXn.KET_LUAN = txtKET_LUAN.getText();
            // Integer    = true
            objKetquaXn.STS = Utils.getInt( txtSTS.getText() );
            }
        if(DbHelper.checkPhanQuyen(DbHelper.UPDATE, "ketqua_xn")==false){
            logger.info("DON'T HAVE UPDATE RIGHT");
        }
        else if(DbHelper.checkPhanQuyen(DbHelper.INSERT, "ketqua_xn")==false){
            logger.info("DON'T HAVE INSERT RIGHT");
        }        
        else{
            objKetquaXn.insert();
        }
        shell.close();
    }
    
    public void setKetquaXnDlgData(KetquaXn obj) {
		this.objKetquaXn = obj;
	}
    
    public void loadKetquaXnDlgData(){
        if(intTypeDlgKetquaXn==TYPE_DLG_VIEW){
            btnNewButtonSaveKetquaXn.setEnabled(false);
        }
        else{
            btnNewButtonSaveKetquaXn.setEnabled(true);
        }

        if(DbHelper.checkPhanQuyen(DbHelper.READ, "ketqua_xn")==false){
            logger.info("DON'T HAVE READ RIGHT");
            return;
        }
        if(objKetquaXn != null){
            if(objKetquaXn.CS_ID==null)
                txtCS_ID.setText("");
            else
                txtCS_ID.setText(""+objKetquaXn.CS_ID.toString());
            if(objKetquaXn.DV_ID==null)
                txtDV_ID.setText("");
            else
                txtDV_ID.setText(""+objKetquaXn.DV_ID.toString());
            if(objKetquaXn.MA_DVKT==null)
                txtMA_DVKT.setText("");
            else
                txtMA_DVKT.setText(""+objKetquaXn.MA_DVKT.toString());
            if(objKetquaXn.TEN_DVKT==null)
                txtTEN_DVKT.setText("");
            else
                txtTEN_DVKT.setText(""+objKetquaXn.TEN_DVKT.toString());
            if(objKetquaXn.CS_NAME==null)
                txtCS_NAME.setText("");
            else
                txtCS_NAME.setText(""+objKetquaXn.CS_NAME.toString());
            if(objKetquaXn.CS_VALUE==null)
                txtCS_VALUE.setText("");
            else
                txtCS_VALUE.setText(""+objKetquaXn.CS_VALUE.toString());
            if(objKetquaXn.CS_RANGE1==null)
                txtCS_RANGE1.setText("");
            else
                txtCS_RANGE1.setText(""+objKetquaXn.CS_RANGE1.toString());
            if(objKetquaXn.CS_RANGE2==null)
                txtCS_RANGE2.setText("");
            else
                txtCS_RANGE2.setText(""+objKetquaXn.CS_RANGE2.toString());
            if(objKetquaXn.CS_DEFAULT==null)
                txtCS_DEFAULT.setText("");
            else
                txtCS_DEFAULT.setText(""+objKetquaXn.CS_DEFAULT.toString());
            if(objKetquaXn.IMAGE_URL==null)
                txtIMAGE_URL.setText("");
            else
                txtIMAGE_URL.setText(""+objKetquaXn.IMAGE_URL.toString());
            if(objKetquaXn.MA_MAY==null)
                txtMA_MAY.setText("");
            else
                txtMA_MAY.setText(""+objKetquaXn.MA_MAY.toString());
            if(objKetquaXn.MO_TA==null)
                txtMO_TA.setText("");
            else
                txtMO_TA.setText(""+objKetquaXn.MO_TA.toString());
            if(objKetquaXn.KET_LUAN==null)
                txtKET_LUAN.setText("");
            else
                txtKET_LUAN.setText(""+objKetquaXn.KET_LUAN.toString());
            if(objKetquaXn.STS==null)
                txtSTS.setText("");
            else
                txtSTS.setText(""+objKetquaXn.STS.toString());
        }
    }
    
    protected void keyPressKetquaXnDlg(KeyEvent e) {
		if(e.keyCode==13){
			saveKetquaXnDlg();
		}
		
	}
}
