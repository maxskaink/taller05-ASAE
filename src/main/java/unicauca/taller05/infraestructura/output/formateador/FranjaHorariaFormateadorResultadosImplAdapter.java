package unicauca.taller05.infraestructura.output.formateador;

import org.springframework.stereotype.Service;
import unicauca.taller05.aplicacion.out.FranjaHorariaFormaterOut;
import unicauca.taller05.infraestructura.output.controladorExcepciones.excepcionesPropias.*;

/**
 * Implementación del puerto de salida que lanza las excepciones correspondientes
 * a los errores de negocio y de validación relacionados con la entidad FranjaHoraria.
 */
@Service
public class FranjaHorariaFormateadorResultadosImplAdapter implements FranjaHorariaFormaterOut {

    @Override
    public void retornarErrorFranjaYaExiste(String mensaje) {
        throw new EntidadYaExisteException(mensaje);
    }

    @Override
    public void retornarErrorEntidadNoExiste(String mensaje) {
        throw new EntidadNoExisteException(mensaje);
    }

    @Override
    public void retornarErrorConflictoDeHorario(String mensaje) {
        throw new ConflictoHorarioException(mensaje);
    }

    @Override
    public void retornarErrorReglaDeNegocio(String mensaje) {
        throw new ReglaNegocioException(mensaje);
    }

    @Override
    public void retornarErrorEliminarFranja(String mensaje) {
        throw new OperacionInvalidaException(mensaje);
    }

    @Override
    public void retornarErrorParametroInvalido(String mensaje) {
        throw new ParametroInvalidoException(mensaje);
    }
}
