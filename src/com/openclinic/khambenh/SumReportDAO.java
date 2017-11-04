package com.openclinic.khambenh;

import java.util.ArrayList;
import java.util.Hashtable;

import com.DbHelper;
import com.model.dao.DvChitiet;
import com.openclinic.ReadNumber;

public class SumReportDAO {
	public ArrayList<DvChitiet> dv = new ArrayList<DvChitiet>();
	public String phanLoadDichVu = "";
	//
	public int TT;
	public int TT2;
	public int BH;
	public int NB;
	public int NB2;
	public int KH;
	//
	public String toString(){
		return "SumReportDAO "+phanLoadDichVu+" TT2="+TT2+" TT="+TT+" "+BH+" NB="+NB+" NB2="+NB2+" Count="+dv.size();
 	}

	public String getTT2String(){
		return ReadNumber.numberToString(TT);
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
	public int getTT2(){
		return TT2;
	}
	public int getTT(){
		return TT;
	}
	public int getBH(){
		return BH;
	}
	public int getNB(){
		return NB;
	}
	public int getNB2(){
		return NB2;
	}	
	public int getKH(){
		return KH;
	}

	public void setPhanLoadDichVu(String MANHOM) {
		// TODO Auto-generated method stub
		phanLoadDichVu = DbHelper.hashLoaiDichVu.get(MANHOM);
	}
}
