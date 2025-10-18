package unicauca.taller05.infraestructura.output.persistencia.entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "FranjaHorario")
public class FranjaHorariaEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length=20)
    private String dia;

    private LocalTime horaInicio;
    private LocalTime horaFin;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "curso_id")
    private CursoEntity curso;

    @ManyToOne(fetch = FetchType.LAZY) //Consultar franja horaria curso con lazy
    //@ManyToOne(fetch = FetchType.EAGER) //Consultar franja horaria horaria por docente con eager
    @JoinColumn(name = "espacio_fisico_id")
    private EspacioFisicoEntity espacioFisico;

}
