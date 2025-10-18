package unicauca.taller05.dominio.modelos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class FranjaHoraria {
    private Integer id;

    private String dia;

    private LocalTime horaInicio;
    private LocalTime horaFin;

    private Curso curso;

    private EspacioFisico espacioFisico;
}
