package com.escola.dao;

import com.escola.conexao.MongoConnection;
import com.escola.model.Aluno;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.DeleteResult;

import org.bson.Document;
import org.bson.types.ObjectId;
import java.util.ArrayList;
import java.util.List;

public class AlunoDAO {
    private final MongoCollection<Document> collection;

    // Construtor: Conecta automaticamente na collection "aluno"
    public AlunoDAO() {
        MongoDatabase db = MongoConnection.getDatabase();
        this.collection = db.getCollection("aluno");
    }

    // 1. CADASTRAR (Create)
    public void cadastrar(Aluno aluno) {
        Document doc = aluno.toDocument();
        collection.insertOne(doc);
        // Atualiza o objeto Java com o ID gerado automaticamente pelo MongoDB
        aluno.setId(doc.getObjectId("_id").toHexString());
    }

    // 2. LOCALIZAR INDIVIDUALMENTE POR ID (Read)
    public Aluno buscarPorId(String id) {
        try {
            Document doc = collection.find(Filters.eq("_id", new ObjectId(id))).first();
            return Aluno.fromDocument(doc);
        } catch (IllegalArgumentException e) {
            // Caso o ID passado não seja um formato válido de ObjectId do Mongo
            return null;
        }
    }

    // 3. ALTERAR (Update)
    public void alterar(Aluno aluno) {
        collection.updateOne(
            Filters.eq("_id", new ObjectId(aluno.getId())),
            new Document("$set", new Document("nome", aluno.getNome())
                                    .append("ra", aluno.getRA()))
        );
    }

    public void excluir(String id) {
        collection.deleteOne(Filters.eq("_id", new ObjectId(id)));
    }
    
    public void excluirTodos() {
        DeleteResult resultado = collection.deleteMany(new Document());
        
        System.out.println("Processo de exclusão em massa concluído.");
        System.out.println("Total de registros removidos: " + resultado.getDeletedCount());
    }
    
    // 5. PESQUISA POR RA (Útil para validações futuras)
    public Aluno buscarPorRA(String ra) {
        Document doc = collection.find(Filters.eq("ra", ra)).first();
        return Aluno.fromDocument(doc);
    }
    
    public List<Aluno> listarTodos() {
        List<Aluno> lista = new ArrayList<>();
        // Acessa a collection e busca tudo
        FindIterable<Document> cursor = collection.find(); 
        
        for (Document doc : cursor) {
            Aluno a = new Aluno(doc.getString("nome"), doc.getString("ra"));
            a.setId(doc.getObjectId("_id").toString());
            lista.add(a);
        }
        return lista;
    }
}