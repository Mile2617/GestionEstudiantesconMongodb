package com.example.application.models.inscripciones;

import com.example.application.interfaces.IInscripciones;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
public class Inscripciones implements IInscripciones {
    @Id
    private String id;
    private String estudianteId;
    private List<String> asignaturasIds;
    private String periodo;

    @Override
    public String getId() { return id; }
    @Override
    public void setId(String id) { this.id = id; }

    @Override
    public String getEstudianteId() { return estudianteId; }
    @Override
    public void setEstudianteId(String estudianteId) { this.estudianteId = estudianteId; }

    @Override
    public List<String> getAsignaturasIds() { return asignaturasIds; }
    @Override
    public void setAsignaturasIds(List<String> asignaturasIds) { this.asignaturasIds = asignaturasIds; }

    @Override
    public String getPeriodo() { return periodo; }
    @Override
    public void setPeriodo(String periodo) { this.periodo = periodo; }
}