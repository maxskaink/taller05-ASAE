package unicauca.taller05.dominio.manejadores.validaciones;

import unicauca.taller05.aplicacion.out.FranjaHorariaFormaterOut;
import unicauca.taller05.aplicacion.out.FranjaHorariaRepositoryOut;
import unicauca.taller05.dominio.manejadores.ManejadorBase;
import unicauca.taller05.dominio.modelos.FranjaHoraria;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public class ValidarDocenteLibre extends ManejadorBase<FranjaHoraria> {

    private final FranjaHorariaRepositoryOut franjaRepositoryOut;
    private final FranjaHorariaFormaterOut franjaFormaterOut;

    public ValidarDocenteLibre(FranjaHorariaRepositoryOut franjaRepositoryOut, FranjaHorariaFormaterOut franjaFormaterOut) {
        this.franjaRepositoryOut = franjaRepositoryOut;
        this.franjaFormaterOut = franjaFormaterOut;
    }

    @Override
    public Optional<FranjaHoraria> manejar(FranjaHoraria franjaHoraria) {
        if (franjaHoraria == null) return Optional.empty();

        if (franjaHoraria.getCurso() == null || franjaHoraria.getCurso().getDocentes() == null) {
            franjaFormaterOut.retornarErrorParametroInvalido("La franja debe tener un curso con docentes asignados");
            return Optional.empty();
        }

        DayOfWeek dia = convertirDiaADayOfWeek(franjaHoraria.getDia());
        LocalTime inicio = franjaHoraria.getHoraInicio();
        LocalTime fin = franjaHoraria.getHoraFin();

        
        for (var docente : franjaHoraria.getCurso().getDocentes()) {
            List<FranjaHoraria> franjasOcupadas = franjaRepositoryOut.obtenerFranjasOcupadasPorDocente(
                dia, inicio, fin, docente.getId()
            );

            if (!franjasOcupadas.isEmpty()) {
                franjaFormaterOut.retornarErrorConflictoDeHorario(
                    "El docente " + docente.getId()  + 
                    " ya tiene clases programadas en ese horario"
                );
                return Optional.empty();
            }
        }

        return next(franjaHoraria);
    }

    private DayOfWeek convertirDiaADayOfWeek(String dia) {
        return switch (dia) {
            case "Lunes" -> DayOfWeek.MONDAY;
            case "Martes" -> DayOfWeek.TUESDAY;
            case "Miercoles" -> DayOfWeek.WEDNESDAY;
            case "Jueves" -> DayOfWeek.THURSDAY;
            case "Viernes" -> DayOfWeek.FRIDAY;
            case "Sabado" -> DayOfWeek.SATURDAY;
            default -> throw new IllegalArgumentException("Día inválido: " + dia);
        };
    }
}