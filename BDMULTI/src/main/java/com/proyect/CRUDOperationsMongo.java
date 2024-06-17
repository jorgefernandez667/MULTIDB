package com.proyect;
import java.lang.reflect.Field;

import org.bson.Document;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

public class CRUDOperationsMongo {

    private static MongoCollection<Document> getCollection(Class<?> clazz, String connectionMongo, String databaseNameMongo) {
        MongoClient mongoClient = MongoClients.create(connectionMongo);
        MongoDatabase database = mongoClient.getDatabase(databaseNameMongo);
        return database.getCollection(clazz.getSimpleName().toLowerCase());
    }

    public static void insertDocument(String tableName, String connectionString, String databaseName, Object object, int idValue) {
        MongoClient mongoClient = MongoClients.create(connectionString);
        MongoDatabase database = mongoClient.getDatabase(databaseName);
        MongoCollection<Document> collection = database.getCollection(tableName);

        Document document = new Document();
        try {
            // Insertar ID en el documento si se proporciona
            if (idValue != 0) {
                String idFieldName = "ID_" + tableName.toLowerCase(); // Nombre de la columna de ID
                document.append(idFieldName, idValue);
            }

            // Insertar los demás campos del objeto en el documento
            for (Field field : object.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                document.append(field.getName(), field.get(object));
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        collection.insertOne(document);
        System.out.println("Documento insertado en MongoDB: " + document);
        mongoClient.close();
    }

    public static void ReadDocuments(Class<?> clazz, String connectionMongo, String databaseNameMongo) {
        MongoCollection<Document> collection = getCollection(clazz, connectionMongo, databaseNameMongo);

        try (MongoCursor<Document> cursor = collection.find().iterator()) {
            while (cursor.hasNext()) {
                System.out.println(cursor.next().toJson());
            }
        }
    }

    public static void UpdateDocument(String collectionName, String connectionMongo, String databaseNameMongo,
            String keyField, Object keyValue, String fieldName, Object nuevoValor) {
        try {
            // Conecta a MongoDB
            MongoClient mongoClient = MongoClients.create(connectionMongo);

            // Obtene la base de datos
            MongoDatabase database = mongoClient.getDatabase(databaseNameMongo);

            // Obtene la colección especificada
            MongoCollection<Document> collection = database.getCollection(collectionName);

            // Actualiza el documento
            collection.updateOne(new Document(keyField, keyValue), new Document("$set", new Document(fieldName, nuevoValor)));

            // Cerra la conexión
            mongoClient.close();

            System.out.println("Documento actualizado en MongoDB.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void UpdateStructureCollection(Class<?> clazz, String connectionString, String databaseName) {
        String collectionName = clazz.getSimpleName().toLowerCase();

        try (MongoClient mongoClient = MongoClients.create(connectionString)) {
            MongoDatabase database = mongoClient.getDatabase(databaseName);
            MongoCollection<Document> collection = database.getCollection(collectionName);

            for (Field field : clazz.getDeclaredFields()) {
                String fieldName = field.getName();
                String fieldType = getMongoType(field.getType());

                Document index = new Document(fieldName, fieldType);
                collection.createIndex(index);
                System.out.println("Índice para el campo " + fieldName + " creado en la colección " + collectionName + ".");
            }
        }
    }

    private static String getMongoType(Class<?> fieldType) {
        if (fieldType == String.class) {
            return "text";
        } else if (fieldType == int.class || fieldType == Integer.class) {
            return "int";
        } else if (fieldType == long.class || fieldType == Long.class) {
            return "long";
        } else if (fieldType == double.class || fieldType == Double.class) {
            return "double";
        } else if (fieldType == float.class || fieldType == Float.class) {
            return "double";
        } else if (fieldType == boolean.class || fieldType == Boolean.class) {
            return "boolean";
        } else if (fieldType == java.util.Date.class) {
            return "date";
        }
        throw new IllegalArgumentException("Tipo de dato no soportado: " + fieldType.getSimpleName());
    }


    
public static void DeleteDocument(String collectionName, String connectionString, String databaseName, int id) {
    try {
        // Conectar a MongoDB
        MongoClient mongoClient = MongoClients.create(connectionString);

        // Obtener la base de datos
        MongoDatabase database = mongoClient.getDatabase(databaseName);

        // Obtener la colección especificada
        MongoCollection<Document> collection = database.getCollection(collectionName);

        // Eliminar documento
        Document filter = new Document("_id", id);  // Utiliza el ID para filtrar el documento a eliminar
        collection.deleteOne(filter);

        System.out.println("Documento eliminado en MongoDB.");

        // Cerrar la conexión
        mongoClient.close();
    } catch (Exception e) {
        e.printStackTrace();
    }
}
}
