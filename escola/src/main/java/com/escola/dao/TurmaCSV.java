package com.escola.dao;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;

import com.escola.model.Aluno;
import com.escola.model.Professor;
import com.escola.model.Turma;

public class TurmaCSV implements ImportavelCSV<Turma> {

    private ProfessorDAO professorDAO;
    private AlunoDAO alunoDAO;
    private TurmaDAO turmaDAO;

    public TurmaCSV(ProfessorDAO professorDAO, AlunoDAO alunoDAO, TurmaDAO turmaDAO) {
        this.professorDAO = professorDAO;
        this.alunoDAO = alunoDAO;
        this.turmaDAO = turmaDAO;
    }

    @Override
    public Turma criar(String[] dados) {

        Turma turma = new Turma();

        turma.setNome(dados[0].trim());

        Professor professor = professorDAO.buscarPorNome(dados[1].trim());

        if (professor == null) {
            throw new RuntimeException("Professor não encontrado: " + dados[1]);
        }

        turma.setProfessorId(new ObjectId(professor.getId()));

        List<ObjectId> alunosIds = new ArrayList<>();

        String[] matriculas = dados[2].split(";");

        for (String matricula : matriculas) {

            Aluno aluno = alunoDAO.buscarPorMatricula(matricula.trim());

            if (aluno == null) {
                throw new RuntimeException("Aluno não encontrado: " + matricula);
            }

            alunosIds.add(new ObjectId(aluno.getId()));
        }

        turma.setAlunosIds(alunosIds);

        return turma;
    }

    @Override
    public void salvar(Turma turma) {
        turmaDAO.salvar(turma);
    }
}