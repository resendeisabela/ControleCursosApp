package com.example.controlecursosapp.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.controlecursosapp.entities.Curso;

import java.util.List;

@Dao
public interface CursoDao {

    @Query("SELECT * FROM Curso WHERE cursoId = :id LIMIT 1")
    Curso getCurso(int id);
    @Query("SELECT * FROM Curso")
    List<Curso> getAll();
    @Insert
    void insertAll(Curso... curso);
    @Update
    void update(Curso curso);
    @Delete
    void delete(Curso curso);
}
