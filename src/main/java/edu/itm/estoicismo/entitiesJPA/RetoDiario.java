package edu.itm.estoicismo.entitiesJPA;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "retos_diarios")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RetoDiario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reto")
    private Integer idReto;

    @Column(name = "descripcion_reto", nullable = false, columnDefinition = "TEXT")
    private String descripcionReto;

    @Column(name = "puntos_recompensa")
    private Integer puntosRecompensa = 10;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_leccion")
    private Leccion leccion;
}