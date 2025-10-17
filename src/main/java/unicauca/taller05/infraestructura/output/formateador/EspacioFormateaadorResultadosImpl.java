package unicauca.taller05.infraestructura.output.formateador;

import org.springframework.stereotype.Service;
import unicauca.taller05.aplicacion.out.EspacioFormaterOut;
import unicauca.taller05.infraestructura.output.controladorExcepciones.excepcionesPropias.EntidadNoExisteException;
import unicauca.taller05.infraestructura.output.controladorExcepciones.excepcionesPropias.EntidadYaExisteException;
import unicauca.taller05.infraestructura.output.controladorExcepciones.excepcionesPropias.ParametroInvalidoException;
import unicauca.taller05.infraestructura.output.controladorExcepciones.excepcionesPropias.ReglaNegocioException;

@Service
public class EspacioFormateaadorResultadosImpl implements EspacioFormaterOut{
    @Override
    public void retornarErrorEspacioYaExiste(String mensaje) {
        throw new EntidadYaExisteException(mensaje);
    }

    @Override
    public void retornarErrorEspacioNoEncontrado(String mensaje) {
        throw new EntidadNoExisteException(mensaje);
    }

    @Override
    public void retornarErrorReglaNegocio(String mensaje) {
        throw new ReglaNegocioException(mensaje);
    }

    @Override
    public void retornarErrorParametroInvalido(String mensaje) {
        throw new ParametroInvalidoException(mensaje);
    }
}
