package com.example.application.interfaces;

import com.example.application.models.registro.RegistroConsultaNotas;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IRegistroConsultaNotasRepository extends MongoRepository<RegistroConsultaNotas, String> {
    List<RegistroConsultaNotas> findByEstudianteId(String estudianteId);
    List<RegistroConsultaNotas> findByAsignaturaId(String asignaturaId);
    List<RegistroConsultaNotas> findByPeriodo(String periodo);
}