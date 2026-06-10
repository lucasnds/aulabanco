package com.escola.dao;

import com.escola.model.Professor;

public class ProfessorCSV
        implements ImportavelCSV<Professor> {

    private ProfessorDAO professorDAO;

    public ProfessorCSV(
            ProfessorDAO professorDAO) {

        this.professorDAO = professorDAO;
    }

    @Override
    public Professor criar(String[] dados) {

        return new Professor(
            dados[0].trim(),
            dados[1].trim()
        );
    }

    @Override
    public void salvar(Professor professor) {
    	
    	 System.out.println(
                 "Importando professor: "
                 + professor.getNome()
                 + " - "
                 + professor.getDisciplina()
         );

        professorDAO.salvar(professor);
    }
}