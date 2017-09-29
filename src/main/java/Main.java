import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        new Main().collectionNames();

    }
    public void collectionNames() {
        String user = "user2"; // the user name
        String database = "admin"; // the name of the database in which the user is defined
        String password = "user2"; // the password as a character array

        MongoCredential credential = MongoCredential.createCredential(user, database, password.toCharArray());
        MongoClient mongoClient = new MongoClient(new ServerAddress("192.168.1.135:27017", 27017), Arrays.asList(credential));

        System.out.println("conexion OK");
        //MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://192.168.1.135:27017"));
        try {
            /*DB db = mongoClient.getDB("demografia");
            Set<String> colls = db.getCollectionNames();
            for (String s : colls) {
                System.out.println("*****");
                System.out.println(s);
                DBCollection collection = db.getCollection(s);
                DBCursor cursor = collection.find();
                while (cursor.hasNext()) {
                    DBObject obj = cursor.next();
                    System.out.println(obj);
                    //do your thing
                }
            }*/
            DB db = mongoClient.getDB("demografia"); //se conecta a la BBDD y si no la crea

            //Tiene que cargar las collections
            String [] allCollectionNames = {
                    "ciudades",
                    "comunidades",
                    "paises"
            };
            for (String collectionName: allCollectionNames){
                elementInitCollection(collectionName, db);
            }
            //db.createCollection("ciudades", null);
            //db.createCollection("comunidades", null);
            //db.createCollection("paises", null);

            //DBCollection collection = db.getCollection("paises");


            db.getCollectionNames().forEach(System.out::println);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            mongoClient.close();
        }
    }
    public void elementInitCollection(String collectionName, DB databaseName){
        DBCollection collection = databaseName.getCollection(collectionName);
        BasicDBObject document = new BasicDBObject();
        document.put("_id","-1");
        document.put("name","X");
        collection.insert(document);
        BasicDBObject searchQuery = new BasicDBObject();
        searchQuery.put("_id", "-1");
        collection.remove(searchQuery);
    }
}
