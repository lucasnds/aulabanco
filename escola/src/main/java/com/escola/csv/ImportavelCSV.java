package com.escola.csv;

public interface ImportavelCSV<T> {

    T criar(String[] dados);

    void salvar(T objeto);
}