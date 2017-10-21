package com.openclinic.khambenh;

import com.openclinic.ReadNumber;

public class SumReportDAO {
	public int TT;
	public int BH;
	public int NB;
	public int KH;
	public String toString(){
		return "SumReportDAO TT="+TT+" "+BH+" NB="+NB;
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
	public String getKHString(){
		return ReadNumber.numberToString(KH);
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
	public int getKH(){
		return KH;
	}
}
