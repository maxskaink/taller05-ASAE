package unicauca.taller05.infraestructura.input.controllerFranjaHoraria.DTOPeticion;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonaDTOPeticion {
    @NotEmpty(message = "El nombre no debe estar vacio")
    private String nombre;
    @NotEmpty(message = "El apellido no debe estar vacio")
    private String apellido;
    @NotEmpty(message = "El correo no debe estar vacio")
    private String correo;
}