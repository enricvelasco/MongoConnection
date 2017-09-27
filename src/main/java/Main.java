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
        //HOLA

        try {
            System.out.println("Entra en el try");
            MongoDatabase db = mongoClient.getDatabase("demografia");
            System.out.println("Hace el get database");
            MongoIterable<String> collections = db.listCollectionNames();
            System.out.println("Hace el fet collectionNames");
            for (String collectionName: collections) {
                System.out.println(collectionName);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            mongoClient.close();
        }
    }
}
