﻿/*
 * Java bean class for entity table ma_dkbh 
 * Created on 14 Nov 2017 ( Date ISO 2017-11-14 - Time 14:56:09 )
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
 * Entity bean for table "ma_dkbh"
 * 
 * @author Telosys Tools Generator
 *
 */
public class MaDkbh implements Serializable
{
    static Logger logger = LogManager.getLogger(MaDkbh.class.getName());
    private static final long serialVersionUID = 1L;

    public String     MA_DKBD ; // Primary Key

    public String     TEN_DKBD ;

    /**
     * Default constructor
     */
    public MaDkbh()
    {
        super();
        // Set default value 
this.TEN_DKBD = "";//String.format("%500.500s", "TEN_DKBD").trim(); // data type = String     500
        // End Set default value 
    }
    
    //----------------------------------------------------------------------
    // GETTER(S) & SETTER(S) FOR THE PRIMARY KEY 
    //----------------------------------------------------------------------
    /**
     * Set the "maDkbd" field value
     * This field is mapped on the database column "MA_DKBD" ( type "VARCHAR", NotNull : true ) 
     * @param MA_DKBD
     */
	public void setMA_DKBD( String MA_DKBD )
    {
        this.MA_DKBD = MA_DKBD ;
    }
    /**
     * Get the "MA_DKBD" field value
     * This field is mapped on the database column "MA_DKBD" ( type "VARCHAR", NotNull : true ) 
     * @return the field value
     */
	public String getMA_DKBD()
    {
        return this.MA_DKBD;
    }

    //----------------------------------------------------------------------
    // GETTER(S) & SETTER(S) FOR DATA FIELDS
    //----------------------------------------------------------------------
    //--- DATABASE MAPPING : TEN_DKBD ( VARCHAR ) 
    /**
     * Set the "TEN_DKBD" field value
     * This field is mapped on the database column "TEN_DKBD" ( type "VARCHAR", NotNull : true ) 
     * @param TEN_DKBD
     */
    public void setTEN_DKBD( String TEN_DKBD )
    {
        this.TEN_DKBD = TEN_DKBD;
    }
    /**
     * Get the "tenDkbd" field value
     * This field is mapped on the database column "TEN_DKBD" ( type "VARCHAR", NotNull : true ) 
     * @return the field value
     */
    public String getTEN_DKBD()
    {
        return this.TEN_DKBD;
    }



/*
 * CRUD functions
 */
    public static MaDkbh load(String MA_DKBD) {
    	Connection connection = null;

		try {
			connection = DbHelper.getSql2o();
			String sql = "select * from ma_dkbh where MA_DKBD=:MA_DKBD";
			logger.debug(sql);
			MaDkbh obj = connection
					.createQuery(
							sql,
							true)
					.addParameter("MA_DKBD", MA_DKBD)
					.executeAndFetchFirst( MaDkbh.class);
            logger.info(obj);
            logger.debug("Load "+obj);
            return obj;
		} catch (Exception t) {
            logger.error("Load Error: ID="+MA_DKBD +"\nException=\n"+ t);
			throw new RuntimeException("LOAD [MaDkbh]. ERROR: "+t.getMessage(), t);
		}
    }
    
    public static MaDkbh load(Integer MA_DKBD) {
    	Connection connection = null;

		try {
			connection = DbHelper.getSql2o();
			String sql = "select * from ma_dkbh where MA_DKBD=:MA_DKBD";
			logger.debug(sql);
			MaDkbh obj = connection
					.createQuery(
							sql,
							true)
					.addParameter("MA_DKBD", MA_DKBD)
					.executeAndFetchFirst( MaDkbh.class);
            logger.info(obj);
            logger.debug("Load "+obj);
            return obj;
		} catch (Exception t) {
            logger.error("Load Error: ID="+MA_DKBD +"\nException=\n"+ t);
			throw new RuntimeException("LOAD [MaDkbh]. ERROR: "+t.getMessage(), t);
		}
    }
    
    public String toString(){
    	String strData = "ma_dkbh "
    			+" MA_DKBD = " + MA_DKBD                + "; TEN_DKBD = " + TEN_DKBD
    			;
    	return strData;
    }
    
    public void insert() {
		//
    	if( MA_DKBD !=null){
			// Update 
            update();
			return;
		}
        if(DbHelper.checkPhanQuyen(DbHelper.INSERT, "ma_dkbh")==false){
            logger.info("YOU (" + DbHelper.getCurrentSessionUserName() + "), DON'T HAVE INSERT RIGHT for table ma_dkbh");
            return;
        }
		Connection connection = null;

		try {
			connection = DbHelper.getSql2o();
            connection.setRollbackOnException(false);

            logger.info("Insert [ma_dkbh] BEGIN: "+ this.toString());

            String sql = "insert into ma_dkbh (TEN_DKBD STS) values(:TEN_DKBD 0)";
                            			String createdId = connection
                            					.createQuery(
							sql,
							true)
                        .addParameter("TEN_DKBD", this.TEN_DKBD)
    					.executeUpdate()
                                                .getKey(String.class);
                            					
            logger.info("Add OK: NewID="+createdId);
            logger.info("Add OK [ma_dkbh] debug: "+toString());
            // Set KEYID MA_DKBD = newID
                            MA_DKBD = ""+createdId;
                            			// Save log
            connection
					.createQuery(
							"insert into action_log(u_id,u_action,dbtable,actionid,fieldid) values(:u_id,:u_action,:dbtable,:actionid,:fieldid)",
							true)
					.addParameter("u_id", DbHelper.getCurrentSessionUserId())
					.addParameter("u_action", "New ma_dkbh. ID="+MA_DKBD)
                    .addParameter("dbtable", "ma_dkbh")
                    .addParameter("fieldid", MA_DKBD)
					.addParameter("actionid", 1)
					.executeUpdate();
            logger.info("Add [ma_dkbh] OK: NewID="+createdId);
            logger.info("Add [ma_dkbh] OK: Data="+this.toString());
		} catch (Exception t) {
            logger.error("Add [ma_dkbh] Error: "+"\nException=\n"+ t);
			throw new RuntimeException("ADD [ma_dkbh]. ERROR: "+t.getMessage(), t);
		}
	}
    
    public void update() {
		//
    	if(MA_DKBD==null){
            insert();
			return;
		}
        if(DbHelper.checkPhanQuyen(DbHelper.UPDATE, "ma_dkbh")==false){
            logger.info("YOU (" + DbHelper.getCurrentSessionUserName() + "), DON'T HAVE UPDATE RIGHT for table ma_dkbh");
            return;
        }
		Connection connection = null;

		try {
			connection = DbHelper.getSql2o();
            connection.setRollbackOnException(false);
            MaDkbh temp = MaDkbh.load(this.MA_DKBD);
            if(temp!=null){
            	logger.info("Begin Update [ma_dkbh]: "+temp.toString());
            }
            logger.info("Update [ma_dkbh] BEGIN: "+ this.toString());
			//
			String sql = "update ma_dkbh set TEN_DKBD=:TEN_DKBD where MA_DKBD=:MA_DKBD";
			connection
					.createQuery(
							sql,
							true)
                    .addParameter("TEN_DKBD", this.TEN_DKBD)
					.addParameter("MA_DKBD", this.MA_DKBD)
					.executeUpdate();
            logger.info("Update [ma_dkbh] OK: ID="+MA_DKBD);
            logger.info("End   Update [ma_dkbh]: "+toString());
            connection
					.createQuery(
							"insert into action_log(u_id,u_action,dbtable,actionid,fieldid) values(:u_id,:u_action,:dbtable,:actionid,:fieldid)",
							true)
					.addParameter("u_id", DbHelper.getCurrentSessionUserId())
					.addParameter("u_action", "Update ma_dkbh. ID="+MA_DKBD)
                    .addParameter("dbtable", "ma_dkbh")
                    .addParameter("fieldid", MA_DKBD)
					.addParameter("actionid", 2)
					.executeUpdate();
		} catch (Exception t) {
			logger.error("Update [ma_dkbh] Error: ID=["+MA_DKBD +"]."+"\nException=\n"+ t);
			throw new RuntimeException("Update [ma_dkbh]. ERROR: "+t.getMessage(), t);
		}
	}
    
    
    public void delete() {
		//
    	if(MA_DKBD==null){
			return;
		}
        if(DbHelper.checkPhanQuyen(DbHelper.DELETE, "ma_dkbh")==false){
            logger.info("YOU (" + DbHelper.getCurrentSessionUserName() + "), DON'T HAVE DELETE RIGHT for table ma_dkbh");
            return;
        }
		Connection connection = null;

		try {
			connection = DbHelper.getSql2o();
            connection.setRollbackOnException(false);
            logger.error("Update Row [ma_dkbh] BEGIN: ID=["+MA_DKBD +"]. " + this.toString());
			String sql = "update ma_dkbh set STS=:STS where MA_DKBD=:MA_DKBD";
			connection
					.createQuery(
							sql,
							true)
					.addParameter("STS", DbHelper.DELETE_STATUS)
					.addParameter("MA_DKBD", this.MA_DKBD)
					.executeUpdate();
			logger.error("Delete [ma_dkbh] OK: ID=["+MA_DKBD +"].");
            connection
					.createQuery(
							"insert into action_log(u_id,u_action,dbtable,actionid,fieldid) values(:u_id,:u_action,:dbtable,:actionid,:fieldid)",
							true)
					.addParameter("u_id", DbHelper.getCurrentSessionUserId())
					.addParameter("u_action", "Update ma_dkbh. ID="+MA_DKBD)
                    .addParameter("dbtable", "ma_dkbh")
                    .addParameter("fieldid", MA_DKBD)
					.addParameter("actionid", 3)
					.executeUpdate();
		} catch (Exception t) {
			logger.error("Delete [ma_dkbh] Error: ID=["+MA_DKBD +"]."+"\nException=\n"+ t);
			throw new RuntimeException("Delete [ma_dkbh]. ID=["+MA_DKBD + " ERROR: "+t.getMessage(), t);
		}
	}
    
	public void deleteRow() {
		//
    	if(MA_DKBD==null){
			return;
		}
        if(DbHelper.checkPhanQuyen(DbHelper.DELETE, "ma_dkbh")==false){
            logger.info("YOU (" + DbHelper.getCurrentSessionUserName() + "), DON'T HAVE DELETE RIGHT for table ma_dkbh");
            return;
        }
		Connection connection = null;

		try {
			connection = DbHelper.getSql2o();
            connection.setRollbackOnException(false);
            logger.error("Delete Row [ma_dkbh] BEGIN: ID=["+MA_DKBD +"]. " + this.toString());
			String sql = "delete from ma_dkbh where MA_DKBD=:MA_DKBD";
			connection
					.createQuery(
							sql,
							true)
					.addParameter("MA_DKBD", this.MA_DKBD)
					.executeUpdate();
			logger.error("Delete Row [ma_dkbh] OK: ID=["+MA_DKBD +"].");
            connection
					.createQuery(
							"insert into action_log(u_id,u_action,dbtable,actionid,fieldid) values(:u_id,:u_action,:dbtable,:actionid,:fieldid)",
							true)
					.addParameter("u_id", DbHelper.getCurrentSessionUserId())
					.addParameter("u_action", "Update ma_dkbh. ID="+MA_DKBD)
                    .addParameter("dbtable", "ma_dkbh")
                    .addParameter("fieldid", MA_DKBD)
					.addParameter("actionid", 4)
					.executeUpdate();
		} catch (Exception t) {
			logger.error("Delete [ma_dkbh] Error: ID=["+MA_DKBD +"]."+"\nException=\n"+ t);
			throw new RuntimeException("Delete [ma_dkbh]. ID=["+MA_DKBD + " ERROR: "+t.getMessage(), t);
		}
	}
    
    public String getIndex(int idx){
        switch(idx){
            case 0:
                // java.lang.String    
                if(this.TEN_DKBD!=null)
                    return this.TEN_DKBD.toString();
                else
                    return "N/A";
            default:
        }
        return "";
    }
}