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
public class EspacioFisicoDTOPeticion {
    @NotEmpty(message = "El nombre no puede estar vacio")
    private String nombre;
    private List<@Min(1) Integer> idFranjasHoraria;
    @Min(1)
    private Integer capacidad;
    @NotEmpty(message = "El estado no puede estar vacio")
    private Boolean estado;
}
