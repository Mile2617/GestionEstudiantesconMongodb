package com.example.application.controllers;

import com.example.application.models.asignaturas.Materia;
import com.example.application.models.asignaturas.Modulo;
import com.example.application.models.estudiantes.Postgrado;
import com.example.application.models.estudiantes.Pregrado;
import com.example.application.utils.Util;

import java.util.Date;
import java.util.List;

public class SistemaGestion {


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

    public IAsignatura crearModulo(String nombre, String codigo, int creditos) {
        IAsignatura asignatura = new Modulo(nombre, codigo, creditos);
        Util.asignaturas.add(asignatura);
        return asignatura;
    }

    public IAsignatura crearMateria(String nombre, String codigo, int creditos) {
        IAsignatura asignatura = new Materia(nombre, codigo, creditos);
        Util.asignaturas.add(asignatura);
        return asignatura;
    }

    public void eliminarEstudiante(IEstudiante estudiante) {
        Util.estudiantes.remove(estudiante);
    }

    public void eliminarAsignatura(IAsignatura asignatura) {
        Util.asignaturas.remove(asignatura);
    }

    public List<IEstudiante> getEstudiantes() {
        return Util.estudiantes;
    }

    public List<IAsignatura> getAsignaturas() {
        return Util.asignaturas;
    }


}
