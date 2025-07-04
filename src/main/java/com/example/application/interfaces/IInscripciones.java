package com.example.application.interfaces;

import java.util.List;

public interface IInscripciones {
    String getId();
    void setId(String id);

    String getEstudianteId();
    void setEstudianteId(String estudianteId);

    List<String> getAsignaturasIds();
    void setAsignaturasIds(List<String> asignaturasIds);

    String getPeriodo();
    void setPeriodo(String periodo);
}