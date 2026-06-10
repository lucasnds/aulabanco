package com.escola.csv;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ImportadorCSV<T> implements Runnable {

    private String caminhoArquivo;
    private ImportavelCSV<T> adapter;

    public ImportadorCSV(
            String caminhoArquivo,
            ImportavelCSV<T> adapter) {

        this.caminhoArquivo = caminhoArquivo;
        this.adapter = adapter;
    }

    @Override
    public void run() {

        try (BufferedReader br =
                new BufferedReader(
                    new FileReader(caminhoArquivo))) {

            String linha;

            while ((linha = br.readLine()) != null) {

                String[] dados =
                    linha.split(",");

                T objeto =
                    adapter.criar(dados);

                adapter.salvar(objeto);
            }

        } catch (IOException e) {

            System.err.println(
                e.getMessage()
            );
        }
    }
}