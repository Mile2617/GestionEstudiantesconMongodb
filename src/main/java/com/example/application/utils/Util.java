package com.example.application.utils;

import com.example.application.interfaces.IEstudiante;
import com.example.application.models.estudiantes.Postgrado;
import com.example.application.models.estudiantes.Pregrado;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Util {

    // Removed static List<IAsignatura> asignaturas

    public static List<IEstudiante> estudiantes = new ArrayList<>();

    static {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            estudiantes.add(new Pregrado("1","Juan","Pérez","juan@mail.com","123456789",32,sdf.parse("20/02/1993"),"Ingeniería de Sistemas"));
            estudiantes.add(new Pregrado("2","Ana","López","ana@mail.com","234567891",28,sdf.parse("15/05/1995"),"Ingeniería Industrial"));
            estudiantes.add(new Pregrado("3","Carlos","Martínez","carlos@mail.com","345678912",25,sdf.parse("10/10/1998"),"Administración"));
            estudiantes.add(new Pregrado("4","María","García","maria@mail.com","456789123",27,sdf.parse("22/08/1996"),"Contaduría"));
            estudiantes.add(new Pregrado("5","Pedro","Ramírez","pedro@mail.com","567891234",29,sdf.parse("30/12/1994"),"Derecho"));
            estudiantes.add(new Postgrado("6","Luis","Gomez","luis@mail.com","987456321",31,sdf.parse("20/02/1994"),"Ingeniería de Software"));
            estudiantes.add(new Postgrado("7","Lucía","Fernández","lucia@mail.com","876543219",33,sdf.parse("12/03/1991"),"Finanzas"));
            estudiantes.add(new Postgrado("8","Miguel","Santos","miguel@mail.com","765432198",35,sdf.parse("05/07/1989"),"Educación"));
            estudiantes.add(new Postgrado("9","Sofía","Ruiz","sofia@mail.com","654321987",30,sdf.parse("18/11/1993"),"Psicología"));
            estudiantes.add(new Postgrado("10","Javier","Morales","javier@mail.com","543219876",32,sdf.parse("25/09/1991"),"Derecho Internacional"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}