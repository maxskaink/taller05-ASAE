package unicauca.taller05.dominio.modelos;


import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;

@AllArgsConstructor
@Data
public class Oficina {
    private Integer id;

    private String nombre;

    private String ubicacion;

    private List<Docente> docentes;
}
