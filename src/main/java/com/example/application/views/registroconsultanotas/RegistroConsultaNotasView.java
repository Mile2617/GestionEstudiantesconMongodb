package com.example.application.views.registroconsultanotas;

import com.example.application.controllers.EstudianteViewController;
import com.example.application.controllers.AsignaturaViewController;
import com.example.application.models.asignaturas.Materia;
import com.example.application.models.estudiantes.Estudiante;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.lineawesome.LineAwesomeIconUrl;

import java.util.*;

@PageTitle("Registro y Consulta de Notas")
@Route("registro-consulta")
@Menu(order = 4, icon = LineAwesomeIconUrl.PENCIL_RULER_SOLID)
@Uses(Button.class)
public class RegistroConsultaNotasView extends Composite<VerticalLayout> {

    private final Grid<Materia> materiasGrid = new Grid<>(Materia.class, false);
    private final Map<Estudiante, Map<Materia, double[]>> notasPorEstudiante = new HashMap<>();
    private final Span promedioAcumuladoLabel = new Span("Promedio acumulado: 0.00");
    private Estudiante estudianteSeleccionado;
    private String tipoEstudioSeleccionado;

    @Autowired
    public RegistroConsultaNotasView(EstudianteViewController estudianteController, AsignaturaViewController asignaturaController) {
        FormLayout form = new FormLayout();
        ComboBox<String> tipoCombo = new ComboBox<>("Tipo de estudio");
        ComboBox<Estudiante> estudianteCombo = new ComboBox<>("Estudiante");
        Button buscarBtn = new Button("Buscar");

        tipoCombo.setItems("Pregrado", "Postgrado", "Doctorado");
        estudianteCombo.setItems(estudianteController.getEstudiantes().stream()
                .filter(e -> e instanceof Estudiante)
                .map(e -> (Estudiante) e)
                .toList());
        estudianteCombo.setItemLabelGenerator(e -> e.getNombre() + " " + e.getApellido());

        materiasGrid.addThemeVariants(GridVariant.LUMO_ROW_STRIPES);

        buscarBtn.addClickListener(e -> {
            estudianteSeleccionado = estudianteCombo.getValue();
            tipoEstudioSeleccionado = tipoCombo.getValue();
            if (estudianteSeleccionado != null && tipoEstudioSeleccionado != null) {
                actualizarFormatoTabla(tipoEstudioSeleccionado);
                List<Materia> materias = estudianteSeleccionado.getAsignaturas() != null
                        ? estudianteSeleccionado.getAsignaturas().stream()
                        .filter(m -> m instanceof Materia)
                        .map(m -> (Materia) m)
                        .toList()
                        : new ArrayList<>();
                materiasGrid.setItems(materias);
                actualizarPromedioAcumulado();
            } else {
                materiasGrid.setItems(new ArrayList<>());
                promedioAcumuladoLabel.setText("Promedio acumulado: 0.00");
            }
        });

        form.add(tipoCombo, estudianteCombo, buscarBtn);
        getContent().add(form, promedioAcumuladoLabel, new H4("Materias inscritas"), materiasGrid);
    }

    private void actualizarFormatoTabla(String tipoEstudio) {
        materiasGrid.removeAllColumns();
        materiasGrid.addColumn(Materia::getNombre).setHeader("Materia");
        materiasGrid.addColumn(Materia::getCodigo).setHeader("Código");
        materiasGrid.addColumn(Materia::getCreditos).setHeader("Créditos");

        int campos = switch (tipoEstudio) {
            case "Pregrado" -> 3;
            case "Postgrado" -> 4;
            case "Doctorado" -> 6;
            default -> 0;
        };

        for (int i = 0; i < campos; i++) {
            int idx = i;
            materiasGrid.addComponentColumn(materia -> crearCampoNota(estudianteSeleccionado, materia, idx))
                    .setHeader("Nota " + (idx + 1));
        }

        materiasGrid.addColumn(materia -> {
            double notaFinal = calcularNotaFinal(estudianteSeleccionado, materia, tipoEstudioSeleccionado);
            return String.format("%.2f", notaFinal);
        }).setHeader("Nota Final");

        materiasGrid.addComponentColumn(materia -> {
            Button guardarBtn = new Button("Guardar notas", e -> {
                if (validarNotas(estudianteSeleccionado, materia)) {
                    Notification.show("Notas guardadas. Nota final: " +
                            String.format("%.2f", calcularNotaFinal(estudianteSeleccionado, materia, tipoEstudioSeleccionado)));
                    materiasGrid.getDataProvider().refreshItem(materia);
                    actualizarPromedioAcumulado();
                } else {
                    Notification.show("Las notas deben ser numéricas entre 0 y 10");
                }
            });
            return guardarBtn;
        }).setHeader("Acción");
    }

    private NumberField crearCampoNota(Estudiante estudiante, Materia materia, int idx) {
        NumberField campoNota = new NumberField();
        campoNota.setWidth("70px");
        campoNota.setMin(0);
        campoNota.setMax(10);
        campoNota.setStep(0.1);
        campoNota.setValue(getNota(estudiante, materia, idx));
        campoNota.addValueChangeListener(e -> {
            setNota(estudiante, materia, idx, e.getValue());
            actualizarPromedioAcumulado();
        });
        return campoNota;
    }

    private double calcularNotaFinal(Estudiante est, Materia mat, String tipoEstudio) {
        double[] notas = notasPorEstudiante.getOrDefault(est, new HashMap<>()).getOrDefault(mat, new double[6]);
        return switch (tipoEstudio) {
            case "Pregrado" -> notas[0] * 0.25 + notas[1] * 0.35 + notas[2] * 0.40;
            case "Postgrado" -> notas[0] * 0.40 + notas[1] * 0.20 + notas[2] * 0.10 + notas[3] * 0.30;
            case "Doctorado" -> notas[0] * 0.20 + notas[1] * 0.10 + notas[2] * 0.10 + notas[3] * 0.25 + notas[4] * 0.15 + notas[5] * 0.20;
            default -> 0;
        };
    }

    private double getNota(Estudiante est, Materia mat, int idx) {
        if (est == null || mat == null) return 0.0;
        notasPorEstudiante.putIfAbsent(est, new HashMap<>());
        Map<Materia, double[]> notasMateria = notasPorEstudiante.get(est);
        notasMateria.putIfAbsent(mat, new double[6]);
        return notasMateria.get(mat)[idx];
    }

    private void setNota(Estudiante est, Materia mat, int idx, Double valor) {
        if (est == null || mat == null || valor == null || valor < 0 || valor > 10) return;
        notasPorEstudiante.putIfAbsent(est, new HashMap<>());
        Map<Materia, double[]> notasMateria = notasPorEstudiante.get(est);
        notasMateria.putIfAbsent(mat, new double[6]);
        notasMateria.get(mat)[idx] = valor;
    }

    private boolean validarNotas(Estudiante est, Materia mat) {
        if (est == null || mat == null) return false;
        double[] notas = notasPorEstudiante.getOrDefault(est, new HashMap<>()).getOrDefault(mat, new double[6]);
        for (double n : notas) {
            if (n < 0 || n > 10) return false;
        }
        return true;
    }

    private void actualizarPromedioAcumulado() {
        if (estudianteSeleccionado == null) return;
        Map<Materia, double[]> notasMateria = notasPorEstudiante.getOrDefault(estudianteSeleccionado, new HashMap<>());
        double totalCreditos = 0;
        double sumaPonderada = 0;

        for (Materia materia : notasMateria.keySet()) {
            int creditos = materia.getCreditos();
            double notaFinal = calcularNotaFinal(estudianteSeleccionado, materia, tipoEstudioSeleccionado);
            sumaPonderada += notaFinal * creditos;
            totalCreditos += creditos;
        }

        double promedio = totalCreditos > 0 ? sumaPonderada / totalCreditos : 0;
        promedioAcumuladoLabel.setText("Promedio acumulado: " + String.format("%.2f", promedio));
    }
}