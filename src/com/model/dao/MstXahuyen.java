﻿/*
 * Java bean class for entity table mst_xahuyen 
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
 * Entity bean for table "mst_xahuyen"
 * 
 * @author Telosys Tools Generator
 *
 */
public class MstXahuyen implements Serializable
{
    static Logger logger = LogManager.getLogger(MstXahuyen.class.getName());
    private static final long serialVersionUID = 1L;

    public Integer    DC_ID ; // Primary Key

    public String     DC_VALUE ;
    public Integer    DC_RANK ;

    /**
     * Default constructor
     */
    public MstXahuyen()
    {
        super();
        // Set default value 
this.DC_VALUE = "";//String.format("%1000.1000s", "DC_VALUE").trim(); // data type = String     1000
this.DC_RANK = 0;                         // data type = Integer   
        // End Set default value 
    }
    
    //----------------------------------------------------------------------
    // GETTER(S) & SETTER(S) FOR THE PRIMARY KEY 
    //----------------------------------------------------------------------
    /**
     * Set the "dcId" field value
     * This field is mapped on the database column "DC_ID" ( type "INT", NotNull : true ) 
     * @param DC_ID
     */
	public void setDC_ID( Integer DC_ID )
    {
        this.DC_ID = DC_ID ;
    }
    /**
     * Get the "DC_ID" field value
     * This field is mapped on the database column "DC_ID" ( type "INT", NotNull : true ) 
     * @return the field value
     */
	public Integer getDC_ID()
    {
        return this.DC_ID;
    }

    //----------------------------------------------------------------------
    // GETTER(S) & SETTER(S) FOR DATA FIELDS
    //----------------------------------------------------------------------
    //--- DATABASE MAPPING : DC_VALUE ( VARCHAR ) 
    /**
     * Set the "DC_VALUE" field value
     * This field is mapped on the database column "DC_VALUE" ( type "VARCHAR", NotNull : true ) 
     * @param DC_VALUE
     */
    public void setDC_VALUE( String DC_VALUE )
    {
        this.DC_VALUE = DC_VALUE;
    }
    /**
     * Get the "dcValue" field value
     * This field is mapped on the database column "DC_VALUE" ( type "VARCHAR", NotNull : true ) 
     * @return the field value
     */
    public String getDC_VALUE()
    {
        return this.DC_VALUE;
    }

    //--- DATABASE MAPPING : DC_RANK ( INT ) 
    /**
     * Set the "DC_RANK" field value
     * This field is mapped on the database column "DC_RANK" ( type "INT", NotNull : true ) 
     * @param DC_RANK
     */
    public void setDC_RANK( Integer DC_RANK )
    {
        this.DC_RANK = DC_RANK;
    }
    /**
     * Get the "dcRank" field value
     * This field is mapped on the database column "DC_RANK" ( type "INT", NotNull : true ) 
     * @return the field value
     */
    public Integer getDC_RANK()
    {
        return this.DC_RANK;
    }



/*
 * CRUD functions
 */
    public static MstXahuyen load(String DC_ID) {
    	Connection connection = null;

		try {
			connection = DbHelper.getSql2o();
			String sql = "select * from mst_xahuyen where DC_ID=:DC_ID";
			logger.debug(sql);
			MstXahuyen obj = connection
					.createQuery(
							sql,
							true)
					.addParameter("DC_ID", DC_ID)
					.executeAndFetchFirst( MstXahuyen.class);
            logger.info(obj);
            logger.debug("Load "+obj);
            return obj;
		} catch (Exception t) {
            logger.error("Load Error: ID="+DC_ID +"\nException=\n"+ t);
			throw new RuntimeException("LOAD [MstXahuyen]. ERROR: "+t.getMessage(), t);
		}
    }
    
    public static MstXahuyen load(Integer DC_ID) {
    	Connection connection = null;

		try {
			connection = DbHelper.getSql2o();
			String sql = "select * from mst_xahuyen where DC_ID=:DC_ID";
			logger.debug(sql);
			MstXahuyen obj = connection
					.createQuery(
							sql,
							true)
					.addParameter("DC_ID", DC_ID)
					.executeAndFetchFirst( MstXahuyen.class);
            logger.info(obj);
            logger.debug("Load "+obj);
            return obj;
		} catch (Exception t) {
            logger.error("Load Error: ID="+DC_ID +"\nException=\n"+ t);
			throw new RuntimeException("LOAD [MstXahuyen]. ERROR: "+t.getMessage(), t);
		}
    }
    
    public String toString(){
    	String strData = "mst_xahuyen "
    			+" DC_ID = " + DC_ID                + "; DC_VALUE = " + DC_VALUE
                + "; DC_RANK = " + DC_RANK
    			;
    	return strData;
    }
    
    public void insert() {
		//
    	if( DC_ID !=null){
			// Update 
            update();
			return;
		}
        if(DbHelper.checkPhanQuyen(DbHelper.INSERT, "mst_xahuyen")==false){
            logger.info("YOU (" + DbHelper.getCurrentSessionUserName() + "), DON'T HAVE INSERT RIGHT for table mst_xahuyen");
            return;
        }
		Connection connection = null;

		try {
			connection = DbHelper.getSql2o();
            connection.setRollbackOnException(false);

            String sql = "insert into mst_xahuyen (DC_VALUE, DC_RANK STS) values(:DC_VALUE, :DC_RANK 0)";
                            			int createdId = connection
                            					.createQuery(
							sql,
							true)
                        .addParameter("DC_VALUE", this.DC_VALUE)
                            .addParameter("DC_RANK", this.DC_RANK)
    					.executeUpdate()
                                                .getKey(Integer.class);
                            					
            logger.info("Add OK: NewID="+createdId);
            logger.info("Add OK [mst_xahuyen] debug: "+toString());
            // Set KEYID DC_ID = newID
                            DC_ID = createdId;
                            			// Save log
            connection
					.createQuery(
							"insert into action_log(u_id,u_action,dbtable,actionid,fieldid) values(:u_id,:u_action,:dbtable,:actionid,:fieldid)",
							true)
					.addParameter("u_id", DbHelper.getCurrentSessionUserId())
					.addParameter("u_action", "New mst_xahuyen. ID="+DC_ID)
                    .addParameter("dbtable", "mst_xahuyen")
                    .addParameter("fieldid", DC_ID)
					.addParameter("actionid", 1)
					.executeUpdate();
            logger.info("Add [mst_xahuyen] OK: NewID="+createdId);
		} catch (Exception t) {
            logger.error("Add [mst_xahuyen] Error: "+"\nException=\n"+ t);
			throw new RuntimeException("ADD [mst_xahuyen]. ERROR: "+t.getMessage(), t);
		}
	}
    
    public void update() {
		//
    	if(DC_ID==null){
            insert();
			return;
		}
        if(DbHelper.checkPhanQuyen(DbHelper.UPDATE, "mst_xahuyen")==false){
            logger.info("YOU (" + DbHelper.getCurrentSessionUserName() + "), DON'T HAVE UPDATE RIGHT for table mst_xahuyen");
            return;
        }
		Connection connection = null;

		try {
			connection = DbHelper.getSql2o();
            connection.setRollbackOnException(false);
			String sql = "update mst_xahuyen set DC_VALUE=:DC_VALUE, DC_RANK=:DC_RANK where DC_ID=:DC_ID";
			connection
					.createQuery(
							sql,
							true)
                    .addParameter("DC_VALUE", this.DC_VALUE)
                    .addParameter("DC_RANK", this.DC_RANK)
					.addParameter("DC_ID", this.DC_ID)
					.executeUpdate();
            logger.info("Update [mst_xahuyen] OK: ID="+DC_ID);
            logger.info("Update [mst_xahuyen] debug: "+toString());
            connection
					.createQuery(
							"insert into action_log(u_id,u_action,dbtable,actionid,fieldid) values(:u_id,:u_action,:dbtable,:actionid,:fieldid)",
							true)
					.addParameter("u_id", DbHelper.getCurrentSessionUserId())
					.addParameter("u_action", "Update mst_xahuyen. ID="+DC_ID)
                    .addParameter("dbtable", "mst_xahuyen")
                    .addParameter("fieldid", DC_ID)
					.addParameter("actionid", 2)
					.executeUpdate();
		} catch (Exception t) {
			logger.error("Update [mst_xahuyen] Error: ID=["+DC_ID +"]."+"\nException=\n"+ t);
			throw new RuntimeException("Update [mst_xahuyen]. ERROR: "+t.getMessage(), t);
		}
	}
    
    
    public void delete() {
		//
    	if(DC_ID==null){
			return;
		}
        if(DbHelper.checkPhanQuyen(DbHelper.DELETE, "mst_xahuyen")==false){
            logger.info("YOU (" + DbHelper.getCurrentSessionUserName() + "), DON'T HAVE DELETE RIGHT for table mst_xahuyen");
            return;
        }
		Connection connection = null;

		try {
			connection = DbHelper.getSql2o();
            connection.setRollbackOnException(false);
			String sql = "update mst_xahuyen set STS=:STS where DC_ID=:DC_ID";
			connection
					.createQuery(
							sql,
							true)
					.addParameter("STS", DbHelper.DELETE_STATUS)
					.addParameter("DC_ID", this.DC_ID)
					.executeUpdate();
			logger.error("Delete [mst_xahuyen] OK: ID=["+DC_ID +"].");
            connection
					.createQuery(
							"insert into action_log(u_id,u_action,dbtable,actionid,fieldid) values(:u_id,:u_action,:dbtable,:actionid,:fieldid)",
							true)
					.addParameter("u_id", DbHelper.getCurrentSessionUserId())
					.addParameter("u_action", "Update mst_xahuyen. ID="+DC_ID)
                    .addParameter("dbtable", "mst_xahuyen")
                    .addParameter("fieldid", DC_ID)
					.addParameter("actionid", 3)
					.executeUpdate();
		} catch (Exception t) {
			logger.error("Delete [mst_xahuyen] Error: ID=["+DC_ID +"]."+"\nException=\n"+ t);
			throw new RuntimeException("Delete [mst_xahuyen]. ID=["+DC_ID + " ERROR: "+t.getMessage(), t);
		}
	}
    
	public void deleteRow() {
		//
    	if(DC_ID==null){
			return;
		}
        if(DbHelper.checkPhanQuyen(DbHelper.DELETE, "mst_xahuyen")==false){
            logger.info("YOU (" + DbHelper.getCurrentSessionUserName() + "), DON'T HAVE DELETE RIGHT for table mst_xahuyen");
            return;
        }
		Connection connection = null;

		try {
			connection = DbHelper.getSql2o();
            connection.setRollbackOnException(false);
			String sql = "delete from mst_xahuyen where DC_ID=:DC_ID";
			connection
					.createQuery(
							sql,
							true)
					.addParameter("DC_ID", this.DC_ID)
					.executeUpdate();
			logger.error("Delete Row [mst_xahuyen] OK: ID=["+DC_ID +"].");
            connection
					.createQuery(
							"insert into action_log(u_id,u_action,dbtable,actionid,fieldid) values(:u_id,:u_action,:dbtable,:actionid,:fieldid)",
							true)
					.addParameter("u_id", DbHelper.getCurrentSessionUserId())
					.addParameter("u_action", "Update mst_xahuyen. ID="+DC_ID)
                    .addParameter("dbtable", "mst_xahuyen")
                    .addParameter("fieldid", DC_ID)
					.addParameter("actionid", 4)
					.executeUpdate();
		} catch (Exception t) {
			logger.error("Delete [mst_xahuyen] Error: ID=["+DC_ID +"]."+"\nException=\n"+ t);
			throw new RuntimeException("Delete [mst_xahuyen]. ID=["+DC_ID + " ERROR: "+t.getMessage(), t);
		}
	}
    
    public String getIndex(int idx){
        switch(idx){
            case 0:
                // java.lang.String    
                if(this.DC_VALUE!=null)
                    return this.DC_VALUE.toString();
                else
                    return "N/A";
            case 1:
                // java.lang.Integer   
                if(this.DC_RANK!=null)
                    return this.DC_RANK.toString();
                else
                    return "N/A";
            default:
        }
        return "";
    }
}