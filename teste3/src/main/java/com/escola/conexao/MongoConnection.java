package com.escola.conexao;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class MongoConnection {
    // Variável que vai segurar a conexão ativa com o servidor do MongoDB
    private static MongoClient mongoClient = null;
    
    // Variável que vai segurar o banco de dados específico ("escola")
    private static MongoDatabase database = null;

    // Método que qualquer parte do sistema vai chamar para pegar o banco de dados
    public static MongoDatabase getDatabase() {
        if (database == null) {
            try {
                // A URI que você está usando para conectar no servidor remoto
                String uri = "mongodb://186.202.57.192:27017";
                
                // Cria o cliente que se conecta ao servidor
                mongoClient = MongoClients.create(uri);
                
                // Conecta especificamente na base de dados "escola"
                database = mongoClient.getDatabase("escola");
                
                System.out.println("Conexão com o MongoDB estabelecida com sucesso!");
            } catch (Exception e) {
                System.err.println("Erro ao conectar ao MongoDB: " + e.getMessage());
            }
        }
        return database;
    }

    // Método para fechar a conexão quando o programa terminar
    public static void close() {
        if (mongoClient != null) {
            mongoClient.close();
            System.out.println("Conexão com o MongoDB fechada.");
        }
    }
}