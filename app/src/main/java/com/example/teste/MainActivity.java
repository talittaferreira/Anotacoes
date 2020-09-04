package com.example.teste;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.view.View;
import android.content.Intent;

import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.example.teste.bancodedados.BancoDeDados;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BancoDeDados bancoDeDados = new BancoDeDados(getBaseContext());
        final Cursor cursor = bancoDeDados.obterAnotacoes();

        String[] nomeCampos = new String[] {"_id", "titulo"};
        int[] idViews = new int[] {R.id.labelId, R.id.labelTitulo};

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(getBaseContext(), R.layout.activity_modelo_lista, cursor,nomeCampos, idViews, 0);

        ListView lista =  (ListView)findViewById(R.id.listaDeNotas);
        lista.setAdapter(adapter);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, EditarAnotacao.class);
                intent.putExtra("id", cursor.getInt(cursor.getColumnIndexOrThrow("_id")));
                startActivity(intent);
                finish();
            }
        });

    }
    public void abrirTelaCriarNovaAnotacao(View v){
        Intent startNewActivity = new Intent(this, CriarAnotacao.class);
        startActivity(startNewActivity);
    }
}
