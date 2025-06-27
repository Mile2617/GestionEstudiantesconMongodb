package com.example.application.views.estudiantes;


import com.example.application.controllers.EstudianteViewController;
import com.example.application.interfaces.IEstudiante;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.*;
import com.vaadin.flow.theme.lumo.LumoUtility.Gap;
import org.vaadin.lineawesome.LineAwesomeIconUrl;

import java.util.Date;

@PageTitle("Nuevo Estudiante")
@Route("new-student/:action?(view|edit)/:id?")
@Menu(order = 1, icon = LineAwesomeIconUrl.PENCIL_RULER_SOLID)
public class NuevoEstudianteView extends Composite<VerticalLayout> implements BeforeEnterObserver {

    TextField tfNombre;
    TextField tfApellido;
    EmailField efEmail;
    DatePicker dpFechaNacimiento;
    NumberField nfEdad;
    TextField tfTelefono;
    Button btGuardar;
    Button btCancelar;
    ComboBox<String> cbTipoAlumno;
    String action;
    IEstudiante estudiante;
    String estudianteId;
    EstudianteViewController sg;

    public NuevoEstudianteView(EstudianteViewController sg) {
        this.sg = sg;
        FormLayout formLayout2Col = new FormLayout();
        tfNombre = new TextField();
        tfApellido = new TextField();
        efEmail = new EmailField();
        dpFechaNacimiento = new DatePicker();
        nfEdad = new NumberField();
        tfTelefono = new TextField();
        cbTipoAlumno = new ComboBox<>();
        HorizontalLayout layoutRow = new HorizontalLayout();
        btGuardar = new Button();
        btCancelar = new Button();
        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");
        formLayout2Col.setWidth("100%");
        tfNombre.setLabel("Nombres");
        tfNombre.setWidth("min-content");
        tfApellido.setLabel("Apellidos");
        tfApellido.setWidth("min-content");
        efEmail.setLabel("Email");
        efEmail.setWidth("min-content");
        dpFechaNacimiento.setLabel("Fecha de nacimiento");
        dpFechaNacimiento.setWidth("min-content");
        nfEdad.setLabel("Edad");
        nfEdad.setWidth("min-content");
        tfTelefono.setLabel("Teléfono");
        tfTelefono.setWidth("min-content");
        cbTipoAlumno.setLabel("Tipo de Alumno");
        cbTipoAlumno.setItems("Pregrado", "Postgrado");
        cbTipoAlumno.setWidth("min-content");
        layoutRow.setWidthFull();
        getContent().setFlexGrow(1.0, layoutRow);
        layoutRow.addClassName(Gap.MEDIUM);
        layoutRow.setWidth("100%");
        layoutRow.getStyle().set("flex-grow", "1");
        btGuardar.setText("Guardar");
        btGuardar.setWidth("min-content");
        btGuardar.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        btGuardar.addClickListener(e -> onClickGuardar());
        btCancelar.setText("Cancelar");
        btCancelar.setWidth("min-content");
        btCancelar.addClickListener(e->onClickCancelar());
        getContent().add(formLayout2Col);
        formLayout2Col.add(tfNombre);
        formLayout2Col.add(tfApellido);
        formLayout2Col.add(efEmail);
        formLayout2Col.add(dpFechaNacimiento);
        formLayout2Col.add(nfEdad);
        formLayout2Col.add(tfTelefono);
        formLayout2Col.add(cbTipoAlumno); // Agregar el ComboBox al formulario
        getContent().add(layoutRow);
        layoutRow.add(btGuardar);
        layoutRow.add(btCancelar);



    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        estudianteId = event.getRouteParameters().get("id").orElse(null);
        action = event.getRouteParameters().get("action").orElse(null);
        if (action!=null && (action.equals("edit") || action.equals("view"))) {
            if (estudianteId != null) {
                estudiante = sg.getEstudiantes().stream().filter(e -> e.getId().equals(estudianteId)).findFirst().orElse(null);
                if (estudiante != null) {
                    setEstudiante(estudiante);
                    if(action.equals("view")) {
                        disableFields();
                    }
                }else{
                    event.forwardTo(EstudiantesView.class);
                }
            }else{
                event.forwardTo(EstudiantesView.class);
            }
        }
    }

    public void setEstudiante(IEstudiante estudiante) {
        tfNombre.setValue(estudiante.getNombre());
        tfApellido.setValue(estudiante.getApellido());
        efEmail.setValue(estudiante.getEmail());
        dpFechaNacimiento.setValue(estudiante.getFechaNacimiento().toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate());
        nfEdad.setValue((double) estudiante.getEdad());
        tfTelefono.setValue(estudiante.getTelefono());
        cbTipoAlumno.setValue(estudiante.getTipoEstudiante());
        cbTipoAlumno.setEnabled(false);

    }

    public void disableFields() {
        tfNombre.setEnabled(false);
        tfApellido.setEnabled(false);
        efEmail.setEnabled(false);
        dpFechaNacimiento.setEnabled(false);
        nfEdad.setEnabled(false);
        tfTelefono.setEnabled(false);
        cbTipoAlumno.setEnabled(false);
        btGuardar.setVisible(false);
    }

    public void onClickCancelar(){
        btCancelar.getUI().ifPresent(ui -> ui.navigate("/"));
    }

    public void onClickGuardar() {
        if (action == null) {
            String id = String.valueOf(new java.util.Random().nextInt(10000));
            String nombre = tfNombre.getValue();
            String apellido = tfApellido.getValue();
            String email = efEmail.getValue();
            String telefono = tfTelefono.getValue();
            Integer edad = nfEdad.getValue().intValue();
            Date fecha =  java.util.Date.from(dpFechaNacimiento.getValue().atStartOfDay(java.time.ZoneId.systemDefault()).toInstant());
            IEstudiante estudiante;
            if (cbTipoAlumno.getValue().equals("Pregrado")) {
               estudiante = sg.crearEstudiantePregrado(id,nombre,apellido,email,telefono,edad,fecha,"Carrera Pregrado");
            } else if (cbTipoAlumno.getValue().equals("Postgrado")) {
               estudiante = sg.crearEstudiantePostgrado(id,nombre,apellido,email,telefono,edad,fecha,"Carrera Pregrado");

            }

        } else if (action.equals("edit")) {
            if(estudianteId!=null) {
                estudiante.setNombre(tfNombre.getValue());
                estudiante.setApellido(tfApellido.getValue());
                estudiante.setEmail(efEmail.getValue());
                estudiante.setTelefono(tfTelefono.getValue());
                estudiante.setEdad(nfEdad.getValue().intValue());
                estudiante.setFechaNacimiento(java.util.Date.from(dpFechaNacimiento.getValue().atStartOfDay(java.time.ZoneId.systemDefault()).toInstant()));
            }
        }
        btGuardar.getUI().ifPresent(ui -> ui.navigate("/"));
    }
}