package edu.itm.ejemplo.entitiesSQL;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProgresoLecciones {

    private int idProgreso;
    private int idUsuario;
    private int idLeccion;
    private Timestamp completadaEn;

}