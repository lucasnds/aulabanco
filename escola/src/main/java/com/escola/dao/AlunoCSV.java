package com.escola.dao;

import com.escola.model.Aluno;

public class AlunoCSV implements ImportavelCSV<Aluno> {

    private AlunoDAO alunoDAO;

    public AlunoCSV(AlunoDAO alunoDAO) {
        this.alunoDAO = alunoDAO;
    }

    @Override
    public Aluno criar(String[] dados) {
        return new Aluno(
            dados[0].trim(),
            dados[1].trim()
        );
    }

    @Override
    public void salvar(Aluno aluno) {
        alunoDAO.salvar(aluno);
    }
}