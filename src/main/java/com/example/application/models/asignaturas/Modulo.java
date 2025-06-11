package com.example.application.models.asignaturas;

import java.util.HashMap;

public class Modulo extends Asignatura {

    private HashMap<String, Double> actividades = new HashMap<>();
    private Double promedio;

    public Modulo(String nombre, String codigo, int creditos) {
        super(nombre, codigo, creditos);
    }

    public void crearActividad(String nombreActividad) {
        actividades.put(nombreActividad,0.0);
    }

    public void calificarActividad(String nombreActividad, Double calificacion) {
        actividades.put(nombreActividad, calificacion);
    }

    public void calcularNotas(){
        double suma = actividades.values().stream().mapToDouble(Double::doubleValue).sum();
        promedio = suma / actividades.size();
    }

    @Override
    public String getTipoAsignatura() {
        return "Materia";
    }

    @Override
    public Double calcularPromedio() {
        calcularNotas();
        return promedio;
    }
}
