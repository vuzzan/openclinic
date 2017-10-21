package com.openclinic;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;

public class MaDichVuCellEditor extends CellEditor {

	private Text text;
	public MaDichVuCellEditor(TableViewer tableViewer) {
		super(tableViewer.getTable(), 0);
	}

	@Override
	protected Control createControl(Composite parent) {
		text = new Text(parent, getStyle());
		text.addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent e) {
				System.out.println(e.keyCode);
			}
		});
		return null;
	}

	@Override
	protected Object doGetValue() {

		return null;
	}

	@Override
	protected void doSetFocus() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void doSetValue(Object value) {
		// TODO Auto-generated method stub

	}

}
