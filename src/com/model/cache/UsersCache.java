/*
 * Very basic JavaBean cache
 * Created on 17 Jul 2017 ( Date ISO 2017-07-17 - Time 08:25:19 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */

package com.model.cache;

import java.util.Hashtable;
import java.util.ArrayList;
import java.util.Map;

import com.model.dao.Users ;

/**
 * Very basic cache for Users instances (just for the Telosys Tools demo)
 * 
 * @author Telosys Tools Generator
 *
 */
public class UsersCache
{
	private final static Map<String,Users> cache = new Hashtable<String,Users>() ;
	public final static ArrayList<String> cacheArrayListKey = new ArrayList<String>() ;
	
	/**
	 * Build the cache key from the Primary Key field(s)
     * @param uId U_ID 
	 * @return the key
	 */
	private final static String getKey( Integer uId ) {
		return ""  + uId  ;
	}

	/**
	 * Put the given Users instance in the cache
	 * @param Users instance to be stored
	 */
	public final static void putUsers(Users users ) {
		String key = getKey( users.getU_ID());
		cache.put(key, users );
	}
	
	/**
	 * Get the Users instance for the given primary key
     * @param uId 
	 * @return the Users instance (or null if none)
	 */
	public final static Users getUsers( Integer uId ) {
		String key = getKey( uId ) ;
		return cache.get(key);
	}

	/**
	 * Removes the Users associated with the given primary key
     * @param uId 
	 */
	public final static void removeUsers ( Integer uId ) {
		String key = getKey( uId ) ;
		cache.remove(key);
	}
	
	/**
	 * Removes the given Users from the cache using its primary key
	 * @param Users instance to be removed
	 */
	public final static void removeUsers (Users users ) { 
		String key = getKey( users.getU_ID());
		cache.remove(key);
	}

}
