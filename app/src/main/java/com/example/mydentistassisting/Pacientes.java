package com.example.mydentistassisting;


public class Pacientes {


    private String id;
    private  int ci;
    private  String nombre;
    private String cel;
    private  int edad;
    private  String repre;



    ///Constructures


    public Pacientes( String id, int ci, String nombre, String cel, int edad, String repre) {
        this.id = id;
        this.ci = ci;
        this.nombre = nombre;
        this.cel = cel;
        this.edad = edad;
        this.repre = repre;
    }

    public Pacientes() {
    }


    /// toString es necesario para mostrar los contenidos de una clase


    @Override
    public String toString() {
        return "Pacientes{" +
                "id='" + id + '\'' +
                ", ci=" + ci +
                ", nombre='" + nombre + '\'' +
                ", cel='" + cel + '\'' +
                ", edad=" + edad +
                ", repre='" + repre + '\'' +
                '}';
    }

    /// getters and setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getCi() {
        return ci;
    }

    public void setCi(int ci) {
        this.ci = ci;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCel() {
        return cel;
    }

    public void setCel(String cel) {
        this.cel = cel;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getRepre() {
        return repre;
    }

    public void setRepre(String repre) {
        this.repre = repre;
    }
}


