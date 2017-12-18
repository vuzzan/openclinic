package com.openclinic.khambenh;

import java.util.Date;
import java.util.Hashtable;
import java.util.List;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Monitor;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TableItem;

import swing2swt.layout.BorderLayout;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Table;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.internal.win32.OS;
import org.sql2o.Connection;
import org.sql2o.data.Row;

import com.DbHelper;
import com.model.dao.Dichvu;
import com.model.dao.DvChitiet;
import com.openclinic.utils.Utils;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.TraverseListener;
import org.eclipse.swt.events.TraverseEvent;

public class ListCLSDlg extends Dialog {
	private class TableLabelProvider extends LabelProvider implements ITableLabelProvider {
		public Image getColumnImage(Object element, int columnIndex) {
			return null;
		}
		public String getColumnText(Object element, int columnIndex) {
			if(element instanceof Dichvu){
				Dichvu obj = (Dichvu)element;
				if(columnIndex==0){
					return obj.MA_DVKT;
				}
				else if(columnIndex==1){
					return obj.TEN_DVKT;
				}
				else if(columnIndex==2){
					return ""+java.text.NumberFormat.getInstance(java.util.Locale.ITALY).format(obj.DON_GIA);
				}
				else if(columnIndex==3){
					return ""+java.text.NumberFormat.getInstance(java.util.Locale.ITALY).format(obj.DON_GIA2);
				}
				else if(columnIndex==4){
					return obj.MANHOM_9324;
				}
				else if(columnIndex==5){
					return obj.QUYET_DINH;
				}
			}
			return "";
		}
	}
	private static class ContentProvider implements IStructuredContentProvider {
		public Object[] getElements(Object inputElement) {
			if(inputElement instanceof List){
				return ((List)inputElement).toArray();
			}
			return new Object[0];
		}
		public void dispose() {
		}
		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		}
	}

	protected Object result;
	protected Shell shlDsDchV;
	private Table tableCLS;
	private TableViewer tableViewerCLS;

	//private Hashtable<String, Dichvu> hashDichVu = new Hashtable<>();
	private Text txtCLS;
	public DvChitiet objDVChiTiet;
	private List<Dichvu> listDichVu;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public ListCLSDlg(Shell parent, int style) {
		super(parent, style);
		setText("SWT Dialog");
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public Object open() {
		createContents();
		shlDsDchV.open();
		shlDsDchV.layout();
		Display display = getParent().getDisplay();
		
		Monitor primary = display.getPrimaryMonitor();
		Rectangle bounds = primary.getBounds();
		Rectangle rect = shlDsDchV.getBounds();
		int x = bounds.x + (bounds.width - rect.width) / 2;
		int y = bounds.y + (bounds.height - rect.height) / 2;
		shlDsDchV.setLocation(x, y);
		
		Composite composite = new Composite(shlDsDchV, SWT.NONE);
		composite.setLayoutData(BorderLayout.SOUTH);
		
		Label lblNewLabel_1 = new Label(composite, SWT.NONE);
		lblNewLabel_1.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		lblNewLabel_1.setBounds(10, 0, 588, 23);
		lblNewLabel_1.setText("Chọn tên DV để tìm, bấm ENTER chọn.");
		//
		while (!shlDsDchV.isDisposed()) {
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
		shlDsDchV = new Shell(getParent(), SWT.DIALOG_TRIM | SWT.PRIMARY_MODAL);
		shlDsDchV.addTraverseListener(new TraverseListener() {
			public void keyTraversed(TraverseEvent e) {
				if (e.detail == SWT.TRAVERSE_ESCAPE) {
	              //e.doit = false;
	            }
			}
		});
		shlDsDchV.setSize(934, 505);
		shlDsDchV.setText("DS DỊCH VỤ");
		shlDsDchV.setLayout(new BorderLayout(0, 0));
		
		Composite composite = new Composite(shlDsDchV, SWT.NONE);
		composite.setLayoutData(BorderLayout.NORTH);
		composite.setLayout(new GridLayout(3, false));
		
		Label lblNewLabel = new Label(composite, SWT.NONE);
		lblNewLabel.setFont(SWTResourceManager.getFont("Tahoma", 12, SWT.NORMAL));
		GridData gd_lblNewLabel = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_lblNewLabel.widthHint = 40;
		lblNewLabel.setLayoutData(gd_lblNewLabel);
		lblNewLabel.setText("Tên");
		
		txtCLS = new Text(composite, SWT.BORDER);
		txtCLS.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtCLS.selectAll();
			}
		});
		txtCLS.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyPress(e);
			}
		});
		GridData gd_txtCLS = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_txtCLS.widthHint = 130;
		txtCLS.setLayoutData(gd_txtCLS);
		txtCLS.setFont(SWTResourceManager.getFont("Tahoma", 12, SWT.NORMAL));
		
		Button btnNewButton = new Button(composite, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				doSearch();
			}
		});
		btnNewButton.setFont(SWTResourceManager.getFont("Tahoma", 12, SWT.NORMAL));
		GridData gd_btnNewButton = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnNewButton.widthHint = 114;
		btnNewButton.setLayoutData(gd_btnNewButton);
		btnNewButton.setText("Lọc");
		
		Composite composite_1 = new Composite(shlDsDchV, SWT.NONE);
		composite_1.setLayoutData(BorderLayout.CENTER);
		composite_1.setLayout(new BorderLayout(0, 0));
		
		tableViewerCLS = new TableViewer(composite_1, SWT.BORDER | SWT.FULL_SELECTION);
		tableCLS = tableViewerCLS.getTable();
		tableCLS.setFont(SWTResourceManager.getFont("Tahoma", 11, SWT.NORMAL));
		tableCLS.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.keyCode==13){
					saveAndClose();
				}
				else{
					keyPress(e);
				}
			}
		});
		tableCLS.setLinesVisible(true);
		tableCLS.setHeaderVisible(true);
		tableCLS.setLayoutData(BorderLayout.CENTER);
		
		TableColumn tblclmnNewColumn = new TableColumn(tableCLS, SWT.NONE);
		tblclmnNewColumn.setWidth(100);
		tblclmnNewColumn.setText("MÃ DV");
		
		TableColumn tblclmnTnDv = new TableColumn(tableCLS, SWT.NONE);
		tblclmnTnDv.setWidth(496);
		tblclmnTnDv.setText("TÊN DV");
		
		TableColumn tblclmnGiBh = new TableColumn(tableCLS, SWT.RIGHT);
		tblclmnGiBh.setWidth(84);
		tblclmnGiBh.setText("GIÁ BH");
		
		TableColumn tblclmnGi = new TableColumn(tableCLS, SWT.RIGHT);
		tblclmnGi.setWidth(100);
		tblclmnGi.setText("GIÁ ");
		
		TableColumn tblclmnMNhm = new TableColumn(tableCLS, SWT.NONE);
		tblclmnMNhm.setWidth(53);
		tblclmnMNhm.setText("NHÓM");
		
		TableColumn tblclmnQ = new TableColumn(tableCLS, SWT.NONE);
		tblclmnQ.setWidth(115);
		tblclmnQ.setText("QĐ");
		
		TableColumn tableColumn_4 = new TableColumn(tableCLS, SWT.NONE);
		tableColumn_4.setWidth(100);
		tableColumn_4.setText("New Column");
		
		TableColumn tableColumn_3 = new TableColumn(tableCLS, SWT.NONE);
		tableColumn_3.setWidth(100);
		tableColumn_3.setText("New Column");
		
		TableColumn tableColumn_2 = new TableColumn(tableCLS, SWT.NONE);
		tableColumn_2.setWidth(100);
		tableColumn_2.setText("New Column");
		tableViewerCLS.setLabelProvider(new TableLabelProvider());
		tableViewerCLS.setContentProvider(new ContentProvider());
		tableViewerCLS.setInput(listDichVu);
		
		txtCLS.setText(objDVChiTiet.TEN_DICH_VU);
		txtCLS.forceFocus();
		txtCLS.selectAll();
		//
		startDlg();
	}

	
	protected void keyPress(KeyEvent e) {
		if(e.keyCode==13 || e.keyCode==SWT.F5){
			doSearch();
		}
		//
	}

	private void startDlg() {
		//
		doSearch();
		//
	}

	private void doSearch() {
		String searchText = txtCLS.getText().trim().toLowerCase();
		try {
			Connection con = DbHelper.getSql2o();
			//
			String sql = "select * from dichvu where LOWER(MA_DVKT) like '%"
					+ searchText
					+ "%' or LOWER(TEN_DVKT) like '%"
					+ searchText + "%' and MANHOM_9324<>'13' order by DV_RANK DESC";
			//System.out.println(sql);
			listDichVu = con.createQuery(sql).executeAndFetch(Dichvu.class);
//			hashDichVu.clear();
//			for (Dichvu obj: listDichVu) {
//				hashDichVu.put(obj.MA_DVKT, obj);
//			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}		
		tableViewerCLS.setInput(listDichVu);
		tableViewerCLS.refresh();
		//
		if(listDichVu.size()==0){
			txtCLS.forceFocus();
			txtCLS.selectAll();
			return;
		}
		tableCLS.forceFocus();
		tableCLS.select(0);
	}

	private void saveAndClose() {
		if(tableCLS.getSelectionCount()==0){
			return;
		}
		TableItem item = tableCLS.getSelection()[0];
		Dichvu objDv  = (Dichvu)item.getData();

		if(objDv!=null){
			objDVChiTiet.MA_DICH_VU = objDv.MA_DVKT;
			objDVChiTiet.TEN_DICH_VU = objDv.TEN_DVKT;
			objDVChiTiet.DON_GIA = objDv.DON_GIA;
			objDVChiTiet.DON_GIA2 = objDv.DON_GIA2;
			objDVChiTiet.MA_NHOM = Utils.getInt( objDv.MANHOM_9324 );
			objDVChiTiet.SO_LUONG = 1;
			objDVChiTiet.MA_VAT_TU = "";
			objDVChiTiet.MA_BENH = "";
			objDVChiTiet.MA_PTTT = 0;
			objDVChiTiet.NGAY_YL = Utils.getDatetime(new Date(), "yyyyMMddHHmm"); 
			objDVChiTiet.NGAY_KQ = Utils.getDatetime(new Date(), "yyyyMMddHHmm"); 
			objDVChiTiet.THANH_TIEN = objDv.DON_GIA2;
			objDVChiTiet.MUC_HUONG = 100;
			objDVChiTiet.TT_BHTT = 0;
			objDVChiTiet.TT_BNTT = 0;
			//
			objDVChiTiet.MA_BAC_SI = "";
			objDVChiTiet.MA_KHOA = "";
			shlDsDchV.close();	
		}
	}
}
