package com.providers;

import org.eclipse.jface.viewers.IColorProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.wb.swt.SWTResourceManager;

import com.model.dao.DvChitiet;
import com.model.dao.ThuocChitiet;
import com.openclinic.utils.Utils;

public class TableLabelProviderTHUOC extends LabelProvider implements ITableLabelProvider, IColorProvider {
	public Image getColumnImage(Object element, int columnIndex) {
		return null;
	}
	public String getColumnText(Object element, int columnIndex) {
		if(element instanceof ThuocChitiet){
			ThuocChitiet obj = (ThuocChitiet)element;
			if(columnIndex==0){
				return ""+(obj.STT==null?"1":obj.STT.intValue());
			}
			else if(columnIndex==1){
				return obj.TEN_THUOC;
			}
			else if(columnIndex==2){
				return obj.MA_THUOC;
			}
			else if(columnIndex==3){
				return obj.HAM_LUONG;
			}
			else if(columnIndex==4){
				return obj.DUONG_DUNG;
			}
			else if(columnIndex==5){
				return ""+(obj.SO_LUONG==null?"1":obj.SO_LUONG.intValue());
			}
			else if(columnIndex==6){
				return ""+(obj.STS==null?"1":Utils.getTinhTrangThuoc(obj.STS));
			}
			else if(columnIndex==7){
				return ""+(obj.THANH_TIEN==null?"0":obj.THANH_TIEN.intValue());
			}
			else if(columnIndex==8){
				return ""+(obj.TT_BH==null?"0":obj.TT_BH.intValue());
			}
			else if(columnIndex==9){
				return ""+(obj.LIEU_DUNG);
			}
			else{
				return "";
			}
			
		}
		return "";
	}
	@Override
	public Color getForeground(Object element) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Color getBackground(Object element) {
		if(element instanceof ThuocChitiet){
			ThuocChitiet obj = (ThuocChitiet)element;
			if(obj.STS<0){
				return SWTResourceManager.getColor(SWT.COLOR_GRAY);
			}
			else{
				return SWTResourceManager.getColor(SWT.COLOR_GREEN);
			}
			
		}
		return null;
	}
}