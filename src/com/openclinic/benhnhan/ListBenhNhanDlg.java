package com.openclinic.benhnhan;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import swing2swt.layout.BorderLayout;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Table;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.internal.win32.OS;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.sql2o.Connection;
import org.sql2o.data.Row;

import com.DbHelper;
import com.model.dao.BenhNhan;
import com.model.dao.MaCskcb;
import com.openclinic.khambenh.FormKhamBenhDlg;

import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;

public class ListBenhNhanDlg extends Dialog {
	static Logger logger = LogManager
			.getLogger(ListBenhNhanDlg.class.getName());
	private class TableLabelProvider extends LabelProvider implements ITableLabelProvider {
		public Image getColumnImage(Object element, int columnIndex) {
			return null;
		}
		public String getColumnText(Object element, int columnIndex) {
			if(element instanceof BenhNhan){
				BenhNhan obj = (BenhNhan)element;
				if(columnIndex==0){
					return obj.HO_TEN;
				}
				else if(columnIndex==1){
					return obj.NGAY_SINH;
				}
				else if(columnIndex==2){
					return obj.GIOI_TINH==1?"Nam":"Nữ";
				}
				else if(columnIndex==3){
					return obj.MA_THE;
				}
				else if(columnIndex==4){
					return obj.DIA_CHI;
				}
				else if(columnIndex==5){
					//return obj.HO_TEN;
				}
				else if(columnIndex==6){
					//return obj.HO_TEN;
				}
			}
			return "";
		}
	}
	private static class ContentProvider implements IStructuredContentProvider {
		public Object[] getElements(Object inputElement) {
			if(inputElement instanceof List){
				return ((List<BenhNhan>)inputElement).toArray();
			}
			return new Object[0];
		}
		public void dispose() {
		}
		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		}
	}

	protected Object result;
	protected Shell shellListBenhNhan;
	private Table table;
	private Text txtMaThe;
	private Text txtHoTen;
	private TableViewer tableViewer;
	private Button btnSearch;
	public String strMathe;
	public String strHoTen;

	public BenhNhan objBenhNhan = null;
	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public ListBenhNhanDlg(Shell parent, int style) {
		super(parent, style);
		setText("SWT Dialog");
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public Object open() {
		createContents();
		shellListBenhNhan.open();
		shellListBenhNhan.layout();
		Display display = getParent().getDisplay();
		while (!shellListBenhNhan.isDisposed()) {
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
		shellListBenhNhan = new Shell(getParent(), SWT.SHELL_TRIM);
		shellListBenhNhan.setImage(SWTResourceManager.getImage(ListBenhNhanDlg.class, "/png/align-center-2x.png"));
		shellListBenhNhan.setSize(992, 492);
		shellListBenhNhan.setText("Bệnh Nhân");
		shellListBenhNhan.setLayout(new BorderLayout(0, 0));
		
		Composite compositeBody = new Composite(shellListBenhNhan, SWT.NONE);
		compositeBody.setFont(SWTResourceManager.getFont("Tahoma", 12, SWT.NORMAL));
		compositeBody.setLayoutData(BorderLayout.CENTER);
		compositeBody.setLayout(new BorderLayout(0, 0));
		
		tableViewer = new TableViewer(compositeBody, SWT.BORDER | SWT.FULL_SELECTION);
		table = tableViewer.getTable();
		table.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.keyCode==13){
					//
					selectBenhNhan();
					//
				}
				else if(e.keyCode==SWT.ESC){
					objBenhNhan = null;
					shellListBenhNhan.close();
				}
			}
		});
		table.setFont(SWTResourceManager.getFont("Tahoma", 11, SWT.NORMAL));
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		
		TableColumn tblclmnNewColumn = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn.setWidth(173);
		tblclmnNewColumn.setText("Họ Tên");
		
		TableColumn tblclmnNewColumn_3 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_3.setWidth(106);
		tblclmnNewColumn_3.setText("Ngày Sinh");
		
		TableColumn tblclmnNewColumn_2 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_2.setWidth(46);
		tblclmnNewColumn_2.setText("GT");
		
		TableColumn tblclmnNewColumn_4 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_4.setWidth(136);
		tblclmnNewColumn_4.setText("Mã Thẻ");
		
		TableColumn tblclmnNewColumn_1 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_1.setWidth(363);
		tblclmnNewColumn_1.setText("Địa Chỉ");
		
		TableColumn tblclmnNewColumn_5 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_5.setWidth(153);
		tblclmnNewColumn_5.setText("SDT");
		tableViewer.setLabelProvider(new TableLabelProvider());
		tableViewer.setContentProvider(new ContentProvider());
		
		Composite compositeHead = new Composite(shellListBenhNhan, SWT.NONE);
		compositeHead.setFont(SWTResourceManager.getFont("Tahoma", 12, SWT.NORMAL));
		compositeHead.setLayoutData(BorderLayout.NORTH);
		
		Label label = new Label(compositeHead, SWT.NONE);
		label.setText("Tìm theo tên");
		label.setFont(SWTResourceManager.getFont("Tahoma", 12, SWT.NORMAL));
		label.setBounds(10, 35, 91, 19);
		
		Label label_1 = new Label(compositeHead, SWT.NONE);
		label_1.setText("Theo mã thẻ");
		label_1.setFont(SWTResourceManager.getFont("Tahoma", 12, SWT.NORMAL));
		label_1.setBounds(10, 3, 90, 19);
		
		txtMaThe = new Text(compositeHead, SWT.BORDER);
		txtMaThe.setFont(SWTResourceManager.getFont("Tahoma", 12, SWT.NORMAL));
		txtMaThe.setBounds(106, 0, 187, 25);
		
		txtHoTen = new Text(compositeHead, SWT.BORDER);
		txtHoTen.setFont(SWTResourceManager.getFont("Tahoma", 12, SWT.NORMAL));
		txtHoTen.setBounds(106, 32, 187, 25);
		
		btnSearch = new Button(compositeHead, SWT.NONE);
		btnSearch.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				doSearch();
			}
		});
		btnSearch.setText("Tìm");
		btnSearch.setImage(SWTResourceManager.getImage(ListBenhNhanDlg.class, "/png/magnifying-glass-2x.png"));
		btnSearch.setFont(SWTResourceManager.getFont("Tahoma", 12, SWT.NORMAL));
		btnSearch.setBounds(298, 30, 122, 29);

		shellListBenhNhan.setMaximized(true);
		loadData();
	}

	protected void selectBenhNhan() {
		if(table.getSelectionCount()==0){
			return;
		}
		TableItem item = table.getSelection()[0];
		objBenhNhan = (BenhNhan)item.getData();
		shellListBenhNhan.close();
	}

	private void loadData() {
		if(strMathe!=null)
			txtMaThe.setText(this.strMathe); 
		if(strHoTen!=null)
			txtHoTen.setText(this.strHoTen); 
		doSearch();
		
	}

	protected void doSearch() {
		String strMaThe = txtMaThe.getText().trim().toLowerCase();
		String strHoTen = txtHoTen.getText().trim().toLowerCase();
		
		try (Connection con = DbHelper.getSql2o()) {
			String sql = "SELECT * FROM benh_nhan ";
			if(strMaThe.length()==0 && strHoTen.length() == 0){
				//return;
			}
			else{
				sql += " where ";
			}
			int firstParam = 0;
			if( strHoTen.length()>0 ){
				sql += " LOWER(HO_TEN) like '%"+ strHoTen + "%'";
				firstParam ++;
			}
			
			if( strMaThe.length()>0){
				if(firstParam>0){
					sql += " or ";
				}
				sql += " LOWER(MA_THE) like '%" + strMaThe + "%' ";
			}
			sql += " order by DATE_ADD DESC";
			logger.info(sql);
			List<BenhNhan> listBenhnhan = con
					.createQuery(sql).executeAndFetch(BenhNhan.class);
					//.executeAndFetchTable().rows();
			//con.close();
			tableViewer.setInput(listBenhnhan);
			tableViewer.refresh();
		} catch (Exception ee) {
			logger.error(ee);
		}
	}
	
}
