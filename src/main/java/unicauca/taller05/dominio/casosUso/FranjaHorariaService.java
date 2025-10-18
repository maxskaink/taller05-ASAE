package unicauca.taller05.dominio.casosUso;

import lombok.AllArgsConstructor;
import unicauca.taller05.aplicacion.in.CUFranjaHorariaIn;
import unicauca.taller05.aplicacion.out.FranjaHorariaFormaterOut;
import unicauca.taller05.aplicacion.out.FranjaHorariaRepositoryOut;
import unicauca.taller05.dominio.modelos.FranjaHoraria;

import java.util.List;

@AllArgsConstructor
public class FranjaHorariaService implements CUFranjaHorariaIn {

    private final FranjaHorariaFormaterOut  franjaFormater;
    private final FranjaHorariaRepositoryOut franjaRepository;

    @Override
    public List<FranjaHoraria> obtenerFranjasPorIdCurso(Integer id) {
        return franjaHorariaPorCurso(id); //Creo que cremaos una metodo duplicado
    }

    @Override
    public List<FranjaHoraria> obtenerFranjaPorIdCursoJPQL(Integer id) {

        if(id == null){
            franjaFormater.retornarErrorParametroInvalido("El id del curso no puede ser nulo");
        }

        var franjas = franjaRepository.obtenerFranjasHorariasPorCursoJPQL(id);

        if(franjas.isEmpty()){
            franjaFormater.retornarErrorEntidadNoExiste("No se han encontrado franjas horarias para el curso con id: " + id);
        }

        return franjas;
    }

    @Override
    public FranjaHoraria crearFranjaHoraria(FranjaHoraria franjaHorariaACrear) {
        return null;
    }

    @Override
    public List<FranjaHoraria> franjaHorariaPorDocente(Integer idDocente) {
        if(idDocente == null){
            franjaFormater.retornarErrorParametroInvalido("El id del docente no puede ser nulo");
            return List.of();
        }

        var franjas = franjaRepository.obtenerFranjasHorariasPorDocente(idDocente);

        if(franjas.isEmpty())
            franjaFormater.retornarErrorEntidadNoExiste("No se han encontrado franjas horarias para el docente con id: " + idDocente);

        return franjas;
    }

    @Override
    public List<FranjaHoraria> franjaHorariaPorCurso(Integer idCurso) {

        if(idCurso == null){
            franjaFormater.retornarErrorParametroInvalido("El id del curso no puede ser nulo");
            return List.of();
        }

        var franjas = franjaRepository.obtenerFranjasHorariasPorCurso(idCurso);

        if(franjas.isEmpty())
            franjaFormater.retornarErrorEntidadNoExiste("No se han encontrado franjas horarias para el curso con id: " + idCurso);

        return franjas;
    }

    @Override
    public FranjaHoraria eliminarFranjaHorariaPorId(Integer idFranja) {
        return null;
    }
}
