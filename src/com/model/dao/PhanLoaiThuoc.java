﻿/*
 * Java bean class for entity table phan_loai_thuoc 
 * Created on 14 Dec 2017 ( Date ISO 2017-12-14 - Time 16:37:52 )
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
import java.util.List;


/**
 * Entity bean for table "phan_loai_thuoc"
 * 
 * @author Telosys Tools Generator
 *
 */
public class PhanLoaiThuoc implements Serializable
{
    static Logger logger = LogManager.getLogger(PhanLoaiThuoc.class.getName());
    private static final long serialVersionUID = 1L;

    public Integer    PL_ID ; // Primary Key

    public String     PL_CODE ;
    public String     PL_NAME ;

    /**
     * Default constructor
     */
    public PhanLoaiThuoc()
    {
        super();
        // Set default value 
this.PL_CODE = "";//String.format("%8.8s", "PL_CODE").trim(); // data type = String     8
this.PL_NAME = "";//String.format("%256.256s", "PL_NAME").trim(); // data type = String     256
        // End Set default value 
    }
    
    public void copy(PhanLoaiThuoc fromObj)
    {
        if(fromObj==null){
            return;
        }
        this.PL_CODE = fromObj.PL_CODE;
        this.PL_NAME = fromObj.PL_NAME;
    }
    //----------------------------------------------------------------------
    // GETTER(S) & SETTER(S) FOR THE PRIMARY KEY 
    //----------------------------------------------------------------------
    /**
     * Set the "plId" field value
     * This field is mapped on the database column "PL_ID" ( type "INT", NotNull : true ) 
     * @param PL_ID
     */
	public void setPL_ID( Integer PL_ID )
    {
        this.PL_ID = PL_ID ;
    }
    /**
     * Get the "PL_ID" field value
     * This field is mapped on the database column "PL_ID" ( type "INT", NotNull : true ) 
     * @return the field value
     */
	public Integer getPL_ID()
    {
        return this.PL_ID;
    }

    //----------------------------------------------------------------------
    // GETTER(S) & SETTER(S) FOR DATA FIELDS
    //----------------------------------------------------------------------
    //--- DATABASE MAPPING : PL_CODE ( VARCHAR ) 
    /**
     * Set the "PL_CODE" field value
     * This field is mapped on the database column "PL_CODE" ( type "VARCHAR", NotNull : false ) 
     * @param PL_CODE
     */
    public void setPL_CODE( String PL_CODE )
    {
        this.PL_CODE = PL_CODE;
    }
    /**
     * Get the "plCode" field value
     * This field is mapped on the database column "PL_CODE" ( type "VARCHAR", NotNull : false ) 
     * @return the field value
     */
    public String getPL_CODE()
    {
        return this.PL_CODE;
    }

    //--- DATABASE MAPPING : PL_NAME ( VARCHAR ) 
    /**
     * Set the "PL_NAME" field value
     * This field is mapped on the database column "PL_NAME" ( type "VARCHAR", NotNull : false ) 
     * @param PL_NAME
     */
    public void setPL_NAME( String PL_NAME )
    {
        this.PL_NAME = PL_NAME;
    }
    /**
     * Get the "plName" field value
     * This field is mapped on the database column "PL_NAME" ( type "VARCHAR", NotNull : false ) 
     * @return the field value
     */
    public String getPL_NAME()
    {
        return this.PL_NAME;
    }



/*
 * CRUD functions
 */
    public static PhanLoaiThuoc load(String PL_ID) {
    	Connection connection = null;

		try {
			connection = DbHelper.getSql2o();
			String sql = "select * from phan_loai_thuoc where PL_ID=:PL_ID";
			logger.debug(sql);
			PhanLoaiThuoc obj = connection
					.createQuery(
							sql,
							true)
					.addParameter("PL_ID", PL_ID)
					.executeAndFetchFirst( PhanLoaiThuoc.class);
            logger.info(obj);
            logger.debug("Load "+obj);
            return obj;
		} catch (Exception t) {
            logger.error("Load Error: ID="+PL_ID +"\nException=\n"+ t);
			throw new RuntimeException("LOAD [PhanLoaiThuoc]. ERROR: "+t.getMessage(), t);
		}
    }
    
    public static PhanLoaiThuoc load(Integer PL_ID) {
    	Connection connection = null;

		try {
			connection = DbHelper.getSql2o();
			String sql = "select * from phan_loai_thuoc where PL_ID=:PL_ID";
			logger.debug(sql);
			PhanLoaiThuoc obj = connection
					.createQuery(
							sql,
							true)
					.addParameter("PL_ID", PL_ID)
					.executeAndFetchFirst( PhanLoaiThuoc.class);
            logger.info(obj);
            logger.debug("Load "+obj);
            return obj;
		} catch (Exception t) {
            logger.error("Load Error: ID="+PL_ID +"\nException=\n"+ t);
			throw new RuntimeException("LOAD [PhanLoaiThuoc]. ERROR: "+t.getMessage(), t);
		}
    }
    
    public String toString(){
    	String strData = "phan_loai_thuoc "
    			+" PL_ID = " + PL_ID                + "; PL_CODE = " + PL_CODE
                + "; PL_NAME = " + PL_NAME
    			;
    	return strData;
    }
    
    public void insert() {
		//
    	if( PL_ID !=null){
			// Update 
            update();
			return;
		}
        if(DbHelper.checkPhanQuyen(DbHelper.INSERT, "phan_loai_thuoc")==false){
            logger.info("YOU (" + DbHelper.getCurrentSessionUserName() + "), DON'T HAVE INSERT RIGHT for table phan_loai_thuoc");
            return;
        }
		Connection connection = null;

		try {
			connection = DbHelper.getSql2o();
            connection.setRollbackOnException(false);

            logger.info("Insert [phan_loai_thuoc] BEGIN: "+ this.toString());

            String sql = "insert into phan_loai_thuoc (PL_CODE, PL_NAME STS) values(:PL_CODE, :PL_NAME 0)";
                            			int createdId = connection
                            					.createQuery(
							sql,
							true)
                        .addParameter("PL_CODE", this.PL_CODE)
                            .addParameter("PL_NAME", this.PL_NAME)
    					.executeUpdate()
                                                .getKey(Integer.class);
                            					
            logger.info("Add OK: NewID="+createdId);
            logger.info("Add OK [phan_loai_thuoc] debug: "+toString());
            // Set KEYID PL_ID = newID
                            PL_ID = createdId;
                            			// Save log
            connection
					.createQuery(
							"insert into action_log(u_id,u_action,dbtable,actionid,fieldid) values(:u_id,:u_action,:dbtable,:actionid,:fieldid)",
							true)
					.addParameter("u_id", DbHelper.getCurrentSessionUserId())
					.addParameter("u_action", "New phan_loai_thuoc. ID="+PL_ID)
                    .addParameter("dbtable", "phan_loai_thuoc")
                    .addParameter("fieldid", PL_ID)
					.addParameter("actionid", 1)
					.executeUpdate();
            logger.info("Add [phan_loai_thuoc] OK: NewID="+createdId);
            logger.info("Add [phan_loai_thuoc] OK: Data="+this.toString());
		} catch (Exception t) {
            logger.error("Add [phan_loai_thuoc] Error: "+"\nException=\n"+ t);
			throw new RuntimeException("ADD [phan_loai_thuoc]. ERROR: "+t.getMessage(), t);
		}
	}
    
    public void update() {
		//
    	if(PL_ID==null){
            insert();
			return;
		}
        if(DbHelper.checkPhanQuyen(DbHelper.UPDATE, "phan_loai_thuoc")==false){
            logger.info("YOU (" + DbHelper.getCurrentSessionUserName() + "), DON'T HAVE UPDATE RIGHT for table phan_loai_thuoc");
            return;
        }
		Connection connection = null;

		try {
			connection = DbHelper.getSql2o();
            connection.setRollbackOnException(false);
            PhanLoaiThuoc temp = PhanLoaiThuoc.load(this.PL_ID);
            if(temp!=null){
            	logger.info("Begin Update [phan_loai_thuoc]: "+temp.toString());
            }
            logger.info("Update [phan_loai_thuoc] BEGIN: "+ this.toString());
			//
			String sql = "update phan_loai_thuoc set PL_CODE=:PL_CODE, PL_NAME=:PL_NAME where PL_ID=:PL_ID";
			connection
					.createQuery(
							sql,
							true)
                    .addParameter("PL_CODE", this.PL_CODE)
                    .addParameter("PL_NAME", this.PL_NAME)
					.addParameter("PL_ID", this.PL_ID)
					.executeUpdate();
            logger.info("Update [phan_loai_thuoc] OK: ID="+PL_ID);
            logger.info("End   Update [phan_loai_thuoc]: "+toString());
            connection
					.createQuery(
							"insert into action_log(u_id,u_action,dbtable,actionid,fieldid) values(:u_id,:u_action,:dbtable,:actionid,:fieldid)",
							true)
					.addParameter("u_id", DbHelper.getCurrentSessionUserId())
					.addParameter("u_action", "Update phan_loai_thuoc. ID="+PL_ID)
                    .addParameter("dbtable", "phan_loai_thuoc")
                    .addParameter("fieldid", PL_ID)
					.addParameter("actionid", 2)
					.executeUpdate();
		} catch (Exception t) {
			logger.error("Update [phan_loai_thuoc] Error: ID=["+PL_ID +"]."+"\nException=\n"+ t);
			throw new RuntimeException("Update [phan_loai_thuoc]. ERROR: "+t.getMessage(), t);
		}
	}
    
    
    public void delete() {
		//
    	if(PL_ID==null){
			return;
		}
        if(DbHelper.checkPhanQuyen(DbHelper.DELETE, "phan_loai_thuoc")==false){
            logger.info("YOU (" + DbHelper.getCurrentSessionUserName() + "), DON'T HAVE DELETE RIGHT for table phan_loai_thuoc");
            return;
        }
		Connection connection = null;

		try {
			connection = DbHelper.getSql2o();
            connection.setRollbackOnException(false);
            logger.error("Update Row [phan_loai_thuoc] BEGIN: ID=["+PL_ID +"]. " + this.toString());
			String sql = "update phan_loai_thuoc set STS=:STS where PL_ID=:PL_ID";
			connection
					.createQuery(
							sql,
							true)
					.addParameter("STS", DbHelper.DELETE_STATUS)
					.addParameter("PL_ID", this.PL_ID)
					.executeUpdate();
			logger.error("Delete [phan_loai_thuoc] OK: ID=["+PL_ID +"].");
            connection
					.createQuery(
							"insert into action_log(u_id,u_action,dbtable,actionid,fieldid) values(:u_id,:u_action,:dbtable,:actionid,:fieldid)",
							true)
					.addParameter("u_id", DbHelper.getCurrentSessionUserId())
					.addParameter("u_action", "Update phan_loai_thuoc. ID="+PL_ID)
                    .addParameter("dbtable", "phan_loai_thuoc")
                    .addParameter("fieldid", PL_ID)
					.addParameter("actionid", 3)
					.executeUpdate();
		} catch (Exception t) {
			logger.error("Delete [phan_loai_thuoc] Error: ID=["+PL_ID +"]."+"\nException=\n"+ t);
			throw new RuntimeException("Delete [phan_loai_thuoc]. ID=["+PL_ID + " ERROR: "+t.getMessage(), t);
		}
	}
    
	public void deleteRow() {
		//
    	if(PL_ID==null){
			return;
		}
        if(DbHelper.checkPhanQuyen(DbHelper.DELETE, "phan_loai_thuoc")==false){
            logger.info("YOU (" + DbHelper.getCurrentSessionUserName() + "), DON'T HAVE DELETE RIGHT for table phan_loai_thuoc");
            return;
        }
		Connection connection = null;

		try {
			connection = DbHelper.getSql2o();
            connection.setRollbackOnException(false);
            logger.error("Delete Row [phan_loai_thuoc] BEGIN: ID=["+PL_ID +"]. " + this.toString());
			String sql = "delete from phan_loai_thuoc where PL_ID=:PL_ID";
			connection
					.createQuery(
							sql,
							true)
					.addParameter("PL_ID", this.PL_ID)
					.executeUpdate();
			logger.error("Delete Row [phan_loai_thuoc] OK: ID=["+PL_ID +"].");
            connection
					.createQuery(
							"insert into action_log(u_id,u_action,dbtable,actionid,fieldid) values(:u_id,:u_action,:dbtable,:actionid,:fieldid)",
							true)
					.addParameter("u_id", DbHelper.getCurrentSessionUserId())
					.addParameter("u_action", "Update phan_loai_thuoc. ID="+PL_ID)
                    .addParameter("dbtable", "phan_loai_thuoc")
                    .addParameter("fieldid", PL_ID)
					.addParameter("actionid", 4)
					.executeUpdate();
		} catch (Exception t) {
			logger.error("Delete [phan_loai_thuoc] Error: ID=["+PL_ID +"]."+"\nException=\n"+ t);
			throw new RuntimeException("Delete [phan_loai_thuoc]. ID=["+PL_ID + " ERROR: "+t.getMessage(), t);
		}
	}
    
    public String getIndex(int idx){
        switch(idx){
            case 0:
                // java.lang.String    
                if(this.PL_CODE!=null)
                    return this.PL_CODE.toString();
                else
                    return "N/A";
            case 1:
                // java.lang.String    
                if(this.PL_NAME!=null)
                    return this.PL_NAME.toString();
                else
                    return "N/A";
            default:
        }
        return "";
    }
}