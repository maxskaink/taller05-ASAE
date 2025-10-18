package unicauca.taller05.dominio.manejadores.validaciones;

import unicauca.taller05.aplicacion.out.DocenteFormaterOut;
import unicauca.taller05.aplicacion.out.DocenteRepositoryOut;
import unicauca.taller05.dominio.manejadores.ManejadorBase;
import unicauca.taller05.dominio.modelos.Docente;

import java.util.Optional;

public class ValidarDocenteCorreoUnico extends ManejadorBase<Docente> {

    private final DocenteRepositoryOut docenteRepositoryOut;
    private final DocenteFormaterOut docenteFormaterOut;

    public ValidarDocenteCorreoUnico(DocenteRepositoryOut docenteRepositoryOut, DocenteFormaterOut docenteFormaterOut) {
        this.docenteRepositoryOut = docenteRepositoryOut;
        this.docenteFormaterOut = docenteFormaterOut;
    }

    @Override
    public Optional<Docente> manejar(Docente docente) {
        if (docente == null) return Optional.empty();

        String correo = docente.getCorreo();
        if (correo != null && docenteRepositoryOut.existePorCorreo(correo)) {
            docenteFormaterOut.retornarErrorDocenteYaExiste("Ya existe un docente con el correo: " + correo);
            return Optional.empty();
        }

        return next(docente);
    }
}
