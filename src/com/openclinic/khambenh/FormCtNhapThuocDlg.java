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
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;



public class FormCtNhapThuocDlg extends Dialog {
	static Logger logger = LogManager.getLogger(FormCtNhapThuocDlg.class.getName());

	protected Object result;
	protected Shell shell;
    private Text txtTENTHUOC;
    private DatePicker txtHANDUNG;
    private Text txtLOT_ID;
    private Text txtDONGIA;
    private Text txtSOLUONG;

    public CtNhapthuoc objCtNhapthuoc;
	protected Thuoc objThuoc;

	private Button btnNewButtonSaveCtNhapthuoc;
    public int intTypeDlgCtNhapthuoc = 1;
	public static final int TYPE_DLG_VIEW = 1;
	public static final int TYPE_DLG_EDIT = 2;
	private Text txtMA_HOAT_CHAT;
    private Text txtMA_AX;
    private Text txtHOAT_CHAT;
    private Text txtHOATCHAT_AX;
    private Text txtMA_DUONG_DUNG;
    private Text txtMA_DUONGDUNG_AX;
    private Text txtDUONG_DUNG;
    private Text txtDUONGDUNG_AX;
    private Text txtHAM_LUONG;
    private Text txtHAMLUONG_AX;
    private Text txtTEN_THUOC;
    private Text txtTENTHUOC_AX;
    private Text txtSO_DANG_KY;
    private Text txtSODANGKY_AX;
    private Text txtDONG_GOI;
    private Text txtDON_VI_TINH;
    private Text txtDON_GIA;
    private Text txtDON_GIA_TT;
    private Text txtSO_LUONG;
    private Text txtMA_CSKCB;
    private Text txtHANG_SX;
    private Text txtNUOC_SX;
    private Text txtNHA_THAU;
    private Text txtQUYET_DINH;
    private Text txtCONG_BO;
    private Text txtMA_THUOC_BV;
    private Text txtLOAI_THUOC;
    private Text txtLOAI_THAU;
    private Text txtNHOM_THAU;
    private Text txtMANHOM_9324;
    private Text txtHIEULUC;
    private Text txtKETQUA;
    private Text txtTHUOC_RANK;
    private Label lblSL;
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

	protected void keyPressThuocDlg(KeyEvent e) {
		// dO NOTHING
		
	}

	/**
	 * Create contents of the dialog.
	 */
	private void createContents() {
		shell = new Shell(getParent(), SWT.SHELL_TRIM | SWT.BORDER | SWT.PRIMARY_MODAL);
		shell.setSize(847, 559);
		shell.setText("Chi tiết thuốc");
		shell.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.keyCode==SWT.ESC){
					objCtNhapthuoc = null;
				}
			}
		});
		shell.setLayout(null);

		Group group = new Group(shell, SWT.NONE);
		group.setBounds(5, 335, 824, 89);
		group.setLayout(null);
		
		Label lbltxtTENTHUOC = new Label(group, SWT.NONE);
		lbltxtTENTHUOC.setBounds(8, 22, 82, 19);
		lbltxtTENTHUOC.setFont(SWTResourceManager.getFont("Tahoma", 12, SWT.NORMAL));
		lbltxtTENTHUOC.setText("Tên Thuốc:");
		
		txtTENTHUOC = new Text(group, SWT.BORDER);
		txtTENTHUOC.setBounds(101, 19, 518, 25);
		txtTENTHUOC.setFont(SWTResourceManager.getFont("Tahoma", 12, SWT.NORMAL));
		
		//
		Label lbltxtSOLUONG = new Label(group, SWT.NONE);
		lbltxtSOLUONG.setBounds(625, 25, 86, 19);
		lbltxtSOLUONG.setFont(SWTResourceManager.getFont("Tahoma", 12, SWT.NORMAL));
		lbltxtSOLUONG.setText("SỐ LƯỢNG:");
		
		txtSOLUONG = new Text(group, SWT.BORDER);
		txtSOLUONG.setBounds(716, 22, 98, 25);
		txtSOLUONG.setFont(SWTResourceManager.getFont("Tahoma", 12, SWT.NORMAL));
		txtSOLUONG.setText("0");
		
		Label lbltxtHANDUNG = new Label(group, SWT.NONE);
		lbltxtHANDUNG.setBounds(8, 53, 88, 19);
		lbltxtHANDUNG.setFont(SWTResourceManager.getFont("Tahoma", 12, SWT.NORMAL));
		lbltxtHANDUNG.setText("HẠN DÙNG:");
		
		txtHANDUNG = new DatePicker(group, SWT.BORDER);
		txtHANDUNG.setBounds(101, 49, 129, 28);
		txtHANDUNG.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		txtHANDUNG.setText("1/1/2017");

		lblSL = new Label(group, SWT.NONE);
		lblSL.setText("SỐ LÔ:");
		lblSL.setFont(SWTResourceManager.getFont("Tahoma", 12, SWT.NORMAL));
		lblSL.setBounds(430, 53, 59, 19);
		
		
		txtLOT_ID = new Text(group, SWT.BORDER);
		txtLOT_ID.setFont(SWTResourceManager.getFont("Tahoma", 12, SWT.NORMAL));
		txtLOT_ID.setBounds(495, 50, 124, 25);

		Label lbltxtDONGIA = new Label(group, SWT.NONE);
		lbltxtDONGIA.setBounds(622, 56, 73, 19);
		lbltxtDONGIA.setFont(SWTResourceManager.getFont("Tahoma", 12, SWT.NORMAL));
		lbltxtDONGIA.setText("ĐƠN GIÁ:");
		
		txtDONGIA = new Text(group, SWT.BORDER);
		txtDONGIA.setBounds(715, 53, 99, 25);
		txtDONGIA.setFont(SWTResourceManager.getFont("Tahoma", 12, SWT.NORMAL));
		txtDONGIA.setText("0");
		
		Group groupChiTietThuoc = new Group(shell, SWT.NONE);
		groupChiTietThuoc.setBounds(5, 0, 824, 329);
		
		
		groupChiTietThuoc.setLayout(null);
		
		Label lbltxtMA_HOAT_CHAT = new Label(groupChiTietThuoc, SWT.NONE);
		lbltxtMA_HOAT_CHAT.setBounds(8, 22, 106, 16);
        lbltxtMA_HOAT_CHAT.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtMA_HOAT_CHAT.setText("MA_HOAT_CHAT :");
		
		txtMA_HOAT_CHAT = new Text(groupChiTietThuoc, SWT.BORDER);
		txtMA_HOAT_CHAT.setEnabled(false);
		txtMA_HOAT_CHAT.setBounds(119, 19, 133, 22);
        txtMA_HOAT_CHAT.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.BOLD));
        txtMA_HOAT_CHAT.setText("MA_HOAT_CHAT");
        txtMA_HOAT_CHAT.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressThuocDlg(e);
			}
		});
		Label lbltxtMA_AX = new Label(groupChiTietThuoc, SWT.NONE);
		lbltxtMA_AX.setBounds(321, 22, 50, 16);
        lbltxtMA_AX.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtMA_AX.setText("MA_AX :");
		
		txtMA_AX = new Text(groupChiTietThuoc, SWT.BORDER);
		txtMA_AX.setEnabled(false);
		txtMA_AX.setBounds(376, 19, 141, 22);
        txtMA_AX.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.BOLD));
        txtMA_AX.setText("MA_AX");
        txtMA_AX.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressThuocDlg(e);
			}
		});
		Label lbltxtHOAT_CHAT = new Label(groupChiTietThuoc, SWT.NONE);
		lbltxtHOAT_CHAT.setBounds(571, 22, 81, 16);
        lbltxtHOAT_CHAT.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtHOAT_CHAT.setText("HOAT_CHAT :");
		
		txtHOAT_CHAT = new Text(groupChiTietThuoc, SWT.BORDER);
		txtHOAT_CHAT.setEnabled(false);
		txtHOAT_CHAT.setBounds(657, 19, 159, 22);
        txtHOAT_CHAT.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.BOLD));
        txtHOAT_CHAT.setText("HOAT_CHAT");
        txtHOAT_CHAT.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressThuocDlg(e);
			}
		});
		Label lbltxtHOATCHAT_AX = new Label(groupChiTietThuoc, SWT.NONE);
		lbltxtHOATCHAT_AX.setBounds(17, 49, 97, 16);
        lbltxtHOATCHAT_AX.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtHOATCHAT_AX.setText("HOATCHAT_AX :");
		
		txtHOATCHAT_AX = new Text(groupChiTietThuoc, SWT.BORDER);
		txtHOATCHAT_AX.setEnabled(false);
		txtHOATCHAT_AX.setBounds(119, 46, 133, 22);
        txtHOATCHAT_AX.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.BOLD));
        txtHOATCHAT_AX.setText("HOATCHAT_AX");
        txtHOATCHAT_AX.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressThuocDlg(e);
			}
		});
		Label lbltxtMA_DUONG_DUNG = new Label(groupChiTietThuoc, SWT.NONE);
		lbltxtMA_DUONG_DUNG.setBounds(257, 49, 114, 16);
        lbltxtMA_DUONG_DUNG.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtMA_DUONG_DUNG.setText("MA_DUONG_DUNG :");
		
		txtMA_DUONG_DUNG = new Text(groupChiTietThuoc, SWT.BORDER);
		txtMA_DUONG_DUNG.setEnabled(false);
		txtMA_DUONG_DUNG.setBounds(376, 46, 141, 22);
        txtMA_DUONG_DUNG.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.BOLD));
        txtMA_DUONG_DUNG.setText("MA_DUONG_DUNG");
        txtMA_DUONG_DUNG.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressThuocDlg(e);
			}
		});
		Label lbltxtMA_DUONGDUNG_AX = new Label(groupChiTietThuoc, SWT.NONE);
		lbltxtMA_DUONGDUNG_AX.setBounds(522, 49, 130, 16);
        lbltxtMA_DUONGDUNG_AX.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtMA_DUONGDUNG_AX.setText("MA_DUONGDUNG_AX :");
		
		txtMA_DUONGDUNG_AX = new Text(groupChiTietThuoc, SWT.BORDER);
		txtMA_DUONGDUNG_AX.setEnabled(false);
		txtMA_DUONGDUNG_AX.setBounds(657, 46, 159, 22);
        txtMA_DUONGDUNG_AX.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.BOLD));
        txtMA_DUONGDUNG_AX.setText("MA_DUONGDUNG_AX");
        txtMA_DUONGDUNG_AX.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressThuocDlg(e);
			}
		});
		Label lbltxtDUONG_DUNG = new Label(groupChiTietThuoc, SWT.NONE);
		lbltxtDUONG_DUNG.setBounds(25, 76, 89, 16);
        lbltxtDUONG_DUNG.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtDUONG_DUNG.setText("DUONG_DUNG :");
		
		txtDUONG_DUNG = new Text(groupChiTietThuoc, SWT.BORDER);
		txtDUONG_DUNG.setEnabled(false);
		txtDUONG_DUNG.setBounds(119, 73, 133, 22);
        txtDUONG_DUNG.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.BOLD));
        txtDUONG_DUNG.setText("DUONG_DUNG");
        txtDUONG_DUNG.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressThuocDlg(e);
			}
		});
		Label lbltxtDUONGDUNG_AX = new Label(groupChiTietThuoc, SWT.NONE);
		lbltxtDUONGDUNG_AX.setBounds(266, 76, 105, 16);
        lbltxtDUONGDUNG_AX.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtDUONGDUNG_AX.setText("DUONGDUNG_AX :");
		
		txtDUONGDUNG_AX = new Text(groupChiTietThuoc, SWT.BORDER);
		txtDUONGDUNG_AX.setEnabled(false);
		txtDUONGDUNG_AX.setBounds(376, 73, 141, 22);
        txtDUONGDUNG_AX.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.BOLD));
        txtDUONGDUNG_AX.setText("DUONGDUNG_AX");
        txtDUONGDUNG_AX.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressThuocDlg(e);
			}
		});
		Label lbltxtHAM_LUONG = new Label(groupChiTietThuoc, SWT.NONE);
		lbltxtHAM_LUONG.setBounds(571, 76, 81, 16);
        lbltxtHAM_LUONG.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtHAM_LUONG.setText("HAM_LUONG :");
		
		txtHAM_LUONG = new Text(groupChiTietThuoc, SWT.BORDER);
		txtHAM_LUONG.setEnabled(false);
		txtHAM_LUONG.setBounds(657, 73, 159, 22);
        txtHAM_LUONG.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.BOLD));
        txtHAM_LUONG.setText("HAM_LUONG");
        txtHAM_LUONG.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressThuocDlg(e);
			}
		});
		Label lbltxtHAMLUONG_AX = new Label(groupChiTietThuoc, SWT.NONE);
		lbltxtHAMLUONG_AX.setBounds(17, 103, 97, 16);
        lbltxtHAMLUONG_AX.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtHAMLUONG_AX.setText("HAMLUONG_AX :");
		
		txtHAMLUONG_AX = new Text(groupChiTietThuoc, SWT.BORDER);
		txtHAMLUONG_AX.setEnabled(false);
		txtHAMLUONG_AX.setBounds(119, 100, 133, 22);
        txtHAMLUONG_AX.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.BOLD));
        txtHAMLUONG_AX.setText("HAMLUONG_AX");
        txtHAMLUONG_AX.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressThuocDlg(e);
			}
		});
		Label lbltxtTEN_THUOC = new Label(groupChiTietThuoc, SWT.NONE);
		lbltxtTEN_THUOC.setBounds(291, 103, 80, 16);
        lbltxtTEN_THUOC.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtTEN_THUOC.setText("TEN_THUOC :");
		
		txtTEN_THUOC = new Text(groupChiTietThuoc, SWT.BORDER);
		txtTEN_THUOC.setEnabled(false);
		txtTEN_THUOC.setBackground(SWTResourceManager.getColor(SWT.COLOR_GREEN));
		txtTEN_THUOC.setBounds(376, 100, 141, 22);
        txtTEN_THUOC.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.BOLD));
        txtTEN_THUOC.setText("TEN_THUOC");
        txtTEN_THUOC.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressThuocDlg(e);
			}
		});
		Label lbltxtTENTHUOC_AX = new Label(groupChiTietThuoc, SWT.NONE);
		lbltxtTENTHUOC_AX.setBounds(556, 103, 96, 16);
        lbltxtTENTHUOC_AX.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtTENTHUOC_AX.setText("TENTHUOC_AX :");
		
		txtTENTHUOC_AX = new Text(groupChiTietThuoc, SWT.BORDER);
		txtTENTHUOC_AX.setEnabled(false);
		txtTENTHUOC_AX.setBounds(657, 100, 159, 22);
        txtTENTHUOC_AX.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.BOLD));
        txtTENTHUOC_AX.setText("TENTHUOC_AX");
        txtTENTHUOC_AX.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressThuocDlg(e);
			}
		});
		Label lbltxtSO_DANG_KY = new Label(groupChiTietThuoc, SWT.NONE);
		lbltxtSO_DANG_KY.setBounds(28, 130, 86, 16);
        lbltxtSO_DANG_KY.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtSO_DANG_KY.setText("SO_DANG_KY :");
		
		txtSO_DANG_KY = new Text(groupChiTietThuoc, SWT.BORDER);
		txtSO_DANG_KY.setEnabled(false);
		txtSO_DANG_KY.setBounds(119, 127, 133, 22);
        txtSO_DANG_KY.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.BOLD));
        txtSO_DANG_KY.setText("SO_DANG_KY");
        txtSO_DANG_KY.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressThuocDlg(e);
			}
		});
		Label lbltxtSODANGKY_AX = new Label(groupChiTietThuoc, SWT.NONE);
		lbltxtSODANGKY_AX.setBounds(276, 130, 95, 16);
        lbltxtSODANGKY_AX.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtSODANGKY_AX.setText("SODANGKY_AX :");
		
		txtSODANGKY_AX = new Text(groupChiTietThuoc, SWT.BORDER);
		txtSODANGKY_AX.setEnabled(false);
		txtSODANGKY_AX.setBackground(SWTResourceManager.getColor(SWT.COLOR_GREEN));
		txtSODANGKY_AX.setBounds(376, 127, 141, 22);
        txtSODANGKY_AX.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.BOLD));
        txtSODANGKY_AX.setText("SODANGKY_AX");
        txtSODANGKY_AX.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressThuocDlg(e);
			}
		});
		Label lbltxtDONG_GOI = new Label(groupChiTietThuoc, SWT.NONE);
		lbltxtDONG_GOI.setBounds(582, 130, 70, 16);
        lbltxtDONG_GOI.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtDONG_GOI.setText("DONG_GOI :");
		
		txtDONG_GOI = new Text(groupChiTietThuoc, SWT.BORDER);
		txtDONG_GOI.setEnabled(false);
		txtDONG_GOI.setBounds(657, 127, 159, 22);
        txtDONG_GOI.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.BOLD));
        txtDONG_GOI.setText("DONG_GOI");
        txtDONG_GOI.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressThuocDlg(e);
			}
		});
		Label lbltxtDON_VI_TINH = new Label(groupChiTietThuoc, SWT.NONE);
		lbltxtDON_VI_TINH.setBounds(26, 157, 88, 16);
        lbltxtDON_VI_TINH.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtDON_VI_TINH.setText("DON_VI_TINH :");
		
		txtDON_VI_TINH = new Text(groupChiTietThuoc, SWT.BORDER);
		txtDON_VI_TINH.setEnabled(false);
		txtDON_VI_TINH.setBounds(119, 154, 133, 22);
        txtDON_VI_TINH.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.BOLD));
        txtDON_VI_TINH.setText("DON_VI_TINH");
        txtDON_VI_TINH.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressThuocDlg(e);
			}
		});
		Label lbltxtDON_GIA = new Label(groupChiTietThuoc, SWT.NONE);
		lbltxtDON_GIA.setBounds(310, 157, 61, 16);
        lbltxtDON_GIA.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtDON_GIA.setText("DON_GIA :");
		
		txtDON_GIA = new Text(groupChiTietThuoc, SWT.BORDER);
		txtDON_GIA.setEnabled(false);
		txtDON_GIA.setBounds(376, 154, 141, 22);
        txtDON_GIA.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.BOLD));
        txtDON_GIA.setText("DON_GIA");
        txtDON_GIA.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressThuocDlg(e);
			}
		});
		Label lbltxtDON_GIA_TT = new Label(groupChiTietThuoc, SWT.NONE);
		lbltxtDON_GIA_TT.setBounds(568, 157, 84, 16);
        lbltxtDON_GIA_TT.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtDON_GIA_TT.setText("DON_GIA_TT :");
		
		txtDON_GIA_TT = new Text(groupChiTietThuoc, SWT.BORDER);
		txtDON_GIA_TT.setEnabled(false);
		txtDON_GIA_TT.setBounds(657, 154, 159, 22);
        txtDON_GIA_TT.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.BOLD));
        txtDON_GIA_TT.setText("DON_GIA_TT");
        txtDON_GIA_TT.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressThuocDlg(e);
			}
		});
		Label lbltxtSO_LUONG = new Label(groupChiTietThuoc, SWT.NONE);
		lbltxtSO_LUONG.setBounds(42, 184, 72, 16);
        lbltxtSO_LUONG.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtSO_LUONG.setText("SO_LUONG :");
		
		txtSO_LUONG = new Text(groupChiTietThuoc, SWT.BORDER);
		txtSO_LUONG.setEnabled(false);
		txtSO_LUONG.setBounds(119, 181, 133, 22);
        txtSO_LUONG.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.BOLD));
        txtSO_LUONG.setText("SO_LUONG");
        txtSO_LUONG.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressThuocDlg(e);
			}
		});
		Label lbltxtMA_CSKCB = new Label(groupChiTietThuoc, SWT.NONE);
		lbltxtMA_CSKCB.setBounds(299, 184, 72, 16);
        lbltxtMA_CSKCB.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtMA_CSKCB.setText("MA_CSKCB :");
		
		txtMA_CSKCB = new Text(groupChiTietThuoc, SWT.BORDER);
		txtMA_CSKCB.setEnabled(false);
		txtMA_CSKCB.setBounds(376, 181, 141, 22);
        txtMA_CSKCB.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.BOLD));
        txtMA_CSKCB.setText("MA_CSKCB");
        txtMA_CSKCB.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressThuocDlg(e);
			}
		});
		Label lbltxtHANG_SX = new Label(groupChiTietThuoc, SWT.NONE);
		lbltxtHANG_SX.setBounds(588, 184, 64, 16);
        lbltxtHANG_SX.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtHANG_SX.setText("HANG_SX :");
		
		txtHANG_SX = new Text(groupChiTietThuoc, SWT.BORDER);
		txtHANG_SX.setEnabled(false);
		txtHANG_SX.setBackground(SWTResourceManager.getColor(SWT.COLOR_GREEN));
		txtHANG_SX.setBounds(657, 181, 159, 22);
        txtHANG_SX.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.BOLD));
        txtHANG_SX.setText("HANG_SX");
        txtHANG_SX.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressThuocDlg(e);
			}
		});
		Label lbltxtNUOC_SX = new Label(groupChiTietThuoc, SWT.NONE);
		lbltxtNUOC_SX.setBounds(49, 211, 65, 16);
        lbltxtNUOC_SX.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtNUOC_SX.setText("NUOC_SX :");
		
		txtNUOC_SX = new Text(groupChiTietThuoc, SWT.BORDER);
		txtNUOC_SX.setEnabled(false);
		txtNUOC_SX.setBounds(119, 208, 133, 22);
        txtNUOC_SX.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.BOLD));
        txtNUOC_SX.setText("NUOC_SX");
        txtNUOC_SX.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressThuocDlg(e);
			}
		});
		Label lbltxtNHA_THAU = new Label(groupChiTietThuoc, SWT.NONE);
		lbltxtNHA_THAU.setBounds(299, 211, 72, 16);
        lbltxtNHA_THAU.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtNHA_THAU.setText("NHA_THAU :");
		
		txtNHA_THAU = new Text(groupChiTietThuoc, SWT.BORDER);
		txtNHA_THAU.setEnabled(false);
		txtNHA_THAU.setBounds(376, 208, 141, 22);
        txtNHA_THAU.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.BOLD));
        txtNHA_THAU.setText("NHA_THAU");
        txtNHA_THAU.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressThuocDlg(e);
			}
		});
		Label lbltxtQUYET_DINH = new Label(groupChiTietThuoc, SWT.NONE);
		lbltxtQUYET_DINH.setBounds(569, 211, 83, 16);
        lbltxtQUYET_DINH.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtQUYET_DINH.setText("QUYET_DINH :");
		
		txtQUYET_DINH = new Text(groupChiTietThuoc, SWT.BORDER);
		txtQUYET_DINH.setEnabled(false);
		txtQUYET_DINH.setBackground(SWTResourceManager.getColor(SWT.COLOR_GREEN));
		txtQUYET_DINH.setBounds(657, 208, 159, 22);
        txtQUYET_DINH.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.BOLD));
        txtQUYET_DINH.setText("QUYET_DINH");
        txtQUYET_DINH.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressThuocDlg(e);
			}
		});
		Label lbltxtCONG_BO = new Label(groupChiTietThuoc, SWT.NONE);
		lbltxtCONG_BO.setBounds(49, 238, 65, 16);
        lbltxtCONG_BO.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtCONG_BO.setText("CONG_BO :");
		
		txtCONG_BO = new Text(groupChiTietThuoc, SWT.BORDER);
		txtCONG_BO.setEnabled(false);
		txtCONG_BO.setBounds(119, 235, 133, 22);
        txtCONG_BO.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.BOLD));
        txtCONG_BO.setText("CONG_BO");
        txtCONG_BO.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressThuocDlg(e);
			}
		});
		Label lbltxtMA_THUOC_BV = new Label(groupChiTietThuoc, SWT.NONE);
		lbltxtMA_THUOC_BV.setBounds(274, 238, 97, 16);
        lbltxtMA_THUOC_BV.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtMA_THUOC_BV.setText("MA_THUOC_BV :");
		
		txtMA_THUOC_BV = new Text(groupChiTietThuoc, SWT.BORDER);
		txtMA_THUOC_BV.setEnabled(false);
		txtMA_THUOC_BV.setBounds(376, 235, 141, 22);
        txtMA_THUOC_BV.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.BOLD));
        txtMA_THUOC_BV.setText("MA_THUOC_BV");
        txtMA_THUOC_BV.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressThuocDlg(e);
			}
		});
		Label lbltxtLOAI_THUOC = new Label(groupChiTietThuoc, SWT.NONE);
		lbltxtLOAI_THUOC.setBounds(568, 238, 84, 16);
        lbltxtLOAI_THUOC.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtLOAI_THUOC.setText("LOAI_THUOC :");
		
		txtLOAI_THUOC = new Text(groupChiTietThuoc, SWT.BORDER);
		txtLOAI_THUOC.setEnabled(false);
		txtLOAI_THUOC.setBounds(657, 235, 159, 22);
        txtLOAI_THUOC.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.BOLD));
        txtLOAI_THUOC.setText("LOAI_THUOC");
        txtLOAI_THUOC.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressThuocDlg(e);
			}
		});
		Label lbltxtLOAI_THAU = new Label(groupChiTietThuoc, SWT.NONE);
		lbltxtLOAI_THAU.setBounds(39, 265, 75, 16);
        lbltxtLOAI_THAU.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtLOAI_THAU.setText("LOAI_THAU :");
		
		txtLOAI_THAU = new Text(groupChiTietThuoc, SWT.BORDER);
		txtLOAI_THAU.setEnabled(false);
		txtLOAI_THAU.setBounds(119, 262, 133, 22);
        txtLOAI_THAU.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.BOLD));
        txtLOAI_THAU.setText("LOAI_THAU");
        txtLOAI_THAU.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressThuocDlg(e);
			}
		});
		Label lbltxtNHOM_THAU = new Label(groupChiTietThuoc, SWT.NONE);
		lbltxtNHOM_THAU.setBounds(288, 265, 83, 16);
        lbltxtNHOM_THAU.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtNHOM_THAU.setText("NHOM_THAU :");
		
		txtNHOM_THAU = new Text(groupChiTietThuoc, SWT.BORDER);
		txtNHOM_THAU.setEnabled(false);
		txtNHOM_THAU.setBounds(376, 262, 141, 22);
        txtNHOM_THAU.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.BOLD));
        txtNHOM_THAU.setText("NHOM_THAU");
        txtNHOM_THAU.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressThuocDlg(e);
			}
		});
		Label lbltxtMANHOM_9324 = new Label(groupChiTietThuoc, SWT.NONE);
		lbltxtMANHOM_9324.setBounds(555, 265, 97, 16);
        lbltxtMANHOM_9324.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtMANHOM_9324.setText("MANHOM_9324 :");
		
		txtMANHOM_9324 = new Text(groupChiTietThuoc, SWT.BORDER);
		txtMANHOM_9324.setEnabled(false);
		txtMANHOM_9324.setBounds(657, 262, 159, 22);
        txtMANHOM_9324.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.BOLD));
        txtMANHOM_9324.setText("MANHOM_9324");
        txtMANHOM_9324.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressThuocDlg(e);
			}
		});
		Label lbltxtHIEULUC = new Label(groupChiTietThuoc, SWT.NONE);
		lbltxtHIEULUC.setBounds(56, 292, 58, 16);
        lbltxtHIEULUC.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtHIEULUC.setText("HIEULUC :");
		
		txtHIEULUC = new Text(groupChiTietThuoc, SWT.BORDER);
		txtHIEULUC.setEnabled(false);
		txtHIEULUC.setBounds(119, 289, 133, 22);
        txtHIEULUC.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.BOLD));
        txtHIEULUC.setText("HIEULUC");
        txtHIEULUC.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressThuocDlg(e);
			}
		});
		Label lbltxtKETQUA = new Label(groupChiTietThuoc, SWT.NONE);
		lbltxtKETQUA.setBounds(315, 292, 56, 16);
        lbltxtKETQUA.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtKETQUA.setText("KETQUA :");
		
		txtKETQUA = new Text(groupChiTietThuoc, SWT.BORDER);
		txtKETQUA.setEnabled(false);
		txtKETQUA.setBounds(376, 289, 141, 22);
        txtKETQUA.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.BOLD));
        txtKETQUA.setText("KETQUA");
        txtKETQUA.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressThuocDlg(e);
			}
		});
		Label lbltxtTHUOC_RANK = new Label(groupChiTietThuoc, SWT.NONE);
		lbltxtTHUOC_RANK.setBounds(564, 292, 88, 16);
        lbltxtTHUOC_RANK.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lbltxtTHUOC_RANK.setText("THUOC_RANK :");
		
		txtTHUOC_RANK = new Text(groupChiTietThuoc, SWT.BORDER);
		txtTHUOC_RANK.setEnabled(false);
		txtTHUOC_RANK.setBounds(657, 289, 159, 22);
        txtTHUOC_RANK.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.BOLD));
        txtTHUOC_RANK.setText("THUOC_RANK");
        txtTHUOC_RANK.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressThuocDlg(e);
			}
		});
		
		txtDONGIA.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressCtNhapthuocDlg(e);
			}
		});
		txtSOLUONG.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressCtNhapthuocDlg(e);
			}
		});
		txtHANDUNG.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressCtNhapthuocDlg(e);
			}
		});
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
						//
						//
						txtDONGIA.setText(""+objThuoc.DON_GIA);
						txtTENTHUOC.setText(""+objThuoc.TEN_THUOC);
						objCtNhapthuoc.THUOC_ID = objThuoc.THUOC_ID;
						txtSOLUONG.forceFocus();
						txtSOLUONG.selectAll();
					}
					//
					loadThuocDlgData();
					//
					
				}
			}
		});

		btnNewButtonSaveCtNhapthuoc = new Button(shell, SWT.NONE);
		btnNewButtonSaveCtNhapthuoc.setBounds(648, 430, 181, 41);
		btnNewButtonSaveCtNhapthuoc.setImage(SWTResourceManager.getImage(FormCtNhapThuocDlg.class, "/png/file-2x.png"));
        btnNewButtonSaveCtNhapthuoc.setFont(SWTResourceManager.getFont("Tahoma", 12, SWT.BOLD));
		btnNewButtonSaveCtNhapthuoc.setText("Lưu lại");
        
        btnNewButtonSaveCtNhapthuoc.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
                saveCtNhapthuocDlg();
			}
		});
        
        //loadCtNhapthuocDlgData();
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
            objCtNhapthuoc.HANDUNG = txtHANDUNG.getDate2().getTime();
            // Integer    = true
            objCtNhapthuoc.SOLUONG = Utils.getInt( txtSOLUONG.getText() );
            //
            objCtNhapthuoc.DONVI = objThuoc!=null?objThuoc.DON_VI_TINH:"";
            //
            objCtNhapthuoc.LOT_ID = txtLOT_ID.getText();
            // Integer    = true
            objCtNhapthuoc.SL_TONKHO = objCtNhapthuoc.SOLUONG;
            // Integer    = true
            objCtNhapthuoc.DONGIA = Utils.getInt( txtDONGIA.getText() );
            // Integer    = true
            objCtNhapthuoc.THANHTIEN = objCtNhapthuoc.DONGIA * objCtNhapthuoc.SOLUONG;
        }
        //
        logger.info(objCtNhapthuoc.toString());
        //
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
    
    public void loadThuocDlgData(){
        if(objThuoc != null){
            if(objThuoc.MA_HOAT_CHAT==null)
                txtMA_HOAT_CHAT.setText("");
            else
                txtMA_HOAT_CHAT.setText(""+objThuoc.MA_HOAT_CHAT.toString());
            if(objThuoc.MA_AX==null)
                txtMA_AX.setText("");
            else
                txtMA_AX.setText(""+objThuoc.MA_AX.toString());
            if(objThuoc.HOAT_CHAT==null)
                txtHOAT_CHAT.setText("");
            else
                txtHOAT_CHAT.setText(""+objThuoc.HOAT_CHAT.toString());
            if(objThuoc.HOATCHAT_AX==null)
                txtHOATCHAT_AX.setText("");
            else
                txtHOATCHAT_AX.setText(""+objThuoc.HOATCHAT_AX.toString());
            if(objThuoc.MA_DUONG_DUNG==null)
                txtMA_DUONG_DUNG.setText("");
            else
                txtMA_DUONG_DUNG.setText(""+objThuoc.MA_DUONG_DUNG.toString());
            if(objThuoc.MA_DUONGDUNG_AX==null)
                txtMA_DUONGDUNG_AX.setText("");
            else
                txtMA_DUONGDUNG_AX.setText(""+objThuoc.MA_DUONGDUNG_AX.toString());
            if(objThuoc.DUONG_DUNG==null)
                txtDUONG_DUNG.setText("");
            else
                txtDUONG_DUNG.setText(""+objThuoc.DUONG_DUNG.toString());
            if(objThuoc.DUONGDUNG_AX==null)
                txtDUONGDUNG_AX.setText("");
            else
                txtDUONGDUNG_AX.setText(""+objThuoc.DUONGDUNG_AX.toString());
            if(objThuoc.HAM_LUONG==null)
                txtHAM_LUONG.setText("");
            else
                txtHAM_LUONG.setText(""+objThuoc.HAM_LUONG.toString());
            if(objThuoc.HAMLUONG_AX==null)
                txtHAMLUONG_AX.setText("");
            else
                txtHAMLUONG_AX.setText(""+objThuoc.HAMLUONG_AX.toString());
            if(objThuoc.TEN_THUOC==null)
                txtTEN_THUOC.setText("");
            else
                txtTEN_THUOC.setText(""+objThuoc.TEN_THUOC.toString());
            if(objThuoc.TENTHUOC_AX==null)
                txtTENTHUOC_AX.setText("");
            else
                txtTENTHUOC_AX.setText(""+objThuoc.TENTHUOC_AX.toString());
            if(objThuoc.SO_DANG_KY==null)
                txtSO_DANG_KY.setText("");
            else
                txtSO_DANG_KY.setText(""+objThuoc.SO_DANG_KY.toString());
            if(objThuoc.SODANGKY_AX==null)
                txtSODANGKY_AX.setText("");
            else
                txtSODANGKY_AX.setText(""+objThuoc.SODANGKY_AX.toString());
            if(objThuoc.DONG_GOI==null)
                txtDONG_GOI.setText("");
            else
                txtDONG_GOI.setText(""+objThuoc.DONG_GOI.toString());
            if(objThuoc.DON_VI_TINH==null)
                txtDON_VI_TINH.setText("");
            else
                txtDON_VI_TINH.setText(""+objThuoc.DON_VI_TINH.toString());
            if(objThuoc.DON_GIA==null)
                txtDON_GIA.setText("");
            else
                txtDON_GIA.setText(""+objThuoc.DON_GIA.toString());
            if(objThuoc.DON_GIA_TT==null)
                txtDON_GIA_TT.setText("");
            else
                txtDON_GIA_TT.setText(""+objThuoc.DON_GIA_TT.toString());
            if(objThuoc.SO_LUONG==null)
                txtSO_LUONG.setText("");
            else
                txtSO_LUONG.setText(""+objThuoc.SO_LUONG.toString());
            if(objThuoc.MA_CSKCB==null)
                txtMA_CSKCB.setText("");
            else
                txtMA_CSKCB.setText(""+objThuoc.MA_CSKCB.toString());
            if(objThuoc.HANG_SX==null)
                txtHANG_SX.setText("");
            else
                txtHANG_SX.setText(""+objThuoc.HANG_SX.toString());
            if(objThuoc.NUOC_SX==null)
                txtNUOC_SX.setText("");
            else
                txtNUOC_SX.setText(""+objThuoc.NUOC_SX.toString());
            if(objThuoc.NHA_THAU==null)
                txtNHA_THAU.setText("");
            else
                txtNHA_THAU.setText(""+objThuoc.NHA_THAU.toString());
            if(objThuoc.QUYET_DINH==null)
                txtQUYET_DINH.setText("");
            else
                txtQUYET_DINH.setText(""+objThuoc.QUYET_DINH.toString());
            if(objThuoc.CONG_BO==null)
                txtCONG_BO.setText("");
            else
                txtCONG_BO.setText(""+objThuoc.CONG_BO.toString());
            if(objThuoc.MA_THUOC_BV==null)
                txtMA_THUOC_BV.setText("");
            else
                txtMA_THUOC_BV.setText(""+objThuoc.MA_THUOC_BV.toString());
            if(objThuoc.LOAI_THUOC==null)
                txtLOAI_THUOC.setText("");
            else
                txtLOAI_THUOC.setText(""+objThuoc.LOAI_THUOC.toString());
            if(objThuoc.LOAI_THAU==null)
                txtLOAI_THAU.setText("");
            else
                txtLOAI_THAU.setText(""+objThuoc.LOAI_THAU.toString());
            if(objThuoc.NHOM_THAU==null)
                txtNHOM_THAU.setText("");
            else
                txtNHOM_THAU.setText(""+objThuoc.NHOM_THAU.toString());
            if(objThuoc.MANHOM_9324==null)
                txtMANHOM_9324.setText("");
            else
                txtMANHOM_9324.setText(""+objThuoc.MANHOM_9324.toString());
            if(objThuoc.HIEULUC==null)
                txtHIEULUC.setText("");
            else
                txtHIEULUC.setText(""+objThuoc.HIEULUC.toString());
            if(objThuoc.KETQUA==null)
                txtKETQUA.setText("");
            else
                txtKETQUA.setText(""+objThuoc.KETQUA.toString());
            if(objThuoc.THUOC_RANK==null)
                txtTHUOC_RANK.setText("");
            else
                txtTHUOC_RANK.setText(""+objThuoc.THUOC_RANK.toString());
        }
    }
}