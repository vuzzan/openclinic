package com.openclinic;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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

import com.DbHelper;
import com.openclinic.utils.Utils;

public class PingIP {

	public static void main(String[] args) {
		System.out.println(  Utils.getDatetime(new Date(), "dd/MM/YYYY") );
		String TEN_BENH_NHAN = String.format("%5.5s", "MA_NOI_CHUYEN").trim();
		System.out.println("["+TEN_BENH_NHAN+"]");
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
