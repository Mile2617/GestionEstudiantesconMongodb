package com.example.application.interfaces;

import com.example.application.models.asignaturas.Asignatura;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IAsignaturaRepository extends MongoRepository<Asignatura, String> {
}
