package com.example.controlecursosapp.entities;

public class CursoAluno {
    public int cursoId;
    public int alunoId;
    public String nomeCurso;
    public String nomeAluno;

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
