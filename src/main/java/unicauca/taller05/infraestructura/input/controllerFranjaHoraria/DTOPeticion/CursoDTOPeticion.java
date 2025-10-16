package unicauca.taller05.infraestructura.input.controllerFranjaHoraria.DTOPeticion;


import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CursoDTOPeticion {

    @NotEmpty(message = "El nombre no puede estar vacio")
    @Size(max = 255, message = "El nombre no puede superar los 255 caracteres")
    private String nombre;
    @NotNull(message = "Debe especificar la asignatura del curso")
    private Integer asignaturaId;
    @NotEmpty(message = "Debe asignar al menos un docente")
    private List<Integer> docentesIds;
    @NotEmpty(message = "Debe registrar al menos una franja horaria")
    private List<FranjaHorariaDTOPeticion> franjas;

}