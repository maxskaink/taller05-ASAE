package unicauca.taller05.infraestructura.input.controllerFranjaHoraria.DTORespuesta;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FranjaHorariaDTORespuesta {
    private Integer id;
    private String dia;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private EspacioFisicoDTORespuesta espacioFisico;
    private CursoDTORespuesta curso;
}