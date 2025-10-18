package unicauca.taller05.dominio.casosUso;

import unicauca.taller05.aplicacion.in.CUDocenteIn;
import unicauca.taller05.aplicacion.out.DocenteFormaterOut;
import unicauca.taller05.aplicacion.out.DocenteRepositoryOut;
import unicauca.taller05.dominio.manejadores.validaciones.ValidarDocenteCorreoUnico;
import unicauca.taller05.dominio.modelos.Docente;

import java.util.Optional;

public class DocenteService implements CUDocenteIn {

    private final DocenteFormaterOut docenteFormaterOut;
    private final DocenteRepositoryOut docenteRepositoryOut;

    public DocenteService(DocenteFormaterOut docenteFormaterOut, DocenteRepositoryOut docenteRepositoryOut) {
        this.docenteFormaterOut = docenteFormaterOut;
        this.docenteRepositoryOut = docenteRepositoryOut;
    }

    @Override
    public Docente crearDocente(Docente docenteACrear) {
        // Construir cadena de validaciones
        var validarCorreo = new ValidarDocenteCorreoUnico(docenteRepositoryOut, docenteFormaterOut);

        Optional<Docente> resultado = validarCorreo.manejar(docenteACrear);
        System.out.println("Resultado de la validación: " + docenteACrear);
        if (resultado.isPresent()) {
            return docenteRepositoryOut.crearDocente(docenteACrear);
        }

        return null;
    }
}
