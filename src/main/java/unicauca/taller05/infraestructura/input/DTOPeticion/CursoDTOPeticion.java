package unicauca.taller05.infraestructura.input.DTOPeticion;


import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CursoDTOPeticion {

    @NotBlank(message = "{curso.nombre}")
    @Size(max = 255, message = "{curso.nombre.largo}")
    private String nombre;
    @NotNull(message = "{curso.asignatura}")
    private Integer asignaturaId;
    @NotEmpty(message = "{curso.docentes}")
    private List<@Min(1)Integer> docentesIds;
    @NotEmpty(message = "{curso.franjas}")
    private List<@Min(1)Integer> franjas;

}