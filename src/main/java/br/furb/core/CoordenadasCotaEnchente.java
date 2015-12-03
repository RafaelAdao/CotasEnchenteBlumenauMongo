package br.furb.core;

import java.util.Arrays;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class CoordenadasCotaEnchente {

	private CoordenadasCotaEnchente() {
	}

	public static boolean obterCotaEnchente(final Double longitude, final Double latitude, final String id) {
		Double[] coordenadas = new Double[2];
		coordenadas[0] = longitude;
		coordenadas[1] = latitude;
		BasicDBObject polygon = new BasicDBObject("type", "Point");
		polygon.put("coordinates", coordenadas);

		BasicDBObject clauses = new BasicDBObject();
		clauses.put("id", id);
		clauses.put("geometry", new BasicDBObject("$geoIntersects", new BasicDBObject("$geometry", polygon)));

		BasicDBObject query = new BasicDBObject("$and", Arrays.asList(clauses));
		DBObject c = MongoProvider.MongoBD.cotas().findOne(query);

		return c != null;
	}
}
