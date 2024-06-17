package com.proyect;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.lang.reflect.Field;

public class MapeoMongo {

    public static void RunMapping(Class<?> clazz, String connectionString, String databaseName) {
        try {
            // Conectar a MongoDB
            MongoClient mongoClient = MongoClients.create(connectionString);

            // Obtener la base de datos (creará la base de datos si no existe)
            MongoDatabase database = mongoClient.getDatabase(databaseName);

            // Mapea la clase y crea colecciones
            mapClassToCollection(clazz, database);

            // Cerrar el cliente de MongoDB
            mongoClient.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void mapClassToCollection(Class<?> clazz, MongoDatabase database) throws ClassNotFoundException {
        for (Class<?> innerClass : clazz.getDeclaredClasses()) {
            String collectionName = innerClass.getSimpleName().toLowerCase();

            // Comprobar si la colección ya existe
            if (collectionExists(database, collectionName)) {
                System.out.println("La colección " + collectionName + " ya existe.");
                continue;
            }

            // Crear la colección
            database.createCollection(collectionName);

            // Obtener la colección recién creada
            MongoCollection<Document> collection = database.getCollection(collectionName);

            // Obtener campos de la clase interna
            Field[] fields = innerClass.getDeclaredFields();


            System.out.println("Colección " + collectionName + " creada exitosamente.");
        }
    }

    private static boolean collectionExists(MongoDatabase database, String collectionName) {
        for (String name : database.listCollectionNames()) {
            if (name.equalsIgnoreCase(collectionName)) {
                return true;
            }
        }
        return false;
    }
}
