package com.example.application.models.asignaturas;

import com.example.application.interfaces.IAsignatura;

public abstract class Asignatura implements IAsignatura {

    private String nombre;
    private String codigo;
    private int creditos;

    public Asignatura(String nombre, String codigo, int creditos) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.creditos = creditos;
    }

    public Asignatura() {
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public int getCreditos() {
        return creditos;
    }

    public void setCreditos(int creditos) {
        this.creditos = creditos;
    }

    public abstract String getTipoAsignatura();

    public abstract Double calcularPromedio();
}
