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
public class AdministrativoDTOPeticion extends PersonaDTOPeticion {
    @NotEmpty(message = "{administrativo.rol}")
    private String rol;
}
