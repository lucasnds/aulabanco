package com.escola.model;

import org.bson.Document;
import org.bson.types.ObjectId;

public class Professor {
    private String id;
    private String nome;
    private String matricula;

    public Professor() {}

    public Professor(String nome, String matricula) {
        this.nome = nome;
        this.matricula = matricula;
    }

    public Document toDocument() {
        Document doc = new Document("nome", this.nome)
                            .append("matricula", this.matricula);
        if (this.id != null) {
            doc.append("_id", new ObjectId(this.id));
        }
        return doc;
    }

    public static Professor fromDocument(Document doc) {
        if (doc == null) return null;
        Professor prof = new Professor();
        prof.setId(doc.getObjectId("_id").toHexString());
        prof.setNome(doc.getString("nome"));
        prof.setMatricula(doc.getString("matricula"));
        return prof;
    }

    // Getters e Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getMatricula() { return matricula; }
    public void setMatricula(String matricula) { this.matricula = matricula; }
}