package unicauca.taller05.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

// Oficina.java
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Oficina {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique=true, length=50)
    private String nombre;

    @Column(length=50)
    private String ubicacion;

    @OneToMany(mappedBy = "oficina")
    private List<Docente> docentes;

}
