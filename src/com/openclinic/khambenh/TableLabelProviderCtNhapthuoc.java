package com.openclinic.khambenh;

import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

import com.model.dao.CtNhapthuoc;
import com.openclinic.utils.Utils;

public class TableLabelProviderCtNhapthuoc extends LabelProvider implements
		ITableLabelProvider {
	public Image getColumnImage(Object element, int columnIndex) {
		return null;
	}

	public String getColumnText(Object element, int columnIndex) {
		if (element instanceof CtNhapthuoc) {
			// return ((CtNhapthuoc) element).getIndex(columnIndex);
			CtNhapthuoc obj = (CtNhapthuoc) element;
			if (columnIndex == 0) {
				return obj.TENKHO;
			} else if (columnIndex == 1) {
				return obj.TENTHUOC;
			} else if (columnIndex == 2) {
				return (obj.HAM_LUONG==null?"":obj.HAM_LUONG);
			} else if (columnIndex == 3) {
				return obj.DONVI;
			} else if (columnIndex == 4) {
				return (obj.HANDUNG);
			} else if (columnIndex == 5) {
				if( obj.TYP==1){
					return Utils.getMoneyDefault(obj.DONGIA);
				}
				else{
					return Utils.getMoneyDefault(obj.DONGIA)+"|"+Utils.getMoneyDefault(obj.DONGIA_BAN);
				}
			} else if (columnIndex == 6) {
				return "" + obj.SL_TONKHO;
			} else if (columnIndex == 7) {
				return "" + obj.CTID_FROM;
			} else if (columnIndex == 8) {
				return "" + obj.SL_DADUNG;
			} else if (columnIndex == 9) {
				return obj.SOLUONG + "";
			} else if (columnIndex == 10) {
			}
		}
		return "";
	}
}