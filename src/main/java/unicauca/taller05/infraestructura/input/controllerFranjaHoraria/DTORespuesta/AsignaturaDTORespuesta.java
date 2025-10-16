package unicauca.taller05.infraestructura.input.controllerFranjaHoraria.DTORespuesta;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AsignaturaDTORespuesta {
    private Integer id;
    private String nombre;
    private String codigo;
    private List<CursoDTORespuesta> cursos;
}
