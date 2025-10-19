package unicauca.taller05.infraestructura.output.persistencia.repositorios;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import unicauca.taller05.aplicacion.out.FranjaHorariaRepositoryOut;
import unicauca.taller05.dominio.modelos.FranjaHoraria;
import unicauca.taller05.infraestructura.output.persistencia.entidades.FranjaHorariaEntity;
import unicauca.taller05.infraestructura.output.persistencia.repositoriosJPA.FranjaHorarioRepository;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class FranjaRepositoryAdapter implements FranjaHorariaRepositoryOut {

    private final FranjaHorarioRepository franjaHorarioRepositoryJPA;
    private final ModelMapper modelMapper;
    @Qualifier("franjaMapperWithoutCourse")
    private final ModelMapper modelMapperWithoutCourse;

    public FranjaRepositoryAdapter(
            FranjaHorarioRepository franjaHorarioRepositoryJPA,
            ModelMapper modelMapper,
            @Qualifier("franjaMapperWithoutCourse") ModelMapper modelMapperWithoutCourse) {
        this.franjaHorarioRepositoryJPA = franjaHorarioRepositoryJPA;
        this.modelMapper = modelMapper;
        this.modelMapperWithoutCourse = modelMapperWithoutCourse;
    }

    @Override
    public FranjaHoraria crearFranjaHoraria(FranjaHoraria franjaHoraria) {
        var entity = modelMapper.map(franjaHoraria, FranjaHorariaEntity.class);
        var guardada = franjaHorarioRepositoryJPA.save(entity);
        return modelMapper.map(guardada, FranjaHoraria.class);
    }

    @Override
    public List<FranjaHoraria> obtenerFranjasHorariasPorCurso(Integer idCurso) {

        return franjaHorarioRepositoryJPA.findByCursoId(idCurso).stream()
                .map(entity->modelMapperWithoutCourse.map(entity, FranjaHoraria.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<FranjaHoraria> obtenerFranjasHorariasPorDocente(Integer idDocente) {
        return franjaHorarioRepositoryJPA.obtenerFranjasPorDocenteIdConJoin(idDocente).stream()
                .map(entity -> modelMapper.map(entity, FranjaHoraria.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<FranjaHoraria> obtenerFranjasHorariasPorCursoJPQL(Integer idCurso) {
        return franjaHorarioRepositoryJPA.obtenerFranjasPorIdCursoConJoin(idCurso).stream()
                .map(entity -> modelMapperWithoutCourse.map(entity, FranjaHoraria.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<FranjaHoraria> obtenerFranjasOcupadasPorEspacio(DayOfWeek dia, LocalTime horaInicio, LocalTime horaFin, Integer idEspacioFisico) {
        return List.of();
    }

    @Override
    public List<FranjaHoraria> obtenerFranjasOcupadasPorDocente(DayOfWeek dia, LocalTime horaInicio, LocalTime horaFin, Integer idDocente) {
        return List.of();
    }
    @Transactional
    @Override
    public FranjaHoraria eliminarFranjaHorariaPorId(Integer cursoId) {
        List<FranjaHorariaEntity> franjas = franjaHorarioRepositoryJPA.findByCursoId(cursoId);
        franjaHorarioRepositoryJPA.eliminarFranjasPorCurso(cursoId);

        return franjas.stream()
                .findFirst()
                .map(f -> modelMapper.map(f, FranjaHoraria.class))
                .orElse(null);
    }
}
