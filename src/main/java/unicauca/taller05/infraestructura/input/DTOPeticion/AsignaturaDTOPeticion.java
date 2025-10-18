package unicauca.taller05.infraestructura.input.DTOPeticion;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
public class AsignaturaDTOPeticion  {
    @NotEmpty(message = "El nombre no puede estar vacio")
    private String nombre;
    @PositiveOrZero(message = "{{asignatura.codigoPositivo}}")
    @NotNull(message = "{{asignatura.codigoVacio}}")
    private String codigo;
    @NotEmpty(message = "{{asignatura.curso}}")
    private List<@Min(1) Integer> cursos;
}
