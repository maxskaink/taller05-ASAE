package unicauca.taller05.dominio.casosUso;

import lombok.AllArgsConstructor;
import unicauca.taller05.aplicacion.in.CUFranjaHorariaIn;
import unicauca.taller05.aplicacion.out.CursoRepositoryOut;
import unicauca.taller05.aplicacion.out.EspacioRepositoryOut;
import unicauca.taller05.aplicacion.out.FranjaHorariaFormaterOut;
import unicauca.taller05.aplicacion.out.FranjaHorariaRepositoryOut;
import unicauca.taller05.dominio.manejadores.validaciones.ValidarDiaYHorarioValido;
import unicauca.taller05.dominio.manejadores.validaciones.ValidarDocenteLibre;
import unicauca.taller05.dominio.manejadores.validaciones.ValidarEspacioFisicoExiste;
import unicauca.taller05.dominio.manejadores.validaciones.ValidarEspacioLibre;
import unicauca.taller05.dominio.manejadores.validaciones.ValidarExistenciaCurso;
import unicauca.taller05.dominio.modelos.FranjaHoraria;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;

@AllArgsConstructor
public class FranjaHorariaService implements CUFranjaHorariaIn {

    private final FranjaHorariaFormaterOut franjaFormater;
    private final FranjaHorariaRepositoryOut franjaRepository;
    private final CursoRepositoryOut cursoRepository; 
    private final EspacioRepositoryOut espacioRepository;
    private final ModelMapper modelMapper;

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
            franjaFormater.retornarErrorEntidadNoExiste("No se han encontrado franjas horarias para el curso co1n id: " + id);
        }

        return franjas;
    }

    @Override
    public FranjaHoraria crearFranjaHoraria(FranjaHoraria franjaHorariaACrear) {
        if (franjaHorariaACrear == null) {
            franjaFormater.retornarErrorParametroInvalido("La franja horaria no puede ser nula");
            return null;
        }
        System.out.println("DebugSERVICIO: FranjaHoraria completa: " + franjaHorariaACrear);
        var validarDiaYHorario = new ValidarDiaYHorarioValido(franjaFormater);
        var validarEspacioExiste = new ValidarEspacioFisicoExiste(franjaFormater, espacioRepository);
        var validarCursoExiste = new ValidarExistenciaCurso(franjaFormater, cursoRepository);
        var validarDocenteLibre = new ValidarDocenteLibre(franjaRepository, franjaFormater);
        var validarEspacioLibre = new ValidarEspacioLibre(franjaRepository, franjaFormater);

        validarDiaYHorario
            .setSiguiente(validarDocenteLibre)
            .setSiguiente(validarDiaYHorario)
            .setSiguiente(validarEspacioLibre)
            .setSiguiente(validarEspacioExiste)
            .setSiguiente(validarCursoExiste);
            


        Optional<FranjaHoraria> resultado = validarDiaYHorario.manejar(franjaHorariaACrear);

        if (resultado.isPresent()) {
            return franjaRepository.crearFranjaHoraria(resultado.get());
        }

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
    public FranjaHoraria eliminarFranjaHorariaPorId(Integer cursoId) {
        if(franjaRepository.obtenerFranjasHorariasPorCurso(cursoId).isEmpty()){
            franjaFormater.retornarErrorEntidadNoExiste("No se ha encontrado una franja horaria con el id: " + cursoId);
            return null;
        }
        if(cursoId == null){
            franjaFormater.retornarErrorParametroInvalido("El id del curso no puede ser nulo");
            return null;
        }
        return franjaRepository.eliminarFranjaHorariaPorId(cursoId);
    }
}
