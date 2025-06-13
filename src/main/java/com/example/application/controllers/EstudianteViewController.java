package com.example.application.controllers;

import com.example.application.interfaces.IEstudiante;
import com.example.application.models.estudiantes.Postgrado;
import com.example.application.models.estudiantes.Pregrado;
import com.example.application.utils.Util;

import java.util.Date;
import java.util.List;

public class EstudianteViewController {

    public IEstudiante crearEstudiantePregrado(String id, String nombre, String apellido, String email, String telefono, int edad, Date fecha, String carrera) {
        IEstudiante estudiante = new Pregrado(id,nombre,apellido,email,telefono,edad,fecha,carrera);
        Util.estudiantes.add(estudiante);
        return estudiante;
    }

    public IEstudiante crearEstudiantePostgrado(String id, String nombre, String apellido, String email, String telefono, int edad, Date fecha, String carrera) {
        IEstudiante estudiante = new Postgrado(id,nombre,apellido,email,telefono,edad,fecha,carrera);
        Util.estudiantes.add(estudiante);
        return estudiante;
    }

    public void eliminarEstudiante(IEstudiante estudiante) {
        Util.estudiantes.remove(estudiante);
    }

    public List<IEstudiante> getEstudiantes() {
        return Util.estudiantes;
    }
}
