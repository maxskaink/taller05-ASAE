package unicauca.taller05.infraestructura.input.DTOPeticion;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DocenteDTOPeticion extends PersonaDTOPeticion {
    @NotEmpty(message = "{docente.oficina}")
    @PositiveOrZero(message = "{docente.oficina.valor}")
    private Integer oficina;

    private List<Integer> cursos = new ArrayList<>();
}