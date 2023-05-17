package com.example.controlecursosapp.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.controlecursosapp.dao.AlunoDao;
import com.example.controlecursosapp.dao.CursoAlunoDao;
import com.example.controlecursosapp.dao.CursoDao;
import com.example.controlecursosapp.entities.Aluno;
import com.example.controlecursosapp.entities.Curso;

@Database(entities = {Aluno.class, Curso.class}, version = 2)
public abstract class CursosOnline extends RoomDatabase {
    private static CursosOnline INSTANCE;

    public static CursosOnline getDatabase(Context context){
        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    CursosOnline.class, "CursosOnline").allowMainThreadQueries().build();
        }
        return INSTANCE;
    }

    public abstract AlunoDao alunoModel();
    public abstract CursoDao cursoModel();
    public abstract CursoAlunoDao cursoALunoModel();
}
