﻿/*
 * Java bean class for entity table khoa_phong 
 * Created on 17 Jul 2017 ( Date ISO 2017-07-17 - Time 08:25:19 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 * template update by NEO
 */

package com.model.dao;

import java.io.Serializable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.sql2o.Connection;
import com.DbHelper;


/**
 * Entity bean for table "khoa_phong"
 * 
 * @author Telosys Tools Generator
 *
 */
public class KhoaPhong implements Serializable
{
    static Logger logger = LogManager.getLogger(KhoaPhong.class.getName());
    private static final long serialVersionUID = 1L;

    public Integer    KP_ID ; // Primary Key

    public String     KP_MABH ;
    public String     KP_MAKHOA ;
    public String     KP_NAME ;
    public String     KP_SOPHONG ;

    /**
     * Default constructor
     */
    public KhoaPhong()
    {
        super();
        // Set default value 
this.KP_MABH = "";//String.format("%8.8s", "KP_MABH").trim(); // data type = String     8
this.KP_MAKHOA = "";//String.format("%3.3s", "KP_MAKHOA").trim(); // data type = String     3
this.KP_NAME = "";//String.format("%1000.1000s", "KP_NAME").trim(); // data type = String     1000
this.KP_SOPHONG = "";//String.format("%5.5s", "KP_SOPHONG").trim(); // data type = String     5
        // End Set default value 
    }
    
    //----------------------------------------------------------------------
    // GETTER(S) & SETTER(S) FOR THE PRIMARY KEY 
    //----------------------------------------------------------------------
    /**
     * Set the "kpId" field value
     * This field is mapped on the database column "KP_ID" ( type "INT", NotNull : true ) 
     * @param KP_ID
     */
	public void setKP_ID( Integer KP_ID )
    {
        this.KP_ID = KP_ID ;
    }
    /**
     * Get the "KP_ID" field value
     * This field is mapped on the database column "KP_ID" ( type "INT", NotNull : true ) 
     * @return the field value
     */
	public Integer getKP_ID()
    {
        return this.KP_ID;
    }

    //----------------------------------------------------------------------
    // GETTER(S) & SETTER(S) FOR DATA FIELDS
    //----------------------------------------------------------------------
    //--- DATABASE MAPPING : KP_MABH ( VARCHAR ) 
    /**
     * Set the "KP_MABH" field value
     * This field is mapped on the database column "KP_MABH" ( type "VARCHAR", NotNull : true ) 
     * @param KP_MABH
     */
    public void setKP_MABH( String KP_MABH )
    {
        this.KP_MABH = KP_MABH;
    }
    /**
     * Get the "kpMabh" field value
     * This field is mapped on the database column "KP_MABH" ( type "VARCHAR", NotNull : true ) 
     * @return the field value
     */
    public String getKP_MABH()
    {
        return this.KP_MABH;
    }

    //--- DATABASE MAPPING : KP_MAKHOA ( VARCHAR ) 
    /**
     * Set the "KP_MAKHOA" field value
     * This field is mapped on the database column "KP_MAKHOA" ( type "VARCHAR", NotNull : true ) 
     * @param KP_MAKHOA
     */
    public void setKP_MAKHOA( String KP_MAKHOA )
    {
        this.KP_MAKHOA = KP_MAKHOA;
    }
    /**
     * Get the "kpMakhoa" field value
     * This field is mapped on the database column "KP_MAKHOA" ( type "VARCHAR", NotNull : true ) 
     * @return the field value
     */
    public String getKP_MAKHOA()
    {
        return this.KP_MAKHOA;
    }

    //--- DATABASE MAPPING : KP_NAME ( VARCHAR ) 
    /**
     * Set the "KP_NAME" field value
     * This field is mapped on the database column "KP_NAME" ( type "VARCHAR", NotNull : true ) 
     * @param KP_NAME
     */
    public void setKP_NAME( String KP_NAME )
    {
        this.KP_NAME = KP_NAME;
    }
    /**
     * Get the "kpName" field value
     * This field is mapped on the database column "KP_NAME" ( type "VARCHAR", NotNull : true ) 
     * @return the field value
     */
    public String getKP_NAME()
    {
        return this.KP_NAME;
    }

    //--- DATABASE MAPPING : KP_SOPHONG ( VARCHAR ) 
    /**
     * Set the "KP_SOPHONG" field value
     * This field is mapped on the database column "KP_SOPHONG" ( type "VARCHAR", NotNull : true ) 
     * @param KP_SOPHONG
     */
    public void setKP_SOPHONG( String KP_SOPHONG )
    {
        this.KP_SOPHONG = KP_SOPHONG;
    }
    /**
     * Get the "kpSophong" field value
     * This field is mapped on the database column "KP_SOPHONG" ( type "VARCHAR", NotNull : true ) 
     * @return the field value
     */
    public String getKP_SOPHONG()
    {
        return this.KP_SOPHONG;
    }



/*
 * CRUD functions
 */
    public static KhoaPhong load(String KP_ID) {
    	Connection connection = null;

		try {
			connection = DbHelper.getSql2o();
			String sql = "select * from khoa_phong where KP_ID=:KP_ID";
			logger.debug(sql);
			KhoaPhong obj = connection
					.createQuery(
							sql,
							true)
					.addParameter("KP_ID", KP_ID)
					.executeAndFetchFirst( KhoaPhong.class);
            logger.info(obj);
            logger.debug("Load "+obj);
            return obj;
		} catch (Exception t) {
            logger.error("Load Error: ID="+KP_ID +"\nException=\n"+ t);
			throw new RuntimeException("LOAD [KhoaPhong]. ERROR: "+t.getMessage(), t);
		}
    }
    
    public static KhoaPhong load(Integer KP_ID) {
    	Connection connection = null;

		try {
			connection = DbHelper.getSql2o();
			String sql = "select * from khoa_phong where KP_ID=:KP_ID";
			logger.debug(sql);
			KhoaPhong obj = connection
					.createQuery(
							sql,
							true)
					.addParameter("KP_ID", KP_ID)
					.executeAndFetchFirst( KhoaPhong.class);
            logger.info(obj);
            logger.debug("Load "+obj);
            return obj;
		} catch (Exception t) {
            logger.error("Load Error: ID="+KP_ID +"\nException=\n"+ t);
			throw new RuntimeException("LOAD [KhoaPhong]. ERROR: "+t.getMessage(), t);
		}
    }
    
    public String toString(){
    	String strData = "khoa_phong "
    			+" KP_ID = " + KP_ID                + "; KP_MABH = " + KP_MABH
                + "; KP_MAKHOA = " + KP_MAKHOA
                + "; KP_NAME = " + KP_NAME
                + "; KP_SOPHONG = " + KP_SOPHONG
    			;
    	return strData;
    }
    
    public void insert() {
		//
    	if( KP_ID !=null){
			// Update 
            update();
			return;
		}
        if(DbHelper.checkPhanQuyen(DbHelper.INSERT, "khoa_phong")==false){
            logger.info("YOU (" + DbHelper.getCurrentSessionUserName() + "), DON'T HAVE INSERT RIGHT for table khoa_phong");
            return;
        }
		Connection connection = null;

		try {
			connection = DbHelper.getSql2o();
            connection.setRollbackOnException(false);

            String sql = "insert into khoa_phong (KP_MABH, KP_MAKHOA, KP_NAME, KP_SOPHONG STS) values(:KP_MABH, :KP_MAKHOA, :KP_NAME, :KP_SOPHONG 0)";
                            			int createdId = connection
                            					.createQuery(
							sql,
							true)
                        .addParameter("KP_MABH", this.KP_MABH)
                            .addParameter("KP_MAKHOA", this.KP_MAKHOA)
                            .addParameter("KP_NAME", this.KP_NAME)
                            .addParameter("KP_SOPHONG", this.KP_SOPHONG)
    					.executeUpdate()
                                                .getKey(Integer.class);
                            					
            logger.info("Add OK: NewID="+createdId);
            logger.info("Add OK [khoa_phong] debug: "+toString());
            // Set KEYID KP_ID = newID
                            KP_ID = createdId;
                            			// Save log
            connection
					.createQuery(
							"insert into action_log(u_id,u_action,dbtable,actionid,fieldid) values(:u_id,:u_action,:dbtable,:actionid,:fieldid)",
							true)
					.addParameter("u_id", DbHelper.getCurrentSessionUserId())
					.addParameter("u_action", "New khoa_phong. ID="+KP_ID)
                    .addParameter("dbtable", "khoa_phong")
                    .addParameter("fieldid", KP_ID)
					.addParameter("actionid", 1)
					.executeUpdate();
            logger.info("Add [khoa_phong] OK: NewID="+createdId);
		} catch (Exception t) {
            logger.error("Add [khoa_phong] Error: "+"\nException=\n"+ t);
			throw new RuntimeException("ADD [khoa_phong]. ERROR: "+t.getMessage(), t);
		}
	}
    
    public void update() {
		//
    	if(KP_ID==null){
            insert();
			return;
		}
        if(DbHelper.checkPhanQuyen(DbHelper.UPDATE, "khoa_phong")==false){
            logger.info("YOU (" + DbHelper.getCurrentSessionUserName() + "), DON'T HAVE UPDATE RIGHT for table khoa_phong");
            return;
        }
		Connection connection = null;

		try {
			connection = DbHelper.getSql2o();
            connection.setRollbackOnException(false);
			String sql = "update khoa_phong set KP_MABH=:KP_MABH, KP_MAKHOA=:KP_MAKHOA, KP_NAME=:KP_NAME, KP_SOPHONG=:KP_SOPHONG where KP_ID=:KP_ID";
			connection
					.createQuery(
							sql,
							true)
                    .addParameter("KP_MABH", this.KP_MABH)
                    .addParameter("KP_MAKHOA", this.KP_MAKHOA)
                    .addParameter("KP_NAME", this.KP_NAME)
                    .addParameter("KP_SOPHONG", this.KP_SOPHONG)
					.addParameter("KP_ID", this.KP_ID)
					.executeUpdate();
            logger.info("Update [khoa_phong] OK: ID="+KP_ID);
            logger.info("Update [khoa_phong] debug: "+toString());
            connection
					.createQuery(
							"insert into action_log(u_id,u_action,dbtable,actionid,fieldid) values(:u_id,:u_action,:dbtable,:actionid,:fieldid)",
							true)
					.addParameter("u_id", DbHelper.getCurrentSessionUserId())
					.addParameter("u_action", "Update khoa_phong. ID="+KP_ID)
                    .addParameter("dbtable", "khoa_phong")
                    .addParameter("fieldid", KP_ID)
					.addParameter("actionid", 2)
					.executeUpdate();
		} catch (Exception t) {
			logger.error("Update [khoa_phong] Error: ID=["+KP_ID +"]."+"\nException=\n"+ t);
			throw new RuntimeException("Update [khoa_phong]. ERROR: "+t.getMessage(), t);
		}
	}
    
    
    public void delete() {
		//
    	if(KP_ID==null){
			return;
		}
        if(DbHelper.checkPhanQuyen(DbHelper.DELETE, "khoa_phong")==false){
            logger.info("YOU (" + DbHelper.getCurrentSessionUserName() + "), DON'T HAVE DELETE RIGHT for table khoa_phong");
            return;
        }
		Connection connection = null;

		try {
			connection = DbHelper.getSql2o();
            connection.setRollbackOnException(false);
			String sql = "update khoa_phong set STS=:STS where KP_ID=:KP_ID";
			connection
					.createQuery(
							sql,
							true)
					.addParameter("STS", DbHelper.DELETE_STATUS)
					.addParameter("KP_ID", this.KP_ID)
					.executeUpdate();
			logger.error("Delete [khoa_phong] OK: ID=["+KP_ID +"].");
            connection
					.createQuery(
							"insert into action_log(u_id,u_action,dbtable,actionid,fieldid) values(:u_id,:u_action,:dbtable,:actionid,:fieldid)",
							true)
					.addParameter("u_id", DbHelper.getCurrentSessionUserId())
					.addParameter("u_action", "Update khoa_phong. ID="+KP_ID)
                    .addParameter("dbtable", "khoa_phong")
                    .addParameter("fieldid", KP_ID)
					.addParameter("actionid", 3)
					.executeUpdate();
		} catch (Exception t) {
			logger.error("Delete [khoa_phong] Error: ID=["+KP_ID +"]."+"\nException=\n"+ t);
			throw new RuntimeException("Delete [khoa_phong]. ID=["+KP_ID + " ERROR: "+t.getMessage(), t);
		}
	}
    
    public String getIndex(int idx){
        switch(idx){
            case 0:
                // java.lang.String    
                if(this.KP_MABH!=null)
                    return this.KP_MABH.toString();
                else
                    return "N/A";
            case 1:
                // java.lang.String    
                if(this.KP_MAKHOA!=null)
                    return this.KP_MAKHOA.toString();
                else
                    return "N/A";
            case 2:
                // java.lang.String    
                if(this.KP_NAME!=null)
                    return this.KP_NAME.toString();
                else
                    return "N/A";
            case 3:
                // java.lang.String    
                if(this.KP_SOPHONG!=null)
                    return this.KP_SOPHONG.toString();
                else
                    return "N/A";
            default:
        }
        return "";
    }
}