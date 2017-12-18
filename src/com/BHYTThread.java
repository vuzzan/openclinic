package com;

import java.io.FileOutputStream;
import java.net.URI;
import java.util.concurrent.Semaphore;

import javax.net.ssl.SSLContext;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpEntity;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.DnsResolver;
import org.apache.http.conn.HttpConnectionFactory;
import org.apache.http.conn.ManagedHttpClientConnection;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.AllowAllHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.apache.http.impl.conn.DefaultHttpResponseParserFactory;
import org.apache.http.impl.conn.ManagedHttpClientConnectionFactory;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.impl.conn.SystemDefaultDnsResolver;
import org.apache.http.impl.io.DefaultHttpRequestWriterFactory;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import com.model.dao.BenhNhan;
import com.model.dao.KhamBenh;
import com.openclinic.utils.Utils;

public class BHYTThread extends Thread {
	public static String UA_FIREFOX30 ="Mozilla/5.0 (Windows NT 6.1; WOW64; rv:52.0) Gecko/20100101 Firefox/52.0";

	
	public static final String HTTP_DOMAIN_ONLY = "egw.baohiemxahoi.gov.vn";
	public static final String HTTP_DOMAIN = "http://"+HTTP_DOMAIN_ONLY;
	static Logger logger = LogManager.getLogger(BHYTThread.class.getName());
	
	public boolean isStop = false;
	public boolean isPause = false;
	public String txtId;
	public String txtPw;
	public String txtCode;
	public String txtDomain;
	//
	private PoolingHttpClientConnectionManager cmSbo;
	public BasicCookieStore cookieStore;
	public CloseableHttpClient httpclient;
	//
	public Semaphore objSem = new Semaphore(0);

	private String access_token;
	private String id_token;
	private String token_type;
	private String username;

	public boolean isLogin = false;

	private String password;
	//
	@Override
	public void interrupt() {
		isStop = true;
		objSem.release();
		super.interrupt();
	}
	@Override
	public void run() {
		// reset betcount here
		if(httpclient==null){
			prepareConnection();
		}
		txtDomain = HTTP_DOMAIN;
		while(true){
			if (isStop == true) {
				break;
			}
			//
			try {
				objSem.acquire();
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (isStop == true) {
				break;
			}
			addScreenLog("RESUME THREAD");
			//
//			for(int i=0; i<Barcode2D.listDataBHYT.size(); i++)
//			{
//				BHYTData obj = Barcode2D.listDataBHYT.get(i);
//				if(obj.checkCode==-1 || obj.checkCode==401){
//					CheckTheObj objCheckTheObj =  checkDataBHYT(obj);
//					//
//			        //Barcode2D.updateCheck();
//			        //
//			        if(objCheckTheObj.checkCode==-1 || objCheckTheObj.checkCode==401){
//			        	// Stop run here
//			        	addScreenLog("RELOGIN ...SLEEP 1s");
//			        	login(username, password);
//				        Utils.sleep(1000);
//				        // quay lai check cai moi xong
//				        i=i-1;
//			        	//break;
//			        }
//			        if(isPause==true){
//			    		isPause = false;
//			    		addScreenLog("PAUSE THREAD");
//			        	break;
//			        }
//			        else{
//			        	Utils.sleep(2000);
//			        }
//				}
//			}
			//
		}
		addScreenLog("STOP THREAD");
	}
	
	public void prepareConnection(){
		if(txtDomain==null)
			txtDomain = HTTP_DOMAIN;
		//proxySetting = null;
		cookieStore = new BasicCookieStore();
		cookieStore.clear();
        ////////////////////////////////////////////////////
	    DnsResolver dnsResolver = new SystemDefaultDnsResolver();
	    X509HostnameVerifier hostnameVerifier = new AllowAllHostnameVerifier();
	    SSLContext sslcontext = SSLContexts.createSystemDefault();
	    HttpConnectionFactory<HttpRoute, ManagedHttpClientConnection> connFactory 
	    			= new ManagedHttpClientConnectionFactory(
	                    new DefaultHttpRequestWriterFactory(),
	                   new DefaultHttpResponseParserFactory());
	    Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder
	                        .<ConnectionSocketFactory> create()
	                        .register(
	                                "https",
	                                new SSLConnectionSocketFactory(sslcontext, hostnameVerifier))
	                        .register("http", new PlainConnectionSocketFactory())
	                        .build();
        cmSbo = new PoolingHttpClientConnectionManager(socketFactoryRegistry,connFactory, dnsResolver);
		cmSbo.setMaxTotal(1000);
	    ///////////////////////////////////////////////////////
	    httpclient = HttpClients.custom()
				.setConnectionManager(cmSbo)
				.setDefaultCookieStore(cookieStore)
				.setRedirectStrategy(new LaxRedirectStrategy())
				.setUserAgent(UA_FIREFOX30)
				.build();
	    System.out.println("httpclient="+httpclient);
	}

	public String getCapcha() {
		txtDomain = "https://gdbhyt.baohiemxahoi.gov.vn";
		if(httpclient==null){
			prepareConnection();
			sendHello();
		}
		logger.info("Get capcha...");
		//
		HttpUriRequest getCapchaReq;
		try {
			//
			String fileName = ""+System.currentTimeMillis();
			//https://gdbhyt.baohiemxahoi.gov.vn/Account/_CaptchaPartial
			getCapchaReq = RequestBuilder
					.post()
					.setUri(new URI(txtDomain+"/Account/_CaptchaPartial"))
					.build();
			getCapchaReq.setHeader("Referer", txtDomain + "/");
			getCapchaReq.setHeader("Origin", txtDomain);
			getCapchaReq.setHeader("X-Requested-With", "XMLHttpRequest" );
			getCapchaReq.setHeader("Content-Length", "0" );
			CloseableHttpResponse getCapchaRes = httpclient.execute(getCapchaReq);
			System.out.println("CAPCHA httpclient="+httpclient);
			//
			HttpEntity entity = getCapchaRes.getEntity();
			String strHtml = EntityUtils.toString(entity);
			logger.info("CAPCHA\n" + strHtml);
			//<img id="Captcha_IMG1" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAADwAAAAeCAYAAABwmH1PAAAAAXNSR0IArs4c6QAAAARnQU1BAACxjwv8YQUAAAAJcEhZcwAADsMAAA7DAcdvqGQAAAIISURBVFhH7ZY7TsNAEIZpOAMVLS0tHADJEgUSh6ChTUWdG6TNLThAGnokThC63ML8Tmac9e68VnmAjD9phTPrnZ1/ZnbNRfvPmASPnUnw2JkEj51J8NiZBB/Ky80TPf1N6gWvZliFZcs1GUoOEh3wfwh1gjmYbqzIVoAJfscc8CWxbHbzqn8NYd9Z6QTWIOtl4kwJtiNNijUa+CtAVZtu3vAvkcbGGmf0O+sUWAKww4ayLwZLcIVoo7r2pipZ/gu4skKStqKHdlgcerH4y9UTWmUHVwgjeSUsmvdS/QtYR4DjTebwy6BvFcqSe74wkb6fEBJdfWHp+22pE5w74+pBtBaP0wGu6G0LGv5zvAQJBcIvCSlzZLPOl3JRpOiiOaHpng68n5zfgeD562179/4tCZbEAqd6+4D98XUtiQ4kdIASZw/Hs++YTjQsKeVLPd75zc87w+v6sZsvKu0mNCO9TEXkhMCSwi8FRh5XbcBgINq9EDO8G12JB5YIWNQtDpzf1f2CDDF60bUXFgvW7gvlfMMSwK0eJYQCvnysFf1AR2nYfiZWhY12hzVA9PwmG1SJxvqNEqAO3TfFGrIr8cLqwQ6MduOEZNkOi6YO2lyh0jUU+yZilW7EzBEwvof17a19pxWKrwCG8X8AZk/PyUVXcBbBHecUPX/+oKeSowlefL7Rk87vi27bH2DtUhKc0x9+AAAAAElFTkSuQmCC" alt="Hình ảnh CAPTCHA" style="height: 28px; width: 140px!important;">
			String strBegin = "data:image/png;base64,";
			String strEnd = "\" alt=\"";
			int posBegin =strHtml.indexOf(strBegin);
			int posEnd = -1;
			if(posBegin>-1){
				posEnd = strHtml.indexOf(strEnd);
			}
			if(posBegin>-1 && posEnd>-1){
				// Write file
				// Write a image byte array into file system
				String imageDataString = strHtml.substring(posBegin+strBegin.length(), posEnd);
				byte[] imageByteArray = decodeImage(imageDataString);
	            FileOutputStream imageOutFile = new FileOutputStream("capcha_"+fileName+".png");
	            imageOutFile.write(imageByteArray);
	            imageOutFile.close();
				System.out.println("File successfully downloaded!");
				getCapchaRes.close();
				// end add list IP
				System.out.println("Filename=" + "capcha_"+fileName+".png");
				return "capcha_"+fileName+".png";
			}
			// tokenize the data
			getCapchaRes.close();
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		} finally {
		}
		return "";
	}
	private void sendHello() {
		//===============================================================================
		// GET VIEW STATE
		//===============================================================================
		HttpUriRequest mainPageReq = RequestBuilder
				.get()
				.setUri("https://gdbhyt.baohiemxahoi.gov.vn" + "/")
				.build();
		Utils.setHeader(mainPageReq, "");
		addScreenLog("--------------SEND Request "+mainPageReq.getURI().toASCIIString());
		CloseableHttpResponse mainPageRes;
		try {
			mainPageRes = httpclient.execute(mainPageReq);
			//
			System.out.println("HELO httpclient="+httpclient);
			StatusLine line = mainPageRes.getStatusLine();
			//
			logger.info("CODE ="+line.getStatusCode());
			System.out.println("CODE ="+line.getStatusCode());
			if(line.getStatusCode()==200){
				//
			}
			HttpEntity entity = mainPageRes.getEntity();
			String strHtml = EntityUtils.toString(entity);
			logger.info("HELLO\n" + strHtml);
			mainPageRes.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		//
	}
	private void addScreenLog(String string) {
		//Barcode2D.addLog(string);
		logger.info(string);
	}
	public static byte[] decodeImage(String imageDataString) {
        return Base64.decodeBase64(imageDataString);
    }
	public void login2(String username, String password, String code) {
		if(httpclient==null){
			prepareConnection();
		}
		this.password = password;
		HttpUriRequest mainPageReq = RequestBuilder
				.post()
				.setUri("https://gdbhyt.baohiemxahoi.gov.vn/Account/login")
				.addParameter("username", username)
				.addParameter("password", password)
				.addParameter("captcha", code)
				.build();
		mainPageReq.setHeader("Accept", "application/json, text/javascript, */*; q=0.01");
		mainPageReq.setHeader("Accept-Encoding", "gzip, deflate, br");
		mainPageReq.setHeader("Accept-Language", "en-US,en;q=0.8,id;q=0.6,vi;q=0.4,ja;q=0.2,es;q=0.2");
		mainPageReq.setHeader("Connection", "keep-alive");
		mainPageReq.setHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
		mainPageReq.setHeader("DNT", "1");
		mainPageReq.setHeader("Host", "gdbhyt.baohiemxahoi.gov.vn");
		mainPageReq.setHeader("Origin", "https://gdbhyt.baohiemxahoi.gov.vn");
		mainPageReq.setHeader("Referer", "https://gdbhyt.baohiemxahoi.gov.vn/");
		mainPageReq.setHeader("X-Requested-With", "XMLHttpRequest");
		mainPageReq.setHeader("Upgrade-Insecure-Requests", "1");
		//mainPageReq.setHeader("Content-Type", "application/json");
		//
		//mainPageReq.setHeader("Content-Length", "0");
		addScreenLog("--------------SEND Request "+mainPageReq.getURI().toASCIIString());
		CloseableHttpResponse mainPageRes;
		try {
			mainPageRes = httpclient.execute(mainPageReq);
			System.out.println("LOGIN2 httpclient="+httpclient);
			//
			StatusLine line = mainPageRes.getStatusLine();
			//
			logger.info("CODE ="+line.getStatusCode());
			System.out.println("CODE ="+line.getStatusCode());
			if(line.getStatusCode()==200){
				//
			}
			HttpEntity entity = mainPageRes.getEntity();
			String strHtml = EntityUtils.toString(entity);
			logger.info("LOGIN2\n" + strHtml);
			mainPageRes.close();
			//{"data":1}
			if(strHtml.indexOf("1}")>0){
				addScreenLog("-LOGIN 2 OK");
				isLogin = true;
				
				home();
				DashboardXml1();
				thongTuyen();
			}
			else{
				addScreenLog("-LOGIN FAIL");
				isLogin = false;
			}
			//
		} catch (Exception e) {
			e.printStackTrace();
		}
		//Home
		
		//
	}
	public void login(String username, String password) {
		if(httpclient==null){
			prepareConnection();
		}
		this.password = password;
		//===============================================================================
		// GET VIEW STATE
		//===============================================================================
		String jsonPost = "{\"username\": {\""+username+"\"}, \"password\": {\""+Utils.MungPass(password)+"\"}}";
		//
		JSONObject param = new JSONObject().put("username", username).put("password", Utils.MungPass(password));
		System.out.println(param.toString());
		//
		try {
			HttpPost request = new HttpPost(txtDomain+ "/api/token/take");
		    StringEntity params = new StringEntity(param.toString());
		    request.addHeader("content-type", "application/json;charset=utf-8");
		    request.setEntity(params);
			logger.info("Login username=["+username+"] pw=["+password+"] JSON="+jsonPost);
			addScreenLog("--------------SEND Request "+request.getURI().toASCIIString());
			CloseableHttpResponse mainPageRes;
			mainPageRes = httpclient.execute(request );
			//
			StatusLine line = mainPageRes.getStatusLine();
			//
			logger.info("CODE LOGIN="+line.getStatusCode());
			System.out.println("CODE LOGIN="+line.getStatusCode());
			HttpEntity entity = mainPageRes.getEntity();
			String strHtml = EntityUtils.toString(entity);
			//if(line.getStatusCode()==200){
				//
				logger.info("LOGIN\n" + strHtml);
			//}
			//logger.info("HELLO\n" + strHtml);
			mainPageRes.close();
			JSONObject result = new JSONObject(strHtml);
			String maKetQua = (String)result.get("maKetQua");
			JSONObject objData = (JSONObject)result.get("APIKey"); 
			//
			//{"data":3}
			if(Integer.parseInt(maKetQua)==200){
				// LOGIN OK
				access_token = (String)objData.get("access_token");
				id_token = (String)objData.get("id_token");
				token_type = (String)objData.get("token_type");
				this.username = (String)objData.get("username");
				//
			}
			else{
				addScreenLog("--------------LOGIN ERROR");
				isLogin=false;
				return;
			}
				
			//
		} catch (Exception e) {
			e.printStackTrace();
		}
		isLogin=true;
		addScreenLog("--------------LOGIN OK. Accesstoken="+access_token);
	}
	public CheckTheObj checkDataBHYT(BenhNhan obj) {
		CheckTheObj objCheckTheObj = new CheckTheObj();
		//===============================================================================
		// GET VIEW STATE https://gdbhyt.baohiemxahoi.gov.vn/ThongTuyenLSKCB/CheckMaThe
		//===============================================================================
		//
		try {
			String strUrl = txtDomain+ "/api/egw/nhanLichSuKCB"+
					"?token="+this.access_token+
					"&id_token="+this.id_token+
					"&username="+this.username+
					"&password="+ Utils.MungPass(this.password);
			//
			HttpPost request = new HttpPost(strUrl);
		    JSONObject param = new JSONObject();
		    //
		    param.put("maThe", obj.MA_THE);
		    param.put("hoTen", obj.HO_TEN);
		    param.put("ngaySinh", obj.NGAY_SINH);
		    param.put("gioiTinh", obj.GIOI_TINH.intValue());
		    param.put("ngayBD", obj.GT_THE_TU);
		    param.put("ngayKT", obj.GT_THE_DEN);
		    param.put("maCSKCB", obj.MA_DKBD);
			//
		    StringEntity params = new StringEntity(param.toString(), "UTF-8");
		    params.setContentEncoding("UTF-8");
		    params.setContentType("application/json");
		    
		    request.addHeader("content-type", "application/json");
		    request.setEntity(params);
		    //
			logger.info("--------------SEND Request "+request.getURI().toASCIIString());
			logger.info("JSON: "+param.toString());
			//
			CloseableHttpResponse mainPageRes;
			mainPageRes = httpclient.execute(request );
			//
			StatusLine line = mainPageRes.getStatusLine();
			//
			logger.info("REQUEST="+line.getStatusCode());
			System.out.println("CODE REQUEST="+line.getStatusCode());
			if(line.getStatusCode()!=200){
				addScreenLog( obj.HO_TEN +":"+"Có lỗi gọi API:"+line.getStatusCode());
				objCheckTheObj.checkText = "Có lỗi gọi API. HTTP CODE="+line.getStatusCode();
				mainPageRes.close();
				return objCheckTheObj;
			}
			HttpEntity entity = mainPageRes.getEntity();
			String strHtml = EntityUtils.toString(entity);
			mainPageRes.close();
			//
			logger.info("REQUEST\n" + strHtml);
			JSONObject result = new JSONObject(strHtml);
			String maKetQua = (String)result.get("maKetQua");
			//{"data":3}
			int iMakq = Integer.parseInt(maKetQua);
			objCheckTheObj.checkCode = iMakq ; 
			String text = DbHelper.hashcheckThe.get(objCheckTheObj.checkCode);
			if(text==null){
				objCheckTheObj.checkText = "Có lỗi...";
			}
			else{
				objCheckTheObj.checkText = text;
			}
			if(result.get("dsLichSuKCB")==null){
				
			}
			else{
				JSONArray dsLichSuKCB = (JSONArray)result.get("dsLichSuKCB");
				if(dsLichSuKCB==null){
					//
				}
				else{
					for(int i=0; i<dsLichSuKCB.length(); i++){
						JSONObject objLichSu = (JSONObject)dsLichSuKCB.get(i);
						String lineText = ""+(Integer)objLichSu.get("maHoSo") +";"+(String)objLichSu.get("maCSKCB") +";" +(String)objLichSu.get("tuNgay") +";" + (String)objLichSu.get("tenBenh");
						objCheckTheObj.listKhamBenh.add( lineText);
						objCheckTheObj.jsonText += lineText+"@";
					}
				}
			}
			//
		} catch (Exception e) {
			e.printStackTrace();
		}
		//addScreenLog("--------------Check OK");
		return objCheckTheObj;
	}
	public void doStop() {
		isPause = true;
	}
	public String checkMaThe(String MaThe, String HoTen, String NgaySinh) {
		System.out.println("MATHE="+MaThe+" Hoten="+HoTen+" Ngaysinh="+NgaySinh);
		//===============================================================================
		// GET VIEW STATE
		//===============================================================================
		String hoten = HoTen==null?"Nguyễn Phúc Thuần":HoTen;
		String ngaysinh = NgaySinh==null?"12/12/2012":NgaySinh;
		HttpUriRequest mainPageReq = RequestBuilder
				.post()
				.setUri("https://gdbhyt.baohiemxahoi.gov.vn/ThongTuyenLSKCB/CheckMaThe")
				.addParameter("MaThe", MaThe)
				.addParameter("HoTen", hoten)
				.addParameter("NgaySinh", ngaysinh)
				.build();
		//
		mainPageReq.setHeader("Accept", "application/json, text/javascript, */*; q=0.01");
		mainPageReq.setHeader("Accept-Encoding", "gzip, deflate, br");
		mainPageReq.setHeader("Accept-Language", "en-US,en;q=0.8,id;q=0.6,vi;q=0.4,ja;q=0.2,es;q=0.2");
		mainPageReq.setHeader("Connection", "keep-alive");
		mainPageReq.setHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
		mainPageReq.setHeader("DNT", "1");
		mainPageReq.setHeader("Host", "gdbhyt.baohiemxahoi.gov.vn");
		mainPageReq.setHeader("Origin", "https://gdbhyt.baohiemxahoi.gov.vn");
		mainPageReq.setHeader("Referer", "https://gdbhyt.baohiemxahoi.gov.vn/");
		mainPageReq.setHeader("X-Requested-With", "XMLHttpRequest");
		mainPageReq.setHeader("Upgrade-Insecure-Requests", "1");
		
		addScreenLog("--------------SEND Request POST "+mainPageReq.getURI().toASCIIString());
		System.out.println("MATHE="+MaThe+" Hoten="+HoTen+" Ngaysinh="+NgaySinh);

		CloseableHttpResponse mainPageRes;
		try {
			mainPageRes = httpclient.execute(mainPageReq);
			System.out.println("checkMaThe httpclient="+httpclient);
			//
			StatusLine line = mainPageRes.getStatusLine();
			//
			logger.info("CODE checkMaThe="+line.getStatusCode());
			System.out.println("CODE checkMaThe="+line.getStatusCode());
			if(line.getStatusCode()==200){
				//
			}
			HttpEntity entity = mainPageRes.getEntity();
			String strHtml = EntityUtils.toString(entity);
			logger.info("checkMaThe\n" + strHtml);
			//
			mainPageRes.close();
			//
			//{"code":3,"message":"Họ tên không đúng! (Tên CSDL thẻ: Nguyễn Ngọc Phương Thanh)"}
			if(strHtml.indexOf("Họ tên không đúng")>-1){
				//
				String strBegin = "Họ tên không đúng! (Tên CSDL thẻ: ";
				String strEnd = ")\"}";
				int posBegin =strHtml.indexOf(strBegin);
				int posEnd = -1;
				if(posBegin>-1){
					posEnd = strHtml.indexOf(strEnd);
				}
				if(posBegin>-1 && posEnd>-1){
					String temp = strHtml.substring(posBegin+strBegin.length(), posEnd);
					// return name
					return temp.trim();
				}
				//
			}
			else if(strHtml.indexOf("Ngày sinh không đúng!")>-1){
				//{"code":3,"message":"Ngày sinh không đúng! (Ngày sinh CSLD thẻ: 01/08/2006)"}
				//
				String strBegin = "Ngày sinh không đúng! (Ngày sinh CSLD thẻ: ";
				String strEnd = ")\"}";
				int posBegin =strHtml.indexOf(strBegin);
				int posEnd = -1;
				if(posBegin>-1){
					posEnd = strHtml.indexOf(strEnd);
				}
				if(posBegin>-1 && posEnd>-1){
					String temp = strHtml.substring(posBegin+strBegin.length(), posEnd);
					// return ngay sinh
					return temp.trim();
				}
				//
			}
			else if(strHtml.indexOf("Nơi KCBBĐ")>-1){
				//{"code":2,"message":"Thẻ hết hạn! Họ tên: Nguyễn Ngọc Phương Thanh, Ngày sinh: 01/08/2006! (ĐC: Trường Tiểu học Chí Linh; Nơi KCBBĐ: 79032; Hạn thẻ: 01/10/2012 - 30/09/2013)."}
				//
				//////////////////////
				String strBegin = "\"message\":\"";
				String strEnd = "Họ tên: ";
				int posBegin =strHtml.indexOf(strBegin);
				int posEnd = -1;
				if(posBegin>-1){
					posEnd = strHtml.indexOf(strEnd);
				}
				String message = "";
				if(posBegin>-1 && posEnd>-1){
					message = strHtml.substring(posBegin+strBegin.length(), posEnd);
					// return ngay sinh
				}
				//////////////////////
				strBegin = strEnd;
				strEnd = "Ngày sinh:";
				posBegin =strHtml.indexOf(strBegin);
				posEnd = -1;
				if(posBegin>-1){
					posEnd = strHtml.indexOf(strEnd);
				}
				hoten = "";
				if(posBegin>-1 && posEnd>-1){
					hoten = strHtml.substring(posBegin+strBegin.length(), posEnd);
					// return ngay sinh
				}
				//////////////////////
				strBegin = strEnd;
				strEnd = "! (ĐC: ";
				posBegin =strHtml.indexOf(strBegin);
				posEnd = -1;
				if(posBegin>-1){
					posEnd = strHtml.indexOf(strEnd);
				}
				ngaysinh = "";
				if(posBegin>-1 && posEnd>-1){
					ngaysinh = strHtml.substring(posBegin+strBegin.length(), posEnd);
					// return ngay sinh
				}
				//{"code":2,"message":"Thẻ hết hạn! Họ tên: Nguyễn Ngọc Phương Thanh, Ngày sinh: 01/08/2006! (ĐC: Trường Tiểu học Chí Linh; Nơi KCBBĐ: 79032; Hạn thẻ: 01/10/2012 - 30/09/2013)."}
				//////////////////////
				strBegin = strEnd;
				strEnd = "; Nơi KCBBĐ";
				posBegin =strHtml.indexOf(strBegin);
				posEnd = -1;
				if(posBegin>-1){
					posEnd = strHtml.indexOf(strEnd);
				}
				String diachi = "";
				if(posBegin>-1 && posEnd>-1){
					diachi = strHtml.substring(posBegin+strBegin.length(), posEnd);
					// return ngay sinh
				}
				//{"code":2,"message":"Thẻ hết hạn! Họ tên: Nguyễn Ngọc Phương Thanh, Ngày sinh: 01/08/2006! (ĐC: Trường Tiểu học Chí Linh; Nơi KCBBĐ: 79032; Hạn thẻ: 01/10/2012 - 30/09/2013)."}
				strBegin = strEnd;
				strEnd = "; Hạn thẻ:";
				posBegin =strHtml.indexOf(strBegin);
				posEnd = -1;
				if(posBegin>-1){
					posEnd = strHtml.indexOf(strEnd);
				}
				String KCBBD = "";
				if(posBegin>-1 && posEnd>-1){
					KCBBD = strHtml.substring(posBegin+strBegin.length(), posEnd);
					// return ngay sinh
				}
				//
				//{"code":2,"message":"Thẻ hết hạn! Họ tên: Nguyễn Ngọc Phương Thanh, Ngày sinh: 01/08/2006! (ĐC: Trường Tiểu học Chí Linh; Nơi KCBBĐ: 79032; Hạn thẻ: 01/10/2012 - 30/09/2013)."}
				strBegin = strEnd;
				strEnd = ").\"}";
				posBegin =strHtml.indexOf(strBegin);
				posEnd = -1;
				if(posBegin>-1){
					posEnd = strHtml.indexOf(strEnd);
				}
				String tungaydenngay = "";
				if(posBegin>-1 && posEnd>-1){
					tungaydenngay = strHtml.substring(posBegin+strBegin.length(), posEnd);
					tungaydenngay.replaceAll("-", "|");
					// return ngay sinh
				}
				//
				return message+"|"+hoten+"|"+ngaysinh+"|"+diachi+"|"+KCBBD+"|"+tungaydenngay;
				//
			}
			//
		} catch (Exception e) {
			e.printStackTrace();
		}
		//
		return "";
	}
	public String home() {
		//===============================================================================
		// GET VIEW STATE
		//===============================================================================
		HttpUriRequest mainPageReq = RequestBuilder
				.get()
				.setUri("https://gdbhyt.baohiemxahoi.gov.vn/Home")
				.build();
		//
		mainPageReq.setHeader("Accept", "application/json, text/javascript, */*; q=0.01");
		mainPageReq.setHeader("Accept-Encoding", "gzip, deflate, br");
		mainPageReq.setHeader("Accept-Language", "en-US,en;q=0.8,id;q=0.6,vi;q=0.4,ja;q=0.2,es;q=0.2");
		mainPageReq.setHeader("Connection", "keep-alive");
		mainPageReq.setHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
		mainPageReq.setHeader("DNT", "1");
		mainPageReq.setHeader("Host", "gdbhyt.baohiemxahoi.gov.vn");
		mainPageReq.setHeader("Upgrade-Insecure-Requests", "1");
		
		addScreenLog("--------------SEND Request GET "+mainPageReq.getURI().toASCIIString());

		CloseableHttpResponse mainPageRes;
		try {
			mainPageRes = httpclient.execute(mainPageReq);
			System.out.println("home httpclient="+httpclient);
			//
			StatusLine line = mainPageRes.getStatusLine();
			//
			logger.info("CODE home="+line.getStatusCode());
			System.out.println("CODE home="+line.getStatusCode());
			if(line.getStatusCode()==200){
				//
			}
			HttpEntity entity = mainPageRes.getEntity();
			String strHtml = EntityUtils.toString(entity);
			logger.info("home\n" + strHtml);
			//
			mainPageRes.close();
			//
			//
		} catch (Exception e) {
			e.printStackTrace();
		}
		//
		return "";
	}
	public String DashboardXml1() {
		//===============================================================================
		// GET VIEW STATE
		//===============================================================================
		HttpUriRequest mainPageReq = RequestBuilder
				.get()
				.setUri("https://gdbhyt.baohiemxahoi.gov.vn/DashboardXml1")
				.build();
		//
		mainPageReq.setHeader("Accept", "application/json, text/javascript, */*; q=0.01");
		mainPageReq.setHeader("Accept-Encoding", "gzip, deflate, br");
		mainPageReq.setHeader("Accept-Language", "en-US,en;q=0.8,id;q=0.6,vi;q=0.4,ja;q=0.2,es;q=0.2");
		mainPageReq.setHeader("Connection", "keep-alive");
		mainPageReq.setHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
		mainPageReq.setHeader("DNT", "1");
		mainPageReq.setHeader("Host", "gdbhyt.baohiemxahoi.gov.vn");
		//mainPageReq.setHeader("Upgrade-Insecure-Requests", "1");
		
		addScreenLog("--------------SEND Request GET DashboardXml1 "+mainPageReq.getURI().toASCIIString());

		CloseableHttpResponse mainPageRes;
		try {
			mainPageRes = httpclient.execute(mainPageReq);
			System.out.println("DashboardXml1 httpclient="+httpclient);
			//
			StatusLine line = mainPageRes.getStatusLine();
			//
			logger.info("CODE DashboardXml1="+line.getStatusCode());
			System.out.println("CODE DashboardXml1="+line.getStatusCode());
			if(line.getStatusCode()==200){
				//
			}
			HttpEntity entity = mainPageRes.getEntity();
			String strHtml = EntityUtils.toString(entity);
			logger.info("DashboardXml1\n" + strHtml);
			//
			mainPageRes.close();
			//
			//
		} catch (Exception e) {
			e.printStackTrace();
		}
		//
		return "";
	}
	public String thongTuyen() {
		//===============================================================================
		// GET VIEW STATE
		//===============================================================================
		HttpUriRequest mainPageReq = RequestBuilder
				.get()
				.setUri("https://gdbhyt.baohiemxahoi.gov.vn/ThongTuyenLSKCB/Index")
				.build();
		//
		mainPageReq.setHeader("Accept", "application/json, text/javascript, */*; q=0.01");
		mainPageReq.setHeader("Accept-Encoding", "gzip, deflate, br");
		mainPageReq.setHeader("Accept-Language", "en-US,en;q=0.8,id;q=0.6,vi;q=0.4,ja;q=0.2,es;q=0.2");
		mainPageReq.setHeader("Connection", "keep-alive");
		mainPageReq.setHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
		mainPageReq.setHeader("DNT", "1");
		mainPageReq.setHeader("Host", "gdbhyt.baohiemxahoi.gov.vn");
		mainPageReq.setHeader("Upgrade-Insecure-Requests", "1");
		
		addScreenLog("--------------SEND Request GET DashboardXml1 "+mainPageReq.getURI().toASCIIString());

		CloseableHttpResponse mainPageRes;
		try {
			mainPageRes = httpclient.execute(mainPageReq);
			System.out.println("ThongTuyenLSKCB httpclient="+httpclient);
			//
			StatusLine line = mainPageRes.getStatusLine();
			//
			logger.info("CODE ThongTuyenLSKCB="+line.getStatusCode());
			System.out.println("CODE ThongTuyenLSKCB="+line.getStatusCode());
			if(line.getStatusCode()==200){
				//
			}
			HttpEntity entity = mainPageRes.getEntity();
			String strHtml = EntityUtils.toString(entity);
			logger.info("ThongTuyenLSKCB\n" + strHtml);
			//
			mainPageRes.close();
			//
			//
		} catch (Exception e) {
			e.printStackTrace();
		}
		//
		return "";
	}
	
	public long getFileSize(String CHECKURL){
		// reset betcount here
		if(httpclient==null){
			prepareConnection();
		}
		try {
			String strUrl = CHECKURL;
			System.out.println("CHECK SIZE="+strUrl);
			//
			HttpGet request = new HttpGet(strUrl);
			System.out.println("CHECK SIZE="+strUrl);
		    //JSONObject param = new JSONObject();
		    //
			//
		    //StringEntity params = new StringEntity(param.toString(), "UTF-8");
		    //params.setContentEncoding("UTF-8");
		    //params.setContentType("application/json");
		    //request.addHeader("content-type", "application/json");
		    //request.setEntity(params);
		    //
			logger.info("--------------SEND httpclient= "+httpclient);
			logger.info("--------------SEND Request "+request.getURI().toASCIIString());
			//logger.info("JSON: "+param.toString());
			//
			CloseableHttpResponse mainPageRes;
			mainPageRes = httpclient.execute(request );
			//
			StatusLine line = mainPageRes.getStatusLine();
			//
			logger.info("REQUEST="+line.getStatusCode());
			System.out.println("CODE REQUEST="+line.getStatusCode());
			if(line.getStatusCode()!=200){
				mainPageRes.close();
				return -1;
			}
			HttpEntity entity = mainPageRes.getEntity();
			String strHtml = EntityUtils.toString(entity);
			mainPageRes.close();
			//
			logger.info("REQUEST\n" + strHtml);
			return Utils.getInt(strHtml);
			//
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
}