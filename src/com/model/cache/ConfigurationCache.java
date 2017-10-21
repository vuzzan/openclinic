/*
 * Very basic JavaBean cache
 * Created on 17 Jul 2017 ( Date ISO 2017-07-17 - Time 08:25:18 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */

package com.model.cache;

import java.util.Hashtable;
import java.util.ArrayList;
import java.util.Map;

import com.model.dao.Configuration ;

/**
 * Very basic cache for Configuration instances (just for the Telosys Tools demo)
 * 
 * @author Telosys Tools Generator
 *
 */
public class ConfigurationCache
{
	private final static Map<String,Configuration> cache = new Hashtable<String,Configuration>() ;
	public final static ArrayList<String> cacheArrayListKey = new ArrayList<String>() ;
	
	/**
	 * Build the cache key from the Primary Key field(s)
     * @param confId CONF_ID 
	 * @return the key
	 */
	private final static String getKey( Integer confId ) {
		return ""  + confId  ;
	}

	/**
	 * Put the given Configuration instance in the cache
	 * @param Configuration instance to be stored
	 */
	public final static void putConfiguration(Configuration configuration ) {
		String key = getKey( configuration.getCONF_ID());
		cache.put(key, configuration );
	}
	
	/**
	 * Get the Configuration instance for the given primary key
     * @param confId 
	 * @return the Configuration instance (or null if none)
	 */
	public final static Configuration getConfiguration( Integer confId ) {
		String key = getKey( confId ) ;
		return cache.get(key);
	}

	/**
	 * Removes the Configuration associated with the given primary key
     * @param confId 
	 */
	public final static void removeConfiguration ( Integer confId ) {
		String key = getKey( confId ) ;
		cache.remove(key);
	}
	
	/**
	 * Removes the given Configuration from the cache using its primary key
	 * @param Configuration instance to be removed
	 */
	public final static void removeConfiguration (Configuration configuration ) { 
		String key = getKey( configuration.getCONF_ID());
		cache.remove(key);
	}

}
