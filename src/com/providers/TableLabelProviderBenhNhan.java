package com.providers;

import org.eclipse.jface.viewers.IColorProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.wb.swt.SWTResourceManager;
import org.sql2o.data.Row;

import com.openclinic.utils.Utils;

public class TableLabelProviderBenhNhan extends LabelProvider implements ITableLabelProvider, IColorProvider {
	public Image getColumnImage(Object element, int columnIndex) {
		return null;
	}

	public String getColumnText(Object element, int columnIndex) {
		if(element instanceof Row){
			Row obj = (Row)element;
			if(columnIndex==0){
				return Utils.getTinhTrangPhieuKham( obj.getInteger("STS").intValue() );
			}
			else if(columnIndex==1){
				return Utils.getDatetime( obj.getDate("KB_DATE"), "HH:mm dd/MM/YY");
			}
			else if(columnIndex==2){
				return obj.getString("HO_TEN").toString();
			}
			else if(columnIndex==3){
				return obj.getString("MA_THE").toString();
			}
			else if(columnIndex==4){
				return Utils.getKieuThanhToan( obj.getInteger("KIEU_TT").intValue() );
			}
			else if(columnIndex==5){
				return obj.getString("T_VTYT").toString();
			}
			else if(columnIndex==6){
				if( obj.getString("NGAY_TTOAN").toString().length()> 10 ){
					return "Đã TT";
				}
				return "Chưa";
			}
			else if(columnIndex==7){
				return obj.getString("MA_KHOA").toString();
			}
			else if(columnIndex==8){
				String MAKHOA =obj.getString("MA_KHOA").toString();
				//String tenKhoa = LoginDlg.hashKhoaPhong.get(MAKHOA);
				return MAKHOA;
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
		if(element instanceof Row){
			Row obj = (Row)element;
			if( obj.getString("NGAY_TTOAN").toString().length()> 10 ){
				return SWTResourceManager.getColor(SWT.COLOR_GREEN);
			}
			
			return Utils.getTinhTrangPhieuKhamColor( obj.getInteger("STS").intValue() );
		}
		return null;
	}
}