package unicauca.taller05.aplicacion.in;

public interface CUFranjaHorariaIn {

    /**
     * Obtiene las franjas horarias de un curso por el id
     * Punto 1.3 del taller
     * @param id id del curso a consultar las franjas
     */
    void obtenerFranjasPorIdCurso(Integer id);

    /**
     * Obtiene las franjas horarias de un curso por el id, usando JPQL
     * Punto 3.1 del taller
     * @param id id del curso a consultar las franjas
     */
    void obtenerFranjaPorIdCursoJPQL(Integer id);

    /**
     * Crea una franja horaria relacionandola con un curso y un espacio fisico
     * Punto 6.1 del taller
     */
    void crearFranjaHoraria();//Deberia de ser recibir un DTO o un modelo del dominio?

    /**
     * Lista de las franjas horarias asignadas a un docente
     * Punto 6.3 del taller
     * @param idDocente id del docente
     */
    void franjaHorariaPorDocente(Integer idDocente);

    /**
     * Lista la franja horaria asignada en un curso
     * Punto 6.4 del taller
     * @param idCurso id del curso
     */
    void franjaHorariaPorCurso(Integer idCurso);
}
