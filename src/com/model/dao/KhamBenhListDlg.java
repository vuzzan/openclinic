/**

*/
package com.model.dao;


import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Monitor;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

import swing2swt.layout.BorderLayout;

import org.eclipse.swt.widgets.Table;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.GridData;
import org.sql2o.Connection;

import com.DbHelper;
import com.openclinic.utils.Utils;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.wb.swt.SWTResourceManager;
public class KhamBenhListDlg extends Dialog {
	static Logger logger = LogManager.getLogger(KhamBenhListDlg.class.getName());
	protected Object result;
	protected Shell shell;

	private class TableLabelProviderKhamBenh extends LabelProvider implements ITableLabelProvider {
		public Image getColumnImage(Object element, int columnIndex) {
			return null;
		}
		public String getColumnText(Object element, int columnIndex) {
			if(element instanceof KhamBenh){
				return ((KhamBenh) element).getIndex(columnIndex);
			}
			return "";
		}
	}
	private static class ContentProviderKhamBenh implements IStructuredContentProvider {
		public Object[] getElements(Object inputElement) {
			if(inputElement instanceof ArrayList){
				return ((ArrayList) inputElement).toArray();
			}
			return new Object[0];
		}
		public void dispose() {
		}
		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		}
	}
	private Table tableKhamBenh;
	private TableViewer tableViewerKhamBenh;
	private List<KhamBenh> listDataKhamBenh;
	private Text textSearchKhamBenh;
	private String textSearchKhamBenhString;
	public KhamBenh objKhamBenh = null;
	public int typeKhamBenhDlg = TYPE_DLG_CHOOSEN; 
	public static final int TYPE_DLG_MANAGER = 0;
	public static final int TYPE_DLG_CHOOSEN = 1;

    public int intTypeDlgKhamBenh;
	public static final int TYPE_DLG_VIEW = 1;
	public static final int TYPE_DLG_EDIT = 2;
	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public KhamBenhListDlg(Shell parent, int style) {
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
		shell.setImage(SWTResourceManager.getImage(KhamBenhDlg.class, "/png/list-2x.png"));
		shell.setSize(610, 340);
		shell.setText("KhamBenh List View");
		shell.setLayout(new BorderLayout(0, 0));
		shell.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.keyCode==SWT.ESC){
					objKhamBenh = null;
				}
			}
		});
        
        Composite compositeInShellKhamBenh = new Composite(shell, SWT.NONE);
		compositeInShellKhamBenh.setLayout(new BorderLayout(0, 0));
		compositeInShellKhamBenh.setLayoutData(BorderLayout.CENTER);
        
		Composite compositeHeaderKhamBenh = new Composite(compositeInShellKhamBenh, SWT.NONE);
		compositeHeaderKhamBenh.setLayoutData(BorderLayout.NORTH);
		compositeHeaderKhamBenh.setLayout(new GridLayout(5, false));

		textSearchKhamBenh = new Text(compositeHeaderKhamBenh, SWT.BORDER);
		textSearchKhamBenh.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		textSearchKhamBenh.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.keyCode==13){
					reloadTableKhamBenh();
				}
			}
		});
		
		Button btnNewButtonSearchKhamBenh = new Button(compositeHeaderKhamBenh, SWT.NONE);
		btnNewButtonSearchKhamBenh.setImage(SWTResourceManager.getImage(KhamBenhDlg.class, "/png/media-play-2x.png"));
		btnNewButtonSearchKhamBenh.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));

		btnNewButtonSearchKhamBenh.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				reloadTableKhamBenh();
			}
		});
		Button btnNewButtonExportExcelKhamBenh = new Button(compositeHeaderKhamBenh, SWT.NONE);
		btnNewButtonExportExcelKhamBenh.setText("Export Excel");
		btnNewButtonExportExcelKhamBenh.setImage(SWTResourceManager.getImage(KhamBenhListDlg.class, "/png/spreadsheet-2x.png"));
		btnNewButtonExportExcelKhamBenh.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		btnNewButtonExportExcelKhamBenh.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				exportExcelTableKhamBenh();
			}
		});
		
		
		GridData gd_btnNewButtonKhamBenh = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnNewButtonKhamBenh.widthHint = 87;
		btnNewButtonSearchKhamBenh.setLayoutData(gd_btnNewButtonKhamBenh);
		btnNewButtonSearchKhamBenh.setText("Search");
        
		tableViewerKhamBenh = new TableViewer(compositeInShellKhamBenh, SWT.BORDER | SWT.FULL_SELECTION);
		tableKhamBenh = tableViewerKhamBenh.getTable();
		tableKhamBenh.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		tableKhamBenh.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.keyCode==SWT.F5){
					reloadTableKhamBenh();
                }
                if(e.keyCode==SWT.F4){
					editTableKhamBenh();
                }
				else if(e.keyCode==13){
					selectTableKhamBenh();
				}
                else if(e.keyCode==SWT.DEL){
					deleteTableKhamBenh();
				}
                else if(e.keyCode==SWT.F7){
					newItemKhamBenh();
				}
			}
		});
        tableKhamBenh.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				selectTableKhamBenh();
			}
		});
        
		tableKhamBenh.setLinesVisible(true);
		tableKhamBenh.setHeaderVisible(true);
		tableKhamBenh.setLayoutData(BorderLayout.CENTER);

		TableColumn tbTableColumnKhamBenhSTT = new TableColumn(tableKhamBenh, SWT.RIGHT);
		tbTableColumnKhamBenhSTT.setWidth(100);
		tbTableColumnKhamBenhSTT.setText("STT");

		TableColumn tbTableColumnKhamBenhBN_ID = new TableColumn(tableKhamBenh, SWT.RIGHT);
		tbTableColumnKhamBenhBN_ID.setWidth(100);
		tbTableColumnKhamBenhBN_ID.setText("BN_ID");

		TableColumn tbTableColumnKhamBenhTEN_BENH_NHAN = new TableColumn(tableKhamBenh, SWT.LEFT);
		tbTableColumnKhamBenhTEN_BENH_NHAN.setWidth(100);
		tbTableColumnKhamBenhTEN_BENH_NHAN.setText("TEN_BENH_NHAN");

		TableColumn tbTableColumnKhamBenhTEN_BENH = new TableColumn(tableKhamBenh, SWT.LEFT);
		tbTableColumnKhamBenhTEN_BENH.setWidth(100);
		tbTableColumnKhamBenhTEN_BENH.setText("TEN_BENH");

		TableColumn tbTableColumnKhamBenhMA_BENH = new TableColumn(tableKhamBenh, SWT.LEFT);
		tbTableColumnKhamBenhMA_BENH.setWidth(100);
		tbTableColumnKhamBenhMA_BENH.setText("MA_BENH");

		TableColumn tbTableColumnKhamBenhMA_BENHKHAC = new TableColumn(tableKhamBenh, SWT.LEFT);
		tbTableColumnKhamBenhMA_BENHKHAC.setWidth(100);
		tbTableColumnKhamBenhMA_BENHKHAC.setText("MA_BENHKHAC");

		TableColumn tbTableColumnKhamBenhMA_LYDO_VVIEN = new TableColumn(tableKhamBenh, SWT.RIGHT);
		tbTableColumnKhamBenhMA_LYDO_VVIEN.setWidth(100);
		tbTableColumnKhamBenhMA_LYDO_VVIEN.setText("MA_LYDO_VVIEN");

		TableColumn tbTableColumnKhamBenhMA_NOI_CHUYEN = new TableColumn(tableKhamBenh, SWT.LEFT);
		tbTableColumnKhamBenhMA_NOI_CHUYEN.setWidth(100);
		tbTableColumnKhamBenhMA_NOI_CHUYEN.setText("MA_NOI_CHUYEN");

		TableColumn tbTableColumnKhamBenhMA_TAI_NAN = new TableColumn(tableKhamBenh, SWT.RIGHT);
		tbTableColumnKhamBenhMA_TAI_NAN.setWidth(100);
		tbTableColumnKhamBenhMA_TAI_NAN.setText("MA_TAI_NAN");

		TableColumn tbTableColumnKhamBenhNGAY_VAO = new TableColumn(tableKhamBenh, SWT.LEFT);
		tbTableColumnKhamBenhNGAY_VAO.setWidth(100);
		tbTableColumnKhamBenhNGAY_VAO.setText("NGAY_VAO");

		TableColumn tbTableColumnKhamBenhNGAY_RA = new TableColumn(tableKhamBenh, SWT.LEFT);
		tbTableColumnKhamBenhNGAY_RA.setWidth(100);
		tbTableColumnKhamBenhNGAY_RA.setText("NGAY_RA");

		TableColumn tbTableColumnKhamBenhSO_NGAY_DTRI = new TableColumn(tableKhamBenh, SWT.RIGHT);
		tbTableColumnKhamBenhSO_NGAY_DTRI.setWidth(100);
		tbTableColumnKhamBenhSO_NGAY_DTRI.setText("SO_NGAY_DTRI");

		TableColumn tbTableColumnKhamBenhKET_QUA_DTRI = new TableColumn(tableKhamBenh, SWT.RIGHT);
		tbTableColumnKhamBenhKET_QUA_DTRI.setWidth(100);
		tbTableColumnKhamBenhKET_QUA_DTRI.setText("KET_QUA_DTRI");

		TableColumn tbTableColumnKhamBenhTINH_TRANG_RV = new TableColumn(tableKhamBenh, SWT.RIGHT);
		tbTableColumnKhamBenhTINH_TRANG_RV.setWidth(100);
		tbTableColumnKhamBenhTINH_TRANG_RV.setText("TINH_TRANG_RV");

		TableColumn tbTableColumnKhamBenhNGAY_TTOAN = new TableColumn(tableKhamBenh, SWT.LEFT);
		tbTableColumnKhamBenhNGAY_TTOAN.setWidth(100);
		tbTableColumnKhamBenhNGAY_TTOAN.setText("NGAY_TTOAN");

		TableColumn tbTableColumnKhamBenhMUC_HUONG = new TableColumn(tableKhamBenh, SWT.RIGHT);
		tbTableColumnKhamBenhMUC_HUONG.setWidth(100);
		tbTableColumnKhamBenhMUC_HUONG.setText("MUC_HUONG");

		TableColumn tbTableColumnKhamBenhT_THUOC = new TableColumn(tableKhamBenh, SWT.RIGHT);
		tbTableColumnKhamBenhT_THUOC.setWidth(100);
		tbTableColumnKhamBenhT_THUOC.setText("T_THUOC");

		TableColumn tbTableColumnKhamBenhT_VTYT = new TableColumn(tableKhamBenh, SWT.RIGHT);
		tbTableColumnKhamBenhT_VTYT.setWidth(100);
		tbTableColumnKhamBenhT_VTYT.setText("T_VTYT");

		TableColumn tbTableColumnKhamBenhT_TONGCHI = new TableColumn(tableKhamBenh, SWT.RIGHT);
		tbTableColumnKhamBenhT_TONGCHI.setWidth(100);
		tbTableColumnKhamBenhT_TONGCHI.setText("T_TONGCHI");

		TableColumn tbTableColumnKhamBenhT_BNTT = new TableColumn(tableKhamBenh, SWT.RIGHT);
		tbTableColumnKhamBenhT_BNTT.setWidth(100);
		tbTableColumnKhamBenhT_BNTT.setText("T_BNTT");

		TableColumn tbTableColumnKhamBenhT_BNCCT = new TableColumn(tableKhamBenh, SWT.RIGHT);
		tbTableColumnKhamBenhT_BNCCT.setWidth(100);
		tbTableColumnKhamBenhT_BNCCT.setText("T_BNCCT");

		TableColumn tbTableColumnKhamBenhT_BHTT = new TableColumn(tableKhamBenh, SWT.RIGHT);
		tbTableColumnKhamBenhT_BHTT.setWidth(100);
		tbTableColumnKhamBenhT_BHTT.setText("T_BHTT");

		TableColumn tbTableColumnKhamBenhT_NGUONKHAC = new TableColumn(tableKhamBenh, SWT.RIGHT);
		tbTableColumnKhamBenhT_NGUONKHAC.setWidth(100);
		tbTableColumnKhamBenhT_NGUONKHAC.setText("T_NGUONKHAC");

		TableColumn tbTableColumnKhamBenhT_NGOAIDS = new TableColumn(tableKhamBenh, SWT.RIGHT);
		tbTableColumnKhamBenhT_NGOAIDS.setWidth(100);
		tbTableColumnKhamBenhT_NGOAIDS.setText("T_NGOAIDS");

		TableColumn tbTableColumnKhamBenhNAM_QT = new TableColumn(tableKhamBenh, SWT.RIGHT);
		tbTableColumnKhamBenhNAM_QT.setWidth(100);
		tbTableColumnKhamBenhNAM_QT.setText("NAM_QT");

		TableColumn tbTableColumnKhamBenhTHANG_QT = new TableColumn(tableKhamBenh, SWT.RIGHT);
		tbTableColumnKhamBenhTHANG_QT.setWidth(100);
		tbTableColumnKhamBenhTHANG_QT.setText("THANG_QT");

		TableColumn tbTableColumnKhamBenhMA_LOAI_KCB = new TableColumn(tableKhamBenh, SWT.RIGHT);
		tbTableColumnKhamBenhMA_LOAI_KCB.setWidth(100);
		tbTableColumnKhamBenhMA_LOAI_KCB.setText("MA_LOAI_KCB");

		TableColumn tbTableColumnKhamBenhMA_KHOA = new TableColumn(tableKhamBenh, SWT.LEFT);
		tbTableColumnKhamBenhMA_KHOA.setWidth(100);
		tbTableColumnKhamBenhMA_KHOA.setText("MA_KHOA");

		TableColumn tbTableColumnKhamBenhMA_CSKCB = new TableColumn(tableKhamBenh, SWT.LEFT);
		tbTableColumnKhamBenhMA_CSKCB.setWidth(100);
		tbTableColumnKhamBenhMA_CSKCB.setText("MA_CSKCB");

		TableColumn tbTableColumnKhamBenhMA_KHUVUC = new TableColumn(tableKhamBenh, SWT.LEFT);
		tbTableColumnKhamBenhMA_KHUVUC.setWidth(100);
		tbTableColumnKhamBenhMA_KHUVUC.setText("MA_KHUVUC");

		TableColumn tbTableColumnKhamBenhMA_PTTT_QT = new TableColumn(tableKhamBenh, SWT.LEFT);
		tbTableColumnKhamBenhMA_PTTT_QT.setWidth(100);
		tbTableColumnKhamBenhMA_PTTT_QT.setText("MA_PTTT_QT");


		TableColumn tbTableColumnKhamBenhCAN_NANG = new TableColumn(tableKhamBenh, SWT.NONE);
		tbTableColumnKhamBenhCAN_NANG.setWidth(100);
		tbTableColumnKhamBenhCAN_NANG.setText("CAN_NANG");


		TableColumn tbTableColumnKhamBenhKB_DATE = new TableColumn(tableKhamBenh, SWT.NONE);
		tbTableColumnKhamBenhKB_DATE.setWidth(100);
		tbTableColumnKhamBenhKB_DATE.setText("KB_DATE");

		TableColumn tbTableColumnKhamBenhKIEU_TT = new TableColumn(tableKhamBenh, SWT.RIGHT);
		tbTableColumnKhamBenhKIEU_TT.setWidth(100);
		tbTableColumnKhamBenhKIEU_TT.setText("KIEU_TT");

		TableColumn tbTableColumnKhamBenhCHANDOAN_BD = new TableColumn(tableKhamBenh, SWT.LEFT);
		tbTableColumnKhamBenhCHANDOAN_BD.setWidth(100);
		tbTableColumnKhamBenhCHANDOAN_BD.setText("CHANDOAN_BD");

		TableColumn tbTableColumnKhamBenhNV_ID = new TableColumn(tableKhamBenh, SWT.RIGHT);
		tbTableColumnKhamBenhNV_ID.setWidth(100);
		tbTableColumnKhamBenhNV_ID.setText("NV_ID");

		TableColumn tbTableColumnKhamBenhNV_NAME = new TableColumn(tableKhamBenh, SWT.LEFT);
		tbTableColumnKhamBenhNV_NAME.setWidth(100);
		tbTableColumnKhamBenhNV_NAME.setText("NV_NAME");

		TableColumn tbTableColumnKhamBenhTABLE_ID = new TableColumn(tableKhamBenh, SWT.RIGHT);
		tbTableColumnKhamBenhTABLE_ID.setWidth(100);
		tbTableColumnKhamBenhTABLE_ID.setText("TABLE_ID");

		TableColumn tbTableColumnKhamBenhNGAY_SINH = new TableColumn(tableKhamBenh, SWT.LEFT);
		tbTableColumnKhamBenhNGAY_SINH.setWidth(100);
		tbTableColumnKhamBenhNGAY_SINH.setText("NGAY_SINH");

		TableColumn tbTableColumnKhamBenhGIOI_TINH = new TableColumn(tableKhamBenh, SWT.RIGHT);
		tbTableColumnKhamBenhGIOI_TINH.setWidth(100);
		tbTableColumnKhamBenhGIOI_TINH.setText("GIOI_TINH");

		TableColumn tbTableColumnKhamBenhDIA_CHI = new TableColumn(tableKhamBenh, SWT.LEFT);
		tbTableColumnKhamBenhDIA_CHI.setWidth(100);
		tbTableColumnKhamBenhDIA_CHI.setText("DIA_CHI");

		TableColumn tbTableColumnKhamBenhMA_THE = new TableColumn(tableKhamBenh, SWT.LEFT);
		tbTableColumnKhamBenhMA_THE.setWidth(100);
		tbTableColumnKhamBenhMA_THE.setText("MA_THE");

		TableColumn tbTableColumnKhamBenhMA_DKBD = new TableColumn(tableKhamBenh, SWT.LEFT);
		tbTableColumnKhamBenhMA_DKBD.setWidth(100);
		tbTableColumnKhamBenhMA_DKBD.setText("MA_DKBD");

		TableColumn tbTableColumnKhamBenhGT_THE_TU = new TableColumn(tableKhamBenh, SWT.LEFT);
		tbTableColumnKhamBenhGT_THE_TU.setWidth(100);
		tbTableColumnKhamBenhGT_THE_TU.setText("GT_THE_TU");

		TableColumn tbTableColumnKhamBenhGT_THE_DEN = new TableColumn(tableKhamBenh, SWT.LEFT);
		tbTableColumnKhamBenhGT_THE_DEN.setWidth(100);
		tbTableColumnKhamBenhGT_THE_DEN.setText("GT_THE_DEN");

		TableColumn tbTableColumnKhamBenhNGAY_CAP = new TableColumn(tableKhamBenh, SWT.LEFT);
		tbTableColumnKhamBenhNGAY_CAP.setWidth(100);
		tbTableColumnKhamBenhNGAY_CAP.setText("NGAY_CAP");

		TableColumn tbTableColumnKhamBenhMIEN_CUNG_CT = new TableColumn(tableKhamBenh, SWT.LEFT);
		tbTableColumnKhamBenhMIEN_CUNG_CT.setWidth(100);
		tbTableColumnKhamBenhMIEN_CUNG_CT.setText("MIEN_CUNG_CT");

		TableColumn tbTableColumnKhamBenhMA_QUAN_LY = new TableColumn(tableKhamBenh, SWT.LEFT);
		tbTableColumnKhamBenhMA_QUAN_LY.setWidth(100);
		tbTableColumnKhamBenhMA_QUAN_LY.setText("MA_QUAN_LY");

		TableColumn tbTableColumnKhamBenhTEN_CHA_ME = new TableColumn(tableKhamBenh, SWT.LEFT);
		tbTableColumnKhamBenhTEN_CHA_ME.setWidth(100);
		tbTableColumnKhamBenhTEN_CHA_ME.setText("TEN_CHA_ME");

		TableColumn tbTableColumnKhamBenhMA_DT_SONG = new TableColumn(tableKhamBenh, SWT.RIGHT);
		tbTableColumnKhamBenhMA_DT_SONG.setWidth(100);
		tbTableColumnKhamBenhMA_DT_SONG.setText("MA_DT_SONG");

		TableColumn tbTableColumnKhamBenhTHOIDIEM_NAMNAM = new TableColumn(tableKhamBenh, SWT.LEFT);
		tbTableColumnKhamBenhTHOIDIEM_NAMNAM.setWidth(100);
		tbTableColumnKhamBenhTHOIDIEM_NAMNAM.setText("THOIDIEM_NAMNAM");

		TableColumn tbTableColumnKhamBenhCHUOI_KIEM_TRA = new TableColumn(tableKhamBenh, SWT.LEFT);
		tbTableColumnKhamBenhCHUOI_KIEM_TRA.setWidth(100);
		tbTableColumnKhamBenhCHUOI_KIEM_TRA.setText("CHUOI_KIEM_TRA");

		TableColumn tbTableColumnKhamBenhGATE_INFO = new TableColumn(tableKhamBenh, SWT.LEFT);
		tbTableColumnKhamBenhGATE_INFO.setWidth(100);
		tbTableColumnKhamBenhGATE_INFO.setText("GATE_INFO");

		TableColumn tbTableColumnKhamBenhSTS = new TableColumn(tableKhamBenh, SWT.RIGHT);
		tbTableColumnKhamBenhSTS.setWidth(100);
		tbTableColumnKhamBenhSTS.setText("STS");

        Menu menuKhamBenh = new Menu(tableKhamBenh);
		tableKhamBenh.setMenu(menuKhamBenh);
		
		MenuItem mntmNewItemKhamBenh = new MenuItem(menuKhamBenh, SWT.NONE);
		mntmNewItemKhamBenh.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				newItemKhamBenh();
			}
		});
		mntmNewItemKhamBenh.setImage(SWTResourceManager.getImage(KhamBenhDlg.class, "/png/arrow-circle-top-2x.png"));
		mntmNewItemKhamBenh.setText("New");
		
		MenuItem mntmEditItemKhamBenh = new MenuItem(menuKhamBenh, SWT.NONE);
		mntmEditItemKhamBenh.setImage(SWTResourceManager.getImage(KhamBenhDlg.class, "/png/wrench-2x.png"));
		mntmEditItemKhamBenh.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				editTableKhamBenh();
			}
		});
		mntmEditItemKhamBenh.setText("Edit");
		
		MenuItem mntmDeleteKhamBenh = new MenuItem(menuKhamBenh, SWT.NONE);
		mntmDeleteKhamBenh.setImage(SWTResourceManager.getImage(KhamBenhDlg.class, "/png/circle-x-2x.png"));
		mntmDeleteKhamBenh.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				deleteTableKhamBenh();
			}
		});
		mntmDeleteKhamBenh.setText("Delete");
		
		MenuItem mntmExportKhamBenh = new MenuItem(menuKhamBenh, SWT.NONE);
		mntmExportKhamBenh.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				exportExcelTableKhamBenh();
			}
		});
		mntmExportKhamBenh.setImage(SWTResourceManager.getImage(KhamBenhDlg.class, "/png/spreadsheet-2x.png"));
		mntmExportKhamBenh.setText("Export Excel");
		
		tableViewerKhamBenh.setLabelProvider(new TableLabelProviderKhamBenh());
		tableViewerKhamBenh.setContentProvider(new ContentProviderKhamBenh());
		tableViewerKhamBenh.setInput(listDataKhamBenh);
        //
        //
		loadDataKhamBenh();
		//
        reloadTableKhamBenh();
	}
    public void setDataKhamBenh(String textSearchString){
		this.textSearchKhamBenhString = textSearchString;
	}
	private void loadDataKhamBenh() {
		if(textSearchKhamBenhString!=null){
			textSearchKhamBenh.setText(textSearchKhamBenhString);
		}
	}
	protected void exportExcelTableKhamBenh() {
        if(DbHelper.checkPhanQuyen(DbHelper.READ, "kham_benh")==false){
			logger.info("DON'T HAVE READ RIGHT");
			return;
		}
        if(listDataKhamBenh!=null){
            // Export to EXCEL
    		
			StringBuffer buff_kham_benh = new StringBuffer();
			String kham_benh_filename = "kham_benh_"+Utils.getDatetimeCurent().replaceAll(":", "_")+".xls";
			String delimiter = "</td><td>";
			// Get header...
			// Get header...
			buff_kham_benh.append( "<table>");
			buff_kham_benh.append( "<tr class='background-color:#dfdfdf'><td>");

			buff_kham_benh.append( "STT" +delimiter);
			buff_kham_benh.append( "BN_ID" +delimiter);
			buff_kham_benh.append( "TEN_BENH_NHAN" +delimiter);
			buff_kham_benh.append( "TEN_BENH" +delimiter);
			buff_kham_benh.append( "MA_BENH" +delimiter);
			buff_kham_benh.append( "MA_BENHKHAC" +delimiter);
			buff_kham_benh.append( "MA_LYDO_VVIEN" +delimiter);
			buff_kham_benh.append( "MA_NOI_CHUYEN" +delimiter);
			buff_kham_benh.append( "MA_TAI_NAN" +delimiter);
			buff_kham_benh.append( "NGAY_VAO" +delimiter);
			buff_kham_benh.append( "NGAY_RA" +delimiter);
			buff_kham_benh.append( "SO_NGAY_DTRI" +delimiter);
			buff_kham_benh.append( "KET_QUA_DTRI" +delimiter);
			buff_kham_benh.append( "TINH_TRANG_RV" +delimiter);
			buff_kham_benh.append( "NGAY_TTOAN" +delimiter);
			buff_kham_benh.append( "MUC_HUONG" +delimiter);
			buff_kham_benh.append( "T_THUOC" +delimiter);
			buff_kham_benh.append( "T_VTYT" +delimiter);
			buff_kham_benh.append( "T_TONGCHI" +delimiter);
			buff_kham_benh.append( "T_BNTT" +delimiter);
			buff_kham_benh.append( "T_BNCCT" +delimiter);
			buff_kham_benh.append( "T_BHTT" +delimiter);
			buff_kham_benh.append( "T_NGUONKHAC" +delimiter);
			buff_kham_benh.append( "T_NGOAIDS" +delimiter);
			buff_kham_benh.append( "NAM_QT" +delimiter);
			buff_kham_benh.append( "THANG_QT" +delimiter);
			buff_kham_benh.append( "MA_LOAI_KCB" +delimiter);
			buff_kham_benh.append( "MA_KHOA" +delimiter);
			buff_kham_benh.append( "MA_CSKCB" +delimiter);
			buff_kham_benh.append( "MA_KHUVUC" +delimiter);
			buff_kham_benh.append( "MA_PTTT_QT" +delimiter);
			buff_kham_benh.append( "CAN_NANG" +delimiter);
			buff_kham_benh.append( "KB_DATE" +delimiter);
			buff_kham_benh.append( "KIEU_TT" +delimiter);
			buff_kham_benh.append( "CHANDOAN_BD" +delimiter);
			buff_kham_benh.append( "NV_ID" +delimiter);
			buff_kham_benh.append( "NV_NAME" +delimiter);
			buff_kham_benh.append( "TABLE_ID" +delimiter);
			buff_kham_benh.append( "NGAY_SINH" +delimiter);
			buff_kham_benh.append( "GIOI_TINH" +delimiter);
			buff_kham_benh.append( "DIA_CHI" +delimiter);
			buff_kham_benh.append( "MA_THE" +delimiter);
			buff_kham_benh.append( "MA_DKBD" +delimiter);
			buff_kham_benh.append( "GT_THE_TU" +delimiter);
			buff_kham_benh.append( "GT_THE_DEN" +delimiter);
			buff_kham_benh.append( "NGAY_CAP" +delimiter);
			buff_kham_benh.append( "MIEN_CUNG_CT" +delimiter);
			buff_kham_benh.append( "MA_QUAN_LY" +delimiter);
			buff_kham_benh.append( "TEN_CHA_ME" +delimiter);
			buff_kham_benh.append( "MA_DT_SONG" +delimiter);
			buff_kham_benh.append( "THOIDIEM_NAMNAM" +delimiter);
			buff_kham_benh.append( "CHUOI_KIEM_TRA" +delimiter);
			buff_kham_benh.append( "GATE_INFO" +delimiter);
			buff_kham_benh.append( "STS");
			// End of header
			buff_kham_benh.append( "</td></tr>");
			buff_kham_benh.append( "\n");
			// Get data...
			for( KhamBenh obj:  listDataKhamBenh){
				buff_kham_benh.append( "<tr><td>");
				buff_kham_benh.append( obj.STT +delimiter);
				buff_kham_benh.append( obj.BN_ID +delimiter);
				buff_kham_benh.append( obj.TEN_BENH_NHAN +delimiter);
				buff_kham_benh.append( obj.TEN_BENH +delimiter);
				buff_kham_benh.append( obj.MA_BENH +delimiter);
				buff_kham_benh.append( obj.MA_BENHKHAC +delimiter);
				buff_kham_benh.append( obj.MA_LYDO_VVIEN +delimiter);
				buff_kham_benh.append( obj.MA_NOI_CHUYEN +delimiter);
				buff_kham_benh.append( obj.MA_TAI_NAN +delimiter);
				buff_kham_benh.append( obj.NGAY_VAO +delimiter);
				buff_kham_benh.append( obj.NGAY_RA +delimiter);
				buff_kham_benh.append( obj.SO_NGAY_DTRI +delimiter);
				buff_kham_benh.append( obj.KET_QUA_DTRI +delimiter);
				buff_kham_benh.append( obj.TINH_TRANG_RV +delimiter);
				buff_kham_benh.append( obj.NGAY_TTOAN +delimiter);
				buff_kham_benh.append( obj.MUC_HUONG +delimiter);
				buff_kham_benh.append( obj.T_THUOC +delimiter);
				buff_kham_benh.append( obj.T_VTYT +delimiter);
				buff_kham_benh.append( obj.T_TONGCHI +delimiter);
				buff_kham_benh.append( obj.T_BNTT +delimiter);
				buff_kham_benh.append( obj.T_BNCCT +delimiter);
				buff_kham_benh.append( obj.T_BHTT +delimiter);
				buff_kham_benh.append( obj.T_NGUONKHAC +delimiter);
				buff_kham_benh.append( obj.T_NGOAIDS +delimiter);
				buff_kham_benh.append( obj.NAM_QT +delimiter);
				buff_kham_benh.append( obj.THANG_QT +delimiter);
				buff_kham_benh.append( obj.MA_LOAI_KCB +delimiter);
				buff_kham_benh.append( obj.MA_KHOA +delimiter);
				buff_kham_benh.append( obj.MA_CSKCB +delimiter);
				buff_kham_benh.append( obj.MA_KHUVUC +delimiter);
				buff_kham_benh.append( obj.MA_PTTT_QT +delimiter);
				buff_kham_benh.append( obj.CAN_NANG +delimiter);
				buff_kham_benh.append( obj.KB_DATE +delimiter);
				buff_kham_benh.append( obj.KIEU_TT +delimiter);
				buff_kham_benh.append( obj.CHANDOAN_BD +delimiter);
				buff_kham_benh.append( obj.NV_ID +delimiter);
				buff_kham_benh.append( obj.NV_NAME +delimiter);
				buff_kham_benh.append( obj.TABLE_ID +delimiter);
				buff_kham_benh.append( obj.NGAY_SINH +delimiter);
				buff_kham_benh.append( obj.GIOI_TINH +delimiter);
				buff_kham_benh.append( obj.DIA_CHI +delimiter);
				buff_kham_benh.append( obj.MA_THE +delimiter);
				buff_kham_benh.append( obj.MA_DKBD +delimiter);
				buff_kham_benh.append( obj.GT_THE_TU +delimiter);
				buff_kham_benh.append( obj.GT_THE_DEN +delimiter);
				buff_kham_benh.append( obj.NGAY_CAP +delimiter);
				buff_kham_benh.append( obj.MIEN_CUNG_CT +delimiter);
				buff_kham_benh.append( obj.MA_QUAN_LY +delimiter);
				buff_kham_benh.append( obj.TEN_CHA_ME +delimiter);
				buff_kham_benh.append( obj.MA_DT_SONG +delimiter);
				buff_kham_benh.append( obj.THOIDIEM_NAMNAM +delimiter);
				buff_kham_benh.append( obj.CHUOI_KIEM_TRA +delimiter);
				buff_kham_benh.append( obj.GATE_INFO +delimiter);
				buff_kham_benh.append( obj.STS );
				// End of header
				buff_kham_benh.append( "</td></tr>");
			}
			//
			buff_kham_benh.append( "</table>");
			Writer out = null;
			try {
				out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(kham_benh_filename), "UTF-8"));
				out.write('\uFEFF'); // BOM for UTF-*
			    out.write(buff_kham_benh.toString());
			} 
			catch(Exception ee){
				ee.printStackTrace();
			}
			finally {
			    try {
					out.close();
				} catch (Exception e) {
					e.printStackTrace();
					logger.error(e);
				}
			}
			//
            return;
        }
		// End of export
	}
	protected void reloadTableKhamBenh() {
        if(DbHelper.checkPhanQuyen(DbHelper.READ, "kham_benh")==false){
			logger.info("DON'T HAVE READ RIGHT");
			return;
		}
    
        if(listDataKhamBenh!=null){
            // 
            tableViewerKhamBenh.setInput(listDataKhamBenh);
            tableViewerKhamBenh.refresh();
            //
            if(listDataKhamBenh.size()==0){
                textSearchKhamBenh.forceFocus();
            }
            else{
                tableKhamBenh.forceFocus();
                tableKhamBenh.setSelection(0);
            }
            return;
        }
		// Do search in the first time
		String searchString = textSearchKhamBenh.getText().toLowerCase().trim();
		String sql = "select * from kham_benh WHERE STS<> "+DbHelper.DELETE_STATUS+" ";
		if(searchString.length()>0){
            sql += " and ( 0 ";
        sql += " or LOWER(TEN_BENH_NHAN) like '%"+searchString+"%'";
        sql += " or LOWER(TEN_BENH) like '%"+searchString+"%'";
        sql += " or LOWER(MA_BENH) like '%"+searchString+"%'";
        sql += " or LOWER(MA_BENHKHAC) like '%"+searchString+"%'";
        sql += " or LOWER(MA_NOI_CHUYEN) like '%"+searchString+"%'";
        sql += " or LOWER(NGAY_VAO) like '%"+searchString+"%'";
        sql += " or LOWER(NGAY_RA) like '%"+searchString+"%'";
        sql += " or LOWER(NGAY_TTOAN) like '%"+searchString+"%'";
        sql += " or LOWER(MA_KHOA) like '%"+searchString+"%'";
        sql += " or LOWER(MA_CSKCB) like '%"+searchString+"%'";
        sql += " or LOWER(MA_KHUVUC) like '%"+searchString+"%'";
        sql += " or LOWER(MA_PTTT_QT) like '%"+searchString+"%'";
        sql += " or LOWER(CHANDOAN_BD) like '%"+searchString+"%'";
        sql += " or LOWER(NV_NAME) like '%"+searchString+"%'";
        sql += " or LOWER(NGAY_SINH) like '%"+searchString+"%'";
        sql += " or LOWER(DIA_CHI) like '%"+searchString+"%'";
        sql += " or LOWER(MA_THE) like '%"+searchString+"%'";
        sql += " or LOWER(MA_DKBD) like '%"+searchString+"%'";
        sql += " or LOWER(GT_THE_TU) like '%"+searchString+"%'";
        sql += " or LOWER(GT_THE_DEN) like '%"+searchString+"%'";
        sql += " or LOWER(NGAY_CAP) like '%"+searchString+"%'";
        sql += " or LOWER(MIEN_CUNG_CT) like '%"+searchString+"%'";
        sql += " or LOWER(MA_QUAN_LY) like '%"+searchString+"%'";
        sql += " or LOWER(TEN_CHA_ME) like '%"+searchString+"%'";
        sql += " or LOWER(THOIDIEM_NAMNAM) like '%"+searchString+"%'";
        sql += " or LOWER(CHUOI_KIEM_TRA) like '%"+searchString+"%'";
        sql += " or LOWER(GATE_INFO) like '%"+searchString+"%'";
            sql += " )";
        }
		try  {
            logger.info(sql);
			Connection con = DbHelper.getSql2o();
			listDataKhamBenh = con.createQuery(sql).executeAndFetch(KhamBenh.class);
	    }   
	    catch(Exception e){
	    	logger.error(e);
	    	MessageDialog.openError(shell, "Error", e.getMessage());
	    }
		// 
		tableViewerKhamBenh.setInput(listDataKhamBenh);
		tableViewerKhamBenh.refresh();
        //
        if(listDataKhamBenh.size()==0){
            textSearchKhamBenh.forceFocus();
        }
        else{
            tableKhamBenh.forceFocus();
            tableKhamBenh.setSelection(0);
        }
	}
    
    protected void selectTableKhamBenh() {
		if(tableKhamBenh.getSelectionCount()==0){
			return;
		}
		TableItem item = tableKhamBenh.getSelection()[0];
		KhamBenh obj = (KhamBenh)item.getData();
        logger.info(obj.toString());
        //
        //
    	if(typeKhamBenhDlg==TYPE_DLG_CHOOSEN){
    		// Choosen and close dlg
    		objKhamBenh = obj;
    		shell.close();
    	}
    	else{
            if(DbHelper.checkPhanQuyen(DbHelper.UPDATE, "kham_benh")==false){
                logger.info("DON'T HAVE UPDATE RIGHT");
                return;
            }
            if(intTypeDlgKhamBenh==TYPE_DLG_VIEW){
                return;
            }
			KhamBenhDlg dlg = new KhamBenhDlg(shell, 0);
            dlg.setKhamBenhDlgData(obj);
            dlg.open();
            //
            reloadTableKhamBenh();
    	}
	}
    protected void editTableKhamBenh() {
        if(intTypeDlgKhamBenh==TYPE_DLG_VIEW){
            return;
        }
        if(DbHelper.checkPhanQuyen(DbHelper.UPDATE, "kham_benh")==false){
            logger.info("DON'T HAVE UPDATE RIGHT");
            return;
        }
    	if(tableKhamBenh.getSelectionCount()==0){
			return;
		}
		TableItem item = tableKhamBenh.getSelection()[0];
		KhamBenh obj = (KhamBenh)item.getData();
        logger.info(obj.toString());
        //
        //
		KhamBenhDlg dlg = new KhamBenhDlg(shell, 0);
        dlg.setKhamBenhDlgData(obj);
        dlg.open();
        //
        reloadTableKhamBenh();
	}
    protected void deleteTableKhamBenh() {
        if(intTypeDlgKhamBenh==TYPE_DLG_VIEW){
            return;
        }
        //
        if(DbHelper.checkPhanQuyen(DbHelper.DELETE, "kham_benh")==false){
            logger.info("DON'T HAVE DELETE RIGHT");
            return;
        }
		if(tableKhamBenh.getSelectionCount()==0){
			return;
		}
        if(MessageDialog.openConfirm(shell, "Confirm?", "Delete ?")==false){
			return;
		}
		
		TableItem item = tableKhamBenh.getSelection()[0];
		KhamBenh obj = (KhamBenh)item.getData();
        logger.info(obj.toString());
        //
		obj.delete();
        listDataKhamBenh.remove(obj);
        //
		reloadTableKhamBenh();
	}

	protected void newItemKhamBenh() {
        if(intTypeDlgKhamBenh==TYPE_DLG_VIEW){
            return;
        }
        //
        if(DbHelper.checkPhanQuyen(DbHelper.INSERT, "kham_benh")==false){
            logger.info("DON'T HAVE INSERT RIGHT");
            return;
        }
		//
		KhamBenhDlg dlg = new KhamBenhDlg(shell, 0);
        KhamBenh obj = new KhamBenh();
		dlg.setKhamBenhDlgData(obj);
		dlg.open();
        listDataKhamBenh.add(obj);
        //
		reloadTableKhamBenh();
		//
	}
}
