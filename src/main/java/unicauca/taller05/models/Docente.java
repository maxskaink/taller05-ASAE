package unicauca.taller05.models;

import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Docente extends Persona {

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="oficina_id")
    private Oficina oficina;

    @ManyToMany(mappedBy = "docentes", fetch = FetchType.EAGER)
    private List<Curso> cursos;
}
