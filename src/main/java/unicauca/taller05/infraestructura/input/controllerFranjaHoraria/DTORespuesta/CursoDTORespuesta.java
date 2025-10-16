package unicauca.taller05.infraestructura.input.controllerFranjaHoraria.DTORespuesta;

import lombok.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CursoDTORespuesta {
    private Integer id;
    private String nombre;
    private AsignaturaDTORespuesta asignatura;
    private List<DocenteDTORespuesta> docentes;
    private List<FranjaHorariaDTORespuesta> franjas;
}