package unicauca.taller05.infraestructura.input.DTOPeticion;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonaDTOPeticion {
    @NotEmpty(message = "{persona.nombre}")
    @Size(min = 5, max = 45, message = "{persona.nombre.largo}")
    private String nombre;
    @NotEmpty(message = "{persona.apellido.vacio}")
    private String apellido;
    @NotEmpty(message = "{persona.correo.vacio}")
    private String correo;
}