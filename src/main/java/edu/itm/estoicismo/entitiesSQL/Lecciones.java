package edu.itm.estoicismo.entitiesSQL;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Lecciones {

    private int idLeccion;
    private int idRuta;
    private String titulo;
    private String contenidoTeorico;
    private int ordenLeccion;

}