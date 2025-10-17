package unicauca.taller05.infraestructura.output.controladorExcepciones.excepcionesPropias;

import unicauca.taller05.infraestructura.output.controladorExcepciones.estructuraExcepciones.CodigoError;

public class ConflictoHorarioException extends RuntimeException {
    private final String llaveMensaje;
    private final String codigo;

    public ConflictoHorarioException(CodigoError code) {
        super(code.getCodigo());
        this.llaveMensaje = code.getLlaveMensaje();
        this.codigo = code.getCodigo();
    }

    public ConflictoHorarioException(final String message) {
        super(message);
        this.llaveMensaje = CodigoError.CONFLICTO_HORARIO.getLlaveMensaje();
        this.codigo = CodigoError.CONFLICTO_HORARIO.getCodigo();
    }
}
