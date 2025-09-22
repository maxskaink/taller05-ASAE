package unicauca.taller05.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Curso {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length=255)
    private String nombre;

    // EAGER 
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "asignatura_id")
    private Asignatura asignatura;

    //EAGER
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "curso_docente",
        joinColumns = @JoinColumn(name = "curso_id"),
        inverseJoinColumns = @JoinColumn(name = "docente_id")
    )
    private List<Docente> docentes;

    //EAGER
    @OneToMany(mappedBy = "curso", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<FranjaHorario> franjas;

}
