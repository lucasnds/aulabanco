package com.escola.dao;

import com.escola.conexao.MongoConnection;
import com.escola.model.Aluno;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

import org.bson.Document;
import org.bson.types.ObjectId;
import java.util.ArrayList;
import java.util.List;

public class AlunoDAO {
    private final MongoCollection<Document> collection;

    public AlunoDAO() {
        MongoDatabase db = MongoConnection.getDatabase();
        this.collection = db.getCollection("aluno");
    }

    public void salvar(Aluno aluno) {
        Document doc = aluno.toDocument();
        collection.insertOne(doc);
        aluno.setId(doc.getObjectId("_id").toHexString());
    }

    public Aluno buscarPorId(String id) {
        try {
            Document doc = collection.find(Filters.eq("_id", new ObjectId(id))).first();
            return Aluno.fromDocument(doc);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
    
    public Aluno buscarPorNome(String nome) {

        Document doc = collection.find(
            Filters.eq("nome", nome)
        ).first();

        return Aluno.fromDocument(doc);
    }
    
    public Aluno buscarPorMatricula(String matricula) {
        Document doc = collection.find(
                Filters.eq("matricula", matricula)
        ).first();

        return Aluno.fromDocument(doc);
    }
    
    public List<Aluno> listarTodos() {
        List<Aluno> lista = new ArrayList<>();
        FindIterable<Document> cursor = collection.find(); 
        
        for (Document doc : cursor) {
            Aluno a = new Aluno(doc.getString("nome"), doc.getString("matricula"));
            a.setId(doc.getObjectId("_id").toHexString());
            lista.add(a);
        }
        return lista;
    }

    public void atualizar(Aluno aluno) {
        collection.updateOne(
            Filters.eq("_id", new ObjectId(aluno.getId())),
            new Document("$set", new Document("nome", aluno.getNome())
                                    .append("matricula", aluno.getMatricula()))
        );
    }

    public void excluir(String id) {

        TurmaDAO turmaDAO = new TurmaDAO();

        if (turmaDAO.alunoEstaEmTurma(new ObjectId(id))) {
            throw new RuntimeException(
                "Aluno vinculado a uma turma."
            );
        }

        collection.deleteOne(
            Filters.eq("_id", new ObjectId(id))
        );
    }
    
    public void excluirTodos() {
        collection.deleteMany(new Document());
    }
    
    
}