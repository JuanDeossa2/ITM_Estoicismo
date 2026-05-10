package edu.itm.estoicismo.entitiesJPA;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "registro_retos")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistroReto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_registro")
    private Integer idRegistro;

    @Column(name = "fecha_cumplimiento", nullable = false)
    private LocalDate fechaCumplimiento;

    @Enumerated(EnumType.STRING)
    private EstadoReto estado = EstadoReto.Pendiente;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_reto")
    private RetoDiario reto;

    public enum EstadoReto {
        Pendiente, Completado, Fallido
    }
}