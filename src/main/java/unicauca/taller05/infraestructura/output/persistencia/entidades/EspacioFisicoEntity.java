package unicauca.taller05.infraestructura.output.persistencia.entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "EspacioFisico")
public class EspacioFisicoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique=true, length=255)
    private String nombre;

    @OneToMany(mappedBy = "espacioFisico", fetch = FetchType.LAZY)
    private List<FranjaHorariaEntity> franjaHorarios;

    private Integer capacidad;

    private Boolean estado;
}
