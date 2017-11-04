﻿/*
 * Java bean class for entity table vendor 
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


/**
 * Entity bean for table "vendor"
 * 
 * @author Telosys Tools Generator
 *
 */
public class Vendor implements Serializable
{
    static Logger logger = LogManager.getLogger(Vendor.class.getName());
    private static final long serialVersionUID = 1L;

    public Integer    V_ID ; // Primary Key

    public String     V_NAME ;
    public String     V_ADDR ;
    public String     V_MST ;
    public String     V_CONTACT ;
    public String     V_PHONE ;
    public Integer    STS ;

    /**
     * Default constructor
     */
    public Vendor()
    {
        super();
        // Set default value 
this.V_NAME = "";//String.format("%256.256s", "V_NAME").trim(); // data type = String     256
this.V_ADDR = "";//String.format("%256.256s", "V_ADDR").trim(); // data type = String     256
this.V_MST = "";//String.format("%256.256s", "V_MST").trim(); // data type = String     256
this.V_CONTACT = "";//String.format("%256.256s", "V_CONTACT").trim(); // data type = String     256
this.V_PHONE = "";//String.format("%256.256s", "V_PHONE").trim(); // data type = String     256
this.STS = 0;                         // data type = Integer   
        // End Set default value 
    }
    
    //----------------------------------------------------------------------
    // GETTER(S) & SETTER(S) FOR THE PRIMARY KEY 
    //----------------------------------------------------------------------
    /**
     * Set the "vId" field value
     * This field is mapped on the database column "V_ID" ( type "INT", NotNull : true ) 
     * @param V_ID
     */
	public void setV_ID( Integer V_ID )
    {
        this.V_ID = V_ID ;
    }
    /**
     * Get the "V_ID" field value
     * This field is mapped on the database column "V_ID" ( type "INT", NotNull : true ) 
     * @return the field value
     */
	public Integer getV_ID()
    {
        return this.V_ID;
    }

    //----------------------------------------------------------------------
    // GETTER(S) & SETTER(S) FOR DATA FIELDS
    //----------------------------------------------------------------------
    //--- DATABASE MAPPING : V_NAME ( VARCHAR ) 
    /**
     * Set the "V_NAME" field value
     * This field is mapped on the database column "V_NAME" ( type "VARCHAR", NotNull : true ) 
     * @param V_NAME
     */
    public void setV_NAME( String V_NAME )
    {
        this.V_NAME = V_NAME;
    }
    /**
     * Get the "vName" field value
     * This field is mapped on the database column "V_NAME" ( type "VARCHAR", NotNull : true ) 
     * @return the field value
     */
    public String getV_NAME()
    {
        return this.V_NAME;
    }

    //--- DATABASE MAPPING : V_ADDR ( VARCHAR ) 
    /**
     * Set the "V_ADDR" field value
     * This field is mapped on the database column "V_ADDR" ( type "VARCHAR", NotNull : true ) 
     * @param V_ADDR
     */
    public void setV_ADDR( String V_ADDR )
    {
        this.V_ADDR = V_ADDR;
    }
    /**
     * Get the "vAddr" field value
     * This field is mapped on the database column "V_ADDR" ( type "VARCHAR", NotNull : true ) 
     * @return the field value
     */
    public String getV_ADDR()
    {
        return this.V_ADDR;
    }

    //--- DATABASE MAPPING : V_MST ( VARCHAR ) 
    /**
     * Set the "V_MST" field value
     * This field is mapped on the database column "V_MST" ( type "VARCHAR", NotNull : false ) 
     * @param V_MST
     */
    public void setV_MST( String V_MST )
    {
        this.V_MST = V_MST;
    }
    /**
     * Get the "vMst" field value
     * This field is mapped on the database column "V_MST" ( type "VARCHAR", NotNull : false ) 
     * @return the field value
     */
    public String getV_MST()
    {
        return this.V_MST;
    }

    //--- DATABASE MAPPING : V_CONTACT ( VARCHAR ) 
    /**
     * Set the "V_CONTACT" field value
     * This field is mapped on the database column "V_CONTACT" ( type "VARCHAR", NotNull : false ) 
     * @param V_CONTACT
     */
    public void setV_CONTACT( String V_CONTACT )
    {
        this.V_CONTACT = V_CONTACT;
    }
    /**
     * Get the "vContact" field value
     * This field is mapped on the database column "V_CONTACT" ( type "VARCHAR", NotNull : false ) 
     * @return the field value
     */
    public String getV_CONTACT()
    {
        return this.V_CONTACT;
    }

    //--- DATABASE MAPPING : V_PHONE ( VARCHAR ) 
    /**
     * Set the "V_PHONE" field value
     * This field is mapped on the database column "V_PHONE" ( type "VARCHAR", NotNull : false ) 
     * @param V_PHONE
     */
    public void setV_PHONE( String V_PHONE )
    {
        this.V_PHONE = V_PHONE;
    }
    /**
     * Get the "vPhone" field value
     * This field is mapped on the database column "V_PHONE" ( type "VARCHAR", NotNull : false ) 
     * @return the field value
     */
    public String getV_PHONE()
    {
        return this.V_PHONE;
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
    public static Vendor load(String V_ID) {
    	Connection connection = null;

		try {
			connection = DbHelper.getSql2o();
			String sql = "select * from vendor where V_ID=:V_ID";
			logger.debug(sql);
			Vendor obj = connection
					.createQuery(
							sql,
							true)
					.addParameter("V_ID", V_ID)
					.executeAndFetchFirst( Vendor.class);
            logger.info(obj);
            logger.debug("Load "+obj);
            return obj;
		} catch (Exception t) {
            logger.error("Load Error: ID="+V_ID +"\nException=\n"+ t);
			throw new RuntimeException("LOAD [Vendor]. ERROR: "+t.getMessage(), t);
		}
    }
    
    public static Vendor load(Integer V_ID) {
    	Connection connection = null;

		try {
			connection = DbHelper.getSql2o();
			String sql = "select * from vendor where V_ID=:V_ID";
			logger.debug(sql);
			Vendor obj = connection
					.createQuery(
							sql,
							true)
					.addParameter("V_ID", V_ID)
					.executeAndFetchFirst( Vendor.class);
            logger.info(obj);
            logger.debug("Load "+obj);
            return obj;
		} catch (Exception t) {
            logger.error("Load Error: ID="+V_ID +"\nException=\n"+ t);
			throw new RuntimeException("LOAD [Vendor]. ERROR: "+t.getMessage(), t);
		}
    }
    
    public String toString(){
    	String strData = "vendor "
    			+" V_ID = " + V_ID                + "; V_NAME = " + V_NAME
                + "; V_ADDR = " + V_ADDR
                + "; V_MST = " + V_MST
                + "; V_CONTACT = " + V_CONTACT
                + "; V_PHONE = " + V_PHONE
                + "; STS = " + STS
    			;
    	return strData;
    }
    
    public void insert() {
		//
    	if( V_ID !=null){
			// Update 
            update();
			return;
		}
        if(DbHelper.checkPhanQuyen(DbHelper.INSERT, "vendor")==false){
            logger.info("YOU (" + DbHelper.getCurrentSessionUserName() + "), DON'T HAVE INSERT RIGHT for table vendor");
            return;
        }
		Connection connection = null;

		try {
			connection = DbHelper.getSql2o();
            connection.setRollbackOnException(false);

            String sql = "insert into vendor (V_NAME, V_ADDR, V_MST, V_CONTACT, V_PHONE,  STS) values(:V_NAME, :V_ADDR, :V_MST, :V_CONTACT, :V_PHONE,  0)";
                            			int createdId = connection
                            					.createQuery(
							sql,
							true)
                        .addParameter("V_NAME", this.V_NAME)
                            .addParameter("V_ADDR", this.V_ADDR)
                            .addParameter("V_MST", this.V_MST)
                            .addParameter("V_CONTACT", this.V_CONTACT)
                            .addParameter("V_PHONE", this.V_PHONE)
        					.executeUpdate()
                                                .getKey(Integer.class);
                            					
            logger.info("Add OK: NewID="+createdId);
            logger.info("Add OK [vendor] debug: "+toString());
            // Set KEYID V_ID = newID
                            V_ID = createdId;
                            			// Save log
            connection
					.createQuery(
							"insert into action_log(u_id,u_action,dbtable,actionid,fieldid) values(:u_id,:u_action,:dbtable,:actionid,:fieldid)",
							true)
					.addParameter("u_id", DbHelper.getCurrentSessionUserId())
					.addParameter("u_action", "New vendor. ID="+V_ID)
                    .addParameter("dbtable", "vendor")
                    .addParameter("fieldid", V_ID)
					.addParameter("actionid", 1)
					.executeUpdate();
            logger.info("Add [vendor] OK: NewID="+createdId);
            logger.info("Add [vendor] OK: Data="+this.toString());
		} catch (Exception t) {
            logger.error("Add [vendor] Error: "+"\nException=\n"+ t);
			throw new RuntimeException("ADD [vendor]. ERROR: "+t.getMessage(), t);
		}
	}
    
    public void update() {
		//
    	if(V_ID==null){
            insert();
			return;
		}
        if(DbHelper.checkPhanQuyen(DbHelper.UPDATE, "vendor")==false){
            logger.info("YOU (" + DbHelper.getCurrentSessionUserName() + "), DON'T HAVE UPDATE RIGHT for table vendor");
            return;
        }
		Connection connection = null;

		try {
			connection = DbHelper.getSql2o();
            connection.setRollbackOnException(false);
            Vendor temp = Vendor.load(this.V_ID);
            if(temp!=null){
            	logger.info("Begin Update [vendor]: "+temp.toString());
            }
			//
			String sql = "update vendor set V_NAME=:V_NAME, V_ADDR=:V_ADDR, V_MST=:V_MST, V_CONTACT=:V_CONTACT, V_PHONE=:V_PHONE, STS=:STS where V_ID=:V_ID";
			connection
					.createQuery(
							sql,
							true)
                    .addParameter("V_NAME", this.V_NAME)
                    .addParameter("V_ADDR", this.V_ADDR)
                    .addParameter("V_MST", this.V_MST)
                    .addParameter("V_CONTACT", this.V_CONTACT)
                    .addParameter("V_PHONE", this.V_PHONE)
                    .addParameter("STS", this.STS)
					.addParameter("V_ID", this.V_ID)
					.executeUpdate();
            logger.info("Update [vendor] OK: ID="+V_ID);
            logger.info("End   Update [vendor]: "+toString());
            connection
					.createQuery(
							"insert into action_log(u_id,u_action,dbtable,actionid,fieldid) values(:u_id,:u_action,:dbtable,:actionid,:fieldid)",
							true)
					.addParameter("u_id", DbHelper.getCurrentSessionUserId())
					.addParameter("u_action", "Update vendor. ID="+V_ID)
                    .addParameter("dbtable", "vendor")
                    .addParameter("fieldid", V_ID)
					.addParameter("actionid", 2)
					.executeUpdate();
		} catch (Exception t) {
			logger.error("Update [vendor] Error: ID=["+V_ID +"]."+"\nException=\n"+ t);
			throw new RuntimeException("Update [vendor]. ERROR: "+t.getMessage(), t);
		}
	}
    
    
    public void delete() {
		//
    	if(V_ID==null){
			return;
		}
        if(DbHelper.checkPhanQuyen(DbHelper.DELETE, "vendor")==false){
            logger.info("YOU (" + DbHelper.getCurrentSessionUserName() + "), DON'T HAVE DELETE RIGHT for table vendor");
            return;
        }
		Connection connection = null;

		try {
			connection = DbHelper.getSql2o();
            connection.setRollbackOnException(false);
			String sql = "update vendor set STS=:STS where V_ID=:V_ID";
			connection
					.createQuery(
							sql,
							true)
					.addParameter("STS", DbHelper.DELETE_STATUS)
					.addParameter("V_ID", this.V_ID)
					.executeUpdate();
			logger.error("Delete [vendor] OK: ID=["+V_ID +"].");
            connection
					.createQuery(
							"insert into action_log(u_id,u_action,dbtable,actionid,fieldid) values(:u_id,:u_action,:dbtable,:actionid,:fieldid)",
							true)
					.addParameter("u_id", DbHelper.getCurrentSessionUserId())
					.addParameter("u_action", "Update vendor. ID="+V_ID)
                    .addParameter("dbtable", "vendor")
                    .addParameter("fieldid", V_ID)
					.addParameter("actionid", 3)
					.executeUpdate();
		} catch (Exception t) {
			logger.error("Delete [vendor] Error: ID=["+V_ID +"]."+"\nException=\n"+ t);
			throw new RuntimeException("Delete [vendor]. ID=["+V_ID + " ERROR: "+t.getMessage(), t);
		}
	}
    
	public void deleteRow() {
		//
    	if(V_ID==null){
			return;
		}
        if(DbHelper.checkPhanQuyen(DbHelper.DELETE, "vendor")==false){
            logger.info("YOU (" + DbHelper.getCurrentSessionUserName() + "), DON'T HAVE DELETE RIGHT for table vendor");
            return;
        }
		Connection connection = null;

		try {
			connection = DbHelper.getSql2o();
            connection.setRollbackOnException(false);
			String sql = "delete from vendor where V_ID=:V_ID";
			connection
					.createQuery(
							sql,
							true)
					.addParameter("V_ID", this.V_ID)
					.executeUpdate();
			logger.error("Delete Row [vendor] OK: ID=["+V_ID +"].");
            connection
					.createQuery(
							"insert into action_log(u_id,u_action,dbtable,actionid,fieldid) values(:u_id,:u_action,:dbtable,:actionid,:fieldid)",
							true)
					.addParameter("u_id", DbHelper.getCurrentSessionUserId())
					.addParameter("u_action", "Update vendor. ID="+V_ID)
                    .addParameter("dbtable", "vendor")
                    .addParameter("fieldid", V_ID)
					.addParameter("actionid", 4)
					.executeUpdate();
		} catch (Exception t) {
			logger.error("Delete [vendor] Error: ID=["+V_ID +"]."+"\nException=\n"+ t);
			throw new RuntimeException("Delete [vendor]. ID=["+V_ID + " ERROR: "+t.getMessage(), t);
		}
	}
    
    public String getIndex(int idx){
        switch(idx){
            case 0:
                // java.lang.String    
                if(this.V_NAME!=null)
                    return this.V_NAME.toString();
                else
                    return "N/A";
            case 1:
                // java.lang.String    
                if(this.V_ADDR!=null)
                    return this.V_ADDR.toString();
                else
                    return "N/A";
            case 2:
                // java.lang.String    
                if(this.V_MST!=null)
                    return this.V_MST.toString();
                else
                    return "N/A";
            case 3:
                // java.lang.String    
                if(this.V_CONTACT!=null)
                    return this.V_CONTACT.toString();
                else
                    return "N/A";
            case 4:
                // java.lang.String    
                if(this.V_PHONE!=null)
                    return this.V_PHONE.toString();
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