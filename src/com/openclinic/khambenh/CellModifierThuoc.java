package com.openclinic.khambenh;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TextCellEditor;

import com.model.dao.DvChitiet;
import com.model.dao.ThuocChitiet;
import com.openclinic.MaDichVuCellEditor;
import com.openclinic.utils.Utils;

public class CellModifierThuoc implements ICellModifier {
	public static String[] columnPropeties = new String[] { "0", "1", "2", "3", "4", "5", "6","7", "8" };
	public CellEditor[] editors;
	TableViewer tableCLS;
	public CellModifierThuoc(TableViewer tableCLS){
		//=========================================================
		this.tableCLS = tableCLS;
		editors = new CellEditor[9];
		editors[0] = new TextCellEditor(tableCLS.getTable());
		editors[1] = new TextCellEditor(tableCLS.getTable());
		editors[2] = new TextCellEditor(tableCLS.getTable());
		editors[3] = new TextCellEditor(tableCLS.getTable());
		editors[4] = new TextCellEditor(tableCLS.getTable());
		editors[5] = new TextCellEditor(tableCLS.getTable());
		editors[6] = new TextCellEditor(tableCLS.getTable());
		editors[7] = new TextCellEditor(tableCLS.getTable());
		editors[8] = new TextCellEditor(tableCLS.getTable());
		this.tableCLS.setCellEditors(editors);
		//=========================================================
	}
	//
	@Override
	public boolean canModify(Object element, String property) { 
		System.out.println("canModify property="+property+" element="+element);
		int columnIndex = Utils.getInt(property);
		if(columnIndex==5){
			ThuocChitiet objMabenh = (ThuocChitiet)element;
			if(objMabenh.STS<0){
				return true;
			}
			return false;
		}
		return false;
	}

	@Override
	public Object getValue(Object element, String property) {
		System.out.println("getValue property="+property);
		int columnIndex = Utils.getInt(property);
		ThuocChitiet objMabenh = (ThuocChitiet)element;
		if(columnIndex==5){
			System.out.println("getValue property="+property+" objMabenh.SO_LUONG="+objMabenh.SO_LUONG);
			return ""+objMabenh.SO_LUONG;
		}
		
		System.out.println("getValue property="+property+" 0");
		return "0";
	}

	@Override
	public void modify(Object element, String property, Object value) {
		System.out.println("modify property="+property);
		int columnIndex = Utils.getInt(property);
		org.eclipse.swt.widgets.TableItem item = (org.eclipse.swt.widgets.TableItem)element;
		ThuocChitiet objMabenh = (ThuocChitiet)item.getData();  
		if(columnIndex==5){
			//
			System.out.println("BEGIN "+objMabenh.SO_LUONG);
			objMabenh.SO_LUONG = Utils.getInt(value);
			System.out.println("END   "+objMabenh.SO_LUONG);
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
