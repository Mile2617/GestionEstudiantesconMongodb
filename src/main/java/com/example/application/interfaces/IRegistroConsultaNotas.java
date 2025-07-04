package com.example.application.interfaces;

import java.util.List;

public interface IRegistroConsultaNotas {
    String getId();
    void setId(String id);

    String getEstudianteId();
    void setEstudianteId(String estudianteId);

    String getAsignaturaId();
    void setAsignaturaId(String asignaturaId);

    List<Double> getNotas();
    void setNotas(List<Double> notas);

    String getTipoEstudio();
    void setTipoEstudio(String tipoEstudio);

    String getPeriodo();
    void setPeriodo(String periodo);
}