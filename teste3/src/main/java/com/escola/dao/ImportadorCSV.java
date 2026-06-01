package com.escola.dao;

import com.escola.model.Aluno;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ImportadorCSV implements Runnable {
    private String caminhoArquivo;
    private AlunoDAO alunoDAO;

    public ImportadorCSV(String caminhoArquivo, AlunoDAO alunoDAO) {
        this.caminhoArquivo = caminhoArquivo;
        this.alunoDAO = alunoDAO;
    }

    @Override
    public void run() {
        System.out.println("Importação iniciada pelo arquivo: " + caminhoArquivo);
        
        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;
            // Pula o cabeçalho se o seu CSV tiver um
            // br.readLine(); 
            
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(",");
                
                // Validação simples de estrutura do CSV
                if (dados.length >= 2) {
                    Aluno aluno = new Aluno(dados[0].trim(), dados[1].trim());
                    
                    // Utiliza o seu método cadastrar que já está pronto [cite: 10, 15]
                    alunoDAO.cadastrar(aluno);
                }
            }
            System.out.println("Processo de importação via Thread finalizado.");
        } catch (IOException e) {
            System.err.println("Erro na leitura do arquivo CSV: " + e.getMessage());
        }
    }
}