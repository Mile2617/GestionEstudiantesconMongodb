package com.example.application.models.registro;

import com.example.application.interfaces.IRegistroConsultaNotas;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
public class RegistroConsultaNotas implements IRegistroConsultaNotas {
    @Id
    private String id;
    private String estudianteId;
    private String asignaturaId;
    private List<Double> notas;
    private String tipoEstudio;
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
    public String getAsignaturaId() { return asignaturaId; }
    @Override
    public void setAsignaturaId(String asignaturaId) { this.asignaturaId = asignaturaId; }

    @Override
    public List<Double> getNotas() { return notas; }
    @Override
    public void setNotas(List<Double> notas) { this.notas = notas; }

    @Override
    public String getTipoEstudio() { return tipoEstudio; }
    @Override
    public void setTipoEstudio(String tipoEstudio) { this.tipoEstudio = tipoEstudio; }

    @Override
    public String getPeriodo() { return periodo; }
    @Override
    public void setPeriodo(String periodo) { this.periodo = periodo; }
}