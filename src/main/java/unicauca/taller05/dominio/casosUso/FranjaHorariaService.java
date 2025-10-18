package unicauca.taller05.dominio.casosUso;

import unicauca.taller05.aplicacion.in.CUFranjaHorariaIn;
import unicauca.taller05.dominio.modelos.FranjaHoraria;

import java.util.List;

public class FranjaHorariaService implements CUFranjaHorariaIn {
    @Override
    public List<FranjaHoraria> obtenerFranjasPorIdCurso(Integer id) {
        return List.of();
    }

    @Override
    public List<FranjaHoraria> obtenerFranjaPorIdCursoJPQL(Integer id) {
        return List.of();
    }

    @Override
    public FranjaHoraria crearFranjaHoraria(FranjaHoraria franjaHorariaACrear) {
        return null;
    }

    @Override
    public List<FranjaHoraria> franjaHorariaPorDocente(Integer idDocente) {
        return List.of();
    }

    @Override
    public List<FranjaHoraria> franjaHorariaPorCurso(Integer idCurso) {
        return List.of();
    }

    @Override
    public FranjaHoraria eliminarFranjaHorariaPorId(Integer idFranja) {
        return null;
    }
}
