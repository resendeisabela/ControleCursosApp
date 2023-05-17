package com.example.controlecursosapp.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.controlecursosapp.entities.Aluno;

@Dao
public interface AlunoDao {
    @Query("SELECT * FROM Aluno WHERE alunoId = :id LIMIT 1")
    Aluno getAluno(int id);
    @Update
    void update(Aluno aluno);
    @Insert
    void insertAll(Aluno... aluno);
    @Delete
    void delete(Aluno aluno);
}
