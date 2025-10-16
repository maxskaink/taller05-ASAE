package unicauca.taller05.infraestructura.input.controllerFranjaHoraria.DTOPeticion;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import unicauca.taller05.infraestructura.output.persistencia.entidades.DocenteEntity;

import java.util.List;

public class OficinaDTOPeticion {
    @NotEmpty(message = "El nombre no puede estar vacio")
    private String nombre;
    @NotEmpty(message = "La ubicaicion no puede estar vacia")
    private String ubicacion;
    @NotEmpty(message = "No puede estar sin docentes una oficina")
    private List<@Min(1) Integer> docentes;
}
