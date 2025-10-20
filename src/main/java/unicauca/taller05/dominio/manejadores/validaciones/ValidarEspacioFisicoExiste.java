package unicauca.taller05.dominio.manejadores.validaciones;

import unicauca.taller05.aplicacion.out.EspacioRepositoryOut;
import unicauca.taller05.aplicacion.out.FranjaHorariaFormaterOut;
import unicauca.taller05.dominio.manejadores.ManejadorBase;
import unicauca.taller05.dominio.modelos.EspacioFisico;
import unicauca.taller05.dominio.modelos.FranjaHoraria;

import java.util.Optional;

public class ValidarEspacioFisicoExiste extends ManejadorBase<FranjaHoraria> {

    private final FranjaHorariaFormaterOut franjaFormaterOut;
    private final EspacioRepositoryOut espacioFisicoRepository;

    public ValidarEspacioFisicoExiste(FranjaHorariaFormaterOut franjaFormaterOut, EspacioRepositoryOut espacioFisicoRepository) {
        this.franjaFormaterOut = franjaFormaterOut;
        this.espacioFisicoRepository = espacioFisicoRepository;
    }

    @Override
    public Optional<FranjaHoraria> manejar(FranjaHoraria franjaHoraria) {
        if (franjaHoraria == null) return Optional.empty();

        if (franjaHoraria.getEspacioFisico() == null) {
            franjaFormaterOut.retornarErrorParametroInvalido("El espacio físico no puede ser nulo");
            return Optional.empty();
        }

        if (!espacioFisicoRepository.existsById(franjaHoraria.getEspacioFisico().getId())) {
            franjaFormaterOut.retornarErrorParametroInvalido("El espacio físico con ID " + franjaHoraria.getEspacioFisico().getId() + " no existe en la base de datos");
            return Optional.empty();
        }

        return next(franjaHoraria);
    }
}
