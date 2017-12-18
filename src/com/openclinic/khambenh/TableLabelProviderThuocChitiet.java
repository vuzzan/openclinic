package com.openclinic.khambenh;

import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

import com.model.dao.ThuocChitiet;
import com.openclinic.utils.Utils;

public class TableLabelProviderThuocChitiet extends LabelProvider
		implements ITableLabelProvider {
	public Image getColumnImage(Object element, int columnIndex) {
		return null;
	}

	public String getColumnText(Object element, int columnIndex) {
		if (element instanceof ThuocChitiet) {
			// return ((ThuocChitiet) element).getIndex(columnIndex);
			ThuocChitiet obj = (ThuocChitiet) element;
			if (columnIndex == 0) {
				return "" + obj.STT;
			} else if (columnIndex == 1) {
				return "" + obj.KHO_NAME;
			} else if (columnIndex == 2) {
				return "" + obj.CT_ID;
			} else if (columnIndex == 3) {
				return "" + obj.TEN_THUOC;
			} else if (columnIndex == 4) {
				return "" + obj.DON_VI_TINH;
			} else if (columnIndex == 5) {
				return "" + obj.SO_LUONG;
			} else if (columnIndex == 6) {
				return "" + obj.DON_GIA;
			} else if (columnIndex == 7) {
				return "" + obj.THANH_TIEN;
			} else if (columnIndex == 8) {
				if(obj.THANHTOAN==1){
					return "TToan";
				}
				return ""+(obj.STS==null?"1":Utils.getTinhTrangThuoc(obj.STS))+obj.STS;
				//return "" + obj.STS;
			} else if (columnIndex == 9) {
				return "" + obj.MA_THUOC;
			} else if (columnIndex == 10) {
				return "" + obj.HAM_LUONG;
			} else if (columnIndex == 11) {
				return "" + obj.DUONG_DUNG;
			}
		}
		return "";
	}
}