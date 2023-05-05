package com.example.mydentistassisting;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class VerPaciente extends AppCompatActivity {

    private ListView lv_todos;
    DataBaseHelper dataBaseHelper;
    AdaptadorPaciente adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_paciente);

        lv_todos = (ListView) findViewById(R.id.lv_todos);

        dataBaseHelper = new DataBaseHelper(VerPaciente.this);

        adapter = new AdaptadorPaciente ( VerPaciente.this, dataBaseHelper);

        lv_todos.setAdapter(adapter);

        lv_todos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                AlertDialog.Builder builder = new AlertDialog.Builder(VerPaciente.this);
                CharSequence[] items = new CharSequence[3];
                items[0] = "Modificar paciente";
                items[1] = "Borrar paciente";
                items[2] = "Cancelar";

                builder.setTitle("Seleccione una opción")
                        .setItems(items, new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int l) {

                                if (l == 0) {
                                    /// Modificar pacientes
                                    Intent intent = new Intent(getApplication(), VerPaciente.class);
                                    startActivity(intent);
                                } else if (l == 1) {
                                    /// Borrar pacientes
                                    Pacientes posicionPaciente = (Pacientes) parent.getItemAtPosition(position);
                                    dataBaseHelper.deleteOne(posicionPaciente);
                                    dataBaseHelper = new DataBaseHelper(VerPaciente.this);

                                    adapter = new AdaptadorPaciente ( VerPaciente.this, dataBaseHelper);

                                    lv_todos.setAdapter(adapter);


                                } else {
                                    //// Cancelar
                                    Intent intent = new Intent(getApplication(), VerPaciente.class);
                                    startActivity(intent);
                                }
                            }
                        });

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }





}