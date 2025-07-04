package com.example.application.views.inscripcion;

import com.example.application.controllers.InscripcionViewController;
import com.example.application.models.asignaturas.Materia;
import com.example.application.models.estudiantes.Estudiante;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.lineawesome.LineAwesomeIconUrl;

import java.util.ArrayList;
import java.util.List;

@PageTitle("Inscripción")
@Route("inscripcion")
@Menu(order = 3, icon = LineAwesomeIconUrl.BOOK_OPEN_SOLID)
@Uses(Button.class)
public class InscripcionView extends Composite<VerticalLayout> {

    private final Grid<Materia> materiasDisponiblesGrid = new Grid<>(Materia.class, false);
    private final Grid<Materia> materiasInscritasGrid = new Grid<>(Materia.class, false);

    @Autowired
    public InscripcionView(InscripcionViewController inscripcionController) {
        FormLayout form = new FormLayout();
        ComboBox<Estudiante> estudianteCombo = new ComboBox<>("Estudiante");
        Button inscribirBtn = new Button("Inscribir seleccionadas");

        estudianteCombo.setItems(inscripcionController.listarEstudiantes());
        estudianteCombo.setItemLabelGenerator(e -> e.getNombre() + " " + e.getApellido());

        materiasDisponiblesGrid.addColumn(Materia::getNombre).setHeader("Materia");
        materiasDisponiblesGrid.addColumn(Materia::getCodigo).setHeader("Código");
        materiasDisponiblesGrid.addColumn(Materia::getCreditos).setHeader("Créditos");
        materiasDisponiblesGrid.setSelectionMode(Grid.SelectionMode.MULTI);
        materiasDisponiblesGrid.addThemeVariants(GridVariant.LUMO_ROW_STRIPES);

        materiasInscritasGrid.addColumn(Materia::getNombre).setHeader("Materia");
        materiasInscritasGrid.addColumn(Materia::getCodigo).setHeader("Código");
        materiasInscritasGrid.addColumn(Materia::getCreditos).setHeader("Créditos");
        materiasInscritasGrid.addThemeVariants(GridVariant.LUMO_ROW_STRIPES);

        estudianteCombo.addValueChangeListener(event -> actualizarGrids(inscripcionController, estudianteCombo));

        inscribirBtn.addClickListener(e -> {
            Estudiante estudiante = estudianteCombo.getValue();
            if (estudiante == null) {
                Notification.show("Seleccione un estudiante");
                return;
            }
            List<Materia> seleccionadas = new ArrayList<>(materiasDisponiblesGrid.getSelectedItems());
            if (seleccionadas.isEmpty()) {
                Notification.show("Seleccione al menos una materia");
                return;
            }
            boolean exito = inscripcionController.inscribirMaterias(estudiante, seleccionadas);
            if (exito) {
                Notification.show("Materias inscritas correctamente");
            } else {
                Notification.show("No se pudo inscribir (ya inscrito o error)");
            }
            actualizarGrids(inscripcionController, estudianteCombo);
        });

        form.add(estudianteCombo);
        HorizontalLayout gridsLayout = new HorizontalLayout();
        gridsLayout.setWidthFull();
        materiasDisponiblesGrid.setWidth("50%");
        materiasInscritasGrid.setWidth("50%");
        gridsLayout.add(materiasDisponiblesGrid, materiasInscritasGrid);

        getContent().add(form, gridsLayout, inscribirBtn);
    }

    private void actualizarGrids(InscripcionViewController inscripcionController, ComboBox<Estudiante> estudianteCombo) {
        Estudiante estudiante = estudianteCombo.getValue();
        List<Materia> inscritas = estudiante != null
                ? inscripcionController.materiasInscritas(estudiante)
                : new ArrayList<>();
        List<Materia> disponibles = estudiante != null
                ? inscripcionController.materiasDisponibles(estudiante)
                : new ArrayList<>();
        materiasDisponiblesGrid.setItems(disponibles);
        materiasInscritasGrid.setItems(inscritas);
    }
}