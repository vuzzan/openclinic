﻿/*
 * Java bean class for entity table benh_nhan 
 * Created on 15 Jan 2018 ( Date ISO 2018-01-15 - Time 12:07:52 )
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

import java.util.Date;

/**
 * Entity bean for table "benh_nhan"
 * 
 * @author Telosys Tools Generator
 *
 */
public class BenhNhan implements Serializable
{
    static Logger logger = LogManager.getLogger(BenhNhan.class.getName());
    private static final long serialVersionUID = 1L;

    public Integer    BN_ID ; // Primary Key

    public String     HO_TEN ;
    public String     NGAY_SINH ;
    public Integer    GIOI_TINH ;
    public String     DIA_CHI ;
    public String     MA_THE ;
    public String     MA_DKBD ;
    public String     GT_THE_TU ;
    public String     GT_THE_DEN ;
    public String     NGAY_CAP ;
    public String     MA_QUAN_LY ;
    public String     TEN_CHA_ME ;
    public Integer    MA_DT_SONG ;
    public String     THOIDIEM_NAMNAM ;
    public String     CHUOI_KIEM_TRA ;
    public Date       DATE_ADD ;
    public Date       LAST_EDIT ;
    public String     GATE_INFO ;
    public Integer    STS ;

    /**
     * Default constructor
     */
    public BenhNhan()
    {
        super();
        // Set default value 
this.HO_TEN = "";//String.format("%255.255s", "HO_TEN").trim(); // data type = String     255
this.NGAY_SINH = "";//String.format("%10.10s", "NGAY_SINH").trim(); // data type = String     10
this.GIOI_TINH = 0;                         // data type = Integer   
this.DIA_CHI = "";//String.format("%500.500s", "DIA_CHI").trim(); // data type = String     500
this.MA_THE = "";//String.format("%15.15s", "MA_THE").trim(); // data type = String     15
this.MA_DKBD = "";//String.format("%5.5s", "MA_DKBD").trim(); // data type = String     5
this.GT_THE_TU = "";//String.format("%10.10s", "GT_THE_TU").trim(); // data type = String     10
this.GT_THE_DEN = "";//String.format("%10.10s", "GT_THE_DEN").trim(); // data type = String     10
this.NGAY_CAP = "";//String.format("%12.12s", "NGAY_CAP").trim(); // data type = String     12
this.MA_QUAN_LY = "";//String.format("%32.32s", "MA_QUAN_LY").trim(); // data type = String     32
this.TEN_CHA_ME = "";//String.format("%255.255s", "TEN_CHA_ME").trim(); // data type = String     255
this.MA_DT_SONG = 0;                         // data type = Integer   
this.THOIDIEM_NAMNAM = "";//String.format("%12.12s", "THOIDIEM_NAMNAM").trim(); // data type = String     12
this.CHUOI_KIEM_TRA = "";//String.format("%64.64s", "CHUOI_KIEM_TRA").trim(); // data type = String     64
this.DATE_ADD = new java.util.Date();      // data type = Date      
this.LAST_EDIT = new java.util.Date();      // data type = Date      
this.GATE_INFO = "";//String.format("%1000.1000s", "GATE_INFO").trim(); // data type = String     1000
this.STS = 0;                         // data type = Integer   
        // End Set default value 
    }
    
    public void copy(BenhNhan fromObj)
    {
        if(fromObj==null){
            return;
        }
        this.HO_TEN = fromObj.HO_TEN;
        this.NGAY_SINH = fromObj.NGAY_SINH;
        this.GIOI_TINH = fromObj.GIOI_TINH;
        this.DIA_CHI = fromObj.DIA_CHI;
        this.MA_THE = fromObj.MA_THE;
        this.MA_DKBD = fromObj.MA_DKBD;
        this.GT_THE_TU = fromObj.GT_THE_TU;
        this.GT_THE_DEN = fromObj.GT_THE_DEN;
        this.NGAY_CAP = fromObj.NGAY_CAP;
        this.MA_QUAN_LY = fromObj.MA_QUAN_LY;
        this.TEN_CHA_ME = fromObj.TEN_CHA_ME;
        this.MA_DT_SONG = fromObj.MA_DT_SONG;
        this.THOIDIEM_NAMNAM = fromObj.THOIDIEM_NAMNAM;
        this.CHUOI_KIEM_TRA = fromObj.CHUOI_KIEM_TRA;
        this.DATE_ADD = fromObj.DATE_ADD;
        this.LAST_EDIT = fromObj.LAST_EDIT;
        this.GATE_INFO = fromObj.GATE_INFO;
        this.STS = fromObj.STS;
    }
    //----------------------------------------------------------------------
    // GETTER(S) & SETTER(S) FOR THE PRIMARY KEY 
    //----------------------------------------------------------------------
    /**
     * Set the "bnId" field value
     * This field is mapped on the database column "BN_ID" ( type "INT", NotNull : true ) 
     * @param BN_ID
     */
	public void setBN_ID( Integer BN_ID )
    {
        this.BN_ID = BN_ID ;
    }
    /**
     * Get the "BN_ID" field value
     * This field is mapped on the database column "BN_ID" ( type "INT", NotNull : true ) 
     * @return the field value
     */
	public Integer getBN_ID()
    {
        return this.BN_ID;
    }

    //----------------------------------------------------------------------
    // GETTER(S) & SETTER(S) FOR DATA FIELDS
    //----------------------------------------------------------------------
    //--- DATABASE MAPPING : HO_TEN ( VARCHAR ) 
    /**
     * Set the "HO_TEN" field value
     * This field is mapped on the database column "HO_TEN" ( type "VARCHAR", NotNull : true ) 
     * @param HO_TEN
     */
    public void setHO_TEN( String HO_TEN )
    {
        this.HO_TEN = HO_TEN;
    }
    /**
     * Get the "hoTen" field value
     * This field is mapped on the database column "HO_TEN" ( type "VARCHAR", NotNull : true ) 
     * @return the field value
     */
    public String getHO_TEN()
    {
        return this.HO_TEN;
    }

    //--- DATABASE MAPPING : NGAY_SINH ( VARCHAR ) 
    /**
     * Set the "NGAY_SINH" field value
     * This field is mapped on the database column "NGAY_SINH" ( type "VARCHAR", NotNull : true ) 
     * @param NGAY_SINH
     */
    public void setNGAY_SINH( String NGAY_SINH )
    {
        this.NGAY_SINH = NGAY_SINH;
    }
    /**
     * Get the "ngaySinh" field value
     * This field is mapped on the database column "NGAY_SINH" ( type "VARCHAR", NotNull : true ) 
     * @return the field value
     */
    public String getNGAY_SINH()
    {
        return this.NGAY_SINH;
    }

    //--- DATABASE MAPPING : GIOI_TINH ( INT ) 
    /**
     * Set the "GIOI_TINH" field value
     * This field is mapped on the database column "GIOI_TINH" ( type "INT", NotNull : true ) 
     * @param GIOI_TINH
     */
    public void setGIOI_TINH( Integer GIOI_TINH )
    {
        this.GIOI_TINH = GIOI_TINH;
    }
    /**
     * Get the "gioiTinh" field value
     * This field is mapped on the database column "GIOI_TINH" ( type "INT", NotNull : true ) 
     * @return the field value
     */
    public Integer getGIOI_TINH()
    {
        return this.GIOI_TINH;
    }

    //--- DATABASE MAPPING : DIA_CHI ( VARCHAR ) 
    /**
     * Set the "DIA_CHI" field value
     * This field is mapped on the database column "DIA_CHI" ( type "VARCHAR", NotNull : true ) 
     * @param DIA_CHI
     */
    public void setDIA_CHI( String DIA_CHI )
    {
        this.DIA_CHI = DIA_CHI;
    }
    /**
     * Get the "diaChi" field value
     * This field is mapped on the database column "DIA_CHI" ( type "VARCHAR", NotNull : true ) 
     * @return the field value
     */
    public String getDIA_CHI()
    {
        return this.DIA_CHI;
    }

    //--- DATABASE MAPPING : MA_THE ( VARCHAR ) 
    /**
     * Set the "MA_THE" field value
     * This field is mapped on the database column "MA_THE" ( type "VARCHAR", NotNull : false ) 
     * @param MA_THE
     */
    public void setMA_THE( String MA_THE )
    {
        this.MA_THE = MA_THE;
    }
    /**
     * Get the "maThe" field value
     * This field is mapped on the database column "MA_THE" ( type "VARCHAR", NotNull : false ) 
     * @return the field value
     */
    public String getMA_THE()
    {
        return this.MA_THE;
    }

    //--- DATABASE MAPPING : MA_DKBD ( VARCHAR ) 
    /**
     * Set the "MA_DKBD" field value
     * This field is mapped on the database column "MA_DKBD" ( type "VARCHAR", NotNull : true ) 
     * @param MA_DKBD
     */
    public void setMA_DKBD( String MA_DKBD )
    {
        this.MA_DKBD = MA_DKBD;
    }
    /**
     * Get the "maDkbd" field value
     * This field is mapped on the database column "MA_DKBD" ( type "VARCHAR", NotNull : true ) 
     * @return the field value
     */
    public String getMA_DKBD()
    {
        return this.MA_DKBD;
    }

    //--- DATABASE MAPPING : GT_THE_TU ( VARCHAR ) 
    /**
     * Set the "GT_THE_TU" field value
     * This field is mapped on the database column "GT_THE_TU" ( type "VARCHAR", NotNull : true ) 
     * @param GT_THE_TU
     */
    public void setGT_THE_TU( String GT_THE_TU )
    {
        this.GT_THE_TU = GT_THE_TU;
    }
    /**
     * Get the "gtTheTu" field value
     * This field is mapped on the database column "GT_THE_TU" ( type "VARCHAR", NotNull : true ) 
     * @return the field value
     */
    public String getGT_THE_TU()
    {
        return this.GT_THE_TU;
    }

    //--- DATABASE MAPPING : GT_THE_DEN ( VARCHAR ) 
    /**
     * Set the "GT_THE_DEN" field value
     * This field is mapped on the database column "GT_THE_DEN" ( type "VARCHAR", NotNull : true ) 
     * @param GT_THE_DEN
     */
    public void setGT_THE_DEN( String GT_THE_DEN )
    {
        this.GT_THE_DEN = GT_THE_DEN;
    }
    /**
     * Get the "gtTheDen" field value
     * This field is mapped on the database column "GT_THE_DEN" ( type "VARCHAR", NotNull : true ) 
     * @return the field value
     */
    public String getGT_THE_DEN()
    {
        return this.GT_THE_DEN;
    }

    //--- DATABASE MAPPING : NGAY_CAP ( VARCHAR ) 
    /**
     * Set the "NGAY_CAP" field value
     * This field is mapped on the database column "NGAY_CAP" ( type "VARCHAR", NotNull : true ) 
     * @param NGAY_CAP
     */
    public void setNGAY_CAP( String NGAY_CAP )
    {
        this.NGAY_CAP = NGAY_CAP;
    }
    /**
     * Get the "ngayCap" field value
     * This field is mapped on the database column "NGAY_CAP" ( type "VARCHAR", NotNull : true ) 
     * @return the field value
     */
    public String getNGAY_CAP()
    {
        return this.NGAY_CAP;
    }

    //--- DATABASE MAPPING : MA_QUAN_LY ( VARCHAR ) 
    /**
     * Set the "MA_QUAN_LY" field value
     * This field is mapped on the database column "MA_QUAN_LY" ( type "VARCHAR", NotNull : true ) 
     * @param MA_QUAN_LY
     */
    public void setMA_QUAN_LY( String MA_QUAN_LY )
    {
        this.MA_QUAN_LY = MA_QUAN_LY;
    }
    /**
     * Get the "maQuanLy" field value
     * This field is mapped on the database column "MA_QUAN_LY" ( type "VARCHAR", NotNull : true ) 
     * @return the field value
     */
    public String getMA_QUAN_LY()
    {
        return this.MA_QUAN_LY;
    }

    //--- DATABASE MAPPING : TEN_CHA_ME ( VARCHAR ) 
    /**
     * Set the "TEN_CHA_ME" field value
     * This field is mapped on the database column "TEN_CHA_ME" ( type "VARCHAR", NotNull : true ) 
     * @param TEN_CHA_ME
     */
    public void setTEN_CHA_ME( String TEN_CHA_ME )
    {
        this.TEN_CHA_ME = TEN_CHA_ME;
    }
    /**
     * Get the "tenChaMe" field value
     * This field is mapped on the database column "TEN_CHA_ME" ( type "VARCHAR", NotNull : true ) 
     * @return the field value
     */
    public String getTEN_CHA_ME()
    {
        return this.TEN_CHA_ME;
    }

    //--- DATABASE MAPPING : MA_DT_SONG ( INT ) 
    /**
     * Set the "MA_DT_SONG" field value
     * This field is mapped on the database column "MA_DT_SONG" ( type "INT", NotNull : true ) 
     * @param MA_DT_SONG
     */
    public void setMA_DT_SONG( Integer MA_DT_SONG )
    {
        this.MA_DT_SONG = MA_DT_SONG;
    }
    /**
     * Get the "maDtSong" field value
     * This field is mapped on the database column "MA_DT_SONG" ( type "INT", NotNull : true ) 
     * @return the field value
     */
    public Integer getMA_DT_SONG()
    {
        return this.MA_DT_SONG;
    }

    //--- DATABASE MAPPING : THOIDIEM_NAMNAM ( VARCHAR ) 
    /**
     * Set the "THOIDIEM_NAMNAM" field value
     * This field is mapped on the database column "THOIDIEM_NAMNAM" ( type "VARCHAR", NotNull : true ) 
     * @param THOIDIEM_NAMNAM
     */
    public void setTHOIDIEM_NAMNAM( String THOIDIEM_NAMNAM )
    {
        this.THOIDIEM_NAMNAM = THOIDIEM_NAMNAM;
    }
    /**
     * Get the "thoidiemNamnam" field value
     * This field is mapped on the database column "THOIDIEM_NAMNAM" ( type "VARCHAR", NotNull : true ) 
     * @return the field value
     */
    public String getTHOIDIEM_NAMNAM()
    {
        return this.THOIDIEM_NAMNAM;
    }

    //--- DATABASE MAPPING : CHUOI_KIEM_TRA ( VARCHAR ) 
    /**
     * Set the "CHUOI_KIEM_TRA" field value
     * This field is mapped on the database column "CHUOI_KIEM_TRA" ( type "VARCHAR", NotNull : true ) 
     * @param CHUOI_KIEM_TRA
     */
    public void setCHUOI_KIEM_TRA( String CHUOI_KIEM_TRA )
    {
        this.CHUOI_KIEM_TRA = CHUOI_KIEM_TRA;
    }
    /**
     * Get the "chuoiKiemTra" field value
     * This field is mapped on the database column "CHUOI_KIEM_TRA" ( type "VARCHAR", NotNull : true ) 
     * @return the field value
     */
    public String getCHUOI_KIEM_TRA()
    {
        return this.CHUOI_KIEM_TRA;
    }

    //--- DATABASE MAPPING : DATE_ADD ( TIMESTAMP ) 
    /**
     * Set the "DATE_ADD" field value
     * This field is mapped on the database column "DATE_ADD" ( type "TIMESTAMP", NotNull : false ) 
     * @param DATE_ADD
     */
    public void setDATE_ADD( Date DATE_ADD )
    {
        this.DATE_ADD = DATE_ADD;
    }
    /**
     * Get the "dateAdd" field value
     * This field is mapped on the database column "DATE_ADD" ( type "TIMESTAMP", NotNull : false ) 
     * @return the field value
     */
    public Date getDATE_ADD()
    {
        return this.DATE_ADD;
    }

    //--- DATABASE MAPPING : LAST_EDIT ( TIMESTAMP ) 
    /**
     * Set the "LAST_EDIT" field value
     * This field is mapped on the database column "LAST_EDIT" ( type "TIMESTAMP", NotNull : false ) 
     * @param LAST_EDIT
     */
    public void setLAST_EDIT( Date LAST_EDIT )
    {
        this.LAST_EDIT = LAST_EDIT;
    }
    /**
     * Get the "lastEdit" field value
     * This field is mapped on the database column "LAST_EDIT" ( type "TIMESTAMP", NotNull : false ) 
     * @return the field value
     */
    public Date getLAST_EDIT()
    {
        return this.LAST_EDIT;
    }

    //--- DATABASE MAPPING : GATE_INFO ( VARCHAR ) 
    /**
     * Set the "GATE_INFO" field value
     * This field is mapped on the database column "GATE_INFO" ( type "VARCHAR", NotNull : false ) 
     * @param GATE_INFO
     */
    public void setGATE_INFO( String GATE_INFO )
    {
        this.GATE_INFO = GATE_INFO;
    }
    /**
     * Get the "gateInfo" field value
     * This field is mapped on the database column "GATE_INFO" ( type "VARCHAR", NotNull : false ) 
     * @return the field value
     */
    public String getGATE_INFO()
    {
        return this.GATE_INFO;
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
    public static BenhNhan load(String BN_ID) {
    	Connection connection = null;

		try {
			connection = DbHelper.getSql2o();
			String sql = "select * from benh_nhan where BN_ID=:BN_ID";
			logger.debug(sql);
			BenhNhan obj = connection
					.createQuery(
							sql,
							true)
					.addParameter("BN_ID", BN_ID)
					.executeAndFetchFirst( BenhNhan.class);
            logger.info(obj);
            logger.debug("Load "+obj);
            return obj;
		} catch (Exception t) {
            logger.error("Load Error: ID="+BN_ID +"\nException=\n"+ t);
			throw new RuntimeException("LOAD [BenhNhan]. ERROR: "+t.getMessage(), t);
		}
    }
    
    public static BenhNhan load(Integer BN_ID) {
    	Connection connection = null;

		try {
			connection = DbHelper.getSql2o();
			String sql = "select * from benh_nhan where BN_ID=:BN_ID";
			logger.debug(sql);
			BenhNhan obj = connection
					.createQuery(
							sql,
							true)
					.addParameter("BN_ID", BN_ID)
					.executeAndFetchFirst( BenhNhan.class);
            logger.info(obj);
            logger.debug("Load "+obj);
            return obj;
		} catch (Exception t) {
            logger.error("Load Error: ID="+BN_ID +"\nException=\n"+ t);
			throw new RuntimeException("LOAD [BenhNhan]. ERROR: "+t.getMessage(), t);
		}
    }
    
    public String toString(){
    	String strData = "benh_nhan "
    			+" BN_ID = " + BN_ID                + "; HO_TEN = " + HO_TEN
                + "; NGAY_SINH = " + NGAY_SINH
                + "; GIOI_TINH = " + GIOI_TINH
                + "; DIA_CHI = " + DIA_CHI
                + "; MA_THE = " + MA_THE
                + "; MA_DKBD = " + MA_DKBD
                + "; GT_THE_TU = " + GT_THE_TU
                + "; GT_THE_DEN = " + GT_THE_DEN
                + "; NGAY_CAP = " + NGAY_CAP
                + "; MA_QUAN_LY = " + MA_QUAN_LY
                + "; TEN_CHA_ME = " + TEN_CHA_ME
                + "; MA_DT_SONG = " + MA_DT_SONG
                + "; THOIDIEM_NAMNAM = " + THOIDIEM_NAMNAM
                + "; CHUOI_KIEM_TRA = " + CHUOI_KIEM_TRA
                + "; DATE_ADD = " + DATE_ADD
                + "; LAST_EDIT = " + LAST_EDIT
                + "; GATE_INFO = " + GATE_INFO
                + "; STS = " + STS
    			;
    	return strData;
    }
    
    public void insert() {
		//
    	if( BN_ID !=null){
			// Update 
            update();
			return;
		}
        if(DbHelper.checkPhanQuyen(DbHelper.INSERT, "benh_nhan")==false){
            logger.info("YOU (" + DbHelper.getCurrentSessionUserName() + "), DON'T HAVE INSERT RIGHT for table benh_nhan");
            return;
        }
		Connection connection = null;

		try {
			connection = DbHelper.getSql2o();
            connection.setRollbackOnException(false);

            logger.info("Insert [benh_nhan] BEGIN: "+ this.toString());

            String sql = "insert into benh_nhan (HO_TEN, NGAY_SINH, GIOI_TINH, DIA_CHI, MA_THE, MA_DKBD, GT_THE_TU, GT_THE_DEN, NGAY_CAP, MA_QUAN_LY, TEN_CHA_ME, MA_DT_SONG, THOIDIEM_NAMNAM, CHUOI_KIEM_TRA, DATE_ADD, LAST_EDIT, GATE_INFO,  STS) values(:HO_TEN, :NGAY_SINH, :GIOI_TINH, :DIA_CHI, :MA_THE, :MA_DKBD, :GT_THE_TU, :GT_THE_DEN, :NGAY_CAP, :MA_QUAN_LY, :TEN_CHA_ME, :MA_DT_SONG, :THOIDIEM_NAMNAM, :CHUOI_KIEM_TRA, :DATE_ADD, :LAST_EDIT, :GATE_INFO,  0)";
                            			int createdId = connection
                            					.createQuery(
							sql,
							true)
                        .addParameter("HO_TEN", this.HO_TEN)
                            .addParameter("NGAY_SINH", this.NGAY_SINH)
                            .addParameter("GIOI_TINH", this.GIOI_TINH)
                            .addParameter("DIA_CHI", this.DIA_CHI)
                            .addParameter("MA_THE", this.MA_THE)
                            .addParameter("MA_DKBD", this.MA_DKBD)
                            .addParameter("GT_THE_TU", this.GT_THE_TU)
                            .addParameter("GT_THE_DEN", this.GT_THE_DEN)
                            .addParameter("NGAY_CAP", this.NGAY_CAP)
                            .addParameter("MA_QUAN_LY", this.MA_QUAN_LY)
                            .addParameter("TEN_CHA_ME", this.TEN_CHA_ME)
                            .addParameter("MA_DT_SONG", this.MA_DT_SONG)
                            .addParameter("THOIDIEM_NAMNAM", this.THOIDIEM_NAMNAM)
                            .addParameter("CHUOI_KIEM_TRA", this.CHUOI_KIEM_TRA)
                            .addParameter("DATE_ADD", this.DATE_ADD)
                            .addParameter("LAST_EDIT", this.LAST_EDIT)
                            .addParameter("GATE_INFO", this.GATE_INFO)
        					.executeUpdate()
                                                .getKey(Integer.class);
                            					
            logger.info("Add OK: NewID="+createdId);
            logger.info("Add OK [benh_nhan] debug: "+toString());
            // Set KEYID BN_ID = newID
                            BN_ID = createdId;
                            			// Save log
            connection
					.createQuery(
							"insert into action_log(u_id,u_action,dbtable,actionid,fieldid) values(:u_id,:u_action,:dbtable,:actionid,:fieldid)",
							true)
					.addParameter("u_id", DbHelper.getCurrentSessionUserId())
					.addParameter("u_action", "New benh_nhan. ID="+BN_ID)
                    .addParameter("dbtable", "benh_nhan")
                    .addParameter("fieldid", BN_ID)
					.addParameter("actionid", 1)
					.executeUpdate();
            logger.info("Add [benh_nhan] OK: NewID="+createdId);
            logger.info("Add [benh_nhan] OK: Data="+this.toString());
		} catch (Exception t) {
            logger.error("Add [benh_nhan] Error: "+"\nException=\n"+ t);
			throw new RuntimeException("ADD [benh_nhan]. ERROR: "+t.getMessage(), t);
		}
	}
    
    public void update() {
		//
    	if(BN_ID==null){
            insert();
			return;
		}
        if(DbHelper.checkPhanQuyen(DbHelper.UPDATE, "benh_nhan")==false){
            logger.info("YOU (" + DbHelper.getCurrentSessionUserName() + "), DON'T HAVE UPDATE RIGHT for table benh_nhan");
            return;
        }
		Connection connection = null;

		try {
			connection = DbHelper.getSql2o();
            connection.setRollbackOnException(false);
            BenhNhan temp = BenhNhan.load(this.BN_ID);
            if(temp!=null){
            	logger.info("Begin Update [benh_nhan]: "+temp.toString());
            }
            logger.info("Update [benh_nhan] BEGIN: "+ this.toString());
			//
			String sql = "update benh_nhan set HO_TEN=:HO_TEN, NGAY_SINH=:NGAY_SINH, GIOI_TINH=:GIOI_TINH, DIA_CHI=:DIA_CHI, MA_THE=:MA_THE, MA_DKBD=:MA_DKBD, GT_THE_TU=:GT_THE_TU, GT_THE_DEN=:GT_THE_DEN, NGAY_CAP=:NGAY_CAP, MA_QUAN_LY=:MA_QUAN_LY, TEN_CHA_ME=:TEN_CHA_ME, MA_DT_SONG=:MA_DT_SONG, THOIDIEM_NAMNAM=:THOIDIEM_NAMNAM, CHUOI_KIEM_TRA=:CHUOI_KIEM_TRA, DATE_ADD=:DATE_ADD, LAST_EDIT=:LAST_EDIT, GATE_INFO=:GATE_INFO, STS=:STS where BN_ID=:BN_ID";
			connection
					.createQuery(
							sql,
							true)
                    .addParameter("HO_TEN", this.HO_TEN)
                    .addParameter("NGAY_SINH", this.NGAY_SINH)
                    .addParameter("GIOI_TINH", this.GIOI_TINH)
                    .addParameter("DIA_CHI", this.DIA_CHI)
                    .addParameter("MA_THE", this.MA_THE)
                    .addParameter("MA_DKBD", this.MA_DKBD)
                    .addParameter("GT_THE_TU", this.GT_THE_TU)
                    .addParameter("GT_THE_DEN", this.GT_THE_DEN)
                    .addParameter("NGAY_CAP", this.NGAY_CAP)
                    .addParameter("MA_QUAN_LY", this.MA_QUAN_LY)
                    .addParameter("TEN_CHA_ME", this.TEN_CHA_ME)
                    .addParameter("MA_DT_SONG", this.MA_DT_SONG)
                    .addParameter("THOIDIEM_NAMNAM", this.THOIDIEM_NAMNAM)
                    .addParameter("CHUOI_KIEM_TRA", this.CHUOI_KIEM_TRA)
                    .addParameter("DATE_ADD", this.DATE_ADD)
                    .addParameter("LAST_EDIT", this.LAST_EDIT)
                    .addParameter("GATE_INFO", this.GATE_INFO)
                    .addParameter("STS", this.STS)
					.addParameter("BN_ID", this.BN_ID)
					.executeUpdate();
            logger.info("Update [benh_nhan] OK: ID="+BN_ID);
            logger.info("End   Update [benh_nhan]: "+toString());
            connection
					.createQuery(
							"insert into action_log(u_id,u_action,dbtable,actionid,fieldid) values(:u_id,:u_action,:dbtable,:actionid,:fieldid)",
							true)
					.addParameter("u_id", DbHelper.getCurrentSessionUserId())
					.addParameter("u_action", "Update benh_nhan. ID="+BN_ID)
                    .addParameter("dbtable", "benh_nhan")
                    .addParameter("fieldid", BN_ID)
					.addParameter("actionid", 2)
					.executeUpdate();
		} catch (Exception t) {
			logger.error("Update [benh_nhan] Error: ID=["+BN_ID +"]."+"\nException=\n"+ t);
			throw new RuntimeException("Update [benh_nhan]. ERROR: "+t.getMessage(), t);
		}
	}
    
    
    public void delete() {
		//
    	if(BN_ID==null){
			return;
		}
        if(DbHelper.checkPhanQuyen(DbHelper.DELETE, "benh_nhan")==false){
            logger.info("YOU (" + DbHelper.getCurrentSessionUserName() + "), DON'T HAVE DELETE RIGHT for table benh_nhan");
            return;
        }
		Connection connection = null;

		try {
			connection = DbHelper.getSql2o();
            connection.setRollbackOnException(false);
            logger.error("Update Row [benh_nhan] BEGIN: ID=["+BN_ID +"]. " + this.toString());
			String sql = "update benh_nhan set STS=:STS where BN_ID=:BN_ID";
			connection
					.createQuery(
							sql,
							true)
					.addParameter("STS", DbHelper.DELETE_STATUS)
					.addParameter("BN_ID", this.BN_ID)
					.executeUpdate();
			logger.error("Delete [benh_nhan] OK: ID=["+BN_ID +"].");
            connection
					.createQuery(
							"insert into action_log(u_id,u_action,dbtable,actionid,fieldid) values(:u_id,:u_action,:dbtable,:actionid,:fieldid)",
							true)
					.addParameter("u_id", DbHelper.getCurrentSessionUserId())
					.addParameter("u_action", "Update benh_nhan. ID="+BN_ID)
                    .addParameter("dbtable", "benh_nhan")
                    .addParameter("fieldid", BN_ID)
					.addParameter("actionid", 3)
					.executeUpdate();
		} catch (Exception t) {
			logger.error("Delete [benh_nhan] Error: ID=["+BN_ID +"]."+"\nException=\n"+ t);
			throw new RuntimeException("Delete [benh_nhan]. ID=["+BN_ID + " ERROR: "+t.getMessage(), t);
		}
	}
    
	public void deleteRow() {
		//
    	if(BN_ID==null){
			return;
		}
        if(DbHelper.checkPhanQuyen(DbHelper.DELETE, "benh_nhan")==false){
            logger.info("YOU (" + DbHelper.getCurrentSessionUserName() + "), DON'T HAVE DELETE RIGHT for table benh_nhan");
            return;
        }
		Connection connection = null;

		try {
			connection = DbHelper.getSql2o();
            connection.setRollbackOnException(false);
            logger.error("Delete Row [benh_nhan] BEGIN: ID=["+BN_ID +"]. " + this.toString());
			String sql = "delete from benh_nhan where BN_ID=:BN_ID";
			connection
					.createQuery(
							sql,
							true)
					.addParameter("BN_ID", this.BN_ID)
					.executeUpdate();
			logger.error("Delete Row [benh_nhan] OK: ID=["+BN_ID +"].");
            connection
					.createQuery(
							"insert into action_log(u_id,u_action,dbtable,actionid,fieldid) values(:u_id,:u_action,:dbtable,:actionid,:fieldid)",
							true)
					.addParameter("u_id", DbHelper.getCurrentSessionUserId())
					.addParameter("u_action", "Update benh_nhan. ID="+BN_ID)
                    .addParameter("dbtable", "benh_nhan")
                    .addParameter("fieldid", BN_ID)
					.addParameter("actionid", 4)
					.executeUpdate();
		} catch (Exception t) {
			logger.error("Delete [benh_nhan] Error: ID=["+BN_ID +"]."+"\nException=\n"+ t);
			throw new RuntimeException("Delete [benh_nhan]. ID=["+BN_ID + " ERROR: "+t.getMessage(), t);
		}
	}
    
    public String getIndex(int idx){
        switch(idx){
            case 0:
                // java.lang.String    
                if(this.HO_TEN!=null)
                    return this.HO_TEN.toString();
                else
                    return "N/A";
            case 1:
                // java.lang.String    
                if(this.NGAY_SINH!=null)
                    return this.NGAY_SINH.toString();
                else
                    return "N/A";
            case 2:
                // java.lang.Integer   
                if(this.GIOI_TINH!=null)
                    return this.GIOI_TINH.toString();
                else
                    return "N/A";
            case 3:
                // java.lang.String    
                if(this.DIA_CHI!=null)
                    return this.DIA_CHI.toString();
                else
                    return "N/A";
            case 4:
                // java.lang.String    
                if(this.MA_THE!=null)
                    return this.MA_THE.toString();
                else
                    return "N/A";
            case 5:
                // java.lang.String    
                if(this.MA_DKBD!=null)
                    return this.MA_DKBD.toString();
                else
                    return "N/A";
            case 6:
                // java.lang.String    
                if(this.GT_THE_TU!=null)
                    return this.GT_THE_TU.toString();
                else
                    return "N/A";
            case 7:
                // java.lang.String    
                if(this.GT_THE_DEN!=null)
                    return this.GT_THE_DEN.toString();
                else
                    return "N/A";
            case 8:
                // java.lang.String    
                if(this.NGAY_CAP!=null)
                    return this.NGAY_CAP.toString();
                else
                    return "N/A";
            case 9:
                // java.lang.String    
                if(this.MA_QUAN_LY!=null)
                    return this.MA_QUAN_LY.toString();
                else
                    return "N/A";
            case 10:
                // java.lang.String    
                if(this.TEN_CHA_ME!=null)
                    return this.TEN_CHA_ME.toString();
                else
                    return "N/A";
            case 11:
                // java.lang.Integer   
                if(this.MA_DT_SONG!=null)
                    return this.MA_DT_SONG.toString();
                else
                    return "N/A";
            case 12:
                // java.lang.String    
                if(this.THOIDIEM_NAMNAM!=null)
                    return this.THOIDIEM_NAMNAM.toString();
                else
                    return "N/A";
            case 13:
                // java.lang.String    
                if(this.CHUOI_KIEM_TRA!=null)
                    return this.CHUOI_KIEM_TRA.toString();
                else
                    return "N/A";
            case 14:
                // java.lang.Date      
                if(this.DATE_ADD!=null)
                    return this.DATE_ADD.toString();
                else
                    return "N/A";
            case 15:
                // java.lang.Date      
                if(this.LAST_EDIT!=null)
                    return this.LAST_EDIT.toString();
                else
                    return "N/A";
            case 16:
                // java.lang.String    
                if(this.GATE_INFO!=null)
                    return this.GATE_INFO.toString();
                else
                    return "N/A";
            case 17:
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