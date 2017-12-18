package com.openclinic.khambenh;

import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

import com.model.dao.Dichvu;

class TableLabelProvider extends LabelProvider implements
		ITableLabelProvider {
	public Image getColumnImage(Object element, int columnIndex) {
		return null;
	}

	public String getColumnText(Object element, int columnIndex) {
		if (element instanceof Dichvu) {
			Dichvu obj = (Dichvu) element;
			if (columnIndex == 0) {
				return obj.MA_DVKT;
			} else if (columnIndex == 1) {
				return obj.TEN_DVKT;
			} else if (columnIndex == 2) {
				return ""
						+ java.text.NumberFormat.getInstance(
								java.util.Locale.ITALY).format(obj.DON_GIA);
			} else if (columnIndex == 3) {
				return ""
						+ java.text.NumberFormat.getInstance(
								java.util.Locale.ITALY)
								.format(obj.DON_GIA2);
			} else if (columnIndex == 4) {
				return obj.MANHOM_9324;
			} else if (columnIndex == 5) {
				return obj.QUYET_DINH;
			}
		}
		return "";
	}
}