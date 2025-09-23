package unicauca.taller05.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class FranjaHorario {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length=20)
    private String dia;

    private LocalTime horaInicio;
    private LocalTime horaFin;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "curso_id")
    private Curso curso;

    @ManyToOne(fetch = FetchType.LAZY) //Consultar franja horaria horaria por docente con lazy
    //@ManyToOne(fetch = FetchType.EAGER) //Consultar franja horaria curso con eager
    @JoinColumn(name = "espacio_fisico_id")
    private EspacioFisico espacioFisico;

}
