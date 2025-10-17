package unicauca.taller05.infraestructura.output.formateador;

import org.springframework.stereotype.Service;
import unicauca.taller05.aplicacion.out.CursoFormaterOut;
import unicauca.taller05.infraestructura.output.controladorExcepciones.excepcionesPropias.*;

@Service
public class CursoFormateadorResultadosImpl implements CursoFormaterOut {
    @Override
    public void retornarErrorCursoNoEncontrado(String mensaje) {
        throw new EntidadNoExisteException(mensaje);
    }

    @Override
    public void retornarErrorReglaNegocio(String mensaje) {
        throw new ReglaNegocioException(mensaje);
    }

    @Override
    public void retornarErrorCursoYaExiste(String mensaje) {
        throw new EntidadYaExisteException(mensaje);
    }

    @Override
    public void retornarErrorOperacionInvalida(String mensaje) {
        throw new OperacionInvalidaException(mensaje);
    }

    @Override
    public void retornarErrorParametroInvalido(String mensaje) {
        throw new ParametroInvalidoException(mensaje);
    }
}
