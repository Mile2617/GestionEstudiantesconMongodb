package com.example.application.utils;

import com.example.application.interfaces.IAsignatura;
import com.example.application.interfaces.IEstudiante;
import com.example.application.models.asignaturas.Materia;
import com.example.application.models.asignaturas.Modulo;
import com.example.application.models.estudiantes.Postgrado;
import com.example.application.models.estudiantes.Pregrado;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Util {

    public static List<IAsignatura> asignaturas  = new ArrayList<>(List.of(
            new Modulo("Matemáticas Básicas", "MOD101", 3),
            new Modulo("Física General", "MOD102", 4),
            new Modulo("Química Fundamental", "MOD103", 3),
            new Modulo("Programación I", "MOD104", 5),
            new Modulo("Historia Universal", "MOD105", 2),
            new Materia("Álgebra", "MAT201", 3),
            new Materia("Física II", "MAT202", 4),
            new Materia("Química Orgánica", "MAT203", 3),
            new Materia("Programación Avanzada", "MAT204", 5),
            new Materia("Literatura Española", "MAT205", 3)
    ));

    public static List<IEstudiante> estudiantes  = new ArrayList<>(List.of(
            new Pregrado("1","Juan","Pérez","juan@mail.com","123456789",32,new Date("20/02/1993"),"Ingeniería de Sistemas"),
            new Pregrado("2","Ana","López","ana@mail.com","234567891",28,new Date("15/05/1995"),"Ingeniería Industrial"),
            new Pregrado("3","Carlos","Martínez","carlos@mail.com","345678912",25,new Date("10/10/1998"),"Administración"),
            new Pregrado("4","María","García","maria@mail.com","456789123",27,new Date("22/08/1996"),"Contaduría"),
            new Pregrado("5","Pedro","Ramírez","pedro@mail.com","567891234",29,new Date("30/12/1994"),"Derecho"),
            new Postgrado("6","Luis","Gomez","luis@mail.com","987456321",31,new Date("20/02/1994"),"Ingeniería de Software"),
            new Postgrado("7","Lucía","Fernández","lucia@mail.com","876543219",33,new Date("12/03/1991"),"Finanzas"),
            new Postgrado("8","Miguel","Santos","miguel@mail.com","765432198",35,new Date("05/07/1989"),"Educación"),
            new Postgrado("9","Sofía","Ruiz","sofia@mail.com","654321987",30,new Date("18/11/1993"),"Psicología"),
            new Postgrado("10","Javier","Morales","javier@mail.com","543219876",32,new Date("25/09/1991"),"Derecho Internacional")
    ));

}