package unicauca.taller05.infraestructura.output.persistencia.entidades;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DocenteEntity extends PersonaEntity {

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "oficina_id")
    private OficinaEntity oficina;

    @ManyToMany(mappedBy = "docentes", fetch = FetchType.EAGER)
    private List<CursoEntity> cursos = new ArrayList<>();
}
