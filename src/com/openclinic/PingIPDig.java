package com.openclinic;

import java.util.Calendar;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import com.openclinic.utils.Utils;

public class PingIPDig {

	protected Shell shell;
	private Text text;
	private DatePicker datePicker;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			PingIPDig window = new PingIPDig();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(450, 300);
		shell.setText("SWT Application");
		
		datePicker = new DatePicker(shell, SWT.NONE);
		datePicker.setBounds(32, 28, 198, 28);
		datePicker.setText("2016");
		
		text = new Text(shell, SWT.BORDER);
		text.setBounds(32, 62, 355, 41);
		
		Button btnNewButton = new Button(shell, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				double month = Utils.differenceInMonths(Calendar.getInstance(), datePicker.getDate2());
				int tuoi = 12 * 6;
				text.setText("" + tuoi + " month="+month);
			}
		});
		btnNewButton.setBounds(32, 119, 68, 23);
		btnNewButton.setText("New Button");

	}
}
