﻿/*
 * Java bean class for entity table mabenh 
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
 * Entity bean for table "mabenh"
 * 
 * @author Telosys Tools Generator
 *
 */
public class Mabenh implements Serializable
{
    static Logger logger = LogManager.getLogger(Mabenh.class.getName());
    private static final long serialVersionUID = 1L;

    public String     MABENH_ID ; // Primary Key

    public String     MABENH_NAME ;
    public Integer    MABENH_RANK ;

    /**
     * Default constructor
     */
    public Mabenh()
    {
        super();
        // Set default value 
this.MABENH_NAME = "";//String.format("%2000.2000s", "MABENH_NAME").trim(); // data type = String     2000
this.MABENH_RANK = 0;                         // data type = Integer   
        // End Set default value 
    }
    
    //----------------------------------------------------------------------
    // GETTER(S) & SETTER(S) FOR THE PRIMARY KEY 
    //----------------------------------------------------------------------
    /**
     * Set the "mabenhId" field value
     * This field is mapped on the database column "MABENH_ID" ( type "VARCHAR", NotNull : true ) 
     * @param MABENH_ID
     */
	public void setMABENH_ID( String MABENH_ID )
    {
        this.MABENH_ID = MABENH_ID ;
    }
    /**
     * Get the "MABENH_ID" field value
     * This field is mapped on the database column "MABENH_ID" ( type "VARCHAR", NotNull : true ) 
     * @return the field value
     */
	public String getMABENH_ID()
    {
        return this.MABENH_ID;
    }

    //----------------------------------------------------------------------
    // GETTER(S) & SETTER(S) FOR DATA FIELDS
    //----------------------------------------------------------------------
    //--- DATABASE MAPPING : MABENH_NAME ( VARCHAR ) 
    /**
     * Set the "MABENH_NAME" field value
     * This field is mapped on the database column "MABENH_NAME" ( type "VARCHAR", NotNull : true ) 
     * @param MABENH_NAME
     */
    public void setMABENH_NAME( String MABENH_NAME )
    {
        this.MABENH_NAME = MABENH_NAME;
    }
    /**
     * Get the "mabenhName" field value
     * This field is mapped on the database column "MABENH_NAME" ( type "VARCHAR", NotNull : true ) 
     * @return the field value
     */
    public String getMABENH_NAME()
    {
        return this.MABENH_NAME;
    }

    //--- DATABASE MAPPING : MABENH_RANK ( INT ) 
    /**
     * Set the "MABENH_RANK" field value
     * This field is mapped on the database column "MABENH_RANK" ( type "INT", NotNull : true ) 
     * @param MABENH_RANK
     */
    public void setMABENH_RANK( Integer MABENH_RANK )
    {
        this.MABENH_RANK = MABENH_RANK;
    }
    /**
     * Get the "mabenhRank" field value
     * This field is mapped on the database column "MABENH_RANK" ( type "INT", NotNull : true ) 
     * @return the field value
     */
    public Integer getMABENH_RANK()
    {
        return this.MABENH_RANK;
    }



/*
 * CRUD functions
 */
    public static Mabenh load(String MABENH_ID) {
    	Connection connection = null;

		try {
			connection = DbHelper.getSql2o();
			String sql = "select * from mabenh where MABENH_ID=:MABENH_ID";
			logger.debug(sql);
			Mabenh obj = connection
					.createQuery(
							sql,
							true)
					.addParameter("MABENH_ID", MABENH_ID)
					.executeAndFetchFirst( Mabenh.class);
            logger.info(obj);
            logger.debug("Load "+obj);
            return obj;
		} catch (Exception t) {
            logger.error("Load Error: ID="+MABENH_ID +"\nException=\n"+ t);
			throw new RuntimeException("LOAD [Mabenh]. ERROR: "+t.getMessage(), t);
		}
    }
    
    public static Mabenh load(Integer MABENH_ID) {
    	Connection connection = null;

		try {
			connection = DbHelper.getSql2o();
			String sql = "select * from mabenh where MABENH_ID=:MABENH_ID";
			logger.debug(sql);
			Mabenh obj = connection
					.createQuery(
							sql,
							true)
					.addParameter("MABENH_ID", MABENH_ID)
					.executeAndFetchFirst( Mabenh.class);
            logger.info(obj);
            logger.debug("Load "+obj);
            return obj;
		} catch (Exception t) {
            logger.error("Load Error: ID="+MABENH_ID +"\nException=\n"+ t);
			throw new RuntimeException("LOAD [Mabenh]. ERROR: "+t.getMessage(), t);
		}
    }
    
    public String toString(){
    	String strData = "mabenh "
    			+" MABENH_ID = " + MABENH_ID                + "; MABENH_NAME = " + MABENH_NAME
                + "; MABENH_RANK = " + MABENH_RANK
    			;
    	return strData;
    }
    
    public void insert() {
		//
    	if( MABENH_ID !=null){
			// Update 
            update();
			return;
		}
        if(DbHelper.checkPhanQuyen(DbHelper.INSERT, "mabenh")==false){
            logger.info("YOU (" + DbHelper.getCurrentSessionUserName() + "), DON'T HAVE INSERT RIGHT for table mabenh");
            return;
        }
		Connection connection = null;

		try {
			connection = DbHelper.getSql2o();
            connection.setRollbackOnException(false);

            String sql = "insert into mabenh (MABENH_NAME, MABENH_RANK STS) values(:MABENH_NAME, :MABENH_RANK 0)";
                            			String createdId = connection
                            					.createQuery(
							sql,
							true)
                        .addParameter("MABENH_NAME", this.MABENH_NAME)
                            .addParameter("MABENH_RANK", this.MABENH_RANK)
    					.executeUpdate()
                                                .getKey(String.class);
                            					
            logger.info("Add OK: NewID="+createdId);
            logger.info("Add OK [mabenh] debug: "+toString());
            // Set KEYID MABENH_ID = newID
                            MABENH_ID = ""+createdId;
                            			// Save log
            connection
					.createQuery(
							"insert into action_log(u_id,u_action,dbtable,actionid,fieldid) values(:u_id,:u_action,:dbtable,:actionid,:fieldid)",
							true)
					.addParameter("u_id", DbHelper.getCurrentSessionUserId())
					.addParameter("u_action", "New mabenh. ID="+MABENH_ID)
                    .addParameter("dbtable", "mabenh")
                    .addParameter("fieldid", MABENH_ID)
					.addParameter("actionid", 1)
					.executeUpdate();
            logger.info("Add [mabenh] OK: NewID="+createdId);
		} catch (Exception t) {
            logger.error("Add [mabenh] Error: "+"\nException=\n"+ t);
			throw new RuntimeException("ADD [mabenh]. ERROR: "+t.getMessage(), t);
		}
	}
    
    public void update() {
		//
    	if(MABENH_ID==null){
            insert();
			return;
		}
        if(DbHelper.checkPhanQuyen(DbHelper.UPDATE, "mabenh")==false){
            logger.info("YOU (" + DbHelper.getCurrentSessionUserName() + "), DON'T HAVE UPDATE RIGHT for table mabenh");
            return;
        }
		Connection connection = null;

		try {
			connection = DbHelper.getSql2o();
            connection.setRollbackOnException(false);
			String sql = "update mabenh set MABENH_NAME=:MABENH_NAME, MABENH_RANK=:MABENH_RANK where MABENH_ID=:MABENH_ID";
			connection
					.createQuery(
							sql,
							true)
                    .addParameter("MABENH_NAME", this.MABENH_NAME)
                    .addParameter("MABENH_RANK", this.MABENH_RANK)
					.addParameter("MABENH_ID", this.MABENH_ID)
					.executeUpdate();
            logger.info("Update [mabenh] OK: ID="+MABENH_ID);
            logger.info("Update [mabenh] debug: "+toString());
            connection
					.createQuery(
							"insert into action_log(u_id,u_action,dbtable,actionid,fieldid) values(:u_id,:u_action,:dbtable,:actionid,:fieldid)",
							true)
					.addParameter("u_id", DbHelper.getCurrentSessionUserId())
					.addParameter("u_action", "Update mabenh. ID="+MABENH_ID)
                    .addParameter("dbtable", "mabenh")
                    .addParameter("fieldid", MABENH_ID)
					.addParameter("actionid", 2)
					.executeUpdate();
		} catch (Exception t) {
			logger.error("Update [mabenh] Error: ID=["+MABENH_ID +"]."+"\nException=\n"+ t);
			throw new RuntimeException("Update [mabenh]. ERROR: "+t.getMessage(), t);
		}
	}
    
    
    public void delete() {
		//
    	if(MABENH_ID==null){
			return;
		}
        if(DbHelper.checkPhanQuyen(DbHelper.DELETE, "mabenh")==false){
            logger.info("YOU (" + DbHelper.getCurrentSessionUserName() + "), DON'T HAVE DELETE RIGHT for table mabenh");
            return;
        }
		Connection connection = null;

		try {
			connection = DbHelper.getSql2o();
            connection.setRollbackOnException(false);
			String sql = "update mabenh set STS=:STS where MABENH_ID=:MABENH_ID";
			connection
					.createQuery(
							sql,
							true)
					.addParameter("STS", DbHelper.DELETE_STATUS)
					.addParameter("MABENH_ID", this.MABENH_ID)
					.executeUpdate();
			logger.error("Delete [mabenh] OK: ID=["+MABENH_ID +"].");
            connection
					.createQuery(
							"insert into action_log(u_id,u_action,dbtable,actionid,fieldid) values(:u_id,:u_action,:dbtable,:actionid,:fieldid)",
							true)
					.addParameter("u_id", DbHelper.getCurrentSessionUserId())
					.addParameter("u_action", "Update mabenh. ID="+MABENH_ID)
                    .addParameter("dbtable", "mabenh")
                    .addParameter("fieldid", MABENH_ID)
					.addParameter("actionid", 3)
					.executeUpdate();
		} catch (Exception t) {
			logger.error("Delete [mabenh] Error: ID=["+MABENH_ID +"]."+"\nException=\n"+ t);
			throw new RuntimeException("Delete [mabenh]. ID=["+MABENH_ID + " ERROR: "+t.getMessage(), t);
		}
	}
    
    public String getIndex(int idx){
        switch(idx){
            case 0:
                // java.lang.String    
                if(this.MABENH_NAME!=null)
                    return this.MABENH_NAME.toString();
                else
                    return "N/A";
            case 1:
                // java.lang.Integer   
                if(this.MABENH_RANK!=null)
                    return this.MABENH_RANK.toString();
                else
                    return "N/A";
            default:
        }
        return "";
    }
}