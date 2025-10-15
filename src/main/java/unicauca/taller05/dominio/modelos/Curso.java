package unicauca.taller05.dominio.modelos;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Data
public class Curso {
    private Integer id;

    private String nombre;

    private Asignatura asignatura;

    private List<Docente> docentes = new ArrayList<>();

    private List<FranjaHoraria> franjas;
}
