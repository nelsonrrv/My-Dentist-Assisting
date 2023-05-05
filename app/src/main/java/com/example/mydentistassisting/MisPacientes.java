package com.example.mydentistassisting;

import android.provider.ContactsContract;

import java.util.ArrayList;
import java.util.List;

public class MisPacientes {

    DataBaseHelper dataBaseHelper;

    List<Pacientes> miListaPacientes;


    public MisPacientes(List<Pacientes> miListaPacientes) {
        this.miListaPacientes = miListaPacientes;
    }

    public MisPacientes(){
        this.miListaPacientes = new ArrayList<>();


    }

    public List<Pacientes> getMiListaPacientes() {
        return miListaPacientes;
    }

    public void setMiListaPacientes(List<Pacientes> miListaPacientes) {
        this.miListaPacientes = miListaPacientes;
    }
}
