package unicauca.taller05.dominio.modelos;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalTime;

@AllArgsConstructor
@Data
public class FranjaHoraria {
    private Integer id;

    private String dia;

    private LocalTime horaInicio;
    private LocalTime horaFin;

    private Curso curso;

    private EspacioFisico espacioFisico;
}
