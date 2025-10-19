package unicauca.taller05.dominio.casosUso;

import lombok.AllArgsConstructor;
import unicauca.taller05.aplicacion.in.CUFranjaHorariaIn;
import unicauca.taller05.aplicacion.out.FranjaHorariaFormaterOut;
import unicauca.taller05.aplicacion.out.FranjaHorariaRepositoryOut;
import unicauca.taller05.dominio.modelos.FranjaHoraria;
import unicauca.taller05.infraestructura.input.DTOPeticion.FranjaHorariaDTOPeticion;

import java.time.LocalTime;
import java.util.List;

import org.modelmapper.ModelMapper;

@AllArgsConstructor
public class FranjaHorariaService implements CUFranjaHorariaIn {

    private final FranjaHorariaFormaterOut  franjaFormater;
    private final FranjaHorariaRepositoryOut franjaRepository;
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
    public FranjaHoraria crearFranjaHoraria(FranjaHorariaDTOPeticion franjaHorariaACrear) {
        if (franjaHorariaACrear == null) {
            franjaFormater.retornarErrorParametroInvalido("La franja horaria no puede ser nula");
            return null;
        }

        LocalTime inicio = franjaHorariaACrear.getHoraInicio();
        LocalTime fin = franjaHorariaACrear.getHoraFin();

        if (!inicio.isBefore(fin)) {
            franjaFormater.retornarErrorParametroInvalido("La hora de inicio debe ser anterior a la hora de fin");
            return null;
        }

        List<FranjaHoraria> existentes = franjaRepository.obtenerFranjasHorariasPorCurso(franjaHorariaACrear.getIdCurso());
        
        boolean solapado = existentes.stream().anyMatch(f ->
            f.getDia().equals(franjaHorariaACrear.getDia()) &&
            (inicio.isBefore(f.getHoraFin()) && fin.isAfter(f.getHoraInicio()))
        );

        if (solapado) {
            franjaFormater.retornarErrorParametroInvalido("La franja horaria se solapa con otra existente en el mismo día");
            return null;
        }

        FranjaHoraria franjaHoraria = modelMapper.map(franjaHorariaACrear, FranjaHoraria.class);
        
        return franjaRepository.crearFranjaHoraria(franjaHoraria);
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
