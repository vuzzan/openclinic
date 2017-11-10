package com.openclinic.khambenh;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import swing2swt.layout.BorderLayout;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Table;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import com.model.dao.KhamBenh;

public class FormHuyGiamPhieuKhamDlg extends Dialog {

	protected Object result;
	protected Shell shlHyPhiuHoc;
	private Text txtSoTien;
	private Table table;
	private Text txtLyDo;
	private Text txtNguoiLam;
	public KhamBenh objPhieuKhamBenh;
	private TableViewer tableViewer;
	private Button btnRadioHuy;
	private Button btnLuLi;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public FormHuyGiamPhieuKhamDlg(Shell parent, int style) {
		super(parent, style);
		setText("SWT Dialog");
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public Object open() {
		createContents();
		shlHyPhiuHoc.open();
		shlHyPhiuHoc.layout();
		Display display = getParent().getDisplay();
		while (!shlHyPhiuHoc.isDisposed()) {
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
		shlHyPhiuHoc = new Shell(getParent(), getStyle());
		shlHyPhiuHoc.setSize(520, 365);
		shlHyPhiuHoc.setText("Hủy Phiếu hoặc Giảm Giá");
		shlHyPhiuHoc.setLayout(new BorderLayout(0, 0));
		
		Composite composite = new Composite(shlHyPhiuHoc, SWT.NONE);
		composite.setLayoutData(BorderLayout.NORTH);
		
		Composite composite_1 = new Composite(shlHyPhiuHoc, SWT.NONE);
		composite_1.setLayoutData(BorderLayout.SOUTH);
		composite_1.setLayout(new GridLayout(5, false));
		
		Label lblLDo = new Label(composite_1, SWT.NONE);
		lblLDo.setText("Lý do");
		
		txtLyDo = new Text(composite_1, SWT.BORDER);
		GridData gd_txtLyDo = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_txtLyDo.widthHint = 223;
		txtLyDo.setLayoutData(gd_txtLyDo);
		
		Label lblNewLabel = new Label(composite_1, SWT.NONE);
		lblNewLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel.setText("Số tiền");
		
		txtSoTien = new Text(composite_1, SWT.BORDER);
		
		Button btnRadioGiamTien = new Button(composite_1, SWT.RADIO);
		btnRadioGiamTien.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		btnRadioGiamTien.setText("Giảm tiền");
		
		Label lblNgiLm = new Label(composite_1, SWT.NONE);
		lblNgiLm.setText("Người yêu cầu");
		
		txtNguoiLam = new Text(composite_1, SWT.BORDER);
		txtNguoiLam.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		new Label(composite_1, SWT.NONE);
		new Label(composite_1, SWT.NONE);
		
		btnRadioHuy = new Button(composite_1, SWT.RADIO);
		btnRadioHuy.setSelection(true);
		btnRadioHuy.setFont(SWTResourceManager.getFont("Tahoma", 8, SWT.BOLD));
		btnRadioHuy.setText("Hủy Phiếu");
		new Label(composite_1, SWT.NONE);
		new Label(composite_1, SWT.NONE);
		new Label(composite_1, SWT.NONE);
		
		Button btnNewButton_1 = new Button(composite_1, SWT.NONE);
		btnNewButton_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		btnNewButton_1.setText("Bỏ qua");
		
		btnLuLi = new Button(composite_1, SWT.NONE);
		btnLuLi.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		btnLuLi.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		btnLuLi.setText("Lưu Lại");
		
		tableViewer = new TableViewer(shlHyPhiuHoc, SWT.BORDER | SWT.FULL_SELECTION);
		table = tableViewer.getTable();
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		table.setLayoutData(BorderLayout.CENTER);
		
		TableColumn tblclmnNewColumn = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn.setWidth(114);
		tblclmnNewColumn.setText("Thời gian");
		
		TableColumn tblclmnNewColumn_4 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_4.setWidth(283);
		tblclmnNewColumn_4.setText("Nội dung");
		
		TableColumn tblclmnNewColumn_3 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_3.setWidth(100);
		tblclmnNewColumn_3.setText("Tên người làm");

		startDlg();
	}

	private void startDlg() {
		// 
		
	}

	
}
