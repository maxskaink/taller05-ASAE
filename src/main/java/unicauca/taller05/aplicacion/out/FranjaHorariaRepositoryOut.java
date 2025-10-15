package unicauca.taller05.aplicacion.out;

import unicauca.taller05.dominio.modelos.FranjaHoraria;

import java.util.List;

public interface FranjaHorariaRepositoryOut {

    /**
     * obtiene todas las franjas ocupadas por
     * un curso con un id específico
     * @param idCurso id del curso a buscar las franjas
     * @return franjas horarias del curso
     */
    List<FranjaHoraria> obtenerFranjasHorariasPorCurso(Integer idCurso);

}
