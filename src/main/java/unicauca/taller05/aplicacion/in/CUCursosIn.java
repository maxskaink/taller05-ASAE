package unicauca.taller05.aplicacion.in;

public interface CUCursosIn {

    /**
     * Obtiene un curso por el nombre de la asignatura
     * Punto 1.2 del taller
     * @param nombreAsignatura nombre de la asignatura a buscar
     */
    void obtenerCursoPorAsignatura(String nombreAsignatura);

    /**
     * Elimina una franja horaria por su id
     * Punto 3.3
     * @param idFranja id de la franja a eliminar
     */
    void eliminarFranjaHorariaPorId(Integer idFranja);
}
