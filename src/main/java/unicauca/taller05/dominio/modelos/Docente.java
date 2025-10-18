package unicauca.taller05.dominio.modelos;

import lombok.*;

import java.util.ArrayList;
import java.util.List;


@AllArgsConstructor
@Data
@NoArgsConstructor
@Setter
@Getter
public class Docente extends Persona{
    private Oficina oficina;

    private List<Curso> cursos = new ArrayList<>();
}
