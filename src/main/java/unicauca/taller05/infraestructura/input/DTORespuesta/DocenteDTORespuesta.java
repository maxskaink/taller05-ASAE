package unicauca.taller05.infraestructura.input.DTORespuesta;
import lombok.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DocenteDTORespuesta extends PersonaDTORespueta{
    private OficinaDTORespuesta oficina;
    //private List<CursoDTORespuesta> cursos;
}