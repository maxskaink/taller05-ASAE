package unicauca.taller05.infraestructura.input.controllerFranjaHoraria.DTOPeticion;

import jakarta.validation.constraints.NotEmpty;

public class AsignaturaDTOPeticion extends PersonaDTOPeticion {
    @NotEmpty(message = "El rol no puede estar vacio")
    private String rol;
}
