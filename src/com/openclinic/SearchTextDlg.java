package com.openclinic;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;

import com.openclinic.khambenh.FormKhamBenhDlg;

public class SearchTextDlg extends Dialog {

	protected Object result;
	protected Shell shell;
	private Text txtSearchText;
	public String strSearchText = "";
	public static FormKhamBenhDlg parentDlg = null;
	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public SearchTextDlg(Shell parent, int style) {
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
		shell = new Shell(getParent(), SWT.NONE);
		shell.setSize(450, 55);
		shell.setText(getText());
		shell.addListener(SWT.Traverse, new Listener() {
			@Override
			public void handleEvent(Event e) {
				// TODO Auto-generated method stub
	            if (e.detail == SWT.TRAVERSE_ESCAPE) {
	            	//e.doit = false;
	            }			
			}
        });
		txtSearchText = new Text(shell, SWT.BORDER);
		txtSearchText.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				strSearchText = txtSearchText.getText();
				if(parentDlg!=null){
					parentDlg.strSearchPhieuKham = strSearchText;
					parentDlg.updateSearchText();
				}
			}
		});
		txtSearchText.setText(strSearchText);
		txtSearchText.setFont(SWTResourceManager.getFont("Tahoma", 13, SWT.NORMAL));
		txtSearchText.setBounds(10, 10, 428, 33);
	}

	public void update(){
		if(txtSearchText!=null){
			txtSearchText.setText(strSearchText);
		}
	}
}
