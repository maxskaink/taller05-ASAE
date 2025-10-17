package unicauca.taller05.infraestructura.output.controladorExcepciones.excepcionesPropias;

import lombok.Getter;
import unicauca.taller05.infraestructura.output.controladorExcepciones.estructuraExcepciones.CodigoError;

@Getter
public class ParametroInvalidoException extends RuntimeException {

    private final String llaveMensaje;
    private final String codigo;

    public ParametroInvalidoException(CodigoError code) {
        super(code.getCodigo());
        this.llaveMensaje = code.getLlaveMensaje();
        this.codigo = code.getCodigo();
    }

    public ParametroInvalidoException(final String message) {
        super(message);
        this.llaveMensaje = CodigoError.PARAMETRO_INVALIDO.getLlaveMensaje();
        this.codigo = CodigoError.PARAMETRO_INVALIDO.getCodigo();
    }
}
