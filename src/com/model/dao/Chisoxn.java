﻿/*
 * Java bean class for entity table chisoxn 
 * Created on 17 Jul 2017 ( Date ISO 2017-07-17 - Time 08:25:18 )
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
 * Entity bean for table "chisoxn"
 * 
 * @author Telosys Tools Generator
 *
 */
public class Chisoxn implements Serializable
{
    static Logger logger = LogManager.getLogger(Chisoxn.class.getName());
    private static final long serialVersionUID = 1L;

    public Integer    CSVN_ID ; // Primary Key

    public Integer    DV_ID ;
    public Integer    CS_ID ;
    public String     MA_DVKT ;
    public String     TEN_DVKT ;
    public String     CS_NAME ;
    public Integer    STS ;

    /**
     * Default constructor
     */
    public Chisoxn()
    {
        super();
        // Set default value 
this.DV_ID = 0;                         // data type = Integer   
this.CS_ID = 0;                         // data type = Integer   
this.MA_DVKT = "";//String.format("%16.16s", "MA_DVKT").trim(); // data type = String     16
this.TEN_DVKT = "";//String.format("%255.255s", "TEN_DVKT").trim(); // data type = String     255
this.CS_NAME = "";//String.format("%255.255s", "CS_NAME").trim(); // data type = String     255
this.STS = 0;                         // data type = Integer   
        // End Set default value 
    }
    
    //----------------------------------------------------------------------
    // GETTER(S) & SETTER(S) FOR THE PRIMARY KEY 
    //----------------------------------------------------------------------
    /**
     * Set the "csvnId" field value
     * This field is mapped on the database column "CSVN_ID" ( type "INT", NotNull : true ) 
     * @param CSVN_ID
     */
	public void setCSVN_ID( Integer CSVN_ID )
    {
        this.CSVN_ID = CSVN_ID ;
    }
    /**
     * Get the "CSVN_ID" field value
     * This field is mapped on the database column "CSVN_ID" ( type "INT", NotNull : true ) 
     * @return the field value
     */
	public Integer getCSVN_ID()
    {
        return this.CSVN_ID;
    }

    //----------------------------------------------------------------------
    // GETTER(S) & SETTER(S) FOR DATA FIELDS
    //----------------------------------------------------------------------
    //--- DATABASE MAPPING : DV_ID ( INT ) 
    /**
     * Set the "DV_ID" field value
     * This field is mapped on the database column "DV_ID" ( type "INT", NotNull : true ) 
     * @param DV_ID
     */
    public void setDV_ID( Integer DV_ID )
    {
        this.DV_ID = DV_ID;
    }
    /**
     * Get the "dvId" field value
     * This field is mapped on the database column "DV_ID" ( type "INT", NotNull : true ) 
     * @return the field value
     */
    public Integer getDV_ID()
    {
        return this.DV_ID;
    }

    //--- DATABASE MAPPING : CS_ID ( INT ) 
    /**
     * Set the "CS_ID" field value
     * This field is mapped on the database column "CS_ID" ( type "INT", NotNull : true ) 
     * @param CS_ID
     */
    public void setCS_ID( Integer CS_ID )
    {
        this.CS_ID = CS_ID;
    }
    /**
     * Get the "csId" field value
     * This field is mapped on the database column "CS_ID" ( type "INT", NotNull : true ) 
     * @return the field value
     */
    public Integer getCS_ID()
    {
        return this.CS_ID;
    }

    //--- DATABASE MAPPING : MA_DVKT ( VARCHAR ) 
    /**
     * Set the "MA_DVKT" field value
     * This field is mapped on the database column "MA_DVKT" ( type "VARCHAR", NotNull : true ) 
     * @param MA_DVKT
     */
    public void setMA_DVKT( String MA_DVKT )
    {
        this.MA_DVKT = MA_DVKT;
    }
    /**
     * Get the "maDvkt" field value
     * This field is mapped on the database column "MA_DVKT" ( type "VARCHAR", NotNull : true ) 
     * @return the field value
     */
    public String getMA_DVKT()
    {
        return this.MA_DVKT;
    }

    //--- DATABASE MAPPING : TEN_DVKT ( VARCHAR ) 
    /**
     * Set the "TEN_DVKT" field value
     * This field is mapped on the database column "TEN_DVKT" ( type "VARCHAR", NotNull : true ) 
     * @param TEN_DVKT
     */
    public void setTEN_DVKT( String TEN_DVKT )
    {
        this.TEN_DVKT = TEN_DVKT;
    }
    /**
     * Get the "tenDvkt" field value
     * This field is mapped on the database column "TEN_DVKT" ( type "VARCHAR", NotNull : true ) 
     * @return the field value
     */
    public String getTEN_DVKT()
    {
        return this.TEN_DVKT;
    }

    //--- DATABASE MAPPING : CS_NAME ( VARCHAR ) 
    /**
     * Set the "CS_NAME" field value
     * This field is mapped on the database column "CS_NAME" ( type "VARCHAR", NotNull : true ) 
     * @param CS_NAME
     */
    public void setCS_NAME( String CS_NAME )
    {
        this.CS_NAME = CS_NAME;
    }
    /**
     * Get the "csName" field value
     * This field is mapped on the database column "CS_NAME" ( type "VARCHAR", NotNull : true ) 
     * @return the field value
     */
    public String getCS_NAME()
    {
        return this.CS_NAME;
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
    public static Chisoxn load(String CSVN_ID) {
    	Connection connection = null;

		try {
			connection = DbHelper.getSql2o();
			String sql = "select * from chisoxn where CSVN_ID=:CSVN_ID";
			logger.debug(sql);
			Chisoxn obj = connection
					.createQuery(
							sql,
							true)
					.addParameter("CSVN_ID", CSVN_ID)
					.executeAndFetchFirst( Chisoxn.class);
            logger.info(obj);
            logger.debug("Load "+obj);
            return obj;
		} catch (Exception t) {
            logger.error("Load Error: ID="+CSVN_ID +"\nException=\n"+ t);
			throw new RuntimeException("LOAD [Chisoxn]. ERROR: "+t.getMessage(), t);
		}
    }
    
    public static Chisoxn load(Integer CSVN_ID) {
    	Connection connection = null;

		try {
			connection = DbHelper.getSql2o();
			String sql = "select * from chisoxn where CSVN_ID=:CSVN_ID";
			logger.debug(sql);
			Chisoxn obj = connection
					.createQuery(
							sql,
							true)
					.addParameter("CSVN_ID", CSVN_ID)
					.executeAndFetchFirst( Chisoxn.class);
            logger.info(obj);
            logger.debug("Load "+obj);
            return obj;
		} catch (Exception t) {
            logger.error("Load Error: ID="+CSVN_ID +"\nException=\n"+ t);
			throw new RuntimeException("LOAD [Chisoxn]. ERROR: "+t.getMessage(), t);
		}
    }
    
    public String toString(){
    	String strData = "chisoxn "
    			+" CSVN_ID = " + CSVN_ID                + "; DV_ID = " + DV_ID
                + "; CS_ID = " + CS_ID
                + "; MA_DVKT = " + MA_DVKT
                + "; TEN_DVKT = " + TEN_DVKT
                + "; CS_NAME = " + CS_NAME
                + "; STS = " + STS
    			;
    	return strData;
    }
    
    public void insert() {
		//
    	if( CSVN_ID !=null){
			// Update 
            update();
			return;
		}
        if(DbHelper.checkPhanQuyen(DbHelper.INSERT, "chisoxn")==false){
            logger.info("YOU (" + DbHelper.getCurrentSessionUserName() + "), DON'T HAVE INSERT RIGHT for table chisoxn");
            return;
        }
		Connection connection = null;

		try {
			connection = DbHelper.getSql2o();
            connection.setRollbackOnException(false);

            String sql = "insert into chisoxn (DV_ID, CS_ID, MA_DVKT, TEN_DVKT, CS_NAME,  STS) values(:DV_ID, :CS_ID, :MA_DVKT, :TEN_DVKT, :CS_NAME,  0)";
                            			int createdId = connection
                            					.createQuery(
							sql,
							true)
                        .addParameter("DV_ID", this.DV_ID)
                            .addParameter("CS_ID", this.CS_ID)
                            .addParameter("MA_DVKT", this.MA_DVKT)
                            .addParameter("TEN_DVKT", this.TEN_DVKT)
                            .addParameter("CS_NAME", this.CS_NAME)
        					.executeUpdate()
                                                .getKey(Integer.class);
                            					
            logger.info("Add OK: NewID="+createdId);
            logger.info("Add OK [chisoxn] debug: "+toString());
            // Set KEYID CSVN_ID = newID
                            CSVN_ID = createdId;
                            			// Save log
            connection
					.createQuery(
							"insert into action_log(u_id,u_action,dbtable,actionid,fieldid) values(:u_id,:u_action,:dbtable,:actionid,:fieldid)",
							true)
					.addParameter("u_id", DbHelper.getCurrentSessionUserId())
					.addParameter("u_action", "New chisoxn. ID="+CSVN_ID)
                    .addParameter("dbtable", "chisoxn")
                    .addParameter("fieldid", CSVN_ID)
					.addParameter("actionid", 1)
					.executeUpdate();
            logger.info("Add [chisoxn] OK: NewID="+createdId);
		} catch (Exception t) {
            logger.error("Add [chisoxn] Error: "+"\nException=\n"+ t);
			throw new RuntimeException("ADD [chisoxn]. ERROR: "+t.getMessage(), t);
		}
	}
    
    public void update() {
		//
    	if(CSVN_ID==null){
            insert();
			return;
		}
        if(DbHelper.checkPhanQuyen(DbHelper.UPDATE, "chisoxn")==false){
            logger.info("YOU (" + DbHelper.getCurrentSessionUserName() + "), DON'T HAVE UPDATE RIGHT for table chisoxn");
            return;
        }
		Connection connection = null;

		try {
			connection = DbHelper.getSql2o();
            connection.setRollbackOnException(false);
			String sql = "update chisoxn set DV_ID=:DV_ID, CS_ID=:CS_ID, MA_DVKT=:MA_DVKT, TEN_DVKT=:TEN_DVKT, CS_NAME=:CS_NAME, STS=:STS where CSVN_ID=:CSVN_ID";
			connection
					.createQuery(
							sql,
							true)
                    .addParameter("DV_ID", this.DV_ID)
                    .addParameter("CS_ID", this.CS_ID)
                    .addParameter("MA_DVKT", this.MA_DVKT)
                    .addParameter("TEN_DVKT", this.TEN_DVKT)
                    .addParameter("CS_NAME", this.CS_NAME)
                    .addParameter("STS", this.STS)
					.addParameter("CSVN_ID", this.CSVN_ID)
					.executeUpdate();
            logger.info("Update [chisoxn] OK: ID="+CSVN_ID);
            logger.info("Update [chisoxn] debug: "+toString());
            connection
					.createQuery(
							"insert into action_log(u_id,u_action,dbtable,actionid,fieldid) values(:u_id,:u_action,:dbtable,:actionid,:fieldid)",
							true)
					.addParameter("u_id", DbHelper.getCurrentSessionUserId())
					.addParameter("u_action", "Update chisoxn. ID="+CSVN_ID)
                    .addParameter("dbtable", "chisoxn")
                    .addParameter("fieldid", CSVN_ID)
					.addParameter("actionid", 2)
					.executeUpdate();
		} catch (Exception t) {
			logger.error("Update [chisoxn] Error: ID=["+CSVN_ID +"]."+"\nException=\n"+ t);
			throw new RuntimeException("Update [chisoxn]. ERROR: "+t.getMessage(), t);
		}
	}
    
    
    public void delete() {
		//
    	if(CSVN_ID==null){
			return;
		}
        if(DbHelper.checkPhanQuyen(DbHelper.DELETE, "chisoxn")==false){
            logger.info("YOU (" + DbHelper.getCurrentSessionUserName() + "), DON'T HAVE DELETE RIGHT for table chisoxn");
            return;
        }
		Connection connection = null;

		try {
			connection = DbHelper.getSql2o();
            connection.setRollbackOnException(false);
			String sql = "update chisoxn set STS=:STS where CSVN_ID=:CSVN_ID";
			connection
					.createQuery(
							sql,
							true)
					.addParameter("STS", DbHelper.DELETE_STATUS)
					.addParameter("CSVN_ID", this.CSVN_ID)
					.executeUpdate();
			logger.error("Delete [chisoxn] OK: ID=["+CSVN_ID +"].");
            connection
					.createQuery(
							"insert into action_log(u_id,u_action,dbtable,actionid,fieldid) values(:u_id,:u_action,:dbtable,:actionid,:fieldid)",
							true)
					.addParameter("u_id", DbHelper.getCurrentSessionUserId())
					.addParameter("u_action", "Update chisoxn. ID="+CSVN_ID)
                    .addParameter("dbtable", "chisoxn")
                    .addParameter("fieldid", CSVN_ID)
					.addParameter("actionid", 3)
					.executeUpdate();
		} catch (Exception t) {
			logger.error("Delete [chisoxn] Error: ID=["+CSVN_ID +"]."+"\nException=\n"+ t);
			throw new RuntimeException("Delete [chisoxn]. ID=["+CSVN_ID + " ERROR: "+t.getMessage(), t);
		}
	}
    
    public String getIndex(int idx){
        switch(idx){
            case 0:
                // java.lang.Integer   
                if(this.DV_ID!=null)
                    return this.DV_ID.toString();
                else
                    return "N/A";
            case 1:
                // java.lang.Integer   
                if(this.CS_ID!=null)
                    return this.CS_ID.toString();
                else
                    return "N/A";
            case 2:
                // java.lang.String    
                if(this.MA_DVKT!=null)
                    return this.MA_DVKT.toString();
                else
                    return "N/A";
            case 3:
                // java.lang.String    
                if(this.TEN_DVKT!=null)
                    return this.TEN_DVKT.toString();
                else
                    return "N/A";
            case 4:
                // java.lang.String    
                if(this.CS_NAME!=null)
                    return this.CS_NAME.toString();
                else
                    return "N/A";
            case 5:
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