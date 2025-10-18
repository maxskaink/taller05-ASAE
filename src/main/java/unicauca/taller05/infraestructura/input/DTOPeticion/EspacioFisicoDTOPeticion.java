package unicauca.taller05.infraestructura.input.DTOPeticion;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
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
    @NotEmpty(message = "{espacioFisico.nombre}")
    @Size(min = 5, max = 45, message = "{espacioFisico.nombre.largo}")
    private String nombre;
    @NotEmpty(message = "Debe asignar al menos una franja horaria")
    private List<Integer> idFranjasHoraria;
    @PositiveOrZero(message = "{espacioFisico.capacidad.valor}")
    private Integer capacidad;
    @NotEmpty(message = "{espacioFisico.estado}")
    private Boolean estado;
}
