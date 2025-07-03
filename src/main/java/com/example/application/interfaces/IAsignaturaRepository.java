package com.example.application.interfaces;

import com.example.application.models.asignaturas.Asignatura;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IAsignaturaRepository extends MongoRepository<Asignatura, String> {
    List<Asignatura> findByNombre(String nombre);
}