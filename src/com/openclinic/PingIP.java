package com.openclinic;

import java.io.File;
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
		if( Utils.getDouble( "15.0" ) <=0  ){
			System.out.println("FAIL");
		}
		else{
			System.out.println("OK");
		}

		
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
