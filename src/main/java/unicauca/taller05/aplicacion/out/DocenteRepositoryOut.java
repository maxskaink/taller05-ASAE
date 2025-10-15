package unicauca.taller05.aplicacion.out;

import unicauca.taller05.dominio.modelos.Curso;

import java.util.List;

public interface DocenteRepositoryOut {

    /**
     * Tra de base de datos los cursos que coinsiden con el nombre de la asignatura
     * @param nombreAsignatura nombre de la asignatura a buscar
     * @return retorna los cursos encontrados.
     */
    List<Curso> obtenerCursos(String nombreAsignatura);
}
