package unicauca.taller05.dominio.modelos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class Persona {
    private Integer id;

    private String nombre;

    private String apellido;

    private String correo;
}
