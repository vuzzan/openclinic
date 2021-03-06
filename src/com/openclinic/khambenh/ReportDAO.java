package com.openclinic.khambenh;

import java.util.ArrayList;

import com.model.dao.BenhNhan;
import com.model.dao.DvChitiet;
import com.model.dao.KhamBenh;
import com.model.dao.ThuocChitiet;

public class ReportDAO {
	
	public BenhNhan bn;
	public KhamBenh kb;
	
	public ArrayList<DvChitiet> congkham;
	public SumReportDAO sumCongkham;
	
	public ArrayList<SumReportDAO> listdv;
	public SumReportDAO sumDv;
	
	public ArrayList<ThuocChitiet> thuoc;
	public SumReportDAO sumThuoc;
	
	public SumReportDAO sumTongCong;
	public String ngayGio;
	public String strNguoiLap;
	
	public String tuNgay;
	public String denNgay;
	
	int totalDate = 1;
	
	public BenhNhan getBN(){
		return bn;
	}
	
	public KhamBenh getKB(){
		return kb;
	}
	
	public ArrayList<SumReportDAO> getDV(){
		return listdv;
	}
	
	
	public ArrayList<ThuocChitiet> getThuoc(){
		return thuoc;
	}
//	public ArrayList<SumReportDAO> getDV_All(){
//		return listdv_all;
//	}
//	public ArrayList<ThuocChitiet> getThuoc_All(){
//		return thuoc_all;
//	}
}
