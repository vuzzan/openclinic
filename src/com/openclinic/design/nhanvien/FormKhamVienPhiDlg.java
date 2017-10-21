package com.openclinic.design.nhanvien;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Label;
import com.openclinic.TheBHXH;
import org.eclipse.swt.widgets.Text;
import com.openclinic.DatePicker;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;

public class FormKhamVienPhiDlg extends Dialog {

	protected Object result;
	protected Shell shell;
	private Text text;
	private Text text_1;
	private Text text_2;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public FormKhamVienPhiDlg(Shell parent, int style) {
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
		shell.setSize(564, 339);
		shell.setText(getText());
		
		Group group = new Group(shell, SWT.NONE);
		group.setLayout(null);
		group.setText("Thông tin bệnh nhân");
		group.setFont(SWTResourceManager.getFont("Tahoma", 11, SWT.BOLD));
		group.setBounds(10, 10, 523, 236);
		
		text = new Text(group, SWT.BORDER);
		text.setFont(SWTResourceManager.getFont("Tahoma", 16, SWT.NORMAL));
		text.setBackground(SWTResourceManager.getColor(SWT.COLOR_YELLOW));
		text.setBounds(113, 60, 386, 28);
		
		DatePicker datePicker = new DatePicker(group, SWT.NONE);
		datePicker.setBounds(113, 94, 127, 28);
		
		text_1 = new Text(group, SWT.BORDER);
		text_1.setTextLimit(5);
		text_1.setText("");
		text_1.setFont(SWTResourceManager.getFont("Tahoma", 16, SWT.NORMAL));
		text_1.setEnabled(true);
		text_1.setBounds(293, 94, 57, 28);
		
		Button button = new Button(group, SWT.TOGGLE);
		button.setText("NAM(1)");
		button.setSelection(false);
		button.setImage(SWTResourceManager.getImage(FormKhamVienPhiDlg.class, "/png/person-2x.png"));
		button.setFont(SWTResourceManager.getFont("Tahoma", 14, SWT.NORMAL));
		button.setBounds(402, 94, 97, 28);
		
		text_2 = new Text(group, SWT.NONE);
		text_2.setFont(SWTResourceManager.getFont("Tahoma", 16, SWT.NORMAL));
		text_2.setBackground(SWTResourceManager.getColor(SWT.COLOR_YELLOW));
		text_2.setBounds(113, 128, 386, 28);
		
		Label label_1 = new Label(group, SWT.NONE);
		label_1.setText("Địa Chỉ");
		label_1.setFont(SWTResourceManager.getFont("Tahoma", 12, SWT.NORMAL));
		label_1.setBounds(10, 133, 97, 23);
		
		Label label_2 = new Label(group, SWT.NONE);
		label_2.setText("Họ Tên");
		label_2.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label_2.setFont(SWTResourceManager.getFont("Tahoma", 12, SWT.NORMAL));
		label_2.setBounds(10, 65, 97, 23);
		
		Label label_3 = new Label(group, SWT.NONE);
		label_3.setText("Ngày Sinh");
		label_3.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label_3.setFont(SWTResourceManager.getFont("Tahoma", 12, SWT.NORMAL));
		label_3.setBounds(10, 97, 97, 23);
		
		Label label_14 = new Label(group, SWT.NONE);
		label_14.setText("NS");
		label_14.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label_14.setFont(SWTResourceManager.getFont("Tahoma", 12, SWT.NORMAL));
		label_14.setBounds(370, 99, 26, 23);
		
		Label label_15 = new Label(group, SWT.NONE);
		label_15.setText("Cân");
		label_15.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label_15.setFont(SWTResourceManager.getFont("Tahoma", 12, SWT.NORMAL));
		label_15.setBounds(253, 99, 34, 23);
		
		Combo combo = new Combo(group, SWT.NONE);
		combo.setToolTipText("Bấm ENTER để tìm bệnh");
		combo.setFont(SWTResourceManager.getFont("Tahoma", 16, SWT.NORMAL));
		combo.setBackground(SWTResourceManager.getColor(SWT.COLOR_YELLOW));
		combo.setBounds(113, 176, 386, 33);
		combo.setText("");
		
		Label label = new Label(group, SWT.NONE);
		label.setBounds(10, 184, 97, 23);
		label.setText("Khoa/BS");
		label.setFont(SWTResourceManager.getFont("Tahoma", 12, SWT.NORMAL));
		
		Button button_1 = new Button(shell, SWT.NONE);
		button_1.setBounds(284, 264, 249, 34);
		button_1.setText("Lưu Phiếu (F2)");
		button_1.setImage(SWTResourceManager.getImage(FormKhamVienPhiDlg.class, "/png/check-3x.png"));
		button_1.setFont(SWTResourceManager.getFont("Tahoma", 12, SWT.NORMAL));

	}

}
