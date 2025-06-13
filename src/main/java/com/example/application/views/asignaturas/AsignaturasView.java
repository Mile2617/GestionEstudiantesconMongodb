package com.example.application.views.asignaturas;


import com.example.application.interfaces.IAsignatura;
import com.example.application.controllers.AsignaturaViewController;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility.Gap;
import org.vaadin.lineawesome.LineAwesomeIconUrl;

@PageTitle("Asignaturas")
@Route("asignaturas")
@Menu(order = 2, icon = LineAwesomeIconUrl.BOOK_OPEN_SOLID)
@Uses(Icon.class)
public class AsignaturasView extends Composite<VerticalLayout> {

    Grid<IAsignatura> gridAsignaturas;
    Button buttonNuevaAsignatura;
    AsignaturaViewController sg;

    public AsignaturasView() {
        sg = new AsignaturaViewController();
        HorizontalLayout layoutRow = new HorizontalLayout();
        buttonNuevaAsignatura = new Button();
        gridAsignaturas = new Grid<>(IAsignatura.class, false);
        gridAsignaturas.addColumn(IAsignatura::getNombre).setHeader("Nombre");
        gridAsignaturas.addColumn(IAsignatura::getCodigo).setHeader("Código");
        gridAsignaturas.addColumn(IAsignatura::getCreditos).setHeader("Créditos");
        gridAsignaturas.addColumn(asig -> asig.getClass().getSimpleName()).setHeader("Tipo");
        gridAsignaturas.addColumn(
                new ComponentRenderer<>(asignatura -> {
                    Button botonBorrar = new Button();
                    botonBorrar.addThemeVariants(ButtonVariant.LUMO_ERROR);
                    botonBorrar.addClickListener(e -> onClickBorrarAsignatura(asignatura));
                    botonBorrar.setIcon(new Icon(VaadinIcon.TRASH));

                    Button botonEditar = new Button();
                    botonEditar.addThemeVariants(ButtonVariant.LUMO_WARNING);
                    botonEditar.addClickListener(e ->
                            getUI().ifPresent(ui -> ui.navigate("nueva-asignatura/edit/" + asignatura.getCodigo()))
                    );
                    botonEditar.setIcon(new Icon(VaadinIcon.EDIT));

                    Button botonVer = new Button();
                    botonVer.addThemeVariants(ButtonVariant.LUMO_SUCCESS);
                    botonVer.addClickListener(e ->
                            getUI().ifPresent(ui -> ui.navigate("nueva-asignatura/view/" + asignatura.getCodigo()))
                    );
                    botonVer.setIcon(new Icon(VaadinIcon.EYE));

                    HorizontalLayout buttons = new HorizontalLayout(botonBorrar, botonEditar, botonVer);
                    return buttons;
                })).setHeader("Acciones").setAutoWidth(true);

        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");
        layoutRow.setWidthFull();
        getContent().setFlexGrow(1.0, layoutRow);
        layoutRow.addClassName(Gap.MEDIUM);
        layoutRow.setWidth("100%");
        layoutRow.getStyle().set("flex-grow", "1");
        buttonNuevaAsignatura.setText("Nueva Asignatura");
        buttonNuevaAsignatura.setWidth("min-content");
        buttonNuevaAsignatura.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buttonNuevaAsignatura.addClickListener(e -> onClickNuevaAsignatura());
        gridAsignaturas.addThemeVariants(GridVariant.LUMO_ROW_STRIPES);
        gridAsignaturas.setWidth("100%");
        gridAsignaturas.getStyle().set("flex-grow", "0");
        gridAsignaturas.setItems(sg.getAsignaturas());
        getContent().add(layoutRow);
        layoutRow.add(buttonNuevaAsignatura);
        getContent().add(gridAsignaturas);
    }

    public void onClickBorrarAsignatura(IAsignatura asignatura) {
        sg.eliminarAsignatura(asignatura);
        gridAsignaturas.getDataProvider().refreshAll();
    }

    public void onClickNuevaAsignatura() {
        buttonNuevaAsignatura.getUI().ifPresent(ui -> ui.navigate("nueva-asignatura"));
    }
}
