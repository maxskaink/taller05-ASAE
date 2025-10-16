package unicauca.taller05.aplicacion.in;

import unicauca.taller05.dominio.modelos.Curso;
import unicauca.taller05.dominio.modelos.FranjaHoraria;

import java.util.List;

public interface CUCursosIn {

    /**
     * Obtiene un curso por el nombre de la asignatura
     * Punto 1.2 del taller
     * @param nombreAsignatura nombre de la asignatura a buscar
     */
    List<Curso> obtenerCursoPorAsignatura(String nombreAsignatura);

}
