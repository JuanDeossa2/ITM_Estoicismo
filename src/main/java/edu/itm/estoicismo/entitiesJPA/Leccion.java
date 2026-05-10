package edu.itm.estoicismo.entitiesJPA;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "lecciones")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Leccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_leccion")
    private Integer idLeccion;

    private String titulo;

    @Column(columnDefinition = "TEXT")
    private String contenido_teorico;

    private Integer orden_leccion;

    @Column(name = "id_ruta")
    private Integer idRuta;
}