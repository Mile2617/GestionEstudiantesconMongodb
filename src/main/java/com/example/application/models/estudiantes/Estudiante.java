package com.example.application.models.estudiantes;

import com.example.application.interfaces.IEstudiante;
import com.example.application.models.asignaturas.Asignatura;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public abstract class Estudiante implements IEstudiante {

    private String id;
    private String nombre;
    private String apellido;
    private String email;
    private String telefono;
    private int edad;
    private Date fechaNacimiento;
    private List<Asignatura> asignaturas;

    public Estudiante(String id, String nombre, String apellido, String email, String telefono, int edad, Date fechaNacimiento) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.telefono = telefono;
        this.edad = edad;
        this.fechaNacimiento = fechaNacimiento;
    }

    public Estudiante() {
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public String getFechaNacimientoString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(fechaNacimiento);
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public List<Asignatura> getAsignaturas() {
        return asignaturas;
    }

    public void addAsignatura(Asignatura asignatura) {
        this.asignaturas.add(asignatura);
    }

    public void removeAsignaturas(Asignatura asignatura) {
        this.asignaturas.remove(asignatura);
    }

    public abstract String getTipoEstudiante();

}
