package com.example.application.controllers;

import java.util.Date;

public interface IEstudiante {

    public String getId();

    public void setId(String id);

    public String getNombre();

    public void setNombre(String nombre);

    public String getApellido();

    public void setApellido(String apellido);

    public String getEmail();

    public void setEmail(String email);

    public String getTelefono() ;

    public void setTelefono(String telefono);

    public int getEdad();

    public void setEdad(int edad);

    public Date getFechaNacimiento();

    public String getTipoEstudiante();
    public String getFechaNacimientoString();
    public void setFechaNacimiento(Date fechaNacimiento);
}
