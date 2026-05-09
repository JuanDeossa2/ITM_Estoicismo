package edu.itm.estoicismo.entitiesSQL;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RutaAprendizaje {
    private int idRuta;
    private String nombreRuta;
    private String descripcion;
    private String nivelDificultad;
}