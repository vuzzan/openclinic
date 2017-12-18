package com.openclinic.khambenh;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Monitor;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;

import com.openclinic.utils.Utils;

import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.widgets.Label;

public class FormMayTinhTienDlg extends Dialog {

	protected Object result;
	protected Shell shlTinhTinThi;
	private Text textTong;
	private Text textTien;
	private Text textThoi;
	public String TONGTIEN;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public FormMayTinhTienDlg(Shell parent, int style) {
		super(parent, style);
		setText("TINH TIỀN THỐI");
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public Object open() {
		createContents();
		shlTinhTinThi.open();
		shlTinhTinThi.layout();
		Display display = getParent().getDisplay();
		
		Monitor primary = display.getPrimaryMonitor();
		Rectangle bounds = primary.getBounds();
		Rectangle rect = shlTinhTinThi.getBounds();
		int x = bounds.x + (bounds.width - rect.width) / 2;
		int y = bounds.y + (bounds.height - rect.height) / 2;
		shlTinhTinThi.setLocation(x, y);
		
		Label lblBmEsc = new Label(shlTinhTinThi, SWT.NONE);
		lblBmEsc.setBounds(10, 282, 424, 13);
		lblBmEsc.setText("Bấm ESC để đóng lại");
		
		while (!shlTinhTinThi.isDisposed()) {
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
		shlTinhTinThi = new Shell(getParent(), getStyle());
		shlTinhTinThi.setSize(450, 374);
		shlTinhTinThi.setText("Tinh Tiền Thối");
		
		textTong = new Text(shlTinhTinThi, SWT.BORDER | SWT.RIGHT);
		textTong.setEditable(false);
		textTong.setText("500000");
		textTong.setFont(SWTResourceManager.getFont("Tahoma", 33, SWT.NORMAL));
		textTong.setBounds(10, 10, 424, 74);
		
		textTien = new Text(shlTinhTinThi, SWT.BORDER | SWT.RIGHT);
		textTien.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
			}
		});
		textTien.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				calculateThoi();
			}
		});
		textTien.setText("200000");
		textTien.setFont(SWTResourceManager.getFont("Tahoma", 33, SWT.NORMAL));
		textTien.setBounds(10, 99, 424, 74);
		
		textThoi = new Text(shlTinhTinThi, SWT.BORDER | SWT.RIGHT);
		textThoi.setEditable(false);
		textThoi.setText("300000");
		textThoi.setFont(SWTResourceManager.getFont("Tahoma", 33, SWT.NORMAL));
		textThoi.setBounds(10, 191, 424, 74);

		setData();
	}

	private void setData() {
		textTong.setText(java.text.NumberFormat.getInstance(java.util.Locale.ITALY).format(Utils.getInt(TONGTIEN)));
		textTien.setText("0");
		calculateThoi();
		textTien.selectAll();
		textTien.forceFocus();
	}

	private void calculateThoi() {
		if(textThoi==null)
			return;
		long tien = Utils.getInt(textTien.getText());
		long thoi = Utils.getInt(TONGTIEN) - tien;
		textThoi.setText(java.text.NumberFormat.getInstance(java.util.Locale.ITALY).format(thoi));
		//textTien.setText(java.text.NumberFormat.getInstance(java.util.Locale.ITALY).format(tien));
	}
}
