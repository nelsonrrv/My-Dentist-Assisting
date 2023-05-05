package com.example.mydentistassisting;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper {



    public static final String PACIENTEX_TABLE = " PACIENTEX_TABLE";
    public static final String COLUMN_NOMBRE_PACIENTE = "NOMBRE_PACIENTE";
    public static final String COLUMN_CEL_PACIENTE = "CEL_PACIENTE";
    public static final String COLUMN_EDAD_PACIENTE = "EDAD_PACIENTE";
    public static final String COLUMN_REPRESENTANTE_PACIENTE = "REPRESENTANTE_PACIENTE";
    public static final String COLUMN_CI = "CI";
    public static final String COLUMN_ID = "ID";


    public DataBaseHelper(@Nullable Context context) {

        super(context, "pacientex.db", null, 1);

    }

    //// Esta es la primera vez que se accesa a la base de datos.
    @Override
    public void onCreate(SQLiteDatabase db) {

        String createTableStatement = " CREATE TABLE " + PACIENTEX_TABLE + " ( " + COLUMN_ID + " TEXT PRIMARY KEY, " + COLUMN_CI + " INTEGER(60), " + COLUMN_NOMBRE_PACIENTE + " TEXT(60), " + COLUMN_CEL_PACIENTE + " TEXT(60), " + COLUMN_EDAD_PACIENTE + " INTEGER(60), " + COLUMN_REPRESENTANTE_PACIENTE + " TEXT(60))";
        db.execSQL(createTableStatement);


    }
    /// Esto es para que se mantenga la base de datos aun con las actualizaciones
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + PACIENTEX_TABLE);
        onCreate(db);
    }

    public boolean addOne (Pacientes pacientes) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_ID, pacientes.getId());
        cv.put(COLUMN_CI, pacientes.getCi());
        cv.put(COLUMN_NOMBRE_PACIENTE, pacientes.getNombre());
        cv.put(COLUMN_CEL_PACIENTE, pacientes.getCel());
        cv.put(COLUMN_EDAD_PACIENTE, pacientes.getEdad());
        cv.put(COLUMN_REPRESENTANTE_PACIENTE, pacientes.getRepre());

        long insert = db.insert(PACIENTEX_TABLE, null, cv);

        if (insert== -1){
            return false;
        } else
        {
            return true;
        }

    }

    public boolean modificarOne (Pacientes pacientes) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_ID, pacientes.getId());
        cv.put(COLUMN_CI, pacientes.getCi());
        cv.put(COLUMN_NOMBRE_PACIENTE, pacientes.getNombre());
        cv.put(COLUMN_CEL_PACIENTE, pacientes.getCel());
        cv.put(COLUMN_EDAD_PACIENTE, pacientes.getEdad());
        cv.put(COLUMN_REPRESENTANTE_PACIENTE, pacientes.getRepre());

        int cantidad = db.update(PACIENTEX_TABLE, cv, COLUMN_ID + " = ?", new String[]{pacientes.getId()});
        if(cantidad!=0){
           return true;
        }else{
            return false;
        }

    }



    public List<Pacientes> getEveryone (){

        List<Pacientes> returnList = new ArrayList<>();

        String queryString = " SELECT * FROM " + PACIENTEX_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString, null);

        if(cursor.moveToFirst()) {

            do {
                String idPaciente = cursor.getString(0);
                int ciPaciente = cursor.getInt(1);
                String nomPaciente= cursor.getString(2);
                String celularPaciente = cursor.getString(3);
                int edPaciente = cursor.getInt(4);
                String representantePaciente = cursor.getString(5);

                Pacientes nuevoPaciente = new Pacientes(idPaciente,ciPaciente,nomPaciente,celularPaciente,edPaciente,representantePaciente);

                returnList.add(nuevoPaciente);

            }
            while (cursor.moveToNext());
        }else{
            //// no agrega nada
        }

        cursor.close();
        db.close();
        return returnList;
    }

    public boolean deleteOne (Pacientes pacientes){

        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = " DELETE  FROM "  + PACIENTEX_TABLE + " WHERE " + COLUMN_ID + " = ?";

        Cursor cursor= db.rawQuery(queryString, new String []{pacientes.getId()});

        if(cursor.moveToFirst()) {
            return true;
        } else {
            return false;
        }


    }



    public String[] buscarOne (String buscar){
        String[] datos = new String[6];
        SQLiteDatabase db = this.getReadableDatabase();
        String queryString = "SELECT FROM " + datos + " WHERE " + COLUMN_CI + "=" + buscar;

        Cursor cursor= db.rawQuery(queryString, null);

        if(cursor.moveToFirst()){
                for (int i = 0 ; i<5; i++) {
                    datos[i] = cursor.getString(i);
                }
                datos[5] = "Encontrado";
        } else{
            datos[5] = "No se encontro " + buscar;
        }
        return datos;
    }

}
