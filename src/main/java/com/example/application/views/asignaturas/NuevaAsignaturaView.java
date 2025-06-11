package com.example.application.views.asignaturas;

//import com.example.application.models.asignaturas.Asignatura;
//import com.example.application.models.asignaturas.Materia;
//import com.example.application.models.asignaturas.Modulo;
//import com.example.application.utils.Util;
import com.example.application.controllers.IAsignatura;
import com.example.application.controllers.SistemaGestion;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.*;
import com.vaadin.flow.theme.lumo.LumoUtility.Gap;
import org.vaadin.lineawesome.LineAwesomeIconUrl;

@PageTitle("Nueva Asignatura")
@Route("nueva-asignatura/:action?(view|edit)/:codigo?")
@Menu(order = 3, icon = LineAwesomeIconUrl.BOOK_OPEN_SOLID)
public class NuevaAsignaturaView extends Composite<VerticalLayout> implements BeforeEnterObserver {

    TextField tfNombre;
    TextField tfCodigo;
    NumberField nfCreditos;
    ComboBox<String> cbTipoAsignatura;
    Button btGuardar;
    Button btCancelar;
    String action;
    IAsignatura asignatura;
    String codigoAsignatura;
    SistemaGestion sg;

    public NuevaAsignaturaView() {
        sg = new SistemaGestion();
        FormLayout formLayout2Col = new FormLayout();
        tfNombre = new TextField();
        tfCodigo = new TextField();
        nfCreditos = new NumberField();
        cbTipoAsignatura = new ComboBox<>();
        HorizontalLayout layoutRow = new HorizontalLayout();
        btGuardar = new Button();
        btCancelar = new Button();
        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");
        formLayout2Col.setWidth("100%");
        tfNombre.setLabel("Nombre");
        tfCodigo.setLabel("Código");
        nfCreditos.setLabel("Créditos");
        cbTipoAsignatura.setLabel("Tipo de Asignatura");
        cbTipoAsignatura.setItems("Modulo", "Materia");
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
        btCancelar.addClickListener(e -> onClickCancelar());
        getContent().add(formLayout2Col);
        formLayout2Col.add(tfNombre);
        formLayout2Col.add(tfCodigo);
        formLayout2Col.add(nfCreditos);
        formLayout2Col.add(cbTipoAsignatura);
        getContent().add(layoutRow);
        layoutRow.add(btGuardar);
        layoutRow.add(btCancelar);
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        codigoAsignatura = event.getRouteParameters().get("codigo").orElse(null);
        action = event.getRouteParameters().get("action").orElse(null);
        if (action != null && (action.equals("edit") || action.equals("view"))) {
            if (codigoAsignatura != null) {
                asignatura = sg.getAsignaturas().stream().filter(a -> a.getCodigo().equals(codigoAsignatura)).findFirst().orElse(null);
                if (asignatura != null) {
                    setAsignatura(asignatura);
                    if (action.equals("view")) {
                        disableFields();
                    }
                } else {
                    event.forwardTo(AsignaturasView.class);
                }
            } else {
                event.forwardTo(AsignaturasView.class);
            }
        }
    }

    public void setAsignatura(IAsignatura asignatura) {
        tfNombre.setValue(asignatura.getNombre());
        tfCodigo.setValue(asignatura.getCodigo());
        nfCreditos.setValue((double) asignatura.getCreditos());
        cbTipoAsignatura.setValue(asignatura.getTipoAsignatura());
        cbTipoAsignatura.setEnabled(false);
        tfCodigo.setEnabled(false);
    }

    public void disableFields() {
        tfNombre.setEnabled(false);
        tfCodigo.setEnabled(false);
        nfCreditos.setEnabled(false);
        cbTipoAsignatura.setEnabled(false);
        btGuardar.setVisible(false);
    }

    public void onClickCancelar() {
        btCancelar.getUI().ifPresent(ui -> ui.navigate("asignaturas"));
    }

    public void onClickGuardar() {
        if (action == null) {
            String nombre = tfNombre.getValue();
            String codigo = tfCodigo.getValue();
            int creditos = nfCreditos.getValue().intValue();
            IAsignatura nueva;
            if ("Modulo".equals(cbTipoAsignatura.getValue())) {
                nueva = sg.crearModulo(nombre, codigo, creditos);
            } else {
                nueva = sg.crearMateria(nombre, codigo, creditos);
            }
        } else if (action.equals("edit")) {
            if (asignatura != null) {
                asignatura.setNombre(tfNombre.getValue());
                asignatura.setCreditos(nfCreditos.getValue().intValue());
            }
        }
        btGuardar.getUI().ifPresent(ui -> ui.navigate("asignaturas"));
    }
}
