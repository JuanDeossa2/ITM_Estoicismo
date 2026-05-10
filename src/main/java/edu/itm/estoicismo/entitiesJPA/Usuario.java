package edu.itm.estoicismo.entitiesJPA;

import jakarta.persistence.*;
import lombok.*;
import java.util.Date;

@Entity
@Table(name = "usuarios")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Integer idUsuario;

    private String nombre_completo;
    private String email;
    private String password_hash;
    private Integer puntos_totales;

    @Column(name = "fecha_registro")
    private Date fechaRegistro;
}