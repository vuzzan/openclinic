package com.openclinic.design.nhanvien;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Text;
import com.openclinic.DatePicker;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;

public class NewPhieuKhamBenhDlg extends Dialog {

	protected Object result;
	protected Shell shell;
	private Text text;
	private Text text_1;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public NewPhieuKhamBenhDlg(Shell parent, int style) {
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
		shell = new Shell(getParent(), getStyle());
		shell.setSize(530, 300);
		shell.setText(getText());
		
		Label label = new Label(shell, SWT.NONE);
		label.setText("Họ Tên");
		label.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label.setFont(SWTResourceManager.getFont("Tahoma", 12, SWT.NORMAL));
		label.setBounds(10, 61, 97, 23);
		
		text = new Text(shell, SWT.BORDER);
		text.setFont(SWTResourceManager.getFont("Tahoma", 16, SWT.NORMAL));
		text.setBackground(SWTResourceManager.getColor(SWT.COLOR_YELLOW));
		text.setBounds(113, 56, 386, 28);
		
		Label label_1 = new Label(shell, SWT.NONE);
		label_1.setText("Ngày Sinh");
		label_1.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label_1.setFont(SWTResourceManager.getFont("Tahoma", 12, SWT.NORMAL));
		label_1.setBounds(10, 93, 97, 23);
		
		DatePicker datePicker = new DatePicker(shell, SWT.NONE);
		datePicker.setBounds(113, 90, 127, 28);
		
		Button button = new Button(shell, SWT.TOGGLE);
		button.setText("NAM(1)");
		button.setSelection(false);
		button.setImage(SWTResourceManager.getImage(NewPhieuKhamBenhDlg.class, "/png/person-2x.png"));
		button.setFont(SWTResourceManager.getFont("Tahoma", 14, SWT.NORMAL));
		button.setBounds(402, 90, 97, 28);
		
		text_1 = new Text(shell, SWT.NONE);
		text_1.setFont(SWTResourceManager.getFont("Tahoma", 16, SWT.NORMAL));
		text_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_YELLOW));
		text_1.setBounds(113, 122, 386, 28);
		
		Label label_2 = new Label(shell, SWT.NONE);
		label_2.setText("Địa Chỉ");
		label_2.setFont(SWTResourceManager.getFont("Tahoma", 12, SWT.NORMAL));
		label_2.setBounds(10, 127, 97, 23);
		
		Label lblGiiTnh = new Label(shell, SWT.NONE);
		lblGiiTnh.setText("Giới tính");
		lblGiiTnh.setFont(SWTResourceManager.getFont("Tahoma", 12, SWT.NORMAL));
		lblGiiTnh.setBounds(331, 93, 65, 23);
		
		Label lblMuaPhiuKhm = new Label(shell, SWT.NONE);
		lblMuaPhiuKhm.setText("Mua phiếu khám bệnh");
		lblMuaPhiuKhm.setFont(SWTResourceManager.getFont("Tahoma", 12, SWT.NORMAL));
		lblMuaPhiuKhm.setBounds(10, 10, 421, 23);
		
		Label lblChnCls = new Label(shell, SWT.NONE);
		lblChnCls.setText("Chọn CLS");
		lblChnCls.setFont(SWTResourceManager.getFont("Tahoma", 12, SWT.NORMAL));
		lblChnCls.setBounds(10, 156, 97, 23);
		
		Combo combo = new Combo(shell, SWT.NONE);
		combo.setFont(SWTResourceManager.getFont("Tahoma", 13, SWT.NORMAL));
		combo.setBounds(113, 158, 386, 21);
		combo.setText("Siêu âm...");
		
		Button button_1 = new Button(shell, SWT.NONE);
		button_1.setText("In Phiếu");
		button_1.setImage(SWTResourceManager.getImage(NewPhieuKhamBenhDlg.class, "/png/print-3x.png"));
		button_1.setFont(SWTResourceManager.getFont("Tahoma", 12, SWT.NORMAL));
		button_1.setBounds(326, 231, 173, 34);

	}
}
