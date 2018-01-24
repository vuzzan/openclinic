package com;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.jface.dialogs.MessageDialog;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import com.model.dao.BenhNhan;
import com.model.dao.Dichvu;
import com.model.dao.KhamBenh;
import com.model.dao.KhoaPhong;
import com.model.dao.Khohang;
import com.model.dao.Mabenh;
import com.model.dao.MstLieudung;
import com.model.dao.Phanquyen;
import com.model.dao.Users;
import com.model.dao.Vendor;
import com.openclinic.Main;

public class DbHelper {
	public static final int DELETE_STATUS = 100;

	public static final int READ = 1;
	public static final int INSERT = 2;
	public static final int UPDATE = 4;
	public static final int DELETE = 8;

	static Logger logger = LogManager.getLogger(DbHelper.class.getName());

	private static Sql2o sql2o = new Sql2o(Main.DB_URL, Main.DB_USER, Main.DB_PASS);
	public static Users currentSessionUserId = null;
	private static  Connection con;
	public static Hashtable<String, KhoaPhong> hashKhoaPhongKP_MABH = new Hashtable<>();
	public static Hashtable<String, KhoaPhong> hashKhoaPhongMAKHOA = new Hashtable<>();
	public static ArrayList<Dichvu> listCongKham = new ArrayList<>();
	public static Hashtable<String, Dichvu> hashCongKham = new Hashtable<>();

	public static List<Phanquyen> listDataPhanquyen=null;
	public static Hashtable<String, Phanquyen> hashDataPhanquyen = new Hashtable<>();

	public static List<Users> listUsers=null;
	public static Hashtable<String, Users> hashDataUsers = new Hashtable<>();
	public static Hashtable<String, Users> hashDataUsersMaCCHN = new Hashtable<>();

	public static List<Users> listUsersNhanVien =null;
	public static Hashtable<String, Users> hashDataUsersNhanVien = new Hashtable<>();
	
	public static Hashtable<String, Mabenh> hashDataMabenh = new Hashtable<>();
	//
	public static Hashtable<String, Vendor> hashDataVendor = new Hashtable<>();
	public static List<Vendor> listDataVendor = null;
	//
	public static Hashtable<String, Khohang> hashDataKhoHang = new Hashtable<>();
	public static List<Khohang> listDataKhohang = null;
	//
	public static Hashtable<String, MstLieudung> hashDataMstLieuDung = new Hashtable<>();
	public static Hashtable<Integer, MstLieudung> hashDataMstLieuDungbyID = new Hashtable<>();
	public static List<MstLieudung> listDataMstLieuDung = null;
	//
	public static Hashtable<String, String> hashLoaiDichVu = new Hashtable<String, String>();

	public static BHYTThread objBHYTThread = new BHYTThread();

	public static Hashtable<Integer, String> hashcheckThe = new Hashtable<Integer, String>();
	//
	public DbHelper(){
		
	}
	public static void startConnection() {
		try{
			logger.info("Start Connection");
			con = sql2o.open();
			con.setRollbackOnException(false);
			logger.info("Start Connection OK");
		}
		catch(Exception e){
			MessageDialog.openError(null, "Có lỗi", "Không tìm thấy DB server");
			logger.error(e);
		}
	}

    public static Connection getSql2o() {
    	if(con==null){
			con = sql2o.open();
    	}
		//logger.info("get connection " + con);
        return con;
    }
    public static void setCurrentSessionUserId(Users obj) {
		DbHelper.currentSessionUserId = obj;
		//
		if(obj.LOAI.toUpperCase().equals("BS")){
			// BS
		}
		else if(obj.LOAI.toUpperCase().equals("ADMIN")){
		}
		else if(obj.LOAI.toUpperCase().equals("ACCT")){
		}
		else if(obj.LOAI.toUpperCase().equals("THUOC")){
		}
//		String sql = "select * from phanquyen WHERE u_id= "+obj.U_ID+" ";
//		try  {
//            logger.info(sql);
//			Connection con = DbHelper.getSql2o();
//			listDataPhanquyen = con.createQuery(sql).executeAndFetch(Phanquyen.class);
//			if(listDataPhanquyen!=null){
//				//
//				for(Phanquyen objPhanquyen: listDataPhanquyen){
//					hashDataPhanquyen.put(objPhanquyen.TABLE_NAME, objPhanquyen);
//				}
//			}
//	    }   
//	    catch(Exception e){
//	    	logger.error(e);
//	    }
		//
	}
	public static Integer getCurrentSessionUserId() {
		return currentSessionUserId.U_ID;
	}
	public static String getCurrentSessionUserName() {
		if(currentSessionUserId!=null)
			return currentSessionUserId.U_NAME;
		return "";
	}

	public static void logDB(String logString) {
		try{
			con.setRollbackOnException(false);
			String sql = "insert into action_log(u_action,dbtable,actionid,fieldid) values('"+logString+"','',0,0)";
	    	con.createQuery(sql).executeUpdate();
	    }   
	    catch(Sql2oException e){
			logger.error(e);
	    	//
	    }
		
	}
	public static String getTENKHOA(String comboBoxText){
		//logger.info("getTENKHOA "+comboBoxText);
		String madvktarr[] = comboBoxText.split("-");
		String MA_DVKT = madvktarr[0].trim();
		KhoaPhong obj = DbHelper.hashKhoaPhongKP_MABH.get(MA_DVKT);
		if(obj!=null){
			return obj.KP_NAME;
		}
		else{
			return "";
		}
	}
	
	public static String getMAKHOA(String comboBoxText){
		logger.info("getMAKHOA "+comboBoxText);
		//String madvktarr[] = comboBoxText.split("-");
		//String MA_DVKT = madvktarr[1].trim();
		//KhoaPhong obj = DbHelper.hashKhoaPhongKP_MABH.get(MA_DVKT);
		KhoaPhong obj = DbHelper.hashKhoaPhongKP_MABH.get(comboBoxText);
		if(obj!=null){
			return obj.KP_MAKHOA;
		}
		else{
			String madvktarr[] = comboBoxText.split("-");
			if(madvktarr.length>1){
				String MA_DVKT = madvktarr[1].trim();
				obj = DbHelper.hashKhoaPhongKP_MABH.get(MA_DVKT);
				if(obj!=null){
					return obj.KP_MAKHOA;
				}
			}
			return "";
		}
	}
	public static boolean checkPhanQuyen(int quyen, String tableName) {
		Phanquyen objPhanquyen = hashDataPhanquyen.get(tableName);
		if(objPhanquyen!=null){
			System.out.println(objPhanquyen.toString());
			if(quyen==READ){
				if(objPhanquyen.READ!=null && objPhanquyen.READ.intValue()==1){
					return true;
				}
			}
			else if(quyen==INSERT){
				if(objPhanquyen.INSERT!=null && objPhanquyen.INSERT.intValue()==1){
					return true;
				}
			}
			else if(quyen==UPDATE){
				if(objPhanquyen.UPDATE!=null && objPhanquyen.UPDATE.intValue()==1){
					return true;
				}
			}
			else if(quyen==DELETE){
				if(objPhanquyen.DELETE!=null && objPhanquyen.DELETE.intValue()==1){
					return true;
				}
			}
			return true;
		}
		else{
			System.out.println("NULL tableName="+tableName);
			return true;
		}
	}
	
	public static String getIDC10TenBenh(String ICDCode) {
		String ret = "";
		String mabenhIDC[] = ICDCode.split(";");
		for(int i=0; i<mabenhIDC.length; i++){
			Mabenh obj = DbHelper.hashDataMabenh.get(mabenhIDC[i].trim());
			ret += obj.MABENH_NAME;
			if(i<mabenhIDC.length-1){
				ret+=";";
			}
		}
		return ret;
	}
/*	
 
  
  
  
  
  
  
  
  
  
  
  
  
  
	public String getIDC_TENBENH()
    {
        return DbHelper.getIDC10TenBenh(getIDC_MA_BENH());
    }
    public String getIDC_MA_BENH()
    {
    	if(this.MA_BENHKHAC!=null && this.MA_BENHKHAC.length()>0){
    		return this.MA_BENH +";"+this.MA_BENHKHAC;
    	}
    	else{
    		return this.MA_BENH;
    	}
    }
    
    public static List<KhamBenh> loadLatest(int BN_ID) {
    	Connection connection = null;

		try {
			connection = DbHelper.getSql2o();
			String sql = "select * from kham_benh where BN_ID=:BN_ID order by MA_LK desc limit 10";
			logger.debug(sql);
			List<KhamBenh> obj = connection
					.createQuery(
							sql,
							true)
					.addParameter("BN_ID", BN_ID)
					.executeAndFetch( KhamBenh.class);
            //logger.info(obj);
            logger.debug("loadLatest BN_ID "+(obj==null?0:obj.size()));
            return obj;
		} catch (Exception t) {
            logger.error("loadLatest Error: ID="+BN_ID +"\nException=\n"+ t);
			throw new RuntimeException("LOAD [KhamBenh]. ERROR: "+t.getMessage(), t);
		}
    }


 public static BenhNhan load(String MA_THE) {
    	Connection connection = null;

		try {
			connection = DbHelper.getSql2o();
			String sql = "select * from benh_nhan where MA_THE=:MA_THE";
			logger.debug(sql);
			BenhNhan obj = connection
					.createQuery(
							sql,
							true)
					.addParameter("MA_THE", MA_THE)
					.executeAndFetchFirst( BenhNhan.class);
            logger.info(obj);
            logger.debug("Load MA_THE "+obj);
            return obj;
		} catch (Exception t) {
            logger.error("Load Error: MA_THE="+MA_THE +"\nException=\n"+ t);
			throw new RuntimeException("LOAD [BenhNhan]. ERROR: "+t.getMessage(), t);
		}
    }
*
*/
}
