package com.example.controlecursosapp.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Curso {
    @PrimaryKey(autoGenerate = true)
    int cursoId;
    String nomeCurso;
    int qtdeHoras;

    public int getCursoId() {
        return cursoId;
    }

    public void setCursoId(int cursoId) {
        this.cursoId = cursoId;
    }

    public String getNomeCurso() {
        return nomeCurso;
    }

    public void setNomeCurso(String nomeCurso) {
        this.nomeCurso = nomeCurso;
    }

    public int getQtdeHoras() {
        return qtdeHoras;
    }

    public void setQtdeHoras(int qtdeHoras) {
        this.qtdeHoras = qtdeHoras;
    }

    public Curso() {
    }

    public Curso(String nomeCurso, int qtdeHoras) {
        this.nomeCurso = nomeCurso;
        this.qtdeHoras = qtdeHoras;
    }

    @Override
    public String toString() {
        return "Curso{" +
                "cursoId=" + cursoId +
                ", nomeCurso='" + nomeCurso + '\'' +
                ", qtdeHoras=" + qtdeHoras +
                '}';
    }
}
