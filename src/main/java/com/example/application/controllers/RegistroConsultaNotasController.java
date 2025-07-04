package com.example.application.controllers;

import com.example.application.interfaces.IRegistroConsultaNotasRepository;
import com.example.application.models.registro.RegistroConsultaNotas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegistroConsultaNotasController {

    private final IRegistroConsultaNotasRepository registroNotasRepository;

    @Autowired
    public RegistroConsultaNotasController(IRegistroConsultaNotasRepository registroNotasRepository) {
        this.registroNotasRepository = registroNotasRepository;
    }

    public RegistroConsultaNotas guardarRegistro(RegistroConsultaNotas registro) {
        return registroNotasRepository.save(registro);
    }

    public List<RegistroConsultaNotas> obtenerPorEstudiante(String estudianteId) {
        return registroNotasRepository.findByEstudianteId(estudianteId);
    }

    public List<RegistroConsultaNotas> obtenerPorAsignatura(String asignaturaId) {
        return registroNotasRepository.findByAsignaturaId(asignaturaId);
    }

    public List<RegistroConsultaNotas> obtenerPorPeriodo(String periodo) {
        return registroNotasRepository.findByPeriodo(periodo);
    }

    public List<RegistroConsultaNotas> obtenerTodos() {
        return registroNotasRepository.findAll();
    }
}