package com.miles.testexport.com.miles.demo;

import org.mapdb.DB;
import org.mapdb.DBMaker;

import java.util.concurrent.ConcurrentMap;

/**
 * @author Miles Hoo
 * @version v1.0.0
 * @since 2018/9/5 19:38
 */
public class MapDBDemo1 {

    public static void main(String[] args) {
        DB db = DBMaker.memoryDB().make();
        ConcurrentMap map = db.hashMap("map").createOrOpen();
        map.put("something", "here");
    }

}
