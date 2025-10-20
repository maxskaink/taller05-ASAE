package unicauca.taller05.dominio.manejadores.validaciones;

import unicauca.taller05.aplicacion.out.FranjaHorariaFormaterOut;
import unicauca.taller05.dominio.manejadores.ManejadorBase;
import unicauca.taller05.dominio.modelos.FranjaHoraria;

import java.time.LocalTime;
import java.util.Optional;

public class ValidarDiaYHorarioValido extends ManejadorBase<FranjaHoraria> {

    private final FranjaHorariaFormaterOut franjaFormaterOut;
    private final LocalTime HORA_MIN = LocalTime.of(7, 0);  
    private final LocalTime HORA_MAX = LocalTime.of(18, 0); 

    public ValidarDiaYHorarioValido(FranjaHorariaFormaterOut franjaFormaterOut) {
        this.franjaFormaterOut = franjaFormaterOut;
    }

    @Override
    public Optional<FranjaHoraria> manejar(FranjaHoraria franjaHoraria) {
        if (franjaHoraria == null) return Optional.empty();

        LocalTime inicio = franjaHoraria.getHoraInicio();
        LocalTime fin = franjaHoraria.getHoraFin();

        if (inicio == null || fin == null) {
            franjaFormaterOut.retornarErrorParametroInvalido("Las horas de inicio y fin no pueden ser nulas");
            return Optional.empty();
        }

        if (!inicio.isBefore(fin)) {
            franjaFormaterOut.retornarErrorParametroInvalido("La hora de inicio debe ser anterior a la hora de fin");
            return Optional.empty();
        }

        if (inicio.isBefore(HORA_MIN) || fin.isAfter(HORA_MAX)) {
            franjaFormaterOut.retornarErrorParametroInvalido("El horario debe estar entre 7:00 AM y 6:00 PM");
            return Optional.empty();
        }

        return next(franjaHoraria);
    }
}