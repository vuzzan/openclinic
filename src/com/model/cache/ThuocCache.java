/*
 * Very basic JavaBean cache
 * Created on 17 Jul 2017 ( Date ISO 2017-07-17 - Time 08:25:19 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */

package com.model.cache;

import java.util.Hashtable;
import java.util.ArrayList;
import java.util.Map;

import com.model.dao.Thuoc ;

/**
 * Very basic cache for Thuoc instances (just for the Telosys Tools demo)
 * 
 * @author Telosys Tools Generator
 *
 */
public class ThuocCache
{
	private final static Map<String,Thuoc> cache = new Hashtable<String,Thuoc>() ;
	public final static ArrayList<String> cacheArrayListKey = new ArrayList<String>() ;
	
	/**
	 * Build the cache key from the Primary Key field(s)
     * @param thuocId THUOC_ID 
	 * @return the key
	 */
	private final static String getKey( Integer thuocId ) {
		return ""  + thuocId  ;
	}

	/**
	 * Put the given Thuoc instance in the cache
	 * @param Thuoc instance to be stored
	 */
	public final static void putThuoc(Thuoc thuoc ) {
		String key = getKey( thuoc.getTHUOC_ID());
		cache.put(key, thuoc );
	}
	
	/**
	 * Get the Thuoc instance for the given primary key
     * @param thuocId 
	 * @return the Thuoc instance (or null if none)
	 */
	public final static Thuoc getThuoc( Integer thuocId ) {
		String key = getKey( thuocId ) ;
		return cache.get(key);
	}

	/**
	 * Removes the Thuoc associated with the given primary key
     * @param thuocId 
	 */
	public final static void removeThuoc ( Integer thuocId ) {
		String key = getKey( thuocId ) ;
		cache.remove(key);
	}
	
	/**
	 * Removes the given Thuoc from the cache using its primary key
	 * @param Thuoc instance to be removed
	 */
	public final static void removeThuoc (Thuoc thuoc ) { 
		String key = getKey( thuoc.getTHUOC_ID());
		cache.remove(key);
	}

}
