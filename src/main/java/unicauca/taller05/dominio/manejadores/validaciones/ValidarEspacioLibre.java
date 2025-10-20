package unicauca.taller05.dominio.manejadores.validaciones;

import unicauca.taller05.aplicacion.out.FranjaHorariaFormaterOut;
import unicauca.taller05.aplicacion.out.FranjaHorariaRepositoryOut;
import unicauca.taller05.dominio.manejadores.ManejadorBase;
import unicauca.taller05.dominio.modelos.FranjaHoraria;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public class ValidarEspacioLibre extends ManejadorBase<FranjaHoraria> {

    private final FranjaHorariaRepositoryOut franjaRepositoryOut;
    private final FranjaHorariaFormaterOut franjaFormaterOut;

    public ValidarEspacioLibre(FranjaHorariaRepositoryOut franjaRepositoryOut, FranjaHorariaFormaterOut franjaFormaterOut) {
        this.franjaRepositoryOut = franjaRepositoryOut;
        this.franjaFormaterOut = franjaFormaterOut;
    }

    @Override
    public Optional<FranjaHoraria> manejar(FranjaHoraria franjaHoraria) {
        if (franjaHoraria == null) return Optional.empty();

        if (franjaHoraria.getEspacioFisico() == null) {
            franjaFormaterOut.retornarErrorParametroInvalido("La franja debe tener un espacio físico asignado");
            return Optional.empty();
        }

        DayOfWeek dia = convertirDiaADayOfWeek(franjaHoraria.getDia());
        LocalTime inicio = franjaHoraria.getHoraInicio();
        LocalTime fin = franjaHoraria.getHoraFin();
        Integer idEspacioFisico = franjaHoraria.getEspacioFisico().getId();

        List<FranjaHoraria> franjasOcupadas = franjaRepositoryOut.obtenerFranjasOcupadasPorEspacio(
            dia, inicio, fin, idEspacioFisico
        );

        if (!franjasOcupadas.isEmpty()) {
            franjaFormaterOut.retornarErrorConflictoDeHorario(
                "El espacio físico  ya está ocupado en ese horario"
            );
            return Optional.empty();
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