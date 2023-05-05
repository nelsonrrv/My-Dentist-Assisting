package com.example.mydentistassisting;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements CalendarView.OnDateChangeListener {

    private CalendarView calendarView;
    private Button btnAdd1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        btnAdd1 = (Button) findViewById(R.id.button5);
        calendarView = (CalendarView) findViewById(R.id.calendarView);
        calendarView.setOnDateChangeListener(this);

        btnAdd1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(btnAdd1.getContext());
                CharSequence[] items = new CharSequence[3];
                items[0] = "Agregar paciente";
                items[1] = "Ver todos los pacientes";
                items[2] = "Cancelar";

                builder.setTitle("Seleccione una opción")
                        .setItems(items, new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int l) {

                                if (l == 0) {
                                    /// Agregar pacientes
                                    Intent intent = new Intent(getApplication(), AddPaciente.class);
                                    startActivity(intent);
                                } else if (l == 1) {
                                    /// Ver lista de pacientes del dia
                                    Intent intent = new Intent(getApplication(), VerPaciente.class);
                                    startActivity(intent);
                                } else {
                                    //// Cancelar
                                    return;
                                }
                            }
                        });

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }

    @Override
    public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
        AlertDialog.Builder builder= new AlertDialog.Builder(this);
        CharSequence [] items= new CharSequence[2];
        items[0] ="Ver pacientes para el día";
        items[1] ="Cancelar";

        final int dia, mes, anio;
        dia= dayOfMonth;
        mes=month+1;
        anio=year;


        builder.setTitle("Seleccione una opción")
                .setItems(items, new DialogInterface.OnClickListener(){

                    @Override
                    public void onClick(DialogInterface dialog, int i) {

                        if (i==0){
                            /// Agregar pacientes en la fecha
                            Intent intent=new Intent(getApplication(), AddPaciente.class);
                            Bundle bundle = new Bundle();
                            bundle.putInt("dia", dia);
                            bundle.putInt("mes", mes);
                            bundle.putInt("anio", anio);
                            intent.putExtras(bundle);
                            startActivity(intent);
                        }
                        else {
                            //// Cancelar
                            return;
                        }
                    }
                });

        AlertDialog dialog= builder.create();
        dialog.show();

    }


}