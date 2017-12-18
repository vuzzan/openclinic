package com.providers;

import org.eclipse.jface.viewers.IColorProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.wb.swt.SWTResourceManager;

import com.DbHelper;
import com.model.dao.DvChitiet;
import com.model.dao.Users;
import com.openclinic.utils.Utils;

public class TableLabelProviderCLS extends LabelProvider implements ITableLabelProvider, IColorProvider {
	public Image getColumnImage(Object element, int columnIndex) {
		return null;
	}
	public String getColumnText(Object element, int columnIndex) {
		if(element instanceof DvChitiet){
			DvChitiet obj = (DvChitiet)element;
			if(columnIndex==0){
				return ""+(obj.DV_ID==null?"#":obj.DV_ID.intValue());
			}
			else if(columnIndex==1){
				return obj.MA_DICH_VU;
			}
			else if(columnIndex==2){
				
				return obj.TEN_DICH_VU;
			}
			else if(columnIndex==3){
				return ""+(obj.SO_LUONG==null?"1":obj.SO_LUONG.intValue());
			}
			else if(columnIndex==4){
				return ""+(obj.STS<0?"Chưa lưu":Utils.getTinhTrangCanLamSan(obj.STS));// + " " +obj.hashCode();
			}
			else if(columnIndex==5){
				if( obj.MUC_HUONG==0 ){
					return ""+(obj.THANH_TIEN2==null?"0":obj.THANH_TIEN2.intValue());
				}

				return ""+(obj.THANH_TIEN==null?"0":obj.THANH_TIEN.intValue());
			}
			else if(columnIndex==6){
				return ""+(obj.TT_BHTT==null?"0":obj.TT_BHTT.intValue());
			}
			else if(columnIndex==7){
				return ""+(obj.TT_BNTT==null?"0":obj.TT_BNTT.intValue());
			}
			else if(columnIndex==8){
				Users bacsi = DbHelper.hashDataUsersMaCCHN.get(obj.MA_BAC_SI);
				return ""+(bacsi==null?"":bacsi.TEN_NHANVIEN);
			}
			else if(columnIndex==9){
				return ""+(obj.MUC_HUONG==0?"Tự CT":"BH");
			}
			else if(columnIndex==10){
				return ""+(obj.THANHTOAN==1?"Xong":"Chưa");
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
		if(element instanceof DvChitiet){
			DvChitiet obj = (DvChitiet)element;
			if(obj.STS<0){
				return SWTResourceManager.getColor(SWT.COLOR_GRAY);
			}
			else if(obj.THANHTOAN==0){
				return SWTResourceManager.getColor(SWT.COLOR_GRAY);
			}
			else if(obj.THANHTOAN==1){
				return SWTResourceManager.getColor(SWT.COLOR_DARK_GREEN);
			}
			else{
				return SWTResourceManager.getColor(SWT.COLOR_GREEN);
			}
			
		}
		return null;
	}
}