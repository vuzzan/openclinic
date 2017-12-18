package com;

import java.io.File;
public class BHYTData {
	public String ngaygiokham = "";
	public String ma_the = "";
	public String ho_ten = "";
	public String ngay_sinh = "";
	public String gioi_tinh = "";
	public String dia_chi = "";
	public String ma_cskcb = "";
	public String tu_ngay = "";
	public String den_ngay = "";
	public String ngay_cap = "";
	public String ten_chame = "";
	public String ma_qlbhxh = "";
	public String doi_tuong = "";
	public String du_nam_nam = "";
	public String chuoi_kt = "";
	public int checkCode = -1;
	public String checkText = "Chưa chạy";
	//
	public String getTextCheck(int columnIndex) {
		switch(columnIndex){
			case 0:
				if(checkCode!=-1){
					return ""+checkCode+" "+checkText;
				}
				else{
					return "Chưa chạy";
				}
			case 1:
				return ma_the;
			case 2:
				return ho_ten;
			case 3:
				return ngay_sinh;
			case 4:
				return gioi_tinh;
			case 5:
				return dia_chi;
			case 6:
				return ma_cskcb;
			case 7:
				return tu_ngay;
			case 8:
				return den_ngay;
			case 9:
				return ngay_cap;
			case 10:
				return ten_chame;
			case 11:
				return ma_qlbhxh;
			case 12:
				return doi_tuong;
			case 13:
				return du_nam_nam;
			case 14:
				return chuoi_kt;
			default:
				return "";
		}
	}
	public String getText(int columnIndex) {
		switch(columnIndex){
			case 0:
				return ngaygiokham;
			case 1:
				return ma_the;
			case 2:
				return ho_ten;
			case 3:
				return ngay_sinh;
			case 4:
				return gioi_tinh;
			case 5:
				return dia_chi;
			case 6:
				return ma_cskcb;
			case 7:
				return tu_ngay;
			case 8:
				return den_ngay;
			case 9:
				return ngay_cap;
			case 10:
				return ten_chame;
			case 11:
				return ma_qlbhxh;
			case 12:
				return doi_tuong;
			case 13:
				return du_nam_nam;
			case 14:
				return chuoi_kt;
			default:
				return "";
		}
	}
	public boolean checkEmpty() {
		if(ma_the.length()<5){
			return true;
		}
		if(this.ho_ten.length()<3){
			return true;
		}
		if(this.ngay_sinh.length()<4){
			return true;
		}
		if(this.ma_cskcb.length()<3){
			return true;
		}
		if(this.tu_ngay.length()<5){
			return true;
		}
		if(this.den_ngay.length()<5){
			return true;
		}
		if(this.dia_chi.length()<2){
			return true;
		}
		if(this.gioi_tinh.length()<1){
			return true;
		}
		
		else{
			int gt = 0;
			try{
				gt = Integer.parseInt(gioi_tinh);
			}
			catch(Exception e){
				
			}
			if(gt==1 || gt==2){
				
			}
			else{
				return true;
			}
		}
		return false;
	}
}
