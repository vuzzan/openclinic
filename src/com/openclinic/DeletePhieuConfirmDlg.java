package com.openclinic;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Monitor;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.GridData;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Rectangle;

import com.DbHelper;

public class DeletePhieuConfirmDlg extends Dialog {

	protected Object result;
	protected Shell shellXacNhanXoa;
	private Text txtUser;
	private Text txtLyDo;
	public String strLydoXoa = "";
	private Label lblLchS;
	private Text txtLichSu;
	public String strLichSu;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public DeletePhieuConfirmDlg(Shell parent, int style) {
		super(parent, style);
		setText("SWT Dialog");
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public Object open() {
		createContents();
		shellXacNhanXoa.open();
		shellXacNhanXoa.layout();
		Display display = getParent().getDisplay();
		
		// Move to center
		Monitor primary = display.getPrimaryMonitor();
		Rectangle bounds = primary.getBounds();
		Rectangle rect = shellXacNhanXoa.getBounds();
		int x = bounds.x + (bounds.width - rect.width) / 2;
		int y = bounds.y + (bounds.height - rect.height) / 2;
		shellXacNhanXoa.setLocation(x, y);
		
		while (!shellXacNhanXoa.isDisposed()) {
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
		shellXacNhanXoa = new Shell(getParent(), SWT.DIALOG_TRIM | SWT.PRIMARY_MODAL);
		shellXacNhanXoa.setSize(686, 420);
		shellXacNhanXoa.setText("Xác Nhận Xóa Phiếu");
		shellXacNhanXoa.setLayout(new GridLayout(1, false));
		
		Label lblNewLabel = new Label(shellXacNhanXoa, SWT.NONE);
		lblNewLabel.setFont(SWTResourceManager.getFont("Tahoma", 14, SWT.NORMAL));
		lblNewLabel.setText("Xác nhận xóa phiếu khám");
		
		txtUser = new Text(shellXacNhanXoa, SWT.BORDER);
		txtUser.setFont(SWTResourceManager.getFont("Tahoma", 11, SWT.NORMAL));
		GridData gd_txtUser = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd_txtUser.heightHint = 32;
		txtUser.setLayoutData(gd_txtUser);
		txtUser.setEnabled(false);
		
		lblLchS = new Label(shellXacNhanXoa, SWT.NONE);
		lblLchS.setText("lịch sử");
		lblLchS.setFont(SWTResourceManager.getFont("Tahoma", 14, SWT.NORMAL));
		
		txtLichSu = new Text(shellXacNhanXoa, SWT.BORDER | SWT.MULTI);
		txtLichSu.setEditable(false);
		txtLichSu.setText("Ghi rõ lý do xóa, ít nhất 20 ký tự");
		txtLichSu.setFont(SWTResourceManager.getFont("Tahoma", 11, SWT.NORMAL));
		GridData gd_txtLichSu = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd_txtLichSu.heightHint = 130;
		txtLichSu.setLayoutData(gd_txtLichSu);
		
		Label lblLDo = new Label(shellXacNhanXoa, SWT.NONE);
		lblLDo.setFont(SWTResourceManager.getFont("Tahoma", 14, SWT.NORMAL));
		GridData gd_lblLDo = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_lblLDo.heightHint = 22;
		lblLDo.setLayoutData(gd_lblLDo);
		lblLDo.setText("Lý do/duyệt:");
		
		txtLyDo = new Text(shellXacNhanXoa, SWT.BORDER | SWT.MULTI);
		txtLyDo.setText("Ghi rõ lý do xóa, ít nhất 20 ký tự");
		txtLyDo.setFont(SWTResourceManager.getFont("Tahoma", 11, SWT.NORMAL));
		GridData gd_text = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd_text.heightHint = 50;
		txtLyDo.setLayoutData(gd_text);
		
		Button btnXcNhnXa = new Button(shellXacNhanXoa, SWT.NONE);
		btnXcNhnXa.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(txtLyDo.getText().trim().length()<20){
					MessageDialog.openError(shellXacNhanXoa, "Có lỗi", "Nhập lý do xóa, ít nhất 20 ký tự");
					return;
				}
				else{
					strLydoXoa = txtLyDo.getText().trim();
					shellXacNhanXoa.close();
				}
			}
		});
		btnXcNhnXa.setFont(SWTResourceManager.getFont("Tahoma", 14, SWT.NORMAL));
		GridData gd_btnXcNhnXa = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_btnXcNhnXa.heightHint = 53;
		btnXcNhnXa.setLayoutData(gd_btnXcNhnXa);
		btnXcNhnXa.setText("Xác nhận xóa");
		//
		startApp();
	}

	private void startApp() {
		txtUser.setText("Tôi là: " + DbHelper.currentSessionUserId.U_NAME +","+DbHelper.currentSessionUserId.TEN_NHANVIEN);
		txtLichSu.setText(strLichSu);
		txtLyDo.selectAll();
		txtLyDo.forceFocus();
		//
	}
}
