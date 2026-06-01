package com.escola.dao;

import com.escola.conexao.MongoConnection;
import com.escola.model.Professor;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.types.ObjectId;

public class ProfessorDAO {
    private final MongoCollection<Document> collection;

    public ProfessorDAO() {
        MongoDatabase db = MongoConnection.getDatabase();
        this.collection = db.getCollection("professor");
    }

    // 1. CADASTRAR
    public void cadastrar(Professor professor) {
        Document doc = professor.toDocument();
        collection.insertOne(doc);
        professor.setId(doc.getObjectId("_id").toHexString());
    }

    // 2. LOCALIZAR INDIVIDUALMENTE
    public Professor buscarPorId(String id) {
        try {
            Document doc = collection.find(Filters.eq("_id", new ObjectId(id))).first();
            return Professor.fromDocument(doc);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    // 3. ALTERAR
    public void alterar(Professor professor) {
        collection.updateOne(
            Filters.eq("_id", new ObjectId(professor.getId())),
            new Document("$set", new Document("nome", professor.getNome())
                                    .append("matricula", professor.getMatricula()))
        );
    }

    // 4. EXCLUIR
    public void excluir(String id) {
        collection.deleteOne(Filters.eq("_id", new ObjectId(id)));
    }
    
    // 5. PESQUISA POR MATRÍCULA
    public Professor buscarPorMatricula(String matricula) {
        Document doc = collection.find(Filters.eq("matricula", matricula)).first();
        return Professor.fromDocument(doc);
    }
}