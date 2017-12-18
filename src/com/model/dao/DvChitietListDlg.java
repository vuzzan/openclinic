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
public class DvChitietListDlg extends Dialog {
	static Logger logger = LogManager.getLogger(DvChitietListDlg.class.getName());
	protected Object result;
	protected Shell shell;

	private class TableLabelProviderDvChitiet extends LabelProvider implements ITableLabelProvider {
		public Image getColumnImage(Object element, int columnIndex) {
			return null;
		}
		public String getColumnText(Object element, int columnIndex) {
			if(element instanceof DvChitiet){
				return ((DvChitiet) element).getIndex(columnIndex);
			}
			return "";
		}
	}
	private static class ContentProviderDvChitiet implements IStructuredContentProvider {
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
	private Table tableDvChitiet;
	private TableViewer tableViewerDvChitiet;
	private List<DvChitiet> listDataDvChitiet;
	private Text textSearchDvChitiet;
	private String textSearchDvChitietString;
	public DvChitiet objDvChitiet = null;
	public int typeDvChitietDlg = TYPE_DLG_CHOOSEN; 
	public static final int TYPE_DLG_MANAGER = 0;
	public static final int TYPE_DLG_CHOOSEN = 1;

    public int intTypeDlgDvChitiet;
	public static final int TYPE_DLG_VIEW = 1;
	public static final int TYPE_DLG_EDIT = 2;
	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public DvChitietListDlg(Shell parent, int style) {
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
		shell.setImage(SWTResourceManager.getImage(DvChitietDlg.class, "/png/list-2x.png"));
		shell.setSize(610, 340);
		shell.setText("DvChitiet List View");
		shell.setLayout(new BorderLayout(0, 0));
		shell.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.keyCode==SWT.ESC){
					objDvChitiet = null;
				}
			}
		});
        
        Composite compositeInShellDvChitiet = new Composite(shell, SWT.NONE);
		compositeInShellDvChitiet.setLayout(new BorderLayout(0, 0));
		compositeInShellDvChitiet.setLayoutData(BorderLayout.CENTER);
        
		Composite compositeHeaderDvChitiet = new Composite(compositeInShellDvChitiet, SWT.NONE);
		compositeHeaderDvChitiet.setLayoutData(BorderLayout.NORTH);
		compositeHeaderDvChitiet.setLayout(new GridLayout(5, false));

		textSearchDvChitiet = new Text(compositeHeaderDvChitiet, SWT.BORDER);
		textSearchDvChitiet.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		textSearchDvChitiet.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.keyCode==13){
					reloadTableDvChitiet();
				}
			}
		});
		
		Button btnNewButtonSearchDvChitiet = new Button(compositeHeaderDvChitiet, SWT.NONE);
		btnNewButtonSearchDvChitiet.setImage(SWTResourceManager.getImage(DvChitietDlg.class, "/png/media-play-2x.png"));
		btnNewButtonSearchDvChitiet.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));

		btnNewButtonSearchDvChitiet.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				reloadTableDvChitiet();
			}
		});
		Button btnNewButtonExportExcelDvChitiet = new Button(compositeHeaderDvChitiet, SWT.NONE);
		btnNewButtonExportExcelDvChitiet.setText("Export Excel");
		btnNewButtonExportExcelDvChitiet.setImage(SWTResourceManager.getImage(KhamBenhListDlg.class, "/png/spreadsheet-2x.png"));
		btnNewButtonExportExcelDvChitiet.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		btnNewButtonExportExcelDvChitiet.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				exportExcelTableDvChitiet();
			}
		});
		
		
		GridData gd_btnNewButtonDvChitiet = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnNewButtonDvChitiet.widthHint = 87;
		btnNewButtonSearchDvChitiet.setLayoutData(gd_btnNewButtonDvChitiet);
		btnNewButtonSearchDvChitiet.setText("Search");
        
		tableViewerDvChitiet = new TableViewer(compositeInShellDvChitiet, SWT.BORDER | SWT.FULL_SELECTION);
		tableDvChitiet = tableViewerDvChitiet.getTable();
		tableDvChitiet.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		tableDvChitiet.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.keyCode==SWT.F5){
					reloadTableDvChitiet();
                }
                if(e.keyCode==SWT.F4){
					editTableDvChitiet();
                }
				else if(e.keyCode==13){
					selectTableDvChitiet();
				}
                else if(e.keyCode==SWT.DEL){
					deleteTableDvChitiet();
				}
                else if(e.keyCode==SWT.F7){
					newItemDvChitiet();
				}
			}
		});
        tableDvChitiet.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				selectTableDvChitiet();
			}
		});
        
		tableDvChitiet.setLinesVisible(true);
		tableDvChitiet.setHeaderVisible(true);
		tableDvChitiet.setLayoutData(BorderLayout.CENTER);

		TableColumn tbTableColumnDvChitietBN_ID = new TableColumn(tableDvChitiet, SWT.RIGHT);
		tbTableColumnDvChitietBN_ID.setWidth(100);
		tbTableColumnDvChitietBN_ID.setText("BN_ID");

		TableColumn tbTableColumnDvChitietMA_LK = new TableColumn(tableDvChitiet, SWT.RIGHT);
		tbTableColumnDvChitietMA_LK.setWidth(100);
		tbTableColumnDvChitietMA_LK.setText("MA_LK");

		TableColumn tbTableColumnDvChitietDV_ID = new TableColumn(tableDvChitiet, SWT.RIGHT);
		tbTableColumnDvChitietDV_ID.setWidth(100);
		tbTableColumnDvChitietDV_ID.setText("DV_ID");

		TableColumn tbTableColumnDvChitietMA_DICH_VU = new TableColumn(tableDvChitiet, SWT.LEFT);
		tbTableColumnDvChitietMA_DICH_VU.setWidth(100);
		tbTableColumnDvChitietMA_DICH_VU.setText("MA_DICH_VU");

		TableColumn tbTableColumnDvChitietMA_VAT_TU = new TableColumn(tableDvChitiet, SWT.LEFT);
		tbTableColumnDvChitietMA_VAT_TU.setWidth(100);
		tbTableColumnDvChitietMA_VAT_TU.setText("MA_VAT_TU");

		TableColumn tbTableColumnDvChitietMA_NHOM = new TableColumn(tableDvChitiet, SWT.RIGHT);
		tbTableColumnDvChitietMA_NHOM.setWidth(100);
		tbTableColumnDvChitietMA_NHOM.setText("MA_NHOM");

		TableColumn tbTableColumnDvChitietGOI_VTYT = new TableColumn(tableDvChitiet, SWT.LEFT);
		tbTableColumnDvChitietGOI_VTYT.setWidth(100);
		tbTableColumnDvChitietGOI_VTYT.setText("GOI_VTYT");

		TableColumn tbTableColumnDvChitietTEN_VAT_TU = new TableColumn(tableDvChitiet, SWT.LEFT);
		tbTableColumnDvChitietTEN_VAT_TU.setWidth(100);
		tbTableColumnDvChitietTEN_VAT_TU.setText("TEN_VAT_TU");

		TableColumn tbTableColumnDvChitietTEN_DICH_VU = new TableColumn(tableDvChitiet, SWT.LEFT);
		tbTableColumnDvChitietTEN_DICH_VU.setWidth(100);
		tbTableColumnDvChitietTEN_DICH_VU.setText("TEN_DICH_VU");

		TableColumn tbTableColumnDvChitietDON_VI_TINH = new TableColumn(tableDvChitiet, SWT.LEFT);
		tbTableColumnDvChitietDON_VI_TINH.setWidth(100);
		tbTableColumnDvChitietDON_VI_TINH.setText("DON_VI_TINH");

		TableColumn tbTableColumnDvChitietPHAM_VI = new TableColumn(tableDvChitiet, SWT.RIGHT);
		tbTableColumnDvChitietPHAM_VI.setWidth(100);
		tbTableColumnDvChitietPHAM_VI.setText("PHAM_VI");

		TableColumn tbTableColumnDvChitietSO_LUONG = new TableColumn(tableDvChitiet, SWT.RIGHT);
		tbTableColumnDvChitietSO_LUONG.setWidth(100);
		tbTableColumnDvChitietSO_LUONG.setText("SO_LUONG");

		TableColumn tbTableColumnDvChitietDON_GIA = new TableColumn(tableDvChitiet, SWT.RIGHT);
		tbTableColumnDvChitietDON_GIA.setWidth(100);
		tbTableColumnDvChitietDON_GIA.setText("DON_GIA");

		TableColumn tbTableColumnDvChitietDON_GIA2 = new TableColumn(tableDvChitiet, SWT.RIGHT);
		tbTableColumnDvChitietDON_GIA2.setWidth(100);
		tbTableColumnDvChitietDON_GIA2.setText("DON_GIA2");

		TableColumn tbTableColumnDvChitietTT_THAU = new TableColumn(tableDvChitiet, SWT.LEFT);
		tbTableColumnDvChitietTT_THAU.setWidth(100);
		tbTableColumnDvChitietTT_THAU.setText("TT_THAU");

		TableColumn tbTableColumnDvChitietTHANH_TIEN = new TableColumn(tableDvChitiet, SWT.RIGHT);
		tbTableColumnDvChitietTHANH_TIEN.setWidth(100);
		tbTableColumnDvChitietTHANH_TIEN.setText("THANH_TIEN");

		TableColumn tbTableColumnDvChitietTHANH_TIEN2 = new TableColumn(tableDvChitiet, SWT.RIGHT);
		tbTableColumnDvChitietTHANH_TIEN2.setWidth(100);
		tbTableColumnDvChitietTHANH_TIEN2.setText("THANH_TIEN2");

		TableColumn tbTableColumnDvChitietT_TRANTT = new TableColumn(tableDvChitiet, SWT.RIGHT);
		tbTableColumnDvChitietT_TRANTT.setWidth(100);
		tbTableColumnDvChitietT_TRANTT.setText("T_TRANTT");

		TableColumn tbTableColumnDvChitietMUC_HUONG = new TableColumn(tableDvChitiet, SWT.RIGHT);
		tbTableColumnDvChitietMUC_HUONG.setWidth(100);
		tbTableColumnDvChitietMUC_HUONG.setText("MUC_HUONG");

		TableColumn tbTableColumnDvChitietT_NGUONKHAC = new TableColumn(tableDvChitiet, SWT.RIGHT);
		tbTableColumnDvChitietT_NGUONKHAC.setWidth(100);
		tbTableColumnDvChitietT_NGUONKHAC.setText("T_NGUONKHAC");

		TableColumn tbTableColumnDvChitietTT_BHTT = new TableColumn(tableDvChitiet, SWT.RIGHT);
		tbTableColumnDvChitietTT_BHTT.setWidth(100);
		tbTableColumnDvChitietTT_BHTT.setText("TT_BHTT");

		TableColumn tbTableColumnDvChitietTT_BNTT = new TableColumn(tableDvChitiet, SWT.RIGHT);
		tbTableColumnDvChitietTT_BNTT.setWidth(100);
		tbTableColumnDvChitietTT_BNTT.setText("TT_BNTT");

		TableColumn tbTableColumnDvChitietT_BNCCT = new TableColumn(tableDvChitiet, SWT.RIGHT);
		tbTableColumnDvChitietT_BNCCT.setWidth(100);
		tbTableColumnDvChitietT_BNCCT.setText("T_BNCCT");

		TableColumn tbTableColumnDvChitietT_NGOAIDS = new TableColumn(tableDvChitiet, SWT.RIGHT);
		tbTableColumnDvChitietT_NGOAIDS.setWidth(100);
		tbTableColumnDvChitietT_NGOAIDS.setText("T_NGOAIDS");

		TableColumn tbTableColumnDvChitietMA_GIUONG = new TableColumn(tableDvChitiet, SWT.LEFT);
		tbTableColumnDvChitietMA_GIUONG.setWidth(100);
		tbTableColumnDvChitietMA_GIUONG.setText("MA_GIUONG");

		TableColumn tbTableColumnDvChitietMA_KHOA = new TableColumn(tableDvChitiet, SWT.LEFT);
		tbTableColumnDvChitietMA_KHOA.setWidth(100);
		tbTableColumnDvChitietMA_KHOA.setText("MA_KHOA");

		TableColumn tbTableColumnDvChitietMA_BAC_SI = new TableColumn(tableDvChitiet, SWT.LEFT);
		tbTableColumnDvChitietMA_BAC_SI.setWidth(100);
		tbTableColumnDvChitietMA_BAC_SI.setText("MA_BAC_SI");

		TableColumn tbTableColumnDvChitietMA_BENH = new TableColumn(tableDvChitiet, SWT.LEFT);
		tbTableColumnDvChitietMA_BENH.setWidth(100);
		tbTableColumnDvChitietMA_BENH.setText("MA_BENH");

		TableColumn tbTableColumnDvChitietNGAY_YL = new TableColumn(tableDvChitiet, SWT.LEFT);
		tbTableColumnDvChitietNGAY_YL.setWidth(100);
		tbTableColumnDvChitietNGAY_YL.setText("NGAY_YL");

		TableColumn tbTableColumnDvChitietNGAY_KQ = new TableColumn(tableDvChitiet, SWT.LEFT);
		tbTableColumnDvChitietNGAY_KQ.setWidth(100);
		tbTableColumnDvChitietNGAY_KQ.setText("NGAY_KQ");

		TableColumn tbTableColumnDvChitietMA_PTTT = new TableColumn(tableDvChitiet, SWT.RIGHT);
		tbTableColumnDvChitietMA_PTTT.setWidth(100);
		tbTableColumnDvChitietMA_PTTT.setText("MA_PTTT");

		TableColumn tbTableColumnDvChitietTYLE_TT = new TableColumn(tableDvChitiet, SWT.RIGHT);
		tbTableColumnDvChitietTYLE_TT.setWidth(100);
		tbTableColumnDvChitietTYLE_TT.setText("TYLE_TT");


		TableColumn tbTableColumnDvChitietCUR_DATE = new TableColumn(tableDvChitiet, SWT.NONE);
		tbTableColumnDvChitietCUR_DATE.setWidth(100);
		tbTableColumnDvChitietCUR_DATE.setText("CUR_DATE");

		TableColumn tbTableColumnDvChitietTYP = new TableColumn(tableDvChitiet, SWT.RIGHT);
		tbTableColumnDvChitietTYP.setWidth(100);
		tbTableColumnDvChitietTYP.setText("TYP");

		TableColumn tbTableColumnDvChitietTHANHTOAN = new TableColumn(tableDvChitiet, SWT.RIGHT);
		tbTableColumnDvChitietTHANHTOAN.setWidth(100);
		tbTableColumnDvChitietTHANHTOAN.setText("THANHTOAN");

		TableColumn tbTableColumnDvChitietNV_ID = new TableColumn(tableDvChitiet, SWT.RIGHT);
		tbTableColumnDvChitietNV_ID.setWidth(100);
		tbTableColumnDvChitietNV_ID.setText("NV_ID");

		TableColumn tbTableColumnDvChitietNV_NAME = new TableColumn(tableDvChitiet, SWT.LEFT);
		tbTableColumnDvChitietNV_NAME.setWidth(100);
		tbTableColumnDvChitietNV_NAME.setText("NV_NAME");

		TableColumn tbTableColumnDvChitietNHOM_DV = new TableColumn(tableDvChitiet, SWT.RIGHT);
		tbTableColumnDvChitietNHOM_DV.setWidth(100);
		tbTableColumnDvChitietNHOM_DV.setText("NHOM_DV");

		TableColumn tbTableColumnDvChitietSTS = new TableColumn(tableDvChitiet, SWT.RIGHT);
		tbTableColumnDvChitietSTS.setWidth(100);
		tbTableColumnDvChitietSTS.setText("STS");

        Menu menuDvChitiet = new Menu(tableDvChitiet);
		tableDvChitiet.setMenu(menuDvChitiet);
		
		MenuItem mntmNewItemDvChitiet = new MenuItem(menuDvChitiet, SWT.NONE);
		mntmNewItemDvChitiet.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				newItemDvChitiet();
			}
		});
		mntmNewItemDvChitiet.setImage(SWTResourceManager.getImage(DvChitietDlg.class, "/png/arrow-circle-top-2x.png"));
		mntmNewItemDvChitiet.setText("New");
		
		MenuItem mntmEditItemDvChitiet = new MenuItem(menuDvChitiet, SWT.NONE);
		mntmEditItemDvChitiet.setImage(SWTResourceManager.getImage(DvChitietDlg.class, "/png/wrench-2x.png"));
		mntmEditItemDvChitiet.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				editTableDvChitiet();
			}
		});
		mntmEditItemDvChitiet.setText("Edit");
		
		MenuItem mntmDeleteDvChitiet = new MenuItem(menuDvChitiet, SWT.NONE);
		mntmDeleteDvChitiet.setImage(SWTResourceManager.getImage(DvChitietDlg.class, "/png/circle-x-2x.png"));
		mntmDeleteDvChitiet.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				deleteTableDvChitiet();
			}
		});
		mntmDeleteDvChitiet.setText("Delete");
		
		MenuItem mntmExportDvChitiet = new MenuItem(menuDvChitiet, SWT.NONE);
		mntmExportDvChitiet.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				exportExcelTableDvChitiet();
			}
		});
		mntmExportDvChitiet.setImage(SWTResourceManager.getImage(DvChitietDlg.class, "/png/spreadsheet-2x.png"));
		mntmExportDvChitiet.setText("Export Excel");
		
		tableViewerDvChitiet.setLabelProvider(new TableLabelProviderDvChitiet());
		tableViewerDvChitiet.setContentProvider(new ContentProviderDvChitiet());
		tableViewerDvChitiet.setInput(listDataDvChitiet);
        //
        //
		loadDataDvChitiet();
		//
        reloadTableDvChitiet();
	}
    public void setDataDvChitiet(String textSearchString){
		this.textSearchDvChitietString = textSearchString;
	}
	private void loadDataDvChitiet() {
		if(textSearchDvChitietString!=null){
			textSearchDvChitiet.setText(textSearchDvChitietString);
		}
	}
	protected void exportExcelTableDvChitiet() {
        if(DbHelper.checkPhanQuyen(DbHelper.READ, "dv_chitiet")==false){
			logger.info("DON'T HAVE READ RIGHT");
			return;
		}
        if(listDataDvChitiet!=null){
            // Export to EXCEL
    		
			StringBuffer buff_dv_chitiet = new StringBuffer();
			String dv_chitiet_filename = "dv_chitiet_"+Utils.getDatetimeCurent().replaceAll(":", "_")+".xls";
			String delimiter = "</td><td>";
			// Get header...
			// Get header...
			buff_dv_chitiet.append( "<table>");
			buff_dv_chitiet.append( "<tr class='background-color:#dfdfdf'><td>");

			buff_dv_chitiet.append( "BN_ID" +delimiter);
			buff_dv_chitiet.append( "MA_LK" +delimiter);
			buff_dv_chitiet.append( "DV_ID" +delimiter);
			buff_dv_chitiet.append( "MA_DICH_VU" +delimiter);
			buff_dv_chitiet.append( "MA_VAT_TU" +delimiter);
			buff_dv_chitiet.append( "MA_NHOM" +delimiter);
			buff_dv_chitiet.append( "GOI_VTYT" +delimiter);
			buff_dv_chitiet.append( "TEN_VAT_TU" +delimiter);
			buff_dv_chitiet.append( "TEN_DICH_VU" +delimiter);
			buff_dv_chitiet.append( "DON_VI_TINH" +delimiter);
			buff_dv_chitiet.append( "PHAM_VI" +delimiter);
			buff_dv_chitiet.append( "SO_LUONG" +delimiter);
			buff_dv_chitiet.append( "DON_GIA" +delimiter);
			buff_dv_chitiet.append( "DON_GIA2" +delimiter);
			buff_dv_chitiet.append( "TT_THAU" +delimiter);
			buff_dv_chitiet.append( "THANH_TIEN" +delimiter);
			buff_dv_chitiet.append( "THANH_TIEN2" +delimiter);
			buff_dv_chitiet.append( "T_TRANTT" +delimiter);
			buff_dv_chitiet.append( "MUC_HUONG" +delimiter);
			buff_dv_chitiet.append( "T_NGUONKHAC" +delimiter);
			buff_dv_chitiet.append( "TT_BHTT" +delimiter);
			buff_dv_chitiet.append( "TT_BNTT" +delimiter);
			buff_dv_chitiet.append( "T_BNCCT" +delimiter);
			buff_dv_chitiet.append( "T_NGOAIDS" +delimiter);
			buff_dv_chitiet.append( "MA_GIUONG" +delimiter);
			buff_dv_chitiet.append( "MA_KHOA" +delimiter);
			buff_dv_chitiet.append( "MA_BAC_SI" +delimiter);
			buff_dv_chitiet.append( "MA_BENH" +delimiter);
			buff_dv_chitiet.append( "NGAY_YL" +delimiter);
			buff_dv_chitiet.append( "NGAY_KQ" +delimiter);
			buff_dv_chitiet.append( "MA_PTTT" +delimiter);
			buff_dv_chitiet.append( "TYLE_TT" +delimiter);
			buff_dv_chitiet.append( "CUR_DATE" +delimiter);
			buff_dv_chitiet.append( "TYP" +delimiter);
			buff_dv_chitiet.append( "THANHTOAN" +delimiter);
			buff_dv_chitiet.append( "NV_ID" +delimiter);
			buff_dv_chitiet.append( "NV_NAME" +delimiter);
			buff_dv_chitiet.append( "NHOM_DV" +delimiter);
			buff_dv_chitiet.append( "STS");
			// End of header
			buff_dv_chitiet.append( "</td></tr>");
			buff_dv_chitiet.append( "\n");
			// Get data...
			for( DvChitiet obj:  listDataDvChitiet){
				buff_dv_chitiet.append( "<tr><td>");
				buff_dv_chitiet.append( obj.BN_ID +delimiter);
				buff_dv_chitiet.append( obj.MA_LK +delimiter);
				buff_dv_chitiet.append( obj.DV_ID +delimiter);
				buff_dv_chitiet.append( obj.MA_DICH_VU +delimiter);
				buff_dv_chitiet.append( obj.MA_VAT_TU +delimiter);
				buff_dv_chitiet.append( obj.MA_NHOM +delimiter);
				buff_dv_chitiet.append( obj.GOI_VTYT +delimiter);
				buff_dv_chitiet.append( obj.TEN_VAT_TU +delimiter);
				buff_dv_chitiet.append( obj.TEN_DICH_VU +delimiter);
				buff_dv_chitiet.append( obj.DON_VI_TINH +delimiter);
				buff_dv_chitiet.append( obj.PHAM_VI +delimiter);
				buff_dv_chitiet.append( obj.SO_LUONG +delimiter);
				buff_dv_chitiet.append( obj.DON_GIA +delimiter);
				buff_dv_chitiet.append( obj.DON_GIA2 +delimiter);
				buff_dv_chitiet.append( obj.TT_THAU +delimiter);
				buff_dv_chitiet.append( obj.THANH_TIEN +delimiter);
				buff_dv_chitiet.append( obj.THANH_TIEN2 +delimiter);
				buff_dv_chitiet.append( obj.T_TRANTT +delimiter);
				buff_dv_chitiet.append( obj.TYLE_TT +delimiter);
				buff_dv_chitiet.append( obj.T_NGUONKHAC +delimiter);
				buff_dv_chitiet.append( obj.TT_BHTT +delimiter);
				buff_dv_chitiet.append( obj.TT_BNTT +delimiter);
				buff_dv_chitiet.append( obj.T_BNCCT +delimiter);
				buff_dv_chitiet.append( obj.T_NGOAIDS +delimiter);
				buff_dv_chitiet.append( obj.MA_GIUONG +delimiter);
				buff_dv_chitiet.append( obj.MA_KHOA +delimiter);
				buff_dv_chitiet.append( obj.MA_BAC_SI +delimiter);
				buff_dv_chitiet.append( obj.MA_BENH +delimiter);
				buff_dv_chitiet.append( obj.NGAY_YL +delimiter);
				buff_dv_chitiet.append( obj.NGAY_KQ +delimiter);
				buff_dv_chitiet.append( obj.MA_PTTT +delimiter);
				buff_dv_chitiet.append( obj.MUC_HUONG +delimiter);
				buff_dv_chitiet.append( obj.CUR_DATE +delimiter);
				buff_dv_chitiet.append( obj.TYP +delimiter);
				buff_dv_chitiet.append( obj.THANHTOAN +delimiter);
				buff_dv_chitiet.append( obj.NV_ID +delimiter);
				buff_dv_chitiet.append( obj.NV_NAME +delimiter);
				buff_dv_chitiet.append( obj.NHOM_DV +delimiter);
				buff_dv_chitiet.append( obj.STS );
				// End of header
				buff_dv_chitiet.append( "</td></tr>");
			}
			//
			buff_dv_chitiet.append( "</table>");
			Writer out = null;
			try {
				out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(dv_chitiet_filename), "UTF-8"));
				out.write('\uFEFF'); // BOM for UTF-*
			    out.write(buff_dv_chitiet.toString());
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
	protected void reloadTableDvChitiet() {
        if(DbHelper.checkPhanQuyen(DbHelper.READ, "dv_chitiet")==false){
			logger.info("DON'T HAVE READ RIGHT");
			return;
		}
    
        if(listDataDvChitiet!=null){
            // 
            tableViewerDvChitiet.setInput(listDataDvChitiet);
            tableViewerDvChitiet.refresh();
            //
            if(listDataDvChitiet.size()==0){
                textSearchDvChitiet.forceFocus();
            }
            else{
                tableDvChitiet.forceFocus();
                tableDvChitiet.setSelection(0);
            }
            return;
        }
		// Do search in the first time
		String searchString = textSearchDvChitiet.getText().toLowerCase().trim();
		String sql = "select * from dv_chitiet WHERE STS<> "+DbHelper.DELETE_STATUS+" ";
		if(searchString.length()>0){
            sql += " and ( 0 ";
        sql += " or LOWER(MA_DICH_VU) like '%"+searchString+"%'";
        sql += " or LOWER(MA_VAT_TU) like '%"+searchString+"%'";
        sql += " or LOWER(GOI_VTYT) like '%"+searchString+"%'";
        sql += " or LOWER(TEN_VAT_TU) like '%"+searchString+"%'";
        sql += " or LOWER(TEN_DICH_VU) like '%"+searchString+"%'";
        sql += " or LOWER(DON_VI_TINH) like '%"+searchString+"%'";
        sql += " or LOWER(TT_THAU) like '%"+searchString+"%'";
        sql += " or LOWER(MA_GIUONG) like '%"+searchString+"%'";
        sql += " or LOWER(MA_KHOA) like '%"+searchString+"%'";
        sql += " or LOWER(MA_BAC_SI) like '%"+searchString+"%'";
        sql += " or LOWER(MA_BENH) like '%"+searchString+"%'";
        sql += " or LOWER(NGAY_YL) like '%"+searchString+"%'";
        sql += " or LOWER(NGAY_KQ) like '%"+searchString+"%'";
        sql += " or LOWER(NV_NAME) like '%"+searchString+"%'";
            sql += " )";
        }
		try  {
            logger.info(sql);
			Connection con = DbHelper.getSql2o();
			listDataDvChitiet = con.createQuery(sql).executeAndFetch(DvChitiet.class);
	    }   
	    catch(Exception e){
	    	logger.error(e);
	    	MessageDialog.openError(shell, "Error", e.getMessage());
	    }
		// 
		tableViewerDvChitiet.setInput(listDataDvChitiet);
		tableViewerDvChitiet.refresh();
        //
        if(listDataDvChitiet.size()==0){
            textSearchDvChitiet.forceFocus();
        }
        else{
            tableDvChitiet.forceFocus();
            tableDvChitiet.setSelection(0);
        }
	}
    
    protected void selectTableDvChitiet() {
		if(tableDvChitiet.getSelectionCount()==0){
			return;
		}
		TableItem item = tableDvChitiet.getSelection()[0];
		DvChitiet obj = (DvChitiet)item.getData();
        logger.info(obj.toString());
        //
        //
    	if(typeDvChitietDlg==TYPE_DLG_CHOOSEN){
    		// Choosen and close dlg
    		objDvChitiet = obj;
    		shell.close();
    	}
    	else{
            if(DbHelper.checkPhanQuyen(DbHelper.UPDATE, "dv_chitiet")==false){
                logger.info("DON'T HAVE UPDATE RIGHT");
                return;
            }
            if(intTypeDlgDvChitiet==TYPE_DLG_VIEW){
                return;
            }
			DvChitietDlg dlg = new DvChitietDlg(shell, 0);
            dlg.setDvChitietDlgData(obj);
            dlg.open();
            //
            reloadTableDvChitiet();
    	}
	}
    protected void editTableDvChitiet() {
        if(intTypeDlgDvChitiet==TYPE_DLG_VIEW){
            return;
        }
        if(DbHelper.checkPhanQuyen(DbHelper.UPDATE, "dv_chitiet")==false){
            logger.info("DON'T HAVE UPDATE RIGHT");
            return;
        }
    	if(tableDvChitiet.getSelectionCount()==0){
			return;
		}
		TableItem item = tableDvChitiet.getSelection()[0];
		DvChitiet obj = (DvChitiet)item.getData();
        logger.info(obj.toString());
        //
        //
		DvChitietDlg dlg = new DvChitietDlg(shell, 0);
        dlg.setDvChitietDlgData(obj);
        dlg.open();
        //
        reloadTableDvChitiet();
	}
    protected void deleteTableDvChitiet() {
        if(intTypeDlgDvChitiet==TYPE_DLG_VIEW){
            return;
        }
        //
        if(DbHelper.checkPhanQuyen(DbHelper.DELETE, "dv_chitiet")==false){
            logger.info("DON'T HAVE DELETE RIGHT");
            return;
        }
		if(tableDvChitiet.getSelectionCount()==0){
			return;
		}
        if(MessageDialog.openConfirm(shell, "Confirm?", "Delete ?")==false){
			return;
		}
		
		TableItem item = tableDvChitiet.getSelection()[0];
		DvChitiet obj = (DvChitiet)item.getData();
        logger.info(obj.toString());
        //
		obj.delete();
        listDataDvChitiet.remove(obj);
        //
		reloadTableDvChitiet();
	}

	protected void newItemDvChitiet() {
        if(intTypeDlgDvChitiet==TYPE_DLG_VIEW){
            return;
        }
        //
        if(DbHelper.checkPhanQuyen(DbHelper.INSERT, "dv_chitiet")==false){
            logger.info("DON'T HAVE INSERT RIGHT");
            return;
        }
		//
		DvChitietDlg dlg = new DvChitietDlg(shell, 0);
        DvChitiet obj = new DvChitiet();
		dlg.setDvChitietDlgData(obj);
		dlg.open();
        listDataDvChitiet.add(obj);
        //
		reloadTableDvChitiet();
		//
	}
}
