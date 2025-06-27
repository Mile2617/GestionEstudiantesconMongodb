package com.example.application.controllers;

import com.example.application.interfaces.IEstudiante;
import com.example.application.interfaces.IEstudianteRepository;
import com.example.application.models.estudiantes.Estudiante;
import com.example.application.models.estudiantes.Postgrado;
import com.example.application.models.estudiantes.Pregrado;
import com.example.application.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Service
public class EstudianteViewController {

    IEstudianteRepository estudianteRepository;

    public EstudianteViewController(IEstudianteRepository estudianteRepository){
        this.estudianteRepository=estudianteRepository;
    }

    public IEstudiante crearEstudiantePregrado(String id, String nombre, String apellido, String email, String telefono, int edad, Date fecha, String carrera) {
        IEstudiante estudiante = new Pregrado(id,nombre,apellido,email,telefono,edad,fecha,carrera);
        Estudiante estudiante1 = new Pregrado(id,nombre,apellido,email,telefono,edad,fecha,carrera);
        //Util.estudiantes.add(estudiante);
        estudianteRepository.save(estudiante1);
        return estudiante;
    }

    public IEstudiante crearEstudiantePostgrado(String id, String nombre, String apellido, String email, String telefono, int edad, Date fecha, String carrera) {
        IEstudiante estudiante = new Postgrado(id,nombre,apellido,email,telefono,edad,fecha,carrera);
        Estudiante estudiante2 = new Postgrado(id,nombre,apellido,email,telefono,edad,fecha,carrera);
        //Util.estudiantes.add(estudiante);
        estudianteRepository.save(estudiante2);
        return estudiante;
    }

    public void eliminarEstudiante(IEstudiante estudiante) {
        Util.estudiantes.remove(estudiante);
    }

    public List<IEstudiante> getEstudiantes() {
        return estudianteRepository.findAll().stream().map(IEstudiante.class::cast).collect(toList());
    }
    }