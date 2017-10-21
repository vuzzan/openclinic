package com.openclinic.khambenh;

import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Monitor;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import com.DbHelper;
import com.model.dao.CtNhapthuoc;
import com.model.dao.Thuoc;
import com.model.dao.ThuocListDlg;
import com.openclinic.DatePicker;
import com.openclinic.LoginDlg;
import com.openclinic.utils.Utils;



public class FormCtNhapThuocDlg extends Dialog {
	static Logger logger = LogManager.getLogger(FormCtNhapThuocDlg.class.getName());

	protected Object result;
	protected Shell shell;
    private Text txtTENTHUOC;
    private Text txtDONVI;
    private DatePicker txtHANDUNG;
    private Text txtSOLUONG;
    private Text txtDONGIA;

    public CtNhapthuoc objCtNhapthuoc;
	protected Thuoc objThuoc;
	private Label lblThmThuc;

	private Button btnNewButtonSaveCtNhapthuoc;
    public int intTypeDlgCtNhapthuoc = 1;
	public static final int TYPE_DLG_VIEW = 1;
	public static final int TYPE_DLG_EDIT = 2;
	
	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public FormCtNhapThuocDlg(Shell parent, int style) {
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
		shell.setSize(425, 300);
		shell.setText("Chi tiết thuốc");
		shell.setLayout(new GridLayout(2, false));
		shell.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.keyCode==SWT.ESC){
					objCtNhapthuoc = null;
				}
			}
		});
		
		lblThmThuc = new Label(shell, SWT.NONE);
		lblThmThuc.setText("Thêm thuốc");
		lblThmThuc.setFont(SWTResourceManager.getFont("Tahoma", 16, SWT.NORMAL));
		new Label(shell, SWT.NONE);
		
		Label lbltxtTENTHUOC = new Label(shell, SWT.NONE);
        lbltxtTENTHUOC.setFont(SWTResourceManager.getFont("Tahoma", 12, SWT.NORMAL));
		lbltxtTENTHUOC.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtTENTHUOC.setText("Tên Thuốc:");
		
		txtTENTHUOC = new Text(shell, SWT.BORDER);
        txtTENTHUOC.setFont(SWTResourceManager.getFont("Tahoma", 12, SWT.NORMAL));
		txtTENTHUOC.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtTENTHUOC.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.keyCode==13){
					// Search thuoc Data
					FormThuocListData dlg = new FormThuocListData(shell, 0);
					dlg.setDataThuoc(txtTENTHUOC.getText());
					dlg.open();
					objThuoc = dlg.objThuoc;
					if( objThuoc !=null ){
						txtDONGIA.setText(""+objThuoc.DON_GIA);
						txtTENTHUOC.setText(""+objThuoc.TEN_THUOC);
						txtDONVI.setText(""+objThuoc.DON_VI_TINH);
						objCtNhapthuoc.THUOC_ID = objThuoc.THUOC_ID;
						txtSOLUONG.forceFocus();
						txtSOLUONG.selectAll();
					}
					//
				}
			}
		});
        
		Label lbltxtDONVI = new Label(shell, SWT.NONE);
        lbltxtDONVI.setFont(SWTResourceManager.getFont("Tahoma", 12, SWT.NORMAL));
		lbltxtDONVI.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtDONVI.setText("Đơn vị tính");
		
		txtDONVI = new Text(shell, SWT.BORDER);
        txtDONVI.setFont(SWTResourceManager.getFont("Tahoma", 12, SWT.NORMAL));
		txtDONVI.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtDONVI.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressCtNhapthuocDlg(e);
			}
		});
        txtDONVI.setEditable(false);
        
		Label lbltxtHANDUNG = new Label(shell, SWT.NONE);
        lbltxtHANDUNG.setFont(SWTResourceManager.getFont("Tahoma", 12, SWT.NORMAL));
		lbltxtHANDUNG.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtHANDUNG.setText("HẠN DÙNG:");
		
		txtHANDUNG = new DatePicker(shell, SWT.BORDER);
        txtHANDUNG.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtHANDUNG.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtHANDUNG.setText("1/1/2017");
        txtHANDUNG.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressCtNhapthuocDlg(e);
			}
		});
        //
		Label lbltxtSOLUONG = new Label(shell, SWT.NONE);
        lbltxtSOLUONG.setFont(SWTResourceManager.getFont("Tahoma", 12, SWT.NORMAL));
		lbltxtSOLUONG.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtSOLUONG.setText("SỐ LƯỢNG:");
		
		txtSOLUONG = new Text(shell, SWT.BORDER);
        txtSOLUONG.setFont(SWTResourceManager.getFont("Tahoma", 12, SWT.NORMAL));
		txtSOLUONG.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtSOLUONG.setText("0");
        txtSOLUONG.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressCtNhapthuocDlg(e);
			}
		});
        
		Label lbltxtDONGIA = new Label(shell, SWT.NONE);
        lbltxtDONGIA.setFont(SWTResourceManager.getFont("Tahoma", 12, SWT.NORMAL));
		lbltxtDONGIA.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbltxtDONGIA.setText("ĐƠN GIÁ:");
		
		txtDONGIA = new Text(shell, SWT.BORDER);
        txtDONGIA.setFont(SWTResourceManager.getFont("Tahoma", 12, SWT.NORMAL));
		txtDONGIA.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        txtDONGIA.setText("0");
        txtDONGIA.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressCtNhapthuocDlg(e);
			}
		});
        
		new Label(shell, SWT.NONE);
		btnNewButtonSaveCtNhapthuoc = new Button(shell, SWT.NONE);
		GridData gd_btnNewButtonSaveCtNhapthuoc = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnNewButtonSaveCtNhapthuoc.heightHint = 47;
		gd_btnNewButtonSaveCtNhapthuoc.widthHint = 181;
		btnNewButtonSaveCtNhapthuoc.setLayoutData(gd_btnNewButtonSaveCtNhapthuoc);
		btnNewButtonSaveCtNhapthuoc.setImage(SWTResourceManager.getImage(FormCtNhapThuocDlg.class, "/png/file-2x.png"));
        btnNewButtonSaveCtNhapthuoc.setFont(SWTResourceManager.getFont("Tahoma", 12, SWT.BOLD));
		btnNewButtonSaveCtNhapthuoc.setText("Lưu lại");
        
        btnNewButtonSaveCtNhapthuoc.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
                saveCtNhapthuocDlg();
			}
		});
        
        loadCtNhapthuocDlgData();
	}

    private void saveCtNhapthuocDlg(){
        if(objCtNhapthuoc == null){
        	logger.info("saveCtNhapthuocDlg objCtNhapthuoc " );
            objCtNhapthuoc = new CtNhapthuoc();
        }
        if(objCtNhapthuoc!=null){
            // String     = false
            objCtNhapthuoc.TENTHUOC = txtTENTHUOC.getText();
            // String     = false
            objCtNhapthuoc.DONVI = txtDONVI.getText();
            // Date       = false
            objCtNhapthuoc.HANDUNG = txtHANDUNG.getDate2().getTime();
            // Integer    = true
            objCtNhapthuoc.SOLUONG = Utils.getInt( txtSOLUONG.getText() );
            // Integer    = true
            objCtNhapthuoc.SL_TONKHO =objCtNhapthuoc.SOLUONG;
            // Integer    = true
            objCtNhapthuoc.DONGIA = Utils.getInt( txtDONGIA.getText() );
            // Integer    = true
            objCtNhapthuoc.THANHTIEN = objCtNhapthuoc.DONGIA * objCtNhapthuoc.SOLUONG;
        }
        //
        logger.info(objCtNhapthuoc.toString());
        //
        //objCtNhapthuoc.insert();
        shell.close();
    }
    
    public void setCtNhapthuocDlgData(CtNhapthuoc obj) {
		this.objCtNhapthuoc = obj;
	}
    
    public void loadCtNhapthuocDlgData(){
    	 if(intTypeDlgCtNhapthuoc==TYPE_DLG_VIEW){
             btnNewButtonSaveCtNhapthuoc.setEnabled(false);
         }
         else{
             btnNewButtonSaveCtNhapthuoc.setEnabled(true);
         }

         if(DbHelper.checkPhanQuyen(DbHelper.READ, "ct_nhapthuoc")==false){
             logger.info("DON'T HAVE READ RIGHT");
             return;
         }
         
        if(objCtNhapthuoc != null){
            if(objCtNhapthuoc.TENTHUOC==null)
                txtTENTHUOC.setText("");
            else
                txtTENTHUOC.setText(""+objCtNhapthuoc.TENTHUOC.toString());
            if(objCtNhapthuoc.DONVI==null)
                txtDONVI.setText("");
            else
                txtDONVI.setText(""+objCtNhapthuoc.DONVI.toString());
            if(objCtNhapthuoc.HANDUNG==null)
                txtHANDUNG.setText(Utils.getDatetime(new Date(), "dd/MM/YYYY"));
            else
                txtHANDUNG.setText(""+objCtNhapthuoc.HANDUNG.toString());
            if(objCtNhapthuoc.SOLUONG==null)
                txtSOLUONG.setText("");
            else
                txtSOLUONG.setText(""+objCtNhapthuoc.SOLUONG.toString());
            if(objCtNhapthuoc.DONGIA==null)
                txtDONGIA.setText("");
            else
                txtDONGIA.setText(""+objCtNhapthuoc.DONGIA.toString());
        }
        txtTENTHUOC.selectAll();
        txtTENTHUOC.forceFocus();
    }
    
    protected void keyPressCtNhapthuocDlg(KeyEvent e) {
		if(e.keyCode==13){
			saveCtNhapthuocDlg();
		}
	}
}