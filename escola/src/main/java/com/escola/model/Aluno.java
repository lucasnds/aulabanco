package com.escola.model;

import org.bson.Document;
import org.bson.types.ObjectId;

public class Aluno {
    private String id;
    private String nome;
    private String ra; 

    public Aluno() {}

    public Aluno(String nome, String ra) {
        this.nome = nome;
        this.ra = ra;
    }

    public Document toDocument() {
        Document doc = new Document("nome", this.nome)
                            .append("ra", this.ra);
        if (this.id != null) {
            doc.append("_id", new ObjectId(this.id));
        }
        return doc;
    }

    public static Aluno fromDocument(Document doc) {
        if (doc == null) return null;
        Aluno aluno = new Aluno();
        aluno.setId(doc.getObjectId("_id").toHexString());
        aluno.setNome(doc.getString("nome"));
        aluno.setRA(doc.getString("ra"));
        return aluno;
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

    public String getRA() {
    	return ra; 
    }
    
    public void setRA(String ra) { 
    	this.ra = ra; 
    }
    
}