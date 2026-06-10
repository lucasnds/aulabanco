package com.escola.dao;

import com.escola.conexao.MongoConnection;
import com.escola.model.Professor;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.types.ObjectId;

public class ProfessorDAO {
    private final MongoCollection<Document> collection;

    public ProfessorDAO() {
        MongoDatabase db = MongoConnection.getDatabase();
        this.collection = db.getCollection("professor");
    }

    public void salvar(Professor professor) {
        Document doc = professor.toDocument();
        collection.insertOne(doc);
        professor.setId(doc.getObjectId("_id").toHexString());
    }

    public Professor buscarPorId(String id) {
        try {
            Document doc = collection.find(Filters.eq("_id", new ObjectId(id))).first();
            return Professor.fromDocument(doc);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
    
    public Professor buscarPorNome(String nome) {
        Document doc = collection.find(
                Filters.eq("nome", nome)
        ).first();

        return Professor.fromDocument(doc);
    }
    
    public List<Professor> buscarPorDisciplina(String disciplina) {

        List<Professor> lista = new ArrayList<>();

        for (Document doc : collection.find(
                Filters.eq("disciplina", disciplina))) {

            lista.add(Professor.fromDocument(doc));
        }

        return lista;
    }
    
    public List<Professor> listarTodos() {

        List<Professor> lista = new ArrayList<>();

        for (Document doc : collection.find()) {
            lista.add(Professor.fromDocument(doc));
        }

        return lista;
    }

    public void atualizar(Professor professor) {
        collection.updateOne(
            Filters.eq("_id", new ObjectId(professor.getId())),
            new Document("$set", new Document("nome", professor.getNome())
                                    .append("disciplina", professor.getDisciplina()))
        );
    }

    public void excluir(String id) {
        collection.deleteOne(Filters.eq("_id", new ObjectId(id)));
    }
    
    public void excluirTodos() {
        collection.deleteMany(new Document());
    }
}