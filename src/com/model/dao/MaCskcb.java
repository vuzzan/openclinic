﻿/*
 * Java bean class for entity table ma_cskcb 
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
 * Entity bean for table "ma_cskcb"
 * 
 * @author Telosys Tools Generator
 *
 */
public class MaCskcb implements Serializable
{
    static Logger logger = LogManager.getLogger(MaCskcb.class.getName());
    private static final long serialVersionUID = 1L;

    public String     MA_CODE ; // Primary Key

    public String     MA_NAME ;
    public String     MA_TUYEN ;
    public Integer    MA_RANK ;

    /**
     * Default constructor
     */
    public MaCskcb()
    {
        super();
        // Set default value 
this.MA_NAME = "";//String.format("%255.255s", "MA_NAME").trim(); // data type = String     255
this.MA_TUYEN = "";//String.format("%128.128s", "MA_TUYEN").trim(); // data type = String     128
this.MA_RANK = 0;                         // data type = Integer   
        // End Set default value 
    }
    
    //----------------------------------------------------------------------
    // GETTER(S) & SETTER(S) FOR THE PRIMARY KEY 
    //----------------------------------------------------------------------
    /**
     * Set the "maCode" field value
     * This field is mapped on the database column "MA_CODE" ( type "VARCHAR", NotNull : true ) 
     * @param MA_CODE
     */
	public void setMA_CODE( String MA_CODE )
    {
        this.MA_CODE = MA_CODE ;
    }
    /**
     * Get the "MA_CODE" field value
     * This field is mapped on the database column "MA_CODE" ( type "VARCHAR", NotNull : true ) 
     * @return the field value
     */
	public String getMA_CODE()
    {
        return this.MA_CODE;
    }

    //----------------------------------------------------------------------
    // GETTER(S) & SETTER(S) FOR DATA FIELDS
    //----------------------------------------------------------------------
    //--- DATABASE MAPPING : MA_NAME ( VARCHAR ) 
    /**
     * Set the "MA_NAME" field value
     * This field is mapped on the database column "MA_NAME" ( type "VARCHAR", NotNull : true ) 
     * @param MA_NAME
     */
    public void setMA_NAME( String MA_NAME )
    {
        this.MA_NAME = MA_NAME;
    }
    /**
     * Get the "maName" field value
     * This field is mapped on the database column "MA_NAME" ( type "VARCHAR", NotNull : true ) 
     * @return the field value
     */
    public String getMA_NAME()
    {
        return this.MA_NAME;
    }

    //--- DATABASE MAPPING : MA_TUYEN ( VARCHAR ) 
    /**
     * Set the "MA_TUYEN" field value
     * This field is mapped on the database column "MA_TUYEN" ( type "VARCHAR", NotNull : true ) 
     * @param MA_TUYEN
     */
    public void setMA_TUYEN( String MA_TUYEN )
    {
        this.MA_TUYEN = MA_TUYEN;
    }
    /**
     * Get the "maTuyen" field value
     * This field is mapped on the database column "MA_TUYEN" ( type "VARCHAR", NotNull : true ) 
     * @return the field value
     */
    public String getMA_TUYEN()
    {
        return this.MA_TUYEN;
    }

    //--- DATABASE MAPPING : MA_RANK ( INT ) 
    /**
     * Set the "MA_RANK" field value
     * This field is mapped on the database column "MA_RANK" ( type "INT", NotNull : true ) 
     * @param MA_RANK
     */
    public void setMA_RANK( Integer MA_RANK )
    {
        this.MA_RANK = MA_RANK;
    }
    /**
     * Get the "maRank" field value
     * This field is mapped on the database column "MA_RANK" ( type "INT", NotNull : true ) 
     * @return the field value
     */
    public Integer getMA_RANK()
    {
        return this.MA_RANK;
    }



/*
 * CRUD functions
 */
    public static MaCskcb load(String MA_CODE) {
    	Connection connection = null;

		try {
			connection = DbHelper.getSql2o();
			String sql = "select * from ma_cskcb where MA_CODE=:MA_CODE";
			logger.debug(sql);
			MaCskcb obj = connection
					.createQuery(
							sql,
							true)
					.addParameter("MA_CODE", MA_CODE)
					.executeAndFetchFirst( MaCskcb.class);
            logger.info(obj);
            logger.debug("Load "+obj);
            return obj;
		} catch (Exception t) {
            logger.error("Load Error: ID="+MA_CODE +"\nException=\n"+ t);
			throw new RuntimeException("LOAD [MaCskcb]. ERROR: "+t.getMessage(), t);
		}
    }
    
    public static MaCskcb load(Integer MA_CODE) {
    	Connection connection = null;

		try {
			connection = DbHelper.getSql2o();
			String sql = "select * from ma_cskcb where MA_CODE=:MA_CODE";
			logger.debug(sql);
			MaCskcb obj = connection
					.createQuery(
							sql,
							true)
					.addParameter("MA_CODE", MA_CODE)
					.executeAndFetchFirst( MaCskcb.class);
            logger.info(obj);
            logger.debug("Load "+obj);
            return obj;
		} catch (Exception t) {
            logger.error("Load Error: ID="+MA_CODE +"\nException=\n"+ t);
			throw new RuntimeException("LOAD [MaCskcb]. ERROR: "+t.getMessage(), t);
		}
    }
    
    public String toString(){
    	String strData = "ma_cskcb "
    			+" MA_CODE = " + MA_CODE                + "; MA_NAME = " + MA_NAME
                + "; MA_TUYEN = " + MA_TUYEN
                + "; MA_RANK = " + MA_RANK
    			;
    	return strData;
    }
    
    public void insert() {
		//
    	if( MA_CODE !=null){
			// Update 
            update();
			return;
		}
        if(DbHelper.checkPhanQuyen(DbHelper.INSERT, "ma_cskcb")==false){
            logger.info("YOU (" + DbHelper.getCurrentSessionUserName() + "), DON'T HAVE INSERT RIGHT for table ma_cskcb");
            return;
        }
		Connection connection = null;

		try {
			connection = DbHelper.getSql2o();
            connection.setRollbackOnException(false);

            String sql = "insert into ma_cskcb (MA_NAME, MA_TUYEN, MA_RANK STS) values(:MA_NAME, :MA_TUYEN, :MA_RANK 0)";
                            			String createdId = connection
                            					.createQuery(
							sql,
							true)
                        .addParameter("MA_NAME", this.MA_NAME)
                            .addParameter("MA_TUYEN", this.MA_TUYEN)
                            .addParameter("MA_RANK", this.MA_RANK)
    					.executeUpdate()
                                                .getKey(String.class);
                            					
            logger.info("Add OK: NewID="+createdId);
            logger.info("Add OK [ma_cskcb] debug: "+toString());
            // Set KEYID MA_CODE = newID
                            MA_CODE = ""+createdId;
                            			// Save log
            connection
					.createQuery(
							"insert into action_log(u_id,u_action,dbtable,actionid,fieldid) values(:u_id,:u_action,:dbtable,:actionid,:fieldid)",
							true)
					.addParameter("u_id", DbHelper.getCurrentSessionUserId())
					.addParameter("u_action", "New ma_cskcb. ID="+MA_CODE)
                    .addParameter("dbtable", "ma_cskcb")
                    .addParameter("fieldid", MA_CODE)
					.addParameter("actionid", 1)
					.executeUpdate();
            logger.info("Add [ma_cskcb] OK: NewID="+createdId);
		} catch (Exception t) {
            logger.error("Add [ma_cskcb] Error: "+"\nException=\n"+ t);
			throw new RuntimeException("ADD [ma_cskcb]. ERROR: "+t.getMessage(), t);
		}
	}
    
    public void update() {
		//
    	if(MA_CODE==null){
            insert();
			return;
		}
        if(DbHelper.checkPhanQuyen(DbHelper.UPDATE, "ma_cskcb")==false){
            logger.info("YOU (" + DbHelper.getCurrentSessionUserName() + "), DON'T HAVE UPDATE RIGHT for table ma_cskcb");
            return;
        }
		Connection connection = null;

		try {
			connection = DbHelper.getSql2o();
            connection.setRollbackOnException(false);
			String sql = "update ma_cskcb set MA_NAME=:MA_NAME, MA_TUYEN=:MA_TUYEN, MA_RANK=:MA_RANK where MA_CODE=:MA_CODE";
			connection
					.createQuery(
							sql,
							true)
                    .addParameter("MA_NAME", this.MA_NAME)
                    .addParameter("MA_TUYEN", this.MA_TUYEN)
                    .addParameter("MA_RANK", this.MA_RANK)
					.addParameter("MA_CODE", this.MA_CODE)
					.executeUpdate();
            logger.info("Update [ma_cskcb] OK: ID="+MA_CODE);
            logger.info("Update [ma_cskcb] debug: "+toString());
            connection
					.createQuery(
							"insert into action_log(u_id,u_action,dbtable,actionid,fieldid) values(:u_id,:u_action,:dbtable,:actionid,:fieldid)",
							true)
					.addParameter("u_id", DbHelper.getCurrentSessionUserId())
					.addParameter("u_action", "Update ma_cskcb. ID="+MA_CODE)
                    .addParameter("dbtable", "ma_cskcb")
                    .addParameter("fieldid", MA_CODE)
					.addParameter("actionid", 2)
					.executeUpdate();
		} catch (Exception t) {
			logger.error("Update [ma_cskcb] Error: ID=["+MA_CODE +"]."+"\nException=\n"+ t);
			throw new RuntimeException("Update [ma_cskcb]. ERROR: "+t.getMessage(), t);
		}
	}
    
    
    public void delete() {
		//
    	if(MA_CODE==null){
			return;
		}
        if(DbHelper.checkPhanQuyen(DbHelper.DELETE, "ma_cskcb")==false){
            logger.info("YOU (" + DbHelper.getCurrentSessionUserName() + "), DON'T HAVE DELETE RIGHT for table ma_cskcb");
            return;
        }
		Connection connection = null;

		try {
			connection = DbHelper.getSql2o();
            connection.setRollbackOnException(false);
			String sql = "update ma_cskcb set STS=:STS where MA_CODE=:MA_CODE";
			connection
					.createQuery(
							sql,
							true)
					.addParameter("STS", DbHelper.DELETE_STATUS)
					.addParameter("MA_CODE", this.MA_CODE)
					.executeUpdate();
			logger.error("Delete [ma_cskcb] OK: ID=["+MA_CODE +"].");
            connection
					.createQuery(
							"insert into action_log(u_id,u_action,dbtable,actionid,fieldid) values(:u_id,:u_action,:dbtable,:actionid,:fieldid)",
							true)
					.addParameter("u_id", DbHelper.getCurrentSessionUserId())
					.addParameter("u_action", "Update ma_cskcb. ID="+MA_CODE)
                    .addParameter("dbtable", "ma_cskcb")
                    .addParameter("fieldid", MA_CODE)
					.addParameter("actionid", 3)
					.executeUpdate();
		} catch (Exception t) {
			logger.error("Delete [ma_cskcb] Error: ID=["+MA_CODE +"]."+"\nException=\n"+ t);
			throw new RuntimeException("Delete [ma_cskcb]. ID=["+MA_CODE + " ERROR: "+t.getMessage(), t);
		}
	}
    
	public void deleteRow() {
		//
    	if(MA_CODE==null){
			return;
		}
        if(DbHelper.checkPhanQuyen(DbHelper.DELETE, "ma_cskcb")==false){
            logger.info("YOU (" + DbHelper.getCurrentSessionUserName() + "), DON'T HAVE DELETE RIGHT for table ma_cskcb");
            return;
        }
		Connection connection = null;

		try {
			connection = DbHelper.getSql2o();
            connection.setRollbackOnException(false);
			String sql = "delete from ma_cskcb where MA_CODE=:MA_CODE";
			connection
					.createQuery(
							sql,
							true)
					.addParameter("MA_CODE", this.MA_CODE)
					.executeUpdate();
			logger.error("Delete Row [ma_cskcb] OK: ID=["+MA_CODE +"].");
            connection
					.createQuery(
							"insert into action_log(u_id,u_action,dbtable,actionid,fieldid) values(:u_id,:u_action,:dbtable,:actionid,:fieldid)",
							true)
					.addParameter("u_id", DbHelper.getCurrentSessionUserId())
					.addParameter("u_action", "Update ma_cskcb. ID="+MA_CODE)
                    .addParameter("dbtable", "ma_cskcb")
                    .addParameter("fieldid", MA_CODE)
					.addParameter("actionid", 4)
					.executeUpdate();
		} catch (Exception t) {
			logger.error("Delete [ma_cskcb] Error: ID=["+MA_CODE +"]."+"\nException=\n"+ t);
			throw new RuntimeException("Delete [ma_cskcb]. ID=["+MA_CODE + " ERROR: "+t.getMessage(), t);
		}
	}
    
    public String getIndex(int idx){
        switch(idx){
            case 0:
                // java.lang.String    
                if(this.MA_NAME!=null)
                    return this.MA_NAME.toString();
                else
                    return "N/A";
            case 1:
                // java.lang.String    
                if(this.MA_TUYEN!=null)
                    return this.MA_TUYEN.toString();
                else
                    return "N/A";
            case 2:
                // java.lang.Integer   
                if(this.MA_RANK!=null)
                    return this.MA_RANK.toString();
                else
                    return "N/A";
            default:
        }
        return "";
    }
}