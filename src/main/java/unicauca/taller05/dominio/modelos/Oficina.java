package unicauca.taller05.dominio.modelos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class Oficina {
    private Integer id;

    private String nombre;

    private String ubicacion;

    private List<Docente> docentes;
}
