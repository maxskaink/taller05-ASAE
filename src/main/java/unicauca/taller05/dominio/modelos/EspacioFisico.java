package unicauca.taller05.dominio.modelos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EspacioFisico {
    private Integer id;

    private String nombre;

    private List<FranjaHoraria> franjasHorarias;

    private Integer capacidad;

    private Boolean estado;
}
