/*
 * Very basic JavaBean cache
 * Created on 17 Jul 2017 ( Date ISO 2017-07-17 - Time 08:25:19 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */

package com.model.cache;

import java.util.Hashtable;
import java.util.ArrayList;
import java.util.Map;

import com.model.dao.Payment ;

/**
 * Very basic cache for Payment instances (just for the Telosys Tools demo)
 * 
 * @author Telosys Tools Generator
 *
 */
public class PaymentCache
{
	private final static Map<String,Payment> cache = new Hashtable<String,Payment>() ;
	public final static ArrayList<String> cacheArrayListKey = new ArrayList<String>() ;
	
	/**
	 * Build the cache key from the Primary Key field(s)
     * @param paidId PAID_ID 
	 * @return the key
	 */
	private final static String getKey( Integer paidId ) {
		return ""  + paidId  ;
	}

	/**
	 * Put the given Payment instance in the cache
	 * @param Payment instance to be stored
	 */
	public final static void putPayment(Payment payment ) {
		String key = getKey( payment.getPAID_ID());
		cache.put(key, payment );
	}
	
	/**
	 * Get the Payment instance for the given primary key
     * @param paidId 
	 * @return the Payment instance (or null if none)
	 */
	public final static Payment getPayment( Integer paidId ) {
		String key = getKey( paidId ) ;
		return cache.get(key);
	}

	/**
	 * Removes the Payment associated with the given primary key
     * @param paidId 
	 */
	public final static void removePayment ( Integer paidId ) {
		String key = getKey( paidId ) ;
		cache.remove(key);
	}
	
	/**
	 * Removes the given Payment from the cache using its primary key
	 * @param Payment instance to be removed
	 */
	public final static void removePayment (Payment payment ) { 
		String key = getKey( payment.getPAID_ID());
		cache.remove(key);
	}

}
