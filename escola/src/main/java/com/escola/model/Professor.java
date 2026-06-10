package com.escola.model;

import org.bson.Document;
import org.bson.types.ObjectId;

public class Professor {
    private String id;
    private String nome;
    private String disciplina;

    public Professor() {}

    public Professor(String nome, String disciplina) {
        this.nome = nome;
        this.disciplina = disciplina;
    }

    public Document toDocument() {
        Document doc = new Document("nome", this.nome)
                            .append("disciplina", this.disciplina);
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
        prof.setDisciplina(doc.getString("disciplina"));
        return prof;
    }

    public String getId() { 
    	return id; 
    }
    
    public void setId(String id) { 
    	this.id = id;
    }

    public String getNome() {
    	return nome; 
    }
    
    public void setNome(String nome) { 
    	this.nome = nome;
    }

    public String getDisciplina() { 
    	return disciplina;
    }
    
    public void setDisciplina(String disciplina) {
    	this.disciplina = disciplina;
    }
}