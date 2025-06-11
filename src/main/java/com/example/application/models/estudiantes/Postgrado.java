package com.example.application.models.estudiantes;

import com.example.application.models.asignaturas.Modulo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Postgrado extends Estudiante {


    private String carrera;
    private List<Modulo> modulos = new ArrayList<>();

    public Postgrado(String id, String nombre, String apellido, String email, String telefono, int edad, Date fechaNacimiento, String carrera) {
        super(id, nombre, apellido, email, telefono, edad, fechaNacimiento);
        this.carrera = carrera;
    }

    public Postgrado() {
        super();
    }

    @Override
    public String getTipoEstudiante() {
        return "Postgrado";
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }
}