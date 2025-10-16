package unicauca.taller05.infraestructura.input.controllerFranjaHoraria.DTOPeticion;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import unicauca.taller05.infraestructura.output.persistencia.entidades.FranjaHorariaEntity;

import java.util.List;

public class EspacioFisicoDTOPeticion {
    @NotEmpty(message = "El nombre no puede estar vacio")
    private String nombre;
    private List<@Min(1) Integer> idFranjasHoraria;
    @Min(1)
    private Integer capacidad;
    @NotEmpty(message = "El estado no puede estar vacio")
    private Boolean estado;
}
