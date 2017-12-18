package com.openclinic.utils;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.sql.DriverManager;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.http.client.methods.HttpUriRequest;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;
import org.sql2o.data.Column;
import org.sql2o.data.Row;

import com.openclinic.Main;

public class Utils {
	public static final int THANHTOAN_BHYT = 1;
	public static final int THANHTOAN_BHYT_2 = 5;
	public static final int THANHTOAN_VIENPHI = 2;
	public static final int THANHTOAN_VIENPHI_FREE = 4;
	public static final int THANHTOAN_TAI_KHAM = 3;
	public static final int THANHTOAN_MUA_CLS = 6;
	public static final int THANHTOAN_MUA_THUOC = 7;
	
	public static final int FORM_NEW    = 0;
	public static final int FORM_UPDATE = 1;
	
	public static final int PHIEUKHAM_CHOKHAM_BS 			= 0;
	public static final int PHIEUKHAM_BSKHAMXONG_CHO_CLS	= 1;
	public static final int PHIEUKHAM_CLS_XONG_CHO_KQ_CLS 	= 2;
	public static final int PHIEUKHAM_CO_KQ_CLS_QUAYLAI_BS	= 3;
	//public static final int PHIEUKHAM_KHAMXONG_BS_CHOTHUOC 	= 4;
	public static final int PHIEUKHAM_KHAMXONG_CHO_LAYTHUOC = 4;
	public static final int PHIEUKHAM_KHAMXONG_RAVE 		= 5;
	
	public static final int THUOC_KHAMXONG_CHOTHUOC 		= PHIEUKHAM_KHAMXONG_CHO_LAYTHUOC;
	public static final int THUOC_KHAMXONG_DALAYTHUOC 		= PHIEUKHAM_KHAMXONG_RAVE;
	
	
	//	public static Hashtable<Integer, String> mstMapTAINANTHUONGTICH = new Hashtable<Integer, String>();
	public static ArrayList<String> mstArrayTAINANTHUONGTICH = new ArrayList<String>();
	public static ArrayList<String> mstArrayTinhTrangPhieuKhamBenh = new ArrayList<String>();
	public static ArrayList<String> mstArrayTinhTrangCanLamSan = new ArrayList<String>();
	public static ArrayList<String> mstArrayTinhTrangThuoc = new ArrayList<String>();
	public static ArrayList<Color> mstArrayTinhTrangPhieuKhamBenhColor = new ArrayList<Color>();

	public static String[] mstArrayKieuThanhToan = {"","BHYT1","Viện Phí", "Tái Khám", "Miễn Phí", "BHYT2", "Mua CLS", "Mua Thuốc VP"};
	public static Color getTinhTrangPhieuKhamColor(int STS) {
		if(STS < mstArrayTinhTrangPhieuKhamBenhColor.size() ){
			return mstArrayTinhTrangPhieuKhamBenhColor.get(STS);
		}
		else{
			return null;
		}
	}
	public static String getTinhTrangThuoc(int STS) {
		if(STS<0){
			return "Chưa lưu";
		}
		else if(STS < mstArrayTinhTrangThuoc.size() ){
			return mstArrayTinhTrangThuoc.get(STS);
		}
		else{
			return "NA";
		}
	}
	
	public static String getTinhTrangCanLamSan(int STS){
		if(STS < mstArrayTinhTrangCanLamSan.size() ){
			return mstArrayTinhTrangCanLamSan.get(STS);
		}
		else{
			return "NA";
		}
	}
	
	public static String getTinhTrangPhieuKham(int STS){
		if(STS < mstArrayTinhTrangPhieuKhamBenh.size() ){
			return mstArrayTinhTrangPhieuKhamBenh.get(STS);
		}
		else{
			return "NA";
		}
	}
	
	public static String getKieuThanhToan(int intValue) {
		if(intValue >mstArrayKieuThanhToan.length ){
			return "";
		}
		else{
			return mstArrayKieuThanhToan[intValue];
		}
	}
	
	//java.text.NumberFormat.getInstance(java.util.Locale.ITALY).format(
	public static String getMoneyDefault(int money){
		return java.text.NumberFormat.getInstance(java.util.Locale.ITALY).format(money);
	}
	public static String getDatetimeDefault(Date now){
		return getDatetime(now, "dd/MM/yyyy");
	}
	public static String getDatetimeCurent(){
		return getDatetime(new Date(), "yyyy-MM-dd_HH:mm:ss");
	}	
	public static String getDatetime(Date now, String format){
		if(format==null || format.length()==0){
			format = "yyyy-MM-dd HH:mm:ss";
		}
		//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(now);
	}

	public static long differenceInDay(Date startDate, Date endDate) {
		System.out.println("Start: "+startDate.getTime() + " End: " + endDate.getTime());
		long diff = endDate.getTime() - startDate.getTime();
		System.out.println("diff: "+diff);

//		long diff = milliseconds2 - milliseconds1;
//		  long diffSeconds = diff / 1000;
//		  long diffMinutes = diff / (60 * 1000);
//		  long diffHours = diff / (60 * 60 * 1000);
		  long diffDays = diff / (24 * 60 * 60 * 1000);
	    return diffDays;
	}
	public static long differenceInDay(Calendar startDate, Calendar endDate) {
		System.out.println("Start: "+startDate.getTime() + " End: " + endDate.getTime());
		long diff = endDate.getTimeInMillis() - startDate.getTimeInMillis();
		System.out.println("diff: "+diff);

//		long diff = milliseconds2 - milliseconds1;
//		  long diffSeconds = diff / 1000;
//		  long diffMinutes = diff / (60 * 1000);
//		  long diffHours = diff / (60 * 60 * 1000);
		  long diffDays = diff / (24 * 60 * 60 * 1000);
	    return diffDays;
	}
	public static double differenceInMonths(Calendar date1, Calendar date2){
        double monthsBetween = 0;
        //difference in month for years
        monthsBetween = (date1.get(Calendar.YEAR)-date2.get(Calendar.YEAR))*12;
        //difference in month for months
        monthsBetween += date1.get(Calendar.MONTH)-date2.get(Calendar.MONTH);
        //difference in month for days
        if(date1.get(Calendar.DAY_OF_MONTH)!=date1.getActualMaximum(Calendar.DAY_OF_MONTH)
                && date1.get(Calendar.DAY_OF_MONTH)!=date1.getActualMaximum(Calendar.DAY_OF_MONTH) ){
            monthsBetween += ((date1.get(Calendar.DAY_OF_MONTH)-date2.get(Calendar.DAY_OF_MONTH))/31d);
        }
        return monthsBetween;
    }
	public static int differenceInMonths(Date d1, Date d2) {
	    Calendar c1 = Calendar.getInstance();
	    c1.setTime(d1);
	    Calendar c2 = Calendar.getInstance();
	    c2.setTime(d2);
	    int diff = 0;
	    if (c2.after(c1)) {
	        while (c2.after(c1)) {
	            c1.add(Calendar.MONTH, 1);
	            if (c2.after(c1)) {
	                diff++;
	            }
	        }
	    } else if (c2.before(c1)) {
	        while (c2.before(c1)) {
	            c1.add(Calendar.MONTH, -1);
	            if (c1.before(c2)) {
	                diff--;
	            }
	        }
	    }
	    return diff;
	}
	
	public static String doCapitalizeFirstLetterInString(String s) {
		if(s==null || s.length()==0){
			return "";
		}
		s=s.replaceAll("\\s{2,}", " ").trim();

		final StringBuilder result = new StringBuilder(s.length());
		String[] words = s.split("\\s");
		for(int i=0,l=words.length;i<l;++i) {
		  if(i>0) result.append(" ");      
		  result.append(Character.toUpperCase(words[i].charAt(0)))
		        .append(words[i].substring(1));
		}
		return result.toString();
	}

	public static void setDateTime(final String strDate, final org.eclipse.swt.widgets.DateTime dateTime) {
		Display.getDefault().asyncExec(new Runnable() {
			public void run() {
				int dt[] = getDateTime(strDate);
				dateTime.setDate(dt[2], dt[1], dt[0]);
			}
		});
		
	}
	public static int[] getDateTime(String strDate) {
		// "12/12/2017";
		String sdt[] = strDate.split("/");
		int dt[] = new int[3];
		dt[0] = 1;
		dt[1] = 1;
		dt[2] = 2017;
		//
		try{
			if(sdt.length==1){
				dt[2] = Integer.parseInt(sdt[1]);
			}
			else if(sdt.length==2){
				dt[1] = Integer.parseInt(sdt[0]);
				dt[2] = Integer.parseInt(sdt[1]);
			}
			else{
				dt[0] = Integer.parseInt(sdt[0]);
				dt[1] = Integer.parseInt(sdt[1]);
				dt[2] = Integer.parseInt(sdt[2]);
			}
		}
		catch(Exception e){
			
		}
		//
		return dt;
	}
	public static int[] getDateTime(String strDate, String format) {
		//			format = "yyyy-MM-dd HH:mm:ss";
		SimpleDateFormat sdf = new SimpleDateFormat(format);//"yyyy-MM-dd");
		int dt[] = new int[3];
		dt[0] = 0;
		dt[1] = 0;
		dt[2] = 2017;
		try {
		    Date convertedCurrentDate;
			convertedCurrentDate = sdf.parse(strDate);
		    //String date = sdf.format( convertedCurrentDate );
		    System.out.println(convertedCurrentDate);
			dt[0] = convertedCurrentDate.getDay()+1;
			dt[1] = convertedCurrentDate.getMonth()+1;
			dt[2] = convertedCurrentDate.getYear()+1900;
		    //===============================================
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		//
		return dt;
	}
	public static Date getDateTimeByDate(String strDate, String format) {
		//			format = "yyyy-MM-dd HH:mm:ss";
		SimpleDateFormat sdf = new SimpleDateFormat(format);//"yyyy-MM-dd");
	    Date convertedCurrentDate = null;
		try {
			convertedCurrentDate = sdf.parse(strDate);
		    return convertedCurrentDate;
		    //===============================================
		} catch (ParseException e1) {
			e1.printStackTrace();
			convertedCurrentDate = null;
		}
		//
		return convertedCurrentDate;
	}

	public static int getInt(Object intval) {
		try{
			return Integer.parseInt(intval.toString());
		}
		catch(Exception e){
			//e.printStackTrace();
		}
		return 0;
	}
	public static Float getDouble(String db) {
		try{
			return Float.parseFloat(db);
		}
		catch(Exception e){
			//
		}
		return new Float(0.0);
	}
	public static void sleep(int milisecond) {
		try{
			Thread.sleep(milisecond);
		}
		catch(Exception e){
			
		}
		
	}

	public static java.sql.Connection getConnection() {
		java.sql.Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = (java.sql.Connection) DriverManager.getConnection(Main.DB_URL, Main.DB_USER, Main.DB_PASS);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	public static String getDatetimeLocal(String local, Date date) {
		if(local==null){
			local = "Duy Xuyên";
		}
		String dt = local + ", ngày " + (date.getDate()) + " tháng "+ (date.getMonth()+1) +" năm "+(date.getYear()+1900);
		return dt;
	}
	public static String getPaymentDefault(int sts) {
		final String strSTS[] = {"Chưa TT", "TT Xong", "Xóa"};
		return sts<2?strSTS[sts]:"Xóa";
	}
	public static int updateStatusPhieuKham(int  beginStatus, int nextStatus) {
		// 
		if(beginStatus==Utils.PHIEUKHAM_CHOKHAM_BS && nextStatus==Utils.PHIEUKHAM_KHAMXONG_CHO_LAYTHUOC
				|| beginStatus==Utils.PHIEUKHAM_CO_KQ_CLS_QUAYLAI_BS && nextStatus==Utils.PHIEUKHAM_KHAMXONG_CHO_LAYTHUOC){
			// BS khám xong, cho thuốc...
			return Utils.PHIEUKHAM_KHAMXONG_CHO_LAYTHUOC;
		}
		//
		return beginStatus;
	}
	public static void exportListRowToExcel(String filename, org.sql2o.data.Table tableDataPhieuKham) {
		List<Row> listRow = tableDataPhieuKham.rows();
		String delimiter = ",";
		if(listRow.size()>0){
			StringBuffer buff = new StringBuffer();
			// Get header...
			List<Column> listColumn = tableDataPhieuKham.columns();
			for(Column col : listColumn){
				buff.append( col.getName() +delimiter);
			}
			buff.append( "\n");
			//
			for(Row row: listRow){
				for(Column col : listColumn){
					buff.append( row.getObject(col.getName()).toString() +delimiter);
				}
				buff.append( "\n");
			}
			Writer out = null;
			try {
				out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filename), "UTF-8"));
				out.write('\uFEFF'); // BOM for UTF-*
				//out.write("sep=\t\n"); // 
				//out.write(0xef); // emits 0xef
			    //out.write(0xbb); // emits 0xbb
			    //out.write(0xbf); // emits 0xbf
			    out.write(buff.toString());
			} 
			catch(Exception ee){
				ee.printStackTrace();
			}
			finally {
			    try {
					out.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			//
		}
	}
	public static String diffMonthDate(int day, int month, int year) {
		Calendar endDate = Calendar.getInstance();
		Calendar startDate = Calendar.getInstance();
		long dayDiff = differenceInDay(startDate, endDate);
		return "";
	}

	
	public static void setHeader(HttpUriRequest mainPage, String string) {
		mainPage.setHeader("Content-Type", "application/json");
//		mainPage.setHeader("DNT", "1");
//		mainPage.setHeader("Host", "gdbhyt.baohiemxahoi.gov.vn");
//		mainPage.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
//		mainPage.setHeader("Accept-Language", "en-US,en;q=0.5");
//		mainPage.setHeader("Accept-Encoding", "gzip, deflate, br");
//		mainPage.setHeader("Connection", "keep-alive");
//		mainPage.setHeader("Upgrade-Insecure-Requests", "1");
	}

	public static String MungPass(String pass) {
		try{
		    MessageDigest m = MessageDigest.getInstance("MD5");
		    byte[] data = pass.getBytes(); 
		    m.update(data,0,data.length);
		    BigInteger i = new BigInteger(1,m.digest());
		    return String.format("%1$032X", i);
		}
		catch(Exception ee){
			ee.printStackTrace();
		}
		return "";
	}

}
