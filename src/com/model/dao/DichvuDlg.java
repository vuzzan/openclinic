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

public class DichvuDlg extends Dialog {
	static Logger logger = LogManager.getLogger(DichvuDlg.class.getName());

	protected Object result;
	protected Shell shell;
    private Text txtMA_DVKT;
    private Text txtTEN_DVKT;
    private Text txtMA_GIA;
    private Text txtDON_GIA;
    private Text txtDON_GIA2;
    private Text txtQUYET_DINH;
    private Text txtCONG_BO;
    private Text txtNHOM_DV;
    private Text txtMANHOM_9324;
    private Text txtHIEULUC;
    private Text txtTYP;
    private Text txtDV_RANK;
    private Text txtSTS;

    public Dichvu objDichvu;
    Button btnNewButtonSaveDichvu;

    public int intTypeDlgDichvu;
	public static final int TYPE_DLG_VIEW = 1;
	public static final int TYPE_DLG_EDIT = 2;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public DichvuDlg(Shell parent, int style) {
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
		shell.setText("DichvuDlg EDIT/NEW");
		shell.setLayout(new GridLayout(2, false));
        shell.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.keyCode==SWT.ESC){
					objDichvu = null;
				}
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
				keyPressDichvuDlg(e);
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
				keyPressDichvuDlg(e);
			}
		});
		Label lbltxtMA_GIA = new Label(shell, SWT.NONE);
        lbltxtMA_GIA.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtMA_GIA.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtMA_GIA.setText("MA_GIA :");
		
		txtMA_GIA = new Text(shell, SWT.BORDER);
        txtMA_GIA.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtMA_GIA.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtMA_GIA.setText("MA_GIA");
        txtMA_GIA.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressDichvuDlg(e);
			}
		});
		Label lbltxtDON_GIA = new Label(shell, SWT.NONE);
        lbltxtDON_GIA.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtDON_GIA.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtDON_GIA.setText("DON_GIA :");
		
		txtDON_GIA = new Text(shell, SWT.BORDER);
        txtDON_GIA.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtDON_GIA.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtDON_GIA.setText("DON_GIA");
        txtDON_GIA.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressDichvuDlg(e);
			}
		});
		Label lbltxtDON_GIA2 = new Label(shell, SWT.NONE);
        lbltxtDON_GIA2.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtDON_GIA2.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtDON_GIA2.setText("DON_GIA2 :");
		
		txtDON_GIA2 = new Text(shell, SWT.BORDER);
        txtDON_GIA2.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtDON_GIA2.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtDON_GIA2.setText("DON_GIA2");
        txtDON_GIA2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressDichvuDlg(e);
			}
		});
		Label lbltxtQUYET_DINH = new Label(shell, SWT.NONE);
        lbltxtQUYET_DINH.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtQUYET_DINH.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtQUYET_DINH.setText("QUYET_DINH :");
		
		txtQUYET_DINH = new Text(shell, SWT.BORDER);
        txtQUYET_DINH.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtQUYET_DINH.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtQUYET_DINH.setText("QUYET_DINH");
        txtQUYET_DINH.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressDichvuDlg(e);
			}
		});
		Label lbltxtCONG_BO = new Label(shell, SWT.NONE);
        lbltxtCONG_BO.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtCONG_BO.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtCONG_BO.setText("CONG_BO :");
		
		txtCONG_BO = new Text(shell, SWT.BORDER);
        txtCONG_BO.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtCONG_BO.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtCONG_BO.setText("CONG_BO");
        txtCONG_BO.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressDichvuDlg(e);
			}
		});
		Label lbltxtNHOM_DV = new Label(shell, SWT.NONE);
        lbltxtNHOM_DV.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtNHOM_DV.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtNHOM_DV.setText("NHOM_DV :");
		
		txtNHOM_DV = new Text(shell, SWT.BORDER);
        txtNHOM_DV.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtNHOM_DV.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtNHOM_DV.setText("NHOM_DV");
        txtNHOM_DV.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressDichvuDlg(e);
			}
		});
		Label lbltxtMANHOM_9324 = new Label(shell, SWT.NONE);
        lbltxtMANHOM_9324.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtMANHOM_9324.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtMANHOM_9324.setText("MANHOM_9324 :");
		
		txtMANHOM_9324 = new Text(shell, SWT.BORDER);
        txtMANHOM_9324.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtMANHOM_9324.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtMANHOM_9324.setText("MANHOM_9324");
        txtMANHOM_9324.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressDichvuDlg(e);
			}
		});
		Label lbltxtHIEULUC = new Label(shell, SWT.NONE);
        lbltxtHIEULUC.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtHIEULUC.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtHIEULUC.setText("HIEULUC :");
		
		txtHIEULUC = new Text(shell, SWT.BORDER);
        txtHIEULUC.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtHIEULUC.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtHIEULUC.setText("HIEULUC");
        txtHIEULUC.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressDichvuDlg(e);
			}
		});
		Label lbltxtTYP = new Label(shell, SWT.NONE);
        lbltxtTYP.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtTYP.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtTYP.setText("TYP :");
		
		txtTYP = new Text(shell, SWT.BORDER);
        txtTYP.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtTYP.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtTYP.setText("TYP");
        txtTYP.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressDichvuDlg(e);
			}
		});
		Label lbltxtDV_RANK = new Label(shell, SWT.NONE);
        lbltxtDV_RANK.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtDV_RANK.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtDV_RANK.setText("DV_RANK :");
		
		txtDV_RANK = new Text(shell, SWT.BORDER);
        txtDV_RANK.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtDV_RANK.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtDV_RANK.setText("DV_RANK");
        txtDV_RANK.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressDichvuDlg(e);
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
				keyPressDichvuDlg(e);
			}
		});
		new Label(shell, SWT.NONE);
		btnNewButtonSaveDichvu = new Button(shell, SWT.NONE);
		btnNewButtonSaveDichvu.setImage(SWTResourceManager.getImage(DichvuDlg.class, "/png/file-2x.png"));
        btnNewButtonSaveDichvu.setFont(SWTResourceManager.getFont("Tahoma", 12, SWT.BOLD));
		btnNewButtonSaveDichvu.setText("Save");
        
        btnNewButtonSaveDichvu.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
                saveDichvuDlg();
			}
		});
        
        loadDichvuDlgData();
	}

    private void saveDichvuDlg(){
        if(intTypeDlgDichvu==TYPE_DLG_VIEW){
            return;
        }
        
        if(objDichvu == null){
            objDichvu = new Dichvu();
        }
        if(objDichvu!=null){
        // String     = false
            objDichvu.MA_DVKT = txtMA_DVKT.getText();
            // String     = false
            objDichvu.TEN_DVKT = txtTEN_DVKT.getText();
            // String     = false
            objDichvu.MA_GIA = txtMA_GIA.getText();
            // Integer    = true
            objDichvu.DON_GIA = Utils.getInt( txtDON_GIA.getText() );
            // Integer    = true
            objDichvu.DON_GIA2 = Utils.getInt( txtDON_GIA2.getText() );
            // String     = false
            objDichvu.QUYET_DINH = txtQUYET_DINH.getText();
            // String     = false
            objDichvu.CONG_BO = txtCONG_BO.getText();
            // Integer    = true
            objDichvu.NHOM_DV = Utils.getInt( txtNHOM_DV.getText() );
            // String     = false
            objDichvu.MANHOM_9324 = txtMANHOM_9324.getText();
            // String     = false
            objDichvu.HIEULUC = txtHIEULUC.getText();
            // Integer    = true
            objDichvu.TYP = Utils.getInt( txtTYP.getText() );
            // Integer    = true
            objDichvu.DV_RANK = Utils.getInt( txtDV_RANK.getText() );
            // Integer    = true
            objDichvu.STS = Utils.getInt( txtSTS.getText() );
            }
        if(DbHelper.checkPhanQuyen(DbHelper.UPDATE, "dichvu")==false){
            logger.info("DON'T HAVE UPDATE RIGHT");
        }
        else if(DbHelper.checkPhanQuyen(DbHelper.INSERT, "dichvu")==false){
            logger.info("DON'T HAVE INSERT RIGHT");
        }        
        else{
            objDichvu.insert();
        }
        shell.close();
    }
    
    public void setDichvuDlgData(Dichvu obj) {
		this.objDichvu = obj;
	}
    
    public void loadDichvuDlgData(){
        if(intTypeDlgDichvu==TYPE_DLG_VIEW){
            btnNewButtonSaveDichvu.setEnabled(false);
        }
        else{
            btnNewButtonSaveDichvu.setEnabled(true);
        }

        if(DbHelper.checkPhanQuyen(DbHelper.READ, "dichvu")==false){
            logger.info("DON'T HAVE READ RIGHT");
            return;
        }
        if(objDichvu != null){
            if(objDichvu.MA_DVKT==null)
                txtMA_DVKT.setText("");
            else
                txtMA_DVKT.setText(""+objDichvu.MA_DVKT.toString());
            if(objDichvu.TEN_DVKT==null)
                txtTEN_DVKT.setText("");
            else
                txtTEN_DVKT.setText(""+objDichvu.TEN_DVKT.toString());
            if(objDichvu.MA_GIA==null)
                txtMA_GIA.setText("");
            else
                txtMA_GIA.setText(""+objDichvu.MA_GIA.toString());
            if(objDichvu.DON_GIA==null)
                txtDON_GIA.setText("");
            else
                txtDON_GIA.setText(""+objDichvu.DON_GIA.toString());
            if(objDichvu.DON_GIA2==null)
                txtDON_GIA2.setText("");
            else
                txtDON_GIA2.setText(""+objDichvu.DON_GIA2.toString());
            if(objDichvu.QUYET_DINH==null)
                txtQUYET_DINH.setText("");
            else
                txtQUYET_DINH.setText(""+objDichvu.QUYET_DINH.toString());
            if(objDichvu.CONG_BO==null)
                txtCONG_BO.setText("");
            else
                txtCONG_BO.setText(""+objDichvu.CONG_BO.toString());
            if(objDichvu.NHOM_DV==null)
                txtNHOM_DV.setText("");
            else
                txtNHOM_DV.setText(""+objDichvu.NHOM_DV.toString());
            if(objDichvu.MANHOM_9324==null)
                txtMANHOM_9324.setText("");
            else
                txtMANHOM_9324.setText(""+objDichvu.MANHOM_9324.toString());
            if(objDichvu.HIEULUC==null)
                txtHIEULUC.setText("");
            else
                txtHIEULUC.setText(""+objDichvu.HIEULUC.toString());
            if(objDichvu.TYP==null)
                txtTYP.setText("");
            else
                txtTYP.setText(""+objDichvu.TYP.toString());
            if(objDichvu.DV_RANK==null)
                txtDV_RANK.setText("");
            else
                txtDV_RANK.setText(""+objDichvu.DV_RANK.toString());
            if(objDichvu.STS==null)
                txtSTS.setText("");
            else
                txtSTS.setText(""+objDichvu.STS.toString());
        }
    }
    
    protected void keyPressDichvuDlg(KeyEvent e) {
		if(e.keyCode==13){
			saveDichvuDlg();
		}
		
	}
}
