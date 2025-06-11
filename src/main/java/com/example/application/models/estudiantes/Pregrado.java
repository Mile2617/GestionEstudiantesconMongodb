package com.example.application.models.estudiantes;

import com.example.application.models.asignaturas.Materia;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Pregrado extends Estudiante {

    private String carrera;
    private List<Materia> materias = new ArrayList<>();

    public Pregrado(String id, String nombre, String apellido, String email, String telefono, int edad, Date fechaNacimiento, String carrera) {
        super(id, nombre, apellido, email, telefono, edad, fechaNacimiento);
        this.carrera = carrera;
    }

    public Pregrado() {
        super();
    }

    @Override
    public String getTipoEstudiante() {
        return "Pregrado";
    }

    public String getCarrera() {
        return carrera;
    }
    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }
}
