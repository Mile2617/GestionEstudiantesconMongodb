package com.example.application.views.estudiantes;


import com.example.application.controllers.EstudianteViewController;
import com.example.application.interfaces.IEstudiante;
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

@PageTitle("Estudiantes")
@Route("")
@Menu(order = 0, icon = LineAwesomeIconUrl.BOOK_SOLID)
@Uses(Icon.class)
public class EstudiantesView extends Composite<VerticalLayout> {

    Grid<IEstudiante> stripedGrid;
    Button buttonNuevoEstudiante;
    EstudianteViewController sg;

    public EstudiantesView() {
        sg = new EstudianteViewController();
        HorizontalLayout layoutRow = new HorizontalLayout();
        buttonNuevoEstudiante = new Button();
        stripedGrid = new Grid<>(IEstudiante.class, false);
        stripedGrid.addColumn(IEstudiante::getId).setHeader("Id");
        stripedGrid.addColumn(IEstudiante::getNombre).setHeader("Nombre");
        stripedGrid.addColumn(IEstudiante::getApellido).setHeader("Apellido");
        stripedGrid.addColumn(IEstudiante::getEmail).setHeader("Email");
        stripedGrid.addColumn(IEstudiante::getTelefono).setHeader("Telefono");
        stripedGrid.addColumn(IEstudiante::getEdad).setHeader("Edad");
        stripedGrid.addColumn(IEstudiante::getFechaNacimientoString).setHeader("Fecha de Nacimiento");
        stripedGrid.addColumn(IEstudiante::getTipoEstudiante).setHeader("Tipo de Estudiante");
        stripedGrid.addColumn(
                new ComponentRenderer<>(estudiante -> {
                    Button botonBorrar = new Button();
                    botonBorrar.addThemeVariants(ButtonVariant.LUMO_ERROR);
                    botonBorrar.addClickListener(e -> {
                        onClickBorrarEstudiante(estudiante);
                    });
                    botonBorrar.setIcon(new Icon(VaadinIcon.TRASH));

                    Button botonEditar = new Button();
                    botonEditar.addThemeVariants(ButtonVariant.LUMO_WARNING);
                    botonEditar.addClickListener(e ->
                            getUI().ifPresent(ui -> ui.navigate("new-student/"+
                                    "edit/"+estudiante.getId()))
                    );
                    botonEditar.setIcon(new Icon(VaadinIcon.EDIT));

                    Button botonVer = new Button();
                    botonVer.addThemeVariants(ButtonVariant.LUMO_SUCCESS);
                    botonVer.addClickListener(e ->
                            getUI().ifPresent(ui -> ui.navigate("new-student/"+
                                    "view/"+estudiante.getId()))
                    );
                    botonVer.setIcon(new Icon(VaadinIcon.EYE));

                    HorizontalLayout buttons = new HorizontalLayout(botonBorrar,botonEditar,botonVer);
                    return buttons;
                })).setHeader("Manage").setAutoWidth(true);

        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");
        layoutRow.setWidthFull();
        getContent().setFlexGrow(1.0, layoutRow);
        layoutRow.addClassName(Gap.MEDIUM);
        layoutRow.setWidth("100%");
        layoutRow.getStyle().set("flex-grow", "1");
        buttonNuevoEstudiante.setText("Nuevo Estudiante");
        buttonNuevoEstudiante.setWidth("min-content");
        buttonNuevoEstudiante.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buttonNuevoEstudiante.addClickListener(e-> onClickNuevoEstudiante());
        stripedGrid.addThemeVariants(GridVariant.LUMO_ROW_STRIPES);
        stripedGrid.setWidth("100%");
        stripedGrid.getStyle().set("flex-grow", "0");
        stripedGrid.setItems(sg.getEstudiantes());
        getContent().add(layoutRow);
        layoutRow.add(buttonNuevoEstudiante);
        getContent().add(stripedGrid);
    }


    public void onClickBorrarEstudiante(IEstudiante estudiante){
        sg.eliminarEstudiante(estudiante);
        stripedGrid.getDataProvider().refreshAll();
    }

    public void onClickNuevoEstudiante(){
        buttonNuevoEstudiante.getUI().ifPresent(ui -> ui.navigate("new-student"));
        System.out.println("Nuevo Estudiante Clicked");
    }

}
