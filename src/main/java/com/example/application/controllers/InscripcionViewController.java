package com.example.application.controllers;

import com.example.application.interfaces.IEstudianteRepository;
import com.example.application.interfaces.IAsignaturaRepository;
import com.example.application.models.asignaturas.Materia;
import com.example.application.models.estudiantes.Estudiante;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InscripcionViewController {

    private final IEstudianteRepository estudianteRepository;
    private final IAsignaturaRepository asignaturaRepository;

    @Autowired
    public InscripcionViewController(IEstudianteRepository estudianteRepository, IAsignaturaRepository asignaturaRepository) {
        this.estudianteRepository = estudianteRepository;
        this.asignaturaRepository = asignaturaRepository;
    }

    public List<Estudiante> listarEstudiantes() {
        // Returns all students from the repository
        return estudianteRepository.findAll().stream()
                .filter(e -> e instanceof Estudiante)
                .map(e -> (Estudiante) e)
                .collect(Collectors.toList());
    }

    public List<Materia> listarMaterias() {
        // Returns all materias from the repository
        return asignaturaRepository.findAll().stream()
                .filter(a -> a instanceof Materia)
                .map(a -> (Materia) a)
                .collect(Collectors.toList());
    }

    public List<Materia> materiasInscritas(Estudiante estudiante) {
        // Returns materias the student is already enrolled in
        return estudiante.getAsignaturas() != null
                ? estudiante.getAsignaturas().stream()
                .filter(a -> a instanceof Materia)
                .map(a -> (Materia) a)
                .collect(Collectors.toList())
                : new ArrayList<>();
    }

    public List<Materia> materiasDisponibles(Estudiante estudiante) {
        // Returns materias not yet enrolled by the student
        List<Materia> todas = listarMaterias();
        List<Materia> inscritas = materiasInscritas(estudiante);
        return todas.stream()
                .filter(m -> !inscritas.contains(m))
                .collect(Collectors.toList());
    }

    public boolean inscribirMaterias(Estudiante estudiante, List<Materia> materias) {
        if (estudiante == null || materias == null || materias.isEmpty()) return false;
        List<Materia> actuales = materiasInscritas(estudiante);
        List<Materia> nuevas = materias.stream()
                .filter(m -> !actuales.contains(m))
                .collect(Collectors.toList());
        if (nuevas.isEmpty()) return false;
        actuales.addAll(nuevas);
        // Set updated list and persist
        estudiante.getAsignaturas().clear();
        estudiante.getAsignaturas().addAll(actuales);
        estudianteRepository.save(estudiante);
        return true;
    }
}