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
public class AsignaturaDTOPeticion  {
    @NotEmpty(message = "El nombre no puede estar vacio")
    private String nombre;
    @Min(1)
    private String codigo;
    @NotEmpty(message = "La asignatura no puede estar sin cursos")
    private List<@Min(1) Integer> cursos;
}
