package com.providers;

import org.eclipse.jface.viewers.IColorProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.wb.swt.SWTResourceManager;
import org.sql2o.data.Row;

import com.DbHelper;
import com.model.dao.KhoaPhong;
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
				return obj.getString("TEN_BENH_NHAN").toString();
			}
			else if(columnIndex==3){
				return obj.getString("MA_THE").toString();
			}
			else if(columnIndex==4){
				return Utils.getKieuThanhToan( obj.getInteger("KIEU_TT").intValue() );
			}
			else if(columnIndex==5){
				return ""+ java.text.NumberFormat.getInstance(java.util.Locale.ITALY)
						.format(obj.getInteger("T_BNTT").intValue()) ;
			}
			else if(columnIndex==6){
				return ""+ java.text.NumberFormat.getInstance(java.util.Locale.ITALY)
						.format(obj.getInteger("T_TONGCHI").intValue()) ;
			}
			else if(columnIndex==7){
				return ""+ java.text.NumberFormat.getInstance(java.util.Locale.ITALY)
						.format(obj.getInteger("T_NGOAIDS").intValue()) ;
			}
			else if(columnIndex==8){
				if( obj.getString("NGAY_TTOAN").toString().length()> 10 ){
					return "Xong";
				}
				return "";
			}
			else if(columnIndex==9){
				return obj.getString("NV_NAME").toString();
			}
			else if(columnIndex==10){
				String MAKHOA =obj.getString("MA_KHOA").toString();
				KhoaPhong objKhoa = DbHelper.hashKhoaPhongMAKHOA.get(MAKHOA);
				if(objKhoa==null){
					return MAKHOA;
				}
				else{
					return objKhoa.KP_NAME;
				}
			}
			else if(columnIndex==11){
				String MALK ="["+obj.getString("MA_LK").toString()+"] "+"("+obj.getString("BN_ID").toString()+")";
				//String tenKhoa = LoginDlg.hashKhoaPhong.get(MAKHOA);
				return MALK;
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
				return SWTResourceManager.getColor(SWT.COLOR_DARK_GREEN);
			}
			
			return Utils.getTinhTrangPhieuKhamColor( obj.getInteger("STS").intValue() );
		}
		return null;
	}
}