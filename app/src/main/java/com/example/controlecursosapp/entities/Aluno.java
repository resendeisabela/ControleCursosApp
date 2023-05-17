package com.example.controlecursosapp.entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = @ForeignKey(entity = Curso.class,
        parentColumns = "cursoId", childColumns = "cursoId",
        onDelete = ForeignKey.CASCADE ))
public class Aluno {
    @PrimaryKey(autoGenerate = true)
    int alunoId;
    int cursoId;
    String nomeAluno;
    String emailAluno;
    String telefoneAluno;

    public int getAlunoId() {
        return alunoId;
    }

    public void setAlunoId(int alunoId) {
        this.alunoId = alunoId;
    }

    public int getCursoId() {
        return cursoId;
    }

    public void setCursoId(int cursoId) {
        this.cursoId = cursoId;
    }

    public String getNomeAluno() {
        return nomeAluno;
    }

    public void setNomeAluno(String nomeAluno) {
        this.nomeAluno = nomeAluno;
    }

    public String getEmailAluno() {
        return emailAluno;
    }

    public void setEmailAluno(String emailAluno) {
        this.emailAluno = emailAluno;
    }

    public String getTelefoneAluno() {
        return telefoneAluno;
    }

    public void setTelefoneAluno(String telefoneAluno) {
        this.telefoneAluno = telefoneAluno;
    }

    public Aluno() {
    }

    public Aluno(int cursoId, String nomeAluno, String emailAluno, String telefoneAluno) {
        this.cursoId = cursoId;
        this.nomeAluno = nomeAluno;
        this.emailAluno = emailAluno;
        this.telefoneAluno = telefoneAluno;
    }

    @Override
    public String toString() {
        return "Aluno{" +
                "alunoId=" + alunoId +
                ", cursoId=" + cursoId +
                ", nomeAluno='" + nomeAluno + '\'' +
                ", emailAluno='" + emailAluno + '\'' +
                ", telefoneAluno='" + telefoneAluno + '\'' +
                '}';
    }
}
