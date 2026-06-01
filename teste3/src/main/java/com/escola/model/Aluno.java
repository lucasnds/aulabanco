package com.escola.model;

import org.bson.Document;
import org.bson.types.ObjectId;

public class Aluno {
    private String id;
    private String nome;
    private String ra; // Registro Acadêmico ou Matrícula do Aluno

    // Construtor vazio (obrigatório para o Java conseguir instanciar depois)
    public Aluno() {}

    // Construtor para facilitar a criação do objeto
    public Aluno(String nome, String ra) {
        this.nome = nome;
        this.ra = ra;
    }

    // Método que transforma a classe Aluno em um Document do MongoDB (para SALVAR)
    public Document toDocument() {
        Document doc = new Document("nome", this.nome)
                            .append("ra", this.ra);
        // Se o objeto já tiver um ID (em caso de atualização), nós mantemos
        if (this.id != null) {
            doc.append("_id", new ObjectId(this.id));
        }
        return doc;
    }

    // Método estático que transforma um Document do Mongo de volta em Aluno (para BUSCAR)
    public static Aluno fromDocument(Document doc) {
        if (doc == null) return null;
        Aluno aluno = new Aluno();
        aluno.setId(doc.getObjectId("_id").toHexString()); // Transforma o ObjectId do Mongo em String comum
        aluno.setNome(doc.getString("nome"));
        aluno.setRA(doc.getString("ra"));
        return aluno;
    }

    // Getters e Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getRA() { return ra; }
    public void setRA(String ra) { this.ra = ra; }
}