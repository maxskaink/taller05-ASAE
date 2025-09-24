package unicauca.taller05.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class EspacioFisico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique=true, length=255)
    private String nombre;

    @OneToMany(mappedBy = "espacioFisico", fetch = FetchType.LAZY)
    private List<FranjaHorario> franjaHorarios;

    private Integer capacidad;

    private Boolean estado;
}
