package unicauca.taller05.infraestructura.output.formateador;

import org.springframework.stereotype.Service;
import unicauca.taller05.aplicacion.out.DocenteFormaterOut;
import unicauca.taller05.infraestructura.output.controladorExcepciones.excepcionesPropias.EntidadNoExisteException;
import unicauca.taller05.infraestructura.output.controladorExcepciones.excepcionesPropias.EntidadYaExisteException;
import unicauca.taller05.infraestructura.output.controladorExcepciones.excepcionesPropias.ParametroInvalidoException;
import unicauca.taller05.infraestructura.output.controladorExcepciones.excepcionesPropias.ReglaNegocioException;

@Service
public class DocenteFormateadorResultadosImpl implements DocenteFormaterOut {
    @Override
    public void retornarErrorDocenteYaExiste(String mensaje) {
        throw new EntidadYaExisteException(mensaje);
    }

    @Override
    public void retornarErrorEntidadNoExiste(String mensaje) {
        throw new EntidadNoExisteException(mensaje);
    }

    @Override
    public void retornarErrorReglaDeNegocio(String mensaje) {
        throw new ReglaNegocioException(mensaje);
    }

    @Override
    public void retornarErrorParametroInvalido(String mensaje) {
        throw new ParametroInvalidoException(mensaje);
    }
}
