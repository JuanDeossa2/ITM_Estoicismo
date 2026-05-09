package edu.itm.estoicismo.entitiesSQL;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Usuarios {
    private int idUsuario;
    private String nombreCompleto;
    private String email;
    private String passwordHash;
    private int  puntosTotales;
    private Date fechaRegistro;
}
