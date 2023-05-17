package com.example.controlecursosapp.dao;

import androidx.room.Dao;
import androidx.room.Query;
import com.example.controlecursosapp.entities.CursoAluno;
import com.example.controlecursosapp.entities.Aluno;

import java.util.List;

@Dao
public interface CursoAlunoDao {
//    @Query("SELECT Aluno.cursoId AS cursoId, Aluno.nomeAluno " +
//            "AS nomeAluno, Curso.nomeCurso AS nomeCurso " +
//            "FROM Aluno INNER JOIN Curso ON Aluno.cursoId = Curso.cursoId")

    //List<CursoAluno> getAllCursoAluno();
}
