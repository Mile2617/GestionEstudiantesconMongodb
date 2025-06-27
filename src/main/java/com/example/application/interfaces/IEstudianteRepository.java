package com.example.application.interfaces;

import com.example.application.models.estudiantes.Estudiante;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IEstudianteRepository extends MongoRepository<Estudiante, String> {

    List<Estudiante> findByApellido(String apellido);

}
