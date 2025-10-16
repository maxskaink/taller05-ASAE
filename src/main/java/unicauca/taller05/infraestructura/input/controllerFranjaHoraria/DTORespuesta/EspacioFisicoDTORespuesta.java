package unicauca.taller05.infraestructura.input.controllerFranjaHoraria.DTORespuesta;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EspacioFisicoDTORespuesta {
    private Integer id;
    private String nombre;
    private Integer capacidad;
    private Boolean estado;
    private List<FranjaHorariaDTORespuesta> franjasHorarias;
}