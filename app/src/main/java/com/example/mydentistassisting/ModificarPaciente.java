package com.example.mydentistassisting;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ModificarPaciente extends Activity {

    public EditText mod_nombre, mod_ci, mod_cel, mod_repre;
    public TextView IdNum1, edadPaciente1;
    private Button btn, btn1;
    Pacientes pacientes;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_paciente);

        Intent intent = getIntent();

        intent.getStringExtra("_ID");

        mod_nombre = (EditText) findViewById(R.id.mod_paciente);
        mod_ci = (EditText) findViewById(R.id.mod_ci);
        mod_cel = (EditText) findViewById(R.id.mod_cel);
        mod_repre = (EditText) findViewById(R.id.mod_menor);
        edadPaciente1 = (TextView) findViewById(R.id.edadPaciente);
        IdNum1=(TextView) findViewById(R.id.idNum);
        btn = (Button) findViewById(R.id.btn);
        btn1 = (Button) findViewById(R.id.btn1);


        btn.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                try {
                    pacientes = new Pacientes( IdNum1.getText().toString(), Integer.parseInt(mod_ci.getText().toString()), mod_nombre.getText().toString(), mod_cel.getText().toString(), Integer.parseInt(edadPaciente1.getText().toString()), mod_repre.getText().toString());


                } catch (NumberFormatException e) {
                    Toast.makeText(ModificarPaciente.this, "Error modificando paciente", Toast.LENGTH_SHORT).show();
                    pacientes = new Pacientes( "error", 0, "error", "error", 0, "error");
                }

                DataBaseHelper dataBaseHelper = new DataBaseHelper(ModificarPaciente.this);

                boolean success = dataBaseHelper.modificarOne(pacientes);

                Toast.makeText( ModificarPaciente.this, "Paciente modificado="+success, Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getApplication(), AddPaciente.class);
                startActivity(intent);
            }
        });

        btn1.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), AddPaciente.class);
                startActivity(intent);

            }
        });

}
}