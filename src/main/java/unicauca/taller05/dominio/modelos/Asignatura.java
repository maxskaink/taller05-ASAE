package unicauca.taller05.dominio.modelos;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import unicauca.taller05.infraestructura.bd.models.Curso;

import java.util.List;

@AllArgsConstructor
@Data
public class Asignatura {
    private Integer id;

    private String nombre;

    private String codigo;

    private List<Curso> cursos;
    
}
