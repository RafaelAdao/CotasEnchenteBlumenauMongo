package br.furb.core;

import com.mongodb.DBCollection;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;

public class MongoProvider {

	public static MongoProvider MongoBD = new MongoProvider();
	
	private MongoClient mongoClient;	
	private Mongo mongo;	
	
	public MongoProvider() {
		mongoClient = new MongoClient("localhost", 27017);
		mongo = new Mongo("localhost", 27017);		
	}
	
	public DBCollection cotas() {
		return mongo.getDB("bdii2015").getCollection("cotas");
	}
	
	public MongoCollection cotasCli() {
		return mongoClient.getDatabase("bdii2015").getCollection("cotas");
	}
}
