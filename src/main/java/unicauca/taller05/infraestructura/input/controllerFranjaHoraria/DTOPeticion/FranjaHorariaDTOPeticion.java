package unicauca.taller05.infraestructura.input.controllerFranjaHoraria.DTOPeticion;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FranjaHorariaDTOPeticion {
    @NotBlank(message = "El día no puede estar vacío")
    @Pattern(
            regexp = "^(Lunes|Martes|Miercoles|Jueves|Viernes|Sabado|Domingo)$",
            message = "El día debe ser un valor válido (Lunes, Martes, Miercoles, Jueves, Viernes, Sabado o Domingo)"
    )
    private String dia;
    @NotNull(message = "La hora de inicio es obligatoria")
    private LocalTime horaInicio;
    @NotNull(message = "La hora de fin es obligatoria")
    private LocalTime horaFin;
    @Min(1)
    @NotNull(message = "El id del curso es obligatorio")
    private int idCurso;
    @Min(1)
    @NotNull(message = "El id del curso es obligatorio")
    private int idEspacioFisico;

}