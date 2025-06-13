package com.example.application.controllers;

import com.example.application.interfaces.IAsignatura;
import com.example.application.interfaces.IEstudiante;
import com.example.application.models.asignaturas.Materia;
import com.example.application.models.asignaturas.Modulo;
import com.example.application.models.estudiantes.Postgrado;
import com.example.application.models.estudiantes.Pregrado;
import com.example.application.utils.Util;

import java.util.Date;
import java.util.List;

public class AsignaturaViewController {

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

    public void eliminarAsignatura(IAsignatura asignatura) {
        Util.asignaturas.remove(asignatura);
    }

    public List<IAsignatura> getAsignaturas() {
        return Util.asignaturas;
    }
}
