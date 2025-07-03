package com.example.application.controllers;

import com.example.application.interfaces.IAsignatura;
import com.example.application.interfaces.IAsignaturaRepository;
import com.example.application.models.asignaturas.Materia;
import com.example.application.models.asignaturas.Modulo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AsignaturaViewController {

    private final IAsignaturaRepository asignaturaRepository;

    @Autowired
    public AsignaturaViewController(IAsignaturaRepository asignaturaRepository) {
        this.asignaturaRepository = asignaturaRepository;
    }

    public IAsignatura crearModulo(String nombre, String codigo, int creditos) {
        Modulo modulo = new Modulo(nombre, codigo, creditos);
        asignaturaRepository.save(modulo);
        return modulo;
    }

    public IAsignatura crearMateria(String nombre, String codigo, int creditos) {
        Materia materia = new Materia(nombre, codigo, creditos);
        asignaturaRepository.save(materia);
        return materia;
    }

    public void eliminarAsignatura(IAsignatura asignatura) {
        asignaturaRepository.deleteById(asignatura.getCodigo());
    }

    public List<IAsignatura> getAsignaturas() {
        return asignaturaRepository.findAll().stream().map(IAsignatura.class::cast).collect(Collectors.toList());
    }
}