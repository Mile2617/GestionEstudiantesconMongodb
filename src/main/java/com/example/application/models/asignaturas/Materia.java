package com.example.application.models.asignaturas;

import java.util.HashMap;

public class Materia extends Asignatura {

    private HashMap<Integer, HashMap<String,Double>> progresos = new HashMap<>();
    private Double[] notasProgresos = {0.0, 0.0, 0.0};

    public Materia(String nombre, String codigo, int creditos) {
        super(nombre, codigo, creditos);
    }


    public void crearActividadPorProgreso(Integer progreso, String nombreActividad) {
        HashMap<String,Double> actividad = new HashMap<>();
        actividad.put(nombreActividad, 0.0);
        progresos.put(progreso,actividad);
    }

    public void calificarActividadPorProgreso(Integer progreso, String nombreActividad, Double calificacion) {
        HashMap<String,Double> actividad = progresos.get(progreso);
        actividad.put(nombreActividad, calificacion);
    }

    public void calcularNotasProgresos() {
        for (int i = 0; i < notasProgresos.length; i++) {
            HashMap<String, Double> actividades = progresos.get(i);
            if (actividades != null) {
                double suma = actividades.values().stream().mapToDouble(Double::doubleValue).sum();
                notasProgresos[i] = suma / actividades.size();
            } else {
                notasProgresos[i] = 0.0;
            }
        }
    }

    @Override
    public String getTipoAsignatura() {
        return "Materia";
    }

    @Override
    public Double calcularPromedio() {
        this.calcularNotasProgresos();
        double promedio = 0;
        for (int i = 0; i < notasProgresos.length; i++) {
           promedio += notasProgresos[i];
        }
        return promedio / notasProgresos.length;
    }
}
