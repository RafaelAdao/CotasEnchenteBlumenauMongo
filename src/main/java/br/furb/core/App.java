package br.furb.core;

import java.util.Arrays;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		
		double[] coordenadas = new double[2];
		//{ $and: [{id : "blumenau_cartaenchente10m.15"}, { geometry: { $geoIntersects: { $geometry: { "type": "Point", "coordinates": [-49.04407505632429, -26.83920259603063] } } } }]}
		coordenadas[0] = -49.04407505632429;
		coordenadas[1] = -26.83920259603063;
		
		BasicDBObject polygon = new BasicDBObject("type", "Point");
		polygon.put("coordinates", coordenadas);

		BasicDBObject clauses = new BasicDBObject();
		clauses.put("id", "blumenau_cartaenchente10m.15");
		clauses.put("geometry", new BasicDBObject(
			        "$geoIntersects", new BasicDBObject(
			            "$geometry", polygon
			        )
			    ));

		BasicDBObject query = new BasicDBObject(
			    "$and", Arrays.asList(clauses)
			);
		
//		BasicDBObject query = new BasicDBObject(  funcionando
//			    "geometry", new BasicDBObject(
//			        "$geoIntersects", new BasicDBObject(
//			            "$geometry", polygon
//			        )
//			    )
//			);
	    
//	    DBObject achou = MongoProvider.MongoBD.cotas().findOne(query);
//	    System.out.println(achou);
	    DBCursor c = MongoProvider.MongoBD.cotas().find(query);
	    while (c.hasNext()){
	    	System.out.println(c.next());
	    }
	}
}