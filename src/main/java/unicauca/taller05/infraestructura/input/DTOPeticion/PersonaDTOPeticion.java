package unicauca.taller05.infraestructura.input.DTOPeticion;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonaDTOPeticion {
    @NotEmpty(message = "El nombre no debe estar vacio")
    private String nombre;
    @NotEmpty(message = "El apellido no debe estar vacio")
    private String apellido;
    @NotEmpty(message = "El correo no debe estar vacio")
    private String correo;
}