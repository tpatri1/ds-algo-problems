package com.trinath.concurrency;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Implement a cache server that allows concurrent access from multiple readers and writers.
 *  The server keeps up to N cache entries for a backend DB.
 *
 *  Writers call put() to add/update entries into the system and return true when values are saved to backend DB.
 *
 *  Readers call get() with a key to retrieve an entry from the system. The call returns the latest updated entry if it exists, or an empty string if it does not  exist in backendDB.
 *
 *  public interface Cache {
 *      String get(String key);
 *      boolean put(String key, String value);
 *      float  cacheHitRatio();
 *  }
 */

 class DbAccess {
    public String readDataByKey(String key){
        return "";
    }
    public void writeDataByKey(String key, String value){

    }
}
//Cache eviction - LRU, LFU,
//Warming up of the cache
//N nodes-
//data partioning
//Routing
//Replication
//driver itself -
//hf(key) --> node -->> node added and dleted-->> Consistent hashing instead(

public class Cache {
    DbAccess dbAccess = new DbAccess();
    Map<String, String> cache = new ConcurrentHashMap<String, String>();
    AtomicInteger hit = new AtomicInteger(0);
    AtomicInteger total = new AtomicInteger(0);
    //Bootstrap cache

    String get(String key){
        total.incrementAndGet();
        if(cache.containsKey(key)){
            hit.incrementAndGet();
            return cache.get(key);
        }
        String value = dbAccess.readDataByKey(key);
        if(!value.isEmpty()){
            cache.put(key, value);
        }

        return value;
    }

    void  put(String key, String value){
        //check if that key value exist in cache else
        if(cache.containsKey(key) && cache.containsKey(key.equals(value))){
            return;
        }
        cache.put(key, value);
        dbAccess.writeDataByKey(key,value);

    }

    double cacheHitRatio(){
        return hit.get()/total.get();
    }

}
