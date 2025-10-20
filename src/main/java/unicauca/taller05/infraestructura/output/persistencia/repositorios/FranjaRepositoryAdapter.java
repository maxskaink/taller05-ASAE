package unicauca.taller05.infraestructura.output.persistencia.repositorios;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
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
@AllArgsConstructor
public class FranjaRepositoryAdapter implements FranjaHorariaRepositoryOut {

    private final FranjaHorarioRepository franjaHorarioRepositoryJPA;
    private final ModelMapper modelMapper;

    @Override
    public FranjaHoraria crearFranjaHoraria(FranjaHoraria franjaHoraria) {
        var entity = modelMapper.map(franjaHoraria, FranjaHorariaEntity.class);
        var guardada = franjaHorarioRepositoryJPA.save(entity);
        return modelMapper.map(guardada, FranjaHoraria.class);
    }

    @Override
    public List<FranjaHoraria> obtenerFranjasHorariasPorCurso(Integer idCurso) {
        var franjas = franjaHorarioRepositoryJPA.obtenerFranjasPorIdCursoConJoin(idCurso);

        // Log para verificar los datos recuperados
        System.out.println("Debug: Franjas recuperadas para el curso ID: " + idCurso);
        franjas.forEach(f -> {
            System.out.println("Franja ID: " + f.getId());
            System.out.println("Curso: " + f.getCurso());
            if (f.getCurso() != null) {
                System.out.println("Curso Nombre: " + f.getCurso().getNombre());
                System.out.println("Curso Asignatura: " + f.getCurso().getAsignatura());
                System.out.println("Curso Docentes: " + f.getCurso().getDocentes());
            }
        });

        return franjas.stream()
                .map(entity -> modelMapper.map(entity, FranjaHoraria.class))
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
                .map(entity -> modelMapper.map(entity, FranjaHoraria.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<FranjaHoraria> obtenerFranjasOcupadasPorEspacio(DayOfWeek dia, LocalTime horaInicio, LocalTime horaFin, Integer idEspacioFisico) {
    String diaEnEspanol = convertirDiaADiaEnEspanol(dia);
    return franjaHorarioRepositoryJPA.obtenerFranjasOcupadasPorEspacio(diaEnEspanol, horaInicio, horaFin, idEspacioFisico).stream()
            .map(entity -> modelMapper.map(entity, FranjaHoraria.class))
            .collect(Collectors.toList());
    }
    private String convertirDiaADiaEnEspanol(DayOfWeek dia) {
        return switch (dia) {
            case MONDAY -> "Lunes";
            case TUESDAY -> "Martes";
            case WEDNESDAY -> "Miercoles";
            case THURSDAY -> "Jueves";
            case FRIDAY -> "Viernes";
            case SATURDAY -> "Sabado";
            default -> throw new IllegalArgumentException("Día inválido: " + dia);
        };
    }
    @Override
    public List<FranjaHoraria> obtenerFranjasOcupadasPorDocente(DayOfWeek dia, LocalTime horaInicio, LocalTime horaFin, Integer idDocente) {
        String diaEnEspanol = convertirDiaADiaEnEspanol(dia);
        return franjaHorarioRepositoryJPA.obtenerFranjasOcupadasPorDocente(diaEnEspanol, horaInicio, horaFin, idDocente).stream()
                .map(entity -> modelMapper.map(entity, FranjaHoraria.class))
                .collect(Collectors.toList());
    }
    @Transactional
    @Override
    public FranjaHoraria eliminarFranjaHorariaPorId(Integer cursoId) {
        List<FranjaHorariaEntity> franjas = franjaHorarioRepositoryJPA.obtenerFranjasPorIdCursoConJoin(cursoId);
        franjaHorarioRepositoryJPA.eliminarFranjasPorCurso(cursoId);

        return franjas.stream()
                .findFirst()
                .map(f -> modelMapper.map(f, FranjaHoraria.class))
                .orElse(null);
    }
}
