package com.escola.model;

import java.util.List;
import org.bson.types.ObjectId;

public class Turma {

    private ObjectId id;
    private String nome;
    private ObjectId professorId;
    private List<ObjectId> alunosIds;
	public ObjectId getId() {
		return id;
	}
	public void setId(ObjectId id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public ObjectId getProfessorId() {
		return professorId;
	}
	public void setProfessorId(ObjectId professorId) {
		this.professorId = professorId;
	}
	public List<ObjectId> getAlunosIds() {
		return alunosIds;
	}
	public void setAlunosIds(List<ObjectId> alunosIds) {
		this.alunosIds = alunosIds;
	}

    
}