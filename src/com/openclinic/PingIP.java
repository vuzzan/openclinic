package com.openclinic;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.sql2o.Connection;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.ExporterInput;
import net.sf.jasperreports.export.OutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;

import com.CheckTheObj;
import com.DbHelper;
import com.openclinic.utils.Utils;

public class PingIP {

	public static void main(String[] args) {
//		String mathe = "GD1234567890123";
//		System.out.println(mathe.substring(0,2));
//		System.out.println(mathe.substring(2,3));
//		System.out.println(mathe.substring(3,5));
//		System.out.println(mathe.substring(5,7));
//		System.out.println(mathe.substring(7,10));
//		System.out.println(mathe.substring(10,15));
		
//		String html = "<b>Thẻ sai họ tên</b>! Họ tên đúng: <b>Nguyễn Thị Ban</b>, Ngày sinh: <b>09/02/1937</b>, Giới tính : <b>Nữ</b>! (ĐC: <b>Thôn Phú Mỹ, Quế Xuân 2, Quế Sơn, Xã Quế Xuân 2, Huyện Quế Sơn, Tỉnh Quảng Nam</b>; Nơi KCBBĐ: <b>49475</b>; Hạn thẻ: <b>01/03/2017 - 30/06/2018; Thời điểm đủ 5 năm liên tục: 01/03/2022</b>).";
		String html = "<b>Thẻ hợp lệ</b>! Họ tên: <b>Đoàn Thanh Nam</b>, Ngày sinh: <b>20/09/1978</b>, Giới tính : <b>Nam</b>! (ĐC: <b>Thôn Thượng Vĩnh, Quế Xuân 2, Quế Sơn</b>; Nơi KCBBĐ: <b>49172</b>; Hạn thẻ: <b>04/01/2017 - 03/01/2018).<b style='color: red'>Chủ thẻ đã được cấp mã thẻ mới : GD4494921563015.Hạn thẻ từ 04/01/2018 đến 03/01/2019</b></b>";
		CheckTheObj ret = new CheckTheObj();

		html = html.replaceAll("<b>", "");
		html = html.replaceAll("</b>", "");
		html = html.replace(')', ' ');
		html = html.replace('(', ';');
		html = html.replaceAll("<b style='color: red'>", ";");
		html = html.replaceAll("Hạn thẻ từ", "; Hạn thẻ từ: ");
		html = html.replaceAll(" đến ", "-");
		
		String tmp0[] = html.split(";");
		String tmp1[] = tmp0[0].split(",");
		for(int i=0; i<tmp1.length; i++){
			//System.out.println(i + " " +tmp1[i].trim());
			String tmp[] = tmp1[i].trim().split(":");
			if(tmp.length==2){
				System.out.println(tmp[1]);
				if( i== 0){
					ret.strHoTen = tmp[1].trim();
					System.out.println("\t strHoTen: " + ret.strHoTen);
				}
				else if( i== 1){
					ret.strNgaySinh= tmp[1].trim();
					System.out.println("\t strNgaySinh: " + ret.strNgaySinh);
				}
				else if( i== 2){
					String gioitinh = tmp[1].trim();
					if(gioitinh.indexOf("Nữ")>-1){
						ret.gioitinh = 2;
					}
					else if(gioitinh.indexOf("Nam")>-1){
						ret.gioitinh = 1;
					}
					else{
						ret.gioitinh = 0;
					}
				}
			}
		}
		for(int i=1; i<tmp0.length; i++){
			//System.out.println(i + " " +tmp0[i].trim());
			String tmp[] = tmp0[i].trim().split(":");
			if(tmp.length==2){
				System.out.println(tmp[1]);
				if( i==3 ){
					if( tmp[1].indexOf("Hạn thẻ")>-1){
						String tmp3[] = tmp[1].split("-");
						if(tmp3.length==2){
							System.out.println("\tTừ ngày: " + tmp3[0] +" đến ngày:" + tmp3[1]);
							ret.strTuNgay = tmp3[0].trim();
							ret.strDenNgay = tmp3[1].trim();
							System.out.println("\t strTuNgay: " + ret.strTuNgay);
							System.out.println("\t strDenNgay: " + ret.strDenNgay);
						}
					}
				}
				//
				if( i== 1){
					ret.strDiaChi = tmp[1].trim();
					System.out.println("\t strDiaChi: " + ret.strDiaChi);
				}
				else if( i== 2){
					ret.strDKKCB= tmp[1].trim();
					System.out.println("\t strDKKCB: " + ret.strDKKCB);
				}
				else if( i== 3){
				}
				else if( i== 4){
					if( tmp[1].trim().indexOf("Chủ thẻ đã được cấp mã thẻ mới")>-1 ){
						ret.strThoidiem5Nam= tmp[1].trim();
						ret.strThoidiem5Nam = ret.strThoidiem5Nam.replaceAll("\\.", "").trim();
						System.out.println("\t strThoidiem5Nam: " + ret.strThoidiem5Nam);
					}
					else{
						String strMathe= tmp[1].trim();
						strMathe = strMathe.replaceAll("\\.", "").trim();
						ret.strMathe = strMathe;
						System.out.println("\t ret.strMathe: " + strMathe);
						ret.checkText = "Chủ thẻ đã được cấp mã thẻ mới: "+ret.strMathe;
					}
				}
				else if( i== 5){
					String tmp3[] = tmp[1].split("-");
					if(tmp3.length==2){
						System.out.println("\tTừ ngày: " + tmp3[0] +" đến ngày:" + tmp3[1]);
						ret.strTuNgay = tmp3[0].trim();
						ret.strDenNgay = tmp3[1].trim();
						System.out.println("\t strTuNgay: " + ret.strTuNgay);
						System.out.println("\t strDenNgay: " + ret.strDenNgay);
					}
				}
			}
		}
		System.out.println(ret.checkText);
		//html = html.replaceAll("(", "");
		//System.out.println(html);
//		String NGAY_VAO2 = "201801040910";
//		Date NGAY_VAO = Utils.getDateTimeByDate(NGAY_VAO2, "yyyyMMddHHmm");
//		double songay = Utils.differenceInDayDouble(NGAY_VAO, Calendar.getInstance().getTime()); 
//		System.out.println(songay);
//		if( songay> 1.0){
//			System.out.println("OK");
//		}
//		else{
//			System.out.println("CHECK NGAY SAI ");
//		}
		//System.out.println("SO NGAY = "+songay+" tmpKhamBenh.NGAY_VAO="+tmpKhamBenh.NGAY_VAO+" "+Calendar.getInstance().getTime().toString()+" NGAY_VAO="+NGAY_VAO.toString());
		//System.out.println(  java.text.NumberFormat.getInstance(java.util.Locale.ITALY).format(11233434.55) );
//		if( Utils.getDouble( "151112.02" ) ==0  ){
//			System.out.println("FAIL");
//		}
//		else{
//			System.out.println("OK");
//		}
//		try {
//			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");// 201711091458
//			Date d1 = sdf.parse("201712181646");
//			Date d2 = sdf.parse("201712181646");
//			long diff = Utils.differenceInDay(d1, d2);
//			System.out.println(diff);
////			String tuNgay = Utils.getDatetime(sdf.parse("201712181646"), "HH:mm dd/MM/yyyy");
////			String denNgay = Utils.getDatetime(sdf.parse("201712181646"), "HH:mm dd/MM/yyyy");
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
//		CheckTheObj objCheckTheObj = new CheckTheObj();
//		String strHtml = "{\"maKetQua\":\"00\",\"dsLichSuKCB\":[{\"maHoSo\":610269378,\"maCSKCB\":\"49172\",\"tuNgay\":\"02/11/2017\",\"denNgay\":\"02/11/2017\",\"tenBenh\":\"B34.9 -  - Nhiễm virus, không xác định\",\"tinhTrang\":\"1\",\"kqDieuTri\":\"2\"},{\"maHoSo\":545799293,\"maCSKCB\":\"49172\",\"tuNgay\":\"02/10/2017\",\"denNgay\":\"02/10/2017\",\"tenBenh\":\"E63 -  - Thiếu dinh dưỡng khác\",\"tinhTrang\":\"1\",\"kqDieuTri\":\"2\"}]}";
//		JSONObject result = new JSONObject(strHtml);
//		String maKetQua = (String)result.get("maKetQua");
//		//{"data":3}
//		int iMakq = Integer.parseInt(maKetQua);
//		objCheckTheObj.checkCode = iMakq ; 
//		String text = DbHelper.hashcheckThe.get(objCheckTheObj.checkCode);
//		if(text==null){
//			objCheckTheObj.checkText = "Có lỗi...";
//		}
//		else{
//			objCheckTheObj.checkText = text;
//		}
//		JSONArray dsLichSuKCB = (JSONArray)result.get("dsLichSuKCB");
//		if(dsLichSuKCB==null){
//			//
//		}
//		else{
//			for(int i=0; i<dsLichSuKCB.length(); i++){
//				JSONObject objLichSu = (JSONObject)dsLichSuKCB.get(i);
//				objCheckTheObj.listKhamBenh.add( ""+(String)objLichSu.get("tuNgay") +" ; " + (String)objLichSu.get("tenBenh"));
//			}
//		}
//		
//		int day = 1;
//		int month = 1;
//		int year= 2015;
//		
//		Date dt = new Date();
//		Date dt2 = new Date();
//		dt2.setDate(day-1);
//		dt2.setMonth(month-1);
//		dt2.setYear(year-1900);
//		Calendar startCalendar = Calendar.getInstance();
//		Calendar endCalendar = Calendar.getInstance();
//		startCalendar.setTime(dt2);
//		endCalendar.setTime(dt);
//		int diffYear = endCalendar.get(Calendar.YEAR) - startCalendar.get(Calendar.YEAR);
//		int difInMonths = diffYear * 12 + endCalendar.get(Calendar.MONTH) - startCalendar.get(Calendar.MONTH);
//		int difInDate = endCalendar.get(Calendar.DATE) - startCalendar.get(Calendar.DATE);
//		//System.out.println("TO MONTH toMonth()= " + difInMonths);
//		//return "" + (diffYear<=0?"":(diffYear +" năm,"))+(difInMonths<=0?"":(difInMonths+" tháng, "))+difInDate+" ngày";
//		
//		System.out.println ( "" + (diffYear<=0?"":(diffYear +" năm,"))+(difInMonths<=0?"":(difInMonths+" tháng, "))+difInDate+" ngày" );
//        int monthsBetween = 0;
//        int dateDiff = endCalendar.get(Calendar.DAY_OF_MONTH)-startCalendar.get(Calendar.DAY_OF_MONTH);      
//
//if(dateDiff<0) {
//            int borrrow = endCalendar.getActualMaximum(Calendar.DAY_OF_MONTH);           
//             dateDiff = (endCalendar.get(Calendar.DAY_OF_MONTH)+borrrow)-startCalendar.get(Calendar.DAY_OF_MONTH);
//             monthsBetween--;
//
//if(dateDiff>0) {
//                 monthsBetween++;
//             }
//        }
//        else {
//            monthsBetween++;
//        }      
//        monthsBetween += endCalendar.get(Calendar.MONTH)-startCalendar.get(Calendar.MONTH);      
//        monthsBetween  += (endCalendar.get(Calendar.YEAR)-startCalendar.get(Calendar.YEAR))*12;  
//		System.out.println("TO MONTH monthsBetween()= " + monthsBetween);

//		String temp = "49 – 426";
//		//String tmp[] = temp.split(";");
//		System.out.println(temp);
//		System.out.println(temp.replaceAll("–", ""));
//		System.out.println(temp);
//		DatePicker txtNgaySinh = new DatePicker(null, 0);
//		txtNgaySinh.setText("2012");
//		double monthDiff = Utils.monthsBetween(Calendar.getInstance(), txtNgaySinh.getDate2());
//		System.out.println(monthDiff);
//		double taxVAT = Utils.getDouble("0.1");
//		System.out.println(taxVAT);

//		System.out.println(  Utils.getDatetime(new Date(), "dd/MM/YYYY") );
//		String TEN_BENH_NHAN = String.format("%5.5s", "MA_NOI_CHUYEN").trim();
//		System.out.println("["+TEN_BENH_NHAN+"]");
		//System.out.println(java.text.NumberFormat.getInstance(java.util.Locale.ITALY).format(123456));
		// System.out.println(Utils.getDatetime(new Date(), "yyyyMMddHHmm"));
		//System.out.println(Utils.getDatetime(new Date(),"yyyy-MM-dd HH:mm"));
//		String reportSrcFile = "report/PhieuBenhNhan.jrxml";
//
//		// First, compile jrxml file.
//		JasperReport jasperReport;
//		try {
//			jasperReport = JasperCompileManager.compileReport(reportSrcFile);
//
//			Connection conn = DbHelper.getSql2o().open();
//			//
//			// // Parameters for report
//			Map<String, Object> parameters = new HashMap<String, Object>();
//			parameters.put("ReportTitle", "Lương Trọng Nghĩa");
//			//
//			JasperPrint print = JasperFillManager.fillReport(jasperReport, parameters);
//
//			// Make sure the output directory exists.
//			File outDir = new File("C:/jasperoutput");
//			   String pdfFileName = "C:/jasperoutput/C1_report.pdf";
//
//
//			outDir.mkdirs();
//			   JasperExportManager.exportReportToPdfFile(print, pdfFileName);
//
//			// PDF Exportor.
////			JRPdfExporter exporter = new JRPdfExporter();
////
////			ExporterInput exporterInput = new SimpleExporterInput(print);
////			// ExporterInput
////			exporter.setExporterInput(exporterInput);
////
////			// ExporterOutput
////			OutputStreamExporterOutput exporterOutput = new SimpleOutputStreamExporterOutput(
////					"C:/jasperoutput/FirstJasperReport.pdf");
////			// Output
////			exporter.setExporterOutput(exporterOutput);
////
////			//
////			SimplePdfExporterConfiguration configuration = new SimplePdfExporterConfiguration();
////			exporter.setConfiguration(configuration);
////			exporter.exportReport();
//
//			System.out.print("Done!");
//		} catch (JRException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}

}
