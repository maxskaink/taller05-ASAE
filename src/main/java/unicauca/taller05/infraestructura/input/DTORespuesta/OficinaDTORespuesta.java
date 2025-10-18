package unicauca.taller05.infraestructura.input.DTORespuesta;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OficinaDTORespuesta {
    private Integer id;
    private String nombre;
    private String ubicacion;
    private List<DocenteDTORespuesta> docentes;
}