/*
 * Very basic JavaBean cache
 * Created on 17 Jul 2017 ( Date ISO 2017-07-17 - Time 08:25:19 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */

package com.model.cache;

import java.util.Hashtable;
import java.util.ArrayList;
import java.util.Map;

import com.model.dao.Vendor ;

/**
 * Very basic cache for Vendor instances (just for the Telosys Tools demo)
 * 
 * @author Telosys Tools Generator
 *
 */
public class VendorCache
{
	private final static Map<String,Vendor> cache = new Hashtable<String,Vendor>() ;
	public final static ArrayList<String> cacheArrayListKey = new ArrayList<String>() ;
	
	/**
	 * Build the cache key from the Primary Key field(s)
     * @param vId V_ID 
	 * @return the key
	 */
	private final static String getKey( Integer vId ) {
		return ""  + vId  ;
	}

	/**
	 * Put the given Vendor instance in the cache
	 * @param Vendor instance to be stored
	 */
	public final static void putVendor(Vendor vendor ) {
		String key = getKey( vendor.getV_ID());
		cache.put(key, vendor );
	}
	
	/**
	 * Get the Vendor instance for the given primary key
     * @param vId 
	 * @return the Vendor instance (or null if none)
	 */
	public final static Vendor getVendor( Integer vId ) {
		String key = getKey( vId ) ;
		return cache.get(key);
	}

	/**
	 * Removes the Vendor associated with the given primary key
     * @param vId 
	 */
	public final static void removeVendor ( Integer vId ) {
		String key = getKey( vId ) ;
		cache.remove(key);
	}
	
	/**
	 * Removes the given Vendor from the cache using its primary key
	 * @param Vendor instance to be removed
	 */
	public final static void removeVendor (Vendor vendor ) { 
		String key = getKey( vendor.getV_ID());
		cache.remove(key);
	}

}
