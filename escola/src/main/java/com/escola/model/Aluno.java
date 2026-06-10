package com.escola.model;

import org.bson.Document;
import org.bson.types.ObjectId;

public class Aluno {
    private String id;
    private String nome;
    private String matricula; 

    public Aluno() {}

    public Aluno(String nome, String matricula) {
        this.nome = nome;
        this.matricula = matricula;
    }

    public Document toDocument() {
    	
        Document doc = new Document()
                .append("nome", this.nome)
                .append("matricula", this.matricula);
        
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
        aluno.setMatricula(doc.getString("matricula"));
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

    public String getMatricula() {
    	return matricula; 
    }
    
    public void setMatricula(String ra) { 
    	this.matricula = ra; 
    }
    
}