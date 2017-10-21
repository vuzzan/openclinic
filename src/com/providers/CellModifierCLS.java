package com.providers;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ICellEditorListener;
import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.widgets.TableItem;

import com.model.dao.DvChitiet;
import com.openclinic.MaDichVuCellEditor;
import com.openclinic.TableTextCellEditor;
import com.openclinic.utils.Utils;

public class CellModifierCLS implements ICellModifier {
	public CellEditor[] editors;
	public static String[] columnPropeties = new String[] { "0", "1", "2", "3", "4", "5" };
	TableViewer tableCLS;
	public CellModifierCLS(TableViewer tableCLS){
		//=========================================================
		this.tableCLS = tableCLS;
		editors = new CellEditor[5];
		editors[0] = new TextCellEditor(tableCLS.getTable());
		editors[1] = new MaDichVuCellEditor(tableCLS);
		editors[2] = new TextCellEditor(tableCLS.getTable());
		editors[3] = new TextCellEditor(tableCLS.getTable());
		editors[4] = new TextCellEditor(tableCLS.getTable());
		//=========================================================
	}
	//
	@Override
	public boolean canModify(Object element, String property) {
		int columnIndex = Utils.getInt(property);
		if(columnIndex==1){
			return true;
		}
		return false;
	}

	@Override
	public Object getValue(Object element, String property) {
		int columnIndex = Utils.getInt(property);
		DvChitiet objMabenh = (DvChitiet)element;
		if(columnIndex==1){
			return objMabenh.MA_DICH_VU;
		}
		else if(columnIndex==0){
			return objMabenh.TEN_DICH_VU;
		}
		return "";
	}

	@Override
	public void modify(Object element, String property, Object value) {
		int columnIndex = Utils.getInt(property);
		//TableItem item = (TableItem)element;
		// DvChitiet objMabenh = (DvChitiet)item.getData();
		DvChitiet objMabenh = (DvChitiet)element;
		if(columnIndex==1){
			//
			System.out.println("BEGIN "+objMabenh.MA_DICH_VU);
			objMabenh.MA_DICH_VU = (String)value;
			System.out.println("END   "+objMabenh.MA_DICH_VU);
		}
		updateTableViewer();
		return;
	}

	public void updateTableViewer()
	{
	    if(tableCLS != null)
	    	tableCLS.refresh();
	}
}