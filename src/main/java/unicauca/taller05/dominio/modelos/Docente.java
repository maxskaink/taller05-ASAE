package unicauca.taller05.dominio.modelos;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Data
public class Docente {
    private Oficina oficina;

    private List<Curso> cursos = new ArrayList<>();
}
