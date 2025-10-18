package unicauca.taller05.infraestructura.input.DTOPeticion;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OficinaDTOPeticion {
    @NotEmpty(message = "{asignatura.nombre.vacio}")
    private String nombre;
    @NotEmpty(message = "{asignatura.ubicacion.vacio}")
    private String ubicacion;
    private List<@PositiveOrZero(message = "{asignatura.doecentes.asigandos}") Integer> docentes;
}
