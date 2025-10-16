package unicauca.taller05.infraestructura.output.controladorExcepciones.excepcionesPropias;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import unicauca.taller05.infraestructura.output.controladorExcepciones.estructuraExcepciones.CodigoError;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public abstract class FranjaHorariaRuntimeException extends RuntimeException{
    protected CodigoError codigoError;

    public abstract String formatException();
}
