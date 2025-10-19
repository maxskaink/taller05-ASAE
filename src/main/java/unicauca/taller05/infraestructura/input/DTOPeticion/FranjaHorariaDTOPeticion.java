package unicauca.taller05.infraestructura.input.DTOPeticion;

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
    @NotBlank(message = "{franjaHoraria.dia.vacio}")
    @Pattern(
            regexp = "^(Lunes|Martes|Miercoles|Jueves|Viernes|Sabado)$",
            message = "{franjaHoraria.dia.invalido}"
    )

    private String dia;
    @NotNull(message = "{franjaHoraria.horaInicio.vacio}")
    private LocalTime horaInicio;
    @NotNull(message = "{franjaHoraria.horaFin.vacio}")
    private LocalTime horaFin;
    @PositiveOrZero(message = "{franjaHoraria.idCurso.positivo}")
    @NotNull(message = "{franjaHoraria.idCurso.vacio}")
    private int idCurso;
    @Min(1)
    @NotNull(message = "{franjaHoraria.idEspacioFisico.vacio}")
    private int idEspacioFisico;

}