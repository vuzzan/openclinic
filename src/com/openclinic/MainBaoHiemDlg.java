package com.openclinic;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import swing2swt.layout.BorderLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;

public class MainBaoHiemDlg extends Dialog {

	protected Object result;
	protected Shell shlBoCoBo;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public MainBaoHiemDlg(Shell parent, int style) {
		super(parent, style);
		setText("SWT Dialog");
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public Object open() {
		createContents();
		shlBoCoBo.open();
		shlBoCoBo.layout();
		Display display = getParent().getDisplay();
		while (!shlBoCoBo.isDisposed()) {
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
		shlBoCoBo = new Shell(getParent(), getStyle());
		shlBoCoBo.setSize(611, 358);
		shlBoCoBo.setText("OpenClinic: Báo Cáo Bảo Hiểm");
		shlBoCoBo.setLayout(new BorderLayout(0, 0));
		
		Composite composite = new Composite(shlBoCoBo, SWT.NONE);
		composite.setLayoutData(BorderLayout.NORTH);
		
		TabFolder tabFolder = new TabFolder(shlBoCoBo, SWT.NONE);
		tabFolder.setLayoutData(BorderLayout.CENTER);
		
		TabItem tabItem = new TabItem(tabFolder, SWT.NONE);
		tabItem.setText("New Item");
		
		Composite composite_1 = new Composite(tabFolder, SWT.NONE);
		tabItem.setControl(composite_1);
		
		TabItem tbtmNewItem = new TabItem(tabFolder, SWT.NONE);
		tbtmNewItem.setText("New Item");
		
		Composite composite_2 = new Composite(tabFolder, SWT.NONE);
		tbtmNewItem.setControl(composite_2);
		
		TabItem tbtmNewItem_1 = new TabItem(tabFolder, SWT.NONE);
		tbtmNewItem_1.setText("New Item");
		
		Composite composite_3 = new Composite(tabFolder, SWT.NONE);
		tbtmNewItem_1.setControl(composite_3);

	}
}
