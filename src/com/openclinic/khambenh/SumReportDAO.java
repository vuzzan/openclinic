package com.openclinic.khambenh;

import java.util.ArrayList;
import java.util.Hashtable;

import com.DbHelper;
import com.model.dao.DvChitiet;
import com.openclinic.ReadNumber;

public class SumReportDAO {
	public ArrayList<DvChitiet> dv = new ArrayList<DvChitiet>();
	public String phanLoaiDichVu = "TEST TEST";
	//
	public double TT;
	public double TT2;
	public double BH;
	public double NB;
	public double NB2;
	public double KH;
	//
	public SumReportDAO(){
		TT = (float)0.0;
		TT2 = (float)0.0;
		BH = (float)0.0;
		NB = (float)0.0;
		NB2 = (float)0.0;
		KH = (float)0.0;
	}
	public String toString(){
		return "SumReportDAO "+phanLoaiDichVu+" TT2="+TT2+" TT="+TT+" "+BH+" NB="+NB+" NB2="+NB2+" Count="+dv.size();
 	}
	//
	public String getTT2String(){
		return ReadNumber.numberToString(TT2);
	}
	public String getTTString(){
		return ReadNumber.numberToString(TT);
	}
	public String getBHString(){
		return ReadNumber.numberToString(BH);
	}
	public String getNBString(){
		return ReadNumber.numberToString(NB);
	}
	public String getNB2String(){
		return ReadNumber.numberToString(NB2);
	}
	public String getKHString(){
		return ReadNumber.numberToString(KH);
	}
	public double getTT2(){
		return TT2;
	}
	public double getTT(){
		return TT;
	}
	public double getBH(){
		return BH;
	}
	public double getNB(){
		return NB;
	}
	public double getNB2(){
		return NB2;
	}	
	public double getKH(){
		return KH;
	}
	public String getphanLoaiDichVu(){
		return phanLoaiDichVu;
	}
	public ArrayList<DvChitiet> getdv(){
		return dv;
	}
	public void setPhanLoaiDichVu(String MANHOM) {
		phanLoaiDichVu = DbHelper.hashLoaiDichVu.get(MANHOM);
	}
}
