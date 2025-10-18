package unicauca.taller05.infraestructura.input.DTOPeticion;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DocenteDTOPeticion extends PersonaDTOPeticion {
    private OficinaDTOPeticion oficina;
}