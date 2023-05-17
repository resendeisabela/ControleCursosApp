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
}
