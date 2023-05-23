package com.example.controlecursosapp.views;

import androidx.appcompat.app.AppCompatActivity;

import com.example.controlecursosapp.MainActivity;
import com.example.controlecursosapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.controlecursosapp.database.CursosOnline;
import com.example.controlecursosapp.databinding.ActivityAlunoListBinding;
import com.example.controlecursosapp.entities.CursoAluno;

import java.util.List;

public class AlunoList extends AppCompatActivity {
    private ActivityAlunoListBinding binding;
    private CursosOnline db;
    private List<CursoAluno> alunoCursos;
    private ListView listViewAluno;
    private Intent edtIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityAlunoListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        db = CursosOnline.getDatabase(getApplicationContext());
        listViewAluno = binding.listAluno;

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AlunoList.this, MainActivity.class);
                startActivity(intent);
            }
        });

        binding.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AlunoList.this, AlunoView.class));
            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        edtIntent = new Intent(this, AlunoView.class);
        preencheAlunos();
    }
    private void preencheAlunos() {
        alunoCursos = db.cursoAlunoModel().getAllCursoAluno();
        ArrayAdapter<CursoAluno> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, alunoCursos);
        listViewAluno.setAdapter(adapter);

        listViewAluno.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapter, View view,
                                    int position, long id){
                CursoAluno alunoSelecionado = alunoCursos.get(position);
                edtIntent.putExtra("ALUNO_SELECIONADO_ID",
                        alunoSelecionado.getAlunoId());
                startActivity(edtIntent);
            }
        });
    }

}
