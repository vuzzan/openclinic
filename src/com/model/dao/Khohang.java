﻿/*
 * Java bean class for entity table khohang 
 * Created on 30 Oct 2017 ( Date ISO 2017-10-30 - Time 08:47:47 )
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
 * Entity bean for table "khohang"
 * 
 * @author Telosys Tools Generator
 *
 */
public class Khohang implements Serializable
{
    static Logger logger = LogManager.getLogger(Khohang.class.getName());
    private static final long serialVersionUID = 1L;

    public Integer    KHO_ID ; // Primary Key

    public String     KHO_NAME ;
    public Integer    STS ;

    /**
     * Default constructor
     */
    public Khohang()
    {
        super();
        // Set default value 
this.KHO_NAME = "";//String.format("%50.50s", "KHO_NAME").trim(); // data type = String     50
this.STS = 0;                         // data type = Integer   
        // End Set default value 
    }
    
    //----------------------------------------------------------------------
    // GETTER(S) & SETTER(S) FOR THE PRIMARY KEY 
    //----------------------------------------------------------------------
    /**
     * Set the "khoId" field value
     * This field is mapped on the database column "KHO_ID" ( type "INT", NotNull : true ) 
     * @param KHO_ID
     */
	public void setKHO_ID( Integer KHO_ID )
    {
        this.KHO_ID = KHO_ID ;
    }
    /**
     * Get the "KHO_ID" field value
     * This field is mapped on the database column "KHO_ID" ( type "INT", NotNull : true ) 
     * @return the field value
     */
	public Integer getKHO_ID()
    {
        return this.KHO_ID;
    }

    //----------------------------------------------------------------------
    // GETTER(S) & SETTER(S) FOR DATA FIELDS
    //----------------------------------------------------------------------
    //--- DATABASE MAPPING : KHO_NAME ( VARCHAR ) 
    /**
     * Set the "KHO_NAME" field value
     * This field is mapped on the database column "KHO_NAME" ( type "VARCHAR", NotNull : false ) 
     * @param KHO_NAME
     */
    public void setKHO_NAME( String KHO_NAME )
    {
        this.KHO_NAME = KHO_NAME;
    }
    /**
     * Get the "khoName" field value
     * This field is mapped on the database column "KHO_NAME" ( type "VARCHAR", NotNull : false ) 
     * @return the field value
     */
    public String getKHO_NAME()
    {
        return this.KHO_NAME;
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
    public static Khohang load(String KHO_ID) {
    	Connection connection = null;

		try {
			connection = DbHelper.getSql2o();
			String sql = "select * from khohang where KHO_ID=:KHO_ID";
			logger.debug(sql);
			Khohang obj = connection
					.createQuery(
							sql,
							true)
					.addParameter("KHO_ID", KHO_ID)
					.executeAndFetchFirst( Khohang.class);
            logger.info(obj);
            logger.debug("Load "+obj);
            return obj;
		} catch (Exception t) {
            logger.error("Load Error: ID="+KHO_ID +"\nException=\n"+ t);
			throw new RuntimeException("LOAD [Khohang]. ERROR: "+t.getMessage(), t);
		}
    }
    
    public static Khohang load(Integer KHO_ID) {
    	Connection connection = null;

		try {
			connection = DbHelper.getSql2o();
			String sql = "select * from khohang where KHO_ID=:KHO_ID";
			logger.debug(sql);
			Khohang obj = connection
					.createQuery(
							sql,
							true)
					.addParameter("KHO_ID", KHO_ID)
					.executeAndFetchFirst( Khohang.class);
            logger.info(obj);
            logger.debug("Load "+obj);
            return obj;
		} catch (Exception t) {
            logger.error("Load Error: ID="+KHO_ID +"\nException=\n"+ t);
			throw new RuntimeException("LOAD [Khohang]. ERROR: "+t.getMessage(), t);
		}
    }
    
    public String toString(){
    	String strData = "khohang "
    			+" KHO_ID = " + KHO_ID                + "; KHO_NAME = " + KHO_NAME
                + "; STS = " + STS
    			;
    	return strData;
    }
    
    public void insert() {
		//
    	if( KHO_ID !=null){
			// Update 
            update();
			return;
		}
        if(DbHelper.checkPhanQuyen(DbHelper.INSERT, "khohang")==false){
            logger.info("YOU (" + DbHelper.getCurrentSessionUserName() + "), DON'T HAVE INSERT RIGHT for table khohang");
            return;
        }
		Connection connection = null;

		try {
			connection = DbHelper.getSql2o();
            connection.setRollbackOnException(false);

            String sql = "insert into khohang (KHO_NAME,  STS) values(:KHO_NAME,  0)";
                            			int createdId = connection
                            					.createQuery(
							sql,
							true)
                        .addParameter("KHO_NAME", this.KHO_NAME)
        					.executeUpdate()
                                                .getKey(Integer.class);
                            					
            logger.info("Add OK: NewID="+createdId);
            logger.info("Add OK [khohang] debug: "+toString());
            // Set KEYID KHO_ID = newID
                            KHO_ID = createdId;
                            			// Save log
            connection
					.createQuery(
							"insert into action_log(u_id,u_action,dbtable,actionid,fieldid) values(:u_id,:u_action,:dbtable,:actionid,:fieldid)",
							true)
					.addParameter("u_id", DbHelper.getCurrentSessionUserId())
					.addParameter("u_action", "New khohang. ID="+KHO_ID)
                    .addParameter("dbtable", "khohang")
                    .addParameter("fieldid", KHO_ID)
					.addParameter("actionid", 1)
					.executeUpdate();
            logger.info("Add [khohang] OK: NewID="+createdId);
		} catch (Exception t) {
            logger.error("Add [khohang] Error: "+"\nException=\n"+ t);
			throw new RuntimeException("ADD [khohang]. ERROR: "+t.getMessage(), t);
		}
	}
    
    public void update() {
		//
    	if(KHO_ID==null){
            insert();
			return;
		}
        if(DbHelper.checkPhanQuyen(DbHelper.UPDATE, "khohang")==false){
            logger.info("YOU (" + DbHelper.getCurrentSessionUserName() + "), DON'T HAVE UPDATE RIGHT for table khohang");
            return;
        }
		Connection connection = null;

		try {
			connection = DbHelper.getSql2o();
            connection.setRollbackOnException(false);
			String sql = "update khohang set KHO_NAME=:KHO_NAME, STS=:STS where KHO_ID=:KHO_ID";
			connection
					.createQuery(
							sql,
							true)
                    .addParameter("KHO_NAME", this.KHO_NAME)
                    .addParameter("STS", this.STS)
					.addParameter("KHO_ID", this.KHO_ID)
					.executeUpdate();
            logger.info("Update [khohang] OK: ID="+KHO_ID);
            logger.info("Update [khohang] debug: "+toString());
            connection
					.createQuery(
							"insert into action_log(u_id,u_action,dbtable,actionid,fieldid) values(:u_id,:u_action,:dbtable,:actionid,:fieldid)",
							true)
					.addParameter("u_id", DbHelper.getCurrentSessionUserId())
					.addParameter("u_action", "Update khohang. ID="+KHO_ID)
                    .addParameter("dbtable", "khohang")
                    .addParameter("fieldid", KHO_ID)
					.addParameter("actionid", 2)
					.executeUpdate();
		} catch (Exception t) {
			logger.error("Update [khohang] Error: ID=["+KHO_ID +"]."+"\nException=\n"+ t);
			throw new RuntimeException("Update [khohang]. ERROR: "+t.getMessage(), t);
		}
	}
    
    
    public void delete() {
		//
    	if(KHO_ID==null){
			return;
		}
        if(DbHelper.checkPhanQuyen(DbHelper.DELETE, "khohang")==false){
            logger.info("YOU (" + DbHelper.getCurrentSessionUserName() + "), DON'T HAVE DELETE RIGHT for table khohang");
            return;
        }
		Connection connection = null;

		try {
			connection = DbHelper.getSql2o();
            connection.setRollbackOnException(false);
			String sql = "update khohang set STS=:STS where KHO_ID=:KHO_ID";
			connection
					.createQuery(
							sql,
							true)
					.addParameter("STS", DbHelper.DELETE_STATUS)
					.addParameter("KHO_ID", this.KHO_ID)
					.executeUpdate();
			logger.error("Delete [khohang] OK: ID=["+KHO_ID +"].");
            connection
					.createQuery(
							"insert into action_log(u_id,u_action,dbtable,actionid,fieldid) values(:u_id,:u_action,:dbtable,:actionid,:fieldid)",
							true)
					.addParameter("u_id", DbHelper.getCurrentSessionUserId())
					.addParameter("u_action", "Update khohang. ID="+KHO_ID)
                    .addParameter("dbtable", "khohang")
                    .addParameter("fieldid", KHO_ID)
					.addParameter("actionid", 3)
					.executeUpdate();
		} catch (Exception t) {
			logger.error("Delete [khohang] Error: ID=["+KHO_ID +"]."+"\nException=\n"+ t);
			throw new RuntimeException("Delete [khohang]. ID=["+KHO_ID + " ERROR: "+t.getMessage(), t);
		}
	}
    
	public void deleteRow() {
		//
    	if(KHO_ID==null){
			return;
		}
        if(DbHelper.checkPhanQuyen(DbHelper.DELETE, "khohang")==false){
            logger.info("YOU (" + DbHelper.getCurrentSessionUserName() + "), DON'T HAVE DELETE RIGHT for table khohang");
            return;
        }
		Connection connection = null;

		try {
			connection = DbHelper.getSql2o();
            connection.setRollbackOnException(false);
			String sql = "delete from khohang where KHO_ID=:KHO_ID";
			connection
					.createQuery(
							sql,
							true)
					.addParameter("KHO_ID", this.KHO_ID)
					.executeUpdate();
			logger.error("Delete Row [khohang] OK: ID=["+KHO_ID +"].");
            connection
					.createQuery(
							"insert into action_log(u_id,u_action,dbtable,actionid,fieldid) values(:u_id,:u_action,:dbtable,:actionid,:fieldid)",
							true)
					.addParameter("u_id", DbHelper.getCurrentSessionUserId())
					.addParameter("u_action", "Update khohang. ID="+KHO_ID)
                    .addParameter("dbtable", "khohang")
                    .addParameter("fieldid", KHO_ID)
					.addParameter("actionid", 4)
					.executeUpdate();
		} catch (Exception t) {
			logger.error("Delete [khohang] Error: ID=["+KHO_ID +"]."+"\nException=\n"+ t);
			throw new RuntimeException("Delete [khohang]. ID=["+KHO_ID + " ERROR: "+t.getMessage(), t);
		}
	}
    
    public String getIndex(int idx){
        switch(idx){
            case 0:
                // java.lang.String    
                if(this.KHO_NAME!=null)
                    return this.KHO_NAME.toString();
                else
                    return "N/A";
            case 1:
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