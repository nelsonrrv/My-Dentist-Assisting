package com.example.mydentistassisting;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

import java.util.List;


public class AddPaciente extends Activity implements OnClickListener {

    static final int DATE_START_DIALOG_ID = 0;
    private int startYear = 1994;
    private int startMonth = 7;
    private int startDay = 3;
    public boolean menor;
    public AgeCalculator age = null;
    private EditText add_paciente, add_ci, add_cel, add_menor;
    private TextView edadPaciente, fechaActual, idNum;
    private ListView lv_pacientes;
    private Button btnStart, btnAdd, btnCancel, btnList;
    public String add_id;
    private  int idexti=0;
    private int idexta=0;
    ArrayAdapter pacienteArrayAdapter;
    DataBaseHelper dataBaseHelper;
    AdaptadorPaciente adapter;
    MisPacientes misPacientes;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_paciente);

        age = new AgeCalculator();
        add_paciente = (EditText) findViewById(R.id.add_paciente);
        add_ci = (EditText) findViewById(R.id.add_ci);
        add_cel = (EditText) findViewById(R.id.add_cel);
        add_menor = (EditText) findViewById(R.id.add_menor);
        edadPaciente = (TextView) findViewById(R.id.edadPaciente);
        idNum=(TextView) findViewById(R.id.idNum);
        fechaActual = (TextView) findViewById(R.id.fechaActual);
        fechaActual.setText(age.getCurrentDate());
        btnStart = (Button) findViewById(R.id.button1);
        btnAdd = (Button) findViewById(R.id.button3);
        btnList = (Button) findViewById(R.id.button4);
        btnCancel = (Button) findViewById(R.id.button2);
        lv_pacientes = (ListView) findViewById(R.id.lv_pacientes);



        ///button listeners

        btnAdd.setOnClickListener( new OnClickListener() {

            public void onClick(View v) {

                Pacientes pacientes;
                try {
                    pacientes = new Pacientes( add_id, Integer.parseInt(add_ci.getText().toString()), add_paciente.getText().toString(), add_cel.getText().toString(), age.getResult(), add_menor.getText().toString());


                } catch (NumberFormatException e) {
                    Toast.makeText(AddPaciente.this, "Error agregando paciente", Toast.LENGTH_SHORT).show();
                    pacientes = new Pacientes( "error", 0, "error", "error", 0, "error");
                }

                DataBaseHelper dataBaseHelper = new DataBaseHelper(AddPaciente.this);

                boolean success = dataBaseHelper.addOne(pacientes);

                Toast.makeText( AddPaciente.this, "Paciente agregado="+success, Toast.LENGTH_SHORT).show();

                idNum.setText("");
                add_ci.setText("");
                add_menor.setText("");
                add_paciente.setText("");
                add_cel.setText("");
                edadPaciente.setText("Edad:");
                add_menor.setVisibility(View.GONE);
                idNum.setVisibility(View.GONE);
            }
        });

        ///button listeners
        btnList.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                dataBaseHelper = new DataBaseHelper(AddPaciente.this);

                adapter = new AdaptadorPaciente ( AddPaciente.this, dataBaseHelper);

                lv_pacientes.setAdapter(adapter);


                Toast.makeText(getApplicationContext(), "Lista desplegada", Toast.LENGTH_SHORT).show();
            }
        });

        btnCancel.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), MainActivity.class);
                startActivity(intent);
            }
        });

        btnStart.setOnClickListener(this);

        lv_pacientes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Pacientes posicionPaciente = (Pacientes) parent.getItemAtPosition(position);
                AlertDialog.Builder builder = new AlertDialog.Builder(AddPaciente.this);
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
                                   
                                    Intent intent = new Intent(getApplicationContext(), ModificarPaciente.class);

                                    intent.putExtra("_ID", posicionPaciente.getId());

                                    startActivityForResult(intent, 1);

                                } else if (l == 1) {
                                    /// Borrar pacientes
                                    dataBaseHelper.deleteOne(posicionPaciente);

                                    dataBaseHelper = new DataBaseHelper(AddPaciente.this);

                                    adapter = new AdaptadorPaciente ( AddPaciente.this, dataBaseHelper);

                                    lv_pacientes.setAdapter(adapter);

                                    Toast.makeText(getApplicationContext(), "Paciente borrado=" + posicionPaciente.toString(), Toast.LENGTH_SHORT).show();

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





    private void MostrarListaPacientes(DataBaseHelper dataBaseHelper2) {
        pacienteArrayAdapter = new ArrayAdapter<Pacientes>(AddPaciente.this, android.R.layout.simple_list_item_1, dataBaseHelper2.getEveryone());
        lv_pacientes.setAdapter(pacienteArrayAdapter);
    }

    protected void onResume() {
        super.onResume();

        SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_PRIVATE);

        idexti = sh.getInt("idI", 0);
        idexta= sh.getInt("idA", 0);

    }

    @Override
    protected void onPause() {
        super.onPause();

        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sharedPreferences.edit();

        myEdit.putInt("idI",idexti );
        myEdit.putInt("idA",idexta );
        myEdit.commit();
    }


    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_START_DIALOG_ID:
                return new DatePickerDialog(this,
                        mDateSetListener,
                        startYear, startMonth, startDay);

        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener mDateSetListener
            = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {
            startYear = selectedYear;
            startMonth = selectedMonth;
            startDay = selectedDay;
            age.setDateOfBirth(startYear, startMonth, startDay);
            calculateAge();
            calculateMenor();
            idNum.setText(add_id);

        }

    };


    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.button1:
                showDialog(DATE_START_DIALOG_ID);
                break;

            default:
                break;
        }
    }

    private void calculateAge() {
        age.calcualteYear();
        age.calcualteMonth();
        age.calcualteDay();
        edadPaciente.setText("Edad:" + age.getResult());


    }
    private void calculateMenor() {

        if (age.getResult() <= 18) {
            idNum.setVisibility(View.VISIBLE);
            add_menor.setVisibility(View.VISIBLE);
            menor = true;
            idexti++;
            add_id = "PI000" + idexti;
        } else {
            idNum.setVisibility(View.VISIBLE);
            add_menor.setText("N/A");
            menor = false;
            idexta++;
            add_id = "PA000" + idexta;
        }


    }
}
