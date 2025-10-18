package unicauca.taller05.infraestructura.output.persistencia.entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Asignatura")
public class AsignaturaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length=255)
    private String nombre;

    @Column(length=50)
    private String codigo;

    @OneToMany(mappedBy = "asignatura")
    private List<CursoEntity> cursos;
}
