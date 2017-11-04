﻿/*
 * Java bean class for entity table nhap_thuoc 
 * Created on 31 Oct 2017 ( Date ISO 2017-10-31 - Time 21:40:06 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 * template update by NEO
 */

package com.model.dao;

import java.io.Serializable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.sql2o.Connection;
import com.DbHelper;

import java.util.Date;

/**
 * Entity bean for table "nhap_thuoc"
 * 
 * @author Telosys Tools Generator
 *
 */
public class NhapThuoc implements Serializable
{
    static Logger logger = LogManager.getLogger(NhapThuoc.class.getName());
    private static final long serialVersionUID = 1L;

    public Integer    NT_ID ; // Primary Key

    public Integer    V_ID ;
    public Integer    KHO_ID ;
    public String     VENDOR_NAME ;
    public String     VENDOR_ADDR ;
    public Date       NGAY_NHAP ;
    public Date       NGAY_HD ;
    public String     TENKHO ;
    public String     SO_HOA_DON ;
    public String     KH_HOA_DON ;
    public Integer    TONGCONG ;
    public Integer    TONGCONG_VAT ;
    public Integer    VAT ;
    public Integer    STS ;

    /**
     * Default constructor
     */
    public NhapThuoc()
    {
        super();
        // Set default value 
this.V_ID = 0;                         // data type = Integer   
this.KHO_ID = 0;                         // data type = Integer   
this.VENDOR_NAME = "";//String.format("%255.255s", "VENDOR_NAME").trim(); // data type = String     255
this.VENDOR_ADDR = "";//String.format("%50.50s", "VENDOR_ADDR").trim(); // data type = String     50
this.NGAY_NHAP = new java.util.Date();      // data type = Date      
this.NGAY_HD = new java.util.Date();      // data type = Date      
this.TENKHO = "";//String.format("%32.32s", "TENKHO").trim(); // data type = String     32
this.SO_HOA_DON = "";//String.format("%32.32s", "SO_HOA_DON").trim(); // data type = String     32
this.KH_HOA_DON = "";//String.format("%50.50s", "KH_HOA_DON").trim(); // data type = String     50
this.TONGCONG = 0;                         // data type = Integer   
this.TONGCONG_VAT = 0;                         // data type = Integer   
this.VAT = 0;                         // data type = Integer   
this.STS = 0;                         // data type = Integer   
        // End Set default value 
    }
    
    //----------------------------------------------------------------------
    // GETTER(S) & SETTER(S) FOR THE PRIMARY KEY 
    //----------------------------------------------------------------------
    /**
     * Set the "ntId" field value
     * This field is mapped on the database column "NT_ID" ( type "INT", NotNull : true ) 
     * @param NT_ID
     */
	public void setNT_ID( Integer NT_ID )
    {
        this.NT_ID = NT_ID ;
    }
    /**
     * Get the "NT_ID" field value
     * This field is mapped on the database column "NT_ID" ( type "INT", NotNull : true ) 
     * @return the field value
     */
	public Integer getNT_ID()
    {
        return this.NT_ID;
    }

    //----------------------------------------------------------------------
    // GETTER(S) & SETTER(S) FOR DATA FIELDS
    //----------------------------------------------------------------------
    //--- DATABASE MAPPING : V_ID ( INT ) 
    /**
     * Set the "V_ID" field value
     * This field is mapped on the database column "V_ID" ( type "INT", NotNull : true ) 
     * @param V_ID
     */
    public void setV_ID( Integer V_ID )
    {
        this.V_ID = V_ID;
    }
    /**
     * Get the "vId" field value
     * This field is mapped on the database column "V_ID" ( type "INT", NotNull : true ) 
     * @return the field value
     */
    public Integer getV_ID()
    {
        return this.V_ID;
    }

    //--- DATABASE MAPPING : KHO_ID ( INT ) 
    /**
     * Set the "KHO_ID" field value
     * This field is mapped on the database column "KHO_ID" ( type "INT", NotNull : false ) 
     * @param KHO_ID
     */
    public void setKHO_ID( Integer KHO_ID )
    {
        this.KHO_ID = KHO_ID;
    }
    /**
     * Get the "khoId" field value
     * This field is mapped on the database column "KHO_ID" ( type "INT", NotNull : false ) 
     * @return the field value
     */
    public Integer getKHO_ID()
    {
        return this.KHO_ID;
    }

    //--- DATABASE MAPPING : VENDOR_NAME ( VARCHAR ) 
    /**
     * Set the "VENDOR_NAME" field value
     * This field is mapped on the database column "VENDOR_NAME" ( type "VARCHAR", NotNull : true ) 
     * @param VENDOR_NAME
     */
    public void setVENDOR_NAME( String VENDOR_NAME )
    {
        this.VENDOR_NAME = VENDOR_NAME;
    }
    /**
     * Get the "vendorName" field value
     * This field is mapped on the database column "VENDOR_NAME" ( type "VARCHAR", NotNull : true ) 
     * @return the field value
     */
    public String getVENDOR_NAME()
    {
        return this.VENDOR_NAME;
    }

    //--- DATABASE MAPPING : VENDOR_ADDR ( VARCHAR ) 
    /**
     * Set the "VENDOR_ADDR" field value
     * This field is mapped on the database column "VENDOR_ADDR" ( type "VARCHAR", NotNull : true ) 
     * @param VENDOR_ADDR
     */
    public void setVENDOR_ADDR( String VENDOR_ADDR )
    {
        this.VENDOR_ADDR = VENDOR_ADDR;
    }
    /**
     * Get the "vendorAddr" field value
     * This field is mapped on the database column "VENDOR_ADDR" ( type "VARCHAR", NotNull : true ) 
     * @return the field value
     */
    public String getVENDOR_ADDR()
    {
        return this.VENDOR_ADDR;
    }

    //--- DATABASE MAPPING : NGAY_NHAP ( TIMESTAMP ) 
    /**
     * Set the "NGAY_NHAP" field value
     * This field is mapped on the database column "NGAY_NHAP" ( type "TIMESTAMP", NotNull : true ) 
     * @param NGAY_NHAP
     */
    public void setNGAY_NHAP( Date NGAY_NHAP )
    {
        this.NGAY_NHAP = NGAY_NHAP;
    }
    /**
     * Get the "ngayNhap" field value
     * This field is mapped on the database column "NGAY_NHAP" ( type "TIMESTAMP", NotNull : true ) 
     * @return the field value
     */
    public Date getNGAY_NHAP()
    {
        return this.NGAY_NHAP;
    }

    //--- DATABASE MAPPING : NGAY_HD ( TIMESTAMP ) 
    /**
     * Set the "NGAY_HD" field value
     * This field is mapped on the database column "NGAY_HD" ( type "TIMESTAMP", NotNull : true ) 
     * @param NGAY_HD
     */
    public void setNGAY_HD( Date NGAY_HD )
    {
        this.NGAY_HD = NGAY_HD;
    }
    /**
     * Get the "ngayHd" field value
     * This field is mapped on the database column "NGAY_HD" ( type "TIMESTAMP", NotNull : true ) 
     * @return the field value
     */
    public Date getNGAY_HD()
    {
        return this.NGAY_HD;
    }

    //--- DATABASE MAPPING : TENKHO ( VARCHAR ) 
    /**
     * Set the "TENKHO" field value
     * This field is mapped on the database column "TENKHO" ( type "VARCHAR", NotNull : false ) 
     * @param TENKHO
     */
    public void setTENKHO( String TENKHO )
    {
        this.TENKHO = TENKHO;
    }
    /**
     * Get the "tenkho" field value
     * This field is mapped on the database column "TENKHO" ( type "VARCHAR", NotNull : false ) 
     * @return the field value
     */
    public String getTENKHO()
    {
        return this.TENKHO;
    }

    //--- DATABASE MAPPING : SO_HOA_DON ( VARCHAR ) 
    /**
     * Set the "SO_HOA_DON" field value
     * This field is mapped on the database column "SO_HOA_DON" ( type "VARCHAR", NotNull : true ) 
     * @param SO_HOA_DON
     */
    public void setSO_HOA_DON( String SO_HOA_DON )
    {
        this.SO_HOA_DON = SO_HOA_DON;
    }
    /**
     * Get the "soHoaDon" field value
     * This field is mapped on the database column "SO_HOA_DON" ( type "VARCHAR", NotNull : true ) 
     * @return the field value
     */
    public String getSO_HOA_DON()
    {
        return this.SO_HOA_DON;
    }

    //--- DATABASE MAPPING : KH_HOA_DON ( VARCHAR ) 
    /**
     * Set the "KH_HOA_DON" field value
     * This field is mapped on the database column "KH_HOA_DON" ( type "VARCHAR", NotNull : true ) 
     * @param KH_HOA_DON
     */
    public void setKH_HOA_DON( String KH_HOA_DON )
    {
        this.KH_HOA_DON = KH_HOA_DON;
    }
    /**
     * Get the "khHoaDon" field value
     * This field is mapped on the database column "KH_HOA_DON" ( type "VARCHAR", NotNull : true ) 
     * @return the field value
     */
    public String getKH_HOA_DON()
    {
        return this.KH_HOA_DON;
    }

    //--- DATABASE MAPPING : TONGCONG ( INT ) 
    /**
     * Set the "TONGCONG" field value
     * This field is mapped on the database column "TONGCONG" ( type "INT", NotNull : true ) 
     * @param TONGCONG
     */
    public void setTONGCONG( Integer TONGCONG )
    {
        this.TONGCONG = TONGCONG;
    }
    /**
     * Get the "tongcong" field value
     * This field is mapped on the database column "TONGCONG" ( type "INT", NotNull : true ) 
     * @return the field value
     */
    public Integer getTONGCONG()
    {
        return this.TONGCONG;
    }

    //--- DATABASE MAPPING : TONGCONG_VAT ( INT ) 
    /**
     * Set the "TONGCONG_VAT" field value
     * This field is mapped on the database column "TONGCONG_VAT" ( type "INT", NotNull : true ) 
     * @param TONGCONG_VAT
     */
    public void setTONGCONG_VAT( Integer TONGCONG_VAT )
    {
        this.TONGCONG_VAT = TONGCONG_VAT;
    }
    /**
     * Get the "tongcongVat" field value
     * This field is mapped on the database column "TONGCONG_VAT" ( type "INT", NotNull : true ) 
     * @return the field value
     */
    public Integer getTONGCONG_VAT()
    {
        return this.TONGCONG_VAT;
    }

    //--- DATABASE MAPPING : VAT ( INT ) 
    /**
     * Set the "VAT" field value
     * This field is mapped on the database column "VAT" ( type "INT", NotNull : true ) 
     * @param VAT
     */
    public void setVAT( Integer VAT )
    {
        this.VAT = VAT;
    }
    /**
     * Get the "vat" field value
     * This field is mapped on the database column "VAT" ( type "INT", NotNull : true ) 
     * @return the field value
     */
    public Integer getVAT()
    {
        return this.VAT;
    }

    //--- DATABASE MAPPING : STS ( INT ) 
    /**
     * Set the "STS" field value
     * This field is mapped on the database column "STS" ( type "INT", NotNull : true ) 
     * @param STS
     */
    public void setSTS( Integer STS )
    {
        this.STS = STS;
    }
    /**
     * Get the "sts" field value
     * This field is mapped on the database column "STS" ( type "INT", NotNull : true ) 
     * @return the field value
     */
    public Integer getSTS()
    {
        return this.STS;
    }



/*
 * CRUD functions
 */
    public static NhapThuoc load(String NT_ID) {
    	Connection connection = null;

		try {
			connection = DbHelper.getSql2o();
			String sql = "select * from nhap_thuoc where NT_ID=:NT_ID";
			logger.debug(sql);
			NhapThuoc obj = connection
					.createQuery(
							sql,
							true)
					.addParameter("NT_ID", NT_ID)
					.executeAndFetchFirst( NhapThuoc.class);
            logger.info(obj);
            logger.debug("Load "+obj);
            return obj;
		} catch (Exception t) {
            logger.error("Load Error: ID="+NT_ID +"\nException=\n"+ t);
			throw new RuntimeException("LOAD [NhapThuoc]. ERROR: "+t.getMessage(), t);
		}
    }
    
    public static NhapThuoc load(Integer NT_ID) {
    	Connection connection = null;

		try {
			connection = DbHelper.getSql2o();
			String sql = "select * from nhap_thuoc where NT_ID=:NT_ID";
			logger.debug(sql);
			NhapThuoc obj = connection
					.createQuery(
							sql,
							true)
					.addParameter("NT_ID", NT_ID)
					.executeAndFetchFirst( NhapThuoc.class);
            logger.info(obj);
            logger.debug("Load "+obj);
            return obj;
		} catch (Exception t) {
            logger.error("Load Error: ID="+NT_ID +"\nException=\n"+ t);
			throw new RuntimeException("LOAD [NhapThuoc]. ERROR: "+t.getMessage(), t);
		}
    }
    
    public String toString(){
    	String strData = "nhap_thuoc "
    			+" NT_ID = " + NT_ID                + "; V_ID = " + V_ID
                + "; KHO_ID = " + KHO_ID
                + "; VENDOR_NAME = " + VENDOR_NAME
                + "; VENDOR_ADDR = " + VENDOR_ADDR
                + "; NGAY_NHAP = " + NGAY_NHAP
                + "; NGAY_HD = " + NGAY_HD
                + "; TENKHO = " + TENKHO
                + "; SO_HOA_DON = " + SO_HOA_DON
                + "; KH_HOA_DON = " + KH_HOA_DON
                + "; TONGCONG = " + TONGCONG
                + "; TONGCONG_VAT = " + TONGCONG_VAT
                + "; VAT = " + VAT
                + "; STS = " + STS
    			;
    	return strData;
    }
    
    public void insert() {
		//
    	if( NT_ID !=null){
			// Update 
            update();
			return;
		}
        if(DbHelper.checkPhanQuyen(DbHelper.INSERT, "nhap_thuoc")==false){
            logger.info("YOU (" + DbHelper.getCurrentSessionUserName() + "), DON'T HAVE INSERT RIGHT for table nhap_thuoc");
            return;
        }
		Connection connection = null;

		try {
			connection = DbHelper.getSql2o();
            connection.setRollbackOnException(false);

            String sql = "insert into nhap_thuoc (V_ID, KHO_ID, VENDOR_NAME, VENDOR_ADDR, NGAY_NHAP, NGAY_HD, TENKHO, SO_HOA_DON, KH_HOA_DON, TONGCONG, TONGCONG_VAT, VAT,  STS) values(:V_ID, :KHO_ID, :VENDOR_NAME, :VENDOR_ADDR, :NGAY_NHAP, :NGAY_HD, :TENKHO, :SO_HOA_DON, :KH_HOA_DON, :TONGCONG, :TONGCONG_VAT, :VAT,  0)";
                            			int createdId = connection
                            					.createQuery(
							sql,
							true)
                        .addParameter("V_ID", this.V_ID)
                            .addParameter("KHO_ID", this.KHO_ID)
                            .addParameter("VENDOR_NAME", this.VENDOR_NAME)
                            .addParameter("VENDOR_ADDR", this.VENDOR_ADDR)
                            .addParameter("NGAY_NHAP", this.NGAY_NHAP)
                            .addParameter("NGAY_HD", this.NGAY_HD)
                            .addParameter("TENKHO", this.TENKHO)
                            .addParameter("SO_HOA_DON", this.SO_HOA_DON)
                            .addParameter("KH_HOA_DON", this.KH_HOA_DON)
                            .addParameter("TONGCONG", this.TONGCONG)
                            .addParameter("TONGCONG_VAT", this.TONGCONG_VAT)
                            .addParameter("VAT", this.VAT)
        					.executeUpdate()
                                                .getKey(Integer.class);
                            					
            logger.info("Add OK: NewID="+createdId);
            logger.info("Add OK [nhap_thuoc] debug: "+toString());
            // Set KEYID NT_ID = newID
                            NT_ID = createdId;
                            			// Save log
            connection
					.createQuery(
							"insert into action_log(u_id,u_action,dbtable,actionid,fieldid) values(:u_id,:u_action,:dbtable,:actionid,:fieldid)",
							true)
					.addParameter("u_id", DbHelper.getCurrentSessionUserId())
					.addParameter("u_action", "New nhap_thuoc. ID="+NT_ID)
                    .addParameter("dbtable", "nhap_thuoc")
                    .addParameter("fieldid", NT_ID)
					.addParameter("actionid", 1)
					.executeUpdate();
            logger.info("Add [nhap_thuoc] OK: NewID="+createdId);
            logger.info("Add [nhap_thuoc] OK: Data="+this.toString());
		} catch (Exception t) {
            logger.error("Add [nhap_thuoc] Error: "+"\nException=\n"+ t);
			throw new RuntimeException("ADD [nhap_thuoc]. ERROR: "+t.getMessage(), t);
		}
	}
    
    public void update() {
		//
    	if(NT_ID==null){
            insert();
			return;
		}
        if(DbHelper.checkPhanQuyen(DbHelper.UPDATE, "nhap_thuoc")==false){
            logger.info("YOU (" + DbHelper.getCurrentSessionUserName() + "), DON'T HAVE UPDATE RIGHT for table nhap_thuoc");
            return;
        }
		Connection connection = null;

		try {
			connection = DbHelper.getSql2o();
            connection.setRollbackOnException(false);
            NhapThuoc temp = NhapThuoc.load(this.NT_ID);
            if(temp!=null){
            	logger.info("Begin Update [nhap_thuoc]: "+temp.toString());
            }
			//
			String sql = "update nhap_thuoc set V_ID=:V_ID, KHO_ID=:KHO_ID, VENDOR_NAME=:VENDOR_NAME, VENDOR_ADDR=:VENDOR_ADDR, NGAY_NHAP=:NGAY_NHAP, NGAY_HD=:NGAY_HD, TENKHO=:TENKHO, SO_HOA_DON=:SO_HOA_DON, KH_HOA_DON=:KH_HOA_DON, TONGCONG=:TONGCONG, TONGCONG_VAT=:TONGCONG_VAT, VAT=:VAT, STS=:STS where NT_ID=:NT_ID";
			connection
					.createQuery(
							sql,
							true)
                    .addParameter("V_ID", this.V_ID)
                    .addParameter("KHO_ID", this.KHO_ID)
                    .addParameter("VENDOR_NAME", this.VENDOR_NAME)
                    .addParameter("VENDOR_ADDR", this.VENDOR_ADDR)
                    .addParameter("NGAY_NHAP", this.NGAY_NHAP)
                    .addParameter("NGAY_HD", this.NGAY_HD)
                    .addParameter("TENKHO", this.TENKHO)
                    .addParameter("SO_HOA_DON", this.SO_HOA_DON)
                    .addParameter("KH_HOA_DON", this.KH_HOA_DON)
                    .addParameter("TONGCONG", this.TONGCONG)
                    .addParameter("TONGCONG_VAT", this.TONGCONG_VAT)
                    .addParameter("VAT", this.VAT)
                    .addParameter("STS", this.STS)
					.addParameter("NT_ID", this.NT_ID)
					.executeUpdate();
            logger.info("Update [nhap_thuoc] OK: ID="+NT_ID);
            logger.info("End   Update [nhap_thuoc]: "+toString());
            connection
					.createQuery(
							"insert into action_log(u_id,u_action,dbtable,actionid,fieldid) values(:u_id,:u_action,:dbtable,:actionid,:fieldid)",
							true)
					.addParameter("u_id", DbHelper.getCurrentSessionUserId())
					.addParameter("u_action", "Update nhap_thuoc. ID="+NT_ID)
                    .addParameter("dbtable", "nhap_thuoc")
                    .addParameter("fieldid", NT_ID)
					.addParameter("actionid", 2)
					.executeUpdate();
		} catch (Exception t) {
			logger.error("Update [nhap_thuoc] Error: ID=["+NT_ID +"]."+"\nException=\n"+ t);
			throw new RuntimeException("Update [nhap_thuoc]. ERROR: "+t.getMessage(), t);
		}
	}
    
    
    public void delete() {
		//
    	if(NT_ID==null){
			return;
		}
        if(DbHelper.checkPhanQuyen(DbHelper.DELETE, "nhap_thuoc")==false){
            logger.info("YOU (" + DbHelper.getCurrentSessionUserName() + "), DON'T HAVE DELETE RIGHT for table nhap_thuoc");
            return;
        }
		Connection connection = null;

		try {
			connection = DbHelper.getSql2o();
            connection.setRollbackOnException(false);
			String sql = "update nhap_thuoc set STS=:STS where NT_ID=:NT_ID";
			connection
					.createQuery(
							sql,
							true)
					.addParameter("STS", DbHelper.DELETE_STATUS)
					.addParameter("NT_ID", this.NT_ID)
					.executeUpdate();
			logger.error("Delete [nhap_thuoc] OK: ID=["+NT_ID +"].");
            connection
					.createQuery(
							"insert into action_log(u_id,u_action,dbtable,actionid,fieldid) values(:u_id,:u_action,:dbtable,:actionid,:fieldid)",
							true)
					.addParameter("u_id", DbHelper.getCurrentSessionUserId())
					.addParameter("u_action", "Update nhap_thuoc. ID="+NT_ID)
                    .addParameter("dbtable", "nhap_thuoc")
                    .addParameter("fieldid", NT_ID)
					.addParameter("actionid", 3)
					.executeUpdate();
		} catch (Exception t) {
			logger.error("Delete [nhap_thuoc] Error: ID=["+NT_ID +"]."+"\nException=\n"+ t);
			throw new RuntimeException("Delete [nhap_thuoc]. ID=["+NT_ID + " ERROR: "+t.getMessage(), t);
		}
	}
    
	public void deleteRow() {
		//
    	if(NT_ID==null){
			return;
		}
        if(DbHelper.checkPhanQuyen(DbHelper.DELETE, "nhap_thuoc")==false){
            logger.info("YOU (" + DbHelper.getCurrentSessionUserName() + "), DON'T HAVE DELETE RIGHT for table nhap_thuoc");
            return;
        }
		Connection connection = null;

		try {
			connection = DbHelper.getSql2o();
            connection.setRollbackOnException(false);
			String sql = "delete from nhap_thuoc where NT_ID=:NT_ID";
			connection
					.createQuery(
							sql,
							true)
					.addParameter("NT_ID", this.NT_ID)
					.executeUpdate();
			logger.error("Delete Row [nhap_thuoc] OK: ID=["+NT_ID +"].");
            connection
					.createQuery(
							"insert into action_log(u_id,u_action,dbtable,actionid,fieldid) values(:u_id,:u_action,:dbtable,:actionid,:fieldid)",
							true)
					.addParameter("u_id", DbHelper.getCurrentSessionUserId())
					.addParameter("u_action", "Update nhap_thuoc. ID="+NT_ID)
                    .addParameter("dbtable", "nhap_thuoc")
                    .addParameter("fieldid", NT_ID)
					.addParameter("actionid", 4)
					.executeUpdate();
		} catch (Exception t) {
			logger.error("Delete [nhap_thuoc] Error: ID=["+NT_ID +"]."+"\nException=\n"+ t);
			throw new RuntimeException("Delete [nhap_thuoc]. ID=["+NT_ID + " ERROR: "+t.getMessage(), t);
		}
	}
    
    public String getIndex(int idx){
        switch(idx){
            case 0:
                // java.lang.Integer   
                if(this.V_ID!=null)
                    return this.V_ID.toString();
                else
                    return "N/A";
            case 1:
                // java.lang.Integer   
                if(this.KHO_ID!=null)
                    return this.KHO_ID.toString();
                else
                    return "N/A";
            case 2:
                // java.lang.String    
                if(this.VENDOR_NAME!=null)
                    return this.VENDOR_NAME.toString();
                else
                    return "N/A";
            case 3:
                // java.lang.String    
                if(this.VENDOR_ADDR!=null)
                    return this.VENDOR_ADDR.toString();
                else
                    return "N/A";
            case 4:
                // java.lang.Date      
                if(this.NGAY_NHAP!=null)
                    return this.NGAY_NHAP.toString();
                else
                    return "N/A";
            case 5:
                // java.lang.Date      
                if(this.NGAY_HD!=null)
                    return this.NGAY_HD.toString();
                else
                    return "N/A";
            case 6:
                // java.lang.String    
                if(this.TENKHO!=null)
                    return this.TENKHO.toString();
                else
                    return "N/A";
            case 7:
                // java.lang.String    
                if(this.SO_HOA_DON!=null)
                    return this.SO_HOA_DON.toString();
                else
                    return "N/A";
            case 8:
                // java.lang.String    
                if(this.KH_HOA_DON!=null)
                    return this.KH_HOA_DON.toString();
                else
                    return "N/A";
            case 9:
                // java.lang.Integer   
                if(this.TONGCONG!=null)
                    return this.TONGCONG.toString();
                else
                    return "N/A";
            case 10:
                // java.lang.Integer   
                if(this.TONGCONG_VAT!=null)
                    return this.TONGCONG_VAT.toString();
                else
                    return "N/A";
            case 11:
                // java.lang.Integer   
                if(this.VAT!=null)
                    return this.VAT.toString();
                else
                    return "N/A";
            case 12:
                // java.lang.Integer   
                if(this.STS!=null)
                    return this.STS.toString();
                else
                    return "N/A";
            default:
        }
        return "";
    }
}