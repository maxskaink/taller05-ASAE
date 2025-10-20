package unicauca.taller05.aplicacion.out;

import unicauca.taller05.dominio.modelos.Curso;

import java.util.List;

public interface CursoRepositoryOut {

    /**
     * Obtiene los cursos relacionados con el nombre de la asignatura
     * Punto 1.2 del taller
     * @param nombreAsignatura nombre de la asignatura a buscar
     * @return lista de cursos relacionados con la asignatura buscada
     */
    List<Curso> obtenerCursoPorNombreAsignatura(String nombreAsignatura);

    /**
     * Verifica si un curso existe por su ID
     * @param id ID del curso a verificar
     * @return true si el curso existe, false en caso contrario
     */
    boolean existsById(Integer id);

}
