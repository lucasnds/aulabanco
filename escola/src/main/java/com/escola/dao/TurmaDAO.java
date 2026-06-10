package com.escola.dao;

import com.escola.conexao.MongoConnection;
import com.escola.model.Turma;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.*;

public class TurmaDAO {

    private final MongoCollection<Document> collection;

    public TurmaDAO() {
        MongoDatabase db = MongoConnection.getDatabase();
        collection = db.getCollection("turmas");
    }

    public void salvar(Turma turma) {

        ProfessorDAO professorDAO = new ProfessorDAO();
        AlunoDAO alunoDAO = new AlunoDAO();

        if (professorDAO.buscarPorId(
                turma.getProfessorId().toHexString()) == null) {

            throw new RuntimeException(
                "Professor não encontrado."
            );
        }

        for (ObjectId alunoId : turma.getAlunosIds()) {

            if (alunoDAO.buscarPorId(
                    alunoId.toHexString()) == null) {

                throw new RuntimeException(
                    "Aluno não encontrado: " + alunoId
                );
            }
        }

        Document doc = new Document()
                .append("nome", turma.getNome())
                .append("professorId", turma.getProfessorId())
                .append("alunosIds", turma.getAlunosIds());

        collection.insertOne(doc);

        turma.setId(doc.getObjectId("_id"));
    }

    public Turma buscarPorId(String id) {

        Document doc = collection.find(eq("_id", new ObjectId(id))).first();

        if(doc == null)
            return null;

        return documentToTurma(doc);
    }
    
    public List<Turma> listarTodos() {

        List<Turma> lista = new ArrayList<>();

        for (Document doc : collection.find()) {
            lista.add(documentToTurma(doc));
        }

        return lista;
    }

    public void excluir(String id) {

        collection.deleteOne(eq("_id", new ObjectId(id)));
    }
    
    public void excluirTodos() {
        collection.deleteMany(new Document());
    }

    public void atualizar(Turma turma) {

        collection.updateOne(
            eq("_id", turma.getId()),
            new Document("$set",
                new Document("nome", turma.getNome())
                .append("professorId", turma.getProfessorId())
                .append("alunosIds", turma.getAlunosIds())
            )
        );
    }
    
    public boolean alunoEstaEmTurma(ObjectId alunoId) {

        Document turma = collection.find(
            eq("alunosIds", alunoId)
        ).first();

        return turma != null;
    }

    private Turma documentToTurma(Document doc) {

        Turma turma = new Turma();

        turma.setId(doc.getObjectId("_id"));
        turma.setNome(doc.getString("nome"));
        turma.setProfessorId(doc.getObjectId("professorId"));

        turma.setAlunosIds(
            doc.getList("alunosIds", ObjectId.class)
        );

        return turma;
    }
}