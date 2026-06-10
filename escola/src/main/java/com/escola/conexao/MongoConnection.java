package com.escola.conexao;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class MongoConnection {
    private static MongoClient mongoClient = null;
    
    private static MongoDatabase database = null;

    public static MongoDatabase getDatabase() {
        if (database == null) {
            try {
                String uri = "mongodb://186.202.57.192:27017";
                
                mongoClient = MongoClients.create(uri);
                
                database = mongoClient.getDatabase("escola");
                
                System.out.println("Iniciando!");
            } catch (Exception e) {
                System.err.println("Erro ao conectar ao MongoDB: " + e.getMessage());
            }
        }
        return database;
    }

    public static void close() {
        if (mongoClient != null) {
            mongoClient.close();
            System.out.println("Finalizando");
        }
    }
}