package unicauca.taller05.dominio.manejadores.validaciones;

import unicauca.taller05.aplicacion.out.CursoRepositoryOut;
import unicauca.taller05.aplicacion.out.FranjaHorariaFormaterOut;
import unicauca.taller05.dominio.manejadores.ManejadorBase;
import unicauca.taller05.dominio.modelos.FranjaHoraria;
import unicauca.taller05.infraestructura.output.persistencia.repositoriosJPA.CursoRepository;

import java.util.Optional;

public class ValidarExistenciaCurso extends ManejadorBase<FranjaHoraria> {

    private final FranjaHorariaFormaterOut franjaFormaterOut;
    private final CursoRepositoryOut cursoRepositoryOut;

    public ValidarExistenciaCurso(FranjaHorariaFormaterOut franjaFormaterOut, CursoRepositoryOut cursoRepositoryOut) {
        this.franjaFormaterOut = franjaFormaterOut;
        this.cursoRepositoryOut = cursoRepositoryOut;
    }

    @Override
    public Optional<FranjaHoraria> manejar(FranjaHoraria franjaHoraria) {
        if (franjaHoraria == null) return Optional.empty();

        // Validar si el curso existe en la base de datos
        if (!cursoRepositoryOut.existsById(franjaHoraria.getCurso().getId())) {
            franjaFormaterOut.retornarErrorParametroInvalido("El curso con ID " + franjaHoraria.getCurso().getId() + " no existe en la base de datos");
            return Optional.empty();
        }

        return next(franjaHoraria);
    }
}