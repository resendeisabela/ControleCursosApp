package com.example.controlecursosapp.views;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.controlecursosapp.MainActivity;
import com.example.controlecursosapp.R;
import com.example.controlecursosapp.database.CursosOnline;
import com.example.controlecursosapp.entities.Aluno;
import com.example.controlecursosapp.entities.Curso;
import com.example.controlecursosapp.databinding.ActivityAlunoViewBinding;

import java.util.List;

public class AlunoView extends AppCompatActivity {
    private ActivityAlunoViewBinding binding;
    private CursosOnline db;
    private int dbAlunoID;
    private Aluno dbAluno;
    private List<Curso> cursos;
    private Spinner spnCursos;
    private ArrayAdapter<Curso> cursosAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityAlunoViewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        db = CursosOnline.getDatabase(getApplicationContext());

        spnCursos = binding.spnCursos;
        dbAlunoID = getIntent().getIntExtra(
                "ALUNO_SELECIONADO_ID", -1);

        binding.btnAddCurso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AlunoView.this, CursoView.class));
            }
        });


        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AlunoView.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(dbAlunoID >= 0){
            preencheAluno();
        } else {
            binding.btnExcluirAluno.setVisibility(View.GONE);
        }
        binding.btnAddCurso.setVisibility(View.VISIBLE);
        preencheCursos();
    }


    private void preencheAluno() {
        dbAluno = db.alunoModel().getAluno(dbAlunoID);
        binding.edtNomeAluno.setText(dbAluno.getNomeAluno());
        binding.edtEmailAluno.setText(dbAluno.getEmailAluno());
        binding.edtTelAluno.setText(dbAluno.getTelefoneAluno());
    }


    private void preencheCursos() {
        cursos = db.cursoModel().getAll();
        cursosAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, cursos);
        spnCursos.setAdapter(cursosAdapter);
        if(dbAluno != null) {
            int cursoId = dbAluno.getCursoId();
            int selectedIndex = -1;
            for (int i = 0; i < cursos.size(); i++) {
                if (cursos.get(i).getCursoId() == cursoId) {
                    selectedIndex = i;
                    break;
                }
            }
            if (selectedIndex != -1) {
                spnCursos.setSelection(selectedIndex);
            }
        }
    }

    public void salvarAluno(View view) {
        String nomeAluno = binding.edtNomeAluno.getText().toString();
        String novoCurso = "";
        String emailAluno = binding.edtEmailAluno.getText().toString();
        String telAluno = binding.edtTelAluno.getText().toString();


        if(spnCursos.getSelectedItem() != null){
            novoCurso = spnCursos.getSelectedItem().toString();
        }
        if(nomeAluno.equals("")){
            Toast.makeText(this, "O nome do aluno é obrigatório", Toast.LENGTH_SHORT).show();
            return;
        }
        if(emailAluno.equals("")){
            Toast.makeText(this, "O email do aluno é obrigatório", Toast.LENGTH_SHORT).show();
            return;
        }
        if(telAluno.equals("")){
            Toast.makeText(this, "O telefone do aluno é obrigatório", Toast.LENGTH_SHORT).show();
            return;
        }
        if(novoCurso.equals("")) {
            Toast.makeText(this, "Entre com um curso", Toast.LENGTH_SHORT).show();
            return;
        }

        Aluno novoAluno = new Aluno();
        novoAluno.setNomeAluno(nomeAluno);
        novoAluno.setEmailAluno(emailAluno);
        novoAluno.setTelefoneAluno(telAluno);
        novoAluno.setCursoId(cursos.get(
                spnCursos.getSelectedItemPosition()).getCursoId());
        if(dbAluno != null){
            novoAluno.setAlunoId(dbAlunoID);
            db.alunoModel().update(novoAluno);
            Toast.makeText(this, "Aluno atualizado com sucesso.",
                    Toast.LENGTH_SHORT).show();
        } else {
            db.alunoModel().insertAll(novoAluno);
            Toast.makeText(this, "Aluno cadastrado com sucesso.",
                    Toast.LENGTH_SHORT).show();
        }
        finish();
    }

    public void excluirAluno(View view) {
        new AlertDialog.Builder(this)
                .setTitle("Exclusão de Aluno")
                .setMessage("Deseja excluir esse aluno?")
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        excluir();
                    }
                })
                .setNegativeButton("Não", null)
                .show();
    }

    public void excluir() {
        db.alunoModel().delete(dbAluno);
        Toast.makeText(this, "Aluno excluído com sucesso.", Toast.LENGTH_SHORT).show();
        finish();
    }

    public void voltar(View view) {
        finish();
    }
}


