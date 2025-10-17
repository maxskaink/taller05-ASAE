package unicauca.taller05.infraestructura.output.controladorExcepciones.excepcionesPropias;

import unicauca.taller05.infraestructura.output.controladorExcepciones.estructuraExcepciones.CodigoError;

public class OperacionInvalidaException extends RuntimeException {
    private final String llaveMensaje;
    private final String codigo;

    public OperacionInvalidaException(CodigoError code) {
        super(code.getCodigo());
        this.llaveMensaje = code.getLlaveMensaje();
        this.codigo = code.getCodigo();
    }

    public OperacionInvalidaException(final String message) {
        super(message);
        this.llaveMensaje = CodigoError.OPERACION_INVALIDA.getLlaveMensaje();
        this.codigo = CodigoError.OPERACION_INVALIDA.getCodigo();
    }
}
