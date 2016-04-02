package com.github.mongo;


import java.net.UnknownHostException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.MongoException;

/**
 * Hello world!
 *
 */
public class MongoTest 
{
    private Mongo mg;
    private DB temp;
    DBCollection information;
    final String host = "server1";
    final int port =12349;
    @Before
    public void init() {
        try {
            mg = new Mongo(host,port);
        }
        catch (UnknownHostException e) {
            
            e.printStackTrace();
            
        }
        catch (MongoException e) {
            
            e.printStackTrace();
            
        }
        temp = mg.getDB("temp");
        information = temp.getCollection("information");
    }
    @After
    public void destory() {
        if (mg != null)
            mg.close();
        mg = null;
        temp = null;
        information = null;
        System.gc();
    }
    
    
    @Test
    // 增
    public void add(){
        DBObject obj = new BasicDBObject() ;
        obj.put("name", "zhangsan");
        obj.put("age", 18);
        obj.put("sex", "男");
        information.insert(obj );
        
    }
    @Test
    // 改
    public void modify(){
        DBObject ref = new BasicDBObject() ;
        ref.put("name", "zhangsan");
        DBCursor find = information.find(ref);
        DBObject o = new BasicDBObject() ;
        o.put("name", "lisi");
        if(find.hasNext()){
            information.update(find.next(), o);
        }
        
    }
    
    @Test
    // 删
    public void del(){
        DBObject ref = new BasicDBObject() ;
        ref.put("name", "lisi");
        information.remove(ref);
        
    }
}
