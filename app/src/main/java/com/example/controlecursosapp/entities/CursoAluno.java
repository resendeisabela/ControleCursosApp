package com.example.controlecursosapp.entities;

public class CursoAluno {
    int cursoId;
    int alunoId;
    String nomeCurso;
    String nomeAluno;

    public int getCursoId() {
        return cursoId;
    }

    @Override
    public String toString() {
        return "CursoAluno{" +
                "cursoId=" + cursoId +
                ", nomeCurso='" + nomeCurso + '\'' +
                ", nomeAluno='" + nomeAluno + '\'' +
                '}';
    }
}
