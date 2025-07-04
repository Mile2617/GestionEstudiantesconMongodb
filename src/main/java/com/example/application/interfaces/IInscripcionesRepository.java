package com.example.application.interfaces;

import com.example.application.models.inscripciones.Inscripciones;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IInscripcionesRepository extends MongoRepository<Inscripciones, String> {
    List<Inscripciones> findByEstudianteId(String estudianteId);
    List<Inscripciones> findByPeriodo(String periodo);
}