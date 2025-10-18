package unicauca.taller05.infraestructura.input.DTOPeticion;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import unicauca.taller05.infraestructura.output.persistencia.entidades.CursoEntity;
import unicauca.taller05.infraestructura.output.persistencia.entidades.OficinaEntity;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DocenteDTOPeticion extends PersonaDTOPeticion {
    @NotEmpty(message = "La oficina no debe estar vacia")
    private OficinaEntity oficina;
    private List<CursoEntity> cursos = new ArrayList<>();
}