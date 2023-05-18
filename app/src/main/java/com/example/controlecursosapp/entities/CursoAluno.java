package com.example.controlecursosapp.entities;

public class CursoAluno {
    public int cursoId;
    public int alunoId;
    public String nomeCurso;
    public String nomeAluno;

    public int getCursoId() {
        return cursoId;
    }
    public int getAlunoId(){
        return alunoId;
    }

    @Override
    public String toString() {
        return
                alunoId + " " + nomeAluno +
                        "\n" + " Curso" + nomeCurso ;
    }
}
