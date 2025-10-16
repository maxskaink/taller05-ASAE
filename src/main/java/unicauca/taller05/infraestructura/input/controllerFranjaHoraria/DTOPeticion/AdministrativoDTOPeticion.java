package unicauca.taller05.infraestructura.input.controllerFranjaHoraria.DTOPeticion;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;


import java.util.List;

public class AdministrativoDTOPeticion {
    @NotBlank(message = "El nombre del administrativo es obligatorio")
    @Size(min = 3, max = 100, message = "El nombre debe tener entre 3 y 100 caracteres")
    private String nombre;
    @NotBlank(message = "El código del administrativo es obligatorio")
    private String codigo;

    private List<@Min(1) Integer> cursos;
}
