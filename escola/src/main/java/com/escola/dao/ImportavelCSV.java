package com.escola.dao;

public interface ImportavelCSV<T> {

    T criar(String[] dados);

    void salvar(T objeto);
}