package unicauca.taller05.infraestructura.input.controllerFranjaHoraria.DTOPeticion;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
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
    @NotEmpty(message = "El nombre no puede estar vacio")
    private String nombre;
    @NotEmpty(message = "La ubicaicion no puede estar vacia")
    private String ubicacion;
    @NotEmpty(message = "No puede estar sin docentes una oficina")
    private List<@Min(1) Integer> docentes;
}
