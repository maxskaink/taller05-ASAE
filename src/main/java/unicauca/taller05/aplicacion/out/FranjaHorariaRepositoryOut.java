package unicauca.taller05.aplicacion.out;

import unicauca.taller05.dominio.modelos.FranjaHoraria;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;

public interface FranjaHorariaRepositoryOut {

    /**
     * Crea una franja horaria en la base de datos
     * Punto 6.1 del taller.
     * @param franjaHoraria franja horaria a crear
     * @return franja horaria creada
     */
    FranjaHoraria crearFranjaHoraria(FranjaHoraria franjaHoraria);

    /**
     * obtiene todas las franjas ocupadas por
     * un curso con un id específico
     * Punto 1.3 del taller (Tambien puede cubrir el 6.4).
     * @param idCurso id del curso a buscar las franjas
     * @return franjas horarias del curso
     */
    List<FranjaHoraria> obtenerFranjasHorariasPorCurso(Integer idCurso);

    /**
     * obtiene todas las franjas ocupadas por
     * un docente con un id específico
     * Punto 6.3
     * @param idDocente id del docente a buscar las franjas
     * @return franjas horarias del docente
     */
    List<FranjaHoraria> obtenerFranjasHorariasPorDocente(Integer idDocente);

    /**
     * Se realiza un join entre curso, franja Horaria y espacio fisico.
     * Se debe usar JPQL para realizar la consulta.
     * Punto 3.1 del taller.
     * @param idCurso id del curso a buscar las franjas horarias
     * @return datos de las franjas, espacio fisico y curso relacionados al curso.
     */
    List<FranjaHoraria> obtenerFranjasHorariasPorCursoJPQL(Integer idCurso);

    /**
     * Utiliza un JPQL para obtener las franjas horarias que ocupan un espacio fisico
     * Se debe realizar un join entre franjaHoraria y espacio fisico.
     * Punto 2.1 del taller.
     * @param dia dia de la semana de la franja horaria
     * @param horaInicio hora inicio a consultar
     * @param horaFin hora fin a consultar
     * @param idEspacioFisico id espacio fisico a consultar
     * @return lista de franjas horarias que ocupan el espacio fisico
     */
    List<FranjaHoraria> obtenerFranjasOcupadasPorEspacio(DayOfWeek dia, LocalTime horaInicio, LocalTime horaFin, Integer idEspacioFisico);

    /**
     * Utiliza SQL nativo para obtener las franjas horarias que ocupan un docente
     * Se deber realizar un join entre franja horaria y docente.
     * Punto 2.2 del taller (Tambien cubre el 6.3).
     * @param dia dia de la semana de la franja horaria
     * @param horaInicio hora inicio a consultar
     * @param horaFin hora fin a consultar
     * @param idDocente id del docente a consultar
     * @return lista de franjas horarias que ocupan el docente
     */
    List<FranjaHoraria> obtenerFranjasOcupadasPorDocente(DayOfWeek dia, LocalTime horaInicio, LocalTime horaFin, Integer idDocente);

    /**
     * Elimina una franja horaria por su id
     * Usando JPQL
     * Punto 3.3
     * @param idFranja id de la franja a eliminar
     */
    FranjaHoraria eliminarFranjaHorariaPorId(Integer idFranja);
}
