package mongodb.driver;

import java.util.Set;

import com.mongodb.*;
import com.mongodb.util.JSON;

public class MongoDriver {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {

			MongoClient mongoClient = new MongoClient("localhost", 27017);
			DB db = mongoClient.getDB("test");
			Set<String> colls = db.getCollectionNames();
			DBCollection coll = db.getCollection("student");
			OperateColl in = new OperateColl();
			in.insertDoc(coll, "{'sno':'2011266765','name':'qinq'}");
			in.insertDoc(coll, "{'sno':'2011234567','name':'qinq'}");
			for (String temp : colls) {
				System.out.println(temp);
			}
			DBCursor cur = coll.find();
			while (cur.hasNext()) {
				System.out.println(cur.next());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}

class OperateColl {
	void insertDoc(DBCollection coll, String json) {
		BasicDBObject doc = (BasicDBObject) JSON.parse(json);
		coll.insert(doc);
	}
}