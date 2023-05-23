package com.example.controlecursosapp.views;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteConstraintException;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.controlecursosapp.MainActivity;
import com.example.controlecursosapp.database.CursosOnline;
import com.example.controlecursosapp.databinding.ActivityCursoViewBinding;
import com.example.controlecursosapp.entities.Curso;

public class CursoView extends AppCompatActivity {
    private CursosOnline db;
    private ActivityCursoViewBinding binding;
    private int dbCursoID;
    private Curso dbCurso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCursoViewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        db = CursosOnline.getDatabase(getApplicationContext());
        dbCursoID = getIntent().getIntExtra(
                "CURSO_SELECIONADO_ID", -1);

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CursoView.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }

    protected void onResume() {
        super.onResume();
        if(dbCursoID >= 0) {
            getDBCurso();
        } else {
            binding.btnExcluirCurso.setVisibility(View.GONE);
        }
    }

    private void getDBCurso() {
        dbCurso = db.cursoModel().getCurso(dbCursoID);
        binding.edtCurso.setText(dbCurso.getNomeCurso());
        binding.edtCargaHoraria.setText(String.valueOf(dbCurso.getQtdeHoras()));
    }

    public void salvarCurso(View view) {
        String nomeCurso = binding.edtCurso.getText().toString();
        if (nomeCurso.equals("")) {
            Toast.makeText(this, "Adicione um curso.",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        if(binding.edtCargaHoraria.getText().toString().trim().isEmpty()){
            Toast.makeText(this, "Adicione uma carga horária ao curso.",
                    Toast.LENGTH_SHORT).show();
            return;
        }
        int qtdeHoras = Integer.parseInt(binding.edtCargaHoraria.getText().toString());

        Curso thisCurso = new Curso(nomeCurso, qtdeHoras);

        if (dbCurso != null) {
            thisCurso.setCursoId(dbCursoID);
            db.cursoModel().update(thisCurso);
            Toast.makeText(this, "Curso atualizado com sucesso.", Toast.LENGTH_SHORT).show();
        } else {
            db.cursoModel().insertAll(thisCurso);
            Toast.makeText(this, "Curso criado com sucesso.", Toast.LENGTH_SHORT).show();
        }
        finish();
    }

    public void excluirCurso(View view) {
        new AlertDialog.Builder(this)
                .setTitle("Exclusão de curso")
                .setMessage("Deseja excluir esse curso?")
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        excluir();
                    }
                })
                .setNegativeButton("Não", null)
                .show();
    }

    private void excluir() {
        try {
            db.cursoModel().delete(dbCurso);
            Toast.makeText(this, "Curso excluído com sucesso", Toast.LENGTH_SHORT).show();
        } catch (SQLiteConstraintException e) {
            Toast.makeText(this, "Impossível excluir curso com alunos cadastrados", Toast.LENGTH_SHORT).show();
        }
        finish();
    }
    public void voltar(View view) {
        finish();
    }
}
