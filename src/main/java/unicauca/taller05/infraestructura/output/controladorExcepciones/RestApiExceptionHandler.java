// ...existing code...
package unicauca.taller05.infraestructura.output.controladorExcepciones;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;

import lombok.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import unicauca.taller05.infraestructura.output.controladorExcepciones.estructuraExcepciones.Error;
import unicauca.taller05.infraestructura.output.controladorExcepciones.estructuraExcepciones.CodigoError;
import unicauca.taller05.infraestructura.output.controladorExcepciones.estructuraExcepciones.ErrorUtils;
import unicauca.taller05.infraestructura.output.controladorExcepciones.excepcionesPropias.EntidadNoExisteException;
import unicauca.taller05.infraestructura.output.controladorExcepciones.excepcionesPropias.EntidadYaExisteException;
import unicauca.taller05.infraestructura.output.controladorExcepciones.excepcionesPropias.ReglaNegocioException;
import unicauca.taller05.infraestructura.output.controladorExcepciones.excepcionesPropias.ParametroInvalidoException;
import unicauca.taller05.infraestructura.output.controladorExcepciones.excepcionesPropias.OperacionInvalidaException;
import unicauca.taller05.infraestructura.output.controladorExcepciones.excepcionesPropias.ConflictoHorarioException;

@ControllerAdvice
public class RestApiExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Error> handleGenericException(final HttpServletRequest req,
                                                        final Exception ex, final Locale locale) {
        final Error error = ErrorUtils
                .crearError(CodigoError.ERROR_GENERICO.getCodigo(),
                        CodigoError.ERROR_GENERICO.getLlaveMensaje(),
                        HttpStatus.INTERNAL_SERVER_ERROR.value())
                .setUrl(req.getRequestURL().toString()).setMetodo(req.getMethod());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(EntidadYaExisteException.class)
    public ResponseEntity<Error> handleEntidadYaExiste(final HttpServletRequest req,
                                                       final EntidadYaExisteException ex) {
        final Error error = ErrorUtils
                .crearError(CodigoError.ENTIDAD_YA_EXISTE.getCodigo(),
                        String.format("%s, %s", CodigoError.ENTIDAD_YA_EXISTE.getLlaveMensaje(), ex.getMessage()),
                        HttpStatus.NOT_ACCEPTABLE.value())
                .setUrl(req.getRequestURL().toString()).setMetodo(req.getMethod());
        return new ResponseEntity<>(error, HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(ReglaNegocioException.class)
    public ResponseEntity<Error> handleReglaNegocio(final HttpServletRequest req,
                                                    final ReglaNegocioException ex, final Locale locale) {
        final Error error = ErrorUtils
                .crearError(CodigoError.VIOLACION_REGLA_DE_NEGOCIO.getCodigo(), ex.formatException(),
                        HttpStatus.BAD_REQUEST.value())
                .setUrl(req.getRequestURL().toString()).setMetodo(req.getMethod());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EntidadNoExisteException.class)
    public ResponseEntity<Error> handleEntidadNoExiste(final HttpServletRequest req,
                                                       final EntidadNoExisteException ex, final Locale locale) {
        final Error error = ErrorUtils
                .crearError(CodigoError.ENTIDAD_NO_ENCONTRADA.getCodigo(),
                        String.format("%s, %s", CodigoError.ENTIDAD_NO_ENCONTRADA.getLlaveMensaje(), ex.getMessage()),
                        HttpStatus.NOT_FOUND.value())
                .setUrl(req.getRequestURL().toString()).setMetodo(req.getMethod());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ParametroInvalidoException.class)
    public ResponseEntity<Error> handleParametroInvalido(final HttpServletRequest req,
                                                         final ParametroInvalidoException ex) {
        final Error error = ErrorUtils
                .crearError(CodigoError.PARAMETRO_INVALIDO.getCodigo(),
                        String.format("%s, %s", CodigoError.PARAMETRO_INVALIDO.getLlaveMensaje(), ex.getMessage()),
                        HttpStatus.BAD_REQUEST.value())
                .setUrl(req.getRequestURL().toString()).setMetodo(req.getMethod());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<Map<String, String>> handleBindException(BindException ex) {
        return getMapResponseEntity(ex);
    }

    @NonNull
    private ResponseEntity<Map<String, String>> getMapResponseEntity(BindException ex) {
        Map<String, String> errores = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String campo = ((FieldError) error).getField();
            String mensajeDeError = error.getDefaultMessage();
            errores.put(campo, mensajeDeError);
        });
        return new ResponseEntity<>(errores, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(OperacionInvalidaException.class)
    public ResponseEntity<Error> handleOperacionInvalida(final HttpServletRequest req,
                                                         final OperacionInvalidaException ex) {
        final Error error = ErrorUtils
                .crearError(CodigoError.OPERACION_INVALIDA.getCodigo(),
                        String.format("%s, %s", CodigoError.OPERACION_INVALIDA.getLlaveMensaje(), ex.getMessage()),
                        HttpStatus.BAD_REQUEST.value())
                .setUrl(req.getRequestURL().toString()).setMetodo(req.getMethod());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConflictoHorarioException.class)
    public ResponseEntity<Error> handleConflictoHorario(final HttpServletRequest req,
                                                        final ConflictoHorarioException ex) {
        final Error error = ErrorUtils
                .crearError(CodigoError.CONFLICTO_HORARIO.getCodigo(),
                        String.format("%s, %s", CodigoError.CONFLICTO_HORARIO.getLlaveMensaje(), ex.getMessage()),
                        HttpStatus.CONFLICT.value())
                .setUrl(req.getRequestURL().toString()).setMetodo(req.getMethod());
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        return getMapResponseEntity(ex);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    ResponseEntity<String> handleConstraintViolationException(ConstraintViolationException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
